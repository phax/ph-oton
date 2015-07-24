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

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.compare.AbstractLongComparator;
import com.helger.commons.locale.LocaleParser;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.base.IHCCell;

public class ComparatorCellLong extends AbstractLongComparator <IHCCell <?>>
{
  private final Locale m_aLocale;
  private final String m_sCommonPrefix;
  private final String m_sCommonSuffix;

  public ComparatorCellLong (@Nonnull final Locale aParseLocale)
  {
    this (aParseLocale, null, null);
  }

  public ComparatorCellLong (@Nonnull final Locale aParseLocale,
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

  @Override
  protected long getAsLong (@Nullable final IHCCell <?> aCell)
  {
    final String sText = getCellText (aCell);

    // Ensure that columns without text are sorted consistently compared to the
    // ones with non-numeric content
    if (StringHelper.hasNoText (sText))
      return -1;
    return LocaleParser.parseLong (sText, m_aLocale, 0);
  }
}
