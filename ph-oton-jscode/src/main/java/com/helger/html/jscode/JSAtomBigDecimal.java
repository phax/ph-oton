/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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

import java.math.BigDecimal;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * An atomic BigDecimal number
 *
 * @author Philip Helger
 */
@Immutable
public class JSAtomBigDecimal extends AbstractJSAtomNumeric
{
  private final BigDecimal m_aValue;

  public JSAtomBigDecimal (@Nonnull final BigDecimal aValue)
  {
    m_aValue = ValueEnforcer.notNull (aValue, "Value");
  }

  @Override
  public boolean isDecimalValue ()
  {
    return true;
  }

  @Override
  public double doubleValue ()
  {
    return m_aValue.doubleValue ();
  }

  @Override
  @Nonnull
  public JSAtomBigDecimal numericMinus ()
  {
    return new JSAtomBigDecimal (m_aValue.negate ());
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericIncr ()
  {
    return new JSAtomBigDecimal (m_aValue.add (BigDecimal.ONE));
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericDecr ()
  {
    return new JSAtomBigDecimal (m_aValue.subtract (BigDecimal.ONE));
  }

  @Nonnull
  private static BigDecimal _getParam (@Nonnull final AbstractJSAtomNumeric aRhs)
  {
    return aRhs instanceof JSAtomBigDecimal ? ((JSAtomBigDecimal) aRhs).getContainedValue () : BigDecimal.valueOf (aRhs.doubleValue ());
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericPlus (@Nonnull final AbstractJSAtomNumeric aRhs)
  {
    final BigDecimal aParam = _getParam (aRhs);
    return new JSAtomBigDecimal (m_aValue.add (aParam));
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericMinus (@Nonnull final AbstractJSAtomNumeric aRhs)
  {
    final BigDecimal aParam = _getParam (aRhs);
    return new JSAtomBigDecimal (m_aValue.subtract (aParam));
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericMul (@Nonnull final AbstractJSAtomNumeric aRhs)
  {
    final BigDecimal aParam = _getParam (aRhs);
    return new JSAtomBigDecimal (m_aValue.multiply (aParam));
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericDiv (@Nonnull final AbstractJSAtomNumeric aRhs)
  {
    final BigDecimal aParam = _getParam (aRhs);
    return new JSAtomBigDecimal (m_aValue.divide (aParam));
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericMod (@Nonnull final AbstractJSAtomNumeric aRhs)
  {
    final BigDecimal aParam = _getParam (aRhs);
    return new JSAtomBigDecimal (m_aValue.remainder (aParam));
  }

  @Nonnull
  public BigDecimal getContainedValue ()
  {
    return m_aValue;
  }

  public void generate (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.plain (m_aValue.toString ());
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSAtomBigDecimal rhs = (JSAtomBigDecimal) o;
    return EqualsHelper.equals (m_aValue, rhs.m_aValue);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aValue).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("value", m_aValue).getToString ();
  }
}
