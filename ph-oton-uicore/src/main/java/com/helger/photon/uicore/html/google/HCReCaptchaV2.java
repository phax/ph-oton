/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.url.SimpleURL;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.html.resource.js.ConstantJSPathProvider;
import com.helger.photon.core.app.html.PhotonJS;

/**
 * Handle Google reCAPTCHA according to
 * https://developers.google.com/recaptcha/docs/display
 *
 * <pre>
 * <div class="g-recaptcha" data-sitekey="your_site_key"></div>
 * </pre>
 *
 * @author Philip Helger
 */
public class HCReCaptchaV2 extends AbstractHCDiv <HCReCaptchaV2>
{
  public static final ICSSClassProvider CSS_G_RECAPTCHA = DefaultCSSClassProvider.create ("g-recaptcha");
  private final String m_sDisplayLanguage;

  public HCReCaptchaV2 (@Nonnull @Nonempty final String sSiteKey, @Nullable final String sDisplayLanguage)
  {
    ValueEnforcer.notEmpty (sSiteKey, "SiteKey");

    addClass (CSS_G_RECAPTCHA);
    setDataAttr ("sitekey", sSiteKey);
    m_sDisplayLanguage = sDisplayLanguage;
  }

  @Override
  public boolean canConvertToMicroNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // Render nodes only in production mode
    // FIXME dev mode captcha!
    return true || GlobalDebug.isProductionMode ();
  }

  @Override
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForceRegistration)
  {
    super.onRegisterExternalResources (aConversionSettings, bForceRegistration);

    final SimpleURL aURL = new SimpleURL ("https://www.google.com/recaptcha/api.js");
    if (m_sDisplayLanguage != null)
      aURL.add ("hl", m_sDisplayLanguage);
    PhotonJS.registerJSIncludeForThisRequest (ConstantJSPathProvider.createExternal (aURL.getAsStringWithEncodedParameters ()));
  }

  @Nonnull
  public static HCReCaptchaV2 create (@Nonnull @Nonempty final String sSiteKey, @Nullable final Locale aDisplayLocale)
  {
    final HCReCaptchaV2 ret = new HCReCaptchaV2 (sSiteKey,
                                                 aDisplayLocale == null ? null : aDisplayLocale.getLanguage ());
    return ret;
  }
}
