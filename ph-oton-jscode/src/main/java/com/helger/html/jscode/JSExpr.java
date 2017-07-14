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

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.PresentForCodeCoverage;
import com.helger.commons.collection.ArrayHelper;
import com.helger.html.jscode.type.JSPrimitiveType;
import com.helger.json.IJson;

/**
 * Factory methods that generate various {@link IJSExpression}s.
 *
 * @author Philip Helger
 */
@Immutable
public final class JSExpr
{
  public static final IJSExpression THIS = new JSAtom ("this");

  public static final IJSExpression NULL = new JSAtom ("null");

  /**
   * Boolean constant that represents <code>true</code>
   */
  public static final JSAtomBoolean TRUE = new JSAtomBoolean (true);

  /**
   * Boolean constant that represents <code>false</code>
   */
  public static final JSAtomBoolean FALSE = new JSAtomBoolean (false);

  /**
   * Boolean constant that represents <code>undefined</code>
   */
  public static final JSAtom UNDEFINED = new JSAtom ("undefined");

  /**
   * Boolean constant that represents <code>undefined</code>
   */
  public static final JSStringLiteral UNDEFINED_STR = new JSStringLiteral ("undefined");

  /** Number of int atoms cached */
  private static final int MAX_ATOM_INT_CACHE = 256;

  private static final JSAtomInt [] INT_CACHE = new JSAtomInt [MAX_ATOM_INT_CACHE];

  static
  {
    for (int i = 0; i < INT_CACHE.length; ++i)
      INT_CACHE[i] = new JSAtomInt (i);
  }

  @PresentForCodeCoverage
  private static final JSExpr s_aInstance = new JSExpr ();

  /**
   * This class is not instancable.
   */
  private JSExpr ()
  {}

  @Nonnull
  public static JSAssignment assign (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final String sValue)
  {
    return assign (aLhs, JSExpr.lit (sValue));
  }

  @Nonnull
  public static JSAssignment assign (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final IJSExpression aRhs)
  {
    return new JSAssignment (aLhs, "=", aRhs);
  }

  @Nonnull
  public static JSAssignment assignPlus (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final IJSExpression aRhs)
  {
    return new JSAssignment (aLhs, "+=", aRhs);
  }

  @Nonnull
  public static JSAssignment assignMinus (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final IJSExpression aRhs)
  {
    return new JSAssignment (aLhs, "-=", aRhs);
  }

  @Nonnull
  public static JSAssignment assignMultiply (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final IJSExpression aRhs)
  {
    return new JSAssignment (aLhs, "*=", aRhs);
  }

  @Nonnull
  public static JSAssignment assignDivide (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final IJSExpression aRhs)
  {
    return new JSAssignment (aLhs, "/=", aRhs);
  }

  @Nonnull
  public static JSAssignment assignModulo (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final IJSExpression aRhs)
  {
    return new JSAssignment (aLhs, "%=", aRhs);
  }

  @Nonnull
  @CodingStyleguideUnaware
  public static JSInvocation _new (@Nonnull final AbstractJSType aType)
  {
    return new JSInvocation (aType);
  }

  @Nonnull
  public static JSInvocation invoke (@Nonnull final JSVar aVar)
  {
    return new JSInvocation ((IJSExpression) null, aVar.name ());
  }

  @Nonnull
  public static JSInvocation invoke (@Nonnull @Nonempty final String sMethod)
  {
    return new JSInvocation ((IJSExpression) null, sMethod);
  }

  @Nonnull
  public static JSInvocation invoke (@Nonnull final JSMethod aMethod)
  {
    return new JSInvocation ((IJSExpression) null, aMethod);
  }

  @Nonnull
  public static JSInvocation invoke (@Nullable final IJSExpression aLhs, @Nonnull @Nonempty final String sMethod)
  {
    return new JSInvocation (aLhs, sMethod);
  }

  @Nonnull
  public static JSInvocation invoke (@Nullable final IJSExpression aLhs, @Nonnull final JSMethod aMethod)
  {
    return new JSInvocation (aLhs, aMethod);
  }

  @Nonnull
  public static JSInvocation invokeThis (@Nonnull @Nonempty final String sMethod)
  {
    return invoke (THIS, sMethod);
  }

  @Nonnull
  public static JSInvocation invokeThis (@Nonnull final JSMethod aMethod)
  {
    return invoke (THIS, aMethod);
  }

  @Nonnull
  public static JSRef ref (@Nonnull @Nonempty final String sField)
  {
    return new JSRef (sField);
  }

  @Nonnull
  public static JSRef ref (@Nonnull @Nonempty final JSFunction aFunction)
  {
    return new JSRef (aFunction.name ());
  }

  @Nonnull
  public static JSFieldRef ref (@Nonnull final IJSExpression aLhs, @Nonnull final JSVar aField)
  {
    return new JSFieldRef (aLhs, aField);
  }

  @Nonnull
  public static JSFieldRef ref (@Nonnull final IJSExpression aLhs, @Nonnull final String sField)
  {
    return new JSFieldRef (aLhs, sField);
  }

