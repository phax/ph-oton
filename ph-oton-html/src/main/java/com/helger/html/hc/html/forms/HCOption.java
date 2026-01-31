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
package com.helger.html.hc.html.forms;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCElementWithInternalChildren;
import com.helger.html.hc.html.IHCHasState;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.xml.microdom.IMicroElement;

/**
 * Represents a single option within a select box.
 *
 * @author Philip Helger
 */
public class HCOption extends AbstractHCElementWithInternalChildren <HCOption, HCTextNode> implements IHCHasState <HCOption>
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

  public final boolean isDisabled ()
  {
    return m_bDisabled;
  }

  @NonNull
  public final HCOption setDisabled (final boolean bDisabled)
  {
    m_bDisabled = bDisabled;
    return this;
  }

  @Nullable
  public final String getLabel ()
  {
    return m_sLabel;
  }

  @NonNull
  public final HCOption setLabel (final String sLabel)
  {
    m_sLabel = sLabel;
    return this;
  }

  public final boolean isSelected ()
  {
    return m_bSelected;
  }

  /**
   * @return <code>true</code> if this option was specially marked selected or
   *         not selected.
   */
  public final boolean isSelectionDefined ()
  {
    return m_bSelectionDefined;
  }

  @NonNull
  public final HCOption setSelected (final boolean bSelected)
  {
    m_bSelected = bSelected;
    m_bSelectionDefined = true;
    return this;
  }

  @Nullable
  public final String getValue ()
  {
    return m_sValue;
  }

  @NonNull
  public final HCOption setValue (@Nullable final String sValue)
  {
    m_sValue = sValue;
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
                            .getToString ();
  }
}
