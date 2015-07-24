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
package com.helger.photon.uicore.html.table;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.compare.AbstractPartComparatorComparable;
import com.helger.commons.locale.LocaleParser;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.api.IHCCell;

/**
 * This comparator is responsible for sorting cells by BigInteger
 *
 * @author Philip Helger
 */
public class ComparatorCellBigInteger extends AbstractPartComparatorComparable <IHCCell <?>, BigInteger>
{
  private static final BigInteger DEFAULT_VALUE = BigInteger.ZERO;
  private static final BigDecimal DEFAULT_VALUE_DECIMAL = new BigDecimal (DEFAULT_VALUE);

  private final Locale m_aLocale;
  private final String m_sCommonPrefix;
  private final String m_sCommonSuffix;

  public ComparatorCellBigInteger (@Nonnull final Locale aParseLocale)
  {
    this (aParseLocale, null, null);
  }

  public ComparatorCellBigInteger (@Nonnull final Locale aParseLocale,
                                   @Nullable final String sCommonPrefix,
                                   @Nullable final String sCommonSuffix)
  {
    ValueEnforcer.notNull (aParseLocale, "ParseLocale");
    m_aLocale = aParseLocale;
    m_sCommonPrefix = sCommonPrefix;
    m_sCommonSuffix = sCommonSuffix;
  }

  @OverrideOnDemand
  protected String getCellText (@Nullable final IHCCell <?> aCell)
  {
    if (aCell == null)
      return "";

    String sText = aCell.getPlainText ();

    // strip common prefix and suffix
    if (StringHelper.hasText (m_sCommonPrefix))
      sText = StringHelper.trimStart (sText, m_sCommonPrefix);
    if (StringHelper.hasText (m_sCommonSuffix))
      sText = StringHelper.trimEnd (sText, m_sCommonSuffix);

    return sText;
  }

  @Nonnull
  protected BigInteger getAsBigInteger (@Nonnull final String sCellText)
  {
    try
    {
      return LocaleParser.parseBigDecimal (sCellText, m_aLocale, DEFAULT_VALUE_DECIMAL).toBigIntegerExact ();
    }
    catch (final ArithmeticException ex)
    {
      // Not a BigInteger
      return DEFAULT_VALUE;
    }
  }

  @Override
  protected BigInteger getPart (@Nonnull final IHCCell <?> aCell)
  {
    final String sText = getCellText (aCell);
    return getAsBigInteger (sText);
  }
}
