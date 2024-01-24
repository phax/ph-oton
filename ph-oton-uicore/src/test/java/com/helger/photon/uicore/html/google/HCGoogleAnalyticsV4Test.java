/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.html.google;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;

import com.helger.html.hc.config.EHCScriptInlineMode;
import com.helger.html.hc.mock.HCTestRuleOptimized;
import com.helger.html.hc.render.HCRenderer;
import com.helger.photon.core.mock.PhotonCoreWebAppTestRule;

/**
 * Test class for class {@link HCGoogleAnalyticsV4}
 *
 * @author Philip Helger
 */
public final class HCGoogleAnalyticsV4Test
{
  @Rule
  public final HCTestRuleOptimized m_aRule = new HCTestRuleOptimized ();

  @Rule
  public final PhotonCoreWebAppTestRule m_aRule2 = new PhotonCoreWebAppTestRule ();

  @Test
  public void testBasic1 ()
  {
    final HCGoogleAnalyticsV4 aGA = new HCGoogleAnalyticsV4 ("tag");
    aGA.setMode (EHCScriptInlineMode.PLAIN_TEXT_NO_ESCAPE);
    assertEquals ("<script xmlns=\"http://www.w3.org/1999/xhtml\" type=\"text/javascript\">" +
                  "window.dataLayer=(window.dataLayer||[]);" +
                  "function gtag(){dataLayer.push(arguments);}" +
                  "gtag('js',new Date());" +
                  "gtag('config','tag');" +
                  "</script>",
                  HCRenderer.getAsHTMLString (aGA));
  }
}
