package com.helger.photon.bootstrap4.utils;

import javax.annotation.Nonnull;

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

public enum ETextColorType implements ICSSClassProvider
{
  PRIMARY (CBootstrapCSS.TEXT_PRIMARY),
  SECONDARY (CBootstrapCSS.TEXT_SECONDARY),
  SUCCESS (CBootstrapCSS.TEXT_SUCCESS),
  DANGER (CBootstrapCSS.TEXT_DANGER),
  WARNING (CBootstrapCSS.TEXT_WARNING),
  INFO (CBootstrapCSS.TEXT_INFO),
  LIGHT (CBootstrapCSS.TEXT_LIGHT),
  DARK (CBootstrapCSS.TEXT_DARK),
  WHITE (CBootstrapCSS.TEXT_WHITE);
  /* Note that the .text-white class has no link styling. */

  private final ICSSClassProvider m_aCSSClass;

  private ETextColorType (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nonnull
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
