/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.core.appid;

import java.util.Locale;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.photon.core.menu.IMenuItemPage;
import com.helger.photon.core.menu.IMenuTree;
import com.helger.scope.mgr.ScopeManager;
import com.helger.text.locale.country.CountryCache;
import com.helger.typeconvert.collection.IAttributeContainerAny;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * This is a utility class to get the
 *
 * @author Philip Helger
 */
public final class RequestSettings
{
  public static final String REQUEST_ATTR_APP_ID = ScopeManager.SCOPE_ATTRIBUTE_PREFIX_INTERNAL + "app-id";
  public static final String REQUEST_ATTR_STATE = ScopeManager.SCOPE_ATTRIBUTE_PREFIX_INTERNAL + "app-state";

  private RequestSettings ()
  {}

  @Nullable
  private static String _getApplicationIDOrNull (@NonNull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    String sAppID = aRequestScope.attrs ().getCastedValue (REQUEST_ATTR_APP_ID);
    if (StringHelper.isEmpty (sAppID))
    {
      // Fallback to last saved state from session
      final PhotonSessionState aPSS = PhotonSessionState.getInstanceIfInstantiated ();
      if (aPSS != null)
        sAppID = aPSS.getLastApplicationID ();

      // None in request nor session -> fall back to default
      if (StringHelper.isEmpty (sAppID))
        sAppID = PhotonGlobalState.getInstance ().getDefaultApplicationID ();
    }
    return sAppID;
  }

  @NonNull
  @Nonempty
  public static String getApplicationID (@NonNull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    final String sAppID = _getApplicationIDOrNull (aRequestScope);
    if (StringHelper.isEmpty (sAppID))
      throw new IllegalStateException ("No app ID is present!");
    return sAppID;
  }

  @Nullable
  private static PhotonRequestState _getRequestStateOrNull (@NonNull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    PhotonRequestState ret = aRequestScope.attrs ().getCastedValue (REQUEST_ATTR_STATE);
    if (ret == null)
    {
      // Fallback to last saved state from session
      final String sAppID = _getApplicationIDOrNull (aRequestScope);
      if (StringHelper.isNotEmpty (sAppID))
      {
        final PhotonSessionState aSessionState = PhotonSessionState.getInstanceIfInstantiated ();
        if (aSessionState != null)
        {
          final PhotonSessionStatePerApp aSessionStatePerApp = aSessionState.state (sAppID);
          // Is e.g. empty if a new session state was created!
          if (aSessionStatePerApp.isNotEmpty ())
            ret = new PhotonRequestState (aSessionStatePerApp);
        }

        // Global state as last resort
        if (ret == null)
          ret = new PhotonRequestState (PhotonGlobalState.state (sAppID));
      }
      // else - no app ID - we're lost
    }
    return ret;
  }

  @NonNull
  public static PhotonRequestState getRequestState (@NonNull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    final PhotonRequestState aState = _getRequestStateOrNull (aRequestScope);
    if (aState == null)
      throw new IllegalStateException ("No state is present!");
    return aState;
  }

  @NonNull
  public static IMenuTree getMenuTree (@NonNull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    final IMenuTree aMenuTree = getRequestState (aRequestScope).getMenuTree ();
    if (aMenuTree == null)
      throw new IllegalStateException ("No menu tree is present!");
    return aMenuTree;
  }

  @NonNull
  public static IMenuItemPage getMenuItem (@NonNull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    final IMenuItemPage aMenuItem = getRequestState (aRequestScope).getMenuItem ();
    if (aMenuItem == null)
      throw new IllegalStateException ("No menu item is present!");
    return aMenuItem;
  }

  @NonNull
  @Nonempty
  public static String getMenuItemID (@NonNull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return getMenuItem (aRequestScope).getID ();
  }

  @NonNull
  public static Locale getDisplayLocale (@NonNull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    final Locale aLocale = getRequestState (aRequestScope).getDisplayLocale ();
    if (aLocale == null)
      throw new IllegalStateException ("No locale is available");
    return aLocale;
  }

  @NonNull
  public static Locale getDisplayCountry (@NonNull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return CountryCache.getInstance ().getCountry (getDisplayLocale (aRequestScope));
  }

  @NonNull
  public static String getDisplayLanguage (@NonNull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return getDisplayLocale (aRequestScope).getLanguage ();
  }

  static void setRequestState (@NonNull final IRequestWebScopeWithoutResponse aRequestScope,
                               @NonNull @Nonempty final String sAppID,
                               @NonNull final PhotonRequestState aState)
  {
    ValueEnforcer.notEmpty (sAppID, "AppID");
    ValueEnforcer.notNull (aState, "State");

    // Remember AppID in request
    final IAttributeContainerAny <String> aAttrs = aRequestScope.attrs ();
    aAttrs.putIn (REQUEST_ATTR_APP_ID, sAppID);
    aAttrs.putIn (REQUEST_ATTR_STATE, aState);
  }
}
