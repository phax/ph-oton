/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.PresentForCodeCoverage;

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

  @Nonnull
  public static AbstractJSExpression minus (@Nonnull final IJSExpression aExpr)
  {
    // Some optimizations
    if (aExpr instanceof AbstractJSAtomNumeric)
      return ((AbstractJSAtomNumeric) aExpr).numericMinus ();

    return new JSOpUnaryWithParanthesis ("-", aExpr);
  }

  @Nonnull
  public static JSExprParanthesis inParantheses (@Nonnull final IJSExpression aExpr)
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
  @Nonnull
  public static AbstractJSExpression not (@Nonnull final IJSExpression aExpr)
  {
    // Some optimizations
    if (aExpr == JSExpr.TRUE)
      return JSExpr.FALSE;
    if (aExpr == JSExpr.FALSE)
      return JSExpr.TRUE;

    return new JSOpUnaryWithParanthesis ("!", aExpr);
  }

  @Nonnull
  public static JSOpUnaryWithParanthesis complement (@Nonnull final IJSExpression aExpr)
  {
    return new JSOpUnaryWithParanthesis ("~", aExpr);
  }

  @Nonnull
  public static AbstractJSExpression incrPostfix (@Nonnull final IJSExpression aExpr)
  {
    // Some optimizations
    if (aExpr instanceof AbstractJSAtomNumeric)
      return ((AbstractJSAtomNumeric) aExpr).numericIncr ();

    return new JSOpUnary (aExpr, "++");
  }

  @Nonnull
  public static AbstractJSExpression incrPrefix (@Nonnull final IJSExpression aExpr)
  {
    // Some optimizations
    if (aExpr instanceof AbstractJSAtomNumeric)
      return ((AbstractJSAtomNumeric) aExpr).numericIncr ();

    return new JSOpUnary ("++", aExpr);
  }

  @Nonnull
  public static AbstractJSExpression decrPostfix (@Nonnull final IJSExpression aExpr)
  {
    // Some optimizations
    if (aExpr instanceof AbstractJSAtomNumeric)
      return ((AbstractJSAtomNumeric) aExpr).numericDecr ();

    return new JSOpUnary (aExpr, "--");
  }

  @Nonnull
  public static AbstractJSExpression decrPrefix (@Nonnull final IJSExpression aExpr)
  {
    // Some optimizations
    if (aExpr instanceof AbstractJSAtomNumeric)
      return ((AbstractJSAtomNumeric) aExpr).numericDecr ();

    return new JSOpUnary ("--", aExpr);
  }

  @Nonnull
  public static JSOpUnary typeof (@Nonnull final IJSExpression aExpr)
  {
    return new JSOpUnary ("typeof ", aExpr);
  }

  /* -- Binary operators -- */

  @Nonnull
  public static AbstractJSExpression plus (@Nonnull final IJSExpression aLeft, @Nonnull final IJSExpression aRight)
  {
    // Some optimizations
    if (aLeft instanceof JSStringLiteral && aRight instanceof JSStringLiteral)
      return new JSStringLiteral (((JSStringLiteral) aLeft).getContainedString () + ((JSStringLiteral) aRight).getContainedString ());
    if (aLeft instanceof AbstractJSAtomNumeric && aRight instanceof AbstractJSAtomNumeric)
      return ((AbstractJSAtomNumeric) aLeft).numericPlus ((AbstractJSAtomNumeric) aRight);

    return new JSOpBinary (aLeft, "+", aRight);
  }

  @Nonnull
  public static AbstractJSExpression minus (@Nonnull final IJSExpression aLeft, @Nonnull final IJSExpression aRight)
  {
    // Some optimizations
    if (aLeft instanceof AbstractJSAtomNumeric && aRight instanceof AbstractJSAtomNumeric)
      return ((AbstractJSAtomNumeric) aLeft).numericMinus ((AbstractJSAtomNumeric) aRight);

    return new JSOpBinary (aLeft, "-", aRight);
  }

  @Nonnull
  public static AbstractJSExpression mul (@Nonnull final IJSExpression aLeft, @Nonnull final IJSExpression aRight)
  {
    // Some optimizations
    if (aLeft instanceof AbstractJSAtomNumeric && aRight instanceof AbstractJSAtomNumeric)
      return ((AbstractJSAtomNumeric) aLeft).numericMul ((AbstractJSAtomNumeric) aRight);

    return new JSOpBinary (aLeft, "*", aRight);
  }

  @Nonnull
  public static AbstractJSExpression div (@Nonnull final IJSExpression aLeft, @Nonnull final IJSExpression aRight)
  {
    // Some optimizations
    if (aLeft instanceof AbstractJSAtomNumeric && aRight instanceof AbstractJSAtomNumeric)
      return ((AbstractJSAtomNumeric) aLeft).numericDiv ((AbstractJSAtomNumeric) aRight);

    return new JSOpBinary (aLeft, "/", aRight);
  }

  @Nonnull
  public static AbstractJSExpression mod (@Nonnull final IJSExpression aLeft, @Nonnull final IJSExpression aRight)
  {
    // Some optimizations
    if (aLeft instanceof AbstractJSAtomNumeric && aRight instanceof AbstractJSAtomNumeric)
      return ((AbstractJSAtomNumeric) aLeft).numericMod ((AbstractJSAtomNumeric) aRight);

    return new JSOpBinary (aLeft, "%", aRight);
  }

  @Nonnull
  public static JSOpBinary shl (@Nonnull final IJSExpression aLeft, @Nonnull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, "<<", aRight);
  }

  @Nonnull
  public static JSOpBinary shr (@Nonnull final IJSExpression aLeft, @Nonnull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, ">>", aRight);
  }

  @Nonnull
  public static JSOpBinary shrz (@Nonnull final IJSExpression aLeft, @Nonnull final IJSExpression aRight)
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
  @Nonnull
  public static JSOpBinary band (@Nonnull final IJSExpression aLeft, @Nonnull final IJSExpression aRight)
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
  @Nonnull
  public static JSOpBinary bor (@Nonnull final IJSExpression aLeft, @Nonnull final IJSExpression aRight)
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
  @Nonnull
  public static IJSExpression cand (@Nonnull final IJSExpression aLeft, @Nonnull final IJSExpression aRight)
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
  @Nonnull
  public static IJSExpression cor (@Nonnull final IJSExpression aLeft, @Nonnull final IJSExpression aRight)
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
  @Nonnull
  public static JSOpBinary xor (@Nonnull final IJSExpression aLeft, @Nonnull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, "^", aRight);
  }

  @Nonnull
  public static JSOpBinary lt (@Nonnull final IJSExpression aLeft, @Nonnull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, "<", aRight);
  }

  @Nonnull
  public static JSOpBinary lte (@Nonnull final IJSExpression aLeft, @Nonnull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, "<=", aRight);
  }

  @Nonnull
  public static JSOpBinary gt (@Nonnull final IJSExpression aLeft, @Nonnull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, ">", aRight);
  }

  @Nonnull
  public static JSOpBinary gte (@Nonnull final IJSExpression aLeft, @Nonnull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, ">=", aRight);
  }

  // equals
  @Nonnull
  public static JSOpBinary eq (@Nonnull final IJSExpression aLeft, @Nonnull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, "==", aRight);
  }

  // exactly equals
  @Nonnull
  public static JSOpBinary eeq (@Nonnull final IJSExpression aLeft, @Nonnull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, "===", aRight);
  }

  // not equal
  @Nonnull
  public static JSOpBinary ne (@Nonnull final IJSExpression aLeft, @Nonnull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, "!=", aRight);
  }

  // exactly not equal
  @Nonnull
  public static JSOpBinary ene (@Nonnull final IJSExpression aLeft, @Nonnull final IJSExpression aRight)
  {
    return new JSOpBinary (aLeft, "!==", aRight);
  }

  @Nonnull
  @CodingStyleguideUnaware
  public static JSOpBinary _instanceof (@Nonnull final IJSExpression aLeft, @Nonnull final AbstractJSType aRight)
  {
    return new JSOpBinary (aLeft, " instanceof ", aRight);
  }

  /* -- Ternary operators -- */

  @Nonnull
  public static JSOpTernary cond (@Nonnull final IJSExpression aCond,
                                  @Nonnull final IJSExpression aIfTrue,
                                  @Nonnull final IJSExpression aIfFalse)
  {
    return new JSOpTernary (aCond, "?", aIfTrue, ":", aIfFalse);
  }
}
