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
package com.helger.html.jscode.type;

import javax.annotation.concurrent.Immutable;

/**
 * Contains all the JS primitive types. Originally they were at
 * <code>JSPrimitiveType</code> but because subclasses should not be
 * instantiated in parent classes this was moved here.
 *
 * @author Philip Helger
 * @since 8.2.6
 */
@Immutable
public final class JSPrimitiveTypes
{
  public static final JSTypeArray ARRAY = new JSTypeArray ();
  public static final JSTypeBoolean BOOLEAN = new JSTypeBoolean ();
  public static final JSTypeDate DATE = new JSTypeDate ();
  public static final JSTypeError ERROR = new JSTypeError ();
  public static final JSTypeEvalError EVAL_ERROR = new JSTypeEvalError ();
  public static final JSTypeFunction FUNCTION = new JSTypeFunction ();
  public static final JSTypeJSON JSON = new JSTypeJSON ();
  public static final JSTypeMath MATH = new JSTypeMath ();
  public static final JSTypeNumber NUMBER = new JSTypeNumber ();
  public static final JSTypeObject OBJECT = new JSTypeObject ();
  public static final JSTypeRangeError RANGE_ERROR = new JSTypeRangeError ();
  public static final JSTypeRegExp REGEXP = new JSTypeRegExp ();
  public static final JSTypeString STRING = new JSTypeString ();
  public static final JSTypeTypeError TYPE_ERROR = new JSTypeTypeError ();
  public static final JSTypeURIError URI_ERROR = new JSTypeURIError ();

  private JSPrimitiveTypes ()
  {}
}
