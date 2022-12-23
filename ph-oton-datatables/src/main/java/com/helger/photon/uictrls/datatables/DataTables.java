/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.datatables;

import java.text.DecimalFormatSymbols;
import java.util.EnumSet;
import java.util.Locale;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.annotation.Since;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.commons.lang.CloneHelper;
import com.helger.commons.state.EChange;
import com.helger.commons.state.ETriState;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.html.annotation.OutOfBandNode;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.html.script.AbstractHCScriptInline;
import com.helger.html.hc.html.tabular.HCColGroup;
import com.helger.html.hc.html.tabular.IHCCol;
import com.helger.html.hc.html.tabular.IHCTable;
import com.helger.html.jquery.JQuery;
import com.helger.html.jquery.JQueryAjaxBuilder;
import com.helger.html.jquery.JQueryInvocation;
import com.helger.html.js.JSMarshaller;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSInvocation;
import com.helger.html.jscode.JSPackage;
import com.helger.html.jscode.JSVar;
import com.helger.json.IJsonObject;
import com.helger.json.JsonObject;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.core.uistate.UIStateRegistry;
import com.helger.photon.uicore.js.JSJQueryHelper;
import com.helger.photon.uictrls.datatables.ajax.DataTablesServerData;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.datatables.column.DataTablesColumnDef;
import com.helger.photon.uictrls.datatables.column.EDTColType;
import com.helger.photon.uictrls.datatables.plugins.DataTablesPluginClientSortingDate;

@OutOfBandNode
public class DataTables extends AbstractHCScriptInline <DataTables>
{
  public static final boolean DEFAULT_AUTOWIDTH = true;
  public static final boolean DEFAULT_DEFER_RENDER = false;
  public static final boolean DEFAULT_INFO = true;
  public static final boolean DEFAULT_JQUERY_UI = false;
  public static final boolean DEFAULT_LENGTH_CHANGE = true;
  public static final boolean DEFAULT_ORDERING = true;
  public static final boolean DEFAULT_PAGING = true;
  public static final boolean DEFAULT_PROCESSING = false;
  public static final boolean DEFAULT_SCROLL_X = false;
  public static final boolean DEFAULT_SEARCHING = true;
  public static final boolean DEFAULT_STATE_SAVE = false;

  public static final boolean DEFAULT_DEFER_LOADING = false;
  public static final boolean DEFAULT_DESTROY = false;
  public static final int DEFAULT_DISPLAY_START = 0;
  public static final int DEFAULT_PAGE_LENGTH = 10;
  public static final EDataTablesPagingType DEFAULT_PAGING_TYPE = EDataTablesPagingType.SIMPLE_NUMBERS;
  public static final boolean DEFAULT_RETRIEVE = false;
  public static final boolean DEFAULT_SCROLL_COLLAPSE = false;

  public static final String EVENT_INIT = "init.dt";

  private static final Logger LOGGER = LoggerFactory.getLogger (DataTables.class);

  // Constructor parameters
  private final IHCTable <?> m_aTable;
  private final int m_nGeneratedJSVariableSuffix = GlobalIDFactory.getNewIntID ();
  private String m_sJSVariableName = "dt" + m_nGeneratedJSVariableSuffix;

  //
  // DataTables - Features
  //

  private boolean m_bAutoWidth = DEFAULT_AUTOWIDTH;
  private boolean m_bDeferRender = DEFAULT_DEFER_RENDER;
  private boolean m_bInfo = DEFAULT_INFO;
  private boolean m_bJQueryUI = DEFAULT_JQUERY_UI;
  private boolean m_bLengthChange = DEFAULT_LENGTH_CHANGE;
  private boolean m_bOrdering = DEFAULT_ORDERING;
  private boolean m_bPaging = DEFAULT_PAGING;
  private boolean m_bProcessing = DEFAULT_PROCESSING;
  private ETriState m_eScrollX = ETriState.UNDEFINED;
  private IJSExpression m_aScrollY;
  private boolean m_bSearching = DEFAULT_SEARCHING;
  // implicit serverSide:boolean [false]
  private boolean m_bStateSave = DEFAULT_STATE_SAVE;

  //
  // DataTables - Data
  //

  // missing ajax.data:object or function ajax.data(data, settings)
  // missing ajax.dataSrc:string or function ajax.dataSrc(data)
  // missing ajax:string or object or function ajax(data, callback, settings)
  // missing data:array
  private JQueryAjaxBuilder m_aAjaxBuilder;

  //
  // DataTables - Callbacks
  //

  // missing createdRow
  // missing drawCallback
  private JSAnonymousFunction m_aHeaderCallback;
  // missing formatNumber
  private JSAnonymousFunction m_aFooterCallback;
  // missing infoCallback
  private JSAnonymousFunction m_aInitComplete;
  // missing preDrawCallback
  // missing rowCallback
  // missing stateLoadCallback
  // missing stateLoaded
  // missing stateLoadParams
  // missing stateSaveCallback
  // missing stateSaveParams

  //
  // DataTables - Options
  //

