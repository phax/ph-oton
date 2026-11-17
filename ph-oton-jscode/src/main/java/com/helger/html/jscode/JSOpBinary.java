/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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

import com.helger.annotation.Nonempty;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.tostring.ToStringGenerator;

/**
 * This class represents a single binary operator
 *
 * @author Philip Helger
 */
public class JSOpBinary extends AbstractJSExpression
{
  private final IJSExpression m_aLeft;
  private final String m_sOp;
  private final IJSGeneratable m_aRight;
  private final boolean m_bUseBraces;

  /**
   * Constructor
   *
   * @param aLeft
   *        Left side. May not be <code>null</code>.
   * @param sOp
   *        Operator. May be empty string in very rare cases (e.g. JQuery selector chaining). May
   *        not be <code>null</code>.
   * @param aRight
   *        Right side. May not be <code>null</code>.
   */
  public JSOpBinary (@NonNull final IJSExpression aLeft,
                     @NonNull final String sOp,
                     @NonNull final IJSGeneratable aRight)
  {
    this (aLeft, sOp, aRight, true);
  }

  /**
   * Constructor
   *
   * @param aLeft
   *        Left side. May not be <code>null</code>.
   * @param sOp
   *        Operator. May be empty string in very rare cases (e.g. JQuery selector chaining). May
   *        not be <code>null</code>.
   * @param aRight
   *        Right side. May not be <code>null</code>.
   * @param bUseBraces
   *        <code>true</code> to indicate usage of braces, <code>false</code> to disable it
   */
  public JSOpBinary (@NonNull final IJSExpression aLeft,
                     @NonNull final String sOp,
                     @NonNull final IJSGeneratable aRight,
                     final boolean bUseBraces)
  {
    m_aLeft = ValueEnforcer.notNull (aLeft, "Left");
    m_sOp = ValueEnforcer.notNull (sOp, "Operator");
    m_aRight = ValueEnforcer.notNull (aRight, "Right");
    m_bUseBraces = bUseBraces;
  }

  @NonNull
  public IJSExpression left ()
  {
    return m_aLeft;
  }

  @NonNull
  @Nonempty
  public String operator ()
  {
    return m_sOp;
  }

  @NonNull
  public IJSGeneratable right ()
  {
    return m_aRight;
  }

  public boolean useBraces ()
  {
    return m_bUseBraces;
  }

  private static boolean _needsSpaceBeforeRight (@NonNull final String sOp, @NonNull final IJSGeneratable aRight)
  {
    if (!sOp.equals ("-") && !sOp.equals ("+"))
      return false;

    // Avoid e.g. "x--1" for "x - (-1)"
    if (sOp.equals ("-") && aRight instanceof final AbstractJSAtomNumeric aNumeric && aNumeric.doubleValue () < 0)
      return true;

    // Avoid e.g. "x---y" for "x - (--y)" or "x+++y" for "x + (++y)"
    return aRight instanceof final JSOpUnary aUnary &&
           !(aRight instanceof JSOpUnaryWithParanthesis) &&
           aUnary.isOperatorFirst () &&
           aUnary.operator ().startsWith (sOp);
  }

  private void _generate (@NonNull final JSFormatter aFormatter, final boolean bUseBraces)
  {
    if (bUseBraces)
      aFormatter.plain ('(');
    if (m_aLeft instanceof final JSOpBinary aLeftOp && aLeftOp.m_sOp.equals (m_sOp))
    {
      // Binary operators are left-associative, so the braces of a left operand with the same
      // operator are not needed
      aLeftOp._generate (aFormatter, false);
    }
    else
      aFormatter.generatable (m_aLeft);
    aFormatter.plain (m_sOp);
    if (_needsSpaceBeforeRight (m_sOp, m_aRight))
      aFormatter.plain (' ');
    aFormatter.generatable (m_aRight);
    if (bUseBraces)
      aFormatter.plain (')');
  }

  public void generate (@NonNull final JSFormatter aFormatter)
  {
    _generate (aFormatter, m_bUseBraces);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSOpBinary rhs = (JSOpBinary) o;
    return m_aLeft.equals (rhs.m_aLeft) && m_sOp.equals (rhs.m_sOp) && m_aRight.equals (rhs.m_aRight);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ())
                            .append (m_aLeft)
                            .append (m_sOp)
                            .append (m_aRight)
                            .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("lhs", m_aLeft)
                            .append ("op", m_sOp)
                            .append ("rhs", m_aRight)
                            .getToString ();
  }
}
