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
package com.helger.appbasics.security.util;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.appbasics.security.AccessManager;
import com.helger.appbasics.security.login.LoggedInUserManager;
import com.helger.appbasics.security.user.IUser;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.PresentForCodeCoverage;
import com.helger.commons.string.StringHelper;

/**
 * Security utility methods
 *
 * @author Philip Helger
 */
@Immutable
public final class SecurityUtils
{
  @PresentForCodeCoverage
  private static final SecurityUtils s_aInstance = new SecurityUtils ();

  private SecurityUtils ()
  {}

  public static boolean isCurrentUserAssignedToUserGroup (@Nullable final String sUserGroupID)
  {
    final String sUserID = LoggedInUserManager.getInstance ().getCurrentUserID ();
    if (sUserID == null)
    {
      // No user logged in
      return false;
    }
    return AccessManager.getInstance ().isUserAssignedToUserGroup (sUserGroupID, sUserID);
  }

  public static boolean hasCurrentUserRole (@Nullable final String sRoleID)
  {
    final String sUserID = LoggedInUserManager.getInstance ().getCurrentUserID ();
    if (sUserID == null)
    {
      // No user logged in
      return false;
    }
    return AccessManager.getInstance ().hasUserRole (sUserID, sRoleID);
  }

  /**
   * Get the display name of the guest user in the specified locale.
   *
   * @param aDisplayLocale
   *        The locale to be used. May not be <code>null</code>.
   * @return <code>null</code> if no translation is present.
   */
  @Nullable
  public static String getGuestUserDisplayName (@Nonnull final Locale aDisplayLocale)
  {
    ValueEnforcer.notNull (aDisplayLocale, "DisplayLocale");

    return ESecurityUIText.GUEST.getDisplayText (aDisplayLocale);
  }

  /**
   * Get the display name of the user.
   *
   * @param sUserID
   *        User ID. May be <code>null</code>.
   * @param aDisplayLocale
   *        The display locale to be used.
   * @return The "guest" text if no user ID was provided, the display name of
   *         the user if a valid user ID was provided or the ID of the user if
   *         an invalid user was provided.
   */
  @Nullable
  public static String getUserDisplayName (@Nullable final String sUserID, @Nonnull final Locale aDisplayLocale)
  {
    if (StringHelper.hasNoText (sUserID))
      return getGuestUserDisplayName (aDisplayLocale);

    final IUser aUser = AccessManager.getInstance ().getUserOfID (sUserID);
    return aUser == null ? sUserID : getUserDisplayName (aUser, aDisplayLocale);
  }

  /**
   * Get the display name of the user. If no display name is present (because
   * first name and last name are empty), the login name is returned.
   *
   * @param aUser
   *        User. May be <code>null</code>.
   * @param aDisplayLocale
   *        The display locale to be used to resolve the "Guest" text if the
   *        passed user is <code>null</code>.
   * @return Never <code>null</code>. Either the display name or the login name
   *         of the user.
   */
  @Nullable
  public static String getUserDisplayName (@Nullable final IUser aUser, @Nonnull final Locale aDisplayLocale)
  {
    ValueEnforcer.notNull (aDisplayLocale, "DisplayLocale");
    if (aUser == null)
      return getGuestUserDisplayName (aDisplayLocale);

    String ret = aUser.getDisplayName ();
    if (StringHelper.hasNoText (ret))
      ret = aUser.getLoginName ();
    return ret;
  }
}
