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
import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.microdom.IMicroElement;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.annotations.SinceHTML5;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCElementWithChildren;

@SinceHTML5
public class HCProgress extends AbstractHCElementWithChildren <HCProgress>
{
  private double m_dValue = CGlobal.ILLEGAL_DOUBLE;
  private double m_dMax = CGlobal.ILLEGAL_DOUBLE;

  public HCProgress ()
  {
    super (EHTMLElement.PROGRESS);
  }

  public double getValue ()
  {
    return m_dValue;
  }

  @Nonnull
  public HCProgress setValue (final double dValue)
  {
    m_dValue = dValue;
    return this;
  }

  public double getMax ()
  {
    return m_dMax;
  }

  @Nonnull
  public HCProgress setMax (final double dMax)
  {
    m_dMax = dMax;
    return this;
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);

    /**
     * <pre>
     * The value attribute, if present, must have a value equal to or
     * greater than zero, and less than or equal to the value of the max
     * attribute, if present, or 1.0, otherwise. The max attribute, if
     * present, must have a value greater than zero.
     * </pre>
     */
    if (!EqualsUtils.equals (m_dValue, CGlobal.ILLEGAL_DOUBLE))
      aElement.setAttribute (CHTMLAttributes.VALUE, Double.toString (m_dValue));
    if (!EqualsUtils.equals (m_dMax, CGlobal.ILLEGAL_DOUBLE))
      aElement.setAttribute (CHTMLAttributes.MAX, Double.toString (m_dMax));
  }
}
