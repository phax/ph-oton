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
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
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
  private JSVar m_aVar;

  public JSRef (@Nonnull @Nonempty final String sName)
  {
    if (!JSMarshaller.isJSIdentifier (sName))
      throw new IllegalArgumentException ("The name '" + sName + "' is not a legal JS identifier!");
    m_sName = sName;
  }

  public JSRef (@Nonnull final JSVar aVar)
  {
    m_aVar = ValueEnforcer.notNull (aVar, "Var");
  }

  @Nonnull
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
  public JSVar fixedVar ()
  {
    return m_aVar;
  }

  public void generate (@Nonnull final JSFormatter aFormatter)
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
    return ToStringGenerator.getDerived (super.toString ()).append ("name", m_sName).appendIfNotNull ("var", m_aVar).getToString ();
  }
}
