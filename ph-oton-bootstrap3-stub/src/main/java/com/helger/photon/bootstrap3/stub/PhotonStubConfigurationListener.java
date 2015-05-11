/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.stub;

import javax.annotation.Nonnull;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.helger.html.meta.EStandardMetaElement;
import com.helger.html.meta.MetaElement;
import com.helger.photon.bootstrap3.EBootstrapCSSPathProvider;
import com.helger.photon.bootstrap3.EBootstrapJSPathProvider;
import com.helger.photon.core.app.html.HTMLConfigManager;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.core.mgr.PhotonCoreManager;
import com.helger.photon.uicore.EUICoreCSSPathProvider;
import com.helger.photon.uicore.EUICoreJSPathProvider;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.famfam.EFamFamIcon;

/**
 * This class triggers some default configuration to run ph-oton applications
 * more easy.
 *
 * @author Philip Helger
 */
public final class PhotonStubConfigurationListener implements ServletContextListener
{
  private static void _registerDefaultResources ()
  {
    final HTMLConfigManager aConfigMgr = PhotonCoreManager.getHTMLConfigMgr ();

    // CSS
    PhotonCSS.registerCSSIncludeForGlobal (EBootstrapCSSPathProvider.BOOTSTRAP_334);
    PhotonCSS.registerCSSIncludeForGlobal (EBootstrapCSSPathProvider.BOOTSTRAP_THEME_334);
    PhotonCSS.registerCSSIncludeForGlobal (EBootstrapCSSPathProvider.BOOTSTRAP_PH);
    PhotonCSS.registerCSSIncludeForGlobal (EUICtrlsCSSPathProvider.FAMFAM_ICONS);
    PhotonCSS.registerCSSIncludeForGlobal (EUICtrlsCSSPathProvider.FAMFAM_FLAGS);
    PhotonCSS.registerCSSIncludeForGlobal (EUICoreCSSPathProvider.UICORE);

    // With conditional comments
    PhotonCSS.registerCSSIncludeForGlobal (EBootstrapCSSPathProvider.BOOTSTRAP_IE9);
    PhotonCSS.registerCSSIncludeForGlobal (EUICoreCSSPathProvider.PLACEHOLDER_FIX);

    // JS
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.JQUERY_1);
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.UICORE_JQUERY);
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.UICORE_FORM);
    PhotonJS.registerJSIncludeForGlobal (EBootstrapJSPathProvider.BOOTSTRAP_334);
    PhotonJS.registerJSIncludeForGlobal (EBootstrapJSPathProvider.BOOTSTRAP_PH);

    // With conditional comments
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.HTML5SHIV_3_7_2);
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.RESPOND);
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.PLACEHOLDER_FIX);

    // Meta elements
    aConfigMgr.addMetaElement (new MetaElement ("generator", "ph-oton stack - https://github.com/phax/ph-oton"));
    aConfigMgr.addMetaElement (EStandardMetaElement.X_UA_COMPATIBLE.getAsMetaElement ("IE=Edge,chrome=1"));
    aConfigMgr.addMetaElement (EStandardMetaElement.VIEWPORT.getAsMetaElement ("width=device-width, initial-scale=1.0"));
  }

  public void contextInitialized (@Nonnull final ServletContextEvent aSCE)
  {
    _registerDefaultResources ();

    // Set default icon set
    EFamFamIcon.setAsDefault ();
  }

  public void contextDestroyed (final ServletContextEvent sce)
  {}
}
