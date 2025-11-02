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

import org.jspecify.annotations.NonNull;

import com.helger.annotation.Nonnegative;
import com.helger.html.hc.html.IHCElementWithInternalChildren;

/**
 * Interface for thead, tbody and tfoot. It encapsulates all table elements
 * having rows.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface IHCTablePart <IMPLTYPE extends IHCTablePart <IMPLTYPE>> extends IHCElementWithInternalChildren <IMPLTYPE, HCRow>
{
  /**
   * @return <code>true</code> if this is a header or footer part,
   *         <code>false</code> if this is the body part.
   */
  boolean isHeaderOrFooter ();

  @NonNull
  HCRow addRow ();

  /**
   * Add a row at the specified index.
   *
   * @param nIndex
   *        The index to add the row at. Must be &ge; 0.
   * @return The created row and never <code>null</code>.
   */
  @NonNull
  HCRow addRowAt (@Nonnegative int nIndex);
}
