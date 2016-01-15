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

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.datetime.format.PDTFormatter;
import com.helger.datetime.format.PDTFromString;

public class ComparatorDTTime extends AbstractComparatorDT <LocalTime>
{
  public ComparatorDTTime (@Nullable final Locale aParseLocale)
  {
    this (null, aParseLocale);
  }

  public ComparatorDTTime (@Nullable final Function <? super String, String> aFormatter,
                           @Nullable final Locale aParseLocale)
  {
    this (aFormatter, PDTFormatter.getDefaultFormatterTime (aParseLocale));
  }

  public ComparatorDTTime (@Nonnull final DateTimeFormatter aDTFormatter)
  {
    this (null, aDTFormatter);
  }

  public ComparatorDTTime (@Nullable final Function <? super String, String> aFormatter,
                           @Nonnull final DateTimeFormatter aDTFormatter)
  {
    super (aFormatter, sCellText -> PDTFromString.getLocalTimeFromString (sCellText, aDTFormatter));
    ValueEnforcer.notNull (aDTFormatter, "DTFormatter");
  }
}
