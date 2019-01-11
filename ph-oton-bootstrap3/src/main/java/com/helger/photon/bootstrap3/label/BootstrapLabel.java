/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.label;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.photon.bootstrap3.CBootstrapCSS;

/**
 * Bootstrap label
 *
 * @author Philip Helger
 */
public class BootstrapLabel extends AbstractHCDiv <BootstrapLabel>
{
  private EBootstrapLabelType m_eType;

  public BootstrapLabel ()
  {
    this (EBootstrapLabelType.DEFAULT);
  }

  public BootstrapLabel (@Nonnull final EBootstrapLabelType eType)
  {
    super ();
    setType (eType);
  }

  @Nonnull
  public EBootstrapLabelType getType ()
  {
    return m_eType;
  }

  @Nonnull
  public BootstrapLabel setType (@Nonnull final EBootstrapLabelType eType)
  {
    m_eType = ValueEnforcer.notNull (eType, "Type");
    return this;
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClasses (CBootstrapCSS.LABEL, m_eType);
  }
}
