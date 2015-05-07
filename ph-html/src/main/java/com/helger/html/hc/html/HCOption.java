/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCCanBeDisabled;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCElementWithInternalChildren;
import com.helger.html.hc.impl.HCTextNode;

/**
 * Represents a single option within a select box.
 *
 * @author Philip Helger
 */
public class HCOption extends AbstractHCElementWithInternalChildren <HCOption, HCTextNode> implements IHCCanBeDisabled <HCOption>
{
  /** By default the option is not disabled */
  public static final boolean DEFAULT_DISABLED = false;
  /** By default the option is not selected */
  public static final boolean DEFAULT_SELECTED = false;

  private boolean m_bDisabled = DEFAULT_DISABLED;
  private String m_sLabel;
  private boolean m_bSelected = DEFAULT_SELECTED;
  private String m_sValue;

  // Check if a selection was defined or not
  private boolean m_bSelectionDefined = false;

  public HCOption ()
  {
    super (EHTMLElement.OPTION);
  }

  public boolean isDisabled ()
  {
    return m_bDisabled;
  }

  @Nonnull
  public HCOption setDisabled (final boolean bDisabled)
  {
    m_bDisabled = bDisabled;
    return this;
  }

  @Nullable
  public String getLabel ()
  {
    return m_sLabel;
  }

  @Nonnull
  public HCOption setLabel (final String sLabel)
  {
    m_sLabel = sLabel;
    return this;
  }

  public boolean isSelected ()
  {
    return m_bSelected;
  }

  /**
   * @return <code>true</code> if this option was specially marked selected or
   *         not selected.
   */
  public boolean isSelectionDefined ()
  {
    return m_bSelectionDefined;
  }

  @Nonnull
  public HCOption setSelected (final boolean bSelected)
  {
    m_bSelected = bSelected;
    m_bSelectionDefined = true;
    return this;
  }

  @Nullable
  public String getValue ()
  {
    return m_sValue;
  }

  @Nonnull
  public HCOption setValue (@Nullable final String sValue)
  {
    m_sValue = sValue;
    return this;
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (m_bDisabled)
      aElement.setAttribute (CHTMLAttributes.DISABLED, CHTMLAttributeValues.DISABLED);
    if (m_sLabel != null)
      aElement.setAttribute (CHTMLAttributes.LABEL, m_sLabel);
    if (m_bSelected)
      aElement.setAttribute (CHTMLAttributes.SELECTED, CHTMLAttributeValues.SELECTED);
    if (m_sValue != null)
      aElement.setAttribute (CHTMLAttributes.VALUE, m_sValue);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("disabled", m_bDisabled)
                            .appendIfNotNull ("label", m_sLabel)
                            .append ("selected", m_bSelected)
                            .appendIfNotNull ("value", m_sValue)
                            .toString ();
  }
}
