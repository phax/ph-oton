/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.pagination;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.AbstractHCUL;
import com.helger.html.hc.html.grouping.IHCLI;
import com.helger.photon.bootstrap3.CBootstrapCSS;

public class BootstrapPager extends AbstractHCUL <BootstrapPager>
{
  public BootstrapPager ()
  {
    addClass (CBootstrapCSS.PAGER);
  }

  @Nonnull
  public BootstrapPager addItemPrev (@Nullable final IHCNode aContent, final boolean bDisabled)
  {
    final IHCLI <?> aItem = addAndReturnItem (aContent).addClass (CBootstrapCSS.PREVIOUS);
    if (bDisabled)
      aItem.addClass (CBootstrapCSS.DISABLED);
    return this;
  }

  @Nonnull
  public BootstrapPager addItemNext (@Nullable final IHCNode aContent, final boolean bDisabled)
  {
    final IHCLI <?> aItem = addAndReturnItem (aContent).addClass (CBootstrapCSS.NEXT);
    if (bDisabled)
      aItem.addClass (CBootstrapCSS.DISABLED);
    return this;
  }
}
