/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.uictrls.datetimepicker;

import java.text.SimpleDateFormat;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;

/**
 * Defines the possible tokens for the dtp format.
 *
 * @author Philip Helger
 * @see SimpleDateFormat
 */
public enum EDateTimePickerFormatToken
{
  AMPM_LOWER ("p", "a"),
  AMPM_UPPER ("P", "a"),
  SECOND ("s", "s"),
  MINUTE ("i", "m"),
  HOUR23 ("h", "H"),
  // hour 1-24 is not supported in JS
  HOUR24 ("h", "k"),
  // hour 0-12 is not supported in JS
  HOUR11 ("H", "K"),
  HOUR12 ("H", "h"),
  DAY_OF_MONTH ("d", "d"),
  MONTH ("m", "M"),
  ABBR_MONTH_NAME ("M", "MMM"),
  FULL_MONTH_NAME ("MM", "MMMM"),
  YEAR ("y", "u");

  private final String m_sJSToken;
  private final String m_sJavaToken;

  EDateTimePickerFormatToken (@Nonnull @Nonempty final String sJSToken, @Nonnull @Nonempty final String sJavaToken)
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
