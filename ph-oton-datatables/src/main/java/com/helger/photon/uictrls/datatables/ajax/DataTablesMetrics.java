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
package com.helger.photon.uictrls.datatables.ajax;

import com.helger.annotation.concurrent.Immutable;
import com.helger.photon.uictrls.datatables.CDataTablesTelemetry;
import com.helger.telemetry.ITelemetryHistogram;
import com.helger.telemetry.TelemetryMetrics;

/**
 * Central registry of the named metric instruments emitted for server-side DataTables requests.
 * Each instrument is created once at class-load time via the vendor neutral {@link TelemetryMetrics}
 * facade - if no {@code ITelemetryMeterSPI} is registered, the underlying instruments are cheap
 * no-ops, so referencing this class in a deployment without an observability backend has no cost.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
public final class DataTablesMetrics
{
  /** Wall-clock duration of handling one server-side DataTables request, by sorted and filtered. */
  public static final ITelemetryHistogram DURATION = TelemetryMetrics.histogram (CDataTablesTelemetry.METRIC_DT_DURATION,
                                                                                 "Wall-clock duration of handling one server-side DataTables request",
                                                                                 CDataTablesTelemetry.UNIT_MILLIS);

  /** Total number of rows the table holds. */
  public static final ITelemetryHistogram ROWS_TOTAL = TelemetryMetrics.histogram (CDataTablesTelemetry.METRIC_DT_ROWS_TOTAL,
                                                                                   "Total number of rows the table holds",
                                                                                   CDataTablesTelemetry.UNIT_ROW);

  /** Number of rows that remained after filtering. */
  public static final ITelemetryHistogram ROWS_FILTERED = TelemetryMetrics.histogram (CDataTablesTelemetry.METRIC_DT_ROWS_FILTERED,
                                                                                      "Number of rows that remained after filtering",
                                                                                      CDataTablesTelemetry.UNIT_ROW);

  private DataTablesMetrics ()
  {}
}
