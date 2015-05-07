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

import com.helger.commons.ValueEnforcer;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.api.EHCParamValueType;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCElement;

/**
 * Represents a single parameter for an {@link HCObject} or an applet.
 *
 * @author Philip Helger
 */
public class HCParam extends AbstractHCElement <HCParam>
{
  private String m_sName;
  private String m_sValue;
  private EHCParamValueType m_eValueType;
  private IMimeType m_aType;

  public HCParam ()
  {
    super (EHTMLElement.PARAM);
  }

  public HCParam (@Nonnull final String sName)
  {
    this ();
    setName (sName);
  }

  @Nullable
  public String getName ()
  {
    return m_sName;
  }

  @Nonnull
  public HCParam setName (@Nonnull final String sName)
  {
    m_sName = ValueEnforcer.notNull (sName, "Name");
    return this;
  }

  @Nullable
  public String getValue ()
  {
    return m_sValue;
  }

  @Nonnull
  public HCParam setValue (@Nullable final String sValue)
  {
    m_sValue = sValue;
    return this;
  }

  @Nullable
  public EHCParamValueType getValueType ()
  {
    return m_eValueType;
  }

  @Nonnull
  public HCParam setValueType (@Nullable final EHCParamValueType eValueType)
  {
    m_eValueType = eValueType;
    return this;
  }

  @Nullable
  public IMimeType getType ()
  {
    return m_aType;
  }

  @Nonnull
  public HCParam setType (@Nullable final IMimeType aType)
  {
    m_aType = aType;
    return this;
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (StringHelper.hasText (m_sName))
      aElement.setAttribute (CHTMLAttributes.NAME, m_sName);
    if (StringHelper.hasText (m_sValue))
      aElement.setAttribute (CHTMLAttributes.VALUE, m_sValue);
    if (m_eValueType != null)
      aElement.setAttribute (CHTMLAttributes.VALUETYPE, m_eValueType);
    if (m_aType != null)
      aElement.setAttribute (CHTMLAttributes.TYPE, m_aType.getAsString ());
    // Should not be self closed!
    aElement.appendText ("");
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("name", m_sName)
                            .appendIfNotNull ("value", m_sValue)
                            .appendIfNotNull ("valueType", m_eValueType)
                            .appendIfNotNull ("type", m_aType)
                            .toString ();
  }
}
