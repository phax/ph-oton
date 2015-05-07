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
package com.helger.photon.core.app;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.photon.core.app.LinkUtils;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;
import com.helger.web.scopes.mgr.WebScopeManager;
import com.helger.web.scopes.mock.WebScopeTestRule;

/**
 * Test class for class {@link LinkUtils}.
 * 
 * @author Philip Helger
 */
public final class LinkUtilsTest
{
  @Rule
  public final TestRule m_aRules = new WebScopeTestRule ().setContextPath ("");

  @Test
  public void testStreamServletName ()
  {
    assertEquals (LinkUtils.DEFAULT_STREAM_SERVLET_NAME, LinkUtils.getStreamServletName ());
    LinkUtils.setStreamServletName ("abc");
    final IRequestWebScopeWithoutResponse aRequestScope = WebScopeManager.getRequestScope ();
    assertEquals ("/abc/x", LinkUtils.getStreamURL (aRequestScope, "x").getAsString ());
    LinkUtils.setStreamServletName (LinkUtils.DEFAULT_STREAM_SERVLET_NAME);
    assertEquals (LinkUtils.DEFAULT_STREAM_SERVLET_NAME, LinkUtils.getStreamServletName ());
    assertEquals ("/" + LinkUtils.DEFAULT_STREAM_SERVLET_NAME + "/x", LinkUtils.getStreamURL (aRequestScope, "x")
                                                                               .getAsString ());
  }
}
