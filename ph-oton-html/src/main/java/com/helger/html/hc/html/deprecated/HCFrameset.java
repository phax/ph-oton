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
package com.helger.html.hc.html.deprecated;

import javax.annotation.Nonnull;

import com.helger.commons.CGlobal;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.annotation.DeprecatedInHTML5;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCElementWithChildren;
import com.helger.xml.microdom.IMicroElement;

/**
 * Represents an HTML &lt;frameset&gt; element
 *
 * @author Philip Helger
 */
@DeprecatedInHTML5
@Deprecated
public class HCFrameset extends AbstractHCElementWithChildren <HCFrameset>
{
  private int m_nCols = CGlobal.ILLEGAL_UINT;
  private int m_nRows = CGlobal.ILLEGAL_UINT;

  public HCFrameset ()
  {
    super (EHTMLElement.FRAMESET);
  }

  public final int getCols ()
  {
    return m_nCols;
  }

  @Nonnull
  public final HCFrameset setCols (final int nCols)
  {
    m_nCols = nCols;
    return this;
  }

  public final int getRows ()
  {
    return m_nRows;
  }

  @Nonnull
  public final HCFrameset setRows (final int nRows)
  {
    m_nRows = nRows;
    return this;
  }

  @Override
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (m_nCols > 0)
      aElement.setAttribute (CHTMLAttributes.COLS, m_nCols);
    if (m_nRows > 0)
      aElement.setAttribute (CHTMLAttributes.ROWS, m_nRows);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("cols", m_nCols).append ("rows", m_nRows).getToString ();
  }
}
