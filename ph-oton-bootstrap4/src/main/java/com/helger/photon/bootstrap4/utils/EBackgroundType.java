/**
 * Copyright (C) 2015-2017 Philip Helger (www.helger.com)
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

public enum EBackgroundType implements ICSSClassProvider
{
  PRIMARY (CBootstrapCSS.BG_PRIMARY),
  SECONDARY (CBootstrapCSS.BG_SECONDARY),
  SUCCESS (CBootstrapCSS.BG_SUCCESS),
  DANGER (CBootstrapCSS.BG_DANGER),
  WARNING (CBootstrapCSS.BG_WARNING),
  INFO (CBootstrapCSS.BG_INFO),
  LIGHT (CBootstrapCSS.BG_LIGHT),
  DARK (CBootstrapCSS.BG_DARK),
  WHITE (CBootstrapCSS.BG_WHITE);

  private final ICSSClassProvider m_aCSSClass;

  private EBackgroundType (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nonnull
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
