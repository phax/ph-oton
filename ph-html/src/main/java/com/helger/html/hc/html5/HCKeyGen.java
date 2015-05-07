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
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.annotations.SinceHTML5;
import com.helger.html.hc.IHCCanBeDisabled;
import com.helger.html.hc.api5.EHCKeyGenType;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCElement;

@SinceHTML5
public class HCKeyGen extends AbstractHCElement <HCKeyGen> implements IHCCanBeDisabled <HCKeyGen>
{
  public static final boolean DEFAULT_AUTOFOCUS = false;
  public static final boolean DEFAULT_DISABLED = false;
  public static final EHCKeyGenType DEFAULT_KEY_TYPE = EHCKeyGenType.DEFAULT;

  private boolean m_bAutofocus = DEFAULT_AUTOFOCUS;
  private String m_sChallenge;
  private boolean m_bDisabled = DEFAULT_DISABLED;
  private String m_sForm;
  private EHCKeyGenType m_eKeyType = DEFAULT_KEY_TYPE;
  private String m_sName;

  public HCKeyGen ()
  {
    super (EHTMLElement.KEYGEN);
  }

  public HCKeyGen (@Nullable final String sName)
  {
    this ();
    m_sName = sName;
  }

  public boolean isAutofocus ()
  {
    return m_bAutofocus;
  }

  @Nonnull
  public HCKeyGen setAutofocus (final boolean bAutofocus)
  {
    m_bAutofocus = bAutofocus;
    return this;
  }

  @Nullable
  public String getChallenge ()
  {
    return m_sChallenge;
  }

  @Nonnull
  public HCKeyGen setChallenge (@Nullable final String sChallenge)
  {
    m_sChallenge = sChallenge;
    return this;
  }

  public boolean isDisabled ()
  {
    return m_bDisabled;
  }

  @Nonnull
  public HCKeyGen setDisabled (final boolean bDisabled)
  {
    m_bDisabled = bDisabled;
    return this;
  }

  @Nullable
  public String getForm ()
  {
    return m_sForm;
  }

  @Nonnull
  public HCKeyGen setForm (@Nullable final String sForm)
  {
    m_sForm = sForm;
    return this;
  }

  @Nonnull
  public EHCKeyGenType getKeyType ()
  {
    return m_eKeyType;
  }

  @Nonnull
  public HCKeyGen setKeyType (@Nonnull final EHCKeyGenType eKeyType)
  {
    m_eKeyType = ValueEnforcer.notNull (eKeyType, "KeyType");
    return this;
  }

  @Nullable
  public String getName ()
  {
    return m_sName;
  }

  @Nonnull
  public HCKeyGen setName (@Nullable final String sName)
  {
    m_sName = sName;
    return this;
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (m_bAutofocus)
      aElement.setAttribute (CHTMLAttributes.AUTOFOCUS, CHTMLAttributeValues.AUTOFOCUS);
    if (StringHelper.hasText (m_sChallenge))
      aElement.setAttribute (CHTMLAttributes.CHALLENGE, m_sChallenge);
    if (m_bDisabled)
      aElement.setAttribute (CHTMLAttributes.DISABLED, CHTMLAttributeValues.DISABLED);
    if (StringHelper.hasText (m_sForm))
      aElement.setAttribute (CHTMLAttributes.FORM, m_sForm);
    aElement.setAttribute (CHTMLAttributes.KEYTYPE, m_eKeyType);
    if (StringHelper.hasText (m_sName))
      aElement.setAttribute (CHTMLAttributes.NAME, m_sName);
  }
}
