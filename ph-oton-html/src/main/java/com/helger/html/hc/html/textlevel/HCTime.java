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
package com.helger.html.hc.html.textlevel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.CGlobal;
import com.helger.commons.datetime.DateTimeFormatterCache;
import com.helger.commons.math.MathHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.typeconvert.TypeConverter;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.annotation.SinceHTML5;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCElementWithChildren;
import com.helger.xml.microdom.IMicroElement;

@SinceHTML5
public class HCTime extends AbstractHCElementWithChildren <HCTime>
{
  public static final int LENGTH_YEAR = 4;
  public static final int LENGTH_MONTH = 2;
  public static final int LENGTH_DAY = 2;
  public static final int LENGTH_WEEKINYEAR = 2;
  public static final int LENGTH_HOUR = 2;
  public static final int LENGTH_MIN = 2;
  public static final int LENGTH_SEC = 2;

  private String m_sDatetime;

  public HCTime ()
  {
    super (EHTMLElement.TIME);
  }

  @Nullable
  public final String getDatetime ()
  {
    return m_sDatetime;
  }

  @Nonnull
  public final HCTime setAsMonth (@Nonnegative final int nYear, @Nonnegative final int nMonth)
  {
    m_sDatetime = StringHelper.getLeadingZero (nYear, LENGTH_YEAR) +
                  "-" +
                  StringHelper.getLeadingZero (nMonth, LENGTH_MONTH);
    return this;
  }

  @Nonnull
  public final HCTime setAsDate (@Nonnegative final int nYear,
                                 @Nonnegative final int nMonth,
                                 @Nonnegative final int nDay)
  {
    m_sDatetime = StringHelper.getLeadingZero (nYear, LENGTH_YEAR) +
                  "-" +
                  StringHelper.getLeadingZero (nMonth, LENGTH_MONTH) +
                  "-" +
                  StringHelper.getLeadingZero (nDay, LENGTH_DAY);
    return this;
  }

  @Nonnull
  public final HCTime setAsDate (@Nonnull final LocalDate aDate)
  {
    m_sDatetime = DateTimeFormatterCache.getDateTimeFormatterStrict ("uuuu-MM-dd").format (aDate);
    return this;
  }

  @Nonnull
  public final HCTime setAsDate (@Nonnull final Date aDate)
  {
    return setAsDate (TypeConverter.convert (aDate, LocalDate.class));
  }

  @Nonnull
  public final HCTime setAsYearlessDate (@Nonnegative final int nMonth, @Nonnegative final int nDay)
  {
    m_sDatetime = StringHelper.getLeadingZero (nMonth, LENGTH_MONTH) +
                  "-" +
                  StringHelper.getLeadingZero (nDay, LENGTH_DAY);
    return this;
  }

  @Nonnull
  public final HCTime setAsTime (@Nonnegative final int nHour, @Nonnegative final int nMinute)
  {
    m_sDatetime = StringHelper.getLeadingZero (nHour, LENGTH_HOUR) +
                  ":" +
                  StringHelper.getLeadingZero (nMinute, LENGTH_MIN);
    return this;
  }

  @Nonnull
  public final HCTime setAsTime (@Nonnegative final int nHour,
                                 @Nonnegative final int nMinute,
                                 @Nonnegative final int nSecond)
  {
    m_sDatetime = StringHelper.getLeadingZero (nHour, LENGTH_HOUR) +
                  ":" +
                  StringHelper.getLeadingZero (nMinute, LENGTH_MIN) +
                  ":" +
                  StringHelper.getLeadingZero (nSecond, LENGTH_SEC);
    return this;
  }

  @Nonnull
  public final HCTime setAsTime (@Nonnegative final int nHour,
                                 @Nonnegative final int nMinute,
                                 @Nonnegative final int nSecond,
                                 @Nonnegative final int nMilliSeconds)
  {
    m_sDatetime = StringHelper.getLeadingZero (nHour, LENGTH_HOUR) +
                  ":" +
                  StringHelper.getLeadingZero (nMinute, LENGTH_MIN) +
                  ":" +
                  StringHelper.getLeadingZero (nSecond, LENGTH_SEC) +
                  "." +
                  nMilliSeconds;
    return this;
  }

  @Nonnull
  public final HCTime setAsTime (@Nonnull final LocalTime aTime)
  {
    m_sDatetime = DateTimeFormatterCache.getDateTimeFormatterStrict ("HH:mm:ss").format (aTime);
    return this;
  }

  @Nonnull
  public final HCTime setAsTime (@Nonnull final Date aDate)
  {
    return setAsTime (TypeConverter.convert (aDate, LocalTime.class));
  }

  @Nonnull
  public final HCTime setAsDateAndTime (@Nonnull final LocalDateTime aDateTime)
  {
    m_sDatetime = DateTimeFormatterCache.getDateTimeFormatterStrict ("uuuu-MM-dd'T'HH:mm:ss.SSSZZ").format (aDateTime);
    return this;
  }

  @Nonnull
  public final HCTime setAsDateAndTime (@Nonnull final ZonedDateTime aDateTime)
  {
    return setAsDateAndTime (aDateTime.toLocalDateTime ());
  }

  @Nonnull
  public final HCTime setAsTimezone (@CheckForSigned final int nMinutes)
  {
    final int nHours = nMinutes / CGlobal.MINUTES_PER_HOUR;
    final int nRestMinutes = nMinutes % CGlobal.MINUTES_PER_HOUR;
    m_sDatetime = (nHours < 0 ? "-" : "+") +
                  StringHelper.getLeadingZero (MathHelper.abs (nHours), LENGTH_HOUR) +
                  ":" +
                  StringHelper.getLeadingZero (MathHelper.abs (nRestMinutes), LENGTH_MIN);
    return this;
  }

  @Nonnull
  public final HCTime setAsWeekInYear (@Nonnegative final int nYear, @Nonnegative final int nWeekInYear)
  {
    m_sDatetime = StringHelper.getLeadingZero (nYear, LENGTH_YEAR) +
                  "-W" +
                  StringHelper.getLeadingZero (nWeekInYear, LENGTH_WEEKINYEAR);
    return this;
  }

  @Nonnull
  public final HCTime setAsYear (@Nonnegative final int nYear)
  {
    m_sDatetime = StringHelper.getLeadingZero (nYear, LENGTH_YEAR);
    return this;
  }

  @Override
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (StringHelper.hasText (m_sDatetime))
      aElement.setAttribute (CHTMLAttributes.DATETIME, m_sDatetime);
  }
}
