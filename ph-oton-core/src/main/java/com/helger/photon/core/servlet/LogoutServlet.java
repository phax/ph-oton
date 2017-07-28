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
package com.helger.photon.core.servlet;

import java.io.IOException;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.url.ISimpleURL;
import com.helger.http.EHTTPMethod;
import com.helger.photon.basic.servletstatus.ServletStatusManager;
import com.helger.photon.core.url.LinkHelper;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Handles the log-out of a user. Can be called with a user context and without.
 *
 * @author Philip Helger
 */
public class LogoutServlet extends AbstractUnifiedResponseServlet
{
  public static final String SERVLET_DEFAULT_NAME = "logout";
  public static final String SERVLET_DEFAULT_PATH = "/" + SERVLET_DEFAULT_NAME;

  public LogoutServlet ()
  {}

  public static boolean isServletRegisteredInServletContext ()
  {
    return ServletStatusManager.getInstance ().isServletRegistered (LogoutServlet.class);
  }

  @Override
  @Nonnull
  protected Set <EHTTPMethod> getAllowedHTTPMethods ()
  {
    // HEAD makes no sense here
    return ALLOWED_METHDOS_GET_POST;
  }

  /**
   * Get the redirection URL. This method is invoked BEFORE an eventual log-out
   * happens!
   *
   * @param aRequestScope
   *        The request web scope to be used. Required for cookie-less handling.
   *        May not be <code>null</code>.
   * @return The URL to redirect to, after this servlet was invoked. May not be
   *         <code>null</code>.
   */
  @OverrideOnDemand
  @Nonnull
  protected ISimpleURL getRedirectURL (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    // No need for a session ID upon logout
    return LinkHelper.getHomeLinkWithoutSession ();
  }

  /**
   * Optional callback to be invoked before the session (if present) is
   * invalidated. May not throw any exception!
   *
   * @param aRequestScope
   *        The current request scope. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void onBeforeSessionInvalidate (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {}

  /**
   * Optional callback to be invoked before the redirect happens. May not throw
   * any exception!
   *
   * @param aRequestScope
   *        The current request scope. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void onBeforeRedirect (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {}

  @Override
  protected void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                @Nonnull final UnifiedResponse aUnifiedResponse) throws ServletException, IOException
  {
    // Get the redirect URL before the session is invalidated, in case the
    // acquisition code requires the current session
    final ISimpleURL aRedirectURL = getRedirectURL (aRequestScope);

    // callback before session invalidate
    onBeforeSessionInvalidate (aRequestScope);

    // Don't create a session, if none is present
    final HttpSession aHttpSession = aRequestScope.getSession (false);
    if (aHttpSession != null)
    {
      // Perform the main logout
      // 1. Invalidate the session
      // 2. Triggers the session scope destruction (via the HttpSessionListener)
      // 3. which triggers WebScopeManager.onSessionEnd
      // 4. which triggers ScopeSessionManager.onScopeEnd
      // 5. which triggers ISessionWebScope.destroyScope
      // 6. which triggers InternalSessionUserHolder.onDestroy
      // 7. which triggers LoggedInUserManager.logoutUser
      aHttpSession.invalidate ();
    }

    // callback before redirect
    onBeforeRedirect (aRequestScope);

    // Go home
    aUnifiedResponse.setRedirect (aRedirectURL);
  }
}
