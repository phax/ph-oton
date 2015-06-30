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
package com.helger.photon.uictrls.bloodhound;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.lang.ICloneable;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.html.js.builder.IJSExpression;
import com.helger.html.js.builder.JSAssocArray;
import com.helger.html.js.builder.jquery.JQueryAjaxBuilder;

/**
 * Represent a prefetch object used in Bloodhound init options
 * 
 * @author Philip Helger
 */
@Immutable
public class BloodhoundPrefetch implements ICloneable <BloodhoundPrefetch>
{
  public static final String JSON_URL = "url";
  public static final String JSON_CACHE_KEY = "cacheKey";
  public static final String JSON_TTL = "ttl";
  public static final String JSON_THUMBPRINT = "thumbprint";
  public static final String JSON_FILTER = "filter";
  public static final String JSON_AJAX = "ajax";

  public static final long DEFAULT_TTL = CGlobal.HOURS_PER_DAY * CGlobal.MILLISECONDS_PER_HOUR;

  private final SimpleURL m_aURL;
  private String m_sCacheKey;
  private long m_nTTL = DEFAULT_TTL;
  private String m_sThumbprint;
  private IJSExpression m_aFilter;
  private JQueryAjaxBuilder m_aAjax;

  /**
   * Constructor
   * 
   * @param aURL
   *        A URL to a JSON file containing an array of datums.
   */
  public BloodhoundPrefetch (@Nonnull final ISimpleURL aURL)
  {
    ValueEnforcer.notNull (aURL, "URL");
    m_aURL = new SimpleURL (aURL);
  }

  public BloodhoundPrefetch (@Nonnull final BloodhoundPrefetch aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");
    m_aURL = aOther.m_aURL.getClone ();
    m_sCacheKey = aOther.m_sCacheKey;
    m_nTTL = aOther.m_nTTL;
    m_sThumbprint = aOther.m_sThumbprint;
    m_aFilter = aOther.m_aFilter;
    m_aAjax = aOther.m_aAjax;
  }

  /**
   * @return A URL to a JSON file containing an array of datums. Required.
   */
  @Nonnull
  public ISimpleURL getURL ()
  {
    return m_aURL;
  }

  /**
   * @return The key that data will be stored in local storage under. Defaults
   *         to value of url. May be <code>null</code>.
   */
  @Nullable
  public String getCacheKey ()
  {
    return m_sCacheKey;
  }

  @Nonnull
  public BloodhoundPrefetch setCacheKey (@Nullable final String sCacheKey)
  {
    m_sCacheKey = sCacheKey;
    return this;
  }

  /**
   * @return The time (in milliseconds) the prefetched data should be cached in
   *         local storage. Defaults to <code>86400000</code> (1 day).
   */
  @Nonnegative
  public long getTTL ()
  {
    return m_nTTL;
  }

  /**
   * The time (in milliseconds) the prefetched data should be cached in
   * localStorage. Defaults to <code>86400000</code> (1 day).
   * 
   * @param nTTL
   *        Time to live in milliseconds. Must be &ge; 1.
   * @return this
   */
  @Nonnull
  public BloodhoundPrefetch setTTL (@Nonnegative final long nTTL)
  {
    m_nTTL = ValueEnforcer.isGT0 (nTTL, "TTL");
    return this;
  }

  /**
   * @return A string used for thumbprinting prefetched data. If this doesn't
   *         match what's stored in local storage, the data will be refetched.
   *         May be <code>null</code>.
   */
  @Nullable
  public String getThumbprint ()
  {
    return m_sThumbprint;
  }

  @Nonnull
  public BloodhoundPrefetch setThumbprint (@Nullable final String sThumbprint)
  {
    m_sThumbprint = sThumbprint;
    return this;
  }

  /**
   * @return A function with the signature <code>filter(parsedResponse)</code>
   *         that transforms the response body into an array of datums. Expected
   *         to return an array of datums. May be <code>null</code>.
   */
  @Nullable
  public IJSExpression getFilter ()
  {
    return m_aFilter;
  }

  /**
   * A function with the signature <code>filter(parsedResponse)</code> that
   * transforms the response body into an array of datums. Expected to return an
   * array of datums.
   * 
   * @param aFilter
   *        Function to use. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public BloodhoundPrefetch setFilter (@Nullable final IJSExpression aFilter)
  {
    m_aFilter = aFilter;
    return this;
  }

  /**
   * @return The ajax settings object passed to <code>jQuery.ajax</code>. Maybe
   *         <code>null</code>.
   */
  @Nullable
  public JQueryAjaxBuilder getAjax ()
  {
    return m_aAjax;
  }

  @Nonnull
  public BloodhoundPrefetch setAjax (@Nullable final JQueryAjaxBuilder aAjax)
  {
    m_aAjax = aAjax;
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public JSAssocArray getAsJSObject ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    ret.add (JSON_URL, m_aURL.getAsString ());
    if (StringHelper.hasText (m_sCacheKey))
      ret.add (JSON_CACHE_KEY, m_sCacheKey);
    if (m_nTTL != DEFAULT_TTL)
      ret.add (JSON_TTL, m_nTTL);
    if (StringHelper.hasText (m_sThumbprint))
      ret.add (JSON_THUMBPRINT, m_sThumbprint);
    if (m_aFilter != null)
      ret.add (JSON_FILTER, m_aFilter);
    if (m_aAjax != null)
      ret.add (JSON_AJAX, m_aAjax.getJSSettings ());
    return ret;
  }

  @Nonnull
  public BloodhoundPrefetch getClone ()
  {
    return new BloodhoundPrefetch (this);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("URL", m_aURL)
                                       .appendIfNotNull ("cacheKey", m_sCacheKey)
                                       .append ("ttl", m_nTTL)
                                       .appendIfNotNull ("thumbPrint", m_sThumbprint)
                                       .appendIfNotNull ("filter", m_aFilter)
                                       .appendIfNotNull ("ajax", m_aAjax)
                                       .toString ();
  }
}
