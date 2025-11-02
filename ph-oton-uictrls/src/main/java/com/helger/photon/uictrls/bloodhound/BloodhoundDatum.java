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
package com.helger.photon.uictrls.bloodhound;

import java.util.Collection;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.OverridingMethodsMustInvokeSuper;
import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.OverrideOnDemand;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.cache.regex.RegExHelper;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.json.IHasJson;
import com.helger.json.IJsonObject;
import com.helger.json.JsonArray;
import com.helger.json.JsonObject;

/**
 * Represents a single Bloodhound datum (= data record) with the minimum data elements
 * {@link #JSON_VALUE} and an automatically created set of {@link #JSON_TOKENS}.
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
   * Constructor using {@link #getTokensFromValue(String)} to tokenize the string.
   *
   * @param sValue
   *        Value to display. Must not be <code>null</code>.
   */
  public BloodhoundDatum (@NonNull final String sValue)
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
  public BloodhoundDatum (@NonNull final String sValue, @NonNull final String... aTokens)
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
  public BloodhoundDatum (@NonNull final String sValue, @NonNull final Collection <String> aTokens)
  {
    ValueEnforcer.notNull (sValue, "Value");
    ValueEnforcer.notEmpty (aTokens, "Tokens");
    m_sValue = sValue;
    m_aTokens = new CommonsArrayList <> (aTokens);
  }

  /**
   * @return The value to display. Never <code>null</code>.
   */
  @NonNull
  public final String getValue ()
  {
    return m_sValue;
  }

  /**
   * @return A list of all tokens. Never <code>null</code> but maybe empty.
   */
  @NonNull
  @ReturnsMutableCopy
  public final ICommonsList <String> getAllTokens ()
  {
    return m_aTokens.getClone ();
  }

  /**
   * @return This object as JSON object representation. May not be <code>null</code>.
   */
  @NonNull
  @ReturnsMutableCopy
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  public IJsonObject getAsJson ()
  {
    return new JsonObject ().add (JSON_VALUE, m_sValue).add (JSON_TOKENS, new JsonArray ().addAll (m_aTokens));
  }

  public int compareTo (@NonNull final BloodhoundDatum aOther)
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
  @NonNull
  public static String [] getTokensFromValue (@NonNull final String sValue)
  {
    return RegExHelper.getSplitToArray (StringHelper.trim (sValue), "\\W+");
  }
}
