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
package com.helger.html.jscode.type;

import javax.annotation.Nonnull;

import com.helger.html.jscode.JSInvocation;

/**
 * Contains the JS built-in type 'Object'
 *
 * @author Philip Helger
 */
public class JSTypeObject extends JSPrimitiveType
{
  public JSTypeObject ()
  {
    super ("Object");
  }

  @Nonnull
  public JSInvocation assign ()
  {
    return global ().invoke ("assign");
  }

  @Nonnull
  public JSInvocation create ()
  {
    return global ().invoke ("create");
  }

  @Nonnull
  public JSInvocation defineProperty ()
  {
    return global ().invoke ("defineProperty");
  }

  @Nonnull
  public JSInvocation defineProperties ()
  {
    return global ().invoke ("defineProperties");
  }

  @Nonnull
  public JSInvocation freeze ()
  {
    return global ().invoke ("freeze");
  }

  @Nonnull
  public JSInvocation getOwnPropertyDescriptor ()
  {
    return global ().invoke ("getOwnPropertyDescriptor");
  }

  @Nonnull
  public JSInvocation getOwnPropertyNames ()
  {
    return global ().invoke ("getOwnPropertyNames");
  }

  @Nonnull
  public JSInvocation getOwnPropertySymbols ()
  {
    return global ().invoke ("getOwnPropertySymbols");
  }

  @Nonnull
  public JSInvocation getPrototypeOf ()
  {
    return global ().invoke ("getPrototypeOf");
  }

  @Nonnull
  public JSInvocation is ()
  {
    return global ().invoke ("is");
  }

  @Nonnull
  public JSInvocation isExtensible ()
  {
    return global ().invoke ("isExtensible");
  }

  @Nonnull
  public JSInvocation isFrozen ()
  {
    return global ().invoke ("isFrozen");
  }

  @Nonnull
  public JSInvocation isSealed ()
  {
    return global ().invoke ("isSealed");
  }

  @Nonnull
  public JSInvocation keys ()
  {
    return global ().invoke ("keys");
  }

  @Nonnull
  public JSInvocation observe ()
  {
    return global ().invoke ("observe");
  }

  @Nonnull
  public JSInvocation preventExtensions ()
  {
    return global ().invoke ("preventExtensions");
  }

  @Nonnull
  public JSInvocation seal ()
  {
    return global ().invoke ("seal");
  }

  @Nonnull
  public JSInvocation setPrototypeOf ()
  {
    return global ().invoke ("setPrototypeOf");
  }
}
