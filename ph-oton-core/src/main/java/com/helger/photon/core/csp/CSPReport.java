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

import java.time.LocalDateTime;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.annotation.style.ReturnsMutableObject;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.commons.CommonsLinkedHashMap;
import com.helger.collection.commons.ICommonsOrderedMap;
import com.helger.json.IJsonObject;
import com.helger.json.JsonObject;

/**
 * A single CSP violation report, normalized from either the legacy
 * <code>application/csp-report</code> format or the Reporting API
 * <code>application/reports+json</code> format, and enriched with the data that only the server
 * knows.<br>
 * The violation fields are kept in the canonical, hyphenated spelling of the legacy format, so that
 * a report coming in via the Reporting API looks the same to a consumer.
 *
 * @author Philip Helger
 * @since 10.4.0
 */
public class CSPReport
{
  /** The canonical name of the "document-uri" field */
  public static final String FIELD_DOCUMENT_URI = "document-uri";
  /** The canonical name of the "referrer" field */
  public static final String FIELD_REFERRER = "referrer";
  /** The canonical name of the "blocked-uri" field */
  public static final String FIELD_BLOCKED_URI = "blocked-uri";
  /** The canonical name of the "effective-directive" field */
  public static final String FIELD_EFFECTIVE_DIRECTIVE = "effective-directive";
  /** The canonical name of the "violated-directive" field */
  public static final String FIELD_VIOLATED_DIRECTIVE = "violated-directive";
  /** The canonical name of the "original-policy" field */
  public static final String FIELD_ORIGINAL_POLICY = "original-policy";
  /** The canonical name of the "disposition" field */
  public static final String FIELD_DISPOSITION = "disposition";
  /** The canonical name of the "source-file" field */
  public static final String FIELD_SOURCE_FILE = "source-file";
  /** The canonical name of the "line-number" field */
  public static final String FIELD_LINE_NUMBER = "line-number";
  /** The canonical name of the "column-number" field */
  public static final String FIELD_COLUMN_NUMBER = "column-number";
  /** The canonical name of the "status-code" field */
  public static final String FIELD_STATUS_CODE = "status-code";
  /** The canonical name of the "script-sample" field */
  public static final String FIELD_SCRIPT_SAMPLE = "script-sample";

  private final IJsonObject m_aBody;
  private final String m_sUserAgent;
  private final String m_sRemoteAddr;
  private final LocalDateTime m_aReceiptDT;
  private final ICommonsOrderedMap <String, String> m_aURIParams = new CommonsLinkedHashMap <> ();
  private ECSPReportClassification m_eClassification = ECSPReportClassification.GENUINE;

  /**
   * Constructor
   *
   * @param aBody
   *        The normalized violation fields. May not be <code>null</code>.
   * @param sUserAgent
   *        The "User-Agent" HTTP request header of the POST that delivered the report. May be
   *        <code>null</code>.
   * @param sRemoteAddr
   *        The remote address of the POST that delivered the report. May be <code>null</code>.
   * @param aReceiptDT
   *        The date and time at which the report was received. May not be <code>null</code>.
   */
  public CSPReport (@NonNull final IJsonObject aBody,
                    @Nullable final String sUserAgent,
                    @Nullable final String sRemoteAddr,
                    @NonNull final LocalDateTime aReceiptDT)
  {
    m_aBody = ValueEnforcer.notNull (aBody, "Body");
    m_sUserAgent = sUserAgent;
    m_sRemoteAddr = sRemoteAddr;
    m_aReceiptDT = ValueEnforcer.notNull (aReceiptDT, "ReceiptDT");
  }

  /**
   * @return The normalized violation fields, using the canonical hyphenated field names. Never
   *         <code>null</code>.
   */
  @NonNull
  @ReturnsMutableObject
  public final IJsonObject getBody ()
  {
    return m_aBody;
  }

  @Nullable
  public final String getDocumentURI ()
  {
    return m_aBody.getAsString (FIELD_DOCUMENT_URI);
  }

  @Nullable
  public final String getReferrer ()
  {
    return m_aBody.getAsString (FIELD_REFERRER);
  }

  @Nullable
  public final String getBlockedURI ()
  {
    return m_aBody.getAsString (FIELD_BLOCKED_URI);
  }

  @Nullable
  public final String getEffectiveDirective ()
  {
    return m_aBody.getAsString (FIELD_EFFECTIVE_DIRECTIVE);
  }

