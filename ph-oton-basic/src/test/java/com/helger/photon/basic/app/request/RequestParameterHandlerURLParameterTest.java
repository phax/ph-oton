/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.app.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import javax.annotation.Nonnull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.commons.locale.LocaleCache;
import com.helger.commons.url.SimpleURL;
import com.helger.photon.basic.app.locale.ApplicationLocaleManager;
import com.helger.photon.basic.app.menu.ApplicationMenuTree;
import com.helger.photon.basic.app.menu.IMenuItemPage;
import com.helger.photon.basic.app.page.AbstractPage;
import com.helger.photon.basic.mock.PhotonBasicWebTestRule;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.mgr.WebScopeManager;

/**
 * Test class for class {@link RequestParameterHandlerURLParameter}.
 *
 * @author Philip Helger
 */
public final class RequestParameterHandlerURLParameterTest
{
  @Rule
  public final TestRule m_aRule = new PhotonBasicWebTestRule ();

  private static final Locale LOCALE = LocaleCache.getInstance ().getLocale ("de_AT");

  private void _test (@Nonnull final String sBasePath, @Nonnull final IMenuItemPage aMenuItem)
  {
    final IRequestWebScopeWithoutResponse aRequestScope = WebScopeManager.getRequestScope ();
    final RequestParameterHandlerURLParameter h = new RequestParameterHandlerURLParameter ();
    final String sParamLocale = h.getRequestParamNameLocale ();
    final String sParamMenuItem = h.getRequestParamNameMenuItem ();

    // No params
    SimpleURL aURL = h.buildURL (aRequestScope, sBasePath, null, null);
    assertEquals (sBasePath, aURL.getAsStringWithEncodedParameters ());
    PhotonRequestParameters aParams = h.getParametersFromURL (aURL);
    assertFalse (aParams.hasLocale ());
    assertFalse (aParams.hasMenuItem ());

    // Locale only
    aURL = h.buildURL (aRequestScope, sBasePath, LOCALE, null);
    assertEquals (sBasePath + "?" + sParamLocale + "=de_AT", aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromURL (aURL);
    assertTrue (aParams.hasLocale ());
    assertEquals (LOCALE, aParams.getLocale ());
    assertFalse (aParams.hasMenuItem ());

    // Menu item only
    aURL = h.buildURL (aRequestScope, sBasePath, null, aMenuItem.getID ());
    assertEquals (sBasePath + "?" + sParamMenuItem + "=test", aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromURL (aURL);
    assertFalse (aParams.hasLocale ());
    assertTrue (aParams.hasMenuItem ());
    assertEquals (aMenuItem, aParams.getMenuItem ());

    // Locale and menu item
    aURL = h.buildURL (aRequestScope, sBasePath, LOCALE, aMenuItem.getID ());
    assertEquals (sBasePath +
                  "?" +
                  sParamLocale +
                  "=de_AT&" +
                  sParamMenuItem +
                  "=test",
                  aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromURL (aURL);
    assertTrue (aParams.hasLocale ());
    assertEquals (LOCALE, aParams.getLocale ());
    assertTrue (aParams.hasMenuItem ());
    assertEquals (aMenuItem, aParams.getMenuItem ());
  }

  @Test
  public void testBasic ()
  {
    ApplicationLocaleManager.getLocaleMgr ().registerLocale (LOCALE);
    final IMenuItemPage aMenuItem = ApplicationMenuTree.getTree ().createRootItem (new AbstractPage ("test")
    {});

    _test ("", aMenuItem);
    _test ("/ctx", aMenuItem);
    _test ("/servlet", aMenuItem);
    _test ("/ctx/servlet", aMenuItem);
  }
}
