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
package com.helger.photon.core.app.resource;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.resource.js.IJSPathProvider;

/**
 * This class keeps track of all the JS files that must be included for a single
 * request, so that the controls are working properly.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class JSResourceSet implements IWebResourceSet <IJSPathProvider>
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (JSResourceSet.class);

  private final ReadWriteLock m_aRWLock = new ReentrantReadWriteLock ();
  @GuardedBy ("m_aRWLock")
  private final Set <IJSPathProvider> m_aItems = new LinkedHashSet <IJSPathProvider> ();
  @GuardedBy ("m_aRWLock")
  private boolean m_bIsCollected = false;

  public JSResourceSet ()
  {}

  public JSResourceSet (@Nonnull final JSResourceSet aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");
    m_aItems.addAll (aOther.m_aItems);
  }

  public JSResourceSet (@Nonnull final Collection <? extends IJSPathProvider> aOther)
  {
    ValueEnforcer.notEmptyNoNullValue (aOther, "Other");
    m_aItems.addAll (aOther);
  }

  public JSResourceSet (@Nonnull final IJSPathProvider... aOther)
  {
    ValueEnforcer.notEmptyNoNullValue (aOther, "Other");
    for (final IJSPathProvider aItem : aOther)
      m_aItems.add (aItem);
  }

  private static void _collectWarn (@Nonnull final String sMsg)
  {
    s_aLogger.warn (sMsg);
  }

  @Nonnull
  public EChange addItem (@Nonnull final IJSPathProvider aJSPathProvider)
  {
    ValueEnforcer.notNull (aJSPathProvider, "JSPathProvider");

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (!m_aItems.add (aJSPathProvider))
        return EChange.UNCHANGED;
      if (m_bIsCollected)
        _collectWarn ("Adding item " + aJSPathProvider + " after collection!");
      return EChange.CHANGED;
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  @Nonnull
  public EChange addItems (@Nonnull final IWebResourceSet <? extends IJSPathProvider> aItems)
  {
    ValueEnforcer.notNull (aItems, "Items");

    EChange ret = EChange.UNCHANGED;
    for (final IJSPathProvider aItem : aItems)
      ret = ret.or (addItem (aItem));
    return ret;
  }

  @Nonnull
  public EChange removeItem (@Nonnull final IJSPathProvider aJSPathProvider)
  {
    ValueEnforcer.notNull (aJSPathProvider, "JSPathProvider");

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (!m_aItems.remove (aJSPathProvider))
        return EChange.UNCHANGED;
      if (m_bIsCollected)
        _collectWarn ("Removed item " + aJSPathProvider + " after collection!");
      return EChange.CHANGED;
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  @Nonnull
  public EChange removeAll ()
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      if (m_aItems.isEmpty ())
        return EChange.UNCHANGED;
      m_aItems.clear ();
      if (m_bIsCollected)
        _collectWarn ("Removed all items after collection!");
      return EChange.CHANGED;
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <IJSPathProvider> getAllItems ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newOrderedSet (m_aItems);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public void getAllItems (@Nonnull final Collection <? super IJSPathProvider> aTarget)
  {
    ValueEnforcer.notNull (aTarget, "Target");

    m_aRWLock.readLock ().lock ();
    try
    {
      aTarget.addAll (m_aItems);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public boolean isEmpty ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aItems.isEmpty ();
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public boolean isNotEmpty ()
  {
    return !isEmpty ();
  }

  @Nonnegative
  public int getCount ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aItems.size ();
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  public Iterator <IJSPathProvider> iterator ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aItems.iterator ();
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public void markAsCollected ()
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      if (m_bIsCollected)
        _collectWarn ("Resource set was already collected before!");
      m_bIsCollected = true;
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final JSResourceSet rhs = (JSResourceSet) o;
    return m_aItems.equals (rhs.m_aItems);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aItems).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("items", m_aItems).toString ();
  }
}
