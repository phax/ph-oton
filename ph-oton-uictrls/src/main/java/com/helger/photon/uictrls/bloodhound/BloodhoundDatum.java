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
package com.helger.photon.uictrls.bloodhound;

import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.json.IHasJson;
import com.helger.json.IJsonObject;
import com.helger.json.JsonArray;
import com.helger.json.JsonObject;

/**
 * Represents a single Bloodhound datum (= data record) with the minimum data
 * elements {@link #JSON_VALUE} and an automatically created set of
 * {@link #JSON_TOKENS}.
 *
 * @author Philip Helger
 */
@Immutable
public class BloodhoundDatum implements IHasJson, Comparable <BloodhoundDatum>
{
  public static final String JSON_VALUE = "value";
  public static final String JSON_TOKENS = "tokens";

  private final String m_sValue;
  private final ICommonsList <String> m_aTokens;

  /**
   * Constructor using {@link #getTokensFromValue(String)} to tokenize the
   * string.
   *
   * @param sValue
   *        Value to display. Must not be <code>null</code>.
   */
  public BloodhoundDatum (@Nonnull final String sValue)
  {
    this (sValue, getTokensFromValue (sValue));
  }

  /**
   * Constructor
   *
   * @param sValue
   *        Value to display. Must not be <code>null</code>.
   * @param aTokens
   *        All possible tokens. Must not be <code>null</code>.
   */
  public BloodhoundDatum (@Nonnull final String sValue, @Nonnull final String... aTokens)
  {
    ValueEnforcer.notNull (sValue, "Value");
    ValueEnforcer.notEmpty (aTokens, "Tokens");
    m_sValue = sValue;
    m_aTokens = new CommonsArrayList <> (aTokens);
  }

  /**
   * Constructor
   *
   * @param sValue
   *        Value to display. Must not be <code>null</code>.
   * @param aTokens
   *        All possible tokens. Must not be <code>null</code>.
   */
  public BloodhoundDatum (@Nonnull final String sValue, @Nonnull final Collection <String> aTokens)
  {
    ValueEnforcer.notNull (sValue, "Value");
    ValueEnforcer.notEmpty (aTokens, "Tokens");
    m_sValue = sValue;
    m_aTokens = new CommonsArrayList <> (aTokens);
  }

  /**
   * @return The value to display. Never <code>null</code>.
   */
  @Nonnull
  public final String getValue ()
  {
    return m_sValue;
  }

  /**
   * @return A list of all tokens. Never <code>null</code> but maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsList <String> getAllTokens ()
  {
    return m_aTokens.getClone ();
  }

  /**
   * @return This object as JSON object representation. May not be
   *         <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  public IJsonObject getAsJson ()
  {
    return new JsonObject ().add (JSON_VALUE, m_sValue).add (JSON_TOKENS, new JsonArray ().addAll (m_aTokens));
  }

  public int compareTo (@Nonnull final BloodhoundDatum aOther)
  {
    return m_sValue.compareTo (aOther.m_sValue);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final BloodhoundDatum rhs = (BloodhoundDatum) o;
    return m_sValue.equals (rhs.m_sValue) && m_aTokens.equals (rhs.m_aTokens);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sValue).append (m_aTokens).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("value", m_sValue).append ("tokens", m_aTokens).getToString ();
  }

  /**
   * Utility method to get all tokens from a single value
   *
   * @param sValue
   *        The value to use. May not be <code>null</code>.
   * @return An array of tokens to use. Never <code>null</code>.
   */
  @Nonnull
  public static String [] getTokensFromValue (@Nonnull final String sValue)
  {
    return RegExHelper.getSplitToArray (StringHelper.trim (sValue), "\\W+");
  }
}
