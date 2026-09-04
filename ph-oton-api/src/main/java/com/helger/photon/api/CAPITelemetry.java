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
package com.helger.photon.api;

import com.helger.annotation.concurrent.Immutable;

/**
 * Constant span, metric and attribute names emitted by the {@link APIInvoker} via the vendor
 * neutral ph-telemetry facade. Centralized here, so that applications can reference the literally
 * same names when building dashboards, alerting rules or tests.
 *
 * @author Philip Helger
 * @since 10.5.1
 */
@Immutable
public final class CAPITelemetry
{
  // === span names ===
  /**
   * Span wrapping the whole invocation of a single REST API - it is started and closed in
   * {@link APIInvoker#invoke(InvokableAPIDescriptor, com.helger.web.scope.IRequestWebScopeWithoutResponse, com.helger.servlet.response.UnifiedResponse)}.
   */
  public static final String SPAN_INVOKE = "photon.api.invoke";

  // === metric instrument names ===
  /** Counter: number of REST API invocations - successful and failed ones. */
  public static final String METRIC_INVOCATIONS = "photon.api.invocations";
  /** Histogram (ms): wall-clock duration of a single REST API invocation. */
  public static final String METRIC_DURATION = "photon.api.duration";

  // === attribute keys ===
  /**
   * The path <em>template</em> of the invoked API as returned by
   * {@link com.helger.photon.api.pathdescriptor.PathDescriptor#getAsURLString()}, e.g.
   * <code>/user/{id}</code>. This is the low cardinality dimension to group metrics by.
   */
  public static final String ATTR_API_ROUTE = "photon.api.route";
  /**
   * The concrete path requested by the user as returned by
   * {@link InvokableAPIDescriptor#getPath()}, e.g. <code>/user/4711</code>. Only used as a span
   * attribute - never as a metric attribute, because its cardinality is unbounded.
   */
  public static final String ATTR_API_PATH = "photon.api.path";
  /** The HTTP method of the invoked API, e.g. <code>GET</code>. */
  public static final String ATTR_API_METHOD = "photon.api.method";
  /** Whether the API invocation was technically successful. */
  public static final String ATTR_API_SUCCESS = "photon.api.success";
  /**
   * Whether a thrown exception was handled by the {@link IAPIExceptionMapper} of the API - in that
   * case the invocation returns normally even though it failed.
   */
  public static final String ATTR_API_EXCEPTION_HANDLED = "photon.api.exception.handled";

  // === metric units ===
  /** Unit for all API request counting instruments. */
  public static final String UNIT_REQUEST = "{request}";
  /** Unit for all duration instruments. */
  public static final String UNIT_MILLIS = "ms";

  private CAPITelemetry ()
  {}
}
