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
package com.helger.photon.core.action.servlet;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDateTime;
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
import com.helger.photon.core.action.IActionDeclaration;
import com.helger.photon.core.action.IActionExceptionCallback;
import com.helger.photon.core.action.IActionExecutor;
import com.helger.photon.core.action.IActionInvoker;
import com.helger.photon.core.servlet.AbstractUnifiedResponseServlet;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.response.UnifiedResponse;

/**
 * Abstract action handling servlet
 *
 * @author Philip Helger
 */
public abstract class AbstractActionServlet extends AbstractUnifiedResponseServlet
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractActionServlet.class);
  private static final IMutableStatisticsHandlerKeyedTimer s_aStatsTimer = StatisticsManager.getKeyedTimerHandler (AbstractActionServlet.class);
  private static final IMutableStatisticsHandlerKeyedCounter s_aStatsCounterSuccess = StatisticsManager.getKeyedCounterHandler (AbstractActionServlet.class +
                                                                                                                                "$success");
  private static final IMutableStatisticsHandlerKeyedCounter s_aStatsCounterError = StatisticsManager.getKeyedCounterHandler (AbstractActionServlet.class +
                                                                                                                              "$error");
  private static final String SCOPE_ATTR_NAME = "$ph-actionservlet.name";
  private static final String SCOPE_ATTR_INVOKER = "$ph-actionservlet.invoker";
  private static final String SCOPE_ATTR_EXECUTOR = "$ph-actionservlet.executor";

  private static final CallbackList <IActionExceptionCallback> s_aExceptionCallbacks = new CallbackList <IActionExceptionCallback> ();

  public AbstractActionServlet ()
  {}

  /**
   * @return The current custom exception handler or <code>null</code> if none
   *         is set.
   */
  @Nonnull
  @ReturnsMutableObject ("design")
  public static CallbackList <IActionExceptionCallback> getExceptionCallbacks ()
  {
    return s_aExceptionCallbacks;
  }

  /**
   * Get the action invoker matching the passed request
   *
   * @param aRequestScope
   *        The request scope to use. May not be <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  protected abstract IActionInvoker getActionInvoker (@Nonnull IRequestWebScopeWithoutResponse aRequestScope);

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected EContinue initRequestState (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                        @Nonnull final UnifiedResponse aUnifiedResponse)
  {
    // cut the leading "/"
    final String sActionName = StringHelper.trimStart (aRequestScope.getPathWithinServlet (), "/");

    final IActionInvoker aActionInvoker = getActionInvoker (aRequestScope);
    final IActionDeclaration aActionDeclaration = aActionInvoker.getRegisteredAction (sActionName);
    if (aActionDeclaration == null)
    {
      s_aLogger.warn ("Unknown action '" + sActionName + "' provided!");

      // No such action
      aUnifiedResponse.setStatus (HttpServletResponse.SC_NOT_FOUND);
      return EContinue.BREAK;
    }

    // Is the declaration applicable for the current scope?
    if (!aActionDeclaration.canExecute (aRequestScope))
    {
      s_aLogger.warn ("Action '" + sActionName + "' cannot be executed in the current scope.");
      aUnifiedResponse.setStatus (HttpServletResponse.SC_UNAUTHORIZED);
      return EContinue.BREAK;
    }

    // Create the executor itself
    final IActionExecutor aActionExecutor = aActionDeclaration.getExecutorFactory ().create ();
    if (aActionExecutor == null)
    {
      s_aLogger.warn ("No ActionExecutor created for action " + aActionDeclaration);
      aUnifiedResponse.setStatus (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      return EContinue.BREAK;
    }

    // Call the initialization of the action executor
    aActionExecutor.initExecution (aRequestScope);

    // Remember in scope
    // Important: use a wrapper to avoid scope destruction
    aRequestScope.setAttribute (SCOPE_ATTR_NAME, sActionName);
    aRequestScope.setAttribute (SCOPE_ATTR_INVOKER, Wrapper.create (aActionInvoker));
    aRequestScope.setAttribute (SCOPE_ATTR_EXECUTOR, aActionExecutor);
    return EContinue.CONTINUE;
  }

  @Override
  @OverrideOnDemand
  @Nullable
  protected LocalDateTime getLastModificationDateTime (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    final IActionExecutor aActionExecutor = aRequestScope.getTypedAttribute (SCOPE_ATTR_EXECUTOR,
                                                                             IActionExecutor.class);
    return aActionExecutor.getLastModificationDateTime ();
  }

  @Override
  protected void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                @Nonnull final UnifiedResponse aUnifiedResponse) throws ServletException, IOException
  {
    // Action is present
    final String sActionName = aRequestScope.getAttributeAsString (SCOPE_ATTR_NAME);
    final IActionInvoker aActionInvoker = (IActionInvoker) aRequestScope.getTypedAttribute (SCOPE_ATTR_INVOKER,
                                                                                            Wrapper.class)
                                                                        .get ();
    final IActionExecutor aActionExecutor = aRequestScope.getTypedAttribute (SCOPE_ATTR_EXECUTOR,
                                                                             IActionExecutor.class);

    // For actions caching is not an option, because it is dynamic content
    aUnifiedResponse.disableCaching ();

    try
    {
      // Start the timing
      final StopWatch aSW = StopWatch.createdStarted ();

      // Handle the main action
      aActionInvoker.invokeAction (sActionName, aActionExecutor, aRequestScope, aUnifiedResponse);

      // Remember the time
      s_aStatsTimer.addTime (sActionName, aSW.stopAndGetMillis ());
      s_aStatsCounterSuccess.increment (sActionName);
    }
    catch (final Throwable t)
    {
      s_aStatsCounterError.increment (sActionName);

      // Notify custom exception handler
      for (final IActionExceptionCallback aExceptionCallback : getExceptionCallbacks ().getAllCallbacks ())
        try
        {
          aExceptionCallback.onActionExecutionException (aActionInvoker,
                                                         sActionName,
                                                         aActionExecutor,
                                                         aRequestScope,
                                                         t);
        }
        catch (final Throwable t2)
        {
          s_aLogger.error ("Exception in custom Action exception handler of function '" + sActionName + "'", t2);
        }

      // Re-throw
      if (t instanceof IOException)
        throw (IOException) t;
      if (t instanceof ServletException)
        throw (ServletException) t;
      throw new ServletException ("Error invoking Action '" + sActionName + "'", t);
    }
  }
}
