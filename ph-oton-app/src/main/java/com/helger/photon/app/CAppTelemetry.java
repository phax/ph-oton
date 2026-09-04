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
package com.helger.photon.app;

import com.helger.annotation.concurrent.Immutable;

/**
 * Constant span, metric and attribute names emitted by the ph-oton-app module via the vendor
 * neutral ph-telemetry facade. Centralized here, so that applications can reference the literally
 * same names when building dashboards, alerting rules or tests.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
public final class CAppTelemetry
{
  // === span names ===
  /**
   * Span wrapping the serialization of a complete HC tree into the HTTP response - it is started
   * and closed in
   * {@link com.helger.photon.app.html.PhotonHTMLHelper#createHTMLResponse(com.helger.web.scope.IRequestWebScopeWithoutResponse, com.helger.servlet.response.UnifiedResponse, com.helger.photon.app.html.IHTMLProvider)}.
   * It nests inside the surrounding page request span, if one is present. It covers the
   * serialization only - the creation of the HC tree happens before the span is started.
   */
  public static final String SPAN_HTML_RESPONSE = "photon.html.response";

  // === metric instrument names ===
  /** Histogram (ms): wall-clock duration of serializing one HC tree into the HTTP response. */
  public static final String METRIC_HTML_DURATION = "photon.html.duration";

  // === attribute keys ===
  /**
   * The MIME type of the created HTML response, without any parameters. Bounded, but only used as a
   * span attribute.
   */
  public static final String ATTR_HTML_MIME_TYPE = "photon.html.mimetype";
  /** Whether the HTML response was created successfully. */
  public static final String ATTR_HTML_SUCCESS = "photon.html.success";

  // === metric units ===
  /** Unit for all duration instruments. */
  public static final String UNIT_MILLIS = "ms";

  private CAppTelemetry ()
  {}
}
