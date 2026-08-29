/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonnegative;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.ReturnsMutableObject;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.html.hc.html.tabular.HCRow;

/**
 * The result of a single "on demand" DataTables AJAX call, as returned by an
 * {@link IDataTablesOnDemandDataProvider}. It contains the rows of the requested page only, plus
 * the two counts DataTables needs to render the paging controls.
 *
 * @author Philip Helger
 * @since 10.4.0
 */
@NotThreadSafe
public class DataTablesOnDemandResult
{
  private final long m_nTotalCount;
  private final long m_nFilteredCount;
  private final ICommonsList <HCRow> m_aRows;

  /**
   * Constructor.
   *
   * @param nTotalCount
   *        The total number of rows available, ignoring the current search text. Must be &ge; 0.
   * @param nFilteredCount
   *        The number of rows matching the current search text. Must be &ge; 0. If no search text
   *        is present, this is identical to the total count.
   * @param aRows
   *        The rows of the requested page. May be <code>null</code> or empty, but may not contain
   *        <code>null</code> elements.
   */
  public DataTablesOnDemandResult (@Nonnegative final long nTotalCount,
                                   @Nonnegative final long nFilteredCount,
                                   @Nullable final Iterable <? extends HCRow> aRows)
  {
    ValueEnforcer.isGE0 (nTotalCount, "TotalCount");
    ValueEnforcer.isGE0 (nFilteredCount, "FilteredCount");
    m_nTotalCount = nTotalCount;
    m_nFilteredCount = nFilteredCount;
    m_aRows = new CommonsArrayList <> (aRows);
    ValueEnforcer.notNullNoNullValue (m_aRows, "Rows");
  }

  /**
   * @return The total number of rows available, ignoring the current search text. Always &ge; 0.
   */
  @Nonnegative
  public long getTotalCount ()
  {
    return m_nTotalCount;
  }

  /**
   * @return The number of rows matching the current search text. Always &ge; 0.
   */
  @Nonnegative
  public long getFilteredCount ()
  {
    return m_nFilteredCount;
  }

  /**
   * @return The rows of the requested page. Never <code>null</code> but maybe empty.
   */
  @NonNull
  @ReturnsMutableObject ("speed")
  public ICommonsList <HCRow> directGetAllRows ()
  {
    return m_aRows;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("TotalCount", m_nTotalCount)
                                       .append ("FilteredCount", m_nFilteredCount)
                                       .append ("RowCount", m_aRows.size ())
                                       .getToString ();
  }

  /**
   * Create a result for a table that has no rows at all.
   *
   * @return Never <code>null</code>.
   */
  @NonNull
  public static DataTablesOnDemandResult createEmpty ()
  {
    return new DataTablesOnDemandResult (0, 0, null);
  }
}
