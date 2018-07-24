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

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.datetime.EDTFormatterMode;
import com.helger.commons.datetime.PDTFormatter;
import com.helger.commons.datetime.PDTFromString;
import com.helger.commons.locale.LocaleParser;
import com.helger.commons.string.StringHelper;
import com.helger.masterdata.currency.CurrencyHelper;
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

  @Nonnull
  public static IComparableExtractor <BigDecimal> getExtractorBigDecimal (@Nonnull final Locale aDisplayLocale)
  {
    return sCellText -> StringHelper.hasNoText (sCellText) ? null
                                                           : LocaleParser.parseBigDecimal (sCellText,
                                                                                           aDisplayLocale,
                                                                                           null);
  }

  @Nonnull
  public static IComparableExtractor <BigDecimal> getExtractorCurrencyFormat (@Nonnull final ECurrency eCurrency)
  {
    return sCellText -> StringHelper.hasNoText (sCellText) ? null
                                                           : CurrencyHelper.parseCurrencyFormat (eCurrency,
                                                                                                 sCellText,
                                                                                                 null);
  }

  @Nonnull
  public static IComparableExtractor <BigDecimal> getExtractorCurrencyValueFormat (@Nonnull final ECurrency eCurrency)
  {
    return sCellText -> StringHelper.hasNoText (sCellText) ? null
                                                           : CurrencyHelper.parseValueFormat (eCurrency,
                                                                                              sCellText,
                                                                                              null);
  }

  @Nonnull
  public static IComparableExtractor <BigInteger> getExtractorBigInteger (@Nonnull final Locale aDisplayLocale)
  {
    return sCellText -> StringHelper.hasNoText (sCellText) ? null
                                                           : LocaleParser.parseBigDecimal (sCellText,
                                                                                           aDisplayLocale,
                                                                                           BigDecimal.ZERO)
                                                                         .toBigIntegerExact ();
  }

  @Nonnull
  public static IComparableExtractor <LocalDate> getExtractorDate (@Nonnull final Locale aDisplayLocale)
  {
    return getExtractorDate (PDTFormatter.getFormatterDate (FormatStyle.MEDIUM,
                                                            aDisplayLocale,
                                                            EDTFormatterMode.PARSE));
  }

  @Nonnull
  public static IComparableExtractor <LocalDate> getExtractorDate (@Nonnull final DateTimeFormatter aDTFormatter)
  {
    return sCellText -> StringHelper.hasNoText (sCellText) ? null
                                                           : ValueEnforcer.notNull (PDTFromString.getLocalDateFromString (sCellText,
                                                                                                                          aDTFormatter),
                                                                                    () -> "Failed to parse date '" +
                                                                                          sCellText +
                                                                                          "' using formatter " +
                                                                                          aDTFormatter);
  }

  @Nonnull
  public static IComparableExtractor <LocalTime> getExtractorTime (@Nonnull final Locale aDisplayLocale)
  {
    return getExtractorTime (PDTFormatter.getFormatterTime (FormatStyle.MEDIUM,
                                                            aDisplayLocale,
                                                            EDTFormatterMode.PARSE));
  }

  @Nonnull
  public static IComparableExtractor <LocalTime> getExtractorTime (@Nonnull final DateTimeFormatter aDTFormatter)
  {
    return sCellText -> StringHelper.hasNoText (sCellText) ? null
                                                           : ValueEnforcer.notNull (PDTFromString.getLocalTimeFromString (sCellText,
                                                                                                                          aDTFormatter),
                                                                                    () -> "Failed to parse time '" +
                                                                                          sCellText +
                                                                                          "' with formatter " +
                                                                                          aDTFormatter);
  }

  @Nonnull
  public static IComparableExtractor <LocalDateTime> getExtractorDateTime (@Nonnull final Locale aDisplayLocale)
  {
    return getExtractorDateTime (PDTFormatter.getFormatterDateTime (FormatStyle.MEDIUM,
                                                                    aDisplayLocale,
                                                                    EDTFormatterMode.PARSE));
  }

  @Nonnull
  public static IComparableExtractor <LocalDateTime> getExtractorDateTime (@Nonnull final DateTimeFormatter aDTFormatter)
  {
    return sCellText -> StringHelper.hasNoText (sCellText) ? null
                                                           : ValueEnforcer.notNull (PDTFromString.getLocalDateTimeFromString (sCellText,
                                                                                                                              aDTFormatter),
                                                                                    () -> "Failed to parse datetime '" +
                                                                                          sCellText +
                                                                                          "' with formatter " +
                                                                                          aDTFormatter);
  }

  @Nonnull
  public static IComparableExtractor <Duration> getExtractorDuration ()
  {
    return sCellText -> StringHelper.hasNoText (sCellText) ? null
                                                           : ValueEnforcer.notNull (PDTFromString.getDurationFromString (sCellText),
                                                                                    () -> "Failed to parse duration '" +
                                                                                          sCellText +
                                                                                          "'");
  }
}
