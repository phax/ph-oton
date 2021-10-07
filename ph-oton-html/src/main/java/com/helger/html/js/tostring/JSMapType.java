/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.html.js.tostring;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * Specific JavaScript map type.
 *
 * @author Philip Helger
 */
@Immutable
public class JSMapType extends JSType
{
  /**
   * The type of the key elements
   */
  private final JSType m_aKeyType;

  /**
   * The type of the value elements
   */
  private final JSType m_aValueType;

  public JSMapType (@Nonnull final JSType aKeyType, @Nonnull final JSType aValueType)
  {
    super (EJSType.MAP);
    m_aKeyType = ValueEnforcer.notNull (aKeyType, "KeyType");
    m_aValueType = ValueEnforcer.notNull (aValueType, "ValueType");
  }

  @Nonnull
  public JSType getKeyType ()
  {
    return m_aKeyType;
  }

  @Nonnull
  public JSType getValueType ()
  {
    return m_aValueType;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSMapType rhs = (JSMapType) o;
    return m_aKeyType.equals (rhs.m_aKeyType) && m_aValueType.equals (rhs.m_aValueType);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aKeyType).append (m_aValueType).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("keyType", m_aKeyType)
                            .append ("valueType", m_aValueType)
                            .getToString ();
  }
}
