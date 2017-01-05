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
package com.helger.photon.uicore.html.select;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.collection.CollectionHelper;
import com.helger.html.request.IHCRequestField;
import com.helger.masterdata.currency.ECurrency;

public class HCCurrencySelect extends HCExtSelect
{
  public HCCurrencySelect (@Nonnull final IHCRequestField aRF, @Nonnull final Locale aDisplayLocale)
  {
    this (CollectionHelper.newList (ECurrency.values ()), aRF, aDisplayLocale);
  }

  public HCCurrencySelect (@Nonnull final Iterable <ECurrency> aCurrencies,
                           @Nonnull final IHCRequestField aRF,
                           @Nonnull final Locale aDisplayLocale)
  {
    super (aRF);

    // Add empty element
    addOptionPleaseSelect (aDisplayLocale);

    // For all supported currencies
    for (final ECurrency eCurrency : aCurrencies)
    {
      final String sDisplayText = eCurrency.getCurrencySymbol () +
                                  " (" +
                                  eCurrency.getDisplayText (aDisplayLocale) +
                                  ')';
      addOption (eCurrency.getID (), sDisplayText);
    }
  }
}
