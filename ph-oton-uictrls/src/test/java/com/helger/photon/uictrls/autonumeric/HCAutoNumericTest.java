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
package com.helger.photon.uictrls.autonumeric;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;

import com.helger.commons.system.ENewLineMode;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.config.mock.HCTestRuleHTMLVersion;
import com.helger.html.hc.config.mock.HCTestRuleOptimized;
import com.helger.html.hc.render.HCRenderer;
import com.helger.html.hchtml.grouping.HCDiv;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.core.mock.PhotonCoreTestRule;

public final class HCAutoNumericTest
{
  private static final String CRLF = ENewLineMode.DEFAULT.getText ();

  @Rule
  public final TestRule m_aRule = RuleChain.outerRule (new PhotonCoreTestRule ())
                                           .around (new HCTestRuleOptimized ())
                                           .around (new HCTestRuleHTMLVersion (EHTMLVersion.HTML5));

  @Test
  public void testGetJS ()
  {
    final HCAutoNumeric a = new HCAutoNumeric (new RequestField ("dummy"), Locale.GERMANY);
    final String sID = a.getID ();
    assertEquals ("<input id=\"" +
                  sID +
                  "\" class=\"auto-numeric-edit\" name=\"dummy\" type=\"text\" value=\"\" />" +
                  CRLF +
                  "<script type=\"text/javascript\">$(document).ready(function(){$('#id10001').autoNumeric('init',{" +
                  CRLF +
                  "  aSep:'.'," +
                  CRLF +
                  "  aDec:','," +
                  CRLF +
                  "  vMin:'-999999999'" +
                  CRLF +
                  "});});</script>" +
                  CRLF,
                  HCRenderer.getAsHTMLString (a));

    a.setThousandSeparator ("");
    // Script is not emitted afterwards, because it was already emitted
    assertEquals ("<input id=\"" +
                  sID +
                  "\" class=\"auto-numeric-edit\" name=\"dummy\" type=\"text\" value=\"\" />" +
                  CRLF,
                  HCRenderer.getAsHTMLString (a));
  }

  @Test
  public void testAddAndRender ()
  {
    final HCAutoNumeric a = new HCAutoNumeric (new RequestField ("dummy"), Locale.GERMANY);
    final HCDiv aDiv = new HCDiv ();
    assertEquals (0, aDiv.getChildCount ());
    // Add the auto numeric which also adds the JS
    aDiv.addChild (a);
    assertEquals (1, aDiv.getChildCount ());
    // Add the script node
    HCRenderer.getAsNode (aDiv);
    assertEquals (2, aDiv.getChildCount ());
  }
}
