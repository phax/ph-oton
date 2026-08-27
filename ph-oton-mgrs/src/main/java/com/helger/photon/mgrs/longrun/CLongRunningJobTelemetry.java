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

/**
 * Constant span, metric and attribute names emitted by the {@link LongRunningJobManager} via the
 * vendor neutral ph-telemetry facade. Centralized here, so that applications can reference the
 * literally same names when building dashboards, alerting rules or tests.
 *
 * @author Philip Helger
 * @since 10.4.0
 */
@Immutable
public final class CLongRunningJobTelemetry
{
  // === span names ===
  /**
   * Span wrapping the whole execution of a single long running job - it is started in
   * {@link LongRunningJobManager#onStartJob(ILongRunningJob, String)} and closed in
   * {@link LongRunningJobManager#onEndJob(String, com.helger.base.state.ESuccess, LongRunningJobResult)}.
   */
  public static final String SPAN_EXECUTE = "photon.longrunningjob.execute";

  // === metric instrument names ===
  /** Counter: number of long running jobs that were started. */
  public static final String METRIC_JOBS_STARTED = "photon.longrunningjob.started";
  /** Counter: number of long running jobs that were ended - successfully or not. */
  public static final String METRIC_JOBS_ENDED = "photon.longrunningjob.ended";
  /** Up-down counter: number of long running jobs currently running. */
  public static final String METRIC_JOBS_RUNNING = "photon.longrunningjob.running";
  /** Histogram (ms): wall-clock duration of a single long running job execution. */
  public static final String METRIC_JOB_DURATION = "photon.longrunningjob.duration";

  // === attribute keys ===
  /**
   * The ID of the job <em>type</em> as returned by {@link ILongRunningJob#getJobID()}. This is the
   * low cardinality dimension to group metrics by. May be absent, if the job type ID is unknown.
   */
  public static final String ATTR_JOB_ID = "photon.job.id";
  /**
   * The unique ID of a single job <em>execution</em> as returned by
   * {@link LongRunningJobData#getID()}. Only used as a span attribute - never as a metric attribute,
   * because its cardinality is unbounded.
   */
  public static final String ATTR_JOB_EXECUTION_ID = "photon.job.execution.id";
  /** The ID of the user who started the job. May be absent. */
  public static final String ATTR_JOB_USER_ID = "photon.job.user.id";
  /** Whether the job execution was technically successful. */
  public static final String ATTR_JOB_SUCCESS = "photon.job.success";
  /**
   * The ID of the {@link ELongRunningJobResultType} of the job result, e.g. <code>text</code> or
   * <code>file</code>.
   */
  public static final String ATTR_JOB_RESULT_TYPE = "photon.job.result.type";

  // === metric units ===
  /** Unit for all job counting instruments. */
  public static final String UNIT_JOB = "{job}";
  /** Unit for all duration instruments. */
  public static final String UNIT_MILLIS = "ms";

  private CLongRunningJobTelemetry ()
  {}
}
