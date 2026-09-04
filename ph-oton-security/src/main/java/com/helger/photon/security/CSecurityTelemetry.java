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
package com.helger.photon.security;

import com.helger.annotation.concurrent.Immutable;

/**
 * Constant metric and attribute names emitted by the ph-oton-security module via the vendor neutral
 * ph-telemetry facade. Centralized here, so that applications can reference the literally same
 * names when building dashboards, alerting rules or tests.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
public final class CSecurityTelemetry
{
  // === metric instrument names ===
  /**
   * Counter: number of successful logins, by login result - this distinguishes a plain
   * <code>SUCCESS</code> from a <code>SUCCESS_WITH_LOGOUT</code>.
   */
  public static final String METRIC_LOGIN_SUCCESS = "photon.security.login.success";
  /**
   * Counter: number of failed logins, by login result. This is the instrument to alert on - a
   * sudden rise of <code>USER_NOT_EXISTING</code> indicates user enumeration, a rise of
   * <code>INVALID_PASSWORD</code> indicates credential stuffing.
   */
  public static final String METRIC_LOGIN_FAILED = "photon.security.login.failed";
  /** Counter: number of logouts that really logged out a user. */
  public static final String METRIC_LOGOUT = "photon.security.logout";
  /** Observable gauge: number of users currently logged in. */
  public static final String METRIC_USERS_LOGGED_IN = "photon.security.users.loggedin";
  /** Histogram (ms): time between the login and the logout of a user. */
  public static final String METRIC_SESSION_DURATION = "photon.security.session.duration";
  /**
   * Counter: number of failed logins that were registered for throttling by remote IP address. The
   * IP address itself is deliberately not an attribute.
   */
  public static final String METRIC_LOGIN_THROTTLE_FAILED = "photon.security.throttle.failed";
  /**
   * Histogram (ms): the artificial waiting time that was really applied after a failed login. A
   * rising aggregate is the clearest sign of a brute force attack in progress.
   */
  public static final String METRIC_LOGIN_THROTTLE_DELAY = "photon.security.throttle.delay";
  /**
   * Observable gauge: number of distinct IP addresses that currently have failed logins on record.
   * A rising number with a low per-IP count is the signature of a distributed attack, which the
   * plain failure counter alone cannot distinguish from a single noisy client.
   *
   * @since 10.6.0
   */
  public static final String METRIC_LOGIN_THROTTLE_TRACKED_IPS = "photon.security.throttle.tracked";

  // === attribute keys ===
  /**
   * The name of the {@link com.helger.photon.security.login.ELoginResult} constant, e.g.
   * <code>INVALID_PASSWORD</code>. The enum has 10 values, so this is a bounded dimension. Use
   * {@link Enum#name()} - the display text is localized and therefore not a stable metric
   * dimension.
   */
  public static final String ATTR_LOGIN_RESULT = "photon.security.login.result";

  // === metric units ===
  /** Unit for all login counting instruments. */
  public static final String UNIT_LOGIN = "{login}";
  /** Unit for all user counting instruments. */
  public static final String UNIT_USER = "{user}";
  /**
   * Unit for all IP address counting instruments.
   *
   * @since 10.6.0
   */
  public static final String UNIT_IP = "{ip}";
  /** Unit for all duration instruments. */
  public static final String UNIT_MILLIS = "ms";

  private CSecurityTelemetry ()
  {}
}
