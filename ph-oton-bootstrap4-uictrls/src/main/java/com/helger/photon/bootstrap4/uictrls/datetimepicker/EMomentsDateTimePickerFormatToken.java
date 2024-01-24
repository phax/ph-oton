/*
 * Copyright (C) 2018-2024 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;

/**
 * Defines the possible tokens for the moments.js format. Works for JS and Java
 * 1.8.
 *
 * @author Philip Helger
 */
public enum EMomentsDateTimePickerFormatToken
{
  AMPM_LOWER ("a", "a"),
  AMPM_UPPER ("A", "a"),
  SECOND ("s", "s"),
  MINUTE ("m", "m"),
  HOUR23 ("H", "H"),
  // hour 1-24 is not supported in JS
  HOUR24 ("k", "k"),
  // hour 0-12 is not supported in JS
  HOUR11 ("h", "K"),
  HOUR12 ("h", "h"),
  DAY_OF_MONTH ("D", "d"),
  MONTH ("M", "M"),
  ABBR_MONTH_NAME ("MMM", "MMM"),
  FULL_MONTH_NAME ("MMMM", "MMMM"),
  YEAR_OLD ("Y", "y"),
  YEAR ("Y", "u");

  private final String m_sJSToken;
  private final String m_sJavaToken;

  EMomentsDateTimePickerFormatToken (@Nonnull @Nonempty final String sJSToken, @Nonnull @Nonempty final String sJavaToken)
  {
    m_sJSToken = sJSToken;
    m_sJavaToken = sJavaToken;
  }

  @Nonnull
  public String getJSToken ()
  {
    return m_sJSToken;
  }

  @Nonnull
  public String getJavaToken ()
  {
    return m_sJavaToken;
  }
}
