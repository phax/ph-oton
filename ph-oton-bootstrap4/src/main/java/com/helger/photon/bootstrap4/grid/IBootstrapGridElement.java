/*
 * Copyright (C) 2018-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.grid;

import javax.annotation.Nonnull;

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrap;

/**
 * Bootstrap3 grid element
 *
 * @author Philip Helger
 */
public interface IBootstrapGridElement extends ICSSClassProvider
{
  int PARTS_NONE = -1;
  int PARTS_AUTO = -2;
  int PARTS_EVENLY = -3;
  int MIN = PARTS_EVENLY;

  /**
   * @return The grid type. Never <code>null</code>.
   */
  @Nonnull
  EBootstrapGridType getGridType ();

  /**
   * @return When in the range 1-12, the parts to span.
   */
  int getParts ();

  /**
   * @return <code>true</code> if this is element for part 12
   */
  boolean isMax ();

  static int getRight (final int nLeft)
  {
    if (nLeft < 0)
    {
      // Keep the special part
      return nLeft;
    }
    if (nLeft == CBootstrap.GRID_SYSTEM_MAX)
    {
      // Avoid returning 0
      return CBootstrap.GRID_SYSTEM_MAX;
    }
    return CBootstrap.GRID_SYSTEM_MAX - nLeft;
  }
}
