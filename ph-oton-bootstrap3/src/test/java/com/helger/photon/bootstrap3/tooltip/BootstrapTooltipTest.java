/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.tooltip;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.helger.html.jquery.JQuerySelector;
import com.helger.html.js.JSWriterSettings;

/**
 * Test class for class {@link BootstrapTooltip}.
 *
 * @author Philip Helger
 */
public final class BootstrapTooltipTest
{
  @Test
  public void testCodeGen ()
  {
    final JSWriterSettings aSettings = new JSWriterSettings ().setIndentAndAlign (false);
    final BootstrapTooltip aBT = new BootstrapTooltip (JQuerySelector.id ("foo"));
    assertEquals ("$('#foo').tooltip({});", aBT.jsAttach ().getJSCode (aSettings));
    aBT.setAnimation (false);
    assertEquals ("$('#foo').tooltip({animation:false});", aBT.jsAttach ().getJSCode (aSettings));
    aBT.setHTML (true);
    assertEquals ("$('#foo').tooltip({animation:false,html:true});", aBT.jsAttach ().getJSCode (aSettings));
    aBT.setPlacement (EBootstrapTooltipPosition.BOTTOM, false);
    assertEquals ("$('#foo').tooltip({animation:false,html:true,placement:'bottom'});", aBT.jsAttach ().getJSCode (aSettings));
    aBT.setPlacement (EBootstrapTooltipPosition.BOTTOM, true);
    assertEquals ("$('#foo').tooltip({animation:false,html:true,placement:'bottom auto'});", aBT.jsAttach ().getJSCode (aSettings));
    aBT.setPlacement (EBootstrapTooltipPosition.TOP, true);
    assertEquals ("$('#foo').tooltip({animation:false,html:true,placement:'top auto'});", aBT.jsAttach ().getJSCode (aSettings));
    aBT.setPlacement (EBootstrapTooltipPosition.TOP, false);
    assertEquals ("$('#foo').tooltip({animation:false,html:true});", aBT.jsAttach ().getJSCode (aSettings));
    aBT.setTrigger (EBootstrapTooltipTrigger.HOVER);
    assertEquals ("$('#foo').tooltip({animation:false,html:true,trigger:'hover'});", aBT.jsAttach ().getJSCode (aSettings));
    aBT.setTrigger (EBootstrapTooltipTrigger.HOVER, EBootstrapTooltipTrigger.FOCUS);
    assertEquals ("$('#foo').tooltip({animation:false,html:true});", aBT.jsAttach ().getJSCode (aSettings));
    aBT.setTrigger ();
    assertEquals ("$('#foo').tooltip({animation:false,html:true});", aBT.jsAttach ().getJSCode (aSettings));
    aBT.setDelay (10);
    assertEquals ("$('#foo').tooltip({animation:false,html:true,delay:10});", aBT.jsAttach ().getJSCode (aSettings));
    aBT.setDelay (10, 20);
    assertEquals ("$('#foo').tooltip({animation:false,html:true,delay:{show:10,hide:20}});", aBT.jsAttach ().getJSCode (aSettings));
  }
}
