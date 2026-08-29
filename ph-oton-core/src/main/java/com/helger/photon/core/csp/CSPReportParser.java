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
import java.util.Locale;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.string.StringHelper;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.json.IJson;
import com.helger.json.IJsonObject;
import com.helger.json.JsonObject;
import com.helger.json.serialize.JsonReader;

/**
 * Parses the body of a CSP reporting request into {@link CSPReport} objects. Both the legacy
 * <code>application/csp-report</code> format and the Reporting API
 * <code>application/reports+json</code> format are supported and are normalized into the same
 * representation.
 *
 * @author Philip Helger
 * @since 10.4.0
 */
@Immutable
public final class CSPReportParser
{
  /** The MIME type of the legacy CSP report format - a single object with a "csp-report" key */
  public static final String MIME_TYPE_CSP_REPORT = "application/csp-report";
  /** The MIME type of the Reporting API format - an array of report envelopes */
  public static final String MIME_TYPE_REPORTS_JSON = "application/reports+json";

  /** The "type" value of a CSP violation inside a Reporting API envelope */
  public static final String REPORT_TYPE_CSP_VIOLATION = "csp-violation";

  /** The name of the wrapper element of the legacy format */
  public static final String ELEMENT_CSP_REPORT = "csp-report";

  private CSPReportParser ()
  {}

  /**
   * Check whether the provided MIME type can be parsed by this class. The check ignores an
   * eventually present parameter like "; charset=utf-8".
   *
   * @param sContentType
   *        The value of the "Content-Type" HTTP request header. May be <code>null</code>.
   * @return <code>true</code> if the content type is supported, <code>false</code> otherwise.
   */
  public static boolean isSupportedContentType (@Nullable final String sContentType)
  {
    final String sMimeType = _getMimeTypeOnly (sContentType);
    return MIME_TYPE_CSP_REPORT.equals (sMimeType) || MIME_TYPE_REPORTS_JSON.equals (sMimeType);
  }

  @Nullable
  private static String _getMimeTypeOnly (@Nullable final String sContentType)
  {
    if (StringHelper.isEmpty (sContentType))
      return null;
    final int nSemicolonIdx = sContentType.indexOf (';');
    final String sMimeType = nSemicolonIdx < 0 ? sContentType : sContentType.substring (0, nSemicolonIdx);
    return sMimeType.trim ().toLowerCase (Locale.ROOT);
  }

  /**
   * Copy a single field from the source object to the target object, translating the field name.
   */
  private static void _copy (@NonNull final IJsonObject aSrc,
                             @NonNull final String sSrcName,
                             @NonNull final IJsonObject aDst,
                             @NonNull final String sDstName)
  {
    final IJson aValue = aSrc.get (sSrcName);
    if (aValue != null)
      aDst.add (sDstName, aValue.getClone ());
  }

  /**
   * Normalize the "body" object of a Reporting API envelope into the canonical, hyphenated field
   * names of the legacy format.
   *
   * @param aBody
   *        The camelCase body object. May not be <code>null</code>.
   * @return The normalized object. Never <code>null</code>.
   */
  @NonNull
  @ReturnsMutableCopy
  public static IJsonObject normalizeReportingAPIBody (@NonNull final IJsonObject aBody)
  {
    final IJsonObject ret = new JsonObject ();
    _copy (aBody, "documentURL", ret, CSPReport.FIELD_DOCUMENT_URI);
    _copy (aBody, "referrer", ret, CSPReport.FIELD_REFERRER);
    _copy (aBody, "blockedURL", ret, CSPReport.FIELD_BLOCKED_URI);
    _copy (aBody, "effectiveDirective", ret, CSPReport.FIELD_EFFECTIVE_DIRECTIVE);
    _copy (aBody, "originalPolicy", ret, CSPReport.FIELD_ORIGINAL_POLICY);
    _copy (aBody, "disposition", ret, CSPReport.FIELD_DISPOSITION);
    _copy (aBody, "sourceFile", ret, CSPReport.FIELD_SOURCE_FILE);
    _copy (aBody, "lineNumber", ret, CSPReport.FIELD_LINE_NUMBER);
    _copy (aBody, "columnNumber", ret, CSPReport.FIELD_COLUMN_NUMBER);
    _copy (aBody, "statusCode", ret, CSPReport.FIELD_STATUS_CODE);
    _copy (aBody, "sample", ret, CSPReport.FIELD_SCRIPT_SAMPLE);
    return ret;
  }

  /**
   * Parse the provided request body.
   *
   * @param aBytes
   *        The raw request body. May not be <code>null</code>.
   * @param sContentType
   *        The value of the "Content-Type" HTTP request header. Determines how the body is
   *        interpreted. May be <code>null</code>.
   * @param sUserAgent
   *        The "User-Agent" HTTP request header. May be <code>null</code>.
   * @param sRemoteAddr
   *        The remote address of the request. May be <code>null</code>.
   * @param aReceiptDT
   *        The date and time of receipt. May not be <code>null</code>.
   * @return A non-<code>null</code> but maybe empty list of the contained reports. Empty if the
   *         body could not be parsed or contained no CSP violation at all.
   */
  @NonNull
  @ReturnsMutableCopy
  public static ICommonsList <CSPReport> parse (final byte @NonNull [] aBytes,
                                                @Nullable final String sContentType,
                                                @Nullable final String sUserAgent,
                                                @Nullable final String sRemoteAddr,
                                                @NonNull final LocalDateTime aReceiptDT)
  {
    final ICommonsList <CSPReport> ret = new CommonsArrayList <> ();

    final IJson aJson = JsonReader.builder ().source (aBytes).read ();
    if (aJson == null)
      return ret;

    if (MIME_TYPE_REPORTS_JSON.equals (_getMimeTypeOnly (sContentType)))
    {
      // Reporting API - an array of envelopes
      if (!aJson.isArray ())
        return ret;

      for (final IJsonObject aEnvelope : aJson.getAsArray ().iteratorObjects ())
      {
        // The endpoint is CSP specific - skip deprecation, intervention, etc.
        final String sType = aEnvelope.getAsString ("type");
        if (StringHelper.isNotEmpty (sType) && !REPORT_TYPE_CSP_VIOLATION.equals (sType))
          continue;

        final IJsonObject aBody = aEnvelope.getAsObject ("body");
        if (aBody != null)
          ret.add (new CSPReport (normalizeReportingAPIBody (aBody), sUserAgent, sRemoteAddr, aReceiptDT));
      }
    }
    else
    {
      // Legacy format - a single object wrapping the report in "csp-report"
      if (!aJson.isObject ())
        return ret;

      final IJsonObject aObj = aJson.getAsObject ();
      // Unwrap if the envelope is present, else take the object as it is
      final IJsonObject aBody = aObj.getAsObject (ELEMENT_CSP_REPORT);
      ret.add (new CSPReport (aBody != null ? aBody.getClone () : aObj.getClone (),
                              sUserAgent,
                              sRemoteAddr,
                              aReceiptDT));
    }

    return ret;
  }
}
