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
package com.helger.photon.uictrls.chart.v4;

import java.math.BigDecimal;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.mutable.MutableBigDecimal;
import com.helger.commons.string.StringHelper;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;

/**
 * Single data set for a complex chart.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class ChartDataSet
{
  private String m_sLabel;
  private JSArray m_aData;
  private String m_sBorderWidth;
  private String m_sBorderColor;
  private IJSExpression m_aFill;
  private String m_sTension;
  private final ICommonsOrderedMap <String, IJSExpression> m_aCustomProps = new CommonsLinkedHashMap <> ();

  public ChartDataSet ()
  {}

  @Nonnull
  public ChartDataSet setLabel (@Nullable final String sLabel)
  {
    m_sLabel = sLabel;
    return this;
  }

  @Nonnull
  public ChartDataSet setData (@Nullable final int... aData)
  {
    m_aData = new JSArray ().addAll (aData);
    return this;
  }

  @Nonnull
  public ChartDataSet setData (@Nullable final double... aData)
  {
    m_aData = new JSArray ().addAll (aData);
    return this;
  }

  @Nonnull
  public ChartDataSet setData (@Nullable final BigDecimal... aData)
  {
    m_aData = new JSArray ().addAll (aData);
    return this;
  }

  @Nonnull
  public ChartDataSet setData (@Nullable final MutableBigDecimal... aData)
  {
    final JSArray arr = new JSArray ();
    if (aData != null)
      for (final MutableBigDecimal aBD : aData)
        arr.add (aBD.getAsBigDecimal ());
    m_aData = arr;
    return this;
  }

  @Nonnull
  public ChartDataSet setBorderWidth (final int nBorderWidth)
  {
    return setBorderWidth (Integer.toString (nBorderWidth));
  }

  @Nonnull
  public ChartDataSet setBorderWidth (@Nullable final String sBorderWidth)
  {
    m_sBorderWidth = sBorderWidth;
    return this;
  }

  @Nonnull
  public ChartDataSet setBorderColor (@Nullable final String sBorderColor)
  {
    m_sBorderColor = sBorderColor;
    return this;
  }

  @Nonnull
  public ChartDataSet setFill (final boolean bFill)
  {
    m_aFill = JSExpr.lit (bFill);
    return this;
  }

  @Nonnull
  public ChartDataSet setFill (final IJSExpression aFill)
  {
    m_aFill = aFill;
    return this;
  }

  @Nonnull
  public ChartDataSet setTension (final int nBorderWidth)
  {
    return setTension (Integer.toString (nBorderWidth));
  }

  @Nonnull
  public ChartDataSet setTension (final double dBorderWidth)
  {
    return setTension (Double.toString (dBorderWidth));
  }

  @Nonnull
  public ChartDataSet setTension (@Nullable final String sTension)
  {
    m_sTension = sTension;
    return this;
  }

  @Nonnull
  public ChartDataSet setCustomProperty (@Nonnull @Nonempty final String sKey, @Nullable final IJSExpression aValue)
  {
    ValueEnforcer.notEmpty (sKey, "Key");
    if (aValue == null)
      m_aCustomProps.remove (sKey);
    else
      m_aCustomProps.put (sKey, aValue);
    return this;
  }

  @Nonnull
  public JSAssocArray getJSData ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    if (StringHelper.hasText (m_sLabel))
      ret.add ("label", m_sLabel);
    if (m_aData != null)
      ret.add ("data", m_aData);
    if (StringHelper.hasText (m_sBorderWidth))
      ret.add ("borderWidth", m_sBorderWidth);
    if (StringHelper.hasText (m_sBorderColor))
      ret.add ("borderColor", m_sBorderColor);
    if (m_aFill != null)
      ret.add ("fill", m_aFill);
    if (StringHelper.hasText (m_sTension))
      ret.add ("tension", m_sTension);
    if (m_aCustomProps.isNotEmpty ())
      ret.addAll (m_aCustomProps);
    return ret;
  }
}
