/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.script;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.annotation.OutOfBandNode;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.config.IHCOnDocumentReadyProvider;
import com.helger.html.js.IHasJSCode;

/**
 * Regular inline JS script elements with the special semantics, that stuff will
 * be executed on document.ready. This requires jQuery to be present!
 *
 * @author Philip Helger
 * @see HCScriptInline
 * @see HCScriptFile
 */
@OutOfBandNode
public class HCScriptInlineOnDocumentReady extends AbstractHCScriptInline <HCScriptInlineOnDocumentReady>
{
  private IHasJSCode m_aOnDocumentReadyCode;

  protected HCScriptInlineOnDocumentReady ()
  {}

  public HCScriptInlineOnDocumentReady (@Nonnull final IHasJSCode aOnDocumentReadyCode)
  {
    setOnDocumentReadyCode (aOnDocumentReadyCode);
  }

  public HCScriptInlineOnDocumentReady (@Nonnull final IHCOnDocumentReadyProvider aODRProvider,
                                        @Nonnull final IHasJSCode aOnDocumentReadyCode)
  {
    setOnDocumentReadyCode (aODRProvider, aOnDocumentReadyCode);
  }

  @Nonnull
  public final HCScriptInlineOnDocumentReady setOnDocumentReadyCode (@Nonnull final IHasJSCode aOnDocumentReadyCode)
  {
    return setOnDocumentReadyCode (HCSettings.getOnDocumentReadyProvider (), aOnDocumentReadyCode);
  }

  @Nonnull
  public final HCScriptInlineOnDocumentReady setOnDocumentReadyCode (@Nonnull final IHCOnDocumentReadyProvider aODRProvider,
                                                                     @Nonnull final IHasJSCode aOnDocumentReadyCode)
  {
    ValueEnforcer.notNull (aODRProvider, "OnDocumentReadyProvider");
    ValueEnforcer.notNull (aOnDocumentReadyCode, "OnDocumentReadyCode");
    setJSCodeProvider (aODRProvider.createOnDocumentReady (aOnDocumentReadyCode));
    m_aOnDocumentReadyCode = aOnDocumentReadyCode;
    return this;
  }

  /**
   * @return The contained code, to be executed on document.ready
   */
  @Nonnull
  public final IHasJSCode getOnDocumentReadyCode ()
  {
    return m_aOnDocumentReadyCode;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).appendIfNotNull ("OnDocumentReadyCode", m_aOnDocumentReadyCode).getToString ();
  }
}
