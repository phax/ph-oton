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
package com.helger.photon.core.ajax;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.statistics.IMutableStatisticsHandlerCounter;
import com.helger.commons.statistics.IMutableStatisticsHandlerKeyedCounter;
import com.helger.commons.statistics.IMutableStatisticsHandlerKeyedTimer;
import com.helger.commons.statistics.StatisticsManager;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.timing.StopWatch;
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
  private static final Logger s_aLogger = LoggerFactory.getLogger (AjaxInvoker.class);
  private static final IMutableStatisticsHandlerCounter s_aStatsGlobalInvoke = StatisticsManager.getCounterHandler (AjaxInvoker.class.getName () +
                                                                                                                    "$invocations");
  private static final IMutableStatisticsHandlerKeyedCounter s_aStatsFunctionInvoke = StatisticsManager.getKeyedCounterHandler (AjaxInvoker.class.getName () +
                                                                                                                                "$func");
  private static final IMutableStatisticsHandlerKeyedTimer s_aStatsFunctionTimer = StatisticsManager.getKeyedTimerHandler (AjaxInvoker.class.getName () +
                                                                                                                           "$timer");

  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  @GuardedBy ("m_aRWLock")
  private final ICommonsMap <String, IAjaxFunctionDeclaration> m_aFuncDecls = new CommonsHashMap <> ();

  public AjaxInvoker ()
  {}

  public static boolean isValidFunctionName (@Nullable final String sFunctionName)
  {
    // All characters allowed should be valid in URLs without masking
    return StringHelper.hasText (sFunctionName) &&
           RegExHelper.stringMatchesPattern ("^[a-zA-Z0-9\\-_]+$", sFunctionName);
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsMap <String, IAjaxFunctionDeclaration> getAllRegisteredFunctions ()
  {
    return m_aRWLock.readLocked ( () -> m_aFuncDecls.getClone ());
  }

  @Nullable
  public IAjaxFunctionDeclaration getRegisteredFunction (@Nullable final String sFunctionName)
  {
    if (StringHelper.hasNoText (sFunctionName))
      return null;

    return m_aRWLock.readLocked ( () -> m_aFuncDecls.get (sFunctionName));
  }

  public boolean isRegisteredFunction (@Nullable final String sFunctionName)
  {
    if (StringHelper.hasNoText (sFunctionName))
      return false;

    return m_aRWLock.readLocked ( () -> m_aFuncDecls.containsKey (sFunctionName));
  }

  public void registerFunction (@Nonnull final IAjaxFunctionDeclaration aFunctionDeclaration)
  {
    ValueEnforcer.notNull (aFunctionDeclaration, "FunctionDeclaration");

    final String sFunctionName = aFunctionDeclaration.getName ();

    m_aRWLock.writeLocked ( () -> {
      if (m_aFuncDecls.containsKey (sFunctionName))
        throw new IllegalArgumentException ("An Ajax function with the name '" +
                                            sFunctionName +
                                            "' is already registered");
      m_aFuncDecls.put (sFunctionName, aFunctionDeclaration);
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
      AjaxSettings.beforeExecutionCallbacks ()
                  .forEach (aCB -> aCB.onBeforeExecution (this, sFunctionName, aRequestScope, aAjaxExecutor));

      // Register all external resources, prior to handling the main request, as
      // the JS/CSS elements will be contained in the AjaxDefaultResponse in
      // case of success
      aAjaxExecutor.registerExternalResources ();

      // Main handle request
      final IAjaxResponse aAjaxResponse = aAjaxExecutor.handleRequest (aRequestScope);

      // Invoke after handler
      AjaxSettings.afterExecutionCallbacks ()
                  .forEach (aCB -> aCB.onAfterExecution (this,
                                                         sFunctionName,
                                                         aRequestScope,
                                                         aAjaxExecutor,
                                                         aAjaxResponse));

      // Increment statistics after successful call
      s_aStatsFunctionInvoke.increment (sFunctionName);

      // Long running AJAX request?
      final long nExecutionMillis = aSW.stopAndGetMillis ();
      s_aStatsFunctionTimer.addTime (sFunctionName, nExecutionMillis);
      final long nLimitMS = AjaxSettings.getLongRunningExecutionLimitTime ();
      if (nLimitMS > 0 && nExecutionMillis > nLimitMS)
      {
        // Long running execution
        AjaxSettings.longRunningExecutionCallbacks ()
                    .forEach (aCB -> aCB.onLongRunningExecution (this,
                                                                 sFunctionName,
                                                                 aRequestScope,
                                                                 aAjaxExecutor,
                                                                 nExecutionMillis));
      }
      return aAjaxResponse;
    }
    catch (final Throwable t)
    {
      AjaxSettings.exceptionCallbacks ()
                  .forEach (aCB -> aCB.onAjaxExecutionException (this, sFunctionName, aAjaxExecutor, aRequestScope, t));

      // Re-throw
      throw t;
    }
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("map", m_aFuncDecls).getToString ();
  }
}
