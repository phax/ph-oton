/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.concurrent.ManagedExecutorService;
import com.helger.commons.error.level.EErrorLevel;
import com.helger.commons.scope.IScope;
import com.helger.photon.core.app.CApplication;
import com.helger.photon.core.app.error.InternalErrorBuilder;
import com.helger.web.scope.IRequestWebScope;
import com.helger.web.scope.mgr.WebScopeManager;
import com.helger.web.scope.singleton.AbstractGlobalWebSingleton;
import com.helger.web.scope.util.AbstractWebScopeAwareRunnable;

/**
 * This is the entry point for request time monitoring. It keeps a central
 * {@link RequestTrackingManager} and runs a daemon {@link Thread} for
 * monitoring all open requests.
 *
 * @author Philip Helger
 * @since 4.0.0
 */
@Immutable
public final class RequestTracker extends AbstractGlobalWebSingleton
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (RequestTracker.class);

  private final RequestTrackingManager m_aRequestTrackingMgr = new RequestTrackingManager ();
  private final CallbackList <ILongRunningRequestCallback> m_aLongRunningCallbacks = new CallbackList <> ();
  private final CallbackList <IParallelRunningRequestCallback> m_aParallelRunningCallbacks = new CallbackList <> ();
  private final ScheduledExecutorService m_aExecSvc;

  private final class RequestTrackerMonitor extends AbstractWebScopeAwareRunnable
  {
    public RequestTrackerMonitor ()
    {
      // Use a fixed app ID to ensure this monitor can be started!
      super (WebScopeManager.getGlobalScope ().getServletContext (), CApplication.APP_ID_PUBLIC);
    }

    @Override
    protected void scopedRun ()
    {
      // Check for long running requests
      try
      {
        m_aRequestTrackingMgr.checkForLongRunningRequests (m_aLongRunningCallbacks);
      }
      catch (final Throwable t)
      {
        new InternalErrorBuilder ().setThrowable (t).addCustomData ("context", "long-running-requests").handle ();
      }
    }
  }

  @Deprecated
  @UsedViaReflection
  public RequestTracker ()
  {
    // Register default callbacks
    m_aLongRunningCallbacks.addCallback (new LoggingLongRunningRequestCallback (EErrorLevel.ERROR))
                           .addCallback (new AuditingLongRunningRequestCallback ());
    m_aParallelRunningCallbacks.addCallback (new LoggingParallelRunningRequestCallback (EErrorLevel.WARN))
                               .addCallback (new AuditingParallelRunningRequestCallback ());

    // Create the executor service
    m_aExecSvc = Executors.newSingleThreadScheduledExecutor (new ThreadFactory ()
    {
      private int m_nCount = 0;

      public Thread newThread (final Runnable r)
      {
        final int nID = m_nCount++;
        final Thread aThread = new Thread (r, "RequestTrackerMonitor-" + nID);
        aThread.setDaemon (true);
        return aThread;
      }
    });

    // Start the monitoring thread each second
    m_aExecSvc.scheduleAtFixedRate (new RequestTrackerMonitor (), 0, 1, TimeUnit.SECONDS);
    s_aLogger.info ("RequestTrackerMonitor was installed successfully.");
  }

  @Override
  protected void onDestroy (@Nonnull final IScope aScopeInDestruction)
  {
    // Destroy RequestTrackerMonitor thread(s)
    ManagedExecutorService.shutdownAndWaitUntilAllTasksAreFinished (m_aExecSvc);
  }

  @Nonnull
  public static RequestTracker getInstance ()
  {
    return getGlobalSingleton (RequestTracker.class);
  }

  /**
   * @return The underlying request tracking manager. Never <code>null</code>.
   *         Don't use except you know what you are doing!
   */
  @Nonnull
  public RequestTrackingManager getRequestTrackingMgr ()
  {
    return m_aRequestTrackingMgr;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public static CallbackList <ILongRunningRequestCallback> getLongRunningRequestCallbacks ()
  {
    return getInstance ().m_aLongRunningCallbacks;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public static CallbackList <IParallelRunningRequestCallback> getParallelRunningRequestCallbacks ()
  {
    return getInstance ().m_aParallelRunningCallbacks;
  }

  /**
   * @return The executor service used to schedule the background tasks
   */
  @Nonnull
  public ScheduledExecutorService getExecutorService ()
  {
    return m_aExecSvc;
  }

  /**
   * Add new request to the tracking
   *
   * @param sRequestID
   *        The unique request ID.
   * @param aRequestScope
   *        The request scope itself.
   */
  public static void addRequest (@Nonnull @Nonempty final String sRequestID,
                                 @Nonnull final IRequestWebScope aRequestScope)
  {
    final RequestTracker aRT = getInstance ();
    aRT.m_aRequestTrackingMgr.addRequest (sRequestID, aRequestScope, aRT.m_aParallelRunningCallbacks);
  }

  /**
   * Remove a request from the tracking.
   *
   * @param sRequestID
   *        The request ID.
   */
  public static void removeRequest (@Nonnull @Nonempty final String sRequestID)
  {
    final RequestTracker aRT = getInstance ();
    aRT.m_aRequestTrackingMgr.removeRequest (sRequestID, aRT.m_aParallelRunningCallbacks);
  }
}
