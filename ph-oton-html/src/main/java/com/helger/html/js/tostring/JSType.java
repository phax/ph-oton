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
package com.helger.html.js.tostring;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.MustImplementEqualsAndHashcode;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.IHasJSCode;

/**
 * Wrapper around JavaScript types.
 *
 * @author Philip Helger
 */
@Immutable
@MustImplementEqualsAndHashcode
public class JSType implements Serializable
{
  /**
   * Special JS type for type auto detection - in this case {@link #getType()}
   * returns <code>null</code>!!
   */
  public static final JSType AUTO_DETECT = new JSType ();

  /**
   * Constant basic type for HTML. In contrast to {@link #STRING} values, HTML
   * values are neither quoted not escaped!
   */
  public static final JSType HTML = new JSType (EJSType.HTML);

  /**
   * Constant basic type for String. Values will be quoted and escaped!
   */
  public static final JSType STRING = new JSType (EJSType.STRING);

  /**
   * Constant basic type for Integer.
   */
  public static final JSType INT = new JSType (EJSType.INT);

  /**
   * Constant basic type for Double.
   */
  public static final JSType DOUBLE = new JSType (EJSType.DOUBLE);

  /**
   * Constant basic type for Double.
   */
  public static final JSType BOOLEAN = new JSType (EJSType.BOOLEAN);

  /**
   * Constant basic type for Void.
   */
  public static final JSType VOID = new JSType (EJSType.VOID);

  /**
   * Constant basic type for objects already having JS code present.
   *
   * @see IHasJSCode
   */
  public static final JSType JS = new JSType (EJSType.JS);

  /**
   * Constant basic type for objects already having JSON code present.
   *
   * @see IHasJSCode
   */
  public static final JSType JSON = new JSType (EJSType.JSON);

  /**
   * The base type.
   */
  private final EJSType m_eType;

  private JSType ()
  {
    m_eType = null;
  }

  /**
   * The constructor is protected to avoid outside instantiation. Use only the
   * above constants.
   *
   * @param eType
   *        The basic type. May not be <code>null</code>.
   */
  protected JSType (@Nonnull final EJSType eType)
  {
    m_eType = ValueEnforcer.notNull (eType, "Type");
  }

  /**
   * Get the contained base type.
   *
   * @return The base type. Is only <code>null</code> for the
   *         {@link #AUTO_DETECT} type.
   */
  @Nullable
  public final EJSType getType ()
  {
    return m_eType;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final JSType rhs = (JSType) o;
    return EqualsHelper.equals (m_eType, rhs.m_eType);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_eType).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (null).append ("jsType", m_eType).getToString ();
  }
}
