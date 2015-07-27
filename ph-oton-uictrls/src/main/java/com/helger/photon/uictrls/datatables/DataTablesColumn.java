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
import com.helger.html.hc.IHCHasCSSClasses;
import com.helger.html.hcext.HCHasCSSClasses;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;
import com.helger.photon.uictrls.datatables.comparator.AbstractComparatorDT;

/**
 * Contains all data for a single DataTables column
 *
 * @author Philip Helger
 */
public class DataTablesColumn implements IHCHasCSSClasses <DataTablesColumn>
{
  public static final boolean DEFAULT_SEARCHABLE = true;
  public static final boolean DEFAULT_SORTABLE = true;
  public static final boolean DEFAULT_VISIBLE = true;

  private final int [] m_aTargets;
  private boolean m_bSearchable = DEFAULT_SEARCHABLE;
  private boolean m_bSortable = DEFAULT_SORTABLE;
  private boolean m_bVisible = DEFAULT_VISIBLE;
  private final HCHasCSSClasses m_aCSSClasses = new HCHasCSSClasses ();
  private String m_sName;
  private String m_sWidth;
  private int [] m_aDataSort;
  private AbstractComparatorDT m_aComparator;

  public DataTablesColumn (@Nonnegative final int nTarget)
  {
    this (new int [] { nTarget });
  }

  public DataTablesColumn (@Nonnull @Nonempty final int... aTargets)
  {
    ValueEnforcer.notEmpty (aTargets, "Targets");
    for (final int nTarget : aTargets)
      ValueEnforcer.isGE0 (nTarget, "Target");
    m_aTargets = ArrayHelper.getCopy (aTargets);
  }

  public DataTablesColumn (@Nonnegative final int nTarget, @Nonnull final DTCol aDTColumn)
  {
    this (nTarget);
    setSearchable (aDTColumn.isSearchable ());
    setSortable (aDTColumn.isSortable ());
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

  public boolean isSearchable ()
  {
    return m_bSearchable;
  }

  @Nonnull
  public DataTablesColumn setSearchable (final boolean bSearchable)
  {
    m_bSearchable = bSearchable;
    return this;
  }

  public boolean isSortable ()
  {
    return m_bSortable;
  }

  @Nonnull
  public DataTablesColumn setSortable (final boolean bSortable)
  {
    m_bSortable = bSortable;
    return this;
  }

  public boolean isVisible ()
  {
    return m_bVisible;
  }

  @Nonnull
  public DataTablesColumn setVisible (final boolean bVisible)
  {
    m_bVisible = bVisible;
    return this;
  }

  public boolean containsClass (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    return m_aCSSClasses.containsClass (aCSSClassProvider);
  }

  @Nonnull
  public DataTablesColumn addClass (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    m_aCSSClasses.addClass (aCSSClassProvider);
    return this;
  }

  @Deprecated
  @Nonnull
  public DataTablesColumn addClasses (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    m_aCSSClasses.addClasses (aCSSClassProvider);
    return this;
  }

  @Nonnull
  public DataTablesColumn addClasses (@Nullable final ICSSClassProvider... aCSSClassProviders)
  {
    m_aCSSClasses.addClasses (aCSSClassProviders);
    return this;
  }

  @Nonnull
  public DataTablesColumn addClasses (@Nullable final Iterable <? extends ICSSClassProvider> aCSSClassProviders)
  {
    m_aCSSClasses.addClasses (aCSSClassProviders);
    return this;
  }

  @Nonnull
  public DataTablesColumn removeClass (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    m_aCSSClasses.removeClass (aCSSClassProvider);
    return this;
  }

  @Nonnull
  public DataTablesColumn removeAllClasses ()
  {
    m_aCSSClasses.removeAllClasses ();
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <ICSSClassProvider> getAllClasses ()
  {
    return m_aCSSClasses.getAllClasses ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <String> getAllClassNames ()
  {
    return m_aCSSClasses.getAllClassNames ();
  }

  @Nullable
  public String getAllClassesAsString ()
  {
    return m_aCSSClasses.getAllClassesAsString ();
  }

  public boolean hasAnyClass ()
  {
    return m_aCSSClasses.hasAnyClass ();
  }

  @Nullable
  public String getName ()
  {
    return m_sName;
  }

  @Nonnull
  public DataTablesColumn setName (@Nullable final String sName)
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
  public DataTablesColumn setWidth (@Nullable final String sWidth)
  {
    m_sWidth = sWidth;
    return this;
  }

  @Nullable
  @ReturnsMutableCopy
  public int [] getDataSort ()
  {
    return ArrayHelper.getCopy (m_aDataSort);
  }

  /**
   * Set the column indices to sort, when this column is sorted
   *
   * @param aDataSort
   *        The sorting column (incl. this column!)
   * @return this
   */
  @Nonnull
  public DataTablesColumn setDataSort (@Nullable final int... aDataSort)
  {
    m_aDataSort = ArrayHelper.getCopy (aDataSort);
    return this;
  }

  @Nullable
  public AbstractComparatorDT getComparator ()
  {
    return m_aComparator;
  }

  @Nonnull
  public DataTablesColumn setComparator (@Nullable final AbstractComparatorDT aComparator)
  {
    m_aComparator = aComparator;
    return this;
  }

  @Nonnull
  public JSAssocArray getAsJS ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    ret.add ("targets", new JSArray ().addAll (m_aTargets));
    if (m_bSearchable != DEFAULT_SEARCHABLE)
      ret.add ("searchable", m_bSearchable);
    if (m_bSortable != DEFAULT_SORTABLE)
      ret.add ("orderable", m_bSortable);
    if (m_bVisible != DEFAULT_VISIBLE)
      ret.add ("visible", m_bVisible);
    final String sClasses = getAllClassesAsString ();
    if (StringHelper.hasText (sClasses))
      ret.add ("className", sClasses);
    if (StringHelper.hasText (m_sName))
      ret.add ("name", m_sName);
    if (StringHelper.hasText (m_sWidth))
      ret.add ("width", m_sWidth);
    if (ArrayHelper.isNotEmpty (m_aDataSort))
      ret.add ("orderData", new JSArray ().addAll (m_aDataSort));
    return ret;
  }
}
