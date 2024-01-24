/*
 * Copyright (C) 2018-2024 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.nav;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.url.ISimpleURL;
import com.helger.html.EHTMLRole;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.AbstractHCLI;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.dropdown.BootstrapDropdownMenu;

public class BootstrapNavItem extends AbstractHCLI <BootstrapNavItem>
{
  public static final boolean DEFAULT_ACTIVE = false;
  public static final boolean DEFAULT_DROP_DOWN = false;

  private boolean m_bIsActive = DEFAULT_ACTIVE;
  private boolean m_bIsDropDown = DEFAULT_DROP_DOWN;

  public BootstrapNavItem ()
  {}

  public final boolean isActive ()
  {
    return m_bIsActive;
  }

  @Nonnull
  public final BootstrapNavItem setActive (final boolean bIsActive)
  {
    m_bIsActive = bIsActive;
    return this;
  }

  public final boolean isDropDown ()
  {
    return m_bIsDropDown;
  }

  @Nonnull
  public BootstrapNavLink addNavLink ()
  {
    return addAndReturnChild (new BootstrapNavLink ());
  }

  @Nonnull
  public BootstrapNavLink addNavLink (@Nullable final ISimpleURL aURL)
  {
    return addAndReturnChild (new BootstrapNavLink (aURL));
  }

  public void addNavDropDown (@Nonnull final String sLabel, @Nonnull final BootstrapDropdownMenu aDropDown)
  {
    addNavDropDown (new HCTextNode (sLabel), aDropDown);
  }

  public void addNavDropDown (@Nonnull final IHCNode aLabelText, @Nonnull final BootstrapDropdownMenu aDropDown)
  {
    final BootstrapNavLink aLabel = new BootstrapNavLink ();
    aLabel.addClass (CBootstrapCSS.DROPDOWN_TOGGLE).ensureID ().setRole (EHTMLRole.BUTTON);
    aLabel.customAttrs ().setDataAttr ("toggle", "dropdown");
    aLabel.customAttrs ().setAriaHasPopup (true);
    aLabel.customAttrs ().setAriaExpanded (true);
    aLabel.addChild (aLabelText);

    aDropDown.customAttrs ().setAriaLabeledBy (aLabel);

    addChild (aLabel);
    addChild (aDropDown);
    m_bIsDropDown = true;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (CBootstrapCSS.NAV_ITEM);
    if (m_bIsActive)
      addClass (CBootstrapCSS.ACTIVE);
    if (m_bIsDropDown)
      addClass (CBootstrapCSS.DROPDOWN);
  }
}
