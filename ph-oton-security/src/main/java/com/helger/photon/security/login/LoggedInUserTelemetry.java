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

import java.time.Duration;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.photon.security.CSecurityTelemetry;
import com.helger.telemetry.TelemetryAttributes;

/**
 * Emits the ph-telemetry metrics for the logins and logouts handled by {@link LoggedInUserManager}.
 * All emission happens through the vendor neutral ph-telemetry facades, so without a registered SPI
 * everything degrades to cheap no-ops.<br>
 * No user ID, login name or IP address is ever used as a metric attribute - the only dimension is
 * the {@link ELoginResult}, which has a fixed set of 10 values.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
final class LoggedInUserTelemetry
{
  private LoggedInUserTelemetry ()
  {}

  /**
   * @param eLoginResult
   *        The login result to build the attributes from. May not be <code>null</code>.
   * @return The bounded attributes shared by the login instruments. Never <code>null</code>.
   */
  @NonNull
  private static TelemetryAttributes _getResultAttrs (@NonNull final ELoginResult eLoginResult)
  {
    return TelemetryAttributes.builder ()
                              .put (CSecurityTelemetry.ATTR_LOGIN_RESULT, eLoginResult.name ())
                              .build ();
  }

  /**
   * Count a successful login.
   *
   * @param eLoginResult
   *        The login result - either {@link ELoginResult#SUCCESS} or
   *        {@link ELoginResult#SUCCESS_WITH_LOGOUT}. May not be <code>null</code>.
   */
  static void onLoginSuccess (@NonNull final ELoginResult eLoginResult)
  {
    LoginMetrics.LOGIN_SUCCESS.add (1, _getResultAttrs (eLoginResult));
  }

  /**
   * Count a failed login.
   *
   * @param eLoginResult
   *        The reason why the login failed. May not be <code>null</code>.
   */
  static void onLoginFailed (@NonNull final ELoginResult eLoginResult)
  {
    LoginMetrics.LOGIN_FAILED.add (1, _getResultAttrs (eLoginResult));
  }

  /**
   * Count a logout that really logged out a user and record how long that user was logged in.
   *
   * @param aSessionDuration
   *        The time between the login and the logout. May not be <code>null</code>.
   */
  static void onLogout (@NonNull final Duration aSessionDuration)
  {
    LoginMetrics.LOGOUT.add (1);
    LoginMetrics.SESSION_DURATION.record (aSessionDuration.toMillis ());
  }
}
