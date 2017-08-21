package com.helger.photon.bootstrap4.utils;

import javax.annotation.Nonnull;

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

public enum EBorderType implements ICSSClassProvider
{
  /** All 4 sides */
  BORDER (CBootstrapCSS.BORDER),
  /** No border */
  NO_BORDER (CBootstrapCSS.BORDER_0),
  /** No top border */
  NO_TOP_BORDER (CBootstrapCSS.BORDER_TOP_0),
  /** No right border */
  NO_RIGHT_BORDER (CBootstrapCSS.BORDER_RIGHT_0),
  /** No bottom border */
  NO_BOTTOM_BORDER (CBootstrapCSS.BORDER_BOTTOM_0),
  /** No left border */
  NO_LEFT_BORDER (CBootstrapCSS.BORDER_LEFT_0);

  private final ICSSClassProvider m_aCSSClass;

  private EBorderType (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nonnull
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
