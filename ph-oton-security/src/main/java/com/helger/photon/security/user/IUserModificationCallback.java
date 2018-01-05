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
package com.helger.photon.security.user;

import javax.annotation.Nonnull;

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
   * @param aUser
   *        The modified user. Never <code>null</code>.
   */
  default void onUserUpdated (@Nonnull final IUser aUser)
  {}

  /**
   * Called after a user's last failed login was updated.
   *
   * @param aUser
   *        The modified user. Never <code>null</code>.
   */
  default void onUserLastFailedLoginUpdated (@Nonnull final IUser aUser)
  {}

  /**
   * Called after a user's password was changed.
   *
   * @param aUser
   *        The modified user. Never <code>null</code>.
   */
  default void onUserPasswordChanged (@Nonnull final IUser aUser)
  {}

  /**
   * Called after a user was deleted.
   *
   * @param aUser
   *        The deleted user. Never <code>null</code>.
   */
  default void onUserDeleted (@Nonnull final IUser aUser)
  {}

  /**
   * Called after a user was undeleted.
   *
   * @param aUser
   *        The undeleted user. Never <code>null</code>.
   */
  default void onUserUndeleted (@Nonnull final IUser aUser)
  {}

  /**
   * Called after a user was enabled or disabled.
   *
   * @param aUser
   *        The enabled or disabled user. Never <code>null</code>.
   * @param bEnabled
   *        <code>true</code> if the user was enabled, <code>false</code> if it
   *        was disabled
   */
  default void onUserEnabled (@Nonnull final IUser aUser, final boolean bEnabled)
  {}
}
