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
package com.helger.photon.core.menu;

import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.collection.commons.ICommonsMap;
import com.helger.collection.hierarchy.visit.IHierarchyVisitorCallback;
import com.helger.tree.withid.DefaultTreeItemWithID;

import jakarta.annotation.Nonnull;

/**
 * Base interface for {@link MenuItemDeterminatorCallback}.
 *
 * @author Philip Helger
 */
public interface IMenuItemDeterminatorCallback extends IHierarchyVisitorCallback <DefaultTreeItemWithID <String, IMenuObject>>
{
  /**
   * @return The menu tree on which this item determinator works. Never
   *         <code>null</code>.
   */
  @Nonnull
  IMenuTree getMenuTree ();

  /**
   * @return A map with all items to be displayed, where the key is the menu
   *         item ID and the value is the expansion state of the item (and never
   *         <code>null</code>).
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsMap <String, Boolean> getAllItemIDs ();
}
