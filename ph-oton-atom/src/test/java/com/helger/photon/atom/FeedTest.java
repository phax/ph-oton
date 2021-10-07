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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.html.hc.html.grouping.HCDiv;

public final class FeedTest
{
  private Feed _createSimpleFeed (final int nEntryCount)
  {
    final Feed aFeed = new Feed ();
    aFeed.setID (Integer.toString (new Random ().nextInt ()));
    aFeed.setTitle (new FeedPlainTextConstruct ("Title of the feed"));
    aFeed.setUpdated (FeedDate.createNow ());
    aFeed.addLink (new FeedLink ("http://localhost/feedtest", FeedLink.REL_SELF));
    aFeed.addAuthor (new FeedPerson (FeedTest.class.getName ()));
    for (int i = 0; i < nEntryCount; ++i)
    {
      final ISimpleURL aURL = new SimpleURL ("http://localhost/feeddetails/" + i);
      final FeedEntry aEntry = new FeedEntry ();
      aEntry.setID (aURL);
      aEntry.setTitle (new FeedPlainTextConstruct ("Title " + i));
      aEntry.setUpdated (FeedDate.createNow ());
      aEntry.addLink (new FeedLink (aURL, FeedLink.REL_ALTERNATE));
      aFeed.addEntry (aEntry);
    }
    return aFeed;
  }

  @Test
  public void testBasic ()
  {
    for (int i = 0; i < 10; ++i)
    {
      final Feed aFeed = _createSimpleFeed (i);
      assertTrue (aFeed.isValid ());
      assertNotNull (aFeed.getAsDocument ());
    }
  }

  @Test
  public void testEntryContentXHTML ()
  {
    final Feed aFeed = _createSimpleFeed (5);
    assertTrue (aFeed.isValid ());
    final FeedEntry aEntry = aFeed.getAllEntries ().get (4);
    aEntry.setContent (new FeedXHTMLContent (new HCDiv ().addChild ("Super!")));
    assertNotNull (aFeed.getAsDocument ());
  }
}
