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
package com.helger.photon.core.ajax.atom;

import java.time.LocalDateTime;
import java.util.Locale;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.annotation.style.OverrideOnDemand;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.id.IHasID;
import com.helger.base.timing.StopWatch;
import com.helger.mime.CMimeType;
import com.helger.photon.ajax.executor.IAjaxExecutor;
import com.helger.photon.app.PhotonUnifiedResponse;
import com.helger.photon.atom.Feed;
import com.helger.photon.atom.FeedGenerator;
import com.helger.photon.atom.FeedLink;
import com.helger.statistics.api.IMutableStatisticsHandlerKeyedCounter;
import com.helger.statistics.impl.StatisticsManager;
import com.helger.text.display.IHasDisplayText;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Abstract news feed action.
 *
 * @author Philip Helger
 */
public abstract class AbstractNewsfeedAjaxExecutor implements IAjaxExecutor, IHasID <String>, IHasDisplayText
{
  private static final Logger LOGGER = LoggerFactory.getLogger (AbstractNewsfeedAjaxExecutor.class);
  private static final IMutableStatisticsHandlerKeyedCounter STATS_HDL_EXECUTE = StatisticsManager.getKeyedCounterHandler (AbstractNewsfeedAjaxExecutor.class.getName () +
                                                                                                                            "$EXECUTE");
  private static final IMutableStatisticsHandlerKeyedCounter STATS_HDL_ERROR = StatisticsManager.getKeyedCounterHandler (AbstractNewsfeedAjaxExecutor.class.getName () +
                                                                                                                          "$ERROR");

  private final IHasDisplayText m_aDisplayText;
  private final String m_sFeedID;

  public AbstractNewsfeedAjaxExecutor (@NonNull final IHasDisplayText aDisplayText, @NonNull @Nonempty final String sFeedID)
  {
    ValueEnforcer.notNull (aDisplayText, "DisplayText");
    ValueEnforcer.notNull (sFeedID, "FeedID");
    m_aDisplayText = aDisplayText;
    m_sFeedID = sFeedID;
  }

  @NonNull
  @Nonempty
  public final String getID ()
  {
    return m_sFeedID;
  }

  public final String getDisplayText (@NonNull final Locale aContentLocale)
  {
    return m_aDisplayText.getDisplayText (aContentLocale);
  }

  @NonNull
  @OverrideOnDemand
  protected String getFeedDescription ()
  {
    return "ph-oton-core";
  }

  protected abstract void fillNewsfeed (@NonNull Feed aFeed);

  @Override
  public void handleRequest (@NonNull final IRequestWebScopeWithoutResponse aRequestScope,
                             @NonNull final PhotonUnifiedResponse aAjaxResponse) throws Exception
  {
    // Increment statistics counter
    final StopWatch aSW = StopWatch.createdStarted ();
    STATS_HDL_EXECUTE.increment (m_sFeedID);

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
      STATS_HDL_ERROR.increment (m_sFeedID);
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
