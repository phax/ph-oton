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

import java.time.LocalDateTime;

import org.junit.Test;

import com.helger.datetime.helper.PDTFactory;
import com.helger.json.IJsonObject;
import com.helger.json.JsonObject;

/**
 * Test class for the default implementation of {@link ICSPReportClassifier}.
 *
 * @author Philip Helger
 */
public final class ICSPReportClassifierTest
{
  private static CSPReport _reportWithSourceFile (final String sSourceFile)
  {
    final IJsonObject aBody = new JsonObject ().add (CSPReport.FIELD_BLOCKED_URI, "https://evil.example.com/x.js");
    if (sSourceFile != null)
      aBody.add (CSPReport.FIELD_SOURCE_FILE, sSourceFile);
    final LocalDateTime aDT = PDTFactory.getCurrentLocalDateTime ();
    return new CSPReport (aBody, "Mozilla/5.0 UnitTest", "127.0.0.1", aDT);
  }

  private static void _assertNoise (final String sSourceFile)
  {
    assertEquals ("source-file '" + sSourceFile + "' must be classified as noise",
                  ECSPReportClassification.LIKELY_NOISE,
                  ICSPReportClassifier.DEFAULT.classify (_reportWithSourceFile (sSourceFile)));
  }

  private static void _assertGenuine (final String sSourceFile)
  {
    assertEquals ("source-file '" + sSourceFile + "' must NOT be classified as noise",
                  ECSPReportClassification.GENUINE,
                  ICSPReportClassifier.DEFAULT.classify (_reportWithSourceFile (sSourceFile)));
  }

  @Test
  public void testNonHttpSchemesAreNoise ()
  {
    // Truncated Firefox internal URL, as observed in production
    _assertNoise ("resource");
    _assertNoise ("resource://gre/modules/something.sys.mjs");
    _assertNoise ("moz-extension://1234-5678/content.js");
    _assertNoise ("chrome-extension://abcdefg/inject.js");
    _assertNoise ("safari-extension://com.example.ext/script.js");
    _assertNoise ("about");
    _assertNoise ("about:blank");
    _assertNoise ("null");
    _assertNoise ("data:text/javascript,alert(1)");
  }

  @Test
  public void testGenuineViolationIsNotNoise ()
  {
    // A real same origin violation must never be classified as noise
    _assertGenuine ("https://smp.example.org/secure/menuitem-service_groups_export_data");
    _assertGenuine ("http://localhost:90/secure/");
    // Casing of the scheme is irrelevant
    _assertGenuine ("HTTPS://smp.example.org/secure/");
  }

  @Test
  public void testAbsentSourceFileIsNotNoise ()
  {
    // Per spec the source-file is legitimately null if the violation cannot be attributed to a
    // global object - that alone is not a noise signal
    _assertGenuine (null);
    _assertGenuine ("");
  }

  @Test
  public void testClassifierIsOverridable ()
  {
    // A downstream consumer may have a completely different noise profile
    final ICSPReportClassifier aAllNoise = aReport -> ECSPReportClassification.LIKELY_NOISE;
    assertEquals (ECSPReportClassification.LIKELY_NOISE,
                  aAllNoise.classify (_reportWithSourceFile ("https://smp.example.org/secure/")));
  }
}
