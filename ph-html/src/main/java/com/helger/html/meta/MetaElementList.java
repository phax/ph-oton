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
package com.helger.html.meta;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ICloneable;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;

/**
 * This class manages a list of meta elements.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class MetaElementList implements ICloneable <MetaElementList>, Serializable
{
  private final Map <String, IMetaElement> m_aMetaElements = new LinkedHashMap <String, IMetaElement> ();

  public MetaElementList ()
  {}

  public MetaElementList (@Nonnull final MetaElementList aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");
    m_aMetaElements.putAll (aOther.m_aMetaElements);
  }

  @Nonnull
  public MetaElementList addMetaElement (@Nonnull final IMetaElement aMetaElement)
  {
    ValueEnforcer.notNull (aMetaElement, "MetaElement");
    m_aMetaElements.put (aMetaElement.getName (), aMetaElement);
    return this;
  }

  @Nonnull
  public MetaElementList addMetaElements (@Nonnull final MetaElementList aMetaElementList)
  {
    ValueEnforcer.notNull (aMetaElementList, "MetaElementList");
    m_aMetaElements.putAll (aMetaElementList.m_aMetaElements);
    return this;
  }

  @Nonnull
  public EChange removeMetaElement (@Nullable final String sMetaElementName)
  {
    return EChange.valueOf (m_aMetaElements.remove (sMetaElementName) != null);
  }

  @Nonnull
  public EChange removeAllMetaElements ()
  {
    if (m_aMetaElements.isEmpty ())
      return EChange.UNCHANGED;
    m_aMetaElements.clear ();
    return EChange.CHANGED;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <String> getAllMetaElementNames ()
  {
    return CollectionHelper.newOrderedSet (m_aMetaElements.keySet ());
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <IMetaElement> getAllMetaElements ()
  {
    return CollectionHelper.newList (m_aMetaElements.values ());
  }

  @Nullable
  public IMetaElement getMetaElementOfName (@Nullable final String sName)
  {
    return m_aMetaElements.get (sName);
  }

  public boolean containsMetaElementWithName (@Nullable final String sName)
  {
    return m_aMetaElements.containsKey (sName);
  }

  @Nonnegative
  public int getMetaElementCount ()
  {
    return m_aMetaElements.size ();
  }

  public boolean hasMetaElements ()
  {
    return !m_aMetaElements.isEmpty ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public MetaElementList getClone ()
  {
    return new MetaElementList (this);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("metaElements", m_aMetaElements).toString ();
  }
}
