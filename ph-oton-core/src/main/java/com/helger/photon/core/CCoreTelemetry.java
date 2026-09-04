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
package com.helger.photon.core;

import com.helger.annotation.concurrent.Immutable;

/**
 * Constant span, metric and attribute names emitted by the ph-oton-core module via the vendor
 * neutral ph-telemetry facade. Centralized here, so that applications can reference the literally
 * same names when building dashboards, alerting rules or tests.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
public final class CCoreTelemetry
{
  // === span names ===
  /**
   * Span wrapping the whole handling of a single UI page request - it is started and closed in
   * {@link com.helger.photon.core.servlet.AbstractApplicationXServletHandler#handleRequest(com.helger.web.scope.IRequestWebScopeWithoutResponse, com.helger.servlet.response.UnifiedResponse)}.
   * The HTML serialization happens in a nested span.
   */
  public static final String SPAN_PAGE_REQUEST = "photon.page.request";

  // === span event names ===
  /**
   * Event on {@link #SPAN_PAGE_REQUEST}, if the request ended in a Post-Redirect-Get instead of an
   * HTML response. That is a regular control flow and explicitly not an error.
   */
  public static final String EVENT_FORCED_REDIRECT = "photon.page.forcedredirect";

  // === metric instrument names ===
  /** Counter: number of handled UI page requests - successful and failed ones. */
  public static final String METRIC_PAGE_REQUESTS = "photon.page.requests";
  /** Histogram (ms): wall-clock duration of handling one UI page request. */
  public static final String METRIC_PAGE_DURATION = "photon.page.duration";
  /** Counter: number of HTTP sessions that were created. */
  public static final String METRIC_SESSIONS_CREATED = "photon.http.sessions.created";
  /**
   * Up-down counter: number of HTTP sessions currently active. Note that a servlet container does
   * not necessarily deliver a "session destroyed" event for every session in every crash or restart
   * scenario, so this is a good operational signal but no accounting record.
   */
  public static final String METRIC_SESSIONS_ACTIVE = "photon.http.sessions.active";
  /** Histogram (ms): wall-clock duration of the servlet context initialization. */
  public static final String METRIC_STARTUP_DURATION = "photon.app.startup.duration";
  /** Histogram (ms): wall-clock duration of the servlet context destruction. */
  public static final String METRIC_SHUTDOWN_DURATION = "photon.app.shutdown.duration";
  /** Counter: number of internal errors, by the class name of the causing throwable. */
  public static final String METRIC_INTERNAL_ERRORS = "photon.internalerror.count";
  /**
   * Counter: number of internal error notification mails that were <em>not</em> sent, by reason.
   * This is what makes a silently mis-configured notification setup visible.
   */
  public static final String METRIC_INTERNAL_ERROR_MAILS_SUPPRESSED = "photon.internalerror.mail.suppressed";

  // === attribute keys ===
  /** Whether the UI page request was handled successfully. */
  public static final String ATTR_PAGE_SUCCESS = "photon.page.success";
  /**
   * The class name of the throwable that caused an internal error, or {@link #VALUE_ERROR_TYPE_NONE}
   * if no throwable is present. The throwable <em>message</em> is unbounded - it usually embeds IDs,
   * paths or SQL - and must never become a metric attribute.
   */
  public static final String ATTR_INTERNAL_ERROR_TYPE = "photon.internalerror.type";
  /** The reason why an internal error notification mail was not sent. */
  public static final String ATTR_INTERNAL_ERROR_SUPPRESS_REASON = "photon.internalerror.suppress.reason";

  // === attribute values ===
  /** Value of {@link #ATTR_INTERNAL_ERROR_TYPE} if the internal error has no throwable. */
  public static final String VALUE_ERROR_TYPE_NONE = "none";
  /**
   * Value of {@link #ATTR_INTERNAL_ERROR_SUPPRESS_REASON}: the same error occurred too often and
   * only every Nth occurrence is sent.
   */
  public static final String VALUE_SUPPRESS_OCCURRENCE_LIMIT = "occurrence-limit";
  /** Value of {@link #ATTR_INTERNAL_ERROR_SUPPRESS_REASON}: no sender address is configured. */
  public static final String VALUE_SUPPRESS_NO_SENDER = "no-sender";
  /** Value of {@link #ATTR_INTERNAL_ERROR_SUPPRESS_REASON}: no receiver address is configured. */
  public static final String VALUE_SUPPRESS_NO_RECEIVER = "no-receiver";
  /** Value of {@link #ATTR_INTERNAL_ERROR_SUPPRESS_REASON}: no SMTP settings are configured. */
  public static final String VALUE_SUPPRESS_NO_SMTP_SETTINGS = "no-smtp-settings";

  // === metric units ===
  /** Unit for all page request counting instruments. */
  public static final String UNIT_REQUEST = "{request}";
  /** Unit for all HTTP session counting instruments. */
  public static final String UNIT_SESSION = "{session}";
  /** Unit for all internal error counting instruments. */
  public static final String UNIT_ERROR = "{error}";
  /** Unit for all duration instruments. */
  public static final String UNIT_MILLIS = "ms";

  private CCoreTelemetry ()
  {}
}
