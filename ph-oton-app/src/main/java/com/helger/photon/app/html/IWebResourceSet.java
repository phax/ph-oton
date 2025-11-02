/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.app.html;

import java.util.Collection;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.state.EChange;
import com.helger.collection.commons.ICommonsIterable;
import com.helger.collection.commons.ICommonsOrderedSet;
import com.helger.html.resource.IHTMLResourceProvider;

/**
 * Base interface for CSS and JS resource sets.
 *
 * @author Philip Helger
 * @param <T>
 *        The contained data type.
 */
public interface IWebResourceSet <T extends IHTMLResourceProvider> extends ICommonsIterable <T>
{
  /**
   * Add an item
   *
   * @param aItem
   *        The item to add. May not be <code>null</code>.
   * @return {@link EChange}
   */
  @NonNull
  EChange addItem (@NonNull T aItem);

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
  @NonNull
  EChange addItem (int nIndex, @NonNull T aItem);

  /**
   * Add all items from another list.
   *
   * @param aItems
   *        The items to be added. May not be <code>null</code>.
   * @return {@link EChange}
   */
  @NonNull
  EChange addItems (@NonNull IWebResourceSet <? extends T> aItems);

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
  @NonNull
  EChange addItems (int nIndex, @NonNull IWebResourceSet <? extends T> aItems);

  /**
   * Unregister an existing item
   *
   * @param aItem
   *        The item to be removed. May not be <code>null</code>.
   * @return {@link EChange}
   */
  @NonNull
  EChange removeItem (@NonNull T aItem);

  /**
   * Remove all items.
   *
   * @return {@link EChange}
   */
  @NonNull
  EChange removeAll ();

  /**
   * @return A non-<code>null</code> set with all items.
   */
  @NonNull
  @ReturnsMutableCopy
  ICommonsOrderedSet <T> getAllItems ();

  /**
   * Add all items to the provided target container.
   *
   * @param aTarget
   *        The container to add the items to. May not be <code>null</code>.
   */
  void getAllItems (@NonNull Collection <? super T> aTarget);

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
}
