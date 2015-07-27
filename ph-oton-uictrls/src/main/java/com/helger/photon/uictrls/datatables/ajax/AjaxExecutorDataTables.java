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

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.compare.ESortOrder;
import com.helger.html.hc.special.HCSpecialNodes;
import com.helger.photon.core.ajax.executor.AbstractAjaxExecutor;
import com.helger.photon.core.ajax.response.AjaxDefaultResponse;
import com.helger.photon.core.ajax.response.IAjaxResponse;
import com.helger.photon.core.state.UIStateRegistry;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uictrls.datatables.CDataTables;
import com.helger.photon.uictrls.datatables.EDataTablesFilterType;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * AJAX handler for filling DataTables
 *
 * @author Philip Helger
 */
public class AjaxExecutorDataTables extends AbstractAjaxExecutor
{
  // This parameter must be passed to identify the table from the
  // UIStateRegistry!
  public static final String OBJECT_ID = CPageParam.PARAM_OBJECT;
  private static final String DISPLAY_START = "iDisplayStart";
  private static final String DISPLAY_LENGTH = "iDisplayLength";
  private static final String COLUMNS = "iColumns";
  private static final String SEARCH = "sSearch";
  private static final String REGEX = "bRegEx";
  private static final String SEARCHABLE_PREFIX = "bSearchable_";
  private static final String SEARCH_PREFIX = "sSearch_";
  private static final String REGEX_PREFIX = "bRegEx_";
  private static final String SORTABLE_PREFIX = "bSortable_";
  private static final String SORTING_COLS = "iSortingCols";
  private static final String SORT_COL_PREFIX = "iSortCol_";
  private static final String SORT_DIR_PREFIX = "sSortDir_";
  private static final String DATA_PROP_PREFIX = "mDataProp_";
  private static final String ECHO = "sEcho";

  private static final String DT_ROW_ID = "DT_RowId";
  private static final String DT_ROW_CLASS = "DT_RowClass";

  private static final Logger s_aLogger = LoggerFactory.getLogger (AjaxExecutorDataTables.class);

  private static void _sort (@Nonnull final RequestData aRequestData, @Nonnull final DataTablesServerData aServerData)
  {
    // Sorting possible and necessary?
    if (aServerData.getRowCount () > 1)
    {
      final Locale aDisplayLocale = aServerData.getDisplayLocale ();

      final DataTablesServerSortState aNewServerSortState = new DataTablesServerSortState (aServerData,
                                                                                           aRequestData.getSortColumnArray (),
                                                                                           aDisplayLocale);
      // Must we change the sorting?
      if (!aServerData.areServerSortStateEqual (aNewServerSortState))
      {
        // Remember the new server state and sort
        aServerData.setServerSortStateAndSort (aNewServerSortState);
      }
    }
  }

