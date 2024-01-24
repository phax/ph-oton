/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * Simple code components that merely generate themselves.
 *
 * @author Philip Helger
 */
@Immutable
public class JSAtom extends AbstractJSExpression
{
  private final String m_sAtom;

  public JSAtom (@Nonnull @Nonempty final String sAtom)
  {
    m_sAtom = ValueEnforcer.notEmpty (sAtom, "Atom");
  }

  @Nonnull
  @Nonempty
  public String getContainedAtom ()
  {
    return m_sAtom;
  }

  public void generate (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.plain (m_sAtom);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSAtom rhs = (JSAtom) o;
    return m_sAtom.equals (rhs.m_sAtom);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_sAtom).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("atom", m_sAtom).getToString ();
  }
}
