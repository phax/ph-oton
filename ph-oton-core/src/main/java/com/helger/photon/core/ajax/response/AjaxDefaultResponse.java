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
package com.helger.photon.core.ajax.response;

import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.microdom.IMicroNode;
import com.helger.commons.microdom.serialize.MicroWriter;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.string.ToStringGenerator;
import com.helger.css.media.ICSSMediaList;
import com.helger.html.hc.IHCConversionSettings;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.render.HCRenderer;
import com.helger.html.hc.special.HCSpecialNodeHandler;
import com.helger.html.hc.special.HCSpecialNodes;
import com.helger.html.hc.special.IHCSpecialNodes;
import com.helger.html.resource.css.ICSSCodeProvider;
import com.helger.html.resource.css.ICSSPathProvider;
import com.helger.html.resource.js.IJSPathProvider;
import com.helger.json.IJson;
import com.helger.json.JsonArray;
import com.helger.json.JsonObject;
import com.helger.json.serialize.JsonWriter;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonHTMLSettings;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

@Immutable
public class AjaxDefaultResponse implements IAjaxResponse
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

  private final boolean m_bSuccess;
  private final String m_sErrorMessage;
  private final IJson m_aSuccessValue;
  private final HCSpecialNodes m_aSpecialNodes = new HCSpecialNodes ();

  private void _addCSSAndJS (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    ValueEnforcer.notNull (aRequestScope, "RequestScope");

    // Grab per-request CSS/JS only in success case!
    // Grab all CSS/JS independent of conditional comment :(
    final boolean bRegular = HCSettings.isUseRegularResources ();

    for (final ICSSPathProvider aCSS : PhotonCSS.getAllRegisteredCSSIncludesForThisRequest ())
      m_aSpecialNodes.addExternalCSS (aCSS.getMediaList (),
                                      PhotonHTMLSettings.getCSSPath (aRequestScope, aCSS, bRegular).getAsString ());

    for (final IJSPathProvider aJS : PhotonJS.getAllRegisteredJSIncludesForThisRequest ())
      m_aSpecialNodes.addExternalJS (PhotonHTMLSettings.getJSPath (aRequestScope, aJS, bRegular).getAsString ());
  }

  /**
   * Success constructor for HC nodes
   *
   * @param aNode
   *        The response HTML node. May be <code>null</code>.
   */
  protected AjaxDefaultResponse (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                 @Nullable final IHCHasChildrenMutable <?, ? super IHCNode> aNode)
  {
    // Now decompose the HCNode itself
    final JsonObject aObj = new JsonObject ();
    if (aNode != null)
    {
      final IHCConversionSettings aConversionSettings = HCSettings.getConversionSettingsWithoutNamespaces ();

      // customize, finalize and extract resources
      HCRenderer.prepareForConversion (aNode, aNode, aConversionSettings);

      if (aConversionSettings.isExtractOutOfBandNodes ())
      {
        // no need to keepOnDocumentReady stuff as the document is already
        // loaded
        final boolean bKeepOnDocumentReady = false;
        HCSpecialNodeHandler.extractSpecialContent (aNode, m_aSpecialNodes, bKeepOnDocumentReady);
      }

      // Serialize remaining node to HTML
      final IMicroNode aMicroNode = aNode.convertToMicroNode (aConversionSettings);
      final String sHTML = aMicroNode == null ? ""
                                              : MicroWriter.getNodeAsString (aMicroNode,
                                                                             aConversionSettings.getXMLWriterSettings ());

      aObj.add (PROPERTY_HTML, sHTML);
    }

    // Do it after all nodes were finalized etc
    _addCSSAndJS (aRequestScope);

    m_bSuccess = true;
    m_sErrorMessage = null;
    m_aSuccessValue = aObj;
  }

  protected AjaxDefaultResponse (final boolean bSuccess,
                                 @Nullable final String sErrorMessage,
                                 @Nullable final IJson aSuccessValue,
                                 @Nullable final IRequestWebScopeWithoutResponse aRequestScope)
  {
    m_bSuccess = bSuccess;
    m_sErrorMessage = sErrorMessage;
    m_aSuccessValue = aSuccessValue;
    if (bSuccess)
      _addCSSAndJS (aRequestScope);
  }

  public boolean isSuccess ()
  {
    return m_bSuccess;
  }

  public boolean isFailure ()
  {
    return !m_bSuccess;
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

  /**
   * @return In case this is a success, this field contains the success object.
   *         May be <code>null</code>.
   */
  @Nullable
  public IJson getSuccessValue ()
  {
    return m_aSuccessValue;
  }

  @Nonnull
  public IHCSpecialNodes getSpecialNodes ()
  {
    return m_aSpecialNodes;
  }

  @Nonnull
  public AjaxDefaultResponse addSpecialNodes (@Nonnull final IHCSpecialNodes aSpecialNodes)
  {
    m_aSpecialNodes.addAll (aSpecialNodes);
    return this;
  }

  @Nonnull
  public IMimeType getMimeType ()
  {
    return CMimeType.APPLICATION_JSON;
  }

  @Nonnull
  public String getResponseAsString (final boolean bIndentAndAlign)
  {
    final JsonObject aAssocArray = new JsonObject ();
    aAssocArray.add (PROPERTY_SUCCESS, m_bSuccess);
    if (m_bSuccess)
    {
      if (m_aSuccessValue != null)
        aAssocArray.add (PROPERTY_VALUE, m_aSuccessValue);
      // Apply special nodes
      if (m_aSpecialNodes.hasExternalCSSs ())
      {
        final JsonArray aList = new JsonArray ();
        for (final Map.Entry <ICSSMediaList, List <String>> aEntry : m_aSpecialNodes.getAllExternalCSSs ().entrySet ())
          for (final String sCSSFile : aEntry.getValue ())
            aList.add (new JsonObject ().add (SUBPROPERTY_CSS_MEDIA, aEntry.getKey ().getMediaString ())
                                        .add (SUBPROPERTY_CSS_HREF, sCSSFile));
        aAssocArray.add (PROPERTY_EXTERNAL_CSS, aList);
      }
      if (m_aSpecialNodes.hasInlineCSSBeforeExternal ())
      {
        final JsonArray aList = new JsonArray ();
        for (final ICSSCodeProvider aEntry : m_aSpecialNodes.getAllInlineCSSBeforeExternal ())
          aList.add (new JsonObject ().add (SUBPROPERTY_CSS_MEDIA, aEntry.getMediaList ().getMediaString ())
                                      .add (SUBPROPERTY_CSS_CONTENT, aEntry.getCSSCode ()));
        aAssocArray.add (PROPERTY_INLINE_CSS_BEFORE_EXTERNAL, aList);
      }
      if (m_aSpecialNodes.hasInlineCSSAfterExternal ())
      {
        final JsonArray aList = new JsonArray ();
        for (final ICSSCodeProvider aEntry : m_aSpecialNodes.getAllInlineCSSAfterExternal ())
          aList.add (new JsonObject ().add (SUBPROPERTY_CSS_MEDIA, aEntry.getMediaList ().getMediaString ())
                                      .add (SUBPROPERTY_CSS_CONTENT, aEntry.getCSSCode ()));
        aAssocArray.add (PROPERTY_INLINE_CSS_AFTER_EXTERNAL, aList);
      }
      if (m_aSpecialNodes.hasExternalJSs ())
        aAssocArray.add (PROPERTY_EXTERNAL_JS, m_aSpecialNodes.getAllExternalJSs ());
      if (m_aSpecialNodes.hasInlineJSBeforeExternal ())
        aAssocArray.add (PROPERTY_INLINE_JS_BEFORE_EXTERNAL, m_aSpecialNodes.getInlineJSBeforeExternal ().getJSCode ());
      if (m_aSpecialNodes.hasInlineJSAfterExternal ())
        aAssocArray.add (PROPERTY_INLINE_JS_AFTER_EXTERNAL, m_aSpecialNodes.getInlineJSAfterExternal ().getJSCode ());
    }
    else
    {
      aAssocArray.add (PROPERTY_ERRORMESSAGE, m_sErrorMessage != null ? m_sErrorMessage : "");
    }
    return JsonWriter.getAsString (aAssocArray);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final AjaxDefaultResponse rhs = (AjaxDefaultResponse) o;
    return m_bSuccess == rhs.m_bSuccess &&
           EqualsHelper.equals (m_sErrorMessage, rhs.m_sErrorMessage) &&
           EqualsHelper.equals (m_aSuccessValue, rhs.m_aSuccessValue);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ())
                            .append (m_bSuccess)
                            .append (m_sErrorMessage)
                            .append (m_aSuccessValue)
                            .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("success", m_bSuccess)
                            .appendIfNotNull ("errorMsg", m_sErrorMessage)
                            .appendIfNotNull ("successValue", m_aSuccessValue)
                            .toString ();
  }

  @Nonnull
  public static AjaxDefaultResponse createSuccess (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return createSuccess (aRequestScope, (IJson) null);
  }

  @Nonnull
  public static AjaxDefaultResponse createSuccess (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                   @Nullable final IJson aSuccessValue)
  {
    return new AjaxDefaultResponse (true, null, aSuccessValue, aRequestScope);
  }

  @Nonnull
  public static AjaxDefaultResponse createSuccess (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                   @Nullable final IHCNode... aNodes)
  {
    // Use the default converter here
    return createSuccess (aRequestScope, new HCNodeList ().addChildren (aNodes));
  }

  @Nonnull
  public static AjaxDefaultResponse createSuccess (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                   @Nullable final IHCHasChildrenMutable <?, ? super IHCNode> aNode)
  {
    // Special case required
    return new AjaxDefaultResponse (aRequestScope, aNode);
  }

  @Nonnull
  public static AjaxDefaultResponse createError ()
  {
    return createError ((String) null);
  }

  @Nonnull
  public static AjaxDefaultResponse createError (@Nullable final String sErrorMessage)
  {
    // No request scope needed in case of error!
    // No converter needed in case of error!
    return new AjaxDefaultResponse (false, sErrorMessage, (IJson) null, (IRequestWebScopeWithoutResponse) null);
  }
}
