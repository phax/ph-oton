/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.http.CHttpHeader;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.SimpleURL;
import com.helger.servlet.ServletHelper;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.xservlet.handler.simple.IXServletSimpleHandler;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

/**
 * An error handler that redirects to a certain error path with some special URL
 * parameters.
 *
 * @author Philip Helger
 */
public class ErrorXServletHandler implements IXServletSimpleHandler
{
  public static final String PARAM_HTTP_ERROR = "httpError";
  public static final String PARAM_HTTP_STATUS_CODE = "httpStatusCode";
  public static final String PARAM_HTTP_STATUS_MESSAGE = "httpStatusMessage";
  public static final String PARAM_HTTP_REQUEST_URI = "httpRequestUri";
  public static final String PARAM_HTTP_REFERRER = "httpReferrer";

  private final String m_sServletPath;

  public ErrorXServletHandler (@Nonnull @Nonempty final String sServletPath)
  {
    ValueEnforcer.notEmpty (sServletPath, "Path");
    ValueEnforcer.isTrue (sServletPath.startsWith ("/"), "Path must start with '/'!");

    m_sServletPath = sServletPath;
  }

  @Nonnull
  @Nonempty
  public final String getPath ()
  {
    return m_sServletPath;
  }

  public void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull final UnifiedResponse aUnifiedResponse) throws Exception
  {
    final HttpServletRequest aRequest = aRequestScope.getRequest ();
    final SimpleURL aURL = new SimpleURL (aRequestScope.getContextPath () + m_sServletPath);
    aURL.add (PARAM_HTTP_ERROR, true);
    aURL.addIfNotNull (PARAM_HTTP_STATUS_CODE,
                       StringHelper.getToString (ServletHelper.getRequestAttribute (aRequest,
                                                                                    RequestDispatcher.ERROR_STATUS_CODE)));
    aURL.addIfNotNull (PARAM_HTTP_STATUS_MESSAGE,
                       StringHelper.getToString (ServletHelper.getRequestAttribute (aRequest,
                                                                                    RequestDispatcher.ERROR_MESSAGE)));
    aURL.addIfNotNull (PARAM_HTTP_REQUEST_URI,
                       StringHelper.getToString (ServletHelper.getRequestAttribute (aRequest,
                                                                                    RequestDispatcher.ERROR_REQUEST_URI)));
    aURL.addIfNotNull (PARAM_HTTP_REFERRER, aRequestScope.headers ().getFirstHeaderValue (CHttpHeader.REFERER));
    aUnifiedResponse.setRedirect (aURL);
  }
}
