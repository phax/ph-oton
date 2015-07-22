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
package com.helger.photon.core.servlet;

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.CGlobal;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.commons.lang.ClassHelper;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.scope.mgr.ScopeManager;
import com.helger.commons.state.EContinue;
import com.helger.commons.statistics.IMutableStatisticsHandlerCounter;
import com.helger.commons.statistics.IMutableStatisticsHandlerKeyedCounter;
import com.helger.commons.statistics.StatisticsManager;
import com.helger.commons.string.StringHelper;
import com.helger.datetime.PDTFactory;
import com.helger.datetime.util.PDTHelper;
import com.helger.photon.core.requesttrack.RequestTracker;
import com.helger.photon.core.servletstatus.ServletStatusManager;
import com.helger.web.http.CHTTPHeader;
import com.helger.web.http.EHTTPMethod;
import com.helger.web.http.EHTTPVersion;
import com.helger.web.scope.domain.IRequestWebScope;
import com.helger.web.scope.domain.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.mgr.WebScopeManager;
import com.helger.web.scope.servlet.AbstractScopeAwareHttpServlet;
import com.helger.web.scope.servlet.RequestScopeInitializer;
import com.helger.web.servlet.request.RequestHelper;
import com.helger.web.servlet.response.UnifiedResponse;
import com.helger.web.servlet.server.StaticServerInfo;

/**
 * Abstract base class for a servlets delivering responses via
 * {@link UnifiedResponse}.
 *
 * @author Philip Helger
 * @since 3.7.0
 */
public abstract class AbstractUnifiedResponseServlet extends AbstractScopeAwareHttpServlet
{
  /** Default allowed methods: HEAD, GET and POST */
  public static final EnumSet <EHTTPMethod> DEFAULT_ALLOWED_METHDOS = EnumSet.of (EHTTPMethod.HEAD,
                                                                                  EHTTPMethod.GET,
                                                                                  EHTTPMethod.POST);
  /** Allowed methods: GET and POST */
  public static final EnumSet <EHTTPMethod> ALLOWED_METHDOS_GET_POST = EnumSet.of (EHTTPMethod.GET, EHTTPMethod.POST);
  /** Allowed methods: GET */
  public static final EnumSet <EHTTPMethod> ALLOWED_METHDOS_GET = EnumSet.of (EHTTPMethod.GET);
  /** Allowed methods: POST */
  public static final EnumSet <EHTTPMethod> ALLOWED_METHDOS_POST = EnumSet.of (EHTTPMethod.POST);

