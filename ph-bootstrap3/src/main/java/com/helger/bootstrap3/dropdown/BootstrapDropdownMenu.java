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
package com.helger.bootstrap3.dropdown;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.bootstrap3.CBootstrapCSS;
import com.helger.bootstrap3.base.BootstrapCaret;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.StringHelper;
import com.helger.html.EHTMLRole;
import com.helger.html.hc.IHCElementWithChildren;
import com.helger.html.hc.html.HCLI;
import com.helger.html.hc.html.HCUL;

public class BootstrapDropdownMenu extends HCUL
{
  public BootstrapDropdownMenu ()
  {
    addClass (CBootstrapCSS.DROPDOWN_MENU);
    setRole (EHTMLRole.MENU);
  }

  @Override
  protected void onAddItem (@Nonnull final HCLI aItem)
  {
    if (aItem.getRole () == null)
      aItem.setRole (EHTMLRole.PRESENTATION);
  }

  @Nonnull
  public BootstrapDropdownMenu addMenuItem (@Nonnull final BootstrapDropdownMenuItem aItem)
  {
    ValueEnforcer.notNull (aItem, "DropdownMenuItem");
    final HCLI aLI = addAndReturnItem (aItem.createLink ());
    if (aItem.isActive ())
      aLI.addClass (CBootstrapCSS.ACTIVE);
    return this;
  }

  @Nonnull
  public BootstrapDropdownMenu addDivider ()
  {
    addItem ().addClass (CBootstrapCSS.DIVIDER);
    return this;
  }

  @Nonnull
  public BootstrapDropdownMenu addHeader (@Nullable final String sHeaderText)
  {
    if (StringHelper.hasText (sHeaderText))
      addItem (sHeaderText).addClass (CBootstrapCSS.DROPDOWN_HEADER);
    return this;
  }

  public static void disableItem (@Nonnull final HCLI aItem)
  {
    aItem.addClass (CBootstrapCSS.DISABLED);
  }

  /**
   * Call this method to convert an element to a dropdown toggle. Important:
   * call this after all children are added, because a caret is added at the
   * end!
   *
   * @param aElement
   *        The element to use. May not be <code>null</code>.
   * @param <IMPLTYPE>
   *        Implementation type
   * @return The passed element. Never <code>null</code>.
   */
  @Nonnull
  public static <IMPLTYPE extends IHCElementWithChildren <?>> IMPLTYPE makeDropdownToggle (@Nonnull final IMPLTYPE aElement)
  {
    aElement.addClass (CBootstrapCSS.DROPDOWN_TOGGLE)
            .setDataAttr ("toggle", "dropdown")
            .addChild (new BootstrapCaret ());
    return aElement;
  }
}
