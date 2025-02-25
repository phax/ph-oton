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

import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.lang.ICloneable;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSFieldRef;
import com.helger.html.jscode.JSReturn;
import com.helger.html.jscode.JSVar;

@NotThreadSafe
public class BloodhoundOptions implements ICloneable <BloodhoundOptions>
{
  public static final String JSON_DATUM_TOKENIZER = "datumTokenizer";
  public static final String JSON_QUERY_TOKENIZER = "queryTokenizer";
  public static final String JSON_LIMIT = "limit";
  public static final String JSON_DUP_DETECTOR = "dupDetector";
  public static final String JSON_SORTER = "sorter";
  public static final String JSON_LOCAL = "local";
  public static final String JSON_PREFETCH = "prefetch";
  public static final String JSON_REMOTE = "remote";

  public static final int DEFAULT_LIMIT = 5;

  private static final Logger LOGGER = LoggerFactory.getLogger (BloodhoundOptions.class);

  private IJSExpression m_aDatumTokenizer;
  private IJSExpression m_aQueryTokenizer;
  private int m_nLimit = DEFAULT_LIMIT;
  private IJSExpression m_aDupDetector;
  private IJSExpression m_aSorter;
  private ICommonsList <? extends BloodhoundDatum> m_aLocal;
  private BloodhoundPrefetch m_aPrefetch;
  private BloodhoundRemote m_aRemote;

  public BloodhoundOptions ()
  {}

  public BloodhoundOptions (@Nonnull final BloodhoundOptions aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");
    m_aDatumTokenizer = aOther.m_aDatumTokenizer;
    m_aQueryTokenizer = aOther.m_aQueryTokenizer;
    m_nLimit = aOther.m_nLimit;
    m_aDupDetector = aOther.m_aDupDetector;
    m_aSorter = aOther.m_aSorter;
    m_aLocal = aOther.m_aLocal == null ? null : aOther.m_aLocal.getClone ();
    m_aPrefetch = aOther.m_aPrefetch == null ? null : aOther.m_aPrefetch.getClone ();
    m_aRemote = aOther.m_aRemote == null ? null : aOther.m_aRemote.getClone ();
  }

  /**
   * @return A function with the signature (datum) that transforms a datum into
   *         an array of string tokens. Required.
   */
  @Nullable
  public IJSExpression getDatumTokenizer ()
  {
    return m_aDatumTokenizer;
  }

  @Nonnull
  public BloodhoundOptions setDatumTokenizer (@Nullable final IJSExpression aDatumTokenizer)
  {
    m_aDatumTokenizer = aDatumTokenizer;
    return this;
  }

  @Nonnull
  private BloodhoundOptions _setSpecialDatumTokenizer (@Nonnull final JSFieldRef aFieldRef,
                                                       @Nonnull @Nonempty final String sDatumValueFieldName)
  {
    ValueEnforcer.notEmpty (sDatumValueFieldName, "DatumValueFieldName");
    final JSVar aVarDatum = new JSVar ("d");
    return setDatumTokenizer (new JSAnonymousFunction (aVarDatum,
                                                       aFieldRef.invoke ().arg (aVarDatum.ref (sDatumValueFieldName))));
  }

  @Nonnull
  public BloodhoundOptions setDatumTokenizerNonword (@Nonnull @Nonempty final String sDatumValueFieldName)
  {
    return _setSpecialDatumTokenizer (BloodhoundJS.bloodhoundTokenizersNonword (), sDatumValueFieldName);
  }

  @Nonnull
  public BloodhoundOptions setDatumTokenizerWhitespace (@Nonnull @Nonempty final String sDatumValueFieldName)
  {
    return _setSpecialDatumTokenizer (BloodhoundJS.bloodhoundTokenizersWhitespace (), sDatumValueFieldName);
  }

  /**
   * Set a datum tokenizer that uses pre-tokenized tokens (e.g. from remote) as
   * contained in the datum. It therefore uses the field
   * {@link BloodhoundDatum#JSON_TOKENS} of each datum.
   *
   * @return this
   */
  @Nonnull
  public BloodhoundOptions setDatumTokenizerPreTokenized ()
  {
    final JSVar aVarDatum = new JSVar ("d");
    return setDatumTokenizer (new JSAnonymousFunction (aVarDatum,
                                                       new JSReturn (aVarDatum.ref (BloodhoundDatum.JSON_TOKENS))));
  }

  /**
   * @return A function with the signature (query) that transforms a query into
   *         an array of string tokens. Required.
   */
  @Nullable
  public IJSExpression getQueryTokenizer ()
  {
    return m_aQueryTokenizer;
  }

  @Nonnull
  public BloodhoundOptions setQueryTokenizer (@Nullable final IJSExpression aQueryTokenizer)
  {
    m_aQueryTokenizer = aQueryTokenizer;
    return this;
  }

  @Nonnull
  public BloodhoundOptions setQueryTokenizerNonword ()
  {
    return setQueryTokenizer (BloodhoundJS.bloodhoundTokenizersNonword ());
  }

