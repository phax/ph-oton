/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.type.ObjectType;
import com.helger.html.hc.IHCConversionSettings;
import com.helger.html.hc.IHCCustomizer;
import com.helger.html.hc.config.HCConversionSettings;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.ext.HCCustomizerAutoFocusFirstCtrl;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.IHCTable;
import com.helger.html.hc.impl.HCCustomizerList;
import com.helger.photon.core.state.IHasUIState;
import com.helger.photon.uictrls.datatables.EDataTablesFilterType;
import com.helger.photon.uictrls.datatables.column.DTOrderSpec;
import com.helger.photon.uictrls.datatables.column.DataTablesColumnDef;

/**
 * This class holds tables to be used by the DataTables server side handling.
 * Each DataTable in each session is represented as a single object of this
 * class. SO if you have a lot of DataTables instances of this class may use a
 * huge amount of memory!
 *
 * @author Philip Helger
 */
public final class DataTablesServerData implements IHasUIState
{
  public static final ObjectType OT_DATATABLES = new ObjectType ("datatables");
  public static final IHCConversionSettings DEFAULT_CONVERSION_SETTINGS = createConversionSettings ();
  private static final Logger s_aLogger = LoggerFactory.getLogger (DataTablesServerData.class);

  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  private final DTOrderSpec [] m_aColumns;
  @GuardedBy ("m_aRWLock")
  private final ICommonsList <DataTablesServerDataRow> m_aRows;
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
    m_aColumns = new DTOrderSpec [nColumnCount];
    for (final DataTablesColumnDef aColumn : aColumns)
    {
      final DTOrderSpec aColumnData = aColumn.getOrderSpec ();
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

    // Ensure all other columns are initialized with empty DTOrderSpec to have
    // the benefit of a cached comparator
    for (int i = 0; i < nColumnCount; ++i)
      if (m_aColumns[i] == null)
        m_aColumns[i] = new DTOrderSpec ().setDisplayLocale (aDisplayLocale);

    // Row data
    m_aRows = new CommonsArrayList <> (aTable.getBodyRowCount ());
    int nCells = 0;
    for (final HCRow aRow : aTable.getAllBodyRows ())
    {
      m_aRows.add (new DataTablesServerDataRow (aRow));
      nCells += aRow.getCellCount ();
    }

    if (s_aLogger.isDebugEnabled ())
      s_aLogger.debug ("Having ServerSide DataTables with " +
                       aTable.getBodyRowCount () +
                       " rows and a total of " +
                       nCells +
                       " cells");
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
    final HCConversionSettings aRealCS = HCSettings.getMutableConversionSettings ().getClone ();
    aRealCS.getMutableXMLWriterSettings ().setEmitNamespaces (false);

    // Remove any "HCCustomizerAutoFocusFirstCtrl" customizer for AJAX calls on
    // DataTables
    final IHCCustomizer aCustomizer = aRealCS.getCustomizer ();
    if (aCustomizer instanceof HCCustomizerAutoFocusFirstCtrl)
      aRealCS.setCustomizer (null);
    else
      if (aCustomizer instanceof HCCustomizerList)
        ((HCCustomizerList) aCustomizer).removeAllCustomizersOfClass (HCCustomizerAutoFocusFirstCtrl.class);

    return aRealCS;
  }

  @Nonnull
  public ObjectType getObjectType ()
  {
    return OT_DATATABLES;
  }

  public boolean hasServerSortState (@Nonnull final DataTablesServerSortState aOtherserverSortState)
  {
    return m_aRWLock.readLocked ( () -> m_aServerSortState.equals (aOtherserverSortState));
  }

  public void setServerSortStateAndSort (@Nonnull final DataTablesServerSortState aNewServerSortState)
  {
    ValueEnforcer.notNull (aNewServerSortState, "NewServerSortState");

    final Comparator <DataTablesServerDataRow> aComp = new ComparatorDataTablesServerDataRow (aNewServerSortState);

    m_aRWLock.writeLocked ( () -> {
      m_aServerSortState = aNewServerSortState;
      m_aRows.sort (aComp);
    });
  }

  @Nonnegative
  public int getRowCount ()
  {
    return m_aRWLock.readLocked ( () -> m_aRows.size ());
  }

  /**
   * Invoked the passed consumer in a read-lock!
   *
   * @param aConsumer
   *        Consumer to be invoked for each row. May not be <code>null</code>.
   */
  public void forEachRow (final Consumer <? super DataTablesServerDataRow> aConsumer)
  {
    m_aRWLock.readLocked ( () -> m_aRows.forEach (aConsumer));
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <DataTablesServerDataRow> getAllRows ()
  {
    return m_aRWLock.readLocked ( () -> m_aRows.getClone ());
  }

  @Nullable
  public DataTablesServerDataRow getRowOfID (@Nullable final String sID)
  {
    if (StringHelper.hasNoText (sID))
      return null;
    return m_aRWLock.readLocked ( () -> m_aRows.findFirst (x -> sID.equals (x.getRowID ())));
  }

  @Nonnull
  public Locale getDisplayLocale ()
  {
    return m_aDisplayLocale;
  }

  @Nullable
  public DTOrderSpec getColumnOrderSpec (@Nonnegative final int nColumnIndex)
  {
    return ArrayHelper.getSafeElement (m_aColumns, nColumnIndex);
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
                                       // .append ("serverSortState",
                                       // m_aServerSortState)
                                       .append ("filterType", m_eFilterType)
                                       .getToString ();
  }
}
