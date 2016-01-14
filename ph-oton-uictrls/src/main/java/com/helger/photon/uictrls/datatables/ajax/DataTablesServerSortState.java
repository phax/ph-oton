/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableObject;
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
  private final List <DTSSRequestDataOrderColumn> m_aOrderColumns;

  DataTablesServerSortState (@Nonnull final DataTablesServerData aServerData, @Nonnull final Locale aDisplayLocale)
  {
    this (aServerData, new ArrayList <DTSSRequestDataOrderColumn> (0), aDisplayLocale);
  }

  public DataTablesServerSortState (@Nonnull final DataTablesServerData aServerData,
                                    @Nonnull final List <DTSSRequestDataOrderColumn> aOrderColumns,
                                    @Nonnull final Locale aDisplayLocale)
  {
    ValueEnforcer.notNull (aServerData, "ServerData");
    ValueEnforcer.notNull (aOrderColumns, "OrderColumns");
    ValueEnforcer.notNull (aDisplayLocale, "DisplayLocale");

    m_aOrderColumns = aOrderColumns;

    // Extract the comparators for all sort columns
    for (final DTSSRequestDataOrderColumn aSortColumn : aOrderColumns)
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
      aSortColumn.setServerSideComparator (aStringComp);
    }
  }

  /**
   * @return Number of columns to sort on
   */
  @Nonnegative
  public int getSortingCols ()
  {
    return m_aOrderColumns.size ();
  }

  @Nonnull
  @ReturnsMutableObject ("speed")
  public List <DTSSRequestDataOrderColumn> directGetAllOrderColumns ()
  {
    return m_aOrderColumns;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final DataTablesServerSortState rhs = (DataTablesServerSortState) o;
    return m_aOrderColumns.equals (rhs.m_aOrderColumns);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aOrderColumns).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("OrderColumns", m_aOrderColumns).toString ();
  }
}
