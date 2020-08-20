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

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Border radius. See https://getbootstrap.com/docs/4.1/utilities/borders/
 *
 * @author Philip Helger
 */
public enum EBootstrapBorderRadiusType implements ICSSClassProvider
{
  ROUNDED (CBootstrapCSS.ROUNDED),
  ROUNDED_TOP (CBootstrapCSS.ROUNDED_TOP),
  ROUNDED_RIGHT (CBootstrapCSS.ROUNDED_RIGHT),
  ROUNDED_BOTTOM (CBootstrapCSS.ROUNDED_BOTTOM),
  ROUNDED_LEFT (CBootstrapCSS.ROUNDED_LEFT),
  ROUNDED_CIRCLE (CBootstrapCSS.ROUNDED_CIRCLE),
  NOT_ROUNDED (CBootstrapCSS.ROUNDED_0);

  private final ICSSClassProvider m_aCSSClass;

  EBootstrapBorderRadiusType (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nonnull
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
