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
package com.helger.photon.core.ajax.response;

import java.nio.charset.StandardCharsets;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.mime.CMimeType;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.config.IHCOnDocumentReadyProvider;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.special.HCSpecialNodes;
import com.helger.html.hc.special.IHCSpecialNodes;
import com.helger.json.IJsonObject;
import com.helger.json.JsonObject;
import com.helger.photon.core.ajax.AjaxResponse.HtmlHelper;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Ajax response with HTML content. The returned Content-Type is JSON and the
 * detailed information are in separate objects. See the public properties.
 *
 * @author Philip Helger
 */
@NotThreadSafe
@Deprecated
public class AjaxHtmlResponse implements IAjaxResponse
{
  /**
   * Response value property - only in case of success - contains the response
   * data as object
   */
  public static final String PROPERTY_VALUE = "value";
  /**
   * Additional CSS files - only in case of success - contains a list of strings
   */
  public static final String PROPERTY_EXTERNAL_CSS = "externalcss";
  /** Additional inline CSS - only in case of success - contains a string */
  public static final String PROPERTY_INLINE_CSS_BEFORE_EXTERNAL = "inlinecssBeforeExternal";
  /** Additional inline CSS - only in case of success - contains a string */
  public static final String PROPERTY_INLINE_CSS_AFTER_EXTERNAL = "inlinecssAfterExternal";
  /** The sub key for CSS elements specifying the media list */
  public static final String SUBPROPERTY_CSS_MEDIA = "media";
  /** The sub key for CSS elements specifying the external CSS href */
  public static final String SUBPROPERTY_CSS_HREF = "href";
  /** The sub key for CSS elements specifying the inline CSS content */
  public static final String SUBPROPERTY_CSS_CONTENT = "content";
  /**
   * Additional JS files - only in case of success - contains a list of strings
   */
  public static final String PROPERTY_EXTERNAL_JS = "externaljs";
  /** Additional inline JS - only in case of success - contains a string */
  public static final String PROPERTY_INLINE_JS_BEFORE_EXTERNAL = "inlinejsBeforeExternal";
  /** Additional inline JS - only in case of success - contains a string */
  public static final String PROPERTY_INLINE_JS_AFTER_EXTERNAL = "inlinejsAfterExternal";
  /** Default property for HTML content */
  public static final String PROPERTY_HTML = "html";

  private final IJsonObject m_aSuccessValue;
  private final HCSpecialNodes m_aSpecialNodes = new HCSpecialNodes ();

  @Nonnull
  public static String getHTMLString (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                      @Nullable final IHCHasChildrenMutable <?, ? super IHCNode> aNode,
                                      @Nonnull final HCSpecialNodes aSpecialNodes,
                                      @Nullable final IHCOnDocumentReadyProvider aOnDocumentReadyProvider)
  {
    return HtmlHelper.getHTMLString (aRequestScope, aNode, aSpecialNodes, aOnDocumentReadyProvider);
  }

  public static final void addCSSAndJS (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                        @Nonnull final HCSpecialNodes aSpecialNodes)
  {
    HtmlHelper.addCSSAndJS (aRequestScope, aSpecialNodes);
  }

  /**
   * Success constructor for HC nodes
   *
   * @param aRequestScope
   *        The source request scope. May not be <code>null</code> in case of
   *        success.
   * @param aNode
   *        The response HTML node. May be <code>null</code>.
   * @param aOnDocumentReadyProvider
   *        if not <code>null</code> than all combined document.ready() scripts
   *        are kept as document.ready() scripts using this provider. If
   *        <code>null</code> than all document.ready() scripts are converted to
   *        regular scripts and are executed after all other scripts. For AJAX
   *        calls, this should be <code>null</code> as there is no "document
   *        ready" callback - alternatively you can provide a custom "on
   *        document ready" provider.
   * @param aCustomJson
   *        Custom JSON object data structure. May be <code>null</code>.
   * @param bSuccess
   *        Success indicator
   */
  protected AjaxHtmlResponse (@Nullable final IRequestWebScopeWithoutResponse aRequestScope,
                              @Nullable final IHCHasChildrenMutable <?, ? super IHCNode> aNode,
                              @Nullable final IHCOnDocumentReadyProvider aOnDocumentReadyProvider,
                              @Nullable final IJsonObject aCustomJson)
  {
    // Now decompose the HCNode itself and set it in "html" property
    final JsonObject aObj = new JsonObject ();

    // First extract the HTML
    aObj.add (PROPERTY_HTML, getHTMLString (aRequestScope, aNode, m_aSpecialNodes, aOnDocumentReadyProvider));

    // Do it after all nodes were finalized etc
    addCSSAndJS (aRequestScope, m_aSpecialNodes);

    aObj.addAll (aCustomJson);

    m_aSuccessValue = aObj;
  }

