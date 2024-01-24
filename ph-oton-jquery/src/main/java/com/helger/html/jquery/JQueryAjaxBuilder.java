/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
package com.helger.html.jquery;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.http.EHttpMethod;
import com.helger.commons.lang.ICloneable;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;

/**
 * Utility class handling <code>$.ajax</code>.<br>
 * By default asynchronous AJAX is enabled<br>
 * By default HTTP caching is disabled<br>
 * By default global events are triggered<br>
 * By default processData is enabled<br>
 * By default traditional processing is disabled
 *
 * @author Philip Helger
 */
public class JQueryAjaxBuilder implements Serializable, ICloneable <JQueryAjaxBuilder>
{
  // modifier
  private IJSExpression m_aAsync;
  private IJSExpression m_aCache;
  private IJSExpression m_aData;
  private IJSExpression m_aDataType;
  private IJSExpression m_aGlobalEvents;
  private IJSExpression m_aProcessData;
  private IJSExpression m_aURL;
  private IJSExpression m_aTraditional;
  private IJSExpression m_aMethod;

  // Callbacks
  private IJSExpression m_aCallbackContext;
  private JSAnonymousFunction m_aBeforeSend;
  private JSAnonymousFunction m_aComplete;
  private JSAnonymousFunction m_aError;
  private JSAnonymousFunction m_aSuccess;

  public JQueryAjaxBuilder ()
  {
    // By default caching is disabled for real world use cases
    cache (false);
  }

  public JQueryAjaxBuilder (@Nonnull final JQueryAjaxBuilder aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");
    m_aAsync = aOther.m_aAsync;
    m_aCache = aOther.m_aCache;
    m_aData = aOther.m_aData;
    m_aDataType = aOther.m_aDataType;
    m_aGlobalEvents = aOther.m_aGlobalEvents;
    m_aProcessData = aOther.m_aProcessData;
    m_aURL = aOther.m_aURL;
    m_aTraditional = aOther.m_aTraditional;
    m_aMethod = aOther.m_aMethod;

    m_aCallbackContext = aOther.m_aCallbackContext;
    m_aBeforeSend = aOther.m_aBeforeSend;
    m_aComplete = aOther.m_aComplete;
    m_aError = aOther.m_aError;
    m_aSuccess = aOther.m_aSuccess;
  }

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
  public final JQueryAjaxBuilder cache (final boolean bCache)
  {
    return cache (JSExpr.lit (bCache));
  }

  @Nonnull
  public final JQueryAjaxBuilder cache (@Nullable final IJSExpression aCache)
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
  public IJSExpression url ()
  {
    return m_aURL;
  }

  @Nonnull
  public JQueryAjaxBuilder url (@Nullable final ISimpleURL aURL)
  {
    return url (aURL == null ? null : aURL.getAsStringWithEncodedParameters ());
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

  @Nullable
  public IJSExpression method ()
  {
    return m_aMethod;
  }

  @Nonnull
  public JQueryAjaxBuilder method (@Nullable final EHttpMethod eMethod)
  {
    return method (eMethod == null ? null : JSExpr.lit (eMethod.getName ()));
  }

  @Nonnull
  public JQueryAjaxBuilder method (@Nullable final String sMethod)
  {
    return method (sMethod == null ? null : JSExpr.lit (sMethod));
  }

  @Nonnull
  public JQueryAjaxBuilder method (@Nullable final IJSExpression aMethod)
  {
    m_aMethod = aMethod;
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
  @ReturnsMutableCopy
  public JQueryAjaxBuilder getClone ()
  {
    return new JQueryAjaxBuilder (this);
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
    if (m_aMethod != null)
      aSettings.add ("method", m_aMethod);
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

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("url", m_aURL).getToString ();
  }
}
