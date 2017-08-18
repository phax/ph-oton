package com.helger.photon.bootstrap4.grid;

import javax.annotation.Nonnull;

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Bootstrap row vertical align
 *
 * @author Philip Helger
 */
public enum EBootstrapRowVerticalAlign implements ICSSClassProvider
{
  TOP (CBootstrapCSS.ALIGN_ITEMS_START),
  MIDDLE (CBootstrapCSS.ALIGN_ITEMS_CENTER),
  BOTTOM (CBootstrapCSS.ALIGN_ITEMS_END);

  private final ICSSClassProvider m_aCSSClass;

  private EBootstrapRowVerticalAlign (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nonnull
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
