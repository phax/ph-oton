package com.helger.photon.bootstrap4.utils;

import javax.annotation.Nonnull;

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Print display class enum
 * 
 * @author Philip Helger
 */
public enum EDisplayPrintType implements ICSSClassProvider
{
  BLOCK (CBootstrapCSS.D_PRINT_BLOCK),
  INLINE (CBootstrapCSS.D_PRINT_INLINE),
  INLINE_BLOCK (CBootstrapCSS.D_PRINT_INLINE_BLOCK),
  NONE (CBootstrapCSS.D_PRINT_NONE);

  private final ICSSClassProvider m_aCSSClass;

  private EDisplayPrintType (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nonnull
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
