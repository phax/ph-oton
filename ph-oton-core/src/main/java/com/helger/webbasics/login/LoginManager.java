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
package com.helger.webbasics.login;

import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.GlobalDebug;
import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.state.EContinue;
import com.helger.commons.string.StringHelper;
import com.helger.photon.basic.security.AccessManager;
import com.helger.photon.basic.security.login.ELoginResult;
import com.helger.photon.basic.security.login.LoggedInUserManager;
import com.helger.photon.basic.security.login.LoginInfo;
import com.helger.photon.basic.security.user.IUser;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.response.UnifiedResponse;
import com.helger.web.useragent.UserAgentDatabase;
import com.helger.webbasics.app.html.IHTMLProvider;
import com.helger.webbasics.app.html.WebHTMLCreator;

/**
 * Handle the application login process. This class requires a separate UI.
 *
 * @author Philip Helger
 */
public class LoginManager
{
  /**
   * Attribute name for the LoginInfo attribute that holds the remote address of
   * the last request. Type: String.
   */
  public static final String LOGIN_INFO_REMOTE_ADDRESS = "remote-address";

  /**
   * Attribute name for the LoginInfo attribute that holds the remote host of
   * the last request. Type: String.
   */
  public static final String LOGIN_INFO_REMOTE_HOST = "remote-host";

  /**
   * Attribute name for the LoginInfo attribute that holds the URI (without the
   * query string) of the last request. Type: String.
   */
  public static final String LOGIN_INFO_REQUEST_URI = "request-uri";

  /**
   * Attribute name for the LoginInfo attribute that holds the query string of
   * the last request. Type: String.
   */
  public static final String LOGIN_INFO_QUERY_STRING = "query-string";

  /**
   * Attribute name for the LoginInfo attribute that holds the user-agent string
   * of the last request. Type: String.
   */
  public static final String LOGIN_INFO_USER_AGENT = "user-agent";

  /**
   * Attribute name for the LoginInfo attribute that holds the number of
   * requests in this session. Type: int.
   *
   * @since 2.1.12
   */
  public static final String LOGIN_INFO_REQUEST_COUNT = "request-count";

  private static final Logger s_aLogger = LoggerFactory.getLogger (LoginManager.class);

  /**
   * Create the HTML code used to render the login screen
   *
   * @param bLoginError
   *        If <code>true</code> an error occurred in a previous login action
   * @param eLoginResult
   *        The login result - only relevant in case of a login error
   * @return Never <code>null</code>.
   */
  @OverrideOnDemand
  protected IHTMLProvider createLoginScreen (final boolean bLoginError, @Nonnull final ELoginResult eLoginResult)
  {
    return new LoginHTMLProvider (bLoginError, eLoginResult);
  }

  /**
   * Check if the login process is in progress
   *
   * @param aRequestScope
   *        Request scope
   * @return <code>true</code> if it is in progress
   * @since 3.4.0
   */
  @OverrideOnDemand
  protected boolean isLoginInProgress (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return CLogin.REQUEST_ACTION_VALIDATE_LOGIN_CREDENTIALS.equals (aRequestScope.getAttributeAsString (CLogin.REQUEST_PARAM_ACTION));
  }

  /**
   * Get the current login name
   *
   * @param aRequestScope
   *        Request scope
   * @return <code>null</code> if no login name was present
   * @since 3.4.0
   */
  @Nullable
  @OverrideOnDemand
  protected String getLoginName (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return aRequestScope.getAttributeAsString (CLogin.REQUEST_ATTR_USERID);
  }

  /**
   * Get the current password
   *
   * @param aRequestScope
   *        Request scope
   * @return <code>null</code> if no password was present
   * @since 3.4.0
   */
  @Nullable
  @OverrideOnDemand
  protected String getPassword (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return aRequestScope.getAttributeAsString (CLogin.REQUEST_ATTR_PASSWORD);
  }

  /**
   * Get the {@link IUser} instance of the specified login name.
   *
   * @param sLoginName
   *        The login name to use. May be <code>null</code>.
   * @return <code>null</code> if no such user exists.
   */
  @Nullable
  @OverrideOnDemand
  protected IUser getUserOfLoginName (@Nullable final String sLoginName)
  {
    return AccessManager.getInstance ().getUserOfLoginName (sLoginName);
  }

  /**
   * @return A list of all role IDs that the user must have so that he can
   *         login! May be <code>null</code> to indicate that any valid user can
   *         login.
   */
  @Nullable
  @OverrideOnDemand
  protected Collection <String> getAllRequiredRoleIDs ()
  {
    return null;
  }

  /**
   * Callback method to notify on a successful login
   *
   * @param aUser
   *        The user who just logged in. Never <code>null</code>.
   * @param eLoginResult
   *        The login result. A success message anyway.
   */
  @OverrideOnDemand
  protected void onUserLogin (@Nonnull final IUser aUser, @Nonnull final ELoginResult eLoginResult)
  {}

