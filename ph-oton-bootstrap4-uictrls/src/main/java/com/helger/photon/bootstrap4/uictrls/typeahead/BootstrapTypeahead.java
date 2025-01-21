/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.photon.bootstrap4.uictrls.typeahead;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.request.IHCRequestField;
import com.helger.photon.app.html.PhotonCSS;
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
