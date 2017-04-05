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
import com.helger.commons.state.EContinue;
import com.helger.commons.string.StringHelper;
import com.helger.commons.wrapper.Wrapper;
import com.helger.photon.core.ajax.IAjaxExecutor;
import com.helger.photon.core.ajax.IAjaxFunctionDeclaration;
import com.helger.photon.core.ajax.IAjaxInvoker;
import com.helger.photon.core.ajax.response.IAjaxResponse;
import com.helger.photon.core.servlet.AbstractUnifiedResponseServlet;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Abstract implementation of a servlet that invokes AJAX functions.
 *
 * @author Philip Helger
 */
@ThreadSafe
public abstract class AbstractAjaxServlet extends AbstractUnifiedResponseServlet
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractAjaxServlet.class);

  private static final String SCOPE_ATTR_NAME = "$ph-ajaxservlet.name";
  private static final String SCOPE_ATTR_INVOKER = "$ph-ajaxservlet.invoker";
  private static final String SCOPE_ATTR_EXECUTOR = "$ph-ajaxservlet.executor";

  protected AbstractAjaxServlet ()
  {}

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
    final IAjaxExecutor aAjaxExecutor = aAjaxDeclaration.getExecutorFactory ().get ();
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
    aRequestScope.setAttribute (SCOPE_ATTR_INVOKER, new Wrapper <> (aAjaxInvoker));
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
      // Invoke function
      final IAjaxResponse aResult = aAjaxInvoker.invokeFunction (sAjaxFunctionName, aAjaxExecutor, aRequestScope);
      if (s_aLogger.isTraceEnabled ())
        s_aLogger.trace ("  AJAX Result: " + aResult);

      // Write result to the passed response
      aResult.applyToResponse (aUnifiedResponse);

      if (aUnifiedResponse.isStatusCodeDefined () || aUnifiedResponse.isRedirectDefined ())
      {
        // Status codes are not meant to be cached
        aUnifiedResponse.removeCaching ();
      }
    }
    catch (final IOException | ServletException ex)
    {
      // Re-throw
      throw ex;
    }
    catch (final Throwable t)
    {
      throw new ServletException ("Error invoking AJAX function '" + sAjaxFunctionName + "'", t);
    }
  }
}
