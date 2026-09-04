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
package com.helger.photon.mgrs.sysmigration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.LongSupplier;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import com.helger.base.state.SuccessWithValue;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.CommonsLinkedHashMap;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsOrderedMap;
import com.helger.dao.DAOException;
import com.helger.photon.app.mock.PhotonAppTestRule;
import com.helger.photon.mgrs.CMgrsTelemetry;
import com.helger.telemetry.ETelemetrySpanKind;
import com.helger.telemetry.ITelemetryCounter;
import com.helger.telemetry.ITelemetryGauge;
import com.helger.telemetry.ITelemetryHistogram;
import com.helger.telemetry.ITelemetryMeterSPI;
import com.helger.telemetry.ITelemetrySpan;
import com.helger.telemetry.ITelemetryTracerSPI;
import com.helger.telemetry.ITelemetryUpDownCounter;
import com.helger.telemetry.Telemetry;
import com.helger.telemetry.TelemetryAttributes;
import com.helger.telemetry.TelemetryMetrics;

/**
 * Test class for the ph-telemetry integration of {@link SystemMigrationHelper}.
 *
 * @author Philip Helger
 */
public final class SystemMigrationTelemetryTest
{
  private static final String MIGRATION_ID = "unit-test-migration";

  /** A span that only records what was set on it. */
  private static final class CapturingSpan implements ITelemetrySpan
  {
    private final String m_sName;
    private final ETelemetrySpanKind m_eKind;
    private final ICommonsOrderedMap <String, Object> m_aAttrs = new CommonsLinkedHashMap <> ();
    private String m_sStatus;
    private Throwable m_aException;
    private boolean m_bClosed;

    CapturingSpan (@NonNull final String sName, @NonNull final ETelemetrySpanKind eKind)
    {
      m_sName = sName;
      m_eKind = eKind;
    }

    @NonNull
    public ITelemetrySpan setAttribute (@NonNull final String sKey, @Nullable final String sValue)
    {
      if (sValue != null)
        m_aAttrs.put (sKey, sValue);
      return this;
    }

    @NonNull
    public ITelemetrySpan setAttribute (@NonNull final String sKey, final boolean bValue)
    {
      m_aAttrs.put (sKey, Boolean.valueOf (bValue));
      return this;
    }

    @NonNull
    public ITelemetrySpan setAttribute (@NonNull final String sKey, final long nValue)
    {
      m_aAttrs.put (sKey, Long.valueOf (nValue));
      return this;
    }

    @NonNull
    public ITelemetrySpan setAttribute (@NonNull final String sKey, final double dValue)
    {
      m_aAttrs.put (sKey, Double.valueOf (dValue));
      return this;
    }

    @NonNull
    public ITelemetrySpan recordException (@NonNull final Throwable aException)
    {
      m_aException = aException;
      return this;
    }

    @NonNull
    public ITelemetrySpan addEvent (@NonNull final String sName, @NonNull final TelemetryAttributes aAttributes)
    {
      return this;
    }

    @NonNull
    public ITelemetrySpan setStatusOk ()
    {
      m_sStatus = "ok";
      return this;
    }

    @NonNull
    public ITelemetrySpan setStatusError (@Nullable final String sMessage)
    {
      m_sStatus = "error";
      return this;
    }

    public void close ()
    {
      m_bClosed = true;
    }
  }

  /** A tracer that remembers all started spans. */
  private static final class CapturingTracer implements ITelemetryTracerSPI
  {
    private final ICommonsList <CapturingSpan> m_aSpans = new CommonsArrayList <> ();

    @NonNull
    public ITelemetrySpan startSpan (@NonNull final String sName, @NonNull final ETelemetrySpanKind eKind)
    {
      final CapturingSpan ret = new CapturingSpan (sName, eKind);
      m_aSpans.add (ret);
      return ret;
    }
  }

  /** A single recorded measurement of an instrument. */
  private record Measurement (String sInstrument, double dValue, ICommonsOrderedMap <String, Object> aAttrs)
  {}

  /** A meter that remembers all recorded measurements. */
  private static final class CapturingMeter implements ITelemetryMeterSPI
  {
    private final ICommonsList <Measurement> m_aMeasurements = new CommonsArrayList <> (new CopyOnWriteArrayList <> ());

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

    private void _record (@NonNull final String sName, final double dValue, @NonNull final TelemetryAttributes aAttrs)
    {
      m_aMeasurements.add (new Measurement (sName, dValue, _toMap (aAttrs)));
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
      return () -> {};
    }
  }

  private static final CapturingTracer TRACER = new CapturingTracer ();
  private static final CapturingMeter METER = new CapturingMeter ();

  @Rule
  public final PhotonAppTestRule m_aRule = new PhotonAppTestRule ();

  @BeforeClass
  public static void installTelemetry ()
  {
    // Must happen before SystemMigrationMetrics is class-loaded, because the instruments are
    // resolved once in its static initializer
    Telemetry.install (TRACER);
    TelemetryMetrics.install (METER);
  }

  @AfterClass
  public static void uninstallTelemetry ()
  {
    Telemetry.install (null);
    TelemetryMetrics.install (null);
  }

  @Before
  public void clearRecordings ()
  {
    TRACER.m_aSpans.clear ();
    METER.m_aMeasurements.clear ();
  }

  @Nullable
  private static Measurement _findMeasurement (@NonNull final String sInstrument)
  {
    return METER.m_aMeasurements.findFirst (x -> x.sInstrument ().equals (sInstrument));
  }

