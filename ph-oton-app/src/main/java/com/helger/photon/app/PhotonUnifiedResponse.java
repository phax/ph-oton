/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.app;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Node;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.http.EHttpMethod;
import com.helger.commons.io.stream.NonBlockingByteArrayOutputStream;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.mime.MimeType;
import com.helger.commons.serialize.SerializationHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.ISimpleURL;
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
import com.helger.http.EHttpVersion;
import com.helger.json.IJson;
import com.helger.json.IJsonArray;
import com.helger.json.IJsonObject;
import com.helger.json.JsonArray;
import com.helger.json.JsonObject;
import com.helger.json.serialize.IJsonWriterSettings;
import com.helger.json.serialize.JsonWriterSettings;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.app.html.PhotonHTMLHelper;
import com.helger.photon.app.html.PhotonJS;
import com.helger.servlet.mock.MockHttpServletRequest;
import com.helger.servlet.request.RequestHelper;
import com.helger.servlet.response.EContentDispositionType;
import com.helger.servlet.response.ERedirectMode;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.serialize.MicroWriter;
import com.helger.xml.serialize.write.IXMLWriterSettings;
import com.helger.xml.serialize.write.XMLWriter;
import com.helger.xml.serialize.write.XMLWriterSettings;

public class PhotonUnifiedResponse extends UnifiedResponse
{
  private final IRequestWebScopeWithoutResponse m_aRequestScope;
  private IXMLWriterSettings m_aXWS = XMLWriterSettings.DEFAULT_XML_SETTINGS;
  private IJsonWriterSettings m_aJWS = JsonWriterSettings.DEFAULT_SETTINGS;

  public PhotonUnifiedResponse (@Nonnull final EHttpVersion eHttpVersion,
                                @Nonnull final EHttpMethod eHttpMethod,
                                @Nonnull final HttpServletRequest aHttpRequest,
                                @Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    super (eHttpVersion, eHttpMethod, aHttpRequest);
    m_aRequestScope = aRequestScope;
  }

  protected final IRequestWebScopeWithoutResponse getRequestScope ()
  {
    return m_aRequestScope;
  }

  @Nonnull
  public final IXMLWriterSettings getXMLWriterSettings ()
  {
    return m_aXWS;
  }

  public final void setXMLWriterSettings (@Nonnull final IXMLWriterSettings aXWS)
  {
    ValueEnforcer.notNull (aXWS, "XWS");
    m_aXWS = aXWS;
  }

  @Nonnull
  public final IJsonWriterSettings getJsonWriterSettings ()
  {
    return m_aJWS;
  }

  public final void setJsonWriterSettings (@Nonnull final IJsonWriterSettings aJWS)
  {
    ValueEnforcer.notNull (aJWS, "JWS");
    m_aJWS = aJWS;
  }

  @Nonnull
  public PhotonUnifiedResponse jsonEmpty ()
  {
    return json (null);
  }

  @Nonnull
  public PhotonUnifiedResponse json (@Nullable final IJson aValue)
  {
    // Ensure it is valid JSON
    final String sResponse = aValue != null ? aValue.getAsJsonString (m_aJWS) : "{}";
    final Charset aCharset = StandardCharsets.UTF_8;
    setContentAndCharset (sResponse, aCharset);
    setMimeType (new MimeType (CMimeType.APPLICATION_JSON).addParameter (CMimeType.PARAMETER_NAME_CHARSET,
                                                                         aCharset.name ()));
    return this;
  }

  @Nonnull
  public PhotonUnifiedResponse xml (@Nullable final String sXML, @Nonnull final Charset aCharset)
  {
    setContentAndCharset (StringHelper.getNotNull (sXML), aCharset);
    setMimeType (new MimeType (CMimeType.APPLICATION_XML).addParameter (CMimeType.PARAMETER_NAME_CHARSET,
                                                                        aCharset.name ()));
    return this;
  }

  @Nonnull
  public PhotonUnifiedResponse xml (@Nullable final IMicroNode aNode)
  {
    return xml (aNode, m_aXWS);
  }

  @Nonnull
  public PhotonUnifiedResponse xml (@Nullable final IMicroNode aNode, @Nonnull final IXMLWriterSettings aSettings)
  {
    return xml (aNode == null ? null : MicroWriter.getNodeAsString (aNode, aSettings), aSettings.getCharset ());
  }

  @Nonnull
  public PhotonUnifiedResponse xml (@Nullable final Node aNode)
  {
    return xml (aNode, m_aXWS);
  }

