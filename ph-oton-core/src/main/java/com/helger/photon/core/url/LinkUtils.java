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
package com.helger.photon.core.url;

import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.SimpleURL;
import com.helger.commons.url.URLProtocolRegistry;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;
import com.helger.web.scopes.mgr.WebScopeManager;

/**
 * Misc utilities to create link URLs.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class LinkUtils
{
  public static final String STREAM_SERVLET_NAME_REGEX = "[a-zA-Z0-9-_]+";

  /**
   * The default name of the stream servlet. If this is different in you
   * application you may not use the methods that refer to this path!
   */
  public static final String DEFAULT_STREAM_SERVLET_NAME = "stream";

  private static final Logger s_aLogger = LoggerFactory.getLogger (LinkUtils.class);
  private static final ReadWriteLock s_aRWLock = new ReentrantReadWriteLock ();

  @GuardedBy ("s_aRWLock")
  private static String s_sStreamServletName = DEFAULT_STREAM_SERVLET_NAME;

  private LinkUtils ()
  {}

  public static void setStreamServletName (@Nonnull @Nonempty final String sStreamServletName)
  {
    ValueEnforcer.notEmpty (sStreamServletName, "StreamServletName");
    if (!RegExHelper.stringMatchesPattern (STREAM_SERVLET_NAME_REGEX, sStreamServletName))
      throw new IllegalArgumentException ("Invalid StreamServletName '" +
                                          sStreamServletName +
                                          "' passed. It must match the following rexg: " +
                                          STREAM_SERVLET_NAME_REGEX);

    s_aRWLock.writeLock ().lock ();
    try
    {
      s_sStreamServletName = sStreamServletName;
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  /**
   * @return The name of the stream servlet path. Default is <code>stream</code>
   * @see #getStreamServletPath()
   */
  @Nonnull
  @Nonempty
  public static String getStreamServletName ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return s_sStreamServletName;
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * @return The relative path of the stream servlet. Default is
   *         <code>/stream</code>
   * @see #getStreamServletName()
   */
  @Nonnull
  @Nonempty
  public static String getStreamServletPath ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return "/" + s_sStreamServletName;
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  private static String _getURIWithContext (@Nonnull final String sContextPath, @Nonnull final String sHRef)
  {
    if (StringHelper.hasText (sContextPath) && sHRef.startsWith (sContextPath))
    {
      s_aLogger.warn ("The passed href '" + sHRef + "' already contains the context path '" + sContextPath + "'!");
      return sHRef;
    }

    // Always prefix with context path!
    final StringBuilder aSB = new StringBuilder (sContextPath);
    if (!StringHelper.startsWith (sHRef, '/'))
      aSB.append ('/');
    return aSB.append (sHRef).toString ();
  }

  /**
   * Prefix the passed href with the relative context path in case the passed
   * href has no protocol yet.<br>
   * Important: this method does not add the session ID in case cookies are
   * disabled by the client!
   *
   * @param sHRef
   *        The href to be extended. May not be <code>null</code>.
   * @return Either the original href if already absolute or
   *         <code>/webapp-context/<i>href</i></code> otherwise. Never
   *         <code>null</code>.
   */
  @Nonnull
  public static String getURIWithContext (@Nonnull final String sHRef)
  {
    ValueEnforcer.notNull (sHRef, "HRef");

    // If known protocol, keep it
    if (URLProtocolRegistry.getInstance ().hasKnownProtocol (sHRef))
      return sHRef;

    final String sContextPath = WebScopeManager.getGlobalScope ().getContextPath ();
    return _getURIWithContext (sContextPath, sHRef);
  }

  /**
   * Prefix the passed href with the relative context path in case the passed
   * href has no protocol yet.
   *
   * @param aRequestScope
   *        The request web scope to be used. Required for cookie-less handling.
   *        May not be <code>null</code>.
   * @param sHRef
   *        The href to be extended. May not be <code>null</code>.
   * @return Either the original href if already absolute or
   *         <code>/webapp-context/<i>href</i></code> otherwise. Never
   *         <code>null</code>.
   */
  @Nonnull
  public static String getURIWithContext (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                          @Nonnull final String sHRef)
  {
    ValueEnforcer.notNull (aRequestScope, "RequestScope");
    ValueEnforcer.notNull (sHRef, "HRef");

    // If known protocol, keep it
    if (URLProtocolRegistry.getInstance ().hasKnownProtocol (sHRef))
      return sHRef;

    final String sContextPath = aRequestScope.getContextPath ();
    return aRequestScope.encodeURL (_getURIWithContext (sContextPath, sHRef));
  }

  /**
   * Prefix the passed href with the relative context path in case the passed
   * href has no protocol yet.<br>
   * Important: this method does not add the session ID in case cookies are
   * disabled by the client!
   *
   * @param sHRef
   *        The href to be extended. May not be <code>null</code>.
   * @return Either the original href if already absolute or
   *         <code>/webapp-context/<i>href</i></code> otherwise. Never
   *         <code>null</code>.
   */
  @Nonnull
  public static SimpleURL getURLWithContext (@Nonnull final String sHRef)
  {
    return getURLWithContext (sHRef, (Map <String, String>) null);
  }

  /**
   * Prefix the passed href with the relative context path in case the passed
   * href has no protocol yet.
   *
   * @param aRequestScope
   *        The request web scope to be used. Required for cookie-less handling.
   *        May not be <code>null</code>.
   * @param sHRef
   *        The href to be extended. May not be <code>null</code>.
   * @return Either the original href if already absolute or
   *         <code>/webapp-context/<i>href</i></code> otherwise. Never
   *         <code>null</code>.
   */
  @Nonnull
  public static SimpleURL getURLWithContext (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                             @Nonnull final String sHRef)
  {
    return getURLWithContext (aRequestScope, sHRef, (Map <String, String>) null);
  }

  /**
   * Prefix the passed href with the relative context path in case the passed
   * href has no protocol yet.<br>
   * Important: this method does not add the session ID in case cookies are
   * disabled by the client!
   *
   * @param sHRef
   *        The href to be extended. May not be <code>null</code>.
   * @param aParams
   *        Optional parameter map. May be <code>null</code>.
   * @return Either the original href if already absolute or
   *         <code>/webapp-context/<i>href</i></code> otherwise. Never
   *         <code>null</code>.
   */
  @Nonnull
  public static SimpleURL getURLWithContext (@Nonnull final String sHRef, @Nullable final Map <String, String> aParams)
  {
    return new SimpleURL (getURIWithContext (sHRef), aParams);
  }

  /**
   * Prefix the passed href with the relative context path in case the passed
   * href has no protocol yet.
   *
   * @param aRequestScope
   *        The request web scope to be used. Required for cookie-less handling.
   *        May not be <code>null</code>.
   * @param sHRef
   *        The href to be extended. May not be <code>null</code>.
   * @param aParams
   *        Optional parameter map. May be <code>null</code>.
   * @return Either the original href if already absolute or
   *         <code>/webapp-context/<i>href</i></code> otherwise. Never
   *         <code>null</code>.
   */
  @Nonnull
  public static SimpleURL getURLWithContext (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                             @Nonnull final String sHRef,
                                             @Nullable final Map <String, String> aParams)
  {
    final String sURIWithContext = getURIWithContext (aRequestScope, sHRef);
    return new SimpleURL (sURIWithContext, aParams);
  }

  /**
   * Prefix the passed href with the absolute server + context path in case the
   * passed href has no protocol yet.<br>
   * Important: this method does not add the session ID in case cookies are
   * disabled by the client!
   *
   * @param sHRef
   *        The href to be extended. May not be <code>null</code>.
   * @return Either the original href if already absolute or
   *         <code>http://servername:8123/webapp-context/<i>href</i></code>
   *         otherwise. Never <code>null</code>.
   */
  @Nonnull
  public static String getURIWithServerAndContext (@Nonnull final String sHRef)
  {
    // If known protocol, keep it
    if (URLProtocolRegistry.getInstance ().hasKnownProtocol (sHRef))
      return sHRef;

    final IRequestWebScopeWithoutResponse aRequestScope = WebScopeManager.getRequestScope ();
    final String sContextPath = aRequestScope.getContextPath ();
    return aRequestScope.getFullServerPath () + _getURIWithContext (sContextPath, sHRef);
  }

  /**
   * Prefix the passed href with the absolute server + context path in case the
   * passed href has no protocol yet.
   *
   * @param aRequestScope
   *        The request web scope to be used. Required for cookie-less handling.
   *        May not be <code>null</code>.
   * @param sHRef
   *        The href to be extended. May not be <code>null</code>.
   * @return Either the original href if already absolute or
   *         <code>http://servername:8123/webapp-context/<i>href</i></code>
   *         otherwise. Never <code>null</code>.
   */
  @Nonnull
  public static String getURIWithServerAndContext (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                   @Nonnull final String sHRef)
  {
    ValueEnforcer.notNull (aRequestScope, "RequestScope");
    ValueEnforcer.notNull (sHRef, "HRef");

    // If known protocol, keep it
    if (URLProtocolRegistry.getInstance ().hasKnownProtocol (sHRef))
      return sHRef;

    final String sContextPath = aRequestScope.getContextPath ();
    final String sURI = aRequestScope.getFullServerPath () + _getURIWithContext (sContextPath, sHRef);
    return aRequestScope.encodeURL (sURI);
  }

  /**
   * Prefix the passed href with the absolute server + context path in case the
   * passed href has no protocol yet.<br>
   * Important: this method does not add the session ID in case cookies are
   * disabled by the client!
   *
   * @param sHRef
   *        The href to be extended.
   * @return Either the original href if already absolute or
   *         <code>http://servername:8123/webapp-context/<i>href</i></code>
   *         otherwise.
   */
  @Nonnull
  public static SimpleURL getURLWithServerAndContext (@Nonnull final String sHRef)
  {
    return getURLWithServerAndContext (sHRef, (Map <String, String>) null);
  }

  /**
   * Prefix the passed href with the absolute server + context path in case the
   * passed href has no protocol yet.
   *
   * @param aRequestScope
   *        The request web scope to be used. Required for cookie-less handling.
   *        May not be <code>null</code>.
   * @param sHRef
   *        The href to be extended.
   * @return Either the original href if already absolute or
   *         <code>http://servername:8123/webapp-context/<i>href</i></code>
   *         otherwise.
   */
  @Nonnull
  public static SimpleURL getURLWithServerAndContext (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                      @Nonnull final String sHRef)
  {
    return getURLWithServerAndContext (aRequestScope, sHRef, (Map <String, String>) null);
  }

  /**
   * Prefix the passed href with the absolute server + context path in case the
   * passed href has no protocol yet.<br>
   * Important: this method does not add the session ID in case cookies are
   * disabled by the client!
   *
   * @param sHRef
   *        The href to be extended.
   * @param aParams
   *        params
   * @return Either the original href if already absolute or
   *         <code>http://servername:8123/webapp-context/<i>href</i></code>
   *         otherwise.
   */
  @Nonnull
  public static SimpleURL getURLWithServerAndContext (@Nonnull final String sHRef,
                                                      @Nullable final Map <String, String> aParams)
  {
    return new SimpleURL (getURIWithServerAndContext (sHRef), aParams);
  }

  /**
   * Prefix the passed href with the absolute server + context path in case the
   * passed href has no protocol yet.
   *
   * @param aRequestScope
   *        The request web scope to be used. Required for cookie-less handling.
   *        May not be <code>null</code>.
   * @param sHRef
   *        The href to be extended.
   * @param aParams
   *        params
   * @return Either the original href if already absolute or
   *         <code>http://servername:8123/webapp-context/<i>href</i></code>
   *         otherwise.
   */
  @Nonnull
  public static SimpleURL getURLWithServerAndContext (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                      @Nonnull final String sHRef,
                                                      @Nullable final Map <String, String> aParams)
  {
    final String sURI = getURIWithServerAndContext (aRequestScope, sHRef);
    return new SimpleURL (sURI, aParams);
  }

  /**
   * @return A link to the start page without any session ID. Never
   *         <code>null</code>. E.g. <code>/</code> or <code>/context</code>.
   *         This is useful for logout links.
   */
  @Nonnull
  public static SimpleURL getHomeLinkWithoutSession ()
  {
    final String sContextPath = WebScopeManager.getGlobalScope ().getContextPath ();
    return new SimpleURL (sContextPath.length () == 0 ? "/" : sContextPath);
  }

  /**
   * Get the default URL to stream the passed URL. It is assumed that the
   * servlet is located under the path "/stream". Because of the logic of the
   * stream servlet, no parameter are assumed.
   *
   * @param aRequestScope
   *        The request web scope to be used. Required for cookie-less handling.
   *        May not be <code>null</code>.
   * @param sURL
   *        The URL to be streamed. If it does not start with a slash ("/") one
   *        is prepended automatically. If the URL already has a protocol, it is
   *        returned unchanged. May neither be <code>null</code> nor empty.
   * @return The URL incl. the context to be stream. E.g.
   *         <code>/<i>webapp-context</i>/stream/<i>URL</i></code>.
   */
  @Nonnull
  public static SimpleURL getStreamURL (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                        @Nonnull @Nonempty final String sURL)
  {
    ValueEnforcer.notNull (aRequestScope, "RequestScope");
    ValueEnforcer.notEmpty (sURL, "URL");

    // If the URL is absolute, use it
    if (URLProtocolRegistry.getInstance ().hasKnownProtocol (sURL))
      return new SimpleURL (sURL);

    final StringBuilder aPrefix = new StringBuilder (getStreamServletPath ());
    if (!StringHelper.startsWith (sURL, '/'))
      aPrefix.append ('/');
    return getURLWithContext (aRequestScope, aPrefix.append (sURL).toString ());
  }
}
