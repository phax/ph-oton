/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.select2;

import javax.annotation.Nonnull;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.script.HCScriptInlineOnDocumentReady;
import com.helger.html.jquery.JQuery;
import com.helger.html.request.IHCRequestField;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.uicore.EUICoreJSPathProvider;
import com.helger.photon.uicore.html.select.HCExtSelect;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;

public class HCSelect2 extends HCExtSelect
{
  public HCSelect2 (@Nonnull final IHCRequestField aRF)
  {
    super (aRF);
    ensureID ();
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);

    // Add special JS code
    aTargetNode.addChild (new HCScriptInlineOnDocumentReady (JQuery.idRef (this).invoke ("select2")));
  }

  @Override
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForcedRegistration)
  {
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.SELECT2);
    PhotonJS.registerJSIncludeForThisRequest (EUICoreJSPathProvider.JQUERY_MOUSEWHEEL);
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.SELECT2);
  }
}
