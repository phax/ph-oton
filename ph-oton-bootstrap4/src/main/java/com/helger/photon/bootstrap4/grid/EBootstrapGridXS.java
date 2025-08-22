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
package com.helger.photon.bootstrap4.grid;

import com.helger.annotation.Nonempty;
import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Bootstrap4 grid columns. Extra small (&lt;576px)
 *
 * @author Philip Helger
 */
public enum EBootstrapGridXS implements IBootstrapGridElement
{
  XS_1 (1, CBootstrapCSS.COL_1, CBootstrapCSS.OFFSET_1),
  XS_2 (2, CBootstrapCSS.COL_2, CBootstrapCSS.OFFSET_2),
  XS_3 (3, CBootstrapCSS.COL_3, CBootstrapCSS.OFFSET_3),
  XS_4 (4, CBootstrapCSS.COL_4, CBootstrapCSS.OFFSET_4),
  XS_5 (5, CBootstrapCSS.COL_5, CBootstrapCSS.OFFSET_5),
  XS_6 (6, CBootstrapCSS.COL_6, CBootstrapCSS.OFFSET_6),
  XS_7 (7, CBootstrapCSS.COL_7, CBootstrapCSS.OFFSET_7),
  XS_8 (8, CBootstrapCSS.COL_8, CBootstrapCSS.OFFSET_8),
  XS_9 (9, CBootstrapCSS.COL_9, CBootstrapCSS.OFFSET_9),
  XS_10 (10, CBootstrapCSS.COL_10, CBootstrapCSS.OFFSET_10),
  XS_11 (11, CBootstrapCSS.COL_11, CBootstrapCSS.OFFSET_11),
  XS_12 (12, CBootstrapCSS.COL_12, null),
  AUTO (PARTS_AUTO, CBootstrapCSS.COL_AUTO, null),
  EVENLY (PARTS_EVENLY, CBootstrapCSS.COL, null);

  private final int m_nParts;
  private final ICSSClassProvider m_aCSSClass;
  private final ICSSClassProvider m_aCSSClassOffset;

  EBootstrapGridXS (final int nParts, @Nonnull final ICSSClassProvider aCSSClass, @Nullable final ICSSClassProvider aCSSClassOffset)
  {
    m_nParts = nParts;
    m_aCSSClass = aCSSClass;
    m_aCSSClassOffset = aCSSClassOffset;
  }

  @Nonnull
  public EBootstrapGridType getGridType ()
  {
    return EBootstrapGridType.XS;
  }

  public int getParts ()
  {
    return m_nParts;
  }

  @Nonnull
  @Nonempty
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }

  @Nullable
  public ICSSClassProvider getCSSClassOffset ()
  {
    return m_aCSSClassOffset;
  }

  public boolean isMax ()
  {
    return this == XS_12;
  }

  @Nullable
  public static EBootstrapGridXS getFromParts (final int nParts)
  {
    switch (nParts)
    {
      case 1:
        return XS_1;
      case 2:
        return XS_2;
      case 3:
        return XS_3;
      case 4:
        return XS_4;
      case 5:
        return XS_5;
      case 6:
        return XS_6;
      case 7:
        return XS_7;
      case 8:
        return XS_8;
      case 9:
        return XS_9;
      case 10:
        return XS_10;
      case 11:
        return XS_11;
      case 12:
        return XS_12;
      case PARTS_AUTO:
        return AUTO;
      case PARTS_EVENLY:
        return EVENLY;
      default:
        return null;
    }
  }
}
