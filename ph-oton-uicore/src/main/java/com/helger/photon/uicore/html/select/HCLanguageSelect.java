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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.compare.IComparator;
import com.helger.commons.text.display.IDisplayTextProvider;
import com.helger.html.request.IHCRequestField;
import com.helger.photon.core.locale.GlobalLocaleManager;

public class HCLanguageSelect extends HCExtSelect
{
  public HCLanguageSelect (@Nonnull final IHCRequestField aRF,
                           @Nonnull final Locale aDisplayLocale,
                           @Nonnull final Iterable <? extends Locale> aLocales,
                           @Nullable final IDisplayTextProvider <Locale> aDisplayTextProvider,
                           final boolean bAlwaysShowPleaseSelect)
  {
    super (aRF);

    final Comparator <Locale> aComp;
    if (aDisplayTextProvider == null)
      aComp = IComparator.getComparatorCollating (x -> x.getDisplayLanguage (aDisplayLocale), aDisplayLocale);
    else
      aComp = aDisplayTextProvider.getComparatorCollating (aDisplayLocale, aDisplayLocale);

    for (final Locale aLocale : CollectionHelper.getSorted (aLocales, aComp))
    {
      final String sDisplayLanguage = aDisplayTextProvider != null ? aDisplayTextProvider.getDisplayText (aLocale,
                                                                                                          aDisplayLocale)
                                                                   : aLocale.getDisplayLanguage (aDisplayLocale);
      addOption (aLocale.getLanguage (), sDisplayLanguage);
    }

    if (!hasSelectedOption () || bAlwaysShowPleaseSelect)
      addOptionPleaseSelect (aDisplayLocale);
  }

  @Nonnull
  public static HCLanguageSelect createForApplicationLocales (@Nonnull final IHCRequestField aRF,
                                                              @Nonnull final Locale aDisplayLocale,
                                                              final boolean bAlwaysShowPleaseSelect)
  {
    return new HCLanguageSelect (aRF,
                                 aDisplayLocale,
                                 GlobalLocaleManager.getInstance ().getAllAvailableLocales (),
                                 null,
                                 bAlwaysShowPleaseSelect);
  }
}
