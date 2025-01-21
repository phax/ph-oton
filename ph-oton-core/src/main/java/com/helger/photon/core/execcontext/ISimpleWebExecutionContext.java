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
package com.helger.photon.core.execcontext;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.url.SimpleURL;
import com.helger.photon.core.menu.IMenuTree;
import com.helger.photon.core.requestparam.RequestParameterManager;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.user.IUser;
import com.helger.servlet.request.IRequestParamMap;
import com.helger.web.scope.IRequestParamContainer;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Interface with the simple web execution context. It consist of a request
 * scope (servlet request etc.), a display locale (determined from URL, session
 * or a default), a menu tree (determined from the application ID in the URL,
 * the session or the default application ID) and the currently logged in user
 * (which may be optional).
 *
 * @author Philip Helger
 */
public interface ISimpleWebExecutionContext
{
  /**
   * @return The current request scope. Never <code>null</code>.
   */
  @Nonnull
  IRequestWebScopeWithoutResponse getRequestScope ();

  /**
   * @return The container with all request parameters. Never <code>null</code>.
   */
  @Nonnull
  default IRequestParamContainer params ()
  {
    return getRequestScope ().params ();
  }

  /**
   * @return A cached request param map for this request.
   */
  @Nonnull
  default IRequestParamMap getRequestParamMap ()
  {
    return getRequestScope ().getRequestParamMap ();
  }

  /**
   * @return The current display locale. Based on the locale of the request
   *         manager.
   */
  @Nonnull
  Locale getDisplayLocale ();

  /**
   * @return The current menu tree. Based on the menu tree of the request
   *         manager.
   */
  @Nonnull
  IMenuTree getMenuTree ();

  /**
   * @return The currently logged in user. May be <code>null</code>.
   * @since 8.1.3
   */
  @Nullable
  IUser getLoggedInUser ();

  /**
   * @return The ID of the currently logged in user. May be <code>null</code>.
   * @since 8.1.3
   */
  @Nullable
  default String getLoggedInUserID ()
  {
    final IUser aLoggedInUser = getLoggedInUser ();
    return aLoggedInUser == null ? null : aLoggedInUser.getID ();
  }

  /**
   * @return <code>true</code> if a user is logged in, and if the current user
   *         is the special "Administrator" user. This is a shortcut to
   *         <code>getLoggedInUser() != null &amp;&amp; getLoggedInUser().isAdmin()</code>
   * @since 8.1.3
   */
  boolean isLoggedInUserAdministrator ();

  /**
   * Check if the currently logged in user has the specified role.
   *
   * @param sRoleID
   *        The role ID to check. May be <code>null</code> but that would make
   *        no sense.
   * @return <code>true</code> if a user is logged in and if it has the role,
   *         <code>false</code> otherwise.
   * @since 8.1.3
   */
  default boolean hasLoggedInUserRole (@Nullable final String sRoleID)
  {
    final String sLoggedInUserID = getLoggedInUserID ();
    return sLoggedInUserID != null &&
           PhotonSecurityManager.getUserGroupMgr ().containsAnyUserGroupWithAssignedUserAndRole (sLoggedInUserID, sRoleID);
  }

  /**
   * Get the URL to the specified menu item using the current display locale.
   *
   * @param sMenuItemID
   *        The ID of the menu item to link to. May not be <code>null</code>.
   * @return The non-<code>null</code> URL to the specified menu item.
   */
  @Nonnull
  default SimpleURL getLinkToMenuItem (@Nonnull final String sMenuItemID)
  {
    return getLinkToMenuItem (getDisplayLocale (), sMenuItemID);
  }

  /**
   * Get the URL to the specified menu item.
   *
   * @param aDisplayLocale
   *        Specific display locale to be used. May not be <code>null</code>.
   * @param sMenuItemID
   *        The ID of the menu item to link to. May not be <code>null</code>.
   * @return The non-<code>null</code> URL to the specified menu item.
   * @since 7.0.2
   */
  @Nonnull
  default SimpleURL getLinkToMenuItem (@Nonnull final Locale aDisplayLocale, @Nonnull final String sMenuItemID)
  {
    return RequestParameterManager.getInstance ().getLinkToMenuItem (getRequestScope (), aDisplayLocale, sMenuItemID);
  }

  /**
   * Get the URL to the specified menu item in the passed application. This is
   * helpful when linking between different applications. The current locale is
   * used.
   *
   * @param sAppID
   *        The application ID to use. May neither be <code>null</code> nor
   *        empty.
   * @param sMenuItemID
   *        The ID of the menu item to link to. May not be <code>null</code>.
   * @return The non-<code>null</code> URL to the specified menu item.
   */
  @Nonnull
  default SimpleURL getLinkToMenuItem (@Nonnull @Nonempty final String sAppID, @Nonnull final String sMenuItemID)
  {
    return getLinkToMenuItem (sAppID, getDisplayLocale (), sMenuItemID);
  }

  /**
   * Get the URL to the specified menu item in the passed application using the
   * specified locale. This is helpful when linking between different
   * applications.
   *
   * @param sAppID
   *        The application ID to use. May neither be <code>null</code> nor
   *        empty.
   * @param aDisplayLocale
   *        Specific display locale to be used. May not be <code>null</code>.
   * @param sMenuItemID
   *        The ID of the menu item to link to. May not be <code>null</code>.
   * @return The non-<code>null</code> URL to the specified menu item.
   * @since 7.0.2
   */
  @Nonnull
  default SimpleURL getLinkToMenuItem (@Nonnull @Nonempty final String sAppID,
                                       @Nonnull final Locale aDisplayLocale,
                                       @Nonnull final String sMenuItemID)
  {
    return RequestParameterManager.getInstance ().getLinkToMenuItem (sAppID, getRequestScope (), aDisplayLocale, sMenuItemID);
  }
}
