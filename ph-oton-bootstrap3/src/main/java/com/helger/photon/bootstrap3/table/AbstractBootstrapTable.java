/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.table;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.tabular.AbstractHCTable;
import com.helger.html.hc.html.tabular.IHCCol;
import com.helger.photon.bootstrap3.CBootstrapCSS;

/**
 * Abstract table with basic Bootstrap styling.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractBootstrapTable <IMPLTYPE extends AbstractHCTable <IMPLTYPE>> extends AbstractHCTable <IMPLTYPE>
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
  public IMPLTYPE setStriped (final boolean bStriped)
  {
    if (bStriped)
      addClass (CBootstrapCSS.TABLE_STRIPED);
    else
      removeClass (CBootstrapCSS.TABLE_STRIPED);
    return thisAsT ();
  }

  @Nonnull
  public IMPLTYPE setBordered (final boolean bBordered)
  {
    if (bBordered)
      addClass (CBootstrapCSS.TABLE_BORDERED);
    else
      removeClass (CBootstrapCSS.TABLE_BORDERED);
    return thisAsT ();
  }

  @Nonnull
  public IMPLTYPE setHover (final boolean bHover)
  {
    if (bHover)
      addClass (CBootstrapCSS.TABLE_HOVER);
    else
      removeClass (CBootstrapCSS.TABLE_HOVER);
    return thisAsT ();
  }

  @Nonnull
  public IMPLTYPE setCondensed (final boolean bCondensed)
  {
    if (bCondensed)
      addClass (CBootstrapCSS.TABLE_CONDENSED);
    else
      removeClass (CBootstrapCSS.TABLE_CONDENSED);
    return thisAsT ();
  }

  @Nonnull
  public HCDiv getAsResponsiveTable ()
  {
    return new HCDiv ().addClass (CBootstrapCSS.TABLE_RESPONSIVE).addChild (this);
  }
}
