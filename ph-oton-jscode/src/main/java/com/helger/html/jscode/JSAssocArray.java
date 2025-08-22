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
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.DoublePredicate;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

import com.helger.annotation.Nonnegative;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.equals.EqualsHelper;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.iface.IHasSize;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.commons.CommonsLinkedHashMap;
import com.helger.collection.commons.ICommonsOrderedMap;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.render.HCRenderer;
import com.helger.html.js.JSMarshaller;
import com.helger.json.IJson;
import com.helger.json.IJsonObject;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * array creation and initialization.
 *
 * @author Philip Helger
 */
public class JSAssocArray extends AbstractJSExpression implements IHasSize
{
  public static final boolean DEFAULT_FORCE_QUOTING_NAMES = false;

  private ICommonsOrderedMap <IJSExpression, IJSExpression> m_aExprs;
  private boolean m_bForceQuotingNames = DEFAULT_FORCE_QUOTING_NAMES;

  public JSAssocArray ()
  {}

  public boolean isForceQuotingNames ()
  {
    return m_bForceQuotingNames;
  }

  @Nonnull
  public JSAssocArray setForceQuotingNames (final boolean bForceQuotingNames)
  {
    m_bForceQuotingNames = bForceQuotingNames;
    return this;
  }

  /**
   * Get the key to be used. May be either a {@link JSAtom} or a
   * {@link JSStringLiteral}.
   *
   * @param sKey
   *        Key to use. May not be <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public final IJSExpression getKey (@Nonnull final String sKey)
  {
    if (!m_bForceQuotingNames)
    {
      // Don't quote value identifiers
      if (JSMarshaller.isJSIdentifier (sKey))
        return new JSAtom (sKey);
    }
    return JSExpr.lit (sKey);
  }

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
  public JSAssocArray addIfNotNull (@Nonnull final String sKey, @Nullable final String sValue)
  {
    if (sValue != null)
      return add (sKey, JSExpr.lit (sValue));
    return this;
  }

  @Nonnull
  public JSAssocArray add (@Nonnull final String sKey, @Nullable final IJson aValue)
  {
    return add (sKey, aValue == null ? JSExpr.NULL : JSExpr.json (aValue));
  }

  @Nonnull
  public JSAssocArray add (@Nonnull final String sKey, @Nullable final IHCNode aValue)
  {
    return add (sKey, aValue == null ? null : HCRenderer.getAsHTMLStringWithoutNamespaces (aValue));
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
  public <T> JSAssocArray addAll (@Nonnull final Iterable <? extends T> aCont,
                                  @Nonnull final Function <? super T, String> aKeyExtractor,
                                  @Nonnull final Function <? super T, IJSExpression> aValueExtractor)
  {
    for (final T aObj : aCont)
      add (aKeyExtractor.apply (aObj), aValueExtractor.apply (aObj));
    return this;
  }

  @Nonnull
  public JSAssocArray add (@Nonnull final String sKey, @Nonnull final IJSExpression aValue)
  {
    return add (getKey (sKey), aValue);
  }

  @Nonnull
  public <T extends IJSExpression> JSAssocArray addIf (@Nonnull final String sKey,
                                                       @Nonnull final T aValue,
                                                       @Nonnull final BooleanSupplier aFilter)
  {
    if (aFilter.getAsBoolean ())
      return add (getKey (sKey), aValue);
    return this;
  }

  @Nonnull
  public <T extends IJSExpression> JSAssocArray addIf (@Nonnull final String sKey,
                                                       @Nonnull final T aValue,
                                                       @Nonnull final Predicate <? super T> aFilter)
  {
    if (aFilter.test (aValue))
      return add (getKey (sKey), aValue);
    return this;
  }

  @Nonnull
  public JSAssocArray addIf (@Nonnull final String sKey, final int nValue, @Nonnull final IntPredicate aFilter)
  {
    if (aFilter.test (nValue))
      return add (sKey, nValue);
    return this;
  }

  @Nonnull
  public JSAssocArray addIf (@Nonnull final String sKey, final long nValue, @Nonnull final LongPredicate aFilter)
  {
    if (aFilter.test (nValue))
      return add (sKey, nValue);
    return this;
  }

  @Nonnull
  public JSAssocArray addIf (@Nonnull final String sKey, final double dValue, @Nonnull final DoublePredicate aFilter)
  {
    if (aFilter.test (dValue))
      return add (sKey, dValue);
    return this;
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
      m_aExprs = new CommonsLinkedHashMap <> ();
    m_aExprs.put (aKey, aValue);
    return this;
  }

  @Nonnull
  public JSAssocArray addAll (@Nonnull final IJsonObject aJson)
  {
    for (final Map.Entry <String, IJson> aEntry : aJson)
      add (aEntry.getKey (), JSExpr.json (aEntry.getValue ()));
    return this;
  }

  @Nonnull
  public JSAssocArray remove (@Nonnull final String sKey)
  {
    if (m_aExprs != null)
      remove (getKey (sKey));
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
    if (sKey == null)
      return null;
    return get (getKey (sKey));
  }

  @Nullable
  public IJSExpression get (@Nullable final IJSExpression aKey)
  {
    if (m_aExprs == null)
      return null;
    return m_aExprs.get (aKey);
  }

  @Nullable
  public IJSExpression computeIfAbsent (@Nullable final String sKey,
                                        @Nonnull final Function <IJSExpression, IJSExpression> aValueSupplier)
  {
    if (sKey == null)
      return null;
    return computeIfAbsent (getKey (sKey), aValueSupplier);
  }

  @Nullable
  public IJSExpression computeIfAbsent (@Nullable final IJSExpression aKey,
                                        @Nonnull final Function <IJSExpression, IJSExpression> aValueSupplier)
  {
    if (m_aExprs == null)
      m_aExprs = new CommonsLinkedHashMap <> ();
    return m_aExprs.computeIfAbsent (aKey, aValueSupplier);
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsOrderedMap <IJSExpression, IJSExpression> getAll ()
  {
    return new CommonsLinkedHashMap <> (m_aExprs);
  }

  public void generate (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.plain ('{');
    if (m_aExprs != null && m_aExprs.isNotEmpty ())
    {
      aFormatter.nl ().indent ();
      boolean bFirst = true;
      for (final Map.Entry <IJSExpression, IJSExpression> aEntry : m_aExprs.entrySet ())
      {
        if (bFirst)
          bFirst = false;
        else
          aFormatter.plain (',').nl ();
        aFormatter.generatable (aEntry.getKey ()).plain (':').generatable (aEntry.getValue ());
      }
      aFormatter.nl ().outdent ();
    }
    aFormatter.plain ('}');
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSAssocArray rhs = (JSAssocArray) o;
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
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("Exprs", m_aExprs)
                            .append ("ForceQuotingNames", m_bForceQuotingNames)
                            .getToString ();
  }
}
