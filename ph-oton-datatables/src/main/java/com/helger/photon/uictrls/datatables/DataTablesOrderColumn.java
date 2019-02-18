/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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

import java.io.Serializable;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.jscode.JSArray;

/**
 * Defines a DataTables orderable column
 *
 * @author Philip Helger
 */
public final class DataTablesOrderColumn implements Serializable
{
  private final int m_nIndex;
  private final ESortOrder m_eSortOrder;

  public DataTablesOrderColumn (@Nonnegative final int nIndex, @Nonnull final ESortOrder eSortOrder)
  {
    ValueEnforcer.isGE0 (nIndex, "Index");
    ValueEnforcer.notNull (eSortOrder, "SortOrder");
    m_nIndex = nIndex;
    m_eSortOrder = eSortOrder;
  }

  @Nonnegative
  public int getIndex ()
  {
    return m_nIndex;
  }

  @Nonnull
  public ESortOrder getSortOrder ()
  {
    return m_eSortOrder;
  }

  @Nonnull
  public JSArray getAsJS ()
  {
    final JSArray ret = new JSArray ();
    ret.add (m_nIndex);
    ret.add (EDataTablesOrderDirectionType.getNameFromSortOrderOrNull (m_eSortOrder));
    return ret;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("index", m_nIndex).append ("sortOrder", m_eSortOrder).getToString ();
  }
}
