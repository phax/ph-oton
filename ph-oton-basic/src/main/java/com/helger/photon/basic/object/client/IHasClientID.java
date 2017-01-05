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

import javax.annotation.Nullable;

import com.helger.commons.equals.EqualsHelper;

/**
 * Base interface for objects that have a client ID.
 *
 * @author Philip Helger
 */
public interface IHasClientID
{
  /**
   * @return The client ID or <code>null</code>.
   */
  @Nullable
  String getClientID ();

  /**
   * Check if the passed client ID has the same ID as this object
   *
   * @param sClientID
   *        The client ID to check. May be <code>null</code>.
   * @return <code>true</code> if this object and the passed object have the
   *         same client ID
   */
  default boolean hasSameClientID (@Nullable final String sClientID)
  {
    return EqualsHelper.equals (getClientID (), sClientID);
  }
}
