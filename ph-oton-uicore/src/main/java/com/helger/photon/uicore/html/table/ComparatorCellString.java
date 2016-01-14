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

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.compare.AbstractCollatingComparator;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.html.tabular.IHCCell;

public class ComparatorCellString extends AbstractCollatingComparator <IHCCell <?>>
{
  private final String m_sCommonPrefix;
  private final String m_sCommonSuffix;

  public ComparatorCellString (@Nullable final Locale aParseLocale)
  {
    this (aParseLocale, null, null);
  }

  public ComparatorCellString (@Nullable final Locale aParseLocale,
                               @Nullable final String sCommonPrefix,
                               @Nullable final String sCommonSuffix)
  {
    super (aParseLocale);
    m_sCommonPrefix = StringHelper.hasText (sCommonPrefix) ? sCommonPrefix : null;
    m_sCommonSuffix = StringHelper.hasText (sCommonSuffix) ? sCommonSuffix : null;
  }

  @OverrideOnDemand
  protected String getCellText (@Nullable final IHCCell <?> aCell)
  {
    if (aCell == null)
      return "";

    String sText = aCell.getPlainText ();

    // strip common prefix and suffix
    if (m_sCommonPrefix != null)
      sText = StringHelper.trimStart (sText, m_sCommonPrefix);
    if (m_sCommonSuffix != null)
      sText = StringHelper.trimEnd (sText, m_sCommonSuffix);

    return sText;
  }

  @Override
  @Nullable
  protected String getPart (@Nonnull final IHCCell <?> aCell)
  {
    return getCellText (aCell);
  }
}
