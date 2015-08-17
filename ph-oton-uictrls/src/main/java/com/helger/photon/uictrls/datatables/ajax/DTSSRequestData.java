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

import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.string.ToStringGenerator;

/**
 * Encapsulates the request data to a single DataTables AJAX request
 *
 * @author Philip Helger
 */
final class DTSSRequestData
{
  private final int m_nDraw;
  private final int m_nDisplayStart;
  private final int m_nDisplayLength;
  private final DTSSRequestDataSearch m_aSearch;
  private final List <DTSSRequestDataColumn> m_aColumnData;
  private final List <DTSSRequestDataOrderColumn> m_aOrderColumns;

  DTSSRequestData (final int nDraw,
                   final int nDisplayStart,
                   final int nDisplayLength,
                   @Nullable final String sSearchText,
                   final boolean bSearchRegEx,
                   @Nonnull final List <DTSSRequestDataColumn> aColumnData,
                   @Nonnull final List <DTSSRequestDataOrderColumn> aOrderColumns)
  {
    ValueEnforcer.notNull (aColumnData, "ColumnData");
    if (CollectionHelper.containsAnyNullElement (aColumnData))
      throw new IllegalArgumentException ("ColumnData may not contain null elements");
    ValueEnforcer.notNull (aOrderColumns, "OrderColumns");
    m_nDraw = nDraw;
    m_nDisplayStart = nDisplayStart;
    m_nDisplayLength = nDisplayLength;
    m_aSearch = new DTSSRequestDataSearch (sSearchText, bSearchRegEx);
    m_aColumnData = aColumnData;
    m_aOrderColumns = aOrderColumns;
  }

  /**
   * @return Information for DataTables to use for rendering.
   */
  public int getDraw ()
  {
    return m_nDraw;
  }

  /**
   * @return Display start point in the current data set.
   */
  public int getDisplayStart ()
  {
    return m_nDisplayStart;
  }

  /**
   * @return Number of records that the table can display in the current draw.
   *         It is expected that the number of records returned will be equal to
   *         this number, unless the server has fewer records to return. If this
   *         value is -1 it means show all entries.
   */
  public int getDisplayLength ()
  {
    return m_nDisplayLength;
  }

  /**
   * @return <code>true</code> if all entries should be shown,
   *         <code>false</code> if the number is limited
   */
  public boolean showAllEntries ()
  {
    return m_nDisplayLength == -1;
  }

  /**
   * @return Number of columns being displayed (useful for getting individual
   *         column search info)
   */
  public int getColumnCount ()
  {
    return m_aColumnData.size ();
  }

  /**
   * @return <code>true</code> if either the global search or any column search
   *         is active.
   */
  public boolean isSearchActive ()
  {
    // Global search text present?
    if (m_aSearch.hasSearchText ())
      return true;

    // Per-column search text present?
    for (final DTSSRequestDataColumn aColumn : m_aColumnData)
      if (aColumn.isSearchable () && aColumn.getSearch ().hasSearchText ())
        return true;

    return false;
  }

  /**
   * @return Global search field
   */
  @Nonnull
  public DTSSRequestDataSearch getSearch ()
  {
    return m_aSearch;
  }

  @Nonnull
  public DTSSRequestDataColumn getColumn (@Nonnegative final int nIndex)
  {
    return m_aColumnData.get (nIndex);
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <DTSSRequestDataColumn> getColumnData ()
  {
    return CollectionHelper.newList (m_aColumnData);
  }

  @Nonnull
  @ReturnsMutableCopy
  public DTSSRequestDataColumn [] getColumnDataArray ()
  {
    return ArrayHelper.newArray (m_aColumnData, DTSSRequestDataColumn.class);
  }

  /**
   * @return Number of columns to sort on. Always &ge; 0.
   */
  @Nonnegative
  public int getOrderColumnCount ()
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
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Draw", m_nDraw)
                                       .append ("DisplayStart", m_nDisplayStart)
                                       .append ("DisplayLength", m_nDisplayLength)
                                       .append ("Search", m_aSearch)
                                       .append ("OrderColumns", m_aOrderColumns)
                                       .append ("ColumnData", m_aColumnData)
                                       .toString ();
  }
}
