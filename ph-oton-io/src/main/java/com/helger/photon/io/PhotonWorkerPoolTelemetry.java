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

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.telemetry.ITelemetrySpan;
import com.helger.telemetry.TelemetryAttributes;

/**
 * Emits the ph-telemetry span attributes and metrics for the asynchronous tasks handled by
 * {@link PhotonWorkerPool}. All emission happens through the vendor neutral ph-telemetry facades,
 * so without a registered SPI everything degrades to cheap no-ops.<br>
 * The caller supplied action name is used as a span attribute only - see
 * {@link CIOTelemetry#ATTR_WORKER_ACTION} for the reason.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
final class PhotonWorkerPoolTelemetry
{
  private PhotonWorkerPoolTelemetry ()
  {}

  /**
   * @param bSuccess
   *        Whether the task completed without throwing.
   * @return The bounded attributes shared by the end-of-task instruments. Never <code>null</code>.
   */
  @NonNull
  private static TelemetryAttributes _getSuccessAttrs (final boolean bSuccess)
  {
    return TelemetryAttributes.builder ().put (CIOTelemetry.ATTR_WORKER_SUCCESS, bSuccess).build ();
  }

  /**
   * Describe the started task on the span and count it.
   *
   * @param aSpan
   *        The span to fill. May not be <code>null</code>.
   * @param sActionName
   *        The caller supplied action name. May not be <code>null</code>.
   */
  static void onTaskStart (@NonNull final ITelemetrySpan aSpan, @NonNull final String sActionName)
  {
    aSpan.setAttribute (CIOTelemetry.ATTR_WORKER_ACTION, sActionName);
    PhotonWorkerPoolMetrics.TASKS_STARTED.add (1);
    // Must use the same (empty) attributes as in onTaskEnd, so that the up-down counter nets out
    // to 0
    PhotonWorkerPoolMetrics.TASKS_RUNNING.add (1);
  }

  /**
   * Mark the span of a task that completed without throwing.
   *
   * @param aSpan
   *        The span to mark. May not be <code>null</code>.
   */
  static void onTaskSuccess (@NonNull final ITelemetrySpan aSpan)
  {
    aSpan.setAttribute (CIOTelemetry.ATTR_WORKER_SUCCESS, true);
    aSpan.setStatusOk ();
  }

  /**
   * Mark the span of a task that threw. The exception is swallowed by the worker pool, so the
   * surrounding {@code Telemetry.withSpan (...)} never sees it and the span must be marked here.
   *
   * @param aSpan
   *        The span to mark. May not be <code>null</code>.
   * @param aException
   *        The exception that occurred. May not be <code>null</code>.
   */
  static void onTaskError (@NonNull final ITelemetrySpan aSpan, @NonNull final Exception aException)
  {
    aSpan.setAttribute (CIOTelemetry.ATTR_WORKER_SUCCESS, false);
    aSpan.recordException (aException);
    aSpan.setStatusError (aException.getMessage ());
  }

  /**
   * Emit the end-of-task metrics.
   *
   * @param bSuccess
   *        Whether the task completed without throwing.
   * @param nDurationMillis
   *        The wall-clock duration of the task in milliseconds.
   */
  static void onTaskEnd (final boolean bSuccess, final long nDurationMillis)
  {
    // Must use the same (empty) attributes as in onTaskStart
    PhotonWorkerPoolMetrics.TASKS_RUNNING.add (-1);
    PhotonWorkerPoolMetrics.TASKS_ENDED.add (1, _getSuccessAttrs (bSuccess));
    PhotonWorkerPoolMetrics.TASK_DURATION.record (nDurationMillis, _getSuccessAttrs (bSuccess));
  }

  /**
   * Count a task whose failure was not handled inside the task body - it either never reached a
   * worker thread at all (e.g. the executor was already shut down) or it failed with an
   * {@link Error}. There is no span and no duration in that case.
   */
  static void onTaskDropped ()
  {
    PhotonWorkerPoolMetrics.TASKS_ENDED.add (1, _getSuccessAttrs (false));
  }
}
