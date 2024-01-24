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
package com.helger.photon.bootstrap4.button;

import javax.annotation.Nullable;

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Button type
 *
 * @author Philip Helger
 */
public enum EBootstrapButtonType implements ICSSClassProvider
{
  NONE (null),
  PRIMARY (CBootstrapCSS.BTN_PRIMARY),
  SECONDARY (CBootstrapCSS.BTN_SECONDARY),
  SUCCESS (CBootstrapCSS.BTN_SUCCESS),
  DANGER (CBootstrapCSS.BTN_DANGER),
  WARNING (CBootstrapCSS.BTN_WARNING),
  INFO (CBootstrapCSS.BTN_INFO),
  LIGHT (CBootstrapCSS.BTN_LIGHT),
  DARK (CBootstrapCSS.BTN_DARK),
  LINK (CBootstrapCSS.BTN_LINK),
  OUTLINE_PRIMARY (CBootstrapCSS.BTN_OUTLINE_PRIMARY),
  OUTLINE_SECONDARY (CBootstrapCSS.BTN_OUTLINE_SECONDARY),
  OUTLINE_SUCCESS (CBootstrapCSS.BTN_OUTLINE_SUCCESS),
  OUTLINE_DANGER (CBootstrapCSS.BTN_OUTLINE_DANGER),
  OUTLINE_WARNING (CBootstrapCSS.BTN_OUTLINE_WARNING),
  OUTLINE_INFO (CBootstrapCSS.BTN_OUTLINE_INFO),
  OUTLINE_LIGHT (CBootstrapCSS.BTN_OUTLINE_LIGHT),
  OUTLINE_DARK (CBootstrapCSS.BTN_OUTLINE_DARK);

  // Difference between 4.1.x and 4.2.x
  public static final EBootstrapButtonType DEFAULT = OUTLINE_SECONDARY;

  private final ICSSClassProvider m_aCSSClass;

  EBootstrapButtonType (@Nullable final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nullable
  public String getCSSClass ()
  {
    return m_aCSSClass == null ? null : m_aCSSClass.getCSSClass ();
  }
}
