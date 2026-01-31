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
package com.helger.photon.app.html;

import java.util.Collection;
import java.util.Iterator;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonnegative;
import com.helger.annotation.concurrent.GuardedBy;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.concurrent.SimpleReadWriteLock;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.state.EChange;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.CommonsLinkedHashSet;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsOrderedSet;
import com.helger.collection.commons.ICommonsSet;
import com.helger.html.resource.js.IJSPathProvider;

/**
 * This class keeps track of all the JS files that must be included for a single request, so that
 * the controls are working properly.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class JSResourceSet implements IWebResourceSet <IJSPathProvider>
{
  private static final Logger LOGGER = LoggerFactory.getLogger (JSResourceSet.class);

  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();

  // For order with indexed insertion
  @GuardedBy ("m_aRWLock")
  private final ICommonsList <IJSPathProvider> m_aList = new CommonsArrayList <> ();

  // For uniqueness check
  @GuardedBy ("m_aRWLock")
  private final ICommonsSet <IJSPathProvider> m_aItems = new CommonsLinkedHashSet <> ();

  @GuardedBy ("m_aRWLock")
  private boolean m_bIsCollected = false;

  public JSResourceSet ()
  {}

  public JSResourceSet (@NonNull final JSResourceSet aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");
    for (final IJSPathProvider aItem : aOther)
      addItem (aItem);
  }

  public JSResourceSet (@NonNull final Collection <? extends IJSPathProvider> aOther)
  {
    ValueEnforcer.notEmptyNoNullValue (aOther, "Other");
    for (final IJSPathProvider aItem : aOther)
      addItem (aItem);
  }

  public JSResourceSet (@NonNull final IJSPathProvider... aOther)
  {
    ValueEnforcer.notEmptyNoNullValue (aOther, "Other");
    for (final IJSPathProvider aItem : aOther)
      addItem (aItem);
  }

  private static void _collectWarn (@NonNull final String sMsg)
  {
    LOGGER.warn (sMsg);
  }

  @NonNull
  public EChange addItem (@NonNull final IJSPathProvider aJSPathProvider)
  {
    return addItem (-1, aJSPathProvider);
  }

  @NonNull
  public EChange addItem (final int nIndex, @NonNull final IJSPathProvider aJSPathProvider)
  {
    ValueEnforcer.notNull (aJSPathProvider, "JSPathProvider");

    return m_aRWLock.writeLockedGet ( () -> {
      // Check uniqueness
      if (!m_aItems.add (aJSPathProvider))
        return EChange.UNCHANGED;

      // Honor index
      if (nIndex >= 0)
        m_aList.add (nIndex, aJSPathProvider);
      else
        m_aList.add (aJSPathProvider);

      if (m_bIsCollected)
        _collectWarn ("Adding item " + aJSPathProvider + " after collection!");
      return EChange.CHANGED;
    });
  }

  @NonNull
  public EChange addItems (@NonNull final IWebResourceSet <? extends IJSPathProvider> aItems)
  {
    ValueEnforcer.notNull (aItems, "Items");

    EChange ret = EChange.UNCHANGED;
    for (final IJSPathProvider aItem : aItems)
      ret = ret.or (addItem (aItem));
    return ret;
  }

  @NonNull
  public EChange addItems (final int nIndex, @NonNull final IWebResourceSet <? extends IJSPathProvider> aItems)
  {
    ValueEnforcer.notNull (aItems, "Items");

    if (nIndex < 0)
      return addItems (aItems);

    EChange ret = EChange.UNCHANGED;
    int nCurIndex = nIndex;
    for (final IJSPathProvider aItem : aItems)
      if (addItem (nCurIndex, aItem).isChanged ())
      {
        ret = EChange.CHANGED;
        nCurIndex++;
      }
    return ret;
  }

  @NonNull
  public EChange removeItem (@NonNull final IJSPathProvider aJSPathProvider)
  {
    ValueEnforcer.notNull (aJSPathProvider, "JSPathProvider");

    return m_aRWLock.writeLockedGet ( () -> {
      if (!m_aItems.remove (aJSPathProvider))
        return EChange.UNCHANGED;
      m_aList.remove (aJSPathProvider);

      if (m_bIsCollected)
        _collectWarn ("Removed item " + aJSPathProvider + " after collection!");
      return EChange.CHANGED;
    });
  }

  @NonNull
  public EChange removeAll ()
  {
    return m_aRWLock.writeLockedGet ( () -> {
      if (m_aItems.isEmpty ())
        return EChange.UNCHANGED;
      m_aItems.clear ();
      m_aList.clear ();

      if (m_bIsCollected)
        _collectWarn ("Removed all items after collection!");
      return EChange.CHANGED;
    });
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsOrderedSet <IJSPathProvider> getAllItems ()
  {
    return m_aRWLock.readLockedGet ( () -> new CommonsLinkedHashSet <> (m_aList));
  }

  public void getAllItems (@NonNull final Collection <? super IJSPathProvider> aTarget)
  {
    ValueEnforcer.notNull (aTarget, "Target");

    m_aRWLock.readLockedBoolean ( () -> aTarget.addAll (m_aList));
  }

  public boolean isEmpty ()
  {
    return m_aRWLock.readLockedBoolean (m_aList::isEmpty);
  }

  public boolean isNotEmpty ()
  {
    return m_aRWLock.readLockedBoolean (m_aList::isNotEmpty);
  }

  @Nonnegative
  @Override
  public int size ()
  {
    return m_aRWLock.readLockedInt (m_aList::size);
  }

  @NonNull
  public Iterator <IJSPathProvider> iterator ()
  {
    return m_aRWLock.readLockedGet (m_aList::iterator);
  }

  public boolean isCollected ()
  {
    return m_aRWLock.readLockedBoolean ( () -> m_bIsCollected);
  }

  public void markAsCollected ()
  {
    m_aRWLock.writeLocked ( () -> {
      if (m_bIsCollected)
        _collectWarn ("Resource set was already collected before!");
      m_bIsCollected = true;
    });
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final JSResourceSet rhs = (JSResourceSet) o;
    return m_aList.equals (rhs.m_aList);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aList).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("list", m_aList).getToString ();
  }
}
