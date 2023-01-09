/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.chart.v1;

import java.math.BigDecimal;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.mutable.MutableBigDecimal;
import com.helger.commons.string.StringHelper;
import com.helger.commons.traits.IGenericImplTrait;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;

/**
 * Base chart for Pie, Doughnut and PolarArea
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Real implementation type
 */
@NotThreadSafe
public abstract class AbstractChartSimple <IMPLTYPE extends AbstractChartSimple <IMPLTYPE>> implements IChart, IGenericImplTrait <IMPLTYPE>
{
  /**
   * Single data set for a simple chart.
   *
   * @author Philip Helger
   */
  @NotThreadSafe
  public static class DataSet
  {
    private String m_sLabel;
    private IJSExpression m_aValue;
    private String m_sColor;
    private String m_sHighlight;

    public DataSet ()
    {}

    @Nonnull
    public DataSet setLabel (@Nullable final String sLabel)
    {
      m_sLabel = sLabel;
      return this;
    }

    @Nonnull
    public DataSet setValue (@Nullable final IJSExpression aValue)
    {
      m_aValue = aValue;
      return this;
    }

    @Nonnull
    public DataSet setValue (@Nonnull final String sValue)
    {
      return setValue (JSExpr.lit (sValue));
    }

    @Nonnull
    public DataSet setValue (final int nValue)
    {
      return setValue (JSExpr.lit (nValue));
    }

    @Nonnull
    public DataSet setValue (final double dValue)
    {
      return setValue (JSExpr.lit (dValue));
    }

    @Nonnull
    public DataSet setValue (@Nullable final BigDecimal aValue)
    {
      return setValue (aValue == null ? JSExpr.NULL : JSExpr.lit (aValue));
    }

    @Nonnull
    public DataSet setValue (@Nullable final MutableBigDecimal aValue)
    {
      return setValue (aValue == null ? null : aValue.getAsBigDecimal ());
    }

    @Nonnull
    public DataSet setColor (@Nullable final String sColor)
    {
      m_sColor = sColor;
      return this;
    }

    @Nonnull
    public DataSet setHighlight (@Nullable final String sHighlight)
    {
      m_sHighlight = sHighlight;
      return this;
    }

    @Nonnull
    public JSAssocArray getJSData ()
    {
      final JSAssocArray ret = new JSAssocArray ();
      if (StringHelper.hasText (m_sLabel))
        ret.add ("label", m_sLabel);
      if (m_aValue != null)
        ret.add ("value", m_aValue);
      if (StringHelper.hasText (m_sColor))
        ret.add ("color", m_sColor);
      if (StringHelper.hasText (m_sHighlight))
        ret.add ("highlight", m_sHighlight);
      return ret;
    }
  }

  private final ICommonsList <DataSet> m_aDataSets = new CommonsArrayList <> ();

  public AbstractChartSimple ()
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
  public IMPLTYPE addDataSet (@Nonnull final DataSet aDataSet)
  {
    ValueEnforcer.notNull (aDataSet, "DataSet");
    m_aDataSets.add (aDataSet);
    return thisAsT ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public final IJSExpression getJSData ()
  {
    final JSArray aJSDataSets = new JSArray ();
    for (final DataSet aDataSet : m_aDataSets)
      aJSDataSets.add (aDataSet.getJSData ());
    return getJSData (aJSDataSets);
  }

  @Nonnull
  @ReturnsMutableCopy
  public final IJSExpression getJSData (@Nonnull final IJSExpression aJSDataSets)
  {
    return aJSDataSets;
  }

  @Nonnull
  @ReturnsMutableCopy
  @OverridingMethodsMustInvokeSuper
  public JSAssocArray getJSOptions ()
  {
    return new JSAssocArray ();
  }
}
