/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.html.table;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.compare.DoubleComparator;
import com.helger.commons.locale.LocaleParser;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.html.tabular.IHCCell;

public class ComparatorCellDouble extends DoubleComparator <IHCCell <?>>
{
  public ComparatorCellDouble (@Nonnull final Locale aParseLocale)
  {
    this (aParseLocale, null, null);
  }

  public ComparatorCellDouble (@Nonnull final Locale aParseLocale,
                               @Nullable final String sCommonPrefix,
                               @Nullable final String sCommonSuffix)
  {
    super (aCell -> {
      final String sText = AbstractComparatorCell.getCellText (aCell, sCommonPrefix, sCommonSuffix);

      /*
       * Ensure that columns without text are sorted consistently compared to
       * the ones with non-numeric content
       */
      if (StringHelper.hasNoText (sText))
        return Double.MIN_VALUE;
      return LocaleParser.parseDouble (sText, aParseLocale, 0);
    });
    ValueEnforcer.notNull (aParseLocale, "ParseLocale");
  }
}
