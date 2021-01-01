/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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

import org.junit.Test;

import com.helger.commons.type.ObjectType;
import com.helger.photon.app.mock.MockCurrentUserIDProvider;

/**
 * Test class for class {@link LoggingAuditor}.
 *
 * @author Philip Helger
 */
public final class LoggingAuditorTest
{
  @Test
  public void testBasic ()
  {
    final ObjectType aOT = new ObjectType ("mock");
    AuditHelper.setAuditor (new LoggingAuditor (new MockCurrentUserIDProvider ("userid")));
    try
    {
      AuditHelper.onAuditCreateSuccess (aOT);
      AuditHelper.onAuditCreateSuccess (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditHelper.onAuditModifySuccess (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditHelper.onAuditModifyFailure (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditHelper.onAuditDeleteSuccess (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditHelper.onAuditDeleteFailure (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditHelper.onAuditUndeleteSuccess (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditHelper.onAuditUndeleteFailure (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditHelper.onAuditExecuteSuccess ("spawn", "this", "is", Integer.valueOf (2), "a", "test");
      AuditHelper.onAuditExecuteFailure ("spawn", "this", "is", Integer.valueOf (2), "a", "test");
      AuditHelper.onAuditExecuteSuccess (aOT, "this", "is", Integer.valueOf (2), "a", "test");
      AuditHelper.onAuditExecuteFailure (aOT, "this", "is", Integer.valueOf (2), "a", "test");
    }
    finally
    {
      AuditHelper.setDefaultAuditor ();
    }
  }
}
