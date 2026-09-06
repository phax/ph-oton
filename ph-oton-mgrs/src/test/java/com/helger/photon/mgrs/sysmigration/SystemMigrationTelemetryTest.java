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

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import com.helger.base.state.SuccessWithValue;
import com.helger.dao.DAOException;
import com.helger.photon.app.mock.PhotonAppTestRule;
import com.helger.photon.mgrs.CMgrsTelemetry;
import com.helger.telemetry.ETelemetrySpanKind;
import com.helger.telemetry.mock.CapturingTelemetry;
import com.helger.telemetry.mock.CapturingTelemetry.CapturedMeasurement;
import com.helger.telemetry.mock.CapturingTelemetry.CapturedSpan;

/**
 * Test class for the ph-telemetry integration of {@link SystemMigrationHelper}.
 *
 * @author Philip Helger
 */
public final class SystemMigrationTelemetryTest
{
  private static final String MIGRATION_ID = "unit-test-migration";

  private static final CapturingTelemetry TELEMETRY = new CapturingTelemetry ();

  @Rule
  public final PhotonAppTestRule m_aRule = new PhotonAppTestRule ();

  @BeforeClass
  public static void installTelemetry ()
  {
    // Must happen before SystemMigrationMetrics is class-loaded, because the instruments are
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

  @Test
  public void testSuccessfulMigration () throws DAOException
  {
    final SystemMigrationManager aMgr = new SystemMigrationManager (null);
    SystemMigrationHelper.performMigrationIfNecessary (aMgr, MIGRATION_ID, () -> {});
    assertTrue (aMgr.wasMigrationExecutedSuccessfully (MIGRATION_ID));

    assertEquals (1, TELEMETRY.getSpanCount ());
    final CapturedSpan aSpan = TELEMETRY.getSpans ().getFirstOrNull ();
    assertNotNull (aSpan);
    assertEquals (CMgrsTelemetry.SPAN_MIGRATION_EXECUTE, aSpan.getName ());
    assertEquals (ETelemetrySpanKind.INTERNAL, aSpan.getKind ());
    assertTrue (aSpan.isClosed ());
    assertTrue (aSpan.isStatusOk ());
    assertEquals (MIGRATION_ID, aSpan.getAttribute (CMgrsTelemetry.ATTR_MIGRATION_ID));
    assertEquals (Boolean.TRUE, aSpan.getAttribute (CMgrsTelemetry.ATTR_MIGRATION_SUCCESS));

    final CapturedMeasurement aExecuted = TELEMETRY.getFirstMeasurement (CMgrsTelemetry.METRIC_MIGRATIONS_EXECUTED);
    assertNotNull (aExecuted);
    assertEquals (1, (int) aExecuted.getValue ());
    assertEquals (MIGRATION_ID, aExecuted.getAttribute (CMgrsTelemetry.ATTR_MIGRATION_ID));
    assertEquals (Boolean.TRUE, aExecuted.getAttribute (CMgrsTelemetry.ATTR_MIGRATION_SUCCESS));
    assertEquals (CMgrsTelemetry.VALUE_FAILURE_NONE,
                  aExecuted.getAttribute (CMgrsTelemetry.ATTR_MIGRATION_FAILURE_KIND));
    assertNotNull (TELEMETRY.getFirstMeasurement (CMgrsTelemetry.METRIC_MIGRATION_DURATION));
  }

  @Test
  public void testAlreadyPerformedMigrationEmitsNothing () throws DAOException
  {
    final SystemMigrationManager aMgr = new SystemMigrationManager (null);
    aMgr.addMigrationResult (SystemMigrationResult.createSuccess (MIGRATION_ID));
    TELEMETRY.reset ();

    SystemMigrationHelper.performMigrationIfNecessary (aMgr, MIGRATION_ID, () -> {
      throw new IllegalStateException ("must not be called");
    });

    assertTrue (TELEMETRY.getSpans ().isEmpty ());
    assertTrue (TELEMETRY.getMeasurements ().isEmpty ());
  }

  @Test
  public void testTechnicalFailure () throws DAOException
  {
    final SystemMigrationManager aMgr = new SystemMigrationManager (null);
    SystemMigrationHelper.performMigrationIfNecessary (aMgr, MIGRATION_ID, () -> {
      throw new IllegalStateException ("oops");
    });
    assertTrue (aMgr.wasMigrationExecutedSuccessfully (MIGRATION_ID) == false);

    final CapturedSpan aSpan = TELEMETRY.getSpans ().getFirstOrNull ();
    assertNotNull (aSpan);
    assertTrue (aSpan.isStatusError ());
    assertNotNull (aSpan.getRecordedException ());
    assertEquals (CMgrsTelemetry.VALUE_FAILURE_TECHNICAL,
                  aSpan.getAttribute (CMgrsTelemetry.ATTR_MIGRATION_FAILURE_KIND));

    final CapturedMeasurement aExecuted = TELEMETRY.getFirstMeasurement (CMgrsTelemetry.METRIC_MIGRATIONS_EXECUTED);
    assertNotNull (aExecuted);
    assertEquals (Boolean.FALSE, aExecuted.getAttribute (CMgrsTelemetry.ATTR_MIGRATION_SUCCESS));
    assertEquals (CMgrsTelemetry.VALUE_FAILURE_TECHNICAL,
                  aExecuted.getAttribute (CMgrsTelemetry.ATTR_MIGRATION_FAILURE_KIND));
  }

  @Test
  public void testBusinessFailure () throws DAOException
  {
    final SystemMigrationManager aMgr = new SystemMigrationManager (null);
    SystemMigrationHelper.performMigrationIfNecessary (aMgr,
                                                       MIGRATION_ID,
                                                       () -> SuccessWithValue.createFailure ("did not work"));
    assertTrue (aMgr.wasMigrationExecutedSuccessfully (MIGRATION_ID) == false);

    final CapturedSpan aSpan = TELEMETRY.getSpans ().getFirstOrNull ();
    assertNotNull (aSpan);
    // A migration that reports a failure without throwing must still mark the span as failed
    assertTrue (aSpan.isStatusError ());
    assertEquals (Boolean.FALSE, aSpan.getAttribute (CMgrsTelemetry.ATTR_MIGRATION_SUCCESS));
    assertEquals (CMgrsTelemetry.VALUE_FAILURE_BUSINESS,
                  aSpan.getAttribute (CMgrsTelemetry.ATTR_MIGRATION_FAILURE_KIND));

    final CapturedMeasurement aExecuted = TELEMETRY.getFirstMeasurement (CMgrsTelemetry.METRIC_MIGRATIONS_EXECUTED);
    assertNotNull (aExecuted);
    assertEquals (Boolean.FALSE, aExecuted.getAttribute (CMgrsTelemetry.ATTR_MIGRATION_SUCCESS));
    assertEquals (CMgrsTelemetry.VALUE_FAILURE_BUSINESS,
                  aExecuted.getAttribute (CMgrsTelemetry.ATTR_MIGRATION_FAILURE_KIND));
  }
}
