/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.core.paging;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonnegative;
import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.array.ArrayHelper;
import com.helger.base.compare.CompareHelper;
import com.helger.base.compare.ESortOrder;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.reflection.GenericReflection;
import com.helger.base.string.StringHelper;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.paging.IPagingSpec;
import com.helger.collection.paging.PagingHelper;
import com.helger.collection.paging.SortField;

/**
 * Helper class to resolve the {@link SortField}s of an {@link IPagingSpec} and the global search
 * text onto the {@link ITableColumn}s of a domain object. The methods taking a list of all domain
 * objects are the in-memory implementation of a paged, sorted and filtered query - they are the
 * reference behaviour every native data store implementation must comply to.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
public final class TableColumnHelper
{
  private static final Logger LOGGER = LoggerFactory.getLogger (TableColumnHelper.class);

  private TableColumnHelper ()
  {}

  /**
   * Find the column with the provided ID.
   *
   * @param <DATATYPE>
   *        The domain object type
   * @param aColumns
   *        All available columns. May not be <code>null</code>.
   * @param sFieldName
   *        The field name to search. May be <code>null</code>. This value is usually provided by a
   *        client and must therefore be treated as untrusted input.
   * @return <code>null</code> if no such column exists.
   */
  @Nullable
  public static <DATATYPE> ITableColumn <DATATYPE> findColumn (@NonNull final ITableColumn <DATATYPE> [] aColumns,
                                                               @Nullable final String sFieldName)
  {
    ValueEnforcer.notNull (aColumns, "Columns");

    if (StringHelper.isNotEmpty (sFieldName))
      for (final ITableColumn <DATATYPE> aColumn : aColumns)
        if (aColumn.getID ().equals (sFieldName))
          return aColumn;
    return null;
  }

  /**
   * Get the columns that form the default order of the provided domain object, in the order of
   * precedence.
   *
   * @param <DATATYPE>
   *        The domain object type
   * @param aColumns
   *        All available columns. May not be <code>null</code>.
   * @return A non-<code>null</code> but maybe empty list.
   * @see ITableColumn#getDefaultSortOrder()
   */
  @NonNull
  @ReturnsMutableCopy
  public static <DATATYPE> ICommonsList <SortColumn <DATATYPE>> getAllDefaultSortColumns (@NonNull final ITableColumn <DATATYPE> [] aColumns)
  {
    ValueEnforcer.notNull (aColumns, "Columns");

    final ICommonsList <SortColumn <DATATYPE>> ret = new CommonsArrayList <> ();
    for (final ITableColumn <DATATYPE> aColumn : aColumns)
    {
      final ESortOrder eSortOrder = aColumn.getDefaultSortOrder ();
      if (eSortOrder != null)
      {
        if (!aColumn.isSortable ())
        {
          LOGGER.error ("The column '" +
                        aColumn.getID () +
                        "' is part of the default order but is not sortable - ignoring it");
          continue;
        }
        ret.add (new SortColumn <> (aColumn, eSortOrder));
      }
    }

    if (ret.isEmpty ())
      LOGGER.error ("None of the provided columns declares a default sort order. Paging will therefore return the rows of a page in an undefined order.");
    return ret;
  }

  /**
   * Get the default order of the provided domain object as data store independent sort fields.
   *
   * @param aColumns
   *        All available columns. May not be <code>null</code>.
   * @return A non-<code>null</code> but maybe empty list.
   * @see #getAllDefaultSortColumns(ITableColumn[])
   */
  @NonNull
  @ReturnsMutableCopy
  public static ICommonsList <SortField> getAllDefaultSortFields (@NonNull final ITableColumn <?> [] aColumns)
  {
    final ICommonsList <SortField> ret = new CommonsArrayList <> ();
    for (final SortColumn <?> aSortColumn : getAllDefaultSortColumns (GenericReflection.uncheckedCast (aColumns)))
      ret.add (new SortField (aSortColumn.getColumn ().getID (), aSortColumn.getSortOrder ()));
    return ret;
  }

  /**
   * Get all sortable columns of the provided paging specification, in the order of precedence.
   * Field names that are unknown or that refer to a non-sortable column are ignored, because they
   * are provided by a client. If nothing remains, the default order is used.
   *
   * @param <DATATYPE>
   *        The domain object type
   * @param aColumns
   *        All available columns. May not be <code>null</code>.
   * @param aPagingSpec
   *        The paging specification to be resolved. May not be <code>null</code>.
   * @return A non-<code>null</code> but maybe empty list, with the matching sort order per column.
   */
  @NonNull
  @ReturnsMutableCopy
  public static <DATATYPE> ICommonsList <SortColumn <DATATYPE>> getAllSortColumns (@NonNull final ITableColumn <DATATYPE> [] aColumns,
                                                                                   @NonNull final IPagingSpec aPagingSpec)
  {
    ValueEnforcer.notNull (aColumns, "Columns");
    ValueEnforcer.notNull (aPagingSpec, "PagingSpec");

    final ICommonsList <SortColumn <DATATYPE>> ret = new CommonsArrayList <> ();
    for (final SortField aSortField : aPagingSpec.getAllSortFields ())
    {
      final ITableColumn <DATATYPE> aColumn = findColumn (aColumns, aSortField.getFieldName ());
      if (aColumn == null || !aColumn.isSortable ())
      {
        LOGGER.warn ("Ignoring the unknown or non-sortable sort field '" + aSortField.getFieldName () + "'");
        continue;
      }
      ret.add (new SortColumn <> (aColumn, aSortField.getSortOrder ()));
    }

    if (ret.isEmpty ())
    {
      // Paging without a deterministic order returns arbitrary rows, so the explicitly declared
      // default order is used. All data stores use this same fallback.
      ret.addAll (getAllDefaultSortColumns (aColumns));
    }
    return ret;
  }

  /**
   * Create the comparator that sorts by the value of the provided value provider, ascending,
   * <code>null</code> values first.
   *
   * @param <DATATYPE>
   *        The domain object type
   * @param aValueProvider
   *        The provider of the value to sort by. May not be <code>null</code>.
   * @return Never <code>null</code>.
   */
  @NonNull
  public static <DATATYPE> Comparator <DATATYPE> createComparator (@NonNull final Function <DATATYPE, String> aValueProvider)
  {
    ValueEnforcer.notNull (aValueProvider, "ValueProvider");
    return (x, y) -> CompareHelper.compare (aValueProvider.apply (x), aValueProvider.apply (y), true);
  }

  /**
   * Create the comparator for the sort fields of the provided paging specification, for the
   * in-memory data stores.
   *
   * @param <DATATYPE>
   *        The domain object type
   * @param aColumns
   *        All available columns. May not be <code>null</code>.
   * @param aPagingSpec
   *        The paging specification to be resolved. May not be <code>null</code>.
   * @return <code>null</code> if no sort field could be resolved.
   */
  @Nullable
  public static <DATATYPE> Comparator <DATATYPE> getComparator (@NonNull final ITableColumn <DATATYPE> [] aColumns,
                                                                @NonNull final IPagingSpec aPagingSpec)
  {
    Comparator <DATATYPE> ret = null;
    for (final SortColumn <DATATYPE> aSortColumn : getAllSortColumns (aColumns, aPagingSpec))
    {
      final Comparator <DATATYPE> aColumnComparator = aSortColumn.getComparator ();
      ret = ret == null ? aColumnComparator : ret.thenComparing (aColumnComparator);
    }
    return ret;
  }

  /**
   * Create the predicate that matches the provided search text against all searchable columns, for
   * the in-memory data stores. The whole search text must be contained in any searchable column.
   *
   * @param <DATATYPE>
   *        The domain object type
   * @param aColumns
   *        All available columns. May not be <code>null</code>.
   * @param sSearchText
   *        The search text to be applied. May be <code>null</code> or empty.
   * @return <code>null</code> if no filtering is to take place.
   */
  @Nullable
  public static <DATATYPE> Predicate <DATATYPE> getSearchPredicate (@NonNull final ITableColumn <DATATYPE> [] aColumns,
                                                                    @Nullable final String sSearchText)
  {
    return getSearchPredicate (aColumns, StringHelper.isEmpty (sSearchText) ? null : new String [] { sSearchText });
  }

  /**
   * Create the predicate that matches the provided search texts against all searchable columns, for
   * the in-memory data stores. A domain object matches, if <b>every</b> search text is contained in
   * <b>any</b> searchable column - this is the "all terms per row" behaviour of the DataTables
   * server side filtering.
   *
   * @param <DATATYPE>
   *        The domain object type
   * @param aColumns
   *        All available columns. May not be <code>null</code>.
   * @param aSearchTexts
   *        The search texts to be applied. May be <code>null</code> or empty.
   * @return <code>null</code> if no filtering is to take place.
   */
  @Nullable
  public static <DATATYPE> Predicate <DATATYPE> getSearchPredicate (@NonNull final ITableColumn <DATATYPE> [] aColumns,
                                                                    final String @Nullable [] aSearchTexts)
  {
    ValueEnforcer.notNull (aColumns, "Columns");

    if (ArrayHelper.isEmpty (aSearchTexts))
      return null;

    Predicate <DATATYPE> ret = null;
    for (final String sSearchText : aSearchTexts)
    {
      if (StringHelper.isEmpty (sSearchText))
        continue;

      // Any searchable column must contain this single search text
      Predicate <DATATYPE> aTermPredicate = null;
      for (final ITableColumn <DATATYPE> aColumn : aColumns)
        if (aColumn.isSearchable ())
        {
          final Predicate <DATATYPE> aColumnPredicate = x -> aColumn.matchesSearchText (x, sSearchText);
          aTermPredicate = aTermPredicate == null ? aColumnPredicate : aTermPredicate.or (aColumnPredicate);
        }

      if (aTermPredicate == null)
      {
        // No searchable column at all - don't silently return everything
        LOGGER.warn ("None of the provided columns is searchable, so the search text is ignored");
        return null;
      }

      // All search texts must match
      ret = ret == null ? aTermPredicate : ret.and (aTermPredicate);
    }
    return ret;
  }

  /**
   * The in-memory implementation of a paged, sorted and filtered query.
   *
   * @param <DATATYPE>
   *        The domain object type
   * @param aColumns
   *        All available columns. May not be <code>null</code>.
   * @param aAll
   *        All available domain objects. May not be <code>null</code>. The caller must pass a list
   *        it owns, because it may be sorted in place.
   * @param aPagingSpec
   *        The paging specification to be applied. May not be <code>null</code>.
   * @param sSearchText
   *        The global search text to be applied. May be <code>null</code> or empty.
   * @return A non-<code>null</code> but maybe empty list.
   */
  @NonNull
  @ReturnsMutableCopy
  public static <DATATYPE> ICommonsList <DATATYPE> getPage (@NonNull final ITableColumn <DATATYPE> [] aColumns,
                                                            @NonNull final ICommonsList <DATATYPE> aAll,
                                                            @NonNull final IPagingSpec aPagingSpec,
                                                            @Nullable final String sSearchText)
  {
    return getPage (aColumns,
                    aAll,
                    aPagingSpec,
                    StringHelper.isEmpty (sSearchText) ? null : new String [] { sSearchText });
  }

  /**
   * The in-memory implementation of a paged, sorted and filtered query.
   *
   * @param <DATATYPE>
   *        The domain object type
   * @param aColumns
   *        All available columns. May not be <code>null</code>.
   * @param aAll
   *        All available domain objects. May not be <code>null</code>. The caller must pass a list
   *        it owns, because it may be sorted in place.
   * @param aPagingSpec
   *        The paging specification to be applied. May not be <code>null</code>.
   * @param aSearchTexts
   *        The global search texts to be applied. May be <code>null</code> or empty.
   * @return A non-<code>null</code> but maybe empty list.
   */
  @NonNull
  @ReturnsMutableCopy
  public static <DATATYPE> ICommonsList <DATATYPE> getPage (@NonNull final ITableColumn <DATATYPE> [] aColumns,
                                                            @NonNull final ICommonsList <DATATYPE> aAll,
                                                            @NonNull final IPagingSpec aPagingSpec,
                                                            final String @Nullable [] aSearchTexts)
  {
    ValueEnforcer.notNull (aAll, "All");
    ValueEnforcer.notNull (aPagingSpec, "PagingSpec");

    if (aPagingSpec.isEmptyPage ())
      return new CommonsArrayList <> ();

    final Predicate <DATATYPE> aFilter = getSearchPredicate (aColumns, aSearchTexts);
    final ICommonsList <DATATYPE> aMatching = aFilter == null ? aAll : aAll.getAll (aFilter);
    // No copy needed - the caller owns the list
    final boolean bCopyList = false;
    return PagingHelper.getPage (aMatching, bCopyList, aPagingSpec, getComparator (aColumns, aPagingSpec));
  }

  /**
   * The in-memory implementation of counting the domain objects matching a search text.
   *
   * @param <DATATYPE>
   *        The domain object type
   * @param aColumns
   *        All available columns. May not be <code>null</code>.
   * @param aAll
   *        All available domain objects. May not be <code>null</code>.
   * @param sSearchText
   *        The global search text to be applied. May be <code>null</code> or empty.
   * @return The number of matching domain objects. Always &ge; 0.
   */
  @Nonnegative
  public static <DATATYPE> long getCount (@NonNull final ITableColumn <DATATYPE> [] aColumns,
                                          @NonNull final ICommonsList <DATATYPE> aAll,
                                          @Nullable final String sSearchText)
  {
    return getCount (aColumns, aAll, StringHelper.isEmpty (sSearchText) ? null : new String [] { sSearchText });
  }

  /**
   * The in-memory implementation of counting the domain objects matching the search texts.
   *
   * @param <DATATYPE>
   *        The domain object type
   * @param aColumns
   *        All available columns. May not be <code>null</code>.
   * @param aAll
   *        All available domain objects. May not be <code>null</code>.
   * @param aSearchTexts
   *        The global search texts to be applied. May be <code>null</code> or empty.
   * @return The number of matching domain objects. Always &ge; 0.
   */
  @Nonnegative
  public static <DATATYPE> long getCount (@NonNull final ITableColumn <DATATYPE> [] aColumns,
                                          @NonNull final ICommonsList <DATATYPE> aAll,
                                          final String @Nullable [] aSearchTexts)
  {
    ValueEnforcer.notNull (aAll, "All");

    final Predicate <DATATYPE> aFilter = getSearchPredicate (aColumns, aSearchTexts);
    return aFilter == null ? aAll.size () : aAll.getCount (aFilter);
  }
}
