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
 * Bootstrap3 grid columns. Extra small - Phones (&lt;768px)
 *
 * @author Philip Helger
 */
public enum EBootstrapGridXS implements IBootstrapGridElementExtended
{
  XS_1 (1, CBootstrapCSS.COL_XS_1, CBootstrapCSS.COL_XS_OFFSET_1, CBootstrapCSS.COL_XS_PUSH_1, CBootstrapCSS.COL_XS_PULL_1),
  XS_2 (2, CBootstrapCSS.COL_XS_2, CBootstrapCSS.COL_XS_OFFSET_2, CBootstrapCSS.COL_XS_PUSH_2, CBootstrapCSS.COL_XS_PULL_2),
  XS_3 (3, CBootstrapCSS.COL_XS_3, CBootstrapCSS.COL_XS_OFFSET_3, CBootstrapCSS.COL_XS_PUSH_3, CBootstrapCSS.COL_XS_PULL_3),
  XS_4 (4, CBootstrapCSS.COL_XS_4, CBootstrapCSS.COL_XS_OFFSET_4, CBootstrapCSS.COL_XS_PUSH_4, CBootstrapCSS.COL_XS_PULL_4),
  XS_5 (5, CBootstrapCSS.COL_XS_5, CBootstrapCSS.COL_XS_OFFSET_5, CBootstrapCSS.COL_XS_PUSH_5, CBootstrapCSS.COL_XS_PULL_5),
  XS_6 (6, CBootstrapCSS.COL_XS_6, CBootstrapCSS.COL_XS_OFFSET_6, CBootstrapCSS.COL_XS_PUSH_6, CBootstrapCSS.COL_XS_PULL_6),
  XS_7 (7, CBootstrapCSS.COL_XS_7, CBootstrapCSS.COL_XS_OFFSET_7, CBootstrapCSS.COL_XS_PUSH_7, CBootstrapCSS.COL_XS_PULL_7),
  XS_8 (8, CBootstrapCSS.COL_XS_8, CBootstrapCSS.COL_XS_OFFSET_8, CBootstrapCSS.COL_XS_PUSH_8, CBootstrapCSS.COL_XS_PULL_8),
  XS_9 (9, CBootstrapCSS.COL_XS_9, CBootstrapCSS.COL_XS_OFFSET_9, CBootstrapCSS.COL_XS_PUSH_9, CBootstrapCSS.COL_XS_PULL_9),
  XS_10 (10, CBootstrapCSS.COL_XS_10, CBootstrapCSS.COL_XS_OFFSET_10, CBootstrapCSS.COL_XS_PUSH_10, CBootstrapCSS.COL_XS_PULL_10),
  XS_11 (11, CBootstrapCSS.COL_XS_11, CBootstrapCSS.COL_XS_OFFSET_11, CBootstrapCSS.COL_XS_PUSH_11, CBootstrapCSS.COL_XS_PULL_11),
  XS_12 (12, CBootstrapCSS.COL_XS_12, CBootstrapCSS.COL_XS_OFFSET_12, CBootstrapCSS.COL_XS_PUSH_12, CBootstrapCSS.COL_XS_PULL_12);

  private final int m_nParts;
  private final ICSSClassProvider m_aCSSClass;
  private final ICSSClassProvider m_aCSSClassOffset;
  private final ICSSClassProvider m_aCSSClassPush;
  private final ICSSClassProvider m_aCSSClassPull;

  EBootstrapGridXS (@Nonnegative final int nParts,
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
  public static EBootstrapGridXS getFromParts (@Nonnegative final int nParts)
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
      default:
        return null;
    }
  }

  public boolean isMax ()
  {
    return this == XS_12;
  }
}
