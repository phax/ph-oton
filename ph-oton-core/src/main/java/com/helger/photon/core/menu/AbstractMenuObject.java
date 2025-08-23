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
package com.helger.photon.core.menu;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.ReturnsMutableObject;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.base.trait.IGenericImplTrait;
import com.helger.typeconvert.collection.AttributeContainerAny;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Implementation base class for menu items.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
@NotThreadSafe
public abstract class AbstractMenuObject <IMPLTYPE extends AbstractMenuObject <IMPLTYPE>> implements
                                         IMenuObject,
                                         IGenericImplTrait <IMPLTYPE>
{
  private final String m_sID;
  private IMenuObjectFilter m_aDisplayFilter;
  private final AttributeContainerAny <String> m_aAttrs = new AttributeContainerAny <> ();

  public AbstractMenuObject (@Nonnull @Nonempty final String sID)
  {
    m_sID = ValueEnforcer.notEmpty (sID, "ID");
  }

  @Nonnull
  @Nonempty
  public final String getID ()
  {
    return m_sID;
  }

  @Nonnull
  public final IMPLTYPE setDisplayFilter (@Nullable final IMenuObjectFilter aDisplayFilter)
  {
    m_aDisplayFilter = aDisplayFilter;
    return thisAsT ();
  }

  @Nullable
  public final IMenuObjectFilter getDisplayFilter ()
  {
    return m_aDisplayFilter;
  }

  @Override
  public final boolean matchesDisplayFilter ()
  {
    return m_aDisplayFilter == null || m_aDisplayFilter.test (this);
  }

  @Nonnull
  @ReturnsMutableObject
  public final AttributeContainerAny <String> attrs ()
  {
    return m_aAttrs;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AbstractMenuObject <?> rhs = (AbstractMenuObject <?>) o;
    return m_sID.equals (rhs.m_sID);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_sID).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("ID", m_sID)
                            .appendIfNotNull ("DisplayFilter", m_aDisplayFilter)
                            .append ("Attrs", m_aAttrs)
                            .getToString ();
  }
}
