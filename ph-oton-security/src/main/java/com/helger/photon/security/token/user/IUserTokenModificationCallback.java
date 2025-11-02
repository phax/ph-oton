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
package com.helger.photon.security.token.user;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.Nonempty;
import com.helger.base.callback.ICallback;
import com.helger.photon.security.token.accesstoken.AccessToken;

/**
 * Callback interface when a user token is created, modified, deleted.
 *
 * @author Philip Helger
 */
public interface IUserTokenModificationCallback extends ICallback
{
  /**
   * Called after a user token was created.
   *
   * @param aUserToken
   *        The created user token. Never <code>null</code>.
   */
  default void onUserTokenCreated (@NonNull final IUserToken aUserToken)
  {}

  /**
   * Called after a user token was edited fully.
   *
   * @param sUserTokenID
   *        The modified user token ID. Never <code>null</code>.
   */
  default void onUserTokenUpdated (@NonNull @Nonempty final String sUserTokenID)
  {}

  /**
   * Called after a user token was deleted.
   *
   * @param sUserTokenID
   *        The deleted user token ID. Never <code>null</code>.
   */
  default void onUserTokenDeleted (@NonNull @Nonempty final String sUserTokenID)
  {}

  /**
   * Called after a new access token was created for a user token.
   *
   * @param sUserTokenID
   *        The changed user token ID. Never <code>null</code>.
   * @param aAccessToken
   *        The created new access token. Never <code>null</code>.
   */
  default void onUserTokenCreateAccessToken (@NonNull @Nonempty final String sUserTokenID,
                                             @NonNull final AccessToken aAccessToken)
  {}

  /**
   * Called after the current access token of a user token got revoked and no
   * new access token was created. In case a new access token is directly
   * created only {@link #onUserTokenCreateAccessToken(String, AccessToken)} is
   * called.
   *
   * @param sUserTokenID
   *        The changed user token ID. Never <code>null</code>.
   */
  default void onUserTokenRevokeAccessToken (@NonNull @Nonempty final String sUserTokenID)
  {}
}
