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
package com.helger.photon.core.atom.ajax;

import java.time.LocalDateTime;
import java.util.Locale;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.id.IHasID;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.statistics.IMutableStatisticsHandlerKeyedCounter;
import com.helger.commons.statistics.StatisticsManager;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.timing.StopWatch;
import com.helger.photon.atom.Feed;
import com.helger.photon.atom.FeedGenerator;
import com.helger.photon.atom.FeedLink;
import com.helger.photon.core.PhotonUnifiedResponse;
import com.helger.photon.core.ajax.executor.IAjaxExecutor;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Abstract news feed action.
 *
 * @author Philip Helger
 */
public abstract class AbstractNewsfeedAjaxExecutor implements IAjaxExecutor, IHasID <String>, IHasDisplayText
{
  private static final Logger LOGGER = LoggerFactory.getLogger (AbstractNewsfeedAjaxExecutor.class);
  private static final IMutableStatisticsHandlerKeyedCounter s_aStatsHdlExecute = StatisticsManager.getKeyedCounterHandler (AbstractNewsfeedAjaxExecutor.class.getName () +
                                                                                                                            "$EXECUTE");
  private static final IMutableStatisticsHandlerKeyedCounter s_aStatsHdlError = StatisticsManager.getKeyedCounterHandler (AbstractNewsfeedAjaxExecutor.class.getName () +
                                                                                                                          "$ERROR");

  private final IHasDisplayText m_aDisplayText;
  private final String m_sFeedID;

  public AbstractNewsfeedAjaxExecutor (@Nonnull final IHasDisplayText aDisplayText,
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

  @Override
  public void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull final PhotonUnifiedResponse aAjaxResponse) throws Exception
  {
    // Increment statistics counter
    final StopWatch aSW = StopWatch.createdStarted ();
    s_aStatsHdlExecute.increment (m_sFeedID);

    final Feed aFeed = new Feed ();
    aFeed.setID ("urn:ph-oton-core:newsfeed:" + m_sFeedID);

    final FeedGenerator aGenerator = new FeedGenerator ("urn:ph-oton-core");
    aGenerator.setDescription (getFeedDescription ());
    aFeed.setGenerator (aGenerator);
    // Using the full context and servlet path is OK here
    aFeed.addLink (new FeedLink (aRequestScope.getFullContextAndServletPath () + m_sFeedID, FeedLink.REL_SELF));

    // Abstract filling
    fillNewsfeed (aFeed);

    if (!aFeed.isValid ())
    {
      LOGGER.error ("Created newsfeed with ID '" + m_sFeedID + "' is invalid!");
      s_aStatsHdlError.increment (m_sFeedID);
    }

    // Performance improvement: set the Last-Modified HTTP header if available
    final LocalDateTime aLDT = aFeed.getUpdated () != null ? aFeed.getUpdated ().getDateTime () : null;

    StatisticsManager.getTimerHandler (AbstractNewsfeedAjaxExecutor.class.getName () + "$TIMER." + m_sFeedID)
                     .addTime (aSW.stopAndGetMillis ());

    aAjaxResponse.xml (aFeed.getAsDocument ());
    aAjaxResponse.setMimeType (CMimeType.APPLICATION_ATOM_XML);
    if (aLDT != null)
      aAjaxResponse.setLastModified (aLDT);
  }
}
