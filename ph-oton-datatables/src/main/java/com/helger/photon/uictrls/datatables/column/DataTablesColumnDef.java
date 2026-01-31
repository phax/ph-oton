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
package com.helger.photon.uictrls.datatables.column;

import java.util.List;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.Nonnegative;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.array.ArrayHelper;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.collection.CollectionHelper;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsOrderedSet;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.ext.HCHasCSSClasses;
import com.helger.html.hc.html.IHCHasCSSClasses;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;
import com.helger.photon.uictrls.datatables.EDataTablesOrderDirectionType;

/**
 * Contains all data for a single DataTables column
 *
 * @author Philip Helger
 */
public class DataTablesColumnDef implements IHCHasCSSClasses <DataTablesColumnDef>
{
  public static final String DEFAULT_CELLTYPE = "td";
  public static final boolean DEFAULT_ORDERABLE = true;
  public static final boolean DEFAULT_SEARCHABLE = true;
  public static final boolean DEFAULT_VISIBLE = true;

  private final int [] m_aTargets;
  /** Cell type to be created for a column. */
  private String m_sCellType = DEFAULT_CELLTYPE;
  /** Class to assign to each cell in the column. */
  private final HCHasCSSClasses m_aClassNames = new HCHasCSSClasses ();
  /**
   * Add padding to the text content used when calculating the optimal with for a table.
   */
  private String m_sContentPadding;
  /**
   * Cell created callback to allow DOM manipulation. function createdCell(cell, cellData, rowData,
   * rowIndex, colIndex)
   */
  private JSAnonymousFunction m_aCreatedCell;
  /** Set the data source for the column from the rows data object / array. */
  private IJSExpression m_aData;
  /** Set default, static, content for a column. */
  private String m_sDefaultContent;
  /** Set a descriptive name for a column. */
  private String m_sName;
  /** Enable or disable ordering on this column. */
  private boolean m_bOrderable = DEFAULT_ORDERABLE;
  /** Define multiple column ordering as the default order for a column. */
  private int [] m_aOrderData;
  /** Live DOM sorting type assignment. */
  private String m_sOrderDataType;
  /** Order direction application sequence. */
  private ICommonsList <EDataTablesOrderDirectionType> m_aOrderSequence = new CommonsArrayList <> (EDataTablesOrderDirectionType.ASC,
                                                                                                   EDataTablesOrderDirectionType.DESC);
  /** Render (process) the data for use in the table. */
  private IJSExpression m_aRender;
  /** Enable or disable filtering on the data in this column. */
  private boolean m_bSearchable = DEFAULT_SEARCHABLE;
  /** Set the column title. */
  private String m_sTitle;
  /** Set the column type - used for filtering and sorting string processing. */
  private EDataTablesColumnType m_eType;
  /** Enable or disable the display of this column. */
  private boolean m_bVisible = DEFAULT_VISIBLE;
  /** Column width assignment. */
  private String m_sWidth;
  /** Server side comparator */
  private final DTOrderSpec m_aOrderSpec;

  public DataTablesColumnDef (@Nonnegative final int nTarget)
  {
    ValueEnforcer.isGE0 (nTarget, "Target");
    m_aTargets = new int [] { nTarget };
    m_aOrderSpec = new DTOrderSpec ();
  }

  public DataTablesColumnDef (final int @NonNull @Nonempty... aTargets)
  {
    ValueEnforcer.notEmpty (aTargets, "Targets");
    for (final int nTarget : aTargets)
      ValueEnforcer.isGE0 (nTarget, "Target");
    m_aTargets = ArrayHelper.getCopy (aTargets);
    m_aOrderSpec = new DTOrderSpec ();
  }

  public DataTablesColumnDef (@Nonnegative final int nTarget, @NonNull final DTCol aDTColumn)
  {
    ValueEnforcer.isGE0 (nTarget, "Target");
    m_aTargets = new int [] { nTarget };
    addClasses (aDTColumn.getAllClasses ());
    setName (aDTColumn.getName ());
    setOrderable (aDTColumn.isOrderable ());
    setOrderData (aDTColumn.getDataSort ());
    setSearchable (aDTColumn.isSearchable ());
    setVisible (aDTColumn.isVisible ());
    if (!aDTColumn.isVisible ())
    {
      // Invisible columns always get width 0
      setWidth ("0");
    }
    else
      if (!aDTColumn.isStar ())
        setWidth (aDTColumn.getWidth ());

    m_aOrderSpec = aDTColumn.getOrderSpec ();
  }

