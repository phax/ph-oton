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
package com.helger.html.js.builder.jquery;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.url.ISimpleURL;
import com.helger.html.js.builder.IJSExpression;
import com.helger.html.js.builder.JSAnonymousFunction;
import com.helger.html.js.builder.JSAssocArray;
import com.helger.html.js.builder.JSExpr;

/**
 * Utility class handling <code>$.ajax</code>
 *
 * @author Philip Helger
 */
public class JQueryAjaxBuilder implements Serializable
{
  /** By default asynchronous AJAX is enabled */
  public static final boolean DEFAULT_ASYNC = true;
  /** By default HTTP caching is enabled */
  public static final boolean DEFAULT_CACHE = true;
  /** By default global events are triggered */
  public static final boolean DEFAULT_GLOBAL_EVENTS = true;
  /** By default processData is enabled */
  public static final boolean DEFAULT_PROCESS_DATA = true;
  /** By default traditional processing is disabled */
  public static final boolean DEFAULT_TRADITIONAL = false;

  // modifier
  private IJSExpression m_aAsync;
  private IJSExpression m_aCache;
  private IJSExpression m_aData;
  private IJSExpression m_aDataType;
  private IJSExpression m_aGlobalEvents;
  private IJSExpression m_aProcessData;
  private IJSExpression m_aURL;
  private IJSExpression m_aTraditional;
  private IJSExpression m_aType;

  // Callbacks
  private IJSExpression m_aCallbackContext;
  private JSAnonymousFunction m_aBeforeSend;
  private JSAnonymousFunction m_aComplete;
  private JSAnonymousFunction m_aError;
  private JSAnonymousFunction m_aSuccess;

  public JQueryAjaxBuilder ()
  {}

  @Nullable
  public IJSExpression async ()
  {
    return m_aAsync;
  }

  @Nonnull
  public JQueryAjaxBuilder async (final boolean bAsync)
  {
    return async (JSExpr.lit (bAsync));
  }

  @Nonnull
  public JQueryAjaxBuilder async (@Nullable final IJSExpression aAsync)
  {
    m_aAsync = aAsync;
    return this;
  }

  @Nullable
  public IJSExpression cache ()
  {
    return m_aCache;
  }

  @Nonnull
  public JQueryAjaxBuilder cache (final boolean bCache)
  {
    return cache (JSExpr.lit (bCache));
  }

  @Nonnull
  public JQueryAjaxBuilder cache (@Nullable final IJSExpression aCache)
  {
    m_aCache = aCache;
    return this;
  }

  @Nullable
  public IJSExpression data ()
  {
    return m_aData;
  }

  @Nonnull
  public JQueryAjaxBuilder data (@Nullable final IJSExpression aData)
  {
    m_aData = aData;
    return this;
  }

  @Nullable
  public IJSExpression dataType ()
  {
    return m_aDataType;
  }

  @Nonnull
  public JQueryAjaxBuilder dataType (@Nullable final String sDataType)
  {
    return dataType (sDataType == null ? null : JSExpr.lit (sDataType));
  }

  @Nonnull
  public JQueryAjaxBuilder dataType (@Nullable final IJSExpression aDataType)
  {
    m_aDataType = aDataType;
    return this;
  }

  @Nullable
  public IJSExpression global ()
  {
    return m_aGlobalEvents;
  }

  @Nonnull
  public JQueryAjaxBuilder global (final boolean bGlobalEvents)
  {
    return global (JSExpr.lit (bGlobalEvents));
  }

  @Nonnull
  public JQueryAjaxBuilder global (@Nullable final IJSExpression aGlobalEvents)
  {
    m_aGlobalEvents = aGlobalEvents;
    return this;
  }

  @Nullable
  public IJSExpression processData ()
  {
    return m_aProcessData;
  }

  @Nonnull
  public JQueryAjaxBuilder processData (final boolean bProcessData)
  {
    return processData (JSExpr.lit (bProcessData));
  }

  @Nonnull
  public JQueryAjaxBuilder processData (@Nullable final IJSExpression aProcessData)
  {
    m_aProcessData = aProcessData;
    return this;
  }

  @Nullable
  public IJSExpression traditional ()
  {
    return m_aTraditional;
  }

  @Nonnull
  public JQueryAjaxBuilder traditional (final boolean bTraditional)
  {
    return traditional (JSExpr.lit (bTraditional));
  }

  @Nonnull
  public JQueryAjaxBuilder traditional (@Nullable final IJSExpression aTraditional)
  {
    m_aTraditional = aTraditional;
    return this;
  }

