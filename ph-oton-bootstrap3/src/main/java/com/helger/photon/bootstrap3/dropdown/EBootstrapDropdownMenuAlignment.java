/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.dropdown;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap3.CBootstrapCSS;

/**
 * Bootstrap dropdown menu alignment
 *
 * @author Philip Helger
 */
public enum EBootstrapDropdownMenuAlignment implements ICSSClassProvider
{
  LEFT (CBootstrapCSS.DROPDOWN_MENU_LEFT),
  RIGHT (CBootstrapCSS.DROPDOWN_MENU_RIGHT);

  public static final EBootstrapDropdownMenuAlignment DEFAULT = LEFT;

  private final ICSSClassProvider m_aCSSClass;

  EBootstrapDropdownMenuAlignment (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nullable
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
