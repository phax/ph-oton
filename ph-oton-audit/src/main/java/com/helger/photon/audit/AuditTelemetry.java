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

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.base.state.ESuccess;
import com.helger.telemetry.TelemetryAttributes;

/**
 * Emits the ph-telemetry metrics for the audit items created by {@link AbstractAuditor}. All
 * emission happens through the vendor neutral ph-telemetry facades, so without a registered SPI
 * everything degrades to cheap no-ops.<br>
 * Only the bounded {@link EAuditActionType} is used as a dimension - never the audit action string,
 * the audit arguments or the user ID.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
final class AuditTelemetry
{
  private AuditTelemetry ()
  {}

  /**
   * Count a created audit item.
   *
   * @param eActionType
   *        The type of the audited action. May not be <code>null</code>.
   * @param eSuccess
   *        Whether the audited action was successful. May not be <code>null</code>.
   */
  static void onAuditItemCreated (@NonNull final EAuditActionType eActionType, @NonNull final ESuccess eSuccess)
  {
    AuditMetrics.AUDIT_ITEMS.add (1,
                                  TelemetryAttributes.builder ()
                                                     .put (CAuditTelemetry.ATTR_AUDIT_ACTION_TYPE,
                                                           eActionType.getID ())
                                                     .put (CAuditTelemetry.ATTR_AUDIT_SUCCESS, eSuccess.isSuccess ())
                                                     .build ());
  }
}
