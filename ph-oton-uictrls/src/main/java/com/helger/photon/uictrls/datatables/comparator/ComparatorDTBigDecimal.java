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
package com.helger.photon.uictrls.datatables.comparator;

import java.math.BigDecimal;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.format.IFormatter;
import com.helger.commons.locale.LocaleParser;
import com.helger.commons.string.ToStringGenerator;

/**
 * This comparator is responsible for sorting cells by BigDecimal
 *
 * @author Philip Helger
 */
public class ComparatorDTBigDecimal extends AbstractComparatorDT
{
  protected static final BigDecimal DEFAULT_VALUE = BigDecimal.ZERO;

  private final Locale m_aParseLocale;

  public ComparatorDTBigDecimal (@Nonnull final Locale aParseLocale)
  {
    this (null, aParseLocale);
  }

  public ComparatorDTBigDecimal (@Nullable final IFormatter aFormatter, @Nonnull final Locale aParseLocale)
  {
    super (aFormatter);
    m_aParseLocale = ValueEnforcer.notNull (aParseLocale, "ParseLocale");
  }

  @Nonnull
  public final Locale getParseLocale ()
  {
    return m_aParseLocale;
  }

  @OverrideOnDemand
  @Nonnull
  protected BigDecimal getAsBigDecimal (@Nonnull final String sCellText)
  {
    // Ensure that columns without text are sorted consistently compared to the
    // ones with non-numeric content
    if (sCellText.isEmpty ())
      return DEFAULT_VALUE;
    return LocaleParser.parseBigDecimal (sCellText, m_aParseLocale, DEFAULT_VALUE);
  }

  @Override
  protected int internalCompare (@Nonnull final String sText1, @Nonnull final String sText2)
  {
    final BigDecimal aBD1 = getAsBigDecimal (sText1);
    final BigDecimal aBD2 = getAsBigDecimal (sText2);
    return aBD1.compareTo (aBD2);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("parseLocale", m_aParseLocale).toString ();
  }
}
