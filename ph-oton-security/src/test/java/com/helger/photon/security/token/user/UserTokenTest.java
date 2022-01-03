/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.security.token.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Locale;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.commons.collection.attr.StringMap;
import com.helger.commons.string.StringHelper;
import com.helger.photon.app.mock.PhotonAppWebTestRule;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.user.IUser;
import com.helger.photon.security.user.IUserManager;
import com.helger.xml.mock.XMLTestHelper;

/**
 * Test class for class {@link UserToken}.
 *
 * @author Philip Helger
 */
public final class UserTokenTest
{
  @Rule
  public final TestRule m_aRule = new PhotonAppWebTestRule ().setDeleteAllData (true);

  @Test
  public void testCreate ()
  {
    final IUserManager aUserMgr = PhotonSecurityManager.getUserMgr ();
    final IUser aUser = aUserMgr.createPredefinedUser ("id1",
                                                       "user1",
                                                       "test@example.org",
                                                       "foobar",
                                                       "Test",
                                                       "Last",
                                                       "description",
                                                       Locale.US,
                                                       null,
                                                       false);
    assertNotNull (aUser);

    final UserToken aUserToken = new UserToken (null, (Map <String, String>) null, aUser);
    assertTrue (StringHelper.hasText (aUserToken.getActiveTokenString ()));
    XMLTestHelper.testMicroTypeConversion (aUserToken);
  }

  @Test
  public void testCreateWithCustomAttrs ()
  {
    final IUserManager aUserMgr = PhotonSecurityManager.getUserMgr ();
    final IUser aUser = aUserMgr.createPredefinedUser ("id2",
                                                       "user2",
                                                       "test@example.org",
                                                       "foobar",
                                                       "Test",
                                                       "Last",
                                                       "description",
                                                       Locale.US,
                                                       null,
                                                       false);
    assertNotNull (aUser);

    final UserToken aUserToken = new UserToken (null, new StringMap ("key", "value"), aUser);
    assertTrue (StringHelper.hasText (aUserToken.getActiveTokenString ()));
    assertEquals (1, aUserToken.attrs ().size ());
    assertEquals ("value", aUserToken.attrs ().getAsString ("key"));

    XMLTestHelper.testMicroTypeConversion (aUserToken);
  }
}
