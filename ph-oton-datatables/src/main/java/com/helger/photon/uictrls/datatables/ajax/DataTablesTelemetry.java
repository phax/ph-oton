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

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.photon.uictrls.datatables.CDataTablesTelemetry;
import com.helger.telemetry.ITelemetrySpan;
import com.helger.telemetry.TelemetryAttributes;

/**
 * Emits the ph-telemetry span attributes and metrics for the server-side DataTables requests
 * handled by {@link AjaxExecutorDataTables}. All emission happens through the vendor neutral
 * ph-telemetry facades, so without a registered SPI everything degrades to cheap no-ops.<br>
 * No user entered search term ever reaches a span or a metric - see
 * {@link CDataTablesTelemetry#ATTR_DT_FILTERED}.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
final class DataTablesTelemetry
{
  private DataTablesTelemetry ()
  {}

  /**
   * @param bSorted
   *        Whether the request asked for a specific sort order.
   * @param bFiltered
   *        Whether a search was active.
   * @return The bounded attributes of the request instruments. Never <code>null</code>.
   */
  @NonNull
  private static TelemetryAttributes _getRequestAttrs (final boolean bSorted, final boolean bFiltered)
  {
    return TelemetryAttributes.builder ()
                              .put (CDataTablesTelemetry.ATTR_DT_SORTED, bSorted)
                              .put (CDataTablesTelemetry.ATTR_DT_FILTERED, bFiltered)
                              .build ();
  }

  /**
   * Describe the request on the span - only the two booleans, never the search terms.
   *
   * @param aSpan
   *        The span to fill. May not be <code>null</code>.
   * @param bSorted
   *        Whether the request asked for a specific sort order.
   * @param bFiltered
   *        Whether a search was active.
   */
  static void onRequestStart (@NonNull final ITelemetrySpan aSpan, final boolean bSorted, final boolean bFiltered)
  {
    aSpan.setAttribute (CDataTablesTelemetry.ATTR_DT_SORTED, bSorted);
    aSpan.setAttribute (CDataTablesTelemetry.ATTR_DT_FILTERED, bFiltered);
  }

  /**
   * Record the row count distribution of one request.
   *
   * @param nTotalRows
   *        The total number of rows the table holds.
   * @param nFilteredRows
   *        The number of rows that remained after filtering.
   */
  static void onRowsProcessed (final int nTotalRows, final int nFilteredRows)
  {
    DataTablesMetrics.ROWS_TOTAL.record (nTotalRows);
    DataTablesMetrics.ROWS_FILTERED.record (nFilteredRows);
  }

  /**
   * Emit the end-of-request metrics. Called for every code path, so that failed requests are timed
   * as well.
   *
   * @param bSorted
   *        Whether the request asked for a specific sort order.
   * @param bFiltered
   *        Whether a search was active.
   * @param nDurationMillis
   *        The wall-clock duration of the request in milliseconds.
   */
  static void onRequestEnd (final boolean bSorted, final boolean bFiltered, final long nDurationMillis)
  {
    DataTablesMetrics.DURATION.record (nDurationMillis, _getRequestAttrs (bSorted, bFiltered));
  }
}
