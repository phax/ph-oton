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
package com.helger.photon.security.object.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.Clock;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.photon.basic.app.dao.impl.DAOException;
import com.helger.photon.basic.mock.PhotonBasicWebTestRule;
import com.helger.photon.basic.object.client.IClient;
import com.helger.photon.security.CSecurity;
import com.helger.photon.security.login.ELoginResult;
import com.helger.photon.security.login.LoggedInUserManager;
import com.helger.photon.security.mgr.PhotonSecurityManager;

/**
 * Test class for class {@link ClientManager}
 *
 * @author Philip Helger
 */
public final class ClientManagerTest
{
  @Rule
  public final TestRule m_aRule = new PhotonBasicWebTestRule ();

  @Test
  public void testAny () throws DAOException
  {
    PhotonSecurityManager.getUserMgr ().createDefaults ();

    final ClientManager aMgr = new ClientManager ("dummy-client.xml");

    // Login required for deletion
    final LoggedInUserManager aLUM = LoggedInUserManager.getInstance ();
    assertEquals (ELoginResult.SUCCESS,
                  aLUM.loginUser (CSecurity.USER_ADMINISTRATOR_LOGIN, CSecurity.USER_ADMINISTRATOR_PASSWORD));
    try
    {
      final String sClientID = Long.toString (Clock.systemUTC ().millis ());
      final IClient aClient = aMgr.createClient (sClientID, "Name1");
      assertNotNull (aClient);
      try
      {
        assertNull (aMgr.createClient (sClientID, "Was anderes"));
      }
      finally
      {
        aMgr.deleteClient (sClientID);
      }
    }
    finally
    {
      aLUM.logoutCurrentUser ();
    }
  }
}
