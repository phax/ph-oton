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
package com.helger.html.hc.html;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.css.ECSSUnit;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCElement;

/**
 * Represents an HTML &lt;col&gt; element
 *
 * @author Philip Helger
 */
public class HCCol extends AbstractHCElement <HCCol>
{
  private String m_sWidth;
  private int m_nSpan = CGlobal.ILLEGAL_UINT;

  public HCCol ()
  {
    super (EHTMLElement.COL);
  }

  public HCCol (@Nonnegative final int nWidth)
  {
    this ();
    setWidth (nWidth);
  }

  /**
   * @return <code>true</code> if this is a star column (width == "*")
   */
  public boolean isStar ()
  {
    return CHTMLAttributeValues.STAR.equals (m_sWidth);
  }

  /**
   * @return The width definition or <code>null</code> if no width is present
   */
  @Nullable
  public String getWidth ()
  {
    return m_sWidth;
  }

  @Nonnull
  public HCCol setWidth (@Nonnegative final int nWidth)
  {
    ValueEnforcer.isGE0 (nWidth, "Width");
    return setWidth (Integer.toString (nWidth));
  }

  @Nonnull
  public HCCol setWidth (@Nullable final String sWidth)
  {
    m_sWidth = sWidth;
    return this;
  }

  @CheckForSigned
  public int getSpan ()
  {
    return m_nSpan;
  }

  @Nonnull
  public HCCol setSpan (final int nSpan)
  {
    m_nSpan = nSpan;
    return this;
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (StringHelper.hasText (m_sWidth))
      aElement.setAttribute (CHTMLAttributes.WIDTH, m_sWidth);
    if (m_nSpan > 0)
      aElement.setAttribute (CHTMLAttributes.SPAN, m_nSpan);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("width", m_sWidth)
                            .append ("span", m_nSpan)
                            .toString ();
  }

  /**
   * @return A new "star" column (<code>&lt;col width="*" /&gt;</code>). Never
   *         <code>null</code>.
   */
  @Nonnull
  public static HCCol star ()
  {
    return new HCCol ().setWidth (CHTMLAttributeValues.STAR);
  }

  /**
   * Create a new column with a certain percentage.
   *
   * @param nPerc
   *        The percentage to be used. Should ideally be between 0 and 100.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static HCCol perc (@Nonnegative final int nPerc)
  {
    return new HCCol ().setWidth (ECSSUnit.perc (nPerc));
  }

  /**
   * Create a new column with an arbitrary width definition.
   *
   * @param sWidth
   *        The width to be used. May be <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static HCCol fromString (@Nullable final String sWidth)
  {
    return new HCCol ().setWidth (sWidth);
  }
}
