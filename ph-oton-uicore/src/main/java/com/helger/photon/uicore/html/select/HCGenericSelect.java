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
