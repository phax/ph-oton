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
package com.helger.webctrls.datatables.comparator;

import java.util.Locale;

import javax.annotation.Nullable;

import com.helger.commons.format.IFormatter;
import com.helger.datetime.format.SerializableDateTimeFormatter;
import com.helger.datetime.format.SerializableDateTimeFormatter.EFormatStyle;

public class ComparatorDTDate extends ComparatorDTDateTime
{
  public ComparatorDTDate (@Nullable final Locale aParseLocale)
  {
    this (null, aParseLocale);
  }

  public ComparatorDTDate (@Nullable final IFormatter aFormatter, @Nullable final Locale aParseLocale)
  {
    super (aFormatter, SerializableDateTimeFormatter.createForDate (EFormatStyle.DEFAULT, aParseLocale));
  }
}
