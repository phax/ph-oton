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
package com.helger.photon.core.html;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsOrderedSet;
import com.helger.commons.locale.LocaleHelper;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.html.metadata.HCHead;
import com.helger.html.hc.html.metadata.HCMeta;
import com.helger.html.hc.html.root.HCHtml;
import com.helger.html.hc.render.HCRenderer;
import com.helger.html.meta.EStandardMetaElement;
import com.helger.html.meta.IMetaElement;
import com.helger.html.resource.css.ICSSPathProvider;
import com.helger.html.resource.js.IJSPathProvider;
import com.helger.photon.app.PhotonAppManager;
import com.helger.photon.app.PhotonAppSettings;
import com.helger.photon.app.html.IHTMLProvider;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.app.html.PhotonHTMLHelper;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.app.html.PhotonMetaElements;
import com.helger.photon.core.appid.RequestSettings;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.xservlet.forcedredirect.ForcedRedirectException;

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
  @Nonnull
  protected HCHtml createHCHtml (@Nonnull final Locale aDisplayLocale)
  {
    return new HCHtml ().setLanguage (aDisplayLocale.getLanguage ());
  }

  /**
   * Add all meta elements to the HTML head element.
   *
   * @param aRequestScope
   *        Current request scope. Never <code>null</code>.
   * @param aHead
   *        The HTML head object. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void addMetaElements (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope, @Nonnull final HCHead aHead)
  {
    final ICommonsList <IMetaElement> aMetaElements = new CommonsArrayList <> ();
    {
      // add special meta element at the beginning
      final IMimeType aMimeType = PhotonHTMLHelper.getMimeType (aRequestScope);
      aMetaElements.add (EStandardMetaElement.CONTENT_TYPE.getAsMetaElement (aMimeType.getAsString ()));
    }
    PhotonMetaElements.getAllRegisteredMetaElementsForGlobal (aMetaElements);
    PhotonMetaElements.getAllRegisteredMetaElementsForThisRequest (aMetaElements);

    for (final IMetaElement aMetaElement : aMetaElements)
      for (final Map.Entry <Locale, String> aEntry : aMetaElement.getContent ())
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

        aHead.metaElements ().add (aMeta);
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
  protected void addGlobalAndPerRequestCSSAndJS (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope, @Nonnull final HCHtml aHtml)
  {
    final boolean bRegular = HCSettings.isUseRegularResources ();
    final HCHead aHead = aHtml.head ();

    // Add configured and per-request CSS
    final ICommonsOrderedSet <ICSSPathProvider> aCSSs = PhotonCSS.getAllRegisteredCSSIncludesForGlobal ();
    PhotonCSS.getAllRegisteredCSSIncludesForThisRequest (aCSSs);

    // Add each CSS separately
    for (final ICSSPathProvider aCSS : aCSSs)
      aHead.addCSS (PhotonHTMLHelper.getCSSNode (aRequestScope, aCSS, bRegular));

    // Add all configured and per-request JS
    final ICommonsOrderedSet <IJSPathProvider> aJSs = PhotonJS.getAllRegisteredJSIncludesForGlobal ();
    PhotonJS.getAllRegisteredJSIncludesForThisRequest (aJSs);

    // Add each JS separately
    for (final IJSPathProvider aJS : aJSs)
      aHead.addJS (PhotonHTMLHelper.getJSNode (aRequestScope, aJS, bRegular));
  }

  /**
   * Overridable method to fill head and body.
   *
   * @param aRequestScope
   *        Current request scope
   * @param aHtml
   *        Created (empty) HTML node
   * @param aDisplayLocale
   *        The display locale of the current request
   */
  protected abstract void fillHeadAndBody (@Nonnull IRequestWebScopeWithoutResponse aRequestScope,
                                           @Nonnull HCHtml aHtml,
                                           @Nonnull Locale aDisplayLocale);

  @Nonnull
  public final HCHtml createHTML (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope) throws ForcedRedirectException
  {
    final Locale aDisplayLocale = RequestSettings.getDisplayLocale (aRequestScope);
    final IHCConversionSettingsToNode aConversionSettings = HCSettings.getConversionSettings ();

    // Create the surrounding HTML element
    final HCHtml aHtml = createHCHtml (aDisplayLocale);

    // Main content filling
    fillHeadAndBody (aRequestScope, aHtml, aDisplayLocale);

    // customize, finalize and extract resources
    // This must be done before the CSS and JS are included because per-request
    // resource registration happens inside
    HCRenderer.prepareForConversion (aHtml, aHtml.body (), aConversionSettings);

    // Add global and per-request CSS and JS
    addGlobalAndPerRequestCSSAndJS (aRequestScope, aHtml);

    // Extract and merge all inline out-of-band nodes
    if (aConversionSettings.isExtractOutOfBandNodes ())
    {
      final ICommonsList <IHCNode> aOOBNodes = aHtml.getAllOutOfBandNodesWithMergedInlineNodes ();
      aHtml.addAllOutOfBandNodesToHead (aOOBNodes);
    }

    // Merge all external CSS and JS nodes
    PhotonHTMLHelper.mergeExternalCSSAndJSNodes (aRequestScope,
                                                 aHtml.head (),
                                                 PhotonAppSettings.isMergeCSSResources (),
                                                 PhotonAppSettings.isMergeJSResources (),
                                                 PhotonAppManager.getWebSiteResourceBundleMgr ());

    // Move scripts to body? If so, after aggregation!
    if (HCSettings.isScriptsInBody ())
      aHtml.moveScriptElementsToBody ();

    // This is only required so that the additional CSS/JS nodes on the head get
    // the correct EHCNodeState
    if (false)
      HCRenderer.prepareForConversion (aHtml.head (), aHtml.body (), aConversionSettings);

    return aHtml;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).getToString ();
  }
}