  /** Delay the loading of server-side data until second draw. */
  private boolean m_bDeferLoading = DEFAULT_DEFER_LOADING;
  /**
   * Destroy any existing table matching the selector and replace with the new
   * options.
   */
  private boolean m_bDestroy = DEFAULT_DESTROY;
  /** Initial paging start point. */
  private int m_nDisplayStart = DEFAULT_DISPLAY_START;
  /**
   * Define the table control elements to appear on the page and in what order.
   */
  private DataTablesDom m_aDom;
  /** Change the options in the page length select list. */
  private IDataTablesLengthMenu m_aLengthMenu;
  /** Initial order (sort) to apply to the table. */
  private DataTablesOrder m_aInitialOrder;
  /**
   * Control which cell the order event handler will be applied to in a column.
   */
  // missing orderCellsTop:boolean [false]
  /** Highlight the columns being ordered in the table's body. */
  // missing orderClasses:boolean [true]
  /** Ordering to always be applied to the table. */
  // missing orderFixed:array or object
  /** Multiple column ordering ability control. */
  // missing orderMulti:boolean [true]
  /** Change the initial page length (number of rows per page). */
  private int m_nPageLength = DEFAULT_PAGE_LENGTH;
  /** Pagination button display options. */
  private EDataTablesPagingType m_ePagingType = DEFAULT_PAGING_TYPE;
  /** Display component renderer types. */
  // missing renderer:string or object
  /** Retrieve an existing DataTables instance. */
  private boolean m_bRetrieve = DEFAULT_RETRIEVE;
  /** Data property name that DataTables will use to set tr element DOM IDs. */
  @Since ("1.10.8")
  // missing rowId:string [DT_RowId]
  /**
   * Allow the table to reduce in height when a limited number of rows are
   * shown.
   */
  private boolean m_bScrollCollapse = DEFAULT_SCROLL_COLLAPSE;
  /** Control case-sensitive filtering option. */
  // missing search.caseInsensitive:boolean [true]
  /**
   * Enable / disable escaping of regular expression characters in the search
   * term.
   */
  // missing search.regex:boolean [false]
  /** Set an initial filtering condition on the table. */
  // missing search.search:string
  /** Enable / disable DataTables' smart filtering. */
  // missing search.smart:boolean [true]
  /** Define an initial search for individual columns. */
  // missing searchCols:array
  /** Set a throttle frequency for searching. */
  // missing searchDelay:int [null]
  /** Saved state validity duration. In seconds. */
  // missing stateDuration:int [7200]
  /** Set the zebra stripe class names for the rows in the table. */
  // missing stripeClasses:array
  /** Tab index control for keyboard navigation. */
  // missing tabIndex:int [0]

  //
  // DataTables - Columns
  //
  /** Set column definition initialisation properties. */
  private final ICommonsList <DataTablesColumnDef> m_aColumnDefs = new CommonsArrayList <> ();

  //
  // DataTables - Internationalisation
  //
  private Locale m_aDisplayLocale;
  private ISimpleURL m_aTextLoadingURL;
  private String m_sTextLoadingURLLocaleParameterName;

  //
  // DataTables - Plugins
  //
  private final ICommonsOrderedMap <String, IDataTablesPlugin> m_aPlugins = new CommonsLinkedHashMap <> ();

  // Custom properties
  private boolean m_bGenerateOnDocumentReady = DataTablesSettings.isDefaultGenerateOnDocumentReady ();
  private EDataTablesFilterType m_eServerFilterType = EDataTablesFilterType.DEFAULT;
  private transient Consumer <JSPackage> m_aJSBeforeModifier;
  private transient BiConsumer <JSPackage, JSVar> m_aJSAfterModifier;

  /**
   * Apply to an existing table. If the table does not have an ID yet, a new one
   * is created.
   *
   * @param aTable
   *        The table to apply the data table to. May not be <code>null</code>
   *        and must have a valid ID!
   */
  public DataTables (@Nonnull final IHCTable <?> aTable)
  {
    ValueEnforcer.notNull (aTable, "Table");
    ValueEnforcer.notEmpty (aTable.getID (), "Table must have an ID to work with DataTables!");

    final HCColGroup aColGroup = aTable.getColGroup ();
    boolean bHasDTCol = false;
    final IHCNode [] aHeaderNodes = new IHCNode [aTable.getColumnCount ()];
    if (aColGroup != null)
    {
      int nColIndex = 0;
      for (final IHCCol <?> aCol : aColGroup.getAllColumns ())
      {
        if (aCol instanceof DTCol)
        {
          bHasDTCol = true;
          aHeaderNodes[nColIndex] = ((DTCol) aCol).getHeaderNode ();
        }
        nColIndex++;
      }
    }

    if (!aTable.hasHeaderRows ())
    {
      if (bHasDTCol)
        aTable.addHeaderRow ().addCells (aHeaderNodes);
      else
        LOGGER.warn ("Table does not have a header row so DataTables may not be displayed correctly!");
    }

    m_aTable = aTable;
  }

  /**
   * @return The underlying table on which this object is operating.
   */
  @Nonnull
  public final IHCTable <?> getTable ()
  {
    return m_aTable;
  }

  /**
   * @return The ID of the underlying table on which this object is operating.
   */
  @Nonnull
  public final String getTableID ()
  {
    return m_aTable.getID ();
  }

