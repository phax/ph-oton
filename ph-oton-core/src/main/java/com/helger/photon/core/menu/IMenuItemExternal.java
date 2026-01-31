/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.url.ISimpleURL;
import com.helger.url.provider.IHasSimpleURL;

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
  @NonNull
  IMenuItemExternal setDisplayFilter (@Nullable IMenuObjectFilter aDisplayFilter);

  /**
   * @return The URL provider used internally. Never <code>null</code>.
   */
  @NonNull
  IHasSimpleURL getURLProvider ();

  /**
   * @return The referenced external URL. Never <code>null</code>.
   */
  @NonNull
  default ISimpleURL getURL ()
  {
    return getURLProvider ().getSimpleURL ();
  }

  /*
   * Change return type
   */
  @NonNull
  IMenuItemExternal setTarget (@Nullable String sTarget);
}
