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
package com.helger.photon.bootstrap4.navbar;

import javax.annotation.Nonnull;

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.grid.EBootstrapGridType;

/**
 * Navbar expansion type. See
 * https://getbootstrap.com/docs/4.1/components/navbar/
 *
 * @author Philip Helger
 */
public enum EBootstrapNavbarExpandType implements ICSSClassProvider
{
  EXPAND_XS (CBootstrapCSS.NAVBAR_EXPAND, EBootstrapGridType.XS),
  EXPAND_SM (CBootstrapCSS.NAVBAR_EXPAND_SM, EBootstrapGridType.SM),
  EXPAND_MD (CBootstrapCSS.NAVBAR_EXPAND_MD, EBootstrapGridType.MD),
  EXPAND_LG (CBootstrapCSS.NAVBAR_EXPAND_LG, EBootstrapGridType.LG),
  EXPAND_XL (CBootstrapCSS.NAVBAR_EXPAND_XL, EBootstrapGridType.XL);

  private final ICSSClassProvider m_aCSSClass;
  private final EBootstrapGridType m_eGridType;

  private EBootstrapNavbarExpandType (@Nonnull final ICSSClassProvider aCSSClass,
                                      @Nonnull final EBootstrapGridType eGridType)
  {
    m_aCSSClass = aCSSClass;
    m_eGridType = eGridType;
  }

  @Nonnull
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }

  /**
   * @return The grid type to be used. Never <code>null</code>.
   */
  @Nonnull
  public EBootstrapGridType getGridType ()
  {
    return m_eGridType;
  }
}
