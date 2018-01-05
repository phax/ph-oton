/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.traits.IGenericImplTrait;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.render.HCRenderer;
import com.helger.json.IJson;
import com.helger.xml.microdom.IMicroQName;

public interface IJSInvocation <IMPLTYPE extends IJSInvocation <IMPLTYPE>> extends
                               IJSExpression,
                               IJSStatement,
                               IGenericImplTrait <IMPLTYPE>
{
  /**
   * Add an expression to this invocation's argument list
   *
   * @param aExpr
   *        Argument to add to argument list
   * @return this
   */
  @Nonnull
  IMPLTYPE arg (@Nonnull final IJSExpression aExpr);

  /**
   * Adds a literal argument. Short for {@code arg(JSExpr.lit(bArgument))}
   *
   * @param bValue
   *        value to be added as an argument
   * @return this
   */
  @Nonnull
  default IMPLTYPE arg (final boolean bValue)
  {
    return arg (JSExpr.lit (bValue));
  }

  /**
   * Adds a literal argument. Short for {@code arg(JSExpr.lit(cArgument))}
   *
   * @param cValue
   *        value to be added as an argument
   * @return this
   */
  @Nonnull
  default IMPLTYPE arg (final char cValue)
  {
    return arg (JSExpr.lit (cValue));
  }

  /**
   * Adds a literal argument. Short for {@code arg(JSExpr.lit(dArgument))}
   *
   * @param dValue
   *        value to be added as an argument
   * @return this
   */
  @Nonnull
  default IMPLTYPE arg (final double dValue)
  {
    return arg (JSExpr.lit (dValue));
  }

  /**
   * Adds a literal argument. Short for {@code arg(JSExpr.lit(fArgument))}
   *
   * @param fValue
   *        value to be added as an argument
   * @return this
   */
  @Nonnull
  default IMPLTYPE arg (final float fValue)
  {
    return arg (JSExpr.lit (fValue));
  }

  /**
   * Adds a literal argument. Short for {@code arg(JSExpr.lit(nArgument))}
   *
   * @param nValue
   *        value to be added as an argument
   * @return this
   */
  @Nonnull
  default IMPLTYPE arg (final int nValue)
  {
    return arg (JSExpr.lit (nValue));
  }

  /**
   * Adds a literal argument. Short for {@code arg(JSExpr.lit(nArgument))}
   *
   * @param nValue
   *        value to be added as an argument
   * @return this
   */
  @Nonnull
  default IMPLTYPE arg (final long nValue)
  {
    return arg (JSExpr.lit (nValue));
  }

  /**
   * Add an expression to this invocation's argument list or "null" if it is
   * <code>null</code>
   *
   * @param aExpr
   *        Argument to add to argument list
   * @return this
   */
  @Nonnull
  default IMPLTYPE argOrNull (@Nullable final IJSExpression aExpr)
  {
    return aExpr == null ? argNull () : arg (aExpr);
  }

  @Nonnull
  default IMPLTYPE argOrNull (@Nullable final Integer aValue)
  {
    return aValue == null ? argNull () : arg (aValue.intValue ());
  }

  @Nonnull
  default IMPLTYPE argOrNull (@Nullable final Long aValue)
  {
    return aValue == null ? argNull () : arg (aValue.longValue ());
  }

  @Nonnull
  default IMPLTYPE arg (@Nullable final String sValue)
  {
    return sValue == null ? argNull () : arg (JSExpr.lit (sValue));
  }

  @Nonnull
  default IMPLTYPE arg (@Nullable final IJson aValue)
  {
    return aValue == null ? argNull () : arg (JSExpr.json (aValue));
  }

  @Nonnull
  default IMPLTYPE arg (@Nullable final IMicroQName aValue)
  {
    return aValue == null ? argNull () : arg (JSExpr.lit (aValue.getName ()));
  }

  @Nonnull
  default IMPLTYPE arg (@Nullable final BigDecimal aValue)
  {
    return aValue == null ? argNull () : arg (JSExpr.lit (aValue));
  }

  @Nonnull
  default IMPLTYPE arg (@Nullable final BigInteger aValue)
  {
    return aValue == null ? argNull () : arg (JSExpr.lit (aValue));
  }

  @Nonnull
  default IMPLTYPE arg (@Nullable final EHTMLElement... aElements)
  {
    if (aElements == null)
      return argNull ();

    final StringBuilder aSB = new StringBuilder ();
    for (final EHTMLElement eElement : aElements)
    {
      if (aSB.length () > 0)
        aSB.append (' ');
      aSB.append (eElement.getElementName ());
    }
    return arg (aSB.toString ());
  }

  @Nonnull
  default IMPLTYPE arg (@Nullable final Iterable <EHTMLElement> aElements)
  {
    if (aElements == null)
      return argNull ();

    final StringBuilder aSB = new StringBuilder ();
    for (final EHTMLElement eElement : aElements)
    {
      if (aSB.length () > 0)
        aSB.append (' ');
      aSB.append (eElement.getElementName ());
    }
    return arg (aSB.toString ());
  }

  @Nonnull
  default IMPLTYPE arg (@Nullable final String... aElements)
  {
    if (aElements == null)
      return argNull ();

    final StringBuilder aSB = new StringBuilder ();
    for (final String sElement : aElements)
    {
      if (aSB.length () > 0)
        aSB.append (' ');
      aSB.append (sElement);
    }
    return arg (aSB.toString ());
  }

  @Nonnull
  default IMPLTYPE arg (@Nullable final IHCNode aHCNode)
  {
    return aHCNode == null ? argNull () : arg (HCRenderer.getAsHTMLStringWithoutNamespaces (aHCNode));
  }

  /**
   * Adds a null argument. Short for {@code arg(JSExpr.NULL)}
   *
   * @return this
   */
  @Nonnull
  default IMPLTYPE argNull ()
  {
    return arg (JSExpr.NULL);
  }

  /**
   * Adds a null argument. Short for {@code arg(JSExpr.THIS)}
   *
   * @return this
   */
  @Nonnull
  default IMPLTYPE argThis ()
  {
    return arg (JSExpr.THIS);
  }

  /**
   * Add an expression to this invocation's argument list
   *
   * @param nIndex
   *        Index to insert
   * @param aArgument
   *        Argument to add to argument list
   * @return this
   */
  @Nonnull
  default IMPLTYPE argOrNull (@Nonnegative final int nIndex, @Nullable final IJSExpression aArgument)
  {
    return aArgument == null ? argNull (nIndex) : arg (nIndex, aArgument);
  }

  /**
   * Add an expression to this invocation's argument list
   *
   * @param nIndex
   *        Index to insert
   * @param aArgument
   *        Argument to add to argument list
   * @return this
   */
  @Nonnull
  IMPLTYPE arg (@Nonnegative final int nIndex, @Nonnull final IJSExpression aArgument);

  /**
   * Adds a literal argument. Short for {@code arg(nIndex, JSExpr.lit(v))}
   *
   * @param nIndex
   *        Index to insert
   * @param bValue
   *        argument value
   * @return this
   */
  @Nonnull
  default IMPLTYPE arg (@Nonnegative final int nIndex, final boolean bValue)
  {
    return arg (nIndex, JSExpr.lit (bValue));
  }

  /**
   * Adds a literal argument. Short for {@code arg(nIndex, JSExpr.lit(v))}
   *
   * @param nIndex
   *        Index to insert
   * @param cValue
   *        argument value
   * @return this
   */
  @Nonnull
  default IMPLTYPE arg (@Nonnegative final int nIndex, final char cValue)
  {
    return arg (nIndex, JSExpr.lit (cValue));
  }

  /**
   * Adds a literal argument. Short for {@code arg(nIndex, JSExpr.lit(v))}
   *
   * @param nIndex
   *        Index to insert
   * @param dValue
   *        argument value
   * @return this
   */
  @Nonnull
  default IMPLTYPE arg (@Nonnegative final int nIndex, final double dValue)
  {
    return arg (nIndex, JSExpr.lit (dValue));
  }

  /**
   * Adds a literal argument. Short for {@code arg(nIndex, JSExpr.lit(v))}
   *
   * @param nIndex
   *        Index to insert
   * @param fValue
   *        argument value
   * @return this
   */
  @Nonnull
  default IMPLTYPE arg (@Nonnegative final int nIndex, final float fValue)
  {
    return arg (nIndex, JSExpr.lit (fValue));
  }

  /**
   * Adds a literal argument. Short for {@code arg(nIndex, JSExpr.lit(v))}
   *
   * @param nIndex
   *        Index to insert
   * @param nValue
   *        argument value
   * @return this
   */
  @Nonnull
  default IMPLTYPE arg (@Nonnegative final int nIndex, final int nValue)
  {
    return arg (nIndex, JSExpr.lit (nValue));
  }

  /**
   * Adds a literal argument. Short for {@code arg(nIndex, JSExpr.lit(v))}
   *
   * @param nIndex
   *        Index to insert
   * @param nValue
   *        argument value
   * @return this
   */
  @Nonnull
  default IMPLTYPE arg (@Nonnegative final int nIndex, final long nValue)
  {
    return arg (nIndex, JSExpr.lit (nValue));
  }

  @Nonnull
  default IMPLTYPE arg (@Nonnegative final int nIndex, @Nullable final Integer aValue)
  {
    return aValue == null ? argNull (nIndex) : arg (nIndex, aValue.intValue ());
  }

  @Nonnull
  default IMPLTYPE arg (@Nonnegative final int nIndex, @Nullable final Long aValue)
  {
    return aValue == null ? argNull (nIndex) : arg (nIndex, aValue.longValue ());
  }

  @Nonnull
  default IMPLTYPE arg (@Nonnegative final int nIndex, @Nullable final String sValue)
  {
    return sValue == null ? argNull (nIndex) : arg (nIndex, JSExpr.lit (sValue));
  }

  @Nonnull
  default IMPLTYPE arg (@Nonnegative final int nIndex, @Nullable final IJson aValue)
  {
    return aValue == null ? argNull (nIndex) : arg (nIndex, JSExpr.json (aValue));
  }

  /**
   * Adds a null argument. Short for {@code arg(nIndex, JSExpr.NULL)}
   *
   * @param nIndex
   *        Index to insert
   * @return this
   */
  @Nonnull
  default IMPLTYPE argNull (@Nonnegative final int nIndex)
  {
    return arg (nIndex, JSExpr.NULL);
  }

  /**
   * Adds a null argument. Short for {@code arg(nIndex, JSExpr.THIS)}
   *
   * @param nIndex
   *        Index to insert
   * @return this
   */
  @Nonnull
  default IMPLTYPE argThis (@Nonnegative final int nIndex)
  {
    return arg (nIndex, JSExpr.THIS);
  }

  /**
   * Add 0-n expressions to this invocation's argument list
   *
   * @param aExprs
   *        Argument to add to argument list
   * @return this
   */
  @Nonnull
  default IMPLTYPE args (@Nullable final Iterable <? extends IJSExpression> aExprs)
  {
    if (aExprs != null)
      for (final IJSExpression aExpr : aExprs)
        arg (aExpr);
    return thisAsT ();
  }

  /**
   * Add 0-n expressions to this invocation's argument list
   *
   * @param aExprs
   *        Argument to add to argument list
   * @return this
   */
  @Nonnull
  default IMPLTYPE args (@Nullable final IJSExpression... aExprs)
  {
    if (aExprs != null)
      for (final IJSExpression aExpr : aExprs)
        arg (aExpr);
    return thisAsT ();
  }

  /**
   * Add 0-n expressions to this invocation's argument list
   *
   * @param aExprs
   *        Argument to add to argument list
   * @return this
   */
  @Nonnull
  default IMPLTYPE argsOrNull (@Nullable final Iterable <? extends IJSExpression> aExprs)
  {
    if (aExprs != null)
      for (final IJSExpression aExpr : aExprs)
        argOrNull (aExpr);
    return thisAsT ();
  }

  /**
   * Add 0-n expressions to this invocation's argument list
   *
   * @param aExprs
   *        Argument to add to argument list
   * @return this
   */
  @Nonnull
  default IMPLTYPE argssOrNull (@Nullable final IJSExpression... aExprs)
  {
    if (aExprs != null)
      for (final IJSExpression aExpr : aExprs)
        argOrNull (aExpr);
    return thisAsT ();
  }

  /**
   * Returns all arguments of the invocation.
   *
   * @return If there's no arguments, an empty array will be returned.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <IJSExpression> args ();

  /**
   * @return Number of currently contained arguments. Always &ge; 0.
   */
  @Nonnegative
  int getArgCount ();

  /**
   * @return <code>true</code> if at least 1 arg is present, <code>false</code>
   *         otherwise.
   */
  boolean hasArgs ();

  @Nullable
  IJSExpression getArgAtIndex (final int nIndex);

  /**
   * Remove all arguments
   *
   * @return this
   */
  @Nonnull
  IMPLTYPE removeAllArgs ();
}
