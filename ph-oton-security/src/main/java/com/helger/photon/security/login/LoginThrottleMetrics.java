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
package com.helger.photon.security.login;

import java.util.function.LongSupplier;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.photon.security.CSecurityTelemetry;
import com.helger.telemetry.ITelemetryCounter;
import com.helger.telemetry.ITelemetryGauge;
import com.helger.telemetry.ITelemetryHistogram;
import com.helger.telemetry.TelemetryMetrics;

/**
 * Central registry of the named metric instruments emitted for the failed login throttling of
 * {@link LoginThrottlePerIP}. Each instrument is created once at class-load time via the vendor
 * neutral {@link TelemetryMetrics} facade - if no {@code ITelemetryMeterSPI} is registered, the
 * underlying instruments are cheap no-ops, so referencing this class in a deployment without an
 * observability backend has no cost.<br>
 * Neither instrument carries any attribute: the only dimension that would be interesting - the
 * remote IP address - is unbounded and personal data, so it must never become a metric attribute.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
public final class LoginThrottleMetrics
{
  /** Failed logins that were registered for per-IP throttling. */
  public static final ITelemetryCounter FAILED = TelemetryMetrics.counter (CSecurityTelemetry.METRIC_LOGIN_THROTTLE_FAILED,
                                                                           "Failed logins that were registered for per-IP throttling",
                                                                           CSecurityTelemetry.UNIT_LOGIN);

  /** The artificial waiting time that was really applied after a failed login. */
  public static final ITelemetryHistogram DELAY = TelemetryMetrics.histogram (CSecurityTelemetry.METRIC_LOGIN_THROTTLE_DELAY,
                                                                              "Artificial waiting time applied after a failed login",
                                                                              CSecurityTelemetry.UNIT_MILLIS);

  private LoginThrottleMetrics ()
  {}

  /**
   * Create the observable gauge over the number of distinct IP addresses that currently have failed
   * logins on record. Deliberately not a constant of this class: the gauge is bound to the life
   * time of the {@link LoginThrottlePerIP} global singleton and must be closed when that singleton
   * is destroyed, so that it does not outlive the global scope.
   *
   * @param aSupplier
   *        The supplier of the current number of tracked IP addresses. Invoked from a backend
   *        thread, so it must be cheap and thread safe. May not be <code>null</code>.
   * @return The gauge handle to be closed on destruction. Never <code>null</code>.
   * @since 10.6.0
   */
  @NonNull
  public static ITelemetryGauge createTrackedIPsGauge (@NonNull final LongSupplier aSupplier)
  {
    return TelemetryMetrics.gauge (CSecurityTelemetry.METRIC_LOGIN_THROTTLE_TRACKED_IPS,
                                   "Number of distinct IP addresses that currently have failed logins on record",
                                   CSecurityTelemetry.UNIT_IP,
                                   aSupplier);
  }
}
