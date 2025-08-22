/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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

import com.helger.annotation.Nonempty;
import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.grid.EBootstrapGridType;

import jakarta.annotation.Nonnull;

/**
 * Text alignment. See https://getbootstrap.com/docs/4.1/utilities/text/
 *
 * @author Philip Helger
 */
public enum EBootstrapTextAlignType implements ICSSClassProvider
{
  LEFT (CBootstrapCSS.TEXT_LEFT, EBootstrapGridType.XS),
  CENTER (CBootstrapCSS.TEXT_CENTER, EBootstrapGridType.XS),
  RIGHT (CBootstrapCSS.TEXT_RIGHT, EBootstrapGridType.XS),
  SM_LEFT (CBootstrapCSS.TEXT_SM_LEFT, EBootstrapGridType.SM),
  SM_CENTER (CBootstrapCSS.TEXT_SM_CENTER, EBootstrapGridType.SM),
  SM_RIGHT (CBootstrapCSS.TEXT_SM_RIGHT, EBootstrapGridType.SM),
  MD_LEFT (CBootstrapCSS.TEXT_MD_LEFT, EBootstrapGridType.MD),
  MD_CENTER (CBootstrapCSS.TEXT_MD_CENTER, EBootstrapGridType.MD),
  MD_RIGHT (CBootstrapCSS.TEXT_MD_RIGHT, EBootstrapGridType.MD),
  LG_LEFT (CBootstrapCSS.TEXT_LG_LEFT, EBootstrapGridType.LG),
  LG_CENTER (CBootstrapCSS.TEXT_LG_CENTER, EBootstrapGridType.LG),
  LG_RIGHT (CBootstrapCSS.TEXT_LG_RIGHT, EBootstrapGridType.LG),
  XL_LEFT (CBootstrapCSS.TEXT_XL_LEFT, EBootstrapGridType.XL),
  XL_CENTER (CBootstrapCSS.TEXT_XL_CENTER, EBootstrapGridType.XL),
  XL_RIGHT (CBootstrapCSS.TEXT_XL_RIGHT, EBootstrapGridType.XL);

  private final ICSSClassProvider m_aCSSClass;
  private final EBootstrapGridType m_eGridType;

  EBootstrapTextAlignType (@Nonnull final ICSSClassProvider aCSSClass, @Nonnull final EBootstrapGridType eGridType)
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
   * @return The grid type on which this text alignment is applied.
   */
  @Nonnull
  public EBootstrapGridType getGridType ()
  {
    return m_eGridType;
  }
}