  @Nonnull
  public BloodhoundOptions setQueryTokenizerWhitespace ()
  {
    return setQueryTokenizer (BloodhoundJS.bloodhoundTokenizersWhitespace ());
  }

  /**
   * @return The max number of suggestions to return from Bloodhound#get. If not
   *         reached, the data source will attempt to backfill the suggestions
   *         from remote.
   */
  @Nonnegative
  public int getLimit ()
  {
    return m_nLimit;
  }

  @Nonnull
  public BloodhoundOptions setLimit (@Nonnegative final int nLimit)
  {
    m_nLimit = ValueEnforcer.isGT0 (nLimit, "Limit");
    return this;
  }

  /**
   * @return If set, this is expected to be a function with the signature
   *         (remoteMatch, localMatch) that returns <code>true</code> if the
   *         datums are duplicates or <code>false</code> otherwise. If not set,
   *         duplicate detection will not be performed.
   */
  @Nullable
  public IJSExpression getDupDetector ()
  {
    return m_aDupDetector;
  }

  @Nonnull
  public BloodhoundOptions setDupDetector (@Nullable final IJSExpression aDupDetector)
  {
    m_aDupDetector = aDupDetector;
    return this;
  }

  /**
   * <pre>
   * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/sort
   * </pre>
   *
   * @return A compare function used to sort matched datums for a given query.
   */
  @Nullable
  public IJSExpression getSorter ()
  {
    return m_aSorter;
  }

  @Nonnull
  public BloodhoundOptions setSorter (@Nullable final IJSExpression aSorter)
  {
    m_aSorter = aSorter;
    return this;
  }

  @Nullable
  @ReturnsMutableCopy
  public ICommonsList <? extends BloodhoundDatum> getLocal ()
  {
    return m_aLocal == null ? null : m_aLocal.getClone ();
  }

  @Nonnull
  public BloodhoundOptions setLocal (@Nullable final BloodhoundDatum... aLocal)
  {
    return setLocal (aLocal == null ? null : CollectionHelper.newList (aLocal));
  }

  @Nonnull
  public BloodhoundOptions setLocal (@Nullable final List <? extends BloodhoundDatum> aLocal)
  {
    m_aLocal = aLocal == null ? null : CollectionHelper.newList (aLocal);
    return this;
  }

  @Nullable
  public BloodhoundPrefetch getPrefetch ()
  {
    return m_aPrefetch;
  }

  @Nonnull
  public BloodhoundOptions setPrefetch (@Nullable final BloodhoundPrefetch aPrefetch)
  {
    m_aPrefetch = aPrefetch;
    return this;
  }

  @Nullable
  public BloodhoundRemote getRemote ()
  {
    return m_aRemote;
  }

  @Nonnull
  public BloodhoundOptions setRemote (@Nullable final BloodhoundRemote aRemote)
  {
    m_aRemote = aRemote;
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public JSAssocArray getAsJSObject ()
  {
    if (m_aDatumTokenizer == null)
      LOGGER.warn ("No datumTokenizer present!");
    if (m_aQueryTokenizer == null)
      LOGGER.warn ("No queryTokenizer present!");
    if (m_aLocal == null && m_aPrefetch == null && m_aRemote == null)
      LOGGER.warn ("Either local, prefetch or remote must be set!");

    final JSAssocArray ret = new JSAssocArray ();
    if (m_aDatumTokenizer != null)
      ret.add (JSON_DATUM_TOKENIZER, m_aDatumTokenizer);
    if (m_aQueryTokenizer != null)
      ret.add (JSON_QUERY_TOKENIZER, m_aQueryTokenizer);
    if (m_nLimit != DEFAULT_LIMIT)
      ret.add (JSON_LIMIT, m_nLimit);
    if (m_aDupDetector != null)
      ret.add (JSON_DUP_DETECTOR, m_aDupDetector);
    if (m_aSorter != null)
      ret.add (JSON_SORTER, m_aSorter);
    if (m_aLocal != null)
    {
      final JSArray aLocal = new JSArray ();
      for (final BloodhoundDatum aDatum : m_aLocal)
        aLocal.add (aDatum.getAsJson ());
      ret.add (JSON_LOCAL, aLocal);
    }
    if (m_aPrefetch != null)
      ret.add (JSON_PREFETCH, m_aPrefetch.getAsJSObject ());
    if (m_aRemote != null)
      ret.add (JSON_REMOTE, m_aRemote.getAsJSObject ());
    return ret;
  }

  @Nonnull
  public BloodhoundOptions getClone ()
  {
    return new BloodhoundOptions (this);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("datumTokenizer", m_aDatumTokenizer)
                                       .appendIfNotNull ("queryTokenizer", m_aQueryTokenizer)
                                       .append ("limit", m_nLimit)
                                       .appendIfNotNull ("dupDetector", m_aDupDetector)
                                       .appendIfNotNull ("sorter", m_aSorter)
                                       .appendIfNotNull ("local", m_aLocal)
                                       .appendIfNotNull ("prefetch", m_aPrefetch)
                                       .appendIfNotNull ("remote", m_aRemote)
                                       .getToString ();
  }
}
