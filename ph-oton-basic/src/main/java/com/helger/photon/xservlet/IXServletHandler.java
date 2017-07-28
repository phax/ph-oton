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
package com.helger.photon.xservlet;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.helger.http.EHTTPMethod;
import com.helger.http.EHTTPVersion;

/**
 * HTTP servlet handler for a single HTTP methods.
 *
 * @author Philip Helger
 * @since 8.8.0
 */
@FunctionalInterface
public interface IXServletHandler extends Serializable
{
  /**
   * Handle the servlet action for a certain request and response.
   *
   * @param aHttpRequest
   *        HTTP servlet request. Never <code>null</code>.
   * @param aHttpResponse
   *        HTTP servlet response. Never <code>null</code>.
   * @param eHttpVersion
   *        HTTP version. Never <code>null</code>.
   * @param eHttpMethod
   *        HTTP method. Never <code>null</code>.
   * @throws ServletException
   *         On business error
   * @throws IOException
   *         On IO error
   */
  void handle (@Nonnull HttpServletRequest aHttpRequest,
               @Nonnull HttpServletResponse aHttpResponse,
               @Nonnull EHTTPVersion eHttpVersion,
               @Nonnull EHTTPMethod eHttpMethod) throws ServletException, IOException;
}
