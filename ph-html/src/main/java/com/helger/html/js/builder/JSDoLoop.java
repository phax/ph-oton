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

import com.helger.commons.ValueEnforcer;
import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.writer.IJSWriterSettings;

/**
 * Do loops
 *
 * @author Philip Helger
 */
public class JSDoLoop extends AbstractJSStatement
{
  /**
   * Test part of Do statement for determining exit state
   */
  private final IJSExpression m_aTest;

  /**
   * JBlock of statements which makes up body of this Do statement
   */
  private JSBlock m_aBody;

  /**
   * Constructor
   *
   * @param aTest
   *        Test expression
   */
  public JSDoLoop (@Nonnull final IJSExpression aTest)
  {
    m_aTest = ValueEnforcer.notNull (aTest, "Test");
  }

  @Nonnull
  public IJSExpression test ()
  {
    return m_aTest;
  }

  @Nonnull
  public JSBlock body ()
  {
    if (m_aBody == null)
      m_aBody = new JSBlock ();
    return m_aBody;
  }

  public void state (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.plain ("do");
    if (m_aBody != null)
      aFormatter.generatable (m_aBody);
    else
      aFormatter.plain ("{}");

    if (JSOp.hasOperator (m_aTest))
      aFormatter.plain ("while").generatable (m_aTest);
    else
      aFormatter.plain ("while(").generatable (m_aTest).plain (')');
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
    final JSDoLoop rhs = (JSDoLoop) o;
    return m_aTest.equals (rhs.m_aTest) && EqualsUtils.equals (m_aBody, rhs.m_aBody);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aTest).append (m_aBody).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("test", m_aTest).append ("body", m_aBody).toString ();
  }
}
