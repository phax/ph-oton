/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroDocument;

/**
 * Main ATOM 1.0 Feed
 *
 * @author Philip Helger
 */
public class Feed extends FeedSource
{
  private static final Logger LOGGER = LoggerFactory.getLogger (Feed.class);

  private final ICommonsList <FeedEntry> m_aEntries = new CommonsArrayList <> ();

  public Feed ()
  {}

  @Nonnull
  public Feed addEntry (@Nonnull final FeedEntry aEntry)
  {
    ValueEnforcer.notNull (aEntry, "Entry");
    m_aEntries.add (aEntry);
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <FeedEntry> getAllEntries ()
  {
    return m_aEntries.getClone ();
  }

  @Nonnegative
  public int getEntryCount ()
  {
    return m_aEntries.size ();
  }

  @Nonnull
  public IMicroDocument getAsDocument ()
  {
    final IMicroDocument aDocument = new MicroDocument ();
    final IMicroElement aElement = aDocument.appendElement (CFeed.XMLNS_ATOM, "feed");
    super.fillElement (aElement);
    for (final IFeedElement aEntry : m_aEntries)
      aElement.appendChild (aEntry.getAsElement ("entry"));
    return aDocument;
  }

  @Override
  public IMicroElement getAsElement (final String sElementName)
  {
    final IMicroElement aElement = super.getAsElement (sElementName);
    for (final IFeedElement aEntry : m_aEntries)
      aElement.appendChild (aEntry.getAsElement ("entry"));
    return aElement;
  }

  @Override
  public boolean isValid ()
  {
    if (!super.isValid ())
      return false;

    // check nested fields
    for (final FeedEntry aValue : m_aEntries)
      if (!aValue.isValid ())
      {
        LOGGER.warn ("At least one entry is invalid");
        return false;
      }

    // atom:feed elements MUST contain one or more atom:author elements, unless
    // all of the atom:feed element's child atom:entry elements contain at least
    // one atom:author element.
    if (getAuthors ().isEmpty ())
      for (final FeedEntry aEntry : m_aEntries)
        if (aEntry.getAllAuthors ().isEmpty ())
        {
          LOGGER.warn ("Both the feed author field and at least one entry author field is empty");
          return false;
        }

    return true;
  }
}
