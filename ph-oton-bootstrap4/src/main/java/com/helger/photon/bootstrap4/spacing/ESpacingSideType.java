package com.helger.photon.bootstrap4.spacing;

import javax.annotation.Nonnull;

public enum ESpacingSideType
{
  /** All 4 sides */
  ALL (""),
  /** Top only */
  TOP ("t"),
  /** Right only */
  RIGHT ("r")
  /** bottom only */
  ,BOTTOM ("b"),
  /** left only */
  LEFT ("l"),
  /** left and right */
  X ("x"),
  /** top and bottom */
  Y ("y");

  private final String m_sCSSClassNamePart;

  private ESpacingSideType (@Nonnull final String sCSSClassNamePart)
  {
    m_sCSSClassNamePart = sCSSClassNamePart;
  }

  @Nonnull
  public String getCSSClassNamePart ()
  {
    return m_sCSSClassNamePart;
  }
}
