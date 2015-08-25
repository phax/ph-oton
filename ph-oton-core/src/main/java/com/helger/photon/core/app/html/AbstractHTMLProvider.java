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
package com.helger.photon.core.app.html;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.locale.LocaleHelper;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.html.metadata.HCCSSNodeDetector;
import com.helger.html.hc.html.metadata.HCHead;
import com.helger.html.hc.html.metadata.HCLink;
import com.helger.html.hc.html.metadata.HCMeta;
import com.helger.html.hc.html.root.HCHtml;
import com.helger.html.hc.html.script.HCJSNodeDetector;
import com.helger.html.hc.html.script.HCScriptFile;
import com.helger.html.hc.render.HCRenderer;
import com.helger.html.meta.EStandardMetaElement;
import com.helger.html.meta.IMetaElement;
import com.helger.html.resource.css.ICSSPathProvider;
import com.helger.html.resource.js.IJSPathProvider;
import com.helger.photon.basic.app.menu.ApplicationMenuTree;
import com.helger.photon.basic.app.menu.IMenuTree;
import com.helger.photon.basic.app.request.ApplicationRequestManager;
import com.helger.photon.core.app.context.ISimpleWebExecutionContext;
import com.helger.photon.core.app.context.SimpleWebExecutionContext;
import com.helger.photon.core.app.redirect.ForcedRedirectException;
import com.helger.photon.core.mgr.PhotonCoreManager;
import com.helger.photon.core.resource.ResourceBundleServlet;
import com.helger.photon.core.resource.WebSiteResourceBundleManager;
import com.helger.photon.core.resource.WebSiteResourceBundleSerialized;
import com.helger.photon.core.resource.WebSiteResourceWithCondition;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Main class for creating HTML output
 *
 * @author Philip Helger
 */
public abstract class AbstractHTMLProvider implements IHTMLProvider
{
  public AbstractHTMLProvider ()
  {}

  @OverrideOnDemand
  protected boolean isUseWebSiteResourceBundlesForCSS ()
  {
    return ResourceBundleServlet.isActive ();
  }

  @OverrideOnDemand
  protected boolean isUseWebSiteResourceBundlesForJS ()
  {
    return ResourceBundleServlet.isActive ();
  }

  @Nonnull
  @OverrideOnDemand
  protected Locale getDisplayLocale ()
  {
    return ApplicationRequestManager.getRequestMgr ().getRequestDisplayLocale ();
  }

  @Nonnull
  protected final IMenuTree getMenuTree ()
  {
    return ApplicationMenuTree.getTree ();
  }

  @OverrideOnDemand
  @Nonnull
  protected HCHtml createHCHtml (@Nonnull final Locale aDisplayLocale)
  {
    return new HCHtml ().setLanguage (aDisplayLocale.getLanguage ());
  }

  /**
   * Fill the HTML body
   *
   * @param aSWEC
   *        Web execution context
   * @param aHtml
   *        HTML object to be filled
   */
  protected abstract void fillBody (@Nonnull final ISimpleWebExecutionContext aSWEC,
                                    @Nonnull HCHtml aHtml) throws ForcedRedirectException;

