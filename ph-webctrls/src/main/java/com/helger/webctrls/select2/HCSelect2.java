package com.helger.webctrls.select2;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNodeWithChildren;
import com.helger.html.hc.html.HCScriptOnDocumentReady;
import com.helger.html.js.builder.jquery.JQuery;
import com.helger.html.request.IHCRequestField;
import com.helger.webbasics.app.html.PerRequestCSSIncludes;
import com.helger.webbasics.app.html.PerRequestJSIncludes;
import com.helger.webctrls.EWebCtrlsCSSPathProvider;
import com.helger.webctrls.EWebCtrlsJSPathProvider;
import com.helger.webctrls.custom.HCExtSelect;

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
    PerRequestCSSIncludes.registerCSSIncludeForThisRequest (EWebCtrlsCSSPathProvider.SELECT2);
    PerRequestJSIncludes.registerJSIncludeForThisRequest (EWebCtrlsJSPathProvider.JQUERY_MOUSEWHEEL);
    PerRequestJSIncludes.registerJSIncludeForThisRequest (EWebCtrlsJSPathProvider.SELECT2);
  }
}
