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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.utils.IHCSpecialNodes;
import com.helger.json.IJsonObject;
import com.helger.json.IJsonProvider;
import com.helger.json.impl.JsonObject;

/**
 * Encapsulates the response to a single DataTables AJAX request
 *
 * @author Philip Helger
 */
final class ResponseData implements IJsonProvider
{
  private final int m_nTotalRecords;
  private final int m_nTotalDisplayRecords;
  private final int m_nEcho;
  private final String m_sColumns;
  private final List <Map <String, String>> m_aData;
  private final IHCSpecialNodes m_aSpecialNodes;

  /**
   * @param nTotalRecords
   *        Unfiltered number of records
   * @param nTotalDisplayRecords
   *        Total number of records after filtering
   * @param nEcho
   *        As sent by the request
   * @param sColumns
   *        Column names
   * @param aData
   *        Main data, where each list item represents a single row (as map from
   *        column-index to HTML content)
   * @param aSpecialNodes
   *        Special nodes for the AJAX response
   */
  ResponseData (final int nTotalRecords,
                final int nTotalDisplayRecords,
                final int nEcho,
                @Nullable final String sColumns,
                @Nonnull final List <Map <String, String>> aData,
                @Nonnull final IHCSpecialNodes aSpecialNodes)
  {
    ValueEnforcer.notNull (aData, "Data");
    ValueEnforcer.notNull (aSpecialNodes, "SpecialNodes");
    m_nTotalRecords = nTotalRecords;
    m_nTotalDisplayRecords = nTotalDisplayRecords;
    m_nEcho = nEcho;
    m_sColumns = sColumns;
    m_aData = aData;
    m_aSpecialNodes = aSpecialNodes;
  }

  /**
   * @return Total records, before filtering (i.e. the total number of records
   *         in the database)
   */
  public int getTotalRecords ()
  {
    return m_nTotalRecords;
  }

  /**
   * @return Total records, after filtering (i.e. the total number of records
   *         after filtering has been applied - not just the number of records
   *         being returned in this result set)
   */
  public int getTotalDisplayRecords ()
  {
    return m_nTotalDisplayRecords;
  }

  /**
   * @return An unaltered copy of sEcho sent from the client side. This
   *         parameter will change with each draw (it is basically a draw count)
   *         - so it is important that this is implemented.
   */
  public int getEcho ()
  {
    return m_nEcho;
  }

  /**
   * @return Optional - this is a string of column names, comma separated (used
   *         in combination with sName) which will allow DataTables to reorder
   *         data on the client-side if required for display. Note that the
   *         number of column names returned must exactly match the number of
   *         columns in the table. For a more flexible JSON format, please
   *         consider using mDataProp.
   */
  @Nullable
  public String getColumns ()
  {
    return m_sColumns;
  }

  /**
   * @return The data in a 2D array.
   */
  @Nonnull
  @ReturnsMutableCopy
  public List <Map <String, String>> getData ()
  {
    return CollectionHelper.newList (m_aData);
  }

  @Nonnull
  public IJsonObject getAsJson ()
  {
    final IJsonObject ret = new JsonObject ();
    ret.add ("recordsTotal", m_nTotalRecords);
    ret.add ("recordsFiltered", m_nTotalDisplayRecords);
    ret.add ("draw", Integer.toString (m_nEcho));
    if (StringHelper.hasText (m_sColumns))
      ret.add ("sColumns", m_sColumns);
    final List <JsonObject> aObjs = new ArrayList <JsonObject> ();
    for (final Map <String, String> aRow : m_aData)
    {
      final JsonObject aObj = new JsonObject ();
      for (final Map.Entry <String, String> aEntry : aRow.entrySet ())
        aObj.add (aEntry.getKey (), aEntry.getValue ());
      aObjs.add (aObj);
    }
    ret.add ("aaData", aObjs);
    return ret;
  }

  @Nonnull
  public IHCSpecialNodes getSpecialNodes ()
  {
    return m_aSpecialNodes;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("totalRecords", m_nTotalRecords)
                                       .append ("totalDisplayRecords", m_nTotalDisplayRecords)
                                       .append ("echo", m_nEcho)
                                       .append ("columns", m_sColumns)
                                       .append ("data", m_aData)
                                       .toString ();
  }
}