  /**
   * @return The unique int ID that is appended to the JS datatables object. Use
   *         this ID for other DataTables related objects as well.
   */
  public final int getGeneratedJSVariableSuffix ()
  {
    return m_nGeneratedJSVariableSuffix;
  }

  /**
   * @return The name of the JS variable that contains the dataTable object. The
   *         scope of the variable depends on the state of the
   *         {@link #isGenerateOnDocumentReady()} method.
   */
  @Nonnull
  @Nonempty
  public final String getJSVariableName ()
  {
    return m_sJSVariableName;
  }

  @Nonnull
  public DataTables setJSVariableName (@Nonnull @Nonempty final String sJSVariableName)
  {
    ValueEnforcer.notEmpty (sJSVariableName, "JSVariableName");
    ValueEnforcer.isTrue (JSMarshaller.isJSIdentifier (sJSVariableName),
                          () -> "JS Variable name is not an identifier: " + sJSVariableName);
    m_sJSVariableName = sJSVariableName;
    return this;
  }

  //
  // DataTables - Features
  //

  public boolean isAutoWidth ()
  {
    return m_bAutoWidth;
  }

  @Nonnull
  public DataTables setAutoWidth (final boolean bAutoWidth)
  {
    m_bAutoWidth = bAutoWidth;
    return this;
  }

  public boolean isDeferRender ()
  {
    return m_bDeferRender;
  }

  @Nonnull
  public DataTables setDeferRender (final boolean bDeferRender)
  {
    m_bDeferRender = bDeferRender;
    return this;
  }

  public boolean isInfo ()
  {
    return m_bInfo;
  }

  @Nonnull
  public DataTables setInfo (final boolean bInfo)
  {
    m_bInfo = bInfo;
    return this;
  }

  public boolean isJQueryUI ()
  {
    return m_bJQueryUI;
  }

  @Nonnull
  public DataTables setJQueryUI (final boolean bJQueryUI)
  {
    m_bJQueryUI = bJQueryUI;
    return this;
  }

  public boolean isLengthChange ()
  {
    return m_bLengthChange;
  }

  /**
   * Feature control the end user's ability to change the paging display length
   * of the table.
   *
   * @param bLengthChange
   *        <code>true</code> to enable it, <code>false</code> to disable it. If
   *        <code>false</code> the selector on top disappears.
   * @return this for chaining
   */
  @Nonnull
  public DataTables setLengthChange (final boolean bLengthChange)
  {
    m_bLengthChange = bLengthChange;
    return this;
  }

  public boolean isOrdering ()
  {
    return m_bOrdering;
  }

  @Nonnull
  public DataTables setOrdering (final boolean bOrdering)
  {
    m_bOrdering = bOrdering;
    return this;
  }

  /**
   * @return <code>true</code> if the paging UI controls are displayed (bottom
   *         right), <code>false</code> otheriwse.
   */
  public boolean isPaging ()
  {
    return m_bPaging;
  }

  @Nonnull
  public DataTables setPaging (final boolean bPaging)
  {
    m_bPaging = bPaging;
    return this;
  }

  public boolean isProcessing ()
  {
    return m_bProcessing;
  }

  @Nonnull
  public DataTables setProcessing (final boolean bProcessing)
  {
    m_bProcessing = bProcessing;
    return this;
  }

  public boolean isScrollX ()
  {
    return m_eScrollX.getAsBooleanValue (DEFAULT_SCROLL_X);
  }

  @Nonnull
  public DataTables setScrollX (final boolean bScrollX)
  {
    m_eScrollX = ETriState.valueOf (bScrollX);
    return this;
  }

  @Nullable
  public IJSExpression getScrollY ()
  {
    return m_aScrollY;
  }

  @Nonnull
  public DataTables setScrollY (final boolean bScrollY)
  {
    return setScrollY (JSExpr.lit (bScrollY));
  }

  @Nonnull
  public DataTables setScrollY (final int nScrollY)
  {
    return setScrollY (JSExpr.lit (nScrollY));
  }

  @Nonnull
  public DataTables setScrollY (@Nonnull final String sScrollY)
  {
    return setScrollY (JSExpr.lit (sScrollY));
  }

  @Nonnull
  public DataTables setScrollY (@Nullable final IJSExpression aScrollY)
  {
    m_aScrollY = aScrollY;
    return this;
  }

  public boolean isSearching ()
  {
    return m_bSearching;
  }

  @Nonnull
  public DataTables setSearching (final boolean bSearching)
  {
    m_bSearching = bSearching;
    return this;
  }

  public boolean isStateSave ()
  {
    return m_bStateSave;
  }

  @Nonnull
  public DataTables setStateSave (final boolean bStateSave)
  {
    m_bStateSave = bStateSave;
    return this;
  }

  //
  // DataTables - Data
  //

  @Nullable
  public JQueryAjaxBuilder getAjaxBuilder ()
  {
    return m_aAjaxBuilder;
  }

  @Nonnull
  public DataTables setAjaxBuilder (@Nullable final JQueryAjaxBuilder aAjaxBuilder)
  {
    if (aAjaxBuilder != null)
      aAjaxBuilder.cache (false).dataType ("json");

    m_aAjaxBuilder = aAjaxBuilder;
    return this;
  }

