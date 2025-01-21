/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.url.ISimpleURL;
import com.helger.css.writer.CSSWriterSettings;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.ext.HCHasCSSClasses;
import com.helger.html.hc.ext.HCHasCSSStyles;
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
  /** An internal class to easily find all matching elements */
  public static final ICSSClassProvider CSS_CLASS_PRISMJS = DefaultCSSClassProvider.create ("prismjs");

  private ISimpleURL m_aSrc;
  private final EPrismLanguage m_eLanguage;
  private final ICommonsList <IPrismPlugin> m_aPlugins = new CommonsArrayList <> ();

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

  @Nullable
  public final ISimpleURL getSrc ()
  {
    return m_aSrc;
  }

  @Nullable
  public final HCPrismJS setSrc (@Nullable final ISimpleURL a)
  {
    m_aSrc = a;
    return this;
  }

  @Nonnull
  public final HCPrismJS addPlugin (@Nonnull final IPrismPlugin aPlugin)
  {
    ValueEnforcer.notNull (aPlugin, "Plugin");
    m_aPlugins.add (aPlugin);
    return this;
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void fillMicroElement (@Nonnull final IMicroElement aPreElement, @Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // Apply plugin on pre element
    {
      final HCHasCSSClasses aPreClasses = new HCHasCSSClasses ();
      aPreClasses.addClass (CSS_CLASS_PRISMJS);
      final HCHasCSSStyles aPreStyles = new HCHasCSSStyles ();

      for (final IPrismPlugin aPlugin : m_aPlugins)
        aPlugin.applyOnPre (aPreElement, aPreClasses, aPreStyles);
      if (aPreClasses.hasAnyClass ())
        aPreElement.setAttribute (CHTMLAttributes.CLASS, aPreClasses.getAllClassesAsString ());
      if (aPreStyles.hasAnyStyle ())
        aPreElement.setAttribute (CHTMLAttributes.STYLE, aPreStyles.getAllStylesAsString (new CSSWriterSettings ()));
    }

    if (m_aSrc != null)
    {
      // External source provided
      aPreElement.setAttribute ("data-src", m_aSrc.getAsStringWithEncodedParameters ());

      // NO nested code element
      super.fillMicroElement (aPreElement, aConversionSettings);
    }
    else
    {
      // Create nested code tag
      final IMicroElement eCode = aPreElement.appendElement (aPreElement.getNamespaceURI (), EHTMLElement.CODE.getElementName ());

      super.fillMicroElement (eCode, aConversionSettings);

      // Apply code classes
      {
        final HCHasCSSClasses aCodeClasses = new HCHasCSSClasses ();
        // Language must go on code
        aCodeClasses.addClass (m_eLanguage);
        eCode.setAttribute (CHTMLAttributes.CLASS, aCodeClasses.getAllClassesAsString ());
      }
    }
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForcedRegistration)
  {
    registerExternalResources (m_aPlugins);
  }

  public static void registerExternalResources (@Nullable final Iterable <? extends IPrismPlugin> aPlugins)
  {
    if (aPlugins != null)
      for (final IPrismPlugin aPlugin : aPlugins)
        aPlugin.registerExternalResourcesBeforePrism ();

    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.PRISMJS);
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.PRISMJS);

    if (aPlugins != null)
      for (final IPrismPlugin aPlugin : aPlugins)
        aPlugin.registerExternalResourcesAfterPrism ();
  }
}
