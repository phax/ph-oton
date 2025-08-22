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
package com.helger.photon.uictrls.chart.v4;

import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Bar chart data set
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class ChartDataSetBar extends AbstractChartDataSet <ChartDataSetBar>
{
  private IJSExpression m_aBackgroundColor;
  private IJSExpression m_aBorderColor;
  private IJSExpression m_aBorderWidth;

  public ChartDataSetBar ()
  {}

  @Nonnull
  public ChartDataSetBar setBackgroundColor (@Nullable final String sBackgroundColor)
  {
    return setBackgroundColor (_toExpr (sBackgroundColor));
  }

  @Nonnull
  public ChartDataSetBar setBackgroundColor (@Nullable final String... aBackgroundColors)
  {
    return setBackgroundColor (_toExpr (aBackgroundColors));
  }

  @Nonnull
  public ChartDataSetBar setBackgroundColor (@Nullable final IJSExpression aBackgroundColor)
  {
    m_aBackgroundColor = aBackgroundColor;
    return this;
  }

  @Nonnull
  public ChartDataSetBar setBorderColor (@Nullable final String sBorderColor)
  {
    return setBorderColor (_toExpr (sBorderColor));
  }

  @Nonnull
  public ChartDataSetBar setBorderColor (@Nullable final IJSExpression aBorderColor)
  {
    m_aBorderColor = aBorderColor;
    return this;
  }

  @Nonnull
  public ChartDataSetBar setBorderColor (@Nullable final String... aBorderColors)
  {
    return setBorderColor (_toExpr (aBorderColors));
  }

  @Nonnull
  public ChartDataSetBar setBorderWidth (final int nBorderWidth)
  {
    return setBorderWidth (Integer.toString (nBorderWidth));
  }

  @Nonnull
  public ChartDataSetBar setBorderWidth (@Nullable final String sBorderWidth)
  {
    return setBorderWidth (_toExpr (sBorderWidth));
  }

  @Nonnull
  public ChartDataSetBar setBorderWidth (@Nullable final IJSExpression aBorderWidth)
  {
    m_aBorderWidth = aBorderWidth;
    return this;
  }

  @Override
  @Nonnull
  public JSAssocArray getJSData ()
  {
    final JSAssocArray ret = super.getJSData ();
    if (m_aBackgroundColor != null)
      ret.add ("backgroundColor", m_aBackgroundColor);
    if (m_aBorderColor != null)
      ret.add ("borderColor", m_aBorderColor);
    if (m_aBorderWidth != null)
      ret.add ("borderWidth", m_aBorderWidth);
    return ret;
  }
}