  /**
   * @return <code>true</code> if DataTables server side processing is used,
   *         <code>false</code> otherwise.
   * @see #isClientSide()
   */
  public boolean isServerSide ()
  {
    return m_aAjaxBuilder != null;
  }

  /**
   * @return <code>true</code> if DataTables client side processing is used,
   *         <code>false</code> otherwise.
   * @see #isServerSide()
   */
  public boolean isClientSide ()
  {
    return m_aAjaxBuilder == null;
  }

  @Nonnull
  public EDataTablesFilterType getServerFilterType ()
  {
    return m_eServerFilterType;
  }

  @Nonnull
  public DataTables setServerFilterType (@Nonnull final EDataTablesFilterType eServerFilterType)
  {
    m_eServerFilterType = ValueEnforcer.notNull (eServerFilterType, "ServerFilterType");
    return this;
  }

  //
  // DataTables - Callbacks
  //

  @Nullable
  public JSAnonymousFunction getFooterCallback ()
  {
    return m_aFooterCallback;
  }

  /**
   * Set footer callback - see
   * https://datatables.net/examples/advanced_init/footer_callback.html
   *
   * @param aFooterCallback
   *        function footerCallback( tfoot, data, start, end, display )
   * @return this
   */
  @Nonnull
  public DataTables setFooterCallback (@Nullable final JSAnonymousFunction aFooterCallback)
  {
    m_aFooterCallback = aFooterCallback;
    return this;
  }

  @Nullable
  public JSAnonymousFunction getHeaderCallback ()
  {
    return m_aHeaderCallback;
  }

  /**
   * Set header callback - see
   * https://datatables.net/reference/option/headerCallback
   *
   * @param aHeaderCallback
   *        function headerCallback( thead, data, start, end, display )
   * @return this
   */
  @Nonnull
  public DataTables setHeaderCallback (@Nullable final JSAnonymousFunction aHeaderCallback)
  {
    m_aHeaderCallback = aHeaderCallback;
    return this;
  }

  @Nullable
  public JSAnonymousFunction getInitComplete ()
  {
    return m_aInitComplete;
  }

  /**
   * Set init complete - see http://datatables.net/reference/option/initComplete
   *
   * @param aInitComplete
   *        initComplete( settings, json )
   * @return this
   */
  @Nonnull
  public DataTables setInitComplete (@Nullable final JSAnonymousFunction aInitComplete)
  {
    m_aInitComplete = aInitComplete;
    return this;
  }

  //
  // DataTables - Options
  //
  public boolean isDeferLoading ()
  {
    return m_bDeferLoading;
  }

  @Nonnull
  public DataTables setDeferLoading (final boolean bDeferLoading)
  {
    m_bDeferLoading = bDeferLoading;
    return this;
  }

  public boolean isDestroy ()
  {
    return m_bDestroy;
  }

  @Nonnull
  public DataTables setDestroy (final boolean bDestroy)
  {
    m_bDestroy = bDestroy;
    return this;
  }

  @Nonnegative
  public int getDisplayStart ()
  {
    return m_nDisplayStart;
  }

  @Nonnull
  public DataTables setDisplayStart (@Nonnegative final int nDisplayStart)
  {
    m_nDisplayStart = nDisplayStart;
    return this;
  }

  @Nullable
  @ReturnsMutableCopy
  public DataTablesDom getDom ()
  {
    return CloneHelper.getCloneIfNotNull (m_aDom);
  }

  @Nullable
  @ReturnsMutableObject ("design")
  public DataTablesDom directGetDom ()
  {
    return m_aDom;
  }

  @Nonnull
  public DataTables setDom (@Nullable final DataTablesDom aDom)
  {
    m_aDom = CloneHelper.getCloneIfNotNull (aDom);
    return this;
  }

  @Nullable
  public IDataTablesLengthMenu getLengthMenu ()
  {
    return m_aLengthMenu;
  }

  @Nonnull
  public DataTables setLengthMenu (@Nullable final IDataTablesLengthMenu aLengthMenu)
  {
    m_aLengthMenu = aLengthMenu;
    if (aLengthMenu != null && !aLengthMenu.isEmpty ())
    {
      setPageLength (aLengthMenu.getItemAtIndex (0).getItemCount ());
      setLengthChange (aLengthMenu.getItemCount () > 1);
      setPaging (aLengthMenu.getItemCount () > 1);
    }
    return this;
  }

  @Nullable
  public DataTablesOrder getInitialOrder ()
  {
    return m_aInitialOrder;
  }

  @Nonnull
  public DataTables setInitialOrder (@Nullable final DataTablesOrder aInitialOrder)
  {
    m_aInitialOrder = aInitialOrder;
    return this;
  }

  /**
   * @return -1 to display all records
   */
  @CheckForSigned
  public int getPageLength ()
  {
    return m_nPageLength;
  }

  /**
   * Set to -1 to show all
   *
   * @param nPageLength
   *        Number of items to display per page
   * @return this
   */
  @Nonnull
  public DataTables setPageLength (final int nPageLength)
  {
    m_nPageLength = nPageLength;
    return this;
  }

