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
import org.jspecify.annotations.Nullable;

import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.js.IJSWriterSettings;

/**
 * Postfix increment (x++) statement
 *
 * @author Philip Helger
 */
public class JSIncrPostfix extends AbstractJSStatement
{
  private final IJSAssignmentTarget m_aExpr;

  /**
   * constructor
   *
   * @param aExpr
   *        expression to be incremented
   */
  public JSIncrPostfix (@NonNull final IJSAssignmentTarget aExpr)
  {
    m_aExpr = ValueEnforcer.notNull (aExpr, "Expr");
  }

  @NonNull
  public IJSAssignmentTarget expr ()
  {
    return m_aExpr;
  }

  public void state (@NonNull final JSFormatter aFormatter)
  {
    aFormatter.generatable (m_aExpr).plain ("++;").nl ();
  }

  @Nullable
  public String getJSCode (@Nullable final IJSWriterSettings aSettings)
  {
    return JSPrinter.getAsString (aSettings, this);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final JSIncrPostfix rhs = (JSIncrPostfix) o;
    return m_aExpr.equals (rhs.m_aExpr);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aExpr).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("expr", m_aExpr).getToString ();
  }
}
