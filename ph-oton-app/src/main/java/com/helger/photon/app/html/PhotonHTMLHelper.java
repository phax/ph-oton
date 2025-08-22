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
package com.helger.photon.app.html;

import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.base.CGlobal;
import com.helger.base.io.nonblocking.NonBlockingByteArrayOutputStream;
import com.helger.base.io.stream.HasInputStream;
import com.helger.base.string.StringHelper;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.html.hc.IHCConversionSettings;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.config.HCConversionSettings;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.ext.HCConditionalCommentNode;
import com.helger.html.hc.html.metadata.HCCSSNodeDetector;
import com.helger.html.hc.html.metadata.HCHead;
import com.helger.html.hc.html.metadata.HCLink;
import com.helger.html.hc.html.metadata.HCMeta;
import com.helger.html.hc.html.root.HCHtml;
import com.helger.html.hc.html.script.HCJSNodeDetector;
import com.helger.html.hc.html.script.HCScriptFile;
import com.helger.html.hc.render.HCRenderer;
import com.helger.html.meta.EStandardMetaElement;
import com.helger.html.resource.css.ICSSPathProvider;
import com.helger.html.resource.js.IJSPathProvider;
import com.helger.mime.CMimeType;
import com.helger.mime.IMimeType;
import com.helger.mime.MimeType;
import com.helger.photon.app.PhotonAppSettings;
import com.helger.photon.app.csrf.CSRFSessionManager;
import com.helger.photon.app.resource.IWebSiteResourceBundleProvider;
import com.helger.photon.app.resource.WebSiteResourceBundleSerialized;
import com.helger.photon.app.resource.WebSiteResourceWithCondition;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * A utility class for consistent HTML creation.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class PhotonHTMLHelper
{
  public static final String META_GENERATOR_VALUE = "https://github.com/phax/ph-oton // phax // ASL 2.0";

  private PhotonHTMLHelper ()
  {}

  private static void _applySessionNonce (@Nonnull final HCConversionSettings aCS)
  {
    final boolean bOnScript = HCSettings.isUseNonceInScript ();
    final boolean bOnStyle = HCSettings.isUseNonceInStyle ();
    // Get per session nonce
    if (bOnScript || bOnStyle)
    {
      final String sNonce = CSRFSessionManager.getInstance ().getNonce ();
      if (bOnScript)
        aCS.setNonceScript (sNonce);
      if (bOnStyle)
        aCS.setNonceStyle (sNonce);
    }
  }

  @Nonnull
  public static HCConversionSettings getHCConversionSettingsWithNonce ()
  {
    final HCConversionSettings aCS = HCSettings.getMutableConversionSettings ().getClone ();
    _applySessionNonce (aCS);
    return aCS;
  }

  @Nonnull
  public static HCConversionSettings getHCConversionSettingsWithoutNamespacesWithNonce ()
  {
    final HCConversionSettings aCS = HCSettings.getConversionSettingsWithoutNamespaces ();
    _applySessionNonce (aCS);
    return aCS;
  }

  /**
   * Get the HTML MIME type to use
   *
   * @param aRequestScope
   *        The request scope. May be <code>null</code>-
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static IMimeType getMimeType (@Nullable final IRequestWebScopeWithoutResponse aRequestScope)
  {
    // Add the charset to the MIME type
    return new MimeType (CMimeType.TEXT_HTML).addParameter (CMimeType.PARAMETER_NAME_CHARSET,
                                                            HCSettings.getHTMLCharset ().name ());
  }

  @Nonnull
  public static IHCNode getCSSNode (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                    @Nonnull final ICSSPathProvider aCSS,
                                    final boolean bRegular)
  {
    final HCLink aLink = HCLink.createCSSLink (PhotonAppSettings.getCSSPath (aRequestScope, aCSS, bRegular))
                               .setMedia (aCSS.getMediaList ())
                               .setPathProvider (aCSS);
    // Set explicitly, because the resulting node does ot go through all stages
    // of preparation
    if (HCSettings.isUseNonceInStyle ())
      aLink.setNonce (CSRFSessionManager.getInstance ().getNonce ());

    final String sConditionalComment = aCSS.getConditionalComment ();
    if (StringHelper.hasText (sConditionalComment))
      return new HCConditionalCommentNode (sConditionalComment, aLink);

    return aLink;
  }

  @Nonnull
  public static IHCNode getJSNode (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                   @Nonnull final IJSPathProvider aJS,
                                   final boolean bRegular)
  {
    final HCScriptFile aScript = new HCScriptFile ().setSrc (PhotonAppSettings.getJSPath (aRequestScope, aJS, bRegular))
                                                    .setPathProvider (aJS);
    // Set explicitly, because the resulting node does not go through all stages
    // of preparation
    if (HCSettings.isUseNonceInScript ())
      aScript.setNonce (CSRFSessionManager.getInstance ().getNonce ());
    aJS.getScriptLoadingMode ().apply (aScript);

    final String sConditionalComment = aJS.getConditionalComment ();
    if (StringHelper.hasText (sConditionalComment))
      return new HCConditionalCommentNode (sConditionalComment, aScript);

    return aScript;
  }

  public static void createHTMLResponse (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                         @Nonnull final UnifiedResponse aUnifiedResponse,
                                         @Nonnull final IHTMLProvider aHTMLProvider)
  {
    // Build the main HC tree
    final HCHtml aHtml = aHTMLProvider.createHTML (aRequestScope);

    // Add some ad comment :)
    aHtml.head ()
         .metaElements ()
         .add (new HCMeta ().setName (EStandardMetaElement.GENERATOR.getName ()).setContent (META_GENERATOR_VALUE));

    // Convert HTML to String, including namespaces
    try (final NonBlockingByteArrayOutputStream aBAOS = new NonBlockingByteArrayOutputStream (50 *
                                                                                              CGlobal.BYTES_PER_KILOBYTE))
    {
      final IMimeType aMimeType = getMimeType (aRequestScope);
      final IHCConversionSettings aCS = PhotonHTMLHelper.getHCConversionSettingsWithNonce ();
      HCRenderer.writeHtmlTo (aHtml, aCS, aBAOS);

      // Write to response
      aUnifiedResponse.setMimeType (aMimeType)
                      .setCharset (aCS.getCharset ())
                      .setContent (HasInputStream.create (aBAOS))
                      .disableCaching ();
    }
  }

  /**
   * Merge external CSS and JS contents to a single resource for improved browser performance. All
   * source nodes are taken from the head and all target nodes are written to the head.
   *
   * @param aRequestScope
   *        Current request scope. Never <code>null</code>.
   * @param aHead
   *        The HTML head object. Never <code>null</code>.
   * @param bMergeCSS
   *        <code>true</code> to aggregate CSS entries.
   * @param bMergeJS
   *        <code>true</code> to aggregate JS entries.
   * @param aWSRBMgr
   *        The resource bundle provider. May not be <code>null</code>.
   */
  public static void mergeExternalCSSAndJSNodes (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                 @Nonnull final HCHead aHead,
                                                 final boolean bMergeCSS,
                                                 final boolean bMergeJS,
                                                 @Nonnull final IWebSiteResourceBundleProvider aWSRBMgr)
  {
    if (!bMergeCSS && !bMergeJS)
    {
      // Nothing to do
      return;
    }

    final boolean bRegular = HCSettings.isUseRegularResources ();

    if (bMergeCSS)
    {
      // Extract all CSS nodes for merging
      final ICommonsList <IHCNode> aCSSNodes = new CommonsArrayList <> ();
      aHead.getAllAndRemoveAllCSSNodes (aCSSNodes);

      final ICommonsList <WebSiteResourceWithCondition> aCSSs = new CommonsArrayList <> ();
      for (final IHCNode aNode : aCSSNodes)
      {
        boolean bStartMerge = true;
        if (HCCSSNodeDetector.isDirectCSSFileNode (aNode))
        {
          final ICSSPathProvider aPathProvider = ((HCLink) aNode).getPathProvider ();
          if (aPathProvider != null)
          {
            aCSSs.add (WebSiteResourceWithCondition.createForCSS (aPathProvider, bRegular));
            bStartMerge = false;
          }
        }

        if (bStartMerge)
        {
          if (!aCSSs.isEmpty ())
          {
            for (final WebSiteResourceBundleSerialized aBundle : aWSRBMgr.getResourceBundles (aCSSs, bRegular))
              aHead.addCSS (aBundle.createNode (aRequestScope));
            aCSSs.clear ();
          }

          // Add the current (non-mergable) node again to head after merging
          aHead.addCSS (aNode);
        }
      }

      // Add the remaining nodes (if any)
      if (!aCSSs.isEmpty ())
        for (final WebSiteResourceBundleSerialized aBundle : aWSRBMgr.getResourceBundles (aCSSs, bRegular))
          aHead.addCSS (aBundle.createNode (aRequestScope));
    }

    if (bMergeJS)
    {
      // Extract all JS nodes for merging
      final ICommonsList <IHCNode> aJSNodes = new CommonsArrayList <> ();
      aHead.getAllAndRemoveAllJSNodes (aJSNodes);

      final ICommonsList <WebSiteResourceWithCondition> aJSs = new CommonsArrayList <> ();
      for (final IHCNode aNode : aJSNodes)
      {
        boolean bStartMerge = true;
        if (HCJSNodeDetector.isDirectJSFileNode (aNode))
        {
          final IJSPathProvider aPathProvider = ((HCScriptFile) aNode).getPathProvider ();
          if (aPathProvider != null)
          {
            aJSs.add (WebSiteResourceWithCondition.createForJS (aPathProvider, bRegular));
            bStartMerge = false;
          }
        }

        if (bStartMerge)
        {
          if (!aJSs.isEmpty ())
          {
            for (final WebSiteResourceBundleSerialized aBundle : aWSRBMgr.getResourceBundles (aJSs, bRegular))
              aHead.addJS (aBundle.createNode (aRequestScope));
            aJSs.clear ();
          }

          // Add the current (non-mergable) node again to head after merging
          aHead.addJS (aNode);
        }
      }

      // Add the remaining nodes (if any)
      if (!aJSs.isEmpty ())
        for (final WebSiteResourceBundleSerialized aBundle : aWSRBMgr.getResourceBundles (aJSs, bRegular))
          aHead.addJS (aBundle.createNode (aRequestScope));
    }
  }
}
