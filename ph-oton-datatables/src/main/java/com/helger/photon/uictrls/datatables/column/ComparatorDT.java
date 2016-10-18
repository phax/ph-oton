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
package com.helger.photon.uictrls.datatables.column;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.compare.CompareHelper;
import com.helger.commons.compare.IComparator;
import com.helger.commons.locale.LocaleParser;
import com.helger.commons.string.StringHelper;
import com.helger.datetime.format.PDTFormatter;
import com.helger.datetime.format.PDTFromString;
import com.helger.masterdata.currency.ECurrency;

/**
 * Marker interface for all data tables Comparators
 *
 * @author Philip Helger
 */
@Immutable
public final class ComparatorDT
{
  private ComparatorDT ()
  {}

  /**
   * Get a IComparator that first formats a cell value (string) (optional) and
   * that converts it to type T using the provided mapper.
   *
   * @param aFormatter
   *        Optional formatter to e.g. remove prefix or suffixes. May be
   *        <code>null</code>.
   * @param aMapper
   *        The mapper to convert from String to a Comparable object. May not be
   *        <code>null</code>.
   * @return A {@link IComparator} that performs the respective comparison.
   */
  @Nonnull
  public static <T extends Comparable <? super T>> IComparator <String> getComparator (@Nullable final Function <? super String, String> aFormatter,
                                                                                       @Nonnull final Function <? super String, T> aMapper)
  {
    Function <? super String, T> aConverter;
    if (aFormatter == null)
    {
      // No formatter needed
      aConverter = aMapper;
    }
    else
    {
      // First apply formatter, than map to String
      aConverter = sCell -> aMapper.apply (sCell == null ? null : aFormatter.apply (sCell));
    }

    return (s1, s2) -> {
      // String to any
      final T aObj1 = aConverter.apply (s1);
      final T aObj2 = aConverter.apply (s2);
      return CompareHelper.compare (aObj1, aObj2);
    };
  }

  @Nonnull
  public static IComparator <String> getComparatorBigDecimal (@Nullable final Function <? super String, String> aFormatter,
                                                              @Nonnull final Locale aDisplayLocale)
  {
    return getComparator (aFormatter,
                          sCellText -> StringHelper.hasNoText (sCellText) ? null
                                                                          : LocaleParser.parseBigDecimal (sCellText,
                                                                                                          aDisplayLocale,
                                                                                                          null));
  }

  @Nonnull
  public static IComparator <String> getComparatorPercentage (@Nonnull final Locale aDisplayLocale)
  {
    return getComparatorBigDecimal (x -> StringHelper.trimEnd (x, "%"), aDisplayLocale);
  }

  @Nonnull
  public static IComparator <String> getComparatorCurrencyFormat (@Nonnull final ECurrency eCurrency)
  {
    return getComparator (null,
                          sCellText -> StringHelper.hasNoText (sCellText) ? null
                                                                          : eCurrency.parseCurrencyFormat (sCellText,
                                                                                                           null));
  }

  @Nonnull
  public static IComparator <String> getComparatorCurrencyValueFormat (@Nonnull final ECurrency eCurrency)
  {
    return getComparator (null,
                          sCellText -> StringHelper.hasNoText (sCellText) ? null
                                                                          : eCurrency.parseValueFormat (sCellText,
                                                                                                        null));
  }

  @Nonnull
  public static IComparator <String> getComparatorBigInteger (@Nullable final Function <? super String, String> aFormatter,
                                                              @Nonnull final Locale aDisplayLocale)
  {
    return getComparator (aFormatter,
                          sCellText -> StringHelper.hasNoText (sCellText) ? null
                                                                          : LocaleParser.parseBigDecimal (sCellText,
                                                                                                          aDisplayLocale,
                                                                                                          BigDecimal.ZERO)
                                                                                        .toBigIntegerExact ());
  }

  @Nonnull
  public static IComparator <String> getComparatorDate (@Nullable final Function <? super String, String> aFormatter,
                                                        @Nonnull final Locale aDisplayLocale)
  {
    return getComparatorDate (aFormatter, PDTFormatter.getDefaultFormatterDate (aDisplayLocale));
  }

  @Nonnull
  public static IComparator <String> getComparatorDate (@Nullable final Function <? super String, String> aFormatter,
                                                        @Nonnull final DateTimeFormatter aDTFormatter)
  {
    return getComparator (aFormatter,
                          sCellText -> StringHelper.hasNoText (sCellText) ? null
                                                                          : ValueEnforcer.notNull (PDTFromString.getLocalDateFromString (sCellText,
                                                                                                                                         aDTFormatter),
                                                                                                   () -> "Failed to parse date '" +
                                                                                                         sCellText +
                                                                                                         "' using formatter " +
                                                                                                         aDTFormatter));
  }

  @Nonnull
  public static IComparator <String> getComparatorTime (@Nullable final Function <? super String, String> aFormatter,
                                                        @Nonnull final Locale aDisplayLocale)
  {
    return getComparatorTime (aFormatter, PDTFormatter.getDefaultFormatterTime (aDisplayLocale));
  }

