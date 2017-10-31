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
package com.helger.photon.core.app.html;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.mime.MimeType;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.IHCNode;
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
import com.helger.photon.core.mgr.PhotonCoreManager;
import com.helger.photon.core.resource.WebSiteResourceBundleManager;
import com.helger.photon.core.resource.WebSiteResourceBundleSerialized;
import com.helger.photon.core.resource.WebSiteResourceWithCondition;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * A utility class for consistent HTML creation.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class PhotonHTMLHelper
{
  private PhotonHTMLHelper ()
  {}

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
    final HCLink aLink = HCLink.createCSSLink (PhotonHTMLSettings.getCSSPath (aRequestScope, aCSS, bRegular))
                               .setMedia (aCSS.getMediaList ())
                               .setPathProvider (aCSS);

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
    final HCScriptFile aScript = new HCScriptFile ().setSrc (PhotonHTMLSettings.getJSPath (aRequestScope,
                                                                                           aJS,
                                                                                           bRegular))
                                                    .setPathProvider (aJS);

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
         .add (new HCMeta ().setName (EStandardMetaElement.GENERATOR.getName ())
                            .setContent ("https://github.com/phax/ph-oton // phax // ASL 2.0"));

    // Convert HTML to String, including namespaces
    final String sXMLCode = HCRenderer.getAsHTMLString (aHtml);

    // Write to response
    final IMimeType aMimeType = getMimeType (aRequestScope);
    aUnifiedResponse.setMimeType (aMimeType)
                    .setContentAndCharset (sXMLCode, HCSettings.getHTMLCharset ())
                    .disableCaching ();
  }

  /**
   * Merge external CSS and JS contents to a single resource for improved
   * browser performance. All source nodes are taken from the head and all
   * target nodes are written to the head.
   *
   * @param aRequestScope
   *        Current request scope. Never <code>null</code>.
   * @param aHead
   *        The HTML head object. Never <code>null</code>.
   * @param bMergeCSS
   *        <code>true</code> to aggregate CSS entries.
   * @param bMergeJS
   *        <code>true</code> to aggregate JS entries.
   */
  public static void mergeExternalCSSAndJSNodes (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                 @Nonnull final HCHead aHead,
                                                 final boolean bMergeCSS,
                                                 final boolean bMergeJS)
  {
    if (!bMergeCSS && !bMergeJS)
    {
      // Nothing to do
      return;
    }

    final WebSiteResourceBundleManager aWSRBMgr = PhotonCoreManager.getWebSiteResourceBundleMgr ();
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
