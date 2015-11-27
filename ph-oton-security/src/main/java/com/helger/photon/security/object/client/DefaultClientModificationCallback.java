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

import com.helger.photon.basic.object.client.IClient;

/**
 * An empty implementation of {@link IClientModificationCallback}. Use this as
 * the base class for implementation so in case the interface is modified you do
 * not need to modify your code.
 *
 * @author Philip Helger
 */
public class DefaultClientModificationCallback implements IClientModificationCallback
{
  public void onClientCreated (@Nonnull final IClient aClient)
  {}

  public void onClientUpdated (@Nonnull final IClient aClient)
  {}

  public void onClientDeleted (@Nonnull final IClient aClient)
  {}
}
