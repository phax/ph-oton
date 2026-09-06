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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.helger.base.type.ObjectType;
import com.helger.collection.commons.ICommonsList;
import com.helger.commons.concurrent.collector.IConcurrentPerformer;
import com.helger.photon.audit.mock.MockCurrentUserIDProvider;
import com.helger.telemetry.mock.CapturingTelemetry;
import com.helger.telemetry.mock.CapturingTelemetry.CapturedGauge;
import com.helger.telemetry.mock.CapturingTelemetry.CapturedMeasurement;

/**
 * Test class for the ph-telemetry integration of {@link AbstractAuditor} and
 * {@link AsynchronousAuditor}.
 *
 * @author Philip Helger
 */
public final class AuditTelemetryTest
{
  private static final ObjectType OT = new ObjectType ("mock");

  private static final CapturingTelemetry TELEMETRY = RecordingTelemetryMeterSPI.TELEMETRY;

  @Before
  public void clearRecordings ()
  {
    TELEMETRY.reset ();
  }

  @Test
  public void testAuditItemsAreCounted ()
  {
    AuditHelper.setAuditor (new LoggingAuditor (new MockCurrentUserIDProvider ("userid")));
    try
    {
      AuditHelper.onAuditCreateSuccess (OT, "some", "unbounded", "arguments");
      AuditHelper.onAuditModifyFailure (OT, "other", "arguments");
    }
    finally
    {
      AuditHelper.setDefaultAuditor ();
    }

    final ICommonsList <CapturedMeasurement> aItems = TELEMETRY.getMeasurements (CAuditTelemetry.METRIC_AUDIT_ITEMS);
    assertEquals (2, aItems.size ());

    final CapturedMeasurement aCreate = aItems.getAtIndex (0);
    assertEquals (1, (int) aCreate.getValue ());
    assertEquals (EAuditActionType.CREATE.getID (), aCreate.getAttribute (CAuditTelemetry.ATTR_AUDIT_ACTION_TYPE));
    assertEquals (Boolean.TRUE, aCreate.getAttribute (CAuditTelemetry.ATTR_AUDIT_SUCCESS));
    // Neither the audit action string nor the arguments nor the user ID may show up
    assertEquals (2, aCreate.getAttributes ().size ());
    assertFalse (aCreate.getAttributes ().containsValue ("userid"));

    final CapturedMeasurement aModify = aItems.getAtIndex (1);
    assertEquals (EAuditActionType.MODIFY.getID (), aModify.getAttribute (CAuditTelemetry.ATTR_AUDIT_ACTION_TYPE));
    assertEquals (Boolean.FALSE, aModify.getAttribute (CAuditTelemetry.ATTR_AUDIT_SUCCESS));
  }

  @Test
  public void testQueueLengthGauge ()
  {
    final IConcurrentPerformer <List <IAuditItem>> aPerformer = aItems -> {};
    final AsynchronousAuditor aAuditor = new AsynchronousAuditor (new MockCurrentUserIDProvider ("userid"), aPerformer);
    // The gauge is created together with the auditor
    final CapturedGauge aGauge = TELEMETRY.getGauge (CAuditTelemetry.METRIC_AUDIT_QUEUE_LENGTH);
    try
    {
      assertNotNull (aGauge);
      assertEquals (aAuditor.getQueueLength (), (int) aGauge.getValue ());
    }
    finally
    {
      assertTrue (aAuditor.stop ().isChanged ());
    }

    // Stopping the auditor closes the gauge again
    assertTrue (aGauge.isClosed ());
  }
}
