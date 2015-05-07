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

import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.annotations.Nonempty;
import com.helger.html.request.IHCRequestField;
import com.helger.masterdata.person.ESalutation;

public class HCSalutationSelect extends HCExtSelect
{
  public HCSalutationSelect (@Nonnull final IHCRequestField aRF, @Nonnull final Locale aDisplayLocale)
  {
    this (aRF, aDisplayLocale, true);
  }

  public HCSalutationSelect (@Nonnull final IHCRequestField aRF,
                             @Nonnull final Locale aDisplayLocale,
                             final boolean bAddOptionPleaseSelect)
  {
    this (aRF, aDisplayLocale, bAddOptionPleaseSelect, ESalutation.values ());
  }

  public HCSalutationSelect (@Nonnull final IHCRequestField aRF,
                             @Nonnull final Locale aDisplayLocale,
                             final boolean bAddOptionPleaseSelect,
                             @Nonnull @Nonempty final ESalutation [] aSalutations)
  {
    super (aRF);

    // add empty item
    if (bAddOptionPleaseSelect)
      addOptionPleaseSelect (aDisplayLocale);

    // for all salutations
    for (final ESalutation eSalutation : aSalutations)
    {
      final String sDisplayText = eSalutation.getDisplayText (aDisplayLocale);
      addOption (eSalutation.getID (), sDisplayText);
    }
  }
}
