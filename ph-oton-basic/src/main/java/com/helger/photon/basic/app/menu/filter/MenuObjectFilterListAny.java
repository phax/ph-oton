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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.annotation.Nonempty;
import com.helger.photon.basic.EPhotonBasicText;
import com.helger.photon.basic.app.menu.IMenuObject;
import com.helger.photon.basic.app.menu.IMenuObjectFilter;

/**
 * This filter wraps a set of several filters and ensures that at least one
 * contained filter is matching.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class MenuObjectFilterListAny extends AbstractMenuObjectFilterCollection
{
  private void _init ()
  {
    setDescription (EPhotonBasicText.MENU_OBJECT_FILTER_ANY.getMultilingualText ());
  }

  public MenuObjectFilterListAny (@Nonnull @Nonempty final Iterable <? extends IMenuObjectFilter> aFilters)
  {
    super (aFilters);
    _init ();
  }

  public MenuObjectFilterListAny (@Nonnull @Nonempty final IMenuObjectFilter... aFilters)
  {
    super (aFilters);
    _init ();
  }

  public boolean matchesFilter (@Nullable final IMenuObject aValue)
  {
    for (final IMenuObjectFilter aFilter : this)
      if (aFilter.matchesFilter (aValue))
        return true;
    return false;
  }
}
