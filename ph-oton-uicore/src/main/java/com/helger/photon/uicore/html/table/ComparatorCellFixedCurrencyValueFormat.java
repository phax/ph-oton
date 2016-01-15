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
package com.helger.photon.uicore.html.table;

import java.math.BigDecimal;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.masterdata.currency.ECurrency;

/**
 * A cell comparator that handles value formatted currency cells that all use
 * the same currency.
 *
 * @author Philip Helger
 */
public final class ComparatorCellFixedCurrencyValueFormat extends AbstractComparatorCell <BigDecimal>
{
  private static final BigDecimal DEFAULT_VALUE = BigDecimal.ZERO;

  public ComparatorCellFixedCurrencyValueFormat (@Nonnull final ECurrency eCurrency)
  {
    super (aCell -> eCurrency.parseValueFormat (getCellText (aCell, null, null), DEFAULT_VALUE));
    ValueEnforcer.notNull (eCurrency, "Currency");
  }
}
