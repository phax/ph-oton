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
package com.helger.photon.uicore.facebook;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.html.HCDiv;
import com.helger.html.hc.html.HCScriptInline;
import com.helger.html.hc.html.HCScriptInlineOnDocumentReady;
import com.helger.html.hc.impl.AbstractHCNodeList;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSInvocation;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.uicore.EUICoreJSPathProvider;

public class HCFacebookSDK extends AbstractHCNodeList <HCFacebookSDK>
{
  private static final String FB_ROOT_ID = "fb-root";

  public HCFacebookSDK (@Nonnull @Nonempty final String sAppID,
                        @Nonnull final Locale aDisplayLocale,
                        final boolean bCheckLoginStatus,
                        final boolean bEnableCookies,
                        final boolean bUseXFBML)
  {
    addChild (new HCDiv ().setID (FB_ROOT_ID));
    final String sLocale = FacebookLocaleMapping.getInstance ().getFBLocale (aDisplayLocale).toString ();
    if (true)
    {
      addChild (new HCScriptInlineOnDocumentReady (JSExpr.invoke ("facebookLoadSDKjQuery")
                                                         .arg (sAppID)
                                                         .arg (sLocale)
                                                         .arg (FB_ROOT_ID)
                                                         .arg (bCheckLoginStatus)
                                                         .arg (bEnableCookies)
                                                         .arg (bUseXFBML)));
    }
    else
    {
      addChild (new HCScriptInline (new JSInvocation ("facebookLoadSDKAsync").arg (sAppID)
                                                                             .arg (sLocale)
                                                                             .arg (FB_ROOT_ID)
                                                                             .arg (bCheckLoginStatus)
                                                                             .arg (bEnableCookies)
                                                                             .arg (bUseXFBML)));
    }
  }

  @Override
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForcedRegistration)
  {
    PhotonJS.registerJSIncludeForThisRequest (EUICoreJSPathProvider.FACEBOOK);
  }
}
