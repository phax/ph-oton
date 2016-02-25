/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
import com.helger.commons.collection.ext.CommonsHashMap;
import com.helger.commons.collection.ext.ICommonsMap;
import com.helger.commons.concurrent.SimpleReadWriteLock;
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
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

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

  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  private final CallbackList <IAjaxExceptionCallback> m_aExceptionCallbacks = new CallbackList <> ();
  private final CallbackList <IAjaxBeforeExecutionCallback> m_aBeforeExecutionCallbacks = new CallbackList <> ();
  private final CallbackList <IAjaxAfterExecutionCallback> m_aAfterExecutionCallbacks = new CallbackList <> ();
  @GuardedBy ("m_aRWLock")
  private long m_nLongRunningExecutionLimitTime = DEFAULT_LONG_RUNNING_EXECUTION_LIMIT_MS;
  private final CallbackList <IAjaxLongRunningExecutionCallback> m_aLongRunningExecutionCallbacks = new CallbackList <> ();
  @GuardedBy ("m_aRWLock")
  private final ICommonsMap <String, IAjaxFunctionDeclaration> m_aMap = new CommonsHashMap <> ();

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
    return m_aRWLock.readLocked ( () -> m_nLongRunningExecutionLimitTime);
  }

  public void setLongRunningExecutionLimitTime (final long nLongRunningExecutionLimitTime)
  {
    m_aRWLock.writeLocked ( () -> m_nLongRunningExecutionLimitTime = nLongRunningExecutionLimitTime);
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public CallbackList <IAjaxLongRunningExecutionCallback> getLongRunningExecutionCallbacks ()
  {
    return m_aLongRunningExecutionCallbacks;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsMap <String, IAjaxFunctionDeclaration> getAllRegisteredFunctions ()
  {
    return m_aRWLock.readLocked ( () -> m_aMap.getClone ());
  }

  @Nullable
  public IAjaxFunctionDeclaration getRegisteredFunction (@Nullable final String sFunctionName)
  {
    if (StringHelper.hasNoText (sFunctionName))
      return null;

    return m_aRWLock.readLocked ( () -> m_aMap.get (sFunctionName));
  }

  public boolean isRegisteredFunction (@Nullable final String sFunctionName)
  {
    if (StringHelper.hasNoText (sFunctionName))
      return false;

    return m_aRWLock.readLocked ( () -> m_aMap.containsKey (sFunctionName));
  }

  public void registerFunction (@Nonnull final IAjaxFunctionDeclaration aFunctionDeclaration)
  {
    ValueEnforcer.notNull (aFunctionDeclaration, "FunctionDeclaration");

    final String sFunctionName = aFunctionDeclaration.getName ();

    m_aRWLock.writeLocked ( () -> {
      if (m_aMap.containsKey (sFunctionName))
        throw new IllegalArgumentException ("An Ajax function with the name '" +
                                            sFunctionName +
                                            "' is already registered");
      m_aMap.put (sFunctionName, aFunctionDeclaration);
    });

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
      getBeforeExecutionCallbacks ().forEach (aCB -> aCB.onBeforeExecution (this,
                                                                            sFunctionName,
                                                                            aRequestScope,
                                                                            aAjaxExecutor));

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
      getAfterExecutionCallbacks ().forEach (aCB -> aCB.onAfterExecution (this,
                                                                          sFunctionName,
                                                                          aRequestScope,
                                                                          aAjaxExecutor,
                                                                          aAjaxResponse));

      // Increment statistics after successful call
      s_aStatsFunctionInvoke.increment (sFunctionName);

      // Long running AJAX request?
      final long nExecutionMillis = aSW.stopAndGetMillis ();
      s_aStatsFunctionTimer.addTime (sFunctionName, nExecutionMillis);
      final long nLimitMS = getLongRunningExecutionLimitTime ();
      if (nLimitMS > 0 && nExecutionMillis > nLimitMS)
      {
        // Long running execution
        getLongRunningExecutionCallbacks ().forEach (aCB -> aCB.onLongRunningExecution (this,
                                                                                        sFunctionName,
                                                                                        aRequestScope,
                                                                                        aAjaxExecutor,
                                                                                        nExecutionMillis));
      }
      return aAjaxResponse;
    }
    catch (final Throwable t)
    {
      getExceptionCallbacks ().forEach (aCB -> aCB.onAjaxExecutionException (this,
                                                                             sFunctionName,
                                                                             aAjaxExecutor,
                                                                             aRequestScope,
                                                                             t));

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
