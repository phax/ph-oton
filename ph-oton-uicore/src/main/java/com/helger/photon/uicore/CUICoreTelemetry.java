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
package com.helger.photon.uicore;

import com.helger.annotation.concurrent.Immutable;

/**
 * Constant span, metric and attribute names emitted by the ph-oton-uicore module via the vendor
 * neutral ph-telemetry facade. Centralized here, so that applications can reference the literally
 * same names when building dashboards, alerting rules or tests.
 *
 * @author Philip Helger
 * @since 10.5.1
 */
@Immutable
public final class CUICoreTelemetry
{
  // === span names ===
  /**
   * Span wrapping the content creation of a single web page - it is started and closed in
   * {@link com.helger.photon.uicore.page.AbstractWebPage#getContent(com.helger.photon.uicore.page.IWebPageExecutionContext)}.
   * It nests inside the surrounding page request span, if one is present.
   */
  public static final String SPAN_PAGE_CONTENT = "photon.webpage.content";

  // === span event names ===
  /**
   * Event on {@link #SPAN_PAGE_CONTENT}, if the content creation ended in a Post-Redirect-Get -
   * e.g. after a successful form submission. That is a regular control flow and explicitly not an
   * error.
   */
  public static final String EVENT_FORCED_REDIRECT = "photon.webpage.forcedredirect";

  // === metric instrument names ===
  /** Counter: number of web pages for which content creation was invoked. */
  public static final String METRIC_PAGE_CONTENT = "photon.webpage.rendered";
  /** Histogram (ms): wall-clock duration of the content creation of a single web page. */
  public static final String METRIC_PAGE_CONTENT_DURATION = "photon.webpage.duration";

  // === attribute keys ===
  /**
   * The ID of the web page as returned by
   * {@link com.helger.photon.core.page.AbstractPage#getID()}. It is bounded by the number of
   * screens of an application and is therefore the low cardinality dimension to group metrics by.
   */
  public static final String ATTR_PAGE_ID = "photon.webpage.id";
  /**
   * Whether the page content was really created - it is <code>false</code>, if
   * {@code isValidToDisplayPage (...)} rejected the display of the page.
   */
  public static final String ATTR_PAGE_DISPLAYED = "photon.webpage.displayed";
  /**
   * The display locale of the request. Only used as a span attribute - never as a metric attribute,
   * to keep the number of metric dimensions small.
   */
  public static final String ATTR_PAGE_LOCALE = "photon.webpage.locale";

  // === metric units ===
  /** Unit for all web page counting instruments. */
  public static final String UNIT_PAGE = "{page}";
  /** Unit for all duration instruments. */
  public static final String UNIT_MILLIS = "ms";

  private CUICoreTelemetry ()
  {}
}
