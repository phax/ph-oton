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
package com.helger.photon.ajax;

import com.helger.annotation.concurrent.Immutable;

/**
 * Constant span, metric and attribute names emitted by the {@link AjaxInvoker} via the vendor
 * neutral ph-telemetry facade. Centralized here, so that applications can reference the literally
 * same names when building dashboards, alerting rules or tests.
 *
 * @author Philip Helger
 * @since 10.5.1
 */
@Immutable
public final class CAjaxTelemetry
{
  // === span names ===
  /**
   * Span wrapping the whole invocation of a single AJAX function - it is started and closed in
   * {@link AjaxInvoker#invokeFunction(String, com.helger.photon.ajax.executor.IAjaxExecutor, com.helger.web.scope.IRequestWebScopeWithoutResponse, com.helger.photon.app.PhotonUnifiedResponse)}.
   */
  public static final String SPAN_INVOKE = "photon.ajax.invoke";

  // === metric instrument names ===
  /** Counter: number of AJAX function invocations - successful and failed ones. */
  public static final String METRIC_INVOCATIONS = "photon.ajax.invocations";
  /** Histogram (ms): wall-clock duration of a single AJAX function invocation. */
  public static final String METRIC_DURATION = "photon.ajax.duration";

  // === attribute keys ===
  /**
   * The name of the invoked AJAX function. All function names are registered up-front in the
   * {@link IAjaxRegistry}, so this is a bounded set and safe to use as a metric attribute.
   */
  public static final String ATTR_AJAX_FUNCTION = "photon.ajax.function";
  /** Whether the AJAX function invocation was technically successful. */
  public static final String ATTR_AJAX_SUCCESS = "photon.ajax.success";

  // === metric units ===
  /** Unit for all AJAX request counting instruments. */
  public static final String UNIT_REQUEST = "{request}";
  /** Unit for all duration instruments. */
  public static final String UNIT_MILLIS = "ms";

  private CAjaxTelemetry ()
  {}
}