  @Nonnull
  public DataTables setPageLengthAll ()
  {
    return setPageLength (DataTablesLengthMenu.COUNT_ALL);
  }

  @Nullable
  public EDataTablesPagingType getPagingType ()
  {
    return m_ePagingType;
  }

  @Nonnull
  public DataTables setPagingType (@Nullable final EDataTablesPagingType ePaginationType)
  {
    m_ePagingType = ePaginationType;
    return this;
  }

  public boolean isRetrieve ()
  {
    return m_bRetrieve;
  }

  /**
   * Retrieve the DataTables object for the given selector. Note that if the
   * table has already been initialised, this parameter will cause DataTables to
   * simply return the object that has already been set up - it will not take
   * account of any changes you might have made to the initialisation object
   * passed to DataTables (setting this parameter to true is an acknowledgement
   * that you understand this!).
   *
   * @param bRetrieve
   *        true to retrieve
   * @return this for chaining
   */
  @Nonnull
  public DataTables setRetrieve (final boolean bRetrieve)
  {
    m_bRetrieve = bRetrieve;
    return this;
  }

  public boolean isScrollCollapse ()
  {
    return m_bScrollCollapse;
  }

  @Nonnull
  public DataTables setScrollCollapse (final boolean bScrollCollapse)
  {
    m_bScrollCollapse = bScrollCollapse;
    return this;
  }

  //
  // DataTables - Columns
  //
  @Nonnull
  @ReturnsMutableObject
  public ICommonsList <DataTablesColumnDef> columnDefs ()
  {
    return m_aColumnDefs;
  }

  @Nullable
  public DataTablesColumnDef findFirstColumnWithTarget (final int nTarget)
  {
    return m_aColumnDefs.findFirst (x -> x.hasTarget (nTarget));
  }

  @Nonnull
  public DataTables addColumn (@Nonnull final DataTablesColumnDef aColumn)
  {
    ValueEnforcer.notNull (aColumn, "Column");

    // Check if targets are unique!
    for (final int nTarget : aColumn.getAllTargets ())
      for (final DataTablesColumnDef aCurColumn : m_aColumnDefs)
        if (aCurColumn.hasTarget (nTarget))
        {
          LOGGER.warn ("Another DataTablesColumn with target " + nTarget + " is already contained!");
          break;
        }
    m_aColumnDefs.add (aColumn);
    return this;
  }

  @Nonnull
  public DataTables addAllColumns (@Nonnull final IHCTable <?> aTable)
  {
    ValueEnforcer.notNull (aTable, "Table");

    // Add all columns
    final HCColGroup aColGroup = aTable.getColGroup ();
    if (aColGroup != null)
    {
      int nColIndex = 0;
      for (final IHCCol <?> aCol : aColGroup.getAllColumns ())
      {
        DataTablesColumnDef aColumn;
        if (aCol instanceof DTCol)
        {
          // Copy data from DTColumn
          final DTCol aDTCol = (DTCol) aCol;
          aColumn = new DataTablesColumnDef (nColIndex, aDTCol);
          if (aDTCol.hasInitialSorting ())
            setInitialOrder (new DataTablesOrder ().addColumn (nColIndex, aDTCol.getInitialSorting ()));
        }
        else
        {
          // Raw column
          aColumn = new DataTablesColumnDef (nColIndex);
          if (!aCol.isStar ())
            aColumn.setWidth (aCol.getWidth ());
        }
        addColumn (aColumn);
        ++nColIndex;
      }
    }
    return this;
  }

  @Nonnull
  public DataTables setAllColumnsOrderable (final boolean bSortable)
  {
    for (final DataTablesColumnDef aColumn : m_aColumnDefs)
      aColumn.setOrderable (bSortable);
    return this;
  }

  @Nonnull
  public DataTables setAllColumnsSearchable (final boolean bSearchable)
  {
    for (final DataTablesColumnDef aColumn : m_aColumnDefs)
      aColumn.setSearchable (bSearchable);
    return this;
  }

  public boolean hasAnyInvisibleColumn ()
  {
    for (final DataTablesColumnDef aColumn : m_aColumnDefs)
      if (!aColumn.isVisible ())
        return true;
    return false;
  }

  @Nonnegative
  public int getVisibleColumnCount ()
  {
    int ret = 0;
    for (final DataTablesColumnDef aColumn : m_aColumnDefs)
      if (aColumn.isVisible ())
        ++ret;
    return ret;
  }

  //
  // DataTables - Internationalisation
  //

  @Nullable
  public Locale getDisplayLocale ()
  {
    return m_aDisplayLocale;
  }

  @Nonnull
  public DataTables setDisplayLocale (@Nullable final Locale aDisplayLocale)
  {
    m_aDisplayLocale = aDisplayLocale;
    return this;
  }

  @Nullable
  public ISimpleURL getTextLoadingURL ()
  {
    return m_aTextLoadingURL;
  }

  @Nullable
  public String getextLoadingURLLocaleParameterName ()
  {
    return m_sTextLoadingURLLocaleParameterName;
  }

