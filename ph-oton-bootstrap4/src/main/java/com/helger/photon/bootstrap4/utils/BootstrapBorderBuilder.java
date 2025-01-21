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

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.html.hc.html.IHCHasCSSClasses;

/**
 * Utility class to build border classes.
 *
 * @author Philip Helger
 */
public class BootstrapBorderBuilder implements Serializable
{
  private EBootstrapBorderType m_eType = EBootstrapBorderType.NONE;
  private EBootstrapBorderColorType m_eColor = null;
  private EBootstrapBorderRadiusType m_eRadius = EBootstrapBorderRadiusType.NOT_ROUNDED;

  public BootstrapBorderBuilder ()
  {}

  /**
   * Set the border type. Default is no border.
   *
   * @param eType
   *        Border type. May not be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public BootstrapBorderBuilder type (@Nonnull final EBootstrapBorderType eType)
  {
    ValueEnforcer.notNull (eType, "Type");
    m_eType = eType;
    return this;
  }

  /**
   * Set the border color. Default is <code>1px solid #e9ecef !important</code>
   *
   * @param eColor
   *        Color to use. May be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public BootstrapBorderBuilder color (@Nullable final EBootstrapBorderColorType eColor)
  {
    m_eColor = eColor;
    return this;
  }

  /**
   * Set the border radius. Default is no radius.
   *
   * @param eRadius
   *        Border type. May not be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public BootstrapBorderBuilder radius (@Nonnull final EBootstrapBorderRadiusType eRadius)
  {
    ValueEnforcer.notNull (eRadius, "Radius");
    m_eRadius = eRadius;
    return this;
  }

  public void applyTo (@Nonnull final IHCHasCSSClasses <?> aObj)
  {
    aObj.addClasses (m_eType, m_eColor, m_eRadius);
  }
}
