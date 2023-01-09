/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.core.locale;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsLinkedHashSet;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsOrderedSet;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;

/**
 * This class manages the locales available in the application.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class LocaleManager implements ILocaleManager
{
  private final ICommonsOrderedSet <Locale> m_aLocales = new CommonsLinkedHashSet <> ();
  private Locale m_aDefaultLocale;

  public LocaleManager ()
  {}

  @Nonnull
  public EChange registerLocale (@Nonnull final Locale aLocale)
  {
    ValueEnforcer.notNull (aLocale, "Locale");
    if (!m_aLocales.add (aLocale))
      return EChange.UNCHANGED;

    if (m_aLocales.size () == 1)
    {
      // If it is the first locale, automatically make it the default locale
      // until further notice!
      setDefaultLocale (aLocale);
    }
    return EChange.CHANGED;
  }

  @Nonnull
  public EChange setDefaultLocale (@Nonnull final Locale aDefaultLocale)
  {
    ValueEnforcer.notNull (aDefaultLocale, "DefaultLocale");
    ValueEnforcer.isTrue (m_aLocales.contains (aDefaultLocale),
                          () -> "The supposed default locale " +
                                aDefaultLocale +
                                " is not a valid application locale! It needs to be registered before it can be set as a default.");

    if (aDefaultLocale.equals (m_aDefaultLocale))
      return EChange.UNCHANGED;
    m_aDefaultLocale = aDefaultLocale;
    return EChange.UNCHANGED;
  }

  @Nullable
  public Locale getDefaultLocale ()
  {
    return m_aDefaultLocale;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <Locale> getAllAvailableLocales ()
  {
    return m_aLocales.getCopyAsList ();
  }

  public boolean hasLocales ()
  {
    return m_aLocales.isNotEmpty ();
  }

  public boolean isSupportedLocale (@Nullable final Locale aLocale)
  {
    return m_aLocales.contains (aLocale);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Locales", m_aLocales).append ("DefaultLocale", m_aDefaultLocale).getToString ();
  }
}
