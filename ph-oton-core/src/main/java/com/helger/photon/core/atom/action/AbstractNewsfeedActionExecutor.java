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
package com.helger.photon.core.atom.action;

import java.util.Locale;

import javax.annotation.Nonnull;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.id.IHasID;
import com.helger.commons.microdom.serialize.MicroWriter;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.stats.IStatisticsHandlerKeyedCounter;
import com.helger.commons.stats.StatisticsManager;
import com.helger.commons.timing.StopWatch;
import com.helger.commons.xml.serialize.XMLWriterSettings;
import com.helger.photon.basic.atom.Feed;
import com.helger.photon.basic.atom.FeedGenerator;
import com.helger.photon.basic.atom.FeedLink;
import com.helger.photon.core.action.executor.AbstractActionExecutor;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.response.UnifiedResponse;

/**
 * Abstract news feed action.
 *
 * @author Philip Helger
 */
public abstract class AbstractNewsfeedActionExecutor extends AbstractActionExecutor implements IHasID <String>, IHasDisplayText
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractNewsfeedActionExecutor.class);
  private static final IStatisticsHandlerKeyedCounter s_aStatsHdlExecute = StatisticsManager.getKeyedCounterHandler (AbstractNewsfeedActionExecutor.class.getName () +
                                                                                                                     "$EXECUTE");
  private static final IStatisticsHandlerKeyedCounter s_aStatsHdlError = StatisticsManager.getKeyedCounterHandler (AbstractNewsfeedActionExecutor.class.getName () +
                                                                                                                   "$ERROR");

  private final IHasDisplayText m_aDisplayText;
  private final String m_sFeedID;

  public AbstractNewsfeedActionExecutor (@Nonnull final IHasDisplayText aDisplayText,
                                         @Nonnull @Nonempty final String sFeedID)
  {
    ValueEnforcer.notNull (aDisplayText, "DisplayText");
    ValueEnforcer.notNull (sFeedID, "FeedID");
    m_aDisplayText = aDisplayText;
    m_sFeedID = sFeedID;
  }

  @Nonnull
  @Nonempty
  public final String getID ()
  {
    return m_sFeedID;
  }

  public final String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return m_aDisplayText.getDisplayText (aContentLocale);
  }

  @Nonnull
  @OverrideOnDemand
  protected String getFeedDescription ()
  {
    return "ph-oton-core";
  }

  protected abstract void fillNewsfeed (@Nonnull Feed aFeed);

  public final void execute (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull final UnifiedResponse aUnifiedResponse) throws Exception
  {
    // Increment statistics counter
    final StopWatch aSW = new StopWatch (true);
    s_aStatsHdlExecute.increment (m_sFeedID);

    final Feed aFeed = new Feed ();
    aFeed.setID ("urn:ph-oton-core:newsfeed:" + m_sFeedID);

    final FeedGenerator aGenerator = new FeedGenerator ("urn:ph-oton-core");
    aGenerator.setDescription (getFeedDescription ());
    aFeed.setGenerator (aGenerator);
    aFeed.addLink (new FeedLink (aRequestScope.getFullContextAndServletPath () + m_sFeedID, FeedLink.REL_SELF));

    // Abstract filling
    fillNewsfeed (aFeed);

    if (!aFeed.isValid ())
    {
      s_aLogger.error ("Created newsfeed with ID '" + m_sFeedID + "' is invalid!");
      s_aStatsHdlError.increment (m_sFeedID);
    }

    // Performance improvement: set the Last-Modified HTTP header if available
    if (aFeed.getUpdated () != null)
    {
      final LocalDateTime aLDT = aFeed.getUpdated ().getDateTime ();
      if (aLDT != null)
        aUnifiedResponse.setLastModified (aLDT.toDateTime (DateTimeZone.UTC));
    }

    final String sXML = MicroWriter.getXMLString (aFeed.getAsDocument ());
    aUnifiedResponse.setContentAndCharset (sXML, XMLWriterSettings.DEFAULT_XML_CHARSET_OBJ)
                    .setMimeType (CMimeType.APPLICATION_ATOM_XML);
    StatisticsManager.getTimerHandler (AbstractNewsfeedActionExecutor.class.getName () + "$TIMER." + m_sFeedID)
                     .addTime (aSW.stopAndGetMillis ());
  }
}
