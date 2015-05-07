/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.button;

import javax.annotation.Nullable;

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap3.CBootstrapCSS;

/**
 * Button type
 *
 * @author Philip Helger
 */
public enum EBootstrapButtonType implements ICSSClassProvider
{
  NONE (null),
  DEFAULT (CBootstrapCSS.BTN_DEFAULT),
  PRIMARY (CBootstrapCSS.BTN_PRIMARY),
  SUCCESS (CBootstrapCSS.BTN_SUCCESS),
  INFO (CBootstrapCSS.BTN_INFO),
  WARNING (CBootstrapCSS.BTN_WARNING),
  DANGER (CBootstrapCSS.BTN_DANGER),
  LINK (CBootstrapCSS.BTN_LINK);

  private final ICSSClassProvider m_aCSSClass;

  private EBootstrapButtonType (@Nullable final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nullable
  public String getCSSClass ()
  {
    return m_aCSSClass == null ? null : m_aCSSClass.getCSSClass ();
  }
}
