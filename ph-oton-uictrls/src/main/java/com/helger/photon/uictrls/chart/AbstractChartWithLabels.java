/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.lang.GenericReflection;
import com.helger.html.js.builder.JSArray;
import com.helger.html.js.builder.JSAssocArray;

/**
 * Base chart for Line, Radar and Bar
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Real implementation type
 */
@NotThreadSafe
public abstract class AbstractChartWithLabels <IMPLTYPE extends AbstractChartWithLabels <IMPLTYPE>> implements IChart
{
  private List <String> m_aLabels;

  public AbstractChartWithLabels ()
  {}

  @Nonnull
  protected final IMPLTYPE thisAsT ()
  {
    // Avoid the unchecked cast warning in all places
    return GenericReflection.<AbstractChartWithLabels <IMPLTYPE>, IMPLTYPE> uncheckedCast (this);
  }

  public boolean hasLabels ()
  {
    return CollectionHelper.isNotEmpty (m_aLabels);
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <String> getAllLabels ()
  {
    return CollectionHelper.newList (m_aLabels);
  }

  @Nonnull
  public IMPLTYPE setLabels (@Nullable final String... aLabels)
  {
    m_aLabels = CollectionHelper.newList (aLabels);
    return thisAsT ();
  }

  @Nonnull
  public JSArray getLabelsAsArray ()
  {
    final JSArray ret = new JSArray ();
    if (m_aLabels != null)
      ret.addAll (m_aLabels);
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  @OverridingMethodsMustInvokeSuper
  public JSAssocArray getJSOptions ()
  {
    final JSAssocArray aOptions = new JSAssocArray ();
    return aOptions;
  }
}
