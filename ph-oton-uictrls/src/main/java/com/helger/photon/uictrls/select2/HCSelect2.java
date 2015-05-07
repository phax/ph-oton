package com.helger.photon.uictrls.select2;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNodeWithChildren;
import com.helger.html.hc.html.HCScriptOnDocumentReady;
import com.helger.html.js.builder.jquery.JQuery;
import com.helger.html.request.IHCRequestField;
import com.helger.photon.core.app.html.PerRequestCSSIncludes;
import com.helger.photon.core.app.html.PerRequestJSIncludes;
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
    PerRequestCSSIncludes.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.SELECT2);
    PerRequestJSIncludes.registerJSIncludeForThisRequest (EUICoreJSPathProvider.JQUERY_MOUSEWHEEL);
    PerRequestJSIncludes.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.SELECT2);
  }
}
