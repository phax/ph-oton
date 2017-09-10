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
package com.helger.photon.basic.xservlet;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;

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
    final HttpServletRequest aRequest = aRequestScope.getRequest ();
    final SimpleURL aURL = new SimpleURL (aRequestScope.getContextPath () + m_sServletPath);
    aURL.add ("httpError", true);
    aURL.addIf ("httpStatusCode",
                StringHelper.getToString (aRequest.getAttribute ("javax.servlet.error.status_code")),
                Objects::nonNull);
    aURL.addIf ("httpStatusMessage",
                StringHelper.getToString (aRequest.getAttribute ("javax.servlet.error.message")),
                Objects::nonNull);
    aURL.addIf ("httpRequestUri",
                StringHelper.getToString (aRequest.getAttribute ("javax.servlet.error.request_uri")),
                Objects::nonNull);
    aURL.addIf ("httpReferrer", aRequestScope.headers ().getFirstHeaderValue (CHttpHeader.REFERER), Objects::nonNull);
    aUnifiedResponse.setRedirect (aURL);
  }
}
