package com.helger.photon.uictrls.datatables.ajax;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.compare.AbstractComparator;
import com.helger.commons.string.ToStringGenerator;

final class ComparatorDataTablesServerDataRow extends AbstractComparator <DataTablesServerDataRow>
{
  private final RequestDataSortColumn [] m_aSortCols;

  ComparatorDataTablesServerDataRow (@Nonnull final DataTablesServerSortState aServerSortState)
  {
    ValueEnforcer.notNull (aServerSortState, "ServerSortState");

    m_aSortCols = aServerSortState.getSortCols ();
  }

  @Override
  protected int mainCompare (@Nonnull final DataTablesServerDataRow aRow1,
                             @Nonnull final DataTablesServerDataRow aRow2)
  {
    int ret = 0;
    for (final RequestDataSortColumn aSortCol : m_aSortCols)
    {
      // Get the cells to compare
      final int nSortColumnIndex = aSortCol.getColumnIndex ();
      final DataTablesServerDataCell aCell1 = aRow1.getCellAtIndex (nSortColumnIndex);
      final DataTablesServerDataCell aCell2 = aRow2.getCellAtIndex (nSortColumnIndex);

      // Main compare
      ret = aSortCol.getComparator ().compare (aCell1.getTextContent (), aCell2.getTextContent ());
      if (ret != 0)
        break;
    }
    return ret;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("sortCols", m_aSortCols).toString ();
  }
}