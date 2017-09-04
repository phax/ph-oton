package com.helger.photon.xservlet;

import java.util.Objects;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.http.CHttpHeader;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.SimpleURL;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.xservlet.handler.simple.IXServletSimpleHandler;

public class ErrorXServletHandler implements IXServletSimpleHandler
{
  private final String m_sServletPath;

  public ErrorXServletHandler (final String sServletPath)
  {
    ValueEnforcer.notEmpty (sServletPath, "ServletPath");
    ValueEnforcer.isTrue (sServletPath.startsWith ("/"), "Path must start with '/'!");

    m_sServletPath = sServletPath;
  }

  public void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull final UnifiedResponse aUnifiedResponse) throws Exception
  {
    final SimpleURL aURL = new SimpleURL (aRequestScope.getContextPath () + m_sServletPath);
    aURL.add ("httpError", true);
    aURL.addIf ("httpStatusCode",
                StringHelper.getToString (aRequestScope.getRequest ().getAttribute ("javax.servlet.error.status_code")),
                Objects::nonNull);
    aURL.addIf ("httpStatusMessage",
                StringHelper.getToString (aRequestScope.getRequest ().getAttribute ("javax.servlet.error.message")),
                Objects::nonNull);
    aURL.addIf ("httpRequestUri",
                StringHelper.getToString (aRequestScope.getRequest ().getAttribute ("javax.servlet.error.request_uri")),
                Objects::nonNull);
    aURL.addIf ("httpReferrer", aRequestScope.headers ().getFirstHeaderValue (CHttpHeader.REFERER), Objects::nonNull);
    aUnifiedResponse.setRedirect (aURL);
  }
}
