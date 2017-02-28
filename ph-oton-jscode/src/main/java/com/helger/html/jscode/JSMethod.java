/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.JSMarshaller;

/**
 * JS method.
 *
 * @author Philip Helger
 */
public class JSMethod extends JSFunction
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (JSMethod.class);

  private final JSDefinedClass m_aOwnerClass;

  /**
   * Constructor
   *
   * @param aOwnerClass
   *        Owning class
   * @param sName
   *        Name of this method
   */
  public JSMethod (@Nonnull final JSDefinedClass aOwnerClass, @Nonnull @Nonempty final String sName)
  {
    super (sName);
    ValueEnforcer.notNull (aOwnerClass, "OwnerClass");
    if (!JSMarshaller.isJSIdentifier (sName))
      throw new IllegalArgumentException ("The name '" + sName + "' is not a legal JS identifier!");
    if (sName.equals (aOwnerClass.name ()))
      throw new IllegalArgumentException ("You cannot name a method like the constructor!");
    if (!Character.isLowerCase (sName.charAt (0)))
      s_aLogger.warn ("Method names should always start with a lowercase character: '" + sName + "'");
    m_aOwnerClass = aOwnerClass;
    body ().newlineAtEnd (false);
  }

  @Nonnull
  public JSDefinedClass parentClass ()
  {
    return m_aOwnerClass;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSMethod rhs = (JSMethod) o;
    return m_aOwnerClass.name ().equals (rhs.m_aOwnerClass.name ());
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aOwnerClass.name ()).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("ownerClass", m_aOwnerClass.name ()).getToString ();
  }
}
