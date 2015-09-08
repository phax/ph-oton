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

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.string.ToStringGenerator;

/**
 * Encapsulates the request data for a single column
 *
 * @author Philip Helger
 */
@Immutable
public final class DTSSRequestDataColumn
{
  private final String m_sData;
  private final String m_sName;
  private final boolean m_bSearchable;
  private final boolean m_bOrderable;
  private final DTSSRequestDataSearch m_aSearch;

  DTSSRequestDataColumn (@Nullable final String sData,
                         @Nullable final String sName,
                         final boolean bSearchable,
                         final boolean bOrderable,
                         @Nullable final String sSearchText,
                         final boolean bSearchRegEx)
  {
    m_sData = sData;
    m_sName = sName;
    m_bSearchable = bSearchable;
    m_bOrderable = bOrderable;
    m_aSearch = bSearchable ? new DTSSRequestDataSearch (sSearchText, bSearchRegEx) : null;
  }

  /**
   * @return Column's data source, as defined by columns.data.
   */
  @Nullable
  public String getData ()
  {
    return m_sData;
  }

  /**
   * @return Column's name, as defined by columns.name.
   */
  @Nullable
  public String getName ()
  {
    return m_sName;
  }

  /**
   * @return Flag to indicate if this column is searchable (true) or not
   *         (false). This is controlled by columns.searchable
   */
  public boolean isSearchable ()
  {
    return m_bSearchable;
  }

  /**
   * @return Flag to indicate if this column is orderable (true) or not (false).
   *         This is controlled by columns.orderable.
   */
  public boolean isOrderable ()
  {
    return m_bOrderable;
  }

  /**
   * @return Individual column search object. May be <code>null</code> if column
   *         is not searchable.
   */
  @Nullable
  public DTSSRequestDataSearch getSearch ()
  {
    return m_aSearch;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Data", m_sData)
                                       .append ("Name", m_sName)
                                       .append ("Searchable", m_bSearchable)
                                       .append ("Orderable", m_bOrderable)
                                       .append ("Search", m_aSearch)
                                       .toString ();
  }
}
