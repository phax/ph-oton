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
package com.helger.photon.audit;

import java.util.function.LongSupplier;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.telemetry.ITelemetryCounter;
import com.helger.telemetry.ITelemetryGauge;
import com.helger.telemetry.TelemetryMetrics;

/**
 * Central registry of the named metric instruments emitted for audit items. The counter is created
 * once at class-load time via the vendor neutral {@link TelemetryMetrics} facade - if no
 * {@code ITelemetryMeterSPI} is registered, it is a cheap no-op, so referencing this class in a
 * deployment without an observability backend has no cost.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
public final class AuditMetrics
{
  /** Created audit items, by action type and success. */
  public static final ITelemetryCounter AUDIT_ITEMS = TelemetryMetrics.counter (CAuditTelemetry.METRIC_AUDIT_ITEMS,
                                                                                "Created audit items",
                                                                                CAuditTelemetry.UNIT_ITEM);

  private AuditMetrics ()
  {}

  /**
   * Create the observable gauge over the queue length of an {@link AsynchronousAuditor}.
   * Deliberately not a constant of this class: the gauge is bound to the life time of one auditor
   * instance and must be closed in {@link AsynchronousAuditor#stop()}, so that a stopped auditor is
   * not kept alive by the gauge callback and a restarted one does not double-report.
   *
   * @param aSupplier
   *        The supplier of the current queue length. Invoked from a backend thread, so it must be
   *        cheap and thread safe. May not be <code>null</code>.
   * @return The gauge handle to be closed on shutdown. Never <code>null</code>.
   */
  @NonNull
  public static ITelemetryGauge createQueueLengthGauge (@NonNull final LongSupplier aSupplier)
  {
    return TelemetryMetrics.gauge (CAuditTelemetry.METRIC_AUDIT_QUEUE_LENGTH,
                                   "Audit items currently waiting in the asynchronous auditor queue",
                                   CAuditTelemetry.UNIT_ITEM,
                                   aSupplier);
  }
}
