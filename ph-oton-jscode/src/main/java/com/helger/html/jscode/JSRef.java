/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import com.helger.annotation.Nonempty;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.equals.EqualsHelper;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.js.JSMarshaller;

/**
 * Global Reference
 *
 * @author Philip Helger
 */
public class JSRef extends AbstractJSAssignmentTarget
{
  /**
   * Name of the field to be accessed. Either this or {@link #m_aVar} is set.
   */
  private String m_sName;

  /**
   * Variable to be accessed.
   */
  private AbstractJSVariable <?> m_aVar;

  public JSRef (@NonNull @Nonempty final String sName)
  {
    if (!JSMarshaller.isJSIdentifier (sName))
      throw new IllegalArgumentException ("The name '" + sName + "' is not a legal JS identifier!");
    m_sName = sName;
  }

  public JSRef (@NonNull final AbstractJSVariable <?> aVar)
  {
    m_aVar = ValueEnforcer.notNull (aVar, "Var");
  }

  @NonNull
  @Nonempty
  public final String name ()
  {
    return m_sName != null ? m_sName : m_aVar.name ();
  }

  public boolean isFixedName ()
  {
    return m_sName != null;
  }

  @Nullable
  public String fixedName ()
  {
    return m_sName;
  }

  public boolean isFixedVar ()
  {
    return m_aVar != null;
  }

  @Nullable
  public AbstractJSVariable <?> fixedVar ()
  {
    return m_aVar;
  }

  public void generate (@NonNull final JSFormatter aFormatter)
  {
    aFormatter.plain (name ());
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSRef rhs = (JSRef) o;
    return EqualsHelper.equals (m_sName, rhs.m_sName) && EqualsHelper.equals (m_aVar, rhs.m_aVar);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_sName).append (m_aVar).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("Name", m_sName)
                            .appendIfNotNull ("Variable", m_aVar)
                            .getToString ();
  }
}
