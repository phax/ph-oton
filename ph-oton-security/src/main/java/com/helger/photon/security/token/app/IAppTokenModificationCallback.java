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
package com.helger.photon.security.token.app;

import javax.annotation.Nonnull;

import com.helger.commons.callback.ICallback;

/**
 * Callback interface when an app token is created, modified, deleted.
 *
 * @author Philip Helger
 */
public interface IAppTokenModificationCallback extends ICallback
{
  /**
   * Called after an app token was created.
   *
   * @param aAppToken
   *        The created app token. Never <code>null</code>.
   */
  void onAppTokenCreated (@Nonnull IAppToken aAppToken);

  /**
   * Called after an app token was edited fully.
   *
   * @param aAppToken
   *        The modified app token. Never <code>null</code>.
   */
  void onAppTokenUpdated (@Nonnull IAppToken aAppToken);

  /**
   * Called after a app token was deleted.
   *
   * @param aAppToken
   *        The deleted app token. Never <code>null</code>.
   */
  void onAppTokenDeleted (@Nonnull IAppToken aAppToken);

  /**
   * Called after a app token was enabled or disabled.
   *
   * @param aAppToken
   *        The enabled or disabled app token. Never <code>null</code>.
   * @param bEnabled
   *        <code>true</code> if the app token was enabled, <code>false</code>
   *        if it was disabled
   */
  void onAppTokenEnabled (@Nonnull IAppToken aAppToken, boolean bEnabled);
}
