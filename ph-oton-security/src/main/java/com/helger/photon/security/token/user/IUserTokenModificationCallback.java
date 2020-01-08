/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;

import com.helger.commons.callback.ICallback;
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
  void onUserTokenCreated (@Nonnull IUserToken aUserToken);

  /**
   * Called after a user token was edited fully.
   *
   * @param aUserToken
   *        The modified user token. Never <code>null</code>.
   */
  void onUserTokenUpdated (@Nonnull IUserToken aUserToken);

  /**
   * Called after a user token was deleted.
   *
   * @param aUserToken
   *        The deleted user token. Never <code>null</code>.
   */
  void onUserTokenDeleted (@Nonnull IUserToken aUserToken);

  /**
   * Called after a new access token was created for a user token.
   *
   * @param aUserToken
   *        The changed user token. Never <code>null</code>.
   * @param aAccessToken
   *        The created new access token. Never <code>null</code>.
   */
  void onUserTokenCreateAccessToken (@Nonnull IUserToken aUserToken, @Nonnull AccessToken aAccessToken);

  /**
   * Called after the current access token of a user token got revoked and no
   * new access token was created. In case a new access token is directly
   * created only {@link #onUserTokenCreateAccessToken(IUserToken, AccessToken)}
   * is called.
   *
   * @param aUserToken
   *        The changed user token. Never <code>null</code>.
   */
  void onUserTokenRevokeAccessToken (@Nonnull IUserToken aUserToken);
}
