/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import javax.xml.datatype.XMLGregorianCalendar;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.datetime.format.PDTToString;
import com.helger.datetime.helper.PDTFactory;
import com.helger.datetime.util.PDTXMLConverter;
import com.helger.html.request.IHCRequestField;
import com.helger.web.scope.util.SessionBackedRequestFieldData;

/**
 * Special session backed request field specially for dates.
 *
 * @author Philip Helger
 */
public class SessionBackedRequestFieldDate extends SessionBackedRequestFieldData implements IHCRequestField
{
  private final Locale m_aDisplayLocale;

  public SessionBackedRequestFieldDate (@NonNull @Nonempty final String sFieldName,
                                        @Nullable final String sDefaultValue,
                                        @NonNull final Locale aDisplayLocale)
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
  public SessionBackedRequestFieldDate (@NonNull @Nonempty final String sFieldName, @NonNull final Locale aDisplayLocale)
  {
    this (sFieldName, (String) null, aDisplayLocale);
  }

  public SessionBackedRequestFieldDate (@NonNull @Nonempty final String sFieldName,
                                        @Nullable final LocalDate aDefaultValue,
                                        @NonNull final Locale aDisplayLocale)
  {
    this (sFieldName, PDTToString.getAsString (aDefaultValue, aDisplayLocale), aDisplayLocale);
  }

  public SessionBackedRequestFieldDate (@NonNull @Nonempty final String sFieldName,
                                        @Nullable final LocalTime aDefaultValue,
                                        @NonNull final Locale aDisplayLocale)
  {
    this (sFieldName, PDTToString.getAsString (aDefaultValue, aDisplayLocale), aDisplayLocale);
  }

  public SessionBackedRequestFieldDate (@NonNull @Nonempty final String sFieldName,
                                        @Nullable final LocalDateTime aDefaultValue,
                                        @NonNull final Locale aDisplayLocale)
  {
    this (sFieldName, PDTToString.getAsString (aDefaultValue, aDisplayLocale), aDisplayLocale);
  }

  public SessionBackedRequestFieldDate (@NonNull @Nonempty final String sFieldName,
                                        @Nullable final ZonedDateTime aDefaultValue,
                                        @NonNull final Locale aDisplayLocale)
  {
    this (sFieldName, PDTToString.getAsString (aDefaultValue, aDisplayLocale), aDisplayLocale);
  }

  public SessionBackedRequestFieldDate (@NonNull @Nonempty final String sFieldName,
                                        @Nullable final XMLGregorianCalendar aDefaultValue,
                                        @NonNull final Locale aDisplayLocale)
  {
    this (sFieldName, PDTXMLConverter.getZonedDateTime (aDefaultValue), aDisplayLocale);
  }

  /**
   * @return The locale as specified in the constructor. Never
   *         <code>null</code>.
   */
  @NonNull
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
    final SessionBackedRequestFieldDate rhs = (SessionBackedRequestFieldDate) o;
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
    return ToStringGenerator.getDerived (super.toString ()).append ("DisplayLocale", m_aDisplayLocale).getToString ();
  }

  @NonNull
  public static SessionBackedRequestFieldDate createLocalDateNow (@NonNull @Nonempty final String sFieldName,
                                                                  @NonNull final Locale aDisplayLocale)
  {
    return new SessionBackedRequestFieldDate (sFieldName, PDTFactory.getCurrentLocalDate (), aDisplayLocale);
  }

  @NonNull
  public static SessionBackedRequestFieldDate createLocalDateTimeNow (@NonNull @Nonempty final String sFieldName,
                                                                      @NonNull final Locale aDisplayLocale)
  {
    return new SessionBackedRequestFieldDate (sFieldName, PDTFactory.getCurrentLocalDateTime (), aDisplayLocale);
  }

  @NonNull
  public static SessionBackedRequestFieldDate createDateTimeNow (@NonNull @Nonempty final String sFieldName,
                                                                 @NonNull final Locale aDisplayLocale)
  {
    return new SessionBackedRequestFieldDate (sFieldName, PDTFactory.getCurrentZonedDateTime (), aDisplayLocale);
  }
}
