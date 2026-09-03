/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.security.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.photon.app.mock.PhotonAppWebTestRule;
import com.helger.photon.security.CSecurity;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.user.IUserManager;

/**
 * Test class for class {@link UserModificationLogoutCallback}.
 *
 * @author Philip Helger
 */
public final class UserModificationLogoutCallbackTest
{
  @Rule
  public final TestRule m_aRule = new PhotonAppWebTestRule ().setDeleteAllData (true);

  private static void _login ()
  {
    PhotonSecurityManager.getUserMgr ().createDefaultsForTest ();

    final LoggedInUserManager aUM = LoggedInUserManager.getInstance ();
    assertEquals (ELoginResult.SUCCESS, aUM.loginUser (CSecurity.USER_USER_LOGIN, CSecurity.USER_USER_PASSWORD));
    assertTrue (aUM.isUserLoggedIn (CSecurity.USER_USER_ID));
    assertEquals (CSecurity.USER_USER_ID, aUM.getCurrentUserID ());
  }

  @Test
  public void testLogoutOnUserDisabled ()
  {
    _login ();

    assertTrue (PhotonSecurityManager.getUserMgr ().disableUser (CSecurity.USER_USER_ID).isChanged ());

    final LoggedInUserManager aUM = LoggedInUserManager.getInstance ();
    assertFalse (aUM.isUserLoggedIn (CSecurity.USER_USER_ID));
    assertNull (aUM.getCurrentUserID ());
  }

  @Test
  public void testLogoutOnUserDeleted ()
  {
    _login ();

    assertTrue (PhotonSecurityManager.getUserMgr ().deleteUser (CSecurity.USER_USER_ID).isChanged ());

    final LoggedInUserManager aUM = LoggedInUserManager.getInstance ();
    assertFalse (aUM.isUserLoggedIn (CSecurity.USER_USER_ID));
    assertNull (aUM.getCurrentUserID ());
  }

  @Test
  public void testLogoutOnPasswordChanged ()
  {
    _login ();

    assertTrue (PhotonSecurityManager.getUserMgr ().setUserPassword (CSecurity.USER_USER_ID, "new-password").isChanged ());

    final LoggedInUserManager aUM = LoggedInUserManager.getInstance ();
    assertFalse (aUM.isUserLoggedIn (CSecurity.USER_USER_ID));
    assertNull (aUM.getCurrentUserID ());
  }

  @Test
  public void testUserEnabledDoesNotLogout ()
  {
    final IUserManager aUserMgr = PhotonSecurityManager.getUserMgr ();
    aUserMgr.createDefaultsForTest ();
    // Disable and enable again, before the login
    assertTrue (aUserMgr.disableUser (CSecurity.USER_USER_ID).isChanged ());
    assertTrue (aUserMgr.enableUser (CSecurity.USER_USER_ID).isChanged ());

    _login ();

    // Enabling an already enabled user must not log him out
    assertTrue (aUserMgr.enableUser (CSecurity.USER_USER_ID).isUnchanged ());

    final LoggedInUserManager aUM = LoggedInUserManager.getInstance ();
    assertTrue (aUM.isUserLoggedIn (CSecurity.USER_USER_ID));
    assertEquals (CSecurity.USER_USER_ID, aUM.getCurrentUserID ());
  }
}
