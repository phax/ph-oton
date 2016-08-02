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

import java.util.Comparator;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.compare.IComparator;
import com.helger.commons.string.ToStringGenerator;

final class ComparatorDataTablesServerDataRow implements IComparator <DataTablesServerDataRow>
{
  private final ICommonsList <DTSSRequestDataOrderColumn> m_aOrderColumns;

  ComparatorDataTablesServerDataRow (@Nonnull final DataTablesServerSortState aServerSortState)
  {
    ValueEnforcer.notNull (aServerSortState, "ServerSortState");

    m_aOrderColumns = aServerSortState.directGetAllOrderColumns ();
  }

  @Override
  public int compare (@Nonnull final DataTablesServerDataRow aRow1, @Nonnull final DataTablesServerDataRow aRow2)
  {
    int ret = 0;
    for (final DTSSRequestDataOrderColumn aOrderColumn : m_aOrderColumns)
    {
      // Get the cells to compare
      final int nSortColumnIndex = aOrderColumn.getColumnIndex ();
      final DataTablesServerDataCell aCell1 = aRow1.getCellAtIndex (nSortColumnIndex);
      final DataTablesServerDataCell aCell2 = aRow2.getCellAtIndex (nSortColumnIndex);

      // Main compare
      final Comparator <String> aBaseComp = aOrderColumn.getServerSideComparator ();
      if (aOrderColumn.getSortDirectionOrDefault ().isAscending ())
        ret = Comparator.nullsLast (aBaseComp).compare (aCell1.getTextContent (), aCell2.getTextContent ());
      else
        ret = Comparator.nullsFirst (aBaseComp).compare (aCell1.getTextContent (), aCell2.getTextContent ());
      if (ret != 0)
        break;
    }
    return ret;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("OrderColumns", m_aOrderColumns).toString ();
  }
}