  @Nonnull
  private static List <DataTablesServerDataRow> _filter (@Nonnull final RequestData aRequestData,
                                                         @Nonnull final DataTablesServerData aServerData)
  {
    List <DataTablesServerDataRow> aResultRows = aServerData.directGetAllRows ();
    if (aRequestData.isSearchActive ())
    {
      final Locale aDisplayLocale = aServerData.getDisplayLocale ();

      // filter rows
      final RequestDataSearch aGlobalSearch = aRequestData.getSearch ();
      final String [] aGlobalSearchTexts = aGlobalSearch.getSearchTexts ();
      final boolean bGlobalSearchRegEx = aGlobalSearch.isRegEx ();
      final RequestDataColumn [] aColumns = aRequestData.getColumnDataArray ();
      final EDataTablesFilterType eFilterType = aServerData.getFilterType ();

      boolean bContainsAnyColumnSpecificSearch = false;
      for (final RequestDataColumn aColumn : aColumns)
        if (aColumn.isSearchable () && aColumn.getSearch ().hasSearchText ())
        {
          bContainsAnyColumnSpecificSearch = true;
          if (eFilterType == EDataTablesFilterType.ALL_TERMS_PER_ROW)
            s_aLogger.error ("DataTables has column specific search term - this is not implemented for filter type ALL_TERMS_PER_ROW!");
        }

      final List <DataTablesServerDataRow> aFilteredRows = new ArrayList <DataTablesServerDataRow> ();
      if (bContainsAnyColumnSpecificSearch)
      {
        // For all rows
        for (final DataTablesServerDataRow aRow : aResultRows)
        {
          // For all cells in row
          int nSearchableCellIndex = 0;
          for (final DataTablesServerDataCell aCell : aRow.directGetAllCells ())
          {
            final RequestDataColumn aColumn = aColumns[nSearchableCellIndex];
            if (aColumn.isSearchable ())
            {
              // Determine search texts
              final RequestDataSearch aColumnSearch = aColumn.getSearch ();
              String [] aColumnSearchTexts;
              boolean bColumnSearchRegEx;
              if (aColumnSearch.hasSearchText ())
              {
                aColumnSearchTexts = aColumnSearch.getSearchTexts ();
                bColumnSearchRegEx = aColumnSearch.isRegEx ();
              }
              else
              {
                aColumnSearchTexts = aGlobalSearchTexts;
                bColumnSearchRegEx = bGlobalSearchRegEx;
              }
              final BitSet aMatchingWords = new BitSet (aColumnSearchTexts.length);

              // Main matching
              if (bColumnSearchRegEx)
                aCell.matchRegEx (aColumnSearchTexts, aMatchingWords);
              else
                aCell.matchPlainTextIgnoreCase (aColumnSearchTexts, aDisplayLocale, aMatchingWords);
              if (!aMatchingWords.isEmpty ())
              {
                aFilteredRows.add (aRow);
                break;
              }
              nSearchableCellIndex++;
            }
          }
        }
      }
      else
      {
        // Only global search is relevant

        // For all rows
        for (final DataTablesServerDataRow aRow : aResultRows)
        {
          // Each matching search term is represented as a bit in here
          final BitSet aMatchingWords = new BitSet (aGlobalSearchTexts.length);

          // For all cells in row
          int nSearchableCellIndex = 0;
          per_row: for (final DataTablesServerDataCell aCell : aRow.directGetAllCells ())
          {
            final RequestDataColumn aColumn = aColumns[nSearchableCellIndex];
            if (aColumn.isSearchable ())
            {
              // Main matching
              if (bGlobalSearchRegEx)
                aCell.matchRegEx (aGlobalSearchTexts, aMatchingWords);
              else
                aCell.matchPlainTextIgnoreCase (aGlobalSearchTexts, aDisplayLocale, aMatchingWords);

              switch (eFilterType)
              {
                case ALL_TERMS_PER_ROW:
                  if (aMatchingWords.cardinality () == aGlobalSearchTexts.length)
                  {
                    // Row matched all search terms
                    aFilteredRows.add (aRow);

                    // Goto next row
                    break per_row;
                  }
                  break;
                case ANY_TERM_PER_ROW:
                  if (!aMatchingWords.isEmpty ())
                  {
                    // Row matched any search term
                    aFilteredRows.add (aRow);

                    // Goto next row
                    break per_row;
                  }
                  // Clear again for next cell
                  aMatchingWords.clear ();
                  break;
                default:
                  throw new IllegalStateException ("Unhandled filter type: " + eFilterType);
              }
            }
            nSearchableCellIndex++;
          }
        }
      }

      if (s_aLogger.isDebugEnabled ())
        s_aLogger.debug ("DataTables filtered " + aFilteredRows.size () + " rows out of " + aResultRows.size ());

      // Use the filtered rows
      aResultRows = aFilteredRows;
    }
    return aResultRows;
  }

  @Nonnull
  private static ResponseData _handleRequest (@Nonnull final RequestData aRequestData,
                                              @Nonnull final DataTablesServerData aServerData)
  {
    _sort (aRequestData, aServerData);

    final List <DataTablesServerDataRow> aResultRows = _filter (aRequestData, aServerData);

    // Build the resulting array
    final HCSpecialNodes aSpecialNodes = new HCSpecialNodes ();
    final List <Map <String, String>> aData = new ArrayList <Map <String, String>> ();
    int nResultRowCount = 0;
    final boolean bAllEntries = aRequestData.showAllEntries ();
    // Just in case ;-)
    final int nMaxResultRows = Math.min (aRequestData.getDisplayLength (), aServerData.getRowCount ());
    while (bAllEntries || nResultRowCount < nMaxResultRows)
    {
      final int nRealIndex = aRequestData.getDisplayStart () + nResultRowCount;
      if (nRealIndex >= aResultRows.size ())
      {
        // No more matching rows available
        break;
      }

      final DataTablesServerDataRow aRow = aResultRows.get (nRealIndex);
      final Map <String, String> aRowData = new HashMap <String, String> ();
      if (aRow.hasRowID ())
        aRowData.put (DT_ROW_ID, aRow.getRowID ());
      if (aRow.hasRowClass ())
        aRowData.put (DT_ROW_CLASS, aRow.getRowClass ());
      int nCellIndex = 0;
      for (final DataTablesServerDataCell aCell : aRow.directGetAllCells ())
      {
        aRowData.put (Integer.toString (nCellIndex++), aCell.getHTML ());

        // Merge all special nodes into the global ones
        aSpecialNodes.addAll (aCell.getSpecialNodes ());
      }
      aData.add (aRowData);
      ++nResultRowCount;
    }

    // Check if the special nodes can be merged
    // TODO HCSpecialNodeHandler.applyModifiers (aNodes);

    // Main response
    final int nTotalRecords = aServerData.getRowCount ();
    final int nTotalDisplayRecords = aResultRows.size ();
    return new ResponseData (nTotalRecords, nTotalDisplayRecords, aRequestData.getEcho (), null, aData, aSpecialNodes);
  }

