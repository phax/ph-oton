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
package com.helger.html.hc.html.forms;

import javax.annotation.Nonnull;

import com.helger.commons.CGlobal;
import com.helger.commons.equals.EqualsHelper;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.config.HCConsistencyChecker;
import com.helger.html.hc.html.AbstractHCElementWithChildren;
import com.helger.html.hc.html.HCHTMLHelper;
import com.helger.xml.microdom.IMicroElement;

public abstract class AbstractHCProgress <IMPLTYPE extends AbstractHCProgress <IMPLTYPE>> extends AbstractHCElementWithChildren <IMPLTYPE>
                                         implements
                                         IHCProgress <IMPLTYPE>
{
  private double m_dValue = CGlobal.ILLEGAL_DOUBLE;
  private double m_dMax = CGlobal.ILLEGAL_DOUBLE;

  public AbstractHCProgress ()
  {
    super (EHTMLElement.PROGRESS);
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

  @Override
  protected void onConsistencyCheck (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.onConsistencyCheck (aConversionSettings);
    if (HCHTMLHelper.recursiveContainsChildWithTagName (this, EHTMLElement.PROGRESS))
      HCConsistencyChecker.consistencyError ("PROGRESS contains other nested progress");
  }

  @Override
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);

    /**
     * <pre>
     * The value attribute, if present, must have a value equal to or
     * greater than zero, and less than or equal to the value of the max
     * attribute, if present, or 1.0, otherwise. The max attribute, if
     * present, must have a value greater than zero.
     * </pre>
     */
    if (!EqualsHelper.equals (m_dValue, CGlobal.ILLEGAL_DOUBLE))
      aElement.setAttribute (CHTMLAttributes.VALUE, Double.toString (m_dValue));
    if (!EqualsHelper.equals (m_dMax, CGlobal.ILLEGAL_DOUBLE))
      aElement.setAttribute (CHTMLAttributes.MAX, Double.toString (m_dMax));
  }
}
