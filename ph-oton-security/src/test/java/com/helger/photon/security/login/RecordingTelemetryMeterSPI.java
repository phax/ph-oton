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
package com.helger.photon.security.login;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.LongSupplier;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.style.IsSPIImplementation;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.CommonsLinkedHashMap;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsOrderedMap;
import com.helger.telemetry.ITelemetryCounter;
import com.helger.telemetry.ITelemetryGauge;
import com.helger.telemetry.ITelemetryHistogram;
import com.helger.telemetry.ITelemetryMeterSPI;
import com.helger.telemetry.ITelemetryUpDownCounter;
import com.helger.telemetry.TelemetryAttributes;

/**
 * A recording {@link ITelemetryMeterSPI} for the ph-oton-security tests.<br>
 * It is registered via the regular SPI mechanism instead of
 * {@link com.helger.telemetry.TelemetryMetrics#install(ITelemetryMeterSPI)}, because the
 * instruments of {@code LoginMetrics} and {@code LoginThrottleMetrics} are resolved once in their
 * static initializers. Other test classes of this module log users in and would therefore bind
 * those instruments to the no-op meter before a telemetry test could install anything.
 *
 * @author Philip Helger
 */
@IsSPIImplementation
public final class RecordingTelemetryMeterSPI implements ITelemetryMeterSPI
{
  /** A single recorded measurement of an instrument. */
  public record Measurement (String sInstrument, double dValue, ICommonsOrderedMap <String, Object> aAttrs)
  {}

  private static final ICommonsList <Measurement> MEASUREMENTS = new CommonsArrayList <> (new CopyOnWriteArrayList <> ());
  private static final ICommonsOrderedMap <String, LongSupplier> GAUGES = new CommonsLinkedHashMap <> ();

  @NonNull
  private static ICommonsOrderedMap <String, Object> _toMap (@NonNull final TelemetryAttributes aAttrs)
  {
    final ICommonsOrderedMap <String, Object> ret = new CommonsLinkedHashMap <> ();
    aAttrs.forEach (new TelemetryAttributes.IVisitor ()
    {
      public void onString (@NonNull final String sKey, @NonNull final String sValue)
      {
        ret.put (sKey, sValue);
      }

      public void onLong (@NonNull final String sKey, final long nValue)
      {
        ret.put (sKey, Long.valueOf (nValue));
      }

      public void onDouble (@NonNull final String sKey, final double dValue)
      {
        ret.put (sKey, Double.valueOf (dValue));
      }

      public void onBoolean (@NonNull final String sKey, final boolean bValue)
      {
        ret.put (sKey, Boolean.valueOf (bValue));
      }
    });
    return ret;
  }

  private static void _record (@NonNull final String sName, final double dValue, @NonNull final TelemetryAttributes aAttrs)
  {
    MEASUREMENTS.add (new Measurement (sName, dValue, _toMap (aAttrs)));
  }

  /**
   * Forget all recorded measurements. To be called before each test.
   */
  public static void clearRecordings ()
  {
    MEASUREMENTS.clear ();
  }

  /**
   * @return All recorded measurements, in the order they were recorded. Never <code>null</code>.
   */
  @NonNull
  public static ICommonsList <Measurement> getAllMeasurements ()
  {
    return MEASUREMENTS;
  }

  /**
   * @param sInstrument
   *        The instrument name to look for. May not be <code>null</code>.
   * @return All recorded measurements of the provided instrument. Never <code>null</code>.
   */
  @NonNull
  public static ICommonsList <Measurement> getMeasurements (@NonNull final String sInstrument)
  {
    return MEASUREMENTS.getAll (x -> x.sInstrument ().equals (sInstrument));
  }

  /**
   * @param sInstrument
   *        The gauge name to look for. May not be <code>null</code>.
   * @return The value supplier of the registered gauge, or <code>null</code> if the gauge was never
   *         created or is already closed.
   */
  @Nullable
  public static LongSupplier getGaugeSupplier (@NonNull final String sInstrument)
  {
    return GAUGES.get (sInstrument);
  }

  @NonNull
  public ITelemetryCounter createCounter (@NonNull final String sName,
                                          @Nullable final String sDescription,
                                          @Nullable final String sUnit)
  {
    return (nValue, aAttrs) -> _record (sName, nValue, aAttrs);
  }

  @NonNull
  public ITelemetryUpDownCounter createUpDownCounter (@NonNull final String sName,
                                                      @Nullable final String sDescription,
                                                      @Nullable final String sUnit)
  {
    return (nValue, aAttrs) -> _record (sName, nValue, aAttrs);
  }

  @NonNull
  public ITelemetryHistogram createHistogram (@NonNull final String sName,
                                              @Nullable final String sDescription,
                                              @Nullable final String sUnit)
  {
    return (dValue, aAttrs) -> _record (sName, dValue, aAttrs);
  }

  @NonNull
  public ITelemetryGauge createGauge (@NonNull final String sName,
                                      @Nullable final String sDescription,
                                      @Nullable final String sUnit,
                                      @NonNull final LongSupplier aSupplier)
  {
    GAUGES.put (sName, aSupplier);
    return () -> GAUGES.remove (sName);
  }
}
