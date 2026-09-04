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

import com.helger.annotation.concurrent.Immutable;
import com.helger.photon.core.CCoreTelemetry;
import com.helger.telemetry.ITelemetryCounter;
import com.helger.telemetry.ITelemetryHistogram;
import com.helger.telemetry.TelemetryMetrics;

/**
 * Central registry of the named metric instruments emitted for UI page requests. Each instrument is
 * created once at class-load time via the vendor neutral {@link TelemetryMetrics} facade - if no
 * {@code ITelemetryMeterSPI} is registered, the underlying instruments are cheap no-ops, so
 * referencing this class in a deployment without an observability backend has no cost.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
public final class PageRequestMetrics
{
  /** Handled UI page requests, by success. */
  public static final ITelemetryCounter REQUESTS = TelemetryMetrics.counter (CCoreTelemetry.METRIC_PAGE_REQUESTS,
                                                                             "Handled UI page requests - successful and failed ones",
                                                                             CCoreTelemetry.UNIT_REQUEST);

  /** Wall-clock duration of handling one UI page request, by success. */
  public static final ITelemetryHistogram DURATION = TelemetryMetrics.histogram (CCoreTelemetry.METRIC_PAGE_DURATION,
                                                                                 "Wall-clock duration of handling one UI page request",
                                                                                 CCoreTelemetry.UNIT_MILLIS);

  private PageRequestMetrics ()
  {}
}
