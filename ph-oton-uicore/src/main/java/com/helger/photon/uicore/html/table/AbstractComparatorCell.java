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

import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.compare.PartComparatorComparable;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.html.tabular.IHCCell;

/**
 * This comparator is responsible for sorting cells by BigDecimal
 *
 * @author Philip Helger
 * @param <PARTTYPE>
 *        The part type that is extracted from the cell and compared
 */
public abstract class AbstractComparatorCell <PARTTYPE extends Comparable <? super PARTTYPE>>
                                             extends PartComparatorComparable <IHCCell <?>, PARTTYPE>
{

  public AbstractComparatorCell (@Nonnull final Function <IHCCell <?>, PARTTYPE> aExtractor)
  {
    super (aExtractor);
  }

  @Nonnull
  protected static String getCellText (@Nullable final IHCCell <?> aCell,
                                       @Nullable final String sCommonPrefix,
                                       @Nullable final String sCommonSuffix)
  {
    if (aCell == null)
      return "";

    String sText = aCell.getPlainText ();

    // strip common prefix and suffix
    if (StringHelper.hasText (sCommonPrefix))
      sText = StringHelper.trimStart (sText, sCommonPrefix);
    if (StringHelper.hasText (sCommonSuffix))
      sText = StringHelper.trimEnd (sText, sCommonSuffix);

    return sText;
  }
}
