/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.navbar;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.forms.AbstractHCButton;
import com.helger.html.hc.html.textlevel.HCSpan;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Bootstrap 4 NavBar toggler
 *
 * @author Philip Helger
 */
public class BootstrapNavbarToggler extends AbstractHCButton <BootstrapNavbarToggler>
{
  private final String m_sIDToToggle;

  public BootstrapNavbarToggler (@Nonnull @Nonempty final String sIDToToggle)
  {
    ValueEnforcer.notEmpty (sIDToToggle, "IDToToggle");
    m_sIDToToggle = sIDToToggle;
  }

  @Nonnull
  @Nonempty
  public final String getIDToToggle ()
  {
    return m_sIDToToggle;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (CBootstrapCSS.NAVBAR_TOGGLER);
    customAttrs ().setDataAttr ("toggle", "collapse");
    // With hash sign
    customAttrs ().setDataAttr ("target", "#" + m_sIDToToggle);
    // without hash sign!
    customAttrs ().setAriaControls (m_sIDToToggle);
    customAttrs ().setAriaExpanded (false);
    customAttrs ().setAriaLabel ("Toggle navigation");
    // Main toggle button
    addChild (new HCSpan ().addClass (CBootstrapCSS.NAVBAR_TOGGLER_ICON));
  }
}
