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
package com.helger.photon.uictrls.datatables;

import java.util.Locale;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonnegative;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.collection.commons.ICommonsList;
import com.helger.html.jscode.JSArray;

/**
 * Read only interface of {@link DataTablesLengthMenu}.
 *
 * @author Philip Helger
 * @since 8.1.2
 */
public interface IDataTablesLengthMenu
{
  /**
   * @return <code>true</code> if no entry is contained, <code>false</code>
   *         otherwise.
   */
  boolean isEmpty ();

  /**
   * @return The number of contained entries. Always &ge; 0.
   */
  @Nonnegative
  int getItemCount ();

  /**
   * @return A copy of all contained items. Never <code>null</code> but maybe
   *         empty.
   */
  @NonNull
  @ReturnsMutableCopy
  ICommonsList <DataTablesLengthMenuItem> getAllItems ();

  /**
   * Get the item at the specified index.
   *
   * @param nIndex
   *        The 0-based index to query.
   * @return <code>null</code> if no such index exists.
   */
  @Nullable
  DataTablesLengthMenuItem getItemAtIndex (@Nonnegative int nIndex);

  /**
   * Get the item with the shortest page size.
   *
   * @return <code>null</code> if no item is present.
   */
  @Nullable
  DataTablesLengthMenuItem getItemWithLeastItemCount ();

  @NonNull
  JSArray getAsJSArray (@NonNull Locale aDisplayLocale);
}
