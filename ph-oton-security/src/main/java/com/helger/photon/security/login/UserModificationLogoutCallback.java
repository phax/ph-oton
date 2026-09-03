/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.security.login;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.base.state.EChange;
import com.helger.photon.security.user.IUserModificationCallback;

/**
 * Implementation of {@link IUserModificationCallback} that logs out a user as soon as he is no
 * longer allowed to be logged in - meaning he was deleted, he was disabled or his password was
 * changed. Without this callback such a user would stay logged in until his session times out.
 *
 * @author Philip Helger
 * @since 10.5.0
 */
@ThreadSafe
public class UserModificationLogoutCallback implements IUserModificationCallback
{
  /** By default a deleted user is logged out */
  public static final boolean DEFAULT_LOGOUT_ON_USER_DELETED = true;
  /** By default a disabled user is logged out */
  public static final boolean DEFAULT_LOGOUT_ON_USER_DISABLED = true;
  /** By default a user with a changed password is logged out */
  public static final boolean DEFAULT_LOGOUT_ON_PASSWORD_CHANGED = true;

  private static final Logger LOGGER = LoggerFactory.getLogger (UserModificationLogoutCallback.class);

  private volatile boolean m_bLogoutOnUserDeleted = DEFAULT_LOGOUT_ON_USER_DELETED;
  private volatile boolean m_bLogoutOnUserDisabled = DEFAULT_LOGOUT_ON_USER_DISABLED;
  private volatile boolean m_bLogoutOnPasswordChanged = DEFAULT_LOGOUT_ON_PASSWORD_CHANGED;

  public UserModificationLogoutCallback ()
  {}

  /**
   * @return <code>true</code> if a user is logged out when he is deleted. Default is
   *         {@link #DEFAULT_LOGOUT_ON_USER_DELETED}.
   */
  public final boolean isLogoutOnUserDeleted ()
  {
    return m_bLogoutOnUserDeleted;
  }

  public final void setLogoutOnUserDeleted (final boolean bLogoutOnUserDeleted)
  {
    m_bLogoutOnUserDeleted = bLogoutOnUserDeleted;
  }

  /**
   * @return <code>true</code> if a user is logged out when he is disabled. Default is
   *         {@link #DEFAULT_LOGOUT_ON_USER_DISABLED}.
   */
  public final boolean isLogoutOnUserDisabled ()
  {
    return m_bLogoutOnUserDisabled;
  }

  public final void setLogoutOnUserDisabled (final boolean bLogoutOnUserDisabled)
  {
    m_bLogoutOnUserDisabled = bLogoutOnUserDisabled;
  }

  /**
   * @return <code>true</code> if a user is logged out when his password is changed. Default is
   *         {@link #DEFAULT_LOGOUT_ON_PASSWORD_CHANGED}. Note: the internal password hash algorithm
   *         upgrade performed by {@link LoggedInUserManager#loginUser(com.helger.photon.security.user.IUser, String, Iterable)}
   *         never triggers a logout, independent of this setting.
   */
  public final boolean isLogoutOnPasswordChanged ()
  {
    return m_bLogoutOnPasswordChanged;
  }

  public final void setLogoutOnPasswordChanged (final boolean bLogoutOnPasswordChanged)
  {
    m_bLogoutOnPasswordChanged = bLogoutOnPasswordChanged;
  }

  private static void _logout (@NonNull @Nonempty final String sUserID, @NonNull final String sReason)
  {
    if (LoggedInUserManager.internalIsPasswordHashUpgradeInProgress (sUserID))
    {
      // This is not a real password change but only an internal re-hashing with
      // the current default algorithm - don't log anybody out because of that
      return;
    }

    final EChange eChange = LoggedInUserManager.getInstance ().logoutUser (sUserID);
    if (eChange.isChanged ())
      LOGGER.info ("Logged out user '" + sUserID + "' because " + sReason);
  }

  @Override
  public void onUserDeleted (@NonNull @Nonempty final String sUserID)
  {
    if (m_bLogoutOnUserDeleted)
      _logout (sUserID, "the user was deleted");
  }

  @Override
  public void onUserEnabled (@NonNull @Nonempty final String sUserID, final boolean bEnabled)
  {
    if (!bEnabled && m_bLogoutOnUserDisabled)
      _logout (sUserID, "the user was disabled");
  }

  @Override
  public void onUserPasswordChanged (@NonNull @Nonempty final String sUserID)
  {
    if (m_bLogoutOnPasswordChanged)
      _logout (sUserID, "the password of the user was changed");
  }
}
