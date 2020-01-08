/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.UnsupportedOperation;

/**
 * A special menu item that is just a link to an existing other menu item.
 * Basically this is used to change the URL of an existing menu item without
 * breaking links.
 *
 * @author Philip Helger
 * @since 8.2.2
 */
public interface IMenuItemRedirectToPage extends IMenuItem
{
  /**
   * @return The target page to which this item links. May not be
   *         <code>null</code>.
   */
  @Nonnull
  IMenuItemPage getTargetMenuItemPage ();

  @Nullable
  default String getTarget ()
  {
    return getTargetMenuItemPage ().getTarget ();
  }

  @UnsupportedOperation
  default IMenuItem setTarget (@Nullable final String sTarget)
  {
    throw new UnsupportedOperationException ("The target of a redirect cannot be changed");
  }

  @Nullable
  default String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return getTargetMenuItemPage ().getDisplayText (aContentLocale);
  }
}
