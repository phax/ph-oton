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
package com.helger.photon.bootstrap3.button;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.html.hc.base.AbstractHCButton;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.photon.bootstrap3.CBootstrapCSS;
import com.helger.photon.uicore.icon.IIcon;

/**
 * Bootstrap button based on an &lt;button&gt;
 *
 * @author Philip Helger
 */
public class BootstrapButton extends AbstractHCButton <BootstrapButton>
{
  private EBootstrapButtonType m_eButtonType = EBootstrapButtonType.DEFAULT;
  private EBootstrapButtonSize m_eButtonSize = EBootstrapButtonSize.DEFAULT;
  private IIcon m_aIcon;

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

  public BootstrapButton (@Nonnull final EBootstrapButtonType eButtonType,
                          @Nonnull final EBootstrapButtonSize eButtonSize)
  {
    addClass (CBootstrapCSS.BTN);
    setButtonType (eButtonType);
    setButtonSize (eButtonSize);
  }

  @Nonnull
  public EBootstrapButtonType getButtonType ()
  {
    return m_eButtonType;
  }

  @Nonnull
  public BootstrapButton setButtonType (@Nonnull final EBootstrapButtonType eButtonType)
  {
    m_eButtonType = ValueEnforcer.notNull (eButtonType, "ButtonType");
    return this;
  }

  @Nullable
  public EBootstrapButtonSize getButtonSize ()
  {
    return m_eButtonSize;
  }

  @Nonnull
  public BootstrapButton setButtonSize (@Nonnull final EBootstrapButtonSize eButtonSize)
  {
    m_eButtonSize = ValueEnforcer.notNull (eButtonSize, "ButtonSize");
    return this;
  }

  @Nullable
  public IIcon getIcon ()
  {
    return m_aIcon;
  }

  @Nonnull
  public BootstrapButton setIcon (@Nullable final IIcon aIcon)
  {
    m_aIcon = aIcon;
    return this;
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void internalBeforeConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.internalBeforeConvertToNode (aConversionSettings);

    // apply type and size
    addClasses (getButtonType (), getButtonSize ());

    // apply icon
    if (getIcon () != null)
    {
      final boolean bAddSeparator = hasChildren ();
      addChild (0, getIcon ().getAsNode ());
      if (bAddSeparator)
      {
        // Add spacer
        addChild (1, new HCTextNode (" "));
      }
    }
  }
}