  @Nullable
  public IJSExpression type ()
  {
    return m_aType;
  }

  @Nonnull
  public JQueryAjaxBuilder type (@Nullable final String sType)
  {
    return type (sType == null ? null : JSExpr.lit (sType));
  }

  @Nonnull
  public JQueryAjaxBuilder type (@Nullable final IJSExpression aType)
  {
    m_aType = aType;
    return this;
  }

  @Nullable
  public IJSExpression url ()
  {
    return m_aURL;
  }

  @Nonnull
  public JQueryAjaxBuilder url (@Nullable final ISimpleURL aURL)
  {
    return url (aURL == null ? null : aURL.getAsString ());
  }

  @Nonnull
  public JQueryAjaxBuilder url (@Nullable final String sURL)
  {
    return url (sURL == null ? null : JSExpr.lit (sURL));
  }

  @Nonnull
  public JQueryAjaxBuilder url (@Nullable final IJSExpression aURL)
  {
    m_aURL = aURL;
    return this;
  }

  // Callback related

  @Nullable
  public IJSExpression context ()
  {
    return m_aCallbackContext;
  }

  @Nonnull
  public JQueryAjaxBuilder context (@Nullable final IJSExpression aContext)
  {
    m_aCallbackContext = aContext;
    return this;
  }

  @Nullable
  public JSAnonymousFunction beforeSend ()
  {
    return m_aBeforeSend;
  }

  /**
   * Set an anonymous beforeSend function. Type: Function( jqXHR jqXHR,
   * PlainObject settings )
   *
   * @param aBeforeSend
   *        May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public JQueryAjaxBuilder beforeSend (@Nullable final JSAnonymousFunction aBeforeSend)
  {
    m_aBeforeSend = aBeforeSend;
    return this;
  }

  @Nullable
  public JSAnonymousFunction complete ()
  {
    return m_aComplete;
  }

  /**
   * Set an anonymous complete function. Type: Function( jqXHR jqXHR, String
   * textStatus )
   *
   * @param aComplete
   *        May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public JQueryAjaxBuilder complete (@Nullable final JSAnonymousFunction aComplete)
  {
    m_aComplete = aComplete;
    return this;
  }

  @Nullable
  public JSAnonymousFunction error ()
  {
    return m_aError;
  }

  /**
   * Set an anonymous error function. Type: Function( jqXHR jqXHR, String
   * textStatus, String errorThrown )
   *
   * @param aError
   *        May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public JQueryAjaxBuilder error (@Nullable final JSAnonymousFunction aError)
  {
    m_aError = aError;
    return this;
  }

  @Nullable
  public JSAnonymousFunction success ()
  {
    return m_aSuccess;
  }

  /**
   * Set an anonymous success function. Type: Function( PlainObject data, String
   * textStatus, jqXHR jqXHR )
   *
   * @param aSuccess
   *        May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public JQueryAjaxBuilder success (@Nullable final JSAnonymousFunction aSuccess)
  {
    m_aSuccess = aSuccess;
    return this;
  }

  @Nonnull
  public JSAssocArray getJSSettings ()
  {
    final JSAssocArray aSettings = new JSAssocArray ();
    if (m_aAsync != null)
      aSettings.add ("async", m_aAsync);
    if (m_aCache != null)
      aSettings.add ("cache", m_aCache);
    if (m_aData != null)
      aSettings.add ("data", m_aData);
    if (m_aDataType != null)
      aSettings.add ("dataType", m_aDataType);
    if (m_aGlobalEvents != null)
      aSettings.add ("global", m_aGlobalEvents);
    if (m_aProcessData != null)
      aSettings.add ("processData", m_aProcessData);
    if (m_aTraditional != null)
      aSettings.add ("traditional", m_aTraditional);
    if (m_aType != null)
      aSettings.add ("type", m_aType);
    if (m_aURL != null)
      aSettings.add ("url", m_aURL);

    // Callbacks
    if (m_aCallbackContext != null)
      aSettings.add ("context", m_aCallbackContext);
    if (m_aBeforeSend != null)
      aSettings.add ("beforeSend", m_aBeforeSend);
    if (m_aComplete != null)
      aSettings.add ("complete", m_aComplete);
    if (m_aError != null)
      aSettings.add ("error", m_aError);
    if (m_aSuccess != null)
      aSettings.add ("success", m_aSuccess);
    return aSettings;
  }

  @Nonnull
  public JQueryInvocation build ()
  {
    return JQuery.ajax (getJSSettings ());
  }
}
