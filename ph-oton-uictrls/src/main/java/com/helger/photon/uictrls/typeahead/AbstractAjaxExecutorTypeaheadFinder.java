/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.json.IJsonArray;
import com.helger.json.JsonArray;
import com.helger.photon.app.PhotonUnifiedResponse;
import com.helger.photon.core.ajax.executor.AbstractAjaxExecutorWithContext;
import com.helger.photon.core.app.context.ILayoutExecutionContext;

/**
 * Abstract AJAX handler that can be used as the source for a Bootstrap
 * typeahead control.
 *
 * @author Philip Helger
 * @param <LECTYPE>
 *        Layout execution context
 */
public abstract class AbstractAjaxExecutorTypeaheadFinder <LECTYPE extends ILayoutExecutionContext> extends
                                                          AbstractAjaxExecutorWithContext <LECTYPE>
{
  public static final String PARAM_QUERY = "query";

  /**
   * A simple finder
   *
   * @author Philip Helger
   */
  @NotThreadSafe
  public static class Finder implements Serializable
  {
    private final Locale m_aSortLocale;
    private String [] m_aSearchTerms;

    /**
     * Constructor
     *
     * @param aSortLocale
     *        The sort locale to use. May not be <code>null</code>.
     */
    protected Finder (@Nonnull final Locale aSortLocale)
    {
      m_aSortLocale = ValueEnforcer.notNull (aSortLocale, "SortLocale");
    }

    /**
     * Initialization method.
     *
     * @param sSearchTerms
     *        The search term string. It is internally separated into multiple
     *        tokens by using a "\s+" regular expression.
     * @return this
     */
    @Nonnull
    protected Finder initialize (@Nonnull @Nonempty final String sSearchTerms)
    {
      if (StringHelper.hasNoTextAfterTrim (sSearchTerms))
        throw new IllegalArgumentException ("SearchTerms");

      // Split search terms by white spaces
      m_aSearchTerms = RegExHelper.getSplitToArray (sSearchTerms.trim (), "\\s+");
      if (m_aSearchTerms.length == 0)
        throw new IllegalStateException ("Weird - splitting of '" + sSearchTerms.trim () + "' failed!");
      for (final String sSearchTerm : m_aSearchTerms)
        if (sSearchTerm.length () == 0)
          throw new IllegalArgumentException ("Weird - empty search term present!");
      return this;
    }

    /**
     * @return An array with all search terms. Never <code>null</code> nor
     *         empty.
     */
    @Nonnull
    @ReturnsMutableCopy
    @Nonempty
    public final String [] getAllSearchTerms ()
    {
      return ArrayHelper.getCopy (m_aSearchTerms);
    }

    /**
     * @return The sort locale provided in the constructor. Never
     *         <code>null</code>.
     */
    @Nonnull
    public final Locale getSortLocale ()
    {
      return m_aSortLocale;
    }

    /**
     * Default matches function: match all search terms!
     *
     * @param sSource
     *        Source string. May be <code>null</code>.
     * @return <code>true</code> if the source is not <code>null</code> and if
     *         all search terms are contained, <code>false</code> otherwise.
     * @see #matchesAll(String)
     */
    public boolean matches (@Nullable final String sSource)
    {
      return matchesAll (sSource);
    }

    /**
     * Match method for a single string. By default a case-insensitive lookup is
     * performed.
     *
     * @param sSource
     *        The source string to search the search term in. Never
     *        <code>null</code>.
     * @param sSearchTerm
     *        The search term to be searched. Never <code>null</code>.
     * @return <code>true</code> if the source string contains the search term,
     *         <code>false</code> otherwise.
     */
    @OverrideOnDemand
    protected boolean isSingleStringMatching (@Nonnull final String sSource, @Nonnull final String sSearchTerm)
    {
      return StringHelper.containsIgnoreCase (sSource, sSearchTerm, m_aSortLocale);
    }

    /**
     * Check if the passed source string matches all query terms.
     *
     * @param sSource
     *        Source string. May be <code>null</code>.
     * @return <code>true</code> if the source is not <code>null</code> and if
     *         all search terms are contained, <code>false</code> otherwise.
     */
    public boolean matchesAll (@Nullable final String sSource)
    {
      if (sSource == null)
        return false;
      for (final String sSearchTerm : m_aSearchTerms)
        if (!isSingleStringMatching (sSource, sSearchTerm))
          return false;
      return true;
    }

    /**
     * Check if the passed source string matches at least one query term.
     *
     * @param sSource
     *        Source string. May be <code>null</code>.
     * @return <code>true</code> if the source is not <code>null</code> and if
     *         one search term is contained, <code>false</code> otherwise.
     */
    public boolean matchesAny (@Nullable final String sSource)
    {
      if (sSource == null)
        return false;
      for (final String sSearchTerm : m_aSearchTerms)
        if (isSingleStringMatching (sSource, sSearchTerm))
          return true;
      return false;
    }

    @Override
    public String toString ()
    {
      return new ToStringGenerator (this).append ("searchTerms", m_aSearchTerms)
                                         .append ("sortLocale", m_aSortLocale)
                                         .getToString ();
    }
  }

  private boolean m_bAddDatumCount = true;

  protected AbstractAjaxExecutorTypeaheadFinder ()
  {}

  /**
   * Get the provided query string from the parameter map. By default the value
   * of parameter {@link #PARAM_QUERY} is used.
   *
   * @param aLEC
   *        The layout execution context.
   * @return <code>null</code> if no such request parameter is present.
   */
  @Nullable
  @OverrideOnDemand
  protected String getQueryString (@Nonnull final LECTYPE aLEC)
  {
    return aLEC.params ().getAsString (PARAM_QUERY);
  }

  /**
   * Create a new {@link Finder} object.
   *
   * @param sOriginalQuery
   *        The original query string. Never <code>null</code>.
   * @param aLEC
   *        The layout execution context. Never <code>null</code>.
   * @return The non-<code>null</code> {@link Finder} object.
   */
  @Nonnull
  @OverrideOnDemand
  protected Finder createFinder (@Nonnull final String sOriginalQuery, @Nonnull final LECTYPE aLEC)
  {
    return new Finder (aLEC.getDisplayLocale ()).initialize (sOriginalQuery);
  }

  /**
   * This is the main searcher method. It must filter all objects matching the
   * criteria in the finder.
   *
   * @param aFinder
   *        The finder. Never <code>null</code>.
   * @param aLEC
   *        The layout execution context. Never <code>null</code>.
   * @return A non-<code>null</code> list with all datums to use.
   */
  @Nonnull
  protected abstract ICommonsList <? extends TypeaheadDatum> getAllMatchingDatums (@Nonnull Finder aFinder,
                                                                                   @Nonnull LECTYPE aLEC);

  /**
   * @return <code>true</code> if the default suffix "[x of y]" should not be
   *         added to the displayname!
   */
  public final boolean isAddDatumCount ()
  {
    return m_bAddDatumCount;
  }

  public final void setAddDatumCount (final boolean bAddDatumCount)
  {
    m_bAddDatumCount = bAddDatumCount;
  }

  @Override
  protected void mainHandleRequest (@Nonnull final LECTYPE aLEC,
                                    @Nonnull final PhotonUnifiedResponse aAjaxResponse) throws Exception
  {
    final String sOriginalQuery = getQueryString (aLEC);
    if (StringHelper.hasNoTextAfterTrim (sOriginalQuery))
    {
      // May happen when the user enters " " (only spaces)
      aAjaxResponse.jsonEmpty ();
      return;
    }

    // Create the main Finder object
    final Finder aFinder = createFinder (sOriginalQuery, aLEC);

    // Map from ID to name
    final ICommonsList <? extends TypeaheadDatum> aMatchingDatums = getAllMatchingDatums (aFinder, aLEC);

    if (m_bAddDatumCount)
    {
      // Add "x of y" to the end
      final int nDatums = aMatchingDatums.size ();
      if (nDatums > 1)
      {
        final Locale aDisplayLocale = aLEC.getDisplayLocale ();
        final String sMax = Integer.toString (nDatums);
        int nIndex = 0;
        for (final TypeaheadDatum aDatum : aMatchingDatums)
        {
          nIndex++;
          final String sText = ETypeaheadText.DATUM_INDEX.getDisplayTextWithArgs (aDisplayLocale,
                                                                                  Integer.toString (nIndex),
                                                                                  sMax);
          aDatum.setValue (aDatum.getValue () + " " + sText);
          aDatum.tokens ().addAll (TypeaheadDatum.getTokensFromValue (sText));
        }
      }
    }

    // Convert to JSON, sorted by display name using the current display locale
    final IJsonArray ret = new JsonArray ().addAllMapped (aMatchingDatums, TypeaheadDatum::getAsJson);

    // Use the simple response, because the response layout is predefined!
    aAjaxResponse.json (ret);
  }
}
