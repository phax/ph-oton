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
package com.helger.photon.uicore.html;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.html.annotation.OutOfBandNode;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.script.HCScriptInlineOnDocumentReady;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.html.JSHtml;
import com.helger.html.resource.css.ICSSPathProvider;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.uicore.EUICoreCSSPathProvider;
import com.helger.photon.uicore.EUICoreJSPathProvider;
import com.helger.url.ISimpleURL;

@OutOfBandNode
public class HCCookieConsent extends HCScriptInlineOnDocumentReady
{
  public enum EPosition
  {
    BOTTOM ("bottom"),
    TOP ("top"),
    BOTTOM_LEFT ("bottom-left"),
    BOTTOM_RIGHT ("bottom-right");

    private final String m_sValue;

    EPosition (@NonNull @Nonempty final String sValue)
    {
      m_sValue = sValue;
    }

    @NonNull
    @Nonempty
    public String getValue ()
    {
      return m_sValue;
    }
  }

  public enum ETheme
  {
    CLASSIC ("classic", EUICoreCSSPathProvider.COOKIE_CONSENT_THEMES_CLASSIC),
    EDGELESS ("edgeless", EUICoreCSSPathProvider.COOKIE_CONSENT_THEMES_EDGELESS);

    private final String m_sValue;
    private final ICSSPathProvider m_aCSS;

    ETheme (@NonNull @Nonempty final String sValue, @NonNull final ICSSPathProvider aCSS)
    {
      m_sValue = sValue;
      m_aCSS = aCSS;
    }

    @NonNull
    @Nonempty
    public String getValue ()
    {
      return m_sValue;
    }

    @NonNull
    public ICSSPathProvider getCSSPathProvider ()
    {
      return m_aCSS;
    }
  }

  private final ETheme m_eTheme;

  public HCCookieConsent (@NonNull final JSAssocArray aInitOptions, @Nullable final ETheme eTheme)
  {
    setOnDocumentReadyCode (JSHtml.window ().ref ("cookieconsent").invoke ("initialise").arg (aInitOptions));
    m_eTheme = eTheme;
  }

  @Override
  protected void onRegisterExternalResources (@NonNull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForceRegistration)
  {
    super.onRegisterExternalResources (aConversionSettings, bForceRegistration);

    PhotonJS.registerJSIncludeForThisRequest (EUICoreJSPathProvider.COOKIE_CONSENT);
    PhotonCSS.registerCSSIncludeForThisRequest (EUICoreCSSPathProvider.COOKIE_CONSENT_ANIMATION);
    PhotonCSS.registerCSSIncludeForThisRequest (EUICoreCSSPathProvider.COOKIE_CONSENT_BASE);
    PhotonCSS.registerCSSIncludeForThisRequest (EUICoreCSSPathProvider.COOKIE_CONSENT_LAYOUT);
    PhotonCSS.registerCSSIncludeForThisRequest (EUICoreCSSPathProvider.COOKIE_CONSENT_MEDIA);
    if (m_eTheme != null)
      PhotonCSS.registerCSSIncludeForThisRequest (m_eTheme.getCSSPathProvider ());
  }

  @NonNull
  public static HCCookieConsent createBottomDefault (@NonNull final String sPopupBackgroundColor,
                                                     @Nullable final String sPopupTextColor,
                                                     @NonNull final String sButtonBackgroundColor,
                                                     @Nullable final String sButtonTextColor)
  {
    return create ((EPosition) null,
                   false,
                   ETheme.CLASSIC,
                   sPopupBackgroundColor,
                   sPopupTextColor,
                   sButtonBackgroundColor,
                   sButtonTextColor,
                   (ISimpleURL) null);
  }

  @NonNull
  public static HCCookieConsent create (@Nullable final EPosition ePos,
                                        final boolean bStatic,
                                        @Nullable final ETheme eTheme,
                                        @NonNull final String sPopupBackgroundColor,
                                        @Nullable final String sPopupTextColor,
                                        @Nullable final String sButtonBackgroundColor,
                                        @Nullable final String sButtonTextColor,
                                        @Nullable final ISimpleURL aPolicyLink)
  {
    ValueEnforcer.notEmpty (sPopupBackgroundColor, "PopupBackgroundColor");

    final JSAssocArray aInitOptions = new JSAssocArray ();
    if (ePos != null)
      aInitOptions.add ("position", ePos.getValue ());
    if (bStatic)
      aInitOptions.add ("static", true);
    if (eTheme != null)
      aInitOptions.add ("theme", eTheme.getValue ());
    final JSAssocArray aPalette = new JSAssocArray ();
    aPalette.addIf ("popup",
                    new JSAssocArray ().addIfNotNull ("background", sPopupBackgroundColor).addIfNotNull ("text", sPopupTextColor),
                    JSAssocArray::isNotEmpty);
    aPalette.addIf ("button",
                    new JSAssocArray ().addIfNotNull ("background", sButtonBackgroundColor).addIfNotNull ("text", sButtonTextColor),
                    JSAssocArray::isNotEmpty);
    aInitOptions.add ("palette", aPalette);

    final JSAssocArray aContent = new JSAssocArray ();
    if (aPolicyLink != null)
      aContent.add ("href", aPolicyLink.getAsString ());
    aInitOptions.addIf ("content", aContent, JSAssocArray::isNotEmpty);
    return new HCCookieConsent (aInitOptions, eTheme);
  }
}
