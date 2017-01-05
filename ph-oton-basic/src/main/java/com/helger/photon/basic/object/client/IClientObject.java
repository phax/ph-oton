/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.object.client;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.photon.basic.object.IObject;

/**
 * Base interface for an object that is uniquely assigned to a client.
 *
 * @author Philip Helger
 */
public interface IClientObject extends IObject, IHasClient
{
  /**
   * @return The client to which the object is assigned to. May not be
   *         <code>null</code>.
   */
  @Nonnull
  IClient getClient ();

  /**
   * @return The client ID to which the object is assigned to. May neither be
   *         <code>null</code> nor empty.
   * @see #getClient()
   */
  @Nonnull
  @Nonempty
  default String getClientID ()
  {
    return getClient ().getID ();
  }
}
