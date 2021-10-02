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
package com.helger.photon.security.user;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.callback.ICallback;

/**
 * Callback interface when a user is created, modified, deleted, undeleted,
 * enabled or disabled.
 *
 * @author Philip Helger
 */
public interface IUserModificationCallback extends ICallback
{
  /**
   * Called after a user was created.
   *
   * @param aUser
   *        The created user. Never <code>null</code>.
   * @param bPredefinedUser
   *        <code>true</code> if it is a predefined user, <code>false</code> if
   *        it is a regular user
   */
  default void onUserCreated (@Nonnull final IUser aUser, final boolean bPredefinedUser)
  {}

  /**
   * Called after a user was edited fully.
   *
   * @param sUserID
   *        The modified user ID. Never <code>null</code>.
   */
  default void onUserUpdated (@Nonnull @Nonempty final String sUserID)
  {}

  /**
   * Called after a user's last failed login was updated.
   *
   * @param sUserID
   *        The modified user ID. Never <code>null</code>.
   */
  default void onUserLastFailedLoginUpdated (@Nonnull @Nonempty final String sUserID)
  {}

  /**
   * Called after a user's password was changed.
   *
   * @param sUserID
   *        The modified user ID. Never <code>null</code>.
   */
  default void onUserPasswordChanged (@Nonnull @Nonempty final String sUserID)
  {}

  /**
   * Called after a user was deleted.
   *
   * @param sUserID
   *        The modified user ID. Never <code>null</code>.
   */
  default void onUserDeleted (@Nonnull @Nonempty final String sUserID)
  {}

  /**
   * Called after a user was undeleted.
   *
   * @param sUserID
   *        The modified user ID. Never <code>null</code>.
   */
  default void onUserUndeleted (@Nonnull @Nonempty final String sUserID)
  {}

  /**
   * Called after a user was enabled or disabled.
   *
   * @param sUserID
   *        The enabled or disabled user ID. Never <code>null</code>.
   * @param bEnabled
   *        <code>true</code> if the user was enabled, <code>false</code> if it
   *        was disabled
   */
  default void onUserEnabled (@Nonnull @Nonempty final String sUserID, final boolean bEnabled)
  {}
}
