/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.mime.CMimeType;
import com.helger.commons.url.SimpleURL;
import com.helger.html.EHTMLRole;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.IHCConversionSettings;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.html.deprecated.HCCenter;
import com.helger.html.hc.html.deprecated.HCFrame;
import com.helger.html.hc.html.deprecated.HCFrameset;
import com.helger.html.hc.html.edits.HCDel;
import com.helger.html.hc.html.edits.HCIns;
import com.helger.html.hc.html.embedded.HCEmbed;
import com.helger.html.hc.html.embedded.HCIFrame;
import com.helger.html.hc.html.embedded.HCImg;
import com.helger.html.hc.html.embedded.HCObject;
import com.helger.html.hc.html.embedded.HCParam;
import com.helger.html.hc.html.forms.HCButton;
import com.helger.html.hc.html.forms.HCButton_Reset;
import com.helger.html.hc.html.forms.HCButton_Submit;
import com.helger.html.hc.html.forms.HCCheckBox;
import com.helger.html.hc.html.forms.HCEdit;
import com.helger.html.hc.html.forms.HCEditFile;
import com.helger.html.hc.html.forms.HCEditPassword;
import com.helger.html.hc.html.forms.HCFieldSet;
import com.helger.html.hc.html.forms.HCForm;
import com.helger.html.hc.html.forms.HCHiddenField;
import com.helger.html.hc.html.forms.HCLabel;
import com.helger.html.hc.html.forms.HCLegend;
import com.helger.html.hc.html.forms.HCOptGroup;
import com.helger.html.hc.html.forms.HCRadioButton;
import com.helger.html.hc.html.forms.HCSelect;
import com.helger.html.hc.html.forms.HCTextArea;
import com.helger.html.hc.html.grouping.HCBlockQuote;
import com.helger.html.hc.html.grouping.HCDD;
import com.helger.html.hc.html.grouping.HCDL;
import com.helger.html.hc.html.grouping.HCDT;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.grouping.HCHR;
import com.helger.html.hc.html.grouping.HCOL;
import com.helger.html.hc.html.grouping.HCP;
import com.helger.html.hc.html.grouping.HCPre;
import com.helger.html.hc.html.grouping.HCUL;
import com.helger.html.hc.html.interactive.HCMenu;
import com.helger.html.hc.html.metadata.EHCLinkType;
import com.helger.html.hc.html.metadata.HCLink;
import com.helger.html.hc.html.metadata.HCStyle;
import com.helger.html.hc.html.root.HCHtml;
import com.helger.html.hc.html.script.HCNoScript;
import com.helger.html.hc.html.script.HCScriptFile;
import com.helger.html.hc.html.script.HCScriptInline;
import com.helger.html.hc.html.sections.HCAddress;
import com.helger.html.hc.html.sections.HCBody;
import com.helger.html.hc.html.sections.HCH1;
import com.helger.html.hc.html.sections.HCH2;
import com.helger.html.hc.html.sections.HCH3;
import com.helger.html.hc.html.sections.HCH4;
import com.helger.html.hc.html.sections.HCH5;
import com.helger.html.hc.html.sections.HCH6;
import com.helger.html.hc.html.tabular.HCCol;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.html.hc.html.textlevel.HCAbbr;
import com.helger.html.hc.html.textlevel.HCB;
import com.helger.html.hc.html.textlevel.HCBDO;
import com.helger.html.hc.html.textlevel.HCCite;
import com.helger.html.hc.html.textlevel.HCCode;
import com.helger.html.hc.html.textlevel.HCDFN;
import com.helger.html.hc.html.textlevel.HCEM;
import com.helger.html.hc.html.textlevel.HCI;
import com.helger.html.hc.html.textlevel.HCKBD;
import com.helger.html.hc.html.textlevel.HCQ;
import com.helger.html.hc.html.textlevel.HCS;
import com.helger.html.hc.html.textlevel.HCSamp;
import com.helger.html.hc.html.textlevel.HCSpan;
import com.helger.html.hc.html.textlevel.HCStrong;
import com.helger.html.hc.html.textlevel.HCSub;
import com.helger.html.hc.html.textlevel.HCSup;
import com.helger.html.hc.html.textlevel.HCVar;
import com.helger.html.hc.mock.HCTestRuleOptimized;
import com.helger.html.hc.render.HCRenderer;
import com.helger.html.js.UnparsedJSCodeProvider;