  @Nonnull
  public DataTables setTextLoadingURL (@Nullable final ISimpleURL aTextLoadingURL,
                                       @Nullable final String sTextLoadingURLLocaleParameterName)
  {
    if (aTextLoadingURL != null && StringHelper.hasNoText (sTextLoadingURLLocaleParameterName))
      throw new IllegalArgumentException ("If a text loading URL is present, a text loading URL locale parameter name must also be present");
    m_aTextLoadingURL = aTextLoadingURL;
    m_sTextLoadingURLLocaleParameterName = sTextLoadingURLLocaleParameterName;
    return this;
  }

  //
  // DataTables - Plugins
  //

  @Nonnull
  public DataTables addPlugin (@Nonnull final IDataTablesPlugin aPlugin)
  {
    ValueEnforcer.notNull (aPlugin, "Plugin");
    final String sName = aPlugin.getName ();
    if (m_aPlugins.containsKey (sName))
      throw new IllegalArgumentException ("A plugin with the name '" + sName + "' is already contained!");

    m_aPlugins.put (sName, aPlugin);
    return this;
  }

  @Nonnull
  public EChange removePlugin (@Nonnull final String sPluginName)
  {
    if (StringHelper.hasNoText (sPluginName))
      return EChange.UNCHANGED;
    return EChange.valueOf (m_aPlugins.remove (sPluginName) != null);
  }

