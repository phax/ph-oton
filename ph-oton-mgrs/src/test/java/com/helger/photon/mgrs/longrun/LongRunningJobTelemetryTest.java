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
import java.util.function.Consumer;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.helger.annotation.Nonempty;
import com.helger.base.id.factory.GlobalIDFactory;
import com.helger.base.id.factory.MemoryStaticIntIDFactory;
import com.helger.base.state.EChange;
import com.helger.base.state.ESuccess;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.telemetry.ETelemetrySpanKind;
import com.helger.telemetry.mock.CapturingTelemetry;
import com.helger.telemetry.mock.CapturingTelemetry.CapturedMeasurement;
import com.helger.telemetry.mock.CapturingTelemetry.CapturedSpan;
import com.helger.text.IMultilingualText;
import com.helger.text.ReadOnlyMultilingualText;

/**
 * Test class for the ph-telemetry integration of {@link LongRunningJobManager}.
 *
 * @author Philip Helger
 */
public final class LongRunningJobTelemetryTest
{
  private static final String JOB_TYPE = "unit-test";

  /** An in-memory result manager. */
  private static final class MockResultManager implements ILongRunningJobResultManager
  {
    private final ICommonsList <LongRunningJobData> m_aResults = new CommonsArrayList <> ();

    public void addResult (@NonNull final LongRunningJobData aJobData)
    {
      m_aResults.add (aJobData);
    }

