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
package com.helger.photon.security.token.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.datetime.helper.PDTFactory;
import com.helger.photon.app.mock.PhotonAppWebTestRule;
import com.helger.photon.security.CSecurity;
import com.helger.photon.security.auth.UserTokenAuthCredentialValidatorSPI;
import com.helger.photon.security.login.ELoginResult;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.token.accesstoken.AccessToken;
import com.helger.photon.security.token.accesstoken.IAccessToken;
import com.helger.photon.security.token.credentials.TokenCredentials;
import com.helger.photon.security.user.IUser;
import com.helger.photon.security.user.IUserManager;

/**
 * Test class for class {@link UserTokenManager}.
 *
 * @author Philip Helger
 */
public final class UserTokenManagerTest
{
  @Rule
  public final TestRule m_aRule = new PhotonAppWebTestRule ().setDeleteAllData (true);

  private static IUserToken _createUserToken ()
  {
    PhotonSecurityManager.getUserMgr ().createDefaultsForTest ();

    final IUser aUser = PhotonSecurityManager.getUserMgr ().getUserOfID (CSecurity.USER_USER_ID);
    assertNotNull (aUser);
    return PhotonSecurityManager.getUserTokenMgr ()
                                .createUserToken (null, (Map <String, String>) null, aUser, "test token");
  }

  @Test
  public void testResolveActiveToken ()
  {
    final IUserToken aUserToken = _createUserToken ();
    final String sTokenString = aUserToken.getAccessTokenList ().getActiveTokenString ();
    assertTrue (sTokenString != null && sTokenString.length () > 0);

    assertSame (aUserToken,
                PhotonSecurityManager.getUserTokenMgr ().getUserTokenOfTokenString (sTokenString));
  }

  /**
   * An expired access token must no longer resolve to its user token.
   */
  @Test
  public void testExpiredTokenIsNotResolved ()
  {
    final IUserToken aUserToken = _createUserToken ();
    final String sTokenString = aUserToken.getAccessTokenList ().getActiveTokenString ();

    // Let the access token expire - the earliest possible "not after" is the
    // "not before" date time, which already lies in the past
    final IAccessToken aAccessToken = aUserToken.getAccessTokenList ().getActiveAccessToken ();
    assertNotNull (aAccessToken);
    ((AccessToken) aAccessToken).setNotAfter (aAccessToken.getNotBefore ());
    assertFalse (aAccessToken.isValidNow ());

    assertNull (PhotonSecurityManager.getUserTokenMgr ().getUserTokenOfTokenString (sTokenString));
  }

  /**
   * A revoked access token must no longer resolve to its user token.
   */
  @Test
  public void testRevokedTokenIsNotResolved ()
  {
    final IUserToken aUserToken = _createUserToken ();
    final String sTokenString = aUserToken.getAccessTokenList ().getActiveTokenString ();

    assertTrue (PhotonSecurityManager.getUserTokenMgr ()
                                     .revokeAccessToken (aUserToken.getID (),
                                                         CSecurity.USER_ADMINISTRATOR_ID,
                                                         PDTFactory.getCurrentLocalDateTime (),
                                                         "for testing")
                                     .isChanged ());

    assertNull (PhotonSecurityManager.getUserTokenMgr ().getUserTokenOfTokenString (sTokenString));
  }

  /**
   * A token of a deleted or disabled user must not validate.
   */
  @Test
  public void testCredentialValidationChecksUserState ()
  {
    final IUserManager aUserMgr = PhotonSecurityManager.getUserMgr ();
    final IUserToken aUserToken = _createUserToken ();
    final String sTokenString = aUserToken.getAccessTokenList ().getActiveTokenString ();

    final UserTokenAuthCredentialValidatorSPI aValidator = new UserTokenAuthCredentialValidatorSPI ();
    final TokenCredentials aCredentials = new TokenCredentials (sTokenString);
    assertTrue (aValidator.supportsCredentials (aCredentials));

    // All good
    assertEquals (ELoginResult.SUCCESS, aValidator.validateCredentials (aCredentials));

    // Disabled user
    assertTrue (aUserMgr.disableUser (CSecurity.USER_USER_ID).isChanged ());
    assertEquals (ELoginResult.USER_IS_DISABLED, aValidator.validateCredentials (aCredentials));
    assertTrue (aUserMgr.enableUser (CSecurity.USER_USER_ID).isChanged ());

    // Deleted user
    assertTrue (aUserMgr.deleteUser (CSecurity.USER_USER_ID).isChanged ());
    assertEquals (ELoginResult.USER_IS_DELETED, aValidator.validateCredentials (aCredentials));
    assertTrue (aUserMgr.undeleteUser (CSecurity.USER_USER_ID).isChanged ());

    // Unknown token
    assertEquals (ELoginResult.TOKEN_NOT_EXISTING,
                  aValidator.validateCredentials (new TokenCredentials ("no-such-token")));
  }
}
