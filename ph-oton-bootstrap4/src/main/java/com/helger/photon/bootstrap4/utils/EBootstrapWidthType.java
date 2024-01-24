/*
 * Copyright (C) 2018-2024 Philip Helger (www.helger.com)
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
 * Width. See https://getbootstrap.com/docs/4.1/utilities/sizing/
 *
 * @author Philip Helger
 */
public enum EBootstrapWidthType implements ICSSClassProvider
{
  W_25 (CBootstrapCSS.W_25),
  W_50 (CBootstrapCSS.W_50),
  W_75 (CBootstrapCSS.W_75),
  W_100 (CBootstrapCSS.W_100),
  W_AUTO (CBootstrapCSS.W_AUTO);

  private final ICSSClassProvider m_aCSSClass;

  EBootstrapWidthType (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nonnull
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
