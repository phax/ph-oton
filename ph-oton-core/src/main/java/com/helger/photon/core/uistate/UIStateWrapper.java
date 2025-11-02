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
package com.helger.photon.core.uistate;

import java.io.Serializable;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.equals.EqualsHelper;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.reflection.GenericReflection;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.base.type.IHasObjectType;
import com.helger.base.type.ObjectType;

/**
 * A special wrapper that wraps an arbitrary object into an {@link IHasUIState} object.
 *
 * @author Philip Helger
 * @param <T>
 *        Wrapped object data type
 */
public class UIStateWrapper <T extends Serializable> implements IHasUIState
{
  private final ObjectType m_aObjectType;
  private final T m_aObject;

  public UIStateWrapper (@NonNull final ObjectType aObjectType, @NonNull final T aObject)
  {
    ValueEnforcer.notNull (aObjectType, "ObjectType");
    ValueEnforcer.notNull (aObject, "Object");

    m_aObjectType = aObjectType;
    m_aObject = aObject;
  }

  @NonNull
  public ObjectType getObjectType ()
  {
    return m_aObjectType;
  }

  /**
   * @return The wrapped object. Never <code>null</code>.
   */
  @NonNull
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
    return new ToStringGenerator (this).append ("objectType", m_aObjectType)
                                       .append ("object", m_aObject)
                                       .getToString ();
  }

  @NonNull
  public static <T extends Serializable> UIStateWrapper <T> create (@NonNull final ObjectType aObjectType,
                                                                    @NonNull final T aObject)
  {
    return new UIStateWrapper <> (aObjectType, aObject);
  }

  @NonNull
  public static <T extends Serializable & IHasObjectType> UIStateWrapper <T> create (@NonNull final T aObject)
  {
    return new UIStateWrapper <> (aObject.getObjectType (), aObject);
  }
}
