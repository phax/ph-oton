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

import javax.annotation.Nonnull;

import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.html.jscode.JSInvocation;

/**
 * Contains the JS built-in type 'Date'
 *
 * @author Philip Helger
 */
public class JSTypeDate extends JSPrimitiveType
{
  public JSTypeDate ()
  {
    super ("Date");
  }

  @Nonnull
  public JSInvocation now ()
  {
    return global ().invoke ("now");
  }

  @Nonnull
  public JSInvocation parse ()
  {
    return global ().invoke ("parse");
  }

  @Nonnull
  @CodingStyleguideUnaware
  public JSInvocation UTC ()
  {
    return global ().invoke ("UTC");
  }
}
