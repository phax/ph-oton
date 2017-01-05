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
import com.helger.commons.collection.ext.ICommonsCollection;
import com.helger.commons.statistics.IMutableStatisticsHandlerCounter;
import com.helger.commons.statistics.IMutableStatisticsHandlerKeyedCounter;
import com.helger.commons.statistics.IMutableStatisticsHandlerKeyedTimer;
import com.helger.commons.statistics.StatisticsManager;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.timing.StopWatch;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.singleton.AbstractApplicationWebSingleton;

/**
 * Central API manager. Runs in an application scope.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class ApplicationAPIManager extends AbstractApplicationWebSingleton implements IAPIInvoker
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (ApplicationAPIManager.class);
  private static final IMutableStatisticsHandlerCounter s_aStatsGlobalInvoke = StatisticsManager.getCounterHandler (ApplicationAPIManager.class.getName () +
                                                                                                                    "$invocations");
  private static final IMutableStatisticsHandlerKeyedCounter s_aStatsFunctionInvoke = StatisticsManager.getKeyedCounterHandler (ApplicationAPIManager.class.getName () +
                                                                                                                                "$func");
  private static final IMutableStatisticsHandlerKeyedTimer s_aStatsFunctionTimer = StatisticsManager.getKeyedTimerHandler (ApplicationAPIManager.class.getName () +
                                                                                                                           "$timer");

  @GuardedBy ("m_aRWLock")
  private final APIDescriptorList m_aApiDecls = new APIDescriptorList ();

  @Deprecated
  @UsedViaReflection
  public ApplicationAPIManager ()
  {}

  @Nonnull
  public static ApplicationAPIManager getInstance ()
  {
    return getApplicationSingleton (ApplicationAPIManager.class);
  }

  public void registerAPI (@Nonnull final APIDescriptor aDescriptor)
  {
    m_aRWLock.writeLocked ( () -> m_aApiDecls.addDescriptor (aDescriptor));
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsCollection <? extends IAPIDescriptor> getAllAPIDescriptors ()
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
    if (s_aLogger.isDebugEnabled ())
      s_aLogger.debug ("Invoking API '" + sPath + "'");

    try
    {
      final StopWatch aSW = StopWatch.createdStarted ();

      // Global increment before invocation
      s_aStatsGlobalInvoke.increment ();

      // Invoke before handler
      APISettings.getBeforeExecutionCallbacks ()
                 .forEach (aCB -> aCB.onBeforeExecution (this, aInvokableDescriptor, aRequestScope));

      aInvokableDescriptor.invokeAPI (aRequestScope, aUnifiedResponse);

      // Invoke after handler
      APISettings.getAfterExecutionCallbacks ()
                 .forEach (aCB -> aCB.onAfterExecution (this, aInvokableDescriptor, aRequestScope));

      // Increment statistics after successful call
      s_aStatsFunctionInvoke.increment (sPath);

      // Long running API request?
      final long nExecutionMillis = aSW.stopAndGetMillis ();
      s_aStatsFunctionTimer.addTime (sPath, nExecutionMillis);
      final long nLimitMS = APISettings.getLongRunningExecutionLimitTime ();
      if (nLimitMS > 0 && nExecutionMillis > nLimitMS)
      {
        // Long running execution
        APISettings.getLongRunningExecutionCallbacks ()
                   .forEach (aCB -> aCB.onLongRunningExecution (this,
                                                                aInvokableDescriptor,
                                                                aRequestScope,
                                                                nExecutionMillis));
      }
    }
    catch (final Throwable t)
    {
      APISettings.getExceptionCallbacks ()
                 .forEach (aCB -> aCB.onAPIExecutionException (this, aInvokableDescriptor, aRequestScope, t));

      // Re-throw
      if (t instanceof Exception)
        throw (Exception) t;
      throw new Exception (t);
    }
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("List", m_aApiDecls).toString ();
  }
}
