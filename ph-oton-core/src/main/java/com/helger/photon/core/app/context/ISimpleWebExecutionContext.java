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
package com.helger.photon.core.app.context;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.attr.IAttributeContainer;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.url.SimpleURL;
import com.helger.photon.basic.app.menu.IMenuTree;
import com.helger.photon.basic.app.request.RequestParameterManager;
import com.helger.web.fileupload.IFileItem;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.request.IRequestParamMap;
import com.helger.web.useragent.IUserAgent;
import com.helger.web.useragent.browser.BrowserInfo;

public interface ISimpleWebExecutionContext extends IAttributeContainer <String, Object>
{
  /**
   * @return The current request scope. Never <code>null</code>.
   */
  @Nonnull
  IRequestWebScopeWithoutResponse getRequestScope ();

  /**
   * @return The current display locale. Base on the item of the request
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

  // The following 4 methods are copied from IRequestScope

  /**
   * Get a list of all attribute values with the same name.
   *
   * @param sName
   *        The name of the attribute to query.
   * @return <code>null</code> if no such attribute value exists
   */
  @Nullable
  default ICommonsList <String> getAttributeAsList (@Nullable final String sName)
  {
    return getAttributeAsList (sName, null);
  }

  /**
   * Get a list of all attribute values with the same name.
   *
   * @param sName
   *        The name of the attribute to query.
   * @param aDefault
   *        The default value to be returned, if no such attribute is present.
   * @return <code>default</code> if no such attribute value exists
   */
  @Nullable
  ICommonsList <String> getAttributeAsList (@Nullable String sName, @Nullable ICommonsList <String> aDefault);

  /**
   * Check if a attribute with the given name is present in the request and has
   * the specified value.
   *
   * @param sName
   *        The name of the attribute to check
   * @param sDesiredValue
   *        The value to be matched
   * @return <code>true</code> if an attribute with the given name is present
   *         and has the desired value
   */
  default boolean hasAttributeValue (@Nullable final String sName, @Nullable final String sDesiredValue)
  {
    return EqualsHelper.equals (getAttributeAsString (sName), sDesiredValue);
  }

  /**
   * Check if a attribute with the given name is present in the request and has
   * the specified value. If no such attribute is present, the passed default
   * value is returned.
   *
   * @param sName
   *        The name of the attribute to check
   * @param sDesiredValue
   *        The value to be matched
   * @param bDefault
   *        the default value to be returned, if the specified attribute is not
   *        present
   * @return <code>true</code> if an attribute with the given name is present
   *         and has the desired value, <code>false</code> if the attribute is
   *         present but has a different value. If the attribute is not present,
   *         the default value is returned.
   */
  default boolean hasAttributeValue (@Nullable final String sName,
                                     @Nullable final String sDesiredValue,
                                     final boolean bDefault)
  {
    final String sValue = getAttributeAsString (sName);
    return sValue == null ? bDefault : EqualsHelper.equals (sValue, sDesiredValue);
  }

  /**
   * Get the value of the checkbox of the request parameter with the given name.
   *
   * @param sName
   *        Request parameter name
   * @param bDefaultValue
   *        the default value to be returned, if no request attribute is present
   * @return The value of the passed request parameter
   */
  boolean getCheckBoxAttr (@Nullable String sName, boolean bDefaultValue);

  /**
   * Get the uploaded file with the specified request parameter.
   *
   * @param sName
   *        The parameter name.
   * @return <code>null</code> if no such uploaded file is present.
   * @throws ClassCastException
   *         if the passed request parameter is not a file
   */
  @Nullable
  default IFileItem getFileItem (@Nullable final String sName)
  {
    return getRequestScope ().getAttributeAsFileItem (sName);
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
   * Get the user agent object of this HTTP request.
   *
   * @return A non-<code>null</code> user agent object.
   */
  @Nonnull
  IUserAgent getUserAgent ();

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
    return RequestParameterManager.getInstance ().getLinkToMenuItem (getRequestScope (), sMenuItemID);
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
    return RequestParameterManager.getInstance ().getLinkToMenuItem (sAppID, getRequestScope (), sMenuItemID);
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
    return RequestParameterManager.getInstance ().getLinkToMenuItem (sAppID,
                                                                     getRequestScope (),
                                                                     aDisplayLocale,
                                                                     sMenuItemID);
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
