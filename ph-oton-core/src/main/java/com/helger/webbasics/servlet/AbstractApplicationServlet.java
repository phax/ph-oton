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
package com.helger.webbasics.servlet;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.appbasics.app.request.ApplicationRequestManager;
import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.io.streams.StreamUtils;
import com.helger.commons.lang.ServiceLoaderUtils;
import com.helger.commons.state.EContinue;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.response.ERedirectMode;
import com.helger.web.servlet.response.UnifiedResponse;
import com.helger.webbasics.app.html.IHTMLProvider;
import com.helger.webbasics.app.html.WebHTMLCreator;
import com.helger.webbasics.app.redirect.ForcedRedirectException;
import com.helger.webbasics.app.redirect.ForcedRedirectManager;
import com.helger.webbasics.spi.IApplicationRequestListenerSPI;

/**
 * Base servlet for the main application.
 *
 * @author Philip Helger
 */
public abstract class AbstractApplicationServlet extends AbstractUnifiedResponseServlet
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractApplicationServlet.class);

  private final List <IApplicationRequestListenerSPI> m_aListeners;

  protected AbstractApplicationServlet ()
  {
    m_aListeners = ServiceLoaderUtils.getAllSPIImplementations (IApplicationRequestListenerSPI.class);
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void onRequestBegin (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    // Run default request initialization (menu item and locale)
    ApplicationRequestManager.getRequestMgr ().onRequestBegin (aRequestScope);

    // Invoke all "request begin" listener
    for (final IApplicationRequestListenerSPI aListener : m_aListeners)
      try
      {
        aListener.onRequestBegin (aRequestScope);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onRequestBegin on " + aListener, t);
      }
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void onRequestEnd (final boolean bExceptionOccurred)
  {
    // Invoke all "request end" listener
    for (final IApplicationRequestListenerSPI aListener : m_aListeners)
      try
      {
        aListener.onRequestEnd (bExceptionOccurred);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onRequestEnd on " + aListener, t);
      }
  }

  /**
   * @param aRequestScope
   *        The request scope
   * @return The HTML provider that creates the content. May not be
   *         <code>null</code>.
   */
  @OverrideOnDemand
  @Nonnull
  protected abstract IHTMLProvider createHTMLProvider (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope);

  /**
   * Callback method instantiated upon exception
   *
   * @param aRequestScope
   *        Initial request scope
   * @param aUnifiedResponse
   *        Response to use
   * @param t
   *        Thrown exception
   * @return Never <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  protected EContinue handleApplicationException (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                  @Nonnull final UnifiedResponse aUnifiedResponse,
                                                  @Nonnull final Throwable t)
  {
    if (t instanceof ForcedRedirectException)
    {
      final ForcedRedirectException aFRE = (ForcedRedirectException) t;
      // Remember the content
      ForcedRedirectManager.getInstance ().createForcedRedirect (aFRE);
      // And set the redirect
      aUnifiedResponse.setRedirect (aFRE.getRedirectTargetURL (), ERedirectMode.POST_REDIRECT_GET);
      // Stop exception handling
      return EContinue.BREAK;
    }
    return EContinue.CONTINUE;
  }

  @Override
  protected final void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                      @Nonnull final UnifiedResponse aUnifiedResponse) throws ServletException
  {
    try
    {
      // Who is responsible for creating the HTML?
      final IHTMLProvider aHTMLProvider = createHTMLProvider (aRequestScope);

      // Create the HTML and put it into the response
      WebHTMLCreator.createHTMLResponse (aRequestScope, aUnifiedResponse, aHTMLProvider);
    }
    catch (final Throwable t)
    {
      // Call callback
      if (handleApplicationException (aRequestScope, aUnifiedResponse, t).isContinue ())
      {
        // Do not show the exceptions that occur, when client cancels a request.
        if (!StreamUtils.isKnownEOFException (t))
        {
          s_aLogger.error ("Error running application", t);
          // Catch Exception and re-throw
          if (t instanceof ServletException)
            throw (ServletException) t;
          throw new ServletException (t);
        }
      }
    }
  }
}
