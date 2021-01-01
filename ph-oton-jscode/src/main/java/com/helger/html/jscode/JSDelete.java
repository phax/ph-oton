/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.IJSWriterSettings;

/**
 * delete statement
 *
 * @author Philip Helger
 */
public class JSDelete extends AbstractJSStatement
{
  private final IJSExpression m_aExpr;

  /**
   * constructor
   *
   * @param aExpr
   *        expression to delete. May not be <code>null</code>.
   */
  public JSDelete (@Nonnull final IJSExpression aExpr)
  {
    m_aExpr = ValueEnforcer.notNull (aExpr, "Expr");
  }

  @Nonnull
  public IJSExpression expr ()
  {
    return m_aExpr;
  }

  public void state (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.plain ("delete ").generatable (m_aExpr).plain (';').nl ();
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
    final JSDelete rhs = (JSDelete) o;
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
