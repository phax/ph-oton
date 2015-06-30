/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.atom;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.XMLConstants;

import com.helger.commons.CGlobal;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.MicroElement;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.ISimpleURL;

/**
 * Represents a link in the ATOM feed
 *
 * @author Philip Helger
 */
public class FeedLink extends AbstractFeedElement
{
  public static final String REL_SELF = "self";
  public static final String REL_ALTERNATE = "alternate";

  private String m_sHref;
  private String m_sRel;
  private String m_sType; // MIME type
  private Locale m_aHrefLang;
  private String m_sTitle;
  private int m_nLength = CGlobal.ILLEGAL_UINT;

  public FeedLink (@Nonnull final ISimpleURL aHref)
  {
    this (aHref.getAsString ());
  }

  public FeedLink (@Nonnull final ISimpleURL aHref, @Nullable final String sRel)
  {
    this (aHref.getAsString (), sRel);
  }

  public FeedLink (@Nullable final String sHref)
  {
    setHref (sHref);
  }

  public FeedLink (@Nullable final String sHref, @Nullable final String sRel)
  {
    this (sHref);
    setRel (sRel);
  }

  public void setHref (@Nullable final String sHref)
  {
    m_sHref = sHref;
  }

  /**
   * The "href" attribute contains the link's IRI. atom:link elements MUST have
   * an href attribute, whose value MUST be a IRI reference [RFC3987].
   *
   * @return href
   */
  @Nullable
  public String getHref ()
  {
    return m_sHref;
  }

  public void setRel (@Nullable final String sRel)
  {
    m_sRel = sRel;
  }

  /**
   * <p>
   * atom:link elements MAY have a "rel" attribute that indicates the link
   * relation type. If the "rel" attribute is not present, the link element MUST
   * be interpreted as if the link relation type is "alternate".
   * </p>
   * <p>
   * The value of "rel" MUST be a string that is non-empty and matches either
   * the "isegment-nz-nc" or the "IRI" production in [RFC3987]. Note that use of
   * a relative reference other than a simple name is not allowed. If a name is
   * given, implementations MUST consider the link relation type equivalent to
   * the same name registered within the IANA Registry of Link Relations
   * (Section 7), and thus to the IRI that would be obtained by appending the
   * value of the rel attribute to the string
   * "http://www.iana.org/assignments/relation/". The value of "rel" describes
   * the meaning of the link, but does not impose any behavioral requirements on
   * Atom Processors.
   * </p>
   * <p>
   * This document defines five initial values for the Registry of Link
   * Relations:
   * </p>
   * <ol>
   * <li>The value "alternate" signifies that the IRI in the value of the href
   * attribute identifies an alternate version of the resource described by the
   * containing element.</li>
   * <li>The value "related" signifies that the IRI in the value of the href
   * attribute identifies a resource related to the resource described by the
   * containing element. For example, the feed for a site that discusses the
   * performance of the search engine at "http://search.example.com" might
   * contain, as a child of atom:feed:
   *
   * <pre>
   * &lt;link rel="related" href="http://search.example.com/"/&gt;
   * </pre>
   *
   * An identical link might appear as a child of any atom:entry whose content
   * contains a discussion of that same search engine.</li>
   * <li>The value "self" signifies that the IRI in the value of the href
   * attribute identifies a resource equivalent to the containing element.</li>
   * <li>The value "enclosure" signifies that the IRI in the value of the href
   * attribute identifies a related resource that is potentially large in size
   * and might require special handling. For atom:link elements with
   * rel="enclosure", the length attribute SHOULD be provided.</li>
   * <li>The value "via" signifies that the IRI in the value of the href
   * attribute identifies a resource that is the source of the information
   * provided in the containing element.</li>
   * </ol>
   *
   * @return rel
   */
  @Nullable
  public String getRel ()
  {
    return m_sRel;
  }

  public void setType (@Nullable final String sType)
  {
    m_sType = sType;
  }

  /**
   * On the link element, the "type" attribute's value is an advisory media
   * type: it is a hint about the type of the representation that is expected to
   * be returned when the value of the href attribute is dereferenced. Note that
   * the type attribute does not override the actual media type returned with
   * the representation. Link elements MAY have a type attribute, whose value
   * MUST conform to the syntax of a MIME media type [MIMEREG].
   *
   * @return type
   */
  @Nullable
  public String getType ()
  {
    return m_sType;
  }

  public void setHrefLang (@Nullable final Locale aContentLocale)
  {
    m_aHrefLang = aContentLocale;
  }

  /**
   * The "hreflang" attribute's content describes the language of the resource
   * pointed to by the href attribute. When used together with the
   * rel="alternate", it implies a translated version of the entry. Link
   * elements MAY have an hreflang attribute, whose value MUST be a language tag
   * [RFC3066].
   *
   * @return href language
   */
  @Nullable
  public Locale getHrefLang ()
  {
    return m_aHrefLang;
  }

  public void setTitle (@Nullable final String sTitle)
  {
    m_sTitle = sTitle;
  }

  /**
   * The "title" attribute conveys human-readable information about the link.
   * The content of the "title" attribute is Language-Sensitive. Entities such
   * as "&amp;amp;" and "&amp;lt;" represent their corresponding characters
   * ("&amp;" and "&lt;", respectively), not markup. Link elements MAY have a
   * title attribute.
   *
   * @return title
   */
  @Nullable
  public String getTitle ()
  {
    return m_sTitle;
  }

  public void setLength (final int nLength)
  {
    m_nLength = nLength;
  }

  /**
   * The "length" attribute indicates an advisory length of the linked content
   * in octets; it is a hint about the content length of the representation
   * returned when the IRI in the href attribute is mapped to a URI and
   * dereferenced. Note that the length attribute does not override the actual
   * content length of the representation as reported by the underlying
   * protocol. Link elements MAY have a length attribute.
   *
   * @return length
   */
  public int getLength ()
  {
    return m_nLength;
  }

  @Nonnull
  public IMicroElement getAsElement (@Nonnull final String sElementName)
  {
    final IMicroElement aElement = new MicroElement (CFeed.XMLNS_ATOM, sElementName);
    if (StringHelper.hasText (m_sHref))
      aElement.setAttribute ("href", m_sHref);
    if (StringHelper.hasText (m_sRel))
      aElement.setAttribute ("rel", m_sRel);
    if (StringHelper.hasText (m_sType))
      aElement.setAttribute ("type", m_sType);
    if (m_aHrefLang != null)
      aElement.setAttribute ("hreflang", m_aHrefLang.toString ());
    if (StringHelper.hasText (m_sTitle))
      aElement.setAttribute ("title", m_sTitle);
    if (m_nLength > 0)
      aElement.setAttribute ("length", Integer.toString (m_nLength));
    if (StringHelper.hasText (getLanguage ()))
      aElement.setAttribute (XMLConstants.XML_NS_URI, "lang", getLanguage ());
    return aElement;
  }

  public boolean isValid ()
  {
    return StringHelper.hasText (m_sHref);
  }
}
