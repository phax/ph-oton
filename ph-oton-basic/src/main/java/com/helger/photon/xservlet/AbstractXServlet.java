/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.xservlet;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.exception.InitializationException;
import com.helger.commons.http.CHTTPHeader;
import com.helger.commons.lang.ClassHelper;
import com.helger.commons.state.EChange;
import com.helger.commons.statistics.IMutableStatisticsHandlerCounter;
import com.helger.commons.statistics.IMutableStatisticsHandlerKeyedCounter;
import com.helger.commons.statistics.IMutableStatisticsHandlerKeyedTimer;
import com.helger.commons.statistics.StatisticsManager;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.timing.StopWatch;
import com.helger.http.EHTTPMethod;
import com.helger.http.EHTTPVersion;
import com.helger.photon.xservlet.ext.XServletConsistencyAdvisor;
import com.helger.photon.xservlet.ext.XServletSecurityAdvisor;
import com.helger.photon.xservlet.requesttrack.RequestTracker;
import com.helger.photon.xservlet.servletstatus.ServletStatusManager;
import com.helger.scope.mgr.ScopeManager;
import com.helger.servlet.http.CountingOnlyHttpServletResponse;
import com.helger.servlet.request.RequestLogger;
import com.helger.servlet.response.StatusAwareHttpResponseWrapper;
import com.helger.web.scope.IRequestWebScope;
import com.helger.web.scope.request.RequestScopeInitializer;

/**
 * Abstract HTTP based servlet. Compared to the default
 * {@link javax.servlet.http.HttpServlet} this class uses a handler map with
 * {@link EHTTPMethod} as the key.<br>
 * The following features are added compared to the default servlet
 * implementation:
 * <ul>
 * <li>It has counting statistics</li>
 * <li>It has timing statistics</li>
 * <li>It enforces a character set on the response</li>
 * </ul>
 *
 * @author Philip Helger
 * @since 9.0.0
 */
