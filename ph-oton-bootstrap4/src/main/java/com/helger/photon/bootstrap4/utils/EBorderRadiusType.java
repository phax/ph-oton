package com.helger.photon.bootstrap4.utils;

import javax.annotation.Nonnull;

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

public enum EBorderRadiusType implements ICSSClassProvider
{
  ROUNDED (CBootstrapCSS.ROUNDED),
  ROUNDED_TOP (CBootstrapCSS.ROUNDED_TOP),
  ROUNDED_RIGHT (CBootstrapCSS.ROUNDED_RIGHT),
  ROUNDED_BOTTOM (CBootstrapCSS.ROUNDED_BOTTOM),
  ROUNDED_LEFT (CBootstrapCSS.ROUNDED_LEFT),
  ROUNDED_CIRCLE (CBootstrapCSS.ROUNDED_CIRCLE),
  NOT_ROUNDED (CBootstrapCSS.ROUNDED_0);

  private final ICSSClassProvider m_aCSSClass;

  private EBorderRadiusType (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nonnull
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
