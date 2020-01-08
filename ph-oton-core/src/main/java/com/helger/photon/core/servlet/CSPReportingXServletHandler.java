/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsHashSet;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.http.EHttpMethod;
import com.helger.commons.io.stream.NonBlockingByteArrayInputStream;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.http.EHttpVersion;
import com.helger.json.IJson;
import com.helger.json.IJsonObject;
import com.helger.json.serialize.JsonReader;
import com.helger.json.serialize.JsonWriterSettings;
import com.helger.web.scope.IRequestWebScope;
import com.helger.xservlet.handler.IXServletHandler;

/**
 * Default Servlet handler for CSP reporting. Used in
 * {@link CSPReportingServlet}.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class CSPReportingXServletHandler implements IXServletHandler
{
  private static final Logger LOGGER = LoggerFactory.getLogger (CSPReportingXServletHandler.class);

  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  private final Consumer <? super IJsonObject> m_aJsonHandler;
  private boolean m_bFilterDuplicates = true;
  @GuardedBy ("m_aRWLock")
  private final ICommonsSet <String> m_aBlockedURIs = new CommonsHashSet <> ();

  public CSPReportingXServletHandler ()
  {
    this (CSPReportingXServletHandler::logCSPReport);
  }

  public CSPReportingXServletHandler (@Nonnull final Consumer <? super IJsonObject> aJsonHandler)
  {
    m_aJsonHandler = ValueEnforcer.notNull (aJsonHandler, "JsonHandler");
  }

  /**
   * @return <code>true</code> if duplicate filtering is enabled (default),
   *         <code>false</code> if not.
   */
  public final boolean isFilterDuplicates ()
  {
    return m_bFilterDuplicates;
  }

  /**
   * Enable or disable duplicate filtering.
   *
   * @param bFilterDuplicates
   *        <code>true</code> to filter duplicates, <code>false</code> to
   *        disable it.
   */
  public final void setFilterDuplicates (final boolean bFilterDuplicates)
  {
    m_bFilterDuplicates = bFilterDuplicates;
  }

  public static void logCSPReport (@Nonnull final IJsonObject aJson)
  {
    LOGGER.warn ("CSP report: " + aJson.getAsJsonString (new JsonWriterSettings ().setIndentEnabled (true)));
  }

  public void onRequest (@Nonnull final HttpServletRequest aHttpRequest,
                         @Nonnull final HttpServletResponse aHttpResponse,
                         @Nonnull final EHttpVersion eHttpVersion,
                         @Nonnull final EHttpMethod eHttpMethod,
                         @Nonnull final IRequestWebScope aRequestScope) throws ServletException, IOException
  {
    // Read all request body bytes
    final byte [] aBytes = StreamHelper.getAllBytes (aHttpRequest.getInputStream ());

    // Try to parse as JSON
    final IJson aJson = JsonReader.builder ().setSource (new NonBlockingByteArrayInputStream (aBytes)).read ();
    if (aJson != null)
    {
      if (aJson.isObject ())
      {
        final IJsonObject aJsonObj = aJson.getAsObject ();
        final String sBlockedURI = aJsonObj.getAsString ("blocked-uri");

        final boolean bIsDuplicate = m_bFilterDuplicates &&
                                     StringHelper.hasText (sBlockedURI) &&
                                     m_aRWLock.writeLocked ( () -> !m_aBlockedURIs.add (sBlockedURI));

        if (bIsDuplicate)
        {
          // Avoid too many reports
          LOGGER.info ("Ignoring already blocked URI '" + sBlockedURI + "'");
        }
        else
        {
          // Unique URL
          m_aJsonHandler.accept (aJson.getAsObject ());
        }
      }
      else
        LOGGER.error ("Weird JSON received: " +
                      aJson.getAsJsonString (new JsonWriterSettings ().setIndentEnabled (true)));
    }
    else
      LOGGER.error ("Failed to parse CSP report JSON: " + new String (aBytes, StandardCharsets.ISO_8859_1));

    // Ack
    aHttpResponse.setStatus (HttpServletResponse.SC_ACCEPTED);
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsSet <String> getAllBlockedURIs ()
  {
    return m_aRWLock.readLocked ( () -> m_aBlockedURIs.getClone ());
  }

  @Nonnull
  public EChange clearAllBlockedURIs ()
  {
    return m_aRWLock.readLocked ( () -> m_aBlockedURIs.removeAll ());
  }
}
