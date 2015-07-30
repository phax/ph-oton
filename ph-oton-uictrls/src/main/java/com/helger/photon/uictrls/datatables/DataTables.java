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
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.commons.lang.CloneHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.annotation.OutOfBandNode;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hcapi.IHCConversionSettingsToNode;
import com.helger.html.hcapi.IHCHasChildrenMutable;
import com.helger.html.hcapi.IHCNode;
import com.helger.html.hchtml.script.AbstractHCScriptInline;
import com.helger.html.hchtml.script.HCScriptInline;
import com.helger.html.hchtml.script.HCScriptInlineOnDocumentReady;
import com.helger.html.hchtml.tabular.HCCol;
import com.helger.html.hchtml.tabular.HCColGroup;
import com.helger.html.hchtml.tabular.IHCCol;
import com.helger.html.hchtml.tabular.IHCTable;
import com.helger.html.jquery.JQuery;
import com.helger.html.jquery.JQueryAjaxBuilder;
import com.helger.html.jquery.JQueryInvocation;
import com.helger.html.jquery.JQuerySelector;
import com.helger.html.jquery.JQuerySelectorList;
import com.helger.html.js.IHasJSCode;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSBlock;
import com.helger.html.jscode.JSConditional;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSInvocation;
import com.helger.html.jscode.JSPackage;
import com.helger.html.jscode.JSRef;
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
import com.helger.web.http.EHTTPMethod;

@OutOfBandNode
public class DataTables extends AbstractHCScriptInline <DataTables>
{
  public static final boolean DEFAULT_GENERATE_ON_DOCUMENT_READY = false;
  public static final boolean DEFAULT_AUTOWIDTH = true;
  public static final boolean DEFAULT_PAGINATE = true;
  public static final boolean DEFAULT_STATE_SAVE = false;
  public static final boolean DEFAULT_JQUERY_UI = false;
  public static final boolean DEFAULT_SCROLL_COLLAPSE = false;
  public static final boolean DEFAULT_USER_JQUERY_AJAX = false;
  public static final boolean DEFAULT_DEFER_RENDER = false;
  public static final EDataTablesPaginationType DEFAULT_PAGINATION_TYPE = EDataTablesPaginationType.FULL_NUMBERS;
  public static final int DEFAULT_DISPLAY_LENGTH = 10;
  public static final boolean DEFAULT_USE_COL_VIS = false;
  public static final boolean DEFAULT_USE_FIXED_HEADER = false;
  public static final boolean DEFAULT_USE_SEARCH_HIGHLIGHT = false;
  public static final boolean DEFAULT_USE_SCROLLER = false;

  private static final Logger s_aLogger = LoggerFactory.getLogger (DataTables.class);
  private static boolean s_bDefaultGenerateOnDocumentReady = DEFAULT_GENERATE_ON_DOCUMENT_READY;

  private final IHCTable <?> m_aTable;
  private boolean m_bGenerateOnDocumentReady = s_bDefaultGenerateOnDocumentReady;
  private Locale m_aDisplayLocale;
  private boolean m_bAutoWidth = DEFAULT_AUTOWIDTH;
  private boolean m_bPaging = DEFAULT_PAGINATE;
  private boolean m_bStateSave = DEFAULT_STATE_SAVE;
  private boolean m_bJQueryUI = DEFAULT_JQUERY_UI;
  private final List <DataTablesColumn> m_aColumns = new ArrayList <DataTablesColumn> ();
  private DataTablesSorting m_aInitialSorting;
  private EDataTablesPaginationType m_ePagingType = DEFAULT_PAGINATION_TYPE;
  private String m_sScrollX;
  private String m_sScrollY;
  private boolean m_bScrollCollapse = DEFAULT_SCROLL_COLLAPSE;
  private DataTablesLengthMenuList m_aLengthMenu;
  private int m_nPageLength = DEFAULT_DISPLAY_LENGTH;
  private DataTablesDom m_aDom;
  // server side processing
  private ISimpleURL m_aAjaxSource;
  private EHTTPMethod m_eServerMethod;
  private Map <String, String> m_aServerParams;
  private boolean m_bUseJQueryAjax = DEFAULT_USER_JQUERY_AJAX;
  private EDataTablesFilterType m_eServerFilterType = EDataTablesFilterType.DEFAULT;
  private boolean m_bDeferRender = DEFAULT_DEFER_RENDER;
  private final int m_nGeneratedJSVariableSuffix = GlobalIDFactory.getNewIntID ();
  private final String m_sGeneratedJSVariableName = "oTable" + m_nGeneratedJSVariableSuffix;
  private ISimpleURL m_aTextLoadingURL;
  private String m_sTextLoadingURLLocaleParameterName;
  private Map <String, String> m_aTextLoadingParams;
  private JSAnonymousFunction m_aHeaderCallback;
  private JSAnonymousFunction m_aFooterCallback;

