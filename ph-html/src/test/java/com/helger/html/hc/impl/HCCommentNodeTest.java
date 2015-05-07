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
package com.helger.html.hc.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;

import com.helger.html.hc.conversion.HCSettings;
import com.helger.html.mock.HCTestRuleOptimized;

/**
 * Test class for class {@link HCCommentNode}
 *
 * @author Philip Helger
 */
public final class HCCommentNodeTest
{
  @Rule
  public final HCTestRuleOptimized m_aRule = new HCTestRuleOptimized ();

  @Test
  public void testAll ()
  {
    assertEquals ("", HCSettings.getAsHTMLString (new HCCommentNode ("")));
    assertEquals ("<!--x-->", HCSettings.getAsHTMLString (new HCCommentNode ("x")));
    assertEquals ("<!-- -->", HCSettings.getAsHTMLString (new HCCommentNode (" ")));
    assertEquals ("<!-- \t -->", HCSettings.getAsHTMLString (new HCCommentNode (" \t ")));
    assertEquals ("<!--abc\ndef-->", HCSettings.getAsHTMLString (new HCCommentNode ("abc\ndef")));
    // Emits a warning
    assertEquals ("<!------>", HCSettings.getAsHTMLString (new HCCommentNode ("--")));
  }
}
