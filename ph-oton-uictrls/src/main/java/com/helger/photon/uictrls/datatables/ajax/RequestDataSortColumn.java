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
package com.helger.photon.uictrls.datatables.ajax;

import java.io.Serializable;
import java.util.Comparator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * Encapsulates the sorting information for the columns that are affected in
 * sorting. So the number of sort columns is always &ge; as the number of
 * overall columns.
 * 
 * @author Philip Helger
 */
@Immutable
final class RequestDataSortColumn implements Serializable
{
  private final int m_nColumnIndex;
  private final ESortOrder m_eSortDirection;
  private Comparator <String> m_aComparator;

  RequestDataSortColumn (@Nonnegative final int nColumnIndex, @Nullable final ESortOrder eSortDirection)
  {
    m_nColumnIndex = nColumnIndex;
    m_eSortDirection = eSortDirection;
  }

  /**
   * @return The index of the column to which the sort should be applied
   */
  @Nonnegative
  public int getColumnIndex ()
  {
    return m_nColumnIndex;
  }

  /**
   * @return Direction to be sorted
   */
  @Nullable
  public ESortOrder getSortDirection ()
  {
    return m_eSortDirection;
  }

  /**
   * @return Direction to be sorted. If not specified, the default is used.
   *         Never <code>null</code>.
   */
  @Nonnull
  public ESortOrder getSortDirectionOrDefault ()
  {
    return m_eSortDirection == null ? ESortOrder.DEFAULT : m_eSortDirection;
  }

  @Nonnull
  public Comparator <String> getComparator ()
  {
    if (m_aComparator == null)
      throw new IllegalStateException ("No comparator defined!");
    return m_aComparator;
  }

  public void setComparator (@Nonnull final Comparator <String> aComparator)
  {
    m_aComparator = ValueEnforcer.notNull (aComparator, "Comparator");
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!(o instanceof RequestDataSortColumn))
      return false;
    final RequestDataSortColumn rhs = (RequestDataSortColumn) o;
    return m_nColumnIndex == rhs.m_nColumnIndex && EqualsUtils.equals (m_eSortDirection, rhs.m_eSortDirection);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_nColumnIndex).append (m_eSortDirection).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("columnIndex", m_nColumnIndex)
                                       .append ("sortDirection", m_eSortDirection)
                                       .append ("comparator", m_aComparator)
                                       .toString ();
  }
}