  @Nullable
  public final String getViolatedDirective ()
  {
    return m_aBody.getAsString (FIELD_VIOLATED_DIRECTIVE);
  }

  @Nullable
  public final String getOriginalPolicy ()
  {
    return m_aBody.getAsString (FIELD_ORIGINAL_POLICY);
  }

  @Nullable
  public final String getDisposition ()
  {
    return m_aBody.getAsString (FIELD_DISPOSITION);
  }

  /**
   * @return The "source-file" of the violation. May be <code>null</code>, because the CSP
   *         specification explicitly allows it to be absent if the violation cannot be attributed
   *         to a global object.
   */
  @Nullable
  public final String getSourceFile ()
  {
    return m_aBody.getAsString (FIELD_SOURCE_FILE);
  }

  public final int getLineNumber ()
  {
    return m_aBody.getAsInt (FIELD_LINE_NUMBER, -1);
  }

  public final int getColumnNumber ()
  {
    return m_aBody.getAsInt (FIELD_COLUMN_NUMBER, -1);
  }

  public final int getStatusCode ()
  {
    return m_aBody.getAsInt (FIELD_STATUS_CODE, -1);
  }

  @Nullable
  public final String getScriptSample ()
  {
    return m_aBody.getAsString (FIELD_SCRIPT_SAMPLE);
  }

  /**
   * @return The "User-Agent" HTTP request header of the POST that delivered the report. May be
   *         <code>null</code>. This is the most reliable field for triage, because it identifies
   *         the browser without having to infer it from the violation fields.
   */
  @Nullable
  public final String getUserAgent ()
  {
    return m_sUserAgent;
  }

  /**
   * @return The remote address of the POST that delivered the report. May be <code>null</code>.
   */
  @Nullable
  public final String getRemoteAddr ()
  {
    return m_sRemoteAddr;
  }

  /**
   * @return The date and time at which the report was received. Never <code>null</code>.
   */
  @NonNull
  public final LocalDateTime getReceiptDateTime ()
  {
    return m_aReceiptDT;
  }

  /**
   * @return The mutable map of the query parameters that were part of the report URI. Never
   *         <code>null</code>.<br>
   *         Note: these values are provided by whoever posted the report and must therefore be
   *         treated as untrusted. Use them for grouping only - never for authorization and never as
   *         a lookup key into anything security relevant.
   */
  @NonNull
  @ReturnsMutableObject
  public final ICommonsOrderedMap <String, String> uriParams ()
  {
    return m_aURIParams;
  }

  /**
   * @return The classification of this report. Never <code>null</code>. Defaults to
   *         {@link ECSPReportClassification#GENUINE}.
   */
  @NonNull
  public final ECSPReportClassification getClassification ()
  {
    return m_eClassification;
  }

  /**
   * Set the classification of this report.
   *
   * @param eClassification
   *        The classification to be set. May not be <code>null</code>.
   * @return this for chaining
   */
  @NonNull
  public final CSPReport setClassification (@NonNull final ECSPReportClassification eClassification)
  {
    m_eClassification = ValueEnforcer.notNull (eClassification, "Classification");
    return this;
  }

  /**
   * @return The complete report - violation fields plus all server side enrichment - as a single
   *         JSON object, suitable for logging. Never <code>null</code>.
   */
  @NonNull
  @ReturnsMutableCopy
  public IJsonObject getAsJson ()
  {
    final IJsonObject ret = new JsonObject ().add ("classification", m_eClassification.getID ())
                                             .addIfNotNull ("user-agent", m_sUserAgent)
                                             .addIfNotNull ("remote-addr", m_sRemoteAddr)
                                             .add ("receipt-dt", m_aReceiptDT.toString ());
    if (m_aURIParams.isNotEmpty ())
      ret.add ("uri-params", new JsonObject ().addAllAny (m_aURIParams));
    ret.add ("csp-report", m_aBody.getClone ());
    return ret;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Body", m_aBody)
                                       .append ("UserAgent", m_sUserAgent)
                                       .append ("RemoteAddr", m_sRemoteAddr)
                                       .append ("ReceiptDT", m_aReceiptDT)
                                       .append ("URIParams", m_aURIParams)
                                       .append ("Classification", m_eClassification)
                                       .getToString ();
  }
}
