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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.function.LongSupplier;

import org.junit.Before;
import org.junit.Test;

import com.helger.base.type.ObjectType;
import com.helger.collection.commons.ICommonsList;
import com.helger.commons.concurrent.collector.IConcurrentPerformer;
import com.helger.photon.audit.RecordingTelemetryMeterSPI.Measurement;
import com.helger.photon.audit.mock.MockCurrentUserIDProvider;

/**
 * Test class for the ph-telemetry integration of {@link AbstractAuditor} and
 * {@link AsynchronousAuditor}.
 *
 * @author Philip Helger
 */
public final class AuditTelemetryTest
{
  private static final ObjectType OT = new ObjectType ("mock");

  @Before
  public void clearRecordings ()
  {
    RecordingTelemetryMeterSPI.clearRecordings ();
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

    final ICommonsList <Measurement> aItems = RecordingTelemetryMeterSPI.getMeasurements (CAuditTelemetry.METRIC_AUDIT_ITEMS);
    assertEquals (2, aItems.size ());

    final Measurement aCreate = aItems.getAtIndex (0);
    assertEquals (1, (int) aCreate.dValue ());
    assertEquals (EAuditActionType.CREATE.getID (), aCreate.aAttrs ().get (CAuditTelemetry.ATTR_AUDIT_ACTION_TYPE));
    assertEquals (Boolean.TRUE, aCreate.aAttrs ().get (CAuditTelemetry.ATTR_AUDIT_SUCCESS));
    // Neither the audit action string nor the arguments nor the user ID may show up
    assertEquals (2, aCreate.aAttrs ().size ());
    assertFalse (aCreate.aAttrs ().containsValue ("userid"));

    final Measurement aModify = aItems.getAtIndex (1);
    assertEquals (EAuditActionType.MODIFY.getID (), aModify.aAttrs ().get (CAuditTelemetry.ATTR_AUDIT_ACTION_TYPE));
    assertEquals (Boolean.FALSE, aModify.aAttrs ().get (CAuditTelemetry.ATTR_AUDIT_SUCCESS));
  }

  @Test
  public void testQueueLengthGauge ()
  {
    final IConcurrentPerformer <List <IAuditItem>> aPerformer = aItems -> {};
    final AsynchronousAuditor aAuditor = new AsynchronousAuditor (new MockCurrentUserIDProvider ("userid"), aPerformer);
    try
    {
      // The gauge is created together with the auditor
      final LongSupplier aGauge = RecordingTelemetryMeterSPI.getGaugeSupplier (CAuditTelemetry.METRIC_AUDIT_QUEUE_LENGTH);
      assertNotNull (aGauge);
      assertEquals (aAuditor.getQueueLength (), (int) aGauge.getAsLong ());
    }
    finally
    {
      assertTrue (aAuditor.stop ().isChanged ());
    }

    // Stopping the auditor closes the gauge again
    assertNull (RecordingTelemetryMeterSPI.getGaugeSupplier (CAuditTelemetry.METRIC_AUDIT_QUEUE_LENGTH));
  }
}
