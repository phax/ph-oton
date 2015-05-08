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
package com.helger.photon.basic.uitext;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.compare.AbstractCollationComparator;

/**
 * Special comparator that compares based on a UI text
 *
 * @author Philip Helger
 * @param <DATATYPE>
 *        Data type to compare
 */
public class ComparatorHasUIText <DATATYPE extends IHasUIText> extends AbstractCollationComparator <DATATYPE>
{
  private final Locale m_aDisplayLocale;

  public ComparatorHasUIText (@Nonnull final Locale aSortLocale, @Nonnull final Locale aDisplayLocale)
  {
    super (aSortLocale);
    m_aDisplayLocale = ValueEnforcer.notNull (aDisplayLocale, "DisplayLocale");
  }

  @Nonnull
  public Locale getDisplayLocale ()
  {
    return m_aDisplayLocale;
  }

  @Override
  protected String asString (@Nonnull final DATATYPE aObject)
  {
    return aObject.getAsUIText (m_aDisplayLocale);
  }
}
