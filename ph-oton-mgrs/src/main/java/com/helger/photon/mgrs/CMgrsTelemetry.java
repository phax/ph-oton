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
package com.helger.photon.mgrs;

import com.helger.annotation.concurrent.Immutable;

/**
 * Constant span, metric and attribute names emitted by the ph-oton-mgrs module via the vendor
 * neutral ph-telemetry facade. Centralized here, so that applications can reference the literally
 * same names when building dashboards, alerting rules or tests.<br>
 * Note: the long running job names live in
 * {@link com.helger.photon.mgrs.longrun.CLongRunningJobTelemetry}, where they have been public API
 * since 10.4.0.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
public final class CMgrsTelemetry
{
  // === span names ===
  /**
   * Span wrapping the execution of a single system migration. Nothing is emitted for a migration
   * that was already performed and is therefore skipped.
   */
  public static final String SPAN_MIGRATION_EXECUTE = "photon.migration.execute";

  // === metric instrument names ===
  /** Counter: number of system migrations that were really executed - successfully or not. */
  public static final String METRIC_MIGRATIONS_EXECUTED = "photon.migration.executed";
  /** Histogram (ms): wall-clock duration of a single system migration. */
  public static final String METRIC_MIGRATION_DURATION = "photon.migration.duration";

  // === attribute keys ===
  /**
   * The ID of the migration. Migration IDs are string literals in application source code, so they
   * are bounded and grow slowly - at most one per release. This is the rare case where an ID
   * <em>is</em> an acceptable metric dimension; do not copy the pattern to an unbounded ID.
   */
  public static final String ATTR_MIGRATION_ID = "photon.migration.id";
  /** Whether the migration succeeded. */
  public static final String ATTR_MIGRATION_SUCCESS = "photon.migration.success";
  /**
   * How the migration failed - {@link #VALUE_FAILURE_NONE}, {@link #VALUE_FAILURE_BUSINESS} or
   * {@link #VALUE_FAILURE_TECHNICAL}.
   */
  public static final String ATTR_MIGRATION_FAILURE_KIND = "photon.migration.failure.kind";

  // === attribute values ===
  /** Value of {@link #ATTR_MIGRATION_FAILURE_KIND}: the migration succeeded. */
  public static final String VALUE_FAILURE_NONE = "none";
  /**
   * Value of {@link #ATTR_MIGRATION_FAILURE_KIND}: the migration ran to completion but reported a
   * failure itself.
   */
  public static final String VALUE_FAILURE_BUSINESS = "business";
  /** Value of {@link #ATTR_MIGRATION_FAILURE_KIND}: the migration threw an exception. */
  public static final String VALUE_FAILURE_TECHNICAL = "technical";

  // === metric units ===
  /** Unit for all migration counting instruments. */
  public static final String UNIT_MIGRATION = "{migration}";
  /** Unit for all duration instruments. */
  public static final String UNIT_MILLIS = "ms";

  private CMgrsTelemetry ()
  {}
}
