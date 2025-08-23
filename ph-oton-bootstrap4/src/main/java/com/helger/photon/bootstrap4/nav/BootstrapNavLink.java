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
package com.helger.photon.bootstrap4.nav;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.textlevel.AbstractHCA;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.url.ISimpleURL;
import com.helger.url.SimpleURL;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public class BootstrapNavLink extends AbstractHCA <BootstrapNavLink>
{
  private static final ISimpleURL DEFAULT_HREF = new SimpleURL ("#");

  private boolean m_bActive = false;
  private boolean m_bDisabled = false;

  public BootstrapNavLink ()
  {
    this (null);
  }

  public BootstrapNavLink (@Nullable final ISimpleURL aURL)
  {
    super (aURL != null ? aURL : DEFAULT_HREF);
  }

  public boolean isActive ()
  {
    return m_bActive;
  }

  @Nonnull
  public BootstrapNavLink setActive (final boolean bActive)
  {
    m_bActive = bActive;
    return this;
  }

  public boolean isDisabled ()
  {
    return m_bDisabled;
  }

  @Nonnull
  public BootstrapNavLink setDisabled (final boolean bDisabled)
  {
    m_bDisabled = bDisabled;
    return this;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (CBootstrapCSS.NAV_LINK);
    if (m_bActive)
      addClass (CBootstrapCSS.ACTIVE);
    if (m_bDisabled)
      addClass (CBootstrapCSS.DISABLED);
  }
}
