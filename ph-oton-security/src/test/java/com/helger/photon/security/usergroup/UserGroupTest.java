/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.security.usergroup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.commons.collection.attr.StringMap;
import com.helger.photon.basic.mock.PhotonBasicWebTestRule;
import com.helger.photon.security.object.StubObject;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.convert.MicroTypeConverter;

/**
 * Test class for class {@link UserGroup}.
 *
 * @author Philip Helger
 */
public final class UserGroupTest
{
  @Rule
  public final TestRule m_aRule = new PhotonBasicWebTestRule ();

  @Test
  public void testBasic ()
  {
    final UserGroup aUserGroup = new UserGroup (StubObject.createForCurrentUserAndID ("id1"), "User group 5", "bla");
    assertEquals ("id1", aUserGroup.getID ());
    assertEquals ("User group 5", aUserGroup.getName ());
  }

  @Test
  public void testMicroConversion ()
  {
    final UserGroup aUserGroup = new UserGroup (StubObject.createForCurrentUserAndID ("id1",
                                                                                      new StringMap ("key", "value")),
                                                "User group 5",
                                                "bla");
    aUserGroup.assignUser ("user1");
    aUserGroup.assignUser ("user2");
    aUserGroup.assignRole ("role1");
    aUserGroup.assignRole ("role2");

    // To XML
    final IMicroElement aElement = MicroTypeConverter.convertToMicroElement (aUserGroup, "usergroup");
    assertNotNull (aElement);

    // From XML
    final UserGroup aUserGroup2 = MicroTypeConverter.convertToNative (aElement, UserGroup.class);
    assertNotNull (aUserGroup2);
    assertEquals ("id1", aUserGroup2.getID ());
    assertEquals ("User group 5", aUserGroup2.getName ());
    assertEquals (2, aUserGroup2.getAllContainedUserIDs ().size ());
    assertTrue (aUserGroup2.getAllContainedUserIDs ().contains ("user1"));
    assertTrue (aUserGroup2.getAllContainedUserIDs ().contains ("user2"));
    assertEquals (2, aUserGroup2.getAllContainedRoleIDs ().size ());
    assertTrue (aUserGroup2.getAllContainedRoleIDs ().contains ("role1"));
    assertTrue (aUserGroup2.getAllContainedRoleIDs ().contains ("role2"));
    assertEquals (1, aUserGroup2.customAttrs ().size ());
    assertEquals ("value", aUserGroup2.customAttrs ().getAsString ("key"));
  }
}
