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
import java.math.BigInteger;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.compare.IComparator;
import com.helger.commons.format.FormatterStringSkipSuffix;
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
public final class ComparatorDT
{
  @Nonnull
  public static <T extends Comparable <? super T>> Comparator <String> getComparator (@Nullable final Function <? super String, String> aFormatter,
                                                                                      @Nonnull final Function <? super String, T> aMapper)
  {
    return Comparator.comparing (aFormatter == null ? sCell -> aMapper.apply (StringHelper.getNotNull (sCell))
                                                    : sCell -> aMapper.apply (sCell == null ? ""
                                                                                            : aFormatter.apply (sCell)));
  }

  @Nonnull
  public static Comparator <String> getComparatorBigDecimal (@Nullable final Function <? super String, String> aFormatter,
                                                             @Nonnull final Locale aDisplayLocale)
  {
    return getComparator (aFormatter,
                          sCellText -> sCellText.isEmpty () ? BigDecimal.ZERO
                                                            : LocaleParser.parseBigDecimal (sCellText,
                                                                                            aDisplayLocale,
                                                                                            BigDecimal.ZERO));
  }

  @Nonnull
  public static Comparator <String> getComparatorPercentage (@Nonnull final Locale aDisplayLocale)
  {
    return getComparatorBigDecimal (new FormatterStringSkipSuffix ("%"), aDisplayLocale);
  }

  @Nonnull
  public static Comparator <String> getComparatorCurrencyFormat (@Nonnull final ECurrency eCurrency)
  {
    return getComparator (null,
                          sCellText -> sCellText.isEmpty () ? BigDecimal.ZERO
                                                            : eCurrency.parseCurrencyFormat (sCellText,
                                                                                             BigDecimal.ZERO));
  }

  @Nonnull
  public static Comparator <String> getComparatorCurrencyValueFormat (@Nonnull final ECurrency eCurrency)
  {
    return getComparator (null,
                          sCellText -> sCellText.isEmpty () ? BigDecimal.ZERO
                                                            : eCurrency.parseValueFormat (sCellText, BigDecimal.ZERO));
  }

  @Nonnull
  public static Comparator <String> getComparatorBigInteger (@Nullable final Function <? super String, String> aFormatter,
                                                             @Nonnull final Locale aDisplayLocale)
  {
    return getComparator (aFormatter,
                          sCellText -> sCellText.isEmpty () ? BigInteger.ZERO
                                                            : LocaleParser.parseBigDecimal (sCellText,
                                                                                            aDisplayLocale,
                                                                                            BigDecimal.ZERO)
                                                                          .toBigIntegerExact ());
  }

  @Nonnull
  public static Comparator <String> getComparatorDate (@Nullable final Function <? super String, String> aFormatter,
                                                       @Nonnull final Locale aDisplayLocale)
  {
    return getComparatorDate (aFormatter, PDTFormatter.getDefaultFormatterDate (aDisplayLocale));
  }

  @Nonnull
  public static Comparator <String> getComparatorDate (@Nullable final Function <? super String, String> aFormatter,
                                                       @Nonnull final DateTimeFormatter aDTFormatter)
  {
    return getComparator (aFormatter, sCellText -> PDTFromString.getLocalDateFromString (sCellText, aDTFormatter));
  }

  @Nonnull
  public static Comparator <String> getComparatorTime (@Nullable final Function <? super String, String> aFormatter,
                                                       @Nonnull final Locale aDisplayLocale)
  {
    return getComparatorTime (aFormatter, PDTFormatter.getDefaultFormatterTime (aDisplayLocale));
  }

  @Nonnull
  public static Comparator <String> getComparatorTime (@Nullable final Function <? super String, String> aFormatter,
                                                       @Nonnull final DateTimeFormatter aDTFormatter)
  {
    return getComparator (aFormatter, sCellText -> PDTFromString.getLocalTimeFromString (sCellText, aDTFormatter));
  }

  @Nonnull
  public static Comparator <String> getComparatorDateTime (@Nullable final Function <? super String, String> aFormatter,
                                                           @Nonnull final Locale aDisplayLocale)
  {
    return getComparatorDateTime (aFormatter, PDTFormatter.getDefaultFormatterDateTime (aDisplayLocale));
  }

  @Nonnull
  public static Comparator <String> getComparatorDateTime (@Nullable final Function <? super String, String> aFormatter,
                                                           @Nonnull final DateTimeFormatter aDTFormatter)
  {
    return getComparator (aFormatter, sCellText -> PDTFromString.getLocalDateTimeFromString (sCellText, aDTFormatter));
  }

  @Nonnull
  public static Comparator <String> getComparatorDuration (@Nullable final Function <? super String, String> aFormatter)
  {
    return getComparator (aFormatter, Duration::parse);
  }

  @Nonnull
  public static Comparator <String> getComparatorString (@Nullable final Function <? super String, String> aFormatter,
                                                         @Nonnull final Locale aDisplayLocale)
  {
    return IComparator.getComparatorCollating (aFormatter == null ? sCell -> StringHelper.getNotNull (sCell)
                                                                              : sCell -> sCell == null ? ""
                                                                                                       : aFormatter.apply (sCell),
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
    final ToIntFunction <String> aMapper = sCellText -> sCellText.isEmpty () ? Integer.MIN_VALUE
                                                                             : LocaleParser.parseInt (sCellText,
                                                                                                      aDisplayLocale,
                                                                                                      0);
    return Comparator.comparingInt (aFormatter == null ? sCell -> aMapper.applyAsInt (StringHelper.getNotNull (sCell))
                                                       : sCell -> aMapper.applyAsInt (sCell == null ? ""
                                                                                                    : aFormatter.apply (sCell)));
  }

  @Nonnull
  public static Comparator <String> getComparatorLong (@Nullable final Function <? super String, String> aFormatter,
                                                       @Nonnull final Locale aDisplayLocale)
  {
    /*
     * Ensure that columns without text are sorted consistently compared to the
     * ones with non-numeric content
     */
    final ToLongFunction <String> aMapper = sCellText -> sCellText.isEmpty () ? Long.MIN_VALUE
                                                                              : LocaleParser.parseLong (sCellText,
                                                                                                        aDisplayLocale,
                                                                                                        0L);
    return Comparator.comparingLong (aFormatter == null ? sCell -> aMapper.applyAsLong (StringHelper.getNotNull (sCell))
                                                        : sCell -> aMapper.applyAsLong (sCell == null ? ""
                                                                                                      : aFormatter.apply (sCell)));
  }

  @Nonnull
  public static Comparator <String> getComparatorDouble (@Nullable final Function <? super String, String> aFormatter,
                                                         @Nonnull final Locale aDisplayLocale)
  {
    /*
     * Ensure that columns without text are sorted consistently compared to the
     * ones with non-numeric content
     */
    final ToDoubleFunction <String> aMapper = sCellText -> sCellText.isEmpty () ? Double.MIN_VALUE
                                                                                : LocaleParser.parseDouble (sCellText,
                                                                                                            aDisplayLocale,
                                                                                                            0L);
    return Comparator.comparingDouble (aFormatter == null ? sCell -> aMapper.applyAsDouble (StringHelper.getNotNull (sCell))
                                                          : sCell -> aMapper.applyAsDouble (sCell == null ? ""
                                                                                                          : aFormatter.apply (sCell)));
  }
}
