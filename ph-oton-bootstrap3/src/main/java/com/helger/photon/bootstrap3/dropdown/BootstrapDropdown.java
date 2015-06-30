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
package com.helger.photon.bootstrap3.dropdown;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.microdom.IMicroElement;
import com.helger.html.CHTMLAttributes;
import com.helger.html.hc.IHCElementWithChildren;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.conversion.HCConsistencyChecker;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCDiv;
import com.helger.html.hc.utils.HCSpecialNodeHandler;
import com.helger.photon.bootstrap3.CBootstrapCSS;
import com.helger.photon.bootstrap3.base.BootstrapCaret;

public class BootstrapDropdown extends AbstractHCDiv <BootstrapDropdown>
{
  public BootstrapDropdown (@Nonnull final EBootstrapDropdownType eDropdownType,
                            @Nonnull final IHCElementWithChildren <?> aSelector,
                            @Nonnull final BootstrapDropdownMenu aMenu)
  {
    this (eDropdownType, aSelector, aMenu, true);
  }

  public BootstrapDropdown (@Nonnull final EBootstrapDropdownType eDropdownType,
                            @Nonnull final IHCElementWithChildren <?> aSelector,
                            @Nonnull final BootstrapDropdownMenu aMenu,
                            final boolean bAddDropDownToggle)
  {
    ValueEnforcer.notNull (aSelector, "Selector");
    ValueEnforcer.notNull (aMenu, "Menu");
    addClass (eDropdownType);

    // Link selector and menu
    aMenu.setCustomAttr (CHTMLAttributes.ARIA_LABELLEDBY, aSelector.ensureID ().getID ());

    if (bAddDropDownToggle)
    {
      // Add dropdown caret
      makeDropdownToggle (aSelector);
    }
    else
    {
      // Required for drop down to work :)
      aSelector.setDataAttr ("toggle", "dropdown");
    }

    // Add children
    addChild (aSelector);
    addChild (aMenu);
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void applyProperties (@Nonnull final IMicroElement aElement,
                                  @Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (aConversionSettings.areConsistencyChecksEnabled ())
    {
      // Anything besides script may not be added besides the 2 default elements
      // from the ctor
      if (getChildCount () >= 2)
        for (int i = 2; i < getChildCount (); ++i)
        {
          final IHCNode aChild = getChildAtIndex (i);
          if (!HCSpecialNodeHandler.isOutOfBandNode (aChild))
            HCConsistencyChecker.consistencyWarning ("A BootstrapDropdown must have only 2 children or scripts!");
        }
    }
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
