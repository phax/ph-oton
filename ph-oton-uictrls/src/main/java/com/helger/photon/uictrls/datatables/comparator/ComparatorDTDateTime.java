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
package com.helger.photon.uictrls.datatables.comparator;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.DateTime;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.format.IFormatter;
import com.helger.commons.string.ToStringGenerator;
import com.helger.datetime.PDTUtils;
import com.helger.datetime.format.PDTFromString;
import com.helger.datetime.format.SerializableDateTimeFormatter;
import com.helger.datetime.format.SerializableDateTimeFormatter.EFormatStyle;

/**
 * This comparator is responsible for sorting cells by date and/or time.
 * 
 * @author Philip Helger
 */
public class ComparatorDTDateTime extends AbstractComparatorDT
{
  private final SerializableDateTimeFormatter m_aDTFormatter;

  public ComparatorDTDateTime (@Nullable final Locale aParseLocale)
  {
    this (null, aParseLocale);
  }

  public ComparatorDTDateTime (@Nullable final IFormatter aFormatter, @Nullable final Locale aParseLocale)
  {
    this (aFormatter, SerializableDateTimeFormatter.create (EFormatStyle.DEFAULT, EFormatStyle.DEFAULT, aParseLocale));
  }

  public ComparatorDTDateTime (@Nonnull final SerializableDateTimeFormatter aDTFormatter)
  {
    this (null, aDTFormatter);
  }

  public ComparatorDTDateTime (@Nullable final IFormatter aFormatter,
                               @Nonnull final SerializableDateTimeFormatter aDTFormatter)
  {
    super (aFormatter);
    m_aDTFormatter = ValueEnforcer.notNull (aDTFormatter, "DTFormatter");
  }

  @Nonnull
  public final SerializableDateTimeFormatter getDateTimeFormatter ()
  {
    return m_aDTFormatter;
  }

  @Override
  protected final int internalCompare (@Nonnull final String sText1, @Nonnull final String sText2)
  {
    final DateTime aDT1 = PDTFromString.getDateTimeFromString (sText1, m_aDTFormatter.getFormatter ());
    final DateTime aDT2 = PDTFromString.getDateTimeFromString (sText2, m_aDTFormatter.getFormatter ());
    return PDTUtils.nullSafeCompare (aDT1, aDT2);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("dtFormatter", m_aDTFormatter).toString ();
  }
}
