/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.AbstractHCElement;
import com.helger.html.hc.html.FakeJS;
import com.helger.html.hc.html.script.HCScriptInline;
import com.helger.xml.microdom.IMicroElement;

@NotThreadSafe
public abstract class AbstractHCControl <IMPLTYPE extends AbstractHCControl <IMPLTYPE>> extends
                                        AbstractHCElement <IMPLTYPE> implements
                                        IHCControl <IMPLTYPE>
{
  public static final boolean DEFAULT_DISABLED = false;
  public static final boolean DEFAULT_READ_ONLY = false;

  /** By default required is disabled */
  public static final boolean DEFAULT_REQUIRED = false;

  /** By default auto focus is disabled */
  public static final boolean DEFAULT_AUTO_FOCUS = false;

  public static final ICSSClassProvider CSS_CLASS_READ_ONLY = DefaultCSSClassProvider.create ("read-only");

  private boolean m_bDisabled = DEFAULT_DISABLED;
  private String m_sName;
  private boolean m_bReadOnly = DEFAULT_READ_ONLY;
  private boolean m_bRequired = DEFAULT_REQUIRED;
  private boolean m_bAutoFocus = DEFAULT_AUTO_FOCUS;

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

  public final boolean isAutoFocus ()
  {
    return m_bAutoFocus;
  }

  @Nonnull
  public final IMPLTYPE setAutoFocus (final boolean bAutoFocus)
  {
    m_bAutoFocus = bAutoFocus;
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

      // Set explicit tab index -1 to avoid focusing
      setTabIndex (-1L);
    }

    if (m_bAutoFocus)
    {
      // Add a JS call that focuses this element (for non HTML5 browsers)
      aTargetNode.addChild (new HCScriptInline (FakeJS.focus (this)));
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
    if (StringHelper.hasText (m_sName))
      aElement.setAttribute (CHTMLAttributes.NAME, m_sName);
    if (m_bReadOnly)
      aElement.setAttribute (CHTMLAttributes.READONLY, CHTMLAttributeValues.READONLY);
    if (m_bRequired)
      aElement.setAttribute (CHTMLAttributes.REQUIRED, CHTMLAttributeValues.REQUIRED);
    if (m_bAutoFocus)
      aElement.setAttribute (CHTMLAttributes.AUTOFOCUS, CHTMLAttributeValues.AUTOFOCUS);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("disabled", m_bDisabled)
                            .appendIfNotNull ("name", m_sName)
                            .append ("readOnly", m_bReadOnly)
                            .append ("required", m_bRequired)
                            .append ("autoFocus", m_bAutoFocus)
                            .getToString ();
  }
}
