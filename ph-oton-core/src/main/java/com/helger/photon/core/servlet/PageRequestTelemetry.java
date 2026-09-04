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
package com.helger.photon.core.servlet;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.photon.core.CCoreTelemetry;
import com.helger.telemetry.ITelemetrySpan;
import com.helger.telemetry.TelemetryAttributes;

/**
 * Emits the ph-telemetry span attributes and metrics for the UI page requests handled by
 * {@link AbstractApplicationXServletHandler}. All emission happens through the vendor neutral
 * ph-telemetry facades, so without a registered SPI everything degrades to cheap no-ops.
 *
 * @author Philip Helger
 * @since 10.5.1
 */
@Immutable
final class PageRequestTelemetry
{
  private PageRequestTelemetry ()
  {}

  /**
   * Mark the span of a successfully handled UI page request.
   *
   * @param aSpan
   *        The span to mark. May not be <code>null</code>.
   */
  static void onPageRequestSuccess (@NonNull final ITelemetrySpan aSpan)
  {
    aSpan.setAttribute (CCoreTelemetry.ATTR_PAGE_SUCCESS, true);
    aSpan.setStatusOk ();
  }

  /**
   * Mark the span of a UI page request that ended in a Post-Redirect-Get. That is a regular control
   * flow and no error - {@code XServletHandlerToSimpleHandler} passes the exception through as
   * well - so the request counts as successful.
   *
   * @param aSpan
   *        The span to mark. May not be <code>null</code>.
   */
  static void onPageRequestRedirect (@NonNull final ITelemetrySpan aSpan)
  {
    aSpan.addEvent (CCoreTelemetry.EVENT_FORCED_REDIRECT);
    aSpan.setAttribute (CCoreTelemetry.ATTR_PAGE_SUCCESS, true);
    aSpan.setStatusOk ();
  }

  /**
   * Mark the span of a failed UI page request.
   *
   * @param aSpan
   *        The span to mark. May not be <code>null</code>.
   * @param aException
   *        The exception that occurred. May not be <code>null</code>.
   */
  static void onPageRequestError (@NonNull final ITelemetrySpan aSpan, @NonNull final Exception aException)
  {
    aSpan.setAttribute (CCoreTelemetry.ATTR_PAGE_SUCCESS, false);
    aSpan.recordException (aException);
    aSpan.setStatusError (aException.getMessage ());
  }

  /**
   * Emit the end-of-request metrics. Called for every code path, so that the counter is a true
   * request total and failed requests are timed as well. No route dimension is used here - the per
   * screen dimension is emitted by the web page itself.
   *
   * @param bSuccess
   *        <code>true</code> if the page request was handled successfully.
   * @param nDurationMillis
   *        The wall-clock duration of the page request in milliseconds.
   */
  static void onPageRequestEnd (final boolean bSuccess, final long nDurationMillis)
  {
    final TelemetryAttributes aAttrs = TelemetryAttributes.builder ()
                                                          .put (CCoreTelemetry.ATTR_PAGE_SUCCESS, bSuccess)
                                                          .build ();
    PageRequestMetrics.REQUESTS.add (1, aAttrs);
    PageRequestMetrics.DURATION.record (nDurationMillis, aAttrs);
  }
}
