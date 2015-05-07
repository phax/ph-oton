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
package com.helger.html.hc.html5;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.annotations.SinceHTML5;
import com.helger.html.hc.IHCCanBeDisabled;
import com.helger.html.hc.api5.EHCCommandType;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCElement;

@SinceHTML5
public class HCCommand extends AbstractHCElement <HCCommand> implements IHCCanBeDisabled <HCCommand>
{
  /** By default checked is disabled */
  public static final boolean DEFAULT_CHECKED = false;
  /** By default default is disabled */
  public static final boolean DEFAULT_DEFAULT = false;
  /** By default disabled is disabled */
  public static final boolean DEFAULT_DISABLED = false;

  private boolean m_bChecked = DEFAULT_CHECKED;
  private boolean m_bDefault = DEFAULT_DEFAULT;
  private boolean m_bDisabled = DEFAULT_DISABLED;
  private ISimpleURL m_aIcon;
  private String m_sLabel;
  private String m_sRadioGroup;
  private EHCCommandType m_eType = EHCCommandType.DEFAULT;

  public HCCommand ()
  {
    super (EHTMLElement.COMMAND);
  }

  public boolean isChecked ()
  {
    return m_bChecked;
  }

  @Nonnull
  public HCCommand setChecked (final boolean bChecked)
  {
    m_bChecked = bChecked;
    return this;
  }

  public boolean isDefault ()
  {
    return m_bDefault;
  }

  @Nonnull
  public HCCommand setDefault (final boolean bDefault)
  {
    m_bDefault = bDefault;
    return this;
  }

  public boolean isDisabled ()
  {
    return m_bDisabled;
  }

  @Nonnull
  public HCCommand setDisabled (final boolean bDisabled)
  {
    m_bDisabled = bDisabled;
    return this;
  }

  @Nullable
  public ISimpleURL getIcon ()
  {
    return m_aIcon;
  }

  @Nonnull
  public HCCommand setIcon (@Nullable final ISimpleURL aIcon)
  {
    m_aIcon = aIcon;
    return this;
  }

  @Nullable
  public String getLabel ()
  {
    return m_sLabel;
  }

  @Nonnull
  public HCCommand setLabel (@Nullable final String sLabel)
  {
    m_sLabel = sLabel;
    return this;
  }

  @Nullable
  public String getRadioGroup ()
  {
    return m_sRadioGroup;
  }

  @Nonnull
  public HCCommand setRadioGroup (@Nullable final String sRadioGroup)
  {
    m_sRadioGroup = sRadioGroup;
    return this;
  }

  @Nonnull
  public EHCCommandType getType ()
  {
    return m_eType;
  }

  @Nonnull
  public HCCommand setType (@Nonnull final EHCCommandType eType)
  {
    m_eType = ValueEnforcer.notNull (eType, "Type");
    return this;
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (m_bChecked)
      aElement.setAttribute (CHTMLAttributes.CHECKED, CHTMLAttributeValues.CHECKED);
    if (m_bDefault)
      aElement.setAttribute (CHTMLAttributes.DEFAULT, CHTMLAttributeValues.DEFAULT);
    if (m_bDisabled)
      aElement.setAttribute (CHTMLAttributes.DISABLED, CHTMLAttributeValues.DISABLED);
    if (m_aIcon != null)
      aElement.setAttribute (CHTMLAttributes.ICON, m_aIcon.getAsString ());
    if (StringHelper.hasText (m_sLabel))
      aElement.setAttribute (CHTMLAttributes.LABEL, m_sLabel);
    if (StringHelper.hasText (m_sRadioGroup) && EHCCommandType.RADIO.equals (m_eType))
      aElement.setAttribute (CHTMLAttributes.RADIOGROUP, m_sRadioGroup);
    aElement.setAttribute (CHTMLAttributes.TYPE, m_eType);
  }

  @Override
  @Nonnull
  public String getPlainText ()
  {
    return StringHelper.getNotNull (m_sLabel);
  }
}
