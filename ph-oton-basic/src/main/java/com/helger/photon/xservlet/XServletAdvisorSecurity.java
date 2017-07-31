package com.helger.photon.xservlet;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.http.CHttpHeader;
import com.helger.commons.state.EFinish;
import com.helger.servlet.request.RequestLogger;
import com.helger.servlet.response.StatusAwareHttpResponseWrapper;

/**
 * Handle special security related stuff that needs to be processed for every
 * servlet. Currently handled are:
 * <ul>
 * <li>Httpoxy attack using the 'Proxy' HTTP header</li>
 * </ul>
 *
 * @author Philip Helger
 */
public class XServletAdvisorSecurity
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (XServletAdvisorSecurity.class);

  @Nonnull
  protected EFinish checkForHttpPoxy (@Nonnull final HttpServletRequest aHttpRequest,
                                      @Nonnull final HttpServletResponse aHttpResponse) throws IOException
  {
    final String sPoxy = aHttpRequest.getHeader (CHttpHeader.PROXY);
    if (sPoxy != null)
    {
      // potentially malicious request - log and block
      s_aLogger.warn ("httpoxy request successfully blocked: " + aHttpRequest);
      RequestLogger.logRequestComplete (aHttpRequest);
      aHttpResponse.sendError (HttpServletResponse.SC_BAD_REQUEST);
      return EFinish.FINISHED;
    }
    return EFinish.UNFINISHED;
  }

  @Nonnull
  public EFinish beforeRequestIsProcessed (@Nonnull final HttpServletRequest aHttpRequest,
                                           @Nonnull final HttpServletResponse aHttpResponse) throws IOException
  {
    if (checkForHttpPoxy (aHttpRequest, aHttpResponse).isUnfinished ())
    {
      // Further checks go here
      return EFinish.UNFINISHED;
    }
    return EFinish.FINISHED;
  }

  /**
   * Called after the request was finalized
   *
   * @param aHttpRequest
   *        HTTP request
   * @param aHttpResponse
   *        HTTP response
   */
  public void afterRequest (@Nonnull final HttpServletRequest aHttpRequest,
                            @Nonnull final StatusAwareHttpResponseWrapper aHttpResponse)
  {
    // Placeholder
  }
}
