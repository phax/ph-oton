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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.type.ObjectType;
import com.helger.html.hc.IHCConversionSettings;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.IHCTable;
import com.helger.photon.core.state.IHasUIState;
import com.helger.photon.uictrls.datatables.DataTablesColumnDef;
import com.helger.photon.uictrls.datatables.EDataTablesFilterType;

/**
 * This class holds tables to be used by the DataTables server side handling.
 *
 * @author Philip Helger
 */
public final class DataTablesServerData implements IHasUIState
{
  /**
   * This class contains all data required for each column of a table.
   *
   * @author Philip Helger
   */
  static final class ColumnData implements Serializable
  {
    private final Comparator <String> m_aComparator;

    private ColumnData (@Nonnull final Comparator <String> aComparator)
    {
      m_aComparator = ValueEnforcer.notNull (aComparator, "Comparator");
    }

    @Nonnull
    public Comparator <String> getComparator ()
    {
      return m_aComparator;
    }

    @Nullable
    public static ColumnData create (@Nullable final Comparator <String> aComparator)
    {
      return aComparator == null ? null : new ColumnData (aComparator);
    }
  }

  public static final ObjectType OT_DATATABLES = new ObjectType ("datatables");

  private final ReadWriteLock m_aRWLock = new ReentrantReadWriteLock ();
  private final ColumnData [] m_aColumns;
  private final List <DataTablesServerDataRow> m_aRows;
  private final Locale m_aDisplayLocale;
  @GuardedBy ("m_aRWLock")
  private DataTablesServerSortState m_aServerSortState;
  private final EDataTablesFilterType m_eFilterType;

  public DataTablesServerData (@Nonnull final IHCTable <?> aTable,
                               @Nonnull final List <DataTablesColumnDef> aColumns,
                               @Nonnull final Locale aDisplayLocale,
                               @Nonnull final EDataTablesFilterType eFilterType)
  {
    ValueEnforcer.notNull (aTable, "Table");
    ValueEnforcer.notNull (aColumns, "Columns");
    ValueEnforcer.notNull (aDisplayLocale, "DisplayLocale");
    ValueEnforcer.notNull (eFilterType, "FilterType");

    // Column data
    final int nColumnCount = aTable.getColumnCount ();
    m_aColumns = new ColumnData [nColumnCount];
    for (final DataTablesColumnDef aColumn : aColumns)
    {
      final ColumnData aColumnData = ColumnData.create (aColumn.getComparator ());
      for (final int nTarget : aColumn.getAllTargets ())
      {
        if (nTarget < 0 || nTarget >= nColumnCount)
          throw new IllegalArgumentException ("DataTablesColumn is targeting illegal column index " +
                                              nTarget +
                                              "; must be >= 0 and < " +
                                              nColumnCount +
                                              " in table " +
                                              aTable.getID ());
        m_aColumns[nTarget] = aColumnData;
      }
    }

    // Create HTML without namespaces
    final IHCConversionSettings aRealCS = createConversionSettings ();

    // Row data
    m_aRows = new ArrayList <DataTablesServerDataRow> (aTable.getBodyRowCount ());
    for (final HCRow aRow : aTable.getAllBodyRows ())
      m_aRows.add (new DataTablesServerDataRow (aRow, aRealCS));
    m_aDisplayLocale = aDisplayLocale;
    m_aServerSortState = new DataTablesServerSortState (this, aDisplayLocale);
    m_eFilterType = eFilterType;
  }

  /**
   * Create the HC conversion settings to be used for HTML serialization.
   *
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static IHCConversionSettings createConversionSettings ()
  {
    // Create HTML without namespaces
    final IHCConversionSettings aRealCS = HCSettings.getConversionSettings ().getClone ();
    aRealCS.getMutableXMLWriterSettings ().setEmitNamespaces (false);
    return aRealCS;
  }

  @Nonnull
  public ObjectType getObjectType ()
  {
    return OT_DATATABLES;
  }

  @Nonnull
  public boolean areServerSortStateEqual (@Nonnull final DataTablesServerSortState aOtherserverSortState)
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aServerSortState.equals (aOtherserverSortState);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public void setServerSortStateAndSort (@Nonnull final DataTablesServerSortState aNewServerSortState)
  {
    ValueEnforcer.notNull (aNewServerSortState, "NewServerSortState");

    final Comparator <DataTablesServerDataRow> aComp = new ComparatorDataTablesServerDataRow (aNewServerSortState);

    m_aRWLock.writeLock ().lock ();
    try
    {
      m_aServerSortState = aNewServerSortState;
      Collections.sort (m_aRows, aComp);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  @Nonnegative
  public int getRowCount ()
  {
    return m_aRows.size ();
  }

  @Nonnull
  @ReturnsMutableObject ("speed")
  public List <DataTablesServerDataRow> directGetAllRows ()
  {
    return m_aRows;
  }

  @Nullable
  public DataTablesServerDataRow getRowOfID (@Nullable final String sID)
  {
    if (StringHelper.hasText (sID))
      for (final DataTablesServerDataRow aRow : m_aRows)
        if (sID.equals (aRow.getRowID ()))
          return aRow;
    return null;
  }

  @Nonnull
  public Locale getDisplayLocale ()
  {
    return m_aDisplayLocale;
  }

  @Nullable
  public Comparator <String> getColumnComparator (@Nonnegative final int nColumnIndex)
  {
    final ColumnData aColumnData = ArrayHelper.getSafeElement (m_aColumns, nColumnIndex);
    return aColumnData == null ? null : aColumnData.getComparator ();
  }

  @Nonnull
  public EDataTablesFilterType getFilterType ()
  {
    return m_eFilterType;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("columnCount", m_aColumns.length)
                                       .append ("rowCount", m_aRows.size ())
                                       .append ("displayLocale", m_aDisplayLocale)
                                       .append ("serverSortState", m_aServerSortState)
                                       .append ("filterType", m_eFilterType)
                                       .toString ();
  }
}
