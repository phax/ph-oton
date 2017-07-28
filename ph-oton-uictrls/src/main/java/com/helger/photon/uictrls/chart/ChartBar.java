/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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

import java.math.BigDecimal;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.mutable.MutableBigDecimal;
import com.helger.commons.string.StringHelper;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;

/**
 * Bar Chart
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class ChartBar extends AbstractChartWithLabels <ChartBar>
{
  /**
   * Single data set for a complex chart.
   *
   * @author Philip Helger
   */
  @NotThreadSafe
  public static class DataSet
  {
    private String m_sLabel;
    private String m_sFillColor;
    private String m_sStrokeColor;
    private String m_sHighlightFill;
    private String m_sHighlightStroke;
    private JSArray m_aData;

    public DataSet ()
    {}

    @Nonnull
    public DataSet setLabel (@Nullable final String sLabel)
    {
      m_sLabel = sLabel;
      return this;
    }

    @Nonnull
    public DataSet setFillColor (@Nullable final String sFillColor)
    {
      m_sFillColor = sFillColor;
      return this;
    }

    @Nonnull
    public DataSet setStrokeColor (@Nullable final String sStrokeColor)
    {
      m_sStrokeColor = sStrokeColor;
      return this;
    }

    @Nonnull
    public DataSet setHighlightFill (@Nullable final String sHighlightFill)
    {
      m_sHighlightFill = sHighlightFill;
      return this;
    }

    @Nonnull
    public DataSet setHighlightStroke (@Nullable final String sHighlightStroke)
    {
      m_sHighlightStroke = sHighlightStroke;
      return this;
    }

    @Nonnull
    public DataSet setData (@Nullable final int... aData)
    {
      m_aData = new JSArray ().addAll (aData);
      return this;
    }

    @Nonnull
    public DataSet setData (@Nullable final double... aData)
    {
      m_aData = new JSArray ().addAll (aData);
      return this;
    }

    @Nonnull
    public DataSet setData (@Nullable final BigDecimal... aData)
    {
      m_aData = new JSArray ().addAll (aData);
      return this;
    }

    @Nonnull
    public DataSet setData (@Nullable final MutableBigDecimal... aData)
    {
      final JSArray arr = new JSArray ();
      if (aData != null)
        for (final MutableBigDecimal aBD : aData)
          arr.add (aBD.getAsBigDecimal ());
      m_aData = arr;
      return this;
    }

    @Nonnull
    public JSAssocArray getJSData ()
    {
      final JSAssocArray ret = new JSAssocArray ();
      if (StringHelper.hasText (m_sLabel))
        ret.add ("label", m_sLabel);
      if (StringHelper.hasText (m_sFillColor))
        ret.add ("fillColor", m_sFillColor);
      if (StringHelper.hasText (m_sStrokeColor))
        ret.add ("strokeColor", m_sStrokeColor);
      if (StringHelper.hasText (m_sHighlightFill))
        ret.add ("highlightFill", m_sHighlightFill);
      if (StringHelper.hasText (m_sHighlightStroke))
        ret.add ("highlightStroke", m_sHighlightStroke);
      if (m_aData != null)
        ret.add ("data", m_aData);
      return ret;
    }
  }

  private final ICommonsList <DataSet> m_aDataSets = new CommonsArrayList <> ();

  public ChartBar ()
  {}

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
  public ICommonsList <DataSet> getAllDataSets ()
  {
    return m_aDataSets.getClone ();
  }

  @Nonnull
  public ChartBar addDataSet (@Nonnull final DataSet aDataSet)
  {
    ValueEnforcer.notNull (aDataSet, "DataSet");
    m_aDataSets.add (aDataSet);
    return this;
  }

  @Nonnull
  @Nonempty
  public final String getJSMethodName ()
  {
    return "Bar";
  }

  @Nonnull
  @ReturnsMutableCopy
  public final JSAssocArray getJSData ()
  {
    final JSArray aJSDataSets = new JSArray ();
    for (final DataSet aDataSet : m_aDataSets)
      aJSDataSets.add (aDataSet.getJSData ());
    return getJSData (aJSDataSets);
  }

  @Nonnull
  @ReturnsMutableCopy
  public final JSAssocArray getJSData (@Nonnull final IJSExpression aJSDataSets)
  {
    final JSAssocArray aData = new JSAssocArray ();
    // Labels is a mandatory element to print always
    aData.add ("labels", getLabelsAsArray ());

    aData.add ("datasets", aJSDataSets);
    return aData;
  }

  @Override
  @Nonnull
  @ReturnsMutableCopy
  public final JSAssocArray getJSOptions ()
  {
    final JSAssocArray aOptions = super.getJSOptions ();
    return aOptions;
  }
}
