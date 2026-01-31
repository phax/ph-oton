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
package com.helger.html.jscode.type;

import org.jspecify.annotations.NonNull;

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

  @NonNull
  public JSInvocation assign ()
  {
    return global ().invoke ("assign");
  }

  @NonNull
  public JSInvocation create ()
  {
    return global ().invoke ("create");
  }

  @NonNull
  public JSInvocation defineProperty ()
  {
    return global ().invoke ("defineProperty");
  }

  @NonNull
  public JSInvocation defineProperties ()
  {
    return global ().invoke ("defineProperties");
  }

  @NonNull
  public JSInvocation freeze ()
  {
    return global ().invoke ("freeze");
  }

  @NonNull
  public JSInvocation getOwnPropertyDescriptor ()
  {
    return global ().invoke ("getOwnPropertyDescriptor");
  }

  @NonNull
  public JSInvocation getOwnPropertyNames ()
  {
    return global ().invoke ("getOwnPropertyNames");
  }

  @NonNull
  public JSInvocation getOwnPropertySymbols ()
  {
    return global ().invoke ("getOwnPropertySymbols");
  }

  @NonNull
  public JSInvocation getPrototypeOf ()
  {
    return global ().invoke ("getPrototypeOf");
  }

  @NonNull
  public JSInvocation is ()
  {
    return global ().invoke ("is");
  }

  @NonNull
  public JSInvocation isExtensible ()
  {
    return global ().invoke ("isExtensible");
  }

  @NonNull
  public JSInvocation isFrozen ()
  {
    return global ().invoke ("isFrozen");
  }

  @NonNull
  public JSInvocation isSealed ()
  {
    return global ().invoke ("isSealed");
  }

  @NonNull
  public JSInvocation keys ()
  {
    return global ().invoke ("keys");
  }

  @NonNull
  public JSInvocation observe ()
  {
    return global ().invoke ("observe");
  }

  @NonNull
  public JSInvocation preventExtensions ()
  {
    return global ().invoke ("preventExtensions");
  }

  @NonNull
  public JSInvocation seal ()
  {
    return global ().invoke ("seal");
  }

  @NonNull
  public JSInvocation setPrototypeOf ()
  {
    return global ().invoke ("setPrototypeOf");
  }
}
