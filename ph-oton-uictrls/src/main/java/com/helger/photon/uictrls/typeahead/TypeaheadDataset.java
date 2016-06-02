/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.typeahead;

import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.render.HCRenderer;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSStringLiteral;

/**
 * Represents a single typeahead dataset.
 *
 * @author Philip Helger
 */
@Immutable
public class TypeaheadDataset
{
  public static final String JSON_NAME = "name";
  public static final String JSON_VALUE_KEY = "valueKey";
  public static final String JSON_LIMIT = "limit";
  public static final String JSON_TEMPLATE = "template";
  public static final String JSON_ENGINE = "engine";
  public static final String JSON_HEADER = "header";
  public static final String JSON_FOOTER = "footer";
  public static final String JSON_LOCAL = "local";
  public static final String JSON_PREFETCH = "prefetch";
  public static final String JSON_REMOTE = "remote";

  public static final String DEFAULT_VALUE_KEY = "value";
  public static final int DEFAULT_LIMIT = 5;

  private static final Logger s_aLogger = LoggerFactory.getLogger (TypeaheadDataset.class);

  private final String m_sName;
  private String m_sValueKey = DEFAULT_VALUE_KEY;
  private int m_nLimit = DEFAULT_LIMIT;
  private IJSExpression m_aTemplate;
  private String m_sEngine;
  private IJSExpression m_aHeader;
  private IJSExpression m_aFooter;
  private ICommonsList <? extends TypeaheadDatum> m_aLocal;
  private TypeaheadPrefetch m_aPrefetch;
  private TypeaheadRemote m_aRemote;

  /**
   * Constructor.
   *
   * @param sName
   *        The string used to identify the dataset. Used by typeahead.js to
   *        cache intelligently.
   */
  public TypeaheadDataset (@Nonnull @Nonempty final String sName)
  {
    ValueEnforcer.notEmpty (sName, "Name");
    m_sName = sName;
  }

  /**
   * @return The string used to identify the dataset. Used by typeahead.js to
   *         cache intelligently.
   */
  @Nonnull
  @Nonempty
  public String getName ()
  {
    return m_sName;
  }

  /**
   * The key used to access the value of the datum in the datum object. Defaults
   * to <code>value</code>.
   *
   * @param sValueKey
   *        The name of the value key in the typeahead datum. May neither be
   *        <code>null</code> nor empty.
   * @return this
   */
  @Nonnull
  public TypeaheadDataset setValueKey (@Nonnull @Nonempty final String sValueKey)
  {
    ValueEnforcer.notEmpty (sValueKey, "ValueKey");
    m_sValueKey = sValueKey;
    return this;
  }

  /**
   * @return The key used to access the value of the datum in the datum object.
   *         Defaults to <code>value</code>.
   */
  @Nonnull
  @Nonempty
  public String getValueKey ()
  {
    return m_sValueKey;
  }

  /**
   * The max number of suggestions from the dataset to display for a given
   * query. Defaults to 5.
   *
   * @param nLimit
   *        The new limit. Must be &ge; 1.
   * @return this
   */
  @Nonnull
  public TypeaheadDataset setLimit (@Nonnegative final int nLimit)
  {
    ValueEnforcer.isGT0 (nLimit, "Limit");
    m_nLimit = nLimit;
    return this;
  }

  /**
   * @return The max number of suggestions from the dataset to display for a
   *         given query. Defaults to 5.
   */
  @Nonnegative
  public int getLimit ()
  {
    return m_nLimit;
  }

  /**
   * The template used to render suggestions. Can be a string or a precompiled
   * template. If not provided, suggestions will render as their value contained
   * in a <code>&lt;p&gt;</code> element (i.e.
   * <code>&lt;p&gt;value&lt;/p&gt;</code>).
   *
   * @param sTemplate
   *        The String template to use. May be <code>null</code> or empty.
   * @return this
   */
  @Nonnull
  public TypeaheadDataset setTemplate (@Nullable final String sTemplate)
  {
    return setTemplate (StringHelper.hasText (sTemplate) ? JSExpr.lit (sTemplate) : (IJSExpression) null);
  }

  /**
   * The template used to render suggestions. Can be a string or a precompiled
   * template. If not provided, suggestions will render as their value contained
   * in a <code>&lt;p&gt;</code> element (i.e.
   * <code>&lt;p&gt;value&lt;/p&gt;</code>).
   *
   * @param aTemplate
   *        The JS expression to use. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public TypeaheadDataset setTemplate (@Nullable final IJSExpression aTemplate)
  {
    m_aTemplate = aTemplate;
    return this;
  }

  /**
   * @return The template used to render suggestions. Can be a string or a
   *         precompiled template. If not provided, suggestions will render as
   *         their value contained in a <code>&lt;p&gt;</code> element (i.e.
   *         <code>&lt;p&gt;value&lt;/p&gt;</code>).
   */
  @Nullable
  public IJSExpression getTemplate ()
  {
    return m_aTemplate;
  }

