/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;

/**
 * Defines the different views of DTP.
 *
 * @author Philip Helger
 */
public enum EBootstrap4DateTimePickerViewModeType
{
  TIMES (0, "times"),
  DAYS (1, "days"),
  MONTHS (2, "months"),
  YEARS (3, "years"),
  DECADES (4, "decades");

  private final int m_nOrder;
  private final String m_sJSValue;

  private EBootstrap4DateTimePickerViewModeType (@Nonnegative final int nJSValue, @Nonnull @Nonempty final String sJSValue)
  {
    m_nOrder = nJSValue;
    m_sJSValue = sJSValue;
  }

  @Nonnull
  @Nonempty
  public String getJSValueString ()
  {
    return m_sJSValue;
  }

  public boolean isLessThan (@Nonnull final EBootstrap4DateTimePickerViewModeType eOther)
  {
    return m_nOrder < eOther.m_nOrder;
  }

  public boolean isGreaterThan (@Nonnull final EBootstrap4DateTimePickerViewModeType eOther)
  {
    return m_nOrder > eOther.m_nOrder;
  }
}
