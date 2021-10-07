/*
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
package com.helger.photon.uictrls.datatables.ajax;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.string.StringHelper;
import com.helger.html.CHTMLAttributes;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.IHCCell;
import com.helger.xml.microdom.IMicroQName;

/**
 * This class holds table rows to be used by the DataTables server side
 * handling.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class DataTablesServerDataRow implements Serializable
{
  private static final Logger LOGGER = LoggerFactory.getLogger (DataTablesServerDataRow.class);

  private final String m_sRowID;
  private final String m_sRowClass;
  private ICommonsOrderedMap <IMicroQName, String> m_aRowData;
  private ICommonsOrderedMap <IMicroQName, String> m_aRowAttr;
  private final ICommonsList <DataTablesServerDataCell> m_aCells;

  public DataTablesServerDataRow (@Nonnull final HCRow aRow)
  {
    if (aRow.hasAnyStyle ())
      LOGGER.warn ("Cell has styles assigned which will be lost: " + aRow.getAllStyles ());

    m_sRowID = aRow.getID ();
    m_sRowClass = aRow.getAllClassesAsString ();

    for (final Map.Entry <IMicroQName, String> aEntry : aRow.customAttrs ().entrySet ())
    {
      final IMicroQName aAttrName = aEntry.getKey ();
      if (CHTMLAttributes.isDataAttrName (aAttrName))
      {
        // Data attribute
        if (m_aRowData == null)
          m_aRowData = new CommonsLinkedHashMap <> ();
        m_aRowData.put (aAttrName, aEntry.getValue ());
      }
      else
      {
        // Custom non-data attribute
        if (m_aRowAttr == null)
          m_aRowAttr = new CommonsLinkedHashMap <> ();
        m_aRowAttr.put (aAttrName, aEntry.getValue ());
      }
    }

    m_aCells = new CommonsArrayList <> (aRow.getCellCount ());
    for (final IHCCell <?> aCell : aRow.getChildren ())
      m_aCells.add (new DataTablesServerDataCell (aCell));
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
  public ICommonsOrderedMap <IMicroQName, String> directGetAllRowData ()
  {
    return m_aRowData;
  }

  public boolean hasRowAttr ()
  {
    return CollectionHelper.isNotEmpty (m_aRowAttr);
  }

  @Nullable
  @ReturnsMutableObject ("speed")
  public ICommonsOrderedMap <IMicroQName, String> directGetAllRowAttrs ()
  {
    return m_aRowAttr;
  }

  @Nonnull
  @ReturnsMutableObject
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
