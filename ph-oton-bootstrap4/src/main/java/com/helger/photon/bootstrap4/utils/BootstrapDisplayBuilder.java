package com.helger.photon.bootstrap4.utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.grid.EBootstrapGridType;

/**
 * Utility class to build a display class.
 *
 * @author Philip Helger
 */
public class BootstrapDisplayBuilder implements ICSSClassProvider
{
  private EBootstrapGridType m_eGrid = EBootstrapGridType.XS;
  private EDisplayType m_eDisplay = EDisplayType.BLOCK;

  public BootstrapDisplayBuilder ()
  {}

  /**
   * Set the grid type. Default is "xs" which means valid for all.
   *
   * @param eGrid
   *        Grid type. May not be <code>null</code>.
   * @return this for chaining
   */
  @Nullable
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
  @Nullable
  public BootstrapDisplayBuilder display (@Nonnull final EDisplayType eDisplay)
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
