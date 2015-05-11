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
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.GlobalDebug;
import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.html.HCHead;
import com.helger.html.hc.html.HCHtml;
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
import com.helger.photon.core.resource.WebSiteResourceBundleSerialized;
import com.helger.photon.core.resource.WebSiteResourceWithCondition;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;

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
  protected boolean useRegularResources ()
  {
    return GlobalDebug.isDebugMode ();
  }

  @OverrideOnDemand
  protected boolean useWebSiteResourceBundlesForCSS ()
  {
    return ResourceBundleServlet.isActive ();
  }

  @OverrideOnDemand
  protected boolean useWebSiteResourceBundlesForJS ()
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
   * Add all meta elements to the HTML head element.
   *
   * @param aRequestScope
   *        Current request scope. Never <code>null</code>.
   * @param aHead
   *        The HTML head object. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void addMetaElementsToHead (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                        @Nonnull final HCHead aHead)
  {
    final List <IMetaElement> aMetaElements = PhotonMetaElements.getAllRegisteredMetaElementsForGlobal ();
    PhotonMetaElements.getAllRegisteredMetaElementsForThisRequest (aMetaElements);

    // add Special meta tag
    final IMimeType aMimeType = PhotonHTMLHelper.getMimeType (aRequestScope);
    aMetaElements.add (0, EStandardMetaElement.CONTENT_TYPE.getAsMetaElement (aMimeType.getAsString ()));

    for (final IMetaElement aMetaElement : aMetaElements)
      aHead.getMetaElementList ().addMetaElement (aMetaElement);
  }

  /**
   * Add all CSS include to the HTML head element.
   *
   * @param aRequestScope
   *        Current request scope. Never <code>null</code>.
   * @param aHead
   *        The HTML head object. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void addCSSToHead (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope, @Nonnull final HCHead aHead)
  {
    final boolean bRegular = useRegularResources ();
    final boolean bAggregateCSS = useWebSiteResourceBundlesForCSS ();

    // Add configured and per-request CSS
    final Set <ICSSPathProvider> aCSSs = PhotonCSS.getAllRegisteredCSSIncludesForGlobal ();
    PhotonCSS.getAllRegisteredCSSIncludesForThisRequest (aCSSs);

    if (bAggregateCSS)
    {
      final List <WebSiteResourceWithCondition> aCSSRes = new ArrayList <WebSiteResourceWithCondition> ();
      for (final ICSSPathProvider aCSS : aCSSs)
        aCSSRes.add (new WebSiteResourceWithCondition (aCSS, bRegular));
      for (final WebSiteResourceBundleSerialized aBundle : PhotonCoreManager.getWebSiteResourceBundleMgr ()
                                                                            .getResourceBundles (aCSSRes, bRegular))
        aHead.addCSS (aBundle.createNode (aRequestScope));
    }
    else
    {
      for (final ICSSPathProvider aCSS : aCSSs)
        aHead.addCSS (PhotonHTMLHelper.getCSSNode (aRequestScope, aCSS, bRegular));
    }
  }

  /**
   * Add all JS includes to the HTML head element.
   *
   * @param aRequestScope
   *        Current request scope. Never <code>null</code>.
   * @param aHead
   *        The HTML head object. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void addJSToHead (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope, @Nonnull final HCHead aHead)
  {
    final boolean bRegular = useRegularResources ();
    final boolean bAggregateJS = useWebSiteResourceBundlesForJS ();

    // Add all configured and per-request JS
    final Set <IJSPathProvider> aJSs = PhotonJS.getAllRegisteredJSIncludesForGlobal ();
    PhotonJS.getAllRegisteredJSIncludesForThisRequest (aJSs);

    if (bAggregateJS)
    {
      final List <WebSiteResourceWithCondition> aJSRes = new ArrayList <WebSiteResourceWithCondition> ();
      for (final IJSPathProvider aJS : aJSs)
        aJSRes.add (new WebSiteResourceWithCondition (aJS, bRegular));
      for (final WebSiteResourceBundleSerialized aBundle : PhotonCoreManager.getWebSiteResourceBundleMgr ()
                                                                            .getResourceBundles (aJSRes, bRegular))
        aHead.addJS (aBundle.createNode (aRequestScope));
    }
    else
    {
      for (final IJSPathProvider aJS : aJSs)
        aHead.addJS (PhotonHTMLHelper.getJSNode (aRequestScope, aJS, bRegular));
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

    // Add all configured meta elements
    addMetaElementsToHead (aRequestScope, aHead);

    // Add CSS and JS
    addCSSToHead (aRequestScope, aHead);
    addJSToHead (aRequestScope, aHead);
  }

  /**
   * Fill the HTML body
   *
   * @param aSWEC
   *        Web execution context
   * @param aHtml
   *        HTML object to be filled
   */
  protected abstract void fillBody (@Nonnull final ISimpleWebExecutionContext aSWEC, @Nonnull HCHtml aHtml) throws ForcedRedirectException;

  @Nonnull
  public final HCHtml createHTML (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope) throws ForcedRedirectException
  {
    final Locale aDisplayLocale = getDisplayLocale ();

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

    return aHtml;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).toString ();
  }
}
