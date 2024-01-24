/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.CGlobal;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCElementWithChildren;
import com.helger.xml.microdom.IMicroElement;

/**
 * Abstract base class for table cells. Works for header, body and footer cells.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractHCCell <IMPLTYPE extends AbstractHCCell <IMPLTYPE>> extends
                                     AbstractHCElementWithChildren <IMPLTYPE> implements
                                     IHCCell <IMPLTYPE>
{
  private HCRow m_aParentRow;
  private int m_nColspan = CGlobal.ILLEGAL_UINT;
  private int m_nRowspan = CGlobal.ILLEGAL_UINT;

  public AbstractHCCell (@Nonnull final EHTMLElement aElement)
  {
    super (aElement);
  }

  @Nullable
  public final HCRow getParentRow ()
  {
    return m_aParentRow;
  }

  @Nonnull
  public final IMPLTYPE internalSetParentRow (@Nullable final HCRow aParentRow)
  {
    m_aParentRow = aParentRow;
    return thisAsT ();
  }

  @Nonnegative
  public final int getColspan ()
  {
    return m_nColspan > 1 ? m_nColspan : 1;
  }

  @Nonnull
  public final IMPLTYPE setColspan (final int nColspan)
  {
    m_nColspan = nColspan;
    return thisAsT ();
  }

  @Nonnegative
  public final int getRowspan ()
  {
    return m_nRowspan > 1 ? m_nRowspan : 1;
  }

  @Nonnull
  public final IMPLTYPE setRowspan (final int nRowspan)
  {
    m_nRowspan = nRowspan;
    return thisAsT ();
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (m_nColspan >= 1 && m_nColspan <= 1000)
      aElement.setAttribute (CHTMLAttributes.COLSPAN, m_nColspan);
    if (m_nRowspan >= 0 && m_nRowspan <= 65534)
      aElement.setAttribute (CHTMLAttributes.ROWSPAN, m_nRowspan);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("colspan", m_nColspan)
                            .append ("rowSpan", m_nRowspan)
                            .getToString ();
  }
}
