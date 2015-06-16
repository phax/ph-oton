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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.ArrayHelper;
import com.helger.commons.compare.ESortOrder;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.AbstractHCCol;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.photon.uictrls.datatables.comparator.AbstractComparatorDT;

/**
 * Specialized column for DataTables to be used in IHCTable implementation
 * constructors.
 *
 * @author Philip Helger
 */
public class DTCol extends AbstractHCCol <DTCol>
{
  public static final boolean DEFAULT_SEARCHABLE = true;
  public static final boolean DEFAULT_SORTABLE = true;
  public static final boolean DEFAULT_VISIBLE = true;

  private IHCNode m_aHeaderNode;
  private ESortOrder m_eInitialSorting = null;
  private boolean m_bSearchable = DEFAULT_SEARCHABLE;
  private boolean m_bSortable = DEFAULT_SORTABLE;
  private boolean m_bVisible = DEFAULT_VISIBLE;
  private String m_sName;
  private int [] m_aDataSort;
  private AbstractComparatorDT m_aComparator;

  public DTCol ()
  {
    this ((IHCNode) null);
  }

  public DTCol (@Nullable final String sHeaderText)
  {
    this (HCTextNode.createOnDemand (sHeaderText));
  }

  public DTCol (@Nullable final IHCNode aHeaderNode)
  {
    setWidth (CHTMLAttributeValues.STAR);
    setHeaderNode (aHeaderNode);
  }

  @Nonnull
  public IHCNode getHeaderNode ()
  {
    return m_aHeaderNode;
  }

  @Nonnull
  public DTCol setHeaderNode (@Nullable final IHCNode aHeaderNode)
  {
    m_aHeaderNode = aHeaderNode;
    return this;
  }

  @Nonnull
  public ESortOrder getInitialSorting ()
  {
    return m_eInitialSorting;
  }

  public boolean hasInitialSorting ()
  {
    return m_eInitialSorting != null;
  }

  @Nonnull
  public DTCol setInitialSorting (@Nullable final ESortOrder eInitialSorting)
  {
    m_eInitialSorting = eInitialSorting;
    return this;
  }

  public boolean isSearchable ()
  {
    return m_bSearchable;
  }

  @Nonnull
  public DTCol setSearchable (final boolean bSearchable)
  {
    m_bSearchable = bSearchable;
    return this;
  }

  public boolean isSortable ()
  {
    return m_bSortable;
  }

  @Nonnull
  public DTCol setSortable (final boolean bSortable)
  {
    m_bSortable = bSortable;
    return this;
  }

  public boolean isVisible ()
  {
    return m_bVisible;
  }

  @Nonnull
  public DTCol setVisible (final boolean bVisible)
  {
    m_bVisible = bVisible;
    return this;
  }

  @Nullable
  public String getName ()
  {
    return m_sName;
  }

  @Nonnull
  public DTCol setName (@Nullable final String sName)
  {
    m_sName = sName;
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
  public DTCol setDataSort (@Nullable final int... aDataSort)
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
  public DTCol setComparator (@Nullable final AbstractComparatorDT aComparator)
  {
    m_aComparator = aComparator;
    return this;
  }
}
