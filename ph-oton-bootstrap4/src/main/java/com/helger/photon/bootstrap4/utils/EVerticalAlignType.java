package com.helger.photon.bootstrap4.utils;

import javax.annotation.Nonnull;

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Vertical align CSS class enum
 *
 * @author Philip Helger
 */
public enum EVerticalAlignType implements ICSSClassProvider
{
  BASELINE (CBootstrapCSS.ALIGN_BASELINE),
  TOP (CBootstrapCSS.ALIGN_TOP),
  MIDDLE (CBootstrapCSS.ALIGN_MIDDLE),
  BOTTOM (CBootstrapCSS.ALIGN_BOTTOM),
  TEXT_TOP (CBootstrapCSS.ALIGN_TEXT_TOP),
  TEXT_BOTTOM (CBootstrapCSS.ALIGN_TEXT_BOTTOM);

  private final ICSSClassProvider m_aCSSClass;

  private EVerticalAlignType (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nonnull
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
