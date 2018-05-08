/**
 * Copyright (C) 2015-2018 Philip Helger (www.helger.com)
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
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.hc.html.sections.AbstractHCNav;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.html.hc.html.textlevel.HCSpan;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.utils.EBootstrapBackgroundType;

/**
 * Bootstrap 4 NavBar
 *
 * @author Philip Helger
 */
public class BootstrapNavbar extends AbstractHCNav <BootstrapNavbar>
{
  public static final EBootstrapNavbarColorSchemeType DEFAULT_COLOR_SCHEME = EBootstrapNavbarColorSchemeType.LIGHT;
  public static final EBootstrapNavbarExpandType DEFAULT_EXPAND = EBootstrapNavbarExpandType.EXPAND_LG;

  private EBootstrapNavbarColorSchemeType m_eColorScheme = DEFAULT_COLOR_SCHEME;
  private EBootstrapNavbarExpandType m_eExpand = DEFAULT_EXPAND;
  private EBootstrapBackgroundType m_eBackground;

  public BootstrapNavbar ()
  {}

  @Nonnull
  public final EBootstrapNavbarColorSchemeType getColorScheme ()
  {
    return m_eColorScheme;
  }

  @Nonnull
  public final BootstrapNavbar setColorScheme (@Nonnull final EBootstrapNavbarColorSchemeType eColorScheme)
  {
    ValueEnforcer.notNull (eColorScheme, "ColorScheme");
    m_eColorScheme = eColorScheme;
    return this;
  }

  @Nonnull
  public final EBootstrapNavbarExpandType getExpand ()
  {
    return m_eExpand;
  }

  @Nonnull
  public final BootstrapNavbar setExpand (@Nonnull final EBootstrapNavbarExpandType eExpand)
  {
    ValueEnforcer.notNull (eExpand, "Expand");
    m_eExpand = eExpand;
    return this;
  }

  @Nullable
  public final EBootstrapBackgroundType getBackground ()
  {
    return m_eBackground;
  }

  @Nonnull
  public final BootstrapNavbar setBackground (@Nullable final EBootstrapBackgroundType eBackground)
  {
    m_eBackground = eBackground;
    return this;
  }

  @Nonnull
  public static IHCElementWithChildren <?> createBrand (@Nonnull final IHCNode aLabel, @Nullable final ISimpleURL aURL)
  {
    IHCElementWithChildren <?> aBrand;
    if (aURL != null)
      aBrand = new HCA ().setHref (aURL).addChild (aLabel);
    else
      if (aLabel instanceof IHCElementWithChildren <?>)
        aBrand = (IHCElementWithChildren <?>) aLabel;
      else
        aBrand = new HCSpan ().addChild (aLabel);

    aBrand.addClass (CBootstrapCSS.NAVBAR_BRAND);
    return aBrand;
  }

  @Nonnull
  public final BootstrapNavbar addBrand (@Nonnull final IHCNode aLabel, @Nullable final ISimpleURL aURL)
  {
    addChild (createBrand (aLabel, aURL));
    return this;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (CBootstrapCSS.NAVBAR);
    addClass (m_eColorScheme);
    addClass (m_eExpand);
    addClass (m_eBackground);
  }
}