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

import com.helger.annotation.concurrent.Immutable;

/**
 * Constant metric and attribute names emitted by the ph-oton-audit module via the vendor neutral
 * ph-telemetry facade. Centralized here, so that applications can reference the literally same
 * names when building dashboards, alerting rules or tests.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
public final class CAuditTelemetry
{
  // === metric instrument names ===
  /** Counter: number of created audit items, by action type and success. */
  public static final String METRIC_AUDIT_ITEMS = "photon.audit.items";
  /**
   * Observable gauge: number of audit items currently waiting in the queue of an
   * {@link AsynchronousAuditor}. A queue that grows because the backing store became slow is
   * invisible today until memory runs out.
   */
  public static final String METRIC_AUDIT_QUEUE_LENGTH = "photon.audit.queue.length";

  // === attribute keys ===
  /**
   * The ID of the {@link EAuditActionType}, e.g. <code>create</code>. The enum has exactly five
   * values, so this is a bounded dimension.<br>
   * Note that the audit <em>action string</em> and the audit <em>arguments</em> are deliberately
   * never used: they are produced by an {@link IAuditActionStringProvider} and contain object IDs,
   * user IDs and field values - unbounded, and frequently personal data. The same holds for the
   * user ID from the {@code ICurrentUserIDProvider}.
   */
  public static final String ATTR_AUDIT_ACTION_TYPE = "photon.audit.action.type";
  /** Whether the audited action was successful. */
  public static final String ATTR_AUDIT_SUCCESS = "photon.audit.success";

  // === metric units ===
  /** Unit for all audit item counting instruments. */
  public static final String UNIT_ITEM = "{item}";

  private CAuditTelemetry ()
  {}
}
