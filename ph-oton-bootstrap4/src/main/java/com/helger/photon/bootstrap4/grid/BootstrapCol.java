/*
 * Copyright (C) 2018-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.grid;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.photon.bootstrap4.base.AbstractBootstrapDiv;

public class BootstrapCol extends AbstractBootstrapDiv <BootstrapCol>
{
  private EBootstrapColOrder m_eOrder;

  public BootstrapCol ()
  {}

  public BootstrapCol (@Nullable final EBootstrapColOrder eOrder)
  {
    setOrder (eOrder);
  }

  @Nonnull
  public final BootstrapCol setOrder (@Nullable final EBootstrapColOrder eOrder)
  {
    m_eOrder = eOrder;
    return this;
  }

  @Nullable
  public final EBootstrapColOrder getOrder ()
  {
    return m_eOrder;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (m_eOrder);
  }
}
