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

public enum ETextColorType implements ICSSClassProvider
{
  PRIMARY (CBootstrapCSS.TEXT_PRIMARY),
  SECONDARY (CBootstrapCSS.TEXT_SECONDARY),
  SUCCESS (CBootstrapCSS.TEXT_SUCCESS),
  DANGER (CBootstrapCSS.TEXT_DANGER),
  WARNING (CBootstrapCSS.TEXT_WARNING),
  INFO (CBootstrapCSS.TEXT_INFO),
  LIGHT (CBootstrapCSS.TEXT_LIGHT),
  DARK (CBootstrapCSS.TEXT_DARK),
  WHITE (CBootstrapCSS.TEXT_WHITE);
  /* Note that the .text-white class has no link styling. */

  private final ICSSClassProvider m_aCSSClass;

  private ETextColorType (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nonnull
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
