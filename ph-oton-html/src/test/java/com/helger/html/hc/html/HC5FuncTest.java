/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.url.SimpleURL;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.IHCConversionSettings;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.html.deprecated.HCHGroup;
import com.helger.html.hc.html.embedded.HCAudio;
import com.helger.html.hc.html.embedded.HCSource;
import com.helger.html.hc.html.embedded.HCTrack;
import com.helger.html.hc.html.embedded.HCVideo;
import com.helger.html.hc.html.forms.HCKeyGen;
import com.helger.html.hc.html.forms.HCMeter;
import com.helger.html.hc.html.forms.HCProgress;
import com.helger.html.hc.html.grouping.HCFigure;
import com.helger.html.hc.html.interactive.HCCommand;
import com.helger.html.hc.html.root.HCHtml;
import com.helger.html.hc.html.script.HCCanvas;
import com.helger.html.hc.html.sections.HCArticle;
import com.helger.html.hc.html.sections.HCAside;
import com.helger.html.hc.html.sections.HCBody;
import com.helger.html.hc.html.sections.HCFooter;
import com.helger.html.hc.html.sections.HCHeader;
import com.helger.html.hc.html.sections.HCNav;
import com.helger.html.hc.html.sections.HCSection;
import com.helger.html.hc.html.textlevel.HCBDI;
import com.helger.html.hc.html.textlevel.HCMark;
import com.helger.html.hc.html.textlevel.HCRP;
import com.helger.html.hc.html.textlevel.HCRT;
import com.helger.html.hc.html.textlevel.HCRuby;
import com.helger.html.hc.html.textlevel.HCTime;
import com.helger.html.hc.html.textlevel.HCWBR;
import com.helger.html.hc.render.HCRenderer;

/**
 * Instantiate and generate code for all HTML5 elements available
 *
 * @author Philip Helger
 */
public final class HC5FuncTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (HC5FuncTest.class);

  @Test
  public void testMainHC ()
  {
    final HCHtml h = new HCHtml ();
    final HCBody b = h.body ();

    b.addChild (new HCArticle ());
    b.addChild (new HCArticle ().addChild ("Bla foo"));
    b.addChild (new HCAside ());
    b.addChild (new HCAside ().addChild ("Aside"));
    final HCAudio aAudio = new HCAudio ();
    aAudio.addSource (new HCSource ().setSrc (new SimpleURL ("a.mp3")));
    aAudio.addTrack (new HCTrack ().setSrc (new SimpleURL ("a.mp3")));
    b.addChild (aAudio);
    b.addChild (new HCBDI ());
    b.addChild (new HCBDI ().addChild ("bidirectional"));
    b.addChild (new HCCanvas ());
    b.addChild (new HCCanvas ().addChild ("Der kann was der Canvas"));
    b.addChild (new HCCommand ());
    b.addChild (new HCFigure ());
    b.addChild (new HCFigure ().addChild ("Bla foo"));
    b.addChild (new HCFooter ());
    b.addChild (new HCFooter ().addChild ("Bla foo"));
    b.addChild (new HCHeader ());
    b.addChild (new HCHeader ().addChild ("Bla foo"));
    b.addChild (new HCHGroup ());
    b.addChild (new HCHGroup ().addChild ("Bla foo"));
    b.addChild (new HCKeyGen ("kg1"));
    b.addChild (new HCMark ());
    b.addChild (new HCMark ().addChild ("Bla foo"));
    b.addChild (new HCMeter ());
    b.addChild (new HCMeter ().addChild ("Bla foo"));
    b.addChild (new HCNav ());
    b.addChild (new HCNav ().addChild ("Bla foo"));
    b.addChild (new HCProgress ());
    b.addChild (new HCProgress ().addChild ("Bla foo"));
    b.addChild (new HCRP ());
    b.addChild (new HCRP ().addChild ("Bla foo"));
    b.addChild (new HCRT ());
    b.addChild (new HCRT ().addChild ("Bla foo"));
    b.addChild (new HCRuby ());
    b.addChild (new HCRuby ().addItem (new HCRP ().addChild ("1")));
    b.addChild (new HCRuby ().addItem (new HCRT ().addChild ("Bla foo")));
    b.addChild (new HCRuby ().addItem (new HCRP ().addChild ("2")));
    b.addChild (new HCSection ());
    b.addChild (new HCSection ().addChild ("Bla foo"));
    b.addChild (new HCTime ());
    b.addChild (new HCTime ().addChild ("Bla foo"));
    final HCVideo aVideo = new HCVideo ();
    aVideo.addSource (new HCSource ().setSrc (new SimpleURL ("a.avi")));
    aVideo.addTrack (new HCTrack ().setSrc (new SimpleURL ("a.avi")));
    b.addChild (aVideo);
    b.addChild (new HCWBR ());

    final IHCConversionSettings aCS = HCSettings.getConversionSettings ().getCloneIfNecessary (EHTMLVersion.HTML5);
    assertNotNull (HCRenderer.getAsNode (h, aCS));
    if (false)
      LOGGER.info (HCRenderer.getAsHTMLString (h, aCS));
  }
}
