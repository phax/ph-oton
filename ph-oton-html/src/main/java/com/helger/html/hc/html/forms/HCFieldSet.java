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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.OverridingMethodsMustInvokeSuper;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCElementWithChildren;
import com.helger.xml.microdom.IMicroElement;

/**
 * Represents an HTML &lt;fieldset&gt; element
 *
 * @author Philip Helger
 */
public class HCFieldSet extends AbstractHCElementWithChildren <HCFieldSet>
{
  public static final boolean DEFAULT_DISABLED = false;

  private boolean m_bDisabled = DEFAULT_DISABLED;
  private String m_sForm;
  private String m_sName;

  public HCFieldSet ()
  {
    super (EHTMLElement.FIELDSET);
  }

  public HCFieldSet (@Nullable final String sLabel)
  {
    this ();
    if (sLabel != null)
      addChild (new HCLegend ().addChild (sLabel));
  }

  public final boolean isDisabled ()
  {
    return m_bDisabled;
  }

  @NonNull
  public final HCFieldSet setDisabled (final boolean bDisabled)
  {
    m_bDisabled = bDisabled;
    return thisAsT ();
  }

  @Nullable
  public final String getForm ()
  {
    return m_sForm;
  }

  @NonNull
  public final HCFieldSet setForm (@Nullable final String sForm)
  {
    m_sForm = sForm;
    return thisAsT ();
  }

  @Nullable
  public final String getName ()
  {
    return m_sName;
  }

  @NonNull
  public final HCFieldSet setName (@Nullable final String sName)
  {
    m_sName = sName;
    return thisAsT ();
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (m_bDisabled)
      aElement.setAttribute (CHTMLAttributes.DISABLED, CHTMLAttributeValues.DISABLED);
    if (StringHelper.isNotEmpty (m_sForm))
      aElement.setAttribute (CHTMLAttributes.FORM, m_sForm);
    if (StringHelper.isNotEmpty (m_sName))
      aElement.setAttribute (CHTMLAttributes.NAME, m_sName);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("disabled", m_bDisabled)
                            .appendIfNotNull ("form", m_sForm)
                            .appendIfNotNull ("name", m_sName)
                            .getToString ();
  }
}
