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
import com.helger.html.resource.js.IJSPathProvider;

/**
 * This class keeps track of all the JS files that must be included for a single
 * request, so that the controls are working properly.
 *
 * @author Philip Helger
 */
public final class JSResourceSet implements IWebResourceSet <IJSPathProvider>, ICloneable <JSResourceSet>
{
  private final Set <IJSPathProvider> m_aItems = new LinkedHashSet <IJSPathProvider> ();

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

  @Nonnull
  public EChange addItem (@Nonnull final IJSPathProvider aJSPathProvider)
  {
    ValueEnforcer.notNull (aJSPathProvider, "JSPathProvider");
    return EChange.valueOf (m_aItems.add (aJSPathProvider));
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
    return EChange.valueOf (m_aItems.remove (aJSPathProvider));
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
  public Set <IJSPathProvider> getAllItems ()
  {
    return CollectionHelper.newOrderedSet (m_aItems);
  }

  public void getAllItems (@Nonnull final Collection <? super IJSPathProvider> aTarget)
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
  public Iterator <IJSPathProvider> iterator ()
  {
    return m_aItems.iterator ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public JSResourceSet getClone ()
  {
    return new JSResourceSet (this);
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
