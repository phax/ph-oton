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

import com.helger.annotation.Nonempty;
import com.helger.annotation.Nonnegative;
import com.helger.annotation.OverridingMethodsMustInvokeSuper;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.collection.CollectionHelper;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;

/**
 * Base chart for charts with labels
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Real implementation type
 * @param <DSTYPE>
 *        The dataset implementation type
 */
@NotThreadSafe
public abstract class AbstractChartV4WithLabels <IMPLTYPE extends AbstractChartV4WithLabels <IMPLTYPE, DSTYPE>, DSTYPE extends AbstractChartDataSet <?>>
                                                extends
                                                AbstractChartV4 <IMPLTYPE>
{
  private ICommonsList <String> m_aLabels;
  private final ICommonsList <DSTYPE> m_aDataSets = new CommonsArrayList <> ();

  public AbstractChartV4WithLabels (@NonNull @Nonempty final String sType)
  {
    super (sType);
  }

  public boolean hasLabels ()
  {
    return CollectionHelper.isNotEmpty (m_aLabels);
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <String> getAllLabels ()
  {
    return new CommonsArrayList <> (m_aLabels);
  }

  @NonNull
  public IMPLTYPE setLabels (@Nullable final String... aLabels)
  {
    m_aLabels = new CommonsArrayList <> (aLabels);
    return thisAsT ();
  }

  @NonNull
  public IMPLTYPE setLabels (@Nullable final Iterable <String> aLabels)
  {
    m_aLabels = new CommonsArrayList <> (aLabels);
    return thisAsT ();
  }

  @NonNull
  public JSArray getDataLabelsAsArray ()
  {
    final JSArray ret = new JSArray ();
    if (m_aLabels != null)
      ret.addAll (m_aLabels);
    return ret;
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

  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <DSTYPE> getAllDataSets ()
  {
    return m_aDataSets.getClone ();
  }

  @NonNull
  public IMPLTYPE addDataSet (@NonNull final DSTYPE aDataSet)
  {
    ValueEnforcer.notNull (aDataSet, "DataSet");
    m_aDataSets.add (aDataSet);
    return thisAsT ();
  }

  @NonNull
  @ReturnsMutableCopy
  public final JSArray getDataDatasetsAsArray ()
  {
    final JSArray aJSDataSets = new JSArray ();
    for (final DSTYPE aDataSet : m_aDataSets)
      aJSDataSets.add (aDataSet.getJSData ());
    return aJSDataSets;
  }

  @NonNull
  @ReturnsMutableCopy
  public final JSAssocArray getJSData ()
  {
    return getJSData (getDataDatasetsAsArray ());
  }

  @NonNull
  @ReturnsMutableCopy
  public final JSAssocArray getJSData (@Nullable final IJSExpression aDatasetData)
  {
    final JSAssocArray aData = new JSAssocArray ();
    aData.add ("labels", getDataLabelsAsArray ());
    aData.add ("datasets", aDatasetData);
    return aData;
  }

  @Override
  @NonNull
  @ReturnsMutableCopy
  @OverridingMethodsMustInvokeSuper
  public JSAssocArray getJSOptions ()
  {
    return super.getJSOptions ();
  }
}
