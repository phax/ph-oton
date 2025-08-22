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
package com.helger.html.hc.html.tabular;

import com.helger.annotation.CheckForSigned;
import com.helger.annotation.Nonnegative;
import com.helger.annotation.OverridingMethodsMustInvokeSuper;
import com.helger.base.CGlobal;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCElement;
import com.helger.xml.microdom.IMicroElement;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Represents an HTML &lt;col&gt; element
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        The implementation type.
 */
public abstract class AbstractHCCol <IMPLTYPE extends AbstractHCCol <IMPLTYPE>> extends AbstractHCElement <IMPLTYPE> implements
                                    IHCCol <IMPLTYPE>
{
  private String m_sWidth;
  private int m_nSpan = CGlobal.ILLEGAL_UINT;

  public AbstractHCCol ()
  {
    super (EHTMLElement.COL);
  }

  public AbstractHCCol (@Nonnegative final int nWidth)
  {
    this ();
    setWidth (nWidth);
  }

  @Nullable
  public final String getWidth ()
  {
    return m_sWidth;
  }

  @Nonnull
  public final IMPLTYPE setWidth (@Nullable final String sWidth)
  {
    m_sWidth = sWidth;
    return thisAsT ();
  }

  @CheckForSigned
  public final int getSpan ()
  {
    return m_nSpan;
  }

  @Nonnull
  public final IMPLTYPE setSpan (final int nSpan)
  {
    m_nSpan = nSpan;
    return thisAsT ();
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (StringHelper.hasText (m_sWidth))
      aElement.setAttribute (CHTMLAttributes.WIDTH, m_sWidth);
    if (m_nSpan > 0)
      aElement.setAttribute (CHTMLAttributes.SPAN, m_nSpan);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).appendIfNotNull ("width", m_sWidth).append ("span", m_nSpan).getToString ();
  }
}
