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
package com.helger.photon.core.ajax.servlet;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.ThreadSafe;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.state.EContinue;
import com.helger.commons.statistics.IMutableStatisticsHandlerKeyedCounter;
import com.helger.commons.statistics.IMutableStatisticsHandlerKeyedTimer;
import com.helger.commons.statistics.StatisticsManager;
import com.helger.commons.string.StringHelper;
import com.helger.commons.timing.StopWatch;
import com.helger.commons.wrapper.Wrapper;
import com.helger.photon.core.ajax.IAjaxExceptionCallback;
import com.helger.photon.core.ajax.IAjaxExecutor;
import com.helger.photon.core.ajax.IAjaxFunctionDeclaration;
import com.helger.photon.core.ajax.IAjaxInvoker;
import com.helger.photon.core.ajax.response.IAjaxResponse;
import com.helger.photon.core.servlet.AbstractUnifiedResponseServlet;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.response.UnifiedResponse;

/**
 * Abstract implementation of a servlet that invokes AJAX functions.
 *
 * @author Philip Helger
 */
@ThreadSafe
public abstract class AbstractAjaxServlet extends AbstractUnifiedResponseServlet
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractAjaxServlet.class);
  private static final IMutableStatisticsHandlerKeyedTimer s_aStatsTimer = StatisticsManager.getKeyedTimerHandler (AbstractAjaxServlet.class);
  private static final IMutableStatisticsHandlerKeyedCounter s_aStatsCounterSuccess = StatisticsManager.getKeyedCounterHandler (AbstractAjaxServlet.class +
                                                                                                                                "$success");
  private static final IMutableStatisticsHandlerKeyedCounter s_aStatsCounterError = StatisticsManager.getKeyedCounterHandler (AbstractAjaxServlet.class +
                                                                                                                              "$error");

  private static final String SCOPE_ATTR_NAME = "$ph-ajaxservlet.name";
  private static final String SCOPE_ATTR_INVOKER = "$ph-ajaxservlet.invoker";
  private static final String SCOPE_ATTR_EXECUTOR = "$ph-ajaxservlet.executor";

  private static final CallbackList <IAjaxExceptionCallback> s_aExceptionCallbacks = new CallbackList <IAjaxExceptionCallback> ();

  /**
   * @return The callback list with the exception handlers
   */
  @Nonnull
  @ReturnsMutableObject ("design")
  public static CallbackList <IAjaxExceptionCallback> getExceptionCallbacks ()
  {
    return s_aExceptionCallbacks;
  }

  /**
   * Get the AJAX invoker matching the passed request
   *
   * @param aRequestScope
   *        The request scope to use. May not be <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  protected abstract IAjaxInvoker getAjaxInvoker (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope);

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected EContinue initRequestState (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                        @Nonnull final UnifiedResponse aUnifiedResponse)
  {
    // cut the leading "/"
    String sFunctionName = aRequestScope.getPathWithinServlet ();
    if (StringHelper.startsWith (sFunctionName, '/'))
      sFunctionName = sFunctionName.substring (1);

    final IAjaxInvoker aAjaxInvoker = getAjaxInvoker (aRequestScope);
    final IAjaxFunctionDeclaration aAjaxDeclaration = aAjaxInvoker.getRegisteredFunction (sFunctionName);
    if (aAjaxDeclaration == null)
    {
      s_aLogger.warn ("Unknown Ajax function '" + sFunctionName + "' provided!");

      // No such action
      aUnifiedResponse.setStatus (HttpServletResponse.SC_NOT_FOUND);
      return EContinue.BREAK;
    }

    // Is the declaration applicable for the current scope?
    if (!aAjaxDeclaration.canExecute (aRequestScope))
    {
      s_aLogger.warn ("Ajax function '" + sFunctionName + "' cannot be executed in the current scope.");
      aUnifiedResponse.setStatus (HttpServletResponse.SC_UNAUTHORIZED);
      return EContinue.BREAK;
    }

    // Create the executor itself
    final IAjaxExecutor aAjaxExecutor = aAjaxDeclaration.getExecutorFactory ().create ();
    if (aAjaxExecutor == null)
    {
      s_aLogger.warn ("No AjaxExecutor created for action " + aAjaxDeclaration);
      aUnifiedResponse.setStatus (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      return EContinue.BREAK;
    }

    // Call the initialization of the action executor
    aAjaxExecutor.initExecution (aRequestScope);

    // Remember in scope
    // Important: use a wrapper to avoid scope destruction
    aRequestScope.setAttribute (SCOPE_ATTR_NAME, sFunctionName);
    aRequestScope.setAttribute (SCOPE_ATTR_INVOKER, Wrapper.create (aAjaxInvoker));
    aRequestScope.setAttribute (SCOPE_ATTR_EXECUTOR, aAjaxExecutor);
    return EContinue.CONTINUE;
  }

  @Override
  protected void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                @Nonnull final UnifiedResponse aUnifiedResponse) throws ServletException, IOException
  {
    // Action is present
    final String sAjaxFunctionName = aRequestScope.getAttributeAsString (SCOPE_ATTR_NAME);
    final IAjaxInvoker aAjaxInvoker = (IAjaxInvoker) aRequestScope.getTypedAttribute (SCOPE_ATTR_INVOKER, Wrapper.class)
                                                                  .get ();
    final IAjaxExecutor aAjaxExecutor = aRequestScope.getTypedAttribute (SCOPE_ATTR_EXECUTOR, IAjaxExecutor.class);

    // Never cache the result but the executor may overwrite it
    aUnifiedResponse.disableCaching ();

    try
    {
      // Start the timing
      final StopWatch aSW = StopWatch.createdStarted ();

      // Invoke function
      final IAjaxResponse aResult = aAjaxInvoker.invokeFunction (sAjaxFunctionName, aAjaxExecutor, aRequestScope);
      if (s_aLogger.isTraceEnabled ())
        s_aLogger.trace ("  AJAX Result: " + aResult);

      // Write result to the passed response
      aResult.applyToResponse (aUnifiedResponse);

      // Remember the time
      s_aStatsTimer.addTime (sAjaxFunctionName, aSW.stopAndGetMillis ());
      s_aStatsCounterSuccess.increment (sAjaxFunctionName);
    }
    catch (final Throwable t)
    {
      s_aStatsCounterError.increment (sAjaxFunctionName);

      // Notify custom exception handler
      for (final IAjaxExceptionCallback aExceptionCallback : getExceptionCallbacks ().getAllCallbacks ())
        try
        {
          aExceptionCallback.onAjaxExecutionException (aAjaxInvoker,
                                                       sAjaxFunctionName,
                                                       aAjaxExecutor,
                                                       aRequestScope,
                                                       t);
        }
        catch (final Throwable t2)
        {
          s_aLogger.error ("Exception in custom AJAX exception handler of function '" + sAjaxFunctionName + "'", t2);
        }

      // Re-throw
      if (t instanceof IOException)
        throw (IOException) t;
      if (t instanceof ServletException)
        throw (ServletException) t;
      throw new ServletException ("Error invoking AJAX function '" + sAjaxFunctionName + "'", t);
    }
  }
}
