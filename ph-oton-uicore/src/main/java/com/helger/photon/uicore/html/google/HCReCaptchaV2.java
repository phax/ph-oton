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
package com.helger.photon.uicore.html.google;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.url.SimpleURL;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.html.resource.js.ConstantJSPathProvider;
import com.helger.photon.app.html.PhotonJS;

/**
 * Handle Google reCAPTCHA according to
 * https://developers.google.com/recaptcha/docs/display
 *
 * <pre>
 * &lt;div class="g-recaptcha" data-sitekey="your_site_key"&gt;&lt;/div&gt;
 * </pre>
 *
 * @author Philip Helger
 */
@Deprecated (forRemoval = true, since = "9.2.8")
public class HCReCaptchaV2 extends AbstractHCDiv <HCReCaptchaV2>
{
  public static final ICSSClassProvider CSS_G_RECAPTCHA = DefaultCSSClassProvider.create ("g-recaptcha");
  public static final String RESPONSE_PARAMETER_NAME = "g-recaptcha-response";

  private final String m_sDisplayLanguage;

  public HCReCaptchaV2 (@Nonnull @Nonempty final String sSiteKey, @Nullable final String sDisplayLanguage)
  {
    ValueEnforcer.notEmpty (sSiteKey, "SiteKey");

    addClass (CSS_G_RECAPTCHA);
    customAttrs ().setDataAttr ("sitekey", sSiteKey);
    m_sDisplayLanguage = sDisplayLanguage;
  }

  /**
   * @return The display language as passed in the constructor. May be
   *         <code>null</code>.
   */
  @Nullable
  public final String getDisplayLanguage ()
  {
    return m_sDisplayLanguage;
  }

  @Override
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForceRegistration)
  {
    super.onRegisterExternalResources (aConversionSettings, bForceRegistration);

    final SimpleURL aURL = new SimpleURL ("https://www.google.com/recaptcha/api.js");
    if (m_sDisplayLanguage != null)
      aURL.add ("hl", m_sDisplayLanguage);
    final String sURI = aURL.getAsStringWithEncodedParameters ();
    PhotonJS.registerJSIncludeForThisRequest (ConstantJSPathProvider.builder ().path (sURI).minifiedPath (sURI).bundlable (false).build ());
  }

  @Nonnull
  public static HCReCaptchaV2 create (@Nonnull @Nonempty final String sSiteKey, @Nullable final Locale aDisplayLocale)
  {
    return new HCReCaptchaV2 (sSiteKey, aDisplayLocale == null ? null : aDisplayLocale.getLanguage ());
  }
}
