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
package com.helger.html.hc.html;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;

import com.helger.html.hc.conversion.HCSettings;
import com.helger.html.mock.HCTestRuleOptimized;

/**
 * Test class for class {@link HCStyle}.
 *
 * @author Philip Helger
 */
public final class HCStyleTest
{
  @Rule
  public final HCTestRuleOptimized m_aRule = new HCTestRuleOptimized ();

  @Test
  public void testSimple ()
  {
    final HCStyle aStyle = new HCStyle ("div{color:red;}");
    assertEquals ("<style xmlns=\"http://www.w3.org/1999/xhtml\" type=\"text/css\">div{color:red;}</style>",
                  HCSettings.getAsHTMLString (aStyle));
  }

  @Test
  public void testQuoted ()
  {
    HCStyle aStyle = new HCStyle ("div{background:url('foo.gif');}");
    assertEquals ("<style xmlns=\"http://www.w3.org/1999/xhtml\" type=\"text/css\">div{background:url('foo.gif');}</style>",
                  HCSettings.getAsHTMLString (aStyle));
    HCStyle.setDefaultMode (HCStyle.EMode.PLAIN_TEXT);
    aStyle = new HCStyle ("div{background:url('foo.gif');}");
    assertEquals ("<style xmlns=\"http://www.w3.org/1999/xhtml\" type=\"text/css\">div{background:url(&#39;foo.gif&#39;);}</style>",
                  HCSettings.getAsHTMLString (aStyle));
    HCStyle.setDefaultMode (HCStyle.EMode.PLAIN_TEXT_NO_ESCAPE);
    aStyle = new HCStyle ("div{background:url('foo.gif');}");
    assertEquals ("<style xmlns=\"http://www.w3.org/1999/xhtml\" type=\"text/css\">div{background:url('foo.gif');}</style>",
                  HCSettings.getAsHTMLString (aStyle));
  }
}
