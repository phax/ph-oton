/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.core.uistate;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.lang.GenericReflection;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.type.IHasObjectType;
import com.helger.commons.type.ObjectType;

/**
 * A special wrapper that wraps an arbitrary object into an {@link IHasUIState}
 * object.
 *
 * @author Philip Helger
 * @param <T>
 *        Wrapped object data type
 */
public class UIStateWrapper <T extends Serializable> implements IHasUIState
{
  private final ObjectType m_aObjectType;
  private final T m_aObject;

  public UIStateWrapper (@Nonnull final ObjectType aObjectType, @Nonnull final T aObject)
  {
    ValueEnforcer.notNull (aObjectType, "ObjectType");
    ValueEnforcer.notNull (aObject, "Object");

    m_aObjectType = aObjectType;
    m_aObject = aObject;
  }

  @Nonnull
  public ObjectType getObjectType ()
  {
    return m_aObjectType;
  }

  /**
   * @return The wrapped object. Never <code>null</code>.
   */
  @Nonnull
  public T getObject ()
  {
    return m_aObject;
  }

  @Nullable
  public <U> U getCastedObject ()
  {
    // Regular cast
    return GenericReflection.uncheckedCast (m_aObject);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final UIStateWrapper <?> rhs = (UIStateWrapper <?>) o;
    return m_aObjectType.equals (rhs.m_aObjectType) && EqualsHelper.equals (m_aObject, rhs.m_aObject);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aObjectType).append (m_aObject).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("objectType", m_aObjectType).append ("object", m_aObject).getToString ();
  }

  @Nonnull
  public static <T extends Serializable> UIStateWrapper <T> create (@Nonnull final ObjectType aObjectType, @Nonnull final T aObject)
  {
    return new UIStateWrapper <> (aObjectType, aObject);
  }

  @Nonnull
  public static <T extends Serializable & IHasObjectType> UIStateWrapper <T> create (@Nonnull final T aObject)
  {
    return new UIStateWrapper <> (aObject.getObjectType (), aObject);
  }
}
