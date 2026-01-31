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

import com.helger.annotation.style.CodingStyleguideUnaware;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.equals.EqualsHelper;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.js.IHasJSCode;
import com.helger.html.js.IJSWriterSettings;

/**
 * If statement, with optional else clause
 *
 * @author Philip Helger
 */
public class JSConditional extends AbstractJSStatement
{
  /**
   * JExpression to test to determine branching
   */
  private final IJSExpression m_aTest;

  /**
   * JBlock of statements for "then" clause
   */
  private final JSBlock m_aThen = new JSBlock ();

  /**
   * JBlock of statements for optional "else" clause
   */
  private JSBlock m_aElse;

  /**
   * Create an If statement
   *
   * @param aTest
   *        {@link IJSExpression} to be tested to determine branching
   */
  public JSConditional (@NonNull final IJSExpression aTest)
  {
    m_aTest = ValueEnforcer.notNull (aTest, "Test");
  }

  /**
   * Create an If statement
   *
   * @param aTest
   *        {@link IJSExpression} to be tested to determine branching
   * @param aThen
   *        "then" block content. May be <code>null</code>.
   */
  public JSConditional (@NonNull final IJSExpression aTest, @Nullable final IHasJSCode aThen)
  {
    this (aTest);
    if (aThen != null)
      m_aThen.add (aThen);
  }

  /**
   * Create an If statement
   *
   * @param aTest
   *        {@link IJSExpression} to be tested to determine branching
   * @param aThen
   *        "then" block content. May be <code>null</code>.
   * @param aElse
   *        "else" block content. May be <code>null</code>.
   */
  public JSConditional (@NonNull final IJSExpression aTest, @Nullable final IHasJSCode aThen, @Nullable final IHasJSCode aElse)
  {
    this (aTest, aThen);
    if (aElse != null)
      _else ().add (aElse);
  }

  @NonNull
  public IJSExpression test ()
  {
    return m_aTest;
  }

  /**
   * Return the block to be executed by the "then" branch
   *
   * @return Then block
   */
  @NonNull
  @CodingStyleguideUnaware
  public final JSBlock _then ()
  {
    return m_aThen;
  }

  /**
   * Create a block to be executed by "else" branch
   *
   * @return Newly generated else block
   */
  @NonNull
  @CodingStyleguideUnaware
  public final JSBlock _else ()
  {
    if (m_aElse == null)
      m_aElse = new JSBlock ();
    return m_aElse;
  }

  /**
   * Creates <code>... else if(...) ...</code> code.
   *
   * @param aBoolExp
   *        The boolean expression
   * @return The conditional for the next "if"
   */
  @NonNull
  public JSConditional elseif (@NonNull final IJSExpression aBoolExp)
  {
    return _else ()._if (aBoolExp);
  }

  public void state (@NonNull final JSFormatter aFormatter)
  {
    if (m_aTest == JSExpr.TRUE)
    {
      m_aThen.generateBody (aFormatter);
      return;
    }
    if (m_aTest == JSExpr.FALSE && m_aElse != null)
    {
      m_aElse.generateBody (aFormatter);
      return;
    }

    if (JSOp.hasOperator (m_aTest))
      aFormatter.plain ("if").generatable (m_aTest);
    else
      aFormatter.plain ("if(").generatable (m_aTest).plain (')');
    aFormatter.generatable (m_aThen);
    if (m_aElse != null)
      aFormatter.plain ("else").generatable (m_aElse);
    aFormatter.nl ();
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
    final JSConditional rhs = (JSConditional) o;
    return m_aTest.equals (rhs.m_aTest) && m_aThen.equals (rhs.m_aThen) && EqualsHelper.equals (m_aElse, rhs.m_aElse);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aTest).append (m_aThen).append (m_aElse).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("test", m_aTest).append ("then", m_aThen).appendIfNotNull ("else", m_aElse).getToString ();
  }
}
