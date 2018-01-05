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
package com.helger.photon.bootstrap4.utils;

import javax.annotation.Nonnull;

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Print display class enum
 * 
 * @author Philip Helger
 */
public enum EDisplayPrintType implements ICSSClassProvider
{
  BLOCK (CBootstrapCSS.D_PRINT_BLOCK),
  INLINE (CBootstrapCSS.D_PRINT_INLINE),
  INLINE_BLOCK (CBootstrapCSS.D_PRINT_INLINE_BLOCK),
  NONE (CBootstrapCSS.D_PRINT_NONE);

  private final ICSSClassProvider m_aCSSClass;

  private EDisplayPrintType (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nonnull
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
