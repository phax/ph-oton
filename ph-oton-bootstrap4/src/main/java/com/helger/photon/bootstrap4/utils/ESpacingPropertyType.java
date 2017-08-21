package com.helger.photon.bootstrap4.utils;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;

public enum ESpacingPropertyType
{
  MARGIN ("m"),
  PADDING ("p");

  private final String m_sCSSClassNamePart;

  private ESpacingPropertyType (@Nonnull @Nonempty final String sCSSClassNamePart)
  {
    m_sCSSClassNamePart = sCSSClassNamePart;
  }

  @Nonnull
  @Nonempty
  public String getCSSClassNamePart ()
  {
    return m_sCSSClassNamePart;
  }
}
