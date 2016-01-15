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

import java.util.Locale;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.compare.IntComparator;
import com.helger.commons.locale.LocaleParser;
import com.helger.commons.string.StringHelper;

public class ComparatorDTInteger extends IntComparator <String> implements IComparatorDT
{
  @Nonnull
  protected static ToIntFunction <String> createPartExtractor (@Nullable final Function <? super String, String> aFormatter,
                                                               @Nonnull final ToIntFunction <String> aExtractor)
  {
    if (aFormatter == null)
      return sCell -> aExtractor.applyAsInt (StringHelper.getNotNull (sCell));

    return sCell -> aExtractor.applyAsInt (sCell == null ? "" : aFormatter.apply (sCell));
  }

  public ComparatorDTInteger (@Nonnull final Locale aParseLocale)
  {
    this (null, aParseLocale);
  }

  public ComparatorDTInteger (@Nullable final Function <? super String, String> aFormatter,
                              @Nonnull final Locale aParseLocale)
  {
    super (createPartExtractor (aFormatter, sCellText -> {
      /*
       * Ensure that columns without text are sorted consistently compared to
       * the ones with non-numeric content
       */
      if (sCellText.isEmpty ())
        return Integer.MIN_VALUE;
      return LocaleParser.parseInt (sCellText, aParseLocale, 0);
    }));
    ValueEnforcer.notNull (aParseLocale, "ParseLocale");
  }
}
