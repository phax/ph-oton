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
package com.helger.photon.basic.app.request;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.scope.mgr.ScopeManager;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.photon.basic.app.PhotonPathMapper;
import com.helger.photon.basic.app.PhotonSessionState;
import com.helger.photon.basic.app.locale.ApplicationLocaleManager;
import com.helger.photon.basic.app.menu.ApplicationMenuTree;
import com.helger.photon.basic.app.menu.IMenuItemPage;
import com.helger.photon.basic.app.menu.IMenuObject;
import com.helger.photon.basic.app.menu.IMenuTree;
import com.helger.tree.withid.DefaultTreeItemWithID;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.singleton.AbstractGlobalWebSingleton;

/**
 * This class holds the per-request configuration settings.
 * <ul>
 * <li>Menu item to show</li>
 * <li>Display locale</li>
 * </ul>
 *
 * @author Philip Helger
 * @since 7.0.2
 */
@NotThreadSafe
public class RequestParameterManager extends AbstractGlobalWebSingleton implements IRequestParameterManager
{
  private IRequestParameterHandler m_aRequestParamHdl = new RequestParameterHandlerURLParameter ();

  @Deprecated
  @UsedViaReflection
  public RequestParameterManager ()
  {}

  @Nonnull
  public static RequestParameterManager getInstance ()
  {
    return getGlobalSingleton (RequestParameterManager.class);
  }

  @Nonnull
  public final IRequestParameterHandler getParameterHandler ()
  {
    return m_aRequestParamHdl;
  }

  public final void setParameterHandler (@Nonnull final IRequestParameterHandler aRequestParameterHdl)
  {
    ValueEnforcer.notNull (aRequestParameterHdl, "RequestParameterHdl");
    m_aRequestParamHdl = aRequestParameterHdl;
  }

  public void onRequestBegin (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                              @Nonnull @Nonempty final String sApplicationID)
  {
    final PhotonRequestParameters aParams = m_aRequestParamHdl.getParametersFromRequest (aRequestScope);

    // determine menu item ID from request
    final IMenuItemPage aMenuItem = aParams.getMenuItem ();
    if (aMenuItem != null)
    {
      // Store in session
      PhotonSessionState.getInstance ().setSelectedMenuItem (sApplicationID, aMenuItem);
    }

    // determine locale from request
    final Locale aDisplayLocale = aParams.getLocale ();
    if (aDisplayLocale != null)
    {
      // Store in session
      PhotonSessionState.getInstance ().setSelectedLocale (sApplicationID, aDisplayLocale);
    }
  }

  @Nullable
  public IMenuItemPage getSessionMenuItem ()
  {
    return PhotonSessionState.getSelectedMenuItemOfCurrentSession (ScopeManager.getRequestApplicationID ());
  }

  @Nonnull
  public IMenuItemPage getRequestMenuItem ()
  {
    // Get selected item from request/session
    final IMenuItemPage aSelectedMenuItem = getSessionMenuItem ();
    if (aSelectedMenuItem != null && aSelectedMenuItem.matchesDisplayFilter ())
      return aSelectedMenuItem;

    // Use default menu item
    final IMenuTree aMenuTree = ApplicationMenuTree.getTree ();
    final IMenuItemPage aDefaultMenuItem = aMenuTree.getDefaultMenuItem ();
    if (aDefaultMenuItem != null && aDefaultMenuItem.matchesDisplayFilter ())
      return aDefaultMenuItem;

    // Last fallback: use the first menu item
    final DefaultTreeItemWithID <String, IMenuObject> aRootItem = aMenuTree.getRootItem ();
    if (aRootItem.hasChildren ())
    {
      final IMenuItemPage ret = aRootItem.findFirstChildMapped (aItem -> aItem.getData () instanceof IMenuItemPage &&
                                                                         aItem.getData ().matchesDisplayFilter (),
                                                                aItem -> (IMenuItemPage) aItem.getData ());
      if (ret != null)
        return ret;
    }

    throw new IllegalStateException ("No menu item is present!");

  }

  @Nullable
  public Locale getSessionDisplayLocale ()
  {
    // Was a request locale set in session scope?
    final Locale aSessionDisplayLocale = PhotonSessionState.getSelectedLocaleOfCurrentSession (ScopeManager.getRequestApplicationID ());
    if (aSessionDisplayLocale != null)
      return aSessionDisplayLocale;

    // Nothing specified - use application default locale
    return ApplicationLocaleManager.getLocaleMgr ().getDefaultLocale ();
  }

  @Nonnull
  public Locale getRequestDisplayLocale ()
  {
    final Locale aLocale = getSessionDisplayLocale ();
    if (aLocale == null)
      throw new IllegalStateException ("No session locale and no default locale is available");
    return aLocale;
  }

  @Nonnull
  public SimpleURL getLinkToMenuItem (@Nonnull @Nonempty final String sApplicationID,
                                      @Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                      @Nonnull final Locale aDisplayLocale,
                                      @Nonnull final String sMenuItemID)
  {
    // Get the servlet path from the app ID
    final String sServletPath = PhotonPathMapper.getApplicationServletPathOfApplicationIDOrDefault (sApplicationID);
    if (sServletPath == null)
      throw new IllegalStateException ("Failed to determine the servlet path for app ID '" +
                                       sApplicationID +
                                       "'. Please make sure you initialized PhotonPathMapper correctly!");

    // Prepend the context path
    final String sBasePath = aRequestScope.getContextPath () + sServletPath;
    return m_aRequestParamHdl.buildURL (aRequestScope, sBasePath, aDisplayLocale, sMenuItemID);
  }

  @Nullable
  public String getMenuItemFromURL (@Nullable final ISimpleURL aURL)
  {
    if (aURL == null)
      return null;

    return m_aRequestParamHdl.getParametersFromURL (aURL).getMenuItemAsString ();
  }

  @Nullable
  public String getLocaleFromURL (@Nullable final ISimpleURL aURL)
  {
    if (aURL == null)
      return null;

    return m_aRequestParamHdl.getParametersFromURL (aURL).getLocaleAsString ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("RequestParamHandler", m_aRequestParamHdl).toString ();
  }
}
