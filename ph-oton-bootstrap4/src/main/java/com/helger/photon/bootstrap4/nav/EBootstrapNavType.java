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
package com.helger.photon.bootstrap4.nav;

import javax.annotation.Nullable;

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Type of {@link BootstrapNav}
 *
 * @author Philip Helger
 */
public enum EBootstrapNavType
{
  DEFAULT (null),
  TABS (CBootstrapCSS.NAV_TABS),
  PILLS (CBootstrapCSS.NAV_PILLS);

  private final ICSSClassProvider m_aCSSClass;

  private EBootstrapNavType (@Nullable final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nullable
  public ICSSClassProvider getCSSClass ()
  {
    return m_aCSSClass;
  }
}
