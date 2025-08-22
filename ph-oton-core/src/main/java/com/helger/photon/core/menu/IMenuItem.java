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

import com.helger.base.string.StringHelper;
import com.helger.text.display.IHasDisplayText;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Base interface for a single menu item.
 *
 * @author Philip Helger
 */
public interface IMenuItem extends IMenuObject, IHasDisplayText
{
  /**
   * @return The (HTML) target of the link. May be <code>null</code>.
   */
  @Nullable
  String getTarget ();

  /**
   * @return <code>true</code> if an explicit target is defined,
   *         <code>false</code> otherwise.
   */
  default boolean hasTarget ()
  {
    return StringHelper.isNotEmpty (getTarget ());
  }

  /**
   * Set the (HTML) target of the link.
   *
   * @param sTarget
   *        The name of the target window. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  IMenuItem setTarget (@Nullable String sTarget);
}
