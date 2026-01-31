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
package com.helger.html.js;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonnegative;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.annotation.style.ReturnsMutableObject;
import com.helger.base.clone.ICloneable;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.iface.IHasSize;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.CollectionHelper;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;

/**
 * A JSCode provider that encapsulates a list of {@link IHasJSCode} elements and
 * itself implements {@link IHasJSCode}.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class CollectingJSCodeProvider implements IHasJSCodeWithSettings, IHasSize, ICloneable <CollectingJSCodeProvider>
{
  private final ICommonsList <IHasJSCode> m_aList = new CommonsArrayList <> ();

  public CollectingJSCodeProvider ()
  {}

  public CollectingJSCodeProvider (@Nullable final IHasJSCode... aProviders)
  {
    if (aProviders != null)
      for (final IHasJSCode aProvider : aProviders)
        if (aProvider != null)
          append (aProvider);
  }

  public CollectingJSCodeProvider (@Nullable final Iterable <? extends IHasJSCode> aProviders)
  {
    if (aProviders != null)
      for (final IHasJSCode aProvider : aProviders)
        if (aProvider != null)
          append (aProvider);
  }

  /**
   * @return The underlying modifiable list. Never <code>null</code> but maybe
   *         empty.
   */
  @NonNull
  @ReturnsMutableObject
  public ICommonsList <IHasJSCode> directAll ()
  {
    return m_aList;
  }

  /**
   * @return A copy of the list with all members. Never <code>null</code> but
   *         maybe empty.
   */
  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <IHasJSCode> getAll ()
  {
    return m_aList.getClone ();
  }

  /**
   * Add JS code at the specified index.
   *
   * @param nIndex
   *        The index where the element should be added. Should be &ge; 0.
   * @param aProvider
   *        The JS code provider to be added. May be <code>null</code>.
   * @return this for chaining
   */
  @NonNull
  public CollectingJSCodeProvider addAt (@Nonnegative final int nIndex, @Nullable final IHasJSCode aProvider)
  {
    if (aProvider != null)
      m_aList.add (nIndex, aProvider);
    return this;
  }

  /**
   * Add JS code at the specified index but unwrapping any
   * {@link CollectingJSCodeProvider} instances.
   *
   * @param nIndex
   *        The index where the element should be added. Should be &ge; 0.
   * @param aProvider
   *        The JS code provider to be added. May be <code>null</code>.
   * @return this for chaining
   */
  @NonNull
  public CollectingJSCodeProvider addFlattenedAt (@Nonnegative final int nIndex, @Nullable final IHasJSCode aProvider)
  {
    if (aProvider != null)
      if (aProvider instanceof CollectingJSCodeProvider)
        m_aList.addAll (nIndex, ((CollectingJSCodeProvider) aProvider).m_aList);
      else
        m_aList.add (nIndex, aProvider);
    return this;
  }

  @NonNull
  public CollectingJSCodeProvider append (@Nullable final IHasJSCode aProvider)
  {
    if (aProvider != null)
      m_aList.add (aProvider);
    return this;
  }

  /**
   * This will append all the content from the given provider. If the provider
   * itself is a {@link CollectingJSCodeProvider}, its contents will be
   * appended.
   *
   * @param aProvider
   *        The provider to add. May be <code>null</code>.
   * @return this for chaining
   */
  @NonNull
  public CollectingJSCodeProvider appendFlattened (@Nullable final IHasJSCode aProvider)
  {
    if (aProvider != null)
      if (aProvider instanceof CollectingJSCodeProvider)
        m_aList.addAll (((CollectingJSCodeProvider) aProvider).m_aList);
      else
        m_aList.add (aProvider);
    return this;
  }

  @NonNull
  public CollectingJSCodeProvider prepend (@Nullable final IHasJSCode aProvider)
  {
    return addAt (0, aProvider);
  }

  @NonNull
  public CollectingJSCodeProvider prependFlattened (@Nullable final IHasJSCode aProvider)
  {
    return addFlattenedAt (0, aProvider);
  }

  /**
   * Remove the entry at the specified index.
   *
   * @param nIndex
   *        the index to be removed. Should be &ge; 0.
   * @return this for chaining
   */
  @NonNull
  public CollectingJSCodeProvider removeAt (@Nonnegative final int nIndex)
  {
    m_aList.removeAtIndex (nIndex);
    return this;
  }

  public void reset ()
  {
    m_aList.clear ();
  }

  @Nonnegative
  public int size ()
  {
    return m_aList.size ();
  }

  public boolean isEmpty ()
  {
    return m_aList.isEmpty ();
  }

  @NonNull
  public String getJSCode (@Nullable final IJSWriterSettings aSettings)
  {
    final StringBuilder aSB = new StringBuilder ();
    for (final IHasJSCode aJSCodeProvider : m_aList)
    {
      String sJSCode;
      if (aJSCodeProvider instanceof IHasJSCodeWithSettings)
        sJSCode = ((IHasJSCodeWithSettings) aJSCodeProvider).getJSCode (aSettings);
      else
        sJSCode = aJSCodeProvider.getJSCode ();
      aSB.append (sJSCode);
    }
    return aSB.toString ();
  }

  @NonNull
  public CollectingJSCodeProvider getClone ()
  {
    return new CollectingJSCodeProvider (m_aList);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final CollectingJSCodeProvider rhs = (CollectingJSCodeProvider) o;
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
    return new ToStringGenerator (this).appendIf ("list", m_aList, CollectionHelper::isNotEmpty).getToString ();
  }
}
