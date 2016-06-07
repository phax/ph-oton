/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.charset.CCharset;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.css.media.ICSSMediaList;
import com.helger.html.hc.IHCConversionSettings;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCNodeList;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.config.IHCOnDocumentReadyProvider;
import com.helger.html.hc.html.root.HCHtml;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.render.HCRenderer;
import com.helger.html.hc.special.HCSpecialNodeHandler;
import com.helger.html.hc.special.HCSpecialNodes;
import com.helger.html.hc.special.IHCSpecialNodes;
import com.helger.html.resource.css.ICSSCodeProvider;
import com.helger.html.resource.css.ICSSPathProvider;
import com.helger.html.resource.js.IJSPathProvider;
import com.helger.json.IJsonObject;
import com.helger.json.JsonArray;
import com.helger.json.JsonObject;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonHTMLHelper;
import com.helger.photon.core.app.html.PhotonHTMLSettings;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.core.resource.ResourceBundleServlet;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.response.UnifiedResponse;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.serialize.MicroWriter;

/**
 * Ajax response with HTML content. The returned Content-Type is JSON and the
 * detailed information are in separate objects. See the public properties.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class AjaxHtmlResponse extends AbstractAjaxResponse
{
  /** Success property */
  public static final String PROPERTY_SUCCESS = "success";
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
  /** Error message property - only in case of error */
  public static final String PROPERTY_ERRORMESSAGE = "errormessage";
  /** Default property for HTML content */
  public static final String PROPERTY_HTML = "html";

  private final IJsonObject m_aSuccessValue;
  private final HCSpecialNodes m_aSpecialNodes = new HCSpecialNodes ();
  private final String m_sErrorMessage;

  @Nonnull
  public static String getHTMLString (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                      @Nullable final IHCHasChildrenMutable <?, ? super IHCNode> aNode,
                                      @Nonnull final HCSpecialNodes aSpecialNodes,
                                      @Nullable final IHCOnDocumentReadyProvider aOnDocumentReadyProvider)
  {
    ValueEnforcer.notNull (aRequestScope, "RequestScope");
    ValueEnforcer.notNull (aSpecialNodes, "SpecialNodes");
    if (aNode == null)
      return "";

    final IHCConversionSettings aConversionSettings = HCSettings.getConversionSettingsWithoutNamespaces ();

    IHCNode aTargetNode = aNode;

    // Special handling for complete HCHtml objects needed
    if (aNode instanceof IHCNodeList <?> &&
        ((IHCNodeList <?>) aNode).getChildCount () == 1 &&
        ((IHCNodeList <?>) aNode).getFirstChild () instanceof HCHtml)
    {
      final HCHtml aHtml = (HCHtml) ((IHCNodeList <?>) aNode).getFirstChild ();
      aTargetNode = aHtml;

      // customize, finalize and extract resources
      // This must be done before the CSS and JS are included because
      // per-request
      // resource registration happens inside
      HCRenderer.prepareForConversion (aHtml, aHtml.getBody (), aConversionSettings);

      // Extract and merge all inline out-of-band nodes
      if (aConversionSettings.isExtractOutOfBandNodes ())
      {
        final ICommonsList <IHCNode> aOOBNodes = aHtml.getAllOutOfBandNodesWithMergedInlineNodes ();
        aHtml.addAllOutOfBandNodesToHead (aOOBNodes);
      }

      final boolean bMergeCSS = ResourceBundleServlet.isEnabled ();
      final boolean bMergeJS = ResourceBundleServlet.isEnabled ();
      PhotonHTMLHelper.mergeExternalCSSAndJSNodes (aRequestScope, aHtml.getHead (), bMergeCSS, bMergeJS);

      // Move scripts to body? If so, after aggregation!
      if (HCSettings.isScriptsInBody ())
        aHtml.moveScriptElementsToBody ();
    }
    else
    {
      // customize, finalize and extract resources
      // Non-HTML node
      HCRenderer.prepareForConversion (aNode, aNode, aConversionSettings);

      if (aConversionSettings.isExtractOutOfBandNodes ())
      {
        HCSpecialNodeHandler.extractSpecialContent (aNode, aSpecialNodes, aOnDocumentReadyProvider);
      }
    }

    // Serialize remaining node to HTML
    final IMicroNode aMicroNode = aTargetNode.convertToMicroNode (aConversionSettings);
    final String sHTML = aMicroNode == null ? ""
                                            : MicroWriter.getNodeAsString (aMicroNode,
                                                                           aConversionSettings.getXMLWriterSettings ());

    return sHTML;
  }

  public static final void addCSSAndJS (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                        @Nonnull final HCSpecialNodes aSpecialNodes)
  {
    ValueEnforcer.notNull (aRequestScope, "RequestScope");

    // Grab per-request CSS/JS only in success case!
    // Grab all CSS/JS independent of conditional comment :(
    final boolean bRegular = HCSettings.isUseRegularResources ();

    for (final ICSSPathProvider aCSS : PhotonCSS.getAllRegisteredCSSIncludesForThisRequest ())
      aSpecialNodes.addExternalCSS (aCSS.getMediaList (),
                                    PhotonHTMLSettings.getCSSPath (aRequestScope, aCSS, bRegular)
                                                      .getAsStringWithEncodedParameters ());

    for (final IJSPathProvider aJS : PhotonJS.getAllRegisteredJSIncludesForThisRequest ())
      aSpecialNodes.addExternalJS (PhotonHTMLSettings.getJSPath (aRequestScope, aJS, bRegular)
                                                     .getAsStringWithEncodedParameters ());
  }

  /**
   * Success constructor for HC nodes
   *
   * @param bSuccess
   *        Success indicator
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
   *        calls, this should be <code>null</code> as there is no
   *        "document ready" callback - alternatively you can provide a custom
   *        "on document ready" provider.
   * @param sErrorMessage
   *        Optional error message if success if <code>false</code>
   */
  protected AjaxHtmlResponse (final boolean bSuccess,
                              @Nullable final IRequestWebScopeWithoutResponse aRequestScope,
                              @Nullable final IHCHasChildrenMutable <?, ? super IHCNode> aNode,
                              @Nullable final IHCOnDocumentReadyProvider aOnDocumentReadyProvider,
                              @Nullable final String sErrorMessage)
  {
    super (bSuccess);

    // Now decompose the HCNode itself and set it in "html" property
    final JsonObject aObj = new JsonObject ();
    if (bSuccess)
    {
      // First extract the HTML
      aObj.add (PROPERTY_HTML, getHTMLString (aRequestScope, aNode, m_aSpecialNodes, aOnDocumentReadyProvider));

      // Do it after all nodes were finalized etc
      addCSSAndJS (aRequestScope, m_aSpecialNodes);
    }

    m_aSuccessValue = aObj;
    m_sErrorMessage = sErrorMessage;
  }

  /**
   * @return In case this is a success, this field contains the success object.
   *         May be <code>null</code>.
   */
  @Nullable
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

  /**
   * @return In case this is a failure, this field contains the error message.
   *         May be <code>null</code>.
   */
  @Nullable
  public String getErrorMessage ()
  {
    return m_sErrorMessage;
  }

  @Nonnull
  public static JsonObject getResponseAsJSON (final boolean bIsSuccess,
                                              @Nullable final IJsonObject aSuccessValue,
                                              @Nonnull final HCSpecialNodes aSpecialNodes,
                                              @Nullable final String sErrorMessage)
  {
    final JsonObject aAssocArray = new JsonObject ();
    aAssocArray.add (PROPERTY_SUCCESS, bIsSuccess);
    if (bIsSuccess)
    {
      if (aSuccessValue != null)
        aAssocArray.add (PROPERTY_VALUE, aSuccessValue);

      // Apply special nodes
      if (aSpecialNodes.hasExternalCSSs ())
      {
        final JsonArray aList = new JsonArray ();
        for (final Map.Entry <ICSSMediaList, ICommonsList <String>> aEntry : aSpecialNodes.getAllExternalCSSs ()
                                                                                          .entrySet ())
          for (final String sCSSFile : aEntry.getValue ())
            aList.add (new JsonObject ().add (SUBPROPERTY_CSS_MEDIA, aEntry.getKey ().getMediaString ())
                                        .add (SUBPROPERTY_CSS_HREF, sCSSFile));
        aAssocArray.add (PROPERTY_EXTERNAL_CSS, aList);
      }
      if (aSpecialNodes.hasInlineCSSBeforeExternal ())
      {
        final JsonArray aList = new JsonArray ();
        for (final ICSSCodeProvider aEntry : aSpecialNodes.getAllInlineCSSBeforeExternal ())
          aList.add (new JsonObject ().add (SUBPROPERTY_CSS_MEDIA, aEntry.getMediaList ().getMediaString ())
                                      .add (SUBPROPERTY_CSS_CONTENT, aEntry.getCSSCode ()));
        aAssocArray.add (PROPERTY_INLINE_CSS_BEFORE_EXTERNAL, aList);
      }
      if (aSpecialNodes.hasInlineCSSAfterExternal ())
      {
        final JsonArray aList = new JsonArray ();
        for (final ICSSCodeProvider aEntry : aSpecialNodes.getAllInlineCSSAfterExternal ())
          aList.add (new JsonObject ().add (SUBPROPERTY_CSS_MEDIA, aEntry.getMediaList ().getMediaString ())
                                      .add (SUBPROPERTY_CSS_CONTENT, aEntry.getCSSCode ()));
        aAssocArray.add (PROPERTY_INLINE_CSS_AFTER_EXTERNAL, aList);
      }
      if (aSpecialNodes.hasExternalJSs ())
        aAssocArray.add (PROPERTY_EXTERNAL_JS, aSpecialNodes.getAllExternalJSs ());
      if (aSpecialNodes.hasInlineJSBeforeExternal ())
        aAssocArray.add (PROPERTY_INLINE_JS_BEFORE_EXTERNAL, aSpecialNodes.getInlineJSBeforeExternal ().getJSCode ());
      if (aSpecialNodes.hasInlineJSAfterExternal ())
        aAssocArray.add (PROPERTY_INLINE_JS_AFTER_EXTERNAL, aSpecialNodes.getInlineJSAfterExternal ().getJSCode ());
    }
    else
    {
      aAssocArray.add (PROPERTY_ERRORMESSAGE, StringHelper.getNotNull (sErrorMessage));
    }
    return aAssocArray;
  }

  @Nonnull
  public JsonObject getResponseAsJSON ()
  {
    return getResponseAsJSON (isSuccess (), m_aSuccessValue, m_aSpecialNodes, m_sErrorMessage);
  }

  public void applyToResponse (@Nonnull final UnifiedResponse aUnifiedResponse)
  {
    final IJsonObject aJson = getResponseAsJSON ();
    aUnifiedResponse.setContentAndCharset (aJson.getAsJsonString (), CCharset.CHARSET_UTF_8_OBJ)
                    .setMimeType (CMimeType.APPLICATION_JSON);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final AjaxHtmlResponse rhs = (AjaxHtmlResponse) o;
    return EqualsHelper.equals (m_sErrorMessage, rhs.m_sErrorMessage) &&
           EqualsHelper.equals (m_aSuccessValue, rhs.m_aSuccessValue);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ())
                            .append (m_sErrorMessage)
                            .append (m_aSuccessValue)
                            .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("errorMsg", m_sErrorMessage)
                            .appendIfNotNull ("successValue", m_aSuccessValue)
                            .toString ();
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
    return new AjaxHtmlResponse (true, aRequestScope, aNode, aOnDocumentReadyProvider, (String) null);
  }

  @Nonnull
  public static AjaxHtmlResponse createError (@Nullable final String sErrorMessage)
  {
    return new AjaxHtmlResponse (false,
                                 (IRequestWebScopeWithoutResponse) null,
                                 (IHCHasChildrenMutable <?, IHCNode>) null,
                                 (IHCOnDocumentReadyProvider) null,
                                 sErrorMessage);
  }
}
