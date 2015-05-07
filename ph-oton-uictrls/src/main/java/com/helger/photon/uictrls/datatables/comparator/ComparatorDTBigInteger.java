/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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

import java.math.BigInteger;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.format.IFormatter;

/**
 * This comparator is responsible for sorting cells by BigInteger
 * 
 * @author Philip Helger
 */
public class ComparatorDTBigInteger extends ComparatorDTBigDecimal
{
  public ComparatorDTBigInteger (@Nonnull final Locale aParseLocale)
  {
    this (null, aParseLocale);
  }

  public ComparatorDTBigInteger (@Nullable final IFormatter aFormatter, @Nonnull final Locale aParseLocale)
  {
    super (aFormatter, aParseLocale);
  }

  @Override
  protected final int internalCompare (@Nonnull final String sText1, @Nonnull final String sText2)
  {
    final BigInteger aBI1 = getAsBigDecimal (sText1).toBigIntegerExact ();
    final BigInteger aBI2 = getAsBigDecimal (sText2).toBigIntegerExact ();
    return aBI1.compareTo (aBI2);
  }
}
