/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.webbasics.servlet;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.helger.commons.state.EContinue;
import com.helger.web.http.EHTTPMethod;
import com.helger.web.http.EHTTPVersion;
import com.helger.web.scopes.domain.IRequestWebScope;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;
import com.helger.web.scopes.servlet.AbstractScopeAwareFilter;
import com.helger.web.servlet.request.RequestHelper;
import com.helger.web.servlet.response.UnifiedResponse;

/**
 * Abstract base class for a filter performing actions via
 * {@link UnifiedResponse}.
 *
 * @author Philip Helger
 * @since 3.7.0
 */
public abstract class AbstractUnifiedResponseFilter extends AbstractScopeAwareFilter
{
  protected AbstractUnifiedResponseFilter ()
  {}

  /**
   * Overwrite this method to fill your response.
   *
   * @param aRequestScope
   *        The request scope to use. There is no direct access to the
   *        {@link HttpServletResponse}. Everything must be handled with the
   *        unified response! Never <code>null</code>.
   * @param aUnifiedResponse
   *        The response object to be filled. Never <code>null</code>.
   * @return If {@link EContinue#BREAK} is returned, the content of the unified
   *         response is rendered to the HTTP servlet response and the filter
   *         chain stops. On {@link EContinue#CONTINUE} the content of the
   *         unified response is discarded and the filter chain continues as
   *         normal.
   * @throws ServletException
   *         In case of an error
   */
  @Nonnull
  protected abstract EContinue handleRequest (@Nonnull IRequestWebScopeWithoutResponse aRequestScope,
                                              @Nonnull UnifiedResponse aUnifiedResponse) throws ServletException;

  @Override
  @Nonnull
  protected final EContinue doFilter (@Nonnull final HttpServletRequest aHttpRequest,
                                      @Nonnull final HttpServletResponse aHttpResponse,
                                      @Nonnull final IRequestWebScope aRequestScope) throws IOException,
                                                                                    ServletException
  {
    // Check HTTP version
    final EHTTPVersion eHTTPVersion = RequestHelper.getHttpVersion (aHttpRequest);
    if (eHTTPVersion == null)
    {
      aHttpResponse.sendError (HttpServletResponse.SC_HTTP_VERSION_NOT_SUPPORTED);
      return EContinue.BREAK;
    }

    // Check HTTP Method
    final EHTTPMethod eHTTPMethod = RequestHelper.getHttpMethod (aHttpRequest);
    if (eHTTPMethod == null)
    {
      if (eHTTPVersion == EHTTPVersion.HTTP_11)
        aHttpResponse.sendError (HttpServletResponse.SC_METHOD_NOT_ALLOWED);
      else
        aHttpResponse.sendError (HttpServletResponse.SC_BAD_REQUEST);
      return EContinue.BREAK;
    }

    final UnifiedResponse aUnifiedResponse = new UnifiedResponse (eHTTPVersion, eHTTPMethod, aHttpRequest);
    if (handleRequest (aRequestScope, aUnifiedResponse).isContinue ())
    {
      // Filter passed, without any output -> continue
      // Discard the content of the unified response
      return EContinue.CONTINUE;
    }

    // Filter ended chain -> send response
    aUnifiedResponse.applyToResponse (aHttpResponse);
    return EContinue.BREAK;
  }
}