  /**
   * Modify the passed {@link LoginInfo} object with details of the passed
   * request scope. This method is called for every request!
   *
   * @param aLoginInfo
   *        Login Info. Never <code>null</code>.
   * @param aRequestScope
   *        The current request scope.
   * @param bLoggedInInThisRequest
   *        <code>true</code> if the user just logged in with this request.
   *        Added in 3.4.0.
   */
  @OverrideOnDemand
  protected void modifyLoginInfo (@Nonnull final LoginInfo aLoginInfo,
                                  @Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                  final boolean bLoggedInInThisRequest)
  {
    // Set some debugging details
    aLoginInfo.setAttribute (LOGIN_INFO_REMOTE_ADDRESS, aRequestScope.getRemoteAddr ());
    aLoginInfo.setAttribute (LOGIN_INFO_REMOTE_HOST, aRequestScope.getRemoteHost ());
    aLoginInfo.setAttribute (LOGIN_INFO_REQUEST_URI, aRequestScope.getRequestURI ());
    aLoginInfo.setAttribute (LOGIN_INFO_QUERY_STRING, aRequestScope.getQueryString ());
    aLoginInfo.setAttribute (LOGIN_INFO_USER_AGENT,
                             UserAgentDatabase.getHttpUserAgentStringFromRequest (aRequestScope.getRequest ()));
    aLoginInfo.setAttribute (LOGIN_INFO_REQUEST_COUNT, aLoginInfo.getAttributeAsInt (LOGIN_INFO_REQUEST_COUNT, 0) + 1);
  }

  /**
   * Main login routine.
   *
   * @param aRequestScope
   *        Request scope
   * @param aUnifiedResponse
   *        Response
   * @return {@link EContinue#BREAK} to indicate that no user is logged in and
   *         therefore the login screen should be shown,
   *         {@link EContinue#CONTINUE} if a user is correctly logged in.
   */
  @Nonnull
  public final EContinue checkUserAndShowLogin (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                @Nonnull final UnifiedResponse aUnifiedResponse)
  {
    final LoggedInUserManager aLoggedInUserManager = LoggedInUserManager.getInstance ();
    String sSessionUserID = aLoggedInUserManager.getCurrentUserID ();
    boolean bLoggedInInThisRequest = false;

    if (sSessionUserID == null)
    {
      // No user currently logged in -> start login
      boolean bLoginError = false;
      ELoginResult eLoginResult = ELoginResult.SUCCESS;

      // Is the special login-check action present?
      if (isLoginInProgress (aRequestScope))
      {
        // Login screen was already shown
        // -> Check request parameters
        final String sLoginName = getLoginName (aRequestScope);
        final String sPassword = getPassword (aRequestScope);

        // Resolve user - may be null
        final IUser aUser = getUserOfLoginName (sLoginName);

        // Try main login
        eLoginResult = aLoggedInUserManager.loginUser (aUser, sPassword, getAllRequiredRoleIDs ());
        if (eLoginResult.isSuccess ())
        {
          // Credentials are valid - implies that the user was resolved
          // correctly
          sSessionUserID = aUser.getID ();
          onUserLogin (aUser, eLoginResult);
          bLoggedInInThisRequest = true;
        }

        if (eLoginResult.isFailure ())
        {
          // Credentials are invalid
          if (GlobalDebug.isDebugMode ())
            s_aLogger.warn ("Login of '" + sLoginName + "' failed because " + eLoginResult);

          // Anyway show the error message only if at least some credential
          // values are passed
          bLoginError = StringHelper.hasText (sLoginName) || StringHelper.hasText (sPassword);
        }
      }

      if (sSessionUserID == null)
      {
        // Show login screen as no user is in the session
        final IHTMLProvider aLoginScreenProvider = createLoginScreen (bLoginError, eLoginResult);
        WebHTMLCreator.createHTMLResponse (aRequestScope, aUnifiedResponse, aLoginScreenProvider);
      }
    }

    // Update details
    final LoginInfo aLoginInfo = aLoggedInUserManager.getLoginInfo (sSessionUserID);
    if (aLoginInfo != null)
    {
      // Update last login info
      aLoginInfo.setLastAccessDTNow ();

      // Set custom attributes
      modifyLoginInfo (aLoginInfo, aRequestScope, bLoggedInInThisRequest);
    }
    else
    {
      // Internal inconsistency
      if (sSessionUserID != null)
        s_aLogger.error ("Failed to resolve LoginInfo of user ID '" + sSessionUserID + "'");
    }

    if (bLoggedInInThisRequest)
    {
      // Avoid double submit by simply redirecting to the desired destination
      // URL without the login parameters
      aUnifiedResponse.setRedirect (aRequestScope.getURL ());
      return EContinue.BREAK;
    }

    // Continue only, if a valid user ID is present
    return EContinue.valueOf (sSessionUserID != null);
  }
}
