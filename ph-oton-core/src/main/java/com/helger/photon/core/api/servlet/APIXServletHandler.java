/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.core.api.servlet;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.functional.ISupplier;
import com.helger.commons.http.EHttpMethod;
import com.helger.commons.io.file.FilenameHelper;
import com.helger.commons.lang.GenericReflection;
import com.helger.http.EHttpVersion;
import com.helger.photon.core.PhotonUnifiedResponse;
import com.helger.photon.core.api.APIPath;
import com.helger.photon.core.api.GlobalAPIInvoker;
import com.helger.photon.core.api.IAPIInvoker;
import com.helger.photon.core.api.InvokableAPIDescriptor;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScope;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.xservlet.handler.simple.IXServletSimpleHandler;

/**
 * Abstract API servlet. Use {@link GlobalAPIInvoker} to register API functions
 * dynamically.
 *
 * @author Philip Helger
 */
public class APIXServletHandler implements IXServletSimpleHandler
{
  private static final Logger LOGGER = LoggerFactory.getLogger (APIXServletHandler.class);

  private final ISupplier <? extends IAPIInvoker> m_aFactory;

  public APIXServletHandler ()
  {
    this ( () -> GlobalAPIInvoker.getInstance ());
  }

  public APIXServletHandler (@Nonnull final ISupplier <? extends IAPIInvoker> aFactory)
  {
    m_aFactory = ValueEnforcer.notNull (aFactory, "Factory");
  }

  @Nonnull
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
    // ensure leading "/"
    final String sAPIPath = FilenameHelper.ensurePathStartingWithSeparator (aRequestScope.getPathWithinServlet ());
    final EHttpMethod eHTTPMethod = aRequestScope.getHttpMethod ();
    final IAPIInvoker aAPIMgr = m_aFactory.get ();
    final InvokableAPIDescriptor aInvokableDescriptor = aAPIMgr.getAPIByPath (new APIPath (eHTTPMethod, sAPIPath));
    if (aInvokableDescriptor == null)
    {
      LOGGER.warn ("Unknown API " + eHTTPMethod + " '" + sAPIPath + "' requested!");

      // No such action
      aUnifiedResponse.setStatus (HttpServletResponse.SC_NOT_FOUND);
    }
    else
    {
      // Is the declaration applicable for the current scope?
      // Check for required headers and parameters
      if (!aInvokableDescriptor.canExecute (aRequestScope))
      {
        LOGGER.warn ("API " + eHTTPMethod + " '" + sAPIPath + "' cannot be executed for the current request.");
        aUnifiedResponse.setStatus (HttpServletResponse.SC_BAD_REQUEST);
      }
      else
      {
        // Don't cache the result. The executor may overwrite it
        aUnifiedResponse.disableCaching ();

        try
        {
          // Main API invocation
          aAPIMgr.invoke (aInvokableDescriptor, aRequestScope, GenericReflection.uncheckedCast (aUnifiedResponse));

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
              aUnifiedResponse.setStatus (HttpServletResponse.SC_NO_CONTENT);
            }
        }
        catch (final IOException | ServletException ex)
        {
          // Re-throw
          throw ex;
        }
        catch (final Throwable t)
        {
          throw new ServletException ("Error invoking API " + eHTTPMethod + " '" + sAPIPath + "'", t);
        }
      }
    }
  }
}
