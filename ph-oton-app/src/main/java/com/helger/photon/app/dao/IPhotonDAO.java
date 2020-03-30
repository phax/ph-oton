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
package com.helger.photon.app.dao;

import java.io.Serializable;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.id.IHasID;

public interface IPhotonDAO <INTERFACETYPE extends IHasID <String> & Serializable>
{
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

  /**
   * @return A non-<code>null</code> but maybe empty list of all contained
   *         objects.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <INTERFACETYPE> getAll ();

  @Nonnull
  @ReturnsMutableCopy
  <T> ICommonsList <T> getNone ();

  /**
   * @param aFilter
   *        The filter to be applied. May not be <code>null</code>.
   * @return A non-<code>null</code> but maybe empty list of all contained
   *         objects.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <INTERFACETYPE> getAll (@Nullable Predicate <? super INTERFACETYPE> aFilter);

  <RETTYPE> ICommonsList <RETTYPE> getAllMapped (@Nullable Predicate <? super INTERFACETYPE> aFilter,
                                                 @Nonnull Function <? super INTERFACETYPE, ? extends RETTYPE> aMapper);

  @Nonnegative
  int getCount (@Nullable Predicate <? super INTERFACETYPE> aFilter);

  @Nullable
  INTERFACETYPE findFirst (@Nullable Predicate <? super INTERFACETYPE> aFilter);

  boolean containsAny (@Nullable Predicate <? super INTERFACETYPE> aFilter);
}
