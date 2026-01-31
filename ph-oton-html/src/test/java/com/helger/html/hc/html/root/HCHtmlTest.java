/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.root;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;

import com.helger.css.media.ECSSMedium;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.html.metadata.HCMeta;
import com.helger.html.hc.html.metadata.HCStyle;
import com.helger.html.hc.html.script.HCScriptInlineOnDocumentReady;
import com.helger.html.hc.html.sections.HCH1;
import com.helger.html.hc.mock.HCTestRuleOptimized;
import com.helger.html.hc.render.HCRenderer;
import com.helger.html.js.UnparsedJSCodeProvider;

/**
 * Test class for class {@link HCHtml}
 *
 * @author Philip Helger
 */
public final class HCHtmlTest
{
  @Rule
  public final HCTestRuleOptimized m_aRule = new HCTestRuleOptimized ();

  @Test
  public void testOutOfBandNodes1 ()
  {
    final HCHtml aHtml = new HCHtml ();
    aHtml.body ().addChild (new HCH1 ().addChild ("Test"));
    aHtml.body ().addChild (new HCStyle ("h1{color:red;}"));

    // Must be done for HCHtml separately
    HCRenderer.prepareHtmlForConversion (aHtml, HCSettings.getConversionSettings ());

    assertEquals ("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">" +
                  "<html xmlns=\"http://www.w3.org/1999/xhtml\" dir=\"ltr\">" +
                  "<head><style type=\"text/css\">h1{color:red;}</style></head>" +
                  "<body><h1>Test</h1></body>" +
                  "</html>",
                  HCRenderer.getAsHTMLString (aHtml));
    // Do it again and check for node consistency
    assertEquals ("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">" +
                  "<html xmlns=\"http://www.w3.org/1999/xhtml\" dir=\"ltr\">" +
                  "<head><style type=\"text/css\">h1{color:red;}</style></head>" +
                  "<body><h1>Test</h1></body>" +
                  "</html>",
                  HCRenderer.getAsHTMLString (aHtml));
  }

  @Test
  public void testOutOfBandNodes1WithStyleMedium ()
  {
    final HCHtml aHtml = new HCHtml ();
    aHtml.body ().addChild (new HCH1 ().addChild ("Test"));
    aHtml.body ().addChild (new HCStyle ("h1{color:red;}").addMedium (ECSSMedium.PRINT));

    // Must be done for HCHtml separately
    HCRenderer.prepareHtmlForConversion (aHtml, HCSettings.getConversionSettings ());

    assertEquals ("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">" +
                  "<html xmlns=\"http://www.w3.org/1999/xhtml\" dir=\"ltr\">" +
                  "<head><style type=\"text/css\" media=\"print\">h1{color:red;}</style></head>" +
                  "<body><h1>Test</h1></body>" +
                  "</html>",
                  HCRenderer.getAsHTMLString (aHtml));
    // Do it again and check for node consistency
    assertEquals ("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">" +
                  "<html xmlns=\"http://www.w3.org/1999/xhtml\" dir=\"ltr\">" +
                  "<head><style type=\"text/css\" media=\"print\">h1{color:red;}</style></head>" +
                  "<body><h1>Test</h1></body>" +
                  "</html>",
                  HCRenderer.getAsHTMLString (aHtml));
  }

  @Test
  public void testOutOfBandNodes2 ()
  {
    final HCHtml aHtml = new HCHtml ();
    aHtml.head ().metaElements ().add (new HCMeta ().setName ("foo").setContent ("bar"));
    aHtml.body ().addChild (new HCH1 ().addChild ("Test"));
    aHtml.body ().addChild (new HCScriptInlineOnDocumentReady (new UnparsedJSCodeProvider ("a=b;")));
    aHtml.body ().addChild (new HCScriptInlineOnDocumentReady (new UnparsedJSCodeProvider ("c=d;")));

    // Must be done for HCHtml separately
    HCRenderer.prepareHtmlForConversion (aHtml, HCSettings.getConversionSettings ());

    assertEquals ("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">" +
                  "<html xmlns=\"http://www.w3.org/1999/xhtml\" dir=\"ltr\">" +
                  "<head><meta name=\"foo\" content=\"bar\" /></head>" +
                  "<body><h1>Test</h1>" +
                  "<script type=\"text/javascript\">" +
                  "$(document).ready(function(){a=b;c=d;});" +
                  "</script>" +
                  "</body>" +
                  "</html>",
                  HCRenderer.getAsHTMLString (aHtml));
    // Do it again and check for node consistency
    assertEquals ("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">" +
                  "<html xmlns=\"http://www.w3.org/1999/xhtml\" dir=\"ltr\">" +
                  "<head><meta name=\"foo\" content=\"bar\" /></head>" +
                  "<body><h1>Test</h1>" +
                  "<script type=\"text/javascript\">" +
                  "$(document).ready(function(){a=b;c=d;});" +
                  "</script>" +
                  "</body>" +
                  "</html>",
                  HCRenderer.getAsHTMLString (aHtml));
  }
}