public abstract class AbstractXServlet extends GenericServlet
{
  /** The name of the request attribute uniquely identifying the request ID */
  public static final String REQUEST_ATTR_ID = ScopeManager.SCOPE_ATTRIBUTE_PREFIX_INTERNAL + "request.id";

  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractXServlet.class);
  private static final IMutableStatisticsHandlerCounter s_aCounterRequestsTotal = StatisticsManager.getCounterHandler (AbstractXServlet.class.getName () +
                                                                                                                       "$requests.total");
  private static final IMutableStatisticsHandlerCounter s_aCounterRequestsAccepted = StatisticsManager.getCounterHandler (AbstractXServlet.class.getName () +
                                                                                                                          "$requests.accepted");
  private static final IMutableStatisticsHandlerCounter s_aCounterRequestsHandled = StatisticsManager.getCounterHandler (AbstractXServlet.class.getName () +
                                                                                                                         "$requests.handled");
  private static final IMutableStatisticsHandlerKeyedCounter s_aCounterRequestsPerVersionAccepted = StatisticsManager.getKeyedCounterHandler (AbstractXServlet.class.getName () +
                                                                                                                                              "$requests-per-version.accepted");
  private static final IMutableStatisticsHandlerKeyedCounter s_aCounterRequestsPerVersionHandled = StatisticsManager.getKeyedCounterHandler (AbstractXServlet.class.getName () +
                                                                                                                                             "$requests-per-version.handled");
  private static final IMutableStatisticsHandlerKeyedCounter s_aCounterRequestsPerMethodAccepted = StatisticsManager.getKeyedCounterHandler (AbstractXServlet.class.getName () +
                                                                                                                                             "$requests-per-method.accepted");
  private static final IMutableStatisticsHandlerKeyedCounter s_aCounterRequestsPerMethodHandled = StatisticsManager.getKeyedCounterHandler (AbstractXServlet.class.getName () +
                                                                                                                                            "$requests-per-method.handled");
  private final IMutableStatisticsHandlerKeyedCounter m_aCounterHttpMethodUnhandled = StatisticsManager.getKeyedCounterHandler (getClass ().getName () +
                                                                                                                                "$method.unhandled");
  private static final IMutableStatisticsHandlerKeyedTimer s_aTimer = StatisticsManager.getKeyedTimerHandler (AbstractXServlet.class);

  private static final AtomicLong s_aRequestID = new AtomicLong (0);
  private static final AtomicBoolean s_aFirstRequest = new AtomicBoolean (true);

  private final ServletStatusManager m_aStatusMgr;

  /** The main handler map */
  protected final XServletHandlerRegistry m_aHandlerRegistry = new XServletHandlerRegistry ();

  // Determine in "init" method
  private transient String m_sStatusApplicationID;

  /**
   * Does nothing, because this is an abstract class.
   */
  public AbstractXServlet ()
  {
    // This handler is always the same, so it is registered here for convenience
    m_aHandlerRegistry.registerHandler (EHTTPMethod.TRACE, new XServletHandlerTRACE ());

    // Default HEAD handler -> invoke with GET
    m_aHandlerRegistry.registerHandler (EHTTPMethod.HEAD,
                                        (aHttpRequest, aHttpResponse, eHttpVersion, eHttpMethod, aRequestScope) -> {
                                          final CountingOnlyHttpServletResponse aResponseWrapper = new CountingOnlyHttpServletResponse (aHttpResponse);
                                          _internalService (aHttpRequest,
                                                            aResponseWrapper,
                                                            eHttpVersion,
                                                            EHTTPMethod.GET,
                                                            aRequestScope);
                                          aResponseWrapper.setContentLengthAutomatically ();
                                        });

    // Default OPTIONS handler
    m_aHandlerRegistry.registerHandler (EHTTPMethod.OPTIONS,
                                        new XServletHandlerOPTIONS (m_aHandlerRegistry::getAllowedHttpMethodsString));

    // Remember to avoid crash on shutdown, when no GlobalScope is present
    m_aStatusMgr = ServletStatusManager.getInstance ();
    m_aStatusMgr.onServletCtor (getClass ());
  }

  /**
   * @return The application ID for this servlet.
   */
  @OverrideOnDemand
  protected String getApplicationID ()
  {
    return ClassHelper.getClassLocalName (getClass ());
  }

  /**
   * A final overload of "init". Overload "init" instead.
   */
  @Override
  public final void init (@Nonnull final ServletConfig aSC) throws ServletException
  {
    super.init (aSC);
    m_aStatusMgr.onServletInit (getClass ());

    m_sStatusApplicationID = getApplicationID ();
    if (StringHelper.hasNoText (m_sStatusApplicationID))
      throw new InitializationException ("Failed retrieve a valid application ID! Please override getApplicationID()");
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  public void destroy ()
  {
    m_aStatusMgr.onServletDestroy (getClass ());
    super.destroy ();
  }

  @Nonnull
  private static EChange _trackBeforeHandleRequest (@Nonnull final IRequestWebScope aRequestScope)
  {
    // Check if an attribute is already present
    // An ID may already be present, if the request is internally dispatched
    // (e.g. via the error handler)
    String sID = aRequestScope.attrs ().getAsString (REQUEST_ATTR_ID);
    if (sID != null)
      return EChange.UNCHANGED;

    // Create a unique ID for the request
    sID = Long.toString (s_aRequestID.incrementAndGet ());
    aRequestScope.attrs ().putIn (REQUEST_ATTR_ID, sID);
    RequestTracker.addRequest (sID, aRequestScope);
    return EChange.CHANGED;
  }

  private static void _trackAfterHandleRequest (@Nonnull final IRequestWebScope aRequestScope)
  {
    final String sID = aRequestScope.attrs ().getAsString (REQUEST_ATTR_ID);
    RequestTracker.removeRequest (sID);
  }

  private void _internalService (@Nonnull final HttpServletRequest aHttpRequest,
                                 @Nonnull final HttpServletResponse aHttpResponse,
                                 @Nonnull final EHTTPVersion eHttpVersion,
                                 @Nonnull final EHTTPMethod eHttpMethod,
                                 @Nonnull final IRequestWebScope aRequestScope) throws ServletException, IOException
  {
    // Find the handler for the HTTP method
    final IXServletHandler aHandler = m_aHandlerRegistry.getHandler (eHttpMethod);
    if (aHandler == null)
    {
      // HTTP method is not supported by this servlet!
      m_aCounterHttpMethodUnhandled.increment (eHttpMethod.getName ());

      aHttpResponse.setHeader (CHTTPHeader.ALLOW, m_aHandlerRegistry.getAllowedHttpMethodsString ());
      if (eHttpVersion == EHTTPVersion.HTTP_11)
        aHttpResponse.sendError (HttpServletResponse.SC_METHOD_NOT_ALLOWED);
      else
        aHttpResponse.sendError (HttpServletResponse.SC_BAD_REQUEST);
      return;
    }

    // HTTP method is supported by this servlet implementation
    final StopWatch aSW = StopWatch.createdStarted ();
    boolean bTrackedRequest = false;
    try
    {
      bTrackedRequest = _trackBeforeHandleRequest (aRequestScope).isChanged ();

      // This may indirectly call "_internalService" again (e.g. for HEAD
      // requests, which calls GET internally)
      aHandler.handle (aHttpRequest, aHttpResponse, eHttpVersion, eHttpMethod, aRequestScope);

      // Handled and no exception
      s_aCounterRequestsHandled.increment ();
      s_aCounterRequestsPerVersionHandled.increment (eHttpVersion.getName ());
      s_aCounterRequestsPerMethodHandled.increment (eHttpMethod.getName ());
    }
    finally
    {
      if (bTrackedRequest)
        _trackAfterHandleRequest (aRequestScope);

      // Timer per HTTP method
      s_aTimer.addTime (eHttpMethod.getName (), aSW.stopAndGetMillis ());
    }
  }

  /**
   * This method logs errors, in case a HttpServletRequest object is missing
   * basic information
   *
   * @param sMsg
   *        The concrete message to emit. May not be <code>null</code>.
   * @param aHttpRequest
   *        The current HTTP request. May not be <code>null</code>.
   */
  @OverrideOnDemand
  protected void logInvalidRequestSetup (@Nonnull final String sMsg, @Nonnull final HttpServletRequest aHttpRequest)
  {
    final StringBuilder aSB = new StringBuilder (sMsg).append (":\n");
    aSB.append (RequestLogger.getRequestComplete (aHttpRequest));
    final String sFullMsg = aSB.toString ();
    s_aLogger.error (sFullMsg);
    log (sFullMsg);
  }

  @Nonnull
  @OverrideOnDemand
  protected XServletSecurityAdvisor createSecurityAdvisor ()
  {
    return new XServletSecurityAdvisor ();
  }

  @Nonnull
  @OverrideOnDemand
  protected XServletConsistencyAdvisor createConsistencyAdvisor ()
  {
    return new XServletConsistencyAdvisor ();
  }

  /**
   * Dispatches client requests to the protected <code>service</code> method.
   * There's no need to override this method.
   *
   * @param aRequest
   *        the {@link HttpServletRequest} object that contains the request the
   *        client made of the servlet
   * @param aResponse
   *        the {@link HttpServletResponse} object that contains the response
   *        the servlet returns to the client
   * @exception IOException
   *            if an input or output error occurs while the servlet is handling
   *            the HTTP request
   * @exception ServletException
   *            if the HTTP request cannot be handled
   * @see javax.servlet.Servlet#service
   */
  @Override
  public final void service (@Nonnull final ServletRequest aRequest,
                             @Nonnull final ServletResponse aResponse) throws ServletException, IOException
  {
    ValueEnforcer.isInstanceOf (aRequest, HttpServletRequest.class, "Non-HTTP servlet request");
    ValueEnforcer.isInstanceOf (aRequest, HttpServletResponse.class, "Non-HTTP servlet response");

    final HttpServletRequest aHttpRequest = (HttpServletRequest) aRequest;
    final HttpServletResponse aHttpResponse = (HttpServletResponse) aResponse;

    s_aCounterRequestsTotal.increment ();
    m_aStatusMgr.onServletInvocation (getClass ());

    // Ensure a valid HTTP version is provided
    final String sProtocol = aHttpRequest.getProtocol ();
    final EHTTPVersion eHTTPVersion = EHTTPVersion.getFromNameOrNull (sProtocol);
    if (eHTTPVersion == null)
    {
      // HTTP version disallowed
      logInvalidRequestSetup ("Request has unsupported HTTP version (" + sProtocol + ")!", aHttpRequest);
      aHttpResponse.sendError (HttpServletResponse.SC_HTTP_VERSION_NOT_SUPPORTED);
      return;
    }
    s_aCounterRequestsPerVersionAccepted.increment (eHTTPVersion.getName ());

    // Ensure a valid HTTP method is provided
    final String sMethod = aHttpRequest.getMethod ();
    final EHTTPMethod eHTTPMethod = EHTTPMethod.getFromNameOrNull (sMethod);
    if (eHTTPMethod == null)
    {
      // HTTP method unknown
      logInvalidRequestSetup ("Request has unsupported HTTP method (" + sMethod + ")!", aHttpRequest);
      aHttpResponse.sendError (HttpServletResponse.SC_NOT_IMPLEMENTED);
      return;
    }
    s_aCounterRequestsPerMethodAccepted.increment (eHTTPMethod.getName ());

    final XServletSecurityAdvisor aSecurityAdvisor = createSecurityAdvisor ();
    if (aSecurityAdvisor.beforeRequestIsProcessed (aHttpRequest, aHttpResponse).isFinished ())
    {
      // Some security related issues was discovered so that the process cannot
      // be handled.
      return;
    }

    final XServletConsistencyAdvisor aConsistencyAdvisor = createConsistencyAdvisor ();
    aConsistencyAdvisor.beforeRequestIsProcessed (aHttpRequest, aHttpResponse);
    final StatusAwareHttpResponseWrapper aHttpResponseWrapper = new StatusAwareHttpResponseWrapper (aHttpResponse);
    try
    {
      // HTTP version and method are valid
      s_aCounterRequestsAccepted.increment ();

      // Create request scope
      final RequestScopeInitializer aRequestScopeInitializer = RequestScopeInitializer.create (m_sStatusApplicationID,
                                                                                               aHttpRequest,
                                                                                               aHttpResponse);
      try
      {

        // Determine handler
        _internalService (aHttpRequest,
                          aHttpResponseWrapper,
                          eHTTPVersion,
                          eHTTPMethod,
                          aRequestScopeInitializer.getRequestScope ());
      }
      finally
      {
        // Destroy request scope
        aRequestScopeInitializer.destroyScope ();
      }
    }
    finally
    {
      aConsistencyAdvisor.afterRequest (aHttpRequest, aHttpResponseWrapper);
    }
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("HandlerRegistry", m_aHandlerRegistry)
                                       .append ("ApplicationID", m_sStatusApplicationID)
                                       .getToString ();
  }
}
