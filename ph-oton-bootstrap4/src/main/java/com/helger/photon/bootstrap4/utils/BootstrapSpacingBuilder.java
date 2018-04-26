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

import java.io.Serializable;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.grid.EBootstrapGridType;

/**
 * Utility class to build a generic spacing.
 *
 * @author Philip Helger
 */
public class BootstrapSpacingBuilder implements ICSSClassProvider, Serializable
{
  private ESpacingPropertyType m_eProperty = ESpacingPropertyType.MARGIN;
  private ESpacingSideType m_eSide = ESpacingSideType.ALL;
  private EBootstrapGridType m_eGrid = EBootstrapGridType.XS;
  private int m_nSize = -1;

  public BootstrapSpacingBuilder ()
  {}

  /**
   * Set the property type. Default is "margin".
   *
   * @param eProperty
   *        Property type. May not be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public BootstrapSpacingBuilder property (@Nonnull final ESpacingPropertyType eProperty)
  {
    ValueEnforcer.notNull (eProperty, "Property");
    m_eProperty = eProperty;
    return this;
  }

  /**
   * Set the edge type. Default is "all".
   *
   * @param eSide
   *        Edge type. May not be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public BootstrapSpacingBuilder side (@Nonnull final ESpacingSideType eSide)
  {
    ValueEnforcer.notNull (eSide, "Side");
    m_eSide = eSide;
    return this;
  }

  /**
   * Set the grid type. Default is "xs" which means valid for all.
   *
   * @param eGrid
   *        Grid type. May not be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public BootstrapSpacingBuilder grid (@Nonnull final EBootstrapGridType eGrid)
  {
    ValueEnforcer.notNull (eGrid, "Grid");
    m_eGrid = eGrid;
    return this;
  }

  /**
   * Number of grid elements to "margin" (0 = x*0, 1 = x*.25, 2 = x*.5, 3 = x*1,
   * 4 = x*1.5, 5 = x*3)
   *
   * @param nSize
   *        Size from 0 to 5 or "-1" for "auto"
   * @return this for chaining
   */
  @Nonnull
  public BootstrapSpacingBuilder size (final int nSize)
  {
    ValueEnforcer.isBetweenInclusive (nSize, "Size", 0, 5);
    m_nSize = nSize;
    return this;
  }

  /**
   * Think of it as a shortcut for <code>size (-1)</code> even though this wont
   * work
   *
   * @return this for chaining
   */
  @Nonnull
  public BootstrapSpacingBuilder auto ()
  {
    m_nSize = -1;
    return this;
  }

  @Nonnull
  public String getCSSClass ()
  {
    String ret = m_eProperty.getCSSClassNamePart () +
                 m_eSide.getCSSClassNamePart () +
                 m_eGrid.getCSSClassNamePart () +
                 '-';
    if (m_nSize == -1)
      ret += "auto";
    else
      ret += m_nSize;
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static BootstrapSpacingBuilder createMarginBuilder ()
  {
    return new BootstrapSpacingBuilder ().property (ESpacingPropertyType.MARGIN);
  }

  @Nonnull
  @ReturnsMutableCopy
  public static BootstrapSpacingBuilder createPaddingBuilder ()
  {
    return new BootstrapSpacingBuilder ().property (ESpacingPropertyType.PADDING);
  }
}
