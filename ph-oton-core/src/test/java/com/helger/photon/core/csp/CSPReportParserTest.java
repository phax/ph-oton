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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.junit.Test;

import com.helger.collection.commons.ICommonsList;
import com.helger.datetime.helper.PDTFactory;

/**
 * Test class for class {@link CSPReportParser}.
 *
 * @author Philip Helger
 */
public final class CSPReportParserTest
{
  private static final LocalDateTime DT = PDTFactory.getCurrentLocalDateTime ();

  private static ICommonsList <CSPReport> _parse (final String sBody, final String sContentType)
  {
    return CSPReportParser.parse (sBody.getBytes (StandardCharsets.UTF_8),
                                  sContentType,
                                  "Mozilla/5.0 UnitTest",
                                  "127.0.0.1",
                                  DT);
  }

  @Test
  public void testSupportedContentTypes ()
  {
    assertTrue (CSPReportParser.isSupportedContentType ("application/csp-report"));
    assertTrue (CSPReportParser.isSupportedContentType ("application/reports+json"));
    // Parameters and casing must not matter
    assertTrue (CSPReportParser.isSupportedContentType ("application/csp-report; charset=utf-8"));
    assertTrue (CSPReportParser.isSupportedContentType ("Application/Reports+JSON"));

    assertFalse (CSPReportParser.isSupportedContentType (null));
    assertFalse (CSPReportParser.isSupportedContentType (""));
    assertFalse (CSPReportParser.isSupportedContentType ("application/json"));
    assertFalse (CSPReportParser.isSupportedContentType ("text/plain"));
  }

  @Test
  public void testLegacyFormat ()
  {
    final String sBody = "{\"csp-report\":{" +
                         "\"document-uri\":\"https://smp.example.org/secure/menuitem-service_groups_export_data\"," +
                         "\"referrer\":\"\"," +
                         "\"blocked-uri\":\"https://evil.example.com/x.js\"," +
                         "\"effective-directive\":\"script-src-elem\"," +
                         "\"violated-directive\":\"script-src-elem\"," +
                         "\"original-policy\":\"default-src 'none'\"," +
                         "\"disposition\":\"enforce\"," +
                         "\"source-file\":\"https://smp.example.org/secure/x\"," +
                         "\"line-number\":42,\"column-number\":7,\"status-code\":0," +
                         "\"script-sample\":\"alert(1)\"}}";
    final ICommonsList <CSPReport> aList = _parse (sBody, "application/csp-report");
    assertEquals (1, aList.size ());

    final CSPReport aReport = aList.getFirstOrNull ();
    assertNotNull (aReport);
    // The "csp-report" envelope must be unwrapped
    assertNull (aReport.getBody ().get (CSPReportParser.ELEMENT_CSP_REPORT));
    assertEquals ("https://smp.example.org/secure/menuitem-service_groups_export_data", aReport.getDocumentURI ());
    assertEquals ("https://evil.example.com/x.js", aReport.getBlockedURI ());
    assertEquals ("script-src-elem", aReport.getEffectiveDirective ());
    assertEquals ("script-src-elem", aReport.getViolatedDirective ());
    assertEquals ("enforce", aReport.getDisposition ());
    assertEquals ("https://smp.example.org/secure/x", aReport.getSourceFile ());
    assertEquals (42, aReport.getLineNumber ());
    assertEquals (7, aReport.getColumnNumber ());
    assertEquals (0, aReport.getStatusCode ());
    assertEquals ("alert(1)", aReport.getScriptSample ());

    // Enrichment
    assertEquals ("Mozilla/5.0 UnitTest", aReport.getUserAgent ());
    assertEquals ("127.0.0.1", aReport.getRemoteAddr ());
    assertEquals (DT, aReport.getReceiptDateTime ());
  }

