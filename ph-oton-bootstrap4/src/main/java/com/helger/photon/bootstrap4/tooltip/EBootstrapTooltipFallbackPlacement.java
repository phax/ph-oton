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
package com.helger.photon.bootstrap4.tooltip;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;

public enum EBootstrapTooltipFallbackPlacement
{
  FLIP ("flip"),
  CLOCK_WISE ("clockwise"),
  COUNTER_CLOCK_WISE ("counterclockwise");

  private final String m_sValue;

  EBootstrapTooltipFallbackPlacement (@Nonnull @Nonempty final String sValue)
  {
    m_sValue = sValue;
  }

  @Nonnull
  @Nonempty
  public String getValue ()
  {
    return m_sValue;
  }
}
