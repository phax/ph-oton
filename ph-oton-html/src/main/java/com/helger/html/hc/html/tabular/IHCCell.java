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
package com.helger.html.hc.html.tabular;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.html.IHCElementWithChildren;

/**
 * Base interface for a table cell.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface IHCCell <IMPLTYPE extends IHCCell <IMPLTYPE>> extends IHCElementWithChildren <IMPLTYPE>
{
  /**
   * @return The owning parent row.
   */
  @Nullable
  HCRow getParentRow ();

  /**
   * @return Current column spanning. Defaults to 1.
   */
  @Nonnegative
  int getColspan ();

  @Nonnull
  IMPLTYPE setColspan (@Nonnegative int nColspan);

  /**
   * @return Current row spanning. Defaults to 1.
   */
  @Nonnegative
  int getRowspan ();

  @Nonnull
  IMPLTYPE setRowspan (@Nonnegative int nRowspan);
}
