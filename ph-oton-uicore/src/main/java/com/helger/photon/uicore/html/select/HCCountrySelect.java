/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.IHasBooleanRepresentation;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.compare.AbstractCollationComparator;
import com.helger.commons.locale.country.ComparatorLocaleDisplayCountryInLocale;
import com.helger.commons.locale.country.CountryCache;
import com.helger.commons.name.IDisplayTextProvider;
import com.helger.html.request.IHCRequestField;
import com.helger.masterdata.locale.DeprecatedLocaleHandler;

public class HCCountrySelect extends HCExtSelect
{
  public static enum EWithDeprecated implements IHasBooleanRepresentation
  {
    TRUE,
    FALSE;

    public static final EWithDeprecated DEFAULT = FALSE;

    public boolean getAsBoolean ()
    {
      return this == TRUE;
    }
  }

  @Nonnull
  public static List <Locale> getAllCountries (@Nonnull final EWithDeprecated eWithDeprecated)
  {
    final boolean bWithDeprecated = eWithDeprecated.getAsBoolean ();
    final List <Locale> aLocales = new ArrayList <Locale> ();
    for (final Locale aCountry : CountryCache.getInstance ().getAllCountryLocales ())
      if (bWithDeprecated || !DeprecatedLocaleHandler.getDefaultInstance ().isDeprecatedLocaleWithFallback (aCountry))
        aLocales.add (aCountry);
    return aLocales;
  }

  public HCCountrySelect (@Nonnull final IHCRequestField aRF, @Nonnull final Locale aDisplayLocale)
  {
    this (aRF, aDisplayLocale, EWithDeprecated.DEFAULT);
  }

  public HCCountrySelect (@Nonnull final IHCRequestField aRF,
                          @Nonnull final Locale aDisplayLocale,
                          @Nonnull final EWithDeprecated eWithDeprecated)
  {
    this (aRF, aDisplayLocale, getAllCountries (eWithDeprecated));
  }

  public HCCountrySelect (@Nonnull final IHCRequestField aRF,
                          @Nonnull final Locale aDisplayLocale,
                          final boolean bAlwaysShowPleaseSelect)
  {
    this (aRF, aDisplayLocale, EWithDeprecated.DEFAULT, bAlwaysShowPleaseSelect);
  }

  public HCCountrySelect (@Nonnull final IHCRequestField aRF,
                          @Nonnull final Locale aDisplayLocale,
                          @Nonnull final EWithDeprecated eWithDeprecated,
                          final boolean bAlwaysShowPleaseSelect)
  {
    this (aRF, aDisplayLocale, getAllCountries (eWithDeprecated), null, bAlwaysShowPleaseSelect);
  }

  public HCCountrySelect (@Nonnull final IHCRequestField aRF,
                          @Nonnull final Locale aDisplayLocale,
                          @Nonnull final Collection <Locale> aLocales)
  {
    this (aRF, aDisplayLocale, aLocales, null);
  }

  public HCCountrySelect (@Nonnull final IHCRequestField aRF,
                          @Nonnull final Locale aDisplayLocale,
                          @Nonnull final Collection <Locale> aLocales,
                          @Nullable final IDisplayTextProvider <Locale> aDisplayTextProvider)
  {
    // Backwards compatibility
    this (aRF, aDisplayLocale, aLocales, aDisplayTextProvider, true);
  }

  public HCCountrySelect (@Nonnull final IHCRequestField aRF,
                          @Nonnull final Locale aDisplayLocale,
                          @Nonnull final Collection <Locale> aLocales,
                          @Nullable final IDisplayTextProvider <Locale> aDisplayTextProvider,
                          final boolean bAlwaysShowPleaseSelect)
  {
    super (aRF);

    Comparator <Locale> aComp;
    if (aDisplayTextProvider == null)
      aComp = new ComparatorLocaleDisplayCountryInLocale (aDisplayLocale, aDisplayLocale);
    else
      aComp = new AbstractCollationComparator <Locale> (aDisplayLocale)
      {
        @Override
        protected String asString (final Locale aObject)
        {
          return aDisplayTextProvider.getDisplayText (aObject, aDisplayLocale);
        }
      };
    for (final Locale aCountry : CollectionHelper.getSorted (aLocales, aComp))

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
