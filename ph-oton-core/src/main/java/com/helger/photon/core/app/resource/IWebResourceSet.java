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
package com.helger.photon.core.app.resource;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.annotation.Nonnull;

import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.state.EChange;

/**
 * Base interface for CSS and JS resource sets.
 * 
 * @author Philip Helger
 * @param <T>
 *        The contained data type.
 */
public interface IWebResourceSet <T> extends Serializable, Iterable <T>
{
  /**
   * Add an item
   *
   * @param aItem
   *        The item to add. May not be <code>null</code>.
   * @return {@link EChange}
   */
  @Nonnull
  EChange addItem (@Nonnull T aItem);

  /**
   * Add all items from another list.
   *
   * @param aItems
   *        The items to be added. May not be <code>null</code>.
   * @return {@link EChange}
   */
  @Nonnull
  EChange addItems (@Nonnull IWebResourceSet <? extends T> aItems);

  /**
   * Unregister an existing item
   *
   * @param aItem
   *        The item to be removed. May not be <code>null</code>.
   * @return {@link EChange}
   */
  @Nonnull
  EChange removeItem (@Nonnull T aItem);

  /**
   * Remove all items.
   *
   * @return {@link EChange}
   */
  @Nonnull
  EChange removeAll ();

  /**
   * @return A non-<code>null</code> set with all items. Order is ensured using
   *         LinkedHashSet.
   */
  @Nonnull
  @ReturnsMutableCopy
  Set <T> getAllItems ();

  /**
   * Add all items to the provided target container.
   *
   * @param aTarget
   *        The container to add the items to. May not be <code>null</code>.
   */
  void getAllItems (@Nonnull Collection <? super T> aTarget);

  /**
   * @return <code>true</code> if no item is contained
   */
  boolean isEmpty ();

  /**
   * @return <code>true</code> if at least a single item is contained
   */
  boolean isNotEmpty ();

  /**
   * @return The number of contained items. Always &ge; 0.
   */
  int getCount ();
}
