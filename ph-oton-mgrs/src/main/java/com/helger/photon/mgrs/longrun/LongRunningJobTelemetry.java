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

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.concurrent.Immutable;
import com.helger.base.state.ESuccess;
import com.helger.telemetry.ETelemetrySpanKind;
import com.helger.telemetry.ITelemetrySpan;
import com.helger.telemetry.Telemetry;
import com.helger.telemetry.TelemetryAttributes;

/**
 * Emits the ph-telemetry spans and metrics for the long running jobs handled by
 * {@link LongRunningJobManager}. All emission happens through the vendor neutral ph-telemetry
 * facades, so without a registered SPI everything degrades to cheap no-ops.
 *
 * @author Philip Helger
 * @since 10.4.0
 */
@Immutable
final class LongRunningJobTelemetry
{
  private static final Logger LOGGER = LoggerFactory.getLogger (LongRunningJobTelemetry.class);

  private LongRunningJobTelemetry ()
  {}

  /**
   * @param aJobData
   *        The job data to build the attributes from. May not be <code>null</code>.
   * @return The low cardinality attributes shared by all instruments. Never <code>null</code>.
   */
  @NonNull
  private static TelemetryAttributes _getJobAttrs (@NonNull final LongRunningJobData aJobData)
  {
    return TelemetryAttributes.builder ()
                              .put (CLongRunningJobTelemetry.ATTR_JOB_TYPE, aJobData.getJobType ())
                              .build ();
  }

  /**
   * Start the telemetry span covering the provided job execution and increase the respective
   * counters. Must be called before the job data is published to other threads, so that the span is
   * safely visible to the thread that eventually ends the job.
   *
   * @param aJobData
   *        The data of the job that was started. May not be <code>null</code>.
   */
  static void onJobStart (@NonNull final LongRunningJobData aJobData)
  {
    final ITelemetrySpan aSpan = Telemetry.startSpan (CLongRunningJobTelemetry.SPAN_EXECUTE,
                                                      ETelemetrySpanKind.INTERNAL);
    aSpan.setAttribute (CLongRunningJobTelemetry.ATTR_JOB_EXECUTION_ID, aJobData.getID ());
    if (aJobData.getJobType () != null)
      aSpan.setAttribute (CLongRunningJobTelemetry.ATTR_JOB_TYPE, aJobData.getJobType ());
    if (aJobData.getStartingUserID () != null)
      aSpan.setAttribute (CLongRunningJobTelemetry.ATTR_JOB_USER_ID, aJobData.getStartingUserID ());
    aJobData.setTelemetrySpan (aSpan);

    final TelemetryAttributes aAttrs = _getJobAttrs (aJobData);
    LongRunningJobMetrics.JOBS_STARTED.add (1, aAttrs);
    LongRunningJobMetrics.JOBS_RUNNING.add (1, aAttrs);
  }

  /**
   * Emit the end-of-job metrics and close the span that was opened in
   * {@link #onJobStart(LongRunningJobData)}.
   *
   * @param aJobData
   *        The data of the job that ended. Must already be ended. May not be <code>null</code>.
   * @param eExecSuccess
   *        The technical success indicator. May not be <code>null</code>.
   * @param aResult
   *        The job result. May not be <code>null</code>.
   */
  static void onJobEnd (@NonNull final LongRunningJobData aJobData,
                        @NonNull final ESuccess eExecSuccess,
                        @NonNull final LongRunningJobResult aResult)
  {
    final boolean bSuccess = eExecSuccess.isSuccess ();
    final String sResultTypeID = aResult.getType ().getID ();

    // Must use the same attributes as in onJobStart, so that the up-down counter nets out to 0
    LongRunningJobMetrics.JOBS_RUNNING.add (-1, _getJobAttrs (aJobData));

    LongRunningJobMetrics.JOBS_ENDED.add (1,
                                          TelemetryAttributes.builder ()
                                                             .put (CLongRunningJobTelemetry.ATTR_JOB_TYPE,
                                                                   aJobData.getJobType ())
                                                             .put (CLongRunningJobTelemetry.ATTR_JOB_SUCCESS, bSuccess)
                                                             .put (CLongRunningJobTelemetry.ATTR_JOB_RESULT_TYPE,
                                                                   sResultTypeID)
                                                             .build ());

    LongRunningJobMetrics.JOB_DURATION.record (aJobData.getDuration ().toMillis (),
                                               TelemetryAttributes.builder ()
                                                                  .put (CLongRunningJobTelemetry.ATTR_JOB_TYPE,
                                                                        aJobData.getJobType ())
                                                                  .put (CLongRunningJobTelemetry.ATTR_JOB_SUCCESS,
                                                                        bSuccess)
                                                                  .build ());

    @SuppressWarnings ("resource")
    final ITelemetrySpan aSpan = aJobData.getTelemetrySpan ();
    if (aSpan != null)
    {
      aSpan.setAttribute (CLongRunningJobTelemetry.ATTR_JOB_SUCCESS, bSuccess);
      aSpan.setAttribute (CLongRunningJobTelemetry.ATTR_JOB_RESULT_TYPE, sResultTypeID);
      if (bSuccess)
        aSpan.setStatusOk ();
      else
        aSpan.setStatusError ("Long running job '" + aJobData.getID () + "' failed");

      // A span is bound to the thread that created it - an OpenTelemetry backend makes the span the
      // current one of that thread. Closing it from a different thread would detach the wrong
      // context, so at least make that misuse visible.
      final Thread aStartThread = aJobData.getTelemetrySpanThread ();
      if (aStartThread != null && aStartThread != Thread.currentThread ())
        LOGGER.warn ("The long running job '" +
                     aJobData.getID () +
                     "' was started on thread '" +
                     aStartThread.getName () +
                     "' but is ended on thread '" +
                     Thread.currentThread ().getName () +
                     "'. The telemetry span is closed nevertheless, to avoid leaking it, but the " +
                     "trace context of both threads may be inconsistent.");
      aSpan.close ();

      // Don't retain the ended span in the job result
      aJobData.setTelemetrySpan (null);
    }
  }
}
