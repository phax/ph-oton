package com.helger.photon.bootstrap4.grid;

import javax.annotation.Nonnull;

public enum EBootstrapEdgeType
{
  ALL (""),
  TOP ("t"),
  RIGHT ("r"),
  BOTTOM ("b"),
  LEFT ("l"),
  X ("x"),
  Y ("y");

  private final String m_sCSSClassNamePart;

  private EBootstrapEdgeType (@Nonnull final String sCSSClassNamePart)
  {
    m_sCSSClassNamePart = sCSSClassNamePart;
  }

  @Nonnull
  public String getCSSClassNamePart ()
  {
    return m_sCSSClassNamePart;
  }
}
