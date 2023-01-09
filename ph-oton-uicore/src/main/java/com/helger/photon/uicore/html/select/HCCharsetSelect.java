/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.html.select;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.charset.CharsetHelper;
import com.helger.html.request.IHCRequestField;

/**
 * Select box for character sets
 *
 * @author Philip Helger
 */
public class HCCharsetSelect extends HCExtSelect
{
  public HCCharsetSelect (@Nonnull final IHCRequestField aRF, final boolean bOnlyRegistered, @Nonnull final Locale aDisplayLocale)
  {
    this (aRF, x -> x.isRegistered () || !bOnlyRegistered, aDisplayLocale);
  }

  public HCCharsetSelect (@Nonnull final IHCRequestField aRF,
                          @Nullable final Predicate <? super Charset> aFilter,
                          @Nonnull final Locale aDisplayLocale)
  {
    super (aRF);

    for (final Charset aCharset : CharsetHelper.getAllCharsets ().values ())
      if (aFilter == null || aFilter.test (aCharset))
        addOption (aCharset.name (), aCharset.displayName (aDisplayLocale));

    addOptionPleaseSelect (aDisplayLocale);
  }
}
