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
import javax.annotation.concurrent.Immutable;

import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * An atomic decimal number
 *
 * @author Philip Helger
 */
@Immutable
public class JSAtomDecimal extends AbstractJSAtomNumeric
{
  private final double m_dValue;

  public JSAtomDecimal (final float fValue)
  {
    m_dValue = fValue;
  }

  public JSAtomDecimal (final double dValue)
  {
    m_dValue = dValue;
  }

  @Override
  public boolean isDecimalValue ()
  {
    return true;
  }

  @Override
  public double doubleValue ()
  {
    return m_dValue;
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericMinus ()
  {
    return new JSAtomDecimal (-m_dValue);
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericIncr ()
  {
    return new JSAtomDecimal (m_dValue + 1);
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericDecr ()
  {
    return new JSAtomDecimal (m_dValue - 1);
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericPlus (@Nonnull final AbstractJSAtomNumeric aRhs)
  {
    return new JSAtomDecimal (m_dValue + aRhs.doubleValue ());
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericMinus (@Nonnull final AbstractJSAtomNumeric aRhs)
  {
    return new JSAtomDecimal (m_dValue - aRhs.doubleValue ());
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericMul (@Nonnull final AbstractJSAtomNumeric aRhs)
  {
    return new JSAtomDecimal (m_dValue * aRhs.doubleValue ());
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericDiv (@Nonnull final AbstractJSAtomNumeric aRhs)
  {
    return new JSAtomDecimal (m_dValue / aRhs.doubleValue ());
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericMod (@Nonnull final AbstractJSAtomNumeric aRhs)
  {
    return new JSAtomDecimal (m_dValue % aRhs.doubleValue ());
  }

  public double getContainedValue ()
  {
    return m_dValue;
  }

  public void generate (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.plain (Double.toString (m_dValue));
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSAtomDecimal rhs = (JSAtomDecimal) o;
    return EqualsHelper.equals (m_dValue, rhs.m_dValue);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_dValue).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("value", m_dValue).getToString ();
  }
}
