/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.datetime;

import static java.time.temporal.ChronoField.ALIGNED_WEEK_OF_YEAR;
import static java.time.temporal.ChronoField.AMPM_OF_DAY;
import static java.time.temporal.ChronoField.DAY_OF_YEAR;
import static java.time.temporal.ChronoField.HOUR_OF_AMPM;
import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;
import static java.time.temporal.ChronoField.YEAR;

import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import java.util.function.Consumer;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;

public enum EDateTimeFormatToken
{
  ABBR_WEEKDAY_NAME ("%a", "EE", b -> b.appendPattern ("EE")),
  FULL_WEEKDAY_NAME ("%A", "EEEE", b -> b.appendPattern ("EEEE")),
  ABBR_MONTH_NAME ("%b", "MMM", b -> b.appendPattern ("MMM")),
  FULL_MONTH_NAME ("%B", "MMMM", b -> b.appendPattern ("MMMM")),
  // _CENTURY ("%C", null), // Java: none
  DAY_OF_MONTH_LZ ("%d", "dd", b -> b.appendValue (ChronoField.DAY_OF_MONTH, 2)),
  DAY_OF_MONTH ("%e", "d", b -> b.appendValue (ChronoField.DAY_OF_MONTH, 1)),
  HOUR23_LZ ("%H", "HH", b -> b.appendValue (HOUR_OF_DAY, 2)),
  HOUR12_LZ ("%I", "hh", b -> b.appendValue (HOUR_OF_AMPM, 2)),
  DAY_OF_YEAR_LZ ("%j", "DDD", b -> b.appendValue (DAY_OF_YEAR, 3)),
  HOUR23 ("%k", "H", b -> b.appendValue (HOUR_OF_DAY, 1)),
  HOUR12 ("%l", "h", b -> b.appendValue (HOUR_OF_AMPM, 1)),
  MONTH ("%m", "M", b -> b.appendValue (MONTH_OF_YEAR, 1)),
  // HACK alert: mapping not present in JS but in some Java
  // standard date time pattern
  MONTH_LZ ("%m", "MM", b -> b.appendValue (MONTH_OF_YEAR, 2)),
  MINUTE_LZ ("%M", "mm", b -> b.appendValue (MINUTE_OF_HOUR, 2)),
  CHAR_NEWLINE ("%n", "\n", b -> b.appendLiteral ('\n')),
  AMPM_UPPER ("%p", "a", b -> b.appendValue (AMPM_OF_DAY)),
  AMPM_LOWER ("%P", "a", b -> b.appendValue (AMPM_OF_DAY)),
  // _SECONDS_SINCE_01011970 ("%s", null), // Java: none
  SECONDS_LZ ("%S", "ss", b -> b.appendValue (SECOND_OF_MINUTE, 2)),
  CHAR_TAB ("%t", "\t", b -> b.appendLiteral ('\t')),
  WEEKNUM_LZ ("%U", "ww", b -> b.appendValue (ALIGNED_WEEK_OF_YEAR, 2)),
  // synonyms: %V %W
  // _DAY_OF_WEEK_FROM_MONDAY ("%u", null), // Java: only text version
  // _DAY_OF_WEEK_FROM_SUNDAY ("%w", null), // Java: only text version
  YEAR_WITHOUT_CENTURY ("%y", "yy", b -> b.appendValue (YEAR, 2)),
  YEAR_WITH_CENTURY ("%Y", "uuuu", b -> b.appendValue (YEAR, 4, 10, SignStyle.EXCEEDS_PAD)),
  CHAR_PERC ("%%", "%", b -> b.appendLiteral ('%'));

  private final String m_sJSCalendarToken;
  private final String m_sJavaToken;
  private Consumer <DateTimeFormatterBuilder> m_aDTFBC;

  private EDateTimeFormatToken (@Nonnull @Nonempty final String sJSCalendarToken,
                                @Nonnull @Nonempty final String sJavaToken,
                                @Nonnull final Consumer <DateTimeFormatterBuilder> aDTFBC)
  {
    m_sJSCalendarToken = sJSCalendarToken;
    m_sJavaToken = sJavaToken;
    m_aDTFBC = aDTFBC;
  }

  @Nonnull
  public String getJSCalendarToken ()
  {
    return m_sJSCalendarToken;
  }

  @Nonnull
  public String getJavaToken ()
  {
    return m_sJavaToken;
  }

  public void addToFormatterBuilder (@Nonnull final DateTimeFormatterBuilder aDTFB)
  {
    m_aDTFBC.accept (aDTFB);
  }
}
