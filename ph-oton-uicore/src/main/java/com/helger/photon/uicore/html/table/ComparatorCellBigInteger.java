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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.locale.LocaleParser;

/**
 * This comparator is responsible for sorting cells by BigInteger
 *
 * @author Philip Helger
 */
public class ComparatorCellBigInteger extends AbstractComparatorCell <BigInteger>
{
  private static final BigInteger DEFAULT_VALUE = BigInteger.ZERO;
  private static final BigDecimal DEFAULT_VALUE_DECIMAL = new BigDecimal (DEFAULT_VALUE);

  public ComparatorCellBigInteger (@Nonnull final Locale aParseLocale)
  {
    this (aParseLocale, null, null);
  }

  public ComparatorCellBigInteger (@Nonnull final Locale aParseLocale,
                                   @Nullable final String sCommonPrefix,
                                   @Nullable final String sCommonSuffix)
  {
    super (aCell -> {
      try
      {
        return LocaleParser.parseBigDecimal (getCellText (aCell, sCommonPrefix, sCommonSuffix),
                                             aParseLocale,
                                             DEFAULT_VALUE_DECIMAL)
                           .toBigInteger ();
      }
      catch (final ArithmeticException ex)
      {
        // Not a BigInteger
        return DEFAULT_VALUE;
      }
    });
    ValueEnforcer.notNull (aParseLocale, "ParseLocale");
  }
}
