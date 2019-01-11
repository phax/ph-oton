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
package com.helger.photon.uicore.html.select;

import java.util.Collection;
import java.util.Comparator;
import java.util.Locale;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.id.IHasID;
import com.helger.commons.text.display.IDisplayTextProvider;
import com.helger.html.request.IHCRequestField;

public class HCGenericSelect <T extends IHasID <String>> extends HCExtSelect
{
  public HCGenericSelect (@Nonnull final IHCRequestField aRF,
                          @Nonnull final Collection <? extends T> aElements,
                          @Nullable final Comparator <? super T> aComparator,
                          @Nonnull final Locale aDisplayLocale,
                          @Nullable final Predicate <? super T> aFilter,
                          @Nonnull final IDisplayTextProvider <? super T> aDisplayTextProvider,
                          final boolean bAddOptionPleaseSelect)
  {
    super (aRF);

    final Collection <? extends T> aIterable = aComparator == null ? aElements
                                                                   : CollectionHelper.getSorted (aElements,
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
