package com.helger.photon.core.servlet;

import javax.annotation.Nonnull;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.web.servlet.request.RequestLogger;

/**
 * httpoxy problem avoidance-filter based on
 * https://www.apache.org/security/asf-httpoxy-response.txt
 *
 * @author Philip Helger
 */
public class HttpoxyFilter implements Filter
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (HttpoxyFilter.class);

  public void init (final FilterConfig aFilterConfig) throws ServletException
  {}

  public void destroy ()
  {}

  public void doFilter (@Nonnull final ServletRequest aRequest,
                        @Nonnull final ServletResponse aResponse,
                        @Nonnull final FilterChain aChain) throws java.io.IOException, ServletException
  {

    final HttpServletRequest aHttpRequest = (HttpServletRequest) aRequest;
    final HttpServletResponse aHttpResponse = (HttpServletResponse) aResponse;

    final String poxy = aHttpRequest.getHeader ("proxy");
    if (poxy == null)
    {
      // call next filter in the chain.
      aChain.doFilter (aRequest, aResponse);
    }
    else
    {
      // potentially malicious request - log and block
      s_aLogger.warn ("httpoxy request successfully blocked!");
      RequestLogger.logRequestComplete (aHttpRequest);
      aHttpResponse.sendError (HttpServletResponse.SC_BAD_REQUEST);
    }
  }
}
