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

/**
 * Constant span, metric and attribute names emitted by the {@link PhotonWorkerPool} via the vendor
 * neutral ph-telemetry facade. Centralized here, so that applications can reference the literally
 * same names when building dashboards, alerting rules or tests.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
public final class CIOTelemetry
{
  // === span names ===
  /**
   * Span wrapping the execution of a single asynchronous task on a worker pool thread. The whole
   * task body runs on one thread, so the span is started and ended on the same thread.
   */
  public static final String SPAN_WORKER_EXECUTE = "photon.worker.execute";

  // === metric instrument names ===
  /** Counter: number of asynchronous tasks that were started on a worker pool thread. */
  public static final String METRIC_WORKER_STARTED = "photon.worker.started";
  /**
   * Counter: number of asynchronous tasks that ended - successfully or not. This may exceed
   * {@link #METRIC_WORKER_STARTED} by the number of tasks that were submitted but never reached a
   * worker thread at all, e.g. because the executor was already shut down. That difference is
   * exactly the number of silently dropped tasks and is worth alerting on.
   */
  public static final String METRIC_WORKER_ENDED = "photon.worker.ended";
  /** Up-down counter: number of asynchronous tasks currently running. */
  public static final String METRIC_WORKER_RUNNING = "photon.worker.running";
  /** Histogram (ms): wall-clock duration of a single asynchronous task. */
  public static final String METRIC_WORKER_DURATION = "photon.worker.duration";

  // === attribute keys ===
  /**
   * The action name that the caller passed to
   * {@link PhotonWorkerPool#run(String, Runnable)} and friends.
   * <p>
   * <b>Span attribute only - deliberately never a metric attribute.</b> The action name is free
   * text supplied by the caller, and callers really do interpolate unbounded values into it, e.g.
   * <code>"Add '" + aParticipantID.getURIEncoded () + "' to the Directory"</code> in phoss-smp.
   * Using it as a metric dimension would blow up the time series cardinality. If an application
   * wants a per-action breakdown, it gets it from the span, not from the metrics.
   */
  public static final String ATTR_WORKER_ACTION = "photon.worker.action";
  /** Whether the asynchronous task completed without throwing. */
  public static final String ATTR_WORKER_SUCCESS = "photon.worker.success";

  // === metric units ===
  /** Unit for all task counting instruments. */
  public static final String UNIT_TASK = "{task}";
  /** Unit for all duration instruments. */
  public static final String UNIT_MILLIS = "ms";

  private CIOTelemetry ()
  {}
}
