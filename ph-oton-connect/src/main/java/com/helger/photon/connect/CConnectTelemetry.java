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
package com.helger.photon.connect;

import com.helger.annotation.concurrent.Immutable;

/**
 * Constant span, metric and attribute names emitted by the ph-oton-connect module via the vendor
 * neutral ph-telemetry facade. Centralized here, so that applications can reference the literally
 * same names when building dashboards, alerting rules or tests.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
public final class CConnectTelemetry
{
  // === span names ===
  /**
   * Span wrapping a single SFTP operation, including the opening and closing of the channel. These
   * are the only genuine outbound network calls in ph-oton, which is why the span kind is
   * <code>CLIENT</code> and not <code>INTERNAL</code> - a hanging remote server then shows up as a
   * gap in the trace instead of as an unexplained slow request.
   */
  public static final String SPAN_SFTP_EXECUTE = "photon.sftp.execute";

  // === metric instrument names ===
  /** Counter: number of SFTP operations - successful and failed ones. */
  public static final String METRIC_SFTP_OPERATIONS = "photon.sftp.operations";
  /** Histogram (ms): wall-clock duration of a single SFTP operation. */
  public static final String METRIC_SFTP_DURATION = "photon.sftp.duration";
  /**
   * Up-down counter: number of JSch sessions currently open. The two existing
   * {@code StatisticsManager} counters for created and destroyed sessions cannot be subtracted from
   * each other by a monitoring system, which is exactly what this instrument provides.
   */
  public static final String METRIC_SFTP_SESSIONS_OPEN = "photon.sftp.sessions.open";

  // === attribute keys ===
  /**
   * The remote host. Bounded per deployment - an application talks to a handful of configured
   * servers - and therefore safe as a metric attribute.
   */
  public static final String ATTR_SFTP_HOST = "photon.sftp.host";
  /** The remote port. Bounded per deployment. Span attribute only. */
  public static final String ATTR_SFTP_PORT = "photon.sftp.port";
  /**
   * The display name of the {@code IChannelSftpRunnable}.
   * <p>
   * <b>Span attribute only - deliberately never a metric attribute.</b> The display name exists for
   * error messages and is free text that typically embeds the remote file name, which is unbounded.
   * Remote paths, file names, user names and of course credentials must never appear on a metric.
   */
  public static final String ATTR_SFTP_OPERATION = "photon.sftp.operation";
  /** Whether the SFTP operation succeeded. */
  public static final String ATTR_SFTP_SUCCESS = "photon.sftp.success";

  // === metric units ===
  /** Unit for all SFTP operation counting instruments. */
  public static final String UNIT_OPERATION = "{operation}";
  /** Unit for all session counting instruments. */
  public static final String UNIT_SESSION = "{session}";
  /** Unit for all duration instruments. */
  public static final String UNIT_MILLIS = "ms";

  private CConnectTelemetry ()
  {}
}
