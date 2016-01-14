/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.XMLConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.MicroElement;
import com.helger.commons.mime.EMimeContentType;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.ISimpleURL;

/**
 * Represents a single entry within an ATOM 1.0 feed.<br>
 * Specs: http://atompub.org/rfc4287.html#element.entry
 *
 * @author Philip Helger
 */
public class FeedEntry extends AbstractFeedElement
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (FeedEntry.class);

  private final List <FeedPerson> m_aAuthors = new ArrayList <FeedPerson> ();
  private final List <FeedCategory> m_aCategories = new ArrayList <FeedCategory> ();
  private IFeedContent m_aContent;
  private final List <FeedPerson> m_aContributors = new ArrayList <FeedPerson> ();
  private ISimpleURL m_aID;
  private final List <FeedLink> m_aLinks = new ArrayList <FeedLink> ();
  private FeedDate m_aPublished;
  private IFeedTextConstruct m_aRights;
  private FeedSource m_aSource;
  private IFeedTextConstruct m_aSummary;
  private IFeedTextConstruct m_aTitle;
  private FeedDate m_aUpdated;

  public FeedEntry ()
  {}

  public void addAuthor (@Nonnull final FeedPerson aAuthor)
  {
    ValueEnforcer.notNull (aAuthor, "Author");
    m_aAuthors.add (aAuthor);
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <FeedPerson> getAllAuthors ()
  {
    return CollectionHelper.newList (m_aAuthors);
  }

  public void addCategory (@Nonnull final FeedCategory aCategory)
  {
    ValueEnforcer.notNull (aCategory, "Category");
    m_aCategories.add (aCategory);
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <FeedCategory> getAllCategories ()
  {
    return CollectionHelper.newList (m_aCategories);
  }

  public void setContent (@Nullable final IFeedContent aContent)
  {
    m_aContent = aContent;
  }

  @Nullable
  public IFeedContent getContent ()
  {
    return m_aContent;
  }

  public void addContributor (@Nonnull final FeedPerson aContributor)
  {
    ValueEnforcer.notNull (aContributor, "Contributor");
    m_aContributors.add (aContributor);
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <FeedPerson> getAllContributors ()
  {
    return CollectionHelper.newList (m_aContributors);
  }

  public void setID (@Nullable final ISimpleURL aID)
  {
    m_aID = aID;
  }

  @Nullable
  public ISimpleURL getID ()
  {
    return m_aID;
  }

  public void addLink (@Nonnull final FeedLink aLink)
  {
    ValueEnforcer.notNull (aLink, "Link");
    m_aLinks.add (aLink);
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <FeedLink> getAllLinks ()
  {
    return CollectionHelper.newList (m_aLinks);
  }

  public void setPublished (@Nullable final FeedDate aPublished)
  {
    m_aPublished = aPublished;
  }

  @Nullable
  public FeedDate getPublished ()
  {
    return m_aPublished;
  }

  public void setRights (@Nullable final IFeedTextConstruct aRights)
  {
    m_aRights = aRights;
  }

  @Nullable
  public IFeedTextConstruct getRights ()
  {
    return m_aRights;
  }

  public void setSource (@Nullable final FeedSource aSource)
  {
    m_aSource = aSource;
  }

  @Nullable
  public FeedSource getSource ()
  {
    return m_aSource;
  }

  public void setSummary (@Nullable final IFeedTextConstruct aSummary)
  {
    m_aSummary = aSummary;
  }

  @Nullable
  public IFeedTextConstruct getSummary ()
  {
    return m_aSummary;
  }

  public void setTitle (@Nullable final IFeedTextConstruct aTitle)
  {
    m_aTitle = aTitle;
  }

  @Nullable
  public IFeedTextConstruct getTitle ()
  {
    return m_aTitle;
  }

  public void setUpdated (@Nullable final FeedDate aUpdated)
  {
    m_aUpdated = aUpdated;
  }

  @Nullable
  public FeedDate getUpdated ()
  {
    return m_aUpdated;
  }

  public IMicroElement getAsElement (final String sElementName)
  {
    final IMicroElement aElement = new MicroElement (CFeed.XMLNS_ATOM, sElementName);
    for (final IFeedElement aAuthor : m_aAuthors)
      aElement.appendChild (aAuthor.getAsElement ("author"));
    for (final IFeedElement aCategory : m_aCategories)
      aElement.appendChild (aCategory.getAsElement ("category"));
    if (m_aContent != null)
      aElement.appendChild (m_aContent.getAsElement ("content"));
    for (final IFeedElement aContributor : m_aContributors)
      aElement.appendChild (aContributor.getAsElement ("contributor"));
    if (m_aID != null)
      aElement.appendElement (CFeed.XMLNS_ATOM, "id").appendText (m_aID.getAsString ());
    for (final IFeedElement aLink : m_aLinks)
      aElement.appendChild (aLink.getAsElement ("link"));
    if (m_aPublished != null)
      aElement.appendChild (m_aPublished.getAsElement ("published"));
    if (m_aRights != null)
      aElement.appendChild (m_aRights.getAsElement ("rights"));
    if (m_aSource != null)
      aElement.appendChild (m_aSource.getAsElement ("source"));
    if (m_aSummary != null)
      aElement.appendChild (m_aSummary.getAsElement ("summary"));
    if (m_aTitle != null)
      aElement.appendChild (m_aTitle.getAsElement ("title"));
    if (m_aUpdated != null)
      aElement.appendChild (m_aUpdated.getAsElement ("updated"));
    if (StringHelper.hasText (getLanguage ()))
      aElement.setAttribute (XMLConstants.XML_NS_URI, "lang", getLanguage ());
    return aElement;
  }

  public boolean isValid ()
  {
    // check mandatory fields
    if (m_aID == null)
    {
      s_aLogger.warn ("Required field 'id' is not set!");
      return false;
    }
    if (m_aTitle == null)
    {
      s_aLogger.warn ("Required field 'title' is not set!");
      return false;
    }
    if (m_aUpdated == null)
    {
      s_aLogger.warn ("Required field 'updated' is not set!");
      return false;
    }

    // check nested fields
    for (final FeedPerson aValue : m_aAuthors)
      if (!aValue.isValid ())
      {
        s_aLogger.warn ("At least one author is invalid");
        return false;
      }
    if (m_aContent != null && !m_aContent.isValid ())
    {
      s_aLogger.warn ("content is invalid");
      return false;
    }
    for (final FeedCategory aValue : m_aCategories)
      if (!aValue.isValid ())
      {
        s_aLogger.warn ("At least one category is invalid");
        return false;
      }
    for (final FeedPerson aValue : m_aContributors)
      if (!aValue.isValid ())
      {
        s_aLogger.warn ("At least one constributor is invalid");
        return false;
      }
    for (final FeedLink aValue : m_aLinks)
      if (!aValue.isValid ())
      {
        s_aLogger.warn ("At least one link is invalid");
        return false;
      }
    if (m_aPublished != null && !m_aPublished.isValid ())
    {
      s_aLogger.warn ("published is invalid");
      return false;
    }
    if (m_aRights != null && !m_aRights.isValid ())
    {
      s_aLogger.warn ("rights is invalid");
      return false;
    }
    if (m_aSource != null && !m_aSource.isValid ())
    {
      s_aLogger.warn ("source is invalid");
      return false;
    }
    if (m_aSummary != null && !m_aSummary.isValid ())
    {
      s_aLogger.warn ("summary is invalid");
      return false;
    }
    if (!m_aTitle.isValid ())
    {
      s_aLogger.warn ("title is invalid");
      return false;
    }
    if (!m_aUpdated.isValid ())
    {
      s_aLogger.warn ("updated is invalid");
      return false;
    }

    // elements that contain no child atom:content element MUST contain at least
    // one atom:link element with a rel attribute value of "alternate".
    if (m_aContent == null)
    {
      boolean bFoundAlternate = false;
      for (final FeedLink aLink : m_aLinks)
        if ("alternate".equals (aLink.getRel ()))
        {
          bFoundAlternate = true;
          break;
        }
      if (!bFoundAlternate)
      {
        s_aLogger.warn ("neither content nor alternate link found!");
        return false;
      }
    }

    // elements MUST NOT contain more than one atom:link element with a rel
    // attribute value of "alternate" that has the same combination of type and
    // hreflang attribute values.
    {
      final Set <String> aUniques = new HashSet <String> ();
      for (final FeedLink aLink : m_aLinks)
        if (FeedLink.REL_ALTERNATE.equals (aLink.getRel ()))
        {
          final String sKey = aLink.getType () +
                              ":" +
                              (aLink.getHrefLang () == null ? "" : aLink.getHrefLang ().toString ());
          if (!aUniques.add (sKey))
          {
            s_aLogger.warn ("'" + FeedLink.REL_ALTERNATE + "' link is not unique: " + aLink);
            return false;
          }
        }
    }

    // elements MUST contain an atom:summary element in either of the following
    // cases:
    // 1. the atom:entry contains an atom:content that has a "src" attribute
    // (and is thus empty).
    // 2. the atom:entry contains content that is encoded in Base64; i.e., the
    // "type" attribute of atom:content is a MIME media type [MIMEREG], but is
    // not an XML media type [RFC3023], does not begin with "text/", and does
    // not end with "/xml" or "+xml".
    if (m_aContent != null && m_aContent instanceof FeedOutOfLineContent)
      if (m_aSummary == null)
      {
        s_aLogger.warn ("Summary is required for out of line content!");
        return false;
      }

    if (m_aContent != null && m_aContent instanceof FeedOtherContent)
    {
      final String sType = m_aContent.getType ();
      if (!EMimeContentType.TEXT.isTypeOf (sType) && !sType.endsWith ("/xml") && !sType.endsWith ("+xml"))
        if (m_aSummary == null)
        {
          s_aLogger.warn ("Summary is required for other content!");
          return false;
        }
    }

    return true;
  }
}
