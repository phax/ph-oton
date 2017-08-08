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
package com.helger.photon.basic.object.tenant;

import javax.annotation.Nullable;

import com.helger.commons.equals.EqualsHelper;

/**
 * Base interface for objects that have a client.
 *
 * @author Philip Helger
 */
public interface IHasTenant extends IHasTenantID
{
  /**
   * @return The client or <code>null</code>.
   */
  @Nullable
  ITenant getTenant ();

  @Nullable
  default String getTenantID ()
  {
    final ITenant aTenant = getTenant ();
    return aTenant == null ? null : aTenant.getID ();
  }

  /**
   * Check if the passed object has the same client ID as this object
   *
   * @param aObj
   *        The object to check. May be <code>null</code>.
   * @return <code>true</code> if this object and the passed object (if not
   *         <code>null</code>) have the same client ID
   */
  default boolean hasSameTenantID (@Nullable final ITenantObject aObj)
  {
    return aObj != null && hasSameTenantID (aObj.getTenantID ());
  }

  /**
   * Check if the passed client has the same ID as this object
   *
   * @param aClient
   *        The client to check. May be <code>null</code>.
   * @return <code>true</code> if this object and the passed object have the
   *         same client.
   */
  default boolean hasSameTenant (@Nullable final ITenant aClient)
  {
    return EqualsHelper.equals (getTenant (), aClient);
  }
}