  /**
   * @return In case this is a success, this field contains the success object.
   *         Never <code>null</code>.
   */
  @Nonnull
  public IJsonObject getSuccessValue ()
  {
    return m_aSuccessValue;
  }

  @Nonnull
  public IHCSpecialNodes getSpecialNodes ()
  {
    return m_aSpecialNodes;
  }

  @Nonnull
  public AjaxHtmlResponse addSpecialNodes (@Nonnull final IHCSpecialNodes aSpecialNodes)
  {
    m_aSpecialNodes.addAll (aSpecialNodes);
    return this;
  }

  @Nonnull
  public static JsonObject getResponseAsJSON (@Nullable final IJsonObject aSuccessValue,
                                              @Nonnull final IHCSpecialNodes aSpecialNodes)
  {
    return HtmlHelper.getResponseAsJSON (aSuccessValue, aSpecialNodes);
  }

  @Nonnull
  public JsonObject getResponseAsJSON ()
  {
    return getResponseAsJSON (m_aSuccessValue, m_aSpecialNodes);
  }

  public void applyToResponse (@Nonnull final UnifiedResponse aUnifiedResponse)
  {
    final IJsonObject aJson = getResponseAsJSON ();
    aUnifiedResponse.setContentAndCharset (aJson.getAsJsonString (), StandardCharsets.UTF_8)
                    .setMimeType (CMimeType.APPLICATION_JSON);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("SuccessValue", m_aSuccessValue)
                            .getToString ();
  }

  @Nonnull
  public static AjaxHtmlResponse createSuccess (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return createSuccess (aRequestScope, (IHCHasChildrenMutable <?, IHCNode>) null, (IHCOnDocumentReadyProvider) null);
  }

  @Nonnull
  public static AjaxHtmlResponse createSuccess (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                @Nullable final IHCNode... aNodes)
  {
    return createSuccess (aRequestScope, new HCNodeList ().addChildren (aNodes));
  }

  @Nonnull
  public static AjaxHtmlResponse createSuccess (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                @Nullable final IHCNode aNode,
                                                @Nullable final IHCOnDocumentReadyProvider aOnDocumentReadyProvider)
  {
    return createSuccess (aRequestScope, new HCNodeList ().addChild (aNode), aOnDocumentReadyProvider);
  }

  @Nonnull
  public static AjaxHtmlResponse createSuccess (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                @Nullable final IHCHasChildrenMutable <?, ? super IHCNode> aNode)
  {
    return createSuccess (aRequestScope, aNode, (IHCOnDocumentReadyProvider) null);
  }

  @Nonnull
  public static AjaxHtmlResponse createSuccess (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                @Nullable final IHCHasChildrenMutable <?, ? super IHCNode> aNode,
                                                @Nullable final IHCOnDocumentReadyProvider aOnDocumentReadyProvider)
  {
    return new AjaxHtmlResponse (aRequestScope, aNode, aOnDocumentReadyProvider, (IJsonObject) null);
  }

  @Nonnull
  public static AjaxHtmlResponse createSuccessExt (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                   @Nullable final IHCHasChildrenMutable <?, ? super IHCNode> aNode,
                                                   @Nullable final IJsonObject aCustomJson)
  {
    return new AjaxHtmlResponse (aRequestScope, aNode, (IHCOnDocumentReadyProvider) null, aCustomJson);
  }
}
