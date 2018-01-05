/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.name.IHasName;
import com.helger.commons.string.StringHelper;

/**
 * DataTables column order sequence type
 *
 * @author Philip Helger
 */
public enum EDataTablesOrderDirectionType implements IHasName
{
  ASC ("asc", ESortOrder.ASCENDING),
  DESC ("desc", ESortOrder.DESCENDING);

  private final String m_sName;
  private final ESortOrder m_eSortOrder;

  private EDataTablesOrderDirectionType (@Nonnull @Nonempty final String sName, @Nonnull final ESortOrder eSortOrder)
  {
    m_sName = sName;
    m_eSortOrder = eSortOrder;
  }

  @Nonnull
  @Nonempty
  public String getName ()
  {
    return m_sName;
  }

  @Nonnull
  public ESortOrder getSortOrder ()
  {
    return m_eSortOrder;
  }

  @Nullable
  public static ESortOrder getSortOrderFromNameOrNull (@Nullable final String sName)
  {
    if (StringHelper.hasText (sName))
      for (final EDataTablesOrderDirectionType e : values ())
        if (e.m_sName.equals (sName))
          return e.m_eSortOrder;
    return null;
  }

  @Nullable
  public static String getNameFromSortOrderOrNull (@Nullable final ESortOrder eSortOrder)
  {
    if (eSortOrder != null)
      for (final EDataTablesOrderDirectionType e : values ())
        if (e.m_eSortOrder.equals (eSortOrder))
          return e.m_sName;
    return null;
  }
}
