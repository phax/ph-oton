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
package com.helger.photon.uictrls.datatables.column;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.datetime.format.EDTFormatterMode;
import com.helger.datetime.format.PDTFormatter;
import com.helger.datetime.format.PDTFromString;
import com.helger.masterdata.currency.CurrencyHelper;
import com.helger.masterdata.currency.ECurrency;
import com.helger.text.locale.LocaleParser;

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

  @NonNull
  public static IComparableExtractor <BigDecimal> getExtractorBigDecimal (@NonNull final Locale aDisplayLocale)
  {
    return sCellText -> StringHelper.isEmpty (sCellText) ? null : LocaleParser.parseBigDecimal (sCellText, aDisplayLocale, null);
  }

  @NonNull
  public static IComparableExtractor <BigDecimal> getExtractorCurrencyFormat (@NonNull final ECurrency eCurrency)
  {
    return sCellText -> StringHelper.isEmpty (sCellText) ? null : CurrencyHelper.parseCurrencyFormat (eCurrency, sCellText, null);
  }

  @NonNull
  public static IComparableExtractor <BigDecimal> getExtractorCurrencyValueFormat (@NonNull final ECurrency eCurrency)
  {
    return sCellText -> StringHelper.isEmpty (sCellText) ? null : CurrencyHelper.parseValueFormat (eCurrency, sCellText, null);
  }

  @NonNull
  public static IComparableExtractor <BigInteger> getExtractorBigInteger (@NonNull final Locale aDisplayLocale)
  {
    return sCellText -> StringHelper.isEmpty (sCellText) ? null
                                                           : LocaleParser.parseBigDecimal (sCellText, aDisplayLocale, BigDecimal.ZERO)
                                                                         .toBigIntegerExact ();
  }

  @NonNull
  public static IComparableExtractor <LocalDate> getExtractorDate (@NonNull final Locale aDisplayLocale)
  {
    return getExtractorDate (PDTFormatter.getFormatterDate (FormatStyle.MEDIUM, aDisplayLocale, EDTFormatterMode.PARSE));
  }

  @NonNull
  public static IComparableExtractor <LocalDate> getExtractorDate (@NonNull final DateTimeFormatter aDTFormatter)
  {
    return sCellText -> StringHelper.isEmpty (sCellText) ? null
                                                           : ValueEnforcer.notNull (PDTFromString.getLocalDateFromString (sCellText,
                                                                                                                          aDTFormatter),
                                                                                    () -> "Failed to parse date '" +
                                                                                          sCellText +
                                                                                          "' using formatter " +
                                                                                          aDTFormatter);
  }

  @NonNull
  public static IComparableExtractor <LocalTime> getExtractorTime (@NonNull final Locale aDisplayLocale)
  {
    return getExtractorTime (PDTFormatter.getFormatterTime (FormatStyle.MEDIUM, aDisplayLocale, EDTFormatterMode.PARSE));
  }

  @NonNull
  public static IComparableExtractor <LocalTime> getExtractorTime (@NonNull final DateTimeFormatter aDTFormatter)
  {
    return sCellText -> StringHelper.isEmpty (sCellText) ? null
                                                           : ValueEnforcer.notNull (PDTFromString.getLocalTimeFromString (sCellText,
                                                                                                                          aDTFormatter),
                                                                                    () -> "Failed to parse time '" +
                                                                                          sCellText +
                                                                                          "' with formatter " +
                                                                                          aDTFormatter);
  }

  @NonNull
  public static IComparableExtractor <LocalDateTime> getExtractorDateTime (@NonNull final Locale aDisplayLocale)
  {
    return getExtractorDateTime (PDTFormatter.getFormatterDateTime (FormatStyle.MEDIUM, aDisplayLocale, EDTFormatterMode.PARSE));
  }

  @NonNull
  public static IComparableExtractor <LocalDateTime> getExtractorDateTime (@NonNull final DateTimeFormatter aDTFormatter)
  {
    return sCellText -> StringHelper.isEmpty (sCellText) ? null
                                                           : ValueEnforcer.notNull (PDTFromString.getLocalDateTimeFromString (sCellText,
                                                                                                                              aDTFormatter),
                                                                                    () -> "Failed to parse datetime '" +
                                                                                          sCellText +
                                                                                          "' with formatter " +
                                                                                          aDTFormatter);
  }

  @NonNull
  public static IComparableExtractor <Duration> getExtractorDuration ()
  {
    return sCellText -> StringHelper.isEmpty (sCellText) ? null
                                                           : ValueEnforcer.notNull (PDTFromString.getDurationFromString (sCellText),
                                                                                    () -> "Failed to parse duration '" + sCellText + "'");
  }
}
