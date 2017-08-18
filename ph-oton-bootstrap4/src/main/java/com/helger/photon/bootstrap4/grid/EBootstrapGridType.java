package com.helger.photon.bootstrap4.grid;

import javax.annotation.Nonnull;

public enum EBootstrapGridType
{
  XS (""),
  SM ("-sm"),
  MD ("-md"),
  LG ("-lg"),
  XL ("-xl");

  private final String m_sCSSClassNamePart;

  private EBootstrapGridType (@Nonnull final String sCSSClassNamePart)
  {
    m_sCSSClassNamePart = sCSSClassNamePart;
  }

  @Nonnull
  public String getCSSClassNamePart ()
  {
    return m_sCSSClassNamePart;
  }
}
