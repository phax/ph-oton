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
package com.helger.photon.uictrls.datatables;

/**
 * Defines how the server side data of a DataTables is provided. This is only relevant if
 * {@link DataTables#isServerSide()} is <code>true</code>.
 *
 * @author Philip Helger
 * @since 10.4.0
 */
public enum EDataTablesServerSideMode
{
  /**
   * The whole table is rendered up-front and the result is stored in the session, so that paging,
   * sorting and filtering can be applied on that snapshot. This is simple to use, because the
   * calling code does not need to be aware of the paging at all, but the memory consumption is
   * proportional to the number of rows times the number of sessions.
   */
  PRERENDERED,
  /**
   * Nothing is stored in the session. Each AJAX request is answered by the application, which is
   * expected to query only the rows of the requested page. This is the only mode that scales to
   * large tables, but the application must be able to page, sort and filter in its data store.
   *
   * @see com.helger.photon.uictrls.datatables.ajax.IDataTablesOnDemandDataProvider
   */
  ON_DEMAND;

  /** Default mode: {@link #PRERENDERED} */
  public static final EDataTablesServerSideMode DEFAULT = PRERENDERED;

  /**
   * @return <code>true</code> if this is the {@link #PRERENDERED} mode.
   */
  public boolean isPrerendered ()
  {
    return this == PRERENDERED;
  }

  /**
   * @return <code>true</code> if this is the {@link #ON_DEMAND} mode.
   */
  public boolean isOnDemand ()
  {
    return this == ON_DEMAND;
  }
}
