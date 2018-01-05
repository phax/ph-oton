/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.XMLConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsHashSet;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.string.StringHelper;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroElement;

/**
 * ATOM 1.0 feed source.
 *
 * @author Philip Helger
 */
public class FeedSource extends AbstractFeedElement
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (FeedSource.class);

  private final ICommonsList <FeedPerson> m_aAuthors = new CommonsArrayList <> ();
  private final ICommonsList <FeedCategory> m_aCategories = new CommonsArrayList <> ();
  private final ICommonsList <FeedPerson> m_aContributors = new CommonsArrayList <> ();
  private FeedGenerator m_aGenerator;
  private String m_sIcon;
  private String m_sID;
  private final ICommonsList <FeedLink> m_aLinks = new CommonsArrayList <> ();
  private String m_sLogo;
  private IFeedTextConstruct m_aRights;
  private IFeedTextConstruct m_aSubtitle;
  private IFeedTextConstruct m_aTitle;
  private FeedDate m_aUpdated;

  public FeedSource ()
  {}

  public final void addAuthor (@Nonnull final FeedPerson aAuthor)
  {
    ValueEnforcer.notNull (aAuthor, "Author");
    m_aAuthors.add (aAuthor);
  }

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsList <FeedPerson> getAuthors ()
  {
    return m_aAuthors.getClone ();
  }

  public final void addCategory (@Nonnull final FeedCategory aCategory)
  {
    ValueEnforcer.notNull (aCategory, "Category");
    m_aCategories.add (aCategory);
  }

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsList <FeedCategory> getCategories ()
  {
    return m_aCategories.getClone ();
  }

  public final void addContributor (@Nonnull final FeedPerson aContributor)
  {
    ValueEnforcer.notNull (aContributor, "Contributor");
    m_aContributors.add (aContributor);
  }

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsList <FeedPerson> getContributors ()
  {
    return m_aContributors.getClone ();
  }

  public final void setGenerator (@Nullable final FeedGenerator aGenerator)
  {
    m_aGenerator = aGenerator;
  }

  @Nullable
  public final FeedGenerator getGenerator ()
  {
    return m_aGenerator;
  }

  public final void setIcon (@Nullable final String sIcon)
  {
    m_sIcon = sIcon;
  }

  @Nullable
  public final String getIcon ()
  {
    return m_sIcon;
  }

  public final void setID (@Nullable final String sID)
  {
    m_sID = sID;
  }

  @Nullable
  public final String getID ()
  {
    return m_sID;
  }

  public final void addLink (@Nonnull final FeedLink aLink)
  {
    ValueEnforcer.notNull (aLink, "Link");
    m_aLinks.add (aLink);
  }

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsList <FeedLink> getLinks ()
  {
    return m_aLinks.getClone ();
  }

  public final void setLogo (@Nullable final String sLogo)
  {
    m_sLogo = sLogo;
  }

  @Nullable
  public final String getLogo ()
  {
    return m_sLogo;
  }

  public final void setRights (@Nullable final IFeedTextConstruct aRights)
  {
    m_aRights = aRights;
  }

  @Nullable
  public final IFeedTextConstruct getRights ()
  {
    return m_aRights;
  }

  public final void setSubtitle (@Nullable final IFeedTextConstruct aSubtitle)
  {
    m_aSubtitle = aSubtitle;
  }

  @Nullable
  public final IFeedTextConstruct getSubtitle ()
  {
    return m_aSubtitle;
  }

  public final void setTitle (@Nullable final IFeedTextConstruct aTitle)
  {
    m_aTitle = aTitle;
  }

  @Nullable
  public final IFeedTextConstruct getTitle ()
  {
    return m_aTitle;
  }

  public final void setUpdated (@Nullable final FeedDate aUpdated)
  {
    m_aUpdated = aUpdated;
  }

  @Nullable
  public final FeedDate getUpdated ()
  {
    return m_aUpdated;
  }

  protected final void fillElement (@Nonnull final IMicroElement aElement)
  {
    for (final IFeedElement aAuthor : m_aAuthors)
      aElement.appendChild (aAuthor.getAsElement ("author"));
    for (final IFeedElement aCategory : m_aCategories)
      aElement.appendChild (aCategory.getAsElement ("category"));
    for (final IFeedElement aContributor : m_aContributors)
      aElement.appendChild (aContributor.getAsElement ("contributor"));
    if (m_aGenerator != null)
      aElement.appendChild (m_aGenerator.getAsElement ("generator"));
    if (m_sIcon != null)
      aElement.appendElement (CFeed.XMLNS_ATOM, "icon").appendText (m_sIcon);
    if (m_sID != null)
      aElement.appendElement (CFeed.XMLNS_ATOM, "id").appendText (m_sID);
    for (final IFeedElement aLink : m_aLinks)
      aElement.appendChild (aLink.getAsElement ("link"));
    if (m_sLogo != null)
      aElement.appendElement (CFeed.XMLNS_ATOM, "logo").appendText (m_sLogo);
    if (m_aRights != null)
      aElement.appendChild (m_aRights.getAsElement ("rights"));
    if (m_aSubtitle != null)
      aElement.appendChild (m_aSubtitle.getAsElement ("subtitle"));
    if (m_aTitle != null)
      aElement.appendChild (m_aTitle.getAsElement ("title"));
    if (m_aUpdated != null)
      aElement.appendChild (m_aUpdated.getAsElement ("updated"));
    if (StringHelper.hasText (getLanguage ()))
      aElement.setAttribute (XMLConstants.XML_NS_URI, "lang", getLanguage ());
  }

  @Nonnull
  public IMicroElement getAsElement (@Nonnull @Nonempty final String sElementName)
  {
    final IMicroElement aElement = new MicroElement (CFeed.XMLNS_ATOM, sElementName);
    fillElement (aElement);
    return aElement;
  }

  public boolean isValid ()
  {
    // check mandatory fields
    if (m_sID == null)
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
    if (m_aGenerator != null && !m_aGenerator.isValid ())
    {
      s_aLogger.warn ("generator is invalid");
      return false;
    }
    for (final FeedLink aValue : m_aLinks)
      if (!aValue.isValid ())
      {
        s_aLogger.warn ("At least one link is invalid");
        return false;
      }
    if (m_aRights != null && !m_aRights.isValid ())
    {
      s_aLogger.warn ("rights is invalid");
      return false;
    }
    if (m_aSubtitle != null && !m_aSubtitle.isValid ())
    {
      s_aLogger.warn ("subtitle is invalid");
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

    // elements SHOULD contain one atom:link element with a rel attribute value
    // of "self". This is the preferred URI for retrieving Atom Feed Documents
    // representing this Atom feed.
    {
      boolean bFoundSelf = false;
      for (final FeedLink aLink : m_aLinks)
        if (FeedLink.REL_SELF.equals (aLink.getRel ()))
        {
          bFoundSelf = true;
          break;
        }
      if (!bFoundSelf)
        s_aLogger.warn ("no '" + FeedLink.REL_SELF + "' link found!");
    }

    // elements MUST NOT contain more than one atom:link element with a rel
    // attribute value of "alternate" that has the same combination of type and
    // hreflang attribute values.
    {
      final ICommonsSet <String> aUniques = new CommonsHashSet <> ();
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

    return true;
  }
}
