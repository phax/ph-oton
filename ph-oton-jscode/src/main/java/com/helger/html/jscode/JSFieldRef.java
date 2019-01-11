/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * Field Reference
 *
 * @author Philip Helger
 */
public class JSFieldRef extends JSRef
{
  /**
   * Object expression upon which this field will be accessed, or null for the
   * implicit 'this'.
   */
  private final IJSGeneratable m_aObject;

  public JSFieldRef (@Nonnull final IJSGeneratable aObject, @Nonnull @Nonempty final String sName)
  {
    super (sName);
    m_aObject = ValueEnforcer.notNull (aObject, "Object");
  }

  public JSFieldRef (@Nonnull final IJSGeneratable aObject, @Nonnull final JSVar aVar)
  {
    super (aVar);
    m_aObject = ValueEnforcer.notNull (aObject, "Object");
  }

  @Nonnull
  public IJSGeneratable object ()
  {
    return m_aObject;
  }

  public boolean isInvocable ()
  {
    return m_aObject instanceof AbstractJSClass || m_aObject instanceof IJSExpression;
  }

  @Nonnull
  public JSInvocation invoke ()
  {
    if (m_aObject instanceof AbstractJSClass)
      return new JSInvocation ((AbstractJSClass) m_aObject, name ());
    if (m_aObject instanceof IJSExpression)
      return new JSInvocation ((IJSExpression) m_aObject, name ());
    throw new IllegalStateException ("The present object " + m_aObject + " cannot be invoked (at the moment)");
  }

  @Override
  public void generate (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.generatable (m_aObject).plain ('.').plain (name ());
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSFieldRef rhs = (JSFieldRef) o;
    return m_aObject.equals (rhs.m_aObject);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aObject).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("object", m_aObject).getToString ();
  }
}
