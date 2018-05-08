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
package com.helger.photon.bootstrap4.utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.base.AbstractBootstrapDiv;

/**
 * Bootstrap responsive embed. See
 * https://getbootstrap.com/docs/4.1/utilities/embed/
 * 
 * @author Philip Helger
 */
public class BootstrapEmbed extends AbstractBootstrapDiv <BootstrapEmbed>
{
  private EBootstrapEmbedAspectRatio m_eAspectRatio;

  public BootstrapEmbed (@Nonnull final EBootstrapEmbedAspectRatio eAspectRatio)
  {
    setAspectRatio (eAspectRatio);
  }

  @Nullable
  public EBootstrapEmbedAspectRatio getAspectRatio ()
  {
    return m_eAspectRatio;
  }

  @Nonnull
  public final BootstrapEmbed setAspectRatio (@Nonnull final EBootstrapEmbedAspectRatio eAspectRatio)
  {
    ValueEnforcer.notNull (eAspectRatio, "AspectRatio");
    m_eAspectRatio = eAspectRatio;
    return this;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClasses (CBootstrapCSS.EMBED_RESPONSIVE, m_eAspectRatio);
  }
}