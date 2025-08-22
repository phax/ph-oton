/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.audit.v2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.base.state.ESuccess;
import com.helger.photon.audit.EAuditActionType;
import com.helger.photon.audit.v2.domain.AuditEvent;
import com.helger.photon.audit.v2.domain.AuditField;
import com.helger.unittest.support.TestHelper;
import com.helger.xml.mock.XMLTestHelper;

/**
 * Test class for class {@link AuditEventBuilder}.
 *
 * @author Philip Helger
 */
public final class AuditEventBuilderTest
{
  @Test
  public void testEmpty ()
  {
    final AuditEventBuilder b = new AuditEventBuilder ();
    final AuditEvent aEvent = b.build ();
    assertNotNull (aEvent);
    assertNotNull (aEvent.getCreationDateTime ());
    assertNull (aEvent.getActor ());
    assertNull (aEvent.getOrigin ());
    assertNull (aEvent.getAction ());
    assertNull (aEvent.getSuccess ());
    assertNotNull (aEvent.getAllFields ());
    assertTrue (aEvent.getAllFields ().isEmpty ());
    assertNotNull (aEvent.fields ());
    assertTrue (aEvent.fields ().isEmpty ());

    // Test serialization
    TestHelper.testDefaultSerialization (aEvent);
    XMLTestHelper.testMicroTypeConversion (aEvent);
  }

  @Test
  public void testFilled ()
  {
    final AuditEventBuilder b = new AuditEventBuilder ();
    final AuditEvent aEvent = b.setActor ("a")
                               .setOrigin ("o")
                               .setAction (EAuditActionType.CREATE)
                               .setSucces (ESuccess.SUCCESS)
                               .addField (new AuditField ("n1", "v1"))
                               .addField ("n2", "v2")
                               .addFieldHiddenValue ("n3")
                               .build ();
    assertNotNull (aEvent);
    assertNotNull (aEvent.getCreationDateTime ());
    assertEquals ("a", aEvent.getActor ());
    assertEquals ("o", aEvent.getOrigin ());
    assertEquals (EAuditActionType.CREATE, aEvent.getAction ());
    assertEquals (ESuccess.SUCCESS, aEvent.getSuccess ());
    assertNotNull (aEvent.getAllFields ());
    assertEquals (3, aEvent.getAllFields ().size ());
    assertNotNull (aEvent.fields ());
    assertEquals (3, aEvent.fields ().size ());

    // Test serialization
    TestHelper.testDefaultSerialization (aEvent);
    XMLTestHelper.testMicroTypeConversion (aEvent);
  }

  @Test
  public void testCurrentUserIDProvider ()
  {
    final AuditEventBuilder b = new AuditEventBuilder ();
    final AuditEvent aEvent = b.setCurrentUserIDProvider ( () -> "17").build ();
    assertNotNull (aEvent);
    assertNotNull (aEvent.getCreationDateTime ());
    assertEquals ("17", aEvent.getActor ());
    assertNull (aEvent.getOrigin ());
    assertNull (aEvent.getAction ());
    assertNull (aEvent.getSuccess ());
    assertNotNull (aEvent.getAllFields ());
    assertTrue (aEvent.getAllFields ().isEmpty ());
    assertNotNull (aEvent.fields ());
    assertTrue (aEvent.fields ().isEmpty ());

    // Test serialization
    TestHelper.testDefaultSerialization (aEvent);
    XMLTestHelper.testMicroTypeConversion (aEvent);
  }
}
