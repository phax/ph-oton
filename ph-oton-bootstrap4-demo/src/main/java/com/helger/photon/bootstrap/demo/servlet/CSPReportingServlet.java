package com.helger.photon.bootstrap.demo.servlet;

import com.helger.commons.http.EHttpMethod;
import com.helger.photon.core.servlet.CSPReportingXServletHandler;
import com.helger.xservlet.AbstractXServlet;

/**
 * Internal servlet to post CSP violations to.
 *
 * @author Philip Helger
 */
public class CSPReportingServlet extends AbstractXServlet
{
  public static final String SERVLET_DEFAULT_NAME = "cspreporting";
  public static final String SERVLET_DEFAULT_PATH = '/' + SERVLET_DEFAULT_NAME;

  static final class ERBCSPReportingXServletHandler extends CSPReportingXServletHandler
  {
    public ERBCSPReportingXServletHandler ()
    {
      // Avoid spamming us?
      setFilterDuplicates (false);
    }
  }

  public CSPReportingServlet ()
  {
    handlerRegistry ().registerHandler (EHttpMethod.POST, new ERBCSPReportingXServletHandler (), false);
  }
}
