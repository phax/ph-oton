/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.core.requestparam;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.commons.locale.LocaleCache;
import com.helger.commons.url.SimpleURL;
import com.helger.photon.app.mock.PhotonAppWebTestRule;
import com.helger.photon.core.locale.GlobalLocaleManager;
import com.helger.photon.core.menu.IMenuItemPage;
import com.helger.photon.core.menu.MenuTree;
import com.helger.photon.core.page.AbstractPage;
import com.helger.servlet.mock.MockHttpServletRequest;
import com.helger.servlet.mock.MockHttpServletResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.impl.RequestWebScope;

/**
 * Test class for class {@link RequestParameterHandlerURLPathOrdered}.
 *
 * @author Philip Helger
 */
public final class RequestParameterHandlerURLPathOrderedTest
{
  @Rule
  public final TestRule m_aRule = new PhotonAppWebTestRule ();

  private static final Locale LOCALE = LocaleCache.getInstance ().getLocale ("de_AT");

  @Test
  public void testBasic ()
  {
    final MockHttpServletRequest aRequest = new MockHttpServletRequest ();
    final IRequestWebScopeWithoutResponse aRequestScope = new RequestWebScope (aRequest, new MockHttpServletResponse ());
    final RequestParameterHandlerURLPathOrdered h = new RequestParameterHandlerURLPathOrdered ();

    GlobalLocaleManager.getInstance ().registerLocale (LOCALE);
    final MenuTree aMenuTree = new MenuTree ();
    final IMenuItemPage aMenuItem = aMenuTree.createRootItem (new AbstractPage ("test")
    {});

    // No params
    SimpleURL aURL = h.buildURL (aRequestScope, "", null, null);
    assertEquals ("", aURL.getAsStringWithEncodedParameters ());
    aRequest.setPathInfo (aURL.getAsStringWithEncodedParameters ());
    PhotonRequestParameters aParams = h.getParametersFromRequest (aRequestScope, aMenuTree);
    assertFalse (aParams.hasLocale ());
    assertFalse (aParams.hasMenuItem ());

    // Locale only
    aURL = h.buildURL (aRequestScope, "", LOCALE, null);
    assertEquals ("/de_AT", aURL.getAsStringWithEncodedParameters ());
    aRequest.setPathInfo (aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromRequest (aRequestScope, aMenuTree);
    assertTrue (aParams.hasLocale ());
    assertEquals (LOCALE, aParams.getLocale ());
    assertFalse (aParams.hasMenuItem ());

    // Locale only but not existing
    aURL = h.buildURL (aRequestScope, "", Locale.US, null);
    assertEquals ("/en_US", aURL.getAsStringWithEncodedParameters ());
    aRequest.setPathInfo (aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromRequest (aRequestScope, aMenuTree);
    assertFalse (aParams.hasLocale ());
    assertFalse (aParams.hasMenuItem ());

    // Menu item only
    aURL = h.buildURL (aRequestScope, "", null, aMenuItem.getID ());
    assertEquals ("/test", aURL.getAsStringWithEncodedParameters ());
    aRequest.setPathInfo (aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromRequest (aRequestScope, aMenuTree);
    assertFalse (aParams.hasLocale ());
    assertTrue (aParams.hasMenuItem ());
    assertEquals (aMenuItem, aParams.getMenuItem ());

    // Menu item only but not existing
    aURL = h.buildURL (aRequestScope, "", null, "other");
    assertEquals ("/other", aURL.getAsStringWithEncodedParameters ());
    aRequest.setPathInfo (aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromRequest (aRequestScope, aMenuTree);
    assertFalse (aParams.hasLocale ());
    assertFalse (aParams.hasMenuItem ());

    // Locale and menu item
    aURL = h.buildURL (aRequestScope, "", LOCALE, aMenuItem.getID ());
    assertEquals ("/de_AT/test", aURL.getAsStringWithEncodedParameters ());
    aRequest.setPathInfo (aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromRequest (aRequestScope, aMenuTree);
    assertTrue (aParams.hasLocale ());
    assertEquals (LOCALE, aParams.getLocale ());
    assertTrue (aParams.hasMenuItem ());

    // Locale and menu item - locale invalid
    aURL = h.buildURL (aRequestScope, "", Locale.US, aMenuItem.getID ());
    assertEquals ("/en_US/test", aURL.getAsStringWithEncodedParameters ());
    aRequest.setPathInfo (aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromRequest (aRequestScope, aMenuTree);
    assertFalse (aParams.hasLocale ());
    assertTrue (aParams.hasMenuItem ());
    assertEquals (aMenuItem, aParams.getMenuItem ());

    // Locale and menu item - menu item invalid
    aURL = h.buildURL (aRequestScope, "", LOCALE, "other");
    assertEquals ("/de_AT/other", aURL.getAsStringWithEncodedParameters ());
    aRequest.setPathInfo (aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromRequest (aRequestScope, aMenuTree);
    assertTrue (aParams.hasLocale ());
    assertEquals (LOCALE, aParams.getLocale ());
    assertFalse (aParams.hasMenuItem ());

    // Locale and menu item - both invalid
    aURL = h.buildURL (aRequestScope, "", Locale.US, "other");
    assertEquals ("/en_US/other", aURL.getAsStringWithEncodedParameters ());
    aRequest.setPathInfo (aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromRequest (aRequestScope, aMenuTree);
    assertFalse (aParams.hasLocale ());
    assertFalse (aParams.hasMenuItem ());
  }
}
