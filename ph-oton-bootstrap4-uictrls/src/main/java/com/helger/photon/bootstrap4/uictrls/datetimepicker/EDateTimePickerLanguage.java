/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.uictrls.datetimepicker;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.locale.LocaleCache;
import com.helger.commons.string.StringHelper;

/**
 * All locales available in the Bootstrap DateTimePicker
 *
 * @author Philip Helger
 */
public enum EDateTimePickerLanguage
{
  AR ("ar", "ar"),
  AZ ("az", "az"),
  BG ("bg", "bg"),
  BN ("bn", "bn"),
  CA ("ca", "ca"),
  CS ("cs", "cs"),
  DA ("da", "da"),
  DE ("de", "de"),
  DE_AT ("de_AT", "de-AT"),
  EE ("ee", "ee"),
  EL ("el", "el"),
  ES ("es", "es"),
  FI ("fi", "fi"),
  FR ("fr", "fr"),
  /* Note: this is not a valid Java locale! */
  HE ("he", "he"),
  HR ("hr", "hr"),
  HU ("hu", "hu"),
  HY ("hy", "hy"),
  /* Note: this is not a valid Java locale! */
  ID ("id", "id"),
  IS ("is", "is"),
  IT ("it", "it"),
  JA ("ja", "ja"),
  KA ("ka", "ka"),
  KO ("ko", "ko"),
  KR ("kr", "kr"),
  LT ("lt", "lt"),
  LV ("lv", "lv"),
  MS ("ms", "ms"),
  NB ("nb", "nb"),
  NL ("nl", "nl"),
  NO ("no", "no"),
  PL ("pl", "pl"),
  PT ("pt", "pt"),
  PT_BR ("pt_BR", "pt-BR"),
  RO ("ro", "ro"),
  /* Note: this is not a valid Java locale! */
  RS ("rs", "rs"),
  /* Note: this is not a valid Java locale! */
  RS_LATIN ("rs_latin", "rs-latin"),
  RU ("ru", "ru"),
  SK ("sk", "sk"),
  SL ("sl", "sl"),
  SV ("sv", "sv"),
  SW ("sw", "sw"),
  TH ("th", "th"),
  TR ("tr", "tr"),
  /* Note: this is not a valid Java locale! */
  UA ("ua", "ua"),
  UK ("uk", "uk"),
  ZH_CN ("zh_CN", "zh-CN"),
  ZH_TW ("zh_TW", "zh-TW");

  public static final String PREDEFINED_LANGUAGE = "en";

  private final Locale m_aLocale;
  private final String m_sLanguageID;

  private EDateTimePickerLanguage (@Nonnull final String sLocale, @Nonnull @Nonempty final String sLanguageID)
  {
    m_aLocale = LocaleCache.getInstance ().getLocale (sLocale);
    if (m_aLocale == null)
      throw new IllegalStateException ("Failed to resolve '" + sLocale + "'");
    m_sLanguageID = sLanguageID;
  }

  @Nonnull
  public Locale getLocale ()
  {
    return m_aLocale;
  }

  /**
   * @return The ID to be used by the DateTimePicker to resolve the include
   *         file.
   */
  @Nonnull
  @Nonempty
  public String getLanguageID ()
  {
    return m_sLanguageID;
  }

  @Nullable
  public static EDateTimePickerLanguage getFromLocaleOrNull (@Nullable final Locale aLocale)
  {
    return getFromLocaleOrDefault (aLocale, null);
  }

  @Nullable
  public static EDateTimePickerLanguage getFromLocaleOrDefault (@Nullable final Locale aLocale,
                                                                @Nullable final EDateTimePickerLanguage eDefault)
  {
    if (aLocale != null)
    {
      // Shortcut for English, because no locale files are present as English is
      // the default
      if (PREDEFINED_LANGUAGE.equals (aLocale.getLanguage ()))
        return eDefault;

      // Check for direct match
      for (final EDateTimePickerLanguage e : values ())
        if (aLocale.equals (e.m_aLocale))
          return e;

      if (StringHelper.hasText (aLocale.getCountry ()))
      {
        // Check locale with language and country only
        final Locale aRealLocale = LocaleCache.getInstance ().getLocale (aLocale.getLanguage (), aLocale.getCountry ());
        for (final EDateTimePickerLanguage e : values ())
          if (aRealLocale.equals (e.m_aLocale))
            return e;
      }

      if (StringHelper.hasText (aLocale.getLanguage ()))
      {
        // Check locale with language only
        final Locale aRealLocale = LocaleCache.getInstance ().getLocale (aLocale.getLanguage ());
        for (final EDateTimePickerLanguage e : values ())
          if (aRealLocale.equals (e.m_aLocale))
            return e;
      }
    }
    return eDefault;
  }
}
