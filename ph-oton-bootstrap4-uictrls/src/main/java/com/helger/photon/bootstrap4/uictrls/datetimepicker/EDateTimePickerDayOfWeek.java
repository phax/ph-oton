/**
 * Copyright (C) 2018-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.uictrls.datetimepicker;

import java.time.DayOfWeek;
import java.util.Calendar;

import javax.annotation.Nonnegative;
import javax.annotation.Nullable;

/**
 * Defines the day of week constants used by moments.js
 *
 * @author Philip Helger
 */
public enum EDateTimePickerDayOfWeek
{
  MONDAY (1),
  TUESDAY (2),
  WEDNESDAY (3),
  THURSDAY (4),
  FRIDAY (5),
  SATURDAY (6),
  SUNDAY (7);

  private final int m_nJSValue;

  private EDateTimePickerDayOfWeek (@Nonnegative final int nJSValue)
  {
    m_nJSValue = nJSValue;
  }

  @Nonnegative
  public int getJSValue ()
  {
    return m_nJSValue;
  }

  @Nullable
  public static EDateTimePickerDayOfWeek getFromJodaValueOrNull (final int nJDKValue)
  {
    if (nJDKValue == DayOfWeek.MONDAY.getValue ())
      return MONDAY;
    if (nJDKValue == DayOfWeek.TUESDAY.getValue ())
      return TUESDAY;
    if (nJDKValue == DayOfWeek.WEDNESDAY.getValue ())
      return WEDNESDAY;
    if (nJDKValue == DayOfWeek.THURSDAY.getValue ())
      return THURSDAY;
    if (nJDKValue == DayOfWeek.FRIDAY.getValue ())
      return FRIDAY;
    if (nJDKValue == DayOfWeek.SATURDAY.getValue ())
      return SATURDAY;
    if (nJDKValue == DayOfWeek.SUNDAY.getValue ())
      return SUNDAY;
    return null;
  }

  @Nullable
  public static EDateTimePickerDayOfWeek getFromJavaValueOrNull (final int nCalendarValue)
  {
    switch (nCalendarValue)
    {
      case Calendar.MONDAY:
        return MONDAY;
      case Calendar.TUESDAY:
        return TUESDAY;
      case Calendar.WEDNESDAY:
        return WEDNESDAY;
      case Calendar.THURSDAY:
        return THURSDAY;
      case Calendar.FRIDAY:
        return FRIDAY;
      case Calendar.SATURDAY:
        return SATURDAY;
      case Calendar.SUNDAY:
        return SUNDAY;
    }
    return null;
  }
}
