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

import java.util.List;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.string.StringHelper;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.ext.HCHasCSSClasses;
import com.helger.html.hc.html.IHCHasCSSClasses;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;
import com.helger.photon.uictrls.datatables.comparator.AbstractComparatorDT;

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
  private final String m_sCellType = DEFAULT_CELLTYPE;
  /** Class to assign to each cell in the column. */
  private final HCHasCSSClasses m_aClassNames = new HCHasCSSClasses ();
  /**
   * Add padding to the text content used when calculating the optimal with for
   * a table.
   */
  private String m_sContentPadding;
  /**
   * Cell created callback to allow DOM manipulation. function createdCell(cell,
   * cellData, rowData, rowIndex, colIndex)
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
  private List <EDataTablesColumnOrderSequenceType> m_aOrderSequence;
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
  private AbstractComparatorDT m_aComparator;

  public DataTablesColumnDef (@Nonnegative final int nTarget)
  {
    this (new int [] { nTarget });
  }

  public DataTablesColumnDef (@Nonnull @Nonempty final int... aTargets)
  {
    ValueEnforcer.notEmpty (aTargets, "Targets");
    for (final int nTarget : aTargets)
      ValueEnforcer.isGE0 (nTarget, "Target");
    m_aTargets = ArrayHelper.getCopy (aTargets);
  }

  public DataTablesColumnDef (@Nonnegative final int nTarget, @Nonnull final DTCol aDTColumn)
  {
    this (nTarget);
    setOrderable (aDTColumn.isOrderable ());
    setSearchable (aDTColumn.isSearchable ());
    setVisible (aDTColumn.isVisible ());
    addClasses (aDTColumn.getAllClasses ());
    setName (aDTColumn.getName ());
    if (!aDTColumn.isVisible ())
    {
      // Invisible columns always get width 0
      setWidth ("0");
    }
    else
      if (!aDTColumn.isStar ())
        setWidth (aDTColumn.getWidth ());
    setDataSort (aDTColumn.getDataSort ());
    setComparator (aDTColumn.getComparator ());
  }

  @Nonnull
  @Nonempty
  @ReturnsMutableCopy
  public int [] getAllTargets ()
  {
    return ArrayHelper.getCopy (m_aTargets);
  }

  public boolean hasTarget (final int nTarget)
  {
    return ArrayHelper.contains (m_aTargets, nTarget);
  }

  public boolean isOrderable ()
  {
    return m_bOrderable;
  }

  @Nonnull
  public DataTablesColumnDef setOrderable (final boolean bOrderable)
  {
    m_bOrderable = bOrderable;
    return this;
  }

  public boolean isSearchable ()
  {
    return m_bSearchable;
  }

  @Nonnull
  public DataTablesColumnDef setSearchable (final boolean bSearchable)
  {
    m_bSearchable = bSearchable;
    return this;
  }

  public boolean isVisible ()
  {
    return m_bVisible;
  }

  @Nonnull
  public DataTablesColumnDef setVisible (final boolean bVisible)
  {
    m_bVisible = bVisible;
    return this;
  }

  public boolean containsClass (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    return m_aClassNames.containsClass (aCSSClassProvider);
  }

  @Nonnull
  public DataTablesColumnDef addClass (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    m_aClassNames.addClass (aCSSClassProvider);
    return this;
  }

  @Deprecated
  @Nonnull
  public DataTablesColumnDef addClasses (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    m_aClassNames.addClasses (aCSSClassProvider);
    return this;
  }

  @Nonnull
  public DataTablesColumnDef addClasses (@Nullable final ICSSClassProvider... aCSSClassProviders)
  {
    m_aClassNames.addClasses (aCSSClassProviders);
    return this;
  }

  @Nonnull
  public DataTablesColumnDef addClasses (@Nullable final Iterable <? extends ICSSClassProvider> aCSSClassProviders)
  {
    m_aClassNames.addClasses (aCSSClassProviders);
    return this;
  }

  @Nonnull
  public DataTablesColumnDef removeClass (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    m_aClassNames.removeClass (aCSSClassProvider);
    return this;
  }

  @Nonnull
  public DataTablesColumnDef removeAllClasses ()
  {
    m_aClassNames.removeAllClasses ();
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <ICSSClassProvider> getAllClasses ()
  {
    return m_aClassNames.getAllClasses ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <String> getAllClassNames ()
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
  public String getName ()
  {
    return m_sName;
  }

  @Nonnull
  public DataTablesColumnDef setName (@Nullable final String sName)
  {
    m_sName = sName;
    return this;
  }

  @Nullable
  public String getWidth ()
  {
    return m_sWidth;
  }

  @Nonnull
  public DataTablesColumnDef setWidth (@Nullable final String sWidth)
  {
    m_sWidth = sWidth;
    return this;
  }

  @Nullable
  @ReturnsMutableCopy
  public int [] getDataSort ()
  {
    return ArrayHelper.getCopy (m_aOrderData);
  }

  /**
   * Set the column indices to sort, when this column is sorted
   *
   * @param aDataSort
   *        The sorting column (incl. this column!)
   * @return this
   */
  @Nonnull
  public DataTablesColumnDef setDataSort (@Nullable final int... aDataSort)
  {
    m_aOrderData = ArrayHelper.getCopy (aDataSort);
    return this;
  }

  @Nullable
  public AbstractComparatorDT getComparator ()
  {
    return m_aComparator;
  }

  @Nonnull
  public DataTablesColumnDef setComparator (@Nullable final AbstractComparatorDT aComparator)
  {
    m_aComparator = aComparator;
    return this;
  }

  @Nonnull
  public JSAssocArray getAsJS ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    ret.add ("targets", new JSArray ().addAll (m_aTargets));
    if (!m_sCellType.equals (DEFAULT_CELLTYPE))
      ret.add ("cellType", m_sCellType);
    final String sClasses = getAllClassesAsString ();
    if (StringHelper.hasText (sClasses))
      ret.add ("className", sClasses);
    if (StringHelper.hasText (m_sContentPadding))
      ret.add ("contentPadding", m_sContentPadding);
    if (m_aCreatedCell != null)
      ret.add ("createdCell", m_aCreatedCell);
    if (m_aData != null)
      ret.add ("data", m_aData);
    if (m_sDefaultContent != null)
      ret.add ("defaultContent", m_sDefaultContent);
    if (StringHelper.hasText (m_sName))
      ret.add ("name", m_sName);
    if (m_bOrderable != DEFAULT_ORDERABLE)
      ret.add ("orderable", m_bOrderable);
    if (ArrayHelper.isNotEmpty (m_aOrderData))
      ret.add ("orderData", new JSArray ().addAll (m_aOrderData));
    if (StringHelper.hasText (m_sOrderDataType))
      ret.add ("orderDataType", m_sOrderDataType);
    if (m_aOrderSequence != null && !m_aOrderSequence.isEmpty ())
    {
      final JSArray aArray = new JSArray ();
      for (final EDataTablesColumnOrderSequenceType eOrderSequence : m_aOrderSequence)
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
    if (StringHelper.hasText (m_sWidth))
      ret.add ("width", m_sWidth);
    return ret;
  }
}
