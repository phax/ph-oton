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
package com.helger.photon.basic.security.user.callback;

import javax.annotation.Nonnull;

import com.helger.commons.callback.ICallback;
import com.helger.photon.basic.security.user.IUser;

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
  void onUserCreated (@Nonnull IUser aUser, boolean bPredefinedUser);

  /**
   * Called after a user was edited fully.
   *
   * @param aUser
   *        The modified user. Never <code>null</code>.
   */
  void onUserUpdated (@Nonnull IUser aUser);

  /**
   * Called after a user's last failed login was updated.
   *
   * @param aUser
   *        The modified user. Never <code>null</code>.
   */
  void onUserLastFailedLoginUpdated (@Nonnull IUser aUser);

  /**
   * Called after a user's password was changed.
   *
   * @param aUser
   *        The modified user. Never <code>null</code>.
   */
  void onUserPasswordChanged (@Nonnull IUser aUser);

  /**
   * Called after a user was deleted.
   *
   * @param aUser
   *        The deleted user. Never <code>null</code>.
   */
  void onUserDeleted (@Nonnull IUser aUser);

  /**
   * Called after a user was undeleted.
   *
   * @param aUser
   *        The undeleted user. Never <code>null</code>.
   */
  void onUserUndeleted (@Nonnull IUser aUser);

  /**
   * Called after a user was enabled or disabled.
   *
   * @param aUser
   *        The enabled or disabled user. Never <code>null</code>.
   * @param bEnabled
   *        <code>true</code> if the user was enabled, <code>false</code> if it
   *        was disabled
   */
  void onUserEnabled (@Nonnull IUser aUser, boolean bEnabled);
}
