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

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.telemetry.ITelemetrySpan;
import com.helger.telemetry.TelemetryAttributes;

/**
 * Emits the ph-telemetry span attributes and metrics for the REST API invocations handled by
 * {@link APIInvoker}. All emission happens through the vendor neutral ph-telemetry facades, so
 * without a registered SPI everything degrades to cheap no-ops.
 *
 * @author Philip Helger
 * @since 10.5.1
 */
@Immutable
final class APITelemetry
{
  private APITelemetry ()
  {}

  /**
   * @param aInvokableDescriptor
   *        The descriptor to get the route from. May not be <code>null</code>.
   * @return The path template of the API - the bounded dimension to group metrics by. Never
   *         <code>null</code>.
   */
  @NonNull
  private static String _getRoute (@NonNull final InvokableAPIDescriptor aInvokableDescriptor)
  {
    return aInvokableDescriptor.getAPIDescriptor ().getPathDescriptor ().getAsURLString ();
  }

  /**
   * @param aInvokableDescriptor
   *        The descriptor to get the HTTP method from. May not be <code>null</code>.
   * @return The name of the HTTP method of the API. Never <code>null</code>.
   */
  @NonNull
  private static String _getMethod (@NonNull final InvokableAPIDescriptor aInvokableDescriptor)
  {
    return aInvokableDescriptor.getAPIDescriptor ().getHttpMethod ().getName ();
  }

  /**
   * Set the descriptive attributes on the span covering a single API invocation.
   *
   * @param aSpan
   *        The span to fill. May not be <code>null</code>.
   * @param aInvokableDescriptor
   *        The descriptor of the invoked API. May not be <code>null</code>.
   */
  static void onInvokeStart (@NonNull final ITelemetrySpan aSpan,
                             @NonNull final InvokableAPIDescriptor aInvokableDescriptor)
  {
    aSpan.setAttribute (CAPITelemetry.ATTR_API_ROUTE, _getRoute (aInvokableDescriptor));
    // The concrete path is unbounded - it is a span attribute only
    aSpan.setAttribute (CAPITelemetry.ATTR_API_PATH, aInvokableDescriptor.getPath ());
    aSpan.setAttribute (CAPITelemetry.ATTR_API_METHOD, _getMethod (aInvokableDescriptor));
  }

  /**
   * Mark the span of a successful API invocation.
   *
   * @param aSpan
   *        The span to mark. May not be <code>null</code>.
   */
  static void onInvokeSuccess (@NonNull final ITelemetrySpan aSpan)
  {
    aSpan.setAttribute (CAPITelemetry.ATTR_API_SUCCESS, true);
    aSpan.setStatusOk ();
  }

  /**
   * Mark the span of a failed API invocation.
   *
   * @param aSpan
   *        The span to mark. May not be <code>null</code>.
   * @param aException
   *        The exception that occurred. May not be <code>null</code>.
   * @param bExceptionHandled
   *        <code>true</code> if the exception was handled by the {@link IAPIExceptionMapper} of the
   *        API and is therefore not propagated to the caller.
   */
  static void onInvokeError (@NonNull final ITelemetrySpan aSpan,
                             @NonNull final Exception aException,
                             final boolean bExceptionHandled)
  {
    aSpan.setAttribute (CAPITelemetry.ATTR_API_SUCCESS, false);
    aSpan.setAttribute (CAPITelemetry.ATTR_API_EXCEPTION_HANDLED, bExceptionHandled);
    if (bExceptionHandled)
    {
      // The exception is not re-thrown, so the surrounding Telemetry.withSpanVoidThrowing never
      // sees it and would otherwise consider the invocation successful
      aSpan.recordException (aException);
      aSpan.setStatusError (aException.getMessage ());
    }
  }

  /**
   * Emit the end-of-invocation metrics. Called for every code path through
   * {@link APIInvoker#invoke(InvokableAPIDescriptor, com.helger.web.scope.IRequestWebScopeWithoutResponse, com.helger.servlet.response.UnifiedResponse)},
   * so that the counter is a true invocation total.
   *
   * @param aInvokableDescriptor
   *        The descriptor of the invoked API. May not be <code>null</code>.
   * @param bSuccess
   *        <code>true</code> if the invocation was technically successful.
   * @param nDurationMillis
   *        The wall-clock duration of the invocation in milliseconds.
   */
  static void onInvokeEnd (@NonNull final InvokableAPIDescriptor aInvokableDescriptor,
                           final boolean bSuccess,
                           final long nDurationMillis)
  {
    // Only bounded values are used as metric attributes
    final TelemetryAttributes aAttrs = TelemetryAttributes.builder ()
                                                          .put (CAPITelemetry.ATTR_API_ROUTE,
                                                                _getRoute (aInvokableDescriptor))
                                                          .put (CAPITelemetry.ATTR_API_METHOD,
                                                                _getMethod (aInvokableDescriptor))
                                                          .put (CAPITelemetry.ATTR_API_SUCCESS, bSuccess)
                                                          .build ();
    APIMetrics.INVOCATIONS.add (1, aAttrs);
    APIMetrics.DURATION.record (nDurationMillis, aAttrs);
  }
}
