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
package com.helger.html.hc.utils;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.system.ENewLineMode;
import com.helger.commons.xml.serialize.EXMLSerializeFormat;
import com.helger.commons.xml.serialize.EXMLSerializeIndent;
import com.helger.commons.xml.serialize.XMLWriterSettings;
import com.helger.html.EHTMLVersion;
import com.helger.html.annotations.OutOfBandNode;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.conversion.HCConversionSettings;
import com.helger.html.hc.html.HCH1;
import com.helger.html.hc.html.HCHtml;
import com.helger.html.hc.html.HCScript;

public class HCSpecialNodeHandlerTest
{
  public static final class MockSpecialNodeListHandler implements IHCSpecialNodeListModifier
  {
    public List <? extends IHCNode> modifySpecialNodes (final List <? extends IHCNode> aNodes)
    {
      final List <IHCNode> ret = CollectionHelper.newList (aNodes);
      if (!ret.isEmpty ())
        ret.remove (0);
      return ret;
    }
  }

  @OutOfBandNode
  @SpecialNodeListModifier (MockSpecialNodeListHandler.class)
  public static final class MockScript extends HCScript
  {
    public MockScript (final String sJSCode)
    {
      super (sJSCode);
    }
  }

  @Test
  public void testSpecialNodeListHandler ()
  {
    final HCHtml aHtml = new HCHtml ();
    aHtml.getHead ().setPageTitle ("Test");
    aHtml.getHead ().addJS (new MockScript ("var x=0;"));
    aHtml.getHead ().addJS (new MockScript ("var y=0;"));
    aHtml.getBody ().addChild (new HCH1 ().addChild ("root"));
    final String sCRLF = ENewLineMode.DEFAULT.getText ();
    assertEquals ("<!DOCTYPE html>" +
                      sCRLF +
                      "<html dir=\"ltr\">" +
                      sCRLF +
                      "<head>" +
                      sCRLF +
                      "<title>Test</title>" +
                      sCRLF +
                      "</head>" +
                      sCRLF +
                      "<body>" +
                      sCRLF +
                      "<h1>root</h1>" +
                      sCRLF +
                      "<script type=\"text/javascript\">" +
                      sCRLF +
                      "<!--" +
                      sCRLF +
                      "var y=0;" +
                      sCRLF +
                      "//-->" +
                      sCRLF +
                      "</script>" +
                      sCRLF +
                      "</body>" +
                      sCRLF +
                      "</html>" +
                      sCRLF,
                  aHtml.getAsHTMLString (new HCConversionSettings (EHTMLVersion.HTML5).setXMLWriterSettings (new XMLWriterSettings ().setEmitNamespaces (false)
                                                                                                                                     .setFormat (EXMLSerializeFormat.HTML)
                                                                                                                                     .setIndent (EXMLSerializeIndent.ALIGN_ONLY))));
  }
}