  /** The name of the request attribute uniquely identifying the request ID */
  public static final String REQUEST_ATTR_ID = ScopeManager.SCOPE_ATTRIBUTE_PREFIX_INTERNAL + "request.id";

  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractUnifiedResponseServlet.class);
  private static final AtomicLong s_aRequestID = new AtomicLong (0);
  private static final AtomicBoolean s_aFirstRequest = new AtomicBoolean (true);

  private final IMutableStatisticsHandlerKeyedCounter m_aStatsHttpVersion = StatisticsManager.getKeyedCounterHandler (getClass ().getName () +
                                                                                                                      "$httpversion");
  private final IMutableStatisticsHandlerKeyedCounter m_aStatsHttpMethodDisallowed = StatisticsManager.getKeyedCounterHandler (getClass ().getName () +
                                                                                                                               "$httpmethod.disallowed");
  private final IMutableStatisticsHandlerKeyedCounter m_aStatsHttpMethodAllowed = StatisticsManager.getKeyedCounterHandler (getClass ().getName () +
                                                                                                                            "$httpmethod.allowed");
  private final IMutableStatisticsHandlerCounter m_aStatsInitFailure = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                            "$init.failure");
  private final IMutableStatisticsHandlerCounter m_aStatsInitSuccess = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                            "$init.success");
  private final IMutableStatisticsHandlerCounter m_aStatsHasLastModification = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                                    "$has-lastmodification");
  private final IMutableStatisticsHandlerCounter m_aStatsHasETag = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                        "$has-etag");
  private final IMutableStatisticsHandlerCounter m_aStatsNotModifiedIfModifiedSince = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                                           "$notmodified.if-modified-since");
  private final IMutableStatisticsHandlerCounter m_aStatsModifiedIfModifiedSince = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                                        "$modified.if-modified-since");
  private final IMutableStatisticsHandlerCounter m_aStatsNotModifiedIfUnmodifiedSince = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                                             "$notmodified.if-unmodified-since");
  private final IMutableStatisticsHandlerCounter m_aStatsModifiedIfUnmodifiedSince = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                                          "$modified.if-unmodified-since");
  private final IMutableStatisticsHandlerCounter m_aStatsNotModifiedIfNonMatch = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                                      "$notmodified.if-unon-match");
  private final IMutableStatisticsHandlerCounter m_aStatsModifiedIfNonMatch = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                                   "$modified.if-unon-match");
  private final IMutableStatisticsHandlerCounter m_aStatsOnRequestBeginFailure = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                                      "$on-request-begin.failure");
  private final IMutableStatisticsHandlerCounter m_aStatsHandledRequestsTotal = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                                     "$handled-requests.total");
  private final IMutableStatisticsHandlerCounter m_aStatsHandledRequestsSuccess = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                                       "$handled-requests.success");
  private final IMutableStatisticsHandlerCounter m_aStatsHandledRequestsFailure = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                                       "$handled-requests.failure");
  private final IMutableStatisticsHandlerCounter m_aStatsOnRequestEndFailure = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                                    "$on-request-end.failure");

  protected AbstractUnifiedResponseServlet ()
  {
    ServletStatusManager.onServletCtor (getClass ());
  }

  protected static final long getUnifiedMillis (final long nMillis)
  {
    // Round down to the nearest second for a proper compare (Java has milli
    // seconds, HTTP requests/responses have not)
    return nMillis / CGlobal.MILLISECONDS_PER_SECOND * CGlobal.MILLISECONDS_PER_SECOND;
  }

  @Nonnull
  protected static final LocalDateTime convertMillisToDateTimeGMT (final long nMillis)
  {
    // Round down to the nearest second for a proper compare
    return PDTFactory.createLocalDateTimeFromMillis (getUnifiedMillis (nMillis));
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void onInit () throws ServletException
  {
    super.onInit ();
    ServletStatusManager.onServletInit (getClass ());
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void onDestroy ()
  {
    ServletStatusManager.onServletDestroy (getClass ());
    super.onDestroy ();
  }

  /**
   * Overwrite only, to avoid further overloading in sub classes.
   */
  @Override
  protected final RequestScopeInitializer beforeRequest (@Nonnull final HttpServletRequest aHttpRequest,
                                                         @Nonnull final HttpServletResponse aHttpResponse)
  {
    return super.beforeRequest (aHttpRequest, aHttpResponse);
  }

  /**
   * Check if the passed HTTP method is allowed. If not, a
   * {@link HttpServletResponse#SC_METHOD_NOT_ALLOWED} will be returned. By
   * default only {@link EHTTPMethod#GET} and {@link EHTTPMethod#POST} are
   * allowed.
   *
   * @return A non-<code>null</code> set of all allowed HTTP methods.
   */
  @OverrideOnDemand
  @Nonnull
  @ReturnsMutableCopy
  protected Set <EHTTPMethod> getAllowedHTTPMethods ()
  {
    return CollectionHelper.newSet (DEFAULT_ALLOWED_METHDOS);
  }

  /**
   * This callback method is unconditionally called before the last-modification
   * checks are performed. So this method can be used to determine the requested
   * object from the request. This method is not called if HTTP version or HTTP
   * method are not supported.
   *
   * @param aRequestScope
   *        The request scope that will be used for processing the request.
   *        Never <code>null</code>.
   * @param aUnifiedResponse
   *        The response object to be filled. Never <code>null</code>.
   * @return {@link EContinue#BREAK} to stop processing (e.g. because a resource
   *         does not exist), {@link EContinue#CONTINUE} to continue processing
   *         as usual.
   */
  @OverrideOnDemand
  protected EContinue initRequestState (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                        @Nonnull final UnifiedResponse aUnifiedResponse)
  {
    return EContinue.CONTINUE;
  }

  /**
   * Get the last modification date time for the current request. If it was not
   * modified since the last request time, a 304 (not modified) response code is
   * returned. This method is always called for GET and HEAD requests. This
   * method is called after
   * {@link #initRequestState(IRequestWebScopeWithoutResponse, UnifiedResponse)}
   * .
   *
   * @param aRequestScope
   *        The request scope that will be used for processing the request.
   *        Never <code>null</code>.
   * @return <code>null</code> if no last modification date time can be
   *         determined
   */
  @OverrideOnDemand
  @Nullable
  protected LocalDateTime getLastModificationDateTime (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return null;
  }

  /**
   * Get the ETag supported for this request. If an ETag matches, a 304 (not
   * modified) response code is returned. This method is always called for GET
   * and HEAD requests.
   *
   * @param aRequestScope
   *        The request scope that will be used for processing the request.
   *        Never <code>null</code>.
   * @return <code>null</code> if this servlet does not support ETags
   */
  @OverrideOnDemand
  @Nullable
  protected String getSupportedETag (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return null;
  }

  /**
   * Called before a valid request is handled. This method is only called if
   * HTTP version matches, HTTP method is supported and sending a cached HTTP
   * response is not an option.
   *
   * @param aRequestScope
   *        The request scope that will be used for processing the request.
   *        Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void onRequestBegin (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {}

  /**
   * This is the main request handling method. Overwrite this method to fill
   * your HTTP response.
   *
   * @param aRequestScope
   *        The request scope to use. There is no direct access to the
   *        {@link HttpServletResponse}. Everything must be handled with the
   *        unified response! Never <code>null</code>.
   * @param aUnifiedResponse
   *        The response object to be filled. Never <code>null</code>.
   * @throws Exception
   *         In case of an error
   */
  protected abstract void handleRequest (@Nonnull IRequestWebScopeWithoutResponse aRequestScope,
                                         @Nonnull UnifiedResponse aUnifiedResponse) throws Exception;

  /**
   * Called when an exception occurred in
   * {@link #handleRequest(IRequestWebScopeWithoutResponse, UnifiedResponse)}.
   * This method is only called for non-request-cancel operations.
   *
   * @param aRequestScope
   *        The source request scope. Never <code>null</code>.
   * @param t
   */
  @OverrideOnDemand
  protected void onException (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope, @Nonnull final Throwable t)
  {
    s_aLogger.error ("Error running servlet " +
                     getClass () +
                     " using " +
                     aRequestScope.getMethod () +
                     " on URI " +
                     aRequestScope.getURL (),
                     t);
  }

  /**
   * Called after a valid request was processed. This method is only called if
   * the handleRequest method was invoked. If an exception occurred this method
   * is called after onException
   *
   * @param bExceptionOccurred
   *        if <code>true</code> an exception occurred in request processing.
   */
  @OverrideOnDemand
  protected void onRequestEnd (final boolean bExceptionOccurred)
  {}

  private static void _trackBeforeHandleRequest (@Nonnull final IRequestWebScope aRequestScope)
  {
    // Create a unique ID for the request
    final String sID = Long.toString (s_aRequestID.incrementAndGet ());
    aRequestScope.setAttribute (REQUEST_ATTR_ID, sID);
    RequestTracker.addRequest (sID, aRequestScope);
  }

  private static void _trackAfterHandleRequest (@Nonnull final IRequestWebScope aRequestScope)
  {
    final String sID = aRequestScope.getAttributeAsString (REQUEST_ATTR_ID);
    RequestTracker.removeRequest (sID);
  }

  private void _run (@Nonnull final HttpServletRequest aHttpRequest,
                     @Nonnull final HttpServletResponse aHttpResponse,
                     @Nonnull final IRequestWebScope aRequestScope,
                     @Nonnull final EHTTPMethod eHTTPMethod) throws ServletException, IOException
  {
    // Increment invocation counter
    ServletStatusManager.onServletInvocation (getClass ());

    final EHTTPVersion eHTTPVersion = RequestHelper.getHttpVersion (aHttpRequest);
    if (eHTTPVersion == null)
    {
      // HTTP version disallowed
      s_aLogger.warn ("Request " +
                      aRequestScope.getURL () +
                      " has no valid HTTP version (" +
                      aHttpRequest.getProtocol () +
                      ")!");
      aHttpResponse.sendError (HttpServletResponse.SC_HTTP_VERSION_NOT_SUPPORTED);
      return;
    }
    m_aStatsHttpVersion.increment (eHTTPVersion.getName ());

    // Notify event listeners about the very first event on this servlet
    // May already be set in test cases!
    if (s_aFirstRequest.getAndSet (false) && !StaticServerInfo.isSet ())
    {
      // First set the default web server info
      StaticServerInfo.init (aRequestScope.getScheme (),
                             aRequestScope.getServerName (),
                             aRequestScope.getServerPort (),
                             WebScopeManager.getGlobalScope ().getContextPath ());
    }

    final Set <EHTTPMethod> aAllowedHTTPMethods = getAllowedHTTPMethods ();
    if (!aAllowedHTTPMethods.contains (eHTTPMethod))
    {
      // Disallow method
      m_aStatsHttpMethodDisallowed.increment (eHTTPMethod.getName ());

      // Build Allow response header
      final StringBuilder aAllow = new StringBuilder ();
      for (final EHTTPMethod eAllowedHTTPMethod : aAllowedHTTPMethods)
      {
        if (aAllow.length () > 0)
          aAllow.append (", ");
        aAllow.append (eAllowedHTTPMethod.getName ());
      }
      final String sAllow = aAllow.toString ();
      s_aLogger.warn ("Request " +
                      aRequestScope.getURL () +
                      " uses disallowed HTTP method " +
                      eHTTPMethod +
                      "! Allowed methods are: " +
                      sAllow);
      aHttpResponse.setHeader (CHTTPHeader.ALLOW, sAllow);

      if (eHTTPVersion == EHTTPVersion.HTTP_11)
        aHttpResponse.sendError (HttpServletResponse.SC_METHOD_NOT_ALLOWED);
      else
        aHttpResponse.sendError (HttpServletResponse.SC_BAD_REQUEST);
      return;
    }
    m_aStatsHttpMethodAllowed.increment (eHTTPMethod.getName ());

    // Now all pre-conditions were checked. As the next step check some last
    // modification issues, for performance reasons. If all optimizations fail,
    // perform the regular request.

    final UnifiedResponse aUnifiedResponse = new UnifiedResponse (eHTTPVersion, eHTTPMethod, aHttpRequest);
    if (initRequestState (aRequestScope, aUnifiedResponse).isBreak ())
    {
      if (s_aLogger.isDebugEnabled ())
        s_aLogger.debug ("Cancelled request after initRequestState with response " + aUnifiedResponse);

      // May e.g. be an 404 error for some not-found resource
      m_aStatsInitFailure.increment ();
      aUnifiedResponse.applyToResponse (aHttpResponse);
      return;
    }
    m_aStatsInitSuccess.increment ();

    // Check for last-modification on GET and HEAD
    if (eHTTPMethod == EHTTPMethod.GET || eHTTPMethod == EHTTPMethod.HEAD)
    {
      final LocalDateTime aLastModification = getLastModificationDateTime (aRequestScope);
      if (aLastModification != null)
      {
        m_aStatsHasLastModification.increment ();

        // Get the If-Modified-Since date header
        final long nRequestIfModifiedSince = aHttpRequest.getDateHeader (CHTTPHeader.IF_MODIFIED_SINCE);
        if (nRequestIfModifiedSince >= 0)
        {
          final LocalDateTime aRequestIfModifiedSince = convertMillisToDateTimeGMT (nRequestIfModifiedSince);
          if (PDTHelper.isLessOrEqual (aLastModification, aRequestIfModifiedSince))
          {
            if (s_aLogger.isDebugEnabled ())
              s_aLogger.debug ("Requested resource was not modified: " + aRequestScope.getPathWithinServlet ());

            // Was not modified since the passed time
            m_aStatsNotModifiedIfModifiedSince.increment ();
            aUnifiedResponse.setStatus (HttpServletResponse.SC_NOT_MODIFIED).applyToResponse (aHttpResponse);
            return;
          }
          m_aStatsModifiedIfModifiedSince.increment ();
        }

        // Get the If-Unmodified-Since date header
        final long nRequestIfUnmodifiedSince = aHttpRequest.getDateHeader (CHTTPHeader.IF_UNMODIFIED_SINCE);
        if (nRequestIfUnmodifiedSince >= 0)
        {
          final LocalDateTime aRequestIfUnmodifiedSince = convertMillisToDateTimeGMT (nRequestIfUnmodifiedSince);
          if (PDTHelper.isGreaterOrEqual (aLastModification, aRequestIfUnmodifiedSince))
          {
            if (s_aLogger.isDebugEnabled ())
              s_aLogger.debug ("Requested resource was not modified: " + aRequestScope.getPathWithinServlet ());

            // Was not modified since the passed time
            m_aStatsNotModifiedIfUnmodifiedSince.increment ();
            aUnifiedResponse.setStatus (HttpServletResponse.SC_NOT_MODIFIED).applyToResponse (aHttpResponse);
            return;
          }
          m_aStatsModifiedIfUnmodifiedSince.increment ();
        }

        // No If-Modified-Since request header present, set the Last-Modified
        // header for later reuse
        aUnifiedResponse.setLastModified (aLastModification);
      }

      // Handle the ETag
      final String sSupportedETag = getSupportedETag (aRequestScope);
      if (StringHelper.hasText (sSupportedETag))
      {
        m_aStatsHasETag.increment ();

        // get the request ETag
        final String sRequestETags = aHttpRequest.getHeader (CHTTPHeader.IF_NON_MATCH);
        if (StringHelper.hasText (sRequestETags))
        {
          // Request header may contain several ETag values
          final List <String> aAllETags = RegExHelper.getSplitToList (sRequestETags, ",\\s+");
          if (aAllETags.isEmpty ())
            s_aLogger.warn ("Empty ETag list found (" + sRequestETags + ")");
          else
          {
            // Scan all found ETags for match
            for (final String sCurrentETag : aAllETags)
              if (sSupportedETag.equals (sCurrentETag))
              {
                if (s_aLogger.isDebugEnabled ())
                  s_aLogger.debug ("Requested resource has the same E-Tag: " + aRequestScope.getPathWithinServlet ());

                // We have a matching ETag
                m_aStatsNotModifiedIfNonMatch.increment ();
                aUnifiedResponse.setStatus (HttpServletResponse.SC_NOT_MODIFIED).applyToResponse (aHttpResponse);
                return;
              }
          }
          m_aStatsModifiedIfNonMatch.increment ();
        }

        // Save the ETag for the response
        aUnifiedResponse.setETagIfApplicable (sSupportedETag);
      }
    }

    // before-callback
    try
    {
      onRequestBegin (aRequestScope);
    }
    catch (final Throwable t)
    {
      m_aStatsOnRequestBeginFailure.increment ();
      s_aLogger.error ("onRequestBegin failed", t);
    }

    boolean bExceptionOccurred = true;
    try
    {
      _trackBeforeHandleRequest (aRequestScope);

      m_aStatsHandledRequestsTotal.increment ();

      // main servlet handling
      handleRequest (aRequestScope, aUnifiedResponse);
      // Only write to the response, if no error occurred
      aUnifiedResponse.applyToResponse (aHttpResponse);
      // No error occurred
      bExceptionOccurred = false;

      m_aStatsHandledRequestsSuccess.increment ();

      if (s_aLogger.isDebugEnabled ())
        s_aLogger.debug ("Successfully handled request: " + aRequestScope.getPathWithinServlet ());
    }
    catch (final Throwable t)
    {
      m_aStatsHandledRequestsFailure.increment ();
      // Do not show the exceptions that occur, when client cancels a request.
      if (StreamHelper.isKnownEOFException (t))
      {
        if (s_aLogger.isDebugEnabled ())
          s_aLogger.debug ("Error delivering requested resource '" +
                           aRequestScope.getPathWithinServlet () +
                           "' - " +
                           ClassHelper.getClassLocalName (t) +
                           " - " +
                           t.getMessage ());
      }
      else
      {
        onException (aRequestScope, t);

        if (t instanceof IOException)
          throw (IOException) t;
        if (t instanceof ServletException)
          throw (ServletException) t;
        throw new ServletException (t);
      }
    }
    finally
    {
      _trackAfterHandleRequest (aRequestScope);

      // after-callback
      try
      {
        onRequestEnd (bExceptionOccurred);
      }
      catch (final Throwable t)
      {
        m_aStatsOnRequestEndFailure.increment ();
        s_aLogger.error ("onRequestEnd failed", t);
      }
    }
  }

  @Override
  protected final void onDelete (@Nonnull final HttpServletRequest aHttpRequest,
                                 @Nonnull final HttpServletResponse aHttpResponse,
                                 @Nonnull final IRequestWebScope aRequestScope) throws ServletException, IOException
  {
    _run (aHttpRequest, aHttpResponse, aRequestScope, EHTTPMethod.DELETE);
  }

  @Override
  protected final void onGet (@Nonnull final HttpServletRequest aHttpRequest,
                              @Nonnull final HttpServletResponse aHttpResponse,
                              @Nonnull final IRequestWebScope aRequestScope) throws ServletException, IOException
  {
    _run (aHttpRequest, aHttpResponse, aRequestScope, EHTTPMethod.GET);
  }

  @Override
  protected final void onHead (@Nonnull final HttpServletRequest aHttpRequest,
                               @Nonnull final HttpServletResponse aHttpResponse,
                               @Nonnull final IRequestWebScope aRequestScope) throws ServletException, IOException
  {
    _run (aHttpRequest, aHttpResponse, aRequestScope, EHTTPMethod.HEAD);
  }

  @Override
  protected final void onOptions (@Nonnull final HttpServletRequest aHttpRequest,
                                  @Nonnull final HttpServletResponse aHttpResponse,
                                  @Nonnull final IRequestWebScope aRequestScope) throws ServletException, IOException
  {
    _run (aHttpRequest, aHttpResponse, aRequestScope, EHTTPMethod.OPTIONS);
  }

  @Override
  protected final void onPost (@Nonnull final HttpServletRequest aHttpRequest,
                               @Nonnull final HttpServletResponse aHttpResponse,
                               @Nonnull final IRequestWebScope aRequestScope) throws ServletException, IOException
  {
    _run (aHttpRequest, aHttpResponse, aRequestScope, EHTTPMethod.POST);
  }

  @Override
  protected final void onPut (@Nonnull final HttpServletRequest aHttpRequest,
                              @Nonnull final HttpServletResponse aHttpResponse,
                              @Nonnull final IRequestWebScope aRequestScope) throws ServletException, IOException
  {
    _run (aHttpRequest, aHttpResponse, aRequestScope, EHTTPMethod.PUT);
  }

  @Override
  protected final void onTrace (@Nonnull final HttpServletRequest aHttpRequest,
                                @Nonnull final HttpServletResponse aHttpResponse,
                                @Nonnull final IRequestWebScope aRequestScope) throws ServletException, IOException
  {
    _run (aHttpRequest, aHttpResponse, aRequestScope, EHTTPMethod.TRACE);
  }
}
