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
import javax.annotation.Nullable;

import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * A representation of a type in JS.
 *
 * @author Philip Helger
 */
public abstract class AbstractJSType implements IJSGeneratable
{
  /**
   * Gets the name of this type.
   *
   * @return Names like "int", "void", "BigInteger".
   */
  @Nonnull
  @Nonempty
  public abstract String name ();

  /**
   * @return The type to be used in "typeof" expressions. A string literal with
   *         the name in it. For <code>Number</code> this returns
   *         <code>"Number"</code>
   */
  @Nonnull
  public final JSStringLiteral typeName ()
  {
    return JSExpr.lit (name ());
  }

  /**
   * @return A "new type" invocation object
   */
  @Nonnull
  @CodingStyleguideUnaware
  public final JSInvocation _new ()
  {
    return JSExpr._new (this);
  }

  /**
   * @param aExprs
   *        Expressions used as arguments
   * @return A "new type" invocation object
   */
  @Nonnull
  @CodingStyleguideUnaware
  public final JSInvocation _new (@Nullable final IJSExpression... aExprs)
  {
    return _new ().args (aExprs);
  }

  /**
   * Create a cast from the passed expression to this type
   *
   * @param aExpr
   *        The expression to be casted. May not be <code>null</code>.
   * @return The {@link JSCast} object
   */
  @Nonnull
  public JSCast casted (@Nonnull final IJSExpression aExpr)
  {
    return JSExpr.cast (this, aExpr);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    return true;
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).getToString ();
  }
}
