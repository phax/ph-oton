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

import org.jspecify.annotations.NonNull;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.Immutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.render.HCRenderer;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSInvocation;
import com.helger.html.jscode.JSRef;
import com.helger.photon.ajax.decl.IAjaxFunctionDeclaration;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.uicore.EUICoreJSPathProvider;
import com.helger.url.ISimpleURL;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Java JavaScript wrapper for form.js
 *
 * @author Philip Helger
 */
@Immutable
public final class JSFormHelper
{
  private JSFormHelper ()
  {}

  public static void registerExternalResources ()
  {
    PhotonJS.registerJSIncludeForThisRequest (EUICoreJSPathProvider.UICORE_FORM);
  }

  @NonNull
  public static JSRef getFormHelper ()
  {
    return JSExpr.ref ("FormHelper");
  }

  @NonNull
  public static JSInvocation updateElementDirect (@NonNull @Nonempty final String sFieldID, @NonNull final IHCNode aHCNode)
  {
    return updateElementDirect (sFieldID, HCRenderer.getAsHTMLStringWithoutNamespaces (aHCNode));
  }

  @NonNull
  public static JSInvocation updateElementDirect (@NonNull @Nonempty final String sFieldID, @NonNull final String sHTMLCode)
  {
    return getFormHelper ().invoke ("updateElementDirect").arg (sFieldID).arg (sHTMLCode);
  }

  @NonNull
  public static JSInvocation updateElementViaAjax (@NonNull final IRequestWebScopeWithoutResponse aRequestScope,
                                                   @NonNull @Nonempty final String sFieldID,
                                                   @NonNull final IAjaxFunctionDeclaration aUpdateCallURL)
  {
    return updateElementViaAjax (sFieldID, aUpdateCallURL.getInvocationURI (aRequestScope));
  }

  @NonNull
  public static JSInvocation updateElementViaAjax (@NonNull @Nonempty final String sFieldID, @NonNull final ISimpleURL aUpdateCallURL)
  {
    return updateElementViaAjax (sFieldID, aUpdateCallURL.getAsString ());
  }

  @NonNull
  public static JSInvocation updateElementViaAjax (@NonNull @Nonempty final String sFieldID, @NonNull final String sUpdateCallURI)
  {
    return getFormHelper ().invoke ("updateElementViaAjax").arg (sFieldID).arg (sUpdateCallURI);
  }

  // missing updateElements

  @NonNull
  public static JSAssocArray createUpdateParam (@NonNull @Nonempty final String sFieldID, @NonNull final IHCNode aHCNode)
  {
    return new JSAssocArray ().add ("id", sFieldID).add ("html", HCRenderer.getAsHTMLStringWithoutNamespaces (aHCNode));
  }

  @NonNull
  public static JSAssocArray createUpdateParam (@NonNull @Nonempty final String sFieldID, @NonNull final ISimpleURL aURL)
  {
    return new JSAssocArray ().add ("id", sFieldID).add ("url", aURL.getAsString ());
  }

  /**
   * Set all options of a &lt;select&gt;
   *
   * @param aSelector
   *        jQuery object
   * @param aValueList
   *        list of array[value,text] - nested array!
   * @return the invocation
   */
  @NonNull
  public static JSInvocation setSelectOptions (@NonNull final IJSExpression aSelector, @NonNull final IJSExpression aValueList)
  {
    return getFormHelper ().invoke ("setSelectOptions").arg (aSelector).arg (aValueList);
  }
}
