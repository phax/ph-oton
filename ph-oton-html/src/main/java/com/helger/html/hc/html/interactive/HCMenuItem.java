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
package com.helger.html.hc.html.interactive;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.base.state.ETriState;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCElementWithChildren;
import com.helger.html.hc.html.grouping.IHCLI;
import com.helger.url.ISimpleURL;
import com.helger.xml.microdom.IMicroElement;

/**
 * Represents an HTML &lt;MENUITEM&gt; element
 *
 * @author Philip Helger
 */
@Deprecated (forRemoval = false)
public class HCMenuItem extends AbstractHCElementWithChildren <HCMenuItem> implements IHCLI <HCMenuItem>
{
  /**
   * Boolean attribute which indicates whether the command is selected. May only be used when the
   * type attribute is checkbox or radio.
   */
  private ETriState m_eChecked = ETriState.UNDEFINED;

  /**
   * Specifies the ID of a separate element, indicating a command to be invoked indirectly. May not
   * be used within a menu item that also includes the attributes checked, disabled, icon, label,
   * radiogroup or type.
   */
  private String m_sCommand;
  /**
   * This Boolean attribute indicates use of the same command as the menu's subject element (such as
   * a button or input).
   */
  private ETriState m_eDefault = ETriState.UNDEFINED;
  /**
   * Boolean attribute which indicates that the command is not available in the current state. Note
   * that disabled is distinct from hidden; the disabled attribute is appropriate in any context
   * where a change in circumstances might render the command relevant.
   */
  private ETriState m_eDisabled = ETriState.UNDEFINED;
  /** Image URL, used to provide a picture to represent the command. */
  private ISimpleURL m_aIcon;
  /**
   * The name of the command as shown to the user. Required when a command attribute is not present.
   */
  private String m_sLabel;
  /**
   * This attribute specifies the name of a group of commands to be toggled as radio buttons when
   * selected. May only be used where the type attribute is radio.
   */
  private String m_sRadioGroup;
  /**
   * This attribute indicates the kind of command, and can be one of three values.
   * <ul>
   * <li>command: A regular command with an associated action. This is the missing value
   * default.</li>
   * <li>checkbox: Represents a command that can be toggled between two different states.</li>
   * <li>radio: Represent one selection from a group of commands that can be toggled as radio
   * buttons.</li>
   * </ul>
   */
  private EHCCommandType m_eType;

  /**
   * Create a new MENUITEM element
   */
  @Deprecated
  public HCMenuItem ()
  {
    super (EHTMLElement.MENUITEM);
  }

  @Deprecated
  public final boolean isChecked ()
  {
    return m_eChecked.isTrue ();
  }

  @Deprecated
  @NonNull
  public final HCMenuItem setChecked (final boolean bMenuItem)
  {
    m_eChecked = ETriState.valueOf (bMenuItem);
    return this;
  }

  @Deprecated
  @Nullable
  public final String getCommand ()
  {
    return m_sCommand;
  }

  @Deprecated
  @NonNull
  public final HCMenuItem setCommand (@Nullable final String sCommand)
  {
    m_sCommand = sCommand;
    return this;
  }

  @Deprecated
  public final boolean isDefault ()
  {
    return m_eDefault.isTrue ();
  }

  @Deprecated
  @NonNull
  public final HCMenuItem setDefault (final boolean bMenuItem)
  {
    m_eDefault = ETriState.valueOf (bMenuItem);
    return this;
  }

  @Deprecated
  public final boolean isDisabled ()
  {
    return m_eDisabled.isTrue ();
  }

  @Deprecated
  @NonNull
  public final HCMenuItem setDisabled (final boolean bMenuItem)
  {
    m_eDisabled = ETriState.valueOf (bMenuItem);
    return this;
  }

  @Deprecated
  @Nullable
  public final ISimpleURL getIcon ()
  {
    return m_aIcon;
  }

  @Deprecated
  @NonNull
  public final HCMenuItem setIcon (@Nullable final ISimpleURL aIcon)
  {
    m_aIcon = aIcon;
    return this;
  }

  @Deprecated
  @Nullable
  public final String getLabel ()
  {
    return m_sLabel;
  }

  @Deprecated
  @NonNull
  public final HCMenuItem setLabel (@Nullable final String sLabel)
  {
    m_sLabel = sLabel;
    return this;
  }

  @Deprecated
  @Nullable
  public final String getRadioGroup ()
  {
    return m_sRadioGroup;
  }

  @Deprecated
  @NonNull
  public final HCMenuItem setRadioGroup (@Nullable final String sRadioGroup)
  {
    m_sRadioGroup = sRadioGroup;
    return this;
  }

  @Deprecated
  @Nullable
  public final EHCCommandType getType ()
  {
    return m_eType;
  }

  @Deprecated
  @NonNull
  public final HCMenuItem setType (@Nullable final EHCCommandType eType)
  {
    m_eType = eType;
    return this;
  }

  @Deprecated
  @Override
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);

    if (m_eChecked.isDefined ())
      aElement.setAttribute (CHTMLAttributes.CHECKED, m_eChecked.isTrue () ? CHTMLAttributeValues.CHECKED : null);
    if (StringHelper.isNotEmpty (m_sCommand))
      aElement.setAttribute (CHTMLAttributes.COMMAND, m_sCommand);
    if (m_eDefault.isDefined ())
      aElement.setAttribute (CHTMLAttributes.DEFAULT, m_eDefault.isTrue () ? CHTMLAttributeValues.DEFAULT : null);
    if (m_eDisabled.isDefined ())
      aElement.setAttribute (CHTMLAttributes.DISABLED, m_eDisabled.isTrue () ? CHTMLAttributeValues.DISABLED : null);
    if (m_aIcon != null)
      aElement.setAttribute (CHTMLAttributes.ICON,
                             m_aIcon.getWithCharset (aConversionSettings.getCharset ()).getAsString ());
    if (StringHelper.isNotEmpty (m_sLabel))
      aElement.setAttribute (CHTMLAttributes.LABEL, m_sLabel);
    if (StringHelper.isNotEmpty (m_sRadioGroup))
      aElement.setAttribute (CHTMLAttributes.RADIOGROUP, m_sRadioGroup);
    if (m_eType != null)
      aElement.setAttribute (CHTMLAttributes.TYPE, m_eType.getAttrValue ());
  }

  @Deprecated
  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("Checked", m_eChecked)
                            .append ("Command", m_sCommand)
                            .append ("Default", m_eDefault)
                            .append ("Disabled", m_eDisabled)
                            .append ("Icon", m_aIcon)
                            .append ("Label", m_sLabel)
                            .append ("RadioGroup", m_sRadioGroup)
                            .append ("Type", m_eType)
                            .getToString ();
  }
}
