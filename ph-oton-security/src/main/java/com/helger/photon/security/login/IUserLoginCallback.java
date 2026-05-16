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

import com.helger.annotation.Nonempty;
import com.helger.base.callback.ICallback;

/**
 * Callback interface when a user logs in.
 *
 * @author Philip Helger
 */
public interface IUserLoginCallback extends ICallback
{
  /**
   * Called when a user is successfully logged in.
   *
   * @param aInfo
   *        The login info of the user that just logged in. Never
   *        <code>null</code>.
   */
  default void onUserLogin (@NonNull final LoginInfo aInfo)
  {}

  /**
   * Called when a user failed to logged in.
   *
   * @param sUserID
   *        The ID of the user who tried to login. Never <code>null</code>.
   * @param eLoginResult
   *        The login result indicating the login error.
   */
  default void onUserLoginError (@NonNull @Nonempty final String sUserID, @NonNull final ELoginResult eLoginResult)
  {}

  /**
   * Called when a user has passed primary credential validation but must still provide a second
   * factor. The user is not yet considered logged in at this point.
   *
   * @param sUserID
   *        The ID of the user awaiting second-factor verification. Never <code>null</code>.
   * @since 10.2.3
   */
  default void onUserSecondFactorRequired (@NonNull @Nonempty final String sUserID)
  {}

  /**
   * Called when a submitted second-factor code was rejected.
   *
   * @param sUserID
   *        The ID of the user whose code was rejected. Never <code>null</code>.
   * @since 10.2.3
   */
  default void onUserSecondFactorFailed (@NonNull @Nonempty final String sUserID)
  {}
}
