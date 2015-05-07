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
package com.helger.webctrls.datatables.ajax;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.string.ToStringGenerator;

/**
 * Encapsulates the request data for a single column
 * 
 * @author Philip Helger
 */
@Immutable
final class RequestDataColumn
{
  private final boolean m_bSearchable;
  private final RequestDataSearch m_aSearch;
  private final boolean m_bSortable;
  private final String m_sDataProp;

  RequestDataColumn (final boolean bSearchable,
                     @Nullable final String sSearchText,
                     final boolean bSearchRegEx,
                     final boolean bSortable,
                     @Nullable final String sDataProp)
  {
    m_bSearchable = bSearchable;
    m_aSearch = bSearchable ? new RequestDataSearch (sSearchText, bSearchRegEx) : null;
    m_bSortable = bSortable;
    m_sDataProp = sDataProp;
  }

  /**
   * @return Indicator for if a column is flagged as searchable or not on the
   *         client-side
   */
  public boolean isSearchable ()
  {
    return m_bSearchable;
  }

  /**
   * @return Individual column search object. May be <code>null</code> if column
   *         is not searchable.
   */
  @Nullable
  public RequestDataSearch getSearch ()
  {
    return m_aSearch;
  }

  /**
   * @return Indicator for if a column is flagged as sortable or not on the
   *         client-side
   */
  public boolean isSortable ()
  {
    return m_bSortable;
  }

  /**
   * @return The value specified by mDataProp for each column. This can be
   *         useful for ensuring that the processing of data is independent from
   *         the order of the columns.
   */
  @Nullable
  public String getDataProp ()
  {
    return m_sDataProp;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("searchable", m_bSearchable)
                                       .append ("search", m_aSearch)
                                       .append ("sortable", m_bSortable)
                                       .append ("dataProp", m_sDataProp)
                                       .toString ();
  }
}
