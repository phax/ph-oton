/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.html.hc.special;

import static org.junit.Assert.assertEquals;

import javax.annotation.Nonnull;

import org.junit.Test;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.system.ENewLineMode;
import com.helger.css.media.CSSMediaList;
import com.helger.css.media.ECSSMedium;
import com.helger.html.EHTMLVersion;
import com.helger.html.annotation.OutOfBandNode;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.config.HCConversionSettings;
import com.helger.html.hc.html.metadata.HCStyle;
import com.helger.html.hc.html.root.HCHtml;
import com.helger.html.hc.html.script.AbstractHCScriptInline;
import com.helger.html.hc.html.sections.HCH1;
import com.helger.html.hc.render.HCRenderer;
import com.helger.html.js.UnparsedJSCodeProvider;
import com.helger.xml.serialize.write.EXMLSerializeIndent;

public final class HCSpecialNodeHandlerTest
{
  public static final class MockSpecialNodeListHandler implements IHCSpecialNodeListModifier
  {
    @Nonnull
    @ReturnsMutableCopy
    public ICommonsList <? extends IHCNode> modifySpecialNodes (final ICommonsList <? extends IHCNode> aNodes)
    {
      final ICommonsList <IHCNode> ret = new CommonsArrayList <> (aNodes);
      // Remove the first
      if (ret.isNotEmpty ())
        ret.remove (0);
      return ret;
    }
  }

  @OutOfBandNode
  @SpecialNodeListModifier (MockSpecialNodeListHandler.class)
  public static final class MockScript extends AbstractHCScriptInline <MockScript>
  {
    public MockScript (final String sJSCode)
    {
      super (new UnparsedJSCodeProvider (sJSCode));
    }
  }

