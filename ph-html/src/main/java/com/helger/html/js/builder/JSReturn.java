/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.html.js.builder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.writer.IJSWriterSettings;

/**
 * A return statement
 *
 * @author Philip Helger
 */
public class JSReturn extends AbstractJSStatement
{
  /**
   * Expression to return; may be null.
   */
  private final IJSExpression m_aExpr;

  /**
   * Return constructor without an expression
   */
  public JSReturn ()
  {
    this (null);
  }

  /**
   * Return constructor
   *
   * @param aExpr
   *        JExpression which evaluates to return value
   */
  public JSReturn (@Nullable final IJSExpression aExpr)
  {
    m_aExpr = aExpr;
  }

  @Nullable
  public IJSExpression expr ()
  {
    return m_aExpr;
  }

  public void state (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.plain ("return");
    if (m_aExpr != null)
      aFormatter.plain (' ').generatable (m_aExpr);
    aFormatter.plain (';').nl ();
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
    final JSReturn rhs = (JSReturn) o;
    return EqualsUtils.equals (m_aExpr, rhs.m_aExpr);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aExpr).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("expr", m_aExpr).toString ();
  }
}
