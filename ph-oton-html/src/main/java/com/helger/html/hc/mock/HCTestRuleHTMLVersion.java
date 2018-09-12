/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import org.junit.rules.ExternalResource;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.config.HCSettings;

/**
 * A JUnit test rule that ensures that a certain HTML version is used.
 *
 * @author Philip Helger
 * @since 5.0.0
 */
public class HCTestRuleHTMLVersion extends ExternalResource
{
  private boolean m_bWasSilent;
  private final EHTMLVersion m_eHTMLVersion;
  private EHTMLVersion m_ePrevHTMLVersion;

  public HCTestRuleHTMLVersion (@Nonnull final EHTMLVersion eHTMLVersion)
  {
    m_eHTMLVersion = ValueEnforcer.notNull (eHTMLVersion, "HTMLVersion");
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  public void before ()
  {
    m_bWasSilent = HCSettings.setSilentMode (true);
    m_ePrevHTMLVersion = HCSettings.getConversionSettings ().getHTMLVersion ();
    HCSettings.setDefaultHTMLVersion (m_eHTMLVersion);
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  public void after ()
  {
    // Reset to old version
    HCSettings.setDefaultHTMLVersion (m_ePrevHTMLVersion);
    HCSettings.setSilentMode (m_bWasSilent);
  }
}
