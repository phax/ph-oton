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
package com.helger.photon.core.api.servlet;

import java.io.IOException;
import java.util.EnumSet;

import javax.annotation.Nonnull;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.http.EHttpMethod;
import com.helger.commons.io.file.FilenameHelper;
import com.helger.photon.core.api.APIPath;
import com.helger.photon.core.api.ApplicationAPIManager;
import com.helger.photon.core.api.IAPIInvoker;
import com.helger.photon.core.api.InvokableAPIDescriptor;
import com.helger.photon.core.servlet.AbstractUnifiedResponseServlet;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Abstract API servlet. Use {@link ApplicationAPIManager} to register API
 * functions dynamically.
 *
 * @author Philip Helger
 */
public abstract class AbstractAPIServlet extends AbstractUnifiedResponseServlet
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractAPIServlet.class);
  private static final EnumSet <EHttpMethod> ALL = EnumSet.allOf (EHttpMethod.class);

  protected AbstractAPIServlet ()
  {}

  @Override
  @Nonnull
  @CodingStyleguideUnaware
  protected EnumSet <EHttpMethod> getAllowedHTTPMethods ()
  {
    return ALL;
  }

  /**
   * Get the API invoker matching the passed request
   *
   * @param aRequestScope
   *        The request scope to use. May not be <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  protected abstract IAPIInvoker getAPIInvoker (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope);

  @Override
  protected void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                @Nonnull final UnifiedResponse aUnifiedResponse) throws Exception
  {
    // ensure leading "/"
    final String sAPIPath = FilenameHelper.ensurePathStartingWithSeparator (aRequestScope.getPathWithinServlet ());
    final EHttpMethod eHTTPMethod = aRequestScope.getHttpMethod ();
    final IAPIInvoker aAPIMgr = getAPIInvoker (aRequestScope);
    final InvokableAPIDescriptor aInvokableDescriptor = aAPIMgr.getAPIByPath (new APIPath (eHTTPMethod, sAPIPath));
    if (aInvokableDescriptor == null)
    {
      s_aLogger.warn ("Unknown API " + eHTTPMethod + " '" + sAPIPath + "' requested!");

      // No such action
      aUnifiedResponse.setStatus (HttpServletResponse.SC_NOT_FOUND);
    }
    else
    {
      // Is the declaration applicable for the current scope?
      // Check for required headers and parameters
      if (!aInvokableDescriptor.canExecute (aRequestScope))
      {
        s_aLogger.warn ("API " + eHTTPMethod + " '" + sAPIPath + "' cannot be executed for the current request.");
        aUnifiedResponse.setStatus (HttpServletResponse.SC_BAD_REQUEST);
      }
      else
      {
        // Don't cache the result. The executor may overwrite it
        aUnifiedResponse.disableCaching ();

        try
        {
          // Main API invocation
          aAPIMgr.invoke (aInvokableDescriptor, aRequestScope, aUnifiedResponse);

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
