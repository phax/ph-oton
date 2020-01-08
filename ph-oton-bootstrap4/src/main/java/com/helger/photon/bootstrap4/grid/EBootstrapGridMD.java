/**
 * Copyright (C) 2018-2020 Philip Helger (www.helger.com)
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
 * Bootstrap4 grid columns. Medium (&ge;768px)
 *
 * @author Philip Helger
 */
public enum EBootstrapGridMD implements IBootstrapGridElement
{
  MD_1 (1, CBootstrapCSS.COL_MD_1, CBootstrapCSS.OFFSET_MD_1),
  MD_2 (2, CBootstrapCSS.COL_MD_2, CBootstrapCSS.OFFSET_MD_2),
  MD_3 (3, CBootstrapCSS.COL_MD_3, CBootstrapCSS.OFFSET_MD_3),
  MD_4 (4, CBootstrapCSS.COL_MD_4, CBootstrapCSS.OFFSET_MD_4),
  MD_5 (5, CBootstrapCSS.COL_MD_5, CBootstrapCSS.OFFSET_MD_5),
  MD_6 (6, CBootstrapCSS.COL_MD_6, CBootstrapCSS.OFFSET_MD_6),
  MD_7 (7, CBootstrapCSS.COL_MD_7, CBootstrapCSS.OFFSET_MD_7),
  MD_8 (8, CBootstrapCSS.COL_MD_8, CBootstrapCSS.OFFSET_MD_8),
  MD_9 (9, CBootstrapCSS.COL_MD_9, CBootstrapCSS.OFFSET_MD_9),
  MD_10 (10, CBootstrapCSS.COL_MD_10, CBootstrapCSS.OFFSET_MD_10),
  MD_11 (11, CBootstrapCSS.COL_MD_11, CBootstrapCSS.OFFSET_MD_11),
  MD_12 (12, CBootstrapCSS.COL_MD_12, null),
  AUTO (PARTS_AUTO, CBootstrapCSS.COL_MD_AUTO, null),
  EVENLY (PARTS_EVENLY, CBootstrapCSS.COL_MD, null);

  private final int m_nParts;
  private final ICSSClassProvider m_aCSSClass;
  private final ICSSClassProvider m_aCSSClassOffset;

  private EBootstrapGridMD (final int nParts,
                            @Nullable final ICSSClassProvider aCSSClass,
                            @Nullable final ICSSClassProvider aCSSClassOffset)
  {
    m_nParts = nParts;
    m_aCSSClass = aCSSClass;
    m_aCSSClassOffset = aCSSClassOffset;
  }

  @Nonnull
  public EBootstrapGridType getGridType ()
  {
    return EBootstrapGridType.MD;
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
    return this == MD_12;
  }

  @Nullable
  public static EBootstrapGridMD getFromParts (final int nParts)
  {
    switch (nParts)
    {
      case 1:
        return MD_1;
      case 2:
        return MD_2;
      case 3:
        return MD_3;
      case 4:
        return MD_4;
      case 5:
        return MD_5;
      case 6:
        return MD_6;
      case 7:
        return MD_7;
      case 8:
        return MD_8;
      case 9:
        return MD_9;
      case 10:
        return MD_10;
      case 11:
        return MD_11;
      case 12:
        return MD_12;
      case PARTS_AUTO:
        return AUTO;
      case PARTS_EVENLY:
        return EVENLY;
      default:
        return null;
    }
  }
}
