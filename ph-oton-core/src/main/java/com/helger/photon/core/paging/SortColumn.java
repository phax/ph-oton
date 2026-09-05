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
package com.helger.photon.core.paging;

import java.util.Comparator;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.base.compare.ESortOrder;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.tostring.ToStringGenerator;

/**
 * A single resolved sort instruction: the column to sort by and the sort order to be applied to it.
 *
 * @author Philip Helger
 * @param <DATATYPE>
 *        The domain object type this column belongs to
 * @since 10.6.0
 */
@Immutable
public class SortColumn <DATATYPE>
{
  private final ITableColumn <DATATYPE> m_aColumn;
  private final ESortOrder m_eSortOrder;

  /**
   * Constructor.
   *
   * @param aColumn
   *        The column to sort by. May not be <code>null</code> and must be sortable.
   * @param eSortOrder
   *        The sort order to be applied. May not be <code>null</code>.
   */
  public SortColumn (@NonNull final ITableColumn <DATATYPE> aColumn, @NonNull final ESortOrder eSortOrder)
  {
    ValueEnforcer.notNull (aColumn, "Column");
    ValueEnforcer.isTrue (aColumn::isSortable, () -> "The column '" + aColumn.getID () + "' is not sortable");
    m_aColumn = aColumn;
    m_eSortOrder = ValueEnforcer.notNull (eSortOrder, "SortOrder");
  }

  @NonNull
  public ITableColumn <DATATYPE> getColumn ()
  {
    return m_aColumn;
  }

  @NonNull
  public ESortOrder getSortOrder ()
  {
    return m_eSortOrder;
  }

  public boolean isAscending ()
  {
    return m_eSortOrder.isAscending ();
  }

  /**
   * @return The comparator of the contained column, reversed if the sort order is descending. Never
   *         <code>null</code>.
   */
  @NonNull
  public Comparator <DATATYPE> getComparator ()
  {
    // Non-null, because the constructor only accepts sortable columns
    final Comparator <DATATYPE> ret = m_aColumn.getComparator ();
    return isAscending () ? ret : ret.reversed ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Column", m_aColumn).append ("SortOrder", m_eSortOrder).getToString ();
  }
}
