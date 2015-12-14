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
package com.helger.photon.basic.app.menu.filter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.app.menu.IMenuObjectFilter;

/**
 * Abstract menu object filter that encapsulates a list of other menu object
 * filters
 *
 * @author Philip Helger
 */
@NotThreadSafe
public abstract class AbstractMenuObjectFilterCollection extends AbstractMenuObjectFilter
                                                         implements Iterable <IMenuObjectFilter>
{
  private final List <IMenuObjectFilter> m_aFilters;

  public AbstractMenuObjectFilterCollection (@Nonnull @Nonempty final Iterable <? extends IMenuObjectFilter> aFilters)
  {
    ValueEnforcer.notEmpty (aFilters, "Filters");
    m_aFilters = CollectionHelper.newList (aFilters);
  }

  public AbstractMenuObjectFilterCollection (@Nonnull @Nonempty final IMenuObjectFilter... aFilters)
  {
    ValueEnforcer.notEmpty (aFilters, "Filters");
    m_aFilters = CollectionHelper.newList (aFilters);
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <IMenuObjectFilter> getAllContainedFilters ()
  {
    return CollectionHelper.newList (m_aFilters);
  }

  @Nonnull
  public Iterator <IMenuObjectFilter> iterator ()
  {
    return m_aFilters.iterator ();
  }

  @Override
  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    // Get this display text
    final String sThisText = super.getDisplayText (aContentLocale);
    final List <String> aNested = new ArrayList <> ();
    // Get the display texts of all contained filters
    for (final IMenuObjectFilter aFilter : m_aFilters)
      aNested.add (aFilter.getDisplayText (aContentLocale));
    // Combine all non-empty, separated by a semicolon
    return StringHelper.getConcatenatedOnDemand (sThisText, ": ", StringHelper.getImplodedNonEmpty ("; ", aNested));
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("filters", m_aFilters).toString ();
  }
}
