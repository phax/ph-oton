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
package com.helger.photon.bootstrap3.servlet;

import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.html.EHTMLVersion;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.conversion.HCConversionSettings;
import com.helger.html.hc.customize.HCMultiCustomizer;
import com.helger.photon.bootstrap3.EBootstrapIcon;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.core.app.html.PhotonHTMLSettings;
import com.helger.photon.core.servlet.AbstractWebAppListenerMultiApp;

/**
 * Bootstrap specific initialization listener
 *
 * @author Philip Helger
 * @param <LECTYPE>
 *        Layout execution context class
 */
public abstract class AbstractWebAppListenerMultiAppBootstrap <LECTYPE extends ILayoutExecutionContext> extends AbstractWebAppListenerMultiApp <LECTYPE>
{
  @Override
  @OverridingMethodsMustInvokeSuper
  protected void initGlobals ()
  {
    super.initGlobals ();

    // UI stuff:

    // Always use HTML5 for Bootstrap3
    PhotonHTMLSettings.setDefaultHTMLVersion (EHTMLVersion.HTML5);

    // Add special Bootstrap customizer
    HCSettings.getMutableConversionSettings ()
              .setCustomizer (new HCMultiCustomizer (HCConversionSettings.createDefaultCustomizer (),
                                                     new BootstrapCustomizer ()));

    // Using Bootstrap icon set by default
    EBootstrapIcon.setAsDefault ();
  }
}
