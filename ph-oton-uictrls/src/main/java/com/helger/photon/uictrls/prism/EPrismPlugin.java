package com.helger.photon.uictrls.prism;

import javax.annotation.Nonnull;

import com.helger.commons.annotations.Nonempty;
import com.helger.html.css.ICSSClassProvider;

public enum EPrismPlugin implements ICSSClassProvider
{
  AUTOLINKER ("autolinker"),
  FILE_HIGHLIGHT ("file-highlight"),
  HIGHLIGHT_KEYWORDS ("highlight-keywords"),
  LINE_HIGHLIGHT ("line-highlight"),
  LINE_NUMBERS ("line-numbers"),
  SHOW_INVISIBLES ("show-invisibles"),
  SHOW_LANGUAGE ("show-language"),
  WPD ("wpd");

  private final String m_sCSSClass;

  private EPrismPlugin (@Nonnull @Nonempty final String sCSSClass)
  {
    m_sCSSClass = sCSSClass;
  }

  @Nonnull
  @Nonempty
  public String getCSSClass ()
  {
    return m_sCSSClass;
  }
}
