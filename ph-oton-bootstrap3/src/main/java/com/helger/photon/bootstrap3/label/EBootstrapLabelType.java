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
package com.helger.photon.bootstrap3.label;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap3.CBootstrapCSS;

/**
 * Type of label
 *
 * @author Philip Helger
 */
public enum EBootstrapLabelType implements ICSSClassProvider
{
  DEFAULT (CBootstrapCSS.LABEL_DEFAULT),
  PRIMARY (CBootstrapCSS.LABEL_PRIMARY),
  SUCCESS (CBootstrapCSS.LABEL_SUCCESS),
  INFO (CBootstrapCSS.LABEL_INFO),
  WARNING (CBootstrapCSS.LABEL_WARNING),
  DANGER (CBootstrapCSS.LABEL_DANGER);

  private final ICSSClassProvider m_aCSSClass;

  private EBootstrapLabelType (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nullable
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
