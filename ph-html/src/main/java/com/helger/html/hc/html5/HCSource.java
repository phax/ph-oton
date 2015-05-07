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

import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.annotations.SinceHTML5;
import com.helger.html.hc.api5.IHCHasMedia;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;

@SinceHTML5
public class HCSource extends AbstractHCMediaElementChild <HCSource> implements IHCHasMedia <HCSource>
{
  private String m_sMediaQuery;
  private ISimpleURL m_aSrc;
  private IMimeType m_aType;

  public HCSource ()
  {
    super (EHTMLElement.SOURCE);
  }

  @Nullable
  public String getMedia ()
  {
    return m_sMediaQuery;
  }

  @Nonnull
  public HCSource setMedia (@Nullable final String sMediaQuery)
  {
    m_sMediaQuery = sMediaQuery;
    return this;
  }

  @Nullable
  public ISimpleURL getSrc ()
  {
    return m_aSrc;
  }

  @Nonnull
  public HCSource setSrc (@Nullable final ISimpleURL aSrc)
  {
    m_aSrc = aSrc;
    return this;
  }

  @Nullable
  public IMimeType getType ()
  {
    return m_aType;
  }

  @Nonnull
  public HCSource setType (@Nullable final IMimeType aType)
  {
    m_aType = aType;
    return this;
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (StringHelper.hasText (m_sMediaQuery))
      aElement.setAttribute (CHTMLAttributes.MEDIA, m_sMediaQuery);
    if (m_aSrc != null)
      aElement.setAttribute (CHTMLAttributes.SRC, m_aSrc.getAsString ());
    if (m_aType != null)
      aElement.setAttribute (CHTMLAttributes.TYPE, m_aType.getAsString ());
  }
}
