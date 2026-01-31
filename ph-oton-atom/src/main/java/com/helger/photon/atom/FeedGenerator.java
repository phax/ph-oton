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
package com.helger.photon.atom;

import javax.xml.XMLConstants;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.base.string.StringHelper;
import com.helger.text.IHasDescription;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroElement;

/**
 * Represents the generator of a feed.
 *
 * @author Philip Helger
 */
public class FeedGenerator extends AbstractFeedElement implements IHasDescription
{
  private String m_sURI;
  private String m_sVersion;
  private String m_sDescription;

  public FeedGenerator ()
  {}

  public FeedGenerator (@Nullable final String sURI)
  {
    setURI (sURI);
  }

  public void setURI (@Nullable final String sURI)
  {
    m_sURI = sURI;
  }

  /**
   * The atom:generator element MAY have a "uri" attribute whose value MUST be an IRI reference
   * [RFC3987]. When dereferenced, the resulting URI (mapped from an IRI, if necessary) SHOULD
   * produce a representation that is relevant to that agent.
   *
   * @return URI
   */
  @Nullable
  public String getURI ()
  {
    return m_sURI;
  }

  public void setVersion (@Nullable final String sVersion)
  {
    m_sVersion = sVersion;
  }

  /**
   * The atom:generator element MAY have a "version" attribute that indicates the version of the
   * generating agent.
   *
   * @return version
   */
  @Nullable
  public String getVersion ()
  {
    return m_sVersion;
  }

  public void setDescription (@Nullable final String sDescription)
  {
    m_sDescription = sDescription;
  }

  @Nullable
  public String getDescription ()
  {
    return m_sDescription;
  }

  @NonNull
  public IMicroElement getAsElement (final String sElementName)
  {
    final IMicroElement aElement = new MicroElement (CFeed.XMLNS_ATOM, sElementName);
    if (StringHelper.isNotEmpty (m_sURI))
      aElement.setAttribute ("uri", m_sURI);
    if (StringHelper.isNotEmpty (m_sVersion))
      aElement.setAttribute ("version", m_sVersion);
    if (StringHelper.isNotEmpty (getLanguage ()))
      aElement.setAttributeNS (XMLConstants.XML_NS_URI, "lang", getLanguage ());
    if (StringHelper.isNotEmpty (m_sDescription))
      aElement.addText (m_sDescription);
    return aElement;
  }

  public boolean isValid ()
  {
    return true;
  }
}
