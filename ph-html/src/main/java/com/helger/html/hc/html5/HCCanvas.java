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

import com.helger.commons.CGlobal;
import com.helger.commons.microdom.IMicroElement;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.annotations.SinceHTML5;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCElementWithChildren;

@SinceHTML5
public class HCCanvas extends AbstractHCElementWithChildren <HCCanvas>
{
  private long m_nHeight = CGlobal.ILLEGAL_ULONG;
  private long m_nWidth = CGlobal.ILLEGAL_ULONG;

  public HCCanvas ()
  {
    super (EHTMLElement.CANVAS);
  }

  public long getHeight ()
  {
    return m_nHeight;
  }

  @Nonnull
  public HCCanvas setHeight (final long nHeight)
  {
    m_nHeight = nHeight;
    return this;
  }

  public long getWidth ()
  {
    return m_nWidth;
  }

  @Nonnull
  public HCCanvas setWidth (final long nWidth)
  {
    m_nWidth = nWidth;
    return this;
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (m_nHeight >= 0)
      aElement.setAttribute (CHTMLAttributes.HEIGHT, m_nHeight);
    if (m_nWidth >= 0)
      aElement.setAttribute (CHTMLAttributes.WIDTH, m_nWidth);
  }
}
