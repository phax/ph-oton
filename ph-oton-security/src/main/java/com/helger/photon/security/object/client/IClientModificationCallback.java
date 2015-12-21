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
package com.helger.photon.security.object.client;

import javax.annotation.Nonnull;

import com.helger.commons.callback.ICallback;
import com.helger.photon.basic.object.client.IClient;

/**
 * Callback interface when a client is created, modified or deleted.
 *
 * @author Philip Helger
 */
public interface IClientModificationCallback extends ICallback
{
  /**
   * Called after a client was created.
   *
   * @param aClient
   *        The created client. Never <code>null</code>.
   */
  default void onClientCreated (@Nonnull final IClient aClient)
  {}

  /**
   * Called after a client was edited fully.
   *
   * @param aClient
   *        The modified client. Never <code>null</code>.
   */
  default void onClientUpdated (@Nonnull final IClient aClient)
  {}

  /**
   * Called after a client was deleted.
   *
   * @param aClient
   *        The deleted client. Never <code>null</code>.
   */
  default void onClientDeleted (@Nonnull final IClient aClient)
  {}
}
