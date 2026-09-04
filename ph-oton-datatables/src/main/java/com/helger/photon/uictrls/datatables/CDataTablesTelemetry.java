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
package com.helger.photon.uictrls.datatables;

import com.helger.annotation.concurrent.Immutable;

/**
 * Constant span, metric and attribute names emitted by the ph-oton-datatables module via the vendor
 * neutral ph-telemetry facade. Centralized here, so that applications can reference the literally
 * same names when building dashboards, alerting rules or tests.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
public final class CDataTablesTelemetry
{
  // === span names ===
  /**
   * Span wrapping the handling of a single server-side DataTables request. It nests inside the
   * <code>photon.ajax.invoke</code> span of the AJAX invoker.
   */
  public static final String SPAN_DT_REQUEST = "photon.datatables.request";
  /** Child span around the server-side sorting. */
  public static final String SPAN_DT_SORT = "photon.datatables.sort";
  /** Child span around the server-side filtering. */
  public static final String SPAN_DT_FILTER = "photon.datatables.filter";

  // === metric instrument names ===
  /** Histogram (ms): wall-clock duration of handling one server-side DataTables request. */
  public static final String METRIC_DT_DURATION = "photon.datatables.duration";
  /** Histogram: total number of rows the table holds. */
  public static final String METRIC_DT_ROWS_TOTAL = "photon.datatables.rows.total";
  /** Histogram: number of rows that remained after filtering. */
  public static final String METRIC_DT_ROWS_FILTERED = "photon.datatables.rows.filtered";

  // === attribute keys ===
  /** Whether the request asked for a specific sort order. */
  public static final String ATTR_DT_SORTED = "photon.datatables.sorted";
  /**
   * Whether a search was active for this request.
   * <p>
   * Only <em>whether</em> a search was active is recorded, never <em>what</em> was searched. The
   * DataTables request carries user entered search terms (<code>search[value]</code> and the per
   * column equivalents); those are unbounded and routinely contain personal data such as the names
   * or e-mail addresses being searched for, so they must never appear in a span attribute either.
   * <p>
   * The per-render object ID of the table is deliberately not used as a dimension either - it is
   * generated per render and is therefore not a stable table identifier. For a per-screen
   * breakdown use <code>photon.webpage.id</code> from the {@code AbstractWebPage} instrumentation.
   */
  public static final String ATTR_DT_FILTERED = "photon.datatables.filtered";

  // === metric units ===
  /** Unit for all row counting instruments. */
  public static final String UNIT_ROW = "{row}";
  /** Unit for all duration instruments. */
  public static final String UNIT_MILLIS = "ms";

  private CDataTablesTelemetry ()
  {}
}
