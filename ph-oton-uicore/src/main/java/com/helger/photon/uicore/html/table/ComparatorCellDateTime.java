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

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.datetime.format.PDTFormatter;
import com.helger.datetime.format.PDTFromString;

/**
 * This comparator is responsible for sorting cells by date and/or time.
 *
 * @author Philip Helger
 */
public class ComparatorCellDateTime extends AbstractComparatorCell <ZonedDateTime>
{
  public ComparatorCellDateTime (@Nullable final Locale aParseLocale)
  {
    this (aParseLocale, null, null);
  }

  public ComparatorCellDateTime (@Nullable final Locale aParseLocale,
                                 @Nullable final String sCommonPrefix,
                                 @Nullable final String sCommonSuffix)
  {
    this (PDTFormatter.getDefaultFormatterDateTime (aParseLocale), sCommonPrefix, sCommonSuffix);
  }

  public ComparatorCellDateTime (@Nonnull final DateTimeFormatter aFormatter)
  {
    this (aFormatter, null, null);
  }

  public ComparatorCellDateTime (@Nonnull final DateTimeFormatter aFormatter,
                                 @Nullable final String sCommonPrefix,
                                 @Nullable final String sCommonSuffix)
  {
    super (aCell -> PDTFromString.getDateTimeFromString (getCellText (aCell, sCommonPrefix, sCommonSuffix),
                                                         aFormatter));
    ValueEnforcer.notNull (aFormatter, "Formatter");
  }
}
