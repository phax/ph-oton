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
package com.helger.photon.app.resource;

import com.helger.annotation.concurrent.Immutable;
import com.helger.photon.app.CAppTelemetry;
import com.helger.telemetry.ITelemetryCounter;
import com.helger.telemetry.TelemetryMetrics;

/**
 * Central registry of the named metric instruments emitted for the web site resource cache and the
 * resource bundling. Each instrument is created once at class-load time via the vendor neutral
 * {@link TelemetryMetrics} facade - if no {@code ITelemetryMeterSPI} is registered, the underlying
 * instruments are cheap no-ops, so referencing this class in a deployment without an observability
 * backend has no cost.<br>
 * No spans are emitted here: this is startup-time work that does not belong to any request trace,
 * and a span without a parent adds noise without adding information.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
public final class WebSiteResourceMetrics
{
  /** Web site resource cache accesses, by hit and resource type. */
  public static final ITelemetryCounter CACHE_ACCESS = TelemetryMetrics.counter (CAppTelemetry.METRIC_RESOURCE_CACHE_ACCESS,
                                                                                 "Web site resource cache accesses",
                                                                                 CAppTelemetry.UNIT_ACCESS);

  /** Web site resource bundles that were newly created. */
  public static final ITelemetryCounter BUNDLES_CREATED = TelemetryMetrics.counter (CAppTelemetry.METRIC_RESOURCE_BUNDLES_CREATED,
                                                                                     "Web site resource bundles that were newly created",
                                                                                     CAppTelemetry.UNIT_BUNDLE);

  /** Persisted web site resource bundles that were skipped on startup. */
  public static final ITelemetryCounter BUNDLES_SKIPPED = TelemetryMetrics.counter (CAppTelemetry.METRIC_RESOURCE_BUNDLES_SKIPPED,
                                                                                     "Persisted web site resource bundles that were skipped on startup",
                                                                                     CAppTelemetry.UNIT_BUNDLE);

  private WebSiteResourceMetrics ()
  {}
}
