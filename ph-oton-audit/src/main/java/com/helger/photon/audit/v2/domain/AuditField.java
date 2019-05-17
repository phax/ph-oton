/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.audit.v2.domain;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;

/**
 * A single field of an {@link AuditEvent}. Has a mandatory name and an optional
 * value.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class AuditField implements Serializable
{
  private final String m_sName;
  private final String m_sValue;

  /**
   * Constructor
   *
   * @param sName
   *        The field name. May be <code>null</code>.
   * @param sValue
   *        The field value. May be <code>null</code>.
   */
  public AuditField (@Nullable final String sName, @Nullable final String sValue)
  {
    m_sName = sName;
    m_sValue = sValue;
  }

  /**
   * @return The name from the constructor. May be <code>null</code>.
   */
  @Nullable
  public String getName ()
  {
    return m_sName;
  }

  /**
   * @return <code>true</code> if a name is present, <code>false</code> if not.
   */
  public boolean hasName ()
  {
    return StringHelper.hasText (m_sName);
  }

  /**
   * @return The value from the constructor. May be <code>null</code>.
   */
  @Nullable
  public String getValue ()
  {
    return m_sValue;
  }

  /**
   * @return <code>true</code> if a value is present, <code>false</code> if not.
   */
  public boolean hasValue ()
  {
    return StringHelper.hasText (m_sValue);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AuditField rhs = (AuditField) o;
    return EqualsHelper.equals (m_sName, rhs.m_sName) && EqualsHelper.equals (m_sValue, rhs.m_sValue);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sName).append (m_sValue).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Name", m_sName).append ("Value", m_sValue).getToString ();
  }

  /**
   * Factory method to create an {@link AuditField} with a value that indicates,
   * that the real value is not to be persisted or displayed.
   *
   * @param sFieldName
   *        Field name. May be <code>null</code>.
   * @return A new {@link AuditField} and never <code>null</code>.
   */
  @Nonnull
  public static AuditField createWithHiddenValue (@Nullable final String sFieldName)
  {
    return new AuditField (sFieldName, ToStringGenerator.CONSTANT_PASSWORD);
  }
}
