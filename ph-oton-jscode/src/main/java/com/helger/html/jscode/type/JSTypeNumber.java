/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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

import com.helger.html.jscode.JSFieldRef;
import com.helger.html.jscode.JSGlobal;

/**
 * Contains the JS built-in type 'Number'
 *
 * @author Philip Helger
 */
public class JSTypeNumber extends JSPrimitiveType
{
  public JSTypeNumber ()
  {
    super ("Number");
  }

  /**
   * @return Global field <code>Number.MAX_VALUE</code>
   */
  @Nonnull
  public JSFieldRef maxValue ()
  {
    return global ().ref ("MAX_VALUE");
  }

  /**
   * @return Global field <code>Number.MIN_VALUE</code>
   */
  @Nonnull
  public JSFieldRef minValue ()
  {
    return global ().ref ("MIN_VALUE");
  }

  /**
   * @return Global field <code>Number.NaN</code> - same as <Code>NaN</code>
   * @see JSGlobal#NaN
   */
  @Nonnull
  public JSFieldRef nan ()
  {
    return global ().ref ("NaN");
  }

  /**
   * @return Global field <code>Number.NEGATIVE_INFINITY</code>
   */
  @Nonnull
  public JSFieldRef negativeInfinity ()
  {
    return global ().ref ("NEGATIVE_INFINITY");
  }

  /**
   * @return Global field <code>Number.POSITIVE_INFINITY</code>
   */
  @Nonnull
  public JSFieldRef positiveInfinity ()
  {
    return global ().ref ("POSITIVE_INFINITY");
  }
}
