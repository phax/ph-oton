/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.core;

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
import com.helger.photon.core.PhotonUnifiedResponse;
import com.helger.web.scope.mgr.WebScoped;

/**
 * Test class for class {@link PhotonUnifiedResponse}.
 *
 * @author Philip Helger
 */
public final class PhotonUnifiedResponseTest
{
  @Rule
  public final TestRule m_aRule = new PhotonBasicWebTestRule ();

  @Test
  public void testFullHtml ()
  {
    try (final WebScoped aWebScoped = new WebScoped ())
    {
      final HCHtml aHtml = new HCHtml ();
      aHtml.head ().addCSS (new HCStyle ("*{font-family:Helvetica;}"));
      aHtml.head ().addJS (new HCScriptInline (new UnparsedJSCodeProvider ("var x = 1;")));
      aHtml.head ().addCSS (HCLink.createCSSLink (new SimpleURL ("res/animate.css")));
      aHtml.head ().addJS (new HCScriptFile ().setSrc (new SimpleURL ("res/stacktrace.js")));
      aHtml.body ().addChild (new HCH1 ().addChild ("Test H1"));
      aHtml.body ().addChild (new HCStyle ("h1{color:red;}"));
      aHtml.body ().addChild (new HCScriptInline (new UnparsedJSCodeProvider ("var y = x;")));
      final PhotonUnifiedResponse aResponse = PhotonUnifiedResponse.createSimple (aWebScoped.getRequestScope ());
      aResponse.html (aHtml);
    }
  }
}
