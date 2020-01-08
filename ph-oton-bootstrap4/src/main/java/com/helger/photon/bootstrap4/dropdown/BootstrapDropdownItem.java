/**
 * Copyright (C) 2018-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.dropdown;

import javax.annotation.Nonnull;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.textlevel.AbstractHCA;
import com.helger.photon.bootstrap4.CBootstrapCSS;

public class BootstrapDropdownItem extends AbstractHCA <BootstrapDropdownItem>
{
  public static final boolean DEFAULT_ACTIVE = false;
  public static final boolean DEFAULT_DISABLED = false;

  private boolean m_bActive = DEFAULT_ACTIVE;
  private boolean m_bDisabled = DEFAULT_DISABLED;

  public BootstrapDropdownItem ()
  {}

  public final boolean isActive ()
  {
    return m_bActive;
  }

  @Nonnull
  public final BootstrapDropdownItem setActive (final boolean bActive)
  {
    m_bActive = bActive;
    return this;
  }

  public final boolean isDisabled ()
  {
    return m_bDisabled;
  }

  @Nonnull
  public final BootstrapDropdownItem setDisabled (final boolean bDisabled)
  {
    m_bDisabled = bDisabled;
    return this;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (CBootstrapCSS.DROPDOWN_ITEM);
    if (m_bActive)
      addClass (CBootstrapCSS.ACTIVE);
    if (m_bDisabled)
      addClass (CBootstrapCSS.DISABLED);
  }
}
