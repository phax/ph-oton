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
package com.helger.html.hc.html.forms;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.string.ToStringGenerator;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCElementWithInternalChildren;
import com.helger.html.hc.html.IHCHasState;
import com.helger.xml.microdom.IMicroElement;

/**
 * Represents an HTML &lt;optgroup&gt; element
 *
 * @author Philip Helger
 */
public class HCOptGroup extends AbstractHCElementWithInternalChildren <HCOptGroup, HCOption> implements IHCHasState <HCOptGroup>
{
  /** By default the opt group is not disabled */
  public static final boolean DEFAULT_DISABLED = false;

  private boolean m_bDisabled = DEFAULT_DISABLED;
  private String m_sLabel;

  public HCOptGroup ()
  {
    super (EHTMLElement.OPTGROUP);
  }

  public final boolean isDisabled ()
  {
    return m_bDisabled;
  }

  @Nonnull
  public final HCOptGroup setDisabled (final boolean bDisabled)
  {
    m_bDisabled = bDisabled;
    return this;
  }

  @Nullable
  public final String getLabel ()
  {
    return m_sLabel;
  }

  @Nonnull
  public final HCOptGroup setLabel (@Nullable final String sLabel)
  {
    m_sLabel = sLabel;
    return this;
  }

  @Override
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (m_bDisabled)
      aElement.setAttribute (CHTMLAttributes.DISABLED, CHTMLAttributeValues.DISABLED);
    if (m_sLabel != null)
      aElement.setAttribute (CHTMLAttributes.LABEL, m_sLabel);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("disabled", m_bDisabled)
                            .appendIfNotNull ("label", m_sLabel)
                            .getToString ();
  }
}
