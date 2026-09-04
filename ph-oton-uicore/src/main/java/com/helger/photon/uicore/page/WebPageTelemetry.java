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
package com.helger.photon.uicore.page;

import java.util.Locale;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.concurrent.Immutable;
import com.helger.photon.uicore.CUICoreTelemetry;
import com.helger.telemetry.ITelemetrySpan;
import com.helger.telemetry.TelemetryAttributes;

/**
 * Emits the ph-telemetry span attributes and metrics for the web page content creation handled by
 * {@link AbstractWebPage}. All emission happens through the vendor neutral ph-telemetry facades, so
 * without a registered SPI everything degrades to cheap no-ops.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
final class WebPageTelemetry
{
  private WebPageTelemetry ()
  {}

  /**
   * Set the descriptive attributes on the span covering a single web page content creation.
   *
   * @param aSpan
   *        The span to fill. May not be <code>null</code>.
   * @param sPageID
   *        The ID of the web page. May not be <code>null</code>.
   * @param aDisplayLocale
   *        The display locale of the request. May be <code>null</code>.
   */
  static void onContentStart (@NonNull final ITelemetrySpan aSpan,
                              @NonNull final String sPageID,
                              @Nullable final Locale aDisplayLocale)
  {
    aSpan.setAttribute (CUICoreTelemetry.ATTR_PAGE_ID, sPageID);
    // The locale is a span attribute only, to keep the metric dimensions small
    if (aDisplayLocale != null)
      aSpan.setAttribute (CUICoreTelemetry.ATTR_PAGE_LOCALE, aDisplayLocale.toString ());
  }

  /**
   * Remember on the span whether the page content was really created.
   *
   * @param aSpan
   *        The span to fill. May not be <code>null</code>.
   * @param bDisplayed
   *        <code>true</code> if the page content was created, <code>false</code> if the display of
   *        the page was rejected.
   */
  static void onContentDisplayed (@NonNull final ITelemetrySpan aSpan, final boolean bDisplayed)
  {
    aSpan.setAttribute (CUICoreTelemetry.ATTR_PAGE_DISPLAYED, bDisplayed);
  }

  /**
   * Mark the span of a successfully created web page content.
   *
   * @param aSpan
   *        The span to mark. May not be <code>null</code>.
   */
  static void onContentSuccess (@NonNull final ITelemetrySpan aSpan)
  {
    aSpan.setStatusOk ();
  }

  /**
   * Mark the span of a content creation that ended in a Post-Redirect-Get - e.g. after a successful
   * form submission. That is a regular control flow and no error -
   * {@code XServletHandlerToSimpleHandler} passes the exception through as well.
   *
   * @param aSpan
   *        The span to mark. May not be <code>null</code>.
   */
  static void onContentRedirect (@NonNull final ITelemetrySpan aSpan)
  {
    aSpan.addEvent (CUICoreTelemetry.EVENT_FORCED_REDIRECT);
    aSpan.setStatusOk ();
  }

  /**
   * Mark the span of a failed web page content creation.
   *
   * @param aSpan
   *        The span to mark. May not be <code>null</code>.
   * @param aException
   *        The exception that occurred. May not be <code>null</code>.
   */
  static void onContentError (@NonNull final ITelemetrySpan aSpan, @NonNull final RuntimeException aException)
  {
    aSpan.recordException (aException);
    aSpan.setStatusError (aException.getMessage ());
  }

  /**
   * Emit the end-of-content-creation metrics. Called for every code path, so that pages that are
   * never displayed - e.g. because of a permission problem - are visible as well.
   *
   * @param sPageID
   *        The ID of the web page. May not be <code>null</code>.
   * @param bDisplayed
   *        <code>true</code> if the page content was created.
   * @param nDurationMillis
   *        The wall-clock duration of the content creation in milliseconds.
   */
  static void onContentEnd (@NonNull final String sPageID, final boolean bDisplayed, final long nDurationMillis)
  {
    WebPageMetrics.RENDERED.add (1,
                                 TelemetryAttributes.builder ()
                                                    .put (CUICoreTelemetry.ATTR_PAGE_ID, sPageID)
                                                    .put (CUICoreTelemetry.ATTR_PAGE_DISPLAYED, bDisplayed)
                                                    .build ());
    WebPageMetrics.DURATION.record (nDurationMillis,
                                    TelemetryAttributes.builder ()
                                                       .put (CUICoreTelemetry.ATTR_PAGE_ID, sPageID)
                                                       .build ());
  }
}