  @Nonnull
  public static IComparator <String> getComparatorTime (@Nullable final Function <? super String, String> aFormatter,
                                                        @Nonnull final DateTimeFormatter aDTFormatter)
  {
    return getComparator (aFormatter,
                          sCellText -> StringHelper.hasNoText (sCellText) ? null
                                                                          : ValueEnforcer.notNull (PDTFromString.getLocalTimeFromString (sCellText,
                                                                                                                                         aDTFormatter),
                                                                                                   () -> "Failed to parse time '" +
                                                                                                         sCellText +
                                                                                                         "' with formatter " +
                                                                                                         aDTFormatter));
  }

  @Nonnull
  public static IComparator <String> getComparatorDateTime (@Nullable final Function <? super String, String> aFormatter,
                                                            @Nonnull final Locale aDisplayLocale)
  {
    return getComparatorDateTime (aFormatter, PDTFormatter.getDefaultFormatterDateTime (aDisplayLocale));
  }

  @Nonnull
  public static IComparator <String> getComparatorDateTime (@Nullable final Function <? super String, String> aFormatter,
                                                            @Nonnull final DateTimeFormatter aDTFormatter)
  {
    return getComparator (aFormatter,
                          sCellText -> StringHelper.hasNoText (sCellText) ? null
                                                                          : ValueEnforcer.notNull (PDTFromString.getLocalDateTimeFromString (sCellText,
                                                                                                                                             aDTFormatter),
                                                                                                   () -> "Failed to parse datetime '" +
                                                                                                         sCellText +
                                                                                                         "' with formatter " +
                                                                                                         aDTFormatter));
  }

  @Nonnull
  public static IComparator <String> getComparatorDuration (@Nullable final Function <? super String, String> aFormatter)
  {
    return getComparator (aFormatter,
                          sCellText -> StringHelper.hasNoText (sCellText) ? null
                                                                          : ValueEnforcer.notNull (PDTFromString.getDurationFromString (sCellText),
                                                                                                   () -> "Failed to parse duration '" +
                                                                                                         sCellText +
                                                                                                         "'"));
  }

  @Nonnull
  public static Comparator <String> getComparatorString (@Nullable final Function <? super String, String> aFormatter,
                                                         @Nonnull final Locale aDisplayLocale)
  {
    if (aFormatter == null)
      return IComparator.getComparatorCollating (aDisplayLocale);
    return IComparator.getComparatorCollating (sCell -> sCell == null ? null : aFormatter.apply (sCell),
                                               aDisplayLocale);
  }

  @Nonnull
  public static Comparator <String> getComparatorInt (@Nullable final Function <? super String, String> aFormatter,
                                                      @Nonnull final Locale aDisplayLocale)
  {
    /*
     * Ensure that columns without text are sorted consistently compared to the
     * ones with non-numeric content
     */
    final ToIntFunction <String> aMapper = sCellText -> StringHelper.hasNoText (sCellText) ? Integer.MIN_VALUE
                                                                                           : LocaleParser.parseInt (sCellText,
                                                                                                                    aDisplayLocale,
                                                                                                                    0);
    if (aFormatter == null)
      return Comparator.comparingInt (sCell -> aMapper.applyAsInt (sCell));
    return Comparator.comparingInt (sCell -> aMapper.applyAsInt (sCell == null ? null : aFormatter.apply (sCell)));
  }

  @Nonnull
  public static Comparator <String> getComparatorLong (@Nullable final Function <? super String, String> aFormatter,
                                                       @Nonnull final Locale aDisplayLocale)
  {
    /*
     * Ensure that columns without text are sorted consistently compared to the
     * ones with non-numeric content
     */
    final ToLongFunction <String> aMapper = sCellText -> StringHelper.hasNoText (sCellText) ? Long.MIN_VALUE
                                                                                            : LocaleParser.parseLong (sCellText,
                                                                                                                      aDisplayLocale,
                                                                                                                      0L);
    if (aFormatter == null)
      return Comparator.comparingLong (sCell -> aMapper.applyAsLong (sCell));
    return Comparator.comparingLong (sCell -> aMapper.applyAsLong (sCell == null ? null : aFormatter.apply (sCell)));
  }

  @Nonnull
  public static Comparator <String> getComparatorDouble (@Nullable final Function <? super String, String> aFormatter,
                                                         @Nonnull final Locale aDisplayLocale)
  {
    /*
     * Ensure that columns without text are sorted consistently compared to the
     * ones with non-numeric content
     */
    final ToDoubleFunction <String> aMapper = sCellText -> StringHelper.hasNoText (sCellText) ? Double.MIN_VALUE
                                                                                              : LocaleParser.parseDouble (sCellText,
                                                                                                                          aDisplayLocale,
                                                                                                                          0L);
    if (aFormatter == null)
      return Comparator.comparingDouble (sCell -> aMapper.applyAsDouble (sCell));
    return Comparator.comparingDouble (sCell -> aMapper.applyAsDouble (sCell == null ? null
                                                                                     : aFormatter.apply (sCell)));
  }
}
