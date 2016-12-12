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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.attr.AttributeValueConverter;
import com.helger.commons.collection.ext.ICommonsCollection;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.collection.ext.ICommonsMap;
import com.helger.commons.collection.ext.ICommonsSet;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.app.menu.IMenuTree;
import com.helger.photon.core.form.RequestFieldBoolean;
import com.helger.servlet.request.RequestHelper;
import com.helger.useragent.IUserAgent;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * This object is instantiated per page view and contains the current request
 * scope, the display locale and a set of custom attributes.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class SimpleWebExecutionContext implements ISimpleWebExecutionContext
{
  private final IRequestWebScopeWithoutResponse m_aRequestScope;
  private final Locale m_aDisplayLocale;
  private final IMenuTree m_aMenuTree;

  public SimpleWebExecutionContext (@Nonnull final ISimpleWebExecutionContext aSWEC)
  {
    this (aSWEC.getRequestScope (), aSWEC.getDisplayLocale (), aSWEC.getMenuTree ());
  }

  public SimpleWebExecutionContext (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                    @Nonnull final Locale aDisplayLocale,
                                    @Nonnull final IMenuTree aMenuTree)
  {
    m_aRequestScope = ValueEnforcer.notNull (aRequestScope, "RequestScope");
    m_aDisplayLocale = ValueEnforcer.notNull (aDisplayLocale, "DisplayLocale");
    m_aMenuTree = ValueEnforcer.notNull (aMenuTree, "MenuTree");
  }

  @Nonnull
  public IRequestWebScopeWithoutResponse getRequestScope ()
  {
    return m_aRequestScope;
  }

  @Nonnull
  public Locale getDisplayLocale ()
  {
    return m_aDisplayLocale;
  }

  @Nonnull
  public IMenuTree getMenuTree ()
  {
    return m_aMenuTree;
  }

  @Nonnegative
  public int getAttributeCount ()
  {
    return m_aRequestScope.getAttributeCount ();
  }

  public boolean isEmpty ()
  {
    return m_aRequestScope.isEmpty ();
  }

  public boolean containsAttribute (@Nullable final String sName)
  {
    return m_aRequestScope.containsAttribute (sName);
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsMap <String, Object> getAllAttributes ()
  {
    return m_aRequestScope.getAllAttributes ();
  }

  public void forAllAttributes (@Nonnull final BiConsumer <? super String, ? super Object> aConsumer)
  {
    m_aRequestScope.forAllAttributes (aConsumer);
  }

  public void forAllAttributeValues (@Nonnull final Consumer <? super Object> aConsumer)
  {
    m_aRequestScope.forAllAttributeValues (aConsumer);
  }

  @Nullable
  public Object getAttributeObject (@Nullable final String sName)
  {
    return m_aRequestScope.getAttributeObject (sName);
  }

  @Nullable
  public String getAttributeAsString (@Nullable final String sName, @Nullable final String sDefault)
  {
    final String ret = m_aRequestScope.getAttributeAsString (sName, sDefault);
    // Automatically and always remove leading and trailing whitespaces
    return StringHelper.trim (ret);
  }

  public int getAttributeAsInt (@Nullable final String sName, final int nDefault)
  {
    // Always use String because we're handling request parameters
    return AttributeValueConverter.getAsInt (sName, getAttributeAsString (sName), nDefault);
  }

  public long getAttributeAsLong (@Nullable final String sName, final long nDefault)
  {
    // Always use String because we're handling request parameters
    return AttributeValueConverter.getAsLong (sName, getAttributeAsString (sName), nDefault);
  }

  public double getAttributeAsDouble (@Nullable final String sName, final double dDefault)
  {
    // Always use String because we're handling request parameters
    return AttributeValueConverter.getAsDouble (sName, getAttributeAsString (sName), dDefault);
  }

  public boolean getAttributeAsBoolean (@Nullable final String sName, final boolean bDefault)
  {
    // Always use String because we're handling request parameters
    return AttributeValueConverter.getAsBoolean (sName, getAttributeAsString (sName), bDefault);
  }

  @Nullable
  public BigInteger getAttributeAsBigInteger (@Nullable final String sName, @Nullable final BigInteger aDefault)
  {
    // Always use String because we're handling request parameters
    return AttributeValueConverter.getAsBigInteger (sName, getAttributeAsString (sName), aDefault);
  }

  @Nullable
  public BigDecimal getAttributeAsBigDecimal (@Nullable final String sName, @Nullable final BigDecimal aDefault)
  {
    // Always use String because we're handling request parameters
    return AttributeValueConverter.getAsBigDecimal (sName, getAttributeAsString (sName), aDefault);
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsSet <String> getAllAttributeNames ()
  {
    return m_aRequestScope.getAllAttributeNames ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsCollection <Object> getAllAttributeValues ()
  {
    return m_aRequestScope.getAllAttributeValues ();
  }

  @Nullable
  public ICommonsList <String> getAttributeAsList (@Nullable final String sName,
                                                   @Nullable final ICommonsList <String> aDefault)
  {
    return m_aRequestScope.getAttributeAsList (sName, aDefault);
  }

  @Nonnull
  public Iterator <Map.Entry <String, Object>> iterator ()
  {
    return m_aRequestScope.iterator ();
  }

  public boolean getCheckBoxAttr (@Nullable final String sName, final boolean bDefaultValue)
  {
    return getCheckBoxAttrStatic (sName, bDefaultValue);
  }

  public static boolean getCheckBoxAttrStatic (@Nullable final String sName, final boolean bDefaultValue)
  {
    return StringHelper.hasNoText (sName) ? bDefaultValue : RequestFieldBoolean.getCheckBoxValue (sName, bDefaultValue);
  }

  @Nonnull
  public IUserAgent getUserAgent ()
  {
    return RequestHelper.getUserAgent (m_aRequestScope.getRequest ());
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("requestURL", m_aRequestScope.getURL ())
                                       .append ("displayLocale", m_aDisplayLocale)
                                       .append ("menuTree", m_aMenuTree)
                                       .toString ();
  }
}
