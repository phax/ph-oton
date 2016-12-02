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

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.locale.country.CountryCache;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.photon.basic.app.PhotonSessionState;
import com.helger.photon.basic.app.menu.IMenuItem;
import com.helger.photon.basic.app.menu.IMenuItemPage;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Predefined request parameter manager
 *
 * @author Philip Helger
 */
public interface IRequestParameterManager
{
  /** By default request parameters are used - for backwards compatibility */
  @Deprecated
  boolean DEFAULT_USE_PATHS = false;

  /** The default name of the parameter selecting the current menu item */
  String DEFAULT_REQUEST_PARAMETER_MENUITEM = "menuitem";

  /** The default name of the parameter selecting the current display locale */
  String DEFAULT_REQUEST_PARAMETER_DISPLAY_LOCALE = "locale";

  /**
   * The separator char to be used if path based handling is enabled, to
   * separate name and value
   */
  @Deprecated
  char SEPARATOR_CHAR = RequestParameterHandlerURLPathNamed.DEFAULT_SEPARATOR_CHAR;

  @Deprecated
  default boolean isUsePaths ()
  {
    return getParameterHandler () instanceof RequestParameterHandlerURLPathNamed;
  }

  @Deprecated
  default void setUsePaths (final boolean bUsePaths)
  {
    setParameterHandler (bUsePaths ? new RequestParameterHandlerURLPathNamed ()
                                   : new RequestParameterHandlerURLParameter ());
  }

  @Nonnull
  IRequestParameterHandler getParameterHandler ();

  void setParameterHandler (@Nonnull IRequestParameterHandler aRequestParameterHdl);

  @Nonnull
  @Nonempty
  String getRequestParamNameMenuItem ();

  void setRequestParamNameMenuItem (@Nonnull @Nonempty String sRequestParamMenuItem);

  @Nonnull
  @Nonempty
  String getRequestParamNameLocale ();

  void setRequestParamNameLocale (@Nonnull @Nonempty String sRequestParamDisplayLocale);

  @Nullable
  IMenuItemPage getMenuItemFromParameterIfValid (@Nullable String sMenuItemID);

  @Nullable
  Locale getLocaleFromParameterIfValid (@Nullable String sDisplayLocale);

  /**
   * To be called upon the beginning of each request. Checks for the content of
   * the request parameter {@value #DEFAULT_REQUEST_PARAMETER_MENUITEM} to
   * determine the selected menu item. Checks for the content of the request
   * parameter {@value #DEFAULT_REQUEST_PARAMETER_DISPLAY_LOCALE} to determine
   * any changes in the display locale.
   *
   * @param aRequestScope
   *        The request scope that just begun. May not be <code>null</code>.
   * @param sApplicationID
   *        The current application ID
   */
  void onRequestBegin (@Nonnull IRequestWebScopeWithoutResponse aRequestScope,
                       @Nonnull @Nonempty String sApplicationID);

  /**
   * @return The ID of the last requested menu item, or <code>null</code> if the
   *         corresponding session parameter is not present.
   */
  @Nullable
  IMenuItemPage getSessionMenuItem ();

  /**
   * Resolve the request parameter for the menu item to an {@link IMenuItem}
   * object. If no parameter is present, return the default menu item.
   *
   * @return The resolved menu item object from the request parameter. Never
   *         <code>null</code>.
   */
  @Nonnull
  IMenuItemPage getRequestMenuItem ();

  /**
   * @return The ID of the current request menu item. May not be
   *         <code>null</code>.
   */
  @Nonnull
  default String getRequestMenuItemID ()
  {
    return getRequestMenuItem ().getID ();
  }

  /**
   * Get the locale stored in the session. If neither request nor session data
   * is present, the default locale is returned.
   *
   * @return May be <code>null</code> if neither session locale nor default
   *         locale is present.
   */
  @Nullable
  Locale getSessionDisplayLocale ();

  /**
   * Get the locale to be used for this request. If no parameter is present, the
   * one from the session is used. If neither request nor session data is
   * present, the default locale is returned.
   *
   * @return The locale to be used for the current request. Never
   *         <code>null</code>.
   */
  @Nonnull
  Locale getRequestDisplayLocale ();

  /**
   * Get the country to be used for this request. If no parameter is present,
   * the one from the session is used. If neither request nor session data is
   * present, the country of the default locale is returned.
   *
   * @return The country-Locale of the request display locale. Never
   *         <code>null</code>.
   * @see #getRequestDisplayLocale()
   */
  @Nonnull
  default Locale getRequestDisplayCountry ()
  {
    return CountryCache.getInstance ().getCountry (getRequestDisplayLocale ());
  }

  /**
   * @return The language name from the current request display locale. Never
   *         <code>null</code>.
   * @see #getRequestDisplayLocale()
   */
  @Nonnull
  default String getRequestDisplayLanguage ()
  {
    return getRequestDisplayLocale ().getLanguage ();
  }

  @Nonnull
  default SimpleURL getLinkToMenuItem (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                       @Nonnull final String sMenuItemID)
  {
    // Determine the current application ID
    final String sAppID = PhotonSessionState.getInstance ().getLastApplicationID ();
    return getLinkToMenuItem (sAppID, aRequestScope, sMenuItemID);
  }

  @Nonnull
  SimpleURL getLinkToMenuItem (@Nonnull @Nonempty String sAppID,
                               @Nonnull IRequestWebScopeWithoutResponse aRequestScope,
                               @Nonnull String sMenuItemID);

  @Nullable
  String getMenuItemFromURL (@Nullable ISimpleURL aURL);

  @Nullable
  String getLocaleFromURL (@Nullable ISimpleURL aURL);
}
