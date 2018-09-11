package com.helger.photon.bootstrap4.uictrls.typeahead;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.request.IHCRequestField;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.typeahead.TypeaheadEdit;

/**
 * Bootstrap version of {@link TypeaheadEdit}
 *
 * @author Philip Helger
 */
public class BootstrapTypeahead extends TypeaheadEdit
{
  public BootstrapTypeahead (@Nonnull final IHCRequestField aRFEdit,
                             @Nonnull final IHCRequestField aRFHidden,
                             @Nonnull final ISimpleURL aAjaxInvocationURL,
                             @Nonnull final Locale aDisplayLocale)
  {
    super (aRFEdit, aRFHidden, aAjaxInvocationURL, aDisplayLocale);
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForceRegistration)
  {
    super.onRegisterExternalResources (aConversionSettings, bForceRegistration);
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.TYPEAHEAD_BOOTSTRAP4);
  }
}
