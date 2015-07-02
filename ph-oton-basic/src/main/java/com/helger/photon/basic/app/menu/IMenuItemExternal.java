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

import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.html.HC_Target;

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
   * @return The referenced external URL.
   */
  @Nonnull
  ISimpleURL getURL ();

  /**
   * @return The (HTML) target of the link
   */
  @Nullable
  String getTarget ();

  /**
   * Set the (HTML) target of the link.
   *
   * @param eTarget
   *        The target window. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  IMenuItemExternal setTarget (@Nullable HC_Target eTarget);

  /**
   * Set the (HTML) target of the link.
   *
   * @param sTarget
   *        The name of the target window. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  IMenuItemExternal setTarget (@Nullable String sTarget);
}
