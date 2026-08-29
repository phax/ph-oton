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
package com.helger.photon.uictrls.datatables.ajax;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.base.string.StringImplode;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.paging.IPagingSpec;
import com.helger.collection.paging.PagingSpec;
import com.helger.collection.paging.SortField;

/**
 * The request of a single "on demand" DataTables AJAX call, as passed to an
 * {@link IDataTablesOnDemandDataProvider}. It is a thin, data store agnostic view onto the
 * underlying {@link DTSSRequestData}.
 *
 * @author Philip Helger
 * @since 10.4.0
 */
@Immutable
public class DataTablesOnDemandRequest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (DataTablesOnDemandRequest.class);

  private final DTSSRequestData m_aRequestData;
  private final IPagingSpec m_aPagingSpec;

  /**
   * Constructor.
   *
   * @param aRequestData
   *        The underlying DataTables request data. May not be <code>null</code>.
   */
  public DataTablesOnDemandRequest (@NonNull final DTSSRequestData aRequestData)
  {
    m_aRequestData = ValueEnforcer.notNull (aRequestData, "RequestData");
    m_aPagingSpec = createPagingSpec (aRequestData);
  }

  /**
   * @return The underlying DataTables request data, for everything that is not covered by the
   *         convenience methods of this class. Never <code>null</code>.
   */
  @NonNull
  public DTSSRequestData getRequestData ()
  {
    return m_aRequestData;
  }

  /**
   * @return The draw counter of the request, that must be echoed back in the response. Never
   *         <code>null</code>.
   */
  public int getDraw ()
  {
    return m_aRequestData.getDraw ();
  }

  /**
   * @return The paging specification of this request - which page is requested, and by which fields
   *         it is to be sorted. Never <code>null</code>.
   * @see #createPagingSpec(DTSSRequestData)
   */
  @NonNull
  public IPagingSpec getPagingSpec ()
  {
    return m_aPagingSpec;
  }

  /**
   * @return The global search text as entered by the user. May be <code>null</code> or empty.
   */
  @Nullable
  public String getSearchText ()
  {
    final String [] aSearchTexts = m_aRequestData.getSearch ().getSearchTexts ();
    return aSearchTexts == null ? null : StringImplode.getImploded (' ', aSearchTexts);
  }

  /**
   * @return The global search text, split into the single search terms. May be <code>null</code> or
   *         empty.
   */
  public String @Nullable [] getSearchTexts ()
  {
    return m_aRequestData.getSearch ().getSearchTexts ();
  }

  /**
   * @return <code>true</code> if a global or a column specific search text is present.
   */
  public boolean isSearchActive ()
  {
    return m_aRequestData.isSearchActive ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("RequestData", m_aRequestData)
                                       .append ("PagingSpec", m_aPagingSpec)
                                       .getToString ();
  }

  /**
   * Convert the provided DataTables request data into a data store agnostic paging specification.
   * The sort field name of a column is taken from the <code>name</code> property of the column
   * (see {@link com.helger.photon.uictrls.datatables.column.DataTablesColumnDef#setName(String)}).
   * If no name was assigned, the 0-based column index is used as the field name instead.<br>
   * Note: the field names are provided by the client and must therefore be treated as untrusted
   * input. A data provider may only ever use them to look up a known field - never to build a query
   * fragment from them.
   *
   * @param aRequestData
   *        The request data to be converted. May not be <code>null</code>.
   * @return Never <code>null</code>.
   */
  @NonNull
  public static IPagingSpec createPagingSpec (@NonNull final DTSSRequestData aRequestData)
  {
    ValueEnforcer.notNull (aRequestData, "RequestData");

    final ICommonsList <SortField> aSortFields = new CommonsArrayList <> ();
    final int nColumnCount = aRequestData.getColumnCount ();
    for (final DTSSRequestDataOrderColumn aOrderColumn : aRequestData.directGetAllOrderColumns ())
    {
      final int nColumnIndex = aOrderColumn.getColumnIndex ();
      if (nColumnIndex < 0 || nColumnIndex >= nColumnCount)
      {
        LOGGER.warn ("The DataTables request wants to sort by the non-existing column index " +
                     nColumnIndex +
                     " - only " +
                     nColumnCount +
                     " columns are present. Ignoring this sort field.");
        continue;
      }

      final DTSSRequestDataColumn aColumn = aRequestData.getColumn (nColumnIndex);
      if (!aColumn.isOrderable ())
      {
        LOGGER.warn ("The DataTables request wants to sort by column index " +
                     nColumnIndex +
                     " which is not orderable. Ignoring this sort field.");
        continue;
      }

      // Prefer the explicit column name over the column index
      final String sFieldName = StringHelper.isNotEmpty (aColumn.getName ()) ? aColumn.getName ()
                                                                            : Integer.toString (nColumnIndex);
      aSortFields.add (new SortField (sFieldName, aOrderColumn.getSortDirectionOrDefault ()));
    }

    // A negative display length means "show all"
    return new PagingSpec (Math.max (aRequestData.getDisplayStart (), 0),
                           aRequestData.getDisplayLength (),
                           aSortFields);
  }
}
