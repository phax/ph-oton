package com.helger.photon.bootstrap4.utils;

import javax.annotation.Nonnull;

public enum EDisplayType
{
  NONE ("none"),
  INLINE ("inline"),
  INLINE_BLOCK ("inline-block"),
  BLOCK ("block"),
  TABLE ("table"),
  TABLE_CELL ("table-cell"),
  FLEX ("flex"),
  INLINE_FLEX ("inline-flex");

  private final String m_sCSSClassNamePart;

  private EDisplayType (@Nonnull final String sCSSClassNamePart)
  {
    m_sCSSClassNamePart = sCSSClassNamePart;
  }

  @Nonnull
  public String getCSSClassNamePart ()
  {
    return m_sCSSClassNamePart;
  }
}
