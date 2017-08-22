/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.table;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.html.tabular.AbstractHCTable;
import com.helger.html.hc.html.tabular.IHCCol;
import com.helger.photon.bootstrap4.CBootstrapCSS;

public abstract class AbstractBootstrapTable <THISTYPE extends AbstractHCTable <THISTYPE>> extends
                                             AbstractHCTable <THISTYPE>
{
  private void _init ()
  {
    addClass (CBootstrapCSS.TABLE);
    setCondensed (true);
  }

  public AbstractBootstrapTable ()
  {
    super ();
    _init ();
  }

  public AbstractBootstrapTable (@Nullable final IHCCol <?> aCol)
  {
    super (aCol);
    _init ();
  }

  public AbstractBootstrapTable (@Nullable final IHCCol <?>... aCols)
  {
    super (aCols);
    _init ();
  }

  public AbstractBootstrapTable (@Nullable final Iterable <? extends IHCCol <?>> aCols)
  {
    super (aCols);
    _init ();
  }

  @Nonnull
  public THISTYPE setStriped (final boolean bStriped)
  {
    if (bStriped)
      addClass (CBootstrapCSS.TABLE_STRIPED);
    else
      removeClass (CBootstrapCSS.TABLE_STRIPED);
    return thisAsT ();
  }

  @Nonnull
  public THISTYPE setBordered (final boolean bBordered)
  {
    if (bBordered)
      addClass (CBootstrapCSS.TABLE_BORDERED);
    else
      removeClass (CBootstrapCSS.TABLE_BORDERED);
    return thisAsT ();
  }

  @Nonnull
  public THISTYPE setHover (final boolean bHover)
  {
    if (bHover)
      addClass (CBootstrapCSS.TABLE_HOVER);
    else
      removeClass (CBootstrapCSS.TABLE_HOVER);
    return thisAsT ();
  }

  @Nonnull
  public THISTYPE setCondensed (final boolean bCondensed)
  {
    if (bCondensed)
      addClass (CBootstrapCSS.TABLE_SM);
    else
      removeClass (CBootstrapCSS.TABLE_SM);
    return thisAsT ();
  }

  @Nonnull
  public THISTYPE setInverse (final boolean bCondensed)
  {
    if (bCondensed)
      addClass (CBootstrapCSS.TABLE_INVERSE);
    else
      removeClass (CBootstrapCSS.TABLE_INVERSE);
    return thisAsT ();
  }

  @Nonnull
  public THISTYPE setResponsive (final boolean bCondensed)
  {
    if (bCondensed)
      addClass (CBootstrapCSS.TABLE_RESPONSIVE);
    else
      removeClass (CBootstrapCSS.TABLE_RESPONSIVE);
    return thisAsT ();
  }
}
