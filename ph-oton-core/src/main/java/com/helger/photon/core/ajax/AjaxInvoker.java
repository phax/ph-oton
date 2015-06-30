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
package com.helger.photon.core.ajax;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
import com.helger.commons.callback.CallbackList;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.statistics.IMutableStatisticsHandlerCounter;
import com.helger.commons.statistics.IMutableStatisticsHandlerKeyedCounter;
import com.helger.commons.statistics.IMutableStatisticsHandlerKeyedTimer;
import com.helger.commons.statistics.StatisticsManager;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.timing.StopWatch;
import com.helger.photon.core.ajax.callback.LoggingAjaxExceptionCallback;
import com.helger.photon.core.ajax.response.IAjaxResponse;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;

/**
 * The default implementation of {@link IAjaxInvoker}.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class AjaxInvoker implements IAjaxInvoker
{
  /**
   * Default milliseconds until an implementation is considered long running.
   */
  public static final long DEFAULT_LONG_RUNNING_EXECUTION_LIMIT_MS = CGlobal.MILLISECONDS_PER_SECOND;

  private static final Logger s_aLogger = LoggerFactory.getLogger (AjaxInvoker.class);
  private static final IMutableStatisticsHandlerCounter s_aStatsGlobalInvoke = StatisticsManager.getCounterHandler (AjaxInvoker.class.getName () +
                                                                                                                    "$invocations");
  private static final IMutableStatisticsHandlerKeyedCounter s_aStatsFunctionInvoke = StatisticsManager.getKeyedCounterHandler (AjaxInvoker.class.getName () +
                                                                                                                                "$func");
  private static final IMutableStatisticsHandlerKeyedTimer s_aStatsFunctionTimer = StatisticsManager.getKeyedTimerHandler (AjaxInvoker.class.getName () +
                                                                                                                           "$timer");

  private final ReadWriteLock m_aRWLock = new ReentrantReadWriteLock ();
  private final CallbackList <IAjaxExceptionCallback> m_aExceptionCallbacks = new CallbackList <IAjaxExceptionCallback> ();
  private final CallbackList <IAjaxBeforeExecutionCallback> m_aBeforeExecutionCallbacks = new CallbackList <IAjaxBeforeExecutionCallback> ();
  private final CallbackList <IAjaxAfterExecutionCallback> m_aAfterExecutionCallbacks = new CallbackList <IAjaxAfterExecutionCallback> ();
  @GuardedBy ("m_aRWLock")
  private long m_nLongRunningExecutionLimitTime = DEFAULT_LONG_RUNNING_EXECUTION_LIMIT_MS;
  private final CallbackList <IAjaxLongRunningExecutionCallback> m_aLongRunningExecutionCallbacks = new CallbackList <IAjaxLongRunningExecutionCallback> ();
  @GuardedBy ("m_aRWLock")
  private final Map <String, IAjaxFunctionDeclaration> m_aMap = new HashMap <String, IAjaxFunctionDeclaration> ();

  public AjaxInvoker ()
  {
    // Register default handler
    getExceptionCallbacks ().addCallback (new LoggingAjaxExceptionCallback ());
  }

  public static boolean isValidFunctionName (@Nullable final String sFunctionName)
  {
    // All characters allowed should be valid in URLs without masking
    return StringHelper.hasText (sFunctionName) &&
           RegExHelper.stringMatchesPattern ("^[a-zA-Z0-9\\-_]+$", sFunctionName);
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public CallbackList <IAjaxExceptionCallback> getExceptionCallbacks ()
  {
    return m_aExceptionCallbacks;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public CallbackList <IAjaxBeforeExecutionCallback> getBeforeExecutionCallbacks ()
  {
    return m_aBeforeExecutionCallbacks;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public CallbackList <IAjaxAfterExecutionCallback> getAfterExecutionCallbacks ()
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
  public CallbackList <IAjaxLongRunningExecutionCallback> getLongRunningExecutionCallbacks ()
  {
    return m_aLongRunningExecutionCallbacks;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Map <String, IAjaxFunctionDeclaration> getAllRegisteredFunctions ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newMap (m_aMap);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nullable
  public IAjaxFunctionDeclaration getRegisteredFunction (@Nullable final String sFunctionName)
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.get (sFunctionName);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nullable
  public IAjaxExecutor createExecutor (@Nullable final String sFunctionName)
  {
    final IAjaxFunctionDeclaration aFunction = getRegisteredFunction (sFunctionName);
    return aFunction == null ? null : aFunction.getExecutorFactory ().create ();
  }

  public boolean isRegisteredFunction (@Nullable final String sFunctionName)
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.containsKey (sFunctionName);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public void registerFunction (@Nonnull final IAjaxFunctionDeclaration aFunctionDeclaration)
  {
    ValueEnforcer.notNull (aFunctionDeclaration, "Function");

    final String sFunctionName = aFunctionDeclaration.getName ();

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (m_aMap.containsKey (sFunctionName))
        throw new IllegalArgumentException ("An Ajax function with the name '" +
                                            sFunctionName +
                                            "' is already registered");
      m_aMap.put (sFunctionName, aFunctionDeclaration);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }

    if (s_aLogger.isDebugEnabled ())
      s_aLogger.debug ("Registered AJAX function '" +
                       sFunctionName +
                       "' with executor factory " +
                       aFunctionDeclaration.getExecutorFactory ());
  }

  @Nonnull
  public IAjaxResponse invokeFunction (@Nonnull final String sFunctionName,
                                       @Nonnull final IAjaxExecutor aAjaxExecutor,
                                       @Nonnull final IRequestWebScopeWithoutResponse aRequestScope) throws Throwable
  {
    ValueEnforcer.notNull (sFunctionName, "FunctionName");
    ValueEnforcer.notNull (aAjaxExecutor, "AjaxExecutor");
    ValueEnforcer.notNull (aRequestScope, "RequestScope");

    if (s_aLogger.isDebugEnabled ())
      s_aLogger.debug ("Invoking Ajax function '" + sFunctionName + "'");

    try
    {
      final StopWatch aSW = StopWatch.createdStarted ();

      // Global increment before invocation
      s_aStatsGlobalInvoke.increment ();

      // Invoke before handler
      for (final IAjaxBeforeExecutionCallback aBeforeCallback : getBeforeExecutionCallbacks ().getAllCallbacks ())
        try
        {
          aBeforeCallback.onBeforeExecution (this, sFunctionName, aRequestScope, aAjaxExecutor);
        }
        catch (final Throwable t)
        {
          s_aLogger.error ("Error invoking Ajax function before execution callback handler " + aBeforeCallback, t);
        }

      // Register all external resources, prior to handling the main request, as
      // the JS/CSS elements will be contained in the AjaxDefaultResponse in
      // case of success
      aAjaxExecutor.registerExternalResources ();

      // Main handle request
      final IAjaxResponse aAjaxResponse = aAjaxExecutor.handleRequest (aRequestScope);
      if (aAjaxResponse.isFailure ())
      {
        // Execution failed
        s_aLogger.warn ("Invoked Ajax function '" +
                        sFunctionName +
                        "' returned a failure: " +
                        aAjaxResponse.toString ());
      }

      // Invoke after handler
      for (final IAjaxAfterExecutionCallback aAfterCallback : getAfterExecutionCallbacks ().getAllCallbacks ())
        try
        {
          aAfterCallback.onAfterExecution (this, sFunctionName, aRequestScope, aAjaxExecutor, aAjaxResponse);
        }
        catch (final Throwable t)
        {
          s_aLogger.error ("Error invoking Ajax after execution callback handler " + aAfterCallback, t);
        }

      // Increment statistics after successful call
      s_aStatsFunctionInvoke.increment (sFunctionName);

      // Long running AJAX request?
      final long nExecutionMillis = aSW.stopAndGetMillis ();
      s_aStatsFunctionTimer.addTime (sFunctionName, nExecutionMillis);
      final long nLimitMS = getLongRunningExecutionLimitTime ();
      if (nLimitMS > 0 && nExecutionMillis > nLimitMS)
      {
        // Long running execution
        for (final IAjaxLongRunningExecutionCallback aLongRunningExecutionCallback : getLongRunningExecutionCallbacks ().getAllCallbacks ())
          try
          {
            aLongRunningExecutionCallback.onLongRunningExecution (this,
                                                                  sFunctionName,
                                                                  aRequestScope,
                                                                  aAjaxExecutor,
                                                                  nExecutionMillis);
          }
          catch (final Throwable t)
          {
            s_aLogger.error ("Error invoking Ajax long running execution callback handler " +
                             aLongRunningExecutionCallback,
                             t);
          }
      }
      return aAjaxResponse;
    }
    catch (final Throwable t)
    {
      for (final IAjaxExceptionCallback aExceptionCallback : getExceptionCallbacks ().getAllCallbacks ())
        try
        {
          aExceptionCallback.onAjaxExecutionException (this, sFunctionName, aAjaxExecutor, aRequestScope, t);
        }
        catch (final Throwable t2)
        {
          s_aLogger.error ("Error invoking Ajax exception callback handler " + aExceptionCallback, t2);
        }

      // Re-throw
      throw t;
    }
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("map", m_aMap)
                                       .append ("exceptionCallbacks", m_aExceptionCallbacks)
                                       .append ("beforeExecutionCallbacks", m_aBeforeExecutionCallbacks)
                                       .append ("afterExecutionCallbacks", m_aAfterExecutionCallbacks)
                                       .append ("longRunningExecutionLimitTime", m_nLongRunningExecutionLimitTime)
                                       .append ("longRunningExecutionCallbacks", m_aLongRunningExecutionCallbacks)
                                       .toString ();
  }
}
