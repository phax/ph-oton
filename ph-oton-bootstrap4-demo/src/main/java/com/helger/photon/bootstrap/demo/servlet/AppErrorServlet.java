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
package com.helger.photon.bootstrap.demo.servlet;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;

import com.helger.photon.core.servlet.AbstractPublicApplicationServlet;
import com.helger.photon.core.servlet.AbstractUnifiedResponseServlet;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

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
                           aHttpRequest.getAttribute ("javax.servlet.error.status_code") +
                           "&httpStatusMessage=" +
                           aHttpRequest.getAttribute ("javax.servlet.error.message") +
                           "&httpRequestUri=" +
                           aHttpRequest.getAttribute ("javax.servlet.error.request_uri");
    aUnifiedResponse.setRedirect (sTarget);
  }
}