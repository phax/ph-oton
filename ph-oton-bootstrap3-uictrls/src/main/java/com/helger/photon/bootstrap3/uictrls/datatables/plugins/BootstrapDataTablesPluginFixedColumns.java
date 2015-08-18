package com.helger.photon.bootstrap3.uictrls.datatables.plugins;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.datatables.plugins.DataTablesPluginFixedColumns;

public class BootstrapDataTablesPluginFixedColumns extends DataTablesPluginFixedColumns
{
  @Override
  public void registerExternalResources (final IHCConversionSettingsToNode aConversionSettings)
  {
    super.registerExternalResources (aConversionSettings);
    // Change CSS
    PhotonCSS.unregisterCSSIncludeFromThisRequest (EUICtrlsCSSPathProvider.DATATABLES_FIXED_COLUMNS);
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.DATATABLES_FIXED_COLUMNS_BOOTSTRAP);
  }
}
