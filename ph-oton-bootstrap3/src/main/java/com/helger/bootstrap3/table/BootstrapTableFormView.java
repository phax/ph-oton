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

import com.helger.html.hc.html.HCCol;
import com.helger.photon.uicore.html.table.HCTableFormViewItemRow;
import com.helger.photon.uicore.html.table.IHCTableFormView;

public class BootstrapTableFormView extends AbstractBootstrapTable <BootstrapTableFormView> implements IHCTableFormView <BootstrapTableFormView>
{
  private void _init ()
  {
    setHover (true);
    setStriped (true);
  }

  public BootstrapTableFormView ()
  {
    super ();
    _init ();
  }

  public BootstrapTableFormView (@Nullable final HCCol aCol)
  {
    super (aCol);
    _init ();
  }

  public BootstrapTableFormView (@Nullable final HCCol... aCols)
  {
    super (aCols);
    _init ();
  }

  public BootstrapTableFormView (@Nullable final Iterable <? extends HCCol> aCols)
  {
    super (aCols);
    _init ();
  }

  @Nonnull
  public HCTableFormViewItemRow createItemRow ()
  {
    final HCTableFormViewItemRow aRow = new HCTableFormViewItemRow (false);
    addBodyRow (aRow);
    return aRow;
  }
}
