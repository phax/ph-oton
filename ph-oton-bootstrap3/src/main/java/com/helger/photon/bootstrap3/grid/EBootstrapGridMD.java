/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
import javax.annotation.Nullable;

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap3.CBootstrapCSS;

/**
 * Bootstrap3 grid columns. Medium - Desktops (&ge;992px)
 *
 * @author Philip Helger
 */
public enum EBootstrapGridMD implements IBootstrapGridElementExtended
{
  MD_1 (1,
        CBootstrapCSS.COL_MD_1,
        CBootstrapCSS.COL_MD_OFFSET_1,
        CBootstrapCSS.COL_MD_PUSH_1,
        CBootstrapCSS.COL_MD_PULL_1),
  MD_2 (2,
        CBootstrapCSS.COL_MD_2,
        CBootstrapCSS.COL_MD_OFFSET_2,
        CBootstrapCSS.COL_MD_PUSH_2,
        CBootstrapCSS.COL_MD_PULL_2),
  MD_3 (3,
        CBootstrapCSS.COL_MD_3,
        CBootstrapCSS.COL_MD_OFFSET_3,
        CBootstrapCSS.COL_MD_PUSH_3,
        CBootstrapCSS.COL_MD_PULL_3),
  MD_4 (4,
        CBootstrapCSS.COL_MD_4,
        CBootstrapCSS.COL_MD_OFFSET_4,
        CBootstrapCSS.COL_MD_PUSH_4,
        CBootstrapCSS.COL_MD_PULL_4),
  MD_5 (5,
        CBootstrapCSS.COL_MD_5,
        CBootstrapCSS.COL_MD_OFFSET_5,
        CBootstrapCSS.COL_MD_PUSH_5,
        CBootstrapCSS.COL_MD_PULL_5),
  MD_6 (6,
        CBootstrapCSS.COL_MD_6,
        CBootstrapCSS.COL_MD_OFFSET_6,
        CBootstrapCSS.COL_MD_PUSH_6,
        CBootstrapCSS.COL_MD_PULL_6),
  MD_7 (7,
        CBootstrapCSS.COL_MD_7,
        CBootstrapCSS.COL_MD_OFFSET_7,
        CBootstrapCSS.COL_MD_PUSH_7,
        CBootstrapCSS.COL_MD_PULL_7),
  MD_8 (8,
        CBootstrapCSS.COL_MD_8,
        CBootstrapCSS.COL_MD_OFFSET_8,
        CBootstrapCSS.COL_MD_PUSH_8,
        CBootstrapCSS.COL_MD_PULL_8),
  MD_9 (9,
        CBootstrapCSS.COL_MD_9,
        CBootstrapCSS.COL_MD_OFFSET_9,
        CBootstrapCSS.COL_MD_PUSH_9,
        CBootstrapCSS.COL_MD_PULL_9),
  MD_10 (10,
         CBootstrapCSS.COL_MD_10,
         CBootstrapCSS.COL_MD_OFFSET_10,
         CBootstrapCSS.COL_MD_PUSH_10,
         CBootstrapCSS.COL_MD_PULL_10),
  MD_11 (11,
         CBootstrapCSS.COL_MD_11,
         CBootstrapCSS.COL_MD_OFFSET_11,
         CBootstrapCSS.COL_MD_PUSH_11,
         CBootstrapCSS.COL_MD_PULL_11),
  MD_12 (12,
         CBootstrapCSS.COL_MD_12,
         CBootstrapCSS.COL_MD_OFFSET_12,
         CBootstrapCSS.COL_MD_PUSH_12,
         CBootstrapCSS.COL_MD_PULL_12);

  private final int m_nParts;
  private final ICSSClassProvider m_aCSSClass;
  private final ICSSClassProvider m_aCSSClassOffset;
  private final ICSSClassProvider m_aCSSClassPush;
  private final ICSSClassProvider m_aCSSClassPull;

  private EBootstrapGridMD (@Nonnegative final int nParts,
                            @Nullable final ICSSClassProvider aCSSClass,
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
  public static EBootstrapGridMD getFromParts (@Nonnegative final int nParts)
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
      default:
        return null;
    }
  }

  public boolean isMax ()
  {
    return this == MD_12;
  }
}
