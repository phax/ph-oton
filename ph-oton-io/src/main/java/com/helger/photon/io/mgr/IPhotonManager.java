/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.io.mgr;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.id.IHasID;

/**
 * Base interface for a manager with common data types.
 *
 * @author Philip Helger
 * @param <INTERFACETYPE>
 *        The data type to be handled.
 * @since 8.2.4
 */
public interface IPhotonManager <INTERFACETYPE extends IHasID <String>>
{
  /**
   * @param <T>
   *        Response type.
   * @return An empty collection of the suitable implementation type.
   */
  @Nonnull
  @ReturnsMutableCopy
  <T> ICommonsList <T> getNone ();

  /**
   * @return A non-<code>null</code> but maybe empty list of all contained
   *         objects.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <INTERFACETYPE> getAll ();

  /**
   * @param sID
   *        The object ID to be checked
   * @return <code>true</code> if a object with this ID is contained,
   *         <code>false</code> if not
   */
  boolean containsWithID (@Nullable String sID);

  /**
   * Check if all IDs are contained
   *
   * @param aIDs
   *        IDs to check
   * @return <code>true</code> if all IDs are contained
   */
  boolean containsAllIDs (@Nullable Iterable <String> aIDs);
}
