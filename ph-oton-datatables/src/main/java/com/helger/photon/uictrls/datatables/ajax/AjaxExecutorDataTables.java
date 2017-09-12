/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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

import java.util.BitSet;
import java.util.Locale;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.string.StringParser;
import com.helger.commons.typeconvert.TypeConverter;
import com.helger.html.hc.special.HCSpecialNodes;
import com.helger.json.JsonObject;
import com.helger.photon.core.PhotonUnifiedResponse;
import com.helger.photon.core.ajax.IAjaxExecutor;
import com.helger.photon.core.state.UIStateRegistry;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uictrls.datatables.DataTablesLengthMenu;
import com.helger.photon.uictrls.datatables.EDataTablesFilterType;
import com.helger.photon.uictrls.datatables.EDataTablesOrderDirectionType;
import com.helger.servlet.request.IRequestParamMap;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * AJAX handler for filling DataTables
 *
 * @author Philip Helger
 */
public class AjaxExecutorDataTables implements IAjaxExecutor
{
  // This parameter must be passed to identify the table from the
  // UIStateRegistry!
  public static final String OBJECT_ID = CPageParam.PARAM_OBJECT;
  private static final String DRAW = "draw";
  private static final String START = "start";
  private static final String LENGTH = "length";
  private static final String SEARCH_VALUE = "search[value]";
  private static final String SEARCH_REGEX = "search[regex]";
  private static final String ORDER = "order";
  private static final String ORDER_COLUMN = "column";
  private static final String ORDER_DIR = "dir";
  private static final String COLUMNS = "columns";
  private static final String COLUMNS_DATA = "data";
  private static final String COLUMNS_NAME = "name";
  private static final String COLUMNS_SEARCHABLE = "searchable";
  private static final String COLUMNS_ORDERABLE = "orderable";
  private static final String COLUMNS_SEARCH = "search";
  private static final String COLUMNS_SEARCH_VALUE = "value";
  private static final String COLUMNS_SEARCH_REGEX = "regex";

  private static final String DT_ROW_ID = "DT_RowId";
  private static final String DT_ROW_CLASS = "DT_RowClass";
  private static final String DT_ROW_DATA = "DT_RowData";
  private static final String DT_ROW_ATTR = "DT_RowAttr";

  private static final Logger s_aLogger = LoggerFactory.getLogger (AjaxExecutorDataTables.class);

  private static void _sort (@Nonnull final DTSSRequestData aRequestData,
                             @Nonnull final DataTablesServerData aServerData)
  {
    // Sorting possible and necessary?
    if (aServerData.getRowCount () > 1)
    {
      final Locale aDisplayLocale = aServerData.getDisplayLocale ();

      final DataTablesServerSortState aNewServerSortState = new DataTablesServerSortState (aServerData,
                                                                                           aRequestData.directGetAllOrderColumns (),
                                                                                           aDisplayLocale);
      // Must we change the sorting?
      if (!aServerData.hasServerSortState (aNewServerSortState))
      {
        // Remember the new server state and do the main sorting
        aServerData.setServerSortStateAndSort (aNewServerSortState);
      }
    }
  }

