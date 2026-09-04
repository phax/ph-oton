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

import com.helger.annotation.concurrent.Immutable;
import com.helger.photon.app.CAppTelemetry;
import com.helger.telemetry.ITelemetryHistogram;
import com.helger.telemetry.TelemetryMetrics;

/**
 * Central registry of the named metric instruments emitted when creating an HTML response. Each
 * instrument is created once at class-load time via the vendor neutral {@link TelemetryMetrics}
 * facade - if no {@code ITelemetryMeterSPI} is registered, the underlying instruments are cheap
 * no-ops, so referencing this class in a deployment without an observability backend has no cost.
 *
 * @author Philip Helger
 * @since 10.5.1
 */
@Immutable
public final class HTMLResponseMetrics
{
  /** Wall-clock duration of serializing one HC tree into the HTTP response, by success. */
  public static final ITelemetryHistogram DURATION = TelemetryMetrics.histogram (CAppTelemetry.METRIC_HTML_DURATION,
                                                                                 "Wall-clock duration of serializing one HC tree into the HTTP response",
                                                                                 CAppTelemetry.UNIT_MILLIS);

  private HTMLResponseMetrics ()
  {}
}
