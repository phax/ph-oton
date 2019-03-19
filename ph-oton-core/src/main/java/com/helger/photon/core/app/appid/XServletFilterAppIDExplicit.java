/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.core.app.appid;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.photon.core.app.locale.GlobalLocaleManager;
import com.helger.photon.core.app.menu.IMenuItemPage;
import com.helger.photon.core.app.menu.IMenuObject;
import com.helger.photon.core.app.menu.IMenuTree;
import com.helger.photon.core.app.request.PhotonRequestParameters;
import com.helger.photon.core.app.request.RequestParameterManager;
import com.helger.tree.withid.DefaultTreeItemWithID;
import com.helger.web.scope.IRequestWebScope;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.xservlet.filter.IXServletHighLevelFilter;

/**
 * XServlet filter responsible for remembering the application ID in the current
 * request
 *
 * @author Philip Helger
 */
public final class XServletFilterAppIDExplicit implements IXServletHighLevelFilter
{
  private final String m_sAppID;

  public XServletFilterAppIDExplicit (@Nonnull @Nonempty final String sAppID)
  {
    m_sAppID = ValueEnforcer.notEmpty (sAppID, "AppID");
  }

  public static void setStatePerApp (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                     @Nonnull final String sAppID)
  {
    // It's important to create a session here!
    final PhotonSessionState aSessionState = PhotonSessionState.getInstance ();

    // Remember AppID in session
    aSessionState.setLastApplicationID (sAppID);
    final PhotonSessionStatePerApp aAppSessionState = aSessionState.state (sAppID);
    final PhotonGlobalStatePerApp aAppGlobalState = PhotonGlobalState.state (sAppID);

    // Get menu tree
    IMenuTree aMenuTree = aAppSessionState.getMenuTree ();
    if (aMenuTree == null)
    {
      aMenuTree = aAppGlobalState.getMenuTree ();
      if (aMenuTree != null)
      {
        // Remember in session so that the next lookup is more efficient
        aAppSessionState.setMenuTree (aMenuTree);
      }
    }
    if (aMenuTree == null)
      throw new IllegalStateException ("Failed to resolve MenuTree for request using application ID '" + sAppID + "'!");

    // Run default request initialization (menu item and locale)
    final PhotonRequestParameters aParams = RequestParameterManager.getInstance ()
                                                                   .getParameterHandler ()
                                                                   .getParametersFromRequest (aRequestScope, aMenuTree);
    // determine menu item ID from request
    IMenuItemPage aMenuItem = aParams.getMenuItem ();
    if (aMenuItem == null)
    {
      // None provided in URL
      // -- check if one is in session
      aMenuItem = aAppSessionState.getMenuItem ();
      if (aMenuItem == null)
      {
        // None in request, none in session
        // -- check for default item
        final IMenuItemPage aDefaultMenuItem = aMenuTree.getDefaultMenuItem ();
        if (aDefaultMenuItem != null && aDefaultMenuItem.matchesDisplayFilter ())
          aMenuItem = aDefaultMenuItem;

        if (aMenuItem == null)
        {
          // Last fallback: use the first menu item
          final DefaultTreeItemWithID <String, IMenuObject> aRootItem = aMenuTree.getRootItem ();
          if (aRootItem.hasChildren ())
          {
            final IMenuItemPage aFirstMenuItem = aRootItem.findFirstChildMapped (aItem -> aItem.getData () instanceof IMenuItemPage &&
                                                                                          aItem.getData ()
                                                                                               .matchesDisplayFilter (),
                                                                                 aItem -> (IMenuItemPage) aItem.getData ());
            if (aFirstMenuItem != null)
              aMenuItem = aFirstMenuItem;
          }
        }
      }
    }

    if (aMenuItem == null)
      throw new IllegalStateException ("No menu item is present for application ID '" + sAppID + "'!");

    // Store in all scopes
    aAppSessionState.setMenuItem (aMenuItem);

    // determine locale from request
    Locale aDisplayLocale = aParams.getLocale ();
    if (aDisplayLocale == null)
    {
      // Was a request locale set in session scope?
      final Locale aSessionDisplayLocale = aAppSessionState.getDisplayLocale ();
      if (aSessionDisplayLocale != null)
        aDisplayLocale = aSessionDisplayLocale;

      if (aDisplayLocale == null)
      {
        // Nothing specified - use default locale
        aDisplayLocale = GlobalLocaleManager.getInstance ().getDefaultLocale ();

        if (aDisplayLocale == null)
          throw new IllegalStateException ("No locale could be determined for application ID '" + sAppID + "!");
      }
    }

    {
      // Store in all scopes
      aAppSessionState.setDisplayLocale (aDisplayLocale);
    }

    RequestSettings.setRequestState (aRequestScope,
                                     sAppID,
                                     new PhotonRequestState (aMenuTree, aMenuItem, aDisplayLocale));
  }

  public void beforeRequest (@Nonnull final IRequestWebScope aRequestScope)
  {
    setStatePerApp (aRequestScope, m_sAppID);
  }

  public void afterRequest (@Nonnull final IRequestWebScope aRequestScope)
  {
    // empty
  }
}
