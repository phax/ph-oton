/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.app.resource;

import java.util.Collection;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsIterable;
import com.helger.commons.collection.impl.ICommonsOrderedSet;
import com.helger.commons.state.EChange;

/**
 * Base interface for CSS and JS resource sets.
 *
 * @author Philip Helger
 * @param <T>
 *        The contained data type.
 */
public interface IWebResourceSet <T> extends ICommonsIterable <T>
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
   * Add an item at the specified index
   *
   * @param nIndex
   *        The index to be used. If the value is &lt; 0 the value is ignored
   *        and item is appended.
   * @param aItem
   *        The item to add. May not be <code>null</code>.
   * @return {@link EChange}
   */
  @Nonnull
  EChange addItem (int nIndex, @Nonnull T aItem);

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
   * Add all items from another list at the specified index.
   *
   * @param nIndex
   *        The index to be used. If the value is &lt; 0 the value is ignored
   *        and item is appended.
   * @param aItems
   *        The items to be added. May not be <code>null</code>.
   * @return {@link EChange}
   */
  @Nonnull
  EChange addItems (int nIndex, @Nonnull IWebResourceSet <? extends T> aItems);

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
   * @return A non-<code>null</code> set with all items.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsOrderedSet <T> getAllItems ();

  /**
   * Add all items to the provided target container.
   *
   * @param aTarget
   *        The container to add the items to. May not be <code>null</code>.
   */
  void getAllItems (@Nonnull Collection <? super T> aTarget);

  /**
   * Call this method to mark the resource set as "collected for further
   * processing". This state may be used to determine additions afterwards which
   * will potentially not be gathered.
   */
  void markAsCollected ();

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
