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

import com.helger.html.meta.MetaElement;
import com.helger.photon.bootstrap3.EBootstrapCSSPathProvider;
import com.helger.photon.bootstrap3.EBootstrapJSPathProvider;
import com.helger.photon.core.app.html.HTMLConfigManager;
import com.helger.photon.core.mgr.PhotonCoreManager;
import com.helger.photon.uicore.EUICoreCSSPathProvider;
import com.helger.photon.uicore.EUICoreJSPathProvider;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.famfam.EFamFamIcon;

/**
 * This class
 *
 * @author Philip Helger
 */
public final class PhotonStubConfigurationListener implements ServletContextListener
{
  private static void _registerDefaultResources ()
  {
    final HTMLConfigManager aConfigMgr = PhotonCoreManager.getHTMLConfigMgr ();

    // CSS
    aConfigMgr.addCSSItem (EBootstrapCSSPathProvider.BOOTSTRAP_334);
    aConfigMgr.addCSSItem (EBootstrapCSSPathProvider.BOOTSTRAP_THEME_334);
    aConfigMgr.addCSSItem (EBootstrapCSSPathProvider.BOOTSTRAP_PH);
    aConfigMgr.addCSSItem (EUICtrlsCSSPathProvider.FAMFAM_ICONS);
    aConfigMgr.addCSSItem (EUICtrlsCSSPathProvider.FAMFAM_FLAGS);
    aConfigMgr.addCSSItem (EUICoreCSSPathProvider.UICORE);

    // With conditional comments
    aConfigMgr.addCSSItem (EBootstrapCSSPathProvider.BOOTSTRAP_IE9);
    aConfigMgr.addCSSItem (EUICoreCSSPathProvider.PLACEHOLDER_FIX);

    // JS
    aConfigMgr.addJSItem (EUICoreJSPathProvider.JQUERY_1);
    aConfigMgr.addJSItem (EUICoreJSPathProvider.UICORE_JQUERY);
    aConfigMgr.addJSItem (EUICoreJSPathProvider.UICORE_FORM);
    aConfigMgr.addJSItem (EBootstrapJSPathProvider.BOOTSTRAP_334);
    aConfigMgr.addJSItem (EBootstrapJSPathProvider.BOOTSTRAP_PH);

    // With conditional comments
    aConfigMgr.addJSItem (EUICoreJSPathProvider.HTML5SHIV_3_7_2);
    aConfigMgr.addJSItem (EUICoreJSPathProvider.RESPOND);
    aConfigMgr.addJSItem (EUICoreJSPathProvider.PLACEHOLDER_FIX);

    // Meta elements
    aConfigMgr.addMetaElement (new MetaElement ("generator", "ph-oton stack - https://github.com/phax/ph-oton"));
    aConfigMgr.addMetaElement (new MetaElement ("X-UA-Compatible", true, "IE=Edge,chrome=1"));
    aConfigMgr.addMetaElement (new MetaElement ("viewport", "width=device-width, initial-scale=1.0"));
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
