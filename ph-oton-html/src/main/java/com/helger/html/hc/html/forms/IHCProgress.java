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
package com.helger.html.hc.html.forms;

import javax.annotation.Nonnull;

import com.helger.html.annotation.SinceHTML5;
import com.helger.html.hc.html.IHCElementWithChildren;

/**
 * Interface for METERs
 *
 * @author Philip Helger
 * @param <THISTYPE>
 *        Implementation type
 */
@SinceHTML5
public interface IHCProgress <THISTYPE extends IHCProgress <THISTYPE>> extends IHCElementWithChildren <THISTYPE>
{
  double getValue ();

  @Nonnull
  THISTYPE setValue (double dValue);

  double getMax ();

  @Nonnull
  THISTYPE setMax (double dMax);
}