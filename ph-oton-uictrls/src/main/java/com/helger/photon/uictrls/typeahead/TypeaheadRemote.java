/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.typeahead;

import com.helger.annotation.Nonempty;
import com.helger.annotation.Nonnegative;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.clone.ICloneable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.ETriState;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSAssocArray;
import com.helger.url.ISimpleURL;
import com.helger.url.SimpleURL;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Represent a remote object used in a dataset
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class TypeaheadRemote implements ICloneable <TypeaheadRemote>
{
  public static final String JSON_URL = "url";
  public static final String JSON_DATA_TYPE = "dataType";
  public static final String JSON_CACHE = "cache";
  public static final String JSON_TIMEOUT = "timeout";
  public static final String JSON_WILDCARD = "wildcard";
  public static final String JSON_REPLACE = "replace";
  public static final String JSON_RATE_LIMIT_FN = "rateLimitFn";
  public static final String JSON_RATE_LIMIT_WAIT = "rateLimitWait";
  public static final String JSON_MAX_PARALLEL_REQUESTS = "maxParallelRequests";
  public static final String JSON_BEFORE_SEND = "beforeSend";
  public static final String JSON_FILTER = "filter";

  public static final String DEFAULT_DATA_TYPE = "json";
  // In the typeahead this is "%QUERY" but because of % URL encoding this sucks
  public static final String DEFAULT_WILDCARD = "_query_";
  public static final ETypeaheadRemoteRateLimitFunction DEFAULT_RATE_LIMIT_FN = ETypeaheadRemoteRateLimitFunction.DEBOUNCE;
  public static final int DEFAULT_RATE_LIMIT_WAIT = 300;
  public static final int DEFAULT_MAX_PARALLEL_REQUESTS = 6;

  private final SimpleURL m_aURL;
  private String m_sDataType = DEFAULT_DATA_TYPE;
  private ETriState m_eCache = ETriState.UNDEFINED;
  private int m_nTimeout;
  private String m_sWildcard = DEFAULT_WILDCARD;
  private JSAnonymousFunction m_aReplace;
  private ETypeaheadRemoteRateLimitFunction m_eRateLimitFn = DEFAULT_RATE_LIMIT_FN;
  private int m_nRateLimitWait = DEFAULT_RATE_LIMIT_WAIT;
  private int m_nMaxParallelRequests = DEFAULT_MAX_PARALLEL_REQUESTS;
  private JSAnonymousFunction m_aBeforeSend;
  private JSAnonymousFunction m_aFilter;

  /**
   * Constructor
   *
   * @param aURL
   *        A URL to make requests to when when the data provided by local and
   *        prefetch is insufficient.
   */
  public TypeaheadRemote (@Nonnull final ISimpleURL aURL)
  {
    ValueEnforcer.notNull (aURL, "URL");

    m_aURL = new SimpleURL (aURL);
  }

  public TypeaheadRemote (@Nonnull final TypeaheadRemote aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");

    m_aURL = aOther.m_aURL.getClone ();
    m_sDataType = aOther.m_sDataType;
    m_eCache = aOther.m_eCache;
    m_nTimeout = aOther.m_nTimeout;
    m_sWildcard = aOther.m_sWildcard;
    m_aReplace = aOther.m_aReplace;
    m_eRateLimitFn = aOther.m_eRateLimitFn;
    m_nRateLimitWait = aOther.m_nRateLimitWait;
    m_nMaxParallelRequests = aOther.m_nMaxParallelRequests;
    m_aBeforeSend = aOther.m_aBeforeSend;
    m_aFilter = aOther.m_aFilter;
  }

  /**
   * @return A URL to a JSON file containing an array of datums.
   */
  @Nonnull
  public ISimpleURL getURL ()
  {
    return m_aURL;
  }

  /**
   * The type of data you're expecting from the server. See the jQuery.ajax docs
   * for more info. Defaults to <code>json</code>.
   *
   * @param sDataType
   *        The data type to use. May neither be <code>null</code> nor empty.
   * @return this
   */
  @Nonnull
  public TypeaheadRemote setDataType (@Nonnull @Nonempty final String sDataType)
  {
    m_sDataType = ValueEnforcer.notEmpty (sDataType, "DataType");
    return this;
  }

  /**
   * @return The type of data you're expecting from the server. See the
   *         jQuery.ajax docs for more info. Defaults to <code>json</code>.
   */
  @Nonnull
  @Nonempty
  public String getDataType ()
  {
    return m_sDataType;
  }

  /**
   * Determines whether or not the browser will cache responses. See the
   * jQuery.ajax docs for more info.
   *
   * @param bCache
   *        Use cache?
   * @return this
   */
  @Nonnull
  public TypeaheadRemote setCache (final boolean bCache)
  {
    return setCache (ETriState.valueOf (bCache));
  }

  /**
   * Determines whether or not the browser will cache responses. See the
   * jQuery.ajax docs for more info.
   *
   * @param eCache
   *        Use cache? May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  public TypeaheadRemote setCache (@Nonnull final ETriState eCache)
  {
    m_eCache = ValueEnforcer.notNull (eCache, "Cache");
    return this;
  }

  /**
   * @return Determines whether or not the browser will cache responses. See the
   *         jQuery.ajax docs for more info.
   */
  @Nonnull
  public ETriState getCache ()
  {
    return m_eCache;
  }

  /**
   * Sets a timeout for requests. See the jQuery.ajax docs for more info.
   *
   * @param nTimeout
   *        Timeout to use.
   * @return this
   */
  @Nonnull
  public TypeaheadRemote setTimeout (final int nTimeout)
  {
    m_nTimeout = nTimeout;
    return this;
  }

  /**
   * @return Sets a timeout for requests. See the jQuery.ajax docs for more
   *         info.
   */
  public int getTimeout ()
  {
    return m_nTimeout;
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
  public TypeaheadRemote setWildcard (@Nullable final String sWildcard)
  {
    m_sWildcard = sWildcard;
    return this;
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
   * A function with the signature <code>replace(url, uriEncodedQuery)</code>
   * that can be used to override the request URL. Expected to return a valid
   * URL. If set, no wildcard substitution will be performed on <code>url</code>
   * .
   *
   * @param aReplace
   *        The function to use. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public TypeaheadRemote setReplace (@Nullable final JSAnonymousFunction aReplace)
  {
    m_aReplace = aReplace;
    return this;
  }

  /**
   * @return A function with the signature
   *         <code>replace(url, uriEncodedQuery)</code> that can be used to
   *         override the request URL. Expected to return a valid URL. If set,
   *         no wildcard substitution will be performed on <code>url</code>.
   */
  @Nullable
  public JSAnonymousFunction getReplace ()
  {
    return m_aReplace;
  }

  /**
   * The function used for rate-limiting network requests. Can be either
   * <code>debounce</code> or <code>throttle</code>. Defaults to
   * <code>debounce</code>.
   *
   * @param eRateLimitFn
   *        function to use. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  public TypeaheadRemote setRateLimitFn (@Nonnull final ETypeaheadRemoteRateLimitFunction eRateLimitFn)
  {
    m_eRateLimitFn = ValueEnforcer.notNull (eRateLimitFn, "RateLimitFunction");
    return this;
  }

  /**
   * @return The function used for rate-limiting network requests. Can be either
   *         <code>debounce</code> or <code>throttle</code>. Defaults to
   *         <code>debounce</code>.
   */
  @Nonnull
  public ETypeaheadRemoteRateLimitFunction getRateLimitFn ()
  {
    return m_eRateLimitFn;
  }

  /**
   * The time interval in milliseconds that will be used by
   * <code>rateLimitFn</code>. Defaults to <code>300</code>.
   *
   * @param nRateLimitWait
   *        Milliseconds to use. Must be &ge; 1.
   * @return this
   */
  @Nonnull
  public TypeaheadRemote setRateLimitWait (@Nonnegative final int nRateLimitWait)
  {
    m_nRateLimitWait = ValueEnforcer.isGT0 (nRateLimitWait, "RateLimitWait");
    return this;
  }

  /**
   * @return The time interval in milliseconds that will be used by
   *         <code>rateLimitFn</code>. Defaults to <code>300</code>.
   */
  @Nonnegative
  public int getRateLimitWait ()
  {
    return m_nRateLimitWait;
  }

  /**
   * The max number of parallel requests typeahead.js can have pending. Defaults
   * to <code>6</code>.
   *
   * @param nMaxParallelRequests
   *        Maximum parallel requests. Must be &ge; 1.
   * @return this
   */
  @Nonnull
  public TypeaheadRemote setMaxParallelRequests (@Nonnegative final int nMaxParallelRequests)
  {
    m_nMaxParallelRequests = ValueEnforcer.isGT0 (nMaxParallelRequests, "MaxParallelRequests");
    return this;
  }

  /**
   * @return The max number of parallel requests typeahead.js can have pending.
   *         Defaults to <code>6</code>.
   */
  @Nonnegative
  public int getMaxParallelRequests ()
  {
    return m_nMaxParallelRequests;
  }

  /**
   * A pre-request callback with the signature
   * <code>beforeSend(jqXhr,settings)</code>. Can be used to set custom headers.
   * See the jQuery.ajax docs for more info.
   *
   * @param aBeforeSend
   *        Function to use. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public TypeaheadRemote setBeforeSend (@Nullable final JSAnonymousFunction aBeforeSend)
  {
    m_aBeforeSend = aBeforeSend;
    return this;
  }

  /**
   * @return A pre-request callback with the signature
   *         <code>beforeSend(jqXhr,settings)</code>. Can be used to set custom
   *         headers. See the jQuery.ajax docs for more info.
   */
  @Nullable
  public JSAnonymousFunction getBeforeSend ()
  {
    return m_aBeforeSend;
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
  public TypeaheadRemote setFilter (@Nullable final JSAnonymousFunction aFilter)
  {
    m_aFilter = aFilter;
    return this;
  }

  /**
   * @return A function with the signature <code>filter(parsedResponse)</code>
   *         that transforms the response body into an array of datums. Expected
   *         to return an array of datums.
   */
  @Nullable
  public JSAnonymousFunction getFilter ()
  {
    return m_aFilter;
  }

  @Nonnull
  @ReturnsMutableCopy
  public JSAssocArray getAsJSObject ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    ret.add (JSON_URL, m_aURL.getAsString ());
    if (!m_sDataType.equals (DEFAULT_DATA_TYPE))
      ret.add (JSON_DATA_TYPE, m_sDataType);
    if (m_eCache.isDefined ())
      ret.add (JSON_CACHE, m_eCache.getAsBooleanValue (false));
    if (m_nTimeout != 0)
      ret.add (JSON_TIMEOUT, m_nTimeout);
    if (m_sWildcard != null)
      ret.add (JSON_WILDCARD, m_sWildcard);
    if (m_aReplace != null)
      ret.add (JSON_REPLACE, m_aReplace);
    if (!m_eRateLimitFn.equals (DEFAULT_RATE_LIMIT_FN))
      ret.add (JSON_RATE_LIMIT_FN, m_eRateLimitFn.getValue ());
    if (m_nRateLimitWait != DEFAULT_RATE_LIMIT_WAIT)
      ret.add (JSON_RATE_LIMIT_WAIT, m_nRateLimitWait);
    if (m_nMaxParallelRequests != DEFAULT_MAX_PARALLEL_REQUESTS)
      ret.add (JSON_MAX_PARALLEL_REQUESTS, m_nMaxParallelRequests);
    if (m_aBeforeSend != null)
      ret.add (JSON_BEFORE_SEND, m_aBeforeSend);
    if (m_aFilter != null)
      ret.add (JSON_FILTER, m_aFilter);
    return ret;
  }

  @Nonnull
  public TypeaheadRemote getClone ()
  {
    return new TypeaheadRemote (this);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("URL", m_aURL)
                                       .append ("dataType", m_sDataType)
                                       .append ("cache", m_eCache)
                                       .append ("timeout", m_nTimeout)
                                       .appendIfNotNull ("wildcard", m_sWildcard)
                                       .appendIfNotNull ("replace", m_aReplace)
                                       .append ("rateLimitFn", m_eRateLimitFn)
                                       .append ("rateLimitWait", m_nRateLimitWait)
                                       .append ("maxParallelRequests", m_nMaxParallelRequests)
                                       .appendIfNotNull ("beforeSend", m_aBeforeSend)
                                       .appendIfNotNull ("filter", m_aFilter)
                                       .getToString ();
  }
}
