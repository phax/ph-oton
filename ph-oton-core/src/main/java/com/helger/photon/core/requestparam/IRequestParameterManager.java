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
package com.helger.photon.core.requestparam;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.photon.core.appid.RequestSettings;
import com.helger.photon.core.menu.IMenuTree;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Predefined request parameter manager
 *
 * @author Philip Helger
 * @since 7.0.2
 */
public interface IRequestParameterManager
{
  /**
   * @return The main URL parameter handler. May not be <code>null</code>.
   */
  @Nonnull
  IRequestParameterHandler getParameterHandler ();

  /**
   * Set a new URL parameter handler.
   *
   * @param aRequestParameterHdl
   */
  void setParameterHandler (@Nonnull IRequestParameterHandler aRequestParameterHdl);

  /**
   * Get the link to the provided menu item, extracting the application ID from
   * the request.
   *
   * @param aRequestScope
   *        The current request scope.
   * @param aDisplayLocale
   *        The display locale currently in use.
   * @param sMenuItemID
   *        The selected menu item. May not be <code>null</code>.
   * @return A server absolute URL but without hostname and port. E.g. like
   *         this: <code>/context/public/locale/menuitemid</code>.
   */
  @Nonnull
  default SimpleURL getLinkToMenuItem (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                       @Nonnull final Locale aDisplayLocale,
                                       @Nonnull final String sMenuItemID)
  {
    return getLinkToMenuItem (RequestSettings.getApplicationID (aRequestScope), aRequestScope, aDisplayLocale, sMenuItemID);
  }

  /**
   * Get the link to the provided menu item.
   *
   * @param sAppID
   *        The photon application ID to use. May neither be <code>null</code>
   *        nor empty.
   * @param aRequestScope
   *        The current request scope.
   * @param aDisplayLocale
   *        The display locale currently in use.
   * @param sMenuItemID
   *        The selected menu item. May not be <code>null</code>.
   * @return A server absolute URL but without hostname and port. E.g. like
   *         this: <code>/context/public/locale/menuitemid</code>.
   */
  @Nonnull
  SimpleURL getLinkToMenuItem (@Nonnull @Nonempty String sAppID,
                               @Nonnull IRequestWebScopeWithoutResponse aRequestScope,
                               @Nonnull Locale aDisplayLocale,
                               @Nonnull String sMenuItemID);

  /**
   * Extract the menu item ID from the provided URL using the given menu tree.
   *
   * @param aURL
   *        The URL to extract from. May be <code>null</code>.
   * @param aMenuTree
   *        The menu tree to match against. May not be <code>null</code>.
   * @return <code>null</code> if either the URL was <code>null</code> or if no
   *         matching menu item was found in the URL.
   */
  @Nullable
  default String getMenuItemFromURL (@Nullable final ISimpleURL aURL, @Nonnull final IMenuTree aMenuTree)
  {
    if (aURL == null)
      return null;
    return getParameterHandler ().getParametersFromURL (aURL, aMenuTree).getMenuItemAsString ();
  }

  /**
   * Extract the locale from the provided URL using the given menu tree.
   *
   * @param aURL
   *        The URL to extract from. May be <code>null</code>.
   * @param aMenuTree
   *        The menu tree to match against. May not be <code>null</code>.
   * @return <code>null</code> if either the URl was <code>null</code> or if no
   *         locale was found in the URL.
   */
  @Nullable
  default String getLocaleFromURL (@Nullable final ISimpleURL aURL, @Nonnull final IMenuTree aMenuTree)
  {
    if (aURL == null)
      return null;

    return getParameterHandler ().getParametersFromURL (aURL, aMenuTree).getLocaleAsString ();
  }
}
