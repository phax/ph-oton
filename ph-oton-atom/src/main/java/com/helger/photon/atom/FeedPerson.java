/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.email.EmailAddressHelper;
import com.helger.commons.string.StringHelper;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroElement;

/**
 * Represents a person within a feed.
 *
 * @author Philip Helger
 */
public class FeedPerson extends AbstractFeedElement
{
  private static final Logger LOGGER = LoggerFactory.getLogger (FeedPerson.class);

  private String m_sName;
  private String m_sURI;
  private String m_sEmail;

  public FeedPerson (@Nullable final String sName)
  {
    setName (sName);
  }

  public void setName (@Nullable final String sName)
  {
    m_sName = sName;
  }

  /**
   * The "atom:name" element's content conveys a human-readable name for the
   * person. The content of atom:name is Language-Sensitive. Person constructs
   * MUST contain exactly one "atom:name" element.
   *
   * @return name
   */
  @Nullable
  public String getName ()
  {
    return m_sName;
  }

  public void setURI (@Nullable final String sURI)
  {
    m_sURI = sURI;
  }

  /**
   * The "atom:uri" element's content conveys an IRI associated with the person.
   * Person constructs MAY contain an atom:uri element, but MUST NOT contain
   * more than one. The content of atom:uri in a Person construct MUST be an IRI
   * reference [RFC3987].
   *
   * @return URI
   */
  @Nullable
  public String getURI ()
  {
    return m_sURI;
  }

  public void setEmail (@Nullable final String sEmail)
  {
    m_sEmail = sEmail;
  }

  /**
   * The "atom:email" element's content conveys an e-mail address associated
   * with the person. Person constructs MAY contain an atom:email element, but
   * MUST NOT contain more than one. Its content MUST conform to the "addr-spec"
   * production in [RFC2822].
   *
   * @return email
   */
  @Nullable
  public String getEmail ()
  {
    return m_sEmail;
  }

  public IMicroElement getAsElement (final String sElementName)
  {
    final IMicroElement aElement = new MicroElement (CFeed.XMLNS_ATOM, sElementName);
    if (StringHelper.hasText (m_sName))
      aElement.appendElement (CFeed.XMLNS_ATOM, "name").appendText (m_sName);
    if (StringHelper.hasText (m_sURI))
      aElement.appendElement (CFeed.XMLNS_ATOM, "uri").appendText (m_sURI);
    if (StringHelper.hasText (m_sEmail))
      aElement.appendElement (CFeed.XMLNS_ATOM, "email").appendText (m_sEmail);
    if (StringHelper.hasText (getLanguage ()))
      aElement.setAttribute (XMLConstants.XML_NS_URI, "lang", getLanguage ());
    return aElement;
  }

  public boolean isValid ()
  {
    if (StringHelper.hasNoText (m_sName))
      return false;

    if (StringHelper.hasText (m_sEmail) && !EmailAddressHelper.isValid (m_sEmail))
    {
      LOGGER.warn ("Email address is invalid!");
      return false;
    }
    return true;
  }
}
