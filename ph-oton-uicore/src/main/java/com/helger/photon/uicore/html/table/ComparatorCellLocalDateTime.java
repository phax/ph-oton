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

import org.joda.time.LocalDateTime;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.compare.AbstractPartComparatorComparable;
import com.helger.commons.string.StringHelper;
import com.helger.datetime.format.PDTFromString;
import com.helger.datetime.format.SerializableDateTimeFormatter;
import com.helger.datetime.format.SerializableDateTimeFormatter.EFormatStyle;
import com.helger.html.hc.html.tabular.IHCCell;

/**
 * This comparator is responsible for sorting cells by date and/or time.
 *
 * @author Philip Helger
 */
public class ComparatorCellLocalDateTime extends AbstractPartComparatorComparable <IHCCell <?>, LocalDateTime>
{
  private final SerializableDateTimeFormatter m_aFormatter;
  private final String m_sCommonPrefix;
  private final String m_sCommonSuffix;

  public ComparatorCellLocalDateTime (@Nullable final Locale aParseLocale)
  {
    this (aParseLocale, null, null);
  }

  public ComparatorCellLocalDateTime (@Nullable final Locale aParseLocale,
                                      @Nullable final String sCommonPrefix,
                                      @Nullable final String sCommonSuffix)
  {
    this (SerializableDateTimeFormatter.create (EFormatStyle.DEFAULT, EFormatStyle.DEFAULT, aParseLocale),
          sCommonPrefix,
          sCommonSuffix);
  }

  public ComparatorCellLocalDateTime (@Nonnull final SerializableDateTimeFormatter aFormatter)
  {
    this (aFormatter, null, null);
  }

  public ComparatorCellLocalDateTime (@Nonnull final SerializableDateTimeFormatter aFormatter,
                                      @Nullable final String sCommonPrefix,
                                      @Nullable final String sCommonSuffix)
  {
    ValueEnforcer.notNull (aFormatter, "Formatter");
    m_aFormatter = aFormatter;
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

  @Override
  protected LocalDateTime getPart (@Nonnull final IHCCell <?> aCell)
  {
    final String sText = getCellText (aCell);
    return PDTFromString.getLocalDateTimeFromString (sText, m_aFormatter.getFormatter ());
  }
}
