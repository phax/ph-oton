/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;

/**
 * Defines the different views of DTP.
 *
 * @author Philip Helger
 */
public enum EDateTimePickerViewType
{
  HOUR (0, "hour"),
  DAY (1, "day"),
  MONTH (2, "month"),
  YEAR (3, "year"),
  DECADE (4, "decade");

  private final int m_nJSValue;
  private final String m_sJSValue;

  private EDateTimePickerViewType (@Nonnegative final int nJSValue, @Nonnull @Nonempty final String sJSValue)
  {
    m_nJSValue = nJSValue;
    m_sJSValue = sJSValue;
  }

  @Nonnegative
  public int getJSValueInt ()
  {
    return m_nJSValue;
  }

  @Nonnull
  @Nonempty
  public String getJSValueString ()
  {
    return m_sJSValue;
  }

  public boolean isLessThan (@Nonnull final EDateTimePickerViewType eOther)
  {
    return m_nJSValue < eOther.m_nJSValue;
  }

  public boolean isGreaterThan (@Nonnull final EDateTimePickerViewType eOther)
  {
    return m_nJSValue > eOther.m_nJSValue;
  }
}
