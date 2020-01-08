/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.grid;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap3.CBootstrapCSS;

/**
 * Bootstrap3 grid columns. Small - Tablets (&ge;768px)
 *
 * @author Philip Helger
 */
public enum EBootstrapGridSM implements IBootstrapGridElementExtended
{
  SM_1 (1,
        CBootstrapCSS.COL_SM_1,
        CBootstrapCSS.COL_SM_OFFSET_1,
        CBootstrapCSS.COL_SM_PUSH_1,
        CBootstrapCSS.COL_SM_PULL_1),
  SM_2 (2,
        CBootstrapCSS.COL_SM_2,
        CBootstrapCSS.COL_SM_OFFSET_2,
        CBootstrapCSS.COL_SM_PUSH_2,
        CBootstrapCSS.COL_SM_PULL_2),
  SM_3 (3,
        CBootstrapCSS.COL_SM_3,
        CBootstrapCSS.COL_SM_OFFSET_3,
        CBootstrapCSS.COL_SM_PUSH_3,
        CBootstrapCSS.COL_SM_PULL_3),
  SM_4 (4,
        CBootstrapCSS.COL_SM_4,
        CBootstrapCSS.COL_SM_OFFSET_4,
        CBootstrapCSS.COL_SM_PUSH_4,
        CBootstrapCSS.COL_SM_PULL_4),
  SM_5 (5,
        CBootstrapCSS.COL_SM_5,
        CBootstrapCSS.COL_SM_OFFSET_5,
        CBootstrapCSS.COL_SM_PUSH_5,
        CBootstrapCSS.COL_SM_PULL_5),
  SM_6 (6,
        CBootstrapCSS.COL_SM_6,
        CBootstrapCSS.COL_SM_OFFSET_6,
        CBootstrapCSS.COL_SM_PUSH_6,
        CBootstrapCSS.COL_SM_PULL_6),
  SM_7 (7,
        CBootstrapCSS.COL_SM_7,
        CBootstrapCSS.COL_SM_OFFSET_7,
        CBootstrapCSS.COL_SM_PUSH_7,
        CBootstrapCSS.COL_SM_PULL_7),
  SM_8 (8,
        CBootstrapCSS.COL_SM_8,
        CBootstrapCSS.COL_SM_OFFSET_8,
        CBootstrapCSS.COL_SM_PUSH_8,
        CBootstrapCSS.COL_SM_PULL_8),
  SM_9 (9,
        CBootstrapCSS.COL_SM_9,
        CBootstrapCSS.COL_SM_OFFSET_9,
        CBootstrapCSS.COL_SM_PUSH_9,
        CBootstrapCSS.COL_SM_PULL_9),
  SM_10 (10,
         CBootstrapCSS.COL_SM_10,
         CBootstrapCSS.COL_SM_OFFSET_10,
         CBootstrapCSS.COL_SM_PUSH_10,
         CBootstrapCSS.COL_SM_PULL_10),
  SM_11 (11,
         CBootstrapCSS.COL_SM_11,
         CBootstrapCSS.COL_SM_OFFSET_11,
         CBootstrapCSS.COL_SM_PUSH_11,
         CBootstrapCSS.COL_SM_PULL_11),
  SM_12 (12,
         CBootstrapCSS.COL_SM_12,
         CBootstrapCSS.COL_SM_OFFSET_12,
         CBootstrapCSS.COL_SM_PUSH_12,
         CBootstrapCSS.COL_SM_PULL_12);

  private final int m_nParts;
  private final ICSSClassProvider m_aCSSClass;
  private final ICSSClassProvider m_aCSSClassOffset;
  private final ICSSClassProvider m_aCSSClassPush;
  private final ICSSClassProvider m_aCSSClassPull;

  private EBootstrapGridSM (@Nonnegative final int nParts,
                            @Nonnull final ICSSClassProvider aCSSClass,
                            @Nullable final ICSSClassProvider aCSSClassOffset,
                            @Nullable final ICSSClassProvider aCSSClassPush,
                            @Nullable final ICSSClassProvider aCSSClassPull)
  {
    m_nParts = nParts;
    m_aCSSClass = aCSSClass;
    m_aCSSClassOffset = aCSSClassOffset;
    m_aCSSClassPush = aCSSClassPush;
    m_aCSSClassPull = aCSSClassPull;
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

  @Nullable
  public ICSSClassProvider getCSSClassOffset ()
  {
    return m_aCSSClassOffset;
  }

  @Nullable
  public ICSSClassProvider getCSSClassPush ()
  {
    return m_aCSSClassPush;
  }

  @Nullable
  public ICSSClassProvider getCSSClassPull ()
  {
    return m_aCSSClassPull;
  }

  @Nullable
  public static EBootstrapGridSM getFromParts (@Nonnegative final int nParts)
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
      default:
        return null;
    }
  }

  public boolean isMax ()
  {
    return this == SM_12;
  }
}
