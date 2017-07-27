/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.functional.IPredicate;
import com.helger.commons.text.display.IHasDisplayText;

/**
 * Special menu item filter to determine the visibility of a menu item. If
 * consists of the filtering and provides an optional descriptive text.
 *
 * @author Philip Helger
 */
@FunctionalInterface
public interface IMenuObjectFilter extends IPredicate <IMenuObject>, IHasDisplayText
{
  @Nullable
  default String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return null;
  }

  @Nonnull
  default IMenuObjectFilter and (@Nonnull final IMenuObjectFilter aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");
    return x -> test (x) && aOther.test (x);
  }

  @Nonnull
  default IMenuObjectFilter or (@Nonnull final IMenuObjectFilter aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");
    return x -> test (x) || aOther.test (x);
  }
}
