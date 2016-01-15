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

import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.compare.PartComparatorComparable;
import com.helger.commons.string.StringHelper;

/**
 * Base class for all table comparators
 *
 * @author Philip Helger
 * @param <PARTTYPE>
 *        The part type that is extracted from the cell text and compared
 */
public abstract class AbstractComparatorDT <PARTTYPE extends Comparable <? super PARTTYPE>>
                                           extends PartComparatorComparable <String, PARTTYPE> implements IComparatorDT
{
  @Nonnull
  protected static <PARTTYPE extends Comparable <? super PARTTYPE>> Function <String, PARTTYPE> createPartExtractor (@Nullable final Function <? super String, String> aFormatter,
                                                                                                                     @Nonnull final Function <String, PARTTYPE> aExtractor)
  {
    if (aFormatter == null)
      return sCell -> aExtractor.apply (StringHelper.getNotNull (sCell));

    return sCell -> aExtractor.apply (sCell == null ? "" : aFormatter.apply (sCell));
  }

  public AbstractComparatorDT (@Nullable final Function <? super String, String> aFormatter,
                               @Nonnull final Function <String, PARTTYPE> aExtractor)
  {
    super (createPartExtractor (aFormatter, aExtractor));
  }
}
