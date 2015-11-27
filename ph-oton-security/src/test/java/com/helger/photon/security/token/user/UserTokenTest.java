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
package com.helger.photon.security.token.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.commons.mock.CommonsTestHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.SMap;
import com.helger.photon.basic.mock.PhotonBasicWebTestRule;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.token.app.AppTokenManager;
import com.helger.photon.security.token.app.IAppToken;

/**
 * Test class for class {@link UserToken}.
 *
 * @author Philip Helger
 */
public final class UserTokenTest
{
  @Rule
  public final TestRule m_aRule = new PhotonBasicWebTestRule ();

  @Test
  public void testCreate ()
  {
    final AppTokenManager aAppTokenMgr = PhotonSecurityManager.getAppTokenMgr ();
    final IAppToken aAppToken = aAppTokenMgr.createAppToken (null, null, "unit test company", null, null, null);

    final UserToken aUserToken = new UserToken (null, (Map <String, String>) null, aAppToken, "Unit test user token");
    assertTrue (StringHelper.hasText (aUserToken.getActiveTokenString ()));
    CommonsTestHelper.testMicroTypeConversion (aUserToken);
  }

  @Test
  public void testCreateWithCustomAttrs ()
  {
    final AppTokenManager aAppTokenMgr = PhotonSecurityManager.getAppTokenMgr ();
    final IAppToken aAppToken = aAppTokenMgr.createAppToken (null, null, "unit test company", null, null, null);

    final UserToken aUserToken = new UserToken (null, new SMap ("key", "value"), aAppToken, "Unit test user token");
    assertTrue (StringHelper.hasText (aUserToken.getActiveTokenString ()));
    assertEquals (1, aUserToken.getAttributes ().getAttributeCount ());
    assertEquals ("value", aUserToken.getAttributes ().getAttributeAsString ("key"));

    CommonsTestHelper.testMicroTypeConversion (aUserToken);
  }
}
