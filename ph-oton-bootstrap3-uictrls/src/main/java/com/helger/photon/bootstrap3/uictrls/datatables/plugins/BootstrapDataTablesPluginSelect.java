package com.helger.photon.bootstrap3.uictrls.datatables.plugins;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.datatables.plugins.DataTablesPluginSelect;

public class BootstrapDataTablesPluginSelect extends DataTablesPluginSelect
{
  @Override
  public void registerExternalResources (final IHCConversionSettingsToNode aConversionSettings)
  {
    super.registerExternalResources (aConversionSettings);
    // Change CSS
    PhotonCSS.unregisterCSSIncludeFromThisRequest (EUICtrlsCSSPathProvider.DATATABLES_SELECT);
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.DATATABLES_SELECT_BOOTSTRAP);
  }
}
