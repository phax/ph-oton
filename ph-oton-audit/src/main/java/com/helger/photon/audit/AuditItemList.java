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
package com.helger.photon.audit;

import java.util.Comparator;
import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.ReturnsImmutableObject;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * Manages a list of {@link IAuditItem} objects.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class AuditItemList
{
  private final ICommonsList <IAuditItem> m_aItems = new CommonsArrayList <> ();

  public AuditItemList ()
  {}

  void internalKeepOnlyLast ()
  {
    final IAuditItem aLastItem = m_aItems.getLastOrNull ();
    m_aItems.clear ();
    m_aItems.add (aLastItem);
  }

  void internalAddItem (@Nonnull final IAuditItem aItem)
  {
    ValueEnforcer.notNull (aItem, "Item");

    m_aItems.add (aItem);
  }

  @Nonnegative
  public int getItemCount ()
  {
    return m_aItems.size ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IAuditItem> getAllItems ()
  {
    return m_aItems.getClone ();
  }

  @Nonnull
  @ReturnsImmutableObject
  @CodingStyleguideUnaware
  public List <IAuditItem> getLastItems (@Nonnegative final int nMaxItems)
  {
    final int nEndIndex = Math.min (nMaxItems, m_aItems.size ());
    return m_aItems.getSorted (Comparator.comparing (IAuditItem::getDateTime).reversed ()).subList (0, nEndIndex);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AuditItemList rhs = (AuditItemList) o;
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
    return new ToStringGenerator (this).append ("items", m_aItems).getToString ();
  }
}