  @Test
  public void testLegacyFormatWithoutEnvelope ()
  {
    // Some agents post the bare report - it must still be understood
    final String sBody = "{\"blocked-uri\":\"https://evil.example.com/x.js\",\"source-file\":\"resource\"}";
    final ICommonsList <CSPReport> aList = _parse (sBody, "application/csp-report");
    assertEquals (1, aList.size ());
    assertEquals ("https://evil.example.com/x.js", aList.getFirstOrNull ().getBlockedURI ());
  }

  @Test
  public void testReportingAPIFormatMultipleEntries ()
  {
    final String sBody = "[" +
                         "{\"type\":\"csp-violation\",\"age\":10,\"url\":\"https://smp.example.org/secure/a\"," +
                         "\"user_agent\":\"Envelope UA\",\"body\":{" +
                         "\"documentURL\":\"https://smp.example.org/secure/a\"," +
                         "\"blockedURL\":\"https://evil.example.com/1.js\"," +
                         "\"effectiveDirective\":\"script-src-elem\"," +
                         "\"originalPolicy\":\"default-src 'none'\"," +
                         "\"disposition\":\"enforce\"," +
                         "\"sourceFile\":\"moz-extension://abc/content.js\"," +
                         "\"lineNumber\":1,\"columnNumber\":2,\"statusCode\":200,\"sample\":\"\"}}," +
                         "{\"type\":\"csp-violation\",\"age\":20,\"url\":\"https://smp.example.org/secure/b\"," +
                         "\"body\":{" +
                         "\"documentURL\":\"https://smp.example.org/secure/b\"," +
                         "\"blockedURL\":\"https://evil.example.com/2.js\"," +
                         "\"effectiveDirective\":\"style-src-elem\"," +
                         "\"sourceFile\":\"https://smp.example.org/secure/b\"}}" +
                         "]";
    final ICommonsList <CSPReport> aList = _parse (sBody, "application/reports+json");
    assertEquals (2, aList.size ());

    final CSPReport a1 = aList.get (0);
    // camelCase must be normalized to the canonical hyphenated names
    assertEquals ("https://smp.example.org/secure/a", a1.getDocumentURI ());
    assertEquals ("https://evil.example.com/1.js", a1.getBlockedURI ());
    assertEquals ("script-src-elem", a1.getEffectiveDirective ());
    assertEquals ("moz-extension://abc/content.js", a1.getSourceFile ());
    assertEquals (1, a1.getLineNumber ());
    assertEquals (2, a1.getColumnNumber ());
    assertEquals (200, a1.getStatusCode ());
    // The User-Agent is always taken from the request header, never from the envelope
    assertEquals ("Mozilla/5.0 UnitTest", a1.getUserAgent ());

    final CSPReport a2 = aList.get (1);
    assertEquals ("https://evil.example.com/2.js", a2.getBlockedURI ());
    assertEquals ("style-src-elem", a2.getEffectiveDirective ());
  }

  @Test
  public void testReportingAPIFormatSkipsForeignTypes ()
  {
    // The endpoint is CSP specific - a deprecation report must be ignored
    final String sBody = "[" +
                         "{\"type\":\"deprecation\",\"body\":{\"id\":\"whatever\"}}," +
                         "{\"type\":\"csp-violation\",\"body\":{\"blockedURL\":\"https://evil.example.com/1.js\"}}" +
                         "]";
    final ICommonsList <CSPReport> aList = _parse (sBody, "application/reports+json");
    assertEquals (1, aList.size ());
    assertEquals ("https://evil.example.com/1.js", aList.getFirstOrNull ().getBlockedURI ());
  }

  @Test
  public void testUnparsableBody ()
  {
    assertTrue (_parse ("this is not JSON", "application/csp-report").isEmpty ());
    assertTrue (_parse ("this is not JSON", "application/reports+json").isEmpty ());
    // Right content type, wrong JSON shape
    assertTrue (_parse ("[1,2,3]", "application/csp-report").isEmpty ());
    assertTrue (_parse ("{\"a\":1}", "application/reports+json").isEmpty ());
  }
}
