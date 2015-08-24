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

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.special.IHCSpecialNodes;
import com.helger.json.IHasJson;
import com.helger.json.IJsonObject;
import com.helger.json.JsonObject;

/**
 * Encapsulates the response to a single DataTables AJAX request
 *
 * @author Philip Helger
 */
final class DTSSResponseData implements IHasJson
{
  private final int m_nDraw;
  private final int m_nTotalRecords;
  private final int m_nTotalDisplayRecords;
  private final List <JsonObject> m_aData;
  private final IHCSpecialNodes m_aSpecialNodes;
  private final String m_sErrorMsg;

  /**
   * @param nDraw
   *        As sent by the request
   * @param nTotalRecords
   *        Unfiltered number of records
   * @param nTotalDisplayRecords
   *        Total number of records after filtering
   * @param aData
   *        Main data, where each list item represents a single row (as map from
   *        column-index to HTML content)
   * @param sErrorMsg
   *        Optional error message
   * @param aSpecialNodes
   *        Special nodes for the AJAX response
   */
  DTSSResponseData (final int nDraw,
                    final int nTotalRecords,
                    final int nTotalDisplayRecords,
                    @Nullable final List <JsonObject> aData,
                    @Nullable final String sErrorMsg,
                    @Nonnull final IHCSpecialNodes aSpecialNodes)
  {
    ValueEnforcer.notNull (aSpecialNodes, "SpecialNodes");
    m_nDraw = nDraw;
    m_nTotalRecords = nTotalRecords;
    m_nTotalDisplayRecords = nTotalDisplayRecords;
    m_aData = aData;
    m_sErrorMsg = sErrorMsg;
    m_aSpecialNodes = aSpecialNodes;
  }

  /**
   * @return The draw counter that this object is a response to - from the draw
   *         parameter sent as part of the data request. Note that it is
   *         strongly recommended for security reasons that you cast this
   *         parameter to an integer, rather than simply echoing back to the
   *         client what it sent in the draw parameter, in order to prevent
   *         Cross Site Scripting (XSS) attacks
   */
  public int getDraw ()
  {
    return m_nDraw;
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
   *         being returned for this page of data).
   */
  public int getTotalDisplayRecords ()
  {
    return m_nTotalDisplayRecords;
  }

  /**
   * @return The data to be displayed in the table. This is an array of data
   *         source objects, one for each row, which will be used by DataTables.
   */
  @Nullable
  @ReturnsMutableCopy
  public List <JsonObject> getData ()
  {
    return m_aData == null ? null : CollectionHelper.newList (m_aData);
  }

  @Nullable
  public String getErrorMessage ()
  {
    return m_sErrorMsg;
  }

  @Nonnull
  public IJsonObject getAsJson ()
  {
    final IJsonObject ret = new JsonObject ();
    ret.add ("draw", Integer.toString (m_nDraw));
    ret.add ("recordsTotal", m_nTotalRecords);
    ret.add ("recordsFiltered", m_nTotalDisplayRecords);
    if (m_aData != null)
      ret.add ("data", m_aData);
    else
      ret.add ("error", m_sErrorMsg);
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
    return new ToStringGenerator (this).append ("Draw", m_nDraw)
                                       .append ("TotalRecords", m_nTotalRecords)
                                       .append ("TotalDisplayRecords", m_nTotalDisplayRecords)
                                       .append ("Data", m_aData)
                                       .append ("ErrorMsg", m_sErrorMsg)
                                       .append ("SpecialNodes", m_aSpecialNodes)
                                       .toString ();
  }
}
