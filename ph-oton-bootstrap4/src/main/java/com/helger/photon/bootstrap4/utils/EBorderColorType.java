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
 * Border color. See https://getbootstrap.com/docs/4.1/utilities/borders/
 * 
 * @author Philip Helger
 */
public enum EBorderColorType implements ICSSClassProvider
{
  PRIMARY (CBootstrapCSS.BORDER_PRIMARY),
  SECONDARY (CBootstrapCSS.BORDER_SECONDARY),
  SUCCESS (CBootstrapCSS.BORDER_SUCCESS),
  DANGER (CBootstrapCSS.BORDER_DANGER),
  WARNING (CBootstrapCSS.BORDER_WARNING),
  INFO (CBootstrapCSS.BORDER_INFO),
  LIGHT (CBootstrapCSS.BORDER_LIGHT),
  DARK (CBootstrapCSS.BORDER_DARK),
  WHITE (CBootstrapCSS.BORDER_WHITE);

  private final ICSSClassProvider m_aCSSClass;

  private EBorderColorType (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nonnull
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
