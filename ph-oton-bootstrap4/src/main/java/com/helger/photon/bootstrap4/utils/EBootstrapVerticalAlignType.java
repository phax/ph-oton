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
package com.helger.photon.bootstrap4.utils;

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

import jakarta.annotation.Nonnull;

/**
 * Vertical alignment. See
 * https://getbootstrap.com/docs/4.1/utilities/vertical-align/
 *
 * @author Philip Helger
 */
public enum EBootstrapVerticalAlignType implements ICSSClassProvider
{
  BASELINE (CBootstrapCSS.ALIGN_BASELINE),
  TOP (CBootstrapCSS.ALIGN_TOP),
  MIDDLE (CBootstrapCSS.ALIGN_MIDDLE),
  BOTTOM (CBootstrapCSS.ALIGN_BOTTOM),
  TEXT_TOP (CBootstrapCSS.ALIGN_TEXT_TOP),
  TEXT_BOTTOM (CBootstrapCSS.ALIGN_TEXT_BOTTOM);

  private final ICSSClassProvider m_aCSSClass;

  EBootstrapVerticalAlignType (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nonnull
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
