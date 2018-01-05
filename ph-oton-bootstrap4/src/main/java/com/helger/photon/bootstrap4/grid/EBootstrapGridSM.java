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
package com.helger.photon.bootstrap4.grid;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Bootstrap4 grid columns. Small (&ge;576px)
 *
 * @author Philip Helger
 */
public enum EBootstrapGridSM implements IBootstrapGridElement
{
  SM_1 (1, CBootstrapCSS.COL_SM_1),
  SM_2 (2, CBootstrapCSS.COL_SM_2),
  SM_3 (3, CBootstrapCSS.COL_SM_3),
  SM_4 (4, CBootstrapCSS.COL_SM_4),
  SM_5 (5, CBootstrapCSS.COL_SM_5),
  SM_6 (6, CBootstrapCSS.COL_SM_6),
  SM_7 (7, CBootstrapCSS.COL_SM_7),
  SM_8 (8, CBootstrapCSS.COL_SM_8),
  SM_9 (9, CBootstrapCSS.COL_SM_9),
  SM_10 (10, CBootstrapCSS.COL_SM_10),
  SM_11 (11, CBootstrapCSS.COL_SM_11),
  SM_12 (12, CBootstrapCSS.COL_SM_12),
  AUTO (PARTS_AUTO, CBootstrapCSS.COL_SM_AUTO),
  EVENLY (PARTS_EVENLY, CBootstrapCSS.COL_SM);

  private final int m_nParts;
  private final ICSSClassProvider m_aCSSClass;

  private EBootstrapGridSM (final int nParts, @Nonnull final ICSSClassProvider aCSSClass)
  {
    m_nParts = nParts;
    m_aCSSClass = aCSSClass;
  }

  @Nonnull
  public EBootstrapGridType getGridType ()
  {
    return EBootstrapGridType.SM;
  }

  @Nonnegative
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

  public boolean isMax ()
  {
    return this == SM_12;
  }

  @Nullable
  public static EBootstrapGridSM getFromParts (final int nParts)
  {
    switch (nParts)
    {
      case 1:
        return SM_1;
      case 2:
        return SM_2;
      case 3:
        return SM_3;
      case 4:
        return SM_4;
      case 5:
        return SM_5;
      case 6:
        return SM_6;
      case 7:
        return SM_7;
      case 8:
        return SM_8;
      case 9:
        return SM_9;
      case 10:
        return SM_10;
      case 11:
        return SM_11;
      case 12:
        return SM_12;
      case PARTS_AUTO:
        return AUTO;
      case PARTS_EVENLY:
        return EVENLY;
      default:
        return null;
    }
  }
}
