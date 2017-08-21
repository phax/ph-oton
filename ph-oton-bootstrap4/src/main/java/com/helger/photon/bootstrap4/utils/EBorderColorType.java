package com.helger.photon.bootstrap4.utils;

import javax.annotation.Nonnull;

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

public enum EBorderColorType implements ICSSClassProvider
{
  PRIMARY (CBootstrapCSS.BORDER_PRIMARY),
  SECONDARY (CBootstrapCSS.BORDER_SECONDARY),
  SUCCESS (CBootstrapCSS.BORDER_SUCCESS),
  DANGER (CBootstrapCSS.BORDER_DANGER),
  WARNING (CBootstrapCSS.BORDER_WARNING),
  INFO (CBootstrapCSS.BORDER_INFO),
  LIGHT (CBootstrapCSS.BORDER_LIGHT),
  DARK (CBootstrapCSS.BORDER_DARK),
  WHITE (CBootstrapCSS.BORDER_WHITE);

  private final ICSSClassProvider m_aCSSClass;

  private EBorderColorType (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nonnull
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
