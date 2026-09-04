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

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.telemetry.ITelemetrySpan;
import com.helger.telemetry.TelemetryAttributes;

/**
 * Emits the ph-telemetry span attributes and metrics for the AJAX function invocations handled by
 * {@link AjaxInvoker}. All emission happens through the vendor neutral ph-telemetry facades, so
 * without a registered SPI everything degrades to cheap no-ops.
 *
 * @author Philip Helger
 * @since 10.5.1
 */
@Immutable
final class AjaxTelemetry
{
  private AjaxTelemetry ()
  {}

  /**
   * Set the descriptive attributes on the span covering a single AJAX function invocation.
   *
   * @param aSpan
   *        The span to fill. May not be <code>null</code>.
   * @param sFunctionName
   *        The name of the invoked AJAX function. May not be <code>null</code>.
   */
  static void onInvokeStart (@NonNull final ITelemetrySpan aSpan, @NonNull final String sFunctionName)
  {
    aSpan.setAttribute (CAjaxTelemetry.ATTR_AJAX_FUNCTION, sFunctionName);
  }

  /**
   * Mark the span of a successful AJAX function invocation.
   *
   * @param aSpan
   *        The span to mark. May not be <code>null</code>.
   */
  static void onInvokeSuccess (@NonNull final ITelemetrySpan aSpan)
  {
    aSpan.setAttribute (CAjaxTelemetry.ATTR_AJAX_SUCCESS, true);
    aSpan.setStatusOk ();
  }

  /**
   * Mark the span of a failed AJAX function invocation. The exception itself is recorded by the
   * surrounding {@code Telemetry.withSpanVoidThrowing (...)} when it is re-thrown.
   *
   * @param aSpan
   *        The span to mark. May not be <code>null</code>.
   */
  static void onInvokeError (@NonNull final ITelemetrySpan aSpan)
  {
    aSpan.setAttribute (CAjaxTelemetry.ATTR_AJAX_SUCCESS, false);
  }

  /**
   * Emit the end-of-invocation metrics. Called for every code path through
   * {@link AjaxInvoker#invokeFunction(String, com.helger.photon.ajax.executor.IAjaxExecutor, com.helger.web.scope.IRequestWebScopeWithoutResponse, com.helger.photon.app.PhotonUnifiedResponse)},
   * so that the counter is a true invocation total.
   *
   * @param sFunctionName
   *        The name of the invoked AJAX function. May not be <code>null</code>.
   * @param bSuccess
   *        <code>true</code> if the invocation was technically successful.
   * @param nDurationMillis
   *        The wall-clock duration of the invocation in milliseconds.
   */
  static void onInvokeEnd (@NonNull final String sFunctionName, final boolean bSuccess, final long nDurationMillis)
  {
    // The function name is registered up-front, so it is a bounded metric dimension
    final TelemetryAttributes aAttrs = TelemetryAttributes.builder ()
                                                          .put (CAjaxTelemetry.ATTR_AJAX_FUNCTION, sFunctionName)
                                                          .put (CAjaxTelemetry.ATTR_AJAX_SUCCESS, bSuccess)
                                                          .build ();
    AjaxMetrics.INVOCATIONS.add (1, aAttrs);
    AjaxMetrics.DURATION.record (nDurationMillis, aAttrs);
  }
}
