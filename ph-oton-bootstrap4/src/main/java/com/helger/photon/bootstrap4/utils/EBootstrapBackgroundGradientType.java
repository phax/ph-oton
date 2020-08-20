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
 * <p>
 * <b>Requires Bootstrap Customization to work!</b>
 * </p>
 * Background gradient. See https://getbootstrap.com/docs/4.1/utilities/colors/
 *
 * @author Philip Helger
 */
public enum EBootstrapBackgroundGradientType implements ICSSClassProvider
{
  PRIMARY (CBootstrapCSS.BG_GRADIENT_PRIMARY),
  SECONDARY (CBootstrapCSS.BG_GRADIENT_SECONDARY),
  SUCCESS (CBootstrapCSS.BG_GRADIENT_SUCCESS),
  DANGER (CBootstrapCSS.BG_GRADIENT_DANGER),
  WARNING (CBootstrapCSS.BG_GRADIENT_WARNING),
  INFO (CBootstrapCSS.BG_GRADIENT_INFO),
  LIGHT (CBootstrapCSS.BG_GRADIENT_LIGHT),
  DARK (CBootstrapCSS.BG_GRADIENT_DARK);

  private final ICSSClassProvider m_aCSSClass;

  EBootstrapBackgroundGradientType (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nonnull
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
