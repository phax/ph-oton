package com.helger.photon.uictrls.prism;

import javax.annotation.Nonnull;

import com.helger.commons.annotations.Nonempty;
import com.helger.html.css.ICSSClassProvider;

public enum EPrismLanguage implements ICSSClassProvider
{
  NONE ("language-none"),
  APACHECONF ("language-apacheconf"),
  C ("language-c"),
  CLIKE ("language-clike"),
  CPP ("language-cpp"),
  CSHARP ("language-csharp"),
  CSS ("language-css"),
  HTTP ("language-http"),
  INI ("language-ini"),
  JAVA ("language-java"),
  JAVASCRIPT ("language-javascript"),
  LATEX ("language-latex"),
  LESS ("language-less"),
  MARKDOWN ("language-markdown"),
  MARKUP ("language-markup"),
  PHP ("language-php"),
  SCSS ("language-scss"),
  SQL ("language-sql"),
  WIKI ("language-wiki");

  private final String m_sCSSClass;

  private EPrismLanguage (@Nonnull @Nonempty final String sCSSClass)
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