/**
 * Instantiate and generate code for all HTML elements available
 *
 * @author Philip Helger
 */
public final class HCFuncTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (HCFuncTest.class);
  @Rule
  public final HCTestRuleOptimized m_aRule = new HCTestRuleOptimized ();

  @Test
  public void testMainHC ()
  {
    final HCHtml h = new HCHtml ();
    final HCBody b = h.body ();
    b.addChild (new HCA (new SimpleURL ("http://www.helger.com")).setTarget (HC_Target.SELF).addChild ("helger"));
    b.addChild (new HCAbbr ().addChild ("abbr"));
    b.addChild (new HCAddress ().addChild ("address"));
    b.addChild (new HCB ().addChild ("echt fett"));
    b.addChild (new HCBDO ().addChild ("bdo"));
    b.addChild (new HCBlockQuote ().addChild ("blockQuote"));
    b.addChild (new HCButton_Reset ("Abbrechen"));
    b.addChild (new HCButton_Submit ("OK"));
    b.addChild (new HCButton ("Knopf"));
    b.addChild (new HCCenter ());
    b.addChild (new HCCenter ().addChild ("Zentriert"));
    b.addChild (new HCCheckBox ());
    b.addChild (new HCCite ());
    b.addChild (new HCCite ().addChild ("Zitiert"));
    b.addChild (new HCCode ());
    b.addChild (new HCCode ().addChild ("var i = 0;"));
    b.addChild (new HCDFN ());
    b.addChild (new HCDFN ().addChild ("<a> ist ein XML Tag"));
    final HCDL aDL = b.addAndReturnChild (new HCDL ());
    aDL.addItem (new HCDD ());
    aDL.addItem (new HCDD ().addChild ("def"));
    aDL.addItem (new HCDT ());
    aDL.addItem (new HCDT ().addChild ("term"));
    b.addChild (new HCDel ());
    b.addChild (new HCDel ().addChild ("gelöscht"));
    b.addChild (new HCDiv ());
    b.addChild (new HCDiv ().addChild ("Absatz"));
    b.addChild (new HCEdit ("filename").setValue ("autoexec.bat"));
    b.addChild (new HCEditFile ("upload"));
    b.addChild (new HCEditPassword ("password"));
    b.addChild (new HCEM ());
    b.addChild (new HCEM ().addChild ("emphasised"));
    b.addChild (new HCEmbed ().setSrc (new SimpleURL ("myfile.txt")));
    b.addChild (new HCFieldSet ());
    b.addChild (new HCFieldSet ("Gruppe"));
    b.addChild (new HCForm (new SimpleURL ()).setSubmitPressingEnter (false));
    b.addChild (new HCFrame ("frame1"));
    b.addChild (new HCFrameset ());
    b.addChild (new HCH1 ());
    b.addChild (new HCH1 ().addChild ("Ü1"));
    b.addChild (new HCH2 ());
    b.addChild (new HCH2 ().addChild ("Ü2"));
    b.addChild (new HCH3 ());
    b.addChild (new HCH3 ().addChild ("Ü3"));
    b.addChild (new HCH4 ());
    b.addChild (new HCH4 ().addChild ("Ü4"));
    b.addChild (new HCH5 ());
    b.addChild (new HCH5 ().addChild ("Ü5"));
    b.addChild (new HCH6 ());
    b.addChild (new HCH6 ().addChild ("Ü6"));
    b.addChild (new HCHiddenField ("action", "value"));
    b.addChild (new HCHiddenField ("action", 4711));
    b.addChild (new HCHiddenField ("action", false));
    b.addChild (new HCHR ());
    b.addChild (new HCIFrame ());
    b.addChild (new HCImg ().setSrc (new SimpleURL ("test1.png")));
    b.addChild (new HCIns ());
    b.addChild (new HCIns ().addChild ("Das wäre also ein Beispiel"));
    b.addChild (new HCI ());
    b.addChild (new HCI ().addChild ("Das wäre also ein Beispiel"));
    b.addChild (new HCKBD ());
    b.addChild (new HCKBD ().addChild ("Das wäre also ein Beispiel"));
    b.addChild (new HCLabel ());
    b.addChild (new HCLabel ().addChild ("Feldname"));
    b.addChild (new HCLegend ());
    b.addChild (new HCLegend ().addChild ("Legend"));
    b.addChild (new HCLink ().setRel (EHCLinkType.ALTERNATE).setType (CMimeType.TEXT_HTML).setHref (new SimpleURL ("any.html")));
    final HCMenu aMenu = new HCMenu ();
    aMenu.addItem ("File");
    aMenu.addItem ("Settings");
    b.addChild (aMenu);
    b.addChild (new HCNoScript ().addChild ("JS missing"));
    final HCObject aObject = new HCObject ().setType (CMimeType.APPLICATION_SHOCKWAVE_FLASH).setClassID ("classID{567}");
    aObject.addChild (new HCParam ("par1"));
    b.addChild (aObject);
    final HCOL aOL = new HCOL ();
    aOL.addItem ("First");
    aOL.addItem ("Seconf");
    b.addChild (aOL);
    b.addChild (new HCOptGroup ());
    b.addChild (new HCP ());
    b.addChild (new HCP ().addChild ("List"));
    b.addChild (new HCPre ());
    b.addChild (new HCPre ().addChild ("List"));
    b.addChild (new HCQ ());
    b.addChild (new HCQ ().addChild ("List"));
    b.addChild (new HCRadioButton ());
    b.addChild (new HCS ());
    b.addChild (new HCS ().addChild ("Das wäre also ein Beispiel"));
    b.addChild (new HCSamp ());
    b.addChild (new HCSamp ().addChild ("List"));
    b.addChild (new HCScriptInline (new UnparsedJSCodeProvider ("i=17;")));
    b.addChild (new HCScriptFile ().setSrc (new SimpleURL ("a.js")));
    final HCSelect aSelect = new HCSelect ("x");
    aSelect.addOption ("y", "Ypsilon");
    aSelect.addOption ("z", "Zet");
    b.addChild (aSelect);
    b.addChild (new HCSpan ());
    b.addChild (new HCSpan ().addChild ("List"));
    b.addChild (new HCStrong ());
    b.addChild (new HCStrong ().addChild ("Das wäre also ein Beispiel"));
    b.addChild (new HCS ());
    b.addChild (new HCS ().addChild ("Das wäre also ein Beispiel"));
    b.addChild (new HCStyle ("div{color:red;}"));
    b.addChild (new HCSub ());
    b.addChild (new HCSub ().addChild ("unter"));
    b.addChild (new HCSup ());
    b.addChild (new HCSup ().addChild ("unter"));
    final HCTable aTable = b.addAndReturnChild (new HCTable (new HCCol (50), new HCCol (20)).setBodyID ("ID"));
    aTable.addHeaderRow ().addCells ("Name", "Wert");
    aTable.addBodyRow ().addCells ("abc", "def");
    aTable.addFooterRow ().addCells ("", "OK!");
    b.addChild (new HCTextArea ("name", "value"));
    final HCUL aUL = new HCUL ();
    aUL.addItem ("bla");
    aUL.addItem ("foo");
    b.addChild (aUL);
    b.addChild (new HCVar ());
    b.addChild (new HCVar ().addChild ("zzz"));

    final IHCConversionSettings aCS = HCSettings.getConversionSettings ().getCloneIfNecessary (EHTMLVersion.XHTML11);
    assertNotNull (HCRenderer.getAsNode (h, aCS));
    if (false)
      LOGGER.info (HCRenderer.getAsHTMLString (h, aCS));
  }

  @Test
  public void testRole ()
  {
    final HCDiv aDiv = new HCDiv ();
    assertNull (aDiv.getRole ());
    aDiv.setRole (EHTMLRole.DIALOG);
    assertEquals (EHTMLRole.DIALOG, aDiv.getRole ());
    assertEquals ("<div xmlns=\"http://www.w3.org/1999/xhtml\" role=\"dialog\"></div>", HCRenderer.getAsHTMLString (aDiv));
  }
}
