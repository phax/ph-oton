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
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.string.StringHelper;
import com.helger.servlet.filter.AbstractHttpServletFilter;
import com.helger.servlet.request.RequestHelper;
import com.helger.servlet.response.ResponseHelper;
import com.helger.servlet.response.StatusAwareHttpResponseWrapper;

/**
 * This filter is used, to determine if some crucial information is missing in
 * some responses. Checked things are status code, character encoding, content
 * type and some headers. This is mainly for debugging purposes.
 *
 * @author Philip Helger
 */
public class CheckResponseFilter extends AbstractHttpServletFilter
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (CheckResponseFilter.class);

  public CheckResponseFilter ()
  {}

  /**
   * @param sRequestURL
   *        The request URL.
   * @param nStatusCode
   *        The response status code.
   */
  @OverrideOnDemand
  protected void checkStatusCode (@Nonnull final String sRequestURL, final int nStatusCode)
  {
    if (nStatusCode < HttpServletResponse.SC_MULTIPLE_CHOICES || nStatusCode >= HttpServletResponse.SC_BAD_REQUEST)
      s_aLogger.warn ("Status code " + nStatusCode + " in response to '" + sRequestURL + "'");
  }

  /**
   * @param sRequestURL
   *        The request URL.
   * @param sCharacterEncoding
   *        The response character encoding.
   * @param nStatusCode
   *        The response status code.
   */
  @OverrideOnDemand
  protected void checkCharacterEncoding (@Nonnull final String sRequestURL,
                                         @Nullable final String sCharacterEncoding,
                                         final int nStatusCode)
  {
    if (StringHelper.hasNoText (sCharacterEncoding) && !ResponseHelper.isEmptyStatusCode (nStatusCode))
      s_aLogger.warn ("No character encoding on " + nStatusCode + " response to '" + sRequestURL + "'");
  }

  /**
   * @param sRequestURL
   *        The request URL.
   * @param sContentType
   *        The response content type.
   * @param nStatusCode
   *        The response status code.
   */
  @OverrideOnDemand
  protected void checkContentType (@Nonnull final String sRequestURL,
                                   @Nullable final String sContentType,
                                   final int nStatusCode)
  {
    if (StringHelper.hasNoText (sContentType) && !ResponseHelper.isEmptyStatusCode (nStatusCode))
      s_aLogger.warn ("No content type on " + nStatusCode + " response to '" + sRequestURL + "'");
  }

  /**
   * @param sRequestURL
   *        The request URL.
   * @param aHeaders
   *        All response HTTP headers.
   * @param nStatusCode
   *        The response status code.
   */
  @OverrideOnDemand
  protected void checkHeaders (@Nonnull final String sRequestURL,
                               @Nonnull final Map <String, ? extends List <String>> aHeaders,
                               final int nStatusCode)
  {
    if (nStatusCode != HttpServletResponse.SC_OK && !aHeaders.isEmpty ())
      s_aLogger.warn ("Headers on " + nStatusCode + " response to '" + sRequestURL + "': " + aHeaders);
  }

  private void _checkResults (@Nonnull final HttpServletRequest aHttpRequest,
                              @Nonnull final StatusAwareHttpResponseWrapper aHttpResponse)
  {
    final String sRequestURL = RequestHelper.getURL (aHttpRequest);
    final int nStatusCode = aHttpResponse.getStatusCode ();
    final ICommonsMap <String, ICommonsList <String>> aHeaders = aHttpResponse.headerMap ().getAllHeaders ();
    final String sCharacterEncoding = aHttpResponse.getCharacterEncoding ();
    final String sContentType = aHttpResponse.getContentType ();

    checkStatusCode (sRequestURL, nStatusCode);
    checkCharacterEncoding (sRequestURL, sCharacterEncoding, nStatusCode);
    checkContentType (sRequestURL, sContentType, nStatusCode);
    checkHeaders (sRequestURL, aHeaders, nStatusCode);
  }

  @Override
  public void doHttpFilter (@Nonnull final HttpServletRequest aRequest,
                            @Nonnull final HttpServletResponse aResponse,
                            @Nonnull final FilterChain aChain) throws IOException, ServletException
  {
    // Create a response wrapper
    final StatusAwareHttpResponseWrapper aWrapper = new StatusAwareHttpResponseWrapper (aResponse);
    try
    {
      aChain.doFilter (aRequest, aWrapper);
    }
    finally
    {
      // Evaluate the result
      _checkResults (aRequest, aWrapper);
    }
  }
}
