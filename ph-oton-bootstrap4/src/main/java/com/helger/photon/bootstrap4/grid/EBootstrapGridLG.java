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

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Bootstrap4 grid columns. Large (&ge;992px)
 *
 * @author Philip Helger
 */
public enum EBootstrapGridLG implements IBootstrapGridElement
{
  LG_1 (1, CBootstrapCSS.COL_LG_1, CBootstrapCSS.OFFSET_LG_1),
  LG_2 (2, CBootstrapCSS.COL_LG_2, CBootstrapCSS.OFFSET_LG_2),
  LG_3 (3, CBootstrapCSS.COL_LG_3, CBootstrapCSS.OFFSET_LG_3),
  LG_4 (4, CBootstrapCSS.COL_LG_4, CBootstrapCSS.OFFSET_LG_4),
  LG_5 (5, CBootstrapCSS.COL_LG_5, CBootstrapCSS.OFFSET_LG_5),
  LG_6 (6, CBootstrapCSS.COL_LG_6, CBootstrapCSS.OFFSET_LG_6),
  LG_7 (7, CBootstrapCSS.COL_LG_7, CBootstrapCSS.OFFSET_LG_7),
  LG_8 (8, CBootstrapCSS.COL_LG_8, CBootstrapCSS.OFFSET_LG_8),
  LG_9 (9, CBootstrapCSS.COL_LG_9, CBootstrapCSS.OFFSET_LG_9),
  LG_10 (10, CBootstrapCSS.COL_LG_10, CBootstrapCSS.OFFSET_LG_10),
  LG_11 (11, CBootstrapCSS.COL_LG_11, CBootstrapCSS.OFFSET_LG_11),
  LG_12 (12, CBootstrapCSS.COL_LG_12, null),
  AUTO (PARTS_AUTO, CBootstrapCSS.COL_LG_AUTO, null),
  EVENLY (PARTS_EVENLY, CBootstrapCSS.COL_LG, null);

  private final int m_nParts;
  private final ICSSClassProvider m_aCSSClass;
  private final ICSSClassProvider m_aCSSClassOffset;

  EBootstrapGridLG (final int nParts, @Nullable final ICSSClassProvider aCSSClass, @Nullable final ICSSClassProvider aCSSClassOffset)
  {
    m_nParts = nParts;
    m_aCSSClass = aCSSClass;
    m_aCSSClassOffset = aCSSClassOffset;
  }

  @Nonnull
  public EBootstrapGridType getGridType ()
  {
    return EBootstrapGridType.LG;
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
    return this == LG_12;
  }

  @Nullable
  public static EBootstrapGridLG getFromParts (final int nParts)
  {
    switch (nParts)
    {
      case 1:
        return LG_1;
      case 2:
        return LG_2;
      case 3:
        return LG_3;
      case 4:
        return LG_4;
      case 5:
        return LG_5;
      case 6:
        return LG_6;
      case 7:
        return LG_7;
      case 8:
        return LG_8;
      case 9:
        return LG_9;
      case 10:
        return LG_10;
      case 11:
        return LG_11;
      case 12:
        return LG_12;
      case PARTS_AUTO:
        return AUTO;
      case PARTS_EVENLY:
        return EVENLY;
      default:
        return null;
    }
  }
}
