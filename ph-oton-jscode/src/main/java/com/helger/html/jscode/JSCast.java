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
package com.helger.html.jscode;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * A cast operation.
 *
 * @author Philip Helger
 */
public class JSCast extends AbstractJSExpression
{
  /**
   * JType to which the expression is to be cast.
   */
  private final AbstractJSType m_aType;

  /**
   * JExpression to be cast.
   */
  private final IJSExpression m_aExpr;

  /**
   * JCast constructor
   *
   * @param aType
   *        JType to which the expression is cast
   * @param aObject
   *        JExpression for the object upon which the cast is applied
   */
  public JSCast (@Nonnull final AbstractJSType aType, @Nonnull final IJSExpression aObject)
  {
    ValueEnforcer.notNull (aType, "Type");
    ValueEnforcer.notNull (aObject, "Object");
    m_aType = aType;
    m_aExpr = aObject;
  }

  @Nonnull
  public AbstractJSType type ()
  {
    return m_aType;
  }

  @Nonnull
  public IJSExpression expr ()
  {
    return m_aExpr;
  }

  public void generate (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.plain ("((").generatable (m_aType).plain (')').generatable (m_aExpr).plain (')');
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSCast rhs = (JSCast) o;
    return m_aType.equals (rhs.m_aType) && m_aExpr.equals (rhs.m_aExpr);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aType).append (m_aExpr).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("type", m_aType).append ("object", m_aExpr).getToString ();
  }
}
