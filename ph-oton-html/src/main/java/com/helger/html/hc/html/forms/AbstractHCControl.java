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

import com.helger.annotation.Nonempty;
import com.helger.annotation.OverridingMethodsMustInvokeSuper;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.AbstractHCElement;
import com.helger.xml.microdom.IMicroElement;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

@NotThreadSafe
public abstract class AbstractHCControl <IMPLTYPE extends AbstractHCControl <IMPLTYPE>> extends
                                        AbstractHCElement <IMPLTYPE> implements
                                        IHCControl <IMPLTYPE>
{
  public static final boolean DEFAULT_DISABLED = false;
  public static final boolean DEFAULT_READ_ONLY = false;

  /** By default required is disabled */
  public static final boolean DEFAULT_REQUIRED = false;

  public static final ICSSClassProvider CSS_CLASS_READ_ONLY = DefaultCSSClassProvider.create ("read-only");

  private boolean m_bDisabled = DEFAULT_DISABLED;
  private String m_sName;
  private boolean m_bReadOnly = DEFAULT_READ_ONLY;
  private boolean m_bRequired = DEFAULT_REQUIRED;

  public AbstractHCControl (@Nonnull @Nonempty final EHTMLElement aElement)
  {
    super (aElement);
  }

  public final boolean isDisabled ()
  {
    return m_bDisabled;
  }

  @Nonnull
  public final IMPLTYPE setDisabled (final boolean bDisabled)
  {
    m_bDisabled = bDisabled;
    return thisAsT ();
  }

  public final String getName ()
  {
    return m_sName;
  }

  @Nonnull
  public final IMPLTYPE setName (@Nullable final String sName)
  {
    m_sName = sName;
    return thisAsT ();
  }

  public final boolean isReadOnly ()
  {
    return m_bReadOnly;
  }

  @Nonnull
  public final IMPLTYPE setReadOnly (final boolean bReadOnly)
  {
    m_bReadOnly = bReadOnly;
    return thisAsT ();
  }

  public final boolean isRequired ()
  {
    return m_bRequired;
  }

  @Nonnull
  public final IMPLTYPE setRequired (final boolean bRequired)
  {
    m_bRequired = bRequired;
    return thisAsT ();
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);

    // Read only?
    if (m_bReadOnly)
    {
      // Add read-only class
      addClass (CSS_CLASS_READ_ONLY);

      // It should be focusable
      if (false)
      {
        // Set explicit tab index -1 to avoid focusing
        setTabIndex (-1L);
      }
    }
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (m_bDisabled)
      aElement.setAttribute (CHTMLAttributes.DISABLED, CHTMLAttributeValues.DISABLED);
    // focus handling is performed in HCDefaultCustomizer!
    if (StringHelper.isNotEmpty (m_sName))
      aElement.setAttribute (CHTMLAttributes.NAME, m_sName);
    if (m_bReadOnly)
      aElement.setAttribute (CHTMLAttributes.READONLY, CHTMLAttributeValues.READONLY);
    if (m_bRequired)
      aElement.setAttribute (CHTMLAttributes.REQUIRED, CHTMLAttributeValues.REQUIRED);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("Disabled", m_bDisabled)
                            .appendIfNotNull ("Name", m_sName)
                            .append ("ReadOnly", m_bReadOnly)
                            .append ("Required", m_bRequired)
                            .getToString ();
  }
}
