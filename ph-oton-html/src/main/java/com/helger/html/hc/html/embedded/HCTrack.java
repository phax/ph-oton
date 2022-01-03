/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.embedded;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.string.StringHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.annotation.SinceHTML5;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCMediaElementChild;
import com.helger.xml.microdom.IMicroElement;

@SinceHTML5
public class HCTrack extends AbstractHCMediaElementChild <HCTrack>
{
  public static final boolean DEFAULT_DEFAULT = false;

  private String m_sKind;
  private ISimpleURL m_aSrc;
  private String m_sSrcLang;
  private String m_sLabel;
  private boolean m_bDefault = DEFAULT_DEFAULT;

  public HCTrack ()
  {
    super (EHTMLElement.TRACK);
  }

  @Nullable
  public final String getKind ()
  {
    return m_sKind;
  }

  @Nonnull
  public final HCTrack setKind (@Nullable final String sKind)
  {
    m_sKind = sKind;
    return this;
  }

  @Nullable
  public final ISimpleURL getSrc ()
  {
    return m_aSrc;
  }

  @Nonnull
  public final HCTrack setSrc (@Nullable final ISimpleURL aSrc)
  {
    m_aSrc = aSrc;
    return this;
  }

  @Nullable
  public final String getSrcLang ()
  {
    return m_sSrcLang;
  }

  @Nonnull
  public final HCTrack setSrcLang (@Nullable final String sSrcLang)
  {
    m_sSrcLang = sSrcLang;
    return this;
  }

  @Nullable
  public final String getLabel ()
  {
    return m_sLabel;
  }

  @Nonnull
  public final HCTrack setLabel (@Nullable final String sLabel)
  {
    m_sLabel = sLabel;
    return this;
  }

  public final boolean isDefault ()
  {
    return m_bDefault;
  }

  @Nonnull
  public final HCTrack setDefault (final boolean bDefault)
  {
    m_bDefault = bDefault;
    return this;
  }

  @Override
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (StringHelper.hasText (m_sKind))
      aElement.setAttribute (CHTMLAttributes.KIND, m_sKind);
    if (m_aSrc != null)
      aElement.setAttribute (CHTMLAttributes.SRC, m_aSrc.getAsStringWithEncodedParameters (aConversionSettings.getCharset ()));
    if (StringHelper.hasText (m_sSrcLang))
      aElement.setAttribute (CHTMLAttributes.SRCLANG, m_sSrcLang);
    if (StringHelper.hasText (m_sLabel))
      aElement.setAttribute (CHTMLAttributes.LABEL, m_sLabel);
    if (m_bDefault)
      aElement.setAttribute (CHTMLAttributes.DEFAULT, CHTMLAttributeValues.DEFAULT);
  }
}
