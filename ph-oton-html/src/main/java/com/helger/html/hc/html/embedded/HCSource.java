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
package com.helger.html.hc.html.embedded;

import com.helger.base.string.StringHelper;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCMediaElementChild;
import com.helger.html.hc.html.IHCHasMedia;
import com.helger.mime.IMimeType;
import com.helger.url.ISimpleURL;
import com.helger.xml.microdom.IMicroElement;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public class HCSource extends AbstractHCMediaElementChild <HCSource> implements IHCHasMedia <HCSource>
{
  private ISimpleURL m_aSrc;
  private IMimeType m_aType;
  // For picture
  private String m_sSrcSet;
  private String m_sSizes;
  private String m_sMediaQuery;

  public HCSource ()
  {
    super (EHTMLElement.SOURCE);
  }

  @Nullable
  public final ISimpleURL getSrc ()
  {
    return m_aSrc;
  }

  @Nonnull
  public final HCSource setSrc (@Nullable final ISimpleURL aSrc)
  {
    m_aSrc = aSrc;
    return this;
  }

  @Nullable
  public final IMimeType getType ()
  {
    return m_aType;
  }

  @Nonnull
  public final HCSource setType (@Nullable final IMimeType aType)
  {
    m_aType = aType;
    return this;
  }

  @Nullable
  public final String getSrcSet ()
  {
    return m_sSrcSet;
  }

  @Nonnull
  public final HCSource setSrcSet (@Nullable final String sSrcSet)
  {
    m_sSrcSet = sSrcSet;
    return this;
  }

  @Nullable
  public final String getSizes ()
  {
    return m_sSizes;
  }

  @Nonnull
  public final HCSource setSizes (@Nullable final String sSizes)
  {
    m_sSizes = sSizes;
    return this;
  }

  @Nullable
  public final String getMedia ()
  {
    return m_sMediaQuery;
  }

  @Nonnull
  public final HCSource setMedia (@Nullable final String sMediaQuery)
  {
    m_sMediaQuery = sMediaQuery;
    return this;
  }

  @Override
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (m_aSrc != null)
      aElement.setAttribute (CHTMLAttributes.SRC, m_aSrc.getAsStringWithEncodedParameters (aConversionSettings.getCharset ()));
    if (m_aType != null)
      aElement.setAttribute (CHTMLAttributes.TYPE, m_aType.getAsString ());
    if (StringHelper.isNotEmpty (m_sSrcSet))
      aElement.setAttribute (CHTMLAttributes.SRCSET, m_sSrcSet);
    if (StringHelper.isNotEmpty (m_sSizes))
      aElement.setAttribute (CHTMLAttributes.SIZES, m_sSizes);
    if (StringHelper.isNotEmpty (m_sMediaQuery))
      aElement.setAttribute (CHTMLAttributes.MEDIA, m_sMediaQuery);
  }
}
