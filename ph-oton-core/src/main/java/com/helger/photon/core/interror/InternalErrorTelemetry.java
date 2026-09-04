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
package com.helger.photon.core.interror;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.Immutable;
import com.helger.photon.core.CCoreTelemetry;
import com.helger.telemetry.TelemetryAttributes;

/**
 * Emits the ph-telemetry metrics for the internal errors handled by {@link InternalErrorHandler}.
 * All emission happens through the vendor neutral ph-telemetry facades, so without a registered SPI
 * everything degrades to cheap no-ops.<br>
 * Neither the throwable message nor the unique internal error ID is ever used as a metric attribute
 * - both are unbounded.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
final class InternalErrorTelemetry
{
  private InternalErrorTelemetry ()
  {}

  /**
   * Count a handled internal error.
   *
   * @param t
   *        The throwable that caused the internal error. May be <code>null</code>.
   */
  static void onInternalError (@Nullable final Throwable t)
  {
    final String sType = t == null ? CCoreTelemetry.VALUE_ERROR_TYPE_NONE : t.getClass ().getName ();
    InternalErrorMetrics.INTERNAL_ERRORS.add (1,
                                              TelemetryAttributes.builder ()
                                                                 .put (CCoreTelemetry.ATTR_INTERNAL_ERROR_TYPE, sType)
                                                                 .build ());
  }

  /**
   * Count an internal error notification mail that was not sent.
   *
   * @param sReason
   *        One of the <code>CCoreTelemetry.VALUE_SUPPRESS_*</code> constants. May neither be
   *        <code>null</code> nor empty.
   */
  static void onMailSuppressed (@NonNull @Nonempty final String sReason)
  {
    InternalErrorMetrics.MAILS_SUPPRESSED.add (1,
                                               TelemetryAttributes.builder ()
                                                                  .put (CCoreTelemetry.ATTR_INTERNAL_ERROR_SUPPRESS_REASON,
                                                                        sReason)
                                                                  .build ());
  }
}
