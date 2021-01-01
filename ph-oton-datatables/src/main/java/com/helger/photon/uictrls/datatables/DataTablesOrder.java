/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.compare.ESortOrder;
import com.helger.html.jscode.JSArray;

public class DataTablesOrder implements Serializable
{
  private final ICommonsList <DataTablesOrderColumn> m_aColumns = new CommonsArrayList <> ();

  public DataTablesOrder ()
  {}

  @Nonnull
  public DataTablesOrder addColumn (@Nonnegative final int nIndex, @Nonnull final ESortOrder eSortOrder)
  {
    m_aColumns.add (new DataTablesOrderColumn (nIndex, eSortOrder));
    return this;
  }

  @Nonnull
  public JSArray getAsJS ()
  {
    final JSArray ret = new JSArray ();
    for (final DataTablesOrderColumn aSortColumn : m_aColumns)
      ret.add (aSortColumn.getAsJS ());
    return ret;
  }
}
