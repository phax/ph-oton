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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.photon.app.mock.PhotonAppWebTestRule;
import com.helger.photon.security.CSecurity;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.servlet.mock.MockHttpServletRequest;
import com.helger.servlet.mock.MockServletContext;
import com.helger.web.scope.mgr.WebScoped;

/**
 * Test class for class {@link RequestUserIDProvider}.
 *
 * @author Philip Helger
 */
public final class RequestUserIDProviderTest
{
  /**
   * Test rule that creates no request scope, so that each test can create the request scopes it
   * needs on its own.
   */
  private static final class RequestLessTestRule extends PhotonAppWebTestRule
  {
    @Override
    @Nullable
    protected MockHttpServletRequest createMockRequest (@NonNull final MockServletContext aServletContext)
    {
      return null;
    }
  }

  @Rule
  public final TestRule m_aRule = new RequestLessTestRule ();

  @Test
  public void testNoRequestScope ()
  {
    assertNull (RequestUserIDProvider.getCurrentUserID ());
    assertTrue (RequestUserIDProvider.removeCurrentUserID ().isUnchanged ());

    // May not throw an exception
    RequestUserIDProvider.setCurrentUserID ("api-user");
    assertNull (RequestUserIDProvider.getCurrentUserID ());
  }

  @Test
  public void testSetAndRemove ()
  {
    try (final WebScoped aWebScoped = new WebScoped ())
    {
      assertNull (RequestUserIDProvider.getCurrentUserID ());

      RequestUserIDProvider.setCurrentUserID ("api-user");
      assertEquals ("api-user", RequestUserIDProvider.getCurrentUserID ());
      // No session may be created
      assertNull (aWebScoped.getRequestScope ().getSession (false));

      assertTrue (RequestUserIDProvider.removeCurrentUserID ().isChanged ());
      assertNull (RequestUserIDProvider.getCurrentUserID ());
      assertTrue (RequestUserIDProvider.removeCurrentUserID ().isUnchanged ());
    }
  }

  @Test
  public void testDoesNotLeakToNextRequest ()
  {
    try (final WebScoped aWebScoped = new WebScoped ())
    {
      RequestUserIDProvider.setCurrentUserID ("api-user");
      assertEquals ("api-user", RequestUserIDProvider.getCurrentUserID ());
    }

    try (final WebScoped aWebScoped = new WebScoped ())
    {
      assertNull (RequestUserIDProvider.getCurrentUserID ());
    }
  }

  @Test
  public void testGlobalUserIDProviderPrefersRequestUser ()
  {
    PhotonSecurityManager.getUserMgr ().createDefaultsForTest ();

    final LoggedInUserManager aLUM = LoggedInUserManager.getInstance ();
    try (final WebScoped aWebScoped = new WebScoped ())
    {
      // No user at all
      assertNull (GlobalUserIDProvider.getCurrentUserID ());

      // Only the request user
      RequestUserIDProvider.setCurrentUserID ("api-user");
      assertEquals ("api-user", GlobalUserIDProvider.getCurrentUserID ());

      // Log in a UI user - the request user still wins
      assertTrue (aLUM.loginUser (CSecurity.USER_ADMINISTRATOR_LOGIN, CSecurity.USER_ADMINISTRATOR_PASSWORD)
                      .isSuccess ());
      assertEquals ("api-user", GlobalUserIDProvider.getCurrentUserID ());
      assertEquals (CSecurity.USER_ADMINISTRATOR_ID, aLUM.getCurrentUserID ());

      // Without the request user, the session user is used
      assertTrue (RequestUserIDProvider.removeCurrentUserID ().isChanged ());
      assertEquals (CSecurity.USER_ADMINISTRATOR_ID, GlobalUserIDProvider.getCurrentUserID ());

      assertTrue (aLUM.logoutCurrentUser ().isChanged ());
      assertFalse (aLUM.isUserLoggedIn (CSecurity.USER_ADMINISTRATOR_ID));
    }
  }
}
