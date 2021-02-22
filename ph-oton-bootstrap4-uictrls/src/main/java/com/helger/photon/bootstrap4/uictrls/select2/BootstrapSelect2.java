package com.helger.photon.bootstrap4.uictrls.select2;

import javax.annotation.Nonnull;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.request.IHCRequestField;
import com.helger.html.request.IHCRequestFieldMultiValue;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.select2.HCSelect2;

/**
 * Bootstrap 4 version of Select2
 *
 * @author Philip Helger
 * @since 8.2.10
 */
public class BootstrapSelect2 extends HCSelect2
{
  public BootstrapSelect2 (@Nonnull final IHCRequestField aRF)
  {
    super (aRF);
  }

  public BootstrapSelect2 (@Nonnull final IHCRequestFieldMultiValue aRF)
  {
    super (aRF);
  }

  @Override
  protected JSAssocArray getSelect2InvocationOptions ()
  {
    JSAssocArray ret = super.getSelect2InvocationOptions ();
    if (ret == null)
      ret = new JSAssocArray ();
    ret.add ("theme", "bootstrap4");
    return ret;
  }

  @Override
  protected void onRegisterExternalResources (final IHCConversionSettingsToNode aConversionSettings, final boolean bForcedRegistration)
  {
    super.onRegisterExternalResources (aConversionSettings, bForcedRegistration);
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.SELECT2_BOOTSTRAP4);
  }
}
