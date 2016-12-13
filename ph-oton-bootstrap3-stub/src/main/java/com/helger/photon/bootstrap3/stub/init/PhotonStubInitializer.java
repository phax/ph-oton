/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.stub.init;

import org.slf4j.bridge.SLF4JBridgeHandler;

import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.scope.ScopeHelper;
import com.helger.commons.system.SystemProperties;
import com.helger.html.hc.config.HCConversionSettings;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.ext.HCCustomizerAutoFocusFirstCtrl;
import com.helger.html.hc.impl.HCCustomizerList;
import com.helger.html.meta.EStandardMetaElement;
import com.helger.html.resource.css.ICSSPathProvider;
import com.helger.html.resource.js.IJSPathProvider;
import com.helger.photon.basic.app.PhotonPathMapper;
import com.helger.photon.bootstrap3.BootstrapCustomConfig;
import com.helger.photon.bootstrap3.servlet.BootstrapCustomizer;
import com.helger.photon.core.ajax.servlet.PublicApplicationAjaxServlet;
import com.helger.photon.core.ajax.servlet.SecureApplicationAjaxServlet;
import com.helger.photon.core.app.CApplication;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.core.app.html.PhotonMetaElements;
import com.helger.photon.core.requesttrack.RequestTracker;
import com.helger.photon.core.servlet.AbstractPublicApplicationServlet;
import com.helger.photon.core.servlet.AbstractSecureApplicationServlet;
import com.helger.photon.uicore.EUICoreCSSPathProvider;
import com.helger.photon.uicore.EUICoreJSPathProvider;
import com.helger.photon.uicore.icon.DefaultIcons;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.autonumeric.AbstractHCAutoNumeric;
import com.helger.photon.uictrls.famfam.EFamFamIcon;
import com.helger.servlet.response.UnifiedResponseDefaultSettings;
import com.helger.web.scope.mgr.ThrowingScopeFactory;
import com.helger.web.scope.mgr.WebScopeManager;

/**
 * This class triggers some default configuration to run ph-oton applications
 * more easy.
 *
 * @author Philip Helger
 */
public final class PhotonStubInitializer
{
  public static void registerDefaultResources ()
  {
    // CSS
    for (final ICSSPathProvider aPP : BootstrapCustomConfig.getAllBootstrapCSS ())
      PhotonCSS.registerCSSIncludeForGlobal (aPP);
    PhotonCSS.registerCSSIncludeForGlobal (EUICtrlsCSSPathProvider.FAMFAM_ICONS);
    PhotonCSS.registerCSSIncludeForGlobal (EUICtrlsCSSPathProvider.FAMFAM_FLAGS);
    PhotonCSS.registerCSSIncludeForGlobal (EUICoreCSSPathProvider.UICORE);

    // With conditional comments
    PhotonCSS.registerCSSIncludeForGlobal (EUICoreCSSPathProvider.JQUERY_PLACEHOLDER);

    // JS
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.JQUERY_1);
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.UICORE_JQUERY);
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.UICORE_FORM);
    for (final IJSPathProvider aPP : BootstrapCustomConfig.getAllBootstrapJS ())
      PhotonJS.registerJSIncludeForGlobal (aPP);

    // With conditional comments
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.HTML5SHIV);
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.RESPOND);
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.JQUERY_PLACEHOLDER);
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.JQUERY_PLACEHOLDER_ALL);

    // Meta elements
    PhotonMetaElements.registerMetaElementForGlobal (EStandardMetaElement.GENERATOR.getAsMetaElement ("ph-oton stack - https://github.com/phax/ph-oton"));
    PhotonMetaElements.registerMetaElementForGlobal (EStandardMetaElement.X_UA_COMPATIBLE.getAsMetaElement ("IE=Edge,chrome=1"));
    // Source: http://v4-alpha.getbootstrap.com/getting-started/introduction/
    PhotonMetaElements.registerMetaElementForGlobal (EStandardMetaElement.VIEWPORT.getAsMetaElement ("width=device-width, initial-scale=1, shrink-to-fit=no"));

    // Finally read stuff from files that may be defined within the application
    PhotonCSS.readCSSIncludesForGlobal (new ClassPathResource (PhotonCSS.DEFAULT_FILENAME));
    PhotonJS.readJSIncludesForGlobal (new ClassPathResource (PhotonJS.DEFAULT_FILENAME));
    PhotonMetaElements.readMetaElementsForGlobal (new ClassPathResource (PhotonMetaElements.DEFAULT_FILENAME));
  }

  public static void onContextInitialized ()
  {
    // Logging: JUL to SLF4J
    SLF4JBridgeHandler.removeHandlersForRootLogger ();
    SLF4JBridgeHandler.install ();

    // Ensure that only web scopes are created and never non-web scopes
    ThrowingScopeFactory.installToMetaScopeFactory ();

    if (GlobalDebug.isDebugMode ())
    {
      if (false)
        ScopeHelper.setDebugSessionScopeEnabled (true);

      // Enable Java Serialization debug
      SystemProperties.setPropertyValue ("sun.io.serialization.extendedDebugInfo", "true");

      if (false)
      {
        // Not production ready yet
        WebScopeManager.setSessionPassivationAllowed (true);
      }

      // Disable in debug mode
      RequestTracker.getInstance ().getRequestTrackingMgr ().setLongRunningCheckEnabled (false);
    }

    // Default response headers to be added
    UnifiedResponseDefaultSettings.setAllowMimeSniffing (false);
    UnifiedResponseDefaultSettings.setEnableXSSFilter (true);

    // Set new customizer only if the default customizer is present
    if (HCConversionSettings.isDefaultCustomizer (HCSettings.getConversionSettings ().getCustomizer ()))
    {
      // Special Bootstrap customizer
      HCSettings.getMutableConversionSettings ()
                .setCustomizer (new HCCustomizerList (new BootstrapCustomizer (),
                                                      new HCCustomizerAutoFocusFirstCtrl ()));
    }

    // Set default icon set if none is defined
    if (!DefaultIcons.areDefined ())
      EFamFamIcon.setAsDefault ();

    // Never use a thousand separator in HCAutoNumeric fields because of
    // parsing problems
    AbstractHCAutoNumeric.setDefaultThousandSeparator ("");

    if (!PhotonPathMapper.containsAnyMapping ())
    {
      // Add default mapping from Application ID to path
      PhotonPathMapper.setApplicationServletPathMapping (CApplication.APP_ID_PUBLIC,
                                                         AbstractPublicApplicationServlet.SERVLET_DEFAULT_PATH);
      PhotonPathMapper.setAjaxServletPathMapping (CApplication.APP_ID_PUBLIC,
                                                  PublicApplicationAjaxServlet.SERVLET_DEFAULT_PATH);
      PhotonPathMapper.setApplicationServletPathMapping (CApplication.APP_ID_SECURE,
                                                         AbstractSecureApplicationServlet.SERVLET_DEFAULT_PATH);
      PhotonPathMapper.setAjaxServletPathMapping (CApplication.APP_ID_SECURE,
                                                  SecureApplicationAjaxServlet.SERVLET_DEFAULT_PATH);
      PhotonPathMapper.setDefaultApplicationID (CApplication.APP_ID_PUBLIC);
    }
  }
}
