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
package com.helger.photon.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.jspecify.annotations.NonNull;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.scope.mock.ScopeTestRule;
import com.helger.telemetry.ETelemetrySpanKind;
import com.helger.telemetry.mock.CapturingTelemetry;
import com.helger.telemetry.mock.CapturingTelemetry.CapturedMeasurement;
import com.helger.telemetry.mock.CapturingTelemetry.CapturedSpan;

/**
 * Test class for the ph-telemetry integration of {@link PhotonWorkerPool}.
 *
 * @author Philip Helger
 */
public final class PhotonWorkerPoolTelemetryTest
{
  private static final String ACTION_NAME = "unit test action";

  private static final CapturingTelemetry TELEMETRY = new CapturingTelemetry ();

  // A fresh global scope - and therefore a fresh worker pool - per test
  @Rule
  public final TestRule m_aRule = new ScopeTestRule ();

  @BeforeClass
  public static void installTelemetry ()
  {
    // Must happen before PhotonWorkerPoolMetrics is class-loaded, because the instruments are
    // resolved once in its static initializer
    TELEMETRY.install ();
  }

  @AfterClass
  public static void uninstallTelemetry ()
  {
    CapturingTelemetry.uninstall ();
  }

  @Before
  public void clearRecordings ()
  {
    TELEMETRY.reset ();
  }

  private static double _getSum (@NonNull final String sInstrument)
  {
    double ret = 0;
    for (final CapturedMeasurement aMeasurement : TELEMETRY.getMeasurements ())
      if (aMeasurement.getInstrumentName ().equals (sInstrument))
        ret += aMeasurement.getValue ();
    return ret;
  }

  @Test
  public void testSuccessfulTask ()
  {
    PhotonWorkerPool.getInstance ().run (ACTION_NAME, () -> {}).join ();

    assertEquals (1, TELEMETRY.getSpanCount ());
    final CapturedSpan aSpan = TELEMETRY.getSpans ().getFirstOrNull ();
    assertNotNull (aSpan);
    assertEquals (CIOTelemetry.SPAN_WORKER_EXECUTE, aSpan.getName ());
    assertEquals (ETelemetrySpanKind.INTERNAL, aSpan.getKind ());
    assertTrue (aSpan.isClosed ());
    assertTrue (aSpan.isStatusOk ());
    // The action name is a span attribute only
    assertEquals (ACTION_NAME, aSpan.getAttribute (CIOTelemetry.ATTR_WORKER_ACTION));
    assertEquals (Boolean.TRUE, aSpan.getAttribute (CIOTelemetry.ATTR_WORKER_SUCCESS));

    final CapturedMeasurement aStarted = TELEMETRY.getFirstMeasurement (CIOTelemetry.METRIC_WORKER_STARTED);
    assertNotNull (aStarted);
    assertEquals (1, (int) aStarted.getValue ());
    assertTrue (aStarted.getAttributes ().isEmpty ());

    final CapturedMeasurement aEnded = TELEMETRY.getFirstMeasurement (CIOTelemetry.METRIC_WORKER_ENDED);
    assertNotNull (aEnded);
    assertEquals (1, (int) aEnded.getValue ());
    assertEquals (Boolean.TRUE, aEnded.getAttribute (CIOTelemetry.ATTR_WORKER_SUCCESS));
    // The unbounded action name must never be a metric attribute
    assertNull (aEnded.getAttribute (CIOTelemetry.ATTR_WORKER_ACTION));
    assertEquals (1, aEnded.getAttributes ().size ());

    assertNotNull (TELEMETRY.getFirstMeasurement (CIOTelemetry.METRIC_WORKER_DURATION));
    // The up-down counter nets out to 0
    assertEquals (0, (int) _getSum (CIOTelemetry.METRIC_WORKER_RUNNING));
  }

  @Test
  public void testFailingTask ()
  {
    // The exception is swallowed by the worker pool, so the future completes normally
    PhotonWorkerPool.getInstance ().run (ACTION_NAME, () -> {
      throw new IllegalStateException ("oops");
    }).join ();

    final CapturedSpan aSpan = TELEMETRY.getSpans ().getFirstOrNull ();
    assertNotNull (aSpan);
    assertTrue (aSpan.isClosed ());
    // Even though the future completes normally, the span is marked as failed
    assertTrue (aSpan.isStatusError ());
    assertNotNull (aSpan.getRecordedException ());
    assertEquals (Boolean.FALSE, aSpan.getAttribute (CIOTelemetry.ATTR_WORKER_SUCCESS));

    final CapturedMeasurement aEnded = TELEMETRY.getFirstMeasurement (CIOTelemetry.METRIC_WORKER_ENDED);
    assertNotNull (aEnded);
    assertEquals (Boolean.FALSE, aEnded.getAttribute (CIOTelemetry.ATTR_WORKER_SUCCESS));

    final CapturedMeasurement aDuration = TELEMETRY.getFirstMeasurement (CIOTelemetry.METRIC_WORKER_DURATION);
    assertNotNull (aDuration);
    assertEquals (Boolean.FALSE, aDuration.getAttribute (CIOTelemetry.ATTR_WORKER_SUCCESS));
    assertEquals (0, (int) _getSum (CIOTelemetry.METRIC_WORKER_RUNNING));
  }

  @Test
  public void testThrowingSupplier ()
  {
    final String sResult = PhotonWorkerPool.getInstance ().<String> supplyThrowing (ACTION_NAME, () -> {
      throw new Exception ("oops");
    }).join ();
    assertNull (sResult);

    final CapturedSpan aSpan = TELEMETRY.getSpans ().getFirstOrNull ();
    assertNotNull (aSpan);
    assertTrue (aSpan.isStatusError ());

    // Exactly one end-of-task measurement, not two
    assertEquals (1, TELEMETRY.getMeasurements (CIOTelemetry.METRIC_WORKER_ENDED).size ());
  }
}
