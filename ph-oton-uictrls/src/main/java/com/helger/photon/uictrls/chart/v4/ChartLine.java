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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;

/**
 * Line Chart
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class ChartLine extends AbstractChartV4WithLabels <ChartLine>
{
  private final ICommonsList <ChartDataSet> m_aDataSets = new CommonsArrayList <> ();

  public ChartLine ()
  {
    super ("line");
  }

  public boolean hasDataSet ()
  {
    return m_aDataSets.isNotEmpty ();
  }

  @Nonnegative
  public int getDataSetCount ()
  {
    return m_aDataSets.size ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <ChartDataSet> getAllDataSets ()
  {
    return m_aDataSets.getClone ();
  }

  @Nonnull
  public ChartLine addDataSet (@Nonnull final ChartDataSet aDataSet)
  {
    ValueEnforcer.notNull (aDataSet, "DataSet");
    m_aDataSets.add (aDataSet);
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public final JSAssocArray getJSData ()
  {
    final JSArray aJSDataSets = new JSArray ();
    for (final ChartDataSet aDataSet : m_aDataSets)
      aJSDataSets.add (aDataSet.getJSData ());

    return getJSData (aJSDataSets);
  }

  @Nonnull
  @ReturnsMutableCopy
  public final JSAssocArray getJSData (@Nonnull final IJSExpression aJSDataSets)
  {
    final JSAssocArray aData = new JSAssocArray ();
    aData.add ("labels", getLabelsAsArray ());
    aData.add ("datasets", aJSDataSets);
    return aData;
  }

  @Override
  @Nonnull
  @ReturnsMutableCopy
  public JSAssocArray getJSOptions ()
  {
    final JSAssocArray ret = super.getJSOptions ();
    return ret;
  }
}