  @Nonnull
  public PhotonUnifiedResponse xml (@Nullable final Node aNode, @Nonnull final IXMLWriterSettings aSettings)
  {
    return xml (aNode == null ? null : XMLWriter.getNodeAsString (aNode, aSettings), aSettings.getCharset ());
  }

  @Nonnull
  public PhotonUnifiedResponse text (@Nullable final String sValue)
  {
    setContentAndCharset (StringHelper.getNotNull (sValue), StandardCharsets.UTF_8);
    setMimeType (CMimeType.TEXT_PLAIN);
    return this;
  }

  public static final class HtmlHelper
  {
    /**
     * Response value property - only in case of success - contains the response
     * data as object
     */
    public static final String PROPERTY_VALUE = "value";
    /**
     * Additional CSS files - only in case of success - contains a list of
     * strings
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
     * Additional JS files - only in case of success - contains a list of
     * strings
     */
    public static final String PROPERTY_EXTERNAL_JS = "externaljs";
    /** Additional inline JS - only in case of success - contains a string */
    public static final String PROPERTY_INLINE_JS_BEFORE_EXTERNAL = "inlinejsBeforeExternal";
    /** Additional inline JS - only in case of success - contains a string */
    public static final String PROPERTY_INLINE_JS_AFTER_EXTERNAL = "inlinejsAfterExternal";
    /** Default property for HTML content */
    public static final String PROPERTY_HTML = "html";

    private HtmlHelper ()
    {}

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
        HCRenderer.prepareForConversion (aHtml, aHtml.body (), aConversionSettings);

        // Extract and merge all inline out-of-band nodes
        if (aConversionSettings.isExtractOutOfBandNodes ())
        {
          final ICommonsList <IHCNode> aOOBNodes = aHtml.getAllOutOfBandNodesWithMergedInlineNodes ();
          aHtml.addAllOutOfBandNodesToHead (aOOBNodes);
        }

        PhotonHTMLHelper.mergeExternalCSSAndJSNodes (aRequestScope,
                                                     aHtml.head (),
                                                     PhotonAppSettings.isMergeCSSResources (),
                                                     PhotonAppSettings.isMergeJSResources (),
                                                     PhotonAppManager.getWebSiteResourceBundleMgr ());

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
      return aMicroNode == null ? ""
                                : MicroWriter.getNodeAsString (aMicroNode, aConversionSettings.getXMLWriterSettings ());
    }

    public static void addCSSAndJS (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                    @Nonnull final HCSpecialNodes aSpecialNodes)
    {
      ValueEnforcer.notNull (aRequestScope, "RequestScope");

      // Grab per-request CSS/JS only in success case!
      // Grab all CSS/JS independent of conditional comment :(
      final boolean bRegular = HCSettings.isUseRegularResources ();

      for (final ICSSPathProvider aCSS : PhotonCSS.getAllRegisteredCSSIncludesForThisRequest ())
        aSpecialNodes.addExternalCSS (aCSS.getMediaList (),
                                      PhotonAppSettings.getCSSPath (aRequestScope, aCSS, bRegular)
                                                       .getAsStringWithEncodedParameters ());

      for (final IJSPathProvider aJS : PhotonJS.getAllRegisteredJSIncludesForThisRequest ())
        aSpecialNodes.addExternalJS (PhotonAppSettings.getJSPath (aRequestScope, aJS, bRegular)
                                                      .getAsStringWithEncodedParameters ());
    }

