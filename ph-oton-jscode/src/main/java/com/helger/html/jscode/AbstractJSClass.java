/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.Nonempty;

/**
 * Represents an abstract JS class.
 *
 * @author Philip Helger
 */
public abstract class AbstractJSClass extends AbstractJSType
{
  protected AbstractJSClass ()
  {}

  /**
   * Gets the super class of this class.
   *
   * @return Returns the JClass representing the superclass of the entity (class
   *         or interface) represented by this {@link AbstractJSClass}. Even if
   *         no super class is given explicitly or this {@link AbstractJSClass}
   *         is not a class, this method still returns {@link AbstractJSClass}
   *         for {@link Object}. If this JClass represents {@link Object},
   *         return null.
   */
  @Nullable
  @CodingStyleguideUnaware
  public abstract AbstractJSClass _extends ();

  /**
   * Checks the relationship between two classes.
   * <p>
   * This method works in the same way as {@link Class#isAssignableFrom(Class)}
   * works. For example
   * <code>baseClass.isAssignableFrom(derivedClass)==true</code>.
   *
   * @param aDerived
   *        class to check
   * @return <code>true</code> if this class is assignable from the passed class
   */
  public final boolean isAssignableFrom (@Nonnull final AbstractJSClass aDerived)
  {
    ValueEnforcer.notNull (aDerived, "Derived");

    // to avoid the confusion, always use "this" explicitly in this method.
    if (this == aDerived)
      return true;

    final AbstractJSClass b = aDerived._extends ();
    if (b != null && isAssignableFrom (b))
      return true;

    return false;
  }

  /**
   * Generates a static method invocation.
   *
   * @param aMethod
   *        Method to invoke
   * @return The created {@link JSInvocation}
   */
  @Nonnull
  public final JSInvocation staticInvoke (@Nonnull final JSMethod aMethod)
  {
    return new JSInvocation (this, aMethod);
  }

  /**
   * Generates a static method invocation.
   *
   * @param sMethod
   *        Method to invoke
   * @return The created {@link JSInvocation}
   */
  @Nonnull
  public final JSInvocation staticInvoke (@Nonnull @Nonempty final String sMethod)
  {
    return new JSInvocation (this, sMethod);
  }

  /**
   * Static field reference.
   *
   * @param sField
   *        Field to reference
   * @return The created {@link JSFieldRef}
   */
  @Nonnull
  public final JSFieldRef staticRef (@Nonnull @Nonempty final String sField)
  {
    return new JSFieldRef (this, sField);
  }

  /**
   * Static field reference.
   *
   * @param aField
   *        Field to reference
   * @return The created {@link JSFieldRef}
   */
  @Nonnull
  public final JSFieldRef staticRef (@Nonnull final JSVar aField)
  {
    return new JSFieldRef (this, aField);
  }

  public void generate (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.type (this);
  }

  /**
   * Prints the class name in JSDoc @link format.
   */
  void printLink (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.plain ("{@link ").generatable (this).plain ('}');
  }
}
