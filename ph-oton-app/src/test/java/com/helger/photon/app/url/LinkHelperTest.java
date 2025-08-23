/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.app.url;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.mgr.WebScopeManager;
import com.helger.web.scope.mock.WebScopeTestRule;

/**
 * Test class for class {@link LinkHelper}.
 *
 * @author Philip Helger
 */
public final class LinkHelperTest
{
  @Rule
  public final TestRule m_aRules = new WebScopeTestRule ().setContextPath ("");

  @Test
  public void testStreamServletName ()
  {
    assertEquals (LinkHelper.DEFAULT_STREAM_SERVLET_NAME, LinkHelper.getStreamServletName ());
    LinkHelper.setStreamServletName ("abc");
    final IRequestWebScopeWithoutResponse aRequestScope = WebScopeManager.getRequestScope ();
    assertEquals ("/abc/x", LinkHelper.getStreamURL (aRequestScope, "x").getAsString ());
    LinkHelper.setStreamServletName (LinkHelper.DEFAULT_STREAM_SERVLET_NAME);
    assertEquals (LinkHelper.DEFAULT_STREAM_SERVLET_NAME, LinkHelper.getStreamServletName ());
    assertEquals ("/" + LinkHelper.DEFAULT_STREAM_SERVLET_NAME + "/x",
                  LinkHelper.getStreamURL (aRequestScope, "x").getAsString ());
  }
}
