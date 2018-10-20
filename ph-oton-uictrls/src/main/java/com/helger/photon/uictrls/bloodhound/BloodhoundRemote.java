/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.lang.ICloneable;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.html.jquery.JQueryAjaxBuilder;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;

/**
 * Represent a remote object used in Bloodhound options
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class BloodhoundRemote implements ICloneable <BloodhoundRemote>
{
  public static final String JSON_URL = "url";
  public static final String JSON_WILDCARD = "wildcard";
  public static final String JSON_REPLACE = "replace";
  public static final String JSON_RATE_LIMIT_BY = "rateLimitBy";
  public static final String JSON_RATE_LIMIT_WAIT = "rateLimitWait";
  public static final String JSON_FILTER = "filter";
  public static final String JSON_AJAX = "ajax";

  // In the typeahead this is "%QUERY" but because of % URL encoding this sucks
  public static final String DEFAULT_WILDCARD = "_query_";
  public static final EBloodhoundRemoteRateLimitBy DEFAULT_RATE_LIMIT_BY = EBloodhoundRemoteRateLimitBy.DEBOUNCE;
  public static final int DEFAULT_RATE_LIMIT_WAIT = 300;

  private final SimpleURL m_aURL;
  private String m_sWildcard;
  private IJSExpression m_aReplace;
  private EBloodhoundRemoteRateLimitBy m_eRateLimitBy;
  private int m_nRateLimitWait = DEFAULT_RATE_LIMIT_WAIT;
  private IJSExpression m_aFilter;
  private JQueryAjaxBuilder m_aAjax;

  /**
   * Constructor
   *
   * @param aURL
   *        A URL to make requests to when when the data provided by local and
   *        prefetch is insufficient. Required.
   */
  public BloodhoundRemote (@Nonnull final ISimpleURL aURL)
  {
    ValueEnforcer.notNull (aURL, "URL");

    m_aURL = new SimpleURL (aURL);
  }

  public BloodhoundRemote (@Nonnull final BloodhoundRemote aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");

    m_aURL = aOther.m_aURL.getClone ();
    m_sWildcard = aOther.m_sWildcard;
    m_aReplace = aOther.m_aReplace;
    m_eRateLimitBy = aOther.m_eRateLimitBy;
    m_nRateLimitWait = aOther.m_nRateLimitWait;
    m_aFilter = aOther.m_aFilter;
    m_aAjax = aOther.m_aAjax;
  }

  /**
   * @return A URL to make requests to when when the data provided by local and
   *         prefetch is insufficient. Required. Never <code>null</code>.
   */
  @Nonnull
  public ISimpleURL getURL ()
  {
    return m_aURL;
  }

  /**
   * @return The pattern in <code>url</code> that will be replaced with the
   *         user's query when a request is made. Defaults to
   *         {@link #DEFAULT_WILDCARD}.
   */
  @Nullable
  public String getWildcard ()
  {
    return m_sWildcard;
  }

  /**
   * The pattern in <code>url</code> that will be replaced with the user's query
   * when a request is made. Defaults to {@link #DEFAULT_WILDCARD}.
   *
   * @param sWildcard
   *        The wild card to use. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public BloodhoundRemote setWildcard (@Nullable final String sWildcard)
  {
    m_sWildcard = sWildcard;
    return this;
  }

  /**
   * @return A function with the signature <code>replace(url, query)</code> that
   *         can be used to override the request URL. Expected to return a valid
   *         URL. If set, no wildcard substitution will be performed on
   *         <code>url</code>.
   */
  @Nullable
  public IJSExpression getReplace ()
  {
    return m_aReplace;
  }

  /**
   * A function with the signature <code>replace(url, query)</code> that can be
   * used to override the request URL. Expected to return a valid URL. If set,
   * no wildcard substitution will be performed on <code>url</code> .
   *
   * @param aReplace
   *        The function to use. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public BloodhoundRemote setReplace (@Nullable final IJSExpression aReplace)
  {
    m_aReplace = aReplace;
    return this;
  }

  /**
   * @return The method used to rate-limit network requests. Can be either
   *         <code>debounce</code> or <code>throttle</code>. Defaults to
   *         <code>debounce</code>.
   */
  @Nonnull
  public EBloodhoundRemoteRateLimitBy getRateLimitBy ()
  {
    return m_eRateLimitBy;
  }

  /**
   * The method used to rate-limit network requests. Can be either
   * <code>debounce</code> or <code>throttle</code>. Defaults to
   * <code>debounce</code>.
   *
   * @param eRateLimitBy
   *        function to use. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  public BloodhoundRemote setRateLimitBy (@Nonnull final EBloodhoundRemoteRateLimitBy eRateLimitBy)
  {
    m_eRateLimitBy = ValueEnforcer.notNull (eRateLimitBy, "RateLimitBy");
    return this;
  }

  /**
   * @return The time interval in milliseconds that will be used by
   *         <code>rateLimitBy</code>. Defaults to <code>300</code>.
   */
  @Nonnegative
  public int getRateLimitWait ()
  {
    return m_nRateLimitWait;
  }

  /**
   * The time interval in milliseconds that will be used by
   * <code>rateLimitBy</code>. Defaults to <code>300</code>.
   *
   * @param nRateLimitWait
   *        Milliseconds to use. Must be &ge; 1.
   * @return this
   */
  @Nonnull
  public BloodhoundRemote setRateLimitWait (@Nonnegative final int nRateLimitWait)
  {
    m_nRateLimitWait = ValueEnforcer.isGT0 (nRateLimitWait, "RateLimitWait");
    return this;
  }

  /**
   * @return A function with the signature <code>filter(parsedResponse)</code>
   *         that transforms the response body into an array of datums. Expected
   *         to return an array of datums.
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
  public BloodhoundRemote setFilter (@Nullable final IJSExpression aFilter)
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
  public BloodhoundRemote setAjax (@Nullable final JQueryAjaxBuilder aAjax)
  {
    m_aAjax = aAjax;
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public JSAssocArray getAsJSObject ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    ret.add (JSON_URL, m_aURL.getAsStringWithEncodedParameters ());
    if (StringHelper.hasText (m_sWildcard))
      ret.add (JSON_WILDCARD, m_sWildcard);
    if (m_aReplace != null)
      ret.add (JSON_REPLACE, m_aReplace);
    if (m_eRateLimitBy != null)
      ret.add (JSON_RATE_LIMIT_BY, m_eRateLimitBy.getValue ());
    if (m_nRateLimitWait != DEFAULT_RATE_LIMIT_WAIT)
      ret.add (JSON_RATE_LIMIT_WAIT, m_nRateLimitWait);
    if (m_aFilter != null)
      ret.add (JSON_FILTER, m_aFilter);
    if (m_aAjax != null)
      ret.add (JSON_AJAX, m_aAjax.getJSSettings ());
    return ret;
  }

  @Nonnull
  public BloodhoundRemote getClone ()
  {
    return new BloodhoundRemote (this);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("URL", m_aURL)
                                       .appendIfNotNull ("wildcard", m_sWildcard)
                                       .appendIfNotNull ("replace", m_aReplace)
                                       .appendIfNotNull ("rateLimitBy", m_eRateLimitBy)
                                       .append ("rateLimitWait", m_nRateLimitWait)
                                       .appendIfNotNull ("filter", m_aFilter)
                                       .appendIfNotNull ("ajax", m_aAjax)
                                       .getToString ();
  }
}
