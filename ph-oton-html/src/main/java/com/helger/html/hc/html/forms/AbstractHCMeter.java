/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.forms;

import javax.annotation.Nonnull;

import com.helger.commons.CGlobal;
import com.helger.commons.equals.EqualsHelper;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.annotation.SinceHTML5;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.config.HCConsistencyChecker;
import com.helger.html.hc.html.AbstractHCElementWithChildren;
import com.helger.html.hc.html.HCHTMLHelper;
import com.helger.xml.microdom.IMicroElement;

@SinceHTML5
public abstract class AbstractHCMeter <IMPLTYPE extends AbstractHCMeter <IMPLTYPE>> extends
                                      AbstractHCElementWithChildren <IMPLTYPE> implements
                                      IHCMeter <IMPLTYPE>
{
  private double m_dValue = CGlobal.ILLEGAL_DOUBLE;
  private double m_dMin = CGlobal.ILLEGAL_DOUBLE;
  private double m_dMax = CGlobal.ILLEGAL_DOUBLE;
  private double m_dLow = CGlobal.ILLEGAL_DOUBLE;
  private double m_dHigh = CGlobal.ILLEGAL_DOUBLE;
  private double m_dOptimum = CGlobal.ILLEGAL_DOUBLE;

  public AbstractHCMeter ()
  {
    super (EHTMLElement.METER);
  }

  public final double getValue ()
  {
    return m_dValue;
  }

  @Nonnull
  public final IMPLTYPE setValue (final double dValue)
  {
    m_dValue = dValue;
    return thisAsT ();
  }

  public final double getMin ()
  {
    return m_dMin;
  }

  @Nonnull
  public final IMPLTYPE setMin (final double dMin)
  {
    m_dMin = dMin;
    return thisAsT ();
  }

  public final double getMax ()
  {
    return m_dMax;
  }

  @Nonnull
  public final IMPLTYPE setMax (final double dMax)
  {
    m_dMax = dMax;
    return thisAsT ();
  }

  public final double getLow ()
  {
    return m_dLow;
  }

  @Nonnull
  public final IMPLTYPE setLow (final double dLow)
  {
    m_dLow = dLow;
    return thisAsT ();
  }

  public final double getHigh ()
  {
    return m_dHigh;
  }

  @Nonnull
  public final IMPLTYPE setHigh (final double dHigh)
  {
    m_dHigh = dHigh;
    return thisAsT ();
  }

  public final double getOptimum ()
  {
    return m_dOptimum;
  }

  @Nonnull
  public final IMPLTYPE setOptimum (final double dOptimum)
  {
    m_dOptimum = dOptimum;
    return thisAsT ();
  }

  @Override
  protected void onConsistencyCheck (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.onConsistencyCheck (aConversionSettings);
    if (HCHTMLHelper.recursiveContainsChildWithTagName (this, EHTMLElement.METER))
      HCConsistencyChecker.consistencyError ("METER contains other nested meter");
  }

  @Override
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    /**
     * <pre>
     * The following inequalities must hold, as applicable:
     *
     * minimum &le; value &le; maximum
     * minimum &le; low &le; maximum (if low is specified)
     * minimum &le; high &le; maximum (if high is specified)
     * minimum &le; optimum &le; maximum (if optimum is specified)
     * low &le; high (if both low and high are specified)
     * </pre>
     */
    super.fillMicroElement (aElement, aConversionSettings);

    if (!EqualsHelper.equals (m_dValue, CGlobal.ILLEGAL_DOUBLE))
      aElement.setAttribute (CHTMLAttributes.VALUE, Double.toString (m_dValue));
    if (!EqualsHelper.equals (m_dMin, CGlobal.ILLEGAL_DOUBLE))
      aElement.setAttribute (CHTMLAttributes.MIN, Double.toString (m_dMin));
    if (!EqualsHelper.equals (m_dMax, CGlobal.ILLEGAL_DOUBLE))
      aElement.setAttribute (CHTMLAttributes.MAX, Double.toString (m_dMax));
    if (!EqualsHelper.equals (m_dLow, CGlobal.ILLEGAL_DOUBLE))
      aElement.setAttribute (CHTMLAttributes.LOW, Double.toString (m_dLow));
    if (!EqualsHelper.equals (m_dHigh, CGlobal.ILLEGAL_DOUBLE))
      aElement.setAttribute (CHTMLAttributes.HIGH, Double.toString (m_dHigh));
    if (!EqualsHelper.equals (m_dOptimum, CGlobal.ILLEGAL_DOUBLE))
      aElement.setAttribute (CHTMLAttributes.OPTIMUM, Double.toString (m_dOptimum));
  }
}
