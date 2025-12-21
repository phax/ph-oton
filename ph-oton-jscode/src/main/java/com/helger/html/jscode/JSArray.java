/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonnegative;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.equals.EqualsHelper;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.iface.IHasSize;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.render.HCRenderer;
import com.helger.json.IJson;

/**
 * array creation and initialization.
 *
 * @author Philip Helger
 */
public class JSArray extends AbstractJSExpression implements IHasSize
{
  private ICommonsList <IJSExpression> m_aExprs;

  public JSArray ()
  {}

  @Nonnegative
  public int size ()
  {
    return m_aExprs == null ? 0 : m_aExprs.size ();
  }

  public boolean isEmpty ()
  {
    return m_aExprs == null || m_aExprs.isEmpty ();
  }

  @Override
  public boolean isNotEmpty ()
  {
    return m_aExprs != null && m_aExprs.isNotEmpty ();
  }

  @NonNull
  public JSArray add (final boolean bValue)
  {
    return add (JSExpr.lit (bValue));
  }

  @NonNull
  public JSArray add (final char cValue)
  {
    return add (JSExpr.lit (cValue));
  }

  @NonNull
  public JSArray add (final double dValue)
  {
    return add (JSExpr.lit (dValue));
  }

  @NonNull
  public JSArray add (final float fValue)
  {
    return add (JSExpr.lit (fValue));
  }

  @NonNull
  public JSArray add (final int nValue)
  {
    return add (JSExpr.lit (nValue));
  }

  @NonNull
  public JSArray add (final long nValue)
  {
    return add (JSExpr.lit (nValue));
  }

  @NonNull
  public JSArray add (@Nullable final BigDecimal aValue)
  {
    return add (aValue == null ? JSExpr.NULL : JSExpr.lit (aValue));
  }

  @NonNull
  public JSArray add (@Nullable final BigInteger aValue)
  {
    return add (aValue == null ? JSExpr.NULL : JSExpr.lit (aValue));
  }

  @NonNull
  public JSArray add (@Nullable final String sValue)
  {
    return add (sValue == null ? JSExpr.NULL : JSExpr.lit (sValue));
  }

  @NonNull
  public JSArray add (@Nullable final IJson aValue)
  {
    return add (aValue == null ? JSExpr.NULL : JSExpr.json (aValue));
  }

  @NonNull
  public JSArray add (@Nullable final IHCNode aValue)
  {
    return add (aValue == null ? null : HCRenderer.getAsHTMLStringWithoutNamespaces (aValue));
  }

  @NonNull
  public JSArray addAll (final boolean @NonNull... aCont)
  {
    for (final boolean bValue : aCont)
      add (bValue);
    return this;
  }

  @NonNull
  public JSArray addAll (final char @NonNull... aCont)
  {
    for (final char cValue : aCont)
      add (cValue);
    return this;
  }

  @NonNull
  public JSArray addAll (final double @NonNull... aCont)
  {
    for (final double dValue : aCont)
      add (dValue);
    return this;
  }

  @NonNull
  public JSArray addAll (final float @NonNull... aCont)
  {
    for (final float fValue : aCont)
      add (fValue);
    return this;
  }

  @NonNull
  public JSArray addAll (final int @NonNull... aCont)
  {
    for (final int nValue : aCont)
      add (nValue);
    return this;
  }

  @NonNull
  public JSArray addAll (final long @NonNull... aCont)
  {
    for (final long nValue : aCont)
      add (nValue);
    return this;
  }

  @NonNull
  public JSArray addAll (@Nullable final BigDecimal @NonNull... aCont)
  {
    for (final BigDecimal aValue : aCont)
      add (aValue);
    return this;
  }

  @NonNull
  public JSArray addAll (@Nullable final BigInteger @NonNull... aCont)
  {
    for (final BigInteger aValue : aCont)
      add (aValue);
    return this;
  }

  @NonNull
  public JSArray addAll (@Nullable final String @NonNull... aCont)
  {
    for (final String sValue : aCont)
      add (sValue);
    return this;
  }

  @NonNull
  public JSArray addAll (@NonNull final Iterable <@Nullable String> aCont)
  {
    for (final String sValue : aCont)
      add (sValue);
    return this;
  }

  @NonNull
  public JSArray addAllExpr (@NonNull final IJSExpression @NonNull... aCont)
  {
    for (final IJSExpression aExpr : aCont)
      add (aExpr);
    return this;
  }

  @NonNull
  public JSArray addAllExpr (@NonNull final Iterable <@NonNull ? extends IJSExpression> aCont)
  {
    for (final IJSExpression aExpr : aCont)
      add (aExpr);
    return this;
  }

  /**
   * Add an element to the array initializer
   *
   * @param aExpr
   *        Expression to add. May not be <code>null</code>.
   * @return this
   */
  @NonNull
  public JSArray add (@NonNull final IJSExpression aExpr)
  {
    ValueEnforcer.notNull (aExpr, "Expr");

    if (m_aExprs == null)
      m_aExprs = new CommonsArrayList <> ();
    m_aExprs.add (aExpr);
    return this;
  }

  @NonNull
  public JSArray remove (@Nonnegative final int nIndex)
  {
    if (m_aExprs != null)
      m_aExprs.remove (nIndex);
    return this;
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <IJSExpression> getAll ()
  {
    return new CommonsArrayList <> (m_aExprs);
  }

  public void generate (@NonNull final JSFormatter aFormatter)
  {
    aFormatter.plain ('[');
    if (m_aExprs != null)
      aFormatter.generatable (m_aExprs);
    aFormatter.plain (']');
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSArray rhs = (JSArray) o;
    return EqualsHelper.equals (m_aExprs, rhs.m_aExprs);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aExprs).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("exprs", m_aExprs).getToString ();
  }
}
