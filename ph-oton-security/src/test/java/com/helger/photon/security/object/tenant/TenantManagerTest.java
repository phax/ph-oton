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
package com.helger.photon.security.object.tenant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.Clock;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.dao.DAOException;
import com.helger.photon.basic.mock.PhotonBasicWebTestRule;
import com.helger.photon.security.CSecurity;
import com.helger.photon.security.login.ELoginResult;
import com.helger.photon.security.login.LoggedInUserManager;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.tenancy.tenant.ITenant;

/**
 * Test class for class {@link TenantManager}
 *
 * @author Philip Helger
 */
public final class TenantManagerTest
{
  @Rule
  public final TestRule m_aRule = new PhotonBasicWebTestRule ();

  @Test
  public void testAny () throws DAOException
  {
    PhotonSecurityManager.getUserMgr ().createDefaults ();

    final TenantManager aMgr = new TenantManager ("dummy-tenant.xml");

    // Login required for deletion
    final LoggedInUserManager aLUM = LoggedInUserManager.getInstance ();
    assertEquals (ELoginResult.SUCCESS,
                  aLUM.loginUser (CSecurity.USER_ADMINISTRATOR_LOGIN, CSecurity.USER_ADMINISTRATOR_PASSWORD));
    try
    {
      final String sTenantID = Long.toString (Clock.systemUTC ().millis ());
      final ITenant aTenant = aMgr.createTenant (sTenantID, "Name1");
      assertNotNull (aTenant);
      try
      {
        assertNull (aMgr.createTenant (sTenantID, "Was anderes"));
      }
      finally
      {
        aMgr.deleteTenant (sTenantID);
      }
    }
    finally
    {
      aLUM.logoutCurrentUser ();
    }
  }
}
