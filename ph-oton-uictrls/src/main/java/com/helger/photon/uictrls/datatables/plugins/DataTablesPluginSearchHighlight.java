package com.helger.photon.uictrls.datatables.plugins;

import javax.annotation.Nullable;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSExpr;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.uicore.EUICoreJSPathProvider;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;

public class DataTablesPluginSearchHighlight extends AbstractDataTablesPlugin
{
  public DataTablesPluginSearchHighlight ()
  {
    super ("searchHighlight");
  }

  @Nullable
  public IJSExpression getInitParams ()
  {
    // No properties present
    return JSExpr.TRUE;
  }

  @Override
  public void registerExternalResources (final IHCConversionSettingsToNode aConversionSettings)
  {
    PhotonJS.registerJSIncludeForThisRequest (EUICoreJSPathProvider.JQUERY_HIGHLIGHT);
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.DATATABLES_SEARCH_HIGHLIGHT);
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.DATATABLES_SEARCH_HIGHLIGHT);
  }
}
