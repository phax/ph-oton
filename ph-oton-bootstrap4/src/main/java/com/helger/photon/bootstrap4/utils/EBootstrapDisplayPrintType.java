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
package com.helger.photon.bootstrap4.utils;

import javax.annotation.Nonnull;

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Print display classes. See
 * https://getbootstrap.com/docs/4.1/utilities/display/
 *
 * @author Philip Helger
 */
public enum EBootstrapDisplayPrintType implements ICSSClassProvider
{
  BLOCK (CBootstrapCSS.D_PRINT_BLOCK),
  INLINE (CBootstrapCSS.D_PRINT_INLINE),
  INLINE_BLOCK (CBootstrapCSS.D_PRINT_INLINE_BLOCK),
  NONE (CBootstrapCSS.D_PRINT_NONE),
  TABLE (CBootstrapCSS.D_PRINT_TABLE),
  TABLE_CELL (CBootstrapCSS.D_PRINT_TABLE_CELL),
  TABLE_ROW (CBootstrapCSS.D_PRINT_TABLE_ROW),
  FLEX (CBootstrapCSS.D_PRINT_FLEX),
  INLINE_FLEX (CBootstrapCSS.D_PRINT_INLINE_FLEX);

  private final ICSSClassProvider m_aCSSClass;

  private EBootstrapDisplayPrintType (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nonnull
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
