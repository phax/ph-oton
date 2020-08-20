/**
 * Copyright (C) 2018-2020 Philip Helger (www.helger.com)
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

import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.datetime.PDTFormatPatterns;
import com.helger.commons.functional.IFunction;

/**
 * Action mode for the {@link BootstrapDateTimePicker}.
 *
 * @author Philip Helger
 */
public enum EBootstrap4DateTimePickerMode
{
  TIME (PDTFormatPatterns::getDefaultPatternTime),
  DATE (PDTFormatPatterns::getDefaultPatternDate),
  DATE_TIME (PDTFormatPatterns::getDefaultPatternDateTime);

  public static final EBootstrap4DateTimePickerMode DEFAULT = DATE;

  private final IFunction <Locale, String> m_aJavaFormatSupplier;

  EBootstrap4DateTimePickerMode (@Nonnull final IFunction <Locale, String> aFormatSupplier)
  {
    m_aJavaFormatSupplier = aFormatSupplier;
  }

  public boolean isTimeContained ()
  {
    return this == TIME || this == DATE_TIME;
  }

  public boolean isDateContained ()
  {
    return this == DATE || this == DATE_TIME;
  }

  @Nonnull
  public String getJSFormat (@Nonnull final Locale aDisplayLocale)
  {
    final String s = m_aJavaFormatSupplier.apply (aDisplayLocale);
    return Bootstrap4DateTimePickerFormatBuilder.fromJavaPattern (s).getJSCalendarFormatString ();
  }
}