  /**
   * The template engine used to compile/render <code>template</code> if it is a
   * string. Any engine can use used as long as it adheres to the expected API.
   * <strong>Required</strong> if <code>template</code> is a string.<br>
   * When you want to use Handlebars as the engine you must include Handlebars
   * and ph-typeahead.js and may specify the name
   * <strong>TypeaheadHandlebars</strong>.
   *
   * @param sEngine
   *        The name of the engine to use. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public TypeaheadDataset setEngine (@Nullable final String sEngine)
  {
    m_sEngine = sEngine;
    return this;
  }

  /**
   * @return The template engine used to compile/render <code>template</code> if
   *         it is a string. Any engine can use used as long as it adheres to
   *         the expected API. <strong>Required</strong> if
   *         <code>template</code> is a string.
   */
  @Nullable
  public String getEngine ()
  {
    return m_sEngine;
  }

  /**
   * The header rendered before suggestions in the dropdown menu. Can be either
   * a DOM element or HTML.
   *
   * @param aHeader
   *        The header to use. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public TypeaheadDataset setHeader (@Nullable final IHCNode aHeader)
  {
    return setHeader (aHeader != null ? HCRenderer.getAsHTMLStringWithoutNamespaces (aHeader) : (String) null);
  }

  /**
   * The header rendered before suggestions in the dropdown menu. Can be either
   * a DOM element or HTML.
   *
   * @param sHeaderHTML
   *        The header to use. May be <code>null</code> or empty.
   * @return this
   */
  @Nonnull
  public TypeaheadDataset setHeader (@Nullable final String sHeaderHTML)
  {
    return setHeader (StringHelper.hasText (sHeaderHTML) ? JSExpr.lit (sHeaderHTML) : (IJSExpression) null);
  }

  /**
   * The header rendered before suggestions in the dropdown menu. Can be either
   * a DOM element or HTML.
   *
   * @param aHeader
   *        The header to use. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public TypeaheadDataset setHeader (@Nullable final IJSExpression aHeader)
  {
    m_aHeader = aHeader;
    return this;
  }

  /**
   * @return The header rendered before suggestions in the dropdown menu. Can be
   *         either a DOM element or HTML.
   */
  @Nullable
  public IJSExpression getHeader ()
  {
    return m_aHeader;
  }

  /**
   * The footer rendered after suggestions in the dropdown menu. Can be either a
   * DOM element or HTML.
   *
   * @param aFooter
   *        The footer to use. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public TypeaheadDataset setFooter (@Nullable final IHCNode aFooter)
  {
    return setFooter (aFooter != null ? HCRenderer.getAsHTMLStringWithoutNamespaces (aFooter) : (String) null);
  }

  /**
   * The footer rendered after suggestions in the dropdown menu. Can be either a
   * DOM element or HTML.
   *
   * @param sFooterHTML
   *        The footer to use. May be <code>null</code> or empty.
   * @return this
   */
  @Nonnull
  public TypeaheadDataset setFooter (@Nullable final String sFooterHTML)
  {
    return setFooter (StringHelper.hasText (sFooterHTML) ? JSExpr.lit (sFooterHTML) : (IJSExpression) null);
  }

  /**
   * The footer rendered after suggestions in the dropdown menu. Can be either a
   * DOM element or HTML.
   *
   * @param aFooter
   *        The footer to use. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public TypeaheadDataset setFooter (@Nullable final IJSExpression aFooter)
  {
    m_aFooter = aFooter;
    return this;
  }

  /**
   * @return The footer rendered after suggestions in the dropdown menu. Can be
   *         either a DOM element or HTML.
   */
  @Nullable
  public IJSExpression getFooter ()
  {
    return m_aFooter;
  }

  @Nonnull
  public TypeaheadDataset setLocal (@Nullable final TypeaheadDatum... aLocal)
  {
    m_aLocal = aLocal == null ? null : CollectionHelper.newList (aLocal);
    return this;
  }

  @Nonnull
  public TypeaheadDataset setLocal (@Nullable final List <? extends TypeaheadDatum> aLocal)
  {
    m_aLocal = aLocal == null ? null : CollectionHelper.newList (aLocal);
    return this;
  }

  @Nullable
  public ICommonsList <? extends TypeaheadDatum> getLocal ()
  {
    return m_aLocal == null ? null : CollectionHelper.newList (m_aLocal);
  }

