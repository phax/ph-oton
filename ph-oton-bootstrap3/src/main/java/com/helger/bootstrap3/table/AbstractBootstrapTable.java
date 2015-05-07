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
package com.helger.bootstrap3.table;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.bootstrap3.CBootstrapCSS;
import com.helger.html.hc.html.AbstractHCTable;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCDiv;

public abstract class AbstractBootstrapTable <THISTYPE extends AbstractHCTable <THISTYPE>> extends AbstractHCTable <THISTYPE>
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

  public AbstractBootstrapTable (@Nullable final HCCol aCol)
  {
    super (aCol);
    _init ();
  }

  public AbstractBootstrapTable (@Nullable final HCCol... aCols)
  {
    super (aCols);
    _init ();
  }

  public AbstractBootstrapTable (@Nullable final Iterable <? extends HCCol> aCols)
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
