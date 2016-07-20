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

/**
 * HTTP poxy problem avoidance-filter based on
 * https://www.apache.org/security/asf-httpoxy-response.txt
 *
 * @author Philip Helger
 */
public class HttpPoxyFilter implements Filter
{
  public void init (final FilterConfig filterConfig) throws ServletException
  {}

  public void destroy ()
  {}

  public void doFilter (@Nonnull final ServletRequest aRequest,
                        @Nonnull final ServletResponse aResponse,
                        @Nonnull final FilterChain aChain) throws java.io.IOException, ServletException
  {

    final HttpServletRequest req = (HttpServletRequest) aRequest;
    final HttpServletResponse res = (HttpServletResponse) aResponse;

    final String poxy = req.getHeader ("proxy");
    if (poxy == null)
    {
      // call next filter in the chain.
      aChain.doFilter (aRequest, aResponse);
    }
    else
    {
      res.sendError (400);
    }
  }
}
