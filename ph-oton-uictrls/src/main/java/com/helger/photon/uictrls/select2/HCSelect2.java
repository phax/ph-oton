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
package com.helger.photon.uictrls.select2;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNodeWithChildren;
import com.helger.html.hc.html.HCScriptOnDocumentReady;
import com.helger.html.js.builder.jquery.JQuery;
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
  public void onAdded (@Nonnegative final int nIndex, @Nonnull final IHCHasChildrenMutable <?, ?> aParent)
  {
    registerExternalResources ();

    // Add special JS code
    ((IHCNodeWithChildren <?>) aParent).addChild (new HCScriptOnDocumentReady (JQuery.idRef (this).invoke ("select2")));
  }

  @Override
  public void onRemoved (@Nonnegative final int nIndex, @Nonnull final IHCHasChildrenMutable <?, ?> aParent)
  {
    // Remove the JS that is now on that index
    aParent.removeChild (nIndex);
  }

  public static void registerExternalResources ()
  {
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.SELECT2);
    PhotonJS.registerJSIncludeForThisRequest (EUICoreJSPathProvider.JQUERY_MOUSEWHEEL);
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.SELECT2);
  }
}
