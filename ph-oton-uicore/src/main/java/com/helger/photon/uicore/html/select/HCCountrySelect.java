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
package com.helger.photon.uicore.html.select;

import java.util.Comparator;
import java.util.Locale;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.helper.CollectionSort;
import com.helger.html.request.IHCRequestField;
import com.helger.masterdata.locale.DeprecatedLocaleHandler;
import com.helger.text.compare.ComparatorHelper;
import com.helger.text.display.IDisplayTextProvider;
import com.helger.text.locale.country.CountryCache;

/**
 * Select box for countries
 *
 * @author Philip Helger
 */
public class HCCountrySelect extends HCExtSelect
{
  public enum EWithDeprecated
  {
    TRUE,
    FALSE;

    public static final EWithDeprecated DEFAULT = FALSE;

    public boolean isWithDeprecated ()
    {
      return this == TRUE;
    }
  }

  @NonNull
  public static ICommonsList <Locale> getAllCountries (@NonNull final EWithDeprecated eWithDeprecated)
  {
    final boolean bWithDeprecated = eWithDeprecated.isWithDeprecated ();
    final ICommonsList <Locale> aLocales = new CommonsArrayList <> ();
    for (final Locale aCountry : CountryCache.getInstance ().getAllCountryLocales ())
      if (bWithDeprecated || !DeprecatedLocaleHandler.getDefaultInstance ().isDeprecatedLocaleWithFallback (aCountry))
        aLocales.add (aCountry);
    return aLocales;
  }

  public HCCountrySelect (@NonNull final IHCRequestField aRF, @NonNull final Locale aDisplayLocale)
  {
    this (aRF, aDisplayLocale, EWithDeprecated.DEFAULT);
  }

  public HCCountrySelect (@NonNull final IHCRequestField aRF,
                          @NonNull final Locale aDisplayLocale,
                          @NonNull final EWithDeprecated eWithDeprecated)
  {
    this (aRF, aDisplayLocale, getAllCountries (eWithDeprecated));
  }

  public HCCountrySelect (@NonNull final IHCRequestField aRF,
                          @NonNull final Locale aDisplayLocale,
                          final boolean bAlwaysShowPleaseSelect)
  {
    this (aRF, aDisplayLocale, EWithDeprecated.DEFAULT, bAlwaysShowPleaseSelect);
  }

  public HCCountrySelect (@NonNull final IHCRequestField aRF,
                          @NonNull final Locale aDisplayLocale,
                          @NonNull final EWithDeprecated eWithDeprecated,
                          final boolean bAlwaysShowPleaseSelect)
  {
    this (aRF, aDisplayLocale, getAllCountries (eWithDeprecated), null, bAlwaysShowPleaseSelect);
  }

  public HCCountrySelect (@NonNull final IHCRequestField aRF,
                          @NonNull final Locale aDisplayLocale,
                          @NonNull final Iterable <? extends Locale> aLocales)
  {
    this (aRF, aDisplayLocale, aLocales, null);
  }

  public HCCountrySelect (@NonNull final IHCRequestField aRF,
                          @NonNull final Locale aDisplayLocale,
                          @NonNull final Iterable <? extends Locale> aLocales,
                          @Nullable final IDisplayTextProvider <Locale> aDisplayTextProvider)
  {
    // Backwards compatibility
    this (aRF, aDisplayLocale, aLocales, aDisplayTextProvider, true);
  }

  public HCCountrySelect (@NonNull final IHCRequestField aRF,
                          @NonNull final Locale aDisplayLocale,
                          @NonNull final Iterable <? extends Locale> aLocales,
                          @Nullable final IDisplayTextProvider <Locale> aDisplayTextProvider,
                          final boolean bAlwaysShowPleaseSelect)
  {
    super (aRF);

    final Comparator <Locale> aComp;
    if (aDisplayTextProvider == null)
      aComp = ComparatorHelper.getComparatorCollating (aLocale -> aLocale.getDisplayCountry (aDisplayLocale),
                                                       aDisplayLocale);
    else
      aComp = aDisplayTextProvider.getComparatorCollating (aDisplayLocale, aDisplayLocale);

    for (final Locale aCountry : CollectionSort.getSorted (aLocales, aComp))
    {
      final String sDisplayCountry = aDisplayTextProvider != null ? aDisplayTextProvider.getDisplayText (aCountry,
                                                                                                         aDisplayLocale)
                                                                  : aCountry.getDisplayCountry (aDisplayLocale);
      addOption (aCountry.getCountry (), sDisplayCountry);
    }

    if (!hasSelectedOption () || bAlwaysShowPleaseSelect)
      addOptionPleaseSelect (aDisplayLocale);
  }
}
