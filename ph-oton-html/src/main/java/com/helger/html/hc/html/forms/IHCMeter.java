/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.forms;

import javax.annotation.Nonnull;

import com.helger.html.annotation.SinceHTML5;
import com.helger.html.hc.html.IHCElementWithChildren;

/**
 * Interface for METERs
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
@SinceHTML5
public interface IHCMeter <IMPLTYPE extends IHCMeter <IMPLTYPE>> extends IHCElementWithChildren <IMPLTYPE>
{
  double getValue ();

  @Nonnull
  IMPLTYPE setValue (double dValue);

  double getMin ();

  @Nonnull
  IMPLTYPE setMin (double dMin);

  double getMax ();

  @Nonnull
  IMPLTYPE setMax (double dMax);

  double getLow ();

  @Nonnull
  IMPLTYPE setLow (double dLow);

  double getHigh ();

  @Nonnull
  IMPLTYPE setHigh (double dHigh);

  double getOptimum ();

  @Nonnull
  IMPLTYPE setOptimum (double dOptimum);
}
