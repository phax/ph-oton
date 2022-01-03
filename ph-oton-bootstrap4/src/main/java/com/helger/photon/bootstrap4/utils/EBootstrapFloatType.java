/*
 * Copyright (C) 2018-2022 Philip Helger (www.helger.com)
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

import com.helger.commons.annotation.Nonempty;
import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.grid.EBootstrapGridType;

/**
 * Floating. See https://getbootstrap.com/docs/4.1/utilities/float/
 *
 * @author Philip Helger
 */
public enum EBootstrapFloatType implements ICSSClassProvider
{
  LEFT (CBootstrapCSS.FLOAT_LEFT, EBootstrapGridType.XS),
  RIGHT (CBootstrapCSS.FLOAT_RIGHT, EBootstrapGridType.XS),
  NONE (CBootstrapCSS.FLOAT_NONE, EBootstrapGridType.XS),
  SM_LEFT (CBootstrapCSS.FLOAT_SM_LEFT, EBootstrapGridType.SM),
  SM_RIGHT (CBootstrapCSS.FLOAT_SM_RIGHT, EBootstrapGridType.SM),
  SM_NONE (CBootstrapCSS.FLOAT_SM_NONE, EBootstrapGridType.SM),
  MD_LEFT (CBootstrapCSS.FLOAT_MD_LEFT, EBootstrapGridType.MD),
  MD_RIGHT (CBootstrapCSS.FLOAT_MD_RIGHT, EBootstrapGridType.MD),
  MD_NONE (CBootstrapCSS.FLOAT_MD_NONE, EBootstrapGridType.MD),
  LG_LEFT (CBootstrapCSS.FLOAT_LG_LEFT, EBootstrapGridType.LG),
  LG_RIGHT (CBootstrapCSS.FLOAT_LG_RIGHT, EBootstrapGridType.LG),
  LG_NONE (CBootstrapCSS.FLOAT_LG_NONE, EBootstrapGridType.LG),
  XL_LEFT (CBootstrapCSS.FLOAT_XL_LEFT, EBootstrapGridType.XL),
  XL_RIGHT (CBootstrapCSS.FLOAT_XL_RIGHT, EBootstrapGridType.XL),
  XL_NONE (CBootstrapCSS.FLOAT_XL_NONE, EBootstrapGridType.XL);

  private final ICSSClassProvider m_aCSSClass;
  private final EBootstrapGridType m_eGridType;

  EBootstrapFloatType (@Nonnull final ICSSClassProvider aCSSClass, @Nonnull final EBootstrapGridType eGridType)
  {
    m_aCSSClass = aCSSClass;
    m_eGridType = eGridType;
  }

  @Nonnull
  @Nonempty
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
