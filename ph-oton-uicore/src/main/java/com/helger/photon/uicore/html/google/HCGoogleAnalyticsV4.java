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

import org.jspecify.annotations.NonNull;

import com.helger.annotation.Nonempty;
import com.helger.annotation.OverridingMethodsMustInvokeSuper;
import com.helger.annotation.style.OverrideOnDemand;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.html.annotation.OutOfBandNode;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.script.AbstractHCScriptInline;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSFunction;
import com.helger.html.jscode.JSPackage;
import com.helger.html.jscode.html.JSHtml;
import com.helger.html.jscode.type.JSTypeDate;
import com.helger.html.resource.js.ConstantJSPathProvider;
import com.helger.photon.app.html.PhotonJS;
import com.helger.url.SimpleURL;

/**
 * Control for emitting Google Analytics V4 code.
 *
 * @author Philip Helger
 * @since 8.4.1
 */
@OutOfBandNode
public class HCGoogleAnalyticsV4 extends AbstractHCScriptInline <HCGoogleAnalyticsV4>
{
  public static final String URL_GTAG = "https://www.googletagmanager.com/gtag/js";

  private final String m_sTagID;

  @NonNull
  private static JSPackage _createJSCode (@NonNull @Nonempty final String sTagID)
  {
    final JSPackage aPkg = new JSPackage ();
    aPkg.assign (JSHtml.window ().ref ("dataLayer"), JSHtml.window ().ref ("dataLayer").cor (new JSArray ()));

    final JSFunction jsFunc = aPkg.function ("gtag");
    jsFunc.body ().add (JSExpr.ref ("dataLayer").invoke ("push").args (JSExpr.ref ("arguments")));

    aPkg.invoke (jsFunc).arg ("js").arg (new JSTypeDate ()._new ());
    aPkg.invoke (jsFunc).arg ("config").arg (sTagID);

    return aPkg;
  }

  public HCGoogleAnalyticsV4 (@NonNull @Nonempty final String sTagID)
  {
    ValueEnforcer.notEmpty (sTagID, "TagID");
    m_sTagID = sTagID;
  }

  @NonNull
  @Nonempty
  public String getTagID ()
  {
    return m_sTagID;
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void onFinalizeNodeState (@NonNull final IHCConversionSettingsToNode aConversionSettings,
                                      @NonNull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    setJSCodeProvider (_createJSCode (m_sTagID));
  }

  @Override
  protected void onRegisterExternalResources (@NonNull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForceRegistration)
  {
    super.onRegisterExternalResources (aConversionSettings, bForceRegistration);
    final String sURI = new SimpleURL (URL_GTAG).add ("id",
     m_sTagID).getAsString ();
    PhotonJS.registerJSIncludeForThisRequest (ConstantJSPathProvider.builder ().path (sURI).minifiedPath (sURI).bundlable (false).build ());
  }
}
