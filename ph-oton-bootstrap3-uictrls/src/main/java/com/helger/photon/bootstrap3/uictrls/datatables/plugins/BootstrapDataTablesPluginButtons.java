package com.helger.photon.bootstrap3.uictrls.datatables.plugins;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.photon.bootstrap3.CBootstrapCSS;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTablesDom;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;
import com.helger.photon.uictrls.datatables.DataTablesDom;
import com.helger.photon.uictrls.datatables.plugins.DataTablesPluginButtons;

public class BootstrapDataTablesPluginButtons extends DataTablesPluginButtons
{
  @Override
  @Nonnull
  @ReturnsMutableCopy
  @OverrideOnDemand
  protected DataTablesDom createDom ()
  {
    return new BootstrapDataTablesDom ();
  }

  @Override
  @OverrideOnDemand
  protected void weaveIntoDom (@Nonnull final DataTablesDom aDom)
  {
    // Check if it is a Bootstrap datatables DOM
    final int i = aDom.indexOf (DataTablesDom.getDivString (CBootstrapCSS.ROW, CBootstrapCSS.HIDDEN_PRINT));
    if (i >= 0)
    {
      aDom.setPosition (i + 1);
      aDom.remove ();
      aDom.openDiv (CBootstrapCSS.COL_SM_4);
      aDom.setPosition (i + 4).openDiv (CBootstrapCSS.COL_SM_4).addCustom ("B").closeDiv ();
      aDom.setPosition (i + 7);
      aDom.remove ();
      aDom.openDiv (CBootstrapCSS.COL_SM_4);
    }
    else
      super.weaveIntoDom (aDom);
  }

  @Override
  public void registerExternalResources (final IHCConversionSettingsToNode aConversionSettings)
  {
    super.registerExternalResources (aConversionSettings);
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.DATATABLES_BUTTONS_BOOTSTRAP);
    // Change CSS
    PhotonCSS.unregisterCSSIncludeFromThisRequest (EUICtrlsCSSPathProvider.DATATABLES_BUTTONS);
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.DATATABLES_BUTTONS_BOOTSTRAP);
  }
}
