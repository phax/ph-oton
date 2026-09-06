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

import java.util.function.LongSupplier;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.style.IsSPIImplementation;
import com.helger.telemetry.ITelemetryCounter;
import com.helger.telemetry.ITelemetryGauge;
import com.helger.telemetry.ITelemetryHistogram;
import com.helger.telemetry.ITelemetryMeterSPI;
import com.helger.telemetry.ITelemetryUpDownCounter;
import com.helger.telemetry.mock.CapturingTelemetry;

/**
 * An {@link ITelemetryMeterSPI} for the ph-oton-audit tests that delegates every instrument to the
 * shared {@link CapturingTelemetry} in {@link #TELEMETRY}.<br>
 * It is registered via the regular SPI mechanism instead of {@link CapturingTelemetry#install()},
 * because the instrument of {@code AuditMetrics} is resolved once in its static initializer. Other
 * test classes of this module create audit items and would therefore bind that instrument to the
 * no-op meter before a telemetry test could install anything.
 *
 * @author Philip Helger
 */
@IsSPIImplementation
public final class RecordingTelemetryMeterSPI implements ITelemetryMeterSPI
{
  /** The capturing telemetry all created instruments record into. */
  public static final CapturingTelemetry TELEMETRY = new CapturingTelemetry ();

  @NonNull
  public ITelemetryCounter createCounter (@NonNull final String sName,
                                          @Nullable final String sDescription,
                                          @Nullable final String sUnit)
  {
    return TELEMETRY.createCounter (sName, sDescription, sUnit);
  }

  @NonNull
  public ITelemetryUpDownCounter createUpDownCounter (@NonNull final String sName,
                                                      @Nullable final String sDescription,
                                                      @Nullable final String sUnit)
  {
    return TELEMETRY.createUpDownCounter (sName, sDescription, sUnit);
  }

  @NonNull
  public ITelemetryHistogram createHistogram (@NonNull final String sName,
                                              @Nullable final String sDescription,
                                              @Nullable final String sUnit)
  {
    return TELEMETRY.createHistogram (sName, sDescription, sUnit);
  }

  @NonNull
  public ITelemetryGauge createGauge (@NonNull final String sName,
                                      @Nullable final String sDescription,
                                      @Nullable final String sUnit,
                                      @NonNull final LongSupplier aSupplier)
  {
    return TELEMETRY.createGauge (sName, sDescription, sUnit, aSupplier);
  }
}
