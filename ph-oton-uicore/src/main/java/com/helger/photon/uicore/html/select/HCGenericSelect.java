/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import java.util.Collection;
import java.util.Comparator;
import java.util.Locale;
import java.util.function.Predicate;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.base.id.IHasID;
import com.helger.collection.helper.CollectionSort;
import com.helger.html.request.IHCRequestField;
import com.helger.text.display.IDisplayTextProvider;

public class HCGenericSelect <T extends IHasID <String>> extends HCExtSelect
{
  public HCGenericSelect (@NonNull final IHCRequestField aRF,
                          @NonNull final Collection <? extends T> aElements,
                          @Nullable final Comparator <? super T> aComparator,
                          @NonNull final Locale aDisplayLocale,
                          @Nullable final Predicate <? super T> aFilter,
                          @NonNull final IDisplayTextProvider <? super T> aDisplayTextProvider,
                          final boolean bAddOptionPleaseSelect)
  {
    super (aRF);

    final Collection <? extends T> aIterable = aComparator == null ? aElements : CollectionSort.getSorted (aElements,
                                                                                                           aComparator);
    // for all items
    for (final T aElement : aIterable)
      if (aFilter == null || aFilter.test (aElement))
        addOption (aElement.getID (), aDisplayTextProvider.getDisplayText (aElement, aDisplayLocale));

    // add empty item
    if (!hasSelectedOption () || bAddOptionPleaseSelect)
      addOptionPleaseSelect (aDisplayLocale);
  }
}
