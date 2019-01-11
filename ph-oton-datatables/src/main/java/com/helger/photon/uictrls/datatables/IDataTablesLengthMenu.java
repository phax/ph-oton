/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;
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
  @Nonnull
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

  @Nonnull
  JSArray getAsJSArray (@Nonnull Locale aDisplayLocale);
}
