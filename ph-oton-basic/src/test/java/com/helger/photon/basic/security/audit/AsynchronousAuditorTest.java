/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.security.audit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javax.annotation.Nonnull;

import org.junit.Test;

import com.helger.commons.callback.IThrowingRunnableWithParameter;
import com.helger.commons.concurrent.ThreadUtils;
import com.helger.commons.mutable.MutableInt;
import com.helger.commons.type.ObjectType;
import com.helger.photon.basic.mock.MockCurrentUserIDProvider;
import com.helger.photon.basic.security.audit.AsynchronousAuditor;
import com.helger.photon.basic.security.audit.AuditUtils;
import com.helger.photon.basic.security.audit.IAuditItem;

/**
 * Test class for class {@link AsynchronousAuditor}.
 * 
 * @author Philip Helger
 */
public class AsynchronousAuditorTest
{
  @Test
  public void testBasic ()
  {
    final ObjectType aOT = new ObjectType ("mock");
    final MutableInt aPerformCount = new MutableInt (0);
    final IThrowingRunnableWithParameter <List <IAuditItem>> aPerformer = new IThrowingRunnableWithParameter <List <IAuditItem>> ()
    {
      public void run (@Nonnull final List <IAuditItem> aItems) throws Exception
      {
        // Count number of items to be handled
        aPerformCount.inc (aItems.size ());
      }
    };
    final AsynchronousAuditor aAuditor = new AsynchronousAuditor (new MockCurrentUserIDProvider ("userid"), aPerformer);
    AuditUtils.setAuditor (aAuditor);
    try
    {
      AuditUtils.onAuditCreateSuccess (aOT);
      AuditUtils.onAuditCreateSuccess (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditUtils.onAuditModifySuccess (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditUtils.onAuditModifyFailure (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditUtils.onAuditDeleteSuccess (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditUtils.onAuditDeleteFailure (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditUtils.onAuditUndeleteSuccess (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      ThreadUtils.sleep (10);
      AuditUtils.onAuditUndeleteFailure (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditUtils.onAuditExecuteSuccess ("spawn", "this", "is", Integer.valueOf (2), "a", "test");
      AuditUtils.onAuditExecuteFailure ("spawn", "this", "is", Integer.valueOf (2), "a", "test");
      AuditUtils.onAuditExecuteSuccess (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditUtils.onAuditExecuteFailure (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      // Stop!
      assertTrue (aAuditor.stop ().isChanged ());
      assertFalse (aAuditor.stop ().isChanged ());

      // Ensure that all audit were performed
      assertEquals (12, aPerformCount.intValue ());

      // And now, after we stopped...
      try
      {
        AuditUtils.onAuditExecuteFailure (aOT, "this", "is", "a", "test");
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
      AuditUtils.setDefaultAuditor ();
    }
  }
}
