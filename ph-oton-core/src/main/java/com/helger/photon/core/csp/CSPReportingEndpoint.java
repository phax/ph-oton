/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.core.csp;

import java.util.Map;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.http.csp.CSPDirective;
import com.helger.url.SimpleURL;

/**
 * A named CSP reporting endpoint. It ties the name used in the CSP <code>report-to</code> directive
 * to the URI used in the <code>Reporting-Endpoints</code> HTTP response header and in the legacy
 * <code>report-uri</code> directive, so that the three cannot drift apart.
 *
 * @author Philip Helger
 * @since 10.4.0
 */
@Immutable
public class CSPReportingEndpoint
{
  /** The default endpoint name to be used in "report-to" and "Reporting-Endpoints" */
  public static final String DEFAULT_ENDPOINT_NAME = "csp-endpoint";

  private final String m_sName;
  private final String m_sURI;

  /**
   * Constructor using {@link #DEFAULT_ENDPOINT_NAME} as the name.
   *
   * @param sURI
   *        The URI the reports are posted to, including all query parameters. May neither be
   *        <code>null</code> nor empty.
   */
  public CSPReportingEndpoint (@NonNull @Nonempty final String sURI)
  {
    this (DEFAULT_ENDPOINT_NAME, sURI);
  }

  /**
   * Constructor
   *
   * @param sName
   *        The endpoint name. May neither be <code>null</code> nor empty.
   * @param sURI
   *        The URI the reports are posted to, including all query parameters. May neither be
   *        <code>null</code> nor empty.
   */
  public CSPReportingEndpoint (@NonNull @Nonempty final String sName, @NonNull @Nonempty final String sURI)
  {
    m_sName = ValueEnforcer.notEmpty (sName, "Name");
    m_sURI = ValueEnforcer.notEmpty (sURI, "URI");
  }

  /**
   * @return The endpoint name as provided in the constructor. Neither <code>null</code> nor empty.
   */
  @NonNull
  @Nonempty
  public final String getName ()
  {
    return m_sName;
  }

  /**
   * @return The endpoint URI as provided in the constructor. Neither <code>null</code> nor empty.
   */
  @NonNull
  @Nonempty
  public final String getURI ()
  {
    return m_sURI;
  }

  /**
   * @return The value of the <code>Reporting-Endpoints</code> HTTP response header for this
   *         endpoint. Neither <code>null</code> nor empty.
   */
  @NonNull
  @Nonempty
  public String getReportingEndpointsHeaderValue ()
  {
    return m_sName + "=\"" + m_sURI + "\"";
  }

  /**
   * @return The CSP <code>report-to</code> directive for this endpoint. Never <code>null</code>.
   */
  @NonNull
  public CSPDirective getAsReportToDirective ()
  {
    return CSPDirective.createReportTo (m_sName);
  }

  /**
   * @return The legacy CSP <code>report-uri</code> directive for this endpoint. Never
   *         <code>null</code>. It is deprecated, but still the only mechanism some browsers honour,
   *         so it is usually emitted next to <code>report-to</code>.
   */
  @NonNull
  public CSPDirective getAsReportURIDirective ()
  {
    return CSPDirective.createReportURI (m_sURI);
  }

  /**
   * Create a new endpoint with the provided query parameters appended to the URI.
   *
   * @param sBaseURI
   *        The URI without any query parameters. May neither be <code>null</code> nor empty.
   * @param aParams
   *        The query parameters to be appended. May be <code>null</code> or empty.
   * @return The URI including the query parameters. Neither <code>null</code> nor empty.
   */
  @NonNull
  @Nonempty
  public static String createURI (@NonNull @Nonempty final String sBaseURI,
                                  @Nullable final Map <String, String> aParams)
  {
    ValueEnforcer.notEmpty (sBaseURI, "BaseURI");
    if (aParams == null || aParams.isEmpty ())
      return sBaseURI;

    final SimpleURL aURL = new SimpleURL (sBaseURI);
    for (final Map.Entry <String, String> aEntry : aParams.entrySet ())
      if (StringHelper.isNotEmpty (aEntry.getKey ()) && StringHelper.isNotEmpty (aEntry.getValue ()))
        aURL.add (aEntry.getKey (), aEntry.getValue ());
    return aURL.getAsString ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Name", m_sName).append ("URI", m_sURI).getToString ();
  }
}
