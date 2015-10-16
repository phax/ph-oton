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
package com.helger.photon.basic.object.accarea;

import javax.annotation.Nullable;

import com.helger.photon.basic.object.client.IClientObject;

/**
 * Base interface for an object that is uniquely assigned to an accounting area.
 *
 * @author Philip Helger
 */
public interface IAccountingAreaObject extends IClientObject, IHasAccountingArea
{
  /**
   * @return The accounting area ID to which the object is assigned to. May be
   *         <code>null</code>.
   * @see #getAccountingArea()
   */
  @Nullable
  String getAccountingAreaID ();

  /**
   * @return The accounting area matching the given ID. May be <code>null</code>
   *         .
   */
  @Nullable
  IAccountingArea getAccountingArea ();
}
