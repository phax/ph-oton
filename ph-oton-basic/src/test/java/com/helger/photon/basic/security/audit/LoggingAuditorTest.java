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

import org.junit.Test;

import com.helger.commons.type.ObjectType;
import com.helger.photon.basic.mock.MockCurrentUserIDProvider;
import com.helger.photon.basic.security.audit.AuditUtils;
import com.helger.photon.basic.security.audit.LoggingAuditor;

/**
 * Test class for class {@link LoggingAuditor}.
 * 
 * @author Philip Helger
 */
public class LoggingAuditorTest
{
  @Test
  public void testBasic ()
  {
    final ObjectType aOT = new ObjectType ("mock");
    AuditUtils.setAuditor (new LoggingAuditor (new MockCurrentUserIDProvider ("userid")));
    try
    {
      AuditUtils.onAuditCreateSuccess (aOT);
      AuditUtils.onAuditCreateSuccess (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditUtils.onAuditModifySuccess (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditUtils.onAuditModifyFailure (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditUtils.onAuditDeleteSuccess (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditUtils.onAuditDeleteFailure (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditUtils.onAuditUndeleteSuccess (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditUtils.onAuditUndeleteFailure (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditUtils.onAuditExecuteSuccess ("spawn", "this", "is", Integer.valueOf (2), "a", "test");
      AuditUtils.onAuditExecuteFailure ("spawn", "this", "is", Integer.valueOf (2), "a", "test");
      AuditUtils.onAuditExecuteSuccess (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditUtils.onAuditExecuteFailure (aOT, "this", "is", Integer.valueOf (2), "a", "test");
    }
    finally
    {
      AuditUtils.setDefaultAuditor ();
    }
  }
}
