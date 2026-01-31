/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.photon.uictrls.prism;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.Nonempty;
import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.core.configfile.EConfigurationFileSyntax;

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
  REGEX ("language-regex"),
  SCSS ("language-scss"),
  SQL ("language-sql"),
  WIKI ("language-wiki"),
  YAML ("language-yaml");

  private final String m_sCSSClass;

  EPrismLanguage (@NonNull @Nonempty final String sCSSClass)
  {
    m_sCSSClass = sCSSClass;
  }

  @NonNull
  @Nonempty
  public String getCSSClass ()
  {
    return m_sCSSClass;
  }

  @NonNull
  public static EPrismLanguage find (@NonNull final EConfigurationFileSyntax eSyntaxHighlightLanguage)
  {
    switch (eSyntaxHighlightLanguage)
    {
      case NONE:
        return NONE;
      case PROPERTIES:
        return APACHECONF;
      case XML:
        return MARKUP;
      case JSON:
        return JAVASCRIPT;
    }
    // Default fallback
    return EPrismLanguage.NONE;
  }
}
