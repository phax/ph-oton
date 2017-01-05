/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.metadata;

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
import com.helger.html.hc.html.HC_Target;
import com.helger.html.hc.html.script.HCScriptFile;
import com.helger.html.hc.html.script.HCScriptInline;
import com.helger.html.hc.mock.HCTestRuleOptimized;
import com.helger.html.hc.render.HCRenderer;
import com.helger.html.js.UnparsedJSCodeProvider;

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
    assertTrue (aHead.getAllMetaElements ().isEmpty ());
    assertEquals (0, aHead.getMetaElementCount ());
    assertTrue (aHead.getAllLinks ().isEmpty ());
    assertEquals (0, aHead.getLinkCount ());
    assertTrue (aHead.getAllCSSNodes ().isEmpty ());
    assertTrue (aHead.getAllJSNodes ().isEmpty ());
    assertEquals ("", aHead.getPlainText ());

    assertSame (aHead, aHead.setProfile ("any"));
    assertEquals ("any", aHead.getProfile ());
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\" profile=\"any\"></head>",
                  HCRenderer.getAsHTMLString (aHead));

    assertSame (aHead, aHead.setPageTitle ("Title"));
    assertEquals ("Title", aHead.getPageTitle ());
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\" profile=\"any\"><title>Title</title></head>",
                  HCRenderer.getAsHTMLString (aHead));

    assertSame (aHead, aHead.setBaseHref (new SimpleURL ("/")));
    assertEquals ("/", aHead.getBaseHref ().getAsStringWithEncodedParameters ());
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\" profile=\"any\"><title>Title</title><base href=\"/\" /></head>",
                  HCRenderer.getAsHTMLString (aHead));

    assertSame (aHead, aHead.setBaseTarget (HC_Target.BLANK));
    assertEquals (HC_Target.BLANK, aHead.getBaseTarget ());
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\" profile=\"any\"><title>Title</title><base href=\"/\" target=\"_blank\" /></head>",
                  HCRenderer.getAsHTMLString (aHead));
    assertNotNull (aHead.toString ());
  }

  @Test
  public void testMetaElements ()
  {
    final HCHead aHead = new HCHead ();
    assertTrue (aHead.getAllMetaElements ().isEmpty ());
    assertEquals (0, aHead.getMetaElementCount ());

    assertSame (aHead, aHead.addMetaElement (new HCMeta ().setName ("foo").setContent ("bar")));
    assertFalse (aHead.getAllMetaElements ().isEmpty ());
    assertEquals (1, aHead.getMetaElementCount ());
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\">" +
                  "<meta name=\"foo\" content=\"bar\" />" +
                  "</head>",
                  HCRenderer.getAsHTMLString (aHead));

    assertSame (aHead, aHead.addMetaElement (new HCMeta ().setHttpEquiv ("goo").setContent ("car")));
    assertEquals (2, aHead.getMetaElementCount ());
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\">" +
                  "<meta name=\"foo\" content=\"bar\" />" +
                  "<meta http-equiv=\"goo\" content=\"car\" />" +
                  "</head>",
                  HCRenderer.getAsHTMLString (aHead));

    assertEquals (EChange.UNCHANGED, aHead.removeMetaElement ("any"));
    assertEquals (2, aHead.getMetaElementCount ());
    assertEquals (EChange.CHANGED, aHead.removeMetaElement ("foo"));
    assertEquals (1, aHead.getMetaElementCount ());
    assertEquals (EChange.UNCHANGED, aHead.removeMetaElement ("foo"));
    assertEquals (1, aHead.getMetaElementCount ());
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\">" +
                  "<meta http-equiv=\"goo\" content=\"car\" />" +
                  "</head>",
                  HCRenderer.getAsHTMLString (aHead));
    assertEquals (EChange.CHANGED, aHead.removeMetaElement ("goo"));
    assertEquals (0, aHead.getMetaElementCount ());
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\"></head>", HCRenderer.getAsHTMLString (aHead));
  }

  @Test
  public void testGenerate ()
  {
    final HCHead aHead = new HCHead ();
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\"></head>", HCRenderer.getAsHTMLString (aHead));

    aHead.setPageTitle ("test");
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\"><title>test</title></head>",
                  HCRenderer.getAsHTMLString (aHead));

    aHead.setBaseHref (new SimpleURL ("/root"));
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\"><title>test</title><base href=\"/root\" /></head>",
                  HCRenderer.getAsHTMLString (aHead));
    aHead.setBaseHref (null);
    aHead.setBaseTarget (HC_Target.BLANK);
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\"><title>test</title><base target=\"_blank\" /></head>",
                  HCRenderer.getAsHTMLString (aHead));
    aHead.setBaseTarget (null);
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\"><title>test</title></head>",
                  HCRenderer.getAsHTMLString (aHead));

    aHead.setShortcutIconHref (new SimpleURL ("/favicon.ico"));
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\"><title>test</title><link rel=\"shortcut icon\" href=\"/favicon.ico\"></link><link rel=\"icon\" type=\"image/icon\" href=\"/favicon.ico\"></link></head>",
                  HCRenderer.getAsHTMLString (aHead));

    aHead.setShortcutIconHref (null);
    aHead.addJS (new HCScriptFile ().setSrc (new SimpleURL ("/my.js")));
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\"><title>test</title><script type=\"text/javascript\" src=\"/my.js\"></script></head>",
                  HCRenderer.getAsHTMLString (aHead));

    aHead.addCSS (HCLink.createCSSLink (new SimpleURL ("/my.css")));
    assertEquals ("<head xmlns=\"http://www.w3.org/1999/xhtml\"><title>test</title><link rel=\"stylesheet\" type=\"text/css\" href=\"/my.css\"></link><script type=\"text/javascript\" src=\"/my.js\"></script></head>",
                  HCRenderer.getAsHTMLString (aHead));
  }

  @Test
  public void testChildrenStuff ()
  {
    final HCHead aHead = new HCHead ();
    assertTrue (aHead.hasChildren ());
    assertEquals (2, aHead.getChildCount ());
    assertTrue (aHead.getChildAtIndex (0) instanceof HCTitle);
    assertTrue (aHead.getChildAtIndex (1) instanceof HCBase);
    assertNull (aHead.getChildAtIndex (2));
    assertTrue (aHead.getFirstChild () instanceof HCTitle);
    assertTrue (aHead.getLastChild () instanceof HCBase);

    aHead.addCSS (new HCStyle ("bla{}"));
    assertEquals (3, aHead.getChildCount ());
    assertTrue (aHead.getChildAtIndex (0) instanceof HCTitle);
    assertTrue (aHead.getChildAtIndex (1) instanceof HCBase);
    assertTrue (aHead.getChildAtIndex (2) instanceof HCStyle);
    assertNull (aHead.getChildAtIndex (3));
    assertTrue (aHead.getFirstChild () instanceof HCTitle);
    assertTrue (aHead.getLastChild () instanceof HCStyle);

    aHead.addCSS (new HCStyle ("foo{}"));
    assertEquals (4, aHead.getChildCount ());
    assertTrue (aHead.getChildAtIndex (0) instanceof HCTitle);
    assertTrue (aHead.getChildAtIndex (1) instanceof HCBase);
    assertTrue (aHead.getChildAtIndex (2) instanceof HCStyle);
    assertTrue (aHead.getChildAtIndex (3) instanceof HCStyle);
    assertNull (aHead.getChildAtIndex (4));
    assertTrue (aHead.getFirstChild () instanceof HCTitle);
    assertTrue (aHead.getLastChild () instanceof HCStyle);

    aHead.addLink (new HCLink ().setRev (EHCLinkType.APPENDIX));
    assertEquals (5, aHead.getChildCount ());
    assertTrue (aHead.getChildAtIndex (0) instanceof HCTitle);
    assertTrue (aHead.getChildAtIndex (1) instanceof HCBase);
    assertTrue (aHead.getChildAtIndex (2) instanceof HCLink);
    assertTrue (aHead.getChildAtIndex (3) instanceof HCStyle);
    assertTrue (aHead.getChildAtIndex (4) instanceof HCStyle);
    assertNull (aHead.getChildAtIndex (5));
    assertTrue (aHead.getFirstChild () instanceof HCTitle);
    assertTrue (aHead.getLastChild () instanceof HCStyle);

    aHead.addJS (new HCScriptInline (new UnparsedJSCodeProvider ("window.x=1;")));
    assertEquals (6, aHead.getChildCount ());
    assertTrue (aHead.getChildAtIndex (0) instanceof HCTitle);
    assertTrue (aHead.getChildAtIndex (1) instanceof HCBase);
    assertTrue (aHead.getChildAtIndex (2) instanceof HCLink);
    assertTrue (aHead.getChildAtIndex (3) instanceof HCStyle);
    assertTrue (aHead.getChildAtIndex (4) instanceof HCStyle);
    assertTrue (aHead.getChildAtIndex (5) instanceof HCScriptInline);
    assertNull (aHead.getChildAtIndex (6));
    assertTrue (aHead.getFirstChild () instanceof HCTitle);
    assertTrue (aHead.getLastChild () instanceof HCScriptInline);
  }
}
