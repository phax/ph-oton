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

import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.app.menu.IMenuObject;

/**
 * This filter wraps a set of several filters and ensures that at least one
 * contained filter ist matching.
 *
 * @author Philip Helger
 */
public final class MenuItemFilterListOne extends AbstractMenuObjectFilter
{
  private final List <AbstractMenuObjectFilter> m_aFilters;

  public MenuItemFilterListOne (@Nonnull @Nonempty final Collection <? extends AbstractMenuObjectFilter> aFilters)
  {
    ValueEnforcer.notEmpty (aFilters, "Filters");
    m_aFilters = CollectionHelper.newList (aFilters);
  }

  public MenuItemFilterListOne (@Nonnull @Nonempty final AbstractMenuObjectFilter... aFilters)
  {
    ValueEnforcer.notEmpty (aFilters, "Filters");
    m_aFilters = CollectionHelper.newList (aFilters);
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <AbstractMenuObjectFilter> getAllFilters ()
  {
    return CollectionHelper.newList (m_aFilters);
  }

  public boolean matchesFilter (@Nullable final IMenuObject aValue)
  {
    for (final AbstractMenuObjectFilter aFilter : m_aFilters)
      if (aFilter.matchesFilter (aValue))
        return true;
    return false;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("filters", m_aFilters).toString ();
  }
}
