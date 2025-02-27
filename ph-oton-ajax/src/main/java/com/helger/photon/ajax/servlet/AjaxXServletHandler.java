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
package com.helger.photon.ajax.servlet;

import java.io.IOException;
import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.http.EHttpMethod;
import com.helger.commons.lang.GenericReflection;
import com.helger.commons.state.EContinue;
import com.helger.commons.string.StringHelper;
import com.helger.commons.wrapper.Wrapper;
import com.helger.http.EHttpVersion;
import com.helger.photon.ajax.GlobalAjaxInvoker;
import com.helger.photon.ajax.IAjaxInvoker;
import com.helger.photon.ajax.IAjaxRegistry;
import com.helger.photon.ajax.decl.IAjaxFunctionDeclaration;
import com.helger.photon.ajax.executor.IAjaxExecutor;
import com.helger.photon.app.PhotonUnifiedResponse;
import com.helger.scope.mgr.ScopeManager;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScope;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.xservlet.handler.simple.IXServletSimpleHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Abstract implementation of a servlet that invokes AJAX functions.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class AjaxXServletHandler implements IXServletSimpleHandler
{
  /**
   * The name of the request parameter used by jQuery to indicate "no cache".
   * Use this constant for parameter filtering.
   */
  public static final String REQUEST_PARAM_JQUERY_NO_CACHE = "_";

  private static final Logger LOGGER = LoggerFactory.getLogger (AjaxXServletHandler.class);

  private static final String SCOPE_ATTR_NAME = ScopeManager.SCOPE_ATTRIBUTE_PREFIX_INTERNAL + "ajaxservlet.name";
  private static final String SCOPE_ATTR_INVOKER = ScopeManager.SCOPE_ATTRIBUTE_PREFIX_INTERNAL + "ajaxservlet.invoker";
  private static final String SCOPE_ATTR_EXECUTOR = ScopeManager.SCOPE_ATTRIBUTE_PREFIX_INTERNAL +
                                                    "ajaxservlet.executor";

  private final Supplier <? extends IAjaxRegistry> m_aRegistryFactory;
  private final Supplier <? extends IAjaxInvoker> m_aInvokerFactory;

  public AjaxXServletHandler ()
  {
    this ( () -> GlobalAjaxInvoker.getInstance ().getRegistry (), () -> GlobalAjaxInvoker.getInstance ().getInvoker ());
  }

  public AjaxXServletHandler (@Nonnull final Supplier <? extends IAjaxRegistry> aRegistryFactory,
                              @Nonnull final Supplier <? extends IAjaxInvoker> aInvokerFactory)
  {
    m_aRegistryFactory = ValueEnforcer.notNull (aRegistryFactory, "aRegistryFactory");
    m_aInvokerFactory = ValueEnforcer.notNull (aInvokerFactory, "InvokerFactory");
  }

  @Nonnull
  @Override
  public PhotonUnifiedResponse createUnifiedResponse (@Nonnull final EHttpVersion eHttpVersion,
                                                      @Nonnull final EHttpMethod eHttpMethod,
                                                      @Nonnull final HttpServletRequest aHttpRequest,
                                                      @Nonnull final IRequestWebScope aRequestScope)
  {
    return new PhotonUnifiedResponse (eHttpVersion, eHttpMethod, aHttpRequest, aRequestScope);
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  public EContinue initRequestState (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                     @Nonnull final UnifiedResponse aUnifiedResponse)
  {
    // cut the leading "/"
    String sFunctionName = aRequestScope.getPathWithinServlet ();
    if (StringHelper.startsWith (sFunctionName, '/'))
      sFunctionName = sFunctionName.substring (1);

    final IAjaxRegistry aRegistry = m_aRegistryFactory.get ();
    final IAjaxInvoker aInvoker = m_aInvokerFactory.get ();

    final IAjaxFunctionDeclaration aAjaxDeclaration = aRegistry.getRegisteredFunction (sFunctionName);
    if (aAjaxDeclaration == null)
    {
      LOGGER.warn ("Unknown Ajax function '" + sFunctionName + "' provided!");

      // No such action
      aUnifiedResponse.setStatus (HttpServletResponse.SC_NOT_FOUND);
      return EContinue.BREAK;
    }

    // Is the declaration applicable for the current scope?
    if (!aAjaxDeclaration.canExecute (aRequestScope))
    {
      LOGGER.warn ("Ajax function '" + sFunctionName + "' self-declined the execution for the current request.");
      aUnifiedResponse.setStatus (HttpServletResponse.SC_UNAUTHORIZED);
      return EContinue.BREAK;
    }

    // Create the executor itself
    final IAjaxExecutor aAjaxExecutor = aAjaxDeclaration.getExecutor ();
    if (aAjaxExecutor == null)
    {
      LOGGER.warn ("No AjaxExecutor created for declaration " + aAjaxDeclaration);
      aUnifiedResponse.setStatus (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      return EContinue.BREAK;
    }

    // Call the initialization of the action executor
    aAjaxExecutor.initExecution (aRequestScope);

    // Remove the jQuery time stamp parameter
    aRequestScope.params ().remove (REQUEST_PARAM_JQUERY_NO_CACHE);

    // Remember in scope
    // Important: use a wrapper to avoid scope destruction
    aRequestScope.attrs ().putIn (SCOPE_ATTR_NAME, sFunctionName);
    aRequestScope.attrs ().putIn (SCOPE_ATTR_INVOKER, new Wrapper <> (aInvoker));
    aRequestScope.attrs ().putIn (SCOPE_ATTR_EXECUTOR, aAjaxExecutor);
    return EContinue.CONTINUE;
  }

  @Override
  public void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull final UnifiedResponse aUnifiedResponse) throws ServletException, IOException
  {
    // Action is present
    final String sAjaxFunctionName = aRequestScope.attrs ().getAsString (SCOPE_ATTR_NAME);
    final IAjaxInvoker aAjaxInvoker = (IAjaxInvoker) aRequestScope.attrs ()
                                                                  .getCastedValue (SCOPE_ATTR_INVOKER, Wrapper.class)
                                                                  .get ();
    final IAjaxExecutor aAjaxExecutor = aRequestScope.attrs ()
                                                     .getCastedValue (SCOPE_ATTR_EXECUTOR, IAjaxExecutor.class);

    // Never cache the result but the executor may overwrite it
    aUnifiedResponse.disableCaching ();

    try
    {
      // Invoke function
      aAjaxInvoker.invokeFunction (sAjaxFunctionName,
                                   aAjaxExecutor,
                                   aRequestScope,
                                   GenericReflection.uncheckedCast (aUnifiedResponse));

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
    catch (final Exception ex)
    {
      throw new ServletException ("Error invoking AJAX function '" + sAjaxFunctionName + "'", ex);
    }
  }
}
