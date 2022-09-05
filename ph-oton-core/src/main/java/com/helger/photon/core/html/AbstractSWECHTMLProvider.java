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

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.html.hc.html.metadata.HCHead;
import com.helger.html.hc.html.root.HCHtml;
import com.helger.photon.app.html.IHTMLProvider;
import com.helger.photon.core.appid.RequestSettings;
import com.helger.photon.core.execcontext.ISimpleWebExecutionContext;
import com.helger.photon.core.execcontext.SimpleWebExecutionContext;
import com.helger.photon.core.menu.IMenuTree;
import com.helger.photon.security.login.LoggedInUserManager;
import com.helger.photon.security.user.IUser;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.xservlet.forcedredirect.ForcedRedirectException;

/**
 * Abstract {@link IHTMLProvider} implementation based on
 * {@link SimpleWebExecutionContext}.
 *
 * @author Philip Helger
 */
public abstract class AbstractSWECHTMLProvider extends AbstractHTMLProvider
{
  public AbstractSWECHTMLProvider ()
  {}

  /**
   * Fill the HTML body
   *
   * @param aSWEC
   *        Web execution context
   * @param aHtml
   *        HTML object to be filled
   * @throws ForcedRedirectException
   *         to indicate that the current page should be left.
   */
  protected abstract void fillBody (@Nonnull final ISimpleWebExecutionContext aSWEC,
                                    @Nonnull final HCHtml aHtml) throws ForcedRedirectException;

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
    final HCHead aHead = aHtml.head ();

    // Add all meta elements
    addMetaElements (aRequestScope, aHead);
  }

  /**
   * Overridable method to fill head and body. The default implementation uses
   * an {@link SimpleWebExecutionContext} which in turn requires a menu tree to
   * be present. If you have an application without a menu tree, override this
   * method.
   *
   * @param aRequestScope
   *        Current request scope
   * @param aHtml
   *        Created (empty) HTML node
   * @param aDisplayLocale
   *        The display locale of the current request
   */
  @Override
  @OverrideOnDemand
  protected void fillHeadAndBody (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                  @Nonnull final HCHtml aHtml,
                                  @Nonnull final Locale aDisplayLocale)
  {
    final IMenuTree aMenuTree = RequestSettings.getMenuTree (aRequestScope);
    final IUser aLoggedInUser = LoggedInUserManager.getInstance ().getCurrentUser ();

    // Build the execution scope
    final ISimpleWebExecutionContext aSWEC = new SimpleWebExecutionContext (aRequestScope, aDisplayLocale, aMenuTree, aLoggedInUser);

    // fill body
    fillBody (aSWEC, aHtml);

    // build HTML header (after body for per-request stuff)
    fillHead (aSWEC, aHtml);
  }
}
