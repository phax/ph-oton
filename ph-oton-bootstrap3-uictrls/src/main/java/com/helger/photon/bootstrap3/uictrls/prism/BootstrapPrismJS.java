/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.uictrls.prism;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.photon.app.html.PhotonCSS;
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
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.PRISMJS_BOOTSTRAP3);
  }
}