  @Override
  @Nonnull
  protected IAjaxResponse mainHandleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope) throws Exception
  {
    if (s_aLogger.isDebugEnabled ())
      s_aLogger.debug ("DataTables AJAX request: " +
                       CollectionHelper.getSortedByKey (aRequestScope.getAllAttributes ()));

    // Read input parameters and ensure non negativeness
    final int nDisplayStart = Math.max (aRequestScope.getAttributeAsInt (DISPLAY_START, 0), 0);
    // -1 means show all
    final int nDisplayLength = Math.max (aRequestScope.getAttributeAsInt (DISPLAY_LENGTH, 0), -1);
    final int nColumns = Math.max (aRequestScope.getAttributeAsInt (COLUMNS, 0), 0);
    final String sSearch = aRequestScope.getAttributeAsString (SEARCH);
    final boolean bRegEx = aRequestScope.getAttributeAsBoolean (REGEX, false);
    final int nSortingCols = Math.max (aRequestScope.getAttributeAsInt (SORTING_COLS), 0);
    final int nEcho = aRequestScope.getAttributeAsInt (ECHO);
    final List <RequestDataColumn> aColumnData = new ArrayList <RequestDataColumn> (nColumns);
    for (int nColumn = 0; nColumn < nColumns; ++nColumn)
    {
      final boolean bCSearchable = aRequestScope.getAttributeAsBoolean (SEARCHABLE_PREFIX + nColumn);
      final String sCSearch = aRequestScope.getAttributeAsString (SEARCH_PREFIX + nColumn);
      final boolean bCRegEx = aRequestScope.getAttributeAsBoolean (REGEX_PREFIX + nColumn);
      final boolean bCSortable = aRequestScope.getAttributeAsBoolean (SORTABLE_PREFIX + nColumn);
      final String sCDataProp = aRequestScope.getAttributeAsString (DATA_PROP_PREFIX + nColumn);
      aColumnData.add (new RequestDataColumn (bCSearchable, sCSearch, bCRegEx, bCSortable, sCDataProp));
    }
    final RequestDataSortColumn [] aSortColumns = new RequestDataSortColumn [nSortingCols];
    for (int i = 0; i < nSortingCols; ++i)
    {
      final int nCSortCol = Math.max (aRequestScope.getAttributeAsInt (SORT_COL_PREFIX + i), 0);
      final String sCSortDir = aRequestScope.getAttributeAsString (SORT_DIR_PREFIX + i);
      final ESortOrder eCSortDir = CDataTables.SORT_ASC.equals (sCSortDir) ? ESortOrder.ASCENDING
                                                                           : CDataTables.SORT_DESC.equals (sCSortDir) ? ESortOrder.DESCENDING
                                                                                                                      : null;
      aSortColumns[i] = new RequestDataSortColumn (nCSortCol, eCSortDir);
    }
    final RequestData aRequestData = new RequestData (nDisplayStart,
                                                      nDisplayLength,
                                                      sSearch,
                                                      bRegEx,
                                                      aColumnData,
                                                      aSortColumns,
                                                      nEcho);

    // Resolve dataTables
    final String sDataTablesID = aRequestScope.getAttributeAsString (OBJECT_ID);
    final DataTablesServerData aServerData = UIStateRegistry.getCurrent ().getCastedState (
                                                                                           DataTablesServerData.OT_DATATABLES,
                                                                                           sDataTablesID);
    if (aServerData == null)
      return AjaxDefaultResponse.createError ("No such data tables ID: " + sDataTablesID);

    // Main request handling
    final ResponseData aResponseData = _handleRequest (aRequestData, aServerData);

    // Convert the response to JSON and add the special nodes
    return AjaxDefaultResponse.createSuccess (aRequestScope, aResponseData.getAsJson ())
                              .addSpecialNodes (aResponseData.getSpecialNodes ());
  }
}
