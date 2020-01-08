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
 * Text color. See https://getbootstrap.com/docs/4.1/utilities/colors/
 *
 * @author Philip Helger
 */
public enum EBootstrapTextColorType implements ICSSClassProvider
{
  PRIMARY (CBootstrapCSS.TEXT_PRIMARY),
  SECONDARY (CBootstrapCSS.TEXT_SECONDARY),
  SUCCESS (CBootstrapCSS.TEXT_SUCCESS),
  DANGER (CBootstrapCSS.TEXT_DANGER),
  WARNING (CBootstrapCSS.TEXT_WARNING),
  INFO (CBootstrapCSS.TEXT_INFO),
  LIGHT (CBootstrapCSS.TEXT_LIGHT),
  DARK (CBootstrapCSS.TEXT_DARK),
  BODY (CBootstrapCSS.TEXT_BODY),
  /** Note that the .text-muted class has no link styling. */
  MUTED (CBootstrapCSS.TEXT_MUTED),
  /** Note that the .text-white class has no link styling. */
  WHITE (CBootstrapCSS.TEXT_WHITE),
  BLACK_50 (CBootstrapCSS.TEXT_BLACK_50),
  WHITE_50 (CBootstrapCSS.TEXT_WHITE_50);

  private final ICSSClassProvider m_aCSSClass;

  private EBootstrapTextColorType (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nonnull
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
