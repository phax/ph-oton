/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;

/**
 * Bar chart data set
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class ChartDataSetLine extends AbstractChartDataSet <ChartDataSetLine>
{
  private IJSExpression m_aBackgroundColor;
  private IJSExpression m_aBorderColor;
  private IJSExpression m_aBorderWidth;
  private IJSExpression m_aFill;
  private IJSExpression m_aTension;

  @NonNull
  public ChartDataSetLine setBackgroundColor (@Nullable final String sBackgroundColor)
  {
    return setBackgroundColor (_toExpr (sBackgroundColor));
  }

  @NonNull
  public ChartDataSetLine setBackgroundColor (@Nullable final String... aBackgroundColors)
  {
    return setBackgroundColor (_toExpr (aBackgroundColors));
  }

  @NonNull
  public ChartDataSetLine setBackgroundColor (@Nullable final IJSExpression aBackgroundColor)
  {
    m_aBackgroundColor = aBackgroundColor;
    return this;
  }

  @NonNull
  public ChartDataSetLine setBorderColor (@Nullable final String sBorderColor)
  {
    return setBorderColor (_toExpr (sBorderColor));
  }

  @NonNull
  public ChartDataSetLine setBorderColor (@Nullable final String... aBorderColors)
  {
    return setBorderColor (_toExpr (aBorderColors));
  }

  @NonNull
  public ChartDataSetLine setBorderColor (@Nullable final IJSExpression aBorderColor)
  {
    m_aBorderColor = aBorderColor;
    return this;
  }

  @NonNull
  public ChartDataSetLine setBorderWidth (final int nBorderWidth)
  {
    return setBorderWidth (Integer.toString (nBorderWidth));
  }

  @NonNull
  public ChartDataSetLine setBorderWidth (@Nullable final String sBorderWidth)
  {
    return setBorderWidth (_toExpr (sBorderWidth));
  }

  @NonNull
  public ChartDataSetLine setBorderWidth (@Nullable final IJSExpression aBorderWidth)
  {
    m_aBorderWidth = aBorderWidth;
    return this;
  }

  @NonNull
  public ChartDataSetLine setFill (final boolean bFill)
  {
    return setFill (JSExpr.lit (bFill));
  }

  @NonNull
  public ChartDataSetLine setFill (final String sFill)
  {
    return setFill (_toExpr (sFill));
  }

  @NonNull
  public ChartDataSetLine setFill (final IJSExpression aFill)
  {
    m_aFill = aFill;
    return this;
  }

  @NonNull
  public ChartDataSetLine setTension (final int nBorderWidth)
  {
    return setTension (Integer.toString (nBorderWidth));
  }

  @NonNull
  public ChartDataSetLine setTension (final double dBorderWidth)
  {
    return setTension (Double.toString (dBorderWidth));
  }

  @NonNull
  public ChartDataSetLine setTension (@Nullable final String sTension)
  {
    return setTension (_toExpr (sTension));
  }

  @NonNull
  public ChartDataSetLine setTension (@Nullable final IJSExpression aTension)
  {
    m_aTension = aTension;
    return this;
  }

  @Override
  @NonNull
  public JSAssocArray getJSData ()
  {
    final JSAssocArray ret = super.getJSData ();
    if (m_aBackgroundColor != null)
      ret.add ("backgroundColor", m_aBackgroundColor);
    if (m_aBorderColor != null)
      ret.add ("borderColor", m_aBorderColor);
    if (m_aBorderWidth != null)
      ret.add ("borderWidth", m_aBorderWidth);
    if (m_aFill != null)
      ret.add ("fill", m_aFill);
    if (m_aTension != null)
      ret.add ("tension", m_aTension);
    return ret;
  }
}
