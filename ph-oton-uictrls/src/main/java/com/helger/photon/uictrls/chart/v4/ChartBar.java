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

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.html.jscode.JSAssocArray;

/**
 * Bar Chart
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class ChartBar extends AbstractChartV4WithLabels <ChartBar, ChartDataSetBar>
{
  public ChartBar ()
  {
    super ("bar");
  }

  @Override
  @Nonnull
  @ReturnsMutableCopy
  public JSAssocArray getJSOptions ()
  {
    final JSAssocArray ret = super.getJSOptions ();
    final JSAssocArray aScales = (JSAssocArray) ret.computeIfAbsent ("scales", k -> new JSAssocArray ());
    final JSAssocArray aY = (JSAssocArray) aScales.computeIfAbsent ("y", k -> new JSAssocArray ());
    aY.add ("beginAtZero", true);
    return ret;
  }
}
