/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.uictrls.datatables.column.DTOrderSpec;

/**
 * Encapsulates the sorting information for the columns that are affected in
 * sorting. So the number of sort columns is always &ge; as the number of
 * overall columns.
 *
 * @author Philip Helger
 */
@Immutable
public final class DTSSRequestDataOrderColumn implements Serializable
{
  private final int m_nColumnIndex;
  private final ESortOrder m_eSortOrder;
  private DTOrderSpec m_aOrderSpec;

  public DTSSRequestDataOrderColumn (@Nonnegative final int nColumnIndex, @Nullable final ESortOrder eSortOrder)
  {
    m_nColumnIndex = nColumnIndex;
    m_eSortOrder = eSortOrder;
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
  public ESortOrder getSortOrder ()
  {
    return m_eSortOrder;
  }

  /**
   * @return Direction to be sorted. If not specified, the default is used.
   *         Never <code>null</code>.
   */
  @Nonnull
  public ESortOrder getSortDirectionOrDefault ()
  {
    return m_eSortOrder == null ? ESortOrder.DEFAULT : m_eSortOrder;
  }

  public void setOrderSpec (@Nonnull final DTOrderSpec aOrderSpec)
  {
    ValueEnforcer.notNull (aOrderSpec, "OrderSpec");
    m_aOrderSpec = aOrderSpec;
  }

  @Nonnull
  public Comparator <String> getOrderComparator ()
  {
    Comparator <String> ret;
    final DTOrderSpec aOS = m_aOrderSpec;
    if (aOS == null)
    {
      // No order information present
      ret = Comparator.naturalOrder ();
    }
    else
    {
      // Use Comparator from order spec
      ret = aOS.getComparator ();
    }

    final boolean bIsAscending = getSortDirectionOrDefault ().isAscending ();
    if (!bIsAscending)
    {
      // Reverse the comparator
      ret = ret.reversed ();
    }

    // Null handling
    if (bIsAscending)
      return Comparator.nullsFirst (ret);
    return Comparator.nullsLast (ret);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final DTSSRequestDataOrderColumn rhs = (DTSSRequestDataOrderColumn) o;
    return m_nColumnIndex == rhs.m_nColumnIndex && EqualsHelper.equals (m_eSortOrder, rhs.m_eSortOrder);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_nColumnIndex).append (m_eSortOrder).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("SolumnIndex", m_nColumnIndex)
                                       .append ("SortOrder", m_eSortOrder)
                                       .append ("OrderSpec", m_aOrderSpec)
                                       .getToString ();
  }
}
