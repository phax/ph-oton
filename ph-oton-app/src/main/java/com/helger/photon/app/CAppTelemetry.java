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
  /**
   * Counter: number of web site resource cache accesses, by hit and resource type. Counting hits
   * and misses makes the hit <em>ratio</em> derivable, and it makes a forgotten
   * {@code WebSiteResourceCache.setCacheEnabled (false)} in production visible - every access is a
   * miss then.
   *
   * @since 10.6.0
   */
  public static final String METRIC_RESOURCE_CACHE_ACCESS = "photon.resource.cache.access";
  /**
   * Counter: number of web site resource bundles that were newly created.
   *
   * @since 10.6.0
   */
  public static final String METRIC_RESOURCE_BUNDLES_CREATED = "photon.resource.bundles.created";
  /**
   * Counter: number of persisted web site resource bundles that were skipped on startup, because at
   * least one contained resource is missing or changed. A number that is consistently greater than
   * zero means the on-disk bundle cache is regenerated more often than expected.
   *
   * @since 10.6.0
   */
  public static final String METRIC_RESOURCE_BUNDLES_SKIPPED = "photon.resource.bundles.skipped";

  // === attribute keys ===
  /**
   * The MIME type of the created HTML response, without any parameters. Bounded, but only used as a
   * span attribute.
   */
  public static final String ATTR_HTML_MIME_TYPE = "photon.html.mimetype";
  /** Whether the HTML response was created successfully. */
  public static final String ATTR_HTML_SUCCESS = "photon.html.success";
  /**
   * Whether a web site resource cache access was a hit.
   *
   * @since 10.6.0
   */
  public static final String ATTR_RESOURCE_CACHE_HIT = "photon.resource.cache.hit";
  /**
   * The ID of the {@link com.helger.photon.app.resource.EWebSiteResourceType} - a two value enum,
   * so it is bounded.<br>
   * The resource <em>path</em> is deliberately not used: it is bounded in principle but can be in
   * the hundreds and is the kind of dimension that quietly multiplies with everything else - the
   * log lines already carry it. Content hashes are not used as attributes at all.
   *
   * @since 10.6.0
   */
  public static final String ATTR_RESOURCE_TYPE = "photon.resource.type";

  // === metric units ===
  /**
   * Unit for all cache access counting instruments.
   *
   * @since 10.6.0
   */
  public static final String UNIT_ACCESS = "{access}";
  /**
   * Unit for all resource bundle counting instruments.
   *
   * @since 10.6.0
   */
  public static final String UNIT_BUNDLE = "{bundle}";
  /** Unit for all duration instruments. */
  public static final String UNIT_MILLIS = "ms";

  private CAppTelemetry ()
  {}
}
