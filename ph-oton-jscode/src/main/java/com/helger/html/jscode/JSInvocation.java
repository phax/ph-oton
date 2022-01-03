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

import com.helger.commons.annotation.Nonempty;

/**
 * Object invocation
 *
 * @author Philip Helger
 */
public class JSInvocation extends AbstractJSInvocation <JSInvocation>
{
  /**
   * Invoke a function
   *
   * @param aFunction
   *        Function to invoke
   */
  public JSInvocation (@Nonnull final JSFunction aFunction)
  {
    super (aFunction);
  }

  /**
   * Invoke a function
   *
   * @param sFunctionName
   *        Function to invoke
   */
  public JSInvocation (@Nonnull final String sFunctionName)
  {
    super (sFunctionName);
  }

  /**
   * Invoke an anonymous function
   *
   * @param aAnonymousFunction
   *        The function to be invoked
   */
  public JSInvocation (@Nonnull final JSAnonymousFunction aAnonymousFunction)
  {
    super (aAnonymousFunction);
  }

  /**
   * Invokes a method on an object.
   *
   * @param aObject
   *        JExpression for the object upon which the named method will be
   *        invoked, or null if none
   * @param sName
   *        Name of method to invoke
   */
  public JSInvocation (@Nullable final IJSExpression aObject, @Nonnull @Nonempty final String sName)
  {
    super (aObject, sName);
  }

  public JSInvocation (@Nullable final IJSExpression aObject, @Nonnull final JSMethod aMethod)
  {
    super (aObject, aMethod);
  }

  /**
   * Invokes a static method on a class.
   *
   * @param aType
   *        Type to use
   * @param sName
   *        Function name to invoke
   */
  public JSInvocation (@Nullable final AbstractJSClass aType, @Nonnull @Nonempty final String sName)
  {
    super (aType, sName);
  }

  public JSInvocation (@Nullable final AbstractJSClass aType, @Nonnull final JSMethod aMethod)
  {
    super (aType, aMethod);
  }

  /**
   * Invokes a constructor of an object (i.e., creates a new object.)
   *
   * @param aType
   *        Type of the object to be created. May not be <code>null</code>.
   */
  public JSInvocation (@Nonnull final IJSGeneratable aType)
  {
    super (aType);
  }
}
