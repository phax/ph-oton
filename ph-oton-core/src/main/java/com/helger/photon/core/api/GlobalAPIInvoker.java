/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.statistics.IMutableStatisticsHandlerCounter;
import com.helger.commons.statistics.IMutableStatisticsHandlerKeyedCounter;
import com.helger.commons.statistics.IMutableStatisticsHandlerKeyedTimer;
import com.helger.commons.statistics.StatisticsManager;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.timing.StopWatch;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.singleton.AbstractGlobalWebSingleton;

/**
 * Central API manager. Runs in an application scope.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class GlobalAPIInvoker extends AbstractGlobalWebSingleton implements IAPIInvoker
{
  private static final Logger LOGGER = LoggerFactory.getLogger (GlobalAPIInvoker.class);
  private static final IMutableStatisticsHandlerCounter s_aStatsGlobalInvoke = StatisticsManager.getCounterHandler (GlobalAPIInvoker.class.getName () +
                                                                                                                    "$invocations");
  private static final IMutableStatisticsHandlerKeyedCounter s_aStatsFunctionInvoke = StatisticsManager.getKeyedCounterHandler (GlobalAPIInvoker.class.getName () +
                                                                                                                                "$func");
  private static final IMutableStatisticsHandlerKeyedTimer s_aStatsFunctionTimer = StatisticsManager.getKeyedTimerHandler (GlobalAPIInvoker.class.getName () +
                                                                                                                           "$timer");

  @GuardedBy ("m_aRWLock")
  private final APIDescriptorList m_aApiDecls = new APIDescriptorList ();

  @Deprecated
  @UsedViaReflection
  public GlobalAPIInvoker ()
  {}

  @Nonnull
  public static GlobalAPIInvoker getInstance ()
  {
    return getGlobalSingleton (GlobalAPIInvoker.class);
  }

  public void registerAPI (@Nonnull final APIDescriptor aDescriptor)
  {
    m_aRWLock.writeLocked ( () -> m_aApiDecls.addDescriptor (aDescriptor));
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IAPIDescriptor> getAllAPIDescriptors ()
  {
    return m_aRWLock.readLocked (m_aApiDecls::getAllDescriptors);
  }

  @Nullable
  public InvokableAPIDescriptor getAPIByPath (@Nonnull final APIPath aPath)
  {
    return m_aRWLock.readLocked ( () -> m_aApiDecls.getMatching (aPath));
  }

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
      s_aStatsGlobalInvoke.increment ();

      // Invoke before handler
      APISettings.beforeExecutionCallbacks ()
                 .forEach (aCB -> aCB.onBeforeExecution (this, aInvokableDescriptor, aRequestScope));

      // Main invocation
      aInvokableDescriptor.invokeAPI (aRequestScope, aUnifiedResponse);

      // Invoke after handler
      APISettings.afterExecutionCallbacks ()
                 .forEach (aCB -> aCB.onAfterExecution (this, aInvokableDescriptor, aRequestScope));

      // Increment statistics after successful call
      s_aStatsFunctionInvoke.increment (sPath);
    }
    catch (final Throwable t)
    {
      boolean bHandled = false;
      final IAPIExceptionMapper aExMapper = aInvokableDescriptor.getAPIDescriptor ().getExceptionMapper ();
      if (aExMapper != null)
      {
        // Apply exception mapper
        bHandled = aExMapper.applyExceptionOnResponse (aInvokableDescriptor, aRequestScope, aUnifiedResponse, t)
                            .isHandled ();
      }

      if (!bHandled)
      {
        APISettings.exceptionCallbacks ()
                   .forEach (aCB -> aCB.onAPIExecutionException (this, aInvokableDescriptor, aRequestScope, t));

        // Re-throw
        if (t instanceof Exception)
          throw (Exception) t;
        throw new Exception (t);
      }
    }
    finally
    {
      // Long running API request?
      final long nExecutionMillis = aSW.stopAndGetMillis ();
      s_aStatsFunctionTimer.addTime (sPath, nExecutionMillis);
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
    return ToStringGenerator.getDerived (super.toString ()).append ("List", m_aApiDecls).getToString ();
  }
}
