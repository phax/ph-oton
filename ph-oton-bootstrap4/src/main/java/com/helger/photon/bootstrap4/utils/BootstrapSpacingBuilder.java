package com.helger.photon.bootstrap4.utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.grid.EBootstrapGridType;

/**
 * Utility class to build a generic spacing.
 *
 * @author Philip Helger
 */
public class BootstrapSpacingBuilder implements ICSSClassProvider
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
  @Nullable
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
  @Nullable
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
  @Nullable
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
}
