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

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.photon.basic.app.menu.IMenuItem;
import com.helger.photon.basic.app.menu.IMenuItemPage;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;

public interface IRequestManager
{
  /** By default request parameters are used - for backwards compatibility */
  boolean DEFAULT_USE_PATHS = false;

  /** The default name of the parameter selecting the current menu item */
  String DEFAULT_REQUEST_PARAMETER_MENUITEM = "menuitem";

  /** The default name of the parameter selecting the current display locale */
  String DEFAULT_REQUEST_PARAMETER_DISPLAY_LOCALE = "locale";

  /**
   * The separator char to be used if path based handling is enabled, to
   * separate name and value
   */
  char SEPARATOR_CHAR = '-';

  boolean isUsePaths ();

  void setUsePaths (boolean bUsePaths);

  @Nonnull
  @Nonempty
  String getRequestParamNameMenuItem ();

  void setRequestParamNameMenuItem (@Nonnull @Nonempty String sRequestParamMenuItem);

  @Nonnull
  @Nonempty
  String getRequestParamNameLocale ();

  void setRequestParamNameLocale (@Nonnull @Nonempty String sRequestParamDisplayLocale);

  /**
   * To be called upon the beginning of each request. Checks for the content of
   * the request parameter {@value #DEFAULT_REQUEST_PARAMETER_MENUITEM} to
   * determine the selected menu item. Checks for the content of the request
   * parameter {@value #DEFAULT_REQUEST_PARAMETER_DISPLAY_LOCALE} to determine
   * any changes in the display locale.
   *
   * @param aRequestScope
   *        The request scope that just begun. May not be <code>null</code>.
   */
  void onRequestBegin (@Nonnull IRequestWebScopeWithoutResponse aRequestScope);

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
  String getRequestMenuItemID ();

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
  Locale getRequestDisplayCountry ();

  /**
   * @return The language name from the current request display locale. Never
   *         <code>null</code>.
   * @see #getRequestDisplayLocale()
   */
  @Nonnull
  String getRequestDisplayLanguage ();

  @Nonnull
  SimpleURL getLinkToMenuItem (@Nonnull IRequestWebScopeWithoutResponse aRequestScope, @Nonnull String sMenuItemID);

  @Nullable
  String getMenuItemFromURL (@Nullable ISimpleURL aURL);

  @Nullable
  String getLocaleFromURL (@Nullable ISimpleURL aURL);
}
