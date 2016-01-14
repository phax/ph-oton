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
import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.masterdata.currency.ECurrency;

/**
 * A cell comparator that handles currency formatted cells that all use the same
 * currency.
 *
 * @author Philip Helger
 */
public final class ComparatorCellFixedCurrencyFormat extends ComparatorCellBigDecimal
{
  private final ECurrency m_eCurrency;

  public ComparatorCellFixedCurrencyFormat (@Nonnull final Locale aLocale, @Nonnull final ECurrency eCurrency)
  {
    super (aLocale);
    m_eCurrency = ValueEnforcer.notNull (eCurrency, "Currency");
  }

  @Nonnull
  public ECurrency getCurrency ()
  {
    return m_eCurrency;
  }

  @Override
  @Nonnull
  protected BigDecimal getAsBigDecimal (@Nonnull final String sCellText)
  {
    return m_eCurrency.parseCurrencyFormat (sCellText, BigDecimal.ZERO);
  }
}
