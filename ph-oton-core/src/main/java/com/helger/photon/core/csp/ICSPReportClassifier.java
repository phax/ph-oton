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

import java.util.Locale;

import org.jspecify.annotations.NonNull;

import com.helger.base.string.StringHelper;

/**
 * Decides whether a received CSP report is actionable or noise.<br>
 * A large share of the reports that reach a public reporting endpoint is not caused by the own
 * markup at all, but by browser internal code and by browser extensions that re-fetch URLs they
 * scraped from the DOM. Such reports cannot be fixed by changing the application, so they are
 * classified instead of being acted upon.
 *
 * @author Philip Helger
 * @since 10.4.0
 */
@FunctionalInterface
public interface ICSPReportClassifier
{
  /**
   * The default classifier: a report is considered noise if it has a "source-file" whose scheme is
   * neither <code>http</code> nor <code>https</code>. That covers the observed values
   * <code>resource</code>, <code>moz-extension</code>, <code>chrome-extension</code>,
   * <code>safari-extension</code>, <code>about</code> and <code>null</code>, as well as the bare
   * scheme names that some browsers leave behind when truncating the URL.<br>
   * An absent "source-file" is deliberately not treated as a noise signal, because the CSP
   * specification allows it to be null when the violation cannot be attributed to a global object.
   */
  ICSPReportClassifier DEFAULT = aReport -> {
    final String sSourceFile = aReport.getSourceFile ();
    if (StringHelper.isEmpty (sSourceFile))
    {
      // Legitimately absent - not a noise signal on its own
      return ECSPReportClassification.GENUINE;
    }

    // Everything up to the first ':' is the scheme. If there is no ':' at all, the whole value is
    // taken as the scheme, which is the browser truncation case (e.g. the bare string "resource")
    final int nColonIdx = sSourceFile.indexOf (':');
    final String sScheme = (nColonIdx < 0 ? sSourceFile : sSourceFile.substring (0, nColonIdx)).toLowerCase (
                                                                                                             Locale.ROOT);

    if ("http".equals (sScheme) || "https".equals (sScheme))
      return ECSPReportClassification.GENUINE;

    return ECSPReportClassification.LIKELY_NOISE;
  };

  /**
   * Classify the provided report.
   *
   * @param aReport
   *        The report to be classified. Never <code>null</code>.
   * @return The classification. May not be <code>null</code>.
   */
  @NonNull
  ECSPReportClassification classify (@NonNull CSPReport aReport);
}
