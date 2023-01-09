/*
 * Copyright (C) 2018-2023 Philip Helger (www.helger.com)
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

import com.helger.commons.annotation.Nonempty;
import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Text transform. See https://getbootstrap.com/docs/4.1/utilities/text/
 *
 * @author Philip Helger
 */
public enum EBootstrapTextTransformType implements ICSSClassProvider
{
  LOWERCASE (CBootstrapCSS.TEXT_LOWERCASE),
  UPPERCASE (CBootstrapCSS.TEXT_UPPERCASE),
  /**
   * Note how text-capitalize only changes the first letter of each word,
   * leaving the case of any other letters unaffected.
   */
  CAPITALIZE (CBootstrapCSS.TEXT_CAPITALIZE);

  private final ICSSClassProvider m_aCSSClass;

  EBootstrapTextTransformType (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nonnull
  @Nonempty
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
