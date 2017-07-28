package com.helger.photon.xservlet.ext;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.http.CHTTPHeader;
import com.helger.commons.state.EFinish;
import com.helger.servlet.request.RequestLogger;

public class XServletSecurityAdvisor
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (XServletSecurityAdvisor.class);

  @Nonnull
  protected EFinish checkForHttpPoxy (@Nonnull final HttpServletRequest aHttpRequest,
                                      @Nonnull final HttpServletResponse aHttpResponse) throws IOException
  {
    final String sPoxy = aHttpRequest.getHeader (CHTTPHeader.PROXY);
    if (sPoxy != null)
    {
      // potentially malicious request - log and block
      s_aLogger.warn ("httpoxy request successfully blocked!");
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
}
