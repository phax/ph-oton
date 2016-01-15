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
package com.helger.photon.uictrls.datatables.comparator;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.locale.LocaleParser;

/**
 * This comparator is responsible for sorting cells by {@link BigDecimal}
 *
 * @author Philip Helger
 */
public class ComparatorDTBigDecimal extends AbstractComparatorDT <BigDecimal>
{
  protected static final BigDecimal DEFAULT_VALUE = BigDecimal.ZERO;

  public ComparatorDTBigDecimal (@Nonnull final Locale aParseLocale)
  {
    this (null, aParseLocale);
  }

  public ComparatorDTBigDecimal (@Nullable final Function <? super String, String> aFormatter,
                                 @Nonnull final Locale aParseLocale)
  {
    super (aFormatter, sCellText -> {
      /*
       * Ensure that columns without text are sorted consistently compared to
       * the ones with non-numeric content
       */
      if (sCellText.isEmpty ())
        return DEFAULT_VALUE;
      return LocaleParser.parseBigDecimal (sCellText, aParseLocale, DEFAULT_VALUE);
    });
    ValueEnforcer.notNull (aParseLocale, "ParseLocale");
  }
}
