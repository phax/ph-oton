/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
 * array component reference.
 *
 * @author Philip Helger
 */
public class JSArrayCompRef extends AbstractJSAssignmentTarget
{
  /**
   * JArray expression upon which this component will be accessed.
   */
  private final IJSExpression m_aArray;

  /**
   * Integer expression representing index of the component
   */
  private final IJSExpression m_aIndex;

  /**
   * JArray component reference constructor given an array expression and index.
   *
   * @param aArray
   *        JExpression for the array upon which the component will be accessed,
   * @param aIndex
   *        JExpression for index of component to access
   */
  JSArrayCompRef (@Nonnull final IJSExpression aArray, @Nonnull final IJSExpression aIndex)
  {
    m_aArray = ValueEnforcer.notNull (aArray, "Array");
    m_aIndex = ValueEnforcer.notNull (aIndex, "Index");
  }

  public void generate (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.generatable (m_aArray).plain ('[').generatable (m_aIndex).plain (']');
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSArrayCompRef rhs = (JSArrayCompRef) o;
    return m_aArray.equals (rhs.m_aArray) && m_aIndex.equals (rhs.m_aIndex);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aArray).append (m_aIndex).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("array", m_aArray).append ("index", m_aIndex).getToString ();
  }
}
