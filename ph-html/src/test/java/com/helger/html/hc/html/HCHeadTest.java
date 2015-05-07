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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;

import com.helger.commons.state.EChange;
import com.helger.commons.url.SimpleURL;
import com.helger.html.hc.conversion.HCSettings;
import com.helger.html.meta.MetaElement;
import com.helger.html.mock.HCTestRuleOptimized;

/**
 * Test class for class {@link HCHead}
 *
 * @author Philip Helger
 */
public final class HCHeadTest
{
  @Rule
  public final HCTestRuleOptimized m_aRule = new HCTestRuleOptimized ();

  @Test
  public void testBasic ()
  {
    final HCHead aHead = new HCHead ();
    assertNull (aHead.getProfile ());
    assertNull (aHead.getPageTitle ());
    assertNull (aHead.getBaseHref ());
    assertNull (aHead.getBaseTarget ());
    assertTrue (aHead.getMetaElementList ().getAllMetaElements ().isEmpty ());
    assertEquals (0, aHead.getMetaElementList ().getMetaElementCount ());
    assertTrue (aHead.getAllLinks ().isEmpty ());
    assertEquals (0, aHead.getLinkCount ());
    assertTrue (aHead.getAllCSSNodes ().isEmpty ());
    assertTrue (aHead.getAllJSNodes ().isEmpty ());
    assertEquals ("", aHead.getPlainText ());

    assertSame (aHead, aHead.setProfile ("any"));
    assertEquals ("any", aHead.getProfile ());
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\" profile=\"any\"></head>",
                  HCSettings.getAsHTMLString (aHead));

    assertSame (aHead, aHead.setPageTitle ("Title"));
    assertEquals ("Title", aHead.getPageTitle ());
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\" profile=\"any\"><title>Title</title></head>",
                  HCSettings.getAsHTMLString (aHead));

    assertSame (aHead, aHead.setBaseHref ("/"));
    assertEquals ("/", aHead.getBaseHref ());
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\" profile=\"any\"><title>Title</title><base href=\"/\" /></head>",
                  HCSettings.getAsHTMLString (aHead));

    assertSame (aHead, aHead.setBaseTarget (HC_Target.BLANK));
    assertEquals (HC_Target.BLANK, aHead.getBaseTarget ());
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\" profile=\"any\"><title>Title</title><base href=\"/\" target=\"_blank\" /></head>",
                  HCSettings.getAsHTMLString (aHead));
    assertNotNull (aHead.toString ());
  }

  @Test
  public void testMetaElements ()
  {
    final HCHead aHead = new HCHead ();
    assertTrue (aHead.getMetaElementList ().getAllMetaElements ().isEmpty ());
    assertEquals (0, aHead.getMetaElementList ().getMetaElementCount ());

    assertSame (aHead.getMetaElementList (), aHead.getMetaElementList ()
                                                  .addMetaElement (new MetaElement ("foo", "bar")));
    assertFalse (aHead.getMetaElementList ().getAllMetaElements ().isEmpty ());
    assertEquals (1, aHead.getMetaElementList ().getMetaElementCount ());
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\">" + "<meta name=\"foo\" content=\"bar\" />" + "</head>",
                  HCSettings.getAsHTMLString (aHead));

    assertSame (aHead.getMetaElementList (),
                aHead.getMetaElementList ().addMetaElement (new MetaElement ("goo", true, "car")));
    assertEquals (2, aHead.getMetaElementList ().getMetaElementCount ());
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\">"
                  + "<meta name=\"foo\" content=\"bar\" />"
                  + "<meta http-equiv=\"goo\" content=\"car\" />"
                  + "</head>", HCSettings.getAsHTMLString (aHead));

    assertEquals (EChange.UNCHANGED, aHead.getMetaElementList ().removeMetaElement ("any"));
    assertEquals (2, aHead.getMetaElementList ().getMetaElementCount ());
    assertEquals (EChange.CHANGED, aHead.getMetaElementList ().removeMetaElement ("foo"));
    assertEquals (1, aHead.getMetaElementList ().getMetaElementCount ());
    assertEquals (EChange.UNCHANGED, aHead.getMetaElementList ().removeMetaElement ("foo"));
    assertEquals (1, aHead.getMetaElementList ().getMetaElementCount ());
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\">"
                  + "<meta http-equiv=\"goo\" content=\"car\" />"
                  + "</head>", HCSettings.getAsHTMLString (aHead));
    assertEquals (EChange.CHANGED, aHead.getMetaElementList ().removeMetaElement ("goo"));
    assertEquals (0, aHead.getMetaElementList ().getMetaElementCount ());
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\"></head>", HCSettings.getAsHTMLString (aHead));
  }

  @Test
  public void testGenerate ()
  {
    final HCHead aHead = new HCHead ();
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\"></head>", HCSettings.getAsHTMLString (aHead));

    aHead.setPageTitle ("test");
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\"><title>test</title></head>",
                  HCSettings.getAsHTMLString (aHead));

    aHead.setBaseHref ("/root");
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\"><title>test</title><base href=\"/root\" /></head>",
                  HCSettings.getAsHTMLString (aHead));
    aHead.setBaseHref (null);
    aHead.setBaseTarget (HC_Target.BLANK);
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\"><title>test</title><base target=\"_blank\" /></head>",
                  HCSettings.getAsHTMLString (aHead));
    aHead.setBaseTarget (null);
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\"><title>test</title></head>",
                  HCSettings.getAsHTMLString (aHead));

    aHead.setShortcutIconHref (new SimpleURL ("/favicon.ico"));
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\"><title>test</title><link rel=\"shortcut icon\" href=\"/favicon.ico\"></link><link rel=\"icon\" type=\"image/icon\" href=\"/favicon.ico\"></link></head>",
                  HCSettings.getAsHTMLString (aHead));

    aHead.setShortcutIconHref (null);
    aHead.addJS (HCScriptFile.create (new SimpleURL ("/my.js")));
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\"><title>test</title><script type=\"text/javascript\" src=\"/my.js\"></script></head>",
                  HCSettings.getAsHTMLString (aHead));

    aHead.addCSS (HCLink.createCSSLink (new SimpleURL ("/my.css")));
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\"><title>test</title><link rel=\"stylesheet\" type=\"text/css\" href=\"/my.css\"></link><script type=\"text/javascript\" src=\"/my.js\"></script></head>",
                  HCSettings.getAsHTMLString (aHead));
  }
}
