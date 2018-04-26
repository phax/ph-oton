/**
 * Copyright (C) 2015-2018 Philip Helger (www.helger.com)
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

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Border type. See https://getbootstrap.com/docs/4.1/utilities/borders/
 * 
 * @author Philip Helger
 */
public enum EBorderType implements ICSSClassProvider
{
  /** All 4 sides */
  BORDER (CBootstrapCSS.BORDER),
  /** Only top border */
  TOP_BORDER (CBootstrapCSS.BORDER_TOP),
  /** Only right border */
  RIGHT_BORDER (CBootstrapCSS.BORDER_RIGHT),
  /** Only bottom border */
  BOTTOM_BORDER (CBootstrapCSS.BORDER_BOTTOM),
  /** Only left border */
  LEFT_BORDER (CBootstrapCSS.BORDER_LEFT),
  /** No border */
  NONE (CBootstrapCSS.BORDER_0),
  /** No top border */
  NO_TOP_BORDER (CBootstrapCSS.BORDER_TOP_0),
  /** No right border */
  NO_RIGHT_BORDER (CBootstrapCSS.BORDER_RIGHT_0),
  /** No bottom border */
  NO_BOTTOM_BORDER (CBootstrapCSS.BORDER_BOTTOM_0),
  /** No left border */
  NO_LEFT_BORDER (CBootstrapCSS.BORDER_LEFT_0);

  private final ICSSClassProvider m_aCSSClass;

  private EBorderType (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nonnull
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
