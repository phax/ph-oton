/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.ajax;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.timing.StopWatch;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.photon.ajax.executor.IAjaxExecutor;
import com.helger.photon.app.PhotonUnifiedResponse;
import com.helger.statistics.api.IMutableStatisticsHandlerCounter;
import com.helger.statistics.api.IMutableStatisticsHandlerKeyedCounter;
import com.helger.statistics.api.IMutableStatisticsHandlerKeyedTimer;
import com.helger.statistics.impl.StatisticsManager;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * The default implementation of {@link IAjaxInvoker}.
 *
 * @author Philip Helger
 */
@Immutable
public class AjaxInvoker implements IAjaxInvoker
{
  private static final Logger LOGGER = LoggerFactory.getLogger (AjaxInvoker.class);
  private static final IMutableStatisticsHandlerCounter STATS_GLOBAL_INVOKE = StatisticsManager.getCounterHandler (AjaxInvoker.class.getName () +
                                                                                                                    "$invocations");
  private static final IMutableStatisticsHandlerKeyedCounter STATS_FUNCTION_INVOKE = StatisticsManager.getKeyedCounterHandler (AjaxInvoker.class.getName () +
                                                                                                                                "$func");
  private static final IMutableStatisticsHandlerKeyedTimer STATS_FUNCTION_TIMER = StatisticsManager.getKeyedTimerHandler (AjaxInvoker.class.getName () +
                                                                                                                           "$timer");

  public AjaxInvoker ()
  {}

  public void invokeFunction (@NonNull final String sFunctionName,
                              @NonNull final IAjaxExecutor aAjaxExecutor,
                              @NonNull final IRequestWebScopeWithoutResponse aRequestScope,
                              @NonNull final PhotonUnifiedResponse aAjaxResponse) throws Exception
  {
    ValueEnforcer.notNull (sFunctionName, "FunctionName");
    ValueEnforcer.notNull (aAjaxExecutor, "AjaxExecutor");
    ValueEnforcer.notNull (aRequestScope, "RequestScope");
    ValueEnforcer.notNull (aAjaxResponse, "AjaxResponse");

    if (LOGGER.isDebugEnabled ())
      LOGGER.debug ("Invoking Ajax function '" + sFunctionName + "'");

    try
    {
      final StopWatch aSW = StopWatch.createdStarted ();

      // Global increment before invocation
      STATS_GLOBAL_INVOKE.increment ();

      // Invoke before handler
      AjaxSettings.beforeExecutionCallbacks ().forEach (aCB -> aCB.onBeforeExecution (this, sFunctionName, aRequestScope, aAjaxExecutor));

      // Register all external resources, prior to handling the main request, as
      // the JS/CSS elements will be contained in the AjaxDefaultResponse in
      // case of success
      aAjaxExecutor.registerExternalResources ();

      // Main handle request
      aAjaxExecutor.handleRequest (aRequestScope, aAjaxResponse);

      // Invoke after handler
      AjaxSettings.afterExecutionCallbacks ()
                  .forEach (aCB -> aCB.onAfterExecution (this, sFunctionName, aRequestScope, aAjaxExecutor, aAjaxResponse));

      // Increment statistics after successful call
      STATS_FUNCTION_INVOKE.increment (sFunctionName);

      // Long running AJAX request?
      final long nExecutionMillis = aSW.stopAndGetMillis ();
      STATS_FUNCTION_TIMER.addTime (sFunctionName, nExecutionMillis);
      final long nLimitMS = AjaxSettings.getLongRunningExecutionLimitTime ();
      if (nLimitMS > 0 && nExecutionMillis > nLimitMS)
      {
        // Long running execution
        AjaxSettings.longRunningExecutionCallbacks ()
                    .forEach (aCB -> aCB.onLongRunningExecution (this, sFunctionName, aRequestScope, aAjaxExecutor, nExecutionMillis));
      }
    }
    catch (final Exception ex)
    {
      AjaxSettings.exceptionCallbacks ()
                  .forEach (aCB -> aCB.onAjaxExecutionException (this, sFunctionName, aAjaxExecutor, aRequestScope, ex));

      // Re-throw
      throw ex;
    }
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).getToString ();
  }
}
