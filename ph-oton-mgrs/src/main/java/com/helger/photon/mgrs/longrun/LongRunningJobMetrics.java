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
package com.helger.photon.mgrs.longrun;

import com.helger.annotation.concurrent.Immutable;
import com.helger.telemetry.ITelemetryCounter;
import com.helger.telemetry.ITelemetryHistogram;
import com.helger.telemetry.ITelemetryUpDownCounter;
import com.helger.telemetry.TelemetryMetrics;

/**
 * Central registry of the named metric instruments emitted for long running jobs. Each instrument is
 * created once at class-load time via the vendor neutral {@link TelemetryMetrics} facade - if no
 * {@code ITelemetryMeterSPI} is registered, the underlying instruments are cheap no-ops, so
 * referencing this class in a deployment without an observability backend has no cost.
 *
 * @author Philip Helger
 * @since 10.4.0
 */
@Immutable
public final class LongRunningJobMetrics
{
  /** Long running jobs started, by job type ID. */
  public static final ITelemetryCounter JOBS_STARTED = TelemetryMetrics.counter (CLongRunningJobTelemetry.METRIC_JOBS_STARTED,
                                                                                 "Long running jobs that were started",
                                                                                 CLongRunningJobTelemetry.UNIT_JOB);

  /** Long running jobs ended, by job type ID, success and result type. */
  public static final ITelemetryCounter JOBS_ENDED = TelemetryMetrics.counter (CLongRunningJobTelemetry.METRIC_JOBS_ENDED,
                                                                               "Long running jobs that were ended - successfully or not",
                                                                               CLongRunningJobTelemetry.UNIT_JOB);

  /** Long running jobs currently running, by job type ID. */
  public static final ITelemetryUpDownCounter JOBS_RUNNING = TelemetryMetrics.upDownCounter (CLongRunningJobTelemetry.METRIC_JOBS_RUNNING,
                                                                                             "Long running jobs currently running",
                                                                                             CLongRunningJobTelemetry.UNIT_JOB);

  /** Wall-clock duration of a long running job, by job type ID and success. */
  public static final ITelemetryHistogram JOB_DURATION = TelemetryMetrics.histogram (CLongRunningJobTelemetry.METRIC_JOB_DURATION,
                                                                                     "Wall-clock duration of a single long running job execution",
                                                                                     CLongRunningJobTelemetry.UNIT_MILLIS);

  private LongRunningJobMetrics ()
  {}
}
