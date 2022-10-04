/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;

/**
 * Base class for all chartjs.org charts.
 *
 * @author Philip Helger
 */
public interface IChart
{
  /**
   * @return The JS method name required for invocation.
   */
  @Nonnull
  @Nonempty
  String getJSMethodName ();

  /**
   * @return The JS data array for invocation.
   */
  @Nonnull
  @ReturnsMutableCopy
  IJSExpression getJSData ();

  /**
   * @param aJSDataSets
   *        the data sets to be used. Must be an array.
   * @return The JS data array for invocation.
   */
  @Nonnull
  @ReturnsMutableCopy
  IJSExpression getJSData (@Nonnull IJSExpression aJSDataSets);

  /**
   * @return The JS options of this chart.
   */
  @Nonnull
  @ReturnsMutableCopy
  JSAssocArray getJSOptions ();
}
