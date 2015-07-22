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
package com.helger.photon.core.requesttrack;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.collection.CollectionHelper;
import com.helger.photon.core.app.error.InternalErrorBuilder;
import com.helger.web.scope.IRequestWebScope;

/**
 * The request time manager manages all currently running requests.
 *
 * @author Philip Helger
 * @since 4.0.0
 */
@ThreadSafe
public final class RequestTrackingManager
{
  public static final boolean DEFAULT_LONG_RUNNING_CHECK_ENABLED = true;
  public static final long DEFAULT_NOTIFICATION_MILLISECONDS = 30 * CGlobal.MILLISECONDS_PER_SECOND;
  public static final boolean DEFAULT_PARALLEL_RUNNING_REQUESTS_CHECK_ENABLED = true;
  public static final int DEFAULT_PARALLEL_RUNNING_REQUESTS_BARRIER = 60;

  private static final Logger s_aLogger = LoggerFactory.getLogger (RequestTrackingManager.class);

  private final ReadWriteLock m_aRWLock = new ReentrantReadWriteLock ();
  @GuardedBy ("m_aRWLock")
  private boolean m_bLongRunningCheckEnabled = DEFAULT_LONG_RUNNING_CHECK_ENABLED;
  @GuardedBy ("m_aRWLock")
  private long m_nLongRunningMilliSeconds = DEFAULT_NOTIFICATION_MILLISECONDS;
  @GuardedBy ("m_aRWLock")
  private boolean m_bParallelRunningRequestCheckEnabled = DEFAULT_PARALLEL_RUNNING_REQUESTS_CHECK_ENABLED;
  @GuardedBy ("m_aRWLock")
  private int m_nParallelRunningRequestBarrier = DEFAULT_PARALLEL_RUNNING_REQUESTS_BARRIER;
  @GuardedBy ("m_aRWLock")
  private boolean m_bParallelRunningRequestsAboveLimit = false;
  // Must be ordered!
  @GuardedBy ("m_aRWLock")
  private final Map <String, TrackedRequest> m_aOpenRequests = new LinkedHashMap <String, TrackedRequest> ();

  public RequestTrackingManager ()
  {}

