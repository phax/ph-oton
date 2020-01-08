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
package com.helger.photon.bootstrap4.utils;

import javax.annotation.Nonnull;

/**
 * Spacing side. See https://getbootstrap.com/docs/4.1/utilities/spacing/
 * 
 * @author Philip Helger
 * @see BootstrapSpacingBuilder
 */
public enum EBootstrapSpacingSideType
{
  /** All 4 sides */
  ALL (""),
  /** Top only */
  TOP ("t"),
  /** Right only */
  RIGHT ("r"),
  /** bottom only */
  BOTTOM ("b"),
  /** left only */
  LEFT ("l"),
  /** left and right */
  X ("x"),
  /** top and bottom */
  Y ("y");

  private final String m_sCSSClassNamePart;

  private EBootstrapSpacingSideType (@Nonnull final String sCSSClassNamePart)
  {
    m_sCSSClassNamePart = sCSSClassNamePart;
  }

  @Nonnull
  public String getCSSClassNamePart ()
  {
    return m_sCSSClassNamePart;
  }
}
