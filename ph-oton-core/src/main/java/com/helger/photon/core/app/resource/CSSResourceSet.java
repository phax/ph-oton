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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.commons.ICloneable;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.resource.css.ICSSPathProvider;

/**
 * This class keeps track of all the CSS files that must be included for a
 * single request, so that the controls are working properly.
 *
 * @author Philip Helger
 */
public final class CSSResourceSet implements IWebResourceSet <ICSSPathProvider>, ICloneable <CSSResourceSet>
{
  private final Set <ICSSPathProvider> m_aItems = new LinkedHashSet <ICSSPathProvider> ();

  public CSSResourceSet ()
  {}

  public CSSResourceSet (@Nonnull final CSSResourceSet aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");
    m_aItems.addAll (aOther.m_aItems);
  }

  public CSSResourceSet (@Nonnull final Collection <? extends ICSSPathProvider> aOther)
  {
    ValueEnforcer.notEmptyNoNullValue (aOther, "Other");
    m_aItems.addAll (aOther);
  }

  public CSSResourceSet (@Nonnull final ICSSPathProvider... aOther)
  {
    ValueEnforcer.notEmptyNoNullValue (aOther, "Other");
    for (final ICSSPathProvider aItem : aOther)
      m_aItems.add (aItem);
  }

  @Nonnull
  public EChange addItem (@Nonnull final ICSSPathProvider aCSSPathProvider)
  {
    ValueEnforcer.notNull (aCSSPathProvider, "CSSPathProvider");
    return EChange.valueOf (m_aItems.add (aCSSPathProvider));
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
  public EChange removeItem (@Nonnull final ICSSPathProvider aCSSPathProvider)
  {
    ValueEnforcer.notNull (aCSSPathProvider, "CSSPathProvider");
    return EChange.valueOf (m_aItems.remove (aCSSPathProvider));
  }

  @Nonnull
  public EChange removeAll ()
  {
    if (m_aItems.isEmpty ())
      return EChange.UNCHANGED;
    m_aItems.clear ();
    return EChange.CHANGED;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <ICSSPathProvider> getAllItems ()
  {
    return CollectionHelper.newOrderedSet (m_aItems);
  }

  public void getAllItems (@Nonnull final Collection <? super ICSSPathProvider> aTarget)
  {
    ValueEnforcer.notNull (aTarget, "Target");
    aTarget.addAll (m_aItems);
  }

  public boolean isEmpty ()
  {
    return m_aItems.isEmpty ();
  }

  public boolean isNotEmpty ()
  {
    return !m_aItems.isEmpty ();
  }

  @Nonnegative
  public int getCount ()
  {
    return m_aItems.size ();
  }

  @Nonnull
  public Iterator <ICSSPathProvider> iterator ()
  {
    return m_aItems.iterator ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public CSSResourceSet getClone ()
  {
    return new CSSResourceSet (this);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final CSSResourceSet rhs = (CSSResourceSet) o;
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
