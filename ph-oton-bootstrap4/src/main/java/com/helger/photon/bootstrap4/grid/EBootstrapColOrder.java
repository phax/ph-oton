/*
 * Copyright (C) 2018-2021 Philip Helger (www.helger.com)
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

import javax.annotation.Nullable;

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Bootstrap4 grid order.
 *
 * @author Philip Helger
 */
public enum EBootstrapColOrder implements ICSSClassProvider
{
  ORDER_1 (1, CBootstrapCSS.ORDER_1),
  ORDER_2 (2, CBootstrapCSS.ORDER_2),
  ORDER_3 (3, CBootstrapCSS.ORDER_3),
  ORDER_4 (4, CBootstrapCSS.ORDER_4),
  ORDER_5 (5, CBootstrapCSS.ORDER_5),
  ORDER_6 (6, CBootstrapCSS.ORDER_6),
  ORDER_7 (7, CBootstrapCSS.ORDER_7),
  ORDER_8 (8, CBootstrapCSS.ORDER_8),
  ORDER_9 (9, CBootstrapCSS.ORDER_9),
  ORDER_10 (10, CBootstrapCSS.ORDER_10),
  ORDER_11 (11, CBootstrapCSS.ORDER_11),
  ORDER_12 (12, CBootstrapCSS.ORDER_12);

  private final int m_nParts;
  private final ICSSClassProvider m_aCSSClass;

  EBootstrapColOrder (final int nParts, @Nullable final ICSSClassProvider aCSSClass)
  {
    m_nParts = nParts;
    m_aCSSClass = aCSSClass;
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
  public static EBootstrapColOrder getFromParts (final int nParts)
  {
    switch (nParts)
    {
      case 1:
        return ORDER_1;
      case 2:
        return ORDER_2;
      case 3:
        return ORDER_3;
      case 4:
        return ORDER_4;
      case 5:
        return ORDER_5;
      case 6:
        return ORDER_6;
      case 7:
        return ORDER_7;
      case 8:
        return ORDER_8;
      case 9:
        return ORDER_9;
      case 10:
        return ORDER_10;
      case 11:
        return ORDER_11;
      case 12:
        return ORDER_12;
      default:
        return null;
    }
  }
}