    public void forEachJobResult (@Nullable final String sJobType,
                                  @NonNull final Consumer <? super LongRunningJobData> aConsumer)
    {
      for (final LongRunningJobData aJobData : m_aResults)
        if (sJobType == null || sJobType.equals (aJobData.getJobType ()))
          aConsumer.accept (aJobData);
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
    public String getJobType ()
    {
      return JOB_TYPE;
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

  private static final CapturingTelemetry TELEMETRY = new CapturingTelemetry ();

  @BeforeClass
  public static void installTelemetry ()
  {
    // Must happen before LongRunningJobMetrics is class-loaded, because the instruments are
    // resolved once in its static initializer
    TELEMETRY.install ();

    // LongRunningJobManager.onStartJob creates a persistent ID
    GlobalIDFactory.setPersistentIntIDFactory (new MemoryStaticIntIDFactory ());
  }

  @AfterClass
  public static void uninstallTelemetry ()
  {
    CapturingTelemetry.uninstall ();
    GlobalIDFactory.setPersistentIntIDFactory (null);
  }

  @Before
  public void clearRecordings ()
  {
    TELEMETRY.reset ();
  }

  @Test
  public void testSuccessfulJob ()
  {
    final LongRunningJobManager aMgr = new LongRunningJobManager (new MockResultManager ());
    final MockJob aJob = new MockJob ();

    final String sExecutionID = aMgr.onStartJob (aJob, "user-4711");

    // A span was started and is still open
    assertEquals (1, TELEMETRY.getSpanCount ());
    final CapturedSpan aSpan = TELEMETRY.getSpans ().getFirstOrNull ();
    assertNotNull (aSpan);
    assertEquals (CLongRunningJobTelemetry.SPAN_EXECUTE, aSpan.getName ());
    assertEquals (ETelemetrySpanKind.INTERNAL, aSpan.getKind ());
    assertFalse (aSpan.isClosed ());
    assertEquals (sExecutionID, aSpan.getAttribute (CLongRunningJobTelemetry.ATTR_JOB_EXECUTION_ID));
    assertEquals (JOB_TYPE, aSpan.getAttribute (CLongRunningJobTelemetry.ATTR_JOB_TYPE));
    assertEquals ("user-4711", aSpan.getAttribute (CLongRunningJobTelemetry.ATTR_JOB_USER_ID));

    // The start metrics were emitted
    final CapturedMeasurement aStarted = TELEMETRY.getFirstMeasurement (CLongRunningJobTelemetry.METRIC_JOBS_STARTED);
    assertNotNull (aStarted);
    assertEquals (1, (int) aStarted.getValue ());
    assertEquals (JOB_TYPE, aStarted.getAttribute (CLongRunningJobTelemetry.ATTR_JOB_TYPE));

    final CapturedMeasurement aRunningUp = TELEMETRY.getFirstMeasurement (CLongRunningJobTelemetry.METRIC_JOBS_RUNNING);
    assertNotNull (aRunningUp);
    assertEquals (1, (int) aRunningUp.getValue ());

    aMgr.onEndJob (sExecutionID, ESuccess.SUCCESS, aJob.createLongRunningJobResult ());

    // The span was completed
    assertTrue (aSpan.isClosed ());
    assertTrue (aSpan.isStatusOk ());
    assertEquals (Boolean.TRUE, aSpan.getAttribute (CLongRunningJobTelemetry.ATTR_JOB_SUCCESS));
    assertEquals (ELongRunningJobResultType.TEXT.getID (),
                  aSpan.getAttribute (CLongRunningJobTelemetry.ATTR_JOB_RESULT_TYPE));

    // The end metrics were emitted
    final CapturedMeasurement aEnded = TELEMETRY.getFirstMeasurement (CLongRunningJobTelemetry.METRIC_JOBS_ENDED);
    assertNotNull (aEnded);
    assertEquals (1, (int) aEnded.getValue ());
    assertEquals (Boolean.TRUE, aEnded.getAttribute (CLongRunningJobTelemetry.ATTR_JOB_SUCCESS));
    assertEquals (ELongRunningJobResultType.TEXT.getID (),
                  aEnded.getAttribute (CLongRunningJobTelemetry.ATTR_JOB_RESULT_TYPE));

    assertNotNull (TELEMETRY.getFirstMeasurement (CLongRunningJobTelemetry.METRIC_JOB_DURATION));

    // The up-down counter must net out to 0 with identical attributes
    double dRunning = 0;
    for (final CapturedMeasurement aMeasurement : TELEMETRY.getMeasurements ())
      if (aMeasurement.getInstrumentName ().equals (CLongRunningJobTelemetry.METRIC_JOBS_RUNNING))
      {
        dRunning += aMeasurement.getValue ();
        assertEquals (JOB_TYPE, aMeasurement.getAttribute (CLongRunningJobTelemetry.ATTR_JOB_TYPE));
        assertEquals (1, aMeasurement.getAttributes ().size ());
      }
    assertEquals (0, (int) dRunning);
  }

  @Test
  public void testFailedJob ()
  {
    final LongRunningJobManager aMgr = new LongRunningJobManager (new MockResultManager ());
    final MockJob aJob = new MockJob ();

    final String sExecutionID = aMgr.onStartJob (aJob, null);
    final CapturedSpan aSpan = TELEMETRY.getSpans ().getFirstOrNull ();
    assertNotNull (aSpan);
    assertNull (aSpan.getAttribute (CLongRunningJobTelemetry.ATTR_JOB_USER_ID));

    aMgr.onEndJob (sExecutionID,
                   ESuccess.FAILURE,
                   LongRunningJobResult.createExceptionText (new IllegalStateException ("oops")));

    assertTrue (aSpan.isClosed ());
    assertTrue (aSpan.isStatusError ());
    assertEquals (Boolean.FALSE, aSpan.getAttribute (CLongRunningJobTelemetry.ATTR_JOB_SUCCESS));

    final CapturedMeasurement aEnded = TELEMETRY.getFirstMeasurement (CLongRunningJobTelemetry.METRIC_JOBS_ENDED);
    assertNotNull (aEnded);
    assertEquals (Boolean.FALSE, aEnded.getAttribute (CLongRunningJobTelemetry.ATTR_JOB_SUCCESS));
  }

  @Test
  public void testJobTypeIsPersisted ()
  {
    final MockResultManager aResultMgr = new MockResultManager ();
    final LongRunningJobManager aMgr = new LongRunningJobManager (aResultMgr);
    final MockJob aJob = new MockJob ();

    final String sExecutionID = aMgr.onStartJob (aJob, "user-4711");
    aMgr.onEndJob (sExecutionID, ESuccess.SUCCESS, aJob.createLongRunningJobResult ());

    final LongRunningJobData aJobData = aResultMgr.getJobResultOfID (sExecutionID);
    assertNotNull (aJobData);
    // In memory the job type is present ...
    assertEquals (JOB_TYPE, aJobData.getJobType ());
    // ... but the span reference is released again
    assertNull (aJobData.getTelemetrySpan ());

    // ... and it survives the persistence round trip together with the unique execution ID
    final LongRunningJobData aReadBack = new LongRunningJobDataMicroTypeConverter ().convertToNative (new LongRunningJobDataMicroTypeConverter ().convertToMicroElement (aJobData,
                                                                                                                                                                         null,
                                                                                                                                                                         "job"));
    assertNotNull (aReadBack);
    assertEquals (sExecutionID, aReadBack.getID ());
    assertEquals (JOB_TYPE, aReadBack.getJobType ());
  }

  @Test
  public void testFilterJobResultsByJobType ()
  {
    final MockResultManager aResultMgr = new MockResultManager ();
    final LongRunningJobManager aMgr = new LongRunningJobManager (aResultMgr);
    final MockJob aJob = new MockJob ();

    final String sExecutionID = aMgr.onStartJob (aJob, "user-4711");
    aMgr.onEndJob (sExecutionID, ESuccess.SUCCESS, aJob.createLongRunningJobResult ());

    // A job result without a job type - as it is read back from a pre v10.4.0 persistence layer
    final LongRunningJobData aLegacy = new LongRunningJobData ("no-type-id",
                                                               null,
                                                               new ReadOnlyMultilingualText (Locale.ENGLISH,
                                                                                             "Legacy job"),
                                                               "user-4711");
    aLegacy.onJobEnd (ESuccess.SUCCESS, LongRunningJobResult.createText ("legacy"));
    aResultMgr.addResult (aLegacy);

    // No filter - everything is returned
    assertEquals (2, aResultMgr.getAllJobResults ().size ());

    // Matching filter
    final ICommonsList <LongRunningJobData> aMatching = aResultMgr.getAllJobResults (JOB_TYPE);
    assertEquals (1, aMatching.size ());
    assertEquals (sExecutionID, aMatching.getFirstOrNull ().getID ());

    // Non-matching filter - job results without a job type never match
    assertTrue (aResultMgr.getAllJobResults ("something-else").isEmpty ());
  }
}
