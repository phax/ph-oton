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
package com.helger.photon.uicore.datetime;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;

public enum EDateTimeFormatToken
{
  ABBR_WEEKDAY_NAME ("%a", "EE"),
  FULL_WEEKDAY_NAME ("%A", "EEEE"),
  ABBR_MONTH_NAME ("%b", "MMM"),
  FULL_MONTH_NAME ("%B", "MMMM"),
  // _CENTURY ("%C", null), // Java: none
  DAY_OF_MONTH_LZ ("%d", "dd"),
  DAY_OF_MONTH ("%e", "d"),
  HOUR23_LZ ("%H", "HH"),
  HOUR12_LZ ("%I", "hh"),
  DAY_OF_YEAR_LZ ("%j", "DDD"),
  HOUR23 ("%k", "H"),
  HOUR12 ("%l", "h"),
  MONTH ("%m", "M"), // HACK alert: mapping not present in JS but in some Java
                     // standard date time pattern
  MONTH_LZ ("%m", "MM"),
  MINUTE_LZ ("%M", "mm"),
  CHAR_NEWLINE ("%n", "\n"),
  AMPM_UPPER ("%p", "a"),
  AMPM_LOWER ("%P", "a"),
  // _SECONDS_SINCE_01011970 ("%s", null), // Java: none
  SECONDS_LZ ("%S", "ss"),
  CHAR_TAB ("%t", "\t"),
  WEEKNUM_LZ ("%U", "ww"), // synonyms: %V %W
  // _DAY_OF_WEEK_FROM_MONDAY ("%u", null), // Java: only text version
  // _DAY_OF_WEEK_FROM_SUNDAY ("%w", null), // Java: only text version
  YEAR_WITHOUT_CENTURY ("%y", "yy"),
  YEAR_WITH_CENTURY ("%Y", "yyyy"),
  CHAR_PERC ("%%", "%");

  private final String m_sJSCalendarToken;
  private final String m_sJavaToken;

  private EDateTimeFormatToken (@Nonnull @Nonempty final String sJSCalendarToken,
                                @Nonnull @Nonempty final String sJavaToken)
  {
    m_sJSCalendarToken = sJSCalendarToken;
    m_sJavaToken = sJavaToken;
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
}
