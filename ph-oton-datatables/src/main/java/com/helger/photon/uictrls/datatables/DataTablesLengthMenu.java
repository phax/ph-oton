/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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

import java.util.Locale;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.text.display.ConstantHasDisplayText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.html.jscode.JSArray;

@NotThreadSafe
public class DataTablesLengthMenu
{
  /** The numerical value representing 'show all items' */
  public static final int COUNT_ALL = -1;

  private final ICommonsList <DataTablesLengthMenuItem> m_aList = new CommonsArrayList<> ();

  public DataTablesLengthMenu ()
  {}

  @Nonnull
  public DataTablesLengthMenu addItemAll ()
  {
    return addItem (COUNT_ALL, EDataTablesText.ALL);
  }

  @Nonnull
  public DataTablesLengthMenu addItem (final int nItemCount)
  {
    return addItem (nItemCount, new ConstantHasDisplayText (Integer.toString (nItemCount)));
  }

  @Nonnull
  public DataTablesLengthMenu addItem (final int nItemCount, @Nonnull final IHasDisplayText aText)
  {
    return addItem (new DataTablesLengthMenuItem (nItemCount, aText));
  }

  @Nonnull
  public DataTablesLengthMenu addItem (@Nonnull final DataTablesLengthMenuItem aItem)
  {
    ValueEnforcer.notNull (aItem, "Item");
    m_aList.add (aItem);
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsOrderedMap <Integer, String> getAsMap (@Nonnull final Locale aDisplayLocale)
  {
    final ICommonsOrderedMap <Integer, String> ret = new CommonsLinkedHashMap<> ();
    for (final DataTablesLengthMenuItem aItem : m_aList)
      ret.put (Integer.valueOf (aItem.getItemCount ()), aItem.getDisplayText (aDisplayLocale));
    return ret;
  }

  public boolean isEmpty ()
  {
    return m_aList.isEmpty ();
  }

  @Nonnegative
  public int getItemCount ()
  {
    return m_aList.size ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <DataTablesLengthMenuItem> getAllItems ()
  {
    return m_aList.getClone ();
  }

  @Nullable
  public DataTablesLengthMenuItem getItemAtIndex (@Nonnegative final int nIndex)
  {
    return m_aList.getAtIndex (nIndex);
  }

  @Nonnull
  public JSArray getAsJSArray (@Nonnull final Locale aDisplayLocale)
  {
    final JSArray aArray1 = new JSArray ();
    final JSArray aArray2 = new JSArray ();
    for (final DataTablesLengthMenuItem aItem : m_aList)
    {
      aArray1.add (aItem.getItemCount ());
      final String sValue = aItem.getDisplayText (aDisplayLocale);
      if (sValue != null)
        aArray2.add (sValue);
      else
        aArray2.add (Integer.toString (aItem.getItemCount ()));
    }
    return new JSArray ().add (aArray1).add (aArray2);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("list", m_aList).getToString ();
  }
}
