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

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.base.equals.EqualsHelper;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.tostring.ToStringGenerator;

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
  @NonNull
  public AbstractJSAtomNumeric numericMinus ()
  {
    return new JSAtomInt (-m_nValue);
  }

  @Override
  @NonNull
  public AbstractJSAtomNumeric numericIncr ()
  {
    return new JSAtomInt (m_nValue + 1);
  }

  @Override
  @NonNull
  public AbstractJSAtomNumeric numericDecr ()
  {
    return new JSAtomInt (m_nValue - 1);
  }

  @Override
  @NonNull
  public AbstractJSAtomNumeric numericPlus (@NonNull final AbstractJSAtomNumeric aRhs)
  {
    if (aRhs.isDecimalValue ())
      return new JSAtomDecimal (m_nValue + aRhs.doubleValue ());
    return new JSAtomInt (m_nValue + (long) aRhs.doubleValue ());
  }

  @Override
  @NonNull
  public AbstractJSAtomNumeric numericMinus (@NonNull final AbstractJSAtomNumeric aRhs)
  {
    if (aRhs.isDecimalValue ())
      return new JSAtomDecimal (m_nValue - aRhs.doubleValue ());
    return new JSAtomInt (m_nValue - (long) aRhs.doubleValue ());
  }

  @Override
  @NonNull
  public AbstractJSAtomNumeric numericMul (@NonNull final AbstractJSAtomNumeric aRhs)
  {
    if (aRhs.isDecimalValue ())
      return new JSAtomDecimal (m_nValue * aRhs.doubleValue ());
    return new JSAtomInt (m_nValue * (long) aRhs.doubleValue ());
  }

  @Override
  @NonNull
  public AbstractJSAtomNumeric numericDiv (@NonNull final AbstractJSAtomNumeric aRhs)
  {
    // In JS there is by default no integer division: 5/2===2.5
    return new JSAtomDecimal (m_nValue / aRhs.doubleValue ());
  }

  @Override
  @NonNull
  public AbstractJSAtomNumeric numericMod (@NonNull final AbstractJSAtomNumeric aRhs)
  {
    if (aRhs.isDecimalValue ())
      return new JSAtomDecimal (m_nValue % aRhs.doubleValue ());
    return new JSAtomInt (m_nValue % (long) aRhs.doubleValue ());
  }

  public long getContainedValue ()
  {
    return m_nValue;
  }

  public void generate (@NonNull final JSFormatter aFormatter)
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
