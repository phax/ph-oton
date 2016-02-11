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
package com.helger.photon.core.ajax.response;

import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.commons.url.SimpleURL;
import com.helger.html.hc.html.metadata.HCLink;
import com.helger.html.hc.html.metadata.HCStyle;
import com.helger.html.hc.html.root.HCHtml;
import com.helger.html.hc.html.script.HCScriptFile;
import com.helger.html.hc.html.script.HCScriptInline;
import com.helger.html.hc.html.sections.HCH1;
import com.helger.html.js.UnparsedJSCodeProvider;
import com.helger.photon.basic.mock.PhotonBasicWebTestRule;
import com.helger.web.mock.MockHttpServletResponse;
import com.helger.web.mock.OfflineHttpServletRequest;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.mgr.WebScopeManager;

/**
 * Test class for class {@link AjaxHtmlResponse}.
 *
 * @author Philip Helger
 */
public final class AjaxHtmlResponseTest
{
  @Rule
  public final TestRule m_aRule = new PhotonBasicWebTestRule ();

  @Test
  public void testFullHtml ()
  {
    final IRequestWebScopeWithoutResponse aRequestScope = WebScopeManager.onRequestBegin ("dummy",
                                                                                          new OfflineHttpServletRequest (),
                                                                                          new MockHttpServletResponse ());
    try
    {
      final HCHtml aHtml = new HCHtml ();
      aHtml.getHead ().addCSS (new HCStyle ("*{font-family:Helvetica;}"));
      aHtml.getHead ().addJS (new HCScriptInline (new UnparsedJSCodeProvider ("var x = 1;")));
      aHtml.getHead ().addCSS (HCLink.createCSSLink (new SimpleURL ("res/animate.css")));
      aHtml.getHead ().addJS (new HCScriptFile ().setSrc (new SimpleURL ("res/stacktrace.js")));
      aHtml.getBody ().addChild (new HCH1 ().addChild ("Test H1"));
      aHtml.getBody ().addChild (new HCStyle ("h1{color:red;}"));
      aHtml.getBody ().addChild (new HCScriptInline (new UnparsedJSCodeProvider ("var y = x;")));
      final AjaxHtmlResponse aResponse = AjaxHtmlResponse.createSuccess (aRequestScope, aHtml);
      assertNotNull (aResponse);
      assertNotNull (aResponse.getSuccessValue ());
      assertNotNull (aResponse.getSuccessValue ().getValue (AjaxHtmlResponse.PROPERTY_HTML));
      System.out.println (aResponse.getSuccessValue ().getValue (AjaxHtmlResponse.PROPERTY_HTML).getAsString ());
    }
    finally
    {
      WebScopeManager.onRequestEnd ();
    }
  }
}
