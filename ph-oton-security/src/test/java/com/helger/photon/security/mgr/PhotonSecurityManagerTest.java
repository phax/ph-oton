/*
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
package com.helger.photon.security.mgr;

import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.photon.app.mock.PhotonAppWebTestRule;
import com.helger.photon.security.CSecurity;
import com.helger.photon.security.lock.DefaultLockManager;
import com.helger.photon.security.role.IRoleManager;
import com.helger.photon.security.role.Role;
import com.helger.photon.security.token.user.IUserTokenManager;
import com.helger.photon.security.user.IUserManager;
import com.helger.photon.security.usergroup.IUserGroupManager;

/**
 * Test class for class {@link Role}.
 *
 * @author Philip Helger
 */
public final class PhotonSecurityManagerTest
{
  @Rule
  public final TestRule m_aRule = new PhotonAppWebTestRule ();

  @Test
  public void testStartup ()
  {
    final DefaultLockManager <String> aLockMgr = PhotonSecurityManager.getLockMgr ();
    assertNotNull (aLockMgr);
    final IRoleManager aRoleMgr = PhotonSecurityManager.getRoleMgr ();
    assertNotNull (aRoleMgr);
    final IUserManager aUserMgr = PhotonSecurityManager.getUserMgr ();
    assertNotNull (aUserMgr);
    final IUserGroupManager aUserGroupMgr = PhotonSecurityManager.getUserGroupMgr ();
    assertNotNull (aUserGroupMgr);
    final IUserTokenManager aUserTokenMgr = PhotonSecurityManager.getUserTokenMgr ();
    assertNotNull (aUserTokenMgr);

    aRoleMgr.createDefaultsForTest ();
    aUserMgr.createDefaultsForTest ();
    aUserGroupMgr.createDefaultsForTest ();

    // Check default stuff
    assertNotNull (aRoleMgr.getRoleOfID (CSecurity.ROLE_ADMINISTRATOR_ID));
    assertNotNull (aRoleMgr.getRoleOfID (CSecurity.ROLE_USER_ID));

    assertNotNull (aUserMgr.getUserOfID (CSecurity.USER_ADMINISTRATOR_ID));
    assertNotNull (aUserMgr.getUserOfID (CSecurity.USER_USER_ID));
    assertNotNull (aUserMgr.getUserOfID (CSecurity.USER_GUEST_ID));

    assertNotNull (aUserGroupMgr.getUserGroupOfID (CSecurity.USERGROUP_ADMINISTRATORS_ID));
    assertNotNull (aUserGroupMgr.getUserGroupOfID (CSecurity.USERGROUP_USERS_ID));
    assertNotNull (aUserGroupMgr.getUserGroupOfID (CSecurity.USERGROUP_GUESTS_ID));
  }
}
