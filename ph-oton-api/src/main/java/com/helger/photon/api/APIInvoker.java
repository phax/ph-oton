/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.timing.StopWatch;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.statistics.api.IMutableStatisticsHandlerCounter;
import com.helger.statistics.api.IMutableStatisticsHandlerKeyedCounter;
import com.helger.statistics.api.IMutableStatisticsHandlerKeyedTimer;
import com.helger.statistics.impl.StatisticsManager;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

import jakarta.annotation.Nonnull;

/**
 * Default implementation of {@link IAPIInvoker}.
 *
 * @author Philip Helger
 */
@Immutable
public class APIInvoker implements IAPIInvoker
{
  private static final Logger LOGGER = LoggerFactory.getLogger (APIInvoker.class);
  private static final IMutableStatisticsHandlerCounter STATS_GLOBAL_INVOKE = StatisticsManager.getCounterHandler (APIInvoker.class.getName () +
                                                                                                                   "$invocations");
  private static final IMutableStatisticsHandlerKeyedCounter STATS_FUNCTION_INVOKE = StatisticsManager.getKeyedCounterHandler (APIInvoker.class.getName () +
                                                                                                                               "$func");
  private static final IMutableStatisticsHandlerKeyedTimer STATS_FUNCTION_TIMER = StatisticsManager.getKeyedTimerHandler (APIInvoker.class.getName () +
                                                                                                                          "$timer");

  public void invoke (@Nonnull final InvokableAPIDescriptor aInvokableDescriptor,
                      @Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                      @Nonnull final UnifiedResponse aUnifiedResponse) throws Exception
  {
    ValueEnforcer.notNull (aInvokableDescriptor, "InvokableDescriptor");
    ValueEnforcer.notNull (aRequestScope, "RequestScope");
    ValueEnforcer.notNull (aUnifiedResponse, "UnifiedResponse");

    final String sPath = aInvokableDescriptor.getPath ();
    if (LOGGER.isDebugEnabled ())
      LOGGER.debug ("Invoking API '" + sPath + "'");

    final StopWatch aSW = StopWatch.createdStarted ();
    try
    {
      // Global increment before invocation
      STATS_GLOBAL_INVOKE.increment ();

      // Invoke before handler
      APISettings.beforeExecutionCallbacks ()
                 .forEach (aCB -> aCB.onBeforeExecution (this, aInvokableDescriptor, aRequestScope));

      // Main invocation
      aInvokableDescriptor.invokeAPI (aRequestScope, aUnifiedResponse);

      // Invoke after handler
      APISettings.afterExecutionCallbacks ()
                 .forEach (aCB -> aCB.onAfterExecution (this, aInvokableDescriptor, aRequestScope));

      // Increment statistics after successful call
      STATS_FUNCTION_INVOKE.increment (sPath);
    }
    catch (final Exception ex)
    {
      boolean bHandled = false;
      final IAPIExceptionMapper aExMapper = aInvokableDescriptor.getAPIDescriptor ().getExceptionMapper ();
      if (aExMapper != null)
      {
        // Apply exception mapper
        bHandled = aExMapper.applyExceptionOnResponse (aInvokableDescriptor, aRequestScope, aUnifiedResponse, ex)
                            .isHandled ();
      }
      if (!bHandled)
      {
        APISettings.exceptionCallbacks ()
                   .forEach (aCB -> aCB.onAPIExecutionException (this, aInvokableDescriptor, aRequestScope, ex));

        // Re-throw
        throw ex;
      }
    }
    finally
    {
      // Long running API request?
      final long nExecutionMillis = aSW.stopAndGetMillis ();
      STATS_FUNCTION_TIMER.addTime (sPath, nExecutionMillis);
      final long nLimitMS = APISettings.getLongRunningExecutionLimitTime ();
      if (nLimitMS > 0 && nExecutionMillis > nLimitMS)
      {
        // Long running execution
        APISettings.longRunningExecutionCallbacks ()
                   .forEach (aCB -> aCB.onLongRunningExecution (this,
                                                                aInvokableDescriptor,
                                                                aRequestScope,
                                                                nExecutionMillis));
      }
    }
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (super.toString ()).getToString ();
  }
}
