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

import java.math.BigDecimal;

import com.helger.annotation.Nonempty;
import com.helger.annotation.OverridingMethodsMustInvokeSuper;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.OverrideOnDemand;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.numeric.mutable.MutableBigDecimal;
import com.helger.base.string.StringHelper;
import com.helger.base.traits.IGenericImplTrait;
import com.helger.collection.commons.CommonsLinkedHashMap;
import com.helger.collection.commons.ICommonsOrderedMap;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Single data set for a complex chart.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
@NotThreadSafe
public class AbstractChartDataSet <IMPLTYPE extends AbstractChartDataSet <IMPLTYPE>> implements
                                  IGenericImplTrait <IMPLTYPE>
{
  private String m_sLabel;
  private JSArray m_aData;
  private final ICommonsOrderedMap <String, IJSExpression> m_aCustomProps = new CommonsLinkedHashMap <> ();

  public AbstractChartDataSet ()
  {}

  @Nullable
  protected static final IJSExpression _toExpr (@Nullable final String s)
  {
    return StringHelper.hasText (s) ? JSExpr.lit (s) : null;
  }

  @Nullable
  protected static final JSArray _toExpr (@Nullable final String... a)
  {
    return a == null ? null : new JSArray ().addAll (a);
  }

  @Nonnull
  public IMPLTYPE setLabel (@Nullable final String sLabel)
  {
    m_sLabel = sLabel;
    return thisAsT ();
  }

  @Nonnull
  public IMPLTYPE setData (@Nullable final int... aData)
  {
    m_aData = new JSArray ().addAll (aData);
    return thisAsT ();
  }

  @Nonnull
  public IMPLTYPE setData (@Nullable final double... aData)
  {
    m_aData = new JSArray ().addAll (aData);
    return thisAsT ();
  }

  @Nonnull
  public IMPLTYPE setData (@Nullable final BigDecimal... aData)
  {
    m_aData = new JSArray ().addAll (aData);
    return thisAsT ();
  }

  @Nonnull
  public IMPLTYPE setData (@Nullable final MutableBigDecimal... aData)
  {
    final JSArray arr = new JSArray ();
    if (aData != null)
      for (final MutableBigDecimal aBD : aData)
        arr.add (aBD.getAsBigDecimal ());
    m_aData = arr;
    return thisAsT ();
  }

  @Nonnull
  public IMPLTYPE setCustomProperty (@Nonnull @Nonempty final String sKey, @Nullable final String sValue)
  {
    return setCustomProperty (sKey, _toExpr (sValue));
  }

  @Nonnull
  public IMPLTYPE setCustomProperty (@Nonnull @Nonempty final String sKey, @Nullable final String... aValues)
  {
    return setCustomProperty (sKey, _toExpr (aValues));
  }

  @Nonnull
  public IMPLTYPE setCustomProperty (@Nonnull @Nonempty final String sKey, @Nullable final IJSExpression aValue)
  {
    ValueEnforcer.notEmpty (sKey, "Key");
    if (aValue == null)
      m_aCustomProps.remove (sKey);
    else
      m_aCustomProps.put (sKey, aValue);
    return thisAsT ();
  }

  @Nonnull
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  public JSAssocArray getJSData ()
  {
    return getJSData (m_aData);
  }

  @Nonnull
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  public JSAssocArray getJSData (@Nullable final IJSExpression aDatasetData)
  {
    final JSAssocArray ret = new JSAssocArray ();
    if (StringHelper.hasText (m_sLabel))
      ret.add ("label", m_sLabel);
    if (aDatasetData != null)
      ret.add ("data", aDatasetData);
    if (m_aCustomProps.isNotEmpty ())
      ret.addAll (m_aCustomProps);
    return ret;
  }
}
