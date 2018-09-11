package com.helger.photon.bootstrap4.uictrls.prism;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.prism.EPrismLanguage;
import com.helger.photon.uictrls.prism.HCPrismJS;

/**
 * Bootstrap version of prism.
 *
 * @author Philip Helger
 */
public class BootstrapPrismJS extends HCPrismJS
{
  public BootstrapPrismJS (@Nonnull final EPrismLanguage eLanguage)
  {
    super (eLanguage);
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForcedRegistration)
  {
    super.onRegisterExternalResources (aConversionSettings, bForcedRegistration);
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.PRISMJS_BOOTSTRAP4);
  }
}
