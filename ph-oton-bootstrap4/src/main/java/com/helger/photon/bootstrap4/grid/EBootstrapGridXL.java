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
package com.helger.photon.bootstrap4.grid;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Bootstrap4 grid columns. Extra large (&ge;1200px)
 *
 * @author Philip Helger
 */
public enum EBootstrapGridXL implements IBootstrapGridElement
{
  XL_1 (1, CBootstrapCSS.COL_XL_1, CBootstrapCSS.OFFSET_XL_1),
  XL_2 (2, CBootstrapCSS.COL_XL_2, CBootstrapCSS.OFFSET_XL_2),
  XL_3 (3, CBootstrapCSS.COL_XL_3, CBootstrapCSS.OFFSET_XL_3),
  XL_4 (4, CBootstrapCSS.COL_XL_4, CBootstrapCSS.OFFSET_XL_4),
  XL_5 (5, CBootstrapCSS.COL_XL_5, CBootstrapCSS.OFFSET_XL_5),
  XL_6 (6, CBootstrapCSS.COL_XL_6, CBootstrapCSS.OFFSET_XL_6),
  XL_7 (7, CBootstrapCSS.COL_XL_7, CBootstrapCSS.OFFSET_XL_7),
  XL_8 (8, CBootstrapCSS.COL_XL_8, CBootstrapCSS.OFFSET_XL_8),
  XL_9 (9, CBootstrapCSS.COL_XL_9, CBootstrapCSS.OFFSET_XL_9),
  XL_10 (10, CBootstrapCSS.COL_XL_10, CBootstrapCSS.OFFSET_XL_10),
  XL_11 (11, CBootstrapCSS.COL_XL_11, CBootstrapCSS.OFFSET_XL_11),
  XL_12 (12, CBootstrapCSS.COL_XL_12, null),
  AUTO (PARTS_AUTO, CBootstrapCSS.COL_XL_AUTO, null),
  EVENLY (PARTS_EVENLY, CBootstrapCSS.COL_XL, null);

  private final int m_nParts;
  private final ICSSClassProvider m_aCSSClass;
  private final ICSSClassProvider m_aCSSClassOffset;

  EBootstrapGridXL (final int nParts, @Nullable final ICSSClassProvider aCSSClass, @Nullable final ICSSClassProvider aCSSClassOffset)
  {
    m_nParts = nParts;
    m_aCSSClass = aCSSClass;
    m_aCSSClassOffset = aCSSClassOffset;
  }

  @Nonnull
  public EBootstrapGridType getGridType ()
  {
    return EBootstrapGridType.XL;
  }

  public int getParts ()
  {
    return m_nParts;
  }

  @Nullable
  public String getCSSClass ()
  {
    return m_aCSSClass == null ? null : m_aCSSClass.getCSSClass ();
  }

  @Nullable
  public ICSSClassProvider getCSSClassOffset ()
  {
    return m_aCSSClassOffset;
  }

  public boolean isMax ()
  {
    return this == XL_12;
  }

  @Nullable
  public static EBootstrapGridXL getFromParts (final int nParts)
  {
    switch (nParts)
    {
      case 1:
        return XL_1;
      case 2:
        return XL_2;
      case 3:
        return XL_3;
      case 4:
        return XL_4;
      case 5:
        return XL_5;
      case 6:
        return XL_6;
      case 7:
        return XL_7;
      case 8:
        return XL_8;
      case 9:
        return XL_9;
      case 10:
        return XL_10;
      case 11:
        return XL_11;
      case 12:
        return XL_12;
      case PARTS_AUTO:
        return AUTO;
      case PARTS_EVENLY:
        return EVENLY;
      default:
        return null;
    }
  }
}