  /**
   * Add all meta elements to the HTML head element.
   *
   * @param aRequestScope
   *        Current request scope. Never <code>null</code>.
   * @param aHead
   *        The HTML head object. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void addMetaElements (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                  @Nonnull final HCHead aHead)
  {
    final List <IMetaElement> aMetaElements = new ArrayList <IMetaElement> ();
    {
      // add special meta element at the beginning
      final IMimeType aMimeType = PhotonHTMLHelper.getMimeType (aRequestScope);
      aMetaElements.add (EStandardMetaElement.CONTENT_TYPE.getAsMetaElement (aMimeType.getAsString ()));
    }
    PhotonMetaElements.getAllRegisteredMetaElementsForGlobal (aMetaElements);
    PhotonMetaElements.getAllRegisteredMetaElementsForThisRequest (aMetaElements);

    for (final IMetaElement aMetaElement : aMetaElements)
      for (final Map.Entry <Locale, String> aEntry : aMetaElement.getContent ().entrySet ())
      {
        final HCMeta aMeta = new HCMeta ();
        if (aMetaElement.isHttpEquiv ())
          aMeta.setHttpEquiv (aMetaElement.getName ());
        else
          aMeta.setName (aMetaElement.getName ());

        aMeta.setContent (aEntry.getValue ());

        final Locale aContentLocale = aEntry.getKey ();
        if (aContentLocale != null && !LocaleHelper.isSpecialLocale (aContentLocale))
          aMeta.setLanguage (aContentLocale.toString ());

        aHead.addMetaElement (aMeta);
      }
  }

  /**
   * Add all global and per-request CSS and JS includes to the HTML page.
   *
   * @param aRequestScope
   *        Current request scope. Never <code>null</code>.
   * @param aHtml
   *        The current HTML object. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void addGlobalAndPerRequestCSSAndJS (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                 @Nonnull final HCHtml aHtml)
  {
    final boolean bRegular = HCSettings.isUseRegularResources ();
    final HCHead aHead = aHtml.getHead ();

    // Add configured and per-request CSS
    final Set <ICSSPathProvider> aCSSs = PhotonCSS.getAllRegisteredCSSIncludesForGlobal ();
    PhotonCSS.getAllRegisteredCSSIncludesForThisRequest (aCSSs);

    // Add each CSS separately
    for (final ICSSPathProvider aCSS : aCSSs)
      aHead.addCSS (PhotonHTMLHelper.getCSSNode (aRequestScope, aCSS, bRegular));

    // Add all configured and per-request JS
    final Set <IJSPathProvider> aJSs = PhotonJS.getAllRegisteredJSIncludesForGlobal ();
    PhotonJS.getAllRegisteredJSIncludesForThisRequest (aJSs);

    // Add each JS separately
    for (final IJSPathProvider aJS : aJSs)
      aHead.addJS (PhotonHTMLHelper.getJSNode (aRequestScope, aJS, bRegular));
  }

  /**
   * Add all CSS include to the HTML head element.
   *
   * @param aRequestScope
   *        Current request scope. Never <code>null</code>.
   * @param aHtml
   *        The current HTML object. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void aggregateCSSAndJS (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                    @Nonnull final HCHtml aHtml)
  {
    final boolean bAggregateCSS = isUseWebSiteResourceBundlesForCSS ();
    final boolean bAggregateJS = isUseWebSiteResourceBundlesForJS ();

    if (!bAggregateCSS && !bAggregateJS)
    {
      // Nothing to do
      return;
    }

    final WebSiteResourceBundleManager aWSRBMgr = PhotonCoreManager.getWebSiteResourceBundleMgr ();
    final boolean bRegular = HCSettings.isUseRegularResources ();
    final HCHead aHead = aHtml.getHead ();

    if (bAggregateCSS)
    {
      // Extract all CSS nodes for merging
      final List <IHCNode> aCSSNodes = new ArrayList <> ();
      aHead.getAllAndRemoveAllCSSNodes (aCSSNodes);

      final List <WebSiteResourceWithCondition> aCSSs = new ArrayList <> ();
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

    if (bAggregateJS)
    {
      // Extract all JS nodes for merging
      final List <IHCNode> aJSNodes = new ArrayList <> ();
      aHead.getAllAndRemoveAllJSNodes (aJSNodes);

      final List <WebSiteResourceWithCondition> aJSs = new ArrayList <> ();
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

  /**
   * Fill the HTML HEAD element.
   *
   * @param aSWEC
   *        Web execution context
   * @param aHtml
   *        The HTML object to be filled.
   */
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void fillHead (@Nonnull final ISimpleWebExecutionContext aSWEC, @Nonnull final HCHtml aHtml)
  {
    final IRequestWebScopeWithoutResponse aRequestScope = aSWEC.getRequestScope ();
    final HCHead aHead = aHtml.getHead ();

    // Add all meta elements
    addMetaElements (aRequestScope, aHead);
  }

  @Nonnull
  public final HCHtml createHTML (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope) throws ForcedRedirectException
  {
    final Locale aDisplayLocale = getDisplayLocale ();
    final IHCConversionSettingsToNode aConversionSettings = HCSettings.getConversionSettings ();

    // Build the execution scope
    final ISimpleWebExecutionContext aSWEC = new SimpleWebExecutionContext (aRequestScope,
                                                                            aDisplayLocale,
                                                                            getMenuTree ());

    // Create the surrounding HTML element
    final HCHtml aHtml = createHCHtml (aDisplayLocale);

    // fill body
    fillBody (aSWEC, aHtml);

    // build HTML header (after body for per-request stuff)
    fillHead (aSWEC, aHtml);

    // customize, finalize and extract resources
    // This must be done before the CSS and JS are included because per-request
    // resource registration happens inside
    HCRenderer.prepareForConversion (aHtml, aHtml.getBody (), aConversionSettings);

    // Add global and per-request CSS and JS
    addGlobalAndPerRequestCSSAndJS (aRequestScope, aHtml);

    // Extract and merge all out-of-band nodes
    if (aConversionSettings.isExtractOutOfBandNodes ())
    {
      final List <IHCNode> aOOBNodes = aHtml.getAllOutOfBandNodesWithMergedInlineNodes ();
      aHtml.addAllOutOfBandNodesToHead (aOOBNodes);
    }

    // Aggregate CSS and JS using the WebSiteResourceBundleManager etc.
    aggregateCSSAndJS (aRequestScope, aHtml);

    // Move scripts to body? If so, after aggregation!
    if (HCSettings.isScriptsInBody ())
      aHtml.moveScriptElementsToBody ();

    // This is only required so that the additional CSS/JS nodes on the head get
    // the correct EHCNodeState
    if (false)
      HCRenderer.prepareForConversion (aHtml.getHead (), aHtml.getBody (), aConversionSettings);

    return aHtml;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).toString ();
  }
}
