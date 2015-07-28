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

import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nonnull;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.bridge.SLF4JBridgeHandler;

import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.scope.ScopeHelper;
import com.helger.commons.system.SystemProperties;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.customize.HCDefaultCustomizer;
import com.helger.html.hc.customize.HCMultiCustomizer;
import com.helger.html.meta.EStandardMetaElement;
import com.helger.photon.bootstrap3.EBootstrapCSSPathProvider;
import com.helger.photon.bootstrap3.EBootstrapJSPathProvider;
import com.helger.photon.bootstrap3.servlet.BootstrapCustomizer;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.core.app.html.PhotonMetaElements;
import com.helger.photon.core.requesttrack.RequestTracker;
import com.helger.photon.uicore.EUICoreCSSPathProvider;
import com.helger.photon.uicore.EUICoreJSPathProvider;
import com.helger.photon.uicore.icon.DefaultIcons;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.autonumeric.AbstractHCAutoNumeric;
import com.helger.photon.uictrls.famfam.EFamFamIcon;
import com.helger.web.scope.mgr.ThrowingScopeFactory;
import com.helger.web.scope.mgr.WebScopeManager;
import com.helger.web.servlet.response.UnifiedResponseDefaultSettings;

/**
 * This class triggers some default configuration to run ph-oton applications
 * more easy.
 *
 * @author Philip Helger
 */
public final class PhotonStubConfigurationListener implements ServletContextListener
{
  private static final AtomicBoolean s_aInitialized = new AtomicBoolean (false);

  public static boolean isInitialized ()
  {
    return s_aInitialized.get ();
  }

  public static void registerDefaultResources ()
  {
    // CSS
    PhotonCSS.registerCSSIncludeForGlobal (EBootstrapCSSPathProvider.BOOTSTRAP_335);
    PhotonCSS.registerCSSIncludeForGlobal (EBootstrapCSSPathProvider.BOOTSTRAP_THEME_335);
    PhotonCSS.registerCSSIncludeForGlobal (EBootstrapCSSPathProvider.BOOTSTRAP_PH);
    PhotonCSS.registerCSSIncludeForGlobal (EUICtrlsCSSPathProvider.FAMFAM_ICONS);
    PhotonCSS.registerCSSIncludeForGlobal (EUICtrlsCSSPathProvider.FAMFAM_FLAGS);
    PhotonCSS.registerCSSIncludeForGlobal (EUICoreCSSPathProvider.UICORE);

    // With conditional comments
    PhotonCSS.registerCSSIncludeForGlobal (EBootstrapCSSPathProvider.BOOTSTRAP_IE9);
    PhotonCSS.registerCSSIncludeForGlobal (EUICoreCSSPathProvider.JQUERY_PLACEHOLDER);

    // JS
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.JQUERY_1);
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.UICORE_JQUERY);
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.UICORE_FORM);
    PhotonJS.registerJSIncludeForGlobal (EBootstrapJSPathProvider.BOOTSTRAP_335);
    PhotonJS.registerJSIncludeForGlobal (EBootstrapJSPathProvider.BOOTSTRAP_PH);

    // With conditional comments
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.HTML5SHIV_3_7_2);
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.RESPOND);
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.JQUERY_PLACEHOLDER);
    PhotonJS.registerJSIncludeForGlobal (EUICoreJSPathProvider.JQUERY_PLACEHOLDER_ALL);

    // Meta elements
    PhotonMetaElements.registerMetaElementForGlobal (EStandardMetaElement.GENERATOR.getAsMetaElement ("ph-oton stack - https://github.com/phax/ph-oton"));
    PhotonMetaElements.registerMetaElementForGlobal (EStandardMetaElement.X_UA_COMPATIBLE.getAsMetaElement ("IE=Edge,chrome=1"));
    PhotonMetaElements.registerMetaElementForGlobal (EStandardMetaElement.VIEWPORT.getAsMetaElement ("width=device-width, initial-scale=1.0"));

    // Finally read stuff from files that may be defined within the application
    PhotonCSS.readCSSIncludesForGlobal (new ClassPathResource (PhotonCSS.DEFAULT_FILENAME));
    PhotonJS.readJSIncludesForGlobal (new ClassPathResource (PhotonJS.DEFAULT_FILENAME));
    PhotonMetaElements.readMetaElementsForGlobal (new ClassPathResource (PhotonMetaElements.DEFAULT_FILENAME));
  }

  public static void onContextInitialized ()
  {
    if (s_aInitialized.compareAndSet (false, true))
    {
      // Logging: JUL to SLF4J
      SLF4JBridgeHandler.removeHandlersForRootLogger ();
      SLF4JBridgeHandler.install ();

      // Default JS and CSS
      registerDefaultResources ();

      // Ensure that only web scopes are created and never non-web scopes
      ThrowingScopeFactory.installToMetaScopeFactory ();

      if (GlobalDebug.isDebugMode ())
      {
        if (false)
          ScopeHelper.setDebugSessionScopeEnabled (true);

        // Enable Java Serialization debug
        SystemProperties.setPropertyValue ("sun.io.serialization.extendedDebugInfo", "true");

        // Not production ready yet
        WebScopeManager.setSessionPassivationAllowed (true);

        // Disable in debug mode
        RequestTracker.getInstance ().getRequestTrackingMgr ().setLongRunningCheckEnabled (false);
      }

      // Default response headers to be added
      UnifiedResponseDefaultSettings.setAllowMimeSniffing (false);
      UnifiedResponseDefaultSettings.setEnableXSSFilter (true);

      // Always use HTML5 for Bootstrap3
      HCSettings.setDefaultHTMLVersion (EHTMLVersion.HTML5);

      // Special Bootstrap customizer
      // Default customizer: disable CSS classes - should fix issue with
      // checkbox in form in IE9
      HCSettings.getMutableConversionSettings ()
                .setCustomizer (new HCMultiCustomizer (new HCDefaultCustomizer (), new BootstrapCustomizer ()));

      // Set default icon set if none is defined
      if (!DefaultIcons.areDefined ())
        EFamFamIcon.setAsDefault ();

      // Never use a thousand separator in HCAutoNumeric fields because of
      // parsing problems
      AbstractHCAutoNumeric.setDefaultThousandSeparator ("");
    }
  }

  public void contextInitialized (@Nonnull final ServletContextEvent aSCE)
  {
    onContextInitialized ();
  }

  public void contextDestroyed (@Nonnull final ServletContextEvent aSCE)
  {}
}
