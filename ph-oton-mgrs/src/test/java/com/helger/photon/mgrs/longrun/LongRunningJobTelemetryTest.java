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
package com.helger.photon.mgrs.longrun;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.LongSupplier;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.helger.annotation.Nonempty;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.state.EChange;
import com.helger.base.state.ESuccess;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.CommonsLinkedHashMap;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsOrderedMap;
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
import com.helger.text.IMultilingualText;
import com.helger.text.ReadOnlyMultilingualText;

/**
 * Test class for the ph-telemetry integration of {@link LongRunningJobManager}.
 *
 * @author Philip Helger
 */
public final class LongRunningJobTelemetryTest
{
  private static final String JOB_ID = "unit-test-job";

  /** A span that only records what was set on it. */
  private static final class CapturingSpan implements ITelemetrySpan
  {
    private final String m_sName;
    private final ETelemetrySpanKind m_eKind;
    private final ICommonsOrderedMap <String, Object> m_aAttrs = new CommonsLinkedHashMap <> ();
    private String m_sStatus;
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

  /** An in-memory result manager. */
  private static final class MockResultManager implements ILongRunningJobResultManager
  {
    private final ICommonsList <LongRunningJobData> m_aResults = new CommonsArrayList <> ();

    public void addResult (@NonNull final LongRunningJobData aJobData)
    {
      m_aResults.add (aJobData);
    }

    @NonNull
    @ReturnsMutableCopy
    public ICommonsList <LongRunningJobData> getAllJobResults ()
    {
      return m_aResults.getClone ();
    }

    @Nullable
    public LongRunningJobData getJobResultOfID (@Nullable final String sJobResultID)
    {
      return m_aResults.findFirst (x -> x.getID ().equals (sJobResultID));
    }

    @NonNull
    public EChange deleteResult (@Nullable final String sJobResultID)
    {
      return EChange.valueOf (m_aResults.removeIf (x -> x.getID ().equals (sJobResultID)));
    }
  }

  /** A job that does nothing but deliver a fixed result. */
  private static final class MockJob implements ILongRunningJob
  {
    @NonNull
    @Nonempty
    public String getJobID ()
    {
      return JOB_ID;
    }

    @NonNull
    public IMultilingualText getJobDescription ()
    {
      return new ReadOnlyMultilingualText (Locale.ENGLISH, "Unit test job");
    }

    @NonNull
    public LongRunningJobResult createLongRunningJobResult ()
    {
      return LongRunningJobResult.createText ("done");
    }
  }

  private static final CapturingTracer TRACER = new CapturingTracer ();
  private static final CapturingMeter METER = new CapturingMeter ();

