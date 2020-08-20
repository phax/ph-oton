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
package com.helger.photon.bootstrap3.form;

import javax.annotation.Nullable;

import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.html.IHCElement;
import com.helger.photon.bootstrap3.CBootstrapCSS;
import com.helger.photon.bootstrap3.EBootstrapIcon;

/**
 * Bootstrap form group highlighting state
 *
 * @author Philip Helger
 */
public enum EBootstrapFormGroupState implements ICSSClassProvider
{
  NONE (null, null),
  SUCCESS (CBootstrapCSS.HAS_SUCCESS, EBootstrapIcon.OK),
  WARNING (CBootstrapCSS.HAS_WARNING, EBootstrapIcon.WARNING_SIGN),
  ERROR (CBootstrapCSS.HAS_ERROR, EBootstrapIcon.REMOVE);

  private final ICSSClassProvider m_aCSSClass;
  private final EBootstrapIcon m_eIcon;

  EBootstrapFormGroupState (@Nullable final ICSSClassProvider aCSSClass, @Nullable final EBootstrapIcon eIcon)
  {
    m_aCSSClass = aCSSClass;
    m_eIcon = eIcon;
  }

  @Nullable
  public String getCSSClass ()
  {
    return m_aCSSClass == null ? null : m_aCSSClass.getCSSClass ();
  }

  public boolean isNone ()
  {
    return this == NONE;
  }

  public boolean isNotNone ()
  {
    return this != NONE;
  }

  public boolean isSuccess ()
  {
    return this == SUCCESS;
  }

  public boolean isWarning ()
  {
    return this == WARNING;
  }

  public boolean isError ()
  {
    return this == ERROR;
  }

  @Nullable
  public EBootstrapIcon getIcon ()
  {
    return m_eIcon;
  }

  @Nullable
  public IHCElement <?> getIconAsNode ()
  {
    return m_eIcon == null ? null : m_eIcon.getAsNode ().addClass (CBootstrapCSS.FORM_CONTROL_FEEDBACK);
  }
}
