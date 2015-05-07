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
package com.helger.html.hc.html5;

import java.util.Date;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.ISOPeriodFormat;

import com.helger.commons.CGlobal;
import com.helger.commons.math.MathHelper;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.string.StringHelper;
import com.helger.commons.typeconvert.TypeConverter;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.annotations.SinceHTML5;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCElementWithChildren;

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
  public String getDatetime ()
  {
    return m_sDatetime;
  }

  @Nonnull
  public HCTime setAsMonth (@Nonnegative final int nYear, @Nonnegative final int nMonth)
  {
    m_sDatetime = StringHelper.getLeadingZero (nYear, LENGTH_YEAR) +
                  "-" +
                  StringHelper.getLeadingZero (nMonth, LENGTH_MONTH);
    return this;
  }

  @Nonnull
  public HCTime setAsDate (@Nonnegative final int nYear, @Nonnegative final int nMonth, @Nonnegative final int nDay)
  {
    m_sDatetime = StringHelper.getLeadingZero (nYear, LENGTH_YEAR) +
                  "-" +
                  StringHelper.getLeadingZero (nMonth, LENGTH_MONTH) +
                  "-" +
                  StringHelper.getLeadingZero (nDay, LENGTH_DAY);
    return this;
  }

  @Nonnull
  public HCTime setAsDate (@Nonnull final LocalDate aDate)
  {
    m_sDatetime = ISODateTimeFormat.yearMonthDay ().print (aDate);
    return this;
  }

  @Nonnull
  public HCTime setAsDate (@Nonnull final Date aDate)
  {
    return setAsDate (TypeConverter.convertIfNecessary (aDate, LocalDate.class));
  }

  @Nonnull
  public HCTime setAsYearlessDate (@Nonnegative final int nMonth, @Nonnegative final int nDay)
  {
    m_sDatetime = StringHelper.getLeadingZero (nMonth, LENGTH_MONTH) +
                  "-" +
                  StringHelper.getLeadingZero (nDay, LENGTH_DAY);
    return this;
  }

  @Nonnull
  public HCTime setAsTime (@Nonnegative final int nHour, @Nonnegative final int nMinute)
  {
    m_sDatetime = StringHelper.getLeadingZero (nHour, LENGTH_HOUR) +
                  ":" +
                  StringHelper.getLeadingZero (nMinute, LENGTH_MIN);
    return this;
  }

  @Nonnull
  public HCTime setAsTime (@Nonnegative final int nHour, @Nonnegative final int nMinute, @Nonnegative final int nSecond)
  {
    m_sDatetime = StringHelper.getLeadingZero (nHour, LENGTH_HOUR) +
                  ":" +
                  StringHelper.getLeadingZero (nMinute, LENGTH_MIN) +
                  ":" +
                  StringHelper.getLeadingZero (nSecond, LENGTH_SEC);
    return this;
  }

  @Nonnull
  public HCTime setAsTime (@Nonnegative final int nHour,
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
  public HCTime setAsTime (@Nonnull final LocalTime aTime)
  {
    m_sDatetime = ISODateTimeFormat.hourMinuteSecondMillis ().print (aTime);
    return this;
  }

  @Nonnull
  public HCTime setAsTime (@Nonnull final Date aDate)
  {
    return setAsTime (TypeConverter.convertIfNecessary (aDate, LocalTime.class));
  }

  @Nonnull
  public HCTime setAsDateAndTime (@Nonnull final LocalDateTime aDateTime)
  {
    m_sDatetime = ISODateTimeFormat.dateTime ().print (aDateTime);
    return this;
  }

  @Nonnull
  public HCTime setAsDateAndTime (@Nonnull final DateTime aDateTime)
  {
    m_sDatetime = ISODateTimeFormat.dateTime ().print (aDateTime);
    return this;
  }

  @Nonnull
  public HCTime setAsTimezone (@CheckForSigned final int nMinutes)
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
  public HCTime setAsWeekInYear (@Nonnegative final int nYear, @Nonnegative final int nWeekInYear)
  {
    m_sDatetime = StringHelper.getLeadingZero (nYear, LENGTH_YEAR) +
                  "-W" +
                  StringHelper.getLeadingZero (nWeekInYear, LENGTH_WEEKINYEAR);
    return this;
  }

  @Nonnull
  public HCTime setAsYear (@Nonnegative final int nYear)
  {
    m_sDatetime = StringHelper.getLeadingZero (nYear, LENGTH_YEAR);
    return this;
  }

  @Nonnull
  public HCTime setAsDuration (@Nonnull final Duration aDuration)
  {
    return setAsDuration (aDuration.toPeriod ());
  }

  @Nonnull
  public HCTime setAsDuration (@Nonnull final Period aPeriod)
  {
    m_sDatetime = ISOPeriodFormat.standard ().print (aPeriod);
    return this;
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (StringHelper.hasText (m_sDatetime))
      aElement.setAttribute (CHTMLAttributes.DATETIME, m_sDatetime);
  }
}
