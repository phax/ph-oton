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
package com.helger.photon.mgrs.sysmigration;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.Immutable;
import com.helger.photon.mgrs.CMgrsTelemetry;
import com.helger.telemetry.ITelemetrySpan;
import com.helger.telemetry.TelemetryAttributes;

/**
 * Emits the ph-telemetry span attributes and metrics for the system migrations performed by
 * {@link SystemMigrationHelper}. All emission happens through the vendor neutral ph-telemetry
 * facades, so without a registered SPI everything degrades to cheap no-ops.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
final class SystemMigrationTelemetry
{
  private SystemMigrationTelemetry ()
  {}

  /**
   * Describe the started migration on the span.
   *
   * @param aSpan
   *        The span to fill. May not be <code>null</code>.
   * @param sMigrationID
   *        The ID of the migration. May neither be <code>null</code> nor empty.
   */
  static void onMigrationStart (@NonNull final ITelemetrySpan aSpan, @NonNull @Nonempty final String sMigrationID)
  {
    aSpan.setAttribute (CMgrsTelemetry.ATTR_MIGRATION_ID, sMigrationID);
  }

  /**
   * Mark the span of a migration that reported a failure without throwing. The surrounding
   * {@code Telemetry.withSpanVoid (...)} only sees thrown exceptions and would otherwise consider
   * the migration successful.
   *
   * @param aSpan
   *        The span to mark. May not be <code>null</code>.
   * @param sMigrationID
   *        The ID of the migration. May neither be <code>null</code> nor empty.
   * @param sErrorMsg
   *        The error message the migration reported. May be <code>null</code>.
   */
  static void onMigrationBusinessFailure (@NonNull final ITelemetrySpan aSpan,
                                          @NonNull @Nonempty final String sMigrationID,
                                          final String sErrorMsg)
  {
    aSpan.setAttribute (CMgrsTelemetry.ATTR_MIGRATION_SUCCESS, false);
    aSpan.setAttribute (CMgrsTelemetry.ATTR_MIGRATION_FAILURE_KIND, CMgrsTelemetry.VALUE_FAILURE_BUSINESS);
    aSpan.setStatusError ("System migration '" + sMigrationID + "' failed: " + sErrorMsg);
  }

  /**
   * Mark the span of a migration that threw. The exception is caught and logged by
   * {@link SystemMigrationHelper}, so the surrounding span helper never sees it.
   *
   * @param aSpan
   *        The span to mark. May not be <code>null</code>.
   * @param sMigrationID
   *        The ID of the migration. May neither be <code>null</code> nor empty.
   * @param aException
   *        The exception that occurred. May not be <code>null</code>.
   */
  static void onMigrationTechnicalFailure (@NonNull final ITelemetrySpan aSpan,
                                           @NonNull @Nonempty final String sMigrationID,
                                           @NonNull final RuntimeException aException)
  {
    aSpan.setAttribute (CMgrsTelemetry.ATTR_MIGRATION_SUCCESS, false);
    aSpan.setAttribute (CMgrsTelemetry.ATTR_MIGRATION_FAILURE_KIND, CMgrsTelemetry.VALUE_FAILURE_TECHNICAL);
    aSpan.recordException (aException);
    aSpan.setStatusError ("System migration '" + sMigrationID + "' failed: " + aException.getMessage ());
  }

  /**
   * Mark the span of a successful migration.
   *
   * @param aSpan
   *        The span to mark. May not be <code>null</code>.
   */
  static void onMigrationSuccess (@NonNull final ITelemetrySpan aSpan)
  {
    aSpan.setAttribute (CMgrsTelemetry.ATTR_MIGRATION_SUCCESS, true);
    aSpan.setAttribute (CMgrsTelemetry.ATTR_MIGRATION_FAILURE_KIND, CMgrsTelemetry.VALUE_FAILURE_NONE);
    aSpan.setStatusOk ();
  }

  /**
   * Emit the end-of-migration metrics. Only called for migrations that were really executed - a
   * migration that was skipped because it already ran emits nothing.
   *
   * @param sMigrationID
   *        The ID of the migration. May neither be <code>null</code> nor empty.
   * @param sFailureKind
   *        One of the <code>CMgrsTelemetry.VALUE_FAILURE_*</code> constants. May not be
   *        <code>null</code>.
   * @param nDurationMillis
   *        The wall-clock duration of the migration in milliseconds.
   */
  static void onMigrationEnd (@NonNull @Nonempty final String sMigrationID,
                              @NonNull final String sFailureKind,
                              final long nDurationMillis)
  {
    SystemMigrationMetrics.MIGRATIONS_EXECUTED.add (1,
                                                    TelemetryAttributes.builder ()
                                                                       .put (CMgrsTelemetry.ATTR_MIGRATION_ID,
                                                                             sMigrationID)
                                                                       .put (CMgrsTelemetry.ATTR_MIGRATION_SUCCESS,
                                                                             CMgrsTelemetry.VALUE_FAILURE_NONE.equals (sFailureKind))
                                                                       .put (CMgrsTelemetry.ATTR_MIGRATION_FAILURE_KIND,
                                                                             sFailureKind)
                                                                       .build ());
    SystemMigrationMetrics.MIGRATION_DURATION.record (nDurationMillis,
                                                      TelemetryAttributes.builder ()
                                                                         .put (CMgrsTelemetry.ATTR_MIGRATION_ID,
                                                                               sMigrationID)
                                                                         .build ());
  }
}
