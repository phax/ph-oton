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
package com.helger.photon.bootstrap4.breadcrumb;

import javax.annotation.Nonnull;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.AbstractHCLI;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Single item inside a {@link BootstrapBreadcrumbList}.
 *
 * @author Philip Helger
 */
public class BootstrapBreadcrumbItem extends AbstractHCLI <BootstrapBreadcrumbItem>
{
  public static final boolean DEFAULT_ACTIVE = false;

  private boolean m_bActive = DEFAULT_ACTIVE;

  public BootstrapBreadcrumbItem ()
  {}

  public final boolean isActive ()
  {
    return m_bActive;
  }

  @Nonnull
  public final BootstrapBreadcrumbItem setActive (final boolean bActive)
  {
    m_bActive = bActive;
    return this;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (CBootstrapCSS.BREADCRUMB_ITEM);
    if (m_bActive)
      addClass (CBootstrapCSS.ACTIVE);
  }
}
