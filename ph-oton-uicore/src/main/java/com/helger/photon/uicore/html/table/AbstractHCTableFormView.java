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
package com.helger.photon.uicore.html.table;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.html.AbstractHCTable;
import com.helger.html.hc.html.HCCol;

public abstract class AbstractHCTableFormView <IMPLTYPE extends AbstractHCTableFormView <IMPLTYPE>> extends AbstractHCTable <IMPLTYPE> implements IHCTableFormView <IMPLTYPE>
{
  public AbstractHCTableFormView ()
  {
    super ();
  }

  public AbstractHCTableFormView (@Nullable final HCCol aCol)
  {
    super (aCol);
  }

  public AbstractHCTableFormView (@Nullable final HCCol... aCols)
  {
    super (aCols);
  }

  public AbstractHCTableFormView (@Nullable final Iterable <? extends HCCol> aCols)
  {
    super (aCols);
  }

  @Nonnull
  public HCTableFormViewItemRow createItemRow ()
  {
    final HCTableFormViewItemRow aRow = new HCTableFormViewItemRow (false);
    addBodyRow (aRow);
    return aRow;
  }
}
