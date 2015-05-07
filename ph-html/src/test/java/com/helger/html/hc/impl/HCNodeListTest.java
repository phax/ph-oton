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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;

import com.helger.commons.microdom.IMicroContainer;
import com.helger.html.hc.conversion.HCSettings;
import com.helger.html.hc.html.HCDiv;
import com.helger.html.hc.html.HCRow;
import com.helger.html.hc.html.HCSpan;
import com.helger.html.hc.html.HCTable;
import com.helger.html.mock.HCTestRuleOptimized;

/**
 * Test class for class {@link HCNodeList}
 *
 * @author Philip Helger
 */
public final class HCNodeListTest
{
  @Rule
  public final HCTestRuleOptimized m_aRule = new HCTestRuleOptimized ();

  @Test
  public void testAll ()
  {
    HCNodeList x = new HCNodeList ();
    assertFalse (x.hasChildren ());
    x.addChild (new HCDiv ().addChild ("d1"));
    x.addChild (new HCDiv ().addChild ("d2"));
    assertTrue (x.hasChildren ());
    final IMicroContainer aNode = (IMicroContainer) HCSettings.getAsNode (x);
    assertNotNull (aNode);
    assertEquals (2, x.getChildCount ());

    x = new HCNodeList ();
    x.addChild (new HCSpan ().addChild ("span"));
    final HCDiv div = x.addAndReturnChild (new HCDiv ().addChild ("d3"));
    assertNotNull (div);
    assertEquals ("<div xmlns=\"http://www.w3.org/1999/xhtml\">d3</div>", HCSettings.getAsHTMLString (div));
    assertEquals (2, x.getChildCount ());
  }

  @Test
  public void testWithTable ()
  {
    final HCTable table = new HCTable ();
    final HCRow tr = table.addBodyRow ();
    tr.addCell (new HCNodeList ().addChild (new HCDiv ().addChild ("dd2")).addChild (new HCDiv ().addChild ("dd1")));
    assertEquals ("<table xmlns=\"http://www.w3.org/1999/xhtml\"><tbody><tr><td>"
                  + "<div>dd2</div>"
                  + "<div>dd1</div>"
                  + "</td></tr></tbody></table>", HCSettings.getAsHTMLString (table));
  }

  @Test
  public void testGetAsNode ()
  {
    final HCNodeList x = new HCNodeList ();
    x.addChild (new HCDiv ().addChild ("Na so was"));
    x.addChild (new HCDiv ().addChild ("aber auch"));
    assertNotNull (HCSettings.getAsNode (x));
    assertEquals ("<div xmlns=\"http://www.w3.org/1999/xhtml\">Na so was</div>"
                  + "<div xmlns=\"http://www.w3.org/1999/xhtml\">aber auch</div>", HCSettings.getAsHTMLString (x));
  }
}
