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

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;

/**
 * Defines the different today types of DTP.<br>
 * Source: https://momentjs.com/docs/#localized-formats
 *
 * @author Philip Helger
 */
public enum Eootstrap4DateTimePickerSpecialFormats
{
  TIME ("LT"),
  TIME_WITH_SECONDS ("LTS"),
  MONTHNUM_DAY_YEAR_FULL ("L"),
  MONTHNUM_DAY_YEAR ("l"),
  MONTHNAME_DAY_YEAR_FULL ("LL"),
  MONTHNAME_DAY_YEAR ("ll"),
  MONTHNAME_DAY_YEAR_TIME_FULL ("LLL"),
  MONTHNAME_DAY_YEAR_TIME ("lll"),
  DAYOFWEEK_MONTHNAME_DAY_YEAR_TIME_FULL ("LLLL"),
  DAYOFWEEK_MONTHNAME_DAY_YEAR_TIME ("llll");

  private final String m_sFormatString;

  Eootstrap4DateTimePickerSpecialFormats (@Nonnull @Nonempty final String sFormatString)
  {
    m_sFormatString = sFormatString;
  }

  @Nonnull
  @Nonempty
  public String getFormatString ()
  {
    return m_sFormatString;
  }
}
