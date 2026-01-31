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

import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.tostring.ToStringGenerator;

/**
 * JS constructor.
 *
 * @author Philip Helger
 */
public class JSConstructor extends JSFunction
{
  private final JSDefinedClass m_aClass;

  /**
   * Constructor constructor
   *
   * @param aClass
   *        Class containing this constructor
   */
  public JSConstructor (@NonNull final JSDefinedClass aClass)
  {
    super (aClass.name ());
    m_aClass = aClass;
  }

  @NonNull
  public JSDefinedClass parentClass ()
  {
    return m_aClass;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSConstructor rhs = (JSConstructor) o;
    return m_aClass.name ().equals (rhs.m_aClass.name ());
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aClass.name ()).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("class", m_aClass.name ()).getToString ();
  }
}
