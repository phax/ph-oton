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
package com.helger.photon.io;

import com.helger.annotation.concurrent.Immutable;
import com.helger.telemetry.ITelemetryCounter;
import com.helger.telemetry.ITelemetryHistogram;
import com.helger.telemetry.ITelemetryUpDownCounter;
import com.helger.telemetry.TelemetryMetrics;

/**
 * Central registry of the named metric instruments emitted for the asynchronous tasks of
 * {@link PhotonWorkerPool}. Each instrument is created once at class-load time via the vendor
 * neutral {@link TelemetryMetrics} facade - if no {@code ITelemetryMeterSPI} is registered, the
 * underlying instruments are cheap no-ops, so referencing this class in a deployment without an
 * observability backend has no cost.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
public final class PhotonWorkerPoolMetrics
{
  /** Asynchronous tasks that were started. */
  public static final ITelemetryCounter TASKS_STARTED = TelemetryMetrics.counter (CIOTelemetry.METRIC_WORKER_STARTED,
                                                                                  "Asynchronous tasks that were started on a worker pool thread",
                                                                                  CIOTelemetry.UNIT_TASK);

  /** Asynchronous tasks that ended, by success. */
  public static final ITelemetryCounter TASKS_ENDED = TelemetryMetrics.counter (CIOTelemetry.METRIC_WORKER_ENDED,
                                                                                "Asynchronous tasks that ended - successfully or not",
                                                                                CIOTelemetry.UNIT_TASK);

  /** Asynchronous tasks currently running. */
  public static final ITelemetryUpDownCounter TASKS_RUNNING = TelemetryMetrics.upDownCounter (CIOTelemetry.METRIC_WORKER_RUNNING,
                                                                                               "Asynchronous tasks currently running",
                                                                                               CIOTelemetry.UNIT_TASK);

  /** Wall-clock duration of a single asynchronous task, by success. */
  public static final ITelemetryHistogram TASK_DURATION = TelemetryMetrics.histogram (CIOTelemetry.METRIC_WORKER_DURATION,
                                                                                       "Wall-clock duration of a single asynchronous task",
                                                                                       CIOTelemetry.UNIT_MILLIS);

  private PhotonWorkerPoolMetrics ()
  {}
}
