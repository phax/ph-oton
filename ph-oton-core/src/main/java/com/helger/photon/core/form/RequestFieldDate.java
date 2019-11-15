/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.core.form;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.datatype.XMLGregorianCalendar;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.datetime.PDTToString;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.datetime.util.PDTXMLConverter;

/**
 * Special request field specially for dates.
 *
 * @author Philip Helger
 */
public class RequestFieldDate extends RequestField
{
  private final Locale m_aDisplayLocale;

  public RequestFieldDate (@Nonnull @Nonempty final String sFieldName,
                           @Nullable final String sDefaultValue,
                           @Nonnull final Locale aDisplayLocale)
  {
    super (sFieldName, sDefaultValue);
    m_aDisplayLocale = ValueEnforcer.notNull (aDisplayLocale, "DisplayLocale");
  }

  /**
   * Constructor without a default value
   *
   * @param sFieldName
   *        Field name
   * @param aDisplayLocale
   *        Display locale to use.
   */
  public RequestFieldDate (@Nonnull @Nonempty final String sFieldName, @Nonnull final Locale aDisplayLocale)
  {
    this (sFieldName, (String) null, aDisplayLocale);
  }

  public RequestFieldDate (@Nonnull @Nonempty final String sFieldName,
                           @Nullable final LocalDate aDefaultValue,
                           @Nonnull final Locale aDisplayLocale)
  {
    this (sFieldName, PDTToString.getAsString (aDefaultValue, aDisplayLocale), aDisplayLocale);
  }

  public RequestFieldDate (@Nonnull @Nonempty final String sFieldName,
                           @Nullable final LocalTime aDefaultValue,
                           @Nonnull final Locale aDisplayLocale)
  {
    this (sFieldName, PDTToString.getAsString (aDefaultValue, aDisplayLocale), aDisplayLocale);
  }

  public RequestFieldDate (@Nonnull @Nonempty final String sFieldName,
                           @Nullable final LocalDateTime aDefaultValue,
                           @Nonnull final Locale aDisplayLocale)
  {
    this (sFieldName, PDTToString.getAsString (aDefaultValue, aDisplayLocale), aDisplayLocale);
  }

  public RequestFieldDate (@Nonnull @Nonempty final String sFieldName,
                           @Nullable final ZonedDateTime aDefaultValue,
                           @Nonnull final Locale aDisplayLocale)
  {
    this (sFieldName, PDTToString.getAsString (aDefaultValue, aDisplayLocale), aDisplayLocale);
  }

  public RequestFieldDate (@Nonnull @Nonempty final String sFieldName,
                           @Nullable final XMLGregorianCalendar aDefaultValue,
                           @Nonnull final Locale aDisplayLocale)
  {
    this (sFieldName, PDTXMLConverter.getZonedDateTime (aDefaultValue), aDisplayLocale);
  }

  /**
   * @return The locale as specified in the constructor. Never
   *         <code>null</code>.
   */
  @Nonnull
  public final Locale getDisplayLocale ()
  {
    return m_aDisplayLocale;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final RequestFieldDate rhs = (RequestFieldDate) o;
    return m_aDisplayLocale.equals (rhs.m_aDisplayLocale);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aDisplayLocale).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("displayLocale", m_aDisplayLocale).getToString ();
  }

  @Nonnull
  public static RequestFieldDate createLocalDateNow (@Nonnull @Nonempty final String sFieldName,
                                                     @Nonnull final Locale aDisplayLocale)
  {
    return new RequestFieldDate (sFieldName, PDTFactory.getCurrentLocalDate (), aDisplayLocale);
  }

  @Nonnull
  public static RequestFieldDate createLocalDateTimeNow (@Nonnull @Nonempty final String sFieldName,
                                                         @Nonnull final Locale aDisplayLocale)
  {
    return new RequestFieldDate (sFieldName, PDTFactory.getCurrentLocalDateTime (), aDisplayLocale);
  }

  @Nonnull
  public static RequestFieldDate createDateTimeNow (@Nonnull @Nonempty final String sFieldName,
                                                    @Nonnull final Locale aDisplayLocale)
  {
    return new RequestFieldDate (sFieldName, PDTFactory.getCurrentZonedDateTime (), aDisplayLocale);
  }
}
