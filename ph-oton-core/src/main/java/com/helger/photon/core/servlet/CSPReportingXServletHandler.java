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
package com.helger.photon.core.servlet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.ELockType;
import com.helger.annotation.concurrent.GuardedBy;
import com.helger.annotation.concurrent.IsLocked;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.concurrent.SimpleReadWriteLock;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.io.stream.StreamHelper;
import com.helger.base.state.EChange;
import com.helger.base.string.StringHelper;
import com.helger.collection.commons.CommonsHashSet;
import com.helger.collection.commons.ICommonsSet;
import com.helger.http.EHttpMethod;
import com.helger.http.EHttpVersion;
import com.helger.json.IJson;
import com.helger.json.IJsonObject;
import com.helger.json.serialize.JsonReader;
import com.helger.json.serialize.JsonWriterSettings;
import com.helger.web.scope.IRequestWebScope;
import com.helger.xservlet.handler.IXServletHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Default Servlet handler for CSP reporting. Used in {@link CSPReportingServlet}.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class CSPReportingXServletHandler implements IXServletHandler
{
  public static final boolean DEFAULT_FILTER_DUPLICATES = true;
  private static final Logger LOGGER = LoggerFactory.getLogger (CSPReportingXServletHandler.class);

  protected final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  private final Consumer <? super IJsonObject> m_aJsonHandler;
  @GuardedBy ("m_aRWLock")
  private boolean m_bFilterDuplicates = DEFAULT_FILTER_DUPLICATES;
  @GuardedBy ("m_aRWLock")
  private final ICommonsSet <String> m_aBlockedURIs = new CommonsHashSet <> ();

  public CSPReportingXServletHandler ()
  {
    this (CSPReportingXServletHandler::logCSPReport);
  }

  public CSPReportingXServletHandler (@NonNull final Consumer <? super IJsonObject> aJsonHandler)
  {
    m_aJsonHandler = ValueEnforcer.notNull (aJsonHandler, "JsonHandler");
  }

  /**
   * @return The JSON consumer provided in the constructor. Never <code>null</code>.
   */
  @NonNull
  public final Consumer <? super IJsonObject> getJsonHandler ()
  {
    return m_aJsonHandler;
  }

  /**
   * @return <code>true</code> if duplicate filtering is enabled (default), <code>false</code> if
   *         not.
   */
  public final boolean isFilterDuplicates ()
  {
    return m_aRWLock.readLockedBoolean ( () -> m_bFilterDuplicates);
  }

  /**
   * Enable or disable duplicate filtering.
   *
   * @param bFilterDuplicates
   *        <code>true</code> to filter duplicates, <code>false</code> to disable it.
   */
  public final void setFilterDuplicates (final boolean bFilterDuplicates)
  {
    m_aRWLock.writeLocked ( () -> m_bFilterDuplicates = bFilterDuplicates);
  }

  @IsLocked (ELockType.WRITE)
  protected final boolean rememberBlockedURL (@NonNull @Nonempty final String sBlockedURI)
  {
    ValueEnforcer.notEmpty (sBlockedURI, "BlockedURI");
    return m_aRWLock.writeLockedBoolean ( () -> !m_aBlockedURIs.add (sBlockedURI));
  }

  public static void logCSPReport (@NonNull final IJsonObject aJson)
  {
    LOGGER.warn ("CSP report: " + aJson.getAsJsonString (JsonWriterSettings.DEFAULT_SETTINGS_FORMATTED));
  }

  public void onRequest (@NonNull final HttpServletRequest aHttpRequest,
                         @NonNull final HttpServletResponse aHttpResponse,
                         @NonNull final EHttpVersion eHttpVersion,
                         @NonNull final EHttpMethod eHttpMethod,
                         @NonNull final IRequestWebScope aRequestScope) throws ServletException, IOException
  {
    // Read all request body bytes
    final byte [] aBytes = StreamHelper.getAllBytes (aHttpRequest.getInputStream ());

    // Try to parse as JSON
    final IJson aJson = JsonReader.builder ().source (aBytes).read ();
    if (aJson != null)
    {
      if (aJson.isObject ())
      {
        final IJsonObject aJsonObj = aJson.getAsObject ();
        final String sBlockedURI = aJsonObj.getAsString ("blocked-uri");

        final boolean bIsDuplicate = isFilterDuplicates () &&
                                     StringHelper.isNotEmpty (sBlockedURI) &&
                                     rememberBlockedURL (sBlockedURI);

        if (bIsDuplicate)
        {
          // Avoid too many reports
          LOGGER.info ("Ignoring already blocked CSP URI '" + sBlockedURI + "'");
        }
        else
        {
          // Unique URL
          m_aJsonHandler.accept (aJson.getAsObject ());
        }
      }
      else
        LOGGER.error ("Weird JSON received: " + aJson.getAsJsonString (JsonWriterSettings.DEFAULT_SETTINGS_FORMATTED));
    }
    else
      LOGGER.error ("Failed to parse CSP report JSON: " + new String (aBytes, StandardCharsets.ISO_8859_1));

    // Ack (202)
    aHttpResponse.setStatus (HttpServletResponse.SC_ACCEPTED);
  }

  @NonNull
  @ReturnsMutableCopy
  public final ICommonsSet <String> getAllBlockedURIs ()
  {
    return m_aRWLock.readLockedGet (m_aBlockedURIs::getClone);
  }

  @NonNull
  public final EChange clearAllBlockedURIs ()
  {
    return m_aRWLock.readLockedGet (m_aBlockedURIs::removeAll);
  }
}
