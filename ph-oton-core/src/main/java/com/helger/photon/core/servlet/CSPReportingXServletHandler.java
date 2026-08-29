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
import java.util.Map;
import java.util.function.Consumer;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
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
import com.helger.collection.commons.CommonsLinkedHashMap;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsOrderedMap;
import com.helger.collection.commons.ICommonsSet;
import com.helger.datetime.helper.PDTFactory;
import com.helger.http.CHttpHeader;
import com.helger.http.EHttpMethod;
import com.helger.http.EHttpVersion;
import com.helger.json.IJsonObject;
import com.helger.json.serialize.JsonWriterSettings;
import com.helger.photon.core.csp.CSPReport;
import com.helger.photon.core.csp.CSPReportParser;
import com.helger.photon.core.csp.ECSPReportClassification;
import com.helger.photon.core.csp.ICSPReportClassifier;
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
  private final Consumer <? super CSPReport> m_aReportHandler;
  @GuardedBy ("m_aRWLock")
  private boolean m_bFilterDuplicates = DEFAULT_FILTER_DUPLICATES;
  @GuardedBy ("m_aRWLock")
  private ICSPReportClassifier m_aClassifier = ICSPReportClassifier.DEFAULT;
  @GuardedBy ("m_aRWLock")
  private final ICommonsSet <String> m_aBlockedURIs = new CommonsHashSet <> ();

  public CSPReportingXServletHandler ()
  {
    this (CSPReportingXServletHandler::logCSPReport);
  }

  public CSPReportingXServletHandler (@NonNull final Consumer <? super CSPReport> aReportHandler)
  {
    m_aReportHandler = ValueEnforcer.notNull (aReportHandler, "ReportHandler");
  }

  /**
   * @return The report consumer provided in the constructor. Never <code>null</code>.
   */
  @NonNull
  public final Consumer <? super CSPReport> getReportHandler ()
  {
    return m_aReportHandler;
  }

  /**
   * @return <code>true</code> if duplicate filtering is enabled (default), <code>false</code> if
   *         not.
   */
  public final boolean isFilterDuplicates ()
  {
    return m_aRWLock.readLockedBoolean (() -> m_bFilterDuplicates);
  }

  /**
   * Enable or disable duplicate filtering.
   *
   * @param bFilterDuplicates
   *        <code>true</code> to filter duplicates, <code>false</code> to disable it.
   */
  public final void setFilterDuplicates (final boolean bFilterDuplicates)
  {
    m_aRWLock.writeLocked (() -> m_bFilterDuplicates = bFilterDuplicates);
  }

  /**
   * @return The classifier used to separate actionable reports from noise. Never <code>null</code>.
   *         Defaults to {@link ICSPReportClassifier#DEFAULT}.
   * @since 10.4.0
   */
  @NonNull
  public final ICSPReportClassifier getClassifier ()
  {
    return m_aRWLock.readLockedGet (() -> m_aClassifier);
  }

  /**
   * Set the classifier used to separate actionable reports from noise. A downstream consumer may
   * have a different noise profile than the default.
   *
   * @param aClassifier
   *        The classifier to be used. May not be <code>null</code>.
   * @since 10.4.0
   */
  public final void setClassifier (@NonNull final ICSPReportClassifier aClassifier)
  {
    ValueEnforcer.notNull (aClassifier, "Classifier");
    m_aRWLock.writeLocked (() -> m_aClassifier = aClassifier);
  }

  @IsLocked (ELockType.WRITE)
  protected final boolean rememberBlockedURL (@NonNull @Nonempty final String sBlockedURI)
  {
    ValueEnforcer.notEmpty (sBlockedURI, "BlockedURI");
    return m_aRWLock.writeLockedBoolean (() -> !m_aBlockedURIs.add (sBlockedURI));
  }

  /**
   * The default report consumer. An actionable report is logged as a warning, a report classified
   * as noise is logged on the info level only, so that it can be filtered away by log
   * configuration.
   *
   * @param aReport
   *        The received report. May not be <code>null</code>.
   */
  public static void logCSPReport (@NonNull final CSPReport aReport)
  {
    final String sMsg = "CSP report: " +
                        aReport.getAsJson ().getAsJsonString (JsonWriterSettings.DEFAULT_SETTINGS_FORMATTED);
    if (aReport.getClassification ().isLikelyNoise ())
      LOGGER.info (sMsg);
    else
      LOGGER.warn (sMsg);
  }

  /**
   * The legacy report consumer signature, taking the plain JSON object. Kept as a convenience for
   * consumers that only care about the violation fields.
   *
   * @param aJsonConsumer
   *        The consumer of the normalized violation fields. May not be <code>null</code>.
   * @return A consumer that can be passed to the constructor. Never <code>null</code>.
   * @since 10.4.0
   */
  @NonNull
  public static Consumer <CSPReport> asJsonConsumer (@NonNull final Consumer <? super IJsonObject> aJsonConsumer)
  {
    ValueEnforcer.notNull (aJsonConsumer, "JsonConsumer");
    return aReport -> aJsonConsumer.accept (aReport.getBody ());
  }

  public void onRequest (@NonNull final HttpServletRequest aHttpRequest,
                         @NonNull final HttpServletResponse aHttpResponse,
                         @NonNull final EHttpVersion eHttpVersion,
                         @NonNull final EHttpMethod eHttpMethod,
                         @NonNull final IRequestWebScope aRequestScope) throws ServletException, IOException
  {
    final String sContentType = aHttpRequest.getContentType ();
    if (!CSPReportParser.isSupportedContentType (sContentType))
    {
      LOGGER.warn ("Rejecting CSP report with unsupported content type '" + sContentType + "'");
      aHttpResponse.setStatus (HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
      return;
    }

    // Read all request body bytes
    final byte [] aBytes = StreamHelper.getAllBytes (aHttpRequest.getInputStream ());

    final ICommonsList <CSPReport> aReports = CSPReportParser.parse (aBytes,
                                                                     sContentType,
                                                                     aHttpRequest.getHeader (CHttpHeader.USER_AGENT),
                                                                     aRequestScope.getRemoteAddr (),
                                                                     PDTFactory.getCurrentLocalDateTime ());
    if (aReports.isEmpty ())
      LOGGER.error ("Failed to parse CSP report of type '" +
                    sContentType +
                    "': " +
                    new String (aBytes, StandardCharsets.ISO_8859_1));
    else
    {
      // The query parameters of the report URI are the only way to add server side context to a
      // report. They are attacker controlled, so they are used for grouping only.
      final Map <String, String> aURIParams = _getURIParams (aRequestScope);

      final ICSPReportClassifier aClassifier = getClassifier ();
      for (final CSPReport aReport : aReports)
      {
        aReport.uriParams ().putAll (aURIParams);
        aReport.setClassification (aClassifier.classify (aReport));

        final String sBlockedURI = aReport.getBlockedURI ();
        final boolean bIsDuplicate = isFilterDuplicates () &&
                                     StringHelper.isNotEmpty (sBlockedURI) &&
                                     rememberBlockedURL (sBlockedURI);
        if (bIsDuplicate)
        {
          // Avoid too many reports
          LOGGER.info ("Ignoring already blocked CSP URI '" + sBlockedURI + "'");
        }
        else
          m_aReportHandler.accept (aReport);
      }
    }

    // Ack (202)
    aHttpResponse.setStatus (HttpServletResponse.SC_ACCEPTED);
  }

  @NonNull
  @ReturnsMutableCopy
  private static Map <String, String> _getURIParams (@NonNull final IRequestWebScope aRequestScope)
  {
    final ICommonsOrderedMap <String, String> ret = new CommonsLinkedHashMap <> ();
    for (final Map.Entry <String, Object> aEntry : aRequestScope.params ().entrySet ())
    {
      final Object aValue = aEntry.getValue ();
      if (aValue instanceof final String sValue)
        ret.put (aEntry.getKey (), sValue);
    }
    return ret;
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

  /**
   * @param eClassification
   *        The classification to check. May be <code>null</code>.
   * @return <code>true</code> if the provided classification is noise.
   * @since 10.4.0
   */
  public static boolean isNoise (@Nullable final ECSPReportClassification eClassification)
  {
    return eClassification != null && eClassification.isLikelyNoise ();
  }
}
