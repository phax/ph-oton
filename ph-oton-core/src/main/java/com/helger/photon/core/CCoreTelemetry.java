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
package com.helger.photon.core;

import com.helger.annotation.concurrent.Immutable;

/**
 * Constant span, metric and attribute names emitted by the ph-oton-core module via the vendor
 * neutral ph-telemetry facade. Centralized here, so that applications can reference the literally
 * same names when building dashboards, alerting rules or tests.
 *
 * @author Philip Helger
 * @since 10.5.1
 */
@Immutable
public final class CCoreTelemetry
{
  // === span names ===
  /**
   * Span wrapping the whole handling of a single UI page request - it is started and closed in
   * {@link com.helger.photon.core.servlet.AbstractApplicationXServletHandler#handleRequest(com.helger.web.scope.IRequestWebScopeWithoutResponse, com.helger.servlet.response.UnifiedResponse)}.
   * The HTML serialization happens in a nested span.
   */
  public static final String SPAN_PAGE_REQUEST = "photon.page.request";

  // === span event names ===
  /**
   * Event on {@link #SPAN_PAGE_REQUEST}, if the request ended in a Post-Redirect-Get instead of an
   * HTML response. That is a regular control flow and explicitly not an error.
   */
  public static final String EVENT_FORCED_REDIRECT = "photon.page.forcedredirect";

  // === metric instrument names ===
  /** Counter: number of handled UI page requests - successful and failed ones. */
  public static final String METRIC_PAGE_REQUESTS = "photon.page.requests";
  /** Histogram (ms): wall-clock duration of handling one UI page request. */
  public static final String METRIC_PAGE_DURATION = "photon.page.duration";

  // === attribute keys ===
  /** Whether the UI page request was handled successfully. */
  public static final String ATTR_PAGE_SUCCESS = "photon.page.success";

  // === metric units ===
  /** Unit for all page request counting instruments. */
  public static final String UNIT_REQUEST = "{request}";
  /** Unit for all duration instruments. */
  public static final String UNIT_MILLIS = "ms";

  private CCoreTelemetry ()
  {}
}
