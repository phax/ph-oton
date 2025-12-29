/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.html.hc.mock;

import org.junit.rules.ExternalResource;

import com.helger.annotation.OverridingMethodsMustInvokeSuper;
import com.helger.annotation.style.OverrideOnDemand;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.config.IHCOnDocumentReadyProvider;
import com.helger.html.js.PhotonInternalUnparsedJS;

/**
 * A JUnit test rule that ensures that optimized HTML, CSS and JS output is created.
 *
 * @author Philip Helger
 * @since 4.7.0
 */
public class HCTestRuleOptimized extends ExternalResource
{
  private boolean m_bWasSilent;
  private IHCOnDocumentReadyProvider m_aOld;

  public HCTestRuleOptimized ()
  {}

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  public void before ()
  {
    m_bWasSilent = HCSettings.setSilentMode (true);
    HCSettings.setDefaultHTMLVersion (EHTMLVersion.XHTML11);
    HCSettings.getMutableConversionSettings ().setToOptimized ();
    m_aOld = HCSettings.getOnDocumentReadyProvider ();
    HCSettings.setOnDocumentReadyProvider (aJSCodeProvider -> new PhotonInternalUnparsedJS ("$(document).ready(function(){" +
                                                                                            aJSCodeProvider.getJSCode () +
                                                                                            "});"));
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  public void after ()
  {
    HCSettings.setOnDocumentReadyProvider (m_aOld);
    HCSettings.getMutableConversionSettings ().setToDefault ();
    HCSettings.setSilentMode (m_bWasSilent);
  }
}
