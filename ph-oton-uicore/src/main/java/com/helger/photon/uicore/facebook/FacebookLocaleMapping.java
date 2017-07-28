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
package com.helger.photon.uicore.facebook;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.cache.Cache;
import com.helger.commons.collection.impl.CommonsHashSet;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.locale.LocaleCache;
import com.helger.scope.IScope;
import com.helger.web.scope.singleton.AbstractGlobalWebSingleton;

public final class FacebookLocaleMapping extends AbstractGlobalWebSingleton
{
  private static final Locale FALLBACK = Locale.US;
  private static final String [] FB_LOCALES = { "af_ZA",
                                                "ar_AR",
                                                "az_AZ",
                                                "be_BY",
                                                "bg_BG",
                                                "bn_IN",
                                                "bs_BA",
                                                "ca_ES",
                                                "cs_CZ",
                                                "cy_GB",
                                                "da_DK",
                                                "de_DE",
                                                "el_GR",
                                                "en_GB",
                                                "en_PI",
                                                "en_UD",
                                                "en_US",
                                                "eo_EO",
                                                "es_ES",
                                                "es_LA",
                                                "et_EE",
                                                "eu_ES",
                                                "fa_IR",
                                                "fb_LT",
                                                "fi_FI",
                                                "fo_FO",
                                                "fr_CA",
                                                "fr_FR",
                                                "fy_NL",
                                                "ga_IE",
                                                "gl_ES",
                                                "he_IL",
                                                "hi_IN",
                                                "hr_HR",
                                                "hu_HU",
                                                "hy_AM",
                                                "id_ID",
                                                "is_IS",
                                                "it_IT",
                                                "ja_JP",
                                                "ka_GE",
                                                "km_KH",
                                                "ko_KR",
                                                "ku_TR",
                                                "la_VA",
                                                "lt_LT",
                                                "lv_LV",
                                                "mk_MK",
                                                "ml_IN",
                                                "ms_MY",
                                                "nb_NO",
                                                "ne_NP",
                                                "nl_NL",
                                                "nn_NO",
                                                "pa_IN",
                                                "pl_PL",
                                                "ps_AF",
                                                "pt_BR",
                                                "pt_PT",
                                                "ro_RO",
                                                "ru_RU",
                                                "sk_SK",
                                                "sl_SI",
                                                "sq_AL",
                                                "sr_RS",
                                                "sv_SE",
                                                "sw_KE",
                                                "ta_IN",
                                                "te_IN",
                                                "th_TH",
                                                "tl_PH",
                                                "tr_TR",
                                                "uk_UA",
                                                "vi_VN",
                                                "zh_CN",
                                                "zh_HK",
                                                "zh_TW" };

  private static final Logger s_aLogger = LoggerFactory.getLogger (FacebookLocaleMapping.class);

  private final ICommonsSet <Locale> m_aFBLocales = new CommonsHashSet <> ();

  private final Cache <Locale, Locale> m_aCache = new Cache <> (aLocale -> {
    return aLocale == null ? FALLBACK : _getFBCompatibleLocale (aLocale);
  }, -1, FacebookLocaleMapping.class.getName ());

  @Nonnull
  private Locale _getFBCompatibleLocale (@Nonnull final Locale aLocale)
  {
    if (m_aFBLocales.contains (aLocale))
      return aLocale;
    for (final Locale aCompatibleLocale : m_aFBLocales)
      if (aCompatibleLocale.getLanguage ().equals (aLocale.getLanguage ()))
      {
        s_aLogger.warn ("Using compatible locale '" +
                        aCompatibleLocale +
                        "' for facebook integration as requested locale '" +
                        aLocale +
                        "' is not supported!");
        return aCompatibleLocale;
      }

    s_aLogger.warn ("Using fallback locale '" +
                    FALLBACK +
                    "' for facebook integration as requested locale '" +
                    aLocale +
                    "' is not supported!");
    return FALLBACK;
  }

  @UsedViaReflection
  @Deprecated
  public FacebookLocaleMapping ()
  {
    for (final String sLocale : FB_LOCALES)
      m_aFBLocales.add (LocaleCache.getInstance ().getLocale (sLocale));
  }

  @Nonnull
  public static FacebookLocaleMapping getInstance ()
  {
    return getGlobalSingleton (FacebookLocaleMapping.class);
  }

  @Override
  protected void onDestroy (@Nonnull final IScope aScopeInDestruction)
  {
    clearCache ();
  }

  @Nullable
  public Locale getFBLocale (@Nonnull final Locale aLocale)
  {
    return m_aCache.getFromCache (aLocale);
  }

  public void clearCache ()
  {
    if (m_aCache.clearCache ().isChanged ())
      s_aLogger.info ("Cache was cleared: " + getClass ().getName ());
  }
}
