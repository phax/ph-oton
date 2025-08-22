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
package com.helger.photon.bootstrap4.stub.init;

import org.slf4j.bridge.SLF4JBridgeHandler;

import com.helger.annotation.concurrent.Immutable;
import com.helger.base.debug.GlobalDebug;
import com.helger.base.system.SystemProperties;
import com.helger.html.CHTMLCharset;
import com.helger.html.hc.config.HCConversionSettings;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.ext.HCCustomizerAutoFocusFirstCtrl;
import com.helger.html.hc.impl.HCCustomizerList;
import com.helger.html.jscode.customize.HCCustomizerExternalizeAttrJS;
import com.helger.html.meta.EStandardMetaElement;
import com.helger.html.meta.MetaElement;
import com.helger.html.resource.css.ICSSPathProvider;
import com.helger.html.resource.js.IJSPathProvider;
import com.helger.io.resource.ClassPathResource;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.app.html.PhotonMetaElements;
import com.helger.photon.bootstrap4.BootstrapCustomConfig;
import com.helger.photon.core.appid.CApplicationID;
import com.helger.photon.core.appid.PhotonGlobalState;
import com.helger.photon.core.servlet.AbstractPublicApplicationServlet;
import com.helger.photon.core.servlet.AbstractSecureApplicationServlet;
import com.helger.photon.uicore.EUICoreCSSPathProvider;
import com.helger.photon.uicore.EUICoreJSPathProvider;
import com.helger.photon.uicore.icon.DefaultIcons;
import com.helger.photon.uictrls.autonumeric.AbstractHCAutoNumeric;
import com.helger.photon.uictrls.famfam.EFamFamFlagIcon;
import com.helger.photon.uictrls.famfam.EFamFamIcon;
import com.helger.scope.ScopeHelper;
import com.helger.web.scope.mgr.WebScopeManager;
import com.helger.xservlet.requesttrack.RequestTrackerSettings;

/**
 * This class triggers some default configuration to run ph-oton applications
 * more easy.
 *
 * @author Philip Helger
 */
@Immutable
public final class PhotonStubInitializer
{
  private PhotonStubInitializer ()
  {}

  public static void registerDefaultResources ()
  {
    // CSS
    for (final ICSSPathProvider aPP : BootstrapCustomConfig.getAllBootstrapCSS ())
      PhotonCSS.registerCSSIncludeForGlobal (aPP);
    EFamFamIcon.registerResourcesForGlobal ();
    EFamFamFlagIcon.registerResourcesForGlobal ();
    PhotonCSS.registerCSSIncludeForGlobal (EUICoreCSSPathProvider.UICORE);

    // JS
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.JQUERY_3);
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.UICORE_JQUERY);
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.UICORE_FORM);
    for (final IJSPathProvider aPP : BootstrapCustomConfig.getAllBootstrapJS ())
      PhotonJS.registerJSIncludeForGlobal (aPP);

    // Meta elements
    PhotonMetaElements.registerMetaElementForGlobal (MetaElement.createMetaCharset (CHTMLCharset.CHARSET_HTML_OBJ));
    PhotonMetaElements.registerMetaElementForGlobal (EStandardMetaElement.GENERATOR.getAsMetaElement ("ph-oton stack - https://github.com/phax/ph-oton"));
    // Source: https://getbootstrap.com/docs/4.6/getting-started/introduction/
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
      RequestTrackerSettings.setLongRunningRequestsCheckEnabled (false);
      RequestTrackerSettings.setParallelRunningRequestsCheckEnabled (false);
    }

    // Set new customizer only if the default customizer is present
    if (HCConversionSettings.isDefaultCustomizer (HCSettings.getConversionSettings ().getCustomizer ()))
    {
      // Special Bootstrap customizer
      HCSettings.getMutableConversionSettings ()
                .setCustomizer (new HCCustomizerList (new HCCustomizerAutoFocusFirstCtrl (),
                                                      new HCCustomizerExternalizeAttrJS ()));
    }

    // Set default icon set if none is defined
    if (!DefaultIcons.areDefined ())
      EFamFamIcon.setAsDefault ();

    // Never use a thousand separator in HCAutoNumeric fields because of
    // parsing problems
    AbstractHCAutoNumeric.setDefaultThousandSeparator ("");

    // Add default mapping from Application ID to path
    if (!PhotonGlobalState.containsAnyApplicationServletPathMapping ())
    {
      PhotonGlobalState.state (CApplicationID.APP_ID_PUBLIC)
                       .setServletPath (AbstractPublicApplicationServlet.SERVLET_DEFAULT_PATH);
      PhotonGlobalState.state (CApplicationID.APP_ID_SECURE)
                       .setServletPath (AbstractSecureApplicationServlet.SERVLET_DEFAULT_PATH);
    }
    if (!PhotonGlobalState.getInstance ().hasDefaultApplicationID ())
      PhotonGlobalState.getInstance ().setDefaultApplicationID (CApplicationID.APP_ID_PUBLIC);
  }
}
