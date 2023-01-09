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

import java.io.Serializable;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.grid.EBootstrapGridType;

/**
 * Utility class to build a display class based on {@link EBootstrapGridType}
 * and {@link EBootstrapDisplayType}.
 *
 * @author Philip Helger
 */
public class BootstrapDisplayBuilder implements ICSSClassProvider, Serializable
{
  private EBootstrapGridType m_eGrid = EBootstrapGridType.XS;
  private EBootstrapDisplayType m_eDisplay = EBootstrapDisplayType.BLOCK;

  public BootstrapDisplayBuilder ()
  {}

  /**
   * Set the grid type. Default is "xs" which means valid for all.
   *
   * @param eGrid
   *        Grid type. May not be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public BootstrapDisplayBuilder grid (@Nonnull final EBootstrapGridType eGrid)
  {
    ValueEnforcer.notNull (eGrid, "GridType");
    m_eGrid = eGrid;
    return this;
  }

  /**
   * Set the display type. Default is "block".
   *
   * @param eDisplay
   *        Display type. May not be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public BootstrapDisplayBuilder display (@Nonnull final EBootstrapDisplayType eDisplay)
  {
    ValueEnforcer.notNull (eDisplay, "eDisplay");
    m_eDisplay = eDisplay;
    return this;
  }

  @Nonnull
  public String getCSSClass ()
  {
    return "d" + m_eGrid.getCSSClassNamePart () + "-" + m_eDisplay.getCSSClassNamePart ();
  }
}
