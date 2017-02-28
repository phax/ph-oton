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
import java.nio.charset.StandardCharsets;

import javax.annotation.Nonnull;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.charset.CharsetManager;
import com.helger.commons.string.StringParser;
import com.helger.servlet.ServletHelper;

/**
 * Special servlet filter that applies a certain encoding to a request and a
 * response.
 *
 * @author Philip Helger
 */
public class CharacterEncodingFilter implements Filter
{
  /** Name of the init parameter for the encoding */
  public static final String INITPARAM_ENCODING = "encoding";
  /** Name of the init parameter to force setting the encoding */
  public static final String INITPARAM_FORCE_ENCODING = "forceEncoding";
  /** The default encoding is UTF-8 */
  public static final String DEFAULT_ENCODING = StandardCharsets.UTF_8.name ();
  /** By default the encoding is not enforced. */
  public static final boolean DEFAULT_FORCE_ENCODING = false;
  private static final String REQUEST_ATTR = CharacterEncodingFilter.class.getName ();
  private static final Logger s_aLogger = LoggerFactory.getLogger (CharacterEncodingFilter.class);

  private String m_sEncoding = DEFAULT_ENCODING;
  private boolean m_bForceEncoding = DEFAULT_FORCE_ENCODING;

  /**
   * @return The encoding to be used by this filter. Neither <code>null</code>
   *         nor empty.
   */
  @OverrideOnDemand
  @Nonnull
  @Nonempty
  protected String getEncoding ()
  {
    return m_sEncoding;
  }

  @OverrideOnDemand
  protected boolean isForceEncoding ()
  {
    return m_bForceEncoding;
  }

  public void init (@Nonnull final FilterConfig aFilterConfig) throws ServletException
  {
    // encoding
    final String sEncoding = aFilterConfig.getInitParameter (INITPARAM_ENCODING);
    if (sEncoding != null)
    {
      // Throws IllegalArgumentException in case it is unknown
      CharsetManager.getCharsetFromName (sEncoding);
      m_sEncoding = sEncoding;
    }

    // force encoding?
    final String sForceEncoding = aFilterConfig.getInitParameter (INITPARAM_FORCE_ENCODING);
    if (sForceEncoding != null)
      m_bForceEncoding = StringParser.parseBool (sForceEncoding);
  }

  public void doFilter (@Nonnull final ServletRequest aRequest,
                        @Nonnull final ServletResponse aResponse,
                        @Nonnull final FilterChain aChain) throws IOException, ServletException
  {
    // Avoid double filtering
    if (aRequest.getAttribute (REQUEST_ATTR) == null)
    {
      final String sEncoding = getEncoding ();
      final String sOldRequestEncoding = aRequest.getCharacterEncoding ();
      // We need this for all form data etc.
      if (sOldRequestEncoding == null || isForceEncoding ())
      {
        aRequest.setCharacterEncoding (sEncoding);
        if (sOldRequestEncoding != null && !sEncoding.equalsIgnoreCase (sOldRequestEncoding))
          s_aLogger.info ("Changed request encoding from '" + sOldRequestEncoding + "' to '" + sEncoding + "'");
      }
      aResponse.setCharacterEncoding (sEncoding);
      ServletHelper.setRequestAttribute (aRequest, REQUEST_ATTR, Boolean.TRUE);
    }

    // Next filter in the chain
    aChain.doFilter (aRequest, aResponse);
  }

  public void destroy ()
  {}
}