  @Test
  public void testSpecialNodeListHandler ()
  {
    final HCHtml aHtml = new HCHtml ();
    aHtml.head ().setPageTitle ("Test");
    aHtml.head ().addJS (new MockScript ("var x=0;"));
    aHtml.head ().addJS (new MockScript ("var y=0;"));
    aHtml.body ().addChild (new HCH1 ().addChild ("root"));
    final String sCRLF = ENewLineMode.DEFAULT.getText ();
    final HCConversionSettings aCS = new HCConversionSettings (EHTMLVersion.HTML5);
    aCS.getXMLWriterSettings ().setEmitNamespaces (false).setIndent (EXMLSerializeIndent.ALIGN_ONLY);

    // Must be done for HCHtml separately
    HCRenderer.prepareHtmlForConversion (aHtml, aCS);

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
                  "var y=0;" +
                  "</script>" +
                  sCRLF +
                  "</body>" +
                  sCRLF +
                  "</html>" +
                  sCRLF,
                  HCRenderer.getAsHTMLString (aHtml, aCS));
  }

  @Test
  public void testCSSMerge1 ()
  {
    final HCHtml aHtml = new HCHtml ();
    aHtml.head ().setPageTitle ("Test");
    aHtml.head ().addCSS (new HCStyle ("body{color:red}"));
    aHtml.head ().addCSS (new HCStyle ("div{color:blue}"));
    aHtml.head ().addCSS (new HCStyle ("span{color:green}"));
    aHtml.body ().addChild (new HCH1 ().addChild ("root"));
    final String sCRLF = ENewLineMode.DEFAULT.getText ();
    final HCConversionSettings aCS = new HCConversionSettings (EHTMLVersion.HTML5);
    aCS.getXMLWriterSettings ().setEmitNamespaces (false).setIndent (EXMLSerializeIndent.ALIGN_ONLY);

    // Must be done for HCHtml separately
    HCRenderer.prepareHtmlForConversion (aHtml, aCS);

    assertEquals ("<!DOCTYPE html>" +
                  sCRLF +
                  "<html dir=\"ltr\">" +
                  sCRLF +
                  "<head>" +
                  sCRLF +
                  "<title>Test</title>" +
                  sCRLF +
                  "<style type=\"text/css\">body{color:red}div{color:blue}span{color:green}</style>" +
                  sCRLF +
                  "</head>" +
                  sCRLF +
                  "<body>" +
                  sCRLF +
                  "<h1>root</h1>" +
                  sCRLF +
                  "</body>" +
                  sCRLF +
                  "</html>" +
                  sCRLF,
                  HCRenderer.getAsHTMLString (aHtml, aCS));
  }

  @Test
  public void testCSSMerge1a ()
  {
    final HCHtml aHtml = new HCHtml ();
    aHtml.head ().setPageTitle ("Test");
    aHtml.head ().addCSS (new HCStyle ("body{color:red}"));
    // Should be the same as no media list specified!
    aHtml.head ().addCSS (new HCStyle ("div{color:blue}").setMedia (new CSSMediaList ()));
    aHtml.head ().addCSS (new HCStyle ("span{color:green}"));
    aHtml.body ().addChild (new HCH1 ().addChild ("root"));
    final String sCRLF = ENewLineMode.DEFAULT.getText ();
    final HCConversionSettings aCS = new HCConversionSettings (EHTMLVersion.HTML5);
    aCS.getXMLWriterSettings ().setEmitNamespaces (false).setIndent (EXMLSerializeIndent.ALIGN_ONLY);

    // Must be done for HCHtml separately
    HCRenderer.prepareHtmlForConversion (aHtml, aCS);

    assertEquals ("<!DOCTYPE html>" +
                  sCRLF +
                  "<html dir=\"ltr\">" +
                  sCRLF +
                  "<head>" +
                  sCRLF +
                  "<title>Test</title>" +
                  sCRLF +
                  "<style type=\"text/css\">body{color:red}div{color:blue}span{color:green}</style>" +
                  sCRLF +
                  "</head>" +
                  sCRLF +
                  "<body>" +
                  sCRLF +
                  "<h1>root</h1>" +
                  sCRLF +
                  "</body>" +
                  sCRLF +
                  "</html>" +
                  sCRLF,
                  HCRenderer.getAsHTMLString (aHtml, aCS));
  }

  @Test
  public void testCSSMerge1b ()
  {
    final HCHtml aHtml = new HCHtml ();
    aHtml.head ().setPageTitle ("Test");
    aHtml.head ().addCSS (new HCStyle ("body{color:red}"));
    // Should be the same as no media list specified!
    aHtml.head ().addCSS (new HCStyle ("div{color:blue}").setMedia (new CSSMediaList (ECSSMedium.ALL)));
    aHtml.head ().addCSS (new HCStyle ("span{color:green}"));
    aHtml.body ().addChild (new HCH1 ().addChild ("root"));
    final String sCRLF = ENewLineMode.DEFAULT.getText ();
    final HCConversionSettings aCS = new HCConversionSettings (EHTMLVersion.HTML5);
    aCS.getXMLWriterSettings ().setEmitNamespaces (false).setIndent (EXMLSerializeIndent.ALIGN_ONLY);

    // Must be done for HCHtml separately
    HCRenderer.prepareHtmlForConversion (aHtml, aCS);

    assertEquals ("<!DOCTYPE html>" +
                  sCRLF +
                  "<html dir=\"ltr\">" +
                  sCRLF +
                  "<head>" +
                  sCRLF +
                  "<title>Test</title>" +
                  sCRLF +
                  "<style type=\"text/css\">body{color:red}div{color:blue}span{color:green}</style>" +
                  sCRLF +
                  "</head>" +
                  sCRLF +
                  "<body>" +
                  sCRLF +
                  "<h1>root</h1>" +
                  sCRLF +
                  "</body>" +
                  sCRLF +
                  "</html>" +
                  sCRLF,
                  HCRenderer.getAsHTMLString (aHtml, aCS));
  }

  @Test
  public void testCSSMerge2 ()
  {
    final HCHtml aHtml = new HCHtml ();
    aHtml.head ().setPageTitle ("Test");
    aHtml.head ().addCSS (new HCStyle ("body{color:red}").setMedia (new CSSMediaList (ECSSMedium.PRINT)));
    aHtml.head ().addCSS (new HCStyle ("div{color:blue}"));
    aHtml.head ().addCSS (new HCStyle ("span{color:green}"));
    aHtml.body ().addChild (new HCH1 ().addChild ("root"));
    final String sCRLF = ENewLineMode.DEFAULT.getText ();
    final HCConversionSettings aCS = new HCConversionSettings (EHTMLVersion.HTML5);
    aCS.getXMLWriterSettings ().setEmitNamespaces (false).setIndent (EXMLSerializeIndent.ALIGN_ONLY);

    // Must be done for HCHtml separately
    HCRenderer.prepareHtmlForConversion (aHtml, aCS);

    assertEquals ("<!DOCTYPE html>" +
                  sCRLF +
                  "<html dir=\"ltr\">" +
                  sCRLF +
                  "<head>" +
                  sCRLF +
                  "<title>Test</title>" +
                  sCRLF +
                  "<style type=\"text/css\" media=\"print\">body{color:red}</style>" +
                  sCRLF +
                  "<style type=\"text/css\">div{color:blue}span{color:green}</style>" +
                  sCRLF +
                  "</head>" +
                  sCRLF +
                  "<body>" +
                  sCRLF +
                  "<h1>root</h1>" +
                  sCRLF +
                  "</body>" +
                  sCRLF +
                  "</html>" +
                  sCRLF,
                  HCRenderer.getAsHTMLString (aHtml, aCS));
  }

  @Test
  public void testCSSMerge3 ()
  {
    final HCHtml aHtml = new HCHtml ();
    aHtml.head ().setPageTitle ("Test");
    aHtml.head ().addCSS (new HCStyle ("body{color:red}").setMedia (new CSSMediaList (ECSSMedium.PRINT)));
    aHtml.head ().addCSS (new HCStyle ("div{color:blue}").setMedia (new CSSMediaList (ECSSMedium.PRINT)));
    aHtml.head ().addCSS (new HCStyle ("span{color:green}"));
    aHtml.body ().addChild (new HCH1 ().addChild ("root"));
    final String sCRLF = ENewLineMode.DEFAULT.getText ();
    final HCConversionSettings aCS = new HCConversionSettings (EHTMLVersion.HTML5);
    aCS.getXMLWriterSettings ().setEmitNamespaces (false).setIndent (EXMLSerializeIndent.ALIGN_ONLY);

    // Must be done for HCHtml separately
    HCRenderer.prepareHtmlForConversion (aHtml, aCS);

    assertEquals ("<!DOCTYPE html>" +
                  sCRLF +
                  "<html dir=\"ltr\">" +
                  sCRLF +
                  "<head>" +
                  sCRLF +
                  "<title>Test</title>" +
                  sCRLF +
                  "<style type=\"text/css\" media=\"print\">body{color:red}div{color:blue}</style>" +
                  sCRLF +
                  "<style type=\"text/css\">span{color:green}</style>" +
                  sCRLF +
                  "</head>" +
                  sCRLF +
                  "<body>" +
                  sCRLF +
                  "<h1>root</h1>" +
                  sCRLF +
                  "</body>" +
                  sCRLF +
                  "</html>" +
                  sCRLF,
                  HCRenderer.getAsHTMLString (aHtml, aCS));
  }

  @Test
  public void testCSSMerge4 ()
  {
    final HCHtml aHtml = new HCHtml ();
    aHtml.head ().setPageTitle ("Test");
    aHtml.head ().addCSS (new HCStyle ("body{color:red}").setMedia (new CSSMediaList (ECSSMedium.PRINT)));
    aHtml.head ().addCSS (new HCStyle ("div{color:blue}"));
    aHtml.head ().addCSS (new HCStyle ("span{color:green}").setMedia (new CSSMediaList (ECSSMedium.PRINT)));
    aHtml.body ().addChild (new HCH1 ().addChild ("root"));
    final String sCRLF = ENewLineMode.DEFAULT.getText ();
    final HCConversionSettings aCS = new HCConversionSettings (EHTMLVersion.HTML5);
    aCS.getXMLWriterSettings ().setEmitNamespaces (false).setIndent (EXMLSerializeIndent.ALIGN_ONLY);

    // Must be done for HCHtml separately
    HCRenderer.prepareHtmlForConversion (aHtml, aCS);

    assertEquals ("<!DOCTYPE html>" +
                  sCRLF +
                  "<html dir=\"ltr\">" +
                  sCRLF +
                  "<head>" +
                  sCRLF +
                  "<title>Test</title>" +
                  sCRLF +
                  "<style type=\"text/css\" media=\"print\">body{color:red}</style>" +
                  sCRLF +
                  "<style type=\"text/css\">div{color:blue}</style>" +
                  sCRLF +
                  "<style type=\"text/css\" media=\"print\">span{color:green}</style>" +
                  sCRLF +
                  "</head>" +
                  sCRLF +
                  "<body>" +
                  sCRLF +
                  "<h1>root</h1>" +
                  sCRLF +
                  "</body>" +
                  sCRLF +
                  "</html>" +
                  sCRLF,
                  HCRenderer.getAsHTMLString (aHtml, aCS));
  }
}
