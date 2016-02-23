/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.CommonsLinkedHashMap;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.collection.ext.ICommonsOrderedMap;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.IHCConversionSettings;
import com.helger.html.hc.html.AbstractHCElement;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.IHCCell;

/**
 * This class holds table rows to be used by the DataTables server side
 * handling.
 *
 * @author Philip Helger
 */
public final class DataTablesServerDataRow implements Serializable
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (DataTablesServerDataRow.class);

  private final String m_sRowID;
  private final String m_sRowClass;
  private ICommonsOrderedMap <String, String> m_aRowData;
  private ICommonsOrderedMap <String, String> m_aRowAttr;
  private final ICommonsList <DataTablesServerDataCell> m_aCells;

  public DataTablesServerDataRow (@Nonnull final HCRow aRow, @Nonnull final IHCConversionSettings aCS)
  {
    if (aRow.hasAnyStyle ())
      s_aLogger.warn ("Cell has styles assigned which will be lost: " + aRow.getAllStyles ());

    m_sRowID = aRow.getID ();
    m_sRowClass = aRow.getAllClassesAsString ();

    if (aRow.hasCustomAttrs ())
      for (final Map.Entry <String, String> aEntry : aRow.getAllCustomAttrsIterable ())
        if (AbstractHCElement.isDataAttrName (aEntry.getKey ()))
        {
          // Data attribute
          if (m_aRowData == null)
            m_aRowData = new CommonsLinkedHashMap <> ();
          m_aRowData.put (aEntry.getKey (), aEntry.getValue ());
        }
        else
        {
          // Custom non-data attribute
          if (m_aRowAttr == null)
            m_aRowAttr = new CommonsLinkedHashMap <> ();
          m_aRowAttr.put (aEntry.getKey (), aEntry.getValue ());
        }

    m_aCells = new CommonsArrayList <> (aRow.getCellCount ());
    for (final IHCCell <?> aCell : aRow.getAllCellsIterable ())
      m_aCells.add (new DataTablesServerDataCell (aCell, aCS));
  }

  public boolean hasRowID ()
  {
    return StringHelper.hasText (m_sRowID);
  }

  @Nullable
  public String getRowID ()
  {
    return m_sRowID;
  }

  public boolean hasRowClass ()
  {
    return StringHelper.hasText (m_sRowClass);
  }

  /**
   * @return All CSS classes of the row as one big string
   */
  @Nullable
  public String getRowClass ()
  {
    return m_sRowClass;
  }

  public boolean hasRowData ()
  {
    return CollectionHelper.isNotEmpty (m_aRowData);
  }

  @Nonnull
  @ReturnsMutableObject ("speed")
  public ICommonsOrderedMap <String, String> directGetAllRowData ()
  {
    return m_aRowData;
  }

  public boolean hasRowAttr ()
  {
    return CollectionHelper.isNotEmpty (m_aRowAttr);
  }

  @Nullable
  @ReturnsMutableObject ("speed")
  public ICommonsOrderedMap <String, String> directGetAllRowAttrs ()
  {
    return m_aRowAttr;
  }

  @Nonnull
  @ReturnsMutableObject ("speed")
  public ICommonsList <DataTablesServerDataCell> directGetAllCells ()
  {
    return m_aCells;
  }

  @Nonnull
  public DataTablesServerDataCell getCellAtIndex (@Nonnegative final int nIndex)
  {
    return m_aCells.get (nIndex);
  }
}
