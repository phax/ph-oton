/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsHashSet;
import com.helger.commons.collection.impl.CommonsLinkedHashSet;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsOrderedSet;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.resource.css.ICSSPathProvider;

/**
 * This class keeps track of all the CSS files that must be included for a
 * single request, so that the controls are working properly.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class CSSResourceSet implements IWebResourceSet <ICSSPathProvider>
{
  private static final Logger LOGGER = LoggerFactory.getLogger (CSSResourceSet.class);

  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();

  // For order with indexed insertion
  @GuardedBy ("m_aRWLock")
  private final ICommonsList <ICSSPathProvider> m_aList = new CommonsArrayList <> ();

  // For uniqueness check
  @GuardedBy ("m_aRWLock")
  private final ICommonsSet <ICSSPathProvider> m_aSet = new CommonsHashSet <> ();

  @GuardedBy ("m_aRWLock")
  private boolean m_bIsCollected = false;

  public CSSResourceSet ()
  {}

  public CSSResourceSet (@Nonnull final CSSResourceSet aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");
    for (final ICSSPathProvider aItem : aOther)
      addItem (aItem);
  }

  public CSSResourceSet (@Nonnull final Collection <? extends ICSSPathProvider> aOther)
  {
    ValueEnforcer.notEmptyNoNullValue (aOther, "Other");
    for (final ICSSPathProvider aItem : aOther)
      addItem (aItem);
  }

  public CSSResourceSet (@Nonnull final ICSSPathProvider... aOther)
  {
    ValueEnforcer.notEmptyNoNullValue (aOther, "Other");
    for (final ICSSPathProvider aItem : aOther)
      addItem (aItem);
  }

  private static void _collectWarn (@Nonnull final String sMsg)
  {
    LOGGER.warn (sMsg);
  }

  @Nonnull
  public EChange addItem (@Nonnull final ICSSPathProvider aCSSPathProvider)
  {
    return addItem (-1, aCSSPathProvider);
  }

  @Nonnull
  public EChange addItem (final int nIndex, @Nonnull final ICSSPathProvider aCSSPathProvider)
  {
    ValueEnforcer.notNull (aCSSPathProvider, "CSSPathProvider");

    return m_aRWLock.writeLockedGet ( () -> {
      // Check uniqueness
      if (!m_aSet.add (aCSSPathProvider))
        return EChange.UNCHANGED;

      // Honor index
      if (nIndex >= 0)
        m_aList.add (nIndex, aCSSPathProvider);
      else
        m_aList.add (aCSSPathProvider);

      if (m_bIsCollected)
        _collectWarn ("Adding item " + aCSSPathProvider + " after collection!");
      return EChange.CHANGED;
    });
  }

  @Nonnull
  public EChange addItems (@Nonnull final IWebResourceSet <? extends ICSSPathProvider> aItems)
  {
    ValueEnforcer.notNull (aItems, "Items");

    EChange ret = EChange.UNCHANGED;
    for (final ICSSPathProvider aItem : aItems)
      ret = ret.or (addItem (aItem));
    return ret;
  }

  @Nonnull
  public EChange addItems (final int nIndex, @Nonnull final IWebResourceSet <? extends ICSSPathProvider> aItems)
  {
    ValueEnforcer.notNull (aItems, "Items");

    if (nIndex < 0)
      return addItems (aItems);

    EChange ret = EChange.UNCHANGED;
    int nCurIndex = nIndex;
    for (final ICSSPathProvider aItem : aItems)
      if (addItem (nCurIndex, aItem).isChanged ())
      {
        ret = EChange.CHANGED;
        nCurIndex++;
      }
    return ret;
  }

  @Nonnull
  public EChange removeItem (@Nonnull final ICSSPathProvider aCSSPathProvider)
  {
    ValueEnforcer.notNull (aCSSPathProvider, "CSSPathProvider");

    return m_aRWLock.writeLockedGet ( () -> {
      if (!m_aSet.remove (aCSSPathProvider))
        return EChange.UNCHANGED;
      m_aList.remove (aCSSPathProvider);

      if (m_bIsCollected)
        _collectWarn ("Removed item " + aCSSPathProvider + " after collection!");
      return EChange.CHANGED;
    });
  }

  @Nonnull
  public EChange removeAll ()
  {
    return m_aRWLock.writeLockedGet ( () -> {
      if (m_aSet.isEmpty ())
        return EChange.UNCHANGED;
      m_aSet.clear ();
      m_aList.clear ();
      if (m_bIsCollected)
        _collectWarn ("Removed all items after collection!");
      return EChange.CHANGED;
    });
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsOrderedSet <ICSSPathProvider> getAllItems ()
  {
    return m_aRWLock.readLockedGet ( () -> new CommonsLinkedHashSet <> (m_aList));
  }

  public void getAllItems (@Nonnull final Collection <? super ICSSPathProvider> aTarget)
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
  public int getCount ()
  {
    return m_aRWLock.readLockedInt (m_aList::size);
  }

  @Nonnull
  public Iterator <ICSSPathProvider> iterator ()
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
    final CSSResourceSet rhs = (CSSResourceSet) o;
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
