/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.core.app.context;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.url.SimpleURL;
import com.helger.photon.basic.app.menu.IMenuTree;
import com.helger.photon.basic.app.request.RequestParameterManager;
import com.helger.servlet.request.IRequestParamMap;
import com.helger.servlet.request.RequestHelper;
import com.helger.useragent.IUserAgent;
import com.helger.useragent.browser.BrowserInfo;
import com.helger.web.scope.IRequestParamContainer;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

public interface ISimpleWebExecutionContext
{
  /**
   * @return The current request scope. Never <code>null</code>.
   */
  @Nonnull
  IRequestWebScopeWithoutResponse getRequestScope ();

  /**
   * @return The container with all request parameters. Never <code>null</code>.
   */
  @Nonnull
  default IRequestParamContainer params ()
  {
    return getRequestScope ().params ();
  }

  // Deprecated
  @Nullable
  default String getAttributeAsString (@Nullable final String sFieldName)
  {
    return params ().getAsString (sFieldName);
  }

  /**
   * @return A cached request param map for this request.
   */
  @Nonnull
  default IRequestParamMap getRequestParamMap ()
  {
    return getRequestScope ().getRequestParamMap ();
  }

  /**
   * @return The current display locale. Based on the locale of the request
   *         manager.
   */
  @Nonnull
  Locale getDisplayLocale ();

  /**
   * @return The current menu tree. Based on the menu tree of the request
   *         manager.
   */
  @Nonnull
  IMenuTree getMenuTree ();

  /**
   * Get the user agent object of this HTTP request.
   *
   * @return A non-<code>null</code> user agent object.
   */
  @Nonnull
  default IUserAgent getUserAgent ()
  {
    return RequestHelper.getUserAgent (getRequestScope ().getRequest ());
  }

  /**
   * @return The information about the matching browser or <code>null</code> if
   *         no known browser was detected.
   */
  @Nullable
  default BrowserInfo getBrowserInfo ()
  {
    return getUserAgent ().getBrowserInfo ();
  }

  /**
   * Get the URL to the specified menu item using the current display locale.
   *
   * @param sMenuItemID
   *        The ID of the menu item to link to. May not be <code>null</code>.
   * @return The non-<code>null</code> URL to the specified menu item.
   */
  @Nonnull
  default SimpleURL getLinkToMenuItem (@Nonnull final String sMenuItemID)
  {
    return getLinkToMenuItem (getDisplayLocale (), sMenuItemID);
  }

  /**
   * Get the URL to the specified menu item.
   *
   * @param aDisplayLocale
   *        Specific display locale to be used. May not be <code>null</code>.
   * @param sMenuItemID
   *        The ID of the menu item to link to. May not be <code>null</code>.
   * @return The non-<code>null</code> URL to the specified menu item.
   * @since 7.0.2
   */
  @Nonnull
  default SimpleURL getLinkToMenuItem (@Nonnull final Locale aDisplayLocale, @Nonnull final String sMenuItemID)
  {
    return RequestParameterManager.getInstance ().getLinkToMenuItem (getRequestScope (), aDisplayLocale, sMenuItemID);
  }

  /**
   * Get the URL to the specified menu item in the passed application. This is
   * helpful when linking between different applications. The current locale is
   * used.
   *
   * @param sAppID
   *        The application ID to use. May neither be <code>null</code> nor
   *        empty.
   * @param sMenuItemID
   *        The ID of the menu item to link to. May not be <code>null</code>.
   * @return The non-<code>null</code> URL to the specified menu item.
   */
  @Nonnull
  default SimpleURL getLinkToMenuItem (@Nonnull @Nonempty final String sAppID, @Nonnull final String sMenuItemID)
  {
    return getLinkToMenuItem (sAppID, getDisplayLocale (), sMenuItemID);
  }

  /**
   * Get the URL to the specified menu item in the passed application using the
   * specified locale. This is helpful when linking between different
   * applications.
   *
   * @param sAppID
   *        The application ID to use. May neither be <code>null</code> nor
   *        empty.
   * @param aDisplayLocale
   *        Specific display locale to be used. May not be <code>null</code>.
   * @param sMenuItemID
   *        The ID of the menu item to link to. May not be <code>null</code>.
   * @return The non-<code>null</code> URL to the specified menu item.
   * @since 7.0.2
   */
  @Nonnull
  default SimpleURL getLinkToMenuItem (@Nonnull @Nonempty final String sAppID,
                                       @Nonnull final Locale aDisplayLocale,
                                       @Nonnull final String sMenuItemID)
  {
    return RequestParameterManager.getInstance ()
                                  .getLinkToMenuItem (sAppID, getRequestScope (), aDisplayLocale, sMenuItemID);
  }

  /**
   * Get the full URL (incl. protocol) and parameters of the current request.
   * <br>
   * <code>http://hostname.com:81/context/servlet/path/a/b?c=123&amp;d=789</code>
   *
   * @return The full URL of the current request.
   */
  @Nonnull
  @Nonempty
  default String getURL ()
  {
    return getRequestScope ().getURL ();
  }
}
