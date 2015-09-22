package com.helger.photon.bootstrap.demo.servlet;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;

import com.helger.photon.core.servlet.AbstractPublicApplicationServlet;
import com.helger.photon.core.servlet.AbstractUnifiedResponseServlet;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.response.UnifiedResponse;

public class AppErrorServlet extends AbstractUnifiedResponseServlet
{
  @Override
  protected void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                @Nonnull final UnifiedResponse aUnifiedResponse) throws Exception
  {
    // In Jetty, the request attributes are already URL encoded!
    final HttpServletRequest aHttpRequest = aRequestScope.getRequest ();
    final String sTarget = aRequestScope.getContextPath () +
                           AbstractPublicApplicationServlet.SERVLET_DEFAULT_PATH +
                           "?httpError=true" +
                           "&httpStatusCode=" +
                           String.valueOf (aHttpRequest.getAttribute ("javax.servlet.error.status_code")) +
                           "&httpStatusMessage=" +
                           String.valueOf (aHttpRequest.getAttribute ("javax.servlet.error.message")) +
                           "&httpRequestUri=" +
                           String.valueOf (aHttpRequest.getAttribute ("javax.servlet.error.request_uri"));
    aUnifiedResponse.setRedirect (sTarget);
  }
}
