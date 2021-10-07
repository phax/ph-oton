/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.atom;

import javax.annotation.Nullable;
import javax.xml.XMLConstants;

import com.helger.commons.string.StringHelper;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroElement;

public class FeedCategory extends AbstractFeedElement
{
  private String m_sTerm;
  private String m_sScheme;
  private String m_sLabel;

  public FeedCategory (@Nullable final String sTerm)
  {
    setTerm (sTerm);
  }

  public void setTerm (@Nullable final String sTerm)
  {
    m_sTerm = sTerm;
  }

  /**
   * The "term" attribute is a string that identifies the category to which the
   * entry or feed belongs. Category elements MUST have a "term" attribute.
   *
   * @return term
   */
  @Nullable
  public String getTerm ()
  {
    return m_sTerm;
  }

  public void setScheme (@Nullable final String sScheme)
  {
    m_sScheme = sScheme;
  }

  /**
   * The "scheme" attribute is an IRI that identifies a categorization scheme.
   * Category elements MAY have a "scheme" attribute.
   *
   * @return scheme
   */
  @Nullable
  public String getScheme ()
  {
    return m_sScheme;
  }

  public void setLabel (@Nullable final String sLabel)
  {
    m_sLabel = sLabel;
  }

  /**
   * The "label" attribute provides a human-readable label for display in
   * end-user applications. The content of the "label" attribute is
   * Language-Sensitive. Entities such as "&amp;amp;" and "&amp;lt;" represent
   * their corresponding characters ("&amp;" and "&lt;", respectively), not
   * markup. Category elements MAY have a "label" attribute.
   *
   * @return type
   */
  @Nullable
  public String getLabel ()
  {
    return m_sLabel;
  }

  public IMicroElement getAsElement (final String sElementName)
  {
    final IMicroElement aElement = new MicroElement (CFeed.XMLNS_ATOM, sElementName);
    if (StringHelper.hasText (m_sTerm))
      aElement.setAttribute ("term", m_sTerm);
    if (StringHelper.hasText (m_sScheme))
      aElement.setAttribute ("scheme", m_sScheme);
    if (StringHelper.hasText (m_sLabel))
      aElement.setAttribute ("label", m_sLabel);
    if (StringHelper.hasText (getLanguage ()))
      aElement.setAttribute (XMLConstants.XML_NS_URI, "lang", getLanguage ());
    return aElement;
  }

  public boolean isValid ()
  {
    return StringHelper.hasText (m_sTerm);
  }
}
