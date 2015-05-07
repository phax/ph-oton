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
package com.helger.photon.uictrls.datatables;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.text.impl.ConstantTextProvider;

public class DataTablesLengthMenuList
{
  public static final int COUNT_ALL = -1;

  private final List <DataTablesLengthMenuItem> m_aList = new ArrayList <DataTablesLengthMenuItem> ();

  public DataTablesLengthMenuList ()
  {}

  @Nonnull
  public DataTablesLengthMenuList addItemAll ()
  {
    return addItem (COUNT_ALL, EDataTablesText.ALL);
  }

  @Nonnull
  public DataTablesLengthMenuList addItem (final int nItemCount)
  {
    return addItem (nItemCount, new ConstantTextProvider (Integer.toString (nItemCount)));
  }

  @Nonnull
  public DataTablesLengthMenuList addItem (final int nItemCount, @Nonnull final IHasDisplayText aText)
  {
    return addItem (new DataTablesLengthMenuItem (nItemCount, aText));
  }

  @Nonnull
  public DataTablesLengthMenuList addItem (@Nonnull final DataTablesLengthMenuItem aItem)
  {
    ValueEnforcer.notNull (aItem, "Item");
    m_aList.add (aItem);
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Map <Integer, String> getAsMap (@Nonnull final Locale aDisplayLocale)
  {
    final Map <Integer, String> ret = new LinkedHashMap <Integer, String> ();
    for (final DataTablesLengthMenuItem aItem : m_aList)
      ret.put (Integer.valueOf (aItem.getItemCount ()), aItem.getDisplayText (aDisplayLocale));
    return ret;
  }

  public boolean isEmpty ()
  {
    return m_aList.isEmpty ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <DataTablesLengthMenuItem> getAllItems ()
  {
    return CollectionHelper.newList (m_aList);
  }

  @Nullable
  public DataTablesLengthMenuItem getItemAtIndex (@Nonnegative final int nIndex)
  {
    return CollectionHelper.getSafe (m_aList, nIndex);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("list", m_aList).toString ();
  }
}
