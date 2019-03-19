/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.helger.commons.concurrent.ThreadHelper;
import com.helger.commons.concurrent.collector.IConcurrentPerformer;
import com.helger.commons.mutable.MutableInt;
import com.helger.commons.type.ObjectType;
import com.helger.photon.app.mock.MockCurrentUserIDProvider;
import com.helger.photon.audit.AsynchronousAuditor;
import com.helger.photon.audit.AuditHelper;
import com.helger.photon.audit.IAuditItem;

/**
 * Test class for class {@link AsynchronousAuditor}.
 *
 * @author Philip Helger
 */
public final class AsynchronousAuditorTest
{
  @Test
  public void testBasic ()
  {
    final ObjectType aOT = new ObjectType ("mock");
    final MutableInt aPerformCount = new MutableInt (0);
    final IConcurrentPerformer <List <IAuditItem>> aPerformer = aItems -> aPerformCount.inc (aItems.size ());
    final AsynchronousAuditor aAuditor = new AsynchronousAuditor (new MockCurrentUserIDProvider ("userid"), aPerformer);
    AuditHelper.setAuditor (aAuditor);
    try
    {
      AuditHelper.onAuditCreateSuccess (aOT);
      AuditHelper.onAuditCreateSuccess (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditHelper.onAuditModifySuccess (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditHelper.onAuditModifyFailure (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditHelper.onAuditDeleteSuccess (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditHelper.onAuditDeleteFailure (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditHelper.onAuditUndeleteSuccess (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      ThreadHelper.sleep (10);
      AuditHelper.onAuditUndeleteFailure (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditHelper.onAuditExecuteSuccess ("spawn", "this", "is", Integer.valueOf (2), "a", "test");
      AuditHelper.onAuditExecuteFailure ("spawn", "this", "is", Integer.valueOf (2), "a", "test");
      AuditHelper.onAuditExecuteSuccess (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditHelper.onAuditExecuteFailure (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      // Stop!
      assertTrue (aAuditor.stop ().isChanged ());
      assertFalse (aAuditor.stop ().isChanged ());

      // Ensure that all audit were performed
      assertEquals (12, aPerformCount.intValue ());

      // And now, after we stopped...
      try
      {
        AuditHelper.onAuditExecuteFailure (aOT, "this", "is", "a", "test");
        fail ();
      }
      catch (final IllegalStateException ex)
      {
        // ... no new objects can be queued!
      }

      // Ensure that nothing changed
      assertEquals (12, aPerformCount.intValue ());
    }
    finally
    {
      AuditHelper.setDefaultAuditor ();
    }
  }
}
