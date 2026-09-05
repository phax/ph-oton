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

import java.util.function.Predicate;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.collection.paging.SortField;
import com.helger.html.hc.html.tabular.HCColGroup;
import com.helger.html.hc.html.tabular.IHCCol;
import com.helger.html.hc.html.tabular.IHCTable;
import com.helger.html.jquery.JQueryAjaxBuilder;
import com.helger.html.jscode.JSAssocArray;
import com.helger.photon.ajax.GlobalAjaxInvoker;
import com.helger.photon.ajax.decl.AjaxFunctionDeclaration;
import com.helger.photon.ajax.decl.IAjaxFunctionDeclaration;
import com.helger.photon.core.paging.ITableColumn;
import com.helger.photon.core.paging.TableColumnHelper;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.DataTablesOrder;
import com.helger.photon.uictrls.datatables.EDataTablesServerSideMode;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Helper to run a DataTables in the server side mode {@link EDataTablesServerSideMode#ON_DEMAND}:
 * the table is never rendered as a whole, instead every AJAX request queries only the rows of the
 * requested page from the underlying data store.<br>
 * An application usually points all its DataTables to a single shared {@link AjaxExecutorDataTables}
 * that keeps a rendered copy of the whole table in the session. The methods of this class override
 * that per table.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
public final class DataTablesOnDemandHelper
{
  private static final Logger LOGGER = LoggerFactory.getLogger (DataTablesOnDemandHelper.class);

  private DataTablesOnDemandHelper ()
  {}

  private static int _getColumnIndexOfName (@NonNull final IHCTable <?> aTable, @NonNull final String sName)
  {
    final HCColGroup aColGroup = aTable.getColGroup ();
    if (aColGroup != null)
    {
      int nIndex = 0;
      for (final IHCCol <?> aCol : aColGroup.getAllColumns ())
      {
        if (aCol instanceof final DTCol aDTCol && sName.equals (aDTCol.getName ()))
          return nIndex;
        ++nIndex;
      }
    }
    return -1;
  }

  /**
   * Register the AJAX function that provides the rows of a single page. Call this once per page
   * instance, e.g. from a field initializer. The created function has a random name and is
   * registered in the {@link GlobalAjaxInvoker}, because one function is needed per page instance
   * and can therefore not be declared as a constant.
   *
   * @param aDataProvider
   *        The provider that queries and renders the rows. May not be <code>null</code>.
   * @param aFilter
   *        The filter deciding whether the function may be invoked, e.g. "a user must be logged
   *        in". May be <code>null</code> to allow every invocation.
   * @return The created function declaration. Never <code>null</code>.
   */
  @NonNull
  public static IAjaxFunctionDeclaration registerAjaxFunction (@NonNull final IDataTablesOnDemandDataProvider aDataProvider,
                                                               @Nullable final Predicate <? super IRequestWebScopeWithoutResponse> aFilter)
  {
    ValueEnforcer.notNull (aDataProvider, "DataProvider");

    final AjaxFunctionDeclaration.Builder aBuilder = AjaxFunctionDeclaration.builder ()
                                                                            .executor (new AjaxExecutorDataTablesOnDemand (aDataProvider));
    if (aFilter != null)
      aBuilder.filter (aFilter);

    final AjaxFunctionDeclaration aFunction = aBuilder.build ();
    GlobalAjaxInvoker.getInstance ().getRegistry ().registerFunction (aFunction);
    return aFunction;
  }

  /**
   * Map the default order declared by the provided columns onto the column indices of the provided
   * table.
   *
   * @param aTable
   *        The table to map onto. May not be <code>null</code>. Its sortable columns must be named
   *        after the ID of the respective {@link ITableColumn}.
   * @param aColumns
   *        All available columns. May not be <code>null</code>.
   * @return <code>null</code> if the table contains none of the default order columns.
   * @see ITableColumn#getDefaultSortOrder()
   * @see DTCol#setName(String)
   */
  @Nullable
  public static DataTablesOrder createInitialOrder (@NonNull final IHCTable <?> aTable,
                                                    @NonNull final ITableColumn <?> [] aColumns)
  {
    ValueEnforcer.notNull (aTable, "Table");
    ValueEnforcer.notNull (aColumns, "Columns");

    final DataTablesOrder ret = new DataTablesOrder ();
    boolean bAny = false;
    for (final SortField aSortField : TableColumnHelper.getAllDefaultSortFields (aColumns))
    {
      final int nColumnIndex = _getColumnIndexOfName (aTable, aSortField.getFieldName ());
      if (nColumnIndex < 0)
      {
        LOGGER.warn ("The default sort column '" +
                     aSortField.getFieldName () +
                     "' is not part of the table '" +
                     aTable.getID () +
                     "' - the initial order of the UI and the default order of the data store may differ");
        continue;
      }
      ret.addColumn (nColumnIndex, aSortField.getSortOrder ());
      bAny = true;
    }
    return bAny ? ret : null;
  }

  /**
   * Switch the provided DataTables to the "on demand" server side mode, so that the AJAX requests
   * are answered by the provided function instead of by a shared {@link AjaxExecutorDataTables}.
   * The initial order of the table is set to the default order declared by the provided columns, so
   * that the order shown in the UI and the order the data provider falls back to cannot drift
   * apart.
   *
   * @param aDataTables
   *        The DataTables to be modified, as created by the application. May not be
   *        <code>null</code>.
   * @param aTable
   *        The underlying table. May not be <code>null</code>. It must have an ID.
   * @param aAjaxFunction
   *        The AJAX function providing the rows, as created by
   *        {@link #registerAjaxFunction(IDataTablesOnDemandDataProvider, Predicate)}. May not be
   *        <code>null</code>.
   * @param aRequestScope
   *        The current request scope, to determine the invocation URL of the AJAX function. May not
   *        be <code>null</code>.
   * @param aColumns
   *        All available columns, to determine the initial order. May not be <code>null</code>.
   * @return The provided DataTables for chaining. Never <code>null</code>.
   */
  @NonNull
  public static DataTables applyOnDemandMode (@NonNull final DataTables aDataTables,
                                              @NonNull final IHCTable <?> aTable,
                                              @NonNull final IAjaxFunctionDeclaration aAjaxFunction,
                                              @NonNull final IRequestWebScopeWithoutResponse aRequestScope,
                                              @NonNull final ITableColumn <?> [] aColumns)
  {
    ValueEnforcer.notNull (aDataTables, "DataTables");
    ValueEnforcer.notNull (aTable, "Table");
    ValueEnforcer.notNull (aAjaxFunction, "AjaxFunction");
    ValueEnforcer.notNull (aRequestScope, "RequestScope");
    ValueEnforcer.notNull (aColumns, "Columns");

    // Overwrite the default AJAX URL of the application, that usually points to the shared executor
    // keeping a copy of the whole table in the session
    aDataTables.setServerSideMode (EDataTablesServerSideMode.ON_DEMAND)
               .setAjaxBuilder (new JQueryAjaxBuilder ().url (aAjaxFunction.getInvocationURL (aRequestScope))
                                                        .data (new JSAssocArray ().add (AjaxExecutorDataTables.OBJECT_ID,
                                                                                        aTable.getID ())));

    final DataTablesOrder aInitialOrder = createInitialOrder (aTable, aColumns);
    if (aInitialOrder != null)
      aDataTables.setInitialOrder (aInitialOrder);

    return aDataTables;
  }
}
