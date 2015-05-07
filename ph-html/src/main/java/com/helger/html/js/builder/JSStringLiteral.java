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

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.marshal.JSMarshaller;

/**
 * String literal.
 *
 * @author Philip Helger
 */
public class JSStringLiteral extends AbstractJSExpression
{
  private final String m_sStr;

  public JSStringLiteral (@Nonnull final String sStr)
  {
    // May be empty
    m_sStr = ValueEnforcer.notNull (sStr, "String");
  }

  /**
   * @return The contained string.
   */
  @Nonnull
  public String getContainedString ()
  {
    return m_sStr;
  }

  @Nonnull
  @Nonempty
  public static String getAsString (@Nonnull final String sStr)
  {
    return '\'' + JSMarshaller.javaScriptEscape (sStr) + '\'';
  }

  public void generate (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.plain (getAsString (m_sStr));
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSStringLiteral rhs = (JSStringLiteral) o;
    return m_sStr.equals (rhs.m_sStr);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_sStr).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("string", m_sStr).toString ();
  }
}