  @BeforeClass
  public static void installTelemetry ()
  {
    // Must happen before LongRunningJobMetrics is class-loaded, because the instruments are
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
  public void testSuccessfulJob ()
  {
    final LongRunningJobManager aMgr = new LongRunningJobManager (new MockResultManager ());
    final MockJob aJob = new MockJob ();

    final String sExecutionID = aMgr.onStartJob (aJob, "user-4711");

    // A span was started and is still open
    assertEquals (1, TRACER.m_aSpans.size ());
    final CapturingSpan aSpan = TRACER.m_aSpans.getFirstOrNull ();
    assertNotNull (aSpan);
    assertEquals (CLongRunningJobTelemetry.SPAN_EXECUTE, aSpan.m_sName);
    assertEquals (ETelemetrySpanKind.INTERNAL, aSpan.m_eKind);
    assertFalse (aSpan.m_bClosed);
    assertEquals (sExecutionID, aSpan.m_aAttrs.get (CLongRunningJobTelemetry.ATTR_JOB_EXECUTION_ID));
    assertEquals (JOB_ID, aSpan.m_aAttrs.get (CLongRunningJobTelemetry.ATTR_JOB_ID));
    assertEquals ("user-4711", aSpan.m_aAttrs.get (CLongRunningJobTelemetry.ATTR_JOB_USER_ID));

    // The start metrics were emitted
    final Measurement aStarted = _findMeasurement (CLongRunningJobTelemetry.METRIC_JOBS_STARTED);
    assertNotNull (aStarted);
    assertEquals (1, (int) aStarted.dValue ());
    assertEquals (JOB_ID, aStarted.aAttrs ().get (CLongRunningJobTelemetry.ATTR_JOB_ID));

    final Measurement aRunningUp = _findMeasurement (CLongRunningJobTelemetry.METRIC_JOBS_RUNNING);
    assertNotNull (aRunningUp);
    assertEquals (1, (int) aRunningUp.dValue ());

    aMgr.onEndJob (sExecutionID, ESuccess.SUCCESS, aJob.createLongRunningJobResult ());

    // The span was completed
    assertTrue (aSpan.m_bClosed);
    assertEquals ("ok", aSpan.m_sStatus);
    assertEquals (Boolean.TRUE, aSpan.m_aAttrs.get (CLongRunningJobTelemetry.ATTR_JOB_SUCCESS));
    assertEquals (ELongRunningJobResultType.TEXT.getID (),
                  aSpan.m_aAttrs.get (CLongRunningJobTelemetry.ATTR_JOB_RESULT_TYPE));

    // The end metrics were emitted
    final Measurement aEnded = _findMeasurement (CLongRunningJobTelemetry.METRIC_JOBS_ENDED);
    assertNotNull (aEnded);
    assertEquals (1, (int) aEnded.dValue ());
    assertEquals (Boolean.TRUE, aEnded.aAttrs ().get (CLongRunningJobTelemetry.ATTR_JOB_SUCCESS));
    assertEquals (ELongRunningJobResultType.TEXT.getID (),
                  aEnded.aAttrs ().get (CLongRunningJobTelemetry.ATTR_JOB_RESULT_TYPE));

    assertNotNull (_findMeasurement (CLongRunningJobTelemetry.METRIC_JOB_DURATION));

    // The up-down counter must net out to 0 with identical attributes
    double dRunning = 0;
    for (final Measurement aMeasurement : METER.m_aMeasurements)
      if (aMeasurement.sInstrument ().equals (CLongRunningJobTelemetry.METRIC_JOBS_RUNNING))
      {
        dRunning += aMeasurement.dValue ();
        assertEquals (JOB_ID, aMeasurement.aAttrs ().get (CLongRunningJobTelemetry.ATTR_JOB_ID));
        assertEquals (1, aMeasurement.aAttrs ().size ());
      }
    assertEquals (0, (int) dRunning);
  }

  @Test
  public void testFailedJob ()
  {
    final LongRunningJobManager aMgr = new LongRunningJobManager (new MockResultManager ());
    final MockJob aJob = new MockJob ();

    final String sExecutionID = aMgr.onStartJob (aJob, null);
    final CapturingSpan aSpan = TRACER.m_aSpans.getFirstOrNull ();
    assertNotNull (aSpan);
    assertNull (aSpan.m_aAttrs.get (CLongRunningJobTelemetry.ATTR_JOB_USER_ID));

    aMgr.onEndJob (sExecutionID,
                   ESuccess.FAILURE,
                   LongRunningJobResult.createExceptionText (new IllegalStateException ("oops")));

    assertTrue (aSpan.m_bClosed);
    assertEquals ("error", aSpan.m_sStatus);
    assertEquals (Boolean.FALSE, aSpan.m_aAttrs.get (CLongRunningJobTelemetry.ATTR_JOB_SUCCESS));

    final Measurement aEnded = _findMeasurement (CLongRunningJobTelemetry.METRIC_JOBS_ENDED);
    assertNotNull (aEnded);
    assertEquals (Boolean.FALSE, aEnded.aAttrs ().get (CLongRunningJobTelemetry.ATTR_JOB_SUCCESS));
  }

  @Test
  public void testJobTypeIDIsNotPersisted ()
  {
    final MockResultManager aResultMgr = new MockResultManager ();
    final LongRunningJobManager aMgr = new LongRunningJobManager (aResultMgr);
    final MockJob aJob = new MockJob ();

    final String sExecutionID = aMgr.onStartJob (aJob, "user-4711");
    aMgr.onEndJob (sExecutionID, ESuccess.SUCCESS, aJob.createLongRunningJobResult ());

    final LongRunningJobData aJobData = aResultMgr.getJobResultOfID (sExecutionID);
    assertNotNull (aJobData);
    // In memory the job type ID is present ...
    assertEquals (JOB_ID, aJobData.getJobID ());
    // ... but the span reference is released again
    assertNull (aJobData.getTelemetrySpan ());

    // ... and it is not part of the persisted representation
    final LongRunningJobData aReadBack = new LongRunningJobDataMicroTypeConverter ().convertToNative (new LongRunningJobDataMicroTypeConverter ().convertToMicroElement (aJobData,
                                                                                                                                                                        null,
                                                                                                                                                                        "job"));
    assertNotNull (aReadBack);
    assertEquals (sExecutionID, aReadBack.getID ());
    assertNull (aReadBack.getJobID ());
  }
}
