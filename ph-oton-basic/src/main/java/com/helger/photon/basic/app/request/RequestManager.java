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
package com.helger.photon.basic.app.request;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.attr.AbstractReadOnlyAttributeContainer;
import com.helger.commons.locale.LocaleCache;
import com.helger.commons.locale.country.CountryCache;
import com.helger.commons.scope.ISessionScope;
import com.helger.commons.scope.mgr.ScopeManager;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.tree.withid.DefaultTreeItemWithID;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.photon.basic.app.locale.ApplicationLocaleManager;
import com.helger.photon.basic.app.locale.ILocaleManager;
import com.helger.photon.basic.app.menu.ApplicationMenuTree;
import com.helger.photon.basic.app.menu.IMenuItemPage;
import com.helger.photon.basic.app.menu.IMenuObject;
import com.helger.photon.basic.app.menu.IMenuTree;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;
import com.helger.web.scopes.domain.ISessionWebScope;
import com.helger.web.scopes.mgr.WebScopeManager;

/**
 * This class holds the per-request configuration settings.
 * <ul>
 * <li>Menu item to show</li>
 * <li>Display locale</li>
 * </ul>
 *
 * @author Philip Helger
 */
public class RequestManager implements IRequestManager
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (RequestManager.class);

  private boolean m_bUsePaths = DEFAULT_USE_PATHS;
  private String m_sRequestParamNameMenuItem = DEFAULT_REQUEST_PARAMETER_MENUITEM;
  private String m_sRequestParamNameLocale = DEFAULT_REQUEST_PARAMETER_DISPLAY_LOCALE;

  public RequestManager ()
  {}

  public boolean isUsePaths ()
  {
    return m_bUsePaths;
  }

  public void setUsePaths (final boolean bUsePaths)
  {
    m_bUsePaths = bUsePaths;
  }

  @Nonnull
  @Nonempty
  public final String getRequestParamNameMenuItem ()
  {
    return m_sRequestParamNameMenuItem;
  }

  public final void setRequestParamNameMenuItem (@Nonnull @Nonempty final String sRequestParamNameMenuItem)
  {
    ValueEnforcer.notEmpty (sRequestParamNameMenuItem, "RequestParamNameMenuItem");
    if (sRequestParamNameMenuItem.indexOf (SEPARATOR_CHAR) >= 0)
      throw new IllegalArgumentException ("Request parameter name may not contain the '" +
                                          SEPARATOR_CHAR +
                                          "' character!");
    m_sRequestParamNameMenuItem = sRequestParamNameMenuItem;
  }

  @Nonnull
  @Nonempty
  public final String getRequestParamNameLocale ()
  {
    return m_sRequestParamNameLocale;
  }

  public final void setRequestParamNameLocale (@Nonnull @Nonempty final String sRequestParamNameLocale)
  {
    ValueEnforcer.notEmpty (sRequestParamNameLocale, "RequestParamNameLocale");
    if (sRequestParamNameLocale.indexOf (SEPARATOR_CHAR) >= 0)
      throw new IllegalArgumentException ("Request parameter name may not contain the '" +
                                          SEPARATOR_CHAR +
                                          "' character!");
    m_sRequestParamNameLocale = sRequestParamNameLocale;
  }

  @Nonnull
  protected final IMenuTree getMenuTree ()
  {
    return ApplicationMenuTree.getTree ();
  }

  @Nonnull
  protected final ILocaleManager getLocaleManager ()
  {
    return ApplicationLocaleManager.getLocaleMgr ();
  }

  @Nonnull
  @Nonempty
  protected String getSessionAttrMenuItem ()
  {
    return "$ph-menuitem-" + ScopeManager.getRequestApplicationID ();
  }

  @Nonnull
  @Nonempty
  protected String getSessionAttrLocale ()
  {
    return "$ph-displaylocale-" + ScopeManager.getRequestApplicationID ();
  }

  @Nonnull
  private static Map <String, Object> _getParametersFromPath (@Nonnull final String sPath)
  {
    // Use paths for standard menu items
    final Map <String, Object> ret = new LinkedHashMap <String, Object> ();
    for (final String sPair : StringHelper.getExploded ('/', StringHelper.trimStartAndEnd (sPath, "/")))
    {
      final String [] aElements = StringHelper.getExplodedArray (SEPARATOR_CHAR, sPair, 2);
      if (aElements.length == 2)
        ret.put (aElements[0], aElements[1]);
    }
    return ret;
  }

  @Nonnull
  protected Map <String, Object> getParametersFromRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    Map <String, Object> ret = null;
    if (m_bUsePaths)
    {
      // Use paths for standard menu items
      ret = _getParametersFromPath (aRequestScope.getPathInfo ());
    }
    else
    {
      // Use request parameters
      ret = aRequestScope.getAllAttributes ();
    }
    return ret;
  }

  public void onRequestBegin (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    final Map <String, Object> aParams = getParametersFromRequest (aRequestScope);

    // determine page from request and store in request
    final String sMenuItemID = AbstractReadOnlyAttributeContainer.getAsString (m_sRequestParamNameMenuItem,
                                                                               aParams.get (m_sRequestParamNameMenuItem),
                                                                               null);
    if (sMenuItemID != null)
    {
      // Validate the menu item ID and check the display filter!
      final IMenuObject aMenuObject = getMenuTree ().getMenuObjectOfID (sMenuItemID);
      if (aMenuObject instanceof IMenuItemPage && aMenuObject.matchesDisplayFilter ())
      {
        final ISessionWebScope aSessionScope = WebScopeManager.getSessionScope (true);
        aSessionScope.setAttribute (getSessionAttrMenuItem (), aMenuObject.getID ());
      }
    }

    // determine locale from request and store in session
    final String sDisplayLocale = AbstractReadOnlyAttributeContainer.getAsString (m_sRequestParamNameLocale,
                                                                                  aParams.get (m_sRequestParamNameLocale),
                                                                                  null);
    if (sDisplayLocale != null)
    {
      final Locale aDisplayLocale = LocaleCache.getInstance ().getLocale (sDisplayLocale);
      if (aDisplayLocale != null)
      {
        // Check if the locale is present in the locale manager
        if (getLocaleManager ().isSupportedLocale (aDisplayLocale))
        {
          // A valid locale was provided
          final ISessionWebScope aSessionScope = WebScopeManager.getSessionScope (true);
          aSessionScope.setAttribute (getSessionAttrLocale (), aDisplayLocale);
        }
      }
      else
        s_aLogger.warn ("Invalid locale '" + sDisplayLocale + "' provided");
    }
  }

  @Nullable
  public IMenuItemPage getSessionMenuItem ()
  {
    final ISessionWebScope aSessionScope = WebScopeManager.getSessionScope (false);
    if (aSessionScope != null)
    {
      final String sMenuItemID = aSessionScope.getAttributeAsString (getSessionAttrMenuItem ());
      if (sMenuItemID != null)
      {
        final IMenuObject aMenuObj = getMenuTree ().getMenuObjectOfID (sMenuItemID);
        if (aMenuObj instanceof IMenuItemPage)
          return (IMenuItemPage) aMenuObj;
      }
    }
    return null;
  }

  @Nonnull
  public IMenuItemPage getRequestMenuItem ()
  {
    // Get selected item from request/session
    final IMenuItemPage aSelectedMenuItem = getSessionMenuItem ();
    if (aSelectedMenuItem != null && aSelectedMenuItem.matchesDisplayFilter ())
      return aSelectedMenuItem;

    // Use default menu item
    final IMenuTree aMenuTree = getMenuTree ();
    final IMenuItemPage aDefaultMenuItem = aMenuTree.getDefaultMenuItem ();
    if (aDefaultMenuItem != null && aDefaultMenuItem.matchesDisplayFilter ())
      return aDefaultMenuItem;

    // Last fallback: use the first menu item
    final DefaultTreeItemWithID <String, IMenuObject> aRootItem = aMenuTree.getRootItem ();
    if (aRootItem.hasChildren ())
      for (final DefaultTreeItemWithID <String, IMenuObject> aItem : aRootItem.getAllChildren ())
        if (aItem.getData () instanceof IMenuItemPage)
        {
          final IMenuItemPage aFirstMenuItem = (IMenuItemPage) aItem.getData ();
          if (aFirstMenuItem.matchesDisplayFilter ())
            return aFirstMenuItem;
        }

    throw new IllegalStateException ("No menu item is present!");
  }

  @Nonnull
  public String getRequestMenuItemID ()
  {
    return getRequestMenuItem ().getID ();
  }

  @Nonnull
  public Locale getRequestDisplayLocale ()
  {
    // Was a request locale set in session scope?
    final ISessionScope aSessionScope = WebScopeManager.getSessionScope (false);
    if (aSessionScope != null)
    {
      final Locale aSessionDisplayLocale = aSessionScope.getCastedAttribute (getSessionAttrLocale ());
      if (aSessionDisplayLocale != null)
        return aSessionDisplayLocale;
    }

    // Nothing specified - use application default locale
    final ILocaleManager aLocaleManager = getLocaleManager ();
    final Locale aDefaultLocale = aLocaleManager.getDefaultLocale ();
    if (aDefaultLocale == null)
      throw new IllegalStateException ("No default locale is specified in " + aLocaleManager);
    return aDefaultLocale;
  }

  @Nonnull
  public Locale getRequestDisplayCountry ()
  {
    return CountryCache.getInstance ().getCountry (getRequestDisplayLocale ());
  }

  @Nonnull
  public String getRequestDisplayLanguage ()
  {
    return getRequestDisplayLocale ().getLanguage ();
  }

  @Nonnull
  public SimpleURL getLinkToMenuItem (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                      @Nonnull final String sMenuItemID)
  {
    final String sPath = aRequestScope.getContextAndServletPath ();
    if (m_bUsePaths)
    {
      // Encode menuitem parameter into path
      return new SimpleURL (aRequestScope.encodeURL (sPath +
                                                     m_sRequestParamNameMenuItem +
                                                     SEPARATOR_CHAR +
                                                     sMenuItemID));
    }
    // Add menu item parameter as URL parameter
    return new SimpleURL (aRequestScope.encodeURL (sPath)).add (m_sRequestParamNameMenuItem, sMenuItemID);
  }

  @Nonnull
  protected Map <String, ?> getParametersFromURL (@Nonnull final ISimpleURL aURL)
  {
    if (m_bUsePaths)
    {
      // Use paths for standard menu items
      return _getParametersFromPath (aURL.getPath ());
    }

    // Use request parameters
    return aURL.getAllParams ();
  }

  @Nullable
  public String getMenuItemFromURL (@Nullable final ISimpleURL aURL)
  {
    if (aURL == null)
      return null;

    final Map <String, ?> aParams = getParametersFromURL (aURL);
    return AbstractReadOnlyAttributeContainer.getAsString (m_sRequestParamNameMenuItem,
                                                           aParams.get (m_sRequestParamNameMenuItem),
                                                           null);
  }

  @Nullable
  public String getLocaleFromURL (@Nullable final ISimpleURL aURL)
  {
    if (aURL == null)
      return null;

    final Map <String, ?> aParams = getParametersFromURL (aURL);
    return AbstractReadOnlyAttributeContainer.getAsString (m_sRequestParamNameLocale,
                                                           aParams.get (m_sRequestParamNameLocale),
                                                           null);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("usePaths", m_bUsePaths)
                                       .append ("requestParamMenuItem", m_sRequestParamNameMenuItem)
                                       .append ("requestParamNameLocale", m_sRequestParamNameLocale)
                                       .toString ();
  }
}
