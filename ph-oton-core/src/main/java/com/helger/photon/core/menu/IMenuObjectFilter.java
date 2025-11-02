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

import java.util.Locale;
import java.util.function.Predicate;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.text.display.IHasDisplayText;

/**
 * Special menu item filter to determine the visibility of a menu item. If
 * consists of the filtering and provides an optional descriptive text.
 *
 * @author Philip Helger
 */
@FunctionalInterface
public interface IMenuObjectFilter extends Predicate <IMenuObject>, IHasDisplayText
{
  @Nullable
  default String getDisplayText (@NonNull final Locale aContentLocale)
  {
    return null;
  }

  @NonNull
  default IMenuObjectFilter and (@NonNull final IMenuObjectFilter aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");
    final IMenuObjectFilter aThis = this;
    return new IMenuObjectFilter ()
    {
      public boolean test (final IMenuObject x)
      {
        return aThis.test (x) && aOther.test (x);
      }

      @Nullable
      @Override
      public String getDisplayText (@NonNull final Locale aContentLocale)
      {
        return StringHelper.getConcatenatedOnDemand (aThis.getDisplayText (aContentLocale), " + ", aOther.getDisplayText (aContentLocale));
      }
    };
  }

  @NonNull
  default IMenuObjectFilter or (@NonNull final IMenuObjectFilter aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");
    final IMenuObjectFilter aThis = this;
    return new IMenuObjectFilter ()
    {
      public boolean test (final IMenuObject x)
      {
        return aThis.test (x) || aOther.test (x);
      }

      @Nullable
      @Override
      public String getDisplayText (@NonNull final Locale aContentLocale)
      {
        return StringHelper.getNotNull (aThis.getDisplayText (aContentLocale), aOther.getDisplayText (aContentLocale));
      }
    };
  }
}