  @Nonnull
  private static ICommonsList <DataTablesServerDataRow> _filter (@Nonnull final DTSSRequestData aRequestData,
                                                                 @Nonnull final DataTablesServerData aServerData)
  {
    if (!aRequestData.isSearchActive ())
    {
      // Must return a copy in case the sort state of this datatable is changed
      // in a parallel request
      return aServerData.getAllRows ();
    }

    final Locale aDisplayLocale = aServerData.getDisplayLocale ();

    // filter rows
    final DTSSRequestDataSearch aGlobalSearch = aRequestData.getSearch ();
    final String [] aGlobalSearchTexts = aGlobalSearch.getSearchTexts ();
    final boolean bGlobalSearchRegEx = aGlobalSearch.isRegEx ();
    final DTSSRequestDataColumn [] aColumns = aRequestData.getColumnDataArray ();
    final EDataTablesFilterType eFilterType = aServerData.getFilterType ();

    boolean bContainsAnyColumnSpecificSearch = false;
    for (final DTSSRequestDataColumn aColumn : aColumns)
      if (aColumn.isSearchable () && aColumn.getSearch ().hasSearchText ())
      {
        bContainsAnyColumnSpecificSearch = true;
        if (eFilterType == EDataTablesFilterType.ALL_TERMS_PER_ROW)
          s_aLogger.error ("DataTables has column specific search term - this is not implemented for filter type ALL_TERMS_PER_ROW!");
      }

    final int nUnfilteredRowCount = aServerData.getRowCount ();
    final ICommonsList <DataTablesServerDataRow> aFilteredRows = new CommonsArrayList <> ();
    if (bContainsAnyColumnSpecificSearch)
    {
      // For all rows
      aServerData.forEachRow (aRow -> {
        // For all cells in row
        int nSearchableCellIndex = 0;
        for (final DataTablesServerDataCell aCell : aRow.directGetAllCells ())
        {
          final DTSSRequestDataColumn aColumn = ArrayHelper.getSafeElement (aColumns, nSearchableCellIndex);
          if (aColumn == null)
            s_aLogger.warn ("Invalid columnn index " + nSearchableCellIndex + " for columns " + aColumns);
          else
            if (aColumn.isSearchable ())
            {
              // Determine search texts
              final DTSSRequestDataSearch aColumnSearch = aColumn.getSearch ();
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

              // Search text may be null!
              if (aColumnSearchTexts != null)
              {
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
              }
              nSearchableCellIndex++;
            }
        }
      });
    }
    else
    {
      // Only global search is relevant

      // For all rows
      aServerData.forEachRow (aRow -> {
        // Each matching search term is represented as a bit in here
        final BitSet aMatchingWords = new BitSet (aGlobalSearchTexts.length);

        // For all cells in row
        int nSearchableCellIndex = 0;
        per_row: for (final DataTablesServerDataCell aCell : aRow.directGetAllCells ())
        {
          final DTSSRequestDataColumn aColumn = aColumns[nSearchableCellIndex];
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
      });
    }

    if (s_aLogger.isDebugEnabled ())
      s_aLogger.debug ("DataTables filtered " + aFilteredRows.size () + " rows out of " + nUnfilteredRowCount);

    // Use the filtered rows
    return aFilteredRows;
  }

  @Nonnull
  private static DTSSResponseData _handleRequest (@Nonnull final DTSSRequestData aRequestData,
                                                  @Nonnull final DataTablesServerData aServerData)
  {
    // Sort before filtering, because if only filtering changes, the sorting
    // does not need to be performed again. If we would filter first, we would
    // need to apply the sorting each time which might be time consuming. So
    // this pattern assumes that filtering changes more often than sorting order
    // changes.
    _sort (aRequestData, aServerData);

    final ICommonsList <DataTablesServerDataRow> aResultRows = _filter (aRequestData, aServerData);

    // Build the resulting array
    final HCSpecialNodes aSpecialNodes = new HCSpecialNodes ();
    final ICommonsList <JsonObject> aData = new CommonsArrayList <> ();
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
      final JsonObject aRowData = new JsonObject ();
      if (aRow.hasRowID ())
        aRowData.add (DT_ROW_ID, aRow.getRowID ());
      if (aRow.hasRowClass ())
        aRowData.add (DT_ROW_CLASS, aRow.getRowClass ());
      if (aRow.hasRowData ())
        aRowData.add (DT_ROW_DATA, aRow.directGetAllRowData ());
      if (aRow.hasRowAttr ())
        aRowData.add (DT_ROW_ATTR, aRow.directGetAllRowAttrs ());
      int nCellIndex = 0;
      for (final DataTablesServerDataCell aCell : aRow.directGetAllCells ())
      {
        // Assume the index is the "data"
        aRowData.add (Integer.toString (nCellIndex++), aCell.getHTMLString ());

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
    final String sErrorMsg = null;
    return new DTSSResponseData (aRequestData.getDraw (),
                                 nTotalRecords,
                                 nTotalDisplayRecords,
                                 aData,
                                 sErrorMsg,
                                 aSpecialNodes);
  }

  public void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull final PhotonUnifiedResponse aAjaxResponse) throws Exception
  {
    if (s_aLogger.isDebugEnabled ())
      s_aLogger.debug ("DataTables AJAX request: " + CollectionHelper.getSortedByKey (aRequestScope.params ()));

    // Read input parameters and ensure non negativeness
    final int nDraw = aRequestScope.params ().getAsInt (DRAW);
    final int nDisplayStart = Math.max (aRequestScope.params ().getAsInt (START, 0), 0);
    // -1 means show all
    // This parameter is "" or "NaN" when scrolling is active - use -1 as well
    final int nDisplayLength = aRequestScope.params ().getAsInt (LENGTH, DataTablesLengthMenu.COUNT_ALL);
    final String sSearchValue = aRequestScope.params ().getAsString (SEARCH_VALUE);
    final boolean bSearchRegEx = aRequestScope.params ().getAsBoolean (SEARCH_REGEX, false);

    // Read "order
    final ICommonsList <DTSSRequestDataOrderColumn> aOrderColumns = new CommonsArrayList <> ();
    {
      // May be null
      final IRequestParamMap aOrder = aRequestScope.getRequestParamMap ().getMap (ORDER);
      if (aOrder != null)
      {
        int nIndex = 0;
        do
        {
          final IRequestParamMap aOrderPerIndex = aOrder.getMap (Integer.toString (nIndex));
          if (aOrderPerIndex == null)
            break;

          final int nOrderColumn = Math.max (TypeConverter.convertToInt (aOrderPerIndex.getString (ORDER_COLUMN), 0),
                                             0);
          final String sOrderDir = aOrderPerIndex.getString (ORDER_DIR);
          final ESortOrder eOrderDir = EDataTablesOrderDirectionType.getSortOrderFromNameOrNull (sOrderDir);
          aOrderColumns.add (new DTSSRequestDataOrderColumn (nOrderColumn, eOrderDir));

          ++nIndex;
        } while (true);
      }
    }

    final ICommonsList <DTSSRequestDataColumn> aColumnData = new CommonsArrayList <> ();
    {
      // May be null
      final IRequestParamMap aColumns = aRequestScope.getRequestParamMap ().getMap (COLUMNS);
      if (aColumns != null)
      {
        int nIndex = 0;
        do
        {
          final IRequestParamMap aColumnsPerIndex = aColumns.getMap (Integer.toString (nIndex));
          if (aColumnsPerIndex == null)
            break;

          final String sCData = aColumnsPerIndex.getString (COLUMNS_DATA);
          final String sCName = aColumnsPerIndex.getString (COLUMNS_NAME);
          final boolean bCSearchable = StringParser.parseBool (aColumnsPerIndex.getString (COLUMNS_SEARCHABLE), true);
          final boolean bCOrderable = StringParser.parseBool (aColumnsPerIndex.getString (COLUMNS_ORDERABLE), true);
          final String sCSearchValue = aColumnsPerIndex.getString (COLUMNS_SEARCH, COLUMNS_SEARCH_VALUE);
          final boolean bCSearchRegEx = StringParser.parseBool (aColumnsPerIndex.getString (COLUMNS_SEARCH,
                                                                                            COLUMNS_SEARCH_REGEX),
                                                                true);
          aColumnData.add (new DTSSRequestDataColumn (sCData,
                                                      sCName,
                                                      bCSearchable,
                                                      bCOrderable,
                                                      sCSearchValue,
                                                      bCSearchRegEx));

          ++nIndex;
        } while (true);
      }
    }

    final DTSSRequestData aRequestData = new DTSSRequestData (nDraw,
                                                              nDisplayStart,
                                                              nDisplayLength,
                                                              sSearchValue,
                                                              bSearchRegEx,
                                                              aColumnData,
                                                              aOrderColumns);

    // Resolve dataTables from UIStateRegistry
    final String sDataTablesID = aRequestScope.params ().getAsString (OBJECT_ID);
    final DataTablesServerData aServerData = UIStateRegistry.getCurrent ()
                                                            .getCastedState (DataTablesServerData.OT_DATATABLES,
                                                                             sDataTablesID);
    if (aServerData == null)
    {
      s_aLogger.error ("No such data tables ID: " + sDataTablesID);
      aAjaxResponse.createNotFound ();
    }
    else
    {
      // Main request handling
      final DTSSResponseData aResponseData = _handleRequest (aRequestData, aServerData);

      // Convert the response to JSON and add the special nodes
      aAjaxResponse.json (PhotonUnifiedResponse.HtmlHelper.getResponseAsJSON (aResponseData.getAsJson (),
                                                                     aResponseData.getSpecialNodes ()));
    }
  }
}
