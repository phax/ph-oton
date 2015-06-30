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
package com.helger.photon.uictrls.datatables.ajax;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.compare.ReverseComparator;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.comparator.ComparatorDTString;

/**
 * The current sort state of a {@link DataTables} object.
 *
 * @author Philip Helger
 */
final class DataTablesServerSortState implements Serializable
{
  private final RequestDataSortColumn [] m_aSortState;

  DataTablesServerSortState (@Nonnull final DataTablesServerData aServerData, @Nonnull final Locale aDisplayLocale)
  {
    this (aServerData, new RequestDataSortColumn [0], aDisplayLocale);
  }

  DataTablesServerSortState (@Nonnull final DataTablesServerData aServerData,
                             @Nonnull final RequestDataSortColumn [] aSortCols,
                             @Nonnull final Locale aDisplayLocale)
  {
    ValueEnforcer.notNull (aServerData, "ServerData");
    ValueEnforcer.notNull (aSortCols, "SortColumns");
    ValueEnforcer.notNull (aDisplayLocale, "DisplayLocale");

    m_aSortState = aSortCols;

    // Extract the comparators for all sort columns
    for (final RequestDataSortColumn aSortColumn : aSortCols)
    {
      // Get the configured comparator
      Comparator <String> aStringComp = aServerData.getColumnComparator (aSortColumn.getColumnIndex ());
      if (aStringComp == null)
      {
        // Default to String comparator
        aStringComp = new ComparatorDTString (aDisplayLocale);
      }
      if (aSortColumn.getSortDirectionOrDefault ().isDescending ())
      {
        // Reverse the comparator
        aStringComp = ReverseComparator.create (aStringComp);
      }
      aSortColumn.setComparator (aStringComp);
    }
  }

  /**
   * @return Number of columns to sort on
   */
  @Nonnegative
  public int getSortingCols ()
  {
    return m_aSortState.length;
  }

  @Nonnull
  @ReturnsMutableCopy
  public RequestDataSortColumn [] getSortCols ()
  {
    return ArrayHelper.getCopy (m_aSortState);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!(o instanceof DataTablesServerSortState))
      return false;
    final DataTablesServerSortState rhs = (DataTablesServerSortState) o;
    return Arrays.equals (m_aSortState, rhs.m_aSortState);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aSortState).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("sortCols", m_aSortState).toString ();
  }
}
