/**
 * Copyright (C) 2018-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.button;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.forms.AbstractHCButton;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.uicore.icon.IIcon;

/**
 * Bootstrap button based on an &lt;button&gt;
 *
 * @author Philip Helger
 */
public class BootstrapButton extends AbstractHCButton <BootstrapButton>
{
  public static final ICSSClassProvider CSS_CLASS_BTN_WITH_ICON = DefaultCSSClassProvider.create ("btn-with-icon");

  private EBootstrapButtonType m_eButtonType = EBootstrapButtonType.DEFAULT;
  private EBootstrapButtonSize m_eButtonSize = EBootstrapButtonSize.DEFAULT;
  private IIcon m_aIcon;
  private boolean m_bBlockLevel = false;

  public BootstrapButton ()
  {
    this (EBootstrapButtonType.DEFAULT, EBootstrapButtonSize.DEFAULT);
  }

  public BootstrapButton (@Nonnull final EBootstrapButtonType eButtonType)
  {
    this (eButtonType, EBootstrapButtonSize.DEFAULT);
  }

  public BootstrapButton (@Nonnull final EBootstrapButtonSize eButtonSize)
  {
    this (EBootstrapButtonType.DEFAULT, eButtonSize);
  }

  public BootstrapButton (@Nonnull final EBootstrapButtonType eButtonType, @Nonnull final EBootstrapButtonSize eButtonSize)
  {
    addClass (CBootstrapCSS.BTN);
    setButtonType (eButtonType);
    setButtonSize (eButtonSize);
  }

  @Nonnull
  public final EBootstrapButtonType getButtonType ()
  {
    return m_eButtonType;
  }

  @Nonnull
  public final BootstrapButton setButtonType (@Nonnull final EBootstrapButtonType eButtonType)
  {
    m_eButtonType = ValueEnforcer.notNull (eButtonType, "ButtonType");
    return this;
  }

  @Nullable
  public final EBootstrapButtonSize getButtonSize ()
  {
    return m_eButtonSize;
  }

  @Nonnull
  public final BootstrapButton setButtonSize (@Nonnull final EBootstrapButtonSize eButtonSize)
  {
    m_eButtonSize = ValueEnforcer.notNull (eButtonSize, "ButtonSize");
    return this;
  }

  public final boolean isBlockLevel ()
  {
    return m_bBlockLevel;
  }

  @Nonnull
  public final BootstrapButton setBlockLevel (final boolean bBlockLevel)
  {
    m_bBlockLevel = bBlockLevel;
    return this;
  }

  @Nullable
  public final IIcon getIcon ()
  {
    return m_aIcon;
  }

  @Nonnull
  public final BootstrapButton setIcon (@Nullable final IIcon aIcon)
  {
    m_aIcon = aIcon;
    return this;
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    // apply type and size
    addClasses (getButtonType (), getButtonSize ());

    if (m_bBlockLevel)
      addClass (CBootstrapCSS.BTN_BLOCK);

    // apply icon
    if (m_aIcon != null)
    {
      // For CSS styling
      addClass (CSS_CLASS_BTN_WITH_ICON);
      final boolean bAddSeparator = hasChildren ();
      addChildAt (0, m_aIcon.getAsNode ());
      if (bAddSeparator)
      {
        // Add spacer
        addChildAt (1, new HCTextNode (" "));
      }
    }
  }
}
