/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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

import com.helger.html.request.IHCRequestField;
import com.helger.masterdata.tax.ETaxCategoryUN5305;

public class HCTaxCategoryUN5305Select extends HCExtSelect
{
  public HCTaxCategoryUN5305Select (@Nonnull final IHCRequestField aRF, @Nonnull final Locale aDisplayLocale)
  {
    super (aRF);

    // add empty item
    addOptionPleaseSelect (aDisplayLocale);

    // for all values
    for (final ETaxCategoryUN5305 eTaxCategory : ETaxCategoryUN5305.values ())
    {
      final String sDisplayText = eTaxCategory.getDisplayText (aDisplayLocale);
      addOption (eTaxCategory.getID (), sDisplayText);
    }
  }
}