  @Test
  public void testSuccessfulMigration () throws DAOException
  {
    final SystemMigrationManager aMgr = new SystemMigrationManager (null);
    SystemMigrationHelper.performMigrationIfNecessary (aMgr, MIGRATION_ID, () -> {});
    assertTrue (aMgr.wasMigrationExecutedSuccessfully (MIGRATION_ID));

    assertEquals (1, TRACER.m_aSpans.size ());
    final CapturingSpan aSpan = TRACER.m_aSpans.getFirstOrNull ();
    assertNotNull (aSpan);
    assertEquals (CMgrsTelemetry.SPAN_MIGRATION_EXECUTE, aSpan.m_sName);
    assertEquals (ETelemetrySpanKind.INTERNAL, aSpan.m_eKind);
    assertTrue (aSpan.m_bClosed);
    assertEquals ("ok", aSpan.m_sStatus);
    assertEquals (MIGRATION_ID, aSpan.m_aAttrs.get (CMgrsTelemetry.ATTR_MIGRATION_ID));
    assertEquals (Boolean.TRUE, aSpan.m_aAttrs.get (CMgrsTelemetry.ATTR_MIGRATION_SUCCESS));

    final Measurement aExecuted = _findMeasurement (CMgrsTelemetry.METRIC_MIGRATIONS_EXECUTED);
    assertNotNull (aExecuted);
    assertEquals (1, (int) aExecuted.dValue ());
    assertEquals (MIGRATION_ID, aExecuted.aAttrs ().get (CMgrsTelemetry.ATTR_MIGRATION_ID));
    assertEquals (Boolean.TRUE, aExecuted.aAttrs ().get (CMgrsTelemetry.ATTR_MIGRATION_SUCCESS));
    assertEquals (CMgrsTelemetry.VALUE_FAILURE_NONE,
                  aExecuted.aAttrs ().get (CMgrsTelemetry.ATTR_MIGRATION_FAILURE_KIND));
    assertNotNull (_findMeasurement (CMgrsTelemetry.METRIC_MIGRATION_DURATION));
  }

  @Test
  public void testAlreadyPerformedMigrationEmitsNothing () throws DAOException
  {
    final SystemMigrationManager aMgr = new SystemMigrationManager (null);
    aMgr.addMigrationResult (SystemMigrationResult.createSuccess (MIGRATION_ID));
    METER.m_aMeasurements.clear ();
    TRACER.m_aSpans.clear ();

    SystemMigrationHelper.performMigrationIfNecessary (aMgr, MIGRATION_ID, () -> {
      throw new IllegalStateException ("must not be called");
    });

    assertTrue (TRACER.m_aSpans.isEmpty ());
    assertTrue (METER.m_aMeasurements.isEmpty ());
  }

  @Test
  public void testTechnicalFailure () throws DAOException
  {
    final SystemMigrationManager aMgr = new SystemMigrationManager (null);
    SystemMigrationHelper.performMigrationIfNecessary (aMgr, MIGRATION_ID, () -> {
      throw new IllegalStateException ("oops");
    });
    assertTrue (aMgr.wasMigrationExecutedSuccessfully (MIGRATION_ID) == false);

    final CapturingSpan aSpan = TRACER.m_aSpans.getFirstOrNull ();
    assertNotNull (aSpan);
    assertEquals ("error", aSpan.m_sStatus);
    assertNotNull (aSpan.m_aException);
    assertEquals (CMgrsTelemetry.VALUE_FAILURE_TECHNICAL,
                  aSpan.m_aAttrs.get (CMgrsTelemetry.ATTR_MIGRATION_FAILURE_KIND));

    final Measurement aExecuted = _findMeasurement (CMgrsTelemetry.METRIC_MIGRATIONS_EXECUTED);
    assertNotNull (aExecuted);
    assertEquals (Boolean.FALSE, aExecuted.aAttrs ().get (CMgrsTelemetry.ATTR_MIGRATION_SUCCESS));
    assertEquals (CMgrsTelemetry.VALUE_FAILURE_TECHNICAL,
                  aExecuted.aAttrs ().get (CMgrsTelemetry.ATTR_MIGRATION_FAILURE_KIND));
  }

  @Test
  public void testBusinessFailure () throws DAOException
  {
    final SystemMigrationManager aMgr = new SystemMigrationManager (null);
    SystemMigrationHelper.performMigrationIfNecessary (aMgr,
                                                       MIGRATION_ID,
                                                       () -> SuccessWithValue.createFailure ("did not work"));
    assertTrue (aMgr.wasMigrationExecutedSuccessfully (MIGRATION_ID) == false);

    final CapturingSpan aSpan = TRACER.m_aSpans.getFirstOrNull ();
    assertNotNull (aSpan);
    // A migration that reports a failure without throwing must still mark the span as failed
    assertEquals ("error", aSpan.m_sStatus);
    assertEquals (Boolean.FALSE, aSpan.m_aAttrs.get (CMgrsTelemetry.ATTR_MIGRATION_SUCCESS));
    assertEquals (CMgrsTelemetry.VALUE_FAILURE_BUSINESS,
                  aSpan.m_aAttrs.get (CMgrsTelemetry.ATTR_MIGRATION_FAILURE_KIND));

    final Measurement aExecuted = _findMeasurement (CMgrsTelemetry.METRIC_MIGRATIONS_EXECUTED);
    assertNotNull (aExecuted);
    assertEquals (Boolean.FALSE, aExecuted.aAttrs ().get (CMgrsTelemetry.ATTR_MIGRATION_SUCCESS));
    assertEquals (CMgrsTelemetry.VALUE_FAILURE_BUSINESS,
                  aExecuted.aAttrs ().get (CMgrsTelemetry.ATTR_MIGRATION_FAILURE_KIND));
  }
}
