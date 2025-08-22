/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import com.helger.base.compare.ESortOrder;
import com.helger.base.equals.EqualsHelper;
import com.helger.base.name.IHasName;
import com.helger.base.string.StringHelper;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * DataTables column order sequence type
 *
 * @author Philip Helger
 */
public enum EDataTablesOrderDirectionType implements IHasName
{
  /**
   * Ascending order
   */
  ASC ("asc", ESortOrder.ASCENDING),
  /**
   * Descending order
   */
  DESC ("desc", ESortOrder.DESCENDING),
  /**
   * Data index order. Note that this option is available as of DataTables 2.0
   * and newer.
   */
  NONE ("", null);

  private final String m_sName;
  private final ESortOrder m_eSortOrder;

  EDataTablesOrderDirectionType (@Nonnull final String sName, @Nullable final ESortOrder eSortOrder)
  {
    m_sName = sName;
    m_eSortOrder = eSortOrder;
  }

  @Nonnull
  public String getName ()
  {
    return m_sName;
  }

  @Nullable
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
    for (final EDataTablesOrderDirectionType e : values ())
      if (EqualsHelper.identityEqual (e.m_eSortOrder, eSortOrder))
        return e.m_sName;
    return null;
  }
}
