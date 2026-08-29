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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jspecify.annotations.Nullable;
import org.junit.Test;

import com.helger.base.compare.ESortOrder;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.paging.IPagingSpec;
import com.helger.collection.paging.SortField;

/**
 * Test class for class {@link DataTablesOnDemandRequest}.
 *
 * @author Philip Helger
 */
public final class DataTablesOnDemandRequestTest
{
  private static DTSSRequestDataColumn _col (@Nullable final String sName, final boolean bOrderable)
  {
    return new DTSSRequestDataColumn (null, sName, true, bOrderable, null, false);
  }

  private static DTSSRequestData _req (final int nDisplayStart,
                                       final int nDisplayLength,
                                       @Nullable final String sSearchText,
                                       final ICommonsList <DTSSRequestDataColumn> aColumns,
                                       final DTSSRequestDataOrderColumn... aOrderColumns)
  {
    return new DTSSRequestData (1,
                                nDisplayStart,
                                nDisplayLength,
                                sSearchText,
                                false,
                                aColumns,
                                new CommonsArrayList <> (aOrderColumns));
  }

  @Test
  public void testPagingOnly ()
  {
    final DTSSRequestData aRequestData = _req (50,
                                               25,
                                               null,
                                               new CommonsArrayList <> (_col ("id", true), _col ("name", true)));
    final DataTablesOnDemandRequest aRequest = new DataTablesOnDemandRequest (aRequestData);

    final IPagingSpec aSpec = aRequest.getPagingSpec ();
    assertEquals (50, aSpec.getStartIndex ());
    assertEquals (25, aSpec.getMaxCount ());
    assertFalse (aSpec.isUnlimited ());
    assertFalse (aSpec.hasSortFields ());
    assertFalse (aRequest.isSearchActive ());
  }

  @Test
  public void testShowAllEntries ()
  {
    final DTSSRequestData aRequestData = _req (0, -1, null, new CommonsArrayList <> (_col ("id", true)));
    assertTrue (new DataTablesOnDemandRequest (aRequestData).getPagingSpec ().isUnlimited ());
  }

  @Test
  public void testSortByColumnName ()
  {
    final DTSSRequestData aRequestData = _req (0,
                                               25,
                                               null,
                                               new CommonsArrayList <> (_col ("id", true), _col ("name", true)),
                                               new DTSSRequestDataOrderColumn (1, ESortOrder.DESCENDING),
                                               new DTSSRequestDataOrderColumn (0, ESortOrder.ASCENDING));
    final IPagingSpec aSpec = new DataTablesOnDemandRequest (aRequestData).getPagingSpec ();

    // The order of precedence must be retained
    assertEquals (new CommonsArrayList <> (SortField.descending ("name"), SortField.ascending ("id")),
                  aSpec.getAllSortFields ());
  }

  @Test
  public void testSortWithoutColumnName ()
  {
    // No column name assigned - the column index is used instead
    final DTSSRequestData aRequestData = _req (0,
                                               25,
                                               null,
                                               new CommonsArrayList <> (_col (null, true), _col ("", true)),
                                               new DTSSRequestDataOrderColumn (1, ESortOrder.ASCENDING));
    assertEquals (new CommonsArrayList <> (SortField.ascending ("1")),
                  new DataTablesOnDemandRequest (aRequestData).getPagingSpec ().getAllSortFields ());
  }

  @Test
  public void testIllegalSortColumnsAreIgnored ()
  {
    // Column index out of range and a non-orderable column
    final DTSSRequestData aRequestData = _req (0,
                                               25,
                                               null,
                                               new CommonsArrayList <> (_col ("id", true), _col ("name", false)),
                                               new DTSSRequestDataOrderColumn (99, ESortOrder.ASCENDING),
                                               new DTSSRequestDataOrderColumn (-1, ESortOrder.ASCENDING),
                                               new DTSSRequestDataOrderColumn (1, ESortOrder.ASCENDING),
                                               new DTSSRequestDataOrderColumn (0, ESortOrder.ASCENDING));
    assertEquals (new CommonsArrayList <> (SortField.ascending ("id")),
                  new DataTablesOnDemandRequest (aRequestData).getPagingSpec ().getAllSortFields ());
  }

  @Test
  public void testSearchText ()
  {
    final DTSSRequestData aRequestData = _req (0,
                                               25,
                                               "  foo   bar ",
                                               new CommonsArrayList <> (_col ("id", true)));
    final DataTablesOnDemandRequest aRequest = new DataTablesOnDemandRequest (aRequestData);
    assertTrue (aRequest.isSearchActive ());
    assertEquals ("foo bar", aRequest.getSearchText ());
    assertEquals (2, aRequest.getSearchTexts ().length);
  }
}
