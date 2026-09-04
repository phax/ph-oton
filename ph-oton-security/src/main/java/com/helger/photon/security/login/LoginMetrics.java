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
 * Central registry of the named metric instruments emitted for logins and logouts. Each instrument
 * is created once at class-load time via the vendor neutral {@link TelemetryMetrics} facade - if no
 * {@code ITelemetryMeterSPI} is registered, the underlying instruments are cheap no-ops, so
 * referencing this class in a deployment without an observability backend has no cost.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
public final class LoginMetrics
{
  /** Successful logins, by login result. */
  public static final ITelemetryCounter LOGIN_SUCCESS = TelemetryMetrics.counter (CSecurityTelemetry.METRIC_LOGIN_SUCCESS,
                                                                                  "Successful logins",
                                                                                  CSecurityTelemetry.UNIT_LOGIN);

  /** Failed logins, by login result. */
  public static final ITelemetryCounter LOGIN_FAILED = TelemetryMetrics.counter (CSecurityTelemetry.METRIC_LOGIN_FAILED,
                                                                                 "Failed logins",
                                                                                 CSecurityTelemetry.UNIT_LOGIN);

  /** Logouts that really logged out a user. */
  public static final ITelemetryCounter LOGOUT = TelemetryMetrics.counter (CSecurityTelemetry.METRIC_LOGOUT,
                                                                           "Logouts that really logged out a user",
                                                                           CSecurityTelemetry.UNIT_LOGIN);

  /** Time between the login and the logout of a user. */
  public static final ITelemetryHistogram SESSION_DURATION = TelemetryMetrics.histogram (CSecurityTelemetry.METRIC_SESSION_DURATION,
                                                                                          "Time between the login and the logout of a user",
                                                                                          CSecurityTelemetry.UNIT_MILLIS);

  private LoginMetrics ()
  {}

  /**
   * Create the observable gauge over the number of currently logged in users. Deliberately not a
   * constant of this class: the gauge is bound to the life time of the
   * {@link LoggedInUserManager} global singleton and must be closed when that singleton is
   * destroyed, so that it does not outlive the global scope.
   *
   * @param aSupplier
   *        The supplier of the current number of logged in users. Invoked from a backend thread, so
   *        it must be cheap and thread safe. May not be <code>null</code>.
   * @return The gauge handle to be closed on destruction. Never <code>null</code>.
   */
  @NonNull
  public static ITelemetryGauge createLoggedInUsersGauge (@NonNull final LongSupplier aSupplier)
  {
    return TelemetryMetrics.gauge (CSecurityTelemetry.METRIC_USERS_LOGGED_IN,
                                   "Number of users currently logged in",
                                   CSecurityTelemetry.UNIT_USER,
                                   aSupplier);
  }
}
