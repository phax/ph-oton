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
package com.helger.photon.core.app.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
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
  private static final Logger s_aLogger = LoggerFactory.getLogger (CSSResourceSet.class);

  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  @GuardedBy ("m_aRWLock")
  private final List <ICSSPathProvider> m_aList = new ArrayList <> ();
  @GuardedBy ("m_aRWLock")
  private final Set <ICSSPathProvider> m_aItems = new HashSet <> ();
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
    s_aLogger.warn (sMsg);
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

    return m_aRWLock.writeLocked ( () -> {
      // Check uniqueness
      if (!m_aItems.add (aCSSPathProvider))
        return EChange.UNCHANGED;

      // Honour index
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

    return m_aRWLock.writeLocked ( () -> {
      if (!m_aItems.remove (aCSSPathProvider))
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
    return m_aRWLock.writeLocked ( () -> {
      if (m_aItems.isEmpty ())
        return EChange.UNCHANGED;
      m_aItems.clear ();
      m_aList.clear ();
      if (m_bIsCollected)
        _collectWarn ("Removed all items after collection!");
      return EChange.CHANGED;
    });
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <ICSSPathProvider> getAllItems ()
  {
    return m_aRWLock.readLocked ( () -> CollectionHelper.newOrderedSet (m_aList));
  }

  public void getAllItems (@Nonnull final Collection <? super ICSSPathProvider> aTarget)
  {
    ValueEnforcer.notNull (aTarget, "Target");

    m_aRWLock.readLocked ( () -> {
      aTarget.addAll (m_aList);
    });
  }

  public boolean isEmpty ()
  {
    return m_aRWLock.readLocked ( () -> m_aList.isEmpty ());
  }

  public boolean isNotEmpty ()
  {
    return !isEmpty ();
  }

  @Nonnegative
  public int getCount ()
  {
    return m_aRWLock.readLocked ( () -> m_aList.size ());
  }

  @Nonnull
  public Iterator <ICSSPathProvider> iterator ()
  {
    return m_aRWLock.readLocked (m_aList::iterator);
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
    return new ToStringGenerator (this).append ("list", m_aList).toString ();
  }
}
