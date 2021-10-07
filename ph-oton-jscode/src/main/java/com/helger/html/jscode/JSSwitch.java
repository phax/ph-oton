/*
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
import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.IJSWriterSettings;

/**
 * Switch statement
 *
 * @author Philip Helger
 */
public class JSSwitch extends AbstractJSStatement
{
  /**
   * Test part of switch statement.
   */
  private final IJSExpression m_aTest;

  /**
   * List of cases.
   */
  private final ICommonsList <JSCase> m_aCases = new CommonsArrayList <> ();

  /**
   * a single default case
   */
  private JSCase m_aDefaultCase;

  /**
   * Construct a switch statement
   *
   * @param aTest
   *        Test expression
   */
  public JSSwitch (@Nonnull final IJSExpression aTest)
  {
    m_aTest = ValueEnforcer.notNull (aTest, "Test");
  }

  @Nonnull
  public IJSExpression test ()
  {
    return m_aTest;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <JSCase> cases ()
  {
    return m_aCases.getClone ();
  }

  @Nonnull
  @CodingStyleguideUnaware
  public JSCase _case (@Nonnull final IJSExpression aLabel)
  {
    final JSCase aCase = new JSCase (aLabel);
    m_aCases.add (aCase);
    return aCase;
  }

  @Nonnull
  @CodingStyleguideUnaware
  public JSCase _case (final int nLabel)
  {
    return _case (JSExpr.lit (nLabel));
  }

  @Nonnull
  @CodingStyleguideUnaware
  public JSCase _case (final long nLabel)
  {
    return _case (JSExpr.lit (nLabel));
  }

  @Nonnull
  @CodingStyleguideUnaware
  public JSCase _case (@Nonnull final String sLabel)
  {
    return _case (JSExpr.lit (sLabel));
  }

  @Nonnull
  @CodingStyleguideUnaware
  public JSCase _default ()
  {
    if (m_aDefaultCase == null)
      m_aDefaultCase = new JSCase (null, true);
    return m_aDefaultCase;
  }

  public void state (@Nonnull final JSFormatter aFormatter)
  {
    if (JSOp.hasOperator (m_aTest))
      aFormatter.plain ("switch ").generatable (m_aTest).plain ('{').nl ();
    else
      aFormatter.plain ("switch (").generatable (m_aTest).plain ("){").nl ();
    for (final JSCase aCase : m_aCases)
      aFormatter.stmt (aCase);
    if (m_aDefaultCase != null)
      aFormatter.stmt (m_aDefaultCase);
    aFormatter.plain ('}').nl ();
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
    final JSSwitch rhs = (JSSwitch) o;
    return m_aTest.equals (rhs.m_aTest) && m_aCases.equals (rhs.m_aCases) && EqualsHelper.equals (m_aDefaultCase, rhs.m_aDefaultCase);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aTest).append (m_aCases).append (m_aDefaultCase).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("test", m_aTest)
                                       .append ("cases", m_aCases)
                                       .append ("defaultCase", m_aDefaultCase)
                                       .getToString ();
  }
}
