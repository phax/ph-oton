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
package com.helger.photon.core.servlet;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.servlet.filter.AbstractHttpServletFilter;
import com.helger.servlet.request.RequestLogger;

/**
 * httpoxy problem avoidance-filter based on
 * https://www.apache.org/security/asf-httpoxy-response.txt
 *
 * @author Philip Helger
 */
public class HttpoxyFilter extends AbstractHttpServletFilter
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (HttpoxyFilter.class);

  @Override
  public void doHttpFilter (@Nonnull final HttpServletRequest aHttpRequest,
                            @Nonnull final HttpServletResponse aHttpResponse,
                            @Nonnull final FilterChain aChain) throws IOException, ServletException
  {
    final String sPoxy = aHttpRequest.getHeader ("proxy");
    if (sPoxy == null)
    {
      // call next filter in the chain.
      aChain.doFilter (aHttpRequest, aHttpResponse);
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
