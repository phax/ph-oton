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
package com.helger.photon.core.app.context;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.attr.AbstractReadOnlyAttributeContainer;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.SimpleURL;
import com.helger.photon.basic.app.menu.IMenuTree;
import com.helger.photon.basic.app.request.ApplicationRequestManager;
import com.helger.photon.basic.app.request.IRequestManager;
import com.helger.photon.core.form.RequestFieldBoolean;
import com.helger.web.fileupload.IFileItem;
import com.helger.web.scope.domain.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.request.IRequestParamMap;
import com.helger.web.useragent.IUserAgent;
import com.helger.web.useragent.UserAgentDatabase;
import com.helger.web.useragent.browser.BrowserInfo;

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
  // Status vars
  private IRequestManager m_aARM;

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

  public boolean containsNoAttribute ()
  {
    return m_aRequestScope.containsNoAttribute ();
  }

  public boolean containsAttribute (@Nullable final String sName)
  {
    return m_aRequestScope.containsAttribute (sName);
  }

  @Nonnull
  @ReturnsMutableCopy
  public Map <String, Object> getAllAttributes ()
  {
    return m_aRequestScope.getAllAttributes ();
  }

  @Nullable
  public Object getAttributeObject (@Nullable final String sName)
  {
    return m_aRequestScope.getAttributeObject (sName);
  }

  @Nullable
  public <DATATYPE> DATATYPE getCastedAttribute (@Nullable final String sName)
  {
    return m_aRequestScope.getCastedAttribute (sName);
  }

  @Nullable
  public <DATATYPE> DATATYPE getCastedAttribute (@Nullable final String sName, @Nullable final DATATYPE aDefault)
  {
    return m_aRequestScope.getCastedAttribute (sName, aDefault);
  }

  @Nullable
  public <DATATYPE> DATATYPE getTypedAttribute (@Nullable final String sName, @Nonnull final Class <DATATYPE> aDstClass)
  {
    return m_aRequestScope.getTypedAttribute (sName, aDstClass);
  }

  @Nullable
  public <DATATYPE> DATATYPE getTypedAttribute (@Nullable final String sName,
                                                @Nonnull final Class <DATATYPE> aDstClass,
                                                @Nullable final DATATYPE aDefault)
  {
    return m_aRequestScope.getTypedAttribute (sName, aDstClass, aDefault);
  }

  @Nullable
  public String getAttributeAsString (@Nullable final String sName)
  {
    return getAttributeAsString (sName, (String) null);
  }

  @Nullable
  public String getAttributeAsString (@Nullable final String sName, @Nullable final String sDefault)
  {
    final String ret = m_aRequestScope.getAttributeAsString (sName, sDefault);
    // Automatically and always remove leading and trailing whitespaces
    return StringHelper.trim (ret);
  }

  public int getAttributeAsInt (@Nullable final String sName)
  {
    return getAttributeAsInt (sName, CGlobal.ILLEGAL_UINT);
  }

  public int getAttributeAsInt (@Nullable final String sName, final int nDefault)
  {
    // Always use String because we're handling request parameters
    return AbstractReadOnlyAttributeContainer.getAsInt (sName, getAttributeAsString (sName), nDefault);
  }

  public long getAttributeAsLong (@Nullable final String sName)
  {
    return getAttributeAsLong (sName, CGlobal.ILLEGAL_ULONG);
  }

  public long getAttributeAsLong (@Nullable final String sName, final long nDefault)
  {
    // Always use String because we're handling request parameters
    return AbstractReadOnlyAttributeContainer.getAsLong (sName, getAttributeAsString (sName), nDefault);
  }

  public double getAttributeAsDouble (@Nullable final String sName)
  {
    return getAttributeAsDouble (sName, CGlobal.ILLEGAL_UINT);
  }

  public double getAttributeAsDouble (@Nullable final String sName, final double dDefault)
  {
    // Always use String because we're handling request parameters
    return AbstractReadOnlyAttributeContainer.getAsDouble (sName, getAttributeAsString (sName), dDefault);
  }

  public boolean getAttributeAsBoolean (@Nullable final String sName)
  {
    return getAttributeAsBoolean (sName, false);
  }

  public boolean getAttributeAsBoolean (@Nullable final String sName, final boolean bDefault)
  {
    // Always use String because we're handling request parameters
    return AbstractReadOnlyAttributeContainer.getAsBoolean (sName, getAttributeAsString (sName), bDefault);
  }

  @Nullable
  public BigInteger getAttributeAsBigInteger (@Nullable final String sName)
  {
    return getAttributeAsBigInteger (sName, null);
  }

  @Nullable
  public BigInteger getAttributeAsBigInteger (@Nullable final String sName, @Nullable final BigInteger aDefault)
  {
    // Always use String because we're handling request parameters
    return AbstractReadOnlyAttributeContainer.getAsBigInteger (sName, getAttributeAsString (sName), aDefault);
  }

  @Nullable
  public BigDecimal getAttributeAsBigDecimal (@Nullable final String sName)
  {
    return getAttributeAsBigDecimal (sName, null);
  }

  @Nullable
  public BigDecimal getAttributeAsBigDecimal (@Nullable final String sName, @Nullable final BigDecimal aDefault)
  {
    // Always use String because we're handling request parameters
    return AbstractReadOnlyAttributeContainer.getAsBigDecimal (sName, getAttributeAsString (sName), aDefault);
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <String> getAllAttributeNames ()
  {
    return m_aRequestScope.getAllAttributeNames ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <Object> getAllAttributeValues ()
  {
    return m_aRequestScope.getAllAttributeValues ();
  }

  @Nullable
  public List <String> getAttributeAsList (@Nullable final String sName)
  {
    return m_aRequestScope.getAttributeAsList (sName);
  }

  @Nullable
  public List <String> getAttributeAsList (@Nullable final String sName, @Nullable final List <String> aDefault)
  {
    return m_aRequestScope.getAttributeAsList (sName, aDefault);
  }

  public boolean hasAttributeValue (@Nullable final String sName, @Nullable final String sDesiredValue)
  {
    return EqualsHelper.equals (getAttributeAsString (sName), sDesiredValue);
  }

  public boolean hasAttributeValue (@Nullable final String sName,
                                    @Nullable final String sDesiredValue,
                                    final boolean bDefault)
  {
    final String sValue = getAttributeAsString (sName);
    return sValue == null ? bDefault : EqualsHelper.equals (sValue, sDesiredValue);
  }

  public boolean getCheckBoxAttr (@Nullable final String sName, final boolean bDefaultValue)
  {
    return getCheckBoxAttrStatic (sName, bDefaultValue);
  }

  public static boolean getCheckBoxAttrStatic (@Nullable final String sName, final boolean bDefaultValue)
  {
    return StringHelper.hasNoText (sName) ? bDefaultValue : RequestFieldBoolean.getCheckBoxValue (sName, bDefaultValue);
  }

  @Nullable
  public IFileItem getFileItem (@Nullable final String sName)
  {
    return m_aRequestScope.getAttributeAsFileItem (sName);
  }

  @Nonnull
  public IRequestParamMap getRequestParamMap ()
  {
    return m_aRequestScope.getRequestParamMap ();
  }

  @Nonnull
  public IUserAgent getUserAgent ()
  {
    return UserAgentDatabase.getUserAgent (m_aRequestScope.getRequest ());
  }

  @Nullable
  public BrowserInfo getBrowserInfo ()
  {
    return getUserAgent ().getBrowserInfo ();
  }

  @Nonnull
  public SimpleURL getLinkToMenuItem (@Nonnull final String sMenuItemID)
  {
    // Cache for performance reasons
    if (m_aARM == null)
      m_aARM = ApplicationRequestManager.getRequestMgr ();
    return m_aARM.getLinkToMenuItem (m_aRequestScope, sMenuItemID);
  }

  @Nonnull
  public SimpleURL getLinkToMenuItem (@Nonnull final String sMenuItemID, @Nullable final Map <String, String> aParams)
  {
    return getLinkToMenuItem (sMenuItemID).addAll (aParams);
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
