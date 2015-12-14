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
package com.helger.photon.security.role;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.convert.MicroTypeConverter;
import com.helger.commons.url.SMap;
import com.helger.photon.basic.mock.PhotonBasicWebTestRule;
import com.helger.photon.security.object.StubObjectWithCustomAttrs;

/**
 * Test class for class {@link Role}.
 *
 * @author Philip Helger
 */
public final class RoleTest
{
  @Rule
  public final TestRule m_aRule = new PhotonBasicWebTestRule ();

  @Test
  public void testBasic ()
  {
    final Role aRole = new Role (StubObjectWithCustomAttrs.createForCurrentUserAndID ("id1"), "Role 1", "Test role");
    assertEquals ("id1", aRole.getID ());
    assertEquals ("Role 1", aRole.getName ());
  }

  @Test
  public void testMicroConversion ()
  {
    final Role aRole = new Role (StubObjectWithCustomAttrs.createForCurrentUserAndID ("id1", new SMap ("key", "value")),
                                 "Role 1",
                                 "bla");

    // To XML
    final IMicroElement aElement = MicroTypeConverter.convertToMicroElement (aRole, "role");
    assertNotNull (aElement);

    // From XML
    final Role aRole2 = MicroTypeConverter.convertToNative (aElement, Role.class);
    assertNotNull (aRole2);
    assertEquals ("id1", aRole2.getID ());
    assertEquals ("Role 1", aRole2.getName ());
    assertEquals (1, aRole2.getAttributes ().getAttributeCount ());
    assertEquals ("value", aRole2.getAttributes ().getAttributeAsString ("key"));
  }
}
