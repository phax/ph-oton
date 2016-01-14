/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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

import com.helger.photon.basic.object.client.IHasClient;

/**
 * Base interface for objects that have an accounting area.
 *
 * @author Philip Helger
 */
public interface IHasAccountingArea extends IHasClient, IHasAccountingAreaID
{
  /**
   * @return The accounting area or <code>null</code>.
   */
  @Nullable
  IAccountingArea getAccountingArea ();

  /**
   * Check if the passed object has the same client ID and the same accounting
   * area ID as this object
   *
   * @param aAccountingArea
   *        The object to check. May be <code>null</code>.
   * @return <code>true</code> if this object and the passed object have the
   *         same client ID <b>and</b> the same accounting area ID
   */
  boolean hasSameClientAndAccountingAreaID (@Nullable IAccountingArea aAccountingArea);

  /**
   * Check if the passed object has the same client ID and the same accounting
   * area ID as this object
   *
   * @param aAccountingAreaObject
   *        The object to check. May be <code>null</code>.
   * @return <code>true</code> if this object and the passed object have the
   *         same client ID <b>and</b> the same accounting area ID
   */
  boolean hasSameClientAndAccountingAreaID (@Nullable IAccountingAreaObject aAccountingAreaObject);
}