  @Nullable
  public IDataTablesPlugin getPluginOfName (@Nullable final String sName)
  {
    if (StringHelper.hasNoText (sName))
      return null;
    return m_aPlugins.get (sName);
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IDataTablesPlugin> getAllPlugins ()
  {
    return m_aPlugins.copyOfValues ();
  }

  // XXX The Rest

  public boolean isGenerateOnDocumentReady ()
  {
    return m_bGenerateOnDocumentReady;
  }

  @Nonnull
  public DataTables setGenerateOnDocumentReady (final boolean bGenerateOnDocumentReady)
  {
    m_bGenerateOnDocumentReady = bGenerateOnDocumentReady;
    return this;
  }

  @Nullable
  public Consumer <JSPackage> getJSBeforeModifier ()
  {
    return m_aJSBeforeModifier;
  }

  @Nonnull
  public DataTables setJSBeforeModifier (@Nullable final Consumer <JSPackage> aJSBeforeModifier)
  {
    // TODO this should be a list
    m_aJSBeforeModifier = aJSBeforeModifier;
    return this;
  }

  @Nullable
  public BiConsumer <JSPackage, JSVar> getJSAfterModifier ()
  {
    return m_aJSAfterModifier;
  }

  @Nonnull
  public DataTables setJSAfterModifier (@Nullable final BiConsumer <JSPackage, JSVar> aJSAfterModifier)
  {
    // TODO this should be a list
    m_aJSAfterModifier = aJSAfterModifier;
    return this;
  }

  /**
   * modify parameter map
   *
   * @param aParams
   *        parameter map
   */
  @OverrideOnDemand
  protected void modifyParams (@Nonnull final JSAssocArray aParams)
  {}

  @Nonnull
  public static IJsonObject createLanguageJson (@Nonnull final Locale aDisplayLocale)
  {
    final IJsonObject aLanguage = new JsonObject ();
    aLanguage.addJson ("aria",
                       new JsonObject ().add ("sortAscending",
                                              EDataTablesText.ARIA_SORT_ASCENDING.getDisplayText (aDisplayLocale))
                                        .add ("sortDescending",
                                              EDataTablesText.ARIA_SORT_DESCENDING.getDisplayText (aDisplayLocale)));
    // Translate??
    aLanguage.add ("decimal", DecimalFormatSymbols.getInstance (aDisplayLocale).getDecimalSeparator ());
    aLanguage.add ("emptyTable", EDataTablesText.EMPTY_TABLE.getDisplayText (aDisplayLocale));
    aLanguage.add ("info", EDataTablesText.INFO.getDisplayText (aDisplayLocale));
    aLanguage.add ("infoEmpty", EDataTablesText.INFO_EMPTY.getDisplayText (aDisplayLocale));
    aLanguage.add ("infoFiltered", EDataTablesText.INFO_FILTERED.getDisplayText (aDisplayLocale));
    aLanguage.add ("infoPostFix", EDataTablesText.INFO_POSTFIX.getDisplayText (aDisplayLocale));
    aLanguage.add ("lengthMenu", EDataTablesText.LENGTH_MENU.getDisplayText (aDisplayLocale));
    aLanguage.add ("loadingRecords", EDataTablesText.LOADING_RECORDS.getDisplayText (aDisplayLocale));
    aLanguage.addJson ("paginate",
                       new JsonObject ().add ("first", EDataTablesText.PAGINATE_FIRST.getDisplayText (aDisplayLocale))
                                        .add ("last", EDataTablesText.PAGINATE_LAST.getDisplayText (aDisplayLocale))
                                        .add ("next", EDataTablesText.PAGINATE_NEXT.getDisplayText (aDisplayLocale))
                                        .add ("previous",
                                              EDataTablesText.PAGINATE_PREVIOUS.getDisplayText (aDisplayLocale)));
    aLanguage.add ("processing", EDataTablesText.PROCESSING.getDisplayText (aDisplayLocale));
    aLanguage.add ("search", EDataTablesText.SEARCH.getDisplayText (aDisplayLocale));
    aLanguage.add ("searchPlaceholder", EDataTablesText.SEARCH_PLACEHOLDER.getDisplayText (aDisplayLocale));
    aLanguage.add ("thousands", EDataTablesText.THOUSANDS.getDisplayText (aDisplayLocale));
    aLanguage.add ("url", "");
    aLanguage.add ("zeroRecords", EDataTablesText.ZERO_RECORDS.getDisplayText (aDisplayLocale));
    return aLanguage;
  }

  @Nonnull
  public JQueryInvocation invokeDataTables ()
  {
    return JQuery.idRef (m_aTable).jqinvoke ("dataTable");
  }

  @Nonnull
  public JQueryInvocation invokeDataTablesAPI ()
  {
    return JQuery.idRef (m_aTable).jqinvoke ("DataTable");
  }

  @Nonnull
  public static JSInvocation invokeDataTablesMoment ()
  {
    return JQuery.fn ().ref ("dataTable").invoke ("moment");
  }

  private void _applyClientSideSortingSettings ()
  {
    if (isClientSide ())
    {
      // Add client-side date/time formatter
      final EnumSet <EDTColType> aDateTimeTypes = EnumSet.noneOf (EDTColType.class);
      for (final IHCCol <?> aCol : m_aTable.getAllColumns ())
        if (aCol instanceof DTCol)
        {
          final EDTColType eColType = ((DTCol) aCol).getColType ();
          if (eColType != null && eColType.isDateTimeType ())
            aDateTimeTypes.add (eColType);
        }
      if (!aDateTimeTypes.isEmpty ())
        addPlugin (new DataTablesPluginClientSortingDate (aDateTimeTypes));
    }
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);

    _applyClientSideSortingSettings ();

    // Determine all applicable plugins
    final ICommonsList <IDataTablesPlugin> aRelevantPlugins = new CommonsArrayList <> ();
    for (final IDataTablesPlugin aPlugin : m_aPlugins.values ())
      if (aPlugin.canBeApplied (this))
        aRelevantPlugins.add (aPlugin);
      else
        LOGGER.warn ("Plugin '" + aPlugin.getName () + "' cannot be applied to DataTable " + getTableID ());

    // Finalize plugins
    for (final IDataTablesPlugin aPlugin : aRelevantPlugins)
      aPlugin.finalizeDataTablesSettings (this);

    final JSAssocArray aParams = new JSAssocArray ();

    //
    // features
    //

    if (m_bAutoWidth != DEFAULT_AUTOWIDTH)
      aParams.add ("autoWidth", m_bAutoWidth);
    if (m_bDeferRender != DEFAULT_DEFER_RENDER)
      aParams.add ("deferRender", m_bDeferRender);
    if (m_bInfo != DEFAULT_INFO)
      aParams.add ("info", m_bInfo);
    if (m_bJQueryUI != DEFAULT_JQUERY_UI)
      aParams.add ("jQueryUI", m_bJQueryUI);
    if (m_bLengthChange != DEFAULT_LENGTH_CHANGE)
      aParams.add ("lengthChange", m_bLengthChange);
    if (m_bOrdering != DEFAULT_ORDERING)
      aParams.add ("ordering", m_bOrdering);
    if (m_bPaging != DEFAULT_PAGING)
      aParams.add ("paging", m_bPaging);
    if (m_bProcessing != DEFAULT_PROCESSING)
      aParams.add ("processing", m_bProcessing);
    if (m_eScrollX.isDefined ())
      aParams.add ("scrollX", m_eScrollX.getAsBooleanValue (DEFAULT_SCROLL_X));
    if (m_aScrollY != null)
      aParams.add ("scrollY", m_aScrollY);
    if (m_bSearching != DEFAULT_SEARCHING)
      aParams.add ("searching", m_bSearching);
    if (m_bStateSave != DEFAULT_STATE_SAVE)
      aParams.add ("stateSave", m_bStateSave);

    //
    // data
    //

    // Server handling parameters
    if (isServerSide ())
    {
      aParams.add ("serverSide", true);
      // This copies the content of the table
      final DataTablesServerData aServerData = new DataTablesServerData (m_aTable,
                                                                         m_aColumnDefs,
                                                                         m_aDisplayLocale,
                                                                         m_eServerFilterType);
      UIStateRegistry.getCurrent ().registerState (m_aTable.getID (), aServerData);
      // Remove all body rows to avoid initial double painting, as the most
      // reasonable state is retrieved from the server!
      m_aTable.removeAllBodyRows ();

      final JSAnonymousFunction aAF = new JSAnonymousFunction ();
      final JSVar aData = aAF.param ("data");
      final JSVar aCallback = aAF.param ("callback");
      aAF.param ("settings");
      aAF.body ()
         .add (m_aAjaxBuilder.getClone ()
                             .data (JQuery.extend ().arg (aData).arg (m_aAjaxBuilder.data ()))
                             .success (JSJQueryHelper.jqueryAjaxSuccessHandler (aCallback, null))
                             .build ());
      aParams.add ("ajax", aAF);
      JSJQueryHelper.registerExternalResources ();
    }
    else
    {
      aParams.add ("serverSide", false);
    }

    //
    // callbacks
    //
    if (m_aFooterCallback != null)
      aParams.add ("footerCallback", m_aFooterCallback);
    if (m_aHeaderCallback != null)
      aParams.add ("headerCallback", m_aHeaderCallback);
    if (m_aInitComplete != null)
      aParams.add ("initComplete", m_aInitComplete);

    //
    // options
    //

    if (m_bDeferLoading != DEFAULT_DEFER_LOADING)
      aParams.add ("deferLoading", m_bDeferLoading);
    if (m_bDestroy != DEFAULT_DESTROY)
      aParams.add ("destroy", m_bDestroy);
    if (m_nDisplayStart != DEFAULT_DISPLAY_START)
      aParams.add ("displayStart", m_nDisplayStart);
    if (m_aDom != null)
      aParams.add ("dom", m_aDom.getAsString ());
    if (m_aLengthMenu != null && !m_aLengthMenu.isEmpty ())
    {
      final Locale aRealLocale = m_aDisplayLocale != null ? m_aDisplayLocale : Locale.US;
      aParams.add ("lengthMenu", m_aLengthMenu.getAsJSArray (aRealLocale));
    }
    // Provide any empty array if no sorting is defined, because otherwise an
    // implicit sorting of the first column, ascending is done
    aParams.add ("order", m_aInitialOrder != null ? m_aInitialOrder.getAsJS () : new JSArray ());
    if (m_nPageLength != DEFAULT_PAGE_LENGTH && m_bLengthChange)
      aParams.add ("pageLength", m_nPageLength);
    if (m_ePagingType != null && m_bPaging)
      aParams.add ("pagingType", m_ePagingType.getName ());
    if (m_bRetrieve != DEFAULT_RETRIEVE)
      aParams.add ("retrieve", m_bRetrieve);
    if (m_bScrollCollapse != DEFAULT_SCROLL_COLLAPSE)
      aParams.add ("scrollCollapse", m_bScrollCollapse);

    //
    // DataTables - Columns
    //
    if (!m_aColumnDefs.isEmpty ())
    {
      final JSArray aArray = new JSArray ();
      for (final DataTablesColumnDef aColumnDef : m_aColumnDefs)
        aArray.add (aColumnDef.getAsJS ());
      aParams.add ("columnDefs", aArray);
    }

    //
    // DataTables - Internationalisation
    //

    if (m_aDisplayLocale != null)
    {
      // Must be an IJson because the language information is also retrieved via
      // AJAX!
      IJsonObject aLanguage;
      if (m_aTextLoadingURL != null)
      {
        // Load texts from there
        final SimpleURL aFinalURL = new SimpleURL (m_aTextLoadingURL).add (m_sTextLoadingURLLocaleParameterName,
                                                                           m_aDisplayLocale.getLanguage ());
        aLanguage = new JsonObject ().add ("url", aFinalURL.getAsStringWithEncodedParameters ());
      }
      else
      {
        // Inline texts
        aLanguage = createLanguageJson (m_aDisplayLocale);
      }
      aParams.add ("language", aLanguage);
    }

    //
    // DataTables - Plugins
    //
    for (final IDataTablesPlugin aPlugin : aRelevantPlugins)
    {
      final IJSExpression aInitParams = aPlugin.getInitParams ();
      if (aInitParams != null)
        aParams.add (aPlugin.getName (), aInitParams);
    }

    //
    // rest
    //

    if (hasAnyInvisibleColumn ())
    {
      // Remove all columns as this breaks the rendering
      // Note: do it after server side processing!
      m_aTable.removeAllColumns ();
    }

    modifyParams (aParams);

    // main on document ready code
    final JSPackage aJSCode = new JSPackage ();

    if (m_aJSBeforeModifier != null)
      m_aJSBeforeModifier.accept (aJSCode);

    final JSVar aJSTable = aJSCode.variable (m_sJSVariableName, invokeDataTables ().arg (aParams));

    // Add plugin init JS
    for (final IDataTablesPlugin aPlugin : aRelevantPlugins)
      aPlugin.addInitJS (this, aJSCode, aJSTable);

    if (m_aJSAfterModifier != null)
      m_aJSAfterModifier.accept (aJSCode, aJSTable);

    // Main HTML code for this element :)
    setJSCodeProvider (m_bGenerateOnDocumentReady ? HCSettings.getOnDocumentReadyProvider ()
                                                              .createOnDocumentReady (aJSCode)
                                                  : aJSCode);

    // Must be called AFTER we set the JS!
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForceRegistration)
  {
    super.onRegisterExternalResources (aConversionSettings, bForceRegistration);
    PhotonJS.registerJSIncludeForThisRequest (EDataTablesJSPathProvider.DATATABLES_1_13);
    PhotonCSS.registerCSSIncludeForThisRequest (EDataTablesCSSPathProvider.DATATABLES_1_13);

    for (final IDataTablesPlugin aPlugin : m_aPlugins.values ())
      if (aPlugin.canBeApplied (this))
        aPlugin.registerExternalResources (aConversionSettings);
  }
}
