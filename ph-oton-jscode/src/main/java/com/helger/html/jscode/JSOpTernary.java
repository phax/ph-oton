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
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * This class represents a single ternary operator.
 *
 * @author Philip Helger
 */
public class JSOpTernary extends AbstractJSExpression
{
  private final IJSExpression m_aExpr1;
  private final String m_sOp1;
  private final IJSExpression m_aExpr2;
  private final String m_sOp2;
  private final IJSExpression m_aExpr3;

  public JSOpTernary (@Nonnull final IJSExpression aExpr1,
                      @Nonnull @Nonempty final String sOp1,
                      @Nonnull final IJSExpression aExpr2,
                      @Nonnull @Nonempty final String sOp2,
                      @Nonnull final IJSExpression aExpr3)
  {
    m_aExpr1 = ValueEnforcer.notNull (aExpr1, "Expr");
    m_sOp1 = ValueEnforcer.notEmpty (sOp1, "Operator1");
    m_aExpr2 = ValueEnforcer.notNull (aExpr2, "Expr2");
    m_sOp2 = ValueEnforcer.notEmpty (sOp2, "Operator2");
    m_aExpr3 = ValueEnforcer.notNull (aExpr3, "Expr3");
  }

  @Nonnull
  public IJSExpression expr1 ()
  {
    return m_aExpr1;
  }

  @Nonnull
  @Nonempty
  public String operator1 ()
  {
    return m_sOp1;
  }

  @Nonnull
  public IJSExpression expr2 ()
  {
    return m_aExpr2;
  }

  @Nonnull
  @Nonempty
  public String operator2 ()
  {
    return m_sOp2;
  }

  @Nonnull
  public IJSExpression expr3 ()
  {
    return m_aExpr3;
  }

  public void generate (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.plain ('(').generatable (m_aExpr1).plain (m_sOp1).generatable (m_aExpr2).plain (m_sOp2).generatable (m_aExpr3).plain (')');
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSOpTernary rhs = (JSOpTernary) o;
    return m_aExpr1.equals (rhs.m_aExpr1) &&
           m_sOp1.equals (rhs.m_sOp1) &&
           m_aExpr2.equals (rhs.m_aExpr2) &&
           m_sOp2.equals (rhs.m_sOp2) &&
           m_aExpr3.equals (rhs.m_aExpr3);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ())
                            .append (m_aExpr1)
                            .append (m_sOp1)
                            .append (m_aExpr2)
                            .append (m_sOp2)
                            .append (m_aExpr3)
                            .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("expr1", m_aExpr1)
                            .append ("op1", m_sOp1)
                            .append ("expr2", m_aExpr2)
                            .append ("op2", m_sOp2)
                            .append ("expr3", m_aExpr3)
                            .getToString ();
  }
}
