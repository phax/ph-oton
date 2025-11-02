/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.tinymce4.type;

import java.util.List;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.CheckForSigned;
import com.helger.annotation.Nonnegative;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.clone.ICloneable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.EChange;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.CollectionHelper;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;

public class TinyMCE4MenubarItemList implements ICloneable <TinyMCE4MenubarItemList>
{
  private final ICommonsList <ETinyMCE4MenuItem> m_aList;

  /**
   * Constructor
   */
  public TinyMCE4MenubarItemList ()
  {
    m_aList = new CommonsArrayList <> ();
  }

  /**
   * Constructor
   *
   * @param aList
   *        Separators are denoted by <code>null</code> elements.
   */
  public TinyMCE4MenubarItemList (@NonNull final ETinyMCE4MenuItem... aList)
  {
    ValueEnforcer.notNull (aList, "List");
    m_aList = new CommonsArrayList <> (aList);
  }

  /**
   * Constructor
   *
   * @param aList
   *        May not contain <code>null</code> elements.
   */
  public TinyMCE4MenubarItemList (@NonNull final List <ETinyMCE4MenuItem> aList)
  {
    ValueEnforcer.notNull (aList, "List");
    m_aList = new CommonsArrayList <> (aList);
  }

  /**
   * Other
   *
   * @param aOther
   *        Source object to copy from. May not be <code>null</code>.
   */
  public TinyMCE4MenubarItemList (@NonNull final TinyMCE4MenubarItemList aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");
    m_aList = aOther.m_aList.getClone ();
  }

  /**
   * @return The default toolbar. Separators are denoted by <code>null</code> elements. See the
   *         respective theme.js file.
   */
  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <ETinyMCE4MenuItem> getAllMenuItems ()
  {
    return new CommonsArrayList <> (m_aList);
  }

  @NonNull
  public TinyMCE4MenubarItemList addMenuItem (@NonNull final ETinyMCE4MenuItem eMenuItem)
  {
    ValueEnforcer.notNull (eMenuItem, "MenuItem");
    m_aList.add (eMenuItem);
    return this;
  }

  @NonNull
  public TinyMCE4MenubarItemList addMenuItem (@Nonnegative final int nIndex, @NonNull final ETinyMCE4MenuItem eMenuItem)
  {
    ValueEnforcer.notNull (eMenuItem, "MenuItem");
    m_aList.add (nIndex, eMenuItem);
    return this;
  }

  @CheckForSigned
  public int getMenuItemIndex (@NonNull final ETinyMCE4MenuItem eMenuItem)
  {
    ValueEnforcer.notNull (eMenuItem, "MenuItem");
    return m_aList.indexOf (eMenuItem);
  }

  @NonNull
  public EChange removeMenuItem (@NonNull final ETinyMCE4MenuItem eMenuItem)
  {
    ValueEnforcer.notNull (eMenuItem, "MenuItem");
    return EChange.valueOf (m_aList.remove (eMenuItem));
  }

  @NonNull
  public EChange removeAtIndex (@Nonnegative final int nIndex)
  {
    return CollectionHelper.removeAtIndex (m_aList, nIndex);
  }

  @NonNull
  public EChange removeAll ()
  {
    if (m_aList.isEmpty ())
      return EChange.UNCHANGED;
    m_aList.clear ();
    return EChange.CHANGED;
  }

  @NonNull
  public String getAsOptionString ()
  {
    final StringBuilder aSB = new StringBuilder ();
    for (final ETinyMCE4MenuItem eMenuItem : m_aList)
    {
      if (aSB.length () > 0)
        aSB.append (' ');
      aSB.append (eMenuItem.getValue ());
    }
    return aSB.toString ();
  }

  @NonNull
  public TinyMCE4MenubarItemList getClone ()
  {
    return new TinyMCE4MenubarItemList (this);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("list", m_aList).getToString ();
  }
}
