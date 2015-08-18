package com.helger.photon.bootstrap3.uictrls.datatables.plugins;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.datatables.plugins.DataTablesPluginFixedHeader;

public class BootstrapDataTablesPluginFixedHeader extends DataTablesPluginFixedHeader
{
  @Override
  public void registerExternalResources (final IHCConversionSettingsToNode aConversionSettings)
  {
    super.registerExternalResources (aConversionSettings);
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.DATATABLES_FIXED_HEADER_BOOTSTRAP);
  }
}
