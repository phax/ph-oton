/*
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
package com.helger.photon.bootstrap4.badge;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.textlevel.AbstractHCA;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Bootstrap4 badge link.
 *
 * @author Philip Helger
 */
public class BootstrapBadgeLink extends AbstractHCA <BootstrapBadgeLink>
{
  private EBootstrapBadgeType m_eBadgeType;
  private boolean m_bPill = BootstrapBadge.DEFAULT_PILL;

  public BootstrapBadgeLink ()
  {}

  public BootstrapBadgeLink (@Nullable final EBootstrapBadgeType eType)
  {
    setBadgeType (eType);
  }

  @Nonnull
  public EBootstrapBadgeType getBadgeType ()
  {
    return m_eBadgeType;
  }

  @Nonnull
  public final BootstrapBadgeLink setBadgeType (@Nullable final EBootstrapBadgeType eType)
  {
    m_eBadgeType = eType;
    return this;
  }

  public boolean isPill ()
  {
    return m_bPill;
  }

  @Nonnull
  public final BootstrapBadgeLink setPill (final boolean bPill)
  {
    m_bPill = bPill;
    return this;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClasses (CBootstrapCSS.BADGE, m_eBadgeType);
    if (m_bPill)
      addClass (CBootstrapCSS.BADGE_PILL);
  }

  @Nullable
  public static BootstrapBadgeLink createOnDemand (@Nullable final IHCNode aNode)
  {
    return aNode == null ? null : new BootstrapBadgeLink ().addChild (aNode);
  }

  @Nonnull
  public static BootstrapBadgeLink createNumeric (final int nValue)
  {
    return new BootstrapBadgeLink ().addChild (Integer.toString (nValue));
  }

  @Nonnull
  public static BootstrapBadgeLink createNumeric (final long nValue)
  {
    return new BootstrapBadgeLink ().addChild (Long.toString (nValue));
  }
}