  // ColVis stuff
  private boolean m_bUseColVis = DEFAULT_USE_COL_VIS;

  // FixedHeader stuff
  private boolean m_bUseFixedHeader = DEFAULT_USE_FIXED_HEADER;
  private JSAssocArray m_aFixedHeaderOptions;

  // Search Highlight stuff
  private boolean m_bUseSearchHighlight = DEFAULT_USE_SEARCH_HIGHLIGHT;

  // Scroller plugin stuff
  private boolean m_bUseScroller = DEFAULT_USE_SCROLLER;

  public static boolean isDefaultGenerateOnDocumentReady ()
  {
    return s_bDefaultGenerateOnDocumentReady;
  }

  public static void setDefaultGenerateOnDocumentReady (final boolean bGenerateOnDocumentReady)
  {
    s_bDefaultGenerateOnDocumentReady = bGenerateOnDocumentReady;
  }

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

  public boolean isPaginate ()
  {
    return m_bPaging;
  }

  @Nonnull
  public DataTables setPaginate (final boolean bPaginate)
  {
    m_bPaging = bPaginate;
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

  @Nonnull
  @ReturnsMutableCopy
  public Collection <DataTablesColumn> getAllColumns ()
  {
    return CollectionHelper.newList (m_aColumns);
  }

  public boolean hasColumns ()
  {
    return !m_aColumns.isEmpty ();
  }

  @Nonnegative
  public int getColumnCount ()
  {
    return m_aColumns.size ();
  }

  @Nonnull
  @Deprecated
  public DataTablesColumn getOrCreateColumnOfTarget (@Nonnegative final int nTarget)
  {
    for (final DataTablesColumn aCurColumn : m_aColumns)
      if (aCurColumn.hasTarget (nTarget))
        return aCurColumn;

    final DataTablesColumn aColumn = new DataTablesColumn (nTarget);
    m_aColumns.add (aColumn);
    return aColumn;
  }

  @Nonnull
  public DataTables addColumn (@Nonnull final DataTablesColumn aColumn)
  {
    ValueEnforcer.notNull (aColumn, "Column");

    // Check if targets are unique!
    for (final int nTarget : aColumn.getAllTargets ())
      for (final DataTablesColumn aCurColumn : m_aColumns)
        if (aCurColumn.hasTarget (nTarget))
        {
          s_aLogger.warn ("Another DataTablesColumn with target " + nTarget + " is already contained!");
          break;
        }
    m_aColumns.add (aColumn);
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
        DataTablesColumn aColumn;
        if (aCol instanceof DTCol)
        {
          // Copy data from DTColumn
          final DTCol aDTCol = (DTCol) aCol;
          aColumn = new DataTablesColumn (nColIndex, aDTCol);
          if (aDTCol.hasInitialSorting ())
            setInitialSorting (new DataTablesSorting ().addColumn (nColIndex, aDTCol.getInitialSorting ()));
        }
        else
        {
          // Raw column
          aColumn = new DataTablesColumn (nColIndex);
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
  public DataTables setAllColumnsSortable (final boolean bSortable)
  {
    for (final DataTablesColumn aColumn : m_aColumns)
      aColumn.setSortable (bSortable);
    return this;
  }

  @Nonnull
  public DataTables setAllColumnsSearchable (final boolean bSearchable)
  {
    for (final DataTablesColumn aColumn : m_aColumns)
      aColumn.setSearchable (bSearchable);
    return this;
  }

  public boolean hasAnyInvisibleColumn ()
  {
    for (final DataTablesColumn aColumn : m_aColumns)
      if (!aColumn.isVisible ())
        return true;
    return false;
  }

  @Nonnegative
  public int getVisibleColumnCount ()
  {
    int ret = 0;
    for (final DataTablesColumn aColumn : m_aColumns)
      if (aColumn.isVisible ())
        ++ret;
    return ret;
  }

  @Nullable
  public DataTablesSorting getInitialSorting ()
  {
    return m_aInitialSorting;
  }

  @Nonnull
  @Deprecated
  public DataTables setInitialSorting (@Nonnegative final int nIndex, @Nonnull final ESortOrder eSortOrder)
  {
    return setInitialSorting (new DataTablesSorting ().addColumn (nIndex, eSortOrder));
  }

  @Nonnull
  public DataTables setInitialSorting (@Nullable final DataTablesSorting aInitialSorting)
  {
    m_aInitialSorting = aInitialSorting;
    return this;
  }

  @Nullable
  public EDataTablesPaginationType getPaginationType ()
  {
    return m_ePagingType;
  }

  @Nonnull
  public DataTables setPaginationType (@Nullable final EDataTablesPaginationType ePaginationType)
  {
    m_ePagingType = ePaginationType;
    return this;
  }

  @Nullable
  public String getScrollX ()
  {
    return m_sScrollX;
  }

  @Nonnull
  public DataTables setScrollX (@Nullable final String sScrollX)
  {
    m_sScrollX = sScrollX;
    return this;
  }

  @Nullable
  public String getScrollY ()
  {
    return m_sScrollY;
  }

  @Nonnull
  public DataTables setScrollY (@Nullable final String sScrollY)
  {
    m_sScrollY = sScrollY;
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

  @Nullable
  @ReturnsMutableCopy
  public DataTablesDom getDom ()
  {
    return CloneHelper.getCloneIfNotNull (m_aDom);
  }

  @Nonnull
  public DataTables setDom (@Nullable final DataTablesDom aDom)
  {
    m_aDom = CloneHelper.getCloneIfNotNull (aDom);
    return this;
  }

  @Nullable
  @ReturnsMutableObject ("design")
  public DataTablesLengthMenuList getLengthMenu ()
  {
    return m_aLengthMenu;
  }

  @Nonnull
  public DataTables setLengthMenu (@Nullable final DataTablesLengthMenuList aLengthMenu)
  {
    m_aLengthMenu = aLengthMenu;
    if (aLengthMenu != null && !aLengthMenu.isEmpty ())
      setDisplayLength (aLengthMenu.getItemAtIndex (0).getItemCount ());
    return this;
  }

  @Nonnegative
  public int getDisplayLength ()
  {
    return m_nPageLength;
  }

  /**
   * Set to -1 to show all
   *
   * @param nDisplayLength
   *        Number of items to display per page
   * @return this
   */
  @Nonnull
  public DataTables setDisplayLength (final int nDisplayLength)
  {
    m_nPageLength = nDisplayLength;
    return this;
  }

  @Nonnull
  public DataTables setDisplayAll ()
  {
    return setDisplayLength (DataTablesLengthMenuList.COUNT_ALL);
  }

  // Server side handling params

  @Nullable
  public ISimpleURL getAjaxSource ()
  {
    return m_aAjaxSource;
  }

  @Nonnull
  public DataTables setAjaxSource (@Nullable final ISimpleURL aAjaxSource)
  {
    m_aAjaxSource = aAjaxSource;
    return this;
  }

  @Nullable
  public EHTTPMethod getServerMethod ()
  {
    return m_eServerMethod;
  }

  @Nonnull
  public DataTables setServerMethod (@Nullable final EHTTPMethod eServerMethod)
  {
    m_eServerMethod = eServerMethod;
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Map <String, String> getAllServerParams ()
  {
    return CollectionHelper.newMap (m_aServerParams);
  }

  @Nonnull
  public DataTables setServerParams (@Nullable final Map <String, String> aServerParams)
  {
    m_aServerParams = aServerParams;
    return this;
  }

  public boolean isUseJQueryAjax ()
  {
    return m_bUseJQueryAjax;
  }

  @Nonnull
  public DataTables setUseJQueryAjax (final boolean bUseJQueryAjax)
  {
    m_bUseJQueryAjax = bUseJQueryAjax;
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

  @Nonnull
  @ReturnsMutableCopy
  public Map <String, String> getAllTextLoadingParams ()
  {
    return CollectionHelper.newMap (m_aTextLoadingParams);
  }

  @Nonnull
  public DataTables setTextLoadingParams (@Nullable final Map <String, String> aTextLoadingParams)
  {
    m_aTextLoadingParams = aTextLoadingParams;
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
                                          EDataTablesText.SORT_ASCENDING.getDisplayText (aDisplayLocale))
                                    .add ("sortDescending",
                                          EDataTablesText.SORT_DESCENDING.getDisplayText (aDisplayLocale)));
    // Translate??
    aLanguage.add ("sDecimal", DecimalFormatSymbols.getInstance (aDisplayLocale).getDecimalSeparator ());
    aLanguage.add ("emptyTable", EDataTablesText.EMPTY_TABLE.getDisplayText (aDisplayLocale));
    aLanguage.add ("info", EDataTablesText.INFO.getDisplayText (aDisplayLocale));
    aLanguage.add ("infoEmpty", EDataTablesText.INFO_EMPTY.getDisplayText (aDisplayLocale));
    aLanguage.add ("infoFiltered", EDataTablesText.INFO_FILTERED.getDisplayText (aDisplayLocale));
    aLanguage.add ("infoPostFix", EDataTablesText.INFO_POSTFIX.getDisplayText (aDisplayLocale));
    aLanguage.add ("lengthMenu", EDataTablesText.LENGTH_MENU.getDisplayText (aDisplayLocale));
    aLanguage.add ("loadingRecords", EDataTablesText.LOADING_RECORDS.getDisplayText (aDisplayLocale));
    aLanguage.add ("paginate",
                   new JsonObject ().add ("first", EDataTablesText.FIRST.getDisplayText (aDisplayLocale))
                                    .add ("last", EDataTablesText.LAST.getDisplayText (aDisplayLocale))
                                    .add ("next", EDataTablesText.NEXT.getDisplayText (aDisplayLocale))
                                    .add ("previous", EDataTablesText.PREVIOUS.getDisplayText (aDisplayLocale)));
    aLanguage.add ("processing", EDataTablesText.PROCESSING.getDisplayText (aDisplayLocale));
    aLanguage.add ("search", EDataTablesText.SEARCH.getDisplayText (aDisplayLocale));
    aLanguage.add ("thousands", EDataTablesText.INFO_THOUSANDS.getDisplayText (aDisplayLocale));
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

    // init parameters
    final JSAssocArray aParams = new JSAssocArray ();
    if (m_bAutoWidth != DEFAULT_AUTOWIDTH)
      aParams.add ("autoWidth", m_bAutoWidth);
    if (m_bPaging != DEFAULT_PAGINATE)
      aParams.add ("paging", m_bPaging);
    if (m_bStateSave != DEFAULT_STATE_SAVE)
      aParams.add ("stateSave", m_bStateSave);
    if (m_bJQueryUI != DEFAULT_JQUERY_UI)
      aParams.add ("jQueryUI", m_bJQueryUI);
    if (!m_aColumns.isEmpty ())
    {
      final JSArray aArray = new JSArray ();
      for (final DataTablesColumn aColumn : m_aColumns)
        aArray.add (aColumn.getAsJS ());
      aParams.add ("columnDefs", aArray);
    }
    // Provide any empty array if no sorting is defined, because otherwise an
    // implicit sorting of the first column, ascending is done
    aParams.add ("order", m_aInitialSorting != null ? m_aInitialSorting.getAsJS () : new JSArray ());
    if (m_ePagingType != null)
      aParams.add ("pagingType", m_ePagingType.getName ());
    if (StringHelper.hasText (m_sScrollX))
      aParams.add ("scrollX", m_sScrollX);
    if (StringHelper.hasText (m_sScrollY))
      aParams.add ("scrollY", m_sScrollY);
    if (m_bScrollCollapse != DEFAULT_SCROLL_COLLAPSE)
      aParams.add ("scrollCollapse", m_bScrollCollapse);
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
      final JSArray aArray1 = new JSArray ();
      final JSArray aArray2 = new JSArray ();
      for (final DataTablesLengthMenuItem aItem : m_aLengthMenu.getAllItems ())
      {
        aArray1.add (aItem.getItemCount ());
        final String sValue = aItem.getDisplayText (m_aDisplayLocale);
        if (sValue != null)
          aArray2.add (sValue);
        else
          aArray2.add (Integer.toString (aItem.getItemCount ()));
      }
      aParams.add ("lengthMenu", new JSArray ().add (aArray1).add (aArray2));
    }
    if (m_nPageLength != DEFAULT_DISPLAY_LENGTH)
      aParams.add ("pageLength", m_nPageLength);

    // Server handling parameters
    final boolean bServerSide = m_aAjaxSource != null;
    if (bServerSide)
    {
      aParams.add ("serverSide", true);
      // This copies the content of the table
      final DataTablesServerData aServerData = new DataTablesServerData (m_aTable,
                                                                         m_aColumns,
                                                                         m_aDisplayLocale,
                                                                         m_eServerFilterType);
      UIStateRegistry.getCurrent ().registerState (m_aTable.getID (), aServerData);
      // Remove all body rows to avoid initial double painting, as the most
      // reasonable state is retrieved from the server!
      m_aTable.removeAllBodyRows ();
    }

    if (hasAnyInvisibleColumn ())
    {
      if (true)
      {
        // Remove all columns as this breaks the rendering
        m_aTable.removeAllColumns ();
        if (false)
        {
          // Just a small test
          final int nVisibleColumnCount = getVisibleColumnCount ();
          for (int i = 0; i < nVisibleColumnCount; ++i)
            m_aTable.addColumn (new HCCol ());
        }
      }
      else
      {
        // Delete all columns from the colgroup that are invisible, because this
        // will break the rendered layout
        // Note: back to front, so that the index does not need to be modified
        // Note: disabled, as this may lead to HC table consistency warnings
        final HCColGroup aColGroup = m_aTable.getColGroup ();
        if (aColGroup != null)
          for (int i = m_aColumns.size () - 1; i >= 0; --i)
          {
            final DataTablesColumn aColumn = m_aColumns.get (i);
            if (!aColumn.isVisible ())
              aColGroup.removeColumnAtIndex (i);
          }
      }
    }

    if (m_aAjaxSource != null)
      aParams.add ("sAjaxSource", m_aAjaxSource.getAsString ());
    if (m_eServerMethod != null)
      aParams.add ("sServerMethod", m_eServerMethod.getName ());
    if (CollectionHelper.isNotEmpty (m_aServerParams))
    {
      final JSAnonymousFunction aAF = new JSAnonymousFunction ();
      final JSVar aData = aAF.param ("aoData");
      for (final Map.Entry <String, String> aEntry : m_aServerParams.entrySet ())
      {
        aAF.body ()
           .invoke (aData, "push")
           .arg (new JSAssocArray ().add ("name", aEntry.getKey ()).add ("value", aEntry.getValue ()));
      }
      aParams.add ("fnServerParams", aAF);
    }
    if (m_bUseJQueryAjax)
    {
      final JSAnonymousFunction aAF = new JSAnonymousFunction ();
      final JSVar sSource = aAF.param ("s");
      final JSVar aoData = aAF.param ("t");
      final JSVar fnCallback = aAF.param ("u");
      final JSVar oSettings = aAF.param ("v");
      final JQueryAjaxBuilder aAjaxBuilder = new JQueryAjaxBuilder ().cache (false)
                                                                     .dataType ("json")
                                                                     .type (m_eServerMethod == null ? null
                                                                                                    : m_eServerMethod.getName ())
                                                                     .url (sSource)
                                                                     .data (aoData)
                                                                     .success (JSJQueryHelper.jqueryAjaxSuccessHandler (fnCallback,
                                                                                                                        true));
      aAF.body ().assign (oSettings.ref ("jqXHR"), aAjaxBuilder.build ());
      aParams.add ("fnServerData", aAF);
      JSJQueryHelper.registerExternalResources ();
    }
    if (m_bDeferRender != DEFAULT_DEFER_RENDER)
      aParams.add ("deferRender", m_bDeferRender);

    // Display texts
    if (m_aDisplayLocale != null)
    {
      IJsonObject aLanguage;
      if (m_aTextLoadingURL != null)
      {
        // Load texts from there
        final SimpleURL aFinalURL = new SimpleURL (m_aTextLoadingURL).add (m_sTextLoadingURLLocaleParameterName,
                                                                           m_aDisplayLocale.getLanguage ());
        if (m_aTextLoadingParams != null)
          aFinalURL.addAll (m_aTextLoadingParams);
        aLanguage = new JsonObject ().add ("url", aFinalURL.getAsString ());
      }
      else
      {
        // Inline texts
        aLanguage = createLanguageJson (m_aDisplayLocale);
      }
      aParams.add ("language", aLanguage);
    }

    if (m_aHeaderCallback != null)
      aParams.add ("headerCallback", m_aHeaderCallback);
    if (m_aFooterCallback != null)
      aParams.add ("footerCallback", m_aFooterCallback);

    // ColVis stuff
    if (m_bUseColVis)
    {
      final JSAssocArray aJSColVisParams = new JSAssocArray ();
      if (m_aDisplayLocale != null)
        aJSColVisParams.add ("buttonText", EDataTablesText.COL_VIS_BUTTON_TEXT.getDisplayText (m_aDisplayLocale));

      final JSArray aExclude = new JSArray ();
      for (final DataTablesColumn aColumn : m_aColumns)
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
      PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.DATATABLES_EXTRAS_COL_VIS);
      PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.DATATABLES_EXTRAS_COL_VIS);
    }
    if (m_bUseFixedHeader)
    {
      PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.DATATABLES_EXTRAS_FIXED_HEADER);
      PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.DATATABLES_EXTRAS_FIXED_HEADER);
    }
    if (m_bUseSearchHighlight)
    {
      PhotonJS.registerJSIncludeForThisRequest (EUICoreJSPathProvider.JQUERY_HIGHLIGHT);
      PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.DATATABLES_SEARCH_HIGHLIGHT);
      PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.DATATABLES_SEARCH_HIGHLIGHT);
    }
    if (m_bUseScroller)
    {
      PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.DATATABLES_EXTRAS_SCROLLER);
      PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.DATATABLES_EXTRAS_SCROLLER);
    }
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

  /**
   * Create an {@link HCScriptInline} or {@link HCScriptInlineOnDocumentReady}
   * block that handles expand and collapse. The following pre-conditions must
   * be met: The first column must be the expand/collapse column and it must
   * contain an image where the event handler is registered.
   *
   * @param aExpandImgURL
   *        The URL of the expand icon (closed state)
   * @param aCollapseImgURL
   *        The URL of the collapse icon (open state)
   * @param nColumnIndexWithDetails
   *        The index of the column that contains the details. Must be &ge; 0
   *        and is usually hidden.
   * @param aCellClass
   *        The CSS class to be applied to the created cell. May be
   *        <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public IHCNode createExpandCollapseHandling (@Nonnull final ISimpleURL aExpandImgURL,
                                               @Nonnull final ISimpleURL aCollapseImgURL,
                                               @Nonnegative final int nColumnIndexWithDetails,
                                               @Nullable final ICSSClassProvider aCellClass)
  {
    final DataTablesColumn aColumn = getOrCreateColumnOfTarget (nColumnIndexWithDetails);
    if (aColumn != null && aColumn.isVisible ())
      s_aLogger.warn ("The column with the expand text, should not be visible!");

    final JSRef jsTable = JSExpr.ref (m_sGeneratedJSVariableName);

    final JSPackage aPackage = new JSPackage ();
    final JSAnonymousFunction aOpenCloseCallback = new JSAnonymousFunction ();
    {
      final JSVar jsTR = aOpenCloseCallback.body ().var ("r",
                                                         JQuery.jQueryThis ().parents (EHTMLElement.TR).component0 ());
      final JSConditional aIf = aOpenCloseCallback.body ()._if (jsTable.invoke ("fnIsOpen").arg (jsTR));
      aIf._then ().assign (JSExpr.THIS.ref (CHTMLAttributes.SRC), aExpandImgURL.getAsString ());
      aIf._then ().invoke (jsTable, "fnClose").arg (jsTR);
      aIf._else ().assign (JSExpr.THIS.ref (CHTMLAttributes.SRC), aCollapseImgURL.getAsString ());
      aIf._else ()
         .invoke (jsTable, "fnOpen")
         .arg (jsTR)
         .arg (jsTable.invoke ("fnGetData").arg (jsTR).component (nColumnIndexWithDetails))
         .arg (aCellClass == null ? null : aCellClass.getCSSClass ());
    }
    aPackage.add (JQuery.idRef (m_aTable)
                        .on ()
                        .arg ("click")
                        .arg (new JQuerySelectorList (JQuerySelector.element (EHTMLElement.TBODY),
                                                      JQuerySelector.element (EHTMLElement.TD),
                                                      JQuerySelector.element (EHTMLElement.IMG)))
                        .arg (aOpenCloseCallback));
    return m_bGenerateOnDocumentReady ? new HCScriptInlineOnDocumentReady (aPackage) : new HCScriptInline (aPackage);
  }

  /**
   * @param aRowSelect
   *        E.g. <code>JQuery.jQueryThis ().parents (EHTMLElement.TR)</code>
   * @param bSwapUsingJQuery
   *        Use it only, if if no actions can be performed on the table! This is
   *        much quicker.
   * @return The created JS code
   */
  @Nonnull
  public IHasJSCode getMoveRowUpCode (@Nonnull final JQueryInvocation aRowSelect, final boolean bSwapUsingJQuery)
  {
    final JSRef jsTable = JSExpr.ref (m_sGeneratedJSVariableName);

    final JSPackage aPackage = new JSPackage ();
    final JSVar aRow = aPackage.var ("row", aRowSelect);
    final JSVar aPrevRow = aPackage.var ("prow", aRow.invoke ("prev"));
    final JSBlock aIfPrev = aPackage._if (aPrevRow.ref ("length").gt (0))._then ();

    if (bSwapUsingJQuery)
    {
      // This is much quicker, if sorting and searching is disabled
      aIfPrev.add (aRow.invoke ("detach"));
      aIfPrev.add (aPrevRow.invoke ("before").arg (aRow));
    }
    else
    {
      final JSVar aRow0 = aIfPrev.var ("row0", aRow.invoke ("get").arg (0));
      final JSVar aPrevRow0 = aIfPrev.var ("prow0", aPrevRow.invoke ("get").arg (0));

      final JSVar aData = aIfPrev.var ("data", jsTable.invoke ("fnGetData").arg (aRow0));
      final JSVar aPrevData = aIfPrev.var ("prevdata", jsTable.invoke ("fnGetData").arg (aPrevRow0));

      aIfPrev.invoke (jsTable, "fnUpdate").arg (aPrevData).arg (aRow0);
      aIfPrev.invoke (jsTable, "fnUpdate").arg (aData).arg (aPrevRow0);
    }
    return aPackage;
  }

  /**
   * @param aRowSelect
   *        E.g. <code>JQuery.jQueryThis ().parents (EHTMLElement.TR)</code>
   * @param bSwapUsingJQuery
   *        Use it only, if if no actions can be performed on the table! This is
   *        much quicker.
   * @return The created JS code
   */
  @Nonnull
  public IHasJSCode getMoveRowDownCode (@Nonnull final JQueryInvocation aRowSelect, final boolean bSwapUsingJQuery)
  {
    final JSRef jsTable = JSExpr.ref (m_sGeneratedJSVariableName);

    final JSPackage aPackage = new JSPackage ();
    final JSVar aRow = aPackage.var ("row", aRowSelect);
    final JSVar aNextRow = aPackage.var ("nrow", aRow.invoke ("next"));
    final JSBlock aIfNext = aPackage._if (aNextRow.ref ("length").gt (0))._then ();

    if (bSwapUsingJQuery)
    {
      // This is much quicker, if sorting and searching is disabled
      aIfNext.add (aRow.invoke ("detach"));
      aIfNext.add (aNextRow.invoke ("after").arg (aRow));
    }
    else
    {
      final JSVar aRow0 = aIfNext.var ("row0", aRow.invoke ("get").arg (0));
      final JSVar aNextRow0 = aIfNext.var ("nrow0", aNextRow.invoke ("get").arg (0));

      final JSVar aData = aIfNext.var ("data", jsTable.invoke ("fnGetData").arg (aRow0));
      final JSVar aNextData = aIfNext.var ("nextdata", jsTable.invoke ("fnGetData").arg (aNextRow0));

      aIfNext.invoke (jsTable, "fnUpdate").arg (aNextData).arg (aRow0);
      aIfNext.invoke (jsTable, "fnUpdate").arg (aData).arg (aNextRow0);
    }
    return aPackage;
  }
}
