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
import org.junit.rules.TestRule;

import com.helger.commons.mock.DebugModeTestRule;
import com.helger.commons.system.ENewLineMode;
import com.helger.commons.xml.serialize.EXMLSerializeIndent;
import com.helger.commons.xml.serialize.IXMLWriterSettings;
import com.helger.html.hc.conversion.HCConversionSettings;
import com.helger.html.hc.conversion.HCSettings;
import com.helger.html.hc.html.HCB;

/**
 * Test class for class {@link HCConditionalCommentNode}
 *
 * @author Philip Helger
 */
public final class HCConditionalCommentNodeTest
{
  // By default, in non-debug mode, no newline is emitted after comment start
  @Rule
  public final TestRule m_aRule = new DebugModeTestRule (false);

  @Test
  public void testAll ()
  {
    HCConditionalCommentNode.setDefaultNewLineMode (ENewLineMode.UNIX);
    HCSettings.getConversionSettingsProvider ()
              .setXMLWriterSettings (HCConversionSettings.createDefaultXMLWriterSettings ()
                                                         .setIndent (EXMLSerializeIndent.NONE));

    assertEquals ("<!--[if IE]>" + "abc" + "<![endif]-->",
                  HCSettings.getAsHTMLString (HCConditionalCommentNode.createForIE (new HCTextNode ("abc"))));
    assertEquals ("<!--[if IE]>" + "<b xmlns=\"http://www.w3.org/1999/xhtml\">bold</b>" + "<![endif]-->",
                  HCSettings.getAsHTMLString (HCConditionalCommentNode.createForIE (new HCB ().addChild ("bold"))));
    assertEquals ("<!--[if IE 7]>" + "<b>bold</b>" + "<![endif]-->",
                  HCSettings.getAsHTMLStringWithoutNamespaces (HCConditionalCommentNode.createForIEExactVersion7 (new HCB ().addChild ("bold"))));

    // But if enabling alignment mode, the newline is printer!
    final IXMLWriterSettings aXWS = HCConversionSettings.createDefaultXMLWriterSettings ()
                                                        .setIndent (EXMLSerializeIndent.ALIGN_ONLY);
    final String sCRLF = aXWS.getNewLineString ();
    HCSettings.getConversionSettingsProvider ().setXMLWriterSettings (aXWS);

    assertEquals ("<!--[if IE]>\n" + "abc" + "<![endif]-->" + sCRLF,
                  HCSettings.getAsHTMLString (HCConditionalCommentNode.createForIE (new HCTextNode ("abc"))));
    assertEquals ("<!--[if IE]>\n" +
                      "<b xmlns=\"http://www.w3.org/1999/xhtml\">bold</b>" +
                      sCRLF +
                      "<![endif]-->" +
                      sCRLF,
                  HCSettings.getAsHTMLString (HCConditionalCommentNode.createForIE (new HCB ().addChild ("bold"))));
    assertEquals ("<!--[if IE]>\n" + "<b>bold</b>" + sCRLF + "<![endif]-->" + sCRLF,
                  HCSettings.getAsHTMLStringWithoutNamespaces (HCConditionalCommentNode.createForIE (new HCB ().addChild ("bold"))));

    // Restore default settings
    HCSettings.getConversionSettingsProvider ()
              .setXMLWriterSettings (HCConversionSettings.createDefaultXMLWriterSettings ());
    HCConditionalCommentNode.setDefaultNewLineMode (ENewLineMode.DEFAULT);
  }
}
