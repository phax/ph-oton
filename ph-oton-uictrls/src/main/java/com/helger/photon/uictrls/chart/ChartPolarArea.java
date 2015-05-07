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

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.state.ETriState;
import com.helger.html.js.builder.JSAssocArray;

/**
 * PolarArea Chart
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class ChartPolarArea extends AbstractChartSimple <ChartPolarArea>
{
  // options
  private ETriState m_eAnimateRotate = ETriState.UNDEFINED;

  public ChartPolarArea ()
  {}

  @Nonnull
  public ChartPolarArea setAnimateRotate (final boolean bAnimateRotate)
  {
    m_eAnimateRotate = ETriState.valueOf (bAnimateRotate);
    return this;
  }

  @Nonnull
  @Nonempty
  public final String getJSMethodName ()
  {
    return "PolarArea";
  }

  @Override
  @Nonnull
  @ReturnsMutableCopy
  public final JSAssocArray getJSOptions ()
  {
    final JSAssocArray aOptions = super.getJSOptions ();
    if (m_eAnimateRotate.isDefined ())
      aOptions.add ("animateRotate", m_eAnimateRotate.getAsBooleanValue (true));
    return aOptions;
  }
}
