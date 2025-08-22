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

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.tostring.ToStringGenerator;

import jakarta.annotation.Nonnull;

/**
 * This class represents a single unary operator
 *
 * @author Philip Helger
 */
@Immutable
public class JSOpUnary extends AbstractJSExpression
{
  private final String m_sOp;
  private final IJSExpression m_aExpr;
  private final boolean m_bOpFirst;

  public JSOpUnary (@Nonnull @Nonempty final String sOp, @Nonnull final IJSExpression aExpr)
  {
    this (sOp, aExpr, true);
  }

  public JSOpUnary (@Nonnull final IJSExpression aExpr, @Nonnull @Nonempty final String sOp)
  {
    this (sOp, aExpr, false);
  }

  private JSOpUnary (@Nonnull final String sOp, @Nonnull final IJSExpression aExpr, final boolean bOpFirst)
  {
    m_sOp = ValueEnforcer.notEmpty (sOp, "Operator");
    m_aExpr = ValueEnforcer.notNull (aExpr, "Expr");
    m_bOpFirst = bOpFirst;
  }

  @Nonnull
  @Nonempty
  public String operator ()
  {
    return m_sOp;
  }

  @Nonnull
  public IJSExpression expr ()
  {
    return m_aExpr;
  }

  public boolean isOperatorFirst ()
  {
    return m_bOpFirst;
  }

  public void generate (@Nonnull final JSFormatter aFormatter)
  {
    if (m_bOpFirst)
      aFormatter.plain (m_sOp).generatable (m_aExpr);
    else
      aFormatter.generatable (m_aExpr).plain (m_sOp);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSOpUnary rhs = (JSOpUnary) o;
    return m_sOp.equals (rhs.m_sOp) && m_aExpr.equals (rhs.m_aExpr) && m_bOpFirst == rhs.m_bOpFirst;
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_sOp).append (m_aExpr).append (m_bOpFirst).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("op", m_sOp)
                            .append ("expr", m_aExpr)
                            .append ("opFirst", m_bOpFirst)
                            .getToString ();
  }
}
