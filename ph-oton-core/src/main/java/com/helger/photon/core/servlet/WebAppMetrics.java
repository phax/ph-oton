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
import com.helger.telemetry.ITelemetryUpDownCounter;
import com.helger.telemetry.TelemetryMetrics;

/**
 * Central registry of the named metric instruments emitted for the HTTP session and servlet context
 * life cycle by {@link WebAppListener}. Each instrument is created once at class-load time via the
 * vendor neutral {@link TelemetryMetrics} facade - if no {@code ITelemetryMeterSPI} is registered,
 * the underlying instruments are cheap no-ops, so referencing this class in a deployment without an
 * observability backend has no cost.<br>
 * None of these instruments carries an attribute: session IDs are unbounded, and the servlet context
 * path and the server name are constant per deployment and belong on the OpenTelemetry
 * <code>Resource</code>, which the deploying application configures.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
public final class WebAppMetrics
{
  /** HTTP sessions that were created. */
  public static final ITelemetryCounter SESSIONS_CREATED = TelemetryMetrics.counter (CCoreTelemetry.METRIC_SESSIONS_CREATED,
                                                                                      "HTTP sessions that were created",
                                                                                      CCoreTelemetry.UNIT_SESSION);

  /** HTTP sessions currently active. */
  public static final ITelemetryUpDownCounter SESSIONS_ACTIVE = TelemetryMetrics.upDownCounter (CCoreTelemetry.METRIC_SESSIONS_ACTIVE,
                                                                                                 "HTTP sessions currently active",
                                                                                                 CCoreTelemetry.UNIT_SESSION);

  /** Wall-clock duration of the servlet context initialization. */
  public static final ITelemetryHistogram STARTUP_DURATION = TelemetryMetrics.histogram (CCoreTelemetry.METRIC_STARTUP_DURATION,
                                                                                          "Wall-clock duration of the servlet context initialization",
                                                                                          CCoreTelemetry.UNIT_MILLIS);

  /** Wall-clock duration of the servlet context destruction. */
  public static final ITelemetryHistogram SHUTDOWN_DURATION = TelemetryMetrics.histogram (CCoreTelemetry.METRIC_SHUTDOWN_DURATION,
                                                                                           "Wall-clock duration of the servlet context destruction",
                                                                                           CCoreTelemetry.UNIT_MILLIS);

  private WebAppMetrics ()
  {}
}
