/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.html.js.builder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.conversion.HCSettings;
import com.helger.html.js.marshal.JSMarshaller;
import com.helger.json.IJson;

/**
 * array creation and initialization.
 *
 * @author Philip Helger
 */
public class JSAssocArray extends AbstractJSExpression
{
  private Map <IJSExpression, IJSExpression> m_aExprs;

  public JSAssocArray ()
  {}

  public boolean isEmpty ()
  {
    return m_aExprs == null || m_aExprs.isEmpty ();
  }

  @Nonnegative
  public int size ()
  {
    return m_aExprs == null ? 0 : m_aExprs.size ();
  }

  @Nonnull
  public JSAssocArray add (@Nonnull final String sKey, final boolean bValue)
  {
    return add (sKey, JSExpr.lit (bValue));
  }

  @Nonnull
  public JSAssocArray add (@Nonnull final String sKey, final char cValue)
  {
    return add (sKey, JSExpr.lit (cValue));
  }

  @Nonnull
  public JSAssocArray add (@Nonnull final String sKey, final double dValue)
  {
    return add (sKey, JSExpr.lit (dValue));
  }

  @Nonnull
  public JSAssocArray add (@Nonnull final String sKey, final float fValue)
  {
    return add (sKey, JSExpr.lit (fValue));
  }

  @Nonnull
  public JSAssocArray add (@Nonnull final String sKey, final int nValue)
  {
    return add (sKey, JSExpr.lit (nValue));
  }

  @Nonnull
  public JSAssocArray add (@Nonnull final String sKey, final long nValue)
  {
    return add (sKey, JSExpr.lit (nValue));
  }

  @Nonnull
  public JSAssocArray add (@Nonnull final String sKey, @Nullable final BigDecimal aValue)
  {
    return add (sKey, aValue == null ? JSExpr.NULL : JSExpr.lit (aValue));
  }

  @Nonnull
  public JSAssocArray add (@Nonnull final String sKey, @Nullable final BigInteger aValue)
  {
    return add (sKey, aValue == null ? JSExpr.NULL : JSExpr.lit (aValue));
  }

  @Nonnull
  public JSAssocArray add (@Nonnull final String sKey, @Nullable final String sValue)
  {
    return add (sKey, sValue == null ? JSExpr.NULL : JSExpr.lit (sValue));
  }

  @Nonnull
  public JSAssocArray add (@Nonnull final String sKey, @Nullable final IJson aValue)
  {
    return add (sKey, aValue == null ? JSExpr.NULL : JSExpr.json (aValue));
  }

  @Nonnull
  public JSAssocArray add (@Nonnull final String sKey, @Nullable final IHCNode aValue)
  {
    return add (sKey, aValue == null ? null : HCSettings.getAsHTMLStringWithoutNamespaces (aValue));
  }

  @Nonnull
  public JSAssocArray addAllStrings (@Nullable final Map <String, String> aValues)
  {
    if (aValues != null)
      for (final Map.Entry <String, String> aEntry : aValues.entrySet ())
        add (aEntry.getKey (), aEntry.getValue ());
    return this;
  }

  @Nonnull
  public JSAssocArray addAll (@Nullable final Map <String, IJSExpression> aValues)
  {
    if (aValues != null)
      for (final Map.Entry <String, IJSExpression> aEntry : aValues.entrySet ())
        add (aEntry.getKey (), aEntry.getValue ());
    return this;
  }

  @Nonnull
  public JSAssocArray add (@Nonnull final String sKey, @Nonnull final IJSExpression aValue)
  {
    // Don't quote value identifiers
    if (JSMarshaller.isJSIdentifier (sKey))
      return add (new JSAtom (sKey), aValue);

    return add (JSExpr.lit (sKey), aValue);
  }

  /**
   * Add an element to the array initializer
   *
   * @param aKey
   *        key to use
   * @param aValue
   *        value to use
   * @return this
   */
  @Nonnull
  public JSAssocArray add (@Nonnull final IJSExpression aKey, @Nonnull final IJSExpression aValue)
  {
    ValueEnforcer.notNull (aKey, "Key");
    ValueEnforcer.notNull (aValue, "Value");

    if (m_aExprs == null)
      m_aExprs = new LinkedHashMap <IJSExpression, IJSExpression> ();
    m_aExprs.put (aKey, aValue);
    return this;
  }

  @Nonnull
  public JSAssocArray remove (@Nonnull final String sKey)
  {
    if (m_aExprs != null)
      remove (JSExpr.lit (sKey));
    return this;
  }

  @Nonnull
  public JSAssocArray remove (@Nullable final IJSExpression aKey)
  {
    if (m_aExprs != null)
      m_aExprs.remove (aKey);
    return this;
  }

  @Nullable
  public IJSExpression get (@Nullable final String sKey)
  {
    return get (JSExpr.lit (sKey));
  }

  @Nullable
  public IJSExpression get (@Nullable final IJSExpression aKey)
  {
    if (m_aExprs == null)
      return null;
    return m_aExprs.get (aKey);
  }

  @Nonnull
  @ReturnsMutableCopy
  public Map <IJSExpression, IJSExpression> getAll ()
  {
    return CollectionHelper.newOrderedMap (m_aExprs);
  }

  public void generate (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.plain ('{').nl ().indent ();
    if (m_aExprs != null)
    {
      boolean bFirst = true;
      for (final Map.Entry <IJSExpression, IJSExpression> aEntry : m_aExprs.entrySet ())
      {
        if (bFirst)
          bFirst = false;
        else
          aFormatter.plain (',').nl ();
        aFormatter.generatable (aEntry.getKey ()).plain (':').generatable (aEntry.getValue ());
      }
    }
    aFormatter.nl ().outdent ().plain ('}');
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSAssocArray rhs = (JSAssocArray) o;
    return EqualsUtils.equals (m_aExprs, rhs.m_aExprs);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aExprs).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("exprs", m_aExprs).toString ();
  }
}
