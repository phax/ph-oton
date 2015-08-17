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
package com.helger.photon.uictrls.datatables;

import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.commons.lang.CloneHelper;
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
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.core.state.UIStateRegistry;
import com.helger.photon.uicore.EUICoreJSPathProvider;
import com.helger.photon.uicore.js.JSJQueryHelper;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;
import com.helger.photon.uictrls.datatables.ajax.DataTablesServerData;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.datatables.column.DataTablesColumnDef;

@OutOfBandNode
public class DataTables extends AbstractHCScriptInline <DataTables>
{
  public static final boolean DEFAULT_AUTOWIDTH = true;
  public static final boolean DEFAULT_DEFER_RENDER = false;
  public static final boolean DEFAULT_JQUERY_UI = false;
  public static final boolean DEFAULT_PAGING = true;
  public static final boolean DEFAULT_SCROLL_X = false;
  public static final boolean DEFAULT_STATE_SAVE = false;

  public static final boolean DEFAULT_SCROLL_COLLAPSE = false;
  public static final EDataTablesPagingType DEFAULT_PAGING_TYPE = EDataTablesPagingType.SIMPLE_NUMBERS;
  public static final int DEFAULT_PAGE_LENGTH = 10;
  public static final boolean DEFAULT_USE_COL_VIS = false;
  public static final boolean DEFAULT_USE_FIXED_HEADER = false;
  public static final boolean DEFAULT_USE_SEARCH_HIGHLIGHT = false;
  public static final boolean DEFAULT_USE_SCROLLER = false;

  private static final Logger s_aLogger = LoggerFactory.getLogger (DataTables.class);

  // Constructor parameters
  private final IHCTable <?> m_aTable;
  private final int m_nGeneratedJSVariableSuffix = GlobalIDFactory.getNewIntID ();
  private final String m_sGeneratedJSVariableName = "dt" + m_nGeneratedJSVariableSuffix;

  //
  // DataTables - Features
  //

  private boolean m_bAutoWidth = DEFAULT_AUTOWIDTH;
  private boolean m_bDeferRender = DEFAULT_DEFER_RENDER;
  // missing info:boolean [true]
  private boolean m_bJQueryUI = DEFAULT_JQUERY_UI;
  // missing lengthChange:boolean [true]
  // missing ordering:boolean [true]
  private boolean m_bPaging = DEFAULT_PAGING;
  // missing processing:boolean [false]
  private ETriState m_eScrollX = ETriState.UNDEFINED;
  private IJSExpression m_aScrollY;
  // missing searching:boolean [true]
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
  // missing initComplete
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
  // missing deferLoading:boolean [false]
  /**
   * Destroy any existing table matching the selector and replace with the new
   * options.
   */
  // missing destroy:boolean [false]
  /** Initial paging start point. */
  // missing displayStart:int [0]
  /**
   * Define the table control elements to appear on the page and in what order.
   */
  private DataTablesDom m_aDom;
  /** Change the options in the page length select list. */
  private DataTablesLengthMenu m_aLengthMenu;
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
  // missing retrieve:boolean [false]
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
  private final List <DataTablesColumnDef> m_aColumnDefs = new ArrayList <DataTablesColumnDef> ();

  //
  // DataTables - Internationalisation
  //
  private Locale m_aDisplayLocale;
  private ISimpleURL m_aTextLoadingURL;
  private String m_sTextLoadingURLLocaleParameterName;

  //
  // DataTables - Plugins
  //
  private final Map <String, IDataTablesPlugin> m_aPlugins = new LinkedHashMap <> ();

  // Custom properties
  private boolean m_bGenerateOnDocumentReady = DataTablesSettings.isDefaultGenerateOnDocumentReady ();
  private EDataTablesFilterType m_eServerFilterType = EDataTablesFilterType.DEFAULT;

  // ColVis stuff
  private boolean m_bUseColVis = DEFAULT_USE_COL_VIS;

  // FixedHeader stuff
  private boolean m_bUseFixedHeader = DEFAULT_USE_FIXED_HEADER;
  private JSAssocArray m_aFixedHeaderOptions;

  // Search Highlight stuff
  private boolean m_bUseSearchHighlight = DEFAULT_USE_SEARCH_HIGHLIGHT;

