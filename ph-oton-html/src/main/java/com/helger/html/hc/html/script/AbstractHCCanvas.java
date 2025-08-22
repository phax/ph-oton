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
package com.helger.html.hc.html.script;

import com.helger.base.CGlobal;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCElementWithChildren;
import com.helger.xml.microdom.IMicroElement;

import jakarta.annotation.Nonnull;

/**
 * BAse class for HTML &lt;canvas&gt; elements.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractHCCanvas <IMPLTYPE extends AbstractHCCanvas <IMPLTYPE>> extends AbstractHCElementWithChildren <IMPLTYPE>
                                       implements
                                       IHCCanvas <IMPLTYPE>
{
  private long m_nHeight = CGlobal.ILLEGAL_ULONG;
  private long m_nWidth = CGlobal.ILLEGAL_ULONG;

  public AbstractHCCanvas ()
  {
    super (EHTMLElement.CANVAS);
  }

  public final long getHeight ()
  {
    return m_nHeight;
  }

  @Nonnull
  public final IMPLTYPE setHeight (final long nHeight)
  {
    m_nHeight = nHeight;
    return thisAsT ();
  }

  public final long getWidth ()
  {
    return m_nWidth;
  }

  @Nonnull
  public final IMPLTYPE setWidth (final long nWidth)
  {
    m_nWidth = nWidth;
    return thisAsT ();
  }

  @Override
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (m_nHeight >= 0)
      aElement.setAttribute (CHTMLAttributes.HEIGHT, m_nHeight);
    if (m_nWidth >= 0)
      aElement.setAttribute (CHTMLAttributes.WIDTH, m_nWidth);
  }
}
