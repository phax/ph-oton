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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.photon.app.mock.PhotonAppWebTestRule;
import com.helger.photon.security.CSecurity;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.user.IUser;
import com.helger.photon.security.user.IUserManager;
import com.helger.scope.mgr.ScopeManager;

/**
 * Test class for class {@link LoggedInUserManager}.
 *
 * @author Philip Helger
 */
public final class LoggedInUserManagerTest
{
  @Rule
  public final TestRule m_aRule = new PhotonAppWebTestRule ();

  @Test
  public void testInit ()
  {
    LoggedInUserManager.getInstance ();
  }

  @Test
  public void testLoginLogout ()
  {
    PhotonSecurityManager.getUserMgr ().createDefaultsForTest ();

    final LoggedInUserManager aUM = LoggedInUserManager.getInstance ();
    // Check any non-present user
    assertFalse (aUM.isUserLoggedIn ("any"));
    assertEquals (ELoginResult.USER_NOT_EXISTING, aUM.loginUser ("bla", "mypw"));
    assertNull (aUM.getCurrentUserID ());

    // Login user
    assertEquals (ELoginResult.SUCCESS,
                  aUM.loginUser (CSecurity.USER_ADMINISTRATOR_LOGIN, CSecurity.USER_ADMINISTRATOR_PASSWORD));
    assertTrue (aUM.isUserLoggedIn (CSecurity.USER_ADMINISTRATOR_ID));
    assertEquals (1, aUM.getLoggedInUserCount ());
    assertEquals (CSecurity.USER_ADMINISTRATOR_ID, aUM.getCurrentUserID ());

    // Try to login another user in the same session
    assertEquals (ELoginResult.SESSION_ALREADY_HAS_USER,
                  aUM.loginUser (CSecurity.USER_USER_LOGIN, CSecurity.USER_USER_PASSWORD));
    assertTrue (aUM.isUserLoggedIn (CSecurity.USER_ADMINISTRATOR_ID));
    assertFalse (aUM.isUserLoggedIn (CSecurity.USER_USER_ID));
    assertEquals (1, aUM.getLoggedInUserCount ());
    assertEquals (CSecurity.USER_ADMINISTRATOR_ID, aUM.getCurrentUserID ());

    // Check current user ID
    assertEquals (CSecurity.USER_ADMINISTRATOR_ID, aUM.getCurrentUserID ());

    // Logout non-logged in user
    assertTrue (aUM.logoutUser (CSecurity.USER_USER_ID).isUnchanged ());
    assertEquals (1, aUM.getLoggedInUserCount ());
    assertEquals (CSecurity.USER_ADMINISTRATOR_ID, aUM.getCurrentUserID ());

    // Logout correct user
    assertTrue (aUM.logoutUser (aUM.getCurrentUserID ()).isChanged ());
    assertEquals (0, aUM.getLoggedInUserCount ());
    assertNull (aUM.getCurrentUserID ());
  }

  /**
   * A login that fails because the session already has a different user, must not log out the
   * existing login of the user that was to be logged in.
   */
  @Test
  public void testFailedLoginDoesNotLogoutOtherUser ()
  {
    PhotonSecurityManager.getUserMgr ().createDefaultsForTest ();

    final LoggedInUserManager aUM = LoggedInUserManager.getInstance ();
    aUM.setLogoutAlreadyLoggedInUser (true);
    try
    {
      // Simulate that the administrator is logged in "somewhere else"
      final IUser aAdmin = PhotonSecurityManager.getUserMgr ().getUserOfID (CSecurity.USER_ADMINISTRATOR_ID);
      assertNotNull (aAdmin);
      assertEquals (ELoginResult.SUCCESS,
                    aUM.internalSessionActivateUser (aAdmin, ScopeManager.getSessionScope ()));
      assertTrue (aUM.isUserLoggedIn (CSecurity.USER_ADMINISTRATOR_ID));

      // Login a different user in this session
      assertEquals (ELoginResult.SUCCESS, aUM.loginUser (CSecurity.USER_USER_LOGIN, CSecurity.USER_USER_PASSWORD));
      assertEquals (CSecurity.USER_USER_ID, aUM.getCurrentUserID ());

      // Now try to login the administrator in this session - this must fail,
      // and it must NOT log out the existing administrator login
      assertEquals (ELoginResult.SESSION_ALREADY_HAS_USER,
                    aUM.loginUser (CSecurity.USER_ADMINISTRATOR_LOGIN, CSecurity.USER_ADMINISTRATOR_PASSWORD));
      assertTrue (aUM.isUserLoggedIn (CSecurity.USER_ADMINISTRATOR_ID));
      assertTrue (aUM.isUserLoggedIn (CSecurity.USER_USER_ID));
      assertEquals (CSecurity.USER_USER_ID, aUM.getCurrentUserID ());
    }
    finally
    {
      aUM.setLogoutAlreadyLoggedInUser (LoggedInUserManager.DEFAULT_LOGOUT_ALREADY_LOGGED_IN_USER);
    }
  }

  /**
   * A user that was deleted or disabled while his session was passivated, must not be logged in
   * again upon session activation.
   */
  @Test
  public void testSessionActivateChecksUserState ()
  {
    final IUserManager aUserMgr = PhotonSecurityManager.getUserMgr ();
    aUserMgr.createDefaultsForTest ();

    final LoggedInUserManager aUM = LoggedInUserManager.getInstance ();
    final IUser aUser = aUserMgr.getUserOfID (CSecurity.USER_USER_ID);
    assertNotNull (aUser);

    // Disabled user
    assertTrue (aUserMgr.disableUser (CSecurity.USER_USER_ID).isChanged ());
    assertEquals (ELoginResult.USER_IS_DISABLED,
                  aUM.internalSessionActivateUser (aUser, ScopeManager.getSessionScope ()));
    assertFalse (aUM.isUserLoggedIn (CSecurity.USER_USER_ID));
    assertTrue (aUserMgr.enableUser (CSecurity.USER_USER_ID).isChanged ());

    // Deleted user
    assertTrue (aUserMgr.deleteUser (CSecurity.USER_USER_ID).isChanged ());
    assertEquals (ELoginResult.USER_IS_DELETED,
                  aUM.internalSessionActivateUser (aUser, ScopeManager.getSessionScope ()));
    assertFalse (aUM.isUserLoggedIn (CSecurity.USER_USER_ID));
    assertTrue (aUserMgr.undeleteUser (CSecurity.USER_USER_ID).isChanged ());

    // Valid user
    assertEquals (ELoginResult.SUCCESS, aUM.internalSessionActivateUser (aUser, ScopeManager.getSessionScope ()));
    assertTrue (aUM.isUserLoggedIn (CSecurity.USER_USER_ID));

    // A second activation of the same user must not succeed
    assertEquals (ELoginResult.USER_ALREADY_LOGGED_IN,
                  aUM.internalSessionActivateUser (aUser, ScopeManager.getSessionScope ()));
  }
}