  /**
   * Can be a URL to a JSON file containing an array of datums or, if more
   * configurability is needed, a prefetch options object.
   *
   * @param aURL
   *        URL to the JSON file. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public TypeaheadDataset setPrefetch (@Nullable final ISimpleURL aURL)
  {
    return setPrefetch (aURL == null ? null : new TypeaheadPrefetch (aURL));
  }

  /**
   * Can be a URL to a JSON file containing an array of datums or, if more
   * configurability is needed, a prefetch options object.
   *
   * @param aPrefetch
   *        Prefetch object. May be <code>null</code>. The object is stored as
   *        is!
   * @return this
   */
  @Nonnull
  public TypeaheadDataset setPrefetch (@Nullable final TypeaheadPrefetch aPrefetch)
  {
    m_aPrefetch = aPrefetch;
    return this;
  }

  /**
   * @return Can be a URL to a JSON file containing an array of datums or, if
   *         more configurability is needed, a prefetch options object.<br>
   *         This returns the internal representation of the object so handle
   *         with care!
   */
  @Nullable
  @ReturnsMutableObject ("Design")
  public TypeaheadPrefetch getPrefetch ()
  {
    return m_aPrefetch;
  }

  /**
   * Can be a URL to fetch suggestions from when the data provided by local and
   * prefetch is insufficient or, if more configurability is needed, a remote
   * options object.
   *
   * @param aRemote
   *        The remote object to use. May be <code>null</code>. The object is
   *        stored as is!
   * @return this
   */
  @Nonnull
  public TypeaheadDataset setRemote (@Nullable final TypeaheadRemote aRemote)
  {
    m_aRemote = aRemote;
    return this;
  }

  /**
   * @return Can be a URL to fetch suggestions from when the data provided by
   *         local and prefetch is insufficient or, if more configurability is
   *         needed, a remote options object.<br>
   *         This returns the internal representation of the object so handle
   *         with care!
   */
  @Nullable
  @ReturnsMutableObject ("design")
  public TypeaheadRemote getRemote ()
  {
    return m_aRemote;
  }

  @Nonnull
  @ReturnsMutableCopy
  public JSAssocArray getAsJSObject ()
  {
    // Consistency checks
    if (m_aTemplate instanceof JSStringLiteral && StringHelper.hasNoText (m_sEngine))
      s_aLogger.warn ("If template is a String, engine must be set!");
    if (m_aLocal == null && m_aPrefetch == null && m_aRemote == null)
      s_aLogger.warn ("Either local, prefetch or remote must be set!");
    if (m_aLocal != null && m_aPrefetch != null)
      s_aLogger.warn ("Only local or prefetch should be used!");
    if (m_aLocal != null && m_aRemote != null)
      s_aLogger.warn ("Only local or remote should be used!");
    if (m_aPrefetch != null && m_aRemote != null)
      s_aLogger.warn ("Only prefetch or remote should be used!");

    // Build result object
    final JSAssocArray ret = new JSAssocArray ().add (JSON_NAME, m_sName);
    if (!m_sValueKey.equals (DEFAULT_VALUE_KEY))
      ret.add (JSON_VALUE_KEY, m_sValueKey);
    if (m_nLimit != DEFAULT_LIMIT)
      ret.add (JSON_LIMIT, m_nLimit);
    if (m_aTemplate != null)
      ret.add (JSON_TEMPLATE, m_aTemplate);
    if (StringHelper.hasText (m_sEngine))
      ret.add (JSON_ENGINE, m_sEngine);
    if (m_aHeader != null)
      ret.add (JSON_HEADER, m_aHeader);
    if (m_aFooter != null)
      ret.add (JSON_FOOTER, m_aFooter);
    if (m_aLocal != null)
    {
      final JSArray aLocal = new JSArray ();
      for (final TypeaheadDatum aDatum : m_aLocal)
        aLocal.add (aDatum.getAsJSObject ());
      ret.add (JSON_LOCAL, aLocal);
    }
    if (m_aPrefetch != null)
      ret.add (JSON_PREFETCH, m_aPrefetch.getAsJSObject ());
    if (m_aRemote != null)
      ret.add (JSON_REMOTE, m_aRemote.getAsJSObject ());
    return ret;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("name", m_sName)
                                       .append ("valueKey", m_sValueKey)
                                       .append ("limit", m_nLimit)
                                       .appendIfNotNull ("template", m_aTemplate)
                                       .appendIfNotNull ("engine", m_sEngine)
                                       .appendIfNotNull ("header", m_aHeader)
                                       .appendIfNotNull ("footer", m_aFooter)
                                       .appendIfNotNull ("local", m_aLocal)
                                       .appendIfNotNull ("prefetch", m_aPrefetch)
                                       .appendIfNotNull ("remote", m_aRemote)
                                       .toString ();
  }
}
