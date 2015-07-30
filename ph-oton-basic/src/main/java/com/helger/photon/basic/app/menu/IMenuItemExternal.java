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
package com.helger.photon.basic.app.menu;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.url.IHasSimpleURL;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.hchtml.HC_Target;

/**
 * Base interface for a single menu item referencing an arbitrary URL.
 *
 * @author Philip Helger
 */
public interface IMenuItemExternal extends IMenuItem
{
  /**
   * {@inheritDoc}
   */
  @Nonnull
  IMenuItemExternal setDisplayFilter (@Nullable IMenuObjectFilter aDisplayFilter);

  /**
   * @return The URL provider used internally. Never <code>null</code>.
   */
  @Nonnull
  IHasSimpleURL getURLProvider ();

  /**
   * @return The referenced external URL. Never <code>null</code>.
   */
  @Nonnull
  ISimpleURL getURL ();

  /*
   * Change return type
   */
  @Nonnull
  IMenuItemExternal setTarget (@Nullable HC_Target eTarget);

  /*
   * Change return type
   */
  @Nonnull
  IMenuItemExternal setTarget (@Nullable String sTarget);
}
