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

import com.helger.photon.core.page.IPage;

/**
 * Base interface for a single menu item referencing an internal page.
 *
 * @author Philip Helger
 */
public interface IMenuItemPage extends IMenuItem
{
  /**
   * {@inheritDoc}
   */
  @NonNull
  IMenuItemPage setDisplayFilter (@Nullable IMenuObjectFilter aDisplayFilter);

  /**
   * @return The referenced page object.
   */
  @NonNull
  IPage getPage ();

  /*
   * Change return type
   */
  @NonNull
  IMenuItemPage setTarget (@Nullable String sTarget);
}
