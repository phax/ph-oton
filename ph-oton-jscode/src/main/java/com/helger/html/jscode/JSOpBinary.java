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
package com.helger.html.jscode;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

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
  private boolean m_bUseBraces;

  private static boolean _useBraces (@Nonnull final IJSExpression aLeft, @Nonnull final String sOp, @Nonnull final IJSGeneratable aRight)
  {
    if (aLeft instanceof JSOpBinary)
    {
      final JSOpBinary r = (JSOpBinary) aLeft;
      if (sOp.equals (r.m_sOp))
        r.m_bUseBraces = false;
    }
    if (aRight instanceof JSOpBinary)
    {
      final JSOpBinary r = (JSOpBinary) aRight;
      if (sOp.equals (r.m_sOp))
        r.m_bUseBraces = false;
    }
    return true;
  }

  /**
   * Constructor
   *
   * @param aLeft
   *        Left side. May not be <code>null</code>.
   * @param sOp
   *        Operator. May be empty string in very rare cases (e.g. JQuery
   *        selector chaining). May not be <code>null</code>.
   * @param aRight
   *        Right side. May not be <code>null</code>.
   */
  public JSOpBinary (@Nonnull final IJSExpression aLeft, @Nonnull final String sOp, @Nonnull final IJSGeneratable aRight)
  {
    this (aLeft, sOp, aRight, _useBraces (aLeft, sOp, aRight));
  }

  /**
   * Constructor
   *
   * @param aLeft
   *        Left side. May not be <code>null</code>.
   * @param sOp
   *        Operator. May be empty string in very rare cases (e.g. JQuery
   *        selector chaining). May not be <code>null</code>.
   * @param aRight
   *        Right side. May not be <code>null</code>.
   * @param bUseBraces
   *        <code>true</code> to indicate usage of braces, <code>false</code> to
   *        disable it
   */
  public JSOpBinary (@Nonnull final IJSExpression aLeft,
                     @Nonnull final String sOp,
                     @Nonnull final IJSGeneratable aRight,
                     final boolean bUseBraces)
  {
    m_aLeft = ValueEnforcer.notNull (aLeft, "Left");
    m_sOp = ValueEnforcer.notNull (sOp, "Operator");
    m_aRight = ValueEnforcer.notNull (aRight, "Right");
    m_bUseBraces = bUseBraces;
  }

  @Nonnull
  public IJSExpression left ()
  {
    return m_aLeft;
  }

  @Nonnull
  @Nonempty
  public String operator ()
  {
    return m_sOp;
  }

  @Nonnull
  public IJSGeneratable right ()
  {
    return m_aRight;
  }

  public boolean useBraces ()
  {
    return m_bUseBraces;
  }

  public void generate (@Nonnull final JSFormatter aFormatter)
  {
    final boolean bUseBraces = m_bUseBraces;

    if (bUseBraces)
      aFormatter.plain ('(');
    aFormatter.generatable (m_aLeft).plain (m_sOp).generatable (m_aRight);
    if (bUseBraces)
      aFormatter.plain (')');
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
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aLeft).append (m_sOp).append (m_aRight).getHashCode ();
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