  @ReturnsMutableCopy
  public int @NonNull @Nonempty [] getAllTargets ()
  {
    return ArrayHelper.getCopy (m_aTargets);
  }

  public boolean hasTarget (final int nTarget)
  {
    return ArrayHelper.contains (m_aTargets, nTarget);
  }

  @NonNull
  @Nonempty
  public String getCellType ()
  {
    return m_sCellType;
  }

  @NonNull
  public DataTablesColumnDef setCellType (@NonNull @Nonempty final String sCellType)
  {
    ValueEnforcer.notEmpty (sCellType, "CellType");
    m_sCellType = sCellType;
    return this;
  }

  public boolean containsClass (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    return m_aClassNames.containsClass (aCSSClassProvider);
  }

  @NonNull
  public DataTablesColumnDef addClass (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    m_aClassNames.addClass (aCSSClassProvider);
    return this;
  }

  @NonNull
  public DataTablesColumnDef removeClass (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    m_aClassNames.removeClass (aCSSClassProvider);
    return this;
  }

  @NonNull
  public DataTablesColumnDef removeAllClasses ()
  {
    m_aClassNames.removeAllClasses ();
    return this;
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsOrderedSet <ICSSClassProvider> getAllClasses ()
  {
    return m_aClassNames.getAllClasses ();
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsOrderedSet <String> getAllClassNames ()
  {
    return m_aClassNames.getAllClassNames ();
  }

  @Nullable
  public String getAllClassesAsString ()
  {
    return m_aClassNames.getAllClassesAsString ();
  }

  public boolean hasAnyClass ()
  {
    return m_aClassNames.hasAnyClass ();
  }

  @Nullable
  public String getContentPadding ()
  {
    return m_sContentPadding;
  }

  @NonNull
  public DataTablesColumnDef setContentPadding (@Nullable final String sContentPadding)
  {
    m_sContentPadding = sContentPadding;
    return this;
  }

  @Nullable
  public JSAnonymousFunction getCreatedCell ()
  {
    return m_aCreatedCell;
  }

  @NonNull
  public DataTablesColumnDef setCreatedCell (@Nullable final JSAnonymousFunction aCreatedCell)
  {
    m_aCreatedCell = aCreatedCell;
    return this;
  }

  @Nullable
  public IJSExpression getData ()
  {
    return m_aData;
  }

  @NonNull
  public DataTablesColumnDef setData (@Nonnegative final int nColumnIndex)
  {
    return setData (JSExpr.lit (nColumnIndex));
  }

  @NonNull
  public DataTablesColumnDef setData (@NonNull @Nonempty final String sPropertyName)
  {
    return setData (JSExpr.lit (sPropertyName));
  }

  @NonNull
  public DataTablesColumnDef setDataNull ()
  {
    return setData (JSExpr.NULL);
  }

  @NonNull
  public DataTablesColumnDef setData (@Nullable final IJSExpression aData)
  {
    m_aData = aData;
    return this;
  }

  @Nullable
  public String getDefaultContent ()
  {
    return m_sDefaultContent;
  }

  @NonNull
  public DataTablesColumnDef setDefaultContent (@Nullable final String sDefaultContent)
  {
    m_sDefaultContent = sDefaultContent;
    return this;
  }

  @Nullable
  public String getName ()
  {
    return m_sName;
  }

  @NonNull
  public DataTablesColumnDef setName (@Nullable final String sName)
  {
    m_sName = sName;
    return this;
  }

  public boolean isOrderable ()
  {
    return m_bOrderable;
  }

  @NonNull
  public DataTablesColumnDef setOrderable (final boolean bOrderable)
  {
    m_bOrderable = bOrderable;
    return this;
  }

  @ReturnsMutableCopy
  public int @Nullable [] getOrderData ()
  {
    return ArrayHelper.getCopy (m_aOrderData);
  }

  /**
   * Set the column indices to sort, when this column is sorted
   *
   * @param aOrderData
   *        The sorting column (incl. this column!)
   * @return this
   */
  @NonNull
  public DataTablesColumnDef setOrderData (final int @Nullable... aOrderData)
  {
    m_aOrderData = ArrayHelper.getCopy (aOrderData);
    return this;
  }

  @Nullable
  public String getOrderDataType ()
  {
    return m_sOrderDataType;
  }

  @NonNull
  public DataTablesColumnDef setOrderDataType (@Nullable final String sOrderDataType)
  {
    m_sOrderDataType = sOrderDataType;
    return this;
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <EDataTablesOrderDirectionType> getOrderSequence ()
  {
    return new CommonsArrayList <> (m_aOrderSequence);
  }

  @NonNull
  public DataTablesColumnDef setOrderSequence (@Nullable final EDataTablesOrderDirectionType... aOrderSequence)
  {
    m_aOrderSequence = ArrayHelper.isEmpty (aOrderSequence) ? null : new CommonsArrayList <> (aOrderSequence);
    return this;
  }

  @NonNull
  public DataTablesColumnDef setOrderSequence (@Nullable final List <EDataTablesOrderDirectionType> aOrderSequence)
  {
    m_aOrderSequence = CollectionHelper.isEmpty (aOrderSequence) ? null : new CommonsArrayList <> (aOrderSequence);
    return this;
  }

  @Nullable
  public IJSExpression getRender ()
  {
    return m_aRender;
  }

  @NonNull
  public DataTablesColumnDef setRender (@Nullable final IJSExpression aRender)
  {
    m_aRender = aRender;
    return this;
  }

  public boolean isSearchable ()
  {
    return m_bSearchable;
  }

  @NonNull
  public DataTablesColumnDef setSearchable (final boolean bSearchable)
  {
    m_bSearchable = bSearchable;
    return this;
  }

  @Nullable
  public String getTitle ()
  {
    return m_sTitle;
  }

  @NonNull
  public DataTablesColumnDef setTitle (@Nullable final String sTitle)
  {
    m_sTitle = sTitle;
    return this;
  }

  @Nullable
  public EDataTablesColumnType getType ()
  {
    return m_eType;
  }

  @NonNull
  public DataTablesColumnDef setType (@Nullable final EDataTablesColumnType eType)
  {
    m_eType = eType;
    return this;
  }

  public boolean isVisible ()
  {
    return m_bVisible;
  }

  @NonNull
  public DataTablesColumnDef setVisible (final boolean bVisible)
  {
    m_bVisible = bVisible;
    return this;
  }

  @Nullable
  public String getWidth ()
  {
    return m_sWidth;
  }

  @NonNull
  public DataTablesColumnDef setWidth (@Nullable final String sWidth)
  {
    m_sWidth = sWidth;
    return this;
  }

  @NonNull
  public DTOrderSpec getOrderSpec ()
  {
    return m_aOrderSpec;
  }

  @NonNull
  public JSAssocArray getAsJS ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    ret.add ("targets", new JSArray ().addAll (m_aTargets));
    if (!m_sCellType.equals (DEFAULT_CELLTYPE))
      ret.add ("cellType", m_sCellType);
    final String sClasses = getAllClassesAsString ();
    if (StringHelper.isNotEmpty (sClasses))
      ret.add ("className", sClasses);
    if (StringHelper.isNotEmpty (m_sContentPadding))
      ret.add ("contentPadding", m_sContentPadding);
    if (m_aCreatedCell != null)
      ret.add ("createdCell", m_aCreatedCell);
    if (m_aData != null)
      ret.add ("data", m_aData);
    if (m_sDefaultContent != null)
      ret.add ("defaultContent", m_sDefaultContent);
    if (StringHelper.isNotEmpty (m_sName))
      ret.add ("name", m_sName);
    if (m_bOrderable != DEFAULT_ORDERABLE)
      ret.add ("orderable", m_bOrderable);
    if (ArrayHelper.isNotEmpty (m_aOrderData))
      ret.add ("orderData", new JSArray ().addAll (m_aOrderData));
    if (StringHelper.isNotEmpty (m_sOrderDataType))
      ret.add ("orderDataType", m_sOrderDataType);
    if (m_aOrderSequence != null && !m_aOrderSequence.isEmpty ())
    {
      final JSArray aArray = new JSArray ();
      for (final EDataTablesOrderDirectionType eOrderSequence : m_aOrderSequence)
        aArray.add (eOrderSequence.getName ());
      ret.add ("orderSequence", aArray);
    }
    if (m_aRender != null)
      ret.add ("render", m_aRender);
    if (m_bSearchable != DEFAULT_SEARCHABLE)
      ret.add ("searchable", m_bSearchable);
    if (m_sTitle != null)
      ret.add ("title", m_sTitle);
    if (m_eType != null)
      ret.add ("type", m_eType.getName ());
    if (m_bVisible != DEFAULT_VISIBLE)
      ret.add ("visible", m_bVisible);
    if (StringHelper.isNotEmpty (m_sWidth))
      ret.add ("width", m_sWidth);
    return ret;
  }
}