  @Nonnull
  public static JSFieldRef ref (@Nonnull final IJSExpression aLhs, @Nonnull final String... aFields)
  {
    if (ArrayHelper.isEmpty (aFields))
      throw new IllegalArgumentException ("Fields may not be empty");

    JSFieldRef ret = new JSFieldRef (aLhs, aFields[0]);
    for (int i = 1; i < aFields.length; ++i)
      ret = new JSFieldRef (ret, aFields[i]);
    return ret;
  }

  /**
   * @param aField
   *        Field to reference
   * @return <code>this.<i>aField</i></code>
   */
  @Nonnull
  public static JSFieldRef refThis (@Nonnull final JSVar aField)
  {
    return ref (THIS, aField);
  }

  /**
   * @param sField
   *        Field name to reference
   * @return <code>this.<i>sField</i></code>
   */
  @Nonnull
  public static JSFieldRef refThis (@Nonnull final String sField)
  {
    return ref (THIS, sField);
  }

  /**
   * @param aFields
   *        Field names to reference
   * @return <code>this.<i>aFields[0]</i>.<i>aFields[1]</i>....</code>
   */
  @Nonnull
  public static JSFieldRef refThis (@Nonnull final String... aFields)
  {
    return ref (THIS, aFields);
  }

  @Nonnull
  public static JSArrayCompRef component (@Nonnull final IJSExpression aLhs, @Nonnull final IJSExpression aIndex)
  {
    return new JSArrayCompRef (aLhs, aIndex);
  }

  @Nonnull
  public static JSCast cast (@Nonnull final AbstractJSType aType, @Nonnull final IJSExpression aExpr)
  {
    return new JSCast (aType, aExpr);
  }

  @Nonnull
  public static JSAtomBoolean lit (final boolean bValue)
  {
    return bValue ? TRUE : FALSE;
  }

  @Nonnull
  public static JSAtomInt lit (final int nValue)
  {
    return nValue >= 0 && nValue < MAX_ATOM_INT_CACHE ? INT_CACHE[nValue] : new JSAtomInt (nValue);
  }

  @Nonnull
  public static JSAtomInt lit (final long nValue)
  {
    return nValue >= 0 && nValue < MAX_ATOM_INT_CACHE ? INT_CACHE[(int) nValue] : new JSAtomInt (nValue);
  }

  @Nonnull
  public static JSAtom lit (@Nonnull final BigInteger aValue)
  {
    return new JSAtom (aValue.toString ());
  }

  @Nonnull
  public static AbstractJSExpression lit (final float fValue)
  {
    if (Float.isNaN (fValue))
      return JSPrimitiveType.NUMBER.nan ();
    return new JSAtomDecimal (fValue);
  }

  @Nonnull
  public static AbstractJSExpression lit (final double dValue)
  {
    if (Double.isNaN (dValue))
      return JSPrimitiveType.NUMBER.nan ();
    return new JSAtomDecimal (dValue);
  }

  @Nonnull
  public static JSAtomBigDecimal lit (@Nonnull final BigDecimal aValue)
  {
    return new JSAtomBigDecimal (aValue);
  }

  @Nonnull
  public static JSStringLiteral lit (final char cValue)
  {
    return new JSStringLiteral (Character.toString (cValue));
  }

  @Nonnull
  public static JSStringLiteral lit (@Nonnull final String sValue)
  {
    return new JSStringLiteral (sValue);
  }

  @Nonnull
  public static IJSExpression convert (@Nullable final Object o)
  {
    if (o == null)
      return NULL;
    if (o instanceof IJSExpression)
      return (IJSExpression) o;
    if (o instanceof String)
      return lit ((String) o);
    if (o instanceof BigInteger)
      return lit ((BigInteger) o);
    if (o instanceof BigDecimal)
      return lit ((BigDecimal) o);
    if (o instanceof Boolean)
      return lit (((Boolean) o).booleanValue ());
    if (o instanceof Character)
      return lit (((Character) o).charValue ());
    if (o instanceof Double)
      return lit (((Double) o).doubleValue ());
    if (o instanceof Float)
      return lit (((Float) o).floatValue ());
    if (o instanceof Long)
      return lit (((Long) o).longValue ());
    if (o instanceof Number)
      return lit (((Number) o).intValue ());
    throw new IllegalArgumentException ("Cannot convert object of class " + o.getClass () + " to IJSExpression!");
  }

  @Nonnull
  public static JSRegExLiteral regex (@Nonnull final String sRegEx)
  {
    return new JSRegExLiteral (sRegEx);
  }

  @Nonnull
  public static JSExprDirect json (@Nonnull final IJson aJson)
  {
    ValueEnforcer.notNull (aJson, "Json");

    return direct (aJson.getAsJsonString ());
  }

  /**
   * Creates an expression directly from a source code fragment.
   * <p>
   * This method can be used as a short-cut to create a {@link IJSExpression}.
   * For example, instead of <code>_a.gt(_b)</code>, you can write it as:
   * <code>JSExpr.direct("a&gt;b")</code>.
   * <p>
   * Be warned that there is a danger in using this method, as it obfuscates the
   * object model.
   *
   * @param sSource
   *        direct JS code
   * @return Direct expression
   */
  @Nonnull
  public static JSExprDirect direct (@Nonnull final String sSource)
  {
    return new JSExprDirect (sSource);
  }
}
