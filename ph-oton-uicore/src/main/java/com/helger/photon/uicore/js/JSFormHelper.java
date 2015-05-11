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

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.conversion.HCSettings;
import com.helger.html.js.builder.IJSExpression;
import com.helger.html.js.builder.JSArray;
import com.helger.html.js.builder.JSAssocArray;
import com.helger.html.js.builder.JSExpr;
import com.helger.html.js.builder.JSInvocation;
import com.helger.html.js.builder.JSRef;
import com.helger.photon.core.ajax.IAjaxFunctionDeclaration;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.uicore.EUICoreJSPathProvider;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;

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

  @Nonnull
  public static JSRef getFormHelper ()
  {
    return JSExpr.ref ("FormHelper");
  }

  @Nonnull
  public static JSInvocation getAllFormValues (@Nonnull @Nonempty final String sFormID,
                                               @Nonnull @Nonempty final String sFieldPrefix)
  {
    return getFormHelper ().invoke ("getAllFormValues").arg (sFormID).arg (sFieldPrefix);
  }

  @Nonnull
  public static JSInvocation setAllFormValues (@Nonnull @Nonempty final String sFormID,
                                               @Nonnull final JSAssocArray aValues)
  {
    return getFormHelper ().invoke ("setAllFormValues").arg (sFormID).arg (aValues);
  }

  @Nonnull
  public static JSInvocation updateElementDirect (@Nonnull @Nonempty final String sFieldID,
                                                  @Nonnull final IHCNode aHCNode)
  {
    return updateElementDirect (sFieldID, HCSettings.getAsHTMLStringWithoutNamespaces (aHCNode));
  }

  @Nonnull
  public static JSInvocation updateElementDirect (@Nonnull @Nonempty final String sFieldID,
                                                  @Nonnull final String sHTMLCode)
  {
    return getFormHelper ().invoke ("updateElementDirect").arg (sFieldID).arg (sHTMLCode);
  }

  @Nonnull
  public static JSInvocation updateElementViaAjax (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                   @Nonnull @Nonempty final String sFieldID,
                                                   @Nonnull final IAjaxFunctionDeclaration aUpdateCallURL)
  {
    return updateElementViaAjax (sFieldID, aUpdateCallURL.getInvocationURI (aRequestScope));
  }

  @Nonnull
  public static JSInvocation updateElementViaAjax (@Nonnull @Nonempty final String sFieldID,
                                                   @Nonnull final ISimpleURL aUpdateCallURL)
  {
    return updateElementViaAjax (sFieldID, aUpdateCallURL.getAsString ());
  }

  @Nonnull
  public static JSInvocation updateElementViaAjax (@Nonnull @Nonempty final String sFieldID,
                                                   @Nonnull final String sUpdateCallURI)
  {
    return getFormHelper ().invoke ("updateElementViaAjax").arg (sFieldID).arg (sUpdateCallURI);
  }

  // missing updateElements

  @Nonnull
  public static JSAssocArray createUpdateParam (@Nonnull @Nonempty final String sFieldID, @Nonnull final IHCNode aHCNode)
  {
    return new JSAssocArray ().add ("id", sFieldID).add ("html", HCSettings.getAsHTMLStringWithoutNamespaces (aHCNode));
  }

  @Nonnull
  public static JSAssocArray createUpdateParam (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                @Nonnull @Nonempty final String sFieldID,
                                                @Nonnull final IAjaxFunctionDeclaration aUpdateCallURL)
  {
    return new JSAssocArray ().add ("id", sFieldID).add ("url", aUpdateCallURL.getInvocationURI (aRequestScope));
  }

  @Nonnull
  public static JSInvocation saveFormData (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                           @Nonnull @Nonempty final String sFormID,
                                           @Nonnull @Nonempty final String sFieldPrefix,
                                           @Nonnull @Nonempty final String sPageID,
                                           @Nonnull final IAjaxFunctionDeclaration aSaveCallURL,
                                           @Nonnull final JSArray aSuccessUpdates,
                                           @Nonnull final JSArray aErrorUpdates)
  {
    return getFormHelper ().invoke ("saveFormData")
                           .arg (sFormID)
                           .arg (sFieldPrefix)
                           .arg (sPageID)
                           .arg (aSaveCallURL.getInvocationURI (aRequestScope))
                           .arg (aSuccessUpdates)
                           .arg (aErrorUpdates);
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
  @Nonnull
  public static JSInvocation setSelectOptions (@Nonnull final IJSExpression aSelector,
                                               @Nonnull final IJSExpression aValueList)
  {
    return getFormHelper ().invoke ("setSelectOptions").arg (aSelector).arg (aValueList);
  }
}
