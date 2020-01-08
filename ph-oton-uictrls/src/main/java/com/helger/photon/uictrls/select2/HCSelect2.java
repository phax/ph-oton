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
package com.helger.photon.uictrls.select2;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.script.HCScriptInlineOnDocumentReady;
import com.helger.html.jquery.JQuery;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSInvocation;
import com.helger.html.request.IHCRequestField;
import com.helger.html.request.IHCRequestFieldMultiValue;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.app.html.PhotonJS;
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

  public HCSelect2 (@Nonnull final IHCRequestFieldMultiValue aRF)
  {
    super (aRF);
    ensureID ();
  }

  /**
   * @return The invocation options to be used. May be <code>null</code> or
   *         empty to indicate that no further options are needed.
   * @since 8.0.2
   */
  @Nullable
  @OverrideOnDemand
  protected JSAssocArray getSelect2InvocationOptions ()
  {
    return null;
  }

  /**
   * @return The select2 JS invocation to be used. May not be <code>null</code>.
   * @since 8.0.2
   */
  @Nonnull
  @OverrideOnDemand
  protected JSInvocation getSelect2Invocation ()
  {
    final JSInvocation ret = JQuery.idRef (this).invoke ("select2");
    final JSAssocArray aOptions = getSelect2InvocationOptions ();
    if (aOptions != null && aOptions.isNotEmpty ())
      ret.arg (aOptions);
    return ret;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);

    // Add special JS code
    aTargetNode.addChild (new HCScriptInlineOnDocumentReady (getSelect2Invocation ()));
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
