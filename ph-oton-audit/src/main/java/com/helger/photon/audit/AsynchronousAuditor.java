/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.photon.audit;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.concurrent.BasicThreadFactory;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.concurrent.collector.ConcurrentCollectorMultiple;
import com.helger.commons.concurrent.collector.IConcurrentPerformer;
import com.helger.commons.state.EChange;
import com.helger.security.authentication.subject.user.ICurrentUserIDProvider;

/**
 * The class handles audit items asynchronously. If a new audit item is to be
 * handled it is put into the {@link IConcurrentPerformer}'s queue as provided
 * in the constructor.<br>
 * Please ensure to call {@link #stop()} if this auditor is no longer used, so
 * that the created {@link ExecutorService} can be gracefully shutdown.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class AsynchronousAuditor extends AbstractAuditor
{
  // Just to have custom named threads....
  private static final ThreadFactory s_aThreadFactory = new BasicThreadFactory.Builder ().setNamingPattern ("AsyncAuditor")
                                                                                         .setDaemon (true)
                                                                                         .build ();
  private static final Logger LOGGER = LoggerFactory.getLogger (AsynchronousAuditor.class);

  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  private final ConcurrentCollectorMultiple <IAuditItem> m_aCollector;
  private final ExecutorService m_aSenderThreadPool;

  private static final class MyCollector extends ConcurrentCollectorMultiple <IAuditItem>
  {
    public MyCollector (@Nonnull final IConcurrentPerformer <List <IAuditItem>> aPerformer)
    {
      setPerformer (aPerformer);
    }
  }

  public AsynchronousAuditor (@Nonnull final ICurrentUserIDProvider aUserIDProvider,
                              @Nonnull final IConcurrentPerformer <List <IAuditItem>> aPerformer)
  {
    super (aUserIDProvider);
    ValueEnforcer.notNull (aPerformer, "Performer");

    m_aCollector = new MyCollector (aPerformer);
    m_aSenderThreadPool = Executors.newSingleThreadExecutor (s_aThreadFactory);
    m_aSenderThreadPool.submit (m_aCollector::collect);
  }

  @Override
  protected void handleAuditItem (@Nonnull final IAuditItem aAuditItem)
  {
    ValueEnforcer.notNull (aAuditItem, "AuditItem");

    m_aRWLock.writeLockedGet ( () -> m_aCollector.queueObject (aAuditItem));
  }

  @Nonnegative
  public int getQueueLength ()
  {
    return m_aRWLock.readLockedInt (m_aCollector::getQueueLength);
  }

  /**
   * When using this auditor, it is important to call this {@link #stop()}
   * method before shutdown. It avoids further queuing of objects and waits
   * until all items are handled. This method blocks until all remaining objects
   * are handled.
   *
   * @return {@link EChange#CHANGED} if the shutdown was performed,
   *         {@link EChange#UNCHANGED} if the auditor was already shut down.
   */
  @Nonnull
  public EChange stop ()
  {
    if (m_aRWLock.writeLockedBoolean ( () -> {
      // Check if the thread pool is already shut down
      if (m_aSenderThreadPool.isShutdown ())
        return true;

      // don't take any more actions
      m_aSenderThreadPool.shutdown ();

      // stop all specific queues
      m_aCollector.stopQueuingNewObjects ();

      final int nQueueLength = m_aCollector.getQueueLength ();
      if (nQueueLength > 0)
        LOGGER.info ("Stopping auditor queue with " + nQueueLength + " items");
      return false;
    }))
    {
      // Already shutdown!
      return EChange.UNCHANGED;
    }

    // Don't wait in a writeLock!
    try
    {
      while (!m_aSenderThreadPool.awaitTermination (1, TimeUnit.SECONDS))
      {
        // wait until we're done
      }
    }
    catch (final InterruptedException ex)
    {
      LOGGER.error ("Error stopping auditor queue", ex);
      Thread.currentThread ().interrupt ();
    }
    return EChange.CHANGED;
  }
}
