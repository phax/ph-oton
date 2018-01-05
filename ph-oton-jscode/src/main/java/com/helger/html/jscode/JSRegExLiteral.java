/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * RegEx literal.
 *
 * @author Philip Helger
 */
public class JSRegExLiteral extends AbstractJSExpression
{
  public static final boolean DEFAULT_GLOBAL = false;
  public static final boolean DEFAULT_CASE_INSENSITIVE = false;
  public static final boolean DEFAULT_MULTI_LINE = false;

  private final String m_sRegEx;
  private boolean m_bGlobal = DEFAULT_GLOBAL;
  private boolean m_bCaseInsensitive = DEFAULT_CASE_INSENSITIVE;
  private boolean m_bMultiLine = DEFAULT_MULTI_LINE;

  public JSRegExLiteral (@Nonnull final String sRegEx)
  {
    m_sRegEx = ValueEnforcer.notNull (sRegEx, "RegEx");
  }

  public boolean global ()
  {
    return m_bGlobal;
  }

  @Nonnull
  public JSRegExLiteral global (final boolean bGlobal)
  {
    m_bGlobal = bGlobal;
    return this;
  }

  public boolean caseInsensitive ()
  {
    return m_bCaseInsensitive;
  }

  @Nonnull
  public JSRegExLiteral caseInsensitive (final boolean bCaseInsensitive)
  {
    m_bCaseInsensitive = bCaseInsensitive;
    return this;
  }

  public boolean multiLine ()
  {
    return m_bMultiLine;
  }

  @Nonnull
  public JSRegExLiteral multiLine (final boolean bMultiLine)
  {
    m_bMultiLine = bMultiLine;
    return this;
  }

  /**
   * Set global, case insensitive and multi line at once
   *
   * @param bGlobal
   *        value for global
   * @param bCaseInsensitive
   *        value for case insensitive
   * @param bMultiLine
   *        value for multi line
   * @return this
   */
  @Nonnull
  public JSRegExLiteral gim (final boolean bGlobal, final boolean bCaseInsensitive, final boolean bMultiLine)
  {
    return global (bGlobal).caseInsensitive (bCaseInsensitive).multiLine (bMultiLine);
  }

  public void generate (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.plain ('/').plain (m_sRegEx).plain ('/');
    if (m_bGlobal)
      aFormatter.plain ('g');
    if (m_bCaseInsensitive)
      aFormatter.plain ('i');
    if (m_bMultiLine)
      aFormatter.plain ('m');
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSRegExLiteral rhs = (JSRegExLiteral) o;
    return m_sRegEx.equals (rhs.m_sRegEx) &&
           m_bGlobal == rhs.m_bGlobal &&
           m_bCaseInsensitive == rhs.m_bCaseInsensitive &&
           m_bMultiLine == rhs.m_bMultiLine;
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ())
                            .append (m_sRegEx)
                            .append (m_bGlobal)
                            .append (m_bCaseInsensitive)
                            .append (m_bMultiLine)
                            .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("regex", m_sRegEx)
                            .append ("global", m_bGlobal)
                            .append ("caseInsensitive", m_bCaseInsensitive)
                            .append ("multiLine", m_bMultiLine)
                            .getToString ();
  }
}