  // Scroller plugin stuff
  private boolean m_bUseScroller = DEFAULT_USE_SCROLLER;

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
        s_aLogger.warn ("Table does not have a header row so DataTables may not be displayed correctly!");
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
    return m_sGeneratedJSVariableName;
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
  public DataTables setScrollY (@Nullable final String sScrollY)
  {
    return setScrollY (JSExpr.lit (sScrollY));
  }

  @Nonnull
  public DataTables setScrollY (@Nullable final IJSExpression aScrollY)
  {
    m_aScrollY = aScrollY;
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

  //
  // DataTables - Options
  //

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
  @ReturnsMutableObject ("design")
  public DataTablesLengthMenu getLengthMenu ()
  {
    return m_aLengthMenu;
  }

  @Nonnull
  public DataTables setLengthMenu (@Nullable final DataTablesLengthMenu aLengthMenu)
  {
    m_aLengthMenu = aLengthMenu;
    if (aLengthMenu != null && !aLengthMenu.isEmpty ())
      setPageLength (aLengthMenu.getItemAtIndex (0).getItemCount ());
    return this;
  }

  @Nullable
  public DataTablesOrder getInitialOrder ()
  {
    return m_aInitialOrder;
  }

  @Nonnull
  @Deprecated
  public DataTables setInitialOrder (@Nonnegative final int nIndex, @Nonnull final ESortOrder eSortOrder)
  {
    return setInitialOrder (new DataTablesOrder ().addColumn (nIndex, eSortOrder));
  }

  @Nonnull
  public DataTables setInitialOrder (@Nullable final DataTablesOrder aInitialOrder)
  {
    m_aInitialOrder = aInitialOrder;
    return this;
  }

  @Nonnegative
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
  @ReturnsMutableCopy
  public Collection <DataTablesColumnDef> getAllColumns ()
  {
    return CollectionHelper.newList (m_aColumnDefs);
  }

  public boolean hasColumns ()
  {
    return !m_aColumnDefs.isEmpty ();
  }

  @Nonnegative
  public int getColumnCount ()
  {
    return m_aColumnDefs.size ();
  }

  @Nonnull
  @Deprecated
  public DataTablesColumnDef getOrCreateColumnOfTarget (@Nonnegative final int nTarget)
  {
    for (final DataTablesColumnDef aCurColumn : m_aColumnDefs)
      if (aCurColumn.hasTarget (nTarget))
        return aCurColumn;

    final DataTablesColumnDef aColumn = new DataTablesColumnDef (nTarget);
    m_aColumnDefs.add (aColumn);
    return aColumn;
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
          s_aLogger.warn ("Another DataTablesColumn with target " + nTarget + " is already contained!");
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

  @Nullable
  public IDataTablesPlugin getPluginOfName (@Nullable final String sName)
  {
    if (StringHelper.hasNoText (sName))
      return null;
    return m_aPlugins.get (sName);
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

  public boolean isUseColVis ()
  {
    return m_bUseColVis;
  }

  @Nonnull
  public DataTables setUseColVis (final boolean bUseColVis)
  {
    m_bUseColVis = bUseColVis;
    return this;
  }

  public boolean isUseFixedHeader ()
  {
    return m_bUseFixedHeader;
  }

  @Nonnull
  public DataTables setUseFixedHeader (final boolean bUseFixedHeader)
  {
    m_bUseFixedHeader = bUseFixedHeader;
    return this;
  }

  @Nullable
  @ReturnsMutableObject ("design")
  public JSAssocArray getFixedHeaderOptions ()
  {
    return m_aFixedHeaderOptions;
  }

  @Nonnull
  public DataTables setFixedHeaderOptions (@Nullable final JSAssocArray aFixedHeaderOptions)
  {
    m_aFixedHeaderOptions = aFixedHeaderOptions;
    return this;
  }

  public boolean isUseSearchHighlight ()
  {
    return m_bUseSearchHighlight;
  }

  @Nonnull
  public DataTables setUseSearchHighlight (final boolean bUseSearchHighlight)
  {
    m_bUseSearchHighlight = bUseSearchHighlight;
    return this;
  }

  public boolean isUseScroller ()
  {
    return m_bUseScroller;
  }

  /**
   * Enable or disable the scroller plugin.
   *
   * @param bUseScroller
   *        <code>true</code> to enable it, <code>false</code> to disable it.
   * @param sScrollHeight
   *        The CSS height definition for the height of the table.
   * @return this
   */
  @Nonnull
  public DataTables setUseScroller (final boolean bUseScroller, @Nullable final String sScrollHeight)
  {
    if (bUseScroller)
      ValueEnforcer.notEmpty (sScrollHeight, "ScrollHeight");

    m_bUseScroller = bUseScroller;
    setScrollCollapse (true);
    setScrollY (sScrollHeight);
    setDeferRender (bUseScroller);
    // Activate the Scroller extra
    if (bUseScroller)
      m_aDom.addScroller ();
    else
      m_aDom.removeScroller ();
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

  /**
   * Add JS code before the data tables init is called
   *
   * @param aPackage
   *        The JS Package to add the code to
   */
  @OverrideOnDemand
  protected void addCodeBeforeDataTables (@Nonnull final JSPackage aPackage)
  {}

  /**
   * Add JS code before the data tables init is called
   *
   * @param aPackage
   *        The JS Package to add the code to
   * @param aJSTable
   *        The JS reference to the created DataTables object
   */
  @OverrideOnDemand
  protected void addCodeAfterDataTables (@Nonnull final JSPackage aPackage, @Nonnull final JSVar aJSTable)
  {}

  @Nonnull
  public static IJsonObject createLanguageJson (@Nonnull final Locale aDisplayLocale)
  {
    final JsonObject aLanguage = new JsonObject ();
    aLanguage.add ("aria",
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
    aLanguage.add ("paginate",
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

  @OverrideOnDemand
  protected void weaveColVisIntoDom (@Nonnull final DataTablesDom aDom)
  {
    aDom.setPosition (0).addColVis ().openDiv ("clear").closeDiv ();
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);

    // Determine all applicable plugins
    final List <IDataTablesPlugin> aRelevantPlugins = new ArrayList <> ();
    for (final IDataTablesPlugin aPlugin : m_aPlugins.values ())
      if (aPlugin.canBeApplied (this))
        aRelevantPlugins.add (aPlugin);
      else
        s_aLogger.warn ("Plugin '" + aPlugin.getName () + "' cannot be applied to DataTable " + getTableID ());

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
    if (m_bJQueryUI != DEFAULT_JQUERY_UI)
      aParams.add ("jQueryUI", m_bJQueryUI);
    if (m_bPaging != DEFAULT_PAGING)
      aParams.add ("paging", m_bPaging);
    if (m_eScrollX.isDefined ())
      aParams.add ("scrollX", m_eScrollX.getAsBooleanValue (DEFAULT_SCROLL_X));
    if (m_aScrollY != null)
      aParams.add ("scrollY", m_aScrollY);
    if (m_bStateSave != DEFAULT_STATE_SAVE)
      aParams.add ("stateSave", m_bStateSave);

    //
    // data
    //

    // Server handling parameters
    final boolean bServerSide = m_aAjaxBuilder != null;
    if (bServerSide)
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
      final JSVar aData = aAF.param ("d");
      final JSVar aCallback = aAF.param ("cb");
      aAF.param ("s");
      m_aAjaxBuilder.success (JSJQueryHelper.jqueryAjaxSuccessHandler (aCallback, null));
      m_aAjaxBuilder.data (JQuery.extend ().arg (aData).arg (m_aAjaxBuilder.data ()));
      aAF.body ().add (m_aAjaxBuilder.build ());
      aParams.add ("ajax", aAF);
      JSJQueryHelper.registerExternalResources ();
    }

    //
    // callbacks
    //
    if (m_aFooterCallback != null)
      aParams.add ("footerCallback", m_aFooterCallback);
    if (m_aHeaderCallback != null)
      aParams.add ("headerCallback", m_aHeaderCallback);

    //
    // options
    //

    {
      DataTablesDom aDom = m_aDom;
      if (m_bUseColVis)
      {
        if (aDom == null)
          aDom = new DataTablesDom ();
        weaveColVisIntoDom (aDom);
      }
      if (aDom != null)
        aParams.add ("dom", aDom.getAsString ());
    }
    if (m_aLengthMenu != null && !m_aLengthMenu.isEmpty ())
    {
      final Locale aRealLocale = m_aDisplayLocale != null ? m_aDisplayLocale : Locale.US;
      aParams.add ("lengthMenu", m_aLengthMenu.getAsJSArray (aRealLocale));
    }
    // Provide any empty array if no sorting is defined, because otherwise an
    // implicit sorting of the first column, ascending is done
    aParams.add ("order", m_aInitialOrder != null ? m_aInitialOrder.getAsJS () : new JSArray ());
    if (m_nPageLength != DEFAULT_PAGE_LENGTH)
      aParams.add ("pageLength", m_nPageLength);
    if (m_ePagingType != null)
      aParams.add ("pagingType", m_ePagingType.getName ());
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
      IJsonObject aLanguage;
      if (m_aTextLoadingURL != null)
      {
        // Load texts from there
        final SimpleURL aFinalURL = new SimpleURL (m_aTextLoadingURL).add (m_sTextLoadingURLLocaleParameterName,
                                                                           m_aDisplayLocale.getLanguage ());
        aLanguage = new JsonObject ().add ("url", aFinalURL.getAsString ());
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
      aParams.add (aPlugin.getName (), aPlugin.getInitParams ());

    //
    // rest
    //

    if (hasAnyInvisibleColumn ())
    {
      // Remove all columns as this breaks the rendering
      // Note: do it after server side processing!
      m_aTable.removeAllColumns ();
    }

    // ColVis stuff
    if (m_bUseColVis)
    {
      final JSAssocArray aJSColVisParams = new JSAssocArray ();
      if (m_aDisplayLocale != null)
        aJSColVisParams.add ("buttonText", EDataTablesText.COL_VIS_BUTTON_TEXT.getDisplayText (m_aDisplayLocale));

      final JSArray aExclude = new JSArray ();
      for (final DataTablesColumnDef aColumn : m_aColumnDefs)
        if (!aColumn.isVisible ())
          aExclude.addAll (aColumn.getAllTargets ());
      aJSColVisParams.add ("exclude", aExclude);

      aParams.add ("colVis", aJSColVisParams);
    }

    // Search highlight stuff
    if (m_bUseSearchHighlight)
      aParams.add ("searchHighlight", true);

    modifyParams (aParams);

    // main on document ready code
    final JSPackage aJSCode = new JSPackage ();

    addCodeBeforeDataTables (aJSCode);

    final JSVar aJSTable = aJSCode.var (m_sGeneratedJSVariableName, invokeDataTables ().arg (aParams));

    // Finalize plugins
    for (final IDataTablesPlugin aPlugin : aRelevantPlugins)
      aPlugin.addInitJS (this, aJSCode, aJSTable);

    if (m_bUseFixedHeader)
    {
      final JSInvocation aJSFixedHeader = new JSInvocation ("new FixedHeader").arg (aJSTable);
      if (m_aFixedHeaderOptions != null)
        aJSFixedHeader.arg (m_aFixedHeaderOptions);
      aJSCode.add (aJSFixedHeader);
    }
    if (m_bUseScroller)
    {
      // See http://legacy.datatables.net/ref#fnAdjustColumnSizing
      aJSCode.add (JQuery.jQueryWindow ().bind ("resize",
                                                new JSAnonymousFunction (aJSTable.invoke ("fnAdjustColumnSizing"))));
    }
    addCodeAfterDataTables (aJSCode, aJSTable);

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
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.DATATABLES_1_10);
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.DATATABLES_1_10);
    if (m_bUseColVis)
    {
      PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.DATATABLES_COL_VIS);
      PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.DATATABLES_COL_VIS);
    }
    if (m_bUseFixedHeader)
    {
      PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.DATATABLES_FIXED_HEADER);
      PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.DATATABLES_FIXED_HEADER);
    }
    if (m_bUseSearchHighlight)
    {
      PhotonJS.registerJSIncludeForThisRequest (EUICoreJSPathProvider.JQUERY_HIGHLIGHT);
      PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.DATATABLES_SEARCH_HIGHLIGHT);
      PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.DATATABLES_SEARCH_HIGHLIGHT);
    }
    if (m_bUseScroller)
    {
      PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.DATATABLES_SCROLLER);
      PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.DATATABLES_SCROLLER);
    }
    for (final IDataTablesPlugin aPlugin : m_aPlugins.values ())
      if (aPlugin.canBeApplied (this))
        aPlugin.registerExternalResources (aConversionSettings);
  }
}
