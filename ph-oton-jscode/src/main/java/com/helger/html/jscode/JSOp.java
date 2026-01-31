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
import org.jspecify.annotations.Nullable;

import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.CodingStyleguideUnaware;
import com.helger.annotation.style.PresentForCodeCoverage;

/**
 * Class for generating expressions containing operators
 *
 * @author Philip Helger
 */
@Immutable
public final class JSOp
{
  @PresentForCodeCoverage
  private static final JSOp INSTANCE = new JSOp ();

  private JSOp ()
  {}

  /**
   * Determine whether the top level of an expression involves an operator.
   *
   * @param aExpr
   *        Expression to check
   * @return <code>true</code> if it involves an operator
   */
  public static boolean hasOperator (@Nullable final IJSExpression aExpr)
  {
    return aExpr instanceof JSOpUnary || aExpr instanceof JSOpBinary || aExpr instanceof JSOpTernary;
  }

  /* -- Unary operators -- */

  @NonNull
  public static AbstractJSExpression minus (@NonNull final IJSExpression aExpr)
  {
    // Some optimizations
    if (aExpr instanceof AbstractJSAtomNumeric)
      return ((AbstractJSAtomNumeric) aExpr).numericMinus ();

    return new JSOpUnaryWithParanthesis ("-", aExpr);
  }

  @NonNull
  public static JSExprParanthesis inParantheses (@NonNull final IJSExpression aExpr)
  {
    return new JSExprParanthesis (aExpr);
  }

  /**
   * Logical not <code>'!x'</code>.
   *
   * @param aExpr
   *        Expression to negate
   * @return ![this]
   */
  @NonNull
  public static AbstractJSExpression not (@NonNull final IJSExpression aExpr)
  {
    // Some optimizations
    if (aExpr == JSExpr.TRUE)
      return JSExpr.FALSE;
    if (aExpr == JSExpr.FALSE)
      return JSExpr.TRUE;

    return new JSOpUnaryWithParanthesis ("!", aExpr);
  }

  @NonNull
  public static JSOpUnaryWithParanthesis complement (@NonNull final IJSExpression aExpr)
  {
    return new JSOpUnaryWithParanthesis ("~", aExpr);
  }

  @NonNull
  public static AbstractJSExpression incrPostfix (@NonNull final IJSExpression aExpr)
  {
    // Some optimizations
    if (aExpr instanceof AbstractJSAtomNumeric)
      return ((AbstractJSAtomNumeric) aExpr).numericIncr ();

    return new JSOpUnary (aExpr, "++");
  }

  @NonNull
  public static AbstractJSExpression incrPrefix (@NonNull final IJSExpression aExpr)
  {
    // Some optimizations
    if (aExpr instanceof AbstractJSAtomNumeric)
      return ((AbstractJSAtomNumeric) aExpr).numericIncr ();

    return new JSOpUnary ("++", aExpr);
  }

  @NonNull
  public static AbstractJSExpression decrPostfix (@NonNull final IJSExpression aExpr)
  {
    // Some optimizations
    if (aExpr instanceof AbstractJSAtomNumeric)
      return ((AbstractJSAtomNumeric) aExpr).numericDecr ();

    return new JSOpUnary (aExpr, "--");
  }

  @NonNull
  public static AbstractJSExpression decrPrefix (@NonNull final IJSExpression aExpr)
  {
    // Some optimizations
    if (aExpr instanceof AbstractJSAtomNumeric)
      return ((AbstractJSAtomNumeric) aExpr).numericDecr ();

    return new JSOpUnary ("--", aExpr);
  }

  @NonNull
  public static JSOpUnary typeof (@NonNull final IJSExpression aExpr)
  {
    return new JSOpUnary ("typeof ", aExpr);
  }

  /* -- Binary operators -- */

  @NonNull
  public static AbstractJSExpression plus (@NonNull final IJSExpression aLeft, @NonNull final IJSExpression aRight)
  {
    // Some optimizations
    if (aLeft instanceof JSStringLiteral && aRight instanceof JSStringLiteral)
      return new JSStringLiteral (((JSStringLiteral) aLeft).getContainedString () + ((JSStringLiteral) aRight).getContainedString ());
    if (aLeft instanceof AbstractJSAtomNumeric && aRight instanceof AbstractJSAtomNumeric)
      return ((AbstractJSAtomNumeric) aLeft).numericPlus ((AbstractJSAtomNumeric) aRight);

    return new JSOpBinary (aLeft, "+", aRight);
  }

  @NonNull
  public static AbstractJSExpression minus (@NonNull final IJSExpression aLeft, @NonNull final IJSExpression aRight)
  {
    // Some optimizations
    if (aLeft instanceof AbstractJSAtomNumeric && aRight instanceof AbstractJSAtomNumeric)
      return ((AbstractJSAtomNumeric) aLeft).numericMinus ((AbstractJSAtomNumeric) aRight);

    return new JSOpBinary (aLeft, "-", aRight);
  }

  @NonNull
  public static AbstractJSExpression mul (@NonNull final IJSExpression aLeft, @NonNull final IJSExpression aRight)
  {
    // Some optimizations
    if (aLeft instanceof AbstractJSAtomNumeric && aRight instanceof AbstractJSAtomNumeric)
      return ((AbstractJSAtomNumeric) aLeft).numericMul ((AbstractJSAtomNumeric) aRight);

    return new JSOpBinary (aLeft, "*", aRight);
  }

