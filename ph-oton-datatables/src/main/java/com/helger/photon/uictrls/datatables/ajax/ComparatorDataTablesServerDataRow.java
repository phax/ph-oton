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

import com.helger.base.compare.IComparator;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.commons.ICommonsList;

final class ComparatorDataTablesServerDataRow implements IComparator <DataTablesServerDataRow>
{
  private final ICommonsList <DTSSRequestDataOrderColumn> m_aOrderColumns;

  ComparatorDataTablesServerDataRow (@NonNull final DataTablesServerSortState aServerSortState)
  {
    ValueEnforcer.notNull (aServerSortState, "ServerSortState");

    m_aOrderColumns = aServerSortState.directGetAllOrderColumns ();
  }

  @Override
  public int compare (@NonNull final DataTablesServerDataRow aRow1, @NonNull final DataTablesServerDataRow aRow2)
  {
    int ret = 0;
    for (final DTSSRequestDataOrderColumn aOrderColumn : m_aOrderColumns)
    {
      // Get the cells to compare
      final int nSortColumnIndex = aOrderColumn.getColumnIndex ();
      final DataTablesServerDataCell aCell1 = aRow1.getCellAtIndex (nSortColumnIndex);
      final DataTablesServerDataCell aCell2 = aRow2.getCellAtIndex (nSortColumnIndex);

      final String sCell1Text = aCell1.getTextContent ();
      final String sCell2Text = aCell2.getTextContent ();

      // Main compare
      ret = aOrderColumn.getOrderComparator ().compare (sCell1Text, sCell2Text);
      if (ret != 0)
        break;
    }
    return ret;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("OrderColumns", m_aOrderColumns).getToString ();
  }
}
