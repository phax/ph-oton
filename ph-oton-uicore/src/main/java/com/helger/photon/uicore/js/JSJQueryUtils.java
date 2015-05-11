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
package com.helger.photon.uicore.js;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.html.js.builder.IJSExpression;
import com.helger.html.js.builder.JSAnonymousFunction;
import com.helger.html.js.builder.JSExpr;
import com.helger.html.js.builder.JSVar;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.uicore.EUICoreJSPathProvider;

/**
 * Java JavaScript wrapper for uicore-jquery.js
 *
 * @author Philip Helger
 */
@Immutable
public final class JSJQueryUtils
{
  private JSJQueryUtils ()
  {}

  public static void registerExternalResources ()
  {
    PhotonJS.registerJSIncludeForThisRequest (EUICoreJSPathProvider.UICORE_JQUERY);
  }

  /**
   * Create a JS anonymous function that can be used as a callback to the
   * jQuery.ajax success callback.
   *
   * @param aHandler
   *        The JS expression that must resolve to a JS function that takes 3
   *        arguments. See jQuery.ajax success callback for details. Note: this
   *        should not be in an invocation but an invokable!
   * @param bInvokeHandlerFirst
   *        <code>true</code> to invoke the handler before the inclusions take
   *        place, <code>false</code> to invoke the handler after the inclusions
   *        took place.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static JSAnonymousFunction jqueryAjaxSuccessHandler (@Nonnull final IJSExpression aHandler,
                                                              final boolean bInvokeHandlerFirst)
  {
    final JSAnonymousFunction ret = new JSAnonymousFunction ();
    final JSVar aData = ret.param ("a");
    final JSVar aTextStatus = ret.param ("b");
    final JSVar aXHR = ret.param ("c");

    ret.body ()
       .invoke ("jqph", "jqueryAjaxSuccessHandler")
       .arg (aData)
       .arg (aTextStatus)
       .arg (aXHR)
       .arg (bInvokeHandlerFirst ? aHandler : JSExpr.NULL)
       .arg (bInvokeHandlerFirst ? JSExpr.NULL : aHandler);
    return ret;
  }
}
