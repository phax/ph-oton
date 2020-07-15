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
package com.helger.photon.uicore.html.google;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.css.property.CCSSProperties;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.forms.AbstractHCButton;
import com.helger.html.hc.html.forms.IHCForm;
import com.helger.html.hc.html.script.HCScriptInline;
import com.helger.html.jscode.JSFunction;
import com.helger.html.jscode.html.JSHtml;
import com.helger.html.resource.js.ConstantJSPathProvider;
import com.helger.photon.app.html.PhotonJS;

/**
 * Handle Google reCAPTCHA according to
 * https://developers.google.com/recaptcha/docs/invisible
 *
 * <pre>
 * &lt;button class="g-recaptcha" data-sitekey="your_site_key" data-callback=
'onSubmit'&gt;Submit&lt;/button&gt;
 * </pre>
 *
 * @author Philip Helger
 */
public class HCReCaptchaInvisible extends AbstractHCButton <HCReCaptchaInvisible>
{
  public static final ICSSClassProvider CSS_G_RECAPTCHA = DefaultCSSClassProvider.create ("g-recaptcha");

  public HCReCaptchaInvisible (@Nonnull @Nonempty final String sSiteKey, @Nonnull final String sProgressCallbackFn)
  {
    ValueEnforcer.notEmpty (sSiteKey, "SiteKey");
    ValueEnforcer.notEmpty (sProgressCallbackFn, "ProgressCallbackFn");

    addClass (CSS_G_RECAPTCHA);
    customAttrs ().setDataAttr ("sitekey", sSiteKey);
    customAttrs ().setDataAttr ("callback", sProgressCallbackFn);
    addStyle (CCSSProperties.DISPLAY_NONE);
  }

  @Override
  public boolean canConvertToMicroNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // Render nodes only in production mode
    return GlobalDebug.isProductionMode ();
  }

  @Override
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForceRegistration)
  {
    super.onRegisterExternalResources (aConversionSettings, bForceRegistration);
    PhotonJS.registerJSIncludeForThisRequest (ConstantJSPathProvider.createExternal ("https://www.google.com/recaptcha/api.js"));
  }

  @Nonnull
  public static HCReCaptchaInvisible createForFormSubmit (@Nonnull @Nonempty final String sSiteKey, @Nonnull final IHCForm <?> aForm)
  {
    final String sFuncName = "jsrc" + GlobalIDFactory.getNewIntID ();
    final HCReCaptchaInvisible ret = new HCReCaptchaInvisible (sSiteKey, sFuncName);

    final JSFunction aFunc = new JSFunction (sFuncName);
    aFunc.body ().add (JSHtml.documentGetElementById (aForm.ensureID ().getID ()).invoke ("submit"));
    ret.addChild (new HCScriptInline (aFunc));
    return ret;
  }
}
