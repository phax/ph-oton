/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.html.hc.ext;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.commons.junit.DebugModeTestRule;
import com.helger.commons.system.ENewLineMode;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.html.textlevel.HCB;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.html.hc.render.HCRenderer;
import com.helger.xml.serialize.write.EXMLSerializeIndent;
import com.helger.xml.serialize.write.XMLWriterSettings;

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
    final ENewLineMode eOldMode = HCSettings.getNewLineMode ();
    HCSettings.setNewLineMode (ENewLineMode.UNIX);

    final EHTMLVersion eOldVersion = HCSettings.getConversionSettings ().getHTMLVersion ();
    HCSettings.setDefaultHTMLVersion (EHTMLVersion.XHTML11);

    final XMLWriterSettings aOldXWS = HCSettings.getMutableConversionSettings ().getXMLWriterSettings ();
    final XMLWriterSettings aXWS2 = aOldXWS.getClone ();
    aXWS2.setIndent (EXMLSerializeIndent.NONE);
    HCSettings.getMutableConversionSettings ().setXMLWriterSettings (aXWS2);

    try
    {
      assertEquals ("<!--[if IE]>" + "abc" + "<![endif]-->",
                    HCRenderer.getAsHTMLString (HCConditionalCommentNode.createForIE (new HCTextNode ("abc"))));
      assertEquals ("<!--[if IE]>" + "<b xmlns=\"http://www.w3.org/1999/xhtml\">bold</b>" + "<![endif]-->",
                    HCRenderer.getAsHTMLString (HCConditionalCommentNode.createForIE (new HCB ().addChild ("bold"))));
      assertEquals ("<!--[if IE 7]>" + "<b>bold</b>" + "<![endif]-->",
                    HCRenderer.getAsHTMLStringWithoutNamespaces (HCConditionalCommentNode.createForIEExactVersion7 (new HCB ().addChild ("bold"))));

      // But if enabling alignment mode, the newline is printer!
      aXWS2.setIndent (EXMLSerializeIndent.ALIGN_ONLY);
      HCSettings.getMutableConversionSettings ().setXMLWriterSettings (aXWS2);
      final String sCRLF = aXWS2.getNewLineString ();

      assertEquals ("<!--[if IE]>" + sCRLF + "abc" + "<![endif]-->" + sCRLF,
                    HCRenderer.getAsHTMLString (HCConditionalCommentNode.createForIE (new HCTextNode ("abc"))));
      assertEquals ("<!--[if IE]>" + sCRLF + "<b xmlns=\"http://www.w3.org/1999/xhtml\">bold</b>" + sCRLF + "<![endif]-->" + sCRLF,
                    HCRenderer.getAsHTMLString (HCConditionalCommentNode.createForIE (new HCB ().addChild ("bold"))));
      assertEquals ("<!--[if IE]>" + sCRLF + "<b>bold</b>" + sCRLF + "<![endif]-->" + sCRLF,
                    HCRenderer.getAsHTMLStringWithoutNamespaces (HCConditionalCommentNode.createForIE (new HCB ().addChild ("bold"))));
    }
    finally
    {
      // Restore default settings
      HCSettings.setNewLineMode (eOldMode);
      HCSettings.setDefaultHTMLVersion (eOldVersion);
      HCSettings.getMutableConversionSettings ().setXMLWriterSettings (aOldXWS);
    }
  }
}
