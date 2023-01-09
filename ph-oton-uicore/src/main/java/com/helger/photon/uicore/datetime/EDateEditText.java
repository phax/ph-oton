/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Translatable;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;

/**
 * Contains multilingual texts for the JS calendar. Only used by a tool for
 * creating the JS files.
 *
 * @author Philip Helger
 */
@Translatable
public enum EDateEditText implements IHasDisplayText
{
  CALENDAR_DAY_LONG_SUNDAY ("Sonntag", "Sunday"),
  CALENDAR_DAY_LONG_MONDAY ("Montag", "Monday"),
  CALENDAR_DAY_LONG_TUESDAY ("Dienstag", "Tuesday"),
  CALENDAR_DAY_LONG_WEDNESDAY ("Mittwoch", "Wednesday"),
  CALENDAR_DAY_LONG_THURSDAY ("Donnerstag", "Thursday"),
  CALENDAR_DAY_LONG_FRIDAY ("Freitag", "Friday"),
  CALENDAR_DAY_LONG_SATURDAY ("Samstag", "Saturday"),
  CALENDAR_DAY_SHORT_SUNDAY ("So", "Sun"),
  CALENDAR_DAY_SHORT_MONDAY ("Mo", "Mon"),
  CALENDAR_DAY_SHORT_TUESDAY ("Di", "Tue"),
  CALENDAR_DAY_SHORT_WEDNESDAY ("Mi", "Wed"),
  CALENDAR_DAY_SHORT_THURSDAY ("Do", "Thu"),
  CALENDAR_DAY_SHORT_FRIDAY ("Fr", "Fri"),
  CALENDAR_DAY_SHORT_SATURDAY ("Sa", "Sat"),
  CALENDAR_FIRST_DAY_OF_WEEK ("1", "0"),
  CALENDAR_MONTH_LONG_JANUARY ("Januar", "January"),
  CALENDAR_MONTH_LONG_FEBRUARY ("Februar", "February"),
  CALENDAR_MONTH_LONG_MARCH ("März", "March"),
  CALENDAR_MONTH_LONG_APRIL ("April", "April"),
  CALENDAR_MONTH_LONG_MAY ("Mai", "May"),
  CALENDAR_MONTH_LONG_JUNE ("Juni", "June"),
  CALENDAR_MONTH_LONG_JULY ("Juli", "July"),
  CALENDAR_MONTH_LONG_AUGUST ("August", "August"),
  CALENDAR_MONTH_LONG_SEPTEMBER ("September", "September"),
  CALENDAR_MONTH_LONG_OCTOBER ("Oktober", "October"),
  CALENDAR_MONTH_LONG_NOVEMBER ("November", "November"),
  CALENDAR_MONTH_LONG_DECEMBER ("Dezember", "December"),
  CALENDAR_MONTH_SHORT_JANUARY ("Jan", "Jan"),
  CALENDAR_MONTH_SHORT_FEBRUARY ("Feb", "Feb"),
  CALENDAR_MONTH_SHORT_MARCH ("Mär", "Mar"),
  CALENDAR_MONTH_SHORT_APRIL ("Apr", "Apr"),
  CALENDAR_MONTH_SHORT_MAY ("Mai", "May"),
  CALENDAR_MONTH_SHORT_JUNE ("Jun", "Jun"),
  CALENDAR_MONTH_SHORT_JULY ("Jul", "Jul"),
  CALENDAR_MONTH_SHORT_AUGUST ("Aug", "Aug"),
  CALENDAR_MONTH_SHORT_SEPTEMBER ("Sep", "Sep"),
  CALENDAR_MONTH_SHORT_OCTOBER ("Okt", "Oct"),
  CALENDAR_MONTH_SHORT_NOVEMBER ("Nov", "Nov"),
  CALENDAR_MONTH_SHORT_DECEMBER ("Dez", "Dec"),
  CALENDAR_INFO ("Über dieses Kalendarmodul", "About the calendar"),
  CALENDAR_ABOUT ("Datum auswählen:\n" +
                  "- Benutzen Sie die \u00ab, \u00bb Buttons um das Jahr zu wählen\n" +
                  "- Benutzen Sie die \u2039, \u203a Buttons um den Monat zu wählen\n" +
                  "- Für eine Schnellauswahl halten Sie die Maustaste über diesen Buttons fest.",
                  "Date selection:\n" +
                                                                                                  "- Use the \u00ab, \u00bb buttons to select year\n" +
                                                                                                  "- Use the \u2039, \u203a buttons to select month\n" +
                                                                                                  "- Hold mouse button on any of the above buttons for faster selection."),
  CALENDAR_ABOUT_TIME ("Zeit auswählen:\n" +
                       "- Klicken Sie auf die Teile der Uhrzeit, um diese zu erhöhen\n" +
                       "- oder klicken Sie mit festgehaltener Shift-Taste um diese zu verringern\n" +
                       "- oder klicken und festhalten für Schnellauswahl.",
                       "Time selection:\n" +
                                                                            "- Click on any of the time parts to increase it\n" +
                                                                            "- or Shift-click to decrease it\n" +
                                                                            "- or click and drag for faster selection."),
  CALENDAR_PREV_YEAR ("Voriges Jahr (Festhalten für Liste)", "Prev. year (hold for menu)"),
  CALENDAR_PREV_MONTH ("Voriger Monat (Festhalten für Liste)", "Prev. month (hold for menu)"),
  CALENDAR_GO_TODAY ("Heute auswählen", "Go Today"),
  CALENDAR_NEXT_MONTH ("Nächst. Monat (Festhalten für Liste)", "Next month (hold for menu)"),
  CALENDAR_NEXT_YEAR ("Nächst. Jahr (Festhalten für Liste)", "Next year (hold for menu)"),
  CALENDAR_SEL_DATE ("Datum auswählen", "Select date"),
  CALENDAR_DRAG_TO_MOVE ("Zum Bewegen festhalten", "Drag to move"),
  CALENDAR_PART_TODAY (" (Heute)", " (today)"),
  CALENDAR_DAY_FIRST ("Woche beginnt mit %s", "Display %s first"),
  CALENDAR_WEEKEND ("0,6", "0,6"),
  CALENDAR_CLOSE ("Schließen", "Close"),
  CALENDAR_TODAY ("Heute", "Today"),
  CALENDAR_TIME_PART ("(Shift-)Klick oder Festhalten und Ziehen um den Wert zu ändern", "(Shift-)Click or drag to change value"),
  CALENDAR_DEF_DATE_FORMAT ("%d.%m.%Y", "%Y-%m-%d"),
  CALENDAR_TT_DATE_FORMAT ("%A, %e. %B", "%a, %b %e"),
  CALENDAR_DEF_TIME_FORMAT ("%H:%M:%S", "%H:%M:%S %P"),
  CALENDAR_WK ("KW", "wk"),
  CALENDAR_TIME ("Zeit:", "Time:");

  private final IMultilingualText m_aTP;

  EDateEditText (@Nonnull final String sDE, @Nonnull final String sEN)
  {
    m_aTP = TextHelper.create_DE_EN (sDE, sEN);
  }

  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
  }
}
