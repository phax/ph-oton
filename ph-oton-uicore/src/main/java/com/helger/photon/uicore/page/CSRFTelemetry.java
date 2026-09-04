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
package com.helger.photon.uicore.page;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.photon.uicore.CUICoreTelemetry;
import com.helger.telemetry.TelemetryAttributes;

/**
 * Emits the ph-telemetry metrics for the CSRF nonce checks performed by {@link WebPageCSRFHandler}.
 * All emission happens through the vendor neutral ph-telemetry facades, so without a registered SPI
 * everything degrades to cheap no-ops.<br>
 * The nonce value itself is a security token and never leaves the process through telemetry.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
final class CSRFTelemetry
{
  private CSRFTelemetry ()
  {}

  /**
   * Count a performed CSRF nonce check.
   *
   * @param aWPEC
   *        The web page execution context the check was performed in. May not be <code>null</code>.
   * @param bValid
   *        <code>true</code> if the provided nonce was the expected one.
   */
  static void onCSRFNonceChecked (@NonNull final IWebPageExecutionContext aWPEC, final boolean bValid)
  {
    CSRFMetrics.CHECKS.add (1,
                            TelemetryAttributes.builder ()
                                               .put (CUICoreTelemetry.ATTR_CSRF_PAGE_ID,
                                                     aWPEC.getWebPage ().getID ())
                                               .put (CUICoreTelemetry.ATTR_CSRF_VALID, bValid)
                                               .build ());
  }
}
