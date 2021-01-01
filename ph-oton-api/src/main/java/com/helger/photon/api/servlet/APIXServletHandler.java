/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.api.servlet;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.functional.ISupplier;
import com.helger.commons.http.CHttp;
import com.helger.commons.http.EHttpMethod;
import com.helger.commons.lang.GenericReflection;
import com.helger.commons.mutable.MutableInt;
import com.helger.http.EHttpVersion;
import com.helger.photon.api.APIPath;
import com.helger.photon.api.GlobalAPIInvoker;
import com.helger.photon.api.IAPIInvoker;
import com.helger.photon.api.IAPIRegistry;
import com.helger.photon.api.InvokableAPIDescriptor;
import com.helger.photon.app.PhotonUnifiedResponse;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScope;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.xservlet.handler.simple.IXServletSimpleHandler;

/**
 * Abstract API servlet. Use {@link IAPIRegistry} to register API functions
 * dynamically and {@link IAPIInvoker} to invoke them afterwards.
 *
 * @author Philip Helger
 */
public class APIXServletHandler implements IXServletSimpleHandler
{
  private static final Logger LOGGER = LoggerFactory.getLogger (APIXServletHandler.class);

  private final ISupplier <? extends IAPIRegistry> m_aRegistryFactory;
  private final ISupplier <? extends IAPIInvoker> m_aInvokerFactory;

  public APIXServletHandler ()
  {
    this ( () -> GlobalAPIInvoker.getInstance ().getRegistry (), () -> GlobalAPIInvoker.getInstance ().getInvoker ());
  }

  public APIXServletHandler (@Nonnull final ISupplier <? extends IAPIRegistry> aRegistryFactory,
                             @Nonnull final ISupplier <? extends IAPIInvoker> aInvokerFactory)
  {
    m_aRegistryFactory = ValueEnforcer.notNull (aRegistryFactory, "RegistryFactory");
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
  public void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull final UnifiedResponse aUnifiedResponse) throws Exception
  {
    final EHttpMethod eHttpMethod = aRequestScope.getHttpMethod ();
    if (eHttpMethod == null)
    {
      // Should have been caught in AbstractXServlet.service ...
      LOGGER.error ("Missing HTTP method in request: " + aRequestScope.getRequest ());

      // No such action
      aUnifiedResponse.setStatus (CHttp.HTTP_NOT_IMPLEMENTED);
      return;
    }

    final APIPath aAPIPath = APIPath.createForServlet (aRequestScope);
    final IAPIRegistry aRegistry = m_aRegistryFactory.get ();
    final IAPIInvoker aInvoker = m_aInvokerFactory.get ();

    final InvokableAPIDescriptor aInvokableDescriptor = aRegistry.getAPIByPath (aAPIPath);
    if (aInvokableDescriptor == null)
    {
      LOGGER.warn ("Unknown API " + eHttpMethod + " '" + aAPIPath.getPath () + "' requested!");

      // No such action
      aUnifiedResponse.setStatus (CHttp.HTTP_NOT_FOUND);
    }
    else
    {
      // Is the declaration applicable for the current scope?
      // Check for required headers and parameters
      final MutableInt aStatusCode = new MutableInt (CHttp.HTTP_BAD_REQUEST);
      if (!aInvokableDescriptor.canExecute (aRequestScope, aStatusCode))
      {
        final int nStatusCode = aStatusCode.intValue ();
        LOGGER.warn ("API " +
                     eHttpMethod +
                     " '" +
                     aAPIPath.getPath () +
                     "' cannot be executed for the current request. Returning HTTP " +
                     nStatusCode);
        aUnifiedResponse.setStatus (nStatusCode);
      }
      else
      {
        // Don't cache the result. The executor may overwrite it
        aUnifiedResponse.disableCaching ();

        try
        {
          // Main API invocation
          aInvoker.invoke (aInvokableDescriptor, aRequestScope, GenericReflection.uncheckedCast (aUnifiedResponse));

          if (aUnifiedResponse.isStatusCodeDefined () || aUnifiedResponse.isRedirectDefined ())
          {
            // Status codes are not meant to be cached
            aUnifiedResponse.removeCaching ();
          }
          else
            if (!aUnifiedResponse.isStatusCodeDefined () &&
                !aUnifiedResponse.isRedirectDefined () &&
                !aUnifiedResponse.hasContent ())
            {
              // Set "No Content" response
              aUnifiedResponse.setStatus (CHttp.HTTP_NO_CONTENT);
            }
        }
        catch (final IOException | ServletException ex)
        {
          // Re-throw
          throw ex;
        }
        catch (final Exception ex)
        {
          throw new ServletException ("Error invoking API " + eHttpMethod + " '" + aAPIPath.getPath () + "'", ex);
        }
      }
    }
  }
}
