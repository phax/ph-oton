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

import com.helger.base.enforce.ValueEnforcer;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.AbstractHCULBase;
import com.helger.photon.bootstrap4.CBootstrapCSS;

import jakarta.annotation.Nonnull;

/**
 * Bootstrap 4 nav
 *
 * @author Philip Helger
 */
public class BootstrapNav extends AbstractHCULBase <BootstrapNav, BootstrapNavItem>
{
  public static final boolean DEFAULT_FILL = false;
  public static final boolean DEFAULT_JUSTIFY = false;

  private EBootstrapNavType m_eNavType;
  private boolean m_bFill = DEFAULT_FILL;
  private boolean m_bJustified = DEFAULT_JUSTIFY;

  public BootstrapNav ()
  {
    this (EBootstrapNavType.DEFAULT);
  }

  public BootstrapNav (@Nonnull final EBootstrapNavType eNavType)
  {
    super (BootstrapNavItem.class);
    setType (eNavType);
  }

  @Override
  protected final BootstrapNavItem createEmptyItem ()
  {
    return new BootstrapNavItem ();
  }

  @Nonnull
  public final EBootstrapNavType getNavType ()
  {
    return m_eNavType;
  }

  @Nonnull
  public final BootstrapNav setType (@Nonnull final EBootstrapNavType eNavType)
  {
    ValueEnforcer.notNull (eNavType, "NavType");
    m_eNavType = eNavType;
    return this;
  }

  public final boolean isFill ()
  {
    return m_bFill;
  }

  @Nonnull
  public final BootstrapNav setFill (final boolean bFill)
  {
    m_bFill = bFill;
    return this;
  }

  public final boolean isJustified ()
  {
    return m_bJustified;
  }

  @Nonnull
  public final BootstrapNav setJustified (final boolean bJustified)
  {
    m_bJustified = bJustified;
    return this;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (CBootstrapCSS.NAV);
    addClass (m_eNavType.getCSSClass ());
    if (m_bFill)
      addClass (CBootstrapCSS.NAV_FILL);
    if (m_bJustified)
      addClass (CBootstrapCSS.NAV_JUSTIFIED);
  }
}