  @Nonnull
  public RequestTrackingManager setLongRunningCheckEnabled (final boolean bLongRunningCheckEnabled)
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_bLongRunningCheckEnabled = bLongRunningCheckEnabled;
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    return this;
  }

  public boolean isLongRunningCheckEnabled ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_bLongRunningCheckEnabled;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  public RequestTrackingManager setNotificationMilliseconds (@Nonnegative final long nLongRunningMilliSeconds)
  {
    ValueEnforcer.isGT0 (nLongRunningMilliSeconds, "LongRunningMilliSeconds");

    m_aRWLock.writeLock ().lock ();
    try
    {
      m_nLongRunningMilliSeconds = nLongRunningMilliSeconds;
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    return this;
  }

  @Nonnegative
  public long getNotificationMilliseconds ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_nLongRunningMilliSeconds;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  public RequestTrackingManager setParallelRunningRequestCheckEnabled (final boolean bParallelRunningRequestCheckEnabled)
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_bParallelRunningRequestCheckEnabled = bParallelRunningRequestCheckEnabled;
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    return this;
  }

  public boolean isParallelRunningRequestCheckEnabled ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_bParallelRunningRequestCheckEnabled;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  public RequestTrackingManager setParallelRunningRequestBarrier (@Nonnegative final int nParallelRunningRequestBarrier)
  {
    ValueEnforcer.isGT0 (nParallelRunningRequestBarrier, "ParallelRunningRequestBarrier");

    m_aRWLock.writeLock ().lock ();
    try
    {
      m_nParallelRunningRequestBarrier = nParallelRunningRequestBarrier;
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    return this;
  }

  @Nonnegative
  public int getParallelRunningRequestBarrier ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_nParallelRunningRequestBarrier;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public void addRequest (@Nonnull @Nonempty final String sRequestID,
                          @Nonnull final IRequestWebScope aRequestScope,
                          @Nonnull final CallbackList <IParallelRunningRequestCallback> aCallbacks)
  {
    boolean bNotifyOnParallelRequests = false;
    List <TrackedRequest> aOpenRequests = null;
    m_aRWLock.writeLock ().lock ();
    try
    {
      if (m_aOpenRequests.put (sRequestID, new TrackedRequest (sRequestID, aRequestScope)) != null)
      {
        // Should never happen
        s_aLogger.error ("Request ID '" + sRequestID + "' is already registered!");
      }
      if (m_bParallelRunningRequestCheckEnabled && m_aOpenRequests.size () >= m_nParallelRunningRequestBarrier)
      {
        // Grab directly here to avoid another locked section
        bNotifyOnParallelRequests = true;
        aOpenRequests = CollectionHelper.newList (m_aOpenRequests.values ());
        // Remember that we're above limit
        m_bParallelRunningRequestsAboveLimit = true;
      }
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }

    if (bNotifyOnParallelRequests)
    {
      // Invoke callbacks "above limit"
      try
      {
        for (final IParallelRunningRequestCallback aCallback : aCallbacks.getAllCallbacks ())
          aCallback.onParallelRunningRequests (aOpenRequests.size (), aOpenRequests);
      }
      catch (final Throwable t)
      {
        new InternalErrorBuilder ().setThrowable (t).addCustomData ("context", "parallel-running-requests").handle ();
      }
    }
  }

  public void removeRequest (@Nonnull @Nonempty final String sRequestID,
                             @Nonnull final CallbackList <IParallelRunningRequestCallback> aCallbacks)
  {
    boolean bNotifyOnParallelRequestsBelowLimit = false;
    m_aRWLock.writeLock ().lock ();
    try
    {
      if (m_aOpenRequests.remove (sRequestID) == null)
      {
        // Should never happen
        s_aLogger.error ("Failed to remove internal request with ID '" + sRequestID + "'");
      }
      if (m_bParallelRunningRequestCheckEnabled &&
          m_bParallelRunningRequestsAboveLimit &&
          m_aOpenRequests.size () < m_nParallelRunningRequestBarrier)
      {
        // Back to normal!
        m_bParallelRunningRequestsAboveLimit = false;
        bNotifyOnParallelRequestsBelowLimit = true;
      }
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }

    if (bNotifyOnParallelRequestsBelowLimit)
    {
      // Invoke callbacks "below limit again"
      try
      {
        for (final IParallelRunningRequestCallback aCallback : aCallbacks.getAllCallbacks ())
          aCallback.onParallelRunningRequestsBelowLimit ();
      }
      catch (final Throwable t)
      {
        new InternalErrorBuilder ().setThrowable (t)
                                   .addCustomData ("context", "parallel-running-requests-below-limit")
                                   .handle ();
      }
    }
  }

  public void checkForLongRunningRequests (@Nonnull final List <ILongRunningRequestCallback> aCallbacks)
  {
    if (s_aLogger.isDebugEnabled ())
      s_aLogger.debug ("Checking for long running requests");

    if (!aCallbacks.isEmpty ())
    {
      m_aRWLock.readLock ().lock ();
      try
      {
        // Check only if they are enabled!
        if (m_bLongRunningCheckEnabled)
        {
          // Grab in read lock!
          final long nNotificationMS = m_nLongRunningMilliSeconds;

          // Iterate all running requests
          final Iterator <Map.Entry <String, TrackedRequest>> it = m_aOpenRequests.entrySet ().iterator ();
          while (it.hasNext ())
          {
            final Map.Entry <String, TrackedRequest> aItem = it.next ();
            final long nRunningMilliseconds = aItem.getValue ().getRunningMilliseconds ();
            if (nRunningMilliseconds > nNotificationMS)
            {
              // Invoke callbacks
              for (final ILongRunningRequestCallback aCallback : aCallbacks)
                aCallback.onLongRunningRequest (aItem.getKey (),
                                                aItem.getValue ().getRequestScope (),
                                                nRunningMilliseconds);
            }
            else
            {
              // Don't check any further, since all other requests should be
              // younger than the current one because we're using a
              // LinkedHashMap!
              break;
            }
          }
        }
      }
      finally
      {
        m_aRWLock.readLock ().unlock ();
      }
    }
  }
}