  @NonNull
  public static AbstractJSExpression div (@NonNull final IJSExpression aLeft, @NonNull final IJSExpression aRight)
  {
    // Some optimizations
    if (aLeft instanceof AbstractJSAtomNumeric && aRight instanceof AbstractJSAtomNumeric)
      return ((AbstractJSAtomNumeric) aLeft).numericDiv ((AbstractJSAtomNumeric) aRight);

    return new JSOpBinary (aLeft, "/", aRight);
  }

  @NonNull
  public static AbstractJSExpression mod (@NonNull final IJSExpression aLeft, @NonNull final IJSExpression aRight)
  {
    // Some optimizations
    if (aLeft instanceof AbstractJSAtomNumeric && aRight instanceof AbstractJSAtomNumeric)
      return ((AbstractJSAtomNumeric) aLeft).numericMod ((AbstractJSAtomNumeric) aRight);

    return new JSOpBinary (aLeft, "%", aRight);
  }

  @NonNull
  public static JSOpBinary shl (@NonNull final IJSExpression aLeft, @NonNull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, "<<", aRight);
  }

  @NonNull
  public static JSOpBinary shr (@NonNull final IJSExpression aLeft, @NonNull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, ">>", aRight);
  }

  @NonNull
  public static JSOpBinary shrz (@NonNull final IJSExpression aLeft, @NonNull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, ">>>", aRight);
  }

  /**
   * Binary-and
   *
   * @param aLeft
   *        lhs
   * @param aRight
   *        rhs
   * @return operator
   */
  @NonNull
  public static JSOpBinary band (@NonNull final IJSExpression aLeft, @NonNull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, "&", aRight);
  }

  /**
   * Binary-or
   *
   * @param aLeft
   *        lhs
   * @param aRight
   *        rhs
   * @return operator
   */
  @NonNull
  public static JSOpBinary bor (@NonNull final IJSExpression aLeft, @NonNull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, "|", aRight);
  }

  /**
   * Logical-and
   *
   * @param aLeft
   *        lhs
   * @param aRight
   *        rhs
   * @return operator
   */
  @NonNull
  public static IJSExpression cand (@NonNull final IJSExpression aLeft, @NonNull final IJSExpression aRight)
  {
    // Some optimizations
    if (aLeft == JSExpr.TRUE)
      return aRight;
    if (aRight == JSExpr.TRUE)
      return aLeft;
    if (aLeft == JSExpr.FALSE || aRight == JSExpr.FALSE)
      return JSExpr.FALSE;

    return new JSOpBinary (aLeft, "&&", aRight);
  }

  /**
   * Logical-or
   *
   * @param aLeft
   *        lhs
   * @param aRight
   *        rhs
   * @return operator
   */
  @NonNull
  public static IJSExpression cor (@NonNull final IJSExpression aLeft, @NonNull final IJSExpression aRight)
  {
    // Some optimizations
    if (aLeft == JSExpr.TRUE || aRight == JSExpr.TRUE)
      return JSExpr.TRUE;
    if (aLeft == JSExpr.FALSE)
      return aRight;
    if (aRight == JSExpr.FALSE)
      return aLeft;

    return new JSOpBinary (aLeft, "||", aRight);
  }

  /**
   * Exclusive-or
   *
   * @param aLeft
   *        lhs
   * @param aRight
   *        rhs
   * @return operator
   */
  @NonNull
  public static JSOpBinary xor (@NonNull final IJSExpression aLeft, @NonNull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, "^", aRight);
  }

  @NonNull
  public static JSOpBinary lt (@NonNull final IJSExpression aLeft, @NonNull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, "<", aRight);
  }

  @NonNull
  public static JSOpBinary lte (@NonNull final IJSExpression aLeft, @NonNull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, "<=", aRight);
  }

  @NonNull
  public static JSOpBinary gt (@NonNull final IJSExpression aLeft, @NonNull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, ">", aRight);
  }

  @NonNull
  public static JSOpBinary gte (@NonNull final IJSExpression aLeft, @NonNull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, ">=", aRight);
  }

  // equals
  @NonNull
  public static JSOpBinary eq (@NonNull final IJSExpression aLeft, @NonNull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, "==", aRight);
  }

  // exactly equals
  @NonNull
  public static JSOpBinary eeq (@NonNull final IJSExpression aLeft, @NonNull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, "===", aRight);
  }

  // not equal
  @NonNull
  public static JSOpBinary ne (@NonNull final IJSExpression aLeft, @NonNull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, "!=", aRight);
  }

  // exactly not equal
  @NonNull
  public static JSOpBinary ene (@NonNull final IJSExpression aLeft, @NonNull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, "!==", aRight);
  }

  @NonNull
  @CodingStyleguideUnaware
  public static JSOpBinary _instanceof (@NonNull final IJSExpression aLeft, @NonNull final AbstractJSType aRight)
  {
    return new JSOpBinary (aLeft, " instanceof ", aRight);
  }

  /* -- Ternary operators -- */

  @NonNull
  public static JSOpTernary cond (@NonNull final IJSExpression aCond,
                                  @NonNull final IJSExpression aIfTrue,
                                  @NonNull final IJSExpression aIfFalse)
  {
    return new JSOpTernary (aCond, "?", aIfTrue, ":", aIfFalse);
  }
}
