/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.alert;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Type of alert
 *
 * @author Philip Helger
 */
public enum EBootstrapAlertType implements ICSSClassProvider
{
  PRIMARY (CBootstrapCSS.ALERT_PRIMARY),
  SECONDARY (CBootstrapCSS.ALERT_SECONDARY),
  SUCCESS (CBootstrapCSS.ALERT_SUCCESS),
  DANGER (CBootstrapCSS.ALERT_DANGER),
  WARNING (CBootstrapCSS.ALERT_WARNING),
  INFO (CBootstrapCSS.ALERT_INFO),
  LIGHT (CBootstrapCSS.ALERT_LIGHT),
  DARK (CBootstrapCSS.ALERT_DARK);

  private final ICSSClassProvider m_aCSSClass;

  private EBootstrapAlertType (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nullable
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
