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
package com.helger.photon.core.api;

import java.util.List;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.statistics.IMutableStatisticsHandlerCounter;
import com.helger.commons.statistics.IMutableStatisticsHandlerKeyedCounter;
import com.helger.commons.statistics.IMutableStatisticsHandlerKeyedTimer;
import com.helger.commons.statistics.StatisticsManager;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.timing.StopWatch;
import com.helger.web.http.EHTTPMethod;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.singleton.AbstractApplicationWebSingleton;
import com.helger.web.servlet.response.UnifiedResponse;

/**
 * Central API manager. Runs in an application scope.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class ApplicationAPIManager extends AbstractApplicationWebSingleton implements IAPIInvoker
{
  /**
   * Default milliseconds until an implementation is considered long running.
   */
  public static final long DEFAULT_LONG_RUNNING_EXECUTION_LIMIT_MS = CGlobal.MILLISECONDS_PER_SECOND;
  private static final Logger s_aLogger = LoggerFactory.getLogger (ApplicationAPIManager.class);
  private static final IMutableStatisticsHandlerCounter s_aStatsGlobalInvoke = StatisticsManager.getCounterHandler (ApplicationAPIManager.class.getName () +
                                                                                                                    "$invocations");
  private static final IMutableStatisticsHandlerKeyedCounter s_aStatsFunctionInvoke = StatisticsManager.getKeyedCounterHandler (ApplicationAPIManager.class.getName () +
                                                                                                                                "$func");
  private static final IMutableStatisticsHandlerKeyedTimer s_aStatsFunctionTimer = StatisticsManager.getKeyedTimerHandler (ApplicationAPIManager.class.getName () +
                                                                                                                           "$timer");

  private final CallbackList <IAPIExceptionCallback> m_aExceptionCallbacks = new CallbackList <IAPIExceptionCallback> ();
  private final CallbackList <IAPIBeforeExecutionCallback> m_aBeforeExecutionCallbacks = new CallbackList <IAPIBeforeExecutionCallback> ();
  private final CallbackList <IAPIAfterExecutionCallback> m_aAfterExecutionCallbacks = new CallbackList <IAPIAfterExecutionCallback> ();
  @GuardedBy ("m_aRWLock")
  private long m_nLongRunningExecutionLimitTime = DEFAULT_LONG_RUNNING_EXECUTION_LIMIT_MS;
  private final CallbackList <IAPILongRunningExecutionCallback> m_aLongRunningExecutionCallbacks = new CallbackList <IAPILongRunningExecutionCallback> ();
  @GuardedBy ("m_aRWLock")
  private final APIDescriptorList m_aList = new APIDescriptorList ();

  @Deprecated
  @UsedViaReflection
  public ApplicationAPIManager ()
  {}

  @Nonnull
  public static ApplicationAPIManager getInstance ()
  {
    return getApplicationSingleton (ApplicationAPIManager.class);
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public CallbackList <IAPIExceptionCallback> getExceptionCallbacks ()
  {
    return m_aExceptionCallbacks;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public CallbackList <IAPIBeforeExecutionCallback> getBeforeExecutionCallbacks ()
  {
    return m_aBeforeExecutionCallbacks;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public CallbackList <IAPIAfterExecutionCallback> getAfterExecutionCallbacks ()
  {
    return m_aAfterExecutionCallbacks;
  }

  @CheckForSigned
  public long getLongRunningExecutionLimitTime ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_nLongRunningExecutionLimitTime;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public void setLongRunningExecutionLimitTime (final long nLongRunningExecutionLimitTime)
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_nLongRunningExecutionLimitTime = nLongRunningExecutionLimitTime;
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public CallbackList <IAPILongRunningExecutionCallback> getLongRunningExecutionCallbacks ()
  {
    return m_aLongRunningExecutionCallbacks;
  }

  public void registerAPI (@Nonnull final APIDescriptor aDescriptor)
  {
    m_aRWLock.writeLocked ( () -> m_aList.addDescriptor (aDescriptor));
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <? extends IAPIDescriptor> getAllDescriptors ()
  {
    return m_aRWLock.readLocked (m_aList::getAllDescriptors);
  }

  @Nullable
  public InvokableAPIDescriptor getAPIByPath (@Nullable final String sPath, @Nonnull final EHTTPMethod eHTTPMethod)
  {
    return m_aRWLock.readLocked ( () -> m_aList.getMatching (sPath, eHTTPMethod));
  }

  public void invoke (@Nonnull final InvokableAPIDescriptor aInvokableDescriptor,
                      @Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                      @Nonnull final UnifiedResponse aUnifiedResponse) throws Exception
  {
    ValueEnforcer.notNull (aInvokableDescriptor, "InvokableDescriptor");
    ValueEnforcer.notNull (aRequestScope, "RequestScope");
    ValueEnforcer.notNull (aUnifiedResponse, "UnifiedResponse");

    final String sPath = aInvokableDescriptor.getPath ();
    if (s_aLogger.isDebugEnabled ())
      s_aLogger.debug ("Invoking API '" + sPath + "'");

    try
    {
      final StopWatch aSW = StopWatch.createdStarted ();

      // Global increment before invocation
      s_aStatsGlobalInvoke.increment ();

      // Invoke before handler
      for (final IAPIBeforeExecutionCallback aBeforeCallback : getBeforeExecutionCallbacks ().getAllCallbacks ())
        try
        {
          aBeforeCallback.onBeforeExecution (this, aInvokableDescriptor, aRequestScope);
        }
        catch (final Throwable t)
        {
          s_aLogger.error ("Error invoking API function before execution callback handler " + aBeforeCallback, t);
        }

      aInvokableDescriptor.invokeAPI (aRequestScope, aUnifiedResponse);

      // Invoke after handler
      for (final IAPIAfterExecutionCallback aAfterCallback : getAfterExecutionCallbacks ().getAllCallbacks ())
        try
        {
          aAfterCallback.onAfterExecution (this, aInvokableDescriptor, aRequestScope);
        }
        catch (final Throwable t)
        {
          s_aLogger.error ("Error invoking API after execution callback handler " + aAfterCallback, t);
        }

      // Increment statistics after successful call
      s_aStatsFunctionInvoke.increment (sPath);

      // Long running API request?
      final long nExecutionMillis = aSW.stopAndGetMillis ();
      s_aStatsFunctionTimer.addTime (sPath, nExecutionMillis);
      final long nLimitMS = getLongRunningExecutionLimitTime ();
      if (nLimitMS > 0 && nExecutionMillis > nLimitMS)
      {
        // Long running execution
        for (final IAPILongRunningExecutionCallback aLongRunningExecutionCallback : getLongRunningExecutionCallbacks ().getAllCallbacks ())
          try
          {
            aLongRunningExecutionCallback.onLongRunningExecution (this,
                                                                  aInvokableDescriptor,
                                                                  aRequestScope,
                                                                  nExecutionMillis);
          }
          catch (final Throwable t)
          {
            s_aLogger.error ("Error invoking API long running execution callback handler " +
                             aLongRunningExecutionCallback,
                             t);
          }
      }
    }
    catch (final Throwable t)
    {
      for (final IAPIExceptionCallback aExceptionCallback : getExceptionCallbacks ().getAllCallbacks ())
        try
        {
          aExceptionCallback.onAPIExecutionException (this, aInvokableDescriptor, aRequestScope, t);
        }
        catch (final Throwable t2)
        {
          s_aLogger.error ("Error invoking API exception callback handler " + aExceptionCallback, t2);
        }

      // Re-throw
      if (t instanceof Exception)
        throw (Exception) t;
      throw new Exception (t);
    }
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("List", m_aList).toString ();
  }
}
