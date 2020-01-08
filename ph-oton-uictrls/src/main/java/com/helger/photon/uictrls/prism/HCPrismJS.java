/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.prism;

import java.util.EnumSet;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.ValueEnforcer;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.ext.HCHasCSSClasses;
import com.helger.html.hc.html.grouping.AbstractHCPre;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;
import com.helger.xml.microdom.IMicroElement;

/**
 * prism.js pre + code element.
 *
 * @author Philip Helger
 */
public class HCPrismJS extends AbstractHCPre <HCPrismJS>
{
  public static final ICSSClassProvider CSS_CLASS_PRISMJS = DefaultCSSClassProvider.create ("prismjs");

  private final EPrismLanguage m_eLanguage;
  private final EnumSet <EPrismPlugin> m_aPlugins = EnumSet.noneOf (EPrismPlugin.class);

  public HCPrismJS (@Nonnull final EPrismLanguage eLanguage)
  {
    m_eLanguage = ValueEnforcer.notNull (eLanguage, "Language");
  }

  /**
   * @return The language as specified in the constructor
   */
  @Nonnull
  public final EPrismLanguage getPrismLanguage ()
  {
    return m_eLanguage;
  }

  @Nonnull
  public final HCPrismJS addPlugin (@Nonnull final EPrismPlugin ePlugin)
  {
    ValueEnforcer.notNull (ePlugin, "Plugin");
    m_aPlugins.add (ePlugin);
    return this;
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void fillMicroElement (@Nonnull final IMicroElement aElement,
                                   @Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // Plugins must go on pre
    final HCHasCSSClasses p = new HCHasCSSClasses ();
    p.addClass (CSS_CLASS_PRISMJS);
    p.addClass (m_eLanguage);
    p.addClasses (m_aPlugins);
    aElement.setAttribute (CHTMLAttributes.CLASS, p.getAllClassesAsString ());

    // Create nested code tag
    final IMicroElement eCode = aElement.appendElement (aElement.getNamespaceURI (),
                                                        EHTMLElement.CODE.getElementName ());
    super.fillMicroElement (eCode, aConversionSettings);
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForcedRegistration)
  {
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.PRISMJS);
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.PRISMJS);
  }
}
