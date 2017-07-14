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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.io.stream.NonBlockingByteArrayInputStream;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.json.IJson;
import com.helger.json.serialize.JsonReader;
import com.helger.json.serialize.JsonWriterSettings;
import com.helger.photon.core.servletstatus.ServletStatusManager;

/**
 * A simple default implementation of a CSP (Content Security Policy) reporting
 * servlet.
 *
 * @author Philip Helger
 */
public class DefaultCSPReportingServlet extends HttpServlet
{
  public static final String SERVLET_DEFAULT_NAME = "cspreporting";
  public static final String SERVLET_DEFAULT_PATH = '/' + SERVLET_DEFAULT_NAME;

  private static final Logger s_aLogger = LoggerFactory.getLogger (DefaultCSPReportingServlet.class);

  private final ServletStatusManager m_aStatusMgr;

  public DefaultCSPReportingServlet ()
  {
    m_aStatusMgr = ServletStatusManager.getInstance ();
    m_aStatusMgr.onServletCtor (getClass ());
  }

  @Override
  public void init () throws ServletException
  {
    m_aStatusMgr.onServletInit (getClass ());
  }

  /**
   * Callback method that is invoked for correctly parsed CSP report JSON
   * object.
   *
   * @param aJson
   *        The parsed JSON. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void handleCSPReport (@Nonnull final IJson aJson)
  {
    s_aLogger.warn ("CSP report: " + aJson.getAsJsonString (new JsonWriterSettings ().setIndentEnabled (true)));
  }

  @Override
  protected void doPost (@Nonnull final HttpServletRequest aHttpRequest,
                         @Nonnull final HttpServletResponse aHttpResponse) throws ServletException, IOException
  {
    m_aStatusMgr.onServletInvocation (getClass ());

    // Read all request body bytes
    final byte [] aBytes = StreamHelper.getAllBytes (aHttpRequest.getInputStream ());

    // Try to parse as JSON
    final IJson aJson = JsonReader.readFromStream (new NonBlockingByteArrayInputStream (aBytes));
    if (aJson != null)
      handleCSPReport (aJson);
    else
      s_aLogger.error ("Failed to parse CSP report JSON: " + new String (aBytes, StandardCharsets.ISO_8859_1));
  }

  @Override
  public void destroy ()
  {
    m_aStatusMgr.onServletDestroy (getClass ());
  }
}
