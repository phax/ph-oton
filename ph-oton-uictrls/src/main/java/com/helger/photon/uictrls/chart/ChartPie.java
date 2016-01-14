/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.chart;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.state.ETriState;
import com.helger.html.jscode.JSAssocArray;

/**
 * Pie and Doughnut Chart
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class ChartPie extends AbstractChartSimple <ChartPie>
{
  // options
  private ETriState m_eSegmentShowStroke = ETriState.UNDEFINED;
  private double m_dPercentageInnerCutout = 0;
  private ETriState m_eAnimateRotate = ETriState.UNDEFINED;
  private ETriState m_eAnimateScale = ETriState.UNDEFINED;

  public ChartPie ()
  {}

  /**
   * Whether we should show a stroke on each segment
   *
   * @param bSegmentShowStroke
   *        <code>true</code> or <code>false</code>
   * @return this
   */
  @Nonnull
  public ChartPie setSegmentShowStroke (final boolean bSegmentShowStroke)
  {
    m_eSegmentShowStroke = ETriState.valueOf (bSegmentShowStroke);
    return this;
  }

  /**
   * The percentage of the chart that we cut out of the middle. If the passed
   * percentage is &gt; 0 this chart will become a doughnut chart.
   *
   * @param dPercentageInnerCutout
   *        Percentage in the range 0 to 100.
   * @return this
   */
  @Nonnull
  public ChartPie setPercentageInnerCutout (final double dPercentageInnerCutout)
  {
    m_dPercentageInnerCutout = dPercentageInnerCutout;
    return this;
  }

  /**
   * Whether we animate the rotation of the Doughnut
   *
   * @param bAnimateRotate
   *        <code>true</code> or <code>false</code>
   * @return this
   */
  @Nonnull
  public ChartPie setAnimateRotate (final boolean bAnimateRotate)
  {
    m_eAnimateRotate = ETriState.valueOf (bAnimateRotate);
    return this;
  }

  /**
   * Whether we animate scaling the Doughnut from the centre
   *
   * @param bAnimateScale
   *        <code>true</code> or <code>false</code>
   * @return this
   */
  @Nonnull
  public ChartPie setAnimateScale (final boolean bAnimateScale)
  {
    m_eAnimateScale = ETriState.valueOf (bAnimateScale);
    return this;
  }

  @Nonnull
  @Nonempty
  public final String getJSMethodName ()
  {
    return m_dPercentageInnerCutout > 0 ? "Doughnut" : "Pie";
  }

  @Override
  @Nonnull
  @ReturnsMutableCopy
  public final JSAssocArray getJSOptions ()
  {
    final JSAssocArray aOptions = super.getJSOptions ();
    if (m_eSegmentShowStroke.isDefined ())
      aOptions.add ("segmentShowStroke", m_eSegmentShowStroke.getAsBooleanValue (true));
    if (m_dPercentageInnerCutout > 0)
      aOptions.add ("percentageInnerCutout", m_dPercentageInnerCutout);
    if (m_eAnimateRotate.isDefined ())
      aOptions.add ("animateRotate", m_eAnimateRotate.getAsBooleanValue (true));
    if (m_eAnimateScale.isDefined ())
      aOptions.add ("animateScale", m_eAnimateScale.getAsBooleanValue (true));
    return aOptions;
  }
}
