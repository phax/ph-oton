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
package com.helger.photon.bootstrap4.base;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.photon.bootstrap4.utils.BootstrapBorderBuilder;
import com.helger.photon.bootstrap4.utils.BootstrapDisplayBuilder;
import com.helger.photon.bootstrap4.utils.BootstrapSpacingBuilder;
import com.helger.photon.bootstrap4.utils.EBootstrapBackgroundType;
import com.helger.photon.bootstrap4.utils.EBootstrapTextAlignType;

/**
 * Base class for common bootstrap DIVs.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
@NotThreadSafe
public abstract class AbstractBootstrapDiv <IMPLTYPE extends AbstractBootstrapDiv <IMPLTYPE>> extends AbstractHCDiv <IMPLTYPE>
{
  private BootstrapSpacingBuilder m_aPadding;
  private BootstrapSpacingBuilder m_aMargin;
  private BootstrapDisplayBuilder m_aDisplay;
  private BootstrapBorderBuilder m_aBorder;
  private EBootstrapTextAlignType m_eTextAlign;
  private EBootstrapBackgroundType m_eBackground;

  public AbstractBootstrapDiv ()
  {}

  @Nullable
  public final BootstrapSpacingBuilder getPadding ()
  {
    return m_aPadding;
  }

  @Nonnull
  public final IMPLTYPE setPadding (@Nullable final BootstrapSpacingBuilder aPadding)
  {
    m_aPadding = aPadding;
    return thisAsT ();
  }

  @Nullable
  public final BootstrapSpacingBuilder getMargin ()
  {
    return m_aMargin;
  }

  @Nonnull
  public final IMPLTYPE setMargin (@Nullable final BootstrapSpacingBuilder aMargin)
  {
    m_aMargin = aMargin;
    return thisAsT ();
  }

  @Nullable
  public final BootstrapDisplayBuilder getDisplay ()
  {
    return m_aDisplay;
  }

  @Nonnull
  public final IMPLTYPE setDisplay (@Nullable final BootstrapDisplayBuilder aDisplay)
  {
    m_aDisplay = aDisplay;
    return thisAsT ();
  }

  @Nullable
  public final BootstrapBorderBuilder getBorder ()
  {
    return m_aBorder;
  }

  @Nonnull
  public final IMPLTYPE setBorder (@Nullable final BootstrapBorderBuilder aBorder)
  {
    m_aBorder = aBorder;
    return thisAsT ();
  }

  @Nullable
  public final EBootstrapTextAlignType getTextAlign ()
  {
    return m_eTextAlign;
  }

  @Nonnull
  public final IMPLTYPE setTextAlign (@Nullable final EBootstrapTextAlignType eTextAlign)
  {
    m_eTextAlign = eTextAlign;
    return thisAsT ();
  }

  @Nullable
  public final EBootstrapBackgroundType getBackground ()
  {
    return m_eBackground;
  }

  @Nonnull
  public final IMPLTYPE setBackground (@Nullable final EBootstrapBackgroundType eBackground)
  {
    m_eBackground = eBackground;
    return thisAsT ();
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (m_aPadding);
    addClass (m_aMargin);
    addClass (m_aDisplay);
    if (m_aBorder != null)
      m_aBorder.applyTo (this);
    addClass (m_eTextAlign);
    addClass (m_eBackground);
  }
}
