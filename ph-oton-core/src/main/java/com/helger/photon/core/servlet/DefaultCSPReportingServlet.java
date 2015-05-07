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
package com.helger.photon.core.servlet;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.charset.CCharset;
import com.helger.commons.charset.CharsetManager;
import com.helger.commons.io.streams.NonBlockingByteArrayInputStream;
import com.helger.commons.io.streams.StreamUtils;
import com.helger.json.IJson;
import com.helger.json.parser.JsonReader;
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

  public DefaultCSPReportingServlet ()
  {
    ServletStatusManager.onServletCtor (getClass ());
  }

  @Override
  public void init () throws ServletException
  {
    ServletStatusManager.onServletInit (getClass ());
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
    s_aLogger.warn ("CSP report: " + aJson.getAsString (new JsonWriterSettings ().setIndentEnabled (true)));
  }

  @Override
  protected void doPost (@Nonnull final HttpServletRequest aHttpRequest, final HttpServletResponse aHttpResponse) throws ServletException,
                                                                                                                 IOException
  {
    ServletStatusManager.onServletInvocation (getClass ());

    // Read all request body bytes
    final byte [] aBytes = StreamUtils.getAllBytes (aHttpRequest.getInputStream ());

    // Try to parse as JSON
    final IJson aJson = JsonReader.readFromStream (new NonBlockingByteArrayInputStream (aBytes));
    if (aJson != null)
      handleCSPReport (aJson);
    else
      s_aLogger.error ("Failed to parse CSP report JSON: " +
                       CharsetManager.getAsString (aBytes, CCharset.CHARSET_ISO_8859_1_OBJ));
  }

  @Override
  public void destroy ()
  {
    ServletStatusManager.onServletDestroy (getClass ());
  }
}
