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
package com.helger.photon.app.html;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.mime.IMimeType;
import com.helger.photon.app.CAppTelemetry;
import com.helger.telemetry.ITelemetrySpan;
import com.helger.telemetry.TelemetryAttributes;

/**
 * Emits the ph-telemetry span attributes and metrics for the HTML responses created by
 * {@link PhotonHTMLHelper}. All emission happens through the vendor neutral ph-telemetry facades,
 * so without a registered SPI everything degrades to cheap no-ops.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
final class HTMLResponseTelemetry
{
  private HTMLResponseTelemetry ()
  {}

  /**
   * Set the MIME type of the created response on the span. Only a span attribute - the metrics of
   * this module stay global.
   *
   * @param aSpan
   *        The span to fill. May not be <code>null</code>.
   * @param aMimeType
   *        The MIME type of the response. May not be <code>null</code>.
   */
  static void onMimeTypeDetermined (@NonNull final ITelemetrySpan aSpan, @NonNull final IMimeType aMimeType)
  {
    aSpan.setAttribute (CAppTelemetry.ATTR_HTML_MIME_TYPE, aMimeType.getAsStringWithoutParameters ());
  }

  /**
   * Mark the span of a successfully created HTML response.
   *
   * @param aSpan
   *        The span to mark. May not be <code>null</code>.
   */
  static void onHTMLResponseSuccess (@NonNull final ITelemetrySpan aSpan)
  {
    aSpan.setAttribute (CAppTelemetry.ATTR_HTML_SUCCESS, true);
    aSpan.setStatusOk ();
  }

  /**
   * Emit the end-of-response metrics. Called for every code path, so that failed renderings are
   * timed as well.
   *
   * @param bSuccess
   *        <code>true</code> if the HTML response was created successfully.
   * @param nDurationMillis
   *        The wall-clock duration of creating the response in milliseconds.
   */
  static void onHTMLResponseEnd (final boolean bSuccess, final long nDurationMillis)
  {
    HTMLResponseMetrics.DURATION.record (nDurationMillis,
                                         TelemetryAttributes.builder ()
                                                            .put (CAppTelemetry.ATTR_HTML_SUCCESS, bSuccess)
                                                            .build ());
  }
}
