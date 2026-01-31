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

/**
 * Base class for numeric values
 *
 * @author Philip Helger
 */
public abstract class AbstractJSAtomNumeric extends AbstractJSExpression
{
  /**
   * @return <code>true</code> if this is a decimal values, <code>false</code>
   *         if it is an integer value
   */
  public abstract boolean isDecimalValue ();

  /**
   * @return The double representation of the value
   */
  public abstract double doubleValue ();

  @NonNull
  public abstract AbstractJSAtomNumeric numericMinus ();

  @NonNull
  public abstract AbstractJSAtomNumeric numericIncr ();

  @NonNull
  public abstract AbstractJSAtomNumeric numericDecr ();

  @NonNull
  public abstract AbstractJSAtomNumeric numericPlus (@NonNull AbstractJSAtomNumeric aExpr);

  @NonNull
  public abstract AbstractJSAtomNumeric numericMinus (@NonNull AbstractJSAtomNumeric aExpr);

  @NonNull
  public abstract AbstractJSAtomNumeric numericMul (@NonNull AbstractJSAtomNumeric aExpr);

  @NonNull
  public abstract AbstractJSAtomNumeric numericDiv (@NonNull AbstractJSAtomNumeric aExpr);

  @NonNull
  public abstract AbstractJSAtomNumeric numericMod (@NonNull AbstractJSAtomNumeric aExpr);
}
