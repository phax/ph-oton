package com.helger.photon.bootstrap4.utils;

import javax.annotation.Nonnull;

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

public enum EBackgroundType implements ICSSClassProvider
{
  PRIMARY (CBootstrapCSS.BG_PRIMARY),
  SECONDARY (CBootstrapCSS.BG_SECONDARY),
  SUCCESS (CBootstrapCSS.BG_SUCCESS),
  DANGER (CBootstrapCSS.BG_DANGER),
  WARNING (CBootstrapCSS.BG_WARNING),
  INFO (CBootstrapCSS.BG_INFO),
  LIGHT (CBootstrapCSS.BG_LIGHT),
  DARK (CBootstrapCSS.BG_DARK),
  WHITE (CBootstrapCSS.BG_WHITE);

  private final ICSSClassProvider m_aCSSClass;

  private EBackgroundType (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nonnull
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
