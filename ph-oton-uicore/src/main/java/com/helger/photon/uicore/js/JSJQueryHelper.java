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
package com.helger.photon.uicore.js;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.html.hc.config.HCSettings;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSParam;
import com.helger.photon.app.csrf.CSRFSessionManager;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.uicore.EUICoreJSPathProvider;

/**
 * Java JavaScript wrapper for uicore-jquery.js
 *
 * @author Philip Helger
 */
@Immutable
public final class JSJQueryHelper
{
  private JSJQueryHelper ()
  {}

  public static void registerExternalResources ()
  {
    PhotonJS.registerJSIncludeForThisRequest (EUICoreJSPathProvider.UICORE_JQUERY);
  }

  /**
   * Create a JS anonymous function that can be used as a callback to the jQuery.ajax success
   * callback. Note: this can only be used with extended HTML responses!
   *
   * @param aHandlerBeforeInclude
   *        The JS expression that must resolve to a JS function that takes 3 arguments. See
   *        jQuery.ajax success callback for details. Note: this should not be in an invocation but
   *        an invokable! This handler is invoked BEFORE the inclusions take place.
   * @param aHandlerAfterInclude
   *        The JS expression that must resolve to a JS function that takes 3 arguments. See
   *        jQuery.ajax success callback for details. Note: this should not be in an invocation but
   *        an invokable! This handler is invoked AFTER the inclusions take place.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static JSAnonymousFunction jqueryAjaxSuccessHandler (@Nullable final IJSExpression aHandlerBeforeInclude,
                                                              @Nullable final IJSExpression aHandlerAfterInclude)
  {
    final JSAnonymousFunction ret = new JSAnonymousFunction ();
    final JSParam aData = ret.param ("a");
    final JSParam aTextStatus = ret.param ("b");
    final JSParam aXHR = ret.param ("c");

    // Add special names to data
    {
      final boolean bScriptNonce = HCSettings.isUseNonceInScript ();
      final boolean bStyleNonce = HCSettings.isUseNonceInStyle ();
      if (bScriptNonce || bStyleNonce)
      {
        final String sNonce = CSRFSessionManager.getInstance ().getNonce ();
        // Avoid null data
        ret.body ().assign (aData, aData.cor (new JSAssocArray ()));
        if (bScriptNonce)
          ret.body ().assign (aData.ref ("scriptNonce"), sNonce);
        if (bStyleNonce)
          ret.body ().assign (aData.ref ("styleNonce"), sNonce);
      }
    }

    ret.body ()
       .invoke ("jqph", "jqueryAjaxSuccessHandler")
       .arg (aData)
       .arg (aTextStatus)
       .arg (aXHR)
       .arg (aHandlerBeforeInclude != null ? aHandlerBeforeInclude : JSExpr.NULL)
       .arg (aHandlerAfterInclude != null ? aHandlerAfterInclude : JSExpr.NULL);
    return ret;
  }
}
