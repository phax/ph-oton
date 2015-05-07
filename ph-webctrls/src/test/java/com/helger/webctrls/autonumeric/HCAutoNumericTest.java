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
package com.helger.webctrls.autonumeric;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Rule;
import org.junit.Test;

import com.helger.commons.system.ENewLineMode;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.conversion.HCConversionSettings;
import com.helger.html.hc.html.HCDiv;
import com.helger.webbasics.form.RequestField;
import com.helger.webbasics.mock.WebBasicTestRule;

public final class HCAutoNumericTest
{
  private static final String CRLF = ENewLineMode.DEFAULT.getText ();

  @Rule
  public final WebBasicTestRule m_aRule = new WebBasicTestRule ();

  @Test
  public void testGetJS ()
  {
    final HCAutoNumeric a = new HCAutoNumeric (new RequestField ("dummy"), Locale.GERMANY);
    final String sID = a.getID ();
    assertEquals ("<input id=\"" +
                  sID +
                  "\" class=\"auto-numeric-edit\" name=\"dummy\" type=\"text\" value=\"\" />" +
                  CRLF, a.getAsHTMLString (new HCConversionSettings (EHTMLVersion.HTML5)));

    a.setThousandSeparator ("");
    assertEquals ("<input id=\"" +
                  sID +
                  "\" class=\"auto-numeric-edit\" name=\"dummy\" type=\"text\" value=\"\" />" +
                  CRLF, a.getAsHTMLString (new HCConversionSettings (EHTMLVersion.HTML5)));
  }

  @Test
  public void testAddAndRemove ()
  {
    final HCAutoNumeric a = new HCAutoNumeric (new RequestField ("dummy"), Locale.GERMANY);
    final HCDiv aDiv = new HCDiv ();
    assertEquals (0, aDiv.getChildCount ());
    // Add the auto numeric which also adds the JS
    aDiv.addChild (a);
    assertEquals (2, aDiv.getChildCount ());
    // Remove the auto numeric should also remove the JS
    aDiv.removeChild (a);
    assertEquals (0, aDiv.getChildCount ());
  }
}
