/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.security.audit;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.callback.IThrowingRunnableWithParameter;
import com.helger.commons.concurrent.ExtendedDefaultThreadFactory;
import com.helger.commons.concurrent.collector.ConcurrentCollectorMultiple;
import com.helger.commons.state.EChange;
import com.helger.photon.basic.security.login.ICurrentUserIDProvider;

/**
 * The class handles all audit items
 * 
 * @author Philip Helger
 */
@ThreadSafe
public class AsynchronousAuditor extends AbstractAuditor
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (AsynchronousAuditor.class);

  private final ReadWriteLock m_aRWLock = new ReentrantReadWriteLock ();
  private final ConcurrentCollectorMultiple <IAuditItem> m_aCollector;
  // Just to have custom named threads....
  private static final ThreadFactory s_aThreadFactory = new ExtendedDefaultThreadFactory ("AsyncAuditor");
  private final ExecutorService m_aSenderThreadPool;

  public AsynchronousAuditor (@Nonnull final ICurrentUserIDProvider aUserIDProvider,
                              @Nonnull final IThrowingRunnableWithParameter <List <IAuditItem>> aPerformer)
  {
    super (aUserIDProvider);
    ValueEnforcer.notNull (aPerformer, "Performer");

    m_aCollector = new ConcurrentCollectorMultiple <IAuditItem> (aPerformer);
    m_aSenderThreadPool = Executors.newSingleThreadExecutor (s_aThreadFactory);
    m_aSenderThreadPool.submit (m_aCollector);
  }

  @Override
  protected void handleAuditItem (@Nonnull final IAuditItem aAuditItem)
  {
    ValueEnforcer.notNull (aAuditItem, "AuditItem");

    m_aRWLock.writeLock ().lock ();
    try
    {
      m_aCollector.queueObject (aAuditItem);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  @Nonnegative
  public int getQueueLength ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aCollector.getQueueLength ();
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * When using this auditor, it is important to call this stop method before
   * shutdown. It avoids further queuing of objects and waits until all items
   * are handled. This method blocks until all remaining objects are handled.
   * 
   * @return {@link EChange#CHANGED} if the shutdown was performed,
   *         {@link EChange#UNCHANGED} if the auditor was already shut down.
   */
  @Nonnull
  public EChange stop ()
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      // Check if the thread pool is already shut down
      if (m_aSenderThreadPool.isShutdown ())
        return EChange.UNCHANGED;

      // don't take any more actions
      m_aSenderThreadPool.shutdown ();

      // stop all specific queues
      m_aCollector.stopQueuingNewObjects ();

      final int nQueueLength = m_aCollector.getQueueLength ();
      if (nQueueLength > 0)
        s_aLogger.info ("Stopping auditor queues with " + nQueueLength + " items");
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
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
      s_aLogger.error ("Error stopping auditor queue", ex);
    }
    return EChange.CHANGED;
  }
}
