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
package com.helger.photon.core.servlet;

import java.io.IOException;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.commons.lang.ServiceLoaderHelper;
import com.helger.commons.state.EContinue;
import com.helger.photon.basic.app.PhotonSessionState;
import com.helger.photon.basic.app.request.ApplicationRequestManager;
import com.helger.photon.core.app.html.IHTMLProvider;
import com.helger.photon.core.app.html.PhotonHTMLHelper;
import com.helger.photon.core.app.redirect.ForcedRedirectException;
import com.helger.photon.core.app.redirect.ForcedRedirectManager;
import com.helger.photon.core.spi.IApplicationRequestListenerSPI;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.response.ERedirectMode;
import com.helger.web.servlet.response.UnifiedResponse;

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
    m_aListeners = ServiceLoaderHelper.getAllSPIImplementations (IApplicationRequestListenerSPI.class);
    if (!m_aListeners.isEmpty ())
      s_aLogger.info ("Loaded " + m_aListeners.size () + " IApplicationRequestListenerSPI implementations");
  }

  @Override
  @OverrideOnDemand
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
        s_aLogger.error ("Failed to invoke onRequestBegin on " + aListener + " with request " + aRequestScope, t);
      }
  }

  @Override
  @OverrideOnDemand
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
        s_aLogger.error ("Failed to invoke onRequestEnd on " +
                         aListener +
                         "; exceptionOccured=" +
                         bExceptionOccurred,
                         t);
      }
  }

  /**
   * @param aRequestScope
   *        The request scope
   * @return The HTML provider that creates the content. May not be
   *         <code>null</code>.
   */
  @Nonnull
  protected abstract IHTMLProvider createHTMLProvider (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope);

  /**
   * Callback method instantiated upon exception. If you override this
   * application ensure to call the method of this class as well as it handles
   * the POST-REDIRECT-GET pattern in here (using the
   * {@link ForcedRedirectException}).
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
  @OverridingMethodsMustInvokeSuper
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
  @OverridingMethodsMustInvokeSuper
  protected void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                @Nonnull final UnifiedResponse aUnifiedResponse) throws IOException, ServletException
  {
    final String sApplicationID = getApplicationID ();

    try
    {
      // Set the last application ID in the session
      PhotonSessionState.getInstance ().setLastApplicationID (sApplicationID);

      // Who is responsible for creating the HTML?
      final IHTMLProvider aHTMLProvider = createHTMLProvider (aRequestScope);

      // Create the HTML and put it into the response
      PhotonHTMLHelper.createHTMLResponse (aRequestScope, aUnifiedResponse, aHTMLProvider);
    }
    catch (final Throwable t)
    {
      // Call exception callback
      if (handleApplicationException (aRequestScope, aUnifiedResponse, t).isContinue ())
      {
        // Do not show the exceptions that occur, when client cancels a request.
        if (!StreamHelper.isKnownEOFException (t))
        {
          // Catch Exception and re-throw
          s_aLogger.error ("Error running application '" + sApplicationID + "'", t);
          if (t instanceof ServletException)
            throw (ServletException) t;
          if (t instanceof IOException)
            throw (IOException) t;
          throw new ServletException (t);
        }
      }
    }
  }
}
