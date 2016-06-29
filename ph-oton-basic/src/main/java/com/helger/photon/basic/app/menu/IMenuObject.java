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
package com.helger.photon.basic.app.menu;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.collection.attr.IMutableAttributeContainerAny;
import com.helger.commons.id.IHasID;

/**
 * Base interface for menu items and menu separators.
 *
 * @author Philip Helger
 */
public interface IMenuObject extends IHasID <String>, IMutableAttributeContainerAny <String>
{
  @Nonnull
  EMenuObjectType getMenuObjectType ();

  /**
   * @return An optional filter that toggles visibility.
   */
  @Nullable
  IMenuObjectFilter getDisplayFilter ();

  /**
   * Set a new display filter for this menu object.
   *
   * @param aDisplayFilter
   *        The new display filter to set. Maybe <code>null</code> to indicate
   *        that no filter is required.
   * @return this
   */
  @Nonnull
  IMenuObject setDisplayFilter (@Nullable IMenuObjectFilter aDisplayFilter);

  /**
   * @return <code>true</code> if either no display filter is installed, or if
   *         the installed filter matches, <code>false</code> otherwise.
   */
  default boolean matchesDisplayFilter ()
  {
    final IMenuObjectFilter aDisplayFilter = getDisplayFilter ();
    return aDisplayFilter == null || aDisplayFilter.test (this);
  }
}
