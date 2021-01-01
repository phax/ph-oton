/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.dropdown;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.StringHelper;
import com.helger.html.EHTMLRole;
import com.helger.html.hc.html.grouping.AbstractHCUL;
import com.helger.html.hc.html.grouping.HCLI;
import com.helger.photon.bootstrap3.CBootstrapCSS;

public class BootstrapDropdownMenu extends AbstractHCUL <BootstrapDropdownMenu>
{
  public BootstrapDropdownMenu ()
  {
    this (EBootstrapDropdownMenuAlignment.DEFAULT);
  }

  public BootstrapDropdownMenu (@Nullable final EBootstrapDropdownMenuAlignment eAlignment)
  {
    addClass (CBootstrapCSS.DROPDOWN_MENU);
    addClass (eAlignment);
    setRole (EHTMLRole.MENU);
  }

  @Override
  protected void onAddItem (@Nonnull final HCLI aItem)
  {
    if (aItem.getRole () == null)
      aItem.setRole (EHTMLRole.PRESENTATION);
  }

  @Nonnull
  public HCLI addMenuItem (@Nonnull final BootstrapDropdownMenuItem aItem)
  {
    ValueEnforcer.notNull (aItem, "DropdownMenuItem");

    final HCLI aLI = addAndReturnItem (aItem.createLink ());
    if (aItem.isActive ())
      aLI.addClass (CBootstrapCSS.ACTIVE);
    if (aItem.isDisabled ())
      aLI.addClass (CBootstrapCSS.DISABLED);
    return aLI;
  }

  @Nonnull
  public HCLI addDivider ()
  {
    return addItem ().addClass (CBootstrapCSS.DIVIDER);
  }

  @Nonnull
  public BootstrapDropdownMenu addHeader (@Nullable final String sHeaderText)
  {
    if (StringHelper.hasText (sHeaderText))
      addAndReturnItem (sHeaderText).addClass (CBootstrapCSS.DROPDOWN_HEADER);
    return this;
  }
}