    @Nonnull
    public static IJsonObject getResponseAsJSON (@Nullable final IJsonObject aSuccessValue,
                                                 @Nonnull final IHCSpecialNodes aSpecialNodes)
    {
      final IJsonObject aAssocArray = new JsonObject ();
      if (aSuccessValue != null)
        aAssocArray.addJson (PROPERTY_VALUE, aSuccessValue);

      // Apply special nodes
      if (aSpecialNodes.hasExternalCSSs ())
      {
        final IJsonArray aList = new JsonArray ();
        for (final Map.Entry <ICSSMediaList, ICommonsList <String>> aEntry : aSpecialNodes.getAllExternalCSSs ()
                                                                                          .entrySet ())
          for (final String sCSSFile : aEntry.getValue ())
            aList.add (new JsonObject ().add (SUBPROPERTY_CSS_MEDIA, aEntry.getKey ().getMediaString ())
                                        .add (SUBPROPERTY_CSS_HREF, sCSSFile));
        aAssocArray.addJson (PROPERTY_EXTERNAL_CSS, aList);
      }
      if (aSpecialNodes.hasInlineCSSBeforeExternal ())
      {
        final IJsonArray aList = new JsonArray ();
        for (final ICSSCodeProvider aEntry : aSpecialNodes.getAllInlineCSSBeforeExternal ())
          aList.add (new JsonObject ().add (SUBPROPERTY_CSS_MEDIA, aEntry.getMediaList ().getMediaString ())
                                      .add (SUBPROPERTY_CSS_CONTENT, aEntry.getCSSCode ()));
        aAssocArray.addJson (PROPERTY_INLINE_CSS_BEFORE_EXTERNAL, aList);
      }
      if (aSpecialNodes.hasInlineCSSAfterExternal ())
      {
        final IJsonArray aList = new JsonArray ();
        for (final ICSSCodeProvider aEntry : aSpecialNodes.getAllInlineCSSAfterExternal ())
          aList.add (new JsonObject ().add (SUBPROPERTY_CSS_MEDIA, aEntry.getMediaList ().getMediaString ())
                                      .add (SUBPROPERTY_CSS_CONTENT, aEntry.getCSSCode ()));
        aAssocArray.addJson (PROPERTY_INLINE_CSS_AFTER_EXTERNAL, aList);
      }
      if (aSpecialNodes.hasExternalJSs ())
        aAssocArray.add (PROPERTY_EXTERNAL_JS, aSpecialNodes.getAllExternalJSs ());
      if (aSpecialNodes.hasInlineJSBeforeExternal ())
        aAssocArray.add (PROPERTY_INLINE_JS_BEFORE_EXTERNAL, aSpecialNodes.getInlineJSBeforeExternal ().getJSCode ());
      if (aSpecialNodes.hasInlineJSAfterExternal ())
        aAssocArray.add (PROPERTY_INLINE_JS_AFTER_EXTERNAL, aSpecialNodes.getInlineJSAfterExternal ().getJSCode ());
      return aAssocArray;
    }
  }

  @SuppressWarnings ("unchecked")
  @Nonnull
  public PhotonUnifiedResponse html (@Nullable final IHCNode aNode)
  {
    return html (aNode == null ? null
                               : aNode instanceof IHCHasChildrenMutable <?, ?> ? (IHCHasChildrenMutable <?, IHCNode>) aNode
                                                                               : new HCNodeList ().addChild (aNode),
                 null,
                 null);
  }

  @Nonnull
  public PhotonUnifiedResponse html (@Nullable final IHCHasChildrenMutable <?, ? super IHCNode> aNode,
                                     @Nullable final IHCOnDocumentReadyProvider aOnDocumentReadyProvider,
                                     @Nullable final IJsonObject aCustomJson)
  {
    final HCSpecialNodes aSpecialNodes = new HCSpecialNodes ();

    // Now decompose the HCNode itself and set it in "html" property
    final IJsonObject aObj = new JsonObject ();
    // First extract the HTML
    aObj.add (HtmlHelper.PROPERTY_HTML,
              HtmlHelper.getHTMLString (m_aRequestScope, aNode, aSpecialNodes, aOnDocumentReadyProvider));
    // Do it after all nodes were finalized etc
    HtmlHelper.addCSSAndJS (m_aRequestScope, aSpecialNodes);

    // Add custom json
    aObj.addAll (aCustomJson);

    final IJsonObject ret = HtmlHelper.getResponseAsJSON (aObj, aSpecialNodes);
    return json (ret);
  }

  /**
   * Create a simple HTML response without JSON structuring
   *
   * @param aNode
   *        The node to be rendered. May be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public PhotonUnifiedResponse htmlSimple (@Nullable final IHCNode aNode)
  {
    if (aNode == null)
      setContentAndCharset ("", HCSettings.getHTMLCharset ());
    else
      setContentAndCharset (HCRenderer.getAsHTMLStringWithoutNamespaces (aNode), HCSettings.getHTMLCharset ());
    setMimeType (PhotonHTMLHelper.getMimeType (m_aRequestScope));
    return this;
  }

  /**
   * HTTP 200 OK
   *
   * @return this for chaining
   */
  @Nonnull
  public PhotonUnifiedResponse createOk ()
  {
    setStatus (HttpServletResponse.SC_OK);
    return this;
  }

  /**
   * HTTP 202 Accepted
   *
   * @return this for chaining
   * @since 8.1.3
   */
  @Nonnull
  public PhotonUnifiedResponse createAccepted ()
  {
    setStatus (HttpServletResponse.SC_ACCEPTED);
    return this;
  }

  /**
   * HTTP 204 No Content
   *
   * @return this for chaining
   */
  @Nonnull
  public PhotonUnifiedResponse createNoContent ()
  {
    setStatus (HttpServletResponse.SC_NO_CONTENT);
    return this;
  }

  /**
   * HTTP 303 See Other
   *
   * @param aRedirectTargetURL
   *        The redirect URL. May not be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public PhotonUnifiedResponse createSeeOther (@Nonnull final ISimpleURL aRedirectTargetURL)
  {
    ValueEnforcer.notNull (aRedirectTargetURL, "Location");

    setRedirect (aRedirectTargetURL, ERedirectMode.POST_REDIRECT_GET);
    return this;
  }

  /**
   * HTTP 400 Bad Request
   *
   * @return this for chaining
   */
  @Nonnull
  public PhotonUnifiedResponse createBadRequest ()
  {
    setStatus (HttpServletResponse.SC_BAD_REQUEST);
    return this;
  }

  /**
   * HTTP 404 Not Found
   *
   * @return this for chaining
   */
  @Nonnull
  public PhotonUnifiedResponse createNotFound ()
  {
    setStatus (HttpServletResponse.SC_NOT_FOUND);
    return this;
  }

  /**
   * HTTP 409 Conflict
   *
   * @return this for chaining
   */
  @Nonnull
  public PhotonUnifiedResponse createConflict ()
  {
    setStatus (HttpServletResponse.SC_CONFLICT);
    return this;
  }

  /**
   * HTTP 412 Precondition Failed
   *
   * @return this for chaining
   */
  @Nonnull
  public PhotonUnifiedResponse createPreconditionFailed ()
  {
    setStatus (HttpServletResponse.SC_PRECONDITION_FAILED);
    return this;
  }

  /**
   * HTTP 500 Internal Server Error
   *
   * @return this for chaining
   */
  @Nonnull
  public PhotonUnifiedResponse createInternalServerError ()
  {
    setStatus (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    return this;
  }

  @Nonnull
  public PhotonUnifiedResponse setContent (@Nonnull final NonBlockingByteArrayOutputStream aBAOS)
  {
    setContent (aBAOS.directGetBuffer (), 0, aBAOS.size ());
    return this;
  }

  @Nonnull
  public PhotonUnifiedResponse serialized (@Nonnull final Serializable aObj)
  {
    setContent (SerializationHelper.getSerializedByteArray (aObj));
    setMimeType (CMimeType.APPLICATION_OCTET_STREAM);
    return this;
  }

  @Nonnull
  public PhotonUnifiedResponse pdf (@Nonnull final NonBlockingByteArrayOutputStream aBAOS)
  {
    setContent (aBAOS);
    setMimeType (CMimeType.APPLICATION_PDF);
    return this;
  }

  @Nonnull
  public PhotonUnifiedResponse pdf (@Nonnull final byte [] aData, @Nonnull @Nonempty final String sFilename)
  {
    setContent (aData);
    setMimeType (CMimeType.APPLICATION_PDF);
    setContentDispositionFilename (sFilename);
    setContentDispositionType (EContentDispositionType.INLINE);
    return this;
  }

  @Nonnull
  public PhotonUnifiedResponse pdf (@Nonnull final NonBlockingByteArrayOutputStream aBAOS,
                                    @Nonnull @Nonempty final String sFilename)
  {
    pdf (aBAOS);
    setContentDispositionFilename (sFilename);
    setContentDispositionType (EContentDispositionType.INLINE);
    return this;
  }

  @Nonnull
  public PhotonUnifiedResponse attachment (@Nonnull @Nonempty final String sFilename)
  {
    setContentDispositionFilename (sFilename);
    setContentDispositionType (EContentDispositionType.ATTACHMENT);
    return this;
  }

  @Nonnull
  public PhotonUnifiedResponse binary (@Nonnull final NonBlockingByteArrayOutputStream aBAOS,
                                       @Nonnull final IMimeType aMimeType,
                                       @Nonnull @Nonempty final String sFilename)
  {
    setContent (aBAOS);
    setMimeType (aMimeType);
    return attachment (sFilename);
  }

  /**
   * Factory method
   *
   * @param aRequestScope
   *        The current request scope. May not be <code>null</code>.
   * @return New {@link PhotonUnifiedResponse}. Never <code>null</code>.
   */
  @Nonnull
  public static PhotonUnifiedResponse createSimple (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    final HttpServletRequest aHttpRequest = aRequestScope.getRequest ();
    if (aHttpRequest instanceof MockHttpServletRequest)
    {
      // No version and no method present
      return new PhotonUnifiedResponse (EHttpVersion.HTTP_11, EHttpMethod.GET, aHttpRequest, aRequestScope);
    }
    return new PhotonUnifiedResponse (RequestHelper.getHttpVersion (aHttpRequest),
                                      RequestHelper.getHttpMethod (aHttpRequest),
                                      aHttpRequest,
                                      aRequestScope);
  }
}
