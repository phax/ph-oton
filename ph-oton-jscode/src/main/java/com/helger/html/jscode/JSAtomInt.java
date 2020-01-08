/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
 * An atomic integer number
 *
 * @author Philip Helger
 */
@Immutable
public class JSAtomInt extends AbstractJSAtomNumeric
{
  private final long m_nValue;

  public JSAtomInt (final long nValue)
  {
    m_nValue = nValue;
  }

  @Override
  public boolean isDecimalValue ()
  {
    return false;
  }

  @Override
  public double doubleValue ()
  {
    return m_nValue;
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericMinus ()
  {
    return new JSAtomInt (-m_nValue);
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericIncr ()
  {
    return new JSAtomInt (m_nValue + 1);
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericDecr ()
  {
    return new JSAtomInt (m_nValue - 1);
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericPlus (@Nonnull final AbstractJSAtomNumeric aRhs)
  {
    if (aRhs.isDecimalValue ())
      return new JSAtomDecimal (m_nValue + aRhs.doubleValue ());
    return new JSAtomInt (m_nValue + (long) aRhs.doubleValue ());
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericMinus (@Nonnull final AbstractJSAtomNumeric aRhs)
  {
    if (aRhs.isDecimalValue ())
      return new JSAtomDecimal (m_nValue - aRhs.doubleValue ());
    return new JSAtomInt (m_nValue - (long) aRhs.doubleValue ());
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericMul (@Nonnull final AbstractJSAtomNumeric aRhs)
  {
    if (aRhs.isDecimalValue ())
      return new JSAtomDecimal (m_nValue * aRhs.doubleValue ());
    return new JSAtomInt (m_nValue * (long) aRhs.doubleValue ());
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericDiv (@Nonnull final AbstractJSAtomNumeric aRhs)
  {
    // In JS there is by default no integer division: 5/2===2.5
    return new JSAtomDecimal (m_nValue / aRhs.doubleValue ());
  }

  @Override
  @Nonnull
  public AbstractJSAtomNumeric numericMod (@Nonnull final AbstractJSAtomNumeric aRhs)
  {
    if (aRhs.isDecimalValue ())
      return new JSAtomDecimal (m_nValue % aRhs.doubleValue ());
    return new JSAtomInt (m_nValue % (long) aRhs.doubleValue ());
  }

  public long getContainedValue ()
  {
    return m_nValue;
  }

  public void generate (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.plain (Long.toString (m_nValue));
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSAtomInt rhs = (JSAtomInt) o;
    return EqualsHelper.equals (m_nValue, rhs.m_nValue);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_nValue).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("value", m_nValue).getToString ();
  }
